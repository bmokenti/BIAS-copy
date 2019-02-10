package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class q102 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    Individual p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected EditText edt;
    protected DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q102);

        setTitle("Q102 Age");

        lib = new LibraryClass();
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        //edittext = (EditText) findViewById(R.id.q102_years);
        //edittext.setVisibility(View.VISIBLE);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        Intent h = getIntent();
        thisHouse = (HouseHold) h.getSerializableExtra("Household");
        edt = (EditText) findViewById(R.id.q102_years);
/*
        final Sample sample = myDB.getSample(myDB.getReadableDatabase(),individual.getAssignmentID());
        sample.getStatusCode();



        if(sample.getStatusCode().equals("2"))

        {
            Intent intent = new Intent(q102.this, q104.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

        if(sample.getStatusCode().equals("3"))

        {
            Intent intent = new Intent(q102.this, q105.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

*/

        /**
         * NEXT Person BUTTON
         */
        Button btnNext = (Button) findViewById(R.id.button);


        //btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Years;
                edt = (EditText) findViewById(R.id.q102_years);
                String years = edt.getText().toString();

                if (years.length() == 0 || years.equals("00") || years.equals("") || Integer.valueOf(edt.getText().toString()) <= 14) {
                    lib.showError(q102.this, "q102 Error", "Please enter age in completd years, Age must be 15 and above");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                } else {
                    //Set Q102 for the current individual
                    individual.setQ102(edt.getText().toString());
                    //Check if individual already been saved and update
                    myDB = new DatabaseHelper(q102.this);
                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.getWritableDatabase();

                    if (myDB.checkIndividual(individual)) {
                        //Update
                        myDB.updateIndividual(myDB.getWritableDatabase(), individual);

                    } else {
                        //Insert
                        myDB.insertIndividual(individual);

                    }


                    //Next question P17
                    Intent intent = new Intent(q102.this, q103.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);


                }


            }
        });


        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q102.super.onBackPressed();
            }


        });
    }
}


/*
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.q102_years:
                Intent intent4 = new Intent(this, q103.class);
                startActivity(intent4);
                break;


        }
    }*/
