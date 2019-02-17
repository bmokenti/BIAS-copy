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

public class q804 extends AppCompatActivity implements Serializable{

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtnother, selected;
    protected RadioGroup rbtngroup;
    protected EditText edt804other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q804);


        setTitle("q804  EVER TESTED FOR HIV");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q804_1);
        rbtn2 = (RadioButton) findViewById(R.id.q804_2);
        rbtn3 = (RadioButton) findViewById(R.id.q804_3);
        rbtn4 = (RadioButton) findViewById(R.id.q804_4);
        rbtn5 = (RadioButton) findViewById(R.id.q804_5);
        rbtn6 = (RadioButton) findViewById(R.id.q804_6);
        rbtn7 = (RadioButton) findViewById(R.id.q804_7);
        rbtnother = (RadioButton) findViewById(R.id.q804_other);
        edt804other = (EditText) findViewById(R.id.q804_otherr);
        rbtngroup = (RadioGroup) findViewById(R.id.q804radioGroup);


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        if(individual.getQ801().equals("1") )
        {

            Intent intent = new Intent(q804.this, q901.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }
        else {

        }




        rbtnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnother.isChecked()) {
                    edt804other.setVisibility(View.VISIBLE);
                    //edt804other.setText("");


                }
            }
        });

        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn1.isChecked()) {
                    edt804other.setVisibility(View.INVISIBLE);
                    edt804other.setText("");


                }
            }
        });
        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn2.isChecked()) {
                    edt804other.setVisibility(View.INVISIBLE);
                    edt804other.setText("");


                }
            }
        });
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn3.isChecked()) {
                    edt804other.setVisibility(View.INVISIBLE);
                    edt804other.setText("");


                }
            }
        });

        rbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn4.isChecked()) {
                    edt804other.setVisibility(View.INVISIBLE);
                    edt804other.setText("");


                }
            }
        });
        rbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn5.isChecked()) {
                    edt804other.setVisibility(View.INVISIBLE);
                    edt804other.setText("");


                }
            }
        });
        rbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn6.isChecked()) {
                    edt804other.setVisibility(View.INVISIBLE);
                    edt804other.setText("");


                }
            }
        });
        rbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn7.isChecked()) {
                    edt804other.setVisibility(View.INVISIBLE);
                    edt804other.setText("");


                }
            }
        });

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
                    lib.showError(q804.this, "Q804 Error", "Please select an option for q804");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    if (selected == rbtnother && (edt804other.length() == 0 || edt804other.getText() == null)) {
                        lib.showError(q804.this, "Q804 Error", "Please specify Other for q804");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else {
                        if (individual.getQ101().equals("2") && (Integer.valueOf(individual.getQ102()) > 14 && (Integer.valueOf(individual.getQ102()) < 50)) && individual.getQ401().equals("1")) {
                            individual.setQ804(selected.getText().toString().substring(0, 1));
                            individual.setQ804Other(edt804other.getText().toString());
                            myDB = new DatabaseHelper(q804.this);
                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            if(myDB.checkIndividual(individual)){
                                //Update
                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);

                            }
                            Intent intent = new Intent(q804.this, q1001.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);
                        } else {
                            individual.setQ804(selected.getText().toString().substring(0, 1));
                            individual.setQ804Other(edt804other.getText().toString());
                            myDB = new DatabaseHelper(q804.this);
                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            if(myDB.checkIndividual(individual)){
                                //Update
                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);

                            }
                            Intent intent = new Intent(q804.this, q1101.class);
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
                q804.super.onBackPressed();
            }


        });
    }
}


