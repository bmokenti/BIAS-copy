package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class q623 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2;
    protected RadioGroup rg1;
    protected DatabaseHelper myDB;
    protected EditText edtOther;
    protected RadioButton selectedRbtn,selectedRbtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q623);

        setTitle("q623: KNOWLEDGE ABOUT HIV/AIDS AND TB ");
        lib = new LibraryClass();
        rg1 = (RadioGroup) findViewById(R.id.q623radioGroup) ;

        rbtn1 = (RadioButton) findViewById(R.id.q623_1);
        rbtn2 = (RadioButton) findViewById(R.id.q623_2);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        //int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;


        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }


        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();



        if( ((sample.getStatusCode().equals("3") )  || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0") )
                || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) && Integer.valueOf(individual.getQ102()) > 64
        ) ||((sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1")) &&
                p1.getP06().equals("2")  ) ))
        {

            Intent q1o2 = new Intent(q623.this, q704.class);
            q1o2.putExtra("Individual", individual);
            startActivity(q1o2);
        }

        RadioButton[] bt = new RadioButton[2];

        for(int f=0;f<rg1.getChildCount();f++)
        {
            View o = rg1.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ623()!= null &&  !ind.getQ623().equals(""))
                {
                    if(Integer.parseInt(ind.getQ623())==f+1)
                    {
                        RadioButton radioButton = bt[f];
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
                int selectedId = rg1.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);
                if (selectedRbtn == null) {
                    lib.showError(q623.this, "Q623: ERROR", "Have you ever heard of Safe Male Circumcision or SMC programme?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    if (rbtn2.isChecked()) {
                        individual.setQ623(selectedRbtn.getText().toString().substring(0, 1));
                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        myDB.close();

                        Intent intent = new Intent(q623.this, q701.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
                    } else {
                        individual.setQ623(selectedRbtn.getText().toString().substring(0, 1));
                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        myDB.close();
                        Intent intent = new Intent(q623.this, q624.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
                    }
                }
            }

             });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (individual.getQ601().equals("2") && ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1"))
                        || sample.getStatusCode().equals("1")) && (Integer.valueOf(individual.getQ102()) <= 24)
                        && (p1.getP07() != null && Integer.parseInt(p1.getP07()) < 14)) {

                    Intent intent = new Intent(q623.this, q605.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);

                } else {
                    if (sample.getStatusCode().equals("1") && individual.getQ604().equals("2")) {

                        Intent q1o2 = new Intent(q623.this, q615.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);
                    } else {
                        if (individual.getQ604().equals("2") && sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) {

                            Intent intent = new Intent(q623.this, q615.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);


                        } else {
                            Intent intent = new Intent(q623.this, q622.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);

                        }
                    }
                }
            }

        });
    }
    }
