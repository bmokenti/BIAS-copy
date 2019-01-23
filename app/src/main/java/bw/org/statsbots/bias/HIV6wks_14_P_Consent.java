package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class HIV6wks_14_P_Consent extends AppCompatActivity implements Serializable, View.OnClickListener {
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected  RadioButton prbtn1,prbtn2,prbtn3, prbtn4,prbtn5,prbtn6, selected=null, selected1=null, selected2=null;
    protected RadioGroup rbtngroup1, rbtngroup2, rbtngroup3;
    protected EditText txtnameofinterviwer, txtinterviewerID, txtParentID, txtdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiv_6wks_14_p_consent);

        setTitle("Parental Consent for Childern 6weeks-14 years");
        lib = new LibraryClass();
        prbtn1 =  (RadioButton)findViewById(R.id.rbtn1);
        prbtn2 =  (RadioButton)findViewById(R.id.rbtn2);
        prbtn3 =  (RadioButton)findViewById(R.id.rbtn3);
        prbtn4 =  (RadioButton)findViewById(R.id.rbtn4);
        prbtn5 =  (RadioButton)findViewById(R.id.rbtn5);
        prbtn6 =  (RadioButton)findViewById(R.id.rbtn6);
        txtnameofinterviwer = findViewById(R.id.NameOfInterviewerTXT);
                txtinterviewerID   = findViewById(R.id.SurbveyStaffIDTXT);
                txtParentID = findViewById(R.id.ChildParticipantsIDTXT);
                txtdate = findViewById(R.id.DateTxt);


        rbtngroup1 = (RadioGroup)findViewById(R.id.ParentalradioGroup) ;
        rbtngroup2 = (RadioGroup)findViewById(R.id.ParentalradioGroup1) ;
        rbtngroup3 = (RadioGroup)findViewById(R.id.ParentalradioGroup2) ;



        prbtn1.setOnClickListener(this);
        prbtn2.setOnClickListener(this);
        prbtn3.setOnClickListener(this);

        prbtn4.setOnClickListener(this);
        prbtn5.setOnClickListener(this);
        prbtn6.setOnClickListener(this);

        final int selectedId = rbtngroup1.getCheckedRadioButtonId();
        final int selectedId1 = rbtngroup2.getCheckedRadioButtonId();
        final int selectedId2 = rbtngroup3.getCheckedRadioButtonId();

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
                    int selectedId = rbtngroup1.getCheckedRadioButtonId();
                    selected = (RadioButton) findViewById(selectedId);

                    int selectedId1 = rbtngroup2.getCheckedRadioButtonId();
                    selected1 = (RadioButton) findViewById(selectedId1);

                    int selectedId2 = rbtngroup3.getCheckedRadioButtonId();
                    selected2 = (RadioButton) findViewById(selectedId2);

                    if (selected == null) {
                        lib.showError(HIV6wks_14_P_Consent.this, "HIV6wks_14_P_Consent:Q2 ", "Do you agree for survey team to ask your child to give blood for HIV testing?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else
                        {
                        if (selected2 == null) {
                            lib.showError(HIV6wks_14_P_Consent.this, "HIV6wks_14_P_Consent: Q3", "Do you agree for your child's blood sample to be sent for additional HIV testing");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {
                            if (selected2 == null) {
                                lib.showError(HIV6wks_14_P_Consent.this, "HIV6wks_14_P_Consent: Q4", "Do you agree to some of your child's blood stored for future research");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {
                                //check EditText

                                String etd1 = txtnameofinterviwer.getText().toString();
                                if ( etd1.length()==0 || etd1.equals("")) {
                                    lib.showError(HIV6wks_14_P_Consent.this, "HIV6wks_14_P_Consent: Q4", "name of the interviewer");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);
                                } else {

                                    String etd2 = txtinterviewerID.getText().toString();
                                    if (etd2.length()==0 || etd2.equals("")) {
                                        lib.showError(HIV6wks_14_P_Consent.this, "HIV6wks_14_P_Consent: Q4", "ID of the interviewer");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);
                                    } else {

                                        String etd3 = txtParentID.getText().toString();
                                        if (etd3.length()==0 || etd3.equals("")) {
                                            lib.showError(HIV6wks_14_P_Consent.this, "HIV6wks_14_P_Consent: Q4", "Parent or Gudian ID");
                                            /**
                                             * VIBRATE DEVICE
                                             */
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);
                                        }
                                        else {

                                            String date = txtdate.getText().toString();
                                            if (date.length()==0 || date.equals("")) {
                                                lib.showError(HIV6wks_14_P_Consent.this, "HIV6wks_14_P_Consent: Q4", "Record Date");
                                                /**
                                                 * VIBRATE DEVICE
                                                 */
                                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                vibs.vibrate(100);
                                            }
                                            else {


                                                //Next question P07
                                                Intent intent = new Intent(HIV6wks_14_P_Consent.this, Adults15_64_RapidTest.class);
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




