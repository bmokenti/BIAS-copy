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

public class q622 extends AppCompatActivity implements Serializable {


    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn3,rbta1, rbta2, rbtaOther,rbtb1,rbtb2,rbtbOther;
    protected RadioGroup rg1,rg2,rg3;
    protected EditText edtaOther, edtbOther;
    protected TextView txta, txtb;
    protected DatabaseHelper myDB;
    protected RadioButton selectedRbtn,selectedRbtna, selectedRbtnb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q622);

        setTitle("q622: KNOWLEDGE ABOUT HIV/AIDS AND TB ");
        lib = new LibraryClass();

        rg1 = (RadioGroup) findViewById(R.id.q622radioGroup) ;
        rg2 = (RadioGroup) findViewById(R.id.q622aGroup1) ;
        rg3 = (RadioGroup) findViewById(R.id.q622bGroup2) ;


        rbtn1 = (RadioButton) findViewById(R.id.q622_1);
        rbtn2 = (RadioButton) findViewById(R.id.q622_2);
        rbtn3 = (RadioButton) findViewById(R.id.q622_9);

        rbta1 = (RadioButton) findViewById(R.id.q622a_1);
        rbta2 = (RadioButton) findViewById(R.id.q622a_9);
        rbtaOther = (RadioButton) findViewById(R.id.q622a_other);
        edtaOther = (EditText) findViewById(R.id.q622a_other1);

        rbtb1 =  (RadioButton) findViewById(R.id.q622b_1);
        rbtb2 =  (RadioButton) findViewById(R.id.q622b_9);
        rbtbOther = (RadioButton) findViewById(R.id.q622b_other);
        edtbOther = (EditText) findViewById(R.id.q622b_other1);

        txta = (TextView) findViewById(R.id.q622a);
        txtb = (TextView) findViewById(R.id.q622b);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;
        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();


        //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());



        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

           thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);



        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }
