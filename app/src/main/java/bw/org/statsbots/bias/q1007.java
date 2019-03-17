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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q1007 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, rbtna3, rbtna4, rbtna5 ;
    protected RadioGroup rg, rga;
    protected Individual individual;protected  DatabaseHelper myDB;
    protected TextView t1;
    protected RadioButton selectedRbtn, selectedRbtna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1007);



        setTitle("Q1007: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1007radioGroup) ;

        rbtn1 = (RadioButton) findViewById(R.id.q1007_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1007_2);

        rga = (RadioGroup)findViewById(R.id.q1007aGroup1) ;
        rbtna1 = (RadioButton) findViewById(R.id.q1007a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q1007a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q1007a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q1007a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q1007a_9);
        t1 = (TextView) findViewById(R.id.q1007a) ;


        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ1007()!= null &&  !ind.getQ1007().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1007())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bta = new RadioButton[5];
        for(int f=0;f<rga.getChildCount();f++)
        {
            View o = rga.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bta[f]=((RadioButton)o);
                if(ind.getQ1007a()!= null &&  !ind.getQ1007a().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1007a())==f+1)
                    {
                        RadioButton radioButton = bta[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q1007.this, "Q1007: ERROR", "Were you TESTED for HIV during any of your antenatal care clinic visit?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    int selectedIda = rga.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedIda);

                    if (selectedRbtna == null && rbtn1.isChecked()) {
                        lib.showError(q1007.this, "Q1007a: ERROR", "What was the result of your last HIV test during your pregnancy?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {


                        if (rbtn2.isChecked()) {
                            individual.setQ1007(selectedRbtn.getText().toString().substring(0, 1));

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();


                            Intent skipto1009 = new Intent(q1007.this, q1009.class);
                            skipto1009.putExtra("Individual", individual);
                            startActivity(skipto1009);
                        } else {

                                individual.setQ1007(selectedRbtn.getText().toString().substring(0, 1));
                                individual.setQ1007a(selectedRbtna.getText().toString().substring(0, 1));

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();


                                Intent skipto1009 = new Intent(q1007.this, q1008.class);
                                skipto1009.putExtra("Individual", individual);
                                startActivity(skipto1009);
                            }
                        }
                    }

                    }

        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (individual.getQ1005a() != null && !(individual.getQ1005a().equals("1"))) {

                    Intent intent = new Intent(q1007.this, q1005.class);
                    intent.putExtra("Individual", individual);
                    intent.putExtra("Personroster", p1);
                    startActivity(intent);


                } else {
                    if (individual.getQ1005() != null && ((individual.getQ1005().equals("2")))) {

                        Intent intent = new Intent(q1007.this, q1005.class);
                        intent.putExtra("Individual", individual);
                        intent.putExtra("Personroster", p1);
                        startActivity(intent);


                    } else {


                        Intent q1o2 = new Intent(q1007.this, q1006.class);
                        q1o2.putExtra("Personroster", p1);
                        startActivity(q1o2);
                    }

                }
            }

        });
    }




    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q1007_1:
                if(checked)

                 rbtna1.setEnabled(true);
                rbtna2.setEnabled(true);
                rbtna3.setEnabled(true);
                rbtna4.setEnabled(true);
                rbtna5.setEnabled(true);
                t1.setTextColor(Color.BLACK);

                break;

            case R.id.q1007_2:
                if(checked)

                    rbtna1.setEnabled(false);
                rbtna2.setEnabled(false);
                rbtna3.setEnabled(false);
                rbtna4.setEnabled(false);
                rbtna5.setEnabled(false);

                rbtna1.setChecked(false);
                rbtna2.setChecked(false);
                rbtna3.setChecked(false);
                rbtna4.setChecked(false);
                rbtna5.setChecked(false);
                t1.setTextColor(Color.LTGRAY);

                break;

            case R.id.q1007a_1:
                if(checked)


                break;


            default:

                break;

        }

    }


}





/*
Intent intent = new Intent(q504.this, q1101.class);
                //intent.putExtra("Household", thisHose);
                startActivity(intent);
 */


