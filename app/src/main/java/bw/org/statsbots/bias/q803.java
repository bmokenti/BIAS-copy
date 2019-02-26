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

public class q803 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtn8, rbtnother, selected;
    protected RadioGroup rbtngroup;
    protected EditText edtother;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q803);


        setTitle("q803  WHY NOT TESTED FOR HIV");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q803_1);
        rbtn2 = (RadioButton) findViewById(R.id.q803_2);
        rbtn3 = (RadioButton) findViewById(R.id.q803_3);
        rbtn4 = (RadioButton) findViewById(R.id.q803_4);
        rbtn5 = (RadioButton) findViewById(R.id.q803_5);
        rbtn6 = (RadioButton) findViewById(R.id.q803_6);
        rbtn7 = (RadioButton) findViewById(R.id.q803_7);
        rbtn8 = (RadioButton) findViewById(R.id.q803_8);
        rbtnother = (RadioButton) findViewById(R.id.q803_other);
        rbtngroup = (RadioGroup) findViewById(R.id.q803radioGroup);
        edtother = (EditText) findViewById(R.id.q803_otherr) ;


        rbtnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbtnother.isChecked()) {
                    edtother.setVisibility(View.VISIBLE);
                }

                    else{
                        edtother.setVisibility(View.INVISIBLE);
                        edtother.setText("");

                    }


            }
        });
        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn1.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });
        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn2.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn3.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });
        rbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn4.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });
        rbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn5.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });
        rbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn6.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });
        rbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn7.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });
        rbtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn8.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        if(individual.getQ801a().equals("1")  && individual.getQ801f().equals("1"))
        {
            Intent intent = new Intent(q803.this, q901.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }
        else {

        }


        if((individual.getQ801a().equals("1")) && individual.getQ101().equals("2") && individual.getQ401().equals("1")   && (Integer.parseInt( individual.getQ102() )>14
                && Integer.parseInt( individual.getQ102() )<50) && (individual.getQ801f().equals("2") || individual.getQ801f().equals("3") ||
                individual.getQ801f().equals("4") || individual.getQ801f().equals("9")))
        {
            Intent intent = new Intent(q803.this, q1001.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }
        else {

        }
        if(individual.getQ801a().equals("1") && individual.getQ101().equals("1"))
        {
            Intent intent = new Intent(q803.this, q1101.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }
        else {

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
                    lib.showError(q803.this, "Q803 Error", "Please select an option for q803");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                } else {
                    if (rbtnother.isChecked() && (edtother.length() == 0 || edtother.getText() == null)) {
                        lib.showError(q803.this, "Q803 Error: Other", "Please type OTHER specify for q803");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    } else {


                    if (rbtn8.isChecked() ) {
                      //  Intent intent = new Intent(q803.this, q901.class);;

                        Intent intent = new Intent(q803.this, q901.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
                    }
                    else {


                            //  Intent intent = new Intent(q803.this, q901.class);;
                      individual.setQ803(selected.getText().toString().substring(0, 1));
                       individual.setQ803Other(edtother.getText().toString());

                            Intent intent = new Intent(q803.this, q804.class);
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
                q803.super.onBackPressed();
            }


        });
    }
}


