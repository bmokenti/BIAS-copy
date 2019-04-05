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

public class HIVParentalConsent6wks_9y extends  AppCompatActivity implements Serializable{
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected Individual individual;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected started_household str;
    protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2, rbtnIndeterminate, rbtn3, rbtn4, rbtn5, rbtn6, rbtn8, rbtn7,rbtn9, rbtn10,rbtn11,rbtn12, rbtn13,  selected1, selected2, selected3,  selected4, selected5 , selected6 ;
    protected RadioGroup rg1, rg2, rg3, rg4, rg5, rg6;
    protected EditText Edttubevolume, EdtDate, EdtGuardian;
    protected CheckBox vol1, vol2, vol3, vol4;
    protected Button btnNext, btnDate, btnPrev, btnloadinfo;
    protected TextView t1, t2, t3, t4, t5, t6, t7, t8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hivparental_consent6wks_9y);

        lib = new LibraryClass();
        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");


        Intent h = getIntent();
        p1 = (PersonRoster) h.getSerializableExtra("Personroster");


        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());


        final List<HouseHold> thisHous = myDB.getHouseForUpdate(p1.getAssignmentID(),p1.getBatch());
        thisHous.get(0).getHIVTB40();



       final Sample sample = myDB.getSample(myDB.getReadableDatabase(), thisHous.get(0).getAssignment_ID());
        sample.getStatusCode();

//        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), ind.getAssignmentID());
//        sample.getSTATUS();

        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;


        final List<PersonRoster> roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }

        thisHouse = myDB.getHouseForUpdate(p1.getAssignmentID(),p1.getBatch()).get(0);


        // p1.getChPrntlConsentBloodDraw();
     //  rbtn1.setText(p1.getChPrntlConsentBloodDraw());
