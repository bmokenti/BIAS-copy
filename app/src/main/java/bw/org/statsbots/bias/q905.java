package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.io.Serializable;
import java.util.List;

public class q905 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected DatabaseHelper myDB;
    protected  Individual individual;
    protected EditText edtdays, edtaother;
    protected RadioButton rbtna1, rbtna2, rbtna3, rbtna4, rbtna5, rbtna6, rbtnaother, selectedRbtna;
    protected RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q905);


        setTitle("Q905: HIV SUPPORT, CARE AND TREATMENT");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);

        edtdays = (EditText) findViewById(R.id.q905_days);

        ck1txt = (CheckBox) findViewById(R.id.q905_99);
        ck2txt = (CheckBox) findViewById(R.id.q905_00);

        rbtna1 = (RadioButton) findViewById(R.id.q905a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q905a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q905a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q905a_4);

        rbtna5 = (RadioButton) findViewById(R.id.q905a_5);
        rbtna6 = (RadioButton) findViewById(R.id.q905a_6);
        rbtnaother = (RadioButton) findViewById(R.id.q905a_other);

        edtaother = (EditText) findViewById(R.id.q905a_other1);


        rg = (RadioGroup) findViewById(R.id.q905rGroup);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        //int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();
        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }
        if( sample.getStatusCode().equals("3") || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0"))
                || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") && Integer.parseInt(individual.getQ102()) >64)  )
        {


            individual.setQ905(null);
            individual.setQ905a(null);
            individual.setQ905aOther(null);

            individual.setQ1001(null);
            individual.setQ1002(null);
            individual.setQ1002a_1(null);
            individual.setQ1002a_2(null);
            individual.setQ1002a_3(null);
            individual.setQ1002a_4(null);
            individual.setQ1002a_5(null);
            individual.setQ1002a_6(null);
            individual.setQ1002a_7(null);
            individual.setQ1002a_8(null);
            // individual.setQ1002a_9(null);
            individual.setQ1002a_10(null);
            individual.setQ1002a_11(null);
            individual.setQ1002a_12(null);
            individual.setQ1002a_13(null);
            individual.setQ1002a_14(null);
            individual.setQ1002a_15(null);
            individual.setQ1002a_16(null);
            individual.setQ1002a_17(null);
            individual.setQ1002a_18(null);
            individual.setQ1002a_Other(null);
            individual.setQ1002b(null);
            individual.setQ1002b_Other(null);
            individual.setQ1003(null);
            individual.setQ1004_Year("0000");
            individual.setQ1004_Month("00");
            individual.setQ1004_Day("00");
            individual.setQ1004a(null);
            individual.setQ1004a(null);
            individual.setQ1004b(null);
            individual.setQ1004b_Other(null);
            individual.setQ1005(null);
            individual.setQ1005a(null);
            individual.setQ1006(null);
            individual.setQ1007(null);
            individual.setQ1007a(null);
            individual.setQ1008(null);
            individual.setQ1008a(null);
            individual.setQ1008a_Other(null);
            individual.setQ1009(null);
            individual.setQ1009a(null);
            individual.setQ1010(null);
            individual.setQ1010_Other(null);
            individual.setQ1011(null);
            individual.setQ1011_Other(null);
            individual.setQ1012_Year("00");
            individual.setQ1012_Month("00");
            individual.setQ1012_Week("00");
            individual.setQ1013(null);
            individual.setQ1014(null);
            individual.setQ1014a(null);
            individual.setQ1014b(null);
            individual.setQ1015(null);
            individual.setQ1015a(null);
            individual.setQ1015b(null);
            individual.setQ1016(null);
            individual.setQ1017(null);

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q905.this, q1101.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

        //Log.d("results", selectedRbtnf.getText().toString().substring(0, 1));
        //Log.d("eastatus",  thisHous.get(0).getHIVTB40() );

        if( ind.getQ905() != null)
        {
            edtdays.setText(ind.getQ905());
        }


//        if( ind.getQ905().equals("99") )
//        {
//            ck1txt.setChecked(true);
//        }
//
//        if( ind.getQ905().equals("00"))
//        {
//            ck2txt.setChecked(true);
//        }



        if (ind.getQ905aOther() != null ) {

            if (ind.getQ905a() != null && ind.getQ905a().equals("O")) {
                rbtnaother.setChecked(true);
                edtaother.setText(ind.getQ905aOther());
            }
        }
        else {
        RadioButton[] bt = new RadioButton[7];
        for(int f=0;f<rg.getChildCount();f++) {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton) {
                bt[f] = ((RadioButton) o);
                if (ind.getQ905a() != null ) {
                    if (!ind.getQ905a().equals("")) {
                        if (Integer.parseInt(ind.getQ905a()) == f + 1) {
                            RadioButton radioButton = bt[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }
        }

//
//        if( ind.getQ905aOther() != null)
//        {
//            edtaother.setText(ind.getQ905aOther());
//        }




        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!ck1txt.isChecked() && !ck2txt.isChecked() && ((edtdays.length() == 0 || Integer.valueOf(edtdays.getText().toString()) >= 30))) {

                    lib.showError(q905.this, "Q905: days", "Please input number of days or select Dont know or none: Number of days should be less than 30");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else if (ck1txt.isChecked() && ck2txt.isChecked()) {

                    lib.showError(q905.this, "Q905: ERROR", "Only one check box should be selected");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    int selectedIda = rg.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedIda);


                    if (selectedRbtna == null && !ck2txt.isChecked()) {
                        lib.showError(q905.this, "Q905a: year", "What are the main reasons that you forget or do not take your ARV pills every day?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else if (edtaother.length() == 0 && rbtnaother.isChecked()) {

                        lib.showError(q905.this, "Q905a: ERROR", "Please specify");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        if (ck2txt.isChecked()) {

                            individual.setQ905(edtdays.getText().toString());
                            individual.setQ905a(null);
                            individual.setQ905aOther(null);

                            myDB = new DatabaseHelper(q905.this);
                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();

                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);


                            Intent intent = new Intent(q905.this, q1001.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);
                        } else {

                            individual.setQ905(edtdays.getText().toString());
                            individual.setQ905a(selectedRbtna.getText().toString().substring(0, 1));
                            if(rbtnaother.isChecked()) {
                                individual.setQ905aOther(edtaother.getText().toString());
                            }
                            else
                            {
                                individual.setQ905aOther(null);
                            }
                            myDB = new DatabaseHelper(q905.this);
                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();

                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);

                            Intent intent = new Intent(q905.this, q1001.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);
                        }

                    }
                }
            }

        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                q905.super.onBackPressed();
            }


        });
    }



    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.q905_99:
                if (checked) {
                    edtdays.setText("99");
                    edtdays.setEnabled(false);
                    ck2txt.setChecked(false);

                    rbtna1.setEnabled(true);
                    rbtna2.setEnabled(true);
                    rbtna3.setEnabled(true);
                    rbtna4.setEnabled(true);
                    rbtna5.setEnabled(true);
                    rbtna6.setEnabled(true);
                    rbtnaother.setEnabled(true);
                }
                // Put some meat on the sandwich
                else {
                    edtdays.setText("");
                    edtdays.setEnabled(true);

                    // Remove the meat
                }
                break;

            case R.id.q905_00:
                if (checked) {
                    edtdays.setText("00");
                    edtdays.setEnabled(false);
                    ck1txt.setChecked(false);
                    rbtna1.setChecked(false);
                    rbtna2.setChecked(false);
                    rbtna3.setChecked(false);
                    rbtna4.setChecked(false);
                    rbtna5.setChecked(false);
                    rbtna6.setChecked(false);
                    rbtnaother.setChecked(false);

                    rbtna1.setEnabled(false);
                    rbtna2.setEnabled(false);
                    rbtna3.setEnabled(false);
                    rbtna4.setEnabled(false);
                    rbtna5.setEnabled(false);
                    rbtna6.setEnabled(false);
                    rbtnaother.setEnabled(false);




                    // Put some meat on the sandwich
                } else {
                    edtdays.setText("");
                    edtdays.setEnabled(true);

                    rbtna1.setEnabled(true);
                    rbtna2.setEnabled(true);
                    rbtna3.setEnabled(true);
                    rbtna4.setEnabled(true);
                    rbtna5.setEnabled(true);
                    rbtna6.setEnabled(true);
                    rbtnaother.setEnabled(true);


                }
                // Remove the meat
                break;
            default:
                break;


        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the view now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.q905a_other:
                if (checked) {

                    edtaother.setVisibility(View.VISIBLE);

                }
                // Put some meat on the sandwich
                else {
                    edtaother.setText("");
                    edtaother.setVisibility(View.INVISIBLE);
                    // Remove the meat
                }
                break;
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q905.this).toBundle());

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

/*
Intent intent = new Intent(q504.this, q1101.class);
                //intent.putExtra("Household", thisHose);
                startActivity(intent);
 */


