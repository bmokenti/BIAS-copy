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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HIVAdultsConsent18Plus extends AppCompatActivity implements Serializable {

            protected HouseHold thisHouse;
            protected PersonRoster p1 = null;
            protected Individual individual;
            protected String currentHH = null;
            protected LibraryClass lib;
            protected DatabaseHelper myDB;
            protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn8, rbtn7,rbtn9, rbtn10,rbtn11,rbtn12, rbtn13,  selected1, selected2, selected3,  selected4, selected5 , selected6 ;
            protected RadioGroup rg1, rg2, rg3, rg4, rg5, rg6;
            protected EditText Edttubevolume, EdtDate, EdtparentID;
            protected CheckBox vol1, vol2, vol3, vol4;
            protected Button btnNext, btnDate, btnPrev;
            protected TextView t1, t2, t3, t4, t5, t6, t7, t8;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_hivadults_consent18_plus);



                lib = new LibraryClass();

                Intent h = getIntent();
                thisHouse = (HouseHold) h.getSerializableExtra("Household");



                Intent i = getIntent();
                individual = (Individual) i.getSerializableExtra("Individual");
                //int p = 0;

                Intent ii = getIntent();
                p1 = (PersonRoster) ii.getSerializableExtra("Personroster");
                //int p = 0;


                myDB = new DatabaseHelper(this);
                myDB.getWritableDatabase();

                //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());

                final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
                individual = ind;

                final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
                sample.getSTATUS();


                final List<PersonRoster> roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
                for (PersonRoster p: roster
                ) {
                    if (p.getSRNO() == ind.getSRNO()){
                        p1 = p;
                        break;
                    }


                }

                if (  (individual.getQ102() != null && Integer.valueOf(individual.getQ102()) >= 18 )  || (Integer.valueOf(p1.getP04YY()) >= 18 ) ) {
                    setTitle("Individual Consent [18-64]");
                }
                else
                {
                    setTitle("Individual Assent 15-17");
                }

                if(individual.getQ102() != null && Integer.valueOf(individual.getQ102()) >= 65 && sample.getStatusCode().equals("2")  )
                {
                    Intent intent = new Intent(HIVAdultsConsent18Plus.this, HIVConsentOver64.class);
                    intent.putExtra("Individual", individual);
                    intent.putExtra("Personroster", p1);
                    startActivity(intent);
                }

                if(( individual.getQ102() != null && Integer.valueOf(individual.getQ102()) <= 17  && individual.getPrntlConsentBloodDraw().equals(2) && individual.getPrntlConsentRHT().equals(2)))
                {
                    Intent intent = new Intent(HIVAdultsConsent18Plus.this, Dashboard.class);
                    intent.putExtra("Individual", individual);
                    intent.putExtra("Personroster", p1);
                    startActivity(intent);
                }
                rg1 = (RadioGroup) findViewById(R.id.rg1);
                rg2 = (RadioGroup) findViewById(R.id.rg2);
                rg3 = (RadioGroup) findViewById(R.id.rg3);
                rg4 = (RadioGroup) findViewById(R.id.rg4);
                rg5 = (RadioGroup) findViewById(R.id.rg5);
                rg6 = (RadioGroup) findViewById(R.id.rg6);

                rbtn1 = (RadioButton) findViewById(R.id.rbtn1);
                rbtn2 = (RadioButton) findViewById(R.id.rbtn2);
                rbtn3 = (RadioButton) findViewById(R.id.rbtn3);
                rbtn4 = (RadioButton) findViewById(R.id.rbtn4);
                rbtn5 = (RadioButton) findViewById(R.id.rbtn5);
                rbtn6 = (RadioButton) findViewById(R.id.rbtn6);
                rbtn7 = (RadioButton) findViewById(R.id.rbtn7);
                rbtn8 = (RadioButton) findViewById(R.id.rbtn8);
                rbtn9 = (RadioButton) findViewById(R.id.rbtn9);
                rbtn10 = (RadioButton) findViewById(R.id.rbtn10);
                rbtn11 = (RadioButton) findViewById(R.id.rbtn11);
                rbtn12 = (RadioButton) findViewById(R.id.rbtn12);
                rbtn13 = (RadioButton) findViewById(R.id.rbtn13);
                Edttubevolume = (EditText) findViewById(R.id.TubeVolComment);
                EdtDate = (EditText) findViewById(R.id.DateTxt);
                EdtparentID = (EditText) findViewById(R.id.ParentTxt);
                btnDate = (Button) findViewById(R.id.datebtn);
                btnNext = (Button) findViewById(R.id.btnnext);
                btnPrev = (Button) findViewById(R.id.btnprev);

                vol1 = (CheckBox) findViewById(R.id.vol1);
                vol2 = (CheckBox) findViewById(R.id.vol2);
                vol3 = (CheckBox) findViewById(R.id.vol3);
                vol4 = (CheckBox) findViewById(R.id.vol4);

                t1  = (TextView) findViewById(R.id.volrectext);
                t2  = (TextView) findViewById(R.id.rhttxt);
                t3  = (TextView) findViewById(R.id.rhtresulttext);
                t4  = (TextView) findViewById(R.id.txtslab);
                t6  = (TextView) findViewById(R.id.bloodColectionStatus);
                t5  = (TextView) findViewById(R.id.txtstore);


                rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if(i == R.id.rbtn1)
                        {
                            // is checked
                            vol1.setEnabled(true);
                            vol2.setEnabled(true);
                            vol3.setEnabled(false);
                            vol4.setEnabled(true);
                            Edttubevolume.setEnabled(true);
                            t1.setTextColor(Color.BLACK);


                            rbtn7.setEnabled(true);
                            rbtn8.setEnabled(true);
                            rbtn9.setEnabled(true);
                            rbtn10.setEnabled(true);
                            t4.setTextColor(Color.BLACK);
                            t5.setTextColor(Color.BLACK);



                            rbtn11.setEnabled(true);
                            rbtn12.setEnabled(true);
                            rbtn13.setEnabled(true);
                            t6.setTextColor(Color.BLACK);


                        }
                        else
                        {
                            vol1.setEnabled(false);
                            vol2.setEnabled(false);
                            vol3.setEnabled(false);
                            vol4.setEnabled(false);
                            t1.setTextColor(Color.LTGRAY);
                            Edttubevolume.setEnabled(false);

                            vol1.setChecked(false);
                            vol2.setChecked(false);
                            vol3.setChecked(false);
                            vol4.setChecked(false);
                            Edttubevolume.setText("");

                            rbtn7.setEnabled(false);
                            rbtn8.setEnabled(false);
                            rbtn9.setEnabled(false);
                            rbtn10.setEnabled(false);
                            t4.setTextColor(Color.LTGRAY);
                            t5.setTextColor(Color.LTGRAY);

                            rbtn11.setEnabled(false);
                            rbtn12.setEnabled(false);
                            rbtn13.setEnabled(false);
                            t6.setTextColor(Color.LTGRAY);


                            rbtn7.setChecked(false);
                            rbtn8.setChecked(false);
                            rbtn9.setChecked(false);
                            rbtn10.setChecked(false);

                            rbtn11.setChecked(false);
                            rbtn12.setChecked(false);
                            rbtn13.setChecked(false);


                        }
                    }
                });


                if (individual.getPrntlConsentBloodDraw()!= null && individual.getPrntlConsentBloodDraw().equals("2"))
                {   t1.setTextColor(Color.LTGRAY);
                    vol1.setEnabled(false);
                    vol2.setEnabled(false);
                    vol3.setEnabled(false);
                    vol4.setEnabled(false);


                    rbtn11.setEnabled(false);
                    rbtn12.setEnabled(false);
                    rbtn13.setEnabled(false);
                    t6.setTextColor(Color.LTGRAY);
                }

                if (individual.getQ102() != null && Integer.valueOf(individual.getQ102())>= 15 )
                {
                    vol3.setEnabled(false);

                }

                rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if(i == R.id.rbtn3)
                        {
                            // is checked

                            rbtn5.setEnabled(true);
                            rbtn6.setEnabled(true);
                            t3.setTextColor(Color.BLACK);

                        }
                        else
                        {
                            rbtn5.setEnabled(false);
                            rbtn6.setEnabled(false);
                            t3.setTextColor(Color.LTGRAY);
                            rbtn5.setChecked(false);
                            rbtn6.setChecked(false);




                        }
                    }
                });


                RadioButton[] bt1 = new RadioButton[2];
                for(int f=0;f<rg1.getChildCount();f++)
                {
                    View o = rg1.getChildAt(f);
                    if (o instanceof RadioButton)
                    {
                        bt1[f]=((RadioButton)o);
                        if(individual.getIndvBloodDraw()!= null &&  !individual.getIndvBloodDraw().equals(""))
                        {
                            if(Integer.parseInt(individual.getIndvBloodDraw())==f+1)
                            {
                                RadioButton radioButton = bt1[f];
                                radioButton.setChecked(true);
                                break;
                            }
                        }
                    }
                }


                RadioButton[] bt2 = new RadioButton[2];
                for(int f=0;f<rg2.getChildCount();f++)
                {
                    View o = rg2.getChildAt(f);
                    if (o instanceof RadioButton)
                    {
                        bt2[f]=((RadioButton)o);
                        if(individual.getB8_O15_Rapid()!= null &&  !individual.getB8_O15_Rapid().equals(""))
                        {
                            if(Integer.parseInt(individual.getB8_O15_Rapid())==f+1)
                            {
                                RadioButton radioButton = bt2[f];
                                radioButton.setChecked(true);
                                break;
                            }
                        }
                    }
                }

                RadioButton[] bt3 = new RadioButton[2];
                for(int f=0;f<rg3.getChildCount();f++)
                {
                    View o = rg3.getChildAt(f);
                    if (o instanceof RadioButton)
                    {
                        bt3[f]=((RadioButton)o);
                        if(individual.getIndRapidResults()!= null &&  !individual.getIndRapidResults().equals(""))
                        {
                            if(Integer.parseInt(individual.getIndRapidResults())==f+1)
                            {
                                RadioButton radioButton = bt3[f];
                                radioButton.setChecked(true);
                                break;
                            }
                        }
                    }
                }

                RadioButton[] bt4 = new RadioButton[2];
                for(int f=0;f<rg4.getChildCount();f++)
                {
                    View o = rg4.getChildAt(f);
                    if (o instanceof RadioButton)
                    {
                        bt4[f]=((RadioButton)o);
                        if(individual.getIndBloodLabTest()!= null &&  !individual.getIndBloodLabTest().equals(""))
                        {
                            if(Integer.parseInt(individual.getIndBloodLabTest())==f+1)
                            {
                                RadioButton radioButton = bt4[f];
                                radioButton.setChecked(true);
                                break;
                            }
                        }
                    }
                }

                RadioButton[] bt5 = new RadioButton[2];
                for(int f=0;f<rg5.getChildCount();f++)
                {
                    View o = rg5.getChildAt(f);
                    if (o instanceof RadioButton)
                    {
                        bt5[f]=((RadioButton)o);
                        if(individual.getIndBloodStore()!= null &&  !individual.getIndBloodStore().equals(""))
                        {
                            if(Integer.parseInt(individual.getIndBloodStore())==f+1)
                            {
                                RadioButton radioButton = bt5[f];
                                radioButton.setChecked(true);
                                break;
                            }
                        }
                    }
                }


                if( individual.getIndRapidDate() != null)
                {
                    EdtDate.setText(individual.getIndRapidDate());
                }

                RadioButton[] bt6 = new RadioButton[3];
                for(int f=0;f<rg6.getChildCount();f++)
                {
                    View o = rg6.getChildAt(f);
                    if (o instanceof RadioButton)
                    {
                        bt6[f]=((RadioButton)o);
                        if(individual.getIndBloodSampleCollected()!= null &&  !individual.getIndBloodSampleCollected().equals(""))
                        {
                            if(Integer.parseInt(individual.getIndBloodSampleCollected())==f+1)
                            {
                                RadioButton radioButton = bt6[f];
                                radioButton.setChecked(true);
                                break;
                            }
                        }
                    }
                }
                if(individual.getBloodVol_1()!= null &&  !individual.getBloodVol_1().equals(""))
                {
                    if(Integer.parseInt(individual.getBloodVol_1())== 1)
                    {
                        vol1.setChecked(true);

                    }else
                    {
                        vol1.setChecked(false);
                    }
                }

                if(individual.getBloodVol_4()!= null &&  !individual.getBloodVol_4().equals(""))
                {
                    if(Integer.parseInt(individual.getBloodVol_4())== 1)
                    {
                        vol2.setChecked(true);

                    }else
                    {
                        vol2.setChecked(false);
                    }
                }

                if(individual.getBloodVol_6()!= null &&  !individual.getBloodVol_6().equals(""))
                {
                    if(Integer.parseInt(individual.getBloodVol_6())== 1)
                    {
                        vol3.setChecked(true);

                    }else
                    {
                        vol3.setChecked(false);
                    }
                }

                if(individual.getBloodVol_10()!= null &&  !individual.getBloodVol_10().equals(""))
                {
                    if(Integer.parseInt(individual.getBloodVol_10())== 1)
                    {
                        vol4.setChecked(true);

                    }else
                    {
                        vol4.setChecked(false);
                    }
                }



                btnDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Create Date Object
                        Date today = new Date();

                        //Convert to calendar Object
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(today);


                        CharSequence s = android.text.format.DateFormat.format("dd/MM/yyyy",today.getTime());
                        EdtDate.setText(s.toString());

                    }
                });


                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int selectedId1 = rg1.getCheckedRadioButtonId();
                        selected1 = (RadioButton) findViewById(selectedId1);

                        if (selected1 == null) {
                            lib.showError(HIVAdultsConsent18Plus.this, "Draw Blood: Error", "Do you agree for the survey team to draw your blood for HIV testing and other related testing?");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);

                        }
                        else {

                            if (!(vol1.isChecked() || vol2.isChecked() || vol3.isChecked() || vol4.isChecked()) && rbtn1.isChecked()) {
                                lib.showError(HIVAdultsConsent18Plus.this, "IndividualConsent: Error: 1a", "Please record container used");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);

                            }
                            else {

                                if (((vol2.isChecked() && (!vol4.isChecked()) )) || ((vol4.isChecked() && (!vol2.isChecked())))
                                || (vol2.isChecked() && vol1.isChecked()) ||  (vol4.isChecked() && vol1.isChecked()) || (vol4.isChecked() && vol1.isChecked() && vol2.isChecked()) ){
                                    lib.showError(HIVAdultsConsent18Plus.this, "IndividualConsent: Error: 1a", "10ml container and 4ml container can not be used" +
                                            " in isolation. The participant need 14ml or just 1ml for rht. Please select accordingly");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);

                                }else {
                                int selectedId2 = rg2.getCheckedRadioButtonId();
                                selected2 = (RadioButton) findViewById(selectedId2);

                                if (selected2 == null ) {
                                    lib.showError(HIVAdultsConsent18Plus.this, "RHT: Error: 2", "Do you agree for the survey team to do RHT?");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);

                                } else {
                                    int selectedId3 = rg3.getCheckedRadioButtonId();
                                    selected3 = (RadioButton) findViewById(selectedId3);

                                    if (selected3 == null && rbtn3.isChecked()) {
                                        lib.showError(HIVAdultsConsent18Plus.this, "RHT Results: Error: 2a", "Please record RHT results?");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);

                                    } else {
                                        int selectedId4 = rg4.getCheckedRadioButtonId();
                                        selected4 = (RadioButton) findViewById(selectedId4);

                                        if (selected4 == null && rbtn1.isChecked()) {
                                            lib.showError(HIVAdultsConsent18Plus.this, "Laboratory: Error: 3", "3. Do you agree for your blood sample to be sent to the laboratory for additional HIV related testing?");
                                            /**
                                             * VIBRATE DEVICE
                                             */
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);

                                        } else {
                                            int selectedId5 = rg5.getCheckedRadioButtonId();
                                            selected5 = (RadioButton) findViewById(selectedId5);

                                            if (selected5 == null && rbtn1.isChecked()) {
                                                lib.showError(HIVAdultsConsent18Plus.this, "Storage: Error: 4", "4. Do you agree for your blood sample to be stored for up to 5 years for future HIV/TB - related research?");
                                                /**
                                                 * VIBRATE DEVICE
                                                 */
                                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                vibs.vibrate(100);

                                            } else {


                                                if (EdtDate == null) {
                                                    lib.showError(HIVAdultsConsent18Plus.this, "DATE: Error: ", "Please record date");
                                                    /**
                                                     * VIBRATE DEVICE
                                                     */
                                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                    vibs.vibrate(100);

                                                } else {
                                                    int selectedId6 = rg6.getCheckedRadioButtonId();
                                                    selected6 = (RadioButton) findViewById(selectedId6);

                                                    if (selected6 == null && rbtn1.isChecked()) {
                                                        lib.showError(HIVAdultsConsent18Plus.this, "Blood collection: Error: 2a", "Please record blood status");
                                                        /**
                                                         * VIBRATE DEVICE
                                                         */
                                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                        vibs.vibrate(100);

                                                    } else {

                                                        if (rbtn2.isChecked()) {
                                                            individual.setIndvBloodDraw(selected1.getText().toString().substring(0, 1));
                                                            individual.setB8_O15_Rapid(selected2.getText().toString().substring(0, 1));
                                                            if (rbtn3.isChecked()) {
                                                                individual.setIndRapidResults(selected3.getText().toString().substring(0, 1));
                                                            }
                                                            individual.setIndRapidDate(EdtDate.getText().toString());

                                                            myDB = new DatabaseHelper(HIVAdultsConsent18Plus.this);
                                                            myDB.onOpen(myDB.getReadableDatabase());

                                                            myDB.onOpen(myDB.getReadableDatabase());
                                                            myDB.getWritableDatabase();
                                                            myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                                            myDB.close();


                                                            Intent intent = new Intent(HIVAdultsConsent18Plus.this, Dashboard.class);
                                                            intent.putExtra("Individual", individual);
                                                            startActivity(intent);

                                                        } else {
                                                            individual.setIndConsentQuestionnaire(selected1.getText().toString().substring(0, 1));

                                                            individual.setIndvBloodDraw(selected1.getText().toString().substring(0, 1));
                                                            if (vol1.isChecked()) {
                                                                individual.setBloodVol_1("1");
                                                            } else {
                                                                individual.setBloodVol_1("2");
                                                            }
                                                            if (vol2.isChecked()) {
                                                                individual.setBloodVol_4("1");
                                                            } else {
                                                                individual.setBloodVol_4("2");
                                                            }
                                                            if (vol3.isChecked()) {
                                                                individual.setBloodVol_6("1");
                                                            } else {
                                                                individual.setBloodVol_6("2");
                                                            }
                                                            if (vol4.isChecked()) {
                                                                individual.setBloodVol_10("1");
                                                            } else {
                                                                individual.setBloodVol_10("2");
                                                            }
                                                            individual.setBloodVolComment(Edttubevolume.getText().toString());

                                                            individual.setB8_O15_Rapid(selected2.getText().toString().substring(0, 1));
                                                            if (rbtn3.isChecked()) {
                                                                individual.setIndRapidResults(selected3.getText().toString().substring(0, 1));

                                                            }
                                                            individual.setIndBloodLabTest(selected4.getText().toString().substring(0, 1));
                                                            individual.setIndBloodStore(selected5.getText().toString().substring(0, 1));

                                                            individual.setIndRapidDate(EdtDate.getText().toString());
                                                            individual.setIndBloodSampleCollected(selected6.getText().toString().substring(0, 1));

                                                            //Check if individual already been saved and update


                                                            //Next question P17
                                                            myDB.onOpen(myDB.getReadableDatabase());
                                                            myDB.getWritableDatabase();
                                                            myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                                            myDB.close();


                                                            Intent intent = new Intent(HIVAdultsConsent18Plus.this, Dashboard.class);
                                                            intent.putExtra("Individual", individual);
                                                            startActivity(intent);


                                                        }
                                                    }
                                                }
                                            }
                                        }

                                    }

                                }

                                }

                            }
                        }
                    }
                });

                btnPrev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });

            }

        }




