package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class q1006 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected Individual individual;
    protected RadioButton rbtn1, rbtn2;
    protected RadioGroup rg;
    protected RadioButton selectedRbtn;protected  DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1006);


        setTitle("Q1006: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1006radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q1006_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1006_2);


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



        if (individual.getQ1005a().equals("2") || individual.getQ1005a().equals("3") ||individual.getQ1005a().equals("4") || individual.getQ1005a().equals("9"))
        {
            Intent intent = new Intent(q1006.this, q1007.class);
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
                if(ind.getQ1006()!= null &&  !ind.getQ1006().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1006())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
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
                    lib.showError(q1006.this, "Q1006: ERROR", "At the time of your first antenatal care visit were you taking ARVs?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {


                    if (rbtn1.isChecked()) {
                        Intent skipto1010 = new Intent(q1006.this, q1010.class);
                        skipto1010.putExtra("Individual", individual);
                        startActivity(skipto1010);
                    } else {


                        if (rbtn1.isChecked()) {
                            individual.setQ1006(selectedRbtn.getText().toString().substring(0,1));

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();

                            Intent skipto1008 = new Intent(q1006.this, q1010.class);
                            skipto1008.putExtra("Individual", individual);
                            startActivity(skipto1008);
                        } else {
                            individual.setQ1006(selectedRbtn.getText().toString().substring(0,1));

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();


                            Intent intent = new Intent(q1006.this, q1008.class);
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
                finish();
                Intent q1o2 = new Intent(q1006.this, q1005.class);
                q1o2.putExtra("Personroster", p1);
                startActivity(q1o2);
            }


        });
    }





}

/*
Intent intent = new Intent(q504.this, q1101.class);
                //intent.putExtra("Household", thisHose);
                startActivity(intent);
 */


