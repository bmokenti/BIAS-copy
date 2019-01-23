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
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q102);

        setTitle("Q102 Age");

        lib = new LibraryClass();

        //edittext = (EditText) findViewById(R.id.q102_years);
        //edittext.setVisibility(View.VISIBLE);

        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;
        edt = (EditText)findViewById(R.id.q102_years);


            /**
             * NEXT Person BUTTON
             */
            Button btnNext = (Button)findViewById(R.id.button);


            //btnNext.setText(btnLabel);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int Years;
                     edt = (EditText)findViewById(R.id.q102_years);
                    String years=edt.getText().toString();

                    if(years.length()==0 || years.equals("00") || years.equals("") || Integer.valueOf(edt.getText().toString()) <= 14)
                    {
                        lib.showError(q102.this,"q102 Error","Please enter age in completd years, Age must be 15 and above");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    }
                    else{
                        //individual.setQ102(edt.getText().toString());
                        //Set P02 fir the current individual
                        //thisHouse.getPersons()[p1.getLineNumber()].setP07(years);
                        //Restart the current activity for next individual


                            //Next question P17
                            Intent intent = new Intent(q102.this, q103.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);







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
