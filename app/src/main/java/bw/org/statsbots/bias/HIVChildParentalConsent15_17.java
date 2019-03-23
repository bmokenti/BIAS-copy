package bw.org.statsbots.bias;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

public class HIVChildParentalConsent15_17 extends AppCompatActivity implements Serializable  {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected Individual individual;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn8, rbtn7,rbtn9, rbtn10,rbtn11,rbtn12, rbtn13,  selected1, selected2, selected3,  selected4, selected5 , selected6 ;
    protected RadioGroup rg1, rg2, rg3, rg4, rg5, rg6;
    protected EditText EdtparentID, EdtDate;
    protected CheckBox vol1, vol2, vol3, vol4;
    protected Button btnNext, btnDate, btnPrev;
    protected TextView t1, t2, t3, t4, t5, t6, t7, t8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiv_15_17__parental__consent);

        setTitle("HIV Parental Consent 15-17 years");


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
        //rbtn5 = (RadioButton) findViewById(R.id.rbtn5);
        // rbtn6 = (RadioButton) findViewById(R.id.rbtn6);
        rbtn7 = (RadioButton) findViewById(R.id.rbtn7);
        rbtn8 = (RadioButton) findViewById(R.id.rbtn8);
        rbtn9 = (RadioButton) findViewById(R.id.rbtn9);
        rbtn10 = (RadioButton) findViewById(R.id.rbtn10);
        //rbtn11 = (RadioButton) findViewById(R.id.rbtn11);
        //rbtn12 = (RadioButton) findViewById(R.id.rbtn12);
        // rbtn13 = (RadioButton) findViewById(R.id.rbtn13);
        EdtparentID = (EditText) findViewById(R.id.ParentTxt);
        EdtDate = (EditText) findViewById(R.id.DateTxt);
        btnDate = (Button) findViewById(R.id.datebtn);
        btnNext = (Button) findViewById(R.id.btnnext);
        btnPrev = (Button) findViewById(R.id.btnprev);

        // vol1 = (CheckBox) findViewById(R.id.vol1);
        //  vol2 = (CheckBox) findViewById(R.id.vol2);
        //  vol3 = (CheckBox) findViewById(R.id.vol3);
        // vol4 = (CheckBox) findViewById(R.id.vol4);

        // t1 = (TextView) findViewById(R.id.volrectext);
        t2 = (TextView) findViewById(R.id.rhttxt);
        // t3 = (TextView) findViewById(R.id.rhtresulttext);
        t4 = (TextView) findViewById(R.id.txtslab);
        // t6 = (TextView) findViewById(R.id.bloodColectionStatus);
        //t5 = (TextView) findViewById(R.id.txtstore);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        //int p = 0;

        Intent ii = getIntent();
        p1 = (PersonRoster) ii.getSerializableExtra("Personroster");


        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());

        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;
        thisHouse = myDB.getHouseForUpdate(p1.getAssignmentID(),p1.getBatch()).get(0);


        final List<PersonRoster> roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }


        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg1.getChildCount();f++)
        {
            View o = rg1.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getPrntlConsentBloodDraw()!= null &&  !ind.getPrntlConsentBloodDraw().equals(""))
                {
                    if(Integer.parseInt(ind.getPrntlConsentBloodDraw())==f+1)
                    {
                        RadioButton radioButton = bt[f];
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
                if(ind.getPrntlConsentRHT()!= null &&  !ind.getPrntlConsentRHT().equals(""))
                {
                    if(Integer.parseInt(ind.getPrntlConsentRHT())==f+1)
                    {
                        RadioButton radioButton = bt2[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }




        RadioButton[] bt3 = new RadioButton[2];
        for(int f=0;f<rg4.getChildCount();f++)
        {
            View o = rg4.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt3[f]=((RadioButton)o);
                if(ind.getPrntlConsentLabTest()!= null &&  !ind.getPrntlConsentLabTest().equals(""))
                {
                    if(Integer.parseInt(ind.getPrntlConsentLabTest())==f+1)
                    {
                        RadioButton radioButton = bt3[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bt4 = new RadioButton[2];
        for(int f=0;f<rg5.getChildCount();f++)
        {
            View o = rg5.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt4[f]=((RadioButton)o);
                if(ind.getPrntlConsentBloodStore()!= null &&  !ind.getPrntlConsentBloodStore().equals(""))
                {
                    if(Integer.parseInt(ind.getPrntlConsentBloodStore())==f+1)
                    {
                        RadioButton radioButton = bt4[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        if( ind.getPrntlParentID() != null)
        {
            EdtDate.setText(ind.getPrntlParentID());
        }


        if( ind.getPrntlConsentDate() != null)
        {
            EdtDate.setText(ind.getPrntlConsentDate());
        }

//        RadioButton[] bt5 = new RadioButton[7];
//        for(int f=0;f<rg5.getChildCount();f++)
//        {
//            View o = rg5.getChildAt(f);
//            if (o instanceof RadioButton)
//            {
//                bt5[f]=((RadioButton)o);
//                if(ind.getPrntlConsentLabTest()!= null &&  !ind.getPrntlConsentLabTest().equals(""))
//                {
//                    if(Integer.parseInt(ind.getPrntlConsentLabTest())==f+1)
//                    {
//                        RadioButton radioButton = bt5[f];
//                        radioButton.setChecked(true);
//                        break;
//                    }
//                }
//            }
//        }


//        //individual.setPrntlConsentLabTest(selected3.getText().toString().substring(0, 1));
//        individual.setPrntlConsentBloodStore(selected4.getText().toString().substring(0, 1));
//        individual.setPrntlParentID(selected5.getText().toString().substring(0, 1));
//        individual.setPrntlConsentDate(EdtDate.getText().toString());
//        // (EdtDate.getText().toString());
//
//
//        //Next question P17
//        myDB = new DatabaseHelper(HIVChildParentalConsent15_17.this);
//        myDB.onOpen(myDB.getReadableDatabase());
//
//        if (myDB.checkIndividual(individual)) {
//            //Update
//            myDB.updateIndividual(myDB.getWritableDatabase(), individual);
//
//        } else {
//            //Insert
//            myDB.insertIndividual(individual);
//
//        }
//



        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();
        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();


if( individual.getIndvQuestionnaireConsent().equals("2") &&
        Integer.valueOf(p1.getP04YY()) >= 18 )
{
    Intent intent = new Intent(HIVChildParentalConsent15_17.this, HIVAdultsConsent18Plus.class);
    intent.putExtra("Individual", individual);
    intent.putExtra("Personroster", p1);
    startActivity(intent);
}



        if( sample.getStatusCode().equals("3") || ( sample.getStatusCode().equals("2") &&  thisHous.get(0).getHIVTB40().equals("0")))

        {
            Intent intent = new Intent(HIVChildParentalConsent15_17.this, Dashboard.class);
            intent.putExtra("Individual", individual);
            intent.putExtra("Personroster", p1);
            startActivity(intent);
        }


        if(   sample.getStatusCode().equals("2") &&  thisHous.get(0).getHIVTB40().equals("1") &&
                p1.getP06().equals("2") && Integer.valueOf(p1.getP04YY()) < 65)

        {
            Intent intent = new Intent(HIVChildParentalConsent15_17.this, Dashboard.class);
            intent.putExtra("Individual", individual);
            intent.putExtra("Personroster", p1);
            startActivity(intent);
        }

        if(( sample.getStatusCode().equals("2") &&  thisHous.get(0).getHIVTB40().equals("1")) && p1.getP06().equals("3")
                && Integer.valueOf(p1.getP07()) <= 13 && (Integer.valueOf(p1.getP04YY()) >=18 && Integer.valueOf(p1.getP04YY()) < 65))

        {
            Intent intent = new Intent(HIVChildParentalConsent15_17.this, HIVAdultsConsent18Plus.class);
            intent.putExtra("Individual", individual);
            intent.putExtra("Personroster", p1);
            startActivity(intent);
        }


        if(( sample.getStatusCode().equals("2") &&  thisHous.get(0).getHIVTB40().equals("1")) && p1.getP06().equals("3")
                && Integer.valueOf(p1.getP07()) <= 13 && Integer.valueOf(p1.getP04YY()) >= 65)

        {
            Intent intent = new Intent(HIVChildParentalConsent15_17.this, HIVConsentOver64.class);
            intent.putExtra("Individual", individual);
            intent.putExtra("Personroster", p1);
            startActivity(intent);
        }




        if ((individual.getQ102() != null && Integer.valueOf(individual.getQ102()) >= 18) && (sample.getStatusCode().equals("1") ||
                (sample.getStatusCode().equals("2") &&thisHous.get(0).getHIVTB40().equals("1")) ) )
        {
            Intent intent = new Intent(HIVChildParentalConsent15_17.this, HIVAdultsConsent18Plus.class);
            intent.putExtra("Individual", individual);
            intent.putExtra("Personroster", p1);
            startActivity(intent);
        }

/*
        if( Integer.valueOf(p1.getP04YY()) >= 18)
        {
            Intent intent = new Intent(HIVChildParentalConsent15_17.this, HIVAdultsConsent18Plus.class);
            intent.putExtra("Individual", individual);
            intent.putExtra("Personroster", p1);
            startActivity(intent);
        }
*/



/*
        if (   Integer.valueOf(individual.getQ102()) >= 18)
        {
            Intent intent = new Intent(HIVChildParentalConsent15_17.this, HIVAdultsConsent18Plus.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

*/
    if (individual.getQ102() != null && Integer.valueOf(individual.getQ102()) >= 16 )
        {
    rbtn3.setEnabled(false);
    rbtn4.setEnabled(false);
    t2.setTextColor(Color.LTGRAY);
    }




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
                  //  t5.setTextColor(Color.BLACK);





                } else {




                    rbtn7.setEnabled(false);
                    rbtn8.setEnabled(false);
                    rbtn9.setEnabled(false);
                    rbtn10.setEnabled(false);
                    t4.setTextColor(Color.LTGRAY);
                   // t5.setTextColor(Color.LTGRAY);

                    rbtn7.setChecked(false);
                    rbtn8.setChecked(false);
                    rbtn9.setChecked(false);
                    rbtn10.setChecked(false);




                }
            }
        });





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
                    lib.showError(HIVChildParentalConsent15_17.this, "Draw Blood: Error", "Do you agree for the survey team to draw your blood for HIV testing and other related testing?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                }  else {
                        int selectedId2 = rg2.getCheckedRadioButtonId();
                        selected2 = (RadioButton) findViewById(selectedId2);

                        if (selected2 == null) {
                            lib.showError(HIVChildParentalConsent15_17.this, "RHT: Error: 2", "Do you agree for the survey team to do RHT?");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);

                        } else {
                            int selectedId4 = rg4.getCheckedRadioButtonId();
                            selected4 = (RadioButton) findViewById(selectedId4);

                            if (selected4 == null && rbtn1.isChecked()) {
                                lib.showError(HIVChildParentalConsent15_17.this, "Laboratory: Error: 3", "3. Do you agree for your childs blood sample to be sent to the laboratory for additional HIV related testing?");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);

                            } else {
                                int selectedId5 = rg5.getCheckedRadioButtonId();
                                selected5 = (RadioButton) findViewById(selectedId5);

                                if (selected5 == null && rbtn1.isChecked()) {
                                    lib.showError(HIVChildParentalConsent15_17.this, "Storage: Error: 4", "4. Do you agree for your Childs blood sample to be stored for up to 5 years for future HIV/TB - related research?");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);

                                } else {


                                    if (EdtDate == null) {
                                        lib.showError(HIVChildParentalConsent15_17.this, "DATE: Error: ", "Please record date");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);

                                    } else {
                                        if (rbtn2.isChecked()) {
                                            individual.setPrntlConsentBloodDraw(selected1.getText().toString().substring(0, 1));
                                            individual.setPrntlConsentRHT(selected2.getText().toString().substring(0, 1));
                                           // individual.setPrntlParentID(selected5.getText().toString().substring(0, 1));
                                            individual.setPrntlConsentDate(EdtDate.getText().toString());


                                            myDB = new DatabaseHelper(HIVChildParentalConsent15_17.this);
                                            myDB.onOpen(myDB.getReadableDatabase());

                                            if (myDB.checkIndividual(individual)) {
                                                //Update
                                                myDB.updateIndividual(myDB.getWritableDatabase(), individual);

                                            } else {
                                                //Insert
                                                myDB.insertIndividual(individual);

                                            }

                                            Intent intent = new Intent(HIVChildParentalConsent15_17.this, HIVAdultsConsent18Plus.class);
                                            intent.putExtra("Individual", individual);

                                            intent.putExtra("Personroster", p1);
                                            startActivity(intent);
                                        } else {

                                            individual.setPrntlConsentBloodDraw(selected1.getText().toString().substring(0, 1));
                                            individual.setPrntlConsentRHT(selected2.getText().toString().substring(0, 1));
                                            //individual.setPrntlConsentLabTest(selected3.getText().toString().substring(0, 1));
                                            individual.setPrntlConsentBloodStore(selected4.getText().toString().substring(0, 1));
                                            individual.setPrntlParentID(selected5.getText().toString().substring(0, 1));
                                            individual.setPrntlConsentDate(EdtDate.getText().toString());
                                                   // (EdtDate.getText().toString());


                                            //Next question P17
                                            myDB = new DatabaseHelper(HIVChildParentalConsent15_17.this);
                                            myDB.onOpen(myDB.getReadableDatabase());

                                            if (myDB.checkIndividual(individual)) {
                                                //Update
                                                myDB.updateIndividual(myDB.getWritableDatabase(), individual);

                                            } else {
                                                //Insert
                                                myDB.insertIndividual(individual);

                                            }

                                            Intent intent = new Intent(HIVChildParentalConsent15_17.this, HIVAdultsConsent18Plus.class);
                                            intent.putExtra("Individual", individual);
                                            intent.putExtra("Personroster", p1);
                                            startActivity(intent);

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


