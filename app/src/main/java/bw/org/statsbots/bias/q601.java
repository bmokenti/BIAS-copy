package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q601 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib, lib2;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, selectedRbtn, selectedRbtn1 ;
    protected RadioGroup rg, rga;
    protected DatabaseHelper myDB;
    protected TextView q601atxt;

    HouseHold thisHose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q601);


        setTitle("Q601: KNOWLEDGE ABOUT HIV/AIDS AND TB");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q601_1);
        rbtn2 = (RadioButton) findViewById(R.id.q601_2);


        rg = findViewById(R.id.q601radioGroup);
        rga = findViewById(R.id.q601aradioGroupa);


        //setTitle("Q402a Did you give consent at the time of intercourse?");
        lib2 = new LibraryClass();
        rbtna1 = (RadioButton) findViewById(R.id.q601a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q601a_2);
        q601atxt = (TextView) findViewById(R.id.q601atxt);




        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
      //  int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), ind.getAssignmentID());
        sample.getSTATUS();

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }
        }



        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ601()!= null &&  !ind.getQ601().equals(""))
                {
                    if(Integer.parseInt(ind.getQ601())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bta = new RadioButton[2];
        for(int f=0;f<rga.getChildCount();f++)
        {
            View o = rga.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bta[f]=((RadioButton)o);
                if(ind.getQ601a()!= null &&  !ind.getQ601a().equals(""))
                {
                    if(Integer.parseInt(ind.getQ601a())==f+1)
                    {
                        RadioButton radioButton = bta[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }
        if (individual.getQ601() != null && individual.getQ601().equals("2"))
        {

            rbtna1.setEnabled(false);
            rbtna2.setEnabled(false);
            rbtna1.setChecked(false);
            rbtna2.setChecked(false);
            q601atxt.setTextColor(Color.LTGRAY);
        }

        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);
                if (selectedRbtn == null) {
                    lib.showError(q601.this, "Q601:", "Have you EVER heard of HIV or an illness called AIDS?");

                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    int selectedId1 = rga.getCheckedRadioButtonId();
                    selectedRbtn1 = (RadioButton) findViewById(selectedId1);

                    if (selectedRbtn1 == null && rbtn1.isChecked()) {
                        lib.showError(q601.this, "Q601a: ERROR", "In the past 4 weeks, have you heard or seen any information about HIV / AIDS?");

                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {

                        if (rbtn2.isChecked()) {
                            individual.setQ601(selectedRbtn.getText().toString().substring(0, 1));
                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();

                            Intent intent = new Intent(q601.this, q604.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);
                        }
                       else
                                {
                                individual.setQ601(selectedRbtn.getText().toString().substring(0, 1));
                                individual.setQ601a(selectedRbtn1.getText().toString().substring(0, 1));
                                    myDB.onOpen(myDB.getReadableDatabase());
                                    myDB.getWritableDatabase();
                                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                    myDB.close();
                                Intent intent = new Intent(q601.this, q602.class);
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

                if ( (individual.getQ401() != null && individual.getQ401().equals("2")) && individual.getQ101().equals("2") && (Integer.valueOf(individual.getQ102()) >= 15 ||
                        Integer.valueOf(individual.getQ102()) <= 64)) {
                    Intent intent = new Intent(q601.this, q401.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                    finish();

                } else {
                    if (((sample.getStatusCode().equals("3")) || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0"))
                            || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) && Integer.valueOf(individual.getQ102()) > 64
                    ) || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) &&
                            p1.getP06().equals("2")))) {
finish();
                        Intent q1o2 = new Intent(q601.this, q307.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);
                    } else {
                        if (individual.getQ401().equals("2") && individual.getQ101().equals("2") && (Integer.valueOf(individual.getQ102()) >= 15 || Integer.valueOf(individual.getQ102()) <= 64)) {
                            Intent intent = new Intent(q601.this, q401.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);

                        } else {
                            if ((individual.getQ101().equals("2") && individual.getQ401().equals("1")) && (individual.getQ201().equals("1") || individual.getQ201().equals("4") ||
                                    individual.getQ201().equals("5") || individual.getQ201().equals("6")) || (Integer.parseInt(individual.getQ102()) > 49)) {
                                Intent intent = new Intent(q601.this, q407.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);
                                finish();

                            } else {
                                if ((individual.getQ101().equals("2")) && ((individual.getQ201().equals("1") &&
                                        individual.getQ202().equals("2")) || individual.getQ201().equals("6"))
                                        || Integer.valueOf(individual.getQ102()) >= 50) {

                                    Intent intent = new Intent(q601.this, q407.class);
                                    intent.putExtra("Individual", individual);
                                    startActivity(intent);

                                } else {
                                    if ((individual.getQ101().equals("2")) && ((individual.getQ201().equals("1") && individual.getQ202().equals("2")) || individual.getQ201().equals("6"))
                                            || Integer.valueOf(individual.getQ102()) >= 50) {

                                        Intent intent = new Intent(q601.this, q408.class);
                                        intent.putExtra("Individual", individual);
                                        startActivity(intent);

                                    } else {
                                        if (individual.getQ101().equals("2")) {
                                            Intent intent = new Intent(q601.this, q410.class);
                                            intent.putExtra("Individual", individual);
                                            startActivity(intent);

                                        } else {

                                            q601.super.onBackPressed();
                                            finish();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        });

    }

    public void onRadioButtonClicked(View v) {


        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q601_1:
                if (checked) {
                    //edtq402.setText("");

                    // q1108aques.setVisibility(View.VISIBLE);
                    //viewa.setVisibility(View.VISIBLE);
                    //chkq1108.setVisibility(View.VISIBLE);
                    //rb99.setVisibility(View.VISIBLE);
                    rbtna1.setEnabled(true);
                    rbtna2.setEnabled(true);
                   // rbtna1.setChecked(false);
                    //rbtna2.setChecked(false);
                    q601atxt.setTextColor(Color.BLACK);
                }


                break;

            case R.id.q601_2:
                if (checked) {

                    rbtna1.setEnabled(false);
                    rbtna2.setEnabled(false);
                    rbtna1.setChecked(false);
                    rbtna2.setChecked(false);
                    q601atxt.setTextColor(Color.LTGRAY);
                }
break;
            case R.id.q601a_1:
                if (checked) {


                }
                break;
            case R.id.q601a_2:
                if (checked) {


                }
                break;
                //txtdays.setVisibility(View.INVISIBLE);
                // txtweeks.setVisibility(View.INVISIBLE);


        }
    }



}

/*
  String txtq402cvalue = txtq402text.getText().toString();
        if (txtq402cvalue == null || txtq402cvalue.length() == 0) {
           lib.showError(q402.this, " Q402 Error ", "Please enter age");

Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibs.vibrate(100);
                    rbtn1.setVisibility(View.VISIBLE);
                    } else {
                    //Check if country entered is in the list
                    if (rbtn1.getText().equals(selected))
                    {
                    txtq402text.setVisibility(View.INVISIBLE);
                    }
                    else {
                    // txtq402text.setVisibility(View.VISIBLE);
                    //rbtn1.setVisibility(View.INVISIBLE);
                    }

                    }
 */









