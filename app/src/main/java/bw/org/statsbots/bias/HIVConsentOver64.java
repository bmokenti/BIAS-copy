package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class HIVConsentOver64 extends AppCompatActivity implements Serializable, View.OnClickListener {

    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected  RadioButton rbtn1,rbtn2,rbtn3, rbtn4,rbtn5,rbtn6,rbtn8,rbtn7,  selected=null, selected1=null, selected2=null, selected3=null;
    protected RadioGroup rbtngroup, rbtngroup1, rbtngroup2, rbtngroup3;
    protected EditText txtnameofinterviwer, txtinterviewerID, txtParentID, txtdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hivconsent_over64);
        setTitle("HIV Consent Adults Over 64 years");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.rbtnq1y);
        rbtn2 =  (RadioButton)findViewById(R.id.rbtnq1n);
        rbtn3 =  (RadioButton)findViewById(R.id.rbtnq2y);
        rbtn4 =  (RadioButton)findViewById(R.id.rbtnq2n);
       // rbtn5 =  (RadioButton)findViewById(R.id.rbtnq3y);
       // rbtn6 =  (RadioButton)findViewById(R.id.rbtnq3n);
        rbtn7 =  (RadioButton)findViewById(R.id.rbtnq4y);
        rbtn8 =  (RadioButton)findViewById(R.id.rbtnq4n);

        txtnameofinterviwer = findViewById(R.id.NameOfInterviewerTXT);
        txtinterviewerID   = findViewById(R.id.SurbveyStaffIDTXT);
        txtParentID = findViewById(R.id.ChildParticipantsIDTXT);
        txtdate = findViewById(R.id.DateTxt);

        rbtngroup = (RadioGroup)findViewById(R.id.ParentalradioGroup) ;
        rbtngroup1 = (RadioGroup)findViewById(R.id.ParentalradioGroup1) ;
        //rbtngroup2 = (RadioGroup)findViewById(R.id.ParentalradioGroup2) ;
        rbtngroup3 = (RadioGroup)findViewById(R.id.ParentalradioGroup3) ;



        rbtn1.setOnClickListener(this);
        rbtn2.setOnClickListener(this);
        rbtn3.setOnClickListener(this);

        rbtn4.setOnClickListener(this);
        //rbtn5.setOnClickListener(this);
        //rbtn6.setOnClickListener(this);
        rbtn7.setOnClickListener(this);
        rbtn8.setOnClickListener(this);


        final int selectedId = rbtngroup.getCheckedRadioButtonId();
        final int selectedId1 = rbtngroup1.getCheckedRadioButtonId();
        final int selectedId2 = rbtngroup2.getCheckedRadioButtonId();
        final int selectedId3 = rbtngroup3.getCheckedRadioButtonId();

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        int p=0;



        /**
         * NEXT Person BUTTON
         */
        Button btnNext = (Button)findViewById(R.id.button);



        /**
         * NEXT BUTTON
         */
        //btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {


                int selectedId = rbtngroup.getCheckedRadioButtonId();
                selected = (RadioButton) findViewById(selectedId);

                int selectedId1 = rbtngroup1.getCheckedRadioButtonId();
                selected1 = (RadioButton) findViewById(selectedId1);

                //int selectedId2 = rbtngroup2.getCheckedRadioButtonId();
               // selected2 = (RadioButton) findViewById(selectedId2);

                int selectedId3 = rbtngroup3.getCheckedRadioButtonId();
                selected3 = (RadioButton) findViewById(selectedId3);

                if (selected == null) {
                    lib.showError(HIVConsentOver64.this, "Q1 ", "[1] Do you agree for survey team to ask your child to do the interview?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    if (selected1 == null) {
                        lib.showError(HIVConsentOver64.this, " Q2", "[2] Do you agree for survey team to ask your child to give blood for HIV testing and other related testing?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }  else {
                            if (selected3 == null) {
                                lib.showError(HIVConsentOver64.this, " Q3", "[3]: Do you agree to have some of your child’s blood stored for future research?");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {
                                //check EditText

                                String etd1 = txtnameofinterviwer.getText().toString();
                                if (etd1.length() == 0 || etd1.equals("")) {
                                    lib.showError(HIVConsentOver64.this, "HIVConsentOver64: ", "name of the interviewer");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);
                                } else {

                                    String etd2 = txtinterviewerID.getText().toString();
                                    if (etd2.length() == 0 || etd2.equals("")) {
                                        lib.showError(HIVConsentOver64.this, "HIVConsentOver64: ", "ID of the interviewer");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);
                                    } else {

                                        String etd3 = txtParentID.getText().toString();
                                        if (etd3.length() == 0 || etd3.equals("")) {
                                            lib.showError(HIVConsentOver64.this, "HIVConsentOver64: ", "Parent or Gudian ID");
                                            /**
                                             * VIBRATE DEVICE
                                             */
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);
                                        } else {

                                            String date = txtdate.getText().toString();
                                            if (date.length() == 0 || date.equals("")) {
                                                lib.showError(HIVConsentOver64.this, "HIVConsentOver64: ", "Record Date");
                                                /**
                                                 * VIBRATE DEVICE
                                                 */
                                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                vibs.vibrate(100);
                                            } else {


                                                //Next question P07
                                                Intent intent = new Intent(HIVConsentOver64.this, Adults15_64_RapidTest.class);
                                                intent.putExtra("Household", thisHouse);
                                                startActivity(intent);

                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }

        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbtn1:
                selected = (RadioButton)findViewById(R.id.rbtn1);
                break;
            case R.id.rbtn2:
                selected = (RadioButton)findViewById(R.id.rbtn2);
                break;
            case R.id.rbtn3:
                selected = (RadioButton)findViewById(R.id.rbtn3);
                break;

            case R.id.rbtn4:
                selected = (RadioButton)findViewById(R.id.rbtn4);
                break;
            case R.id.rbtn5:
                selected = (RadioButton)findViewById(R.id.rbtn5);
                break;
            case R.id.rbtn6:
                selected = (RadioButton)findViewById(R.id.rbtn6);
                break;
            default:
                break;
        }
    }
}





