package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class q611 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2, rbtn9, rbtn1b, rbtnb2, rbtnb9, rbtnc1, rbtnc2, rbtnc9, selected, selected1, selected2;
    protected RadioGroup rbtngroup, rbtngroupb, rbtngroupc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q611);


        setTitle("q611 HIV TRANSMISSION- MOTHER TO CHILD");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q611a_1);
        rbtn2 = (RadioButton) findViewById(R.id.q611a_2);
        rbtn9 = (RadioButton) findViewById(R.id.q611a_9);
        rbtngroup = (RadioGroup) findViewById(R.id.q611aradioGroup);

        rbtn1 = (RadioButton) findViewById(R.id.q611b_1);
        rbtn2 = (RadioButton) findViewById(R.id.q611b_2);
        rbtn9 = (RadioButton) findViewById(R.id.q611b_9);
        rbtngroupb = (RadioGroup) findViewById(R.id.q611bradioGroup);

        rbtn1 = (RadioButton) findViewById(R.id.q611c_1);
        rbtn2 = (RadioButton) findViewById(R.id.q611c_2);
        rbtn9 = (RadioButton) findViewById(R.id.q611c_9);
        rbtngroupc = (RadioGroup) findViewById(R.id.q611cradioGroup);


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        //int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();
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

        RadioButton[] bt = new RadioButton[3];
        for(int f=0;f<rbtngroup.getChildCount();f++)
        {
            View o = rbtngroup.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ611a()!= null &&  !ind.getQ611a().equals(""))
                {
                    if(Integer.parseInt(ind.getQ611a())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bt2 = new RadioButton[3];
        for(int f=0;f<rbtngroupb.getChildCount();f++)
        {
            View o = rbtngroupb.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt2[f]=((RadioButton)o);
                if(ind.getQ611b()!= null &&  !ind.getQ611b().equals(""))
                {
                    if(Integer.parseInt(ind.getQ611b())==f+1)
                    {
                        RadioButton radioButton = bt2[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bt3 = new RadioButton[3];
        for(int f=0;f<rbtngroupc.getChildCount();f++)
        {
            View o = rbtngroupc.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt3[f]=((RadioButton)o);
                if(ind.getQ611c()!= null &&  !ind.getQ611c().equals(""))
                {
                    if(Integer.parseInt(ind.getQ611c())==f+1)
                    {
                        RadioButton radioButton = bt3[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }




        /**
         * NEXT question
         */
        Button btnNext = (Button) findViewById(R.id.button);

        /**
         * NEXT and SAVE BUTTON
         */
        // btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = rbtngroup.getCheckedRadioButtonId();
                selected = (RadioButton) findViewById(selectedId);

                if (selected == null) {
                    lib.showError(q611.this, "Q611a Error", "Please select a value for q611a");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    int selectedId2 = rbtngroupb.getCheckedRadioButtonId();
                    selected1 = (RadioButton) findViewById(selectedId2);

                    if (selected1 == null) {
                        lib.showError(q611.this, "Q611b Error", "Please select a value for q611b");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    }
                    else
                    {
                        int selectedId3 = rbtngroupc.getCheckedRadioButtonId();
                        selected2 = (RadioButton) findViewById(selectedId3);
                        if (selected2 == null) {
                            lib.showError(q611.this, "Q611c Error", "Please select a value for q611c");

                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        }
                        else {

                            //Set Q611 and Q611a, 611b, 611c for the current individual
                            individual.setQ611a(selected.getText().toString().substring(0, 1));
                            individual.setQ611b(selected1.getText().toString().substring(0, 1));
                            individual.setQ611c(selected2.getText().toString().substring(0, 1));

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();

                            Intent intent = new Intent(q611.this, q612.class);
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
                if (((Integer.valueOf(individual.getQ102()) > 24 && Integer.valueOf(individual.getQ102()) <= 64) && individual.getQ601().equals("1")
                        && (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1"))) || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") &&
                        (p1.getP07() != null && Integer.parseInt(p1.getP07()) < 14)) || (sample.getStatusCode().equals("1"))) {

                    Intent intent = new Intent(q611.this, q605.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);

                } else {
                    q611.super.onBackPressed();
                }
            }

        });
    }
}

