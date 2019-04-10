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

public class q1008 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected Individual individual;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, rbtna3, rbtna4, rbtna5, rbtna6, rbtna7, rbtna8, rbtna10, rbtnaOther ;
    protected RadioGroup rg, rga;protected  DatabaseHelper myDB;
    protected TextView t1;
    protected EditText edtOther;
    protected RadioButton selectedRbtn, selectedRbtna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1008);

        setTitle("Q1008: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1008radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q1008_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1008_2);

        rga = (RadioGroup)findViewById(R.id.q1008aGroup1) ;
        rbtna1 = (RadioButton) findViewById(R.id.q1008a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q1008a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q1008a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q1008a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q1008a_5);
        rbtna6 = (RadioButton) findViewById(R.id.q1008a_6);
        rbtna7 = (RadioButton) findViewById(R.id.q1008a_7);
        rbtna8 = (RadioButton) findViewById(R.id.q1008a_8);
        rbtna10 = (RadioButton) findViewById(R.id.q1008a_10);
        rbtnaOther = (RadioButton) findViewById(R.id.q1008a_other);
        edtOther = (EditText) findViewById(R.id.q1008a_other1);

        t1 = (TextView) findViewById(R.id.q1008a) ;


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

        if (individual.getQ1007a() != null &&  !individual.getQ1007a().equals("1"))
        {
            Intent intent = new Intent(q1008.this, q1009.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ1008()!= null )
                {
                    if(Integer.parseInt(ind.getQ1008())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }



        if (ind.getQ1008a_Other() != null ) {

            if (ind.getQ1008a() != null && ind.getQ1008a().equals("O")) {
                rbtnaOther.setChecked(true);
                edtOther.setText(ind.getQ1008a_Other());
            }
        }
        else {
            RadioButton[] bta = new RadioButton[10];
            for (int f = 0; f < rga.getChildCount(); f++) {
                View o = rga.getChildAt(f);
                if (o instanceof RadioButton) {
                    bta[f] = ((RadioButton) o);
                    if (ind.getQ1008a() != null) {
                        if (!ind.getQ1008a().equals("")) {
                            if (Integer.parseInt(ind.getQ1008a()) == f + 1) {
                                RadioButton radioButton = bta[f];
                                radioButton.setChecked(true);
                                break;
                            }
                        }
                    }
                }
            }
        }


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q1008.this, "Q1008: ERROR", " Did you EVER take ARVs during your pregnancy?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    int selectedIda = rga.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedIda);

                    if (selectedRbtna == null && rbtn2.isChecked()) {
                        lib.showError(q1008.this, "Q1008a: ERROR", "What was the MAIN REASON you did not take ARVs while you were pregnant?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {


                        if (rbtnaOther.isChecked() && edtOther.length() == 0) {
                            lib.showError(q1008.this, "Q1008a: ERROR : Other specify", "Please specify or deselect other option");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {


                            if (rbtn1.isChecked()) {


                                individual.setQ1008(selectedRbtn.getText().toString().substring(0, 1));
                                individual.setQ1008a(null);
                                individual.setQ1008a_Other(null);
                                individual.setQ1009(null);
                                individual.setQ1009a(null);

                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                myDB.close();

                                Intent skipto1009 = new Intent(q1008.this, q1010.class);
                                skipto1009.putExtra("Individual", individual);
                                startActivity(skipto1009);

                            } else {
                                individual.setQ1008(selectedRbtn.getText().toString().substring(0, 1));
                                individual.setQ1008a(selectedRbtna.getText().toString().substring(0, 1));
                                if(rbtnaOther.isChecked()) {
                                    individual.setQ1008a_Other(edtOther.getText().toString());
                                }
                                else
                                {
                                    individual.setQ1008a_Other(null);
                                }
                                individual.setQ1009(null);
                                individual.setQ1009a(null);

                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                myDB.close();

                                Intent skipto1009 = new Intent(q1008.this, q1010.class);
                                skipto1009.putExtra("Individual", individual);
                                startActivity(skipto1009);
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
                if(individual.getQ1006() != null || individual.getQ1006().equals("2"))
                {
                    finish();


                    Intent intent = new Intent(q1008.this, q1006.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                }

            }


        });
    }




    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q1008_1:
                if(checked)

                    rbtna1.setEnabled(false);
                rbtna2.setEnabled(false);
                rbtna3.setEnabled(false);
                rbtna4.setEnabled(false);
                rbtna5.setEnabled(false);
                rbtna6.setEnabled(false);
                rbtna7.setEnabled(false);
                rbtna8.setEnabled(false);
                rbtna10.setEnabled(false);
                rbtnaOther.setEnabled(false);
                t1.setTextColor(Color.LTGRAY);

                rbtna1.setChecked(false);
                rbtna2.setChecked(false);
                rbtna3.setChecked(false);
                rbtna4.setChecked(false);
                rbtna5.setChecked(false);
                rbtna6.setChecked(false);
                rbtna7.setChecked(false);
                rbtna8.setChecked(false);
                rbtna10.setChecked(false);
                rbtnaOther.setChecked(false);

                break;

            case R.id.q1008_2:
                if(checked)

                    rbtna1.setEnabled(true);
                rbtna2.setEnabled(true);
                rbtna3.setEnabled(true);
                rbtna4.setEnabled(true);
                rbtna5.setEnabled(true);
                rbtna6.setEnabled(true);
                rbtna7.setEnabled(true);
                rbtna8.setEnabled(true);
                rbtna10.setEnabled(true);
                rbtnaOther.setEnabled(true);
                t1.setTextColor(Color.BLACK);





                break;

            case R.id.q1008a_other:
                if(checked) {
                    edtOther.setVisibility(View.VISIBLE);
                }
                else
                    edtOther.setVisibility(View.INVISIBLE);
                edtOther.setText("");

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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q1008.this).toBundle());

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