int yy =Integer.valueOf(p1.getP04YY());
int mm = Integer.valueOf(p1.getP04MM());
int wks = Integer.valueOf(p1.getP04WKS());

        if((yy == 1 && mm <= 6 ) || (yy == 00 && mm <= 11) || (mm == 1 && wks >= 2))
        {
            setTitle("Parental Consent less than 18 months");
        }
        else {
            setTitle("Parental Consent 19 months to 9 years");
        }


        if( sample.getStatusCode().equals("1") && Integer.valueOf(p1.getP04YY()) >=65 )


        {
            Intent intent = new Intent(HIVParentalConsent6wks_9y.this, HIVConsentOver64.class);
            intent.putExtra("Individual", individual);
            intent.putExtra("Personroster", p1);
            startActivity(intent);

        }


        if(Integer.valueOf(p1.getP04YY()) >=10 && Integer.valueOf(p1.getP04YY()) <=14)
        {
            Intent q1o2 = new Intent(HIVParentalConsent6wks_9y.this, HIVParentalConsent10_14yrs.class);
            q1o2.putExtra("Individual", individual);
            q1o2.putExtra("Personroster", p1);
            startActivity(q1o2);
        }
        else {
            setTitle("Parental Consent 18 months to 9 years");
        }

        if(Integer.valueOf(p1.getP04YY()) >=18  )
        {
            Intent q1o2 = new Intent(HIVParentalConsent6wks_9y.this, IndividualQuestionaireConsent.class);
            q1o2.putExtra("Individual", individual);
            q1o2.putExtra("Personroster", p1);
            startActivity(q1o2);
        }





            if(Integer.valueOf(p1.getP04YY()) >= 15  && Integer.valueOf(p1.getP04YY()) <= 64)
        {
            Intent q1o2 = new Intent(HIVParentalConsent6wks_9y.this, IndQuetParentalConsent.class);
            q1o2.putExtra("Individual", individual);
            q1o2.putExtra("Personroster", p1);
            startActivity(q1o2);
        }

        if((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")  && Integer.valueOf(p1.getP04YY()) >= 65) &&
                (p1.getP06().equals("3")))
        {
            if(p1.getP07()!=null){
                if(Integer.valueOf(p1.getP07()) < 14)
                {

                    Intent q1o2 = new Intent(HIVParentalConsent6wks_9y.this, HIVConsentOver64.class);
                    q1o2.putExtra("Individual", individual);
                    q1o2.putExtra("Personroster", p1);
                    startActivity(q1o2);
                }


            }


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
        rbtnIndeterminate = (RadioButton) findViewById(R.id.rbtnIndeterminate);
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
        EdtGuardian = (EditText) findViewById(R.id.ParentID);
        btnDate = (Button) findViewById(R.id.datebtn);
        btnNext = (Button) findViewById(R.id.btnnext);
        btnPrev = (Button) findViewById(R.id.btnprev);
        btnloadinfo = (Button) findViewById(R.id.LoadConsetInfo);

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

        if(rbtn1.isChecked() && ((yy == 1 && mm <= 6 ) || (yy == 00 && mm <= 11) || (mm == 1 && wks >= 2)))
        {
            rbtn3.setEnabled(false);
            rbtn4.setEnabled(false);
            rbtn5.setEnabled(false);
            rbtn6.setEnabled(false);
            rbtnIndeterminate.setEnabled(false);
            t2.setTextColor(Color.LTGRAY);
            t3.setTextColor(Color.LTGRAY);
        }
        else {
           // rbtn3.setEnabled(true);
            //rbtn4.setEnabled(true);
            //rbtn5.setEnabled(true);
            //rbtn6.setEnabled(true);

        }
        if (rbtn1.isChecked() && ((yy == 1 && mm <= 6 ) || (yy == 00 && mm <= 11) || (mm == 1 && wks >= 2)))
        {
            vol1.setEnabled(true);
            vol3.setEnabled(false);
            vol2.setEnabled(false);
            vol4.setEnabled(false);

        }

        if (rbtn1.isChecked() && ((yy == 1 && mm >= 7 ) || (yy <= 2) ))
        {
            vol1.setEnabled(true);
            vol3.setEnabled(false);
            vol2.setEnabled(false);
            vol4.setEnabled(false);

        }

        if(rbtn1.isChecked() && (yy >=3 && yy <= 14))
        { vol1.setEnabled(true);
            vol2.setEnabled(false);
            vol4.setEnabled(false);
            vol3.setEnabled(true);

        }
//
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int yy = Integer.valueOf(p1.getP04YY());
                int mm = Integer.valueOf(p1.getP04MM());
                int wks = Integer.valueOf(p1.getP04WKS());
                if (i == R.id.rbtn1 && (yy >= 3 && yy <= 14)) {
                    // is checked

                    vol1.setEnabled(true);
                    vol2.setEnabled(false);
                    vol3.setEnabled(true);
                    vol4.setEnabled(false);
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


                } else {
                    if (i == R.id.rbtn1 && ((yy == 1 && mm <= 6) || (yy == 00 && mm <= 11) || (mm == 1 && wks >= 2))) {
                        rbtn3.setEnabled(false);
                        rbtn4.setEnabled(false);
                        rbtn5.setEnabled(false);
                        rbtn6.setEnabled(false);
                        rbtnIndeterminate.setEnabled(false);
                        t2.setTextColor(Color.LTGRAY);
                        t3.setTextColor(Color.LTGRAY);

//                        vol1.setEnabled(true);
//                        vol3.setEnabled(false);
//                        vol2.setEnabled(false);
//                        vol4.setEnabled(false);

                        Edttubevolume.setEnabled(true);
                        vol1.setEnabled(true);
                        vol3.setEnabled(false);
                        vol2.setEnabled(false);
                        vol4.setEnabled(false);
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


                    }  else {

                            if (i == R.id.rbtn1 && (i == R.id.rbtn1 && ((yy == 1 && mm >= 7) || (yy <= 2)))) {
                                vol3.setEnabled(false);
                                vol2.setEnabled(false);
                                vol4.setEnabled(false);
                                vol1.setEnabled(true);

                                Edttubevolume.setEnabled(true);

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
                            else {

                                if ((i == R.id.rbtn2)) {
                                    vol1.setEnabled(false);
                                    vol3.setEnabled(false);
                                    vol2.setEnabled(false);
                                    vol4.setEnabled(false);
                                    Edttubevolume.setEnabled(false);
                                    rbtn7.setEnabled(false);
                                    rbtn8.setEnabled(false);
                                    rbtn9.setEnabled(false);
                                    rbtn10.setEnabled(false);

                                    vol1.setChecked(false);
                                    vol3.setChecked(false);
                                    vol2.setChecked(false);
                                    vol4.setChecked(false);
                                    Edttubevolume.setText("");
                                    rbtn7.setChecked(false);
                                    rbtn8.setChecked(false);
                                    rbtn9.setChecked(false);
                                    rbtn10.setChecked(false);
                                    rbtn11.setChecked(false);
                                    rbtn12.setChecked(false);
                                    rbtn13.setChecked(false);

                                    t4.setTextColor(Color.LTGRAY);
                                    t5.setTextColor(Color.LTGRAY);

                                    rbtn11.setEnabled(false);
                                    rbtn12.setEnabled(false);
                                    rbtn13.setEnabled(false);
                                    t6.setTextColor(Color.LTGRAY);

                                }
                            }
                            }

                    }
                }


    });






        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rbtn3)
                {
                    // is checked
                    rbtn5.setEnabled(true);
                    rbtn6.setEnabled(true);
                    rbtnIndeterminate.setEnabled(true);
                    t3.setTextColor(Color.BLACK);
                }
                else
                {


                    rbtn5.setEnabled(false);
                    rbtn6.setEnabled(false);
                    rbtnIndeterminate.setEnabled(false);

                    t3.setTextColor(Color.LTGRAY);

                    rbtn5.setChecked(false);
                    rbtn6.setChecked(false);
                    rbtnIndeterminate.setEnabled(false);




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
                if(p1.getChPrntlConsentBloodDraw()!= null &&  !p1.getChPrntlConsentBloodDraw().equals(""))
                {
                    if(Integer.parseInt(p1.getChPrntlConsentBloodDraw())==f+1)
                    {
                        RadioButton radioButton = bt1[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        if(p1.getBloodVolume_1()!= null &&  !p1.getBloodVolume_1().equals(""))
        {
            if(Integer.parseInt(p1.getBloodVolume_1())== 1)
            {
                vol1.setChecked(true);

            }else
            {
                vol1.setChecked(false);
            }
        }

        if(p1.getBloodVolume_4()!= null &&  !p1.getBloodVolume_4().equals(""))
        {
            if(Integer.parseInt(p1.getBloodVolume_4())== 1)
            {
                vol2.setChecked(true);

            }else
            {
                vol2.setChecked(false);
            }
        }

        if(p1.getBloodVolume_6()!= null &&  !p1.getBloodVolume_6().equals(""))
        {
            if(Integer.parseInt(p1.getBloodVolume_6())== 1)
            {
                vol3.setChecked(true);

            }else
            {
                vol3.setChecked(false);
            }
        }

        if(p1.getBloodVolume_10()!= null &&  !p1.getBloodVolume_10().equals(""))
        {
            if(Integer.parseInt(p1.getBloodVolume_10())== 1)
            {
                vol4.setChecked(true);

            }else
            {
                vol4.setChecked(false);
            }
        }


        RadioButton[] bt2 = new RadioButton[2];
        for(int f=0;f<rg2.getChildCount();f++)
        {
            View o = rg2.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt2[f]=((RadioButton)o);
                if(p1.getChPrntlConsentRHT()!= null &&  !p1.getChPrntlConsentRHT().equals(""))
                {
                    if(Integer.parseInt(p1.getChPrntlConsentRHT())==f+1)
                    {
                        RadioButton radioButton = bt2[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bt3 = new RadioButton[3];
        for(int f=0;f<rg3.getChildCount();f++)
        {
            View o = rg3.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt3[f]=((RadioButton)o);
                if(p1.getRapidResults()!= null &&  !p1.getRapidResults().equals(""))
                {
                    if(Integer.parseInt(p1.getRapidResults())==f+1)
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
                if(p1.getChPrntlConsentLabTest()!= null &&  !p1.getChPrntlConsentLabTest().equals(""))
                {
                    if(Integer.parseInt(p1.getChPrntlConsentLabTest())==f+1)
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
                if(p1.getChPrntlConsentBloodStore()!= null &&  !p1.getChPrntlConsentBloodStore().equals(""))
                {
                    if(Integer.parseInt(p1.getChPrntlConsentBloodStore())==f+1)
                    {
                        RadioButton radioButton = bt5[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        if( p1.getB3_Guardian() != null)
        {
            EdtGuardian.setText(p1.getB3_Guardian());
        }

        if( p1.getRapidDate() != null)
        {
            EdtDate.setText(p1.getRapidDate());
        }



        RadioButton[] bt6 = new RadioButton[3];
        for(int f=0;f<rg6.getChildCount();f++)
        {
            View o = rg6.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt6[f]=((RadioButton)o);
                if(p1.getBloodSampleCollected()!= null &&  !p1.getBloodSampleCollected().equals(""))
                {
                    if(Integer.parseInt(p1.getBloodSampleCollected())==f+1)
                    {
                        RadioButton radioButton = bt6[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
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
                Date d = new Date();
                CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss",today.getTime());
                EdtDate.setText(s.toString());

            }
        });

        btnloadinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create Date Object
                Intent intent = new Intent(HIVParentalConsent6wks_9y.this, Consents_Info_6wks_9years.class);
                intent.putExtra("Individual", individual);
                intent.putExtra("Personroster", p1);
                startActivity(intent);

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId1 = rg1.getCheckedRadioButtonId();
                selected1 = (RadioButton) findViewById(selectedId1);

                if (selected1 == null) {
                    lib.showError(HIVParentalConsent6wks_9y.this, "Draw Blood: Error", "Do you agree for the survey team to draw your blood for HIV testing and other related testing?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                } else {

                    if (!(vol1.isChecked() || vol2.isChecked() || vol3.isChecked() || vol4.isChecked()) && rbtn1.isChecked()) {
                        lib.showError(HIVParentalConsent6wks_9y.this, "IndividualConsent: Error: 1a", "Please record container used");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    } else {

                        if ((vol1.isChecked() && vol3.isChecked()) )
                        {
                            lib.showError(HIVParentalConsent6wks_9y.this, "IndividualConsent: Error: 1a", "6ml container and 1ml container can not be used" +
                                    " together. The participant need 6ml or just 1ml for rht. Please select accordingly");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);

                        } else {
                        int selectedId2 = rg2.getCheckedRadioButtonId();
                        selected2 = (RadioButton) findViewById(selectedId2);

                        if (selected2 == null && (Integer.valueOf(p1.getP04YY()) >1 && Integer.valueOf(p1.getP04MM()) >6))
                        {
                            lib.showError(HIVParentalConsent6wks_9y.this, "RHT: Error: 2", "Do you agree for the survey team to do RHT?");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);

                        } else {
                            int selectedId3 = rg3.getCheckedRadioButtonId();
                            selected3 = (RadioButton) findViewById(selectedId3);

                            if (selected3 == null && rbtn3.isChecked() && !(Integer.valueOf(p1.getP04YY()) < 2 && Integer.valueOf(p1.getP04MM()) <=6)) {
                                lib.showError(HIVParentalConsent6wks_9y.this, "RHT Results: Error: 2a", "Please record RHT results?");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);

                            } else {
                                int selectedId4 = rg4.getCheckedRadioButtonId();
                                selected4 = (RadioButton) findViewById(selectedId4);

                                if (selected4 == null && rbtn1.isChecked()) {
                                    lib.showError(HIVParentalConsent6wks_9y.this, "Laboratory: Error: 3", "3. Do you agree for your child's blood sample to be sent to the laboratory for additional HIV related testing?");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);

                                } else {
                                    int selectedId5 = rg5.getCheckedRadioButtonId();
                                    selected5 = (RadioButton) findViewById(selectedId5);

                                    if (selected5 == null && rbtn1.isChecked()) {
                                        lib.showError(HIVParentalConsent6wks_9y.this, "Storage: Error: 4", "4. Do you agree for your child's blood sample to be stored for up to 5 years for future HIV/TB - related research?");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);

                                    } else {


                                        if (EdtGuardian == null || EdtGuardian.length() == 0) {
                                            lib.showError(HIVParentalConsent6wks_9y.this, "Guardian: Error: ", "Please please select Guardian ID");
                                            /**
                                             * VIBRATE DEVICE
                                             */
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);

                                        } else {


                                            if (EdtDate == null) {
                                                lib.showError(HIVParentalConsent6wks_9y.this, "DATE: Error: ", "Please record date");
                                                /**
                                                 * VIBRATE DEVICE
                                                 */
                                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                vibs.vibrate(100);

                                            } else {
                                                int selectedId6 = rg6.getCheckedRadioButtonId();
                                                selected6 = (RadioButton) findViewById(selectedId6);

                                                if (selected6 == null && rbtn1.isChecked()) {
                                                    lib.showError(HIVParentalConsent6wks_9y.this, "Blood collection: Error: 2a", "Please record blood status");
                                                    /**
                                                     * VIBRATE DEVICE
                                                     */
                                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                    vibs.vibrate(100);

                                                } else {


                                                    if (rbtn2.isChecked()) {

                                                        p1.setChPrntlConsentBloodDraw(selected1.getText().toString().substring(0, 1));

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("ChPrntlConsentBloodDraw", p1.getAssignmentID(), p1.getBatch(), p1.getChPrntlConsentBloodDraw(), String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("BloodVolume_1", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("BloodVolume_4", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("BloodVolume_6", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("BloodVolume_10", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("BloodVolumeComment", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("ChPrntlConsentLabTest", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("ChPrntlConsentBloodStore", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("BloodSampleCollected", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));

                                                        if (rbtn3.isChecked() || rbtn4.isChecked()) {
                                                            p1.setChPrntlConsentRHT(selected2.getText().toString().substring(0, 1));

                                                            myDB.onOpen(myDB.getReadableDatabase());
                                                            myDB.updateConsents("ChPrntlConsentRHT", p1.getAssignmentID(), p1.getBatch(), p1.getChPrntlConsentRHT(), String.valueOf(p1.getSRNO()));
                                                            myDB.close();
                                                        } else {
                                                            myDB.onOpen(myDB.getReadableDatabase());
                                                            myDB.updateConsents("ChPrntlConsentRHT", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                            myDB.close();
                                                        }

                                                        if (rbtn3.isChecked()) {

                                                            p1.setRapidResults(selected3.getText().toString().substring(0, 1));

                                                            myDB.onOpen(myDB.getReadableDatabase());
                                                            //myDB.updateRoster(thisHouse,"RapidResults",p1.getRapidResults(), String.valueOf(p1.getSRNO()));
                                                            myDB.updateConsents("RapidResults", p1.getAssignmentID(), p1.getBatch(), p1.getRapidResults(), String.valueOf(p1.getSRNO()));
                                                            myDB.close();
                                                        } else {
                                                            myDB.onOpen(myDB.getReadableDatabase());
                                                            //myDB.updateRoster(thisHouse,"RapidResults",p1.getRapidResults(), String.valueOf(p1.getSRNO()));
                                                            myDB.updateConsents("RapidResults", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                            myDB.close();
                                                        }

                                                        p1.setB3_Guardian(EdtGuardian.getText().toString());

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("B3_Guardian", p1.getAssignmentID(), p1.getBatch(), p1.getB3_Guardian(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();

                                                        p1.setRapidDate(EdtDate.getText().toString());

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("RapidDate", p1.getAssignmentID(), p1.getBatch(), p1.getRapidDate(), String.valueOf(p1.getSRNO()));
                                                        /*******UPDATE HOUSE FOR PARTIAL SEND*****************/

                                                        //UPDATE HOUSEHOLD
                                                        myDB.updateHousehold(myDB.getReadableDatabase(), thisHouse.getAssignment_ID(), thisHouse.getBatchNumber(), "Clear", "3");
                                                        myDB.close();
                                                        /********************END PARTIAL****************/


                                                        Intent intent = new Intent(HIVParentalConsent6wks_9y.this, Dashboard.class);
                                                        intent.putExtra("Individual", individual);
                                                        intent.putExtra("Personroster", p1);
                                                        startActivity(intent);

                                                    } else {
                                                        p1.setChPrntlConsentBloodDraw(selected1.getText().toString().substring(0, 1));


                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("ChPrntlConsentBloodDraw", p1.getAssignmentID(), p1.getBatch(), p1.getChPrntlConsentBloodDraw(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();


                                                        if (vol1.isChecked()) {
                                                            p1.setBloodVolume_1("1");

                                                        } else {
                                                            p1.setBloodVolume_1("2");
                                                        }

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodVolume_1", p1.getAssignmentID(), p1.getBatch(), p1.getBloodVolume_1(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();

                                                        // myDB.updateRoster(thisHouse,"BloodVolume_1",p1.getBloodVolume_1(), String.valueOf(p1.getSRNO()));
                                                        if (vol2.isChecked()) {
                                                            p1.setBloodVolume_4("1");
                                                        } else {
                                                            p1.setBloodVolume_4("2");
                                                        }


                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodVolume_4", p1.getAssignmentID(), p1.getBatch(), p1.getBloodVolume_4(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();
                                                        //myDB.updateRoster(thisHouse,"BloodVolume_4",p1.getBloodVolume_4(), String.valueOf(p1.getSRNO()));
                                                        if (vol3.isChecked()) {
                                                            p1.setBloodVolume_6("1");
                                                        } else {
                                                            p1.setBloodVolume_6("2");
                                                        }

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodVolume_6", p1.getAssignmentID(), p1.getBatch(), p1.getBloodVolume_6(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();
                                                        //myDB.updateRoster(thisHouse,"BloodVolume_6",p1.getBloodVolume_6(), String.valueOf(p1.getSRNO()));
                                                        if (vol4.isChecked()) {
                                                            p1.setBloodVolume_10("1");
                                                        } else {
                                                            p1.setBloodVolume_10("2");
                                                        }


                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodVolume_10", p1.getAssignmentID(), p1.getBatch(), p1.getBloodVolume_10(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();

                                                        p1.setBloodVolumeComment(Edttubevolume.getText().toString());

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodVolumeComment", p1.getAssignmentID(), p1.getBatch(), p1.getBloodVolumeComment(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();

                                                        if (rbtn3.isChecked() || rbtn4.isChecked()) {
                                                            p1.setChPrntlConsentRHT(selected2.getText().toString().substring(0, 1));

                                                            myDB.onOpen(myDB.getReadableDatabase());
                                                            myDB.updateConsents("ChPrntlConsentRHT", p1.getAssignmentID(), p1.getBatch(), p1.getChPrntlConsentRHT(), String.valueOf(p1.getSRNO()));
                                                            myDB.close();
                                                        }

                                                        if (rbtn3.isChecked()) {

                                                            p1.setRapidResults(selected3.getText().toString().substring(0, 1));

                                                            myDB.onOpen(myDB.getReadableDatabase());
                                                            //myDB.updateRoster(thisHouse,"RapidResults",p1.getRapidResults(), String.valueOf(p1.getSRNO()));
                                                            myDB.updateConsents("RapidResults", p1.getAssignmentID(), p1.getBatch(), p1.getRapidResults(), String.valueOf(p1.getSRNO()));
                                                            myDB.close();
                                                        } else {
                                                            myDB.onOpen(myDB.getReadableDatabase());
                                                            //myDB.updateRoster(thisHouse,"RapidResults",p1.getRapidResults(), String.valueOf(p1.getSRNO()));
                                                            myDB.updateConsents("RapidResults", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                            myDB.close();
                                                        }

                                                        p1.setChPrntlConsentLabTest(selected4.getText().toString().substring(0, 1));

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("ChPrntlConsentLabTest", p1.getAssignmentID(), p1.getBatch(), p1.getChPrntlConsentLabTest(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();

                                                        p1.setChPrntlConsentBloodStore(selected5.getText().toString().substring(0, 1));

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("ChPrntlConsentBloodStore", p1.getAssignmentID(), p1.getBatch(), p1.getChPrntlConsentBloodStore(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();

                                                        //************************set guardian id or name***************************************/
                                                        p1.setB3_Guardian(EdtGuardian.getText().toString());

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("B3_Guardian", p1.getAssignmentID(), p1.getBatch(), p1.getB3_Guardian(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();

                                                        p1.setRapidDate(EdtDate.getText().toString());
                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("RapidDate", p1.getAssignmentID(), p1.getBatch(), p1.getRapidDate(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();

                                                        p1.setBloodSampleCollected(selected6.getText().toString().substring(0, 1));

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodSampleCollected", p1.getAssignmentID(), p1.getBatch(), p1.getBloodSampleCollected(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();

                                                        Intent intent = new Intent(HIVParentalConsent6wks_9y.this, Dashboard.class);
                                                        intent.putExtra("Individual", individual);
                                                        intent.putExtra("Personroster", p1);
                                                        startActivity(intent);


                                                    }


                                                    //Check if individual already been saved and update

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



    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HIVParentalConsent6wks_9y.this, started_household.class);
        intent.putExtra("Household", thisHouse);
        startActivity(intent);
    }
}




