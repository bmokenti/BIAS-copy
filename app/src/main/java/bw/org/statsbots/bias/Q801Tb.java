package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;
public class Q801Tb extends AppCompatActivity implements Serializable {


    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, rbtnb1, rbtnb2, rbtnb3, rbtnd1, rbtnd2, rbtnd3, rbtnd4, rbtnd5, rbtnd6, rbtnd7, rbtnd8, rbtnd10, rbtnd11, rbtnd12, rbtndOther, rbtne1,
            rbtne2, rbtne3, rbtne4, rbtne5, rbtne6, rbtne7, rbtne8, rbtne9, rbtneOther, rbtnf1, rbtnf2, rbtnf3, rbtnf4, rbtnf5;
    protected RadioGroup rg, rga, rgb, rgc, rgd, rge, rgf;
    protected EditText edtcmnths, edtcyear, edtdother, edteother;
    protected TextView t801a, t801b, t801c, t801d, t801e, t801f;
    protected CheckBox chkc99, chkc9999;
    protected DatabaseHelper myDB;
    protected RadioButton selectedRbtn, selectedRbtnb, selectedRbtnd, selectedRbtne, selectedRbtnf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q801_tb);


        setTitle("Q801: HIV TESTING(15-64 Years");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q801_1);
        rbtn2 = (RadioButton) findViewById(R.id.q801_2);
        rbtna1 = (RadioButton) findViewById(R.id.q801a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q801a_2);


        chkc99 = (CheckBox) findViewById(R.id.q801c_99);
        chkc9999 = (CheckBox) findViewById(R.id.q801c_9999);

        edtcmnths = (EditText) findViewById(R.id.q801c_months);
        edtcyear = (EditText) findViewById(R.id.q801c_year);
        edtdother = (EditText) findViewById(R.id.q801d_other1);
        edteother = (EditText) findViewById(R.id.q801e_other1);

        rbtnf1 = (RadioButton) findViewById(R.id.q801f_1);
        rbtnf2 = (RadioButton) findViewById(R.id.q801f_2);
        rbtnf3 = (RadioButton) findViewById(R.id.q801f_3);
        rbtnf4 = (RadioButton) findViewById(R.id.q801f_4);
        rbtnf5 = (RadioButton) findViewById(R.id.q801f_9);

        t801a = (TextView) findViewById(R.id.q801atxt);
        t801b = (TextView) findViewById(R.id.q801btxt);
        t801c = (TextView) findViewById(R.id.q801ctxt);

        t801f = (TextView) findViewById(R.id.q801ftxt);


        rg = (RadioGroup) findViewById(R.id.q801radioGroup);
        rga = (RadioGroup) findViewById(R.id.q801aradioGroup);

        rgf = (RadioGroup) findViewById(R.id.q801fradioGroup);

        final int selectedId = rg.getCheckedRadioButtonId();


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        //  int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(), individual.getBatch(), individual.getSRNO());
        individual = ind;


        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(), individual.getBatch());
        thisHous.get(0).getHIVTB40();

        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

        final List<PersonRoster> roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p : roster
        ) {
            if (p.getSRNO() == ind.getSRNO()) {
                p1 = p;
                break;
            }


        }

        if (Integer.valueOf(individual.getQ102()) >= 65 &&
                (sample.getStatusCode().equals("3") ||
                        (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1"))
                        || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0")))) {

            Intent intent = new Intent(Q801Tb.this, q1101.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }


//        rgd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if(i == R.id.q801d_other)
//                {
//                    // is checked
//                    edtdother.setVisibility(View.VISIBLE);
//                }
//                else
//                {
//                    // not checked
//                    edtdother.setVisibility(View.INVISIBLE);
//                    edtdother.setText("");
//                }
//            }
//        });


//        rge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if(i == R.id.q801e_other)
//                {
//                    // is checked
//                    edteother.setVisibility(View.VISIBLE);
//                }
//                else
//                {
//                    // not checked
//                    edteother.setVisibility(View.INVISIBLE);
//                    edteother.setText("");
//                }
//            }
//        });


        RadioButton[] bt = new RadioButton[2];
        for (int f = 0; f < rg.getChildCount(); f++) {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton) {
                bt[f] = ((RadioButton) o);
                if (ind.getQ801() != null && !ind.getQ801().equals("")) {
                    if (Integer.parseInt(ind.getQ801()) == f + 1) {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }


        RadioButton[] bta = new RadioButton[2];
        for (int f = 0; f < rga.getChildCount(); f++) {
            View o = rga.getChildAt(f);
            if (o instanceof RadioButton) {
                bta[f] = ((RadioButton) o);
                if (ind.getQ801a() != null && !ind.getQ801a().equals("")) {
                    if (Integer.parseInt(ind.getQ801a()) == f + 1) {
                        RadioButton radioButton = bta[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }


        if (ind.getQ801cMonth() != null) {
            edtcmnths.setText(ind.getQ801cMonth());
        }

        if (ind.getQ801cYear() != null) {
            edtcyear.setText(ind.getQ801cYear());
        }

        if (ind.getQ801cMonth() == "99") {
            chkc99.setChecked(true);
        }
        if (ind.getQ801cYear() == "9999") {
            chkc9999.setChecked(true);
        }


        RadioButton[] btf = new RadioButton[5];
        for (int f = 0; f < rgf.getChildCount(); f++) {
            View o = rgf.getChildAt(f);
            if (o instanceof RadioButton) {
                btf[f] = ((RadioButton) o);
                if (ind.getQ801f() != null && !ind.getQ801f().equals("")) {
                    if (Integer.parseInt(ind.getQ801f()) == f + 1) {
                        RadioButton radioButton = btf[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }


        if (individual.getQ801() != null && individual.getQ801().equals("2"))

            {
                rbtna1.setEnabled(false);
                rbtna2.setEnabled(false);
                rbtna1.setChecked(false);
                rbtna2.setChecked(false);

// set b controls.
                edtcmnths.setEnabled(false);
                edtcyear.setEnabled(false);
                chkc99.setEnabled(false);
                chkc9999.setEnabled(false);

                edtcmnths.setText("00");
                edtcyear.setText("0000");
                chkc99.setChecked(false);
                chkc9999.setChecked(false);

// set d controls

                rbtnf1.setEnabled(false);
                rbtnf2.setEnabled(false);
                rbtnf3.setEnabled(false);
                rbtnf4.setEnabled(false);
                rbtnf5.setEnabled(false);

                rbtnf1.setChecked(false);
                rbtnf2.setChecked(false);
                rbtnf3.setChecked(false);
                rbtnf4.setChecked(false);
                rbtnf5.setChecked(false);

                t801a.setTextColor(Color.LTGRAY);
                t801c.setTextColor(Color.LTGRAY);
                t801f.setTextColor(Color.LTGRAY);
            }



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q801_1  )
                {

                    rbtna1.setEnabled(true);
                    rbtna2.setEnabled(true);



                    edtcmnths.setEnabled(true);
                    edtcyear.setEnabled(true);
                    chkc99.setEnabled(true);
                    chkc9999.setEnabled(true);


                    rbtnf1.setEnabled(true);
                    rbtnf2.setEnabled(true);
                    rbtnf3.setEnabled(true);
                    rbtnf4.setEnabled(true);
                    rbtnf5.setEnabled(true);

                    t801a.setTextColor(Color.BLACK);
                    t801c.setTextColor(Color.BLACK);
                    t801f.setTextColor(Color.BLACK);
                }

                else {
                    rbtna1.setEnabled(false);
                    rbtna2.setEnabled(false);
                    rbtna1.setChecked(false);
                    rbtna2.setChecked(false);

// set b controls.

                    edtcmnths.setEnabled(false);
                    edtcyear.setEnabled(false);
                    chkc99.setEnabled(false);
                    chkc9999.setEnabled(false);

                    edtcmnths.setText("00");
                    edtcyear.setText("0000");
                    chkc99.setChecked(false);
                    chkc9999.setChecked(false);

// set d controls



// set e controls

//set f controls
                    rbtnf1.setEnabled(false);
                    rbtnf2.setEnabled(false);
                    rbtnf3.setEnabled(false);
                    rbtnf4.setEnabled(false);
                    rbtnf5.setEnabled(false);

                    rbtnf1.setChecked(false);
                    rbtnf2.setChecked(false);
                    rbtnf3.setChecked(false);
                    rbtnf4.setChecked(false);
                    rbtnf5.setChecked(false);

                    t801a.setTextColor(Color.LTGRAY);
                    t801c.setTextColor(Color.LTGRAY);
                    t801f.setTextColor(Color.LTGRAY);
                }
            }

        });



        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(Q801Tb.this, "Q801: Error ", " Have you ever been tested for HIV?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);


                } else {

                    int selectedIda = rga.getCheckedRadioButtonId();
                    final RadioButton selectedRbtna = (RadioButton) findViewById(selectedIda);


                    if (selectedRbtna == null && rbtn1.isChecked()) {
                        lib.showError(Q801Tb.this, "Q801a: ERROR", "Have you tested for HIV in the past 12 months?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    } else {


                            if (edtcmnths.length() == 0 && !chkc99.isChecked() && rbtn1.isChecked()) {
                                lib.showError(Q801Tb.this, "Q801c: ERROR: month", "What month and year was your last HIV Test?, " +
                                        "Please provide month or select Dont know month");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);

                            } else {
                                if (edtcyear.length() == 0 && !chkc9999.isChecked() && rbtn1.isChecked()) {
                                    lib.showError(Q801Tb.this, "Q801c: ERROR: year", "What month and year was your last HIV Test?, " +
                                            "Please provide year or select Dont know year");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);

                                }   else {

                                                    int selectedIdf = rgf.getCheckedRadioButtonId();
                                                    selectedRbtnf = (RadioButton) findViewById(selectedIdf);
                                                    //   Log.d("results", selectedRbtnf.getText().toString().substring(0, 1));

                                                    if (selectedRbtnf == null && (rbtn1.isChecked())) {
                                                        lib.showError(Q801Tb.this, "Q801Tbf: ERROR", "What was the result of your last HIV test?");
                                                        /**
                                                         * VIBRATE DEVICE
                                                         */
                                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                        vibs.vibrate(100);

                                                    }  else {
                                                            //above 64 and tb oly

                                                            if ((rbtn1.isChecked()) ) {

                                                                individual.setQ801(selectedRbtn.getText().toString().substring(0, 1));
                                                                individual.setQ801a(selectedRbtna.getText().toString().substring(0, 1));


                                                                if (edtcmnths.getText().toString().length() == 0) {
                                                                    individual.setQ801cMonth("00");
                                                                } else if (edtcmnths.getText().toString().length() == 1) {
                                                                    individual.setQ801cMonth("0" + edtcmnths.getText().toString());
                                                                } else {
                                                                    individual.setQ801cMonth(edtcmnths.getText().toString());
                                                                }

                                                                if (edtcyear.getText().toString().length() == 0) {
                                                                    individual.setQ801cYear("0000");
                                                                } else if (edtcyear.getText().toString().length() == 2) {
                                                                    individual.setQ801cYear("00" + edtcyear.getText().toString());
                                                                } else {
                                                                    individual.setQ801cYear(edtcyear.getText().toString());
                                                                }

                                                                individual.setQ801f(selectedRbtnf.getText().toString().substring(0, 1));

                                                                individual.setQ802(null);
                                                                individual.setQ802a(null);
                                                                individual.setQ802aOther(null);
                                                                individual.setQ803(null);
                                                                individual.setQ803Other(null);
                                                                individual.setQ804(null);
                                                                individual.setQ804Other(null);
                                                                individual.setQ901(null);
                                                                individual.setQ901a(null);
                                                                individual.setQ901aOther(null);
                                                                individual.setQ902Month("00");
                                                                individual.setQ902Year("0000");
                                                                individual.setQ903a(null);
                                                                individual.setQ903b(null);
                                                                individual.setQ903c(null);
                                                                individual.setQ903d(null);
                                                                individual.setQ903e(null);
                                                                individual.setQ903f(null);
                                                                individual.setQ903g(null);
                                                                individual.setQ903h(null);


                                                                myDB = new DatabaseHelper(Q801Tb.this);
                                                                myDB.onOpen(myDB.getReadableDatabase());
                                                                myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                                                myDB.close();
                                                                // myDB.updateInd("Q801f", individual.getAssignmentID(), individual.getBatch(), individual.getQ801f(), String.valueOf(individual.getSRNO()));

                                                                Intent intent = new Intent(Q801Tb.this, q904.class);
                                                                intent.putExtra("Individual", individual);
                                                                startActivity(intent);

                                                            } else {
                                                                //NO and above 64 and tb only



                                                                    individual.setQ801(selectedRbtn.getText().toString().substring(0, 1));
                                                                    // individual.setQ801f("0");
                                                                    individual.setQ801a(null);
                                                                    individual.setQ801cMonth("00");
                                                                    individual.setQ801cYear("0000");
                                                                    individual.setQ801f(null);

                                                                    individual.setQ802(null);
                                                                    individual.setQ802a(null);
                                                                    individual.setQ802aOther(null);
                                                                    individual.setQ803(null);
                                                                    individual.setQ803Other(null);
                                                                    individual.setQ804(null);
                                                                    individual.setQ804Other(null);

                                                                    individual.setQ901(null);
                                                                    individual.setQ901a(null);
                                                                    individual.setQ901aOther(null);
                                                                    individual.setQ902Month("00");
                                                                    individual.setQ902Year("0000");
                                                                    individual.setQ903a(null);
                                                                    individual.setQ903b(null);
                                                                    individual.setQ903c(null);
                                                                    individual.setQ903d(null);
                                                                    individual.setQ903e(null);
                                                                    individual.setQ903f(null);
                                                                    individual.setQ903g(null);
                                                                    individual.setQ903h(null);

                                                                    myDB = new DatabaseHelper(Q801Tb.this);
                                                                    myDB.onOpen(myDB.getReadableDatabase());
                                                                    myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                                                    myDB.close();

                                                                    //myDB.updateInd("Q801f", individual.getAssignmentID(), individual.getBatch(), null, String.valueOf(individual.getSRNO()));
                                                                    // myDB.updateInd("Q202",individual.getAssignmentID(),individual.getBatch(),null,String.valueOf(individual.getSRNO()));

                                                                    Intent intent = new Intent(Q801Tb.this, q904.class);
                                                                    intent.putExtra("Individual", individual);
                                                                    startActivity(intent);


                                                    }

                                            }
                                        }
                                    }
                                }
                            }
            }
        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                finish();
                Intent intent = new Intent(Q801Tb.this, q705.class);
                intent.putExtra("Individual", individual);
                startActivity(intent);

            }


        });
    }


    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q801_1:
                if (checked ) {


                } else {

                }
                break;

            case R.id.q801_2:
                if (checked) {
                    // set a controls

                }
                else
                {

                }

                break;

            case R.id.q801a_1:
                if (checked) {

                }
                // Put some meat on the sandwich
                else {


                }
                break;

            case R.id.q801a_2:
                if (checked) {

                }
                // Put some meat on the sandwich
                else {
                    // Remove the meat
                }
                break;

            case R.id.q801d_other:
                if (checked) {
                    //edtdother.setVisibility(View.VISIBLE);
                }
                // Put some meat on the sandwich
                else {
                    //edtdother.setVisibility(View.INVISIBLE);
                    //  edtdother.setText("");

                }
                break;

            case R.id.q801e_other:
                if (checked) {
                    //  edteother.setVisibility(View.VISIBLE);
                }
                // Put some meat on the sandwich
                else {
                    // edteother.setVisibility(View.INVISIBLE);
                    // edteother.setText("");
                }
                break;

            default:

                break;

        }

    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q801c_99:
                if (checked) {
                    edtcmnths.setEnabled(false);
                    edtcmnths.setText("99");




                } else {
                    edtcmnths.setEnabled(true);
                    edtcmnths.setText("");
                }
                break;
            case R.id.q801c_9999:
                if (checked) {

                    edtcyear.setEnabled(false);



                    edtcyear.setText("9999");


                } else {
                    edtcyear.setEnabled(true);



                    edtcyear.setText("");
                }

        }
    }

    //   thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

//    final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
//        for (PersonRoster p: roster
//        ) {
//        if (p.getSRNO() == ind.getSRNO()){
//            p1 = p;
//            break;
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intervie_control, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {

            case R.id.pause:
                // Show the settings activity
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("[Demo!] Are you sure you want to pause the interview");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(getApplicationContext(), started_household.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Q801Tb.this).toBundle());

                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

