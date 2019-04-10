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

public class q1004 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Individual individual;
    protected Button btn;
    protected RadioButton rbtna1, rbtna2, rbtnb1, rbtnb2, rbtnb3, rbtnb4, rbtnb5, rbtnb6, rbtnb7, rbtnb8, rbtnb10, rbtnb11, rbtnb12, rbtnbOther ;
    protected RadioGroup rgb, rga;protected  DatabaseHelper myDB;
    protected TextView ta, tb;
    protected EditText edtdays, edtmonths, edtyears, edtOther;
    protected RadioButton selectedRbtna, selectedRbtnb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1004);


        setTitle("Q1004: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        edtdays = (EditText) findViewById(R.id.q1004_days) ;
        edtmonths = (EditText) findViewById(R.id.q1004_months) ;
        edtyears = (EditText) findViewById(R.id.q1004_year) ;


        rga = (RadioGroup)findViewById(R.id.q1004aGroup1) ;
        rbtna1 = (RadioButton) findViewById(R.id.q1004a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q1004a_2);

        rgb = (RadioGroup)findViewById(R.id.q1004bGroup2) ;
        rbtnb1 = (RadioButton) findViewById(R.id.q1004b_1);
        rbtnb2 = (RadioButton) findViewById(R.id.q1004b_2);
        rbtnb3 = (RadioButton) findViewById(R.id.q1004b_3);
        rbtnb4 = (RadioButton) findViewById(R.id.q1004b_4);
        rbtnb5 = (RadioButton) findViewById(R.id.q1004b_5);
        rbtnb6 = (RadioButton) findViewById(R.id.q1004b_6);
        rbtnb7 = (RadioButton) findViewById(R.id.q1004b_7);
        rbtnb8 = (RadioButton) findViewById(R.id.q1004b_8);
        rbtnb10 = (RadioButton) findViewById(R.id.q1004b_10);
        rbtnb11 = (RadioButton) findViewById(R.id.q1004b_11);
        rbtnb12 = (RadioButton) findViewById(R.id.q1004b_12);
        rbtnbOther = (RadioButton) findViewById(R.id.q1004b_other);
        edtOther =  (EditText) findViewById(R.id.q1004b_other1);



        ta = (TextView) findViewById(R.id.q1004a) ;
        tb = (TextView) findViewById(R.id.q1004b) ;


        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();
           thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

    final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
        if (p.getSRNO() == ind.getSRNO()){
            p1 = p;
            break;
        }
    }



        rgb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q1004b_other1)
                {
                    // is checked
                    edtOther.setVisibility(View.VISIBLE);
                }
                else
                {
                    // not checked
                    edtOther.setVisibility(View.INVISIBLE);
                    edtOther.setText("");
                }
            }
        });

        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rga.getChildCount();f++)
        {
            View o = rga.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ1004a()!= null )
                {
                    if(Integer.parseInt(ind.getQ1004a())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        if (ind.getQ1004b_Other() != null ) {

            if (ind.getQ1004b() != null && ind.getQ1004b().equals("O")) {
                rbtnbOther.setChecked(true);
                edtOther.setText(ind.getQ1004b_Other());
            }
        }
        else {
        RadioButton[] btb = new RadioButton[12];
        for(int f=0;f<rgb.getChildCount();f++)
        {
            View o = rgb.getChildAt(f);
            if (o instanceof RadioButton) {
                btb[f] = ((RadioButton) o);
                if (ind.getQ1004b() != null ) {
                    if (!ind.getQ1004b().equals("")) {
                        if (Integer.parseInt(ind.getQ1004b()) == f + 1) {
                            RadioButton radioButton = btb[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
            }
        }

        if(ind.getQ1004_Day() != null)
        {
            edtdays.setText(ind.getQ1004_Day());
        }

        if(ind.getQ1004_Month()!= null)
        {
            edtmonths.setText(ind.getQ1004_Month());
        }


        if(ind.getQ1004_Year()!= null)
        {
            edtyears.setText(ind.getQ1004_Year());
        }








        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (edtdays.length() == 0 || edtmonths.length() == 0 || edtyears.length() == 0) {
                    lib.showError(q1004.this, "Q1004: ERROR", "What is the date of birth for your youngest child?" +
                            "If dont know days or months put 99 and for year put 9999");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {


                    if (Integer.valueOf(edtdays.getText().toString()) > 31) {
                        lib.showError(q1004.this, "Q1004a: ERROR", "  Days cannot be more thana 31");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {


                        if (Integer.valueOf(edtmonths.getText().toString()) > 12) {
                            lib.showError(q1004.this, "Q1004a: ERROR", "  Months cannot be more than 12");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {


                            if (Integer.valueOf(edtdays.getText().toString()) > 2019) {
                                lib.showError(q1004.this, "Q1004a: ERROR", "  Years cannot be more than 2019");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {


                                int selectedIda = rga.getCheckedRadioButtonId();
                                selectedRbtna = (RadioButton) findViewById(selectedIda);

                                if (selectedRbtna == null) {
                                    lib.showError(q1004.this, "Q1004a: ERROR", "  Whilst pregnant with your youngest child, did you attend an ante natal care clinic?");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);
                                } else {

                                    int selectedIdb = rgb.getCheckedRadioButtonId();
                                    selectedRbtnb = (RadioButton) findViewById(selectedIdb);

                                    if (selectedRbtnb == null && rbtna2.isChecked()) {
                                        lib.showError(q1004.this, "Q1004b: ERROR", "What is the MAIN reason you did not visit a clinic for antenatal " +
                                                "care when you were pregnant with this child?");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);
                                    } else {


                                        if (edtOther.getText() == null && rbtnbOther.isChecked()) {
                                            lib.showError(q1004.this, "Q1004b: Other", "Other specify");
                                            /**
                                             * VIBRATE DEVICE
                                             */
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);
                                        } else {


                                            if (rbtna1.isChecked()) {

                                                //                                                individual.setQ1004_Day(edtdays.getText().toString());
//                                                individual.setQ1004_Month(edtmonths.getText().toString());
//                                                individual.setQ1004_Year(edtyears.getText().toString());



                                                if (edtdays.getText().toString().length() == 0) {
                                                    individual.setQ1004_Day("00");
                                                } else if (edtdays.getText().toString().length() == 1) {
                                                    individual.setQ1004_Day("0" + edtdays.getText().toString());
                                                } else {
                                                    individual.setQ1004_Day(edtdays.getText().toString());
                                                }


                                                if (edtmonths.getText().toString().length() == 0) {
                                                    individual.setQ1004_Month("00");
                                                } else if (edtmonths.getText().toString().length() == 1) {
                                                    individual.setQ1004_Month("0" + edtmonths.getText().toString());
                                                } else {
                                                    individual.setQ1004_Month(edtmonths.getText().toString());
                                                }


                                                if (edtyears.getText().toString().length() == 0) {
                                                    individual.setQ1004_Year("0000");
                                                }
                                                else {
                                                    individual.setQ1004_Year(edtyears.getText().toString());
                                                }






                                                individual.setQ1004a(selectedRbtna.getText().toString().substring(0, 1));
                                                individual.setQ1004b(null);
                                                individual.setQ1004b_Other(null);


                                                myDB.onOpen(myDB.getReadableDatabase());
                                                myDB.getWritableDatabase();
                                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                                myDB.close();


                                                Intent intent = new Intent(q1004.this, q1005.class);
                                                intent.putExtra("Individual", individual);
                                                startActivity(intent);
                                            } else {
                                                if (edtdays.getText().toString().length() == 0) {
                                                    individual.setQ1004_Day("00");
                                                } else if (edtdays.getText().toString().length() == 1) {
                                                    individual.setQ1004_Day("0" + edtdays.getText().toString());
                                                } else {
                                                    individual.setQ1004_Day(edtdays.getText().toString());
                                                }


                                                if (edtmonths.getText().toString().length() == 0) {
                                                    individual.setQ1004_Month("00");
                                                } else if (edtmonths.getText().toString().length() == 1) {
                                                    individual.setQ1004_Month("0" + edtmonths.getText().toString());
                                                } else {
                                                    individual.setQ1004_Month(edtmonths.getText().toString());
                                                }


                                                if (edtyears.getText().toString().length() == 0) {
                                                    individual.setQ1004_Year("0000");
                                                }
                                                else {
                                                    individual.setQ1004_Year(edtyears.getText().toString());
                                                }



                                                individual.setQ1004a(selectedRbtna.getText().toString().substring(0, 1));
                                                individual.setQ1004b(selectedRbtnb.getText().toString().substring(0, 1));
                                                if(rbtnbOther.isChecked()) {
                                                    individual.setQ1004b_Other(edtOther.getText().toString());
                                                }
                                                else
                                                {
                                                    individual.setQ1004b_Other(null);
                                                }

                                                myDB.onOpen(myDB.getReadableDatabase());
                                                myDB.getWritableDatabase();
                                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                                myDB.close();



                                                Intent intent = new Intent(q1004.this, q1005.class);
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
            }


        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skipto1017 = new Intent(q1004.this, q1003.class);
                skipto1017.putExtra("Individual", individual);
                startActivity(skipto1017);

            }


        });
    }




    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q1004a_1:
                if(checked)

                    rbtnb1.setEnabled(false);
                rbtnb2.setEnabled(false);
                rbtnb3.setEnabled(false);
                rbtnb4.setEnabled(false);
                rbtnb5.setEnabled(false);
                rbtnb6.setEnabled(false);
                rbtnb7.setEnabled(false);
                rbtnb8.setEnabled(false);
                rbtnb10.setEnabled(false);
                rbtnb11.setEnabled(false);

                rbtnb12.setEnabled(false);
                rbtnbOther.setEnabled(false);

                rbtnb1.setChecked(false);
                rbtnb2.setChecked(false);
                rbtnb3.setChecked(false);
                rbtnb4.setChecked(false);
                rbtnb5.setChecked(false);
                rbtnb6.setChecked(false);
                rbtnb7.setChecked(false);
                rbtnb8.setChecked(false);
                rbtnb10.setChecked(false);
                rbtnb11.setChecked(false);

                rbtnb12.setChecked(false);
                rbtnbOther.setChecked(false);



                tb.setTextColor(Color.BLACK);





                break;

            case R.id.q1004a_2:
                if(checked)

                    rbtnb1.setEnabled(true);
                rbtnb2.setEnabled(true);
                rbtnb3.setEnabled(true);
                rbtnb4.setEnabled(true);
                rbtnb5.setEnabled(true);
                rbtnb6.setEnabled(true);
                rbtnb7.setEnabled(true);
                rbtnb8.setEnabled(true);
                rbtnb10.setEnabled(true);
                rbtnb11.setEnabled(true);

                rbtnb12.setEnabled(true);
                rbtnbOther.setEnabled(true);


                    break;


            default:

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
    public boolean onOptionsItemSelected(MenuItem item) {
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q1004.this).toBundle());

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


                return true;

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


