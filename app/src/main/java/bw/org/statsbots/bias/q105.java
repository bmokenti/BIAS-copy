package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class q105 extends AppCompatActivity implements Serializable{


    protected LibraryClass lib;
    protected  RadioButton rbtn1,rbtn2,rbtn3, rbtn4,rbtn5,rbtn6, rbtn7,rbtn8, rbtn9 ;
    protected RadioGroup rbtngroup;
    protected  HouseHold thisHouse;
    protected Individual individual;
    protected RadioButton selectedRbtn;
    protected EditText edt, edt1, edt2;
    TextView q105text;
    PersonRoster p1 = null;
    Individual pp1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q105);


        setTitle("Q105. EMPLOYMENT STATUS ");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q105_1a);
        rbtn2 =  (RadioButton)findViewById(R.id.q105_1b);
        rbtn3 =  (RadioButton)findViewById(R.id.q105_1c);
        rbtn4 =  (RadioButton)findViewById(R.id.q105_1d);
        rbtn5 =  (RadioButton)findViewById(R.id.q105_1e);
        rbtn6 =  (RadioButton)findViewById(R.id.q105_1f);
        rbtn7 =  (RadioButton)findViewById(R.id.q105_1g);
        rbtn8 =  (RadioButton)findViewById(R.id.q105_1h);
        rbtn9 = findViewById(R.id.q105_other);
        edt = (EditText) findViewById(R.id.Q105_txtOther);
        edt1 = (EditText) findViewById(R.id.Q105atxt);
        edt2 = (EditText) findViewById(R.id.Q105btxt);

        final RadioGroup rg = (RadioGroup)findViewById(R.id.q105radioGroup);

        lib = new LibraryClass();

        //edittext = (EditText) findViewById(R.id.q102_years);
        //edittext.setVisibility(View.VISIBLE);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;


        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn3.isChecked()) {
                    edt.setVisibility(View.INVISIBLE);
                    edt.setText("");

                }
            }
        });

        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn3.isChecked()) {
                    edt.setVisibility(View.INVISIBLE);
                    edt.setText("");

                }
            }
        });
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn3.isChecked()) {
                    edt.setVisibility(View.INVISIBLE);
                    edt.setText("");

                }
            }
        });

        rbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn3.isChecked()) {
                    edt.setVisibility(View.INVISIBLE);
                    edt.setText("");

                }
            }
        });
        rbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn3.isChecked()) {
                    edt.setVisibility(View.INVISIBLE);
                    edt.setText("");

                }
            }
        });
        rbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn3.isChecked()) {
                    edt.setVisibility(View.INVISIBLE);
                    edt.setText("");

                }
            }
        });

        rbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn3.isChecked()) {
                    edt.setVisibility(View.INVISIBLE);
                    edt.setText("");

                }
            }
        });
        rbtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn3.isChecked()) {
                    edt.setVisibility(View.INVISIBLE);
                    edt.setText("");

                }
            }
        });
        rbtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn9.isChecked()) {
                    edt.setVisibility(View.VISIBLE);

                }
            }
        });

        /**
         * NEXT Person BUTTON
         */
        Button btnNext = (Button)findViewById(R.id.button);


        //btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Years;


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if(selectedRbtn == null)
                {
                    lib.showError(q105.this,"q105 Error","Please select employment status");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                }
                else {
                    if (rbtn9.isChecked() && (edt.getText() == null || edt.length() == 0)) {
                        lib.showError(q105.this, "q105 Other specify", "If the Other specify button is selected, other specify box should have a value");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);


                    }
                    else
                        {
                        if (edt1.length() == 0 || edt1.getText() == null) {
                            lib.showError(q105.this, "q105a Error", "Please state current occupation?");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);


                        } else {
                            if (edt2.length() == 0 || edt2.getText() == null) {
                                lib.showError(q105.this, "q105b Error", "What is the main product, service or activity at your place of work?");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);


                            } else {
                                individual.setQ105(edt.getText().toString());
                                individual.setQ105Other(selectedRbtn.getText().toString());
                                individual.setQ105a(selectedRbtn.getText().toString());
                                individual.setQ105b(selectedRbtn.getText().toString());
                                //Set P02 fir the current individual
                                //thisHouse.getPersons()[p1.getLineNumber()].setP07(years);
                                //Restart the current activity for next individual


                                //Next question P17
                                Intent intent = new Intent(q105.this, q106.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);


                            }


                        }
                    }
                }

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