//
//
//        if (sample.getStatusCode().equals("1") || (sample.getStatusCode().equals("2") &&thisHous.get(0).getHIVTB40().equals("1") &&
//                (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 )))



        if(individual.getQ604().equals("2") &&  (sample.getStatusCode().equals("1") || (sample.getStatusCode().equals("2") &&thisHous.get(0).getHIVTB40().equals("1") &&
              (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 ))))
        {
            individual.setQ622(null);
            individual.setQ622a(null);
            individual.setQ622aOther(null);
            individual.setQ622b(null);
            individual.setQ622bOther(null);

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(), individual);
            myDB.close();

            Intent q1o2 = new Intent(q622.this, q623.class);
            q1o2.putExtra("Individual", individual);
            startActivity(q1o2);
        }



        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q622_1)
                {
                    rbta1.setEnabled(true);
                    rbta2.setEnabled(true);
                    rbtaOther.setEnabled(true);
                    txta.setTextColor(Color.BLACK);

                    rbtb1.setEnabled(false);
                    rbtb2.setEnabled(false);
                    rbtbOther.setEnabled(false);
                    txtb.setTextColor(Color.LTGRAY);
                    edtbOther.setVisibility(View.INVISIBLE);

                    rbtb1.setChecked(false);
                    rbtb2.setChecked(false);
                    rbtbOther.setChecked(false);
                    edtbOther.setText("");

                    /*******************/

                }
                else {
                    if (i == R.id.q622_2) {
                        rbta1.setEnabled(false);
                        rbta2.setEnabled(false);
                        rbtaOther.setEnabled(false);
                        txta.setTextColor(Color.LTGRAY);
                        edtaOther.setVisibility(View.INVISIBLE);

                        rbta1.setChecked(false);
                        rbta2.setChecked(false);
                        rbtaOther.setChecked(false);
                        edtaOther.setText("");

                        rbtb1.setEnabled(true);
                        rbtb2.setEnabled(true);
                        txtb.setTextColor(Color.BLACK);
                        rbtbOther.setEnabled(true);
                    } else {
                        if (i == R.id.q622_9) {
                            rbta1.setEnabled(false);
                            rbta2.setEnabled(false);
                            rbtaOther.setEnabled(false);

                            rbta1.setChecked(false);
                            rbta2.setChecked(false);
                            rbtaOther.setChecked(false);
                            txta.setTextColor(Color.LTGRAY);
                            edtaOther.setText("");

                            rbtb1.setEnabled(false);
                            rbtb2.setEnabled(false);
                            rbtbOther.setEnabled(false);
                            txtb.setTextColor(Color.LTGRAY);
                            edtbOther.setVisibility(View.INVISIBLE);

                            rbtb1.setChecked(false);
                            rbtb2.setChecked(false);
                            rbtbOther.setChecked(false);
                            edtbOther.setText("");

                        }
                    }
                }
            }
        });


        if (individual.getQ622() != null && individual.getQ622().equals("9"))
        {
            rbta1.setEnabled(false);
            rbta2.setEnabled(false);
            rbtaOther.setEnabled(false);

            rbta1.setChecked(false);
            rbta2.setChecked(false);
            rbtaOther.setChecked(false);
            txta.setTextColor(Color.LTGRAY);
            edtaOther.setText("");

            rbtb1.setEnabled(false);
            rbtb2.setEnabled(false);
            rbtbOther.setEnabled(false);
            txtb.setTextColor(Color.LTGRAY);
            edtbOther.setVisibility(View.INVISIBLE);

            rbtb1.setChecked(false);
            rbtb2.setChecked(false);
            rbtbOther.setChecked(false);
            edtbOther.setText("");
        }

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q622a_other)
                {
                    // is checked
                    edtaOther.setVisibility(View.VISIBLE);
                }
                else
                {
                    // not checked
                    edtaOther.setVisibility(View.INVISIBLE);
                    edtaOther.setText("");
                }
            }
        });


        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q622b_other)
                {
                    // is checked
                    edtbOther.setVisibility(View.VISIBLE);
                }
                else
                {
                    // not checked
                    edtbOther.setVisibility(View.INVISIBLE);
                    edtbOther.setText("");
                }
            }
        });


        RadioButton[] bt = new RadioButton[3];
        for(int f=0;f<rg1.getChildCount();f++)
        {
            View o = rg1.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);

                if(ind.getQ622()!= null &&  !ind.getQ622().equals(""))
                {
                    if(Integer.parseInt(ind.getQ622())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

//
        if (ind.getQ622aOther() != null ) {
            if (!ind.getQ622aOther().equals("")) {
                if (ind.getQ622a() != null && ind.getQ622a().equals("O")) {
                    rbtaOther.setChecked(true);
                    edtaOther.setText(ind.getQ622aOther());
                }
            }
        }
        else
        {

        RadioButton[] bt1 = new RadioButton[3];
        for(int f=0;f<rg2.getChildCount();f++) {
            View o = rg2.getChildAt(f);
            if (o instanceof RadioButton) {
                bt1[f] = ((RadioButton) o);

                {
                    if (ind.getQ622a() != null) {
                        if (!ind.getQ622a().equals("")) {
                            if (Integer.parseInt(ind.getQ622a()) == f + 1) {
                                RadioButton radioButton = bt1[f];
                                radioButton.setChecked(true);
                                break;
                            }
                        }
                    }
                }
            }
        }
        }




        RadioButton[] bt2 = new RadioButton[3];

        for(int f=0;f<rg3.getChildCount();f++) {
            View o = rg3.getChildAt(f);
            if (o instanceof RadioButton) {
                bt2[f] = ((RadioButton) o);

                if (ind.getQ622bOther() != null ) {
                    rbtbOther.setChecked(true);
                    edtbOther.setText(ind.getQ622bOther());
                } else {
                    if (ind.getQ622b() != null ) {
                        if (!ind.getQ622b().equals("")) {
                            if (Integer.parseInt(ind.getQ622b()) == f + 1) {
                                RadioButton radioButton = bt2[f];
                                radioButton.setChecked(true);
                                break;
                            }
                        }
                    }

            }
        }
        }

//
//        if(ind.getQ621bOther() != null)
//        {
//            edtbOther.setText(ind.getQ621bOther());
//        }


        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg1.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);
                if (selectedRbtn == null) {
                    lib.showError(q622.this, "Q622: ERROR", "Do you think HIV positive people should be concerned about TB?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }
                else {
                    int selectedId1 = rg2.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedId1);

                    if (selectedRbtna == null && rbtn1.isChecked()) {
                        lib.showError(q622.this, "Q622: ERROR : A", "If YES, why?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else {

                        if (rbtaOther.isChecked() && edtaOther.length() == 0) {
                            lib.showError(q622.this, "Q622A: ERROR : Other", "Please specify");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {
                            int selectedId2 = rg3.getCheckedRadioButtonId();

                            selectedRbtnb = (RadioButton) findViewById(selectedId2);
                            if (selectedRbtnb == null && rbtn2.isChecked()) {
                                lib.showError(q622.this, "Q622: ERROR: B", "If NO, why not?");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {

                                if (rbtbOther.isChecked() && edtbOther.length() == 0) {
                                    lib.showError(q622.this, "Q622B: ERROR : Other", "Please specify");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);
                                } else {
                                    if (rbtn1.isChecked()) {
                                        individual.setQ622(selectedRbtn.getText().toString().substring(0, 1));
                                        individual.setQ622a(selectedRbtna.getText().toString().substring(0, 1));
                                        if (rbtaOther.isChecked()) {
                                            individual.setQ622aOther(edtaOther.getText().toString());
                                        }
                                        else
                                        {
                                            individual.setQ622aOther(null);
                                        }

                                        individual.setQ622b(null);
                                        individual.setQ622bOther(null);

                                        myDB.onOpen(myDB.getReadableDatabase());
                                        myDB.getWritableDatabase();
                                        myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                        myDB.close();

                                        Intent intent = new Intent(q622.this, q623.class);
                                        intent.putExtra("Individual", individual);
                                        startActivity(intent);
                                    }
                                   else {
                                            if (rbtn2.isChecked()) {
                                                individual.setQ622(selectedRbtn.getText().toString().substring(0, 1));

                                                individual.setQ622b(selectedRbtnb.getText().toString().substring(0, 1));
                                                if(rbtbOther.isChecked()) {
                                                    individual.setQ622bOther(edtbOther.getText().toString());
                                                }
                                                else
                                                {
                                                    individual.setQ622bOther(null);
                                                }

                                                individual.setQ622a(null);
                                                individual.setQ622aOther(null);

                                                myDB.onOpen(myDB.getReadableDatabase());
                                                myDB.getWritableDatabase();
                                                myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                                myDB.close();
                                                Intent intent = new Intent(q622.this, q623.class);
                                                intent.putExtra("Individual", individual);
                                                startActivity(intent);

                                            }
                                            else {
                                                    individual.setQ622(selectedRbtn.getText().toString().substring(0, 1));
                                                individual.setQ622a(null);
                                                individual.setQ622aOther(null);
                                                individual.setQ622b(null);
                                                individual.setQ622bOther(null);

                                                    myDB.onOpen(myDB.getReadableDatabase());
                                                    myDB.getWritableDatabase();
                                                    myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                                    myDB.close();
                                                    Intent intent = new Intent(q622.this, q623.class);
                                                    intent.putExtra("Individual", individual);
                                                    startActivity(intent);
                                                }
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
                if( sample.getStatusCode().equals("1") && individual.getQ604().equals("1") )
                {
                    finish();
                    Intent q1o2 = new Intent(q622.this, q615.class);
                    q1o2.putExtra("Individual", individual);
                    startActivity(q1o2);
                }
                else {
                    finish();
                    Intent q1o2 = new Intent(q622.this, q621.class);
                    q1o2.putExtra("Individual", individual);
                    startActivity(q1o2);
                }
            }


        });
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q622.this).toBundle());

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
