package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class HIVParentalConsent10_14yrs extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected Individual individual;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn8, rbtn7,rbtn9, rbtn10,rbtn11,rbtn12, rbtn13,  selected1, selected2, selected3,  selected4, selected5 , selected6 ;
    protected RadioGroup rg1, rg2, rg3, rg4, rg5, rg6;
    protected EditText EDTParentID, EdtDate;
    protected DatabaseHelper myDB;
    protected CheckBox vol1, vol2, vol3, vol4;
    protected Button btnNext, btnDate, btnPrev;
    protected TextView t1, t2, t3, t4, t5, t6, t7, t8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hivparental_consent10_14yrs);


                setTitle("HIV Parental Consent 10-14 years");

        Intent h = getIntent();
        p1 = (PersonRoster) h.getSerializableExtra("Personroster");


        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());

        myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());


                lib = new LibraryClass();

                rg1 = (RadioGroup) findViewById(R.id.rg1);
                rg2 = (RadioGroup) findViewById(R.id.rg2);
                //rg3 = (RadioGroup) findViewById(R.id.rg3);
                rg4 = (RadioGroup) findViewById(R.id.rg4);
                rg5 = (RadioGroup) findViewById(R.id.rg5);
                rg6 = (RadioGroup) findViewById(R.id.rg6);

                rbtn1 = (RadioButton) findViewById(R.id.rbtn1);
                rbtn2 = (RadioButton) findViewById(R.id.rbtn2);
                rbtn3 = (RadioButton) findViewById(R.id.rbtn3);
                rbtn4 = (RadioButton) findViewById(R.id.rbtn4);
               // rbtn5 = (RadioButton) findViewById(R.id.rbtn5);
                //rbtn6 = (RadioButton) findViewById(R.id.rbtn6);
                rbtn7 = (RadioButton) findViewById(R.id.rbtn7);
                rbtn8 = (RadioButton) findViewById(R.id.rbtn8);
                rbtn9 = (RadioButton) findViewById(R.id.rbtn9);
                rbtn10 = (RadioButton) findViewById(R.id.rbtn10);
                rbtn11 = (RadioButton) findViewById(R.id.rbtn11);
                rbtn12 = (RadioButton) findViewById(R.id.rbtn12);
                rbtn13 = (RadioButton) findViewById(R.id.rbtn13);
                EDTParentID = (EditText) findViewById(R.id.ParentTxt);
                EdtDate = (EditText) findViewById(R.id.DateTxt);
                btnDate = (Button) findViewById(R.id.datebtn);
                btnNext = (Button) findViewById(R.id.btnnext);
                btnPrev = (Button) findViewById(R.id.btnprev);

               // vol1 = (CheckBox) findViewById(R.id.vol1);
               // vol2 = (CheckBox) findViewById(R.id.vol2);
               // vol3 = (CheckBox) findViewById(R.id.vol3);
               // vol4 = (CheckBox) findViewById(R.id.vol4);

               // t1 = (TextView) findViewById(R.id.volrectext);
                t2 = (TextView) findViewById(R.id.rhttxt);
               // t3 = (TextView) findViewById(R.id.rhtresulttext);
                t4 = (TextView) findViewById(R.id.txtslab);
               // t6 = (TextView) findViewById(R.id.bloodColectionStatus);
               // t5 = (TextView) findViewById(R.id.txtstore);

                rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (i == R.id.rbtn1) {
                            // is checked




                            rbtn7.setEnabled(true);
                            rbtn8.setEnabled(true);
                            rbtn9.setEnabled(true);
                            rbtn10.setEnabled(true);
                            t4.setTextColor(Color.BLACK);
                        //    t5.setTextColor(Color.BLACK);




                        } else {




                            rbtn7.setEnabled(false);
                            rbtn8.setEnabled(false);
                            rbtn9.setEnabled(false);
                            rbtn10.setEnabled(false);
                            t4.setTextColor(Color.LTGRAY);
                            //t5.setTextColor(Color.LTGRAY);



                            rbtn7.setChecked(false);
                            rbtn8.setChecked(false);
                            rbtn9.setChecked(false);
                            rbtn10.setChecked(false);



                        }
                    }
                });




        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int selectedId1 = rg1.getCheckedRadioButtonId();
                        selected1 = (RadioButton) findViewById(selectedId1);

                        if (selected1 == null) {
                            lib.showError(bw.org.statsbots.bias.HIVParentalConsent10_14yrs.this, "Draw Blood: Error", "Do you agree for the survey team to draw your blood for HIV testing and other related testing?");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);

                        }  else {
                                int selectedId2 = rg2.getCheckedRadioButtonId();
                                selected2 = (RadioButton) findViewById(selectedId2);

                                if (selected2 == null) {
                                    lib.showError(bw.org.statsbots.bias.HIVParentalConsent10_14yrs.this, "RHT: Error: 2", "Do you agree for the survey team to do RHT?");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);

                                }  else {
                                        int selectedId4 = rg4.getCheckedRadioButtonId();
                                        selected4 = (RadioButton) findViewById(selectedId4);

                                        if (selected4 == null && rbtn1.isChecked()) {
                                            lib.showError(bw.org.statsbots.bias.HIVParentalConsent10_14yrs.this, "Laboratory: Error: 3", "3. Do you agree for your blood sample to be sent to the laboratory for additional HIV related testing?");
                                            /**
                                             * VIBRATE DEVICE
                                             */
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);

                                        } else {
                                            int selectedId5 = rg5.getCheckedRadioButtonId();
                                            selected5 = (RadioButton) findViewById(selectedId5);

                                            if (selected5 == null && rbtn1.isChecked()) {
                                                lib.showError(bw.org.statsbots.bias.HIVParentalConsent10_14yrs.this, "Storage: Error: 4", "4. Do you agree for your blood sample to be stored for up to 5 years for future HIV/TB - related research?");
                                                /**
                                                 * VIBRATE DEVICE
                                                 */
                                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                vibs.vibrate(100);

                                            } else {


                                                if (EdtDate == null) {
                                                    lib.showError(bw.org.statsbots.bias.HIVParentalConsent10_14yrs.this, "DATE: Error: ", "Please record date");
                                                    /**
                                                     * VIBRATE DEVICE
                                                     */
                                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                    vibs.vibrate(100);

                                                } else {

                                                    thisHouse.getPersons()[p1.getLineNumber()].setU15Rapid_Results(selected3.getText().toString().substring(0,1));



                                                    Intent intent = new Intent(bw.org.statsbots.bias.HIVParentalConsent10_14yrs.this, HIVAssents10_14years.class);
                                                        intent.putExtra("Household", thisHouse);
                                                        startActivity(intent);



                                                }

                                        }
                                    }
                                }
                            }

                    }
                });


            }
        }

