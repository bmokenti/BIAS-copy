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

public class Tb_XRay_Consent extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected Individual individual;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn8, rbtn7,rbtn9, rbtn10,rbtn11,rbtn12, rbtn13, rbtn14,  selected1, selected2, selected3,  selected4, selected5 , selected6, selected7;
    protected RadioGroup rg1, rg2, rg3, rg4, rg5, rg6, rg7;
    protected EditText EDTParentID, EdtDate, EdtGuardian;
    protected DatabaseHelper myDB;
    protected CheckBox vol1, vol2, vol3, vol4;
    protected Button btnNext, btnDate, btnPrev;
    protected TextView t1, t2, t3, t4, t5, t6, t7, t8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb__xray__consent);


        setTitle("TB Tests Consents");

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
        rg7 = (RadioGroup) findViewById(R.id.rg7);

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
        rbtn14= (RadioButton) findViewById(R.id.rbtn14);
        EDTParentID = (EditText) findViewById(R.id.ParentTxt);
        EdtDate = (EditText) findViewById(R.id.DateTxt);
        EdtGuardian = (EditText) findViewById(R.id.ParentTxt);
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
       t6 = (TextView) findViewById(R.id.txttbaddmed);
         t7 = (TextView) findViewById(R.id.txtTBspAddTests);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbtn2) {
                    // is checked




                    rbtn3.setEnabled(false);
                    rbtn4.setEnabled(false);
                    rbtn7.setEnabled(false);
                    rbtn8.setEnabled(false);
                    t2.setTextColor(Color.LTGRAY);
                    t4.setTextColor(Color.LTGRAY);
                    //    t5.setTextColor(Color.BLACK);

                    rbtn3.setChecked(false);
                    rbtn4.setChecked(false);
                    rbtn7.setChecked(false);
                    rbtn8.setChecked(false);


                } else {


                    rbtn7.setEnabled(true);
                    rbtn8.setEnabled(true);
                    rbtn3.setEnabled(true);
                    rbtn4.setEnabled(true);
                    t2.setTextColor(Color.BLACK);
                    t4.setTextColor(Color.BLACK);
                    //t5.setTextColor(Color.LTGRAY);





                }
            }
        });

        rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbtn10) {
                    // is checked




                    rbtn11.setEnabled(false);
                    rbtn12.setEnabled(false);
                    rbtn13.setEnabled(false);
                    rbtn14.setEnabled(false);
                    t6.setTextColor(Color.LTGRAY);
                    t7.setTextColor(Color.LTGRAY);
                    //    t5.setTextColor(Color.BLACK);

                    rbtn11.setChecked(false);
                    rbtn12.setChecked(false);
                    rbtn13.setChecked(false);
                    rbtn14.setChecked(false);





                } else {



                    rbtn11.setEnabled(true);
                    rbtn12.setEnabled(true);
                    rbtn13.setEnabled(true);
                    rbtn14.setEnabled(true);
                    t6.setTextColor(Color.BLACK);
                    t7.setTextColor(Color.BLACK);
                    //t5.setTextColor(Color.LTGRAY);





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
                    lib.showError(bw.org.statsbots.bias.Tb_XRay_Consent.this, "X-Ray: Error", "1. Do you agree for the survey team to perform a chest x-ray for TB screening other related testing?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                }  else {
                    int selectedId2 = rg2.getCheckedRadioButtonId();
                    selected2 = (RadioButton) findViewById(selectedId2);

                    if (selected2 == null) {
                        lib.showError(bw.org.statsbots.bias.Tb_XRay_Consent.this, "X-Ray Add Tests: Error: 2", " 2. Do you agree for your chest x-ray to be sent to a medical panel for review for additional TB related diagnosis and testing");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    }  else {
                        int selectedId4 = rg4.getCheckedRadioButtonId();
                        selected4 = (RadioButton) findViewById(selectedId4);

                        if (selected4 == null && rbtn1.isChecked()) {
                            lib.showError(bw.org.statsbots.bias.Tb_XRay_Consent.this, "X-Ray storage: Error: 3", "3. Do you agree for your chest x-ray to be electronically stored for up to 2 years for potential reanalysis?");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);

                        } else {
                            int selectedId5 = rg5.getCheckedRadioButtonId();
                            selected5 = (RadioButton) findViewById(selectedId5);

                            if (selected5 == null && rbtn1.isChecked()) {
                                lib.showError(bw.org.statsbots.bias.Tb_XRay_Consent.this, "Sputum Collection: Error: 4", "4.Do you agree for the survey team to collect 2 sputum samples for TB screening?");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);

                            }
                            else {
                                int selectedId6 = rg6.getCheckedRadioButtonId();
                                selected6 = (RadioButton) findViewById(selectedId6);

                                if (selected6 == null && rbtn1.isChecked()) {
                                    lib.showError(bw.org.statsbots.bias.Tb_XRay_Consent.this, "Sputum Results review: Error: 5", "5. Do you agree for your sputum sample results to be sent to a medical panel for additional TB related diagnosis and testing?");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);

                                }
                                else {
                                    int selectedId7 = rg7.getCheckedRadioButtonId();
                                    selected7 = (RadioButton) findViewById(selectedId7);

                                    if (selected7 == null && rbtn1.isChecked()) {
                                        lib.showError(bw.org.statsbots.bias.Tb_XRay_Consent.this, "Sputum Add Testing: Error: 6", "6. Do you agree for your sputum sample to be sent to the laboratory for additional TB related testing?");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);

                                    } else {


                                        if (EdtDate == null) {
                                            lib.showError(bw.org.statsbots.bias.Tb_XRay_Consent.this, "DATE: Error: ", "Please record date");
                                            /**
                                             * VIBRATE DEVICE
                                             */
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);

                                        } else {

                                            if (rbtn2.isChecked()) {

                                                p1.setIndTB_X_Ray(selected1.getText().toString().substring(0, 1));
                                                myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                myDB.onOpen(myDB.getReadableDatabase());
                                                myDB.updateConsents("IndTB_X_Ray", p1.getAssignmentID(), p1.getBatch(), p1.getIndTB_X_Ray(), String.valueOf(p1.getSRNO()));
                                                myDB.close();


                                                if (rbtn10.isChecked())
                                                {
                                                    p1.setIndSP_Collect(selected5.getText().toString().substring(0, 1));
                                                    myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                    myDB.onOpen(myDB.getReadableDatabase());
                                                    myDB.updateConsents("IndSP_Collect", p1.getAssignmentID(), p1.getBatch(), p1.getIndSP_Collect(), String.valueOf(p1.getSRNO()));
                                                    myDB.close();
                                                }
                                                else
                                                {
                                                    p1.setIndSP_Collect(selected5.getText().toString().substring(0, 1));
                                                    myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                    myDB.onOpen(myDB.getReadableDatabase());
                                                    myDB.updateConsents("IndSP_Collect", p1.getAssignmentID(), p1.getBatch(), p1.getIndSP_Collect(), String.valueOf(p1.getSRNO()));
                                                    myDB.close();

                                                    p1.setIndSP_AddTests(selected6.getText().toString().substring(0, 1));
                                                    myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                    myDB.onOpen(myDB.getReadableDatabase());
                                                    myDB.updateConsents("IndSP_AddTests", p1.getAssignmentID(), p1.getBatch(), p1.getIndSP_AddTests(), String.valueOf(p1.getSRNO()));
                                                    myDB.close();

                                                    p1.setIndSP_LabTests(selected7.getText().toString().substring(0, 1));
                                                    myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                    myDB.onOpen(myDB.getReadableDatabase());
                                                    myDB.updateConsents("IndSP_LabTests", p1.getAssignmentID(), p1.getBatch(), p1.getIndSP_LabTests(), String.valueOf(p1.getSRNO()));
                                                    myDB.close();
                                                }


                                                p1.setIndTB_ConsentDate(EdtDate.getText().toString());
                                                myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                myDB.onOpen(myDB.getReadableDatabase());
                                                //  myDB.updateRoster(thisHouse,"tRapidDate",p1.getRapidDate(), String.valueOf(p1.getSRNO()));
                                                myDB.updateConsents("IndTB_ConsentDate", p1.getAssignmentID(), p1.getBatch(), p1.getIndTB_ConsentDate(), String.valueOf(p1.getSRNO()));
                                                myDB.close();


                                                Intent intent = new Intent(Tb_XRay_Consent.this, Dashboard.class);
                                                intent.putExtra("Individual", individual);
                                                startActivity(intent);

                                            } else {

                                                p1.setIndTB_X_Ray(selected1.getText().toString().substring(0, 1));
                                                myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                myDB.onOpen(myDB.getReadableDatabase());
                                                myDB.updateConsents("IndTB_X_Ray", p1.getAssignmentID(), p1.getBatch(), p1.getIndTB_X_Ray(), String.valueOf(p1.getSRNO()));
                                                myDB.close();

                                                p1.setIndTB_X_RayReview(selected2.getText().toString().substring(0, 1));
                                                myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                myDB.onOpen(myDB.getReadableDatabase());
                                                myDB.updateConsents("IndTB_X_RayReview", p1.getAssignmentID(), p1.getBatch(), p1.getIndTB_X_RayReview(), String.valueOf(p1.getSRNO()));
                                                myDB.close();

                                                p1.setIndTB_X_RayStore(selected4.getText().toString().substring(0, 1));
                                                myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                myDB.onOpen(myDB.getReadableDatabase());
                                                myDB.updateConsents("IndTB_X_RayStore", p1.getAssignmentID(), p1.getBatch(), p1.getIndTB_X_RayStore(), String.valueOf(p1.getSRNO()));
                                                myDB.close();



                                                if (rbtn10.isChecked())
                                                {
                                                    p1.setIndSP_Collect(selected5.getText().toString().substring(0, 1));
                                                    myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                    myDB.onOpen(myDB.getReadableDatabase());
                                                    myDB.updateConsents("IndSP_Collect", p1.getAssignmentID(), p1.getBatch(), p1.getIndSP_Collect(), String.valueOf(p1.getSRNO()));
                                                    myDB.close();
                                                }
                                                else
                                                {
                                                    p1.setIndSP_Collect(selected5.getText().toString().substring(0, 1));
                                                    myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                    myDB.onOpen(myDB.getReadableDatabase());
                                                    myDB.updateConsents("IndSP_Collect", p1.getAssignmentID(), p1.getBatch(), p1.getIndSP_Collect(), String.valueOf(p1.getSRNO()));
                                                    myDB.close();

                                                    p1.setIndSP_AddTests(selected6.getText().toString().substring(0, 1));
                                                    myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                    myDB.onOpen(myDB.getReadableDatabase());
                                                    myDB.updateConsents("IndSP_AddTests", p1.getAssignmentID(), p1.getBatch(), p1.getIndSP_AddTests(), String.valueOf(p1.getSRNO()));
                                                    myDB.close();

                                                    p1.setIndSP_LabTests(selected7.getText().toString().substring(0, 1));
                                                    myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                    myDB.onOpen(myDB.getReadableDatabase());
                                                    myDB.updateConsents("IndSP_LabTests", p1.getAssignmentID(), p1.getBatch(), p1.getIndSP_LabTests(), String.valueOf(p1.getSRNO()));
                                                    myDB.close();
                                                }



                                                //************************set guardian id or name***************************************/


                                                p1.setIndTB_ConsentDate(EdtDate.getText().toString());
                                                myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                myDB.onOpen(myDB.getReadableDatabase());
                                                myDB.updateConsents("IndTB_ConsentDate", p1.getAssignmentID(), p1.getBatch(), p1.getIndTB_ConsentDate(), String.valueOf(p1.getSRNO()));
                                                myDB.close();


                                                //Check if individual already been saved and update


                                                //Next question P17
                                                // myDB = new DatabaseHelper(Tb_XRay_Consent.this);
                                                // myDB.onOpen(myDB.getReadableDatabase());


                                                //Update


                                                Intent intent = new Intent(Tb_XRay_Consent.this, TB_Parental_Consents.class);
                                                intent.putExtra("Personroster", p1);
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
        });


    }
}

