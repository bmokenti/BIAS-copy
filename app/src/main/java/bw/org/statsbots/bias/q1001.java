package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class q1001 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected DatabaseHelper myDB;
    protected Individual individual;
    protected RadioButton rbtn1, rbtn2, rbtn3;
    protected RadioGroup rg;
    protected RadioButton selectedRbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1001);

        setTitle("Q1001: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1001radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q1001_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1001_2);
        rbtn3 = (RadioButton) findViewById(R.id.q1001_3);

        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);
        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;


        Log.d("age",individual.getQ102());
        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();


        if( sample.getStatusCode().equals("3") )
        {
            Intent intent = new Intent(q1001.this, q1101.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

        if((individual.getQ101().equals("1")) || (individual.getQ101().equals("2") && (Integer.parseInt( individual.getQ102() )>49 || individual.getQ401().equals("2"))
                && ( sample.getStatusCode().equals("1") || (sample.getStatusCode().equals("2") && thisHouse.getHIVTB40().equals("1")) )) )
        {
            Intent intent = new Intent(q1001.this, q1101.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q1001.this, "Q1001: ERROR", "Are you currently pregnant?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {


                    if (rbtn1.isChecked()) {
                        individual.setQ1001(selectedRbtn.getText().toString().substring(0,1));

                        Intent skipto1003 = new Intent(q1001.this, q1003.class);
                        skipto1003.putExtra("Individual", individual);
                        startActivity(skipto1003);
                    }
                    else
                        {

                        individual.setQ1001(selectedRbtn.getText().toString().substring(0,1));

                        Intent intent = new Intent(q1001.this, q1002.class);
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
                q1001.super.onBackPressed();
            }


        });
    }





}

/*
Intent intent = new Intent(q504.this, q1101.class);
                //intent.putExtra("Household", thisHose);
                startActivity(intent);
 */


