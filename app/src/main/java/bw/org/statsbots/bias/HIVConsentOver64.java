package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HIVConsentOver64 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected Individual individual;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtnIndeterminate,  rbtn5, rbtn6, rbtn8, rbtn7,rbtn9, rbtn10,rbtn11,rbtn12, rbtn13,  selected1, selected2, selected3,  selected4, selected5 , selected6 ;
    protected RadioGroup rg1, rg2, rg3, rg4, rg5, rg6;
    protected EditText Edttubevolume, EdtDate;
    protected CheckBox vol1, vol2, vol3, vol4;
    protected Button btnNext, btnDate, btnPrev, btnloadinfo;
    protected TextView t1, t2, t3, t4, t5, t6, t7, t8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hivconsent_over64);



        lib = new LibraryClass();


        Intent h = getIntent();
        p1 = (PersonRoster) h.getSerializableExtra("Personroster");


        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());
      //  myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());


       // setTitle("HIV Consent Adults Over 64 years");




/*
        if (p1.getChPrntlConsentBloodDraw().equals("2") && p1.getChPrntlConsentRHT().equals("2"))
        {
            Intent intent = new Intent(HIVConsentOver64.this, Dashboard.class);
            intent.putExtra("Personroster", p1);
            startActivity(intent);
        }
*/



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
        btnDate = (Button) findViewById(R.id.datebtn);
        btnNext = (Button) findViewById(R.id.btnnext);
        btnPrev = (Button) findViewById(R.id.btnprev);
        rbtnIndeterminate = (RadioButton) findViewById(R.id.rbtnIndeterminate);
        btnloadinfo = (Button) findViewById(R.id.LoadConsetInfo);
        EdtDate.setEnabled(false);
        //  rbtnIndeterminate.setChecked(false);
        vol1 = (CheckBox) findViewById(R.id.vol1);
        vol2 = (CheckBox) findViewById(R.id.vol2);
        vol3 = (CheckBox) findViewById(R.id.vol3);
        vol4 = (CheckBox) findViewById(R.id.vol4);

        t1 = (TextView) findViewById(R.id.volrectext);
        t2 = (TextView) findViewById(R.id.rhttxt);
        t3 = (TextView) findViewById(R.id.rhtresulttext);
        t4 = (TextView) findViewById(R.id.txtslab);
        t6 = (TextView) findViewById(R.id.bloodColectionStatus);
        t5 = (TextView) findViewById(R.id.txtstore);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbtn1 &&(p1.getP04YY() != null && Integer.valueOf(p1.getP04YY())>= 65) ) {
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


                } else
                    if (i == R.id.rbtn1 && (p1.getP04YY() != null && (Integer.valueOf(p1.getP04YY())>= 3 && Integer.valueOf(p1.getP04YY())<= 14) ))
                {
                    vol2.setEnabled(false);
                    vol4.setEnabled(false);

                    vol1.setEnabled(true);

                    vol3.setEnabled(true);

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

                    else {
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


        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbtn3) {
                    // is checked

                    rbtnIndeterminate.setEnabled(true);
                    rbtn5.setEnabled(true);
                    rbtn6.setEnabled(true);
                    t3.setTextColor(Color.BLACK);


                } else {


                    rbtn5.setEnabled(false);
                    rbtn6.setEnabled(false);
                    rbtnIndeterminate.setEnabled(false);

                    t3.setTextColor(Color.LTGRAY);

                    rbtn5.setChecked(false);
                    rbtnIndeterminate.setChecked(false);
                    rbtn6.setChecked(false);


                }
            }
        });

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        List<HouseHold> hhh = myDB.getHouseForUpdate(p1.getAssignmentID(),p1.getBatch());
        thisHouse=hhh.get(0);

        if(Integer.valueOf(p1.getP04YY()) < 15)
        {
            setTitle("HIV Assent 10-14 years");
        }
        else
        {
            setTitle("HIV Consent Adults Over 64 years");
        }



        RadioButton[] bt1 = new RadioButton[2];
        for(int f=0;f<rg1.getChildCount();f++)
        {
            View o = rg1.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt1[f]=((RadioButton)o);
                if(p1.getBloodDraw()!= null &&  !p1.getBloodDraw().equals(""))
                {
                    if(Integer.parseInt(p1.getBloodDraw())==f+1)
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


        //////////////////////////////////////////////////////////////////////////////////////////////
        RadioButton[] bt2 = new RadioButton[2];
        for(int f=0;f<rg2.getChildCount();f++)
        {
            View o = rg2.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt2[f]=((RadioButton)o);
                if(p1.getRapid()!= null &&  !p1.getRapid().equals(""))
                {
                    if(Integer.parseInt(p1.getRapid())==f+1)
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
                if(p1.getBloodLabTest()!= null &&  !p1.getBloodLabTest().equals(""))
                {
                    if(Integer.parseInt(p1.getBloodLabTest())==f+1)
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
                if(p1.getBloodStore()!= null &&  !p1.getBloodStore().equals(""))
                {
                    if(Integer.parseInt(p1.getBloodStore())==f+1)
                    {
                        RadioButton radioButton = bt5[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        if( p1.getBloodVolumeComment() != null)
        {
            Edttubevolume.setText(p1.getBloodVolumeComment());
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


        /*if (individual.getQ102() != null && Integer.valueOf(individual.getQ102())>= 15 )
        {
            vol3.setEnabled(false);

        }*/

        if (p1.getP04YY() != null && Integer.valueOf(p1.getP04YY())>= 65 )
        {
            vol3.setEnabled(false);

        }
        if (p1.getP04YY() != null && (Integer.valueOf(p1.getP04YY())>= 3 && Integer.valueOf(p1.getP04YY())<= 14) )
        {
            vol2.setEnabled(false);
            vol4.setEnabled(false);

        }

        if (p1.getP04YY() != null && Integer.valueOf(p1.getP04YY())< 3 )
        {
            vol2.setEnabled(false);
            vol3.setEnabled(false);
            vol4.setEnabled(false);

        }

        if (p1.getChPrntlConsentBloodDraw() != null && p1.getChPrntlConsentBloodDraw().equals("2") )
        {

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.updateConsents("ChPrntlConsentBloodDraw", p1.getAssignmentID(), p1.getBatch(), "0", String.valueOf(p1.getSRNO()));
            myDB.close();


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


        //  btnloadinfo = (Button) findViewById(R.id.LoadConsetInfo);
        btnloadinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create Date Object

                if (p1.getP04YY() != null && Integer.valueOf(p1.getP04YY()) >= 65) {
                    Intent intent = new Intent(HIVConsentOver64.this, HIVOv64ConsentsInfo.class);
                    intent.putExtra("Individual", individual);
                    intent.putExtra("Personroster", p1);
                    startActivity(intent);
                } else {
                    if (p1.getP04YY() != null && Integer.valueOf(p1.getP04YY()) <= 14) {
                        Intent intent = new Intent(HIVConsentOver64.this, HIVAssentsInfo_10_17.class);
                        intent.putExtra("Individual", individual);
                        intent.putExtra("Personroster", p1);
                        startActivity(intent);
                    }
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId1 = rg1.getCheckedRadioButtonId();
                selected1 = (RadioButton) findViewById(selectedId1);

                if (selected1 == null) {
                    lib.showError(HIVConsentOver64.this, "Draw Blood: Error", "Do you agree for the survey team to draw your blood for HIV testing and other related testing?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                } else {

                    if (!(vol1.isChecked() || vol2.isChecked() || vol3.isChecked() || vol4.isChecked()) && rbtn1.isChecked()) {
                        lib.showError(HIVConsentOver64.this, "IndividualConsent: Error: 1a", "Please record container used");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    }
                    else {

                        if (((vol2.isChecked() && (!vol4.isChecked()) ) || (vol4.isChecked() && (!vol2.isChecked()))
                                || (vol2.isChecked() && vol1.isChecked()) ||  (vol4.isChecked() && vol1.isChecked()) ||
                                (vol4.isChecked() && vol1.isChecked() && vol2.isChecked()) ||(vol1.isChecked() && vol3.isChecked()) ) )
                        {
                            lib.showError(HIVConsentOver64.this, "IndividualConsent: Error: 1a", "10ml container and 4ml container can not be used" +
                                    " in isolation. The participant need 14ml or just 1ml for rht. Please select accordingly");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);

                        }else {
                            int selectedId2 = rg2.getCheckedRadioButtonId();
                            selected2 = (RadioButton) findViewById(selectedId2);

                            if (selected2 == null) {
                                lib.showError(HIVConsentOver64.this, "RHT: Error: 2", "Do you agree for the survey team to do RHT?");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);

                            } else {
                                int selectedId3 = rg3.getCheckedRadioButtonId();
                                selected3 = (RadioButton) findViewById(selectedId3);

                                if (selected3 == null && rbtn3.isChecked()) {
                                    lib.showError(HIVConsentOver64.this, "RHT Results: Error: 2a", "Please record RHT results?");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);

                                } else {
                                    int selectedId4 = rg4.getCheckedRadioButtonId();
                                    selected4 = (RadioButton) findViewById(selectedId4);

                                    if (selected4 == null && rbtn1.isChecked()) {
                                        lib.showError(HIVConsentOver64.this, "Laboratory: Error: 3", "3. Do you agree for your blood sample to be sent to the laboratory for additional HIV related testing?");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);

                                    } else {
                                        int selectedId5 = rg5.getCheckedRadioButtonId();
                                        selected5 = (RadioButton) findViewById(selectedId5);

                                        if (selected5 == null && rbtn1.isChecked()) {
                                            lib.showError(HIVConsentOver64.this, "Storage: Error: 4", "4. Do you agree for your blood sample to be stored for up to 5 years for future HIV/TB - related research?");
                                            /**
                                             * VIBRATE DEVICE
                                             */
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);

                                        } else {


                                            if (EdtDate == null) {
                                                lib.showError(HIVConsentOver64.this, "DATE: Error: ", "Please record date");
                                                /**
                                                 * VIBRATE DEVICE
                                                 */
                                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                vibs.vibrate(100);

                                            } else {
                                                int selectedId6 = rg6.getCheckedRadioButtonId();
                                                selected6 = (RadioButton) findViewById(selectedId6);

                                                if (selected6 == null && rbtn1.isChecked()) {
                                                    lib.showError(HIVConsentOver64.this, "Blood collection: Error: 2a", "Please record blood status");
                                                    /**
                                                     * VIBRATE DEVICE
                                                     */
                                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                    vibs.vibrate(100);

                                                } else {

                                                    if (rbtn2.isChecked()) {

                                                        p1.setBloodDraw(selected1.getText().toString().substring(0, 1));
                                                        p1.setRapid(selected2.getText().toString().substring(0, 1));


                                                        p1.setRapidDate(EdtDate.getText().toString());
                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodDraw", p1.getAssignmentID(), p1.getBatch(), p1.getBloodDraw(), String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("BloodVolume_1", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("BloodVolume_4", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("BloodVolume_6", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("BloodVolume_10", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("BloodVolumeComment", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));

                                                        myDB.updateConsents("Rapid", p1.getAssignmentID(), p1.getBatch(), p1.getRapid(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();

                                                        if (rbtn3.isChecked()) {

                                                            p1.setRapidResults(selected3.getText().toString().substring(0, 1));

                                                            myDB.onOpen(myDB.getReadableDatabase());
                                                            //myDB.updateRoster(thisHouse,"RapidResults",p1.getRapidResults(), String.valueOf(p1.getSRNO()));
                                                            myDB.updateConsents("RapidResults", p1.getAssignmentID(), p1.getBatch(), p1.getRapidResults(), String.valueOf(p1.getSRNO()));
                                                            myDB.close();
                                                        }
                                                        else {
                                                            myDB.onOpen(myDB.getReadableDatabase());
                                                            //myDB.updateRoster(thisHouse,"RapidResults",p1.getRapidResults(), String.valueOf(p1.getSRNO()));
                                                            myDB.updateConsents("RapidResults", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                            myDB.close();
                                                        }

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodLabTest", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("BloodStore", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("RapidDate", p1.getAssignmentID(), p1.getBatch(), p1.getRapidDate(), String.valueOf(p1.getSRNO()));
                                                        myDB.updateConsents("BloodSampleCollected", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));
                                                        myDB.close();




//                                                        myDB.onOpen(myDB.getReadableDatabase());
//                                                        //  myDB.updateRoster(thisHouse,"tRapidDate",p1.getRapidDate(), String.valueOf(p1.getSRNO()));
//                                                        myDB.updateConsents("RapidDate", p1.getAssignmentID(), p1.getBatch(), p1.getRapidDate(), String.valueOf(p1.getSRNO()));
//                                                        myDB.close();


                                                        /*******************Launch VISIT***************************/


                                                        final CharSequence[] list1 = new String[3];
                                                        final ArrayList<String> list = new ArrayList<>();
                                                        try {
                                                            if (individual.getVISIT1() != null && individual.getVISIT2() == null && individual.getVISIT3() == null) {

                                                                list1[0] = "Visit 1 : " + individual.getDATE1();
                                                                list.add("Visit 1 : " + individual.getDATE1());
                                                                list1[1] = "Visit 2 : ";
                                                                list.add("Visit 2 : ");
                                                            } else if (individual.getVISIT1() != null && individual.getVISIT2() != null && individual.getVISIT3() == null) {

                                                                list1[0] = "Visit 1 : " + individual.getDATE1();
                                                                list.add("Visit 1 : " + individual.getDATE1());
                                                                list1[1] = "Visit 2 : " + individual.getDATE2();
                                                                list.add("Visit 2 : " + individual.getDATE2());
                                                                list1[2] = "Visit 3 : ";
                                                                list.add("Visit 3 : ");
                                                            } else if (individual.getVISIT1() != null && individual.getVISIT2() != null && individual.getVISIT3() != null) {

                                                                list1[0] = "Visit 1 : " + individual.getDATE1();
                                                                list.add("Visit 1 : " + individual.getDATE1());
                                                                list1[1] = "Visit 2 : " + individual.getDATE2();
                                                                list.add("Visit 2 : " + individual.getDATE2());
                                                                list1[2] = "Visit 3 : ";
                                                                list.add("Visit 3 : " + individual.getDATE3());
                                                            } else {
                                                                list.add("Visit 1 ");
                                                            }


                                                        } catch (Exception f) {
                                                            f.printStackTrace();
                                                        }


                                                        final int FinalResult[] = new int[1];

                                                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(HIVConsentOver64.this);
                                                        builder2.setTitle("Select Visit Number");

                                                        int i = 0;

                                                        final String[] s = list.toArray(new String[list.size()]);
                                                        final Individual tempIndiv = individual;
                                                        builder2.setSingleChoiceItems(
                                                                s, // Items list
                                                                -1, // Index of checked item (-1 = no selection)
                                                                new DialogInterface.OnClickListener() // Item click listener
                                                                {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        // Get the alert dialog selected item's text
                                                                        final CharSequence[] results = new String[6];
                                                                        results[0] = "1. Completed";
                                                                        results[1] = "2. Partially Completed";
                                                                        results[2] = "3. Present but not available for interviews";
                                                                        results[3] = "4. Refused";
                                                                        results[4] = "5. Postponed";
                                                                        results[5] = "6. Other (Specify)";


                                                                        if (i == 0) {
                                                                            //SHOW LIST FOR RESULTS
                                                                            final AlertDialog.Builder builder3 = new AlertDialog.Builder(HIVConsentOver64.this);
                                                                            builder3.setTitle("Select Visit 1 Result");
                                                                            builder3.setSingleChoiceItems(
                                                                                    results, // Items list
                                                                                    -1, // Index of checked item (-1 = no selection)
                                                                                    new DialogInterface.OnClickListener() // Item click listener
                                                                                    {
                                                                                        @Override
                                                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                                                            //save the selected results
                                                                                            SQLiteDatabase db = myDB.getWritableDatabase();
                                                                                            ContentValues hhValues = new ContentValues();
                                                                                            hhValues.put("VISIT1", i + 1);
                                                                                            Date d = new Date();
                                                                                            CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss", d.getTime());
                                                                                            hhValues.put("DATE1", s.toString());
                                                                                            i = db.update
                                                                                                    ("Individual", // table
                                                                                                            hhValues, // column/value
                                                                                                            "Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                                                                                                            new String[]{String.valueOf(tempIndiv.getAssignmentID()), String.valueOf(tempIndiv.getBatch()), String.valueOf(tempIndiv.getSRNO())}
                                                                                                    );

                                                                                            db.close();


                                                                                            //Restart the current activity
                                                                                            Intent intent = new Intent(HIVConsentOver64.this, started_household.class);
                                                                                            intent.putExtra("Household", thisHouse);
                                                                                            startActivity(intent);
                                                                                            finish();


                                                                                        }
                                                                                    });
                                                                            AlertDialog ad2 = builder3.show();

                                                                            //SET DIVIDER
                                                                            ListView listView = ad2.getListView();
                                                                            listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                                                            listView.setDividerHeight(3);


                                                                        } else if (s.length == 2) {

                                                                            final AlertDialog.Builder builder3 = new AlertDialog.Builder(HIVConsentOver64.this);
                                                                            builder3.setTitle("Select Visit 2 Result");
                                                                            builder3.setSingleChoiceItems(
                                                                                    results, // Items list
                                                                                    -1, // Index of checked item (-1 = no selection)
                                                                                    new DialogInterface.OnClickListener() // Item click listener
                                                                                    {
                                                                                        @Override
                                                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                                                            //save the selected results
                                                                                            SQLiteDatabase db = myDB.getWritableDatabase();
                                                                                            ContentValues hhValues = new ContentValues();
                                                                                            hhValues.put("VISIT2", i + 1);
                                                                                            Date d = new Date();
                                                                                            CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss", d.getTime());
                                                                                            hhValues.put("DATE2", s.toString());
                                                                                            i = db.update
                                                                                                    ("Individual", // table
                                                                                                            hhValues, // column/value
                                                                                                            "Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                                                                                                            new String[]{String.valueOf(tempIndiv.getAssignmentID()), String.valueOf(tempIndiv.getBatch()), String.valueOf(tempIndiv.getSRNO())}
                                                                                                    );

                                                                                            db.close();


                                                                                            //Restart the current activity
                                                                                            Intent intent = new Intent(HIVConsentOver64.this, started_household.class);
                                                                                            intent.putExtra("Household", thisHouse);
                                                                                            startActivity(intent);
                                                                                            finish();


                                                                                        }
                                                                                    });
                                                                            AlertDialog ad2 = builder3.show();

                                                                            //SET DIVIDER
                                                                            ListView listView = ad2.getListView();
                                                                            listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                                                            listView.setDividerHeight(3);

                                                                        } else {

                                                                            final AlertDialog.Builder builder3 = new AlertDialog.Builder(HIVConsentOver64.this);
                                                                            builder3.setTitle("Select Visit 3 Result");
                                                                            builder3.setSingleChoiceItems(
                                                                                    results, // Items list
                                                                                    -1, // Index of checked item (-1 = no selection)
                                                                                    new DialogInterface.OnClickListener() // Item click listener
                                                                                    {
                                                                                        @Override
                                                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                                                            //save the selected results
                                                                                            SQLiteDatabase db = myDB.getWritableDatabase();
                                                                                            ContentValues hhValues = new ContentValues();
                                                                                            hhValues.put("VISIT3", i + 1);
                                                                                            Date d = new Date();
                                                                                            CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss", d.getTime());
                                                                                            hhValues.put("DATE3", s.toString());
                                                                                            i = db.update
                                                                                                    ("Individual", // table
                                                                                                            hhValues, // column/value
                                                                                                            "Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                                                                                                            new String[]{String.valueOf(tempIndiv.getAssignmentID()), String.valueOf(tempIndiv.getBatch()), String.valueOf(tempIndiv.getSRNO())}
                                                                                                    );

                                                                                            db.close();


                                                                                            //Restart the current activity
                                                                                            Intent intent = new Intent(HIVConsentOver64.this, started_household.class);
                                                                                            intent.putExtra("Household", thisHouse);
                                                                                            startActivity(intent);
                                                                                            finish();


                                                                                        }
                                                                                    });
                                                                            AlertDialog ad2 = builder3.show();

                                                                            //SET DIVIDER
                                                                            ListView listView = ad2.getListView();
                                                                            listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                                                            listView.setDividerHeight(3);


                                                                        }


                                                                    }
                                                                });

                                                        AlertDialog ad = builder2.show();

                                                        //SET DIVIDER
                                                        ListView listView = ad.getListView();
                                                        //listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));


                                                        listView.setDividerHeight(3);


                                                        //OK Button layout
                                                        final Button positiveButton = ad.getButton(AlertDialog.BUTTON_POSITIVE);
                                                        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                                                        positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                                                        positiveButton.setTextColor(Color.WHITE);
                                                        positiveButton.setBackgroundColor(Color.parseColor("#3FC0FF"));
                                                        positiveButton.setLayoutParams(positiveButtonLL);

                                                        /******************************END SET STATUS************************************************/


                                                    } else {
                                                        p1.setBloodDraw(selected1.getText().toString().substring(0, 1));


                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodDraw", p1.getAssignmentID(), p1.getBatch(), p1.getBloodDraw(), String.valueOf(p1.getSRNO()));



                                                        if (vol1.isChecked()) {
                                                            p1.setBloodVolume_1("1");

                                                        } else {
                                                            p1.setBloodVolume_1("2");
                                                        }

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodVolume_1", p1.getAssignmentID(), p1.getBatch(), p1.getBloodVolume_1(), String.valueOf(p1.getSRNO()));


                                                        // myDB.updateRoster(thisHouse,"BloodVolume_1",p1.getBloodVolume_1(), String.valueOf(p1.getSRNO()));
                                                        if (vol2.isChecked()) {
                                                            p1.setBloodVolume_4("1");
                                                        } else {
                                                            p1.setBloodVolume_4("2");
                                                        }


                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodVolume_4", p1.getAssignmentID(), p1.getBatch(), p1.getBloodVolume_4(), String.valueOf(p1.getSRNO()));

                                                        //myDB.updateRoster(thisHouse,"BloodVolume_4",p1.getBloodVolume_4(), String.valueOf(p1.getSRNO()));
                                                        if (vol3.isChecked()) {
                                                            p1.setBloodVolume_6("1");
                                                        } else {
                                                            p1.setBloodVolume_6("2");
                                                        }

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodVolume_6", p1.getAssignmentID(), p1.getBatch(), p1.getBloodVolume_6(), String.valueOf(p1.getSRNO()));

                                                        //myDB.updateRoster(thisHouse,"BloodVolume_6",p1.getBloodVolume_6(), String.valueOf(p1.getSRNO()));
                                                        if (vol4.isChecked()) {
                                                            p1.setBloodVolume_10("1");
                                                        } else {
                                                            p1.setBloodVolume_10("2");
                                                        }


                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodVolume_10", p1.getAssignmentID(), p1.getBatch(), p1.getBloodVolume_10(), String.valueOf(p1.getSRNO()));



                                                        p1.setBloodVolumeComment(Edttubevolume.getText().toString());

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodVolumeComment", p1.getAssignmentID(), p1.getBatch(), p1.getBloodVolumeComment(), String.valueOf(p1.getSRNO()));



                                                        p1.setRapid(selected2.getText().toString().substring(0, 1));

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("Rapid", p1.getAssignmentID(), p1.getBatch(), p1.getRapid(), String.valueOf(p1.getSRNO()));


                                                        if (rbtn3.isChecked()) {

                                                            p1.setRapidResults(selected3.getText().toString().substring(0, 1));

                                                            myDB.onOpen(myDB.getReadableDatabase());
                                                            //myDB.updateRoster(thisHouse,"RapidResults",p1.getRapidResults(), String.valueOf(p1.getSRNO()));
                                                            myDB.updateConsents("RapidResults", p1.getAssignmentID(), p1.getBatch(), p1.getRapidResults(), String.valueOf(p1.getSRNO()));

                                                        }
else
                                                        {

                                                            myDB.onOpen(myDB.getReadableDatabase());
                                                            //myDB.updateRoster(thisHouse,"RapidResults",p1.getRapidResults(), String.valueOf(p1.getSRNO()));
                                                            myDB.updateConsents("RapidResults", p1.getAssignmentID(), p1.getBatch(), null, String.valueOf(p1.getSRNO()));

                                                        }

                                                        p1.setBloodLabTest(selected4.getText().toString().substring(0, 1));

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodLabTest", p1.getAssignmentID(), p1.getBatch(), p1.getBloodLabTest(), String.valueOf(p1.getSRNO()));


                                                        p1.setBloodStore(selected5.getText().toString().substring(0, 1));

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodStore", p1.getAssignmentID(), p1.getBatch(), p1.getBloodStore(), String.valueOf(p1.getSRNO()));



                                                        p1.setRapidDate(EdtDate.getText().toString());

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("RapidDate", p1.getAssignmentID(), p1.getBatch(), p1.getRapidDate(), String.valueOf(p1.getSRNO()));


                                                        p1.setBloodSampleCollected(selected6.getText().toString().substring(0, 1));

                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                        myDB.updateConsents("BloodSampleCollected", p1.getAssignmentID(), p1.getBatch(), p1.getBloodSampleCollected(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();

                                                        //Check if individual already been saved and update


                                                        //Next question P17
                                                        // myDB = new DatabaseHelper(HIVConsentOver64.this);
                                                        // myDB.onOpen(myDB.getReadableDatabase());


                                                        //Update
                                                        /*******************Launch VISIT***************************/

                                                        if(individual!=null) {


                                                            final CharSequence[] list1 = new String[3];
                                                            final ArrayList<String> list = new ArrayList<>();
                                                            try {
                                                                if (individual.getVISIT1() != null && individual.getVISIT2() == null && individual.getVISIT3() == null) {

                                                                    list1[0] = "Visit 1 : " + individual.getDATE1();
                                                                    list.add("Visit 1 : " + individual.getDATE1());
                                                                    list1[1] = "Visit 2 : ";
                                                                    list.add("Visit 2 : ");
                                                                } else if (individual.getVISIT1() != null && individual.getVISIT2() != null && individual.getVISIT3() == null) {

                                                                    list1[0] = "Visit 1 : " + individual.getDATE1();
                                                                    list.add("Visit 1 : " + individual.getDATE1());
                                                                    list1[1] = "Visit 2 : " + individual.getDATE2();
                                                                    list.add("Visit 2 : " + individual.getDATE2());
                                                                    list1[2] = "Visit 3 : ";
                                                                    list.add("Visit 3 : ");
                                                                } else if (individual.getVISIT1() != null && individual.getVISIT2() != null && individual.getVISIT3() != null) {

                                                                    list1[0] = "Visit 1 : " + individual.getDATE1();
                                                                    list.add("Visit 1 : " + individual.getDATE1());
                                                                    list1[1] = "Visit 2 : " + individual.getDATE2();
                                                                    list.add("Visit 2 : " + individual.getDATE2());
                                                                    list1[2] = "Visit 3 : ";
                                                                    list.add("Visit 3 : " + individual.getDATE3());
                                                                } else {
                                                                    list.add("Visit 1 ");
                                                                }


                                                            } catch (Exception f) {
                                                                f.printStackTrace();
                                                            }


                                                            final int FinalResult[] = new int[1];

                                                            final AlertDialog.Builder builder2 = new AlertDialog.Builder(HIVConsentOver64.this);
                                                            builder2.setTitle("Select Visit Number");

                                                            int i = 0;

                                                            final String[] s = list.toArray(new String[list.size()]);
                                                            final Individual tempIndiv = individual;
                                                            builder2.setSingleChoiceItems(
                                                                    s, // Items list
                                                                    -1, // Index of checked item (-1 = no selection)
                                                                    new DialogInterface.OnClickListener() // Item click listener
                                                                    {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                                            // Get the alert dialog selected item's text
                                                                            final CharSequence[] results = new String[6];
                                                                            results[0] = "1. Completed";
                                                                            results[1] = "2. Partially Completed";
                                                                            results[2] = "3. Present but not available for interviews";
                                                                            results[3] = "4. Refused";
                                                                            results[4] = "5. Postponed";
                                                                            results[5] = "6. Other (Specify)";


                                                                            if (i == 0) {
                                                                                //SHOW LIST FOR RESULTS
                                                                                final AlertDialog.Builder builder3 = new AlertDialog.Builder(HIVConsentOver64.this);
                                                                                builder3.setTitle("Select Visit 1 Result");
                                                                                builder3.setSingleChoiceItems(
                                                                                        results, // Items list
                                                                                        -1, // Index of checked item (-1 = no selection)
                                                                                        new DialogInterface.OnClickListener() // Item click listener
                                                                                        {
                                                                                            @Override
                                                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                                                //save the selected results
                                                                                                SQLiteDatabase db = myDB.getWritableDatabase();
                                                                                                ContentValues hhValues = new ContentValues();
                                                                                                hhValues.put("VISIT1", i + 1);
                                                                                                Date d = new Date();
                                                                                                CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss", d.getTime());
                                                                                                hhValues.put("DATE1", s.toString());
                                                                                                i = db.update
                                                                                                        ("Individual", // table
                                                                                                                hhValues, // column/value
                                                                                                                "Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                                                                                                                new String[]{String.valueOf(tempIndiv.getAssignmentID()), String.valueOf(tempIndiv.getBatch()), String.valueOf(tempIndiv.getSRNO())}
                                                                                                        );

                                                                                                /*******UPDATE HOUSE FOR PARTIAL SEND*****************/

                                                                                                //UPDATE HOUSEHOLD
                                                                                                myDB.updateHousehold(myDB.getReadableDatabase(), thisHouse.getAssignment_ID(), thisHouse.getBatchNumber(), "Clear", "3");
                                                                                                myDB.close();
                                                                                                /********************END PARTIAL****************/


                                                                                                //Restart the current activity
                                                                                                Intent intent = new Intent(HIVConsentOver64.this, started_household.class);
                                                                                                intent.putExtra("Household", thisHouse);
                                                                                                startActivity(intent);
                                                                                                finish();


                                                                                            }
                                                                                        });
                                                                                AlertDialog ad2 = builder3.show();

                                                                                //SET DIVIDER
                                                                                ListView listView = ad2.getListView();
                                                                                listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                                                                listView.setDividerHeight(3);


                                                                            } else if (s.length == 2) {

                                                                                final AlertDialog.Builder builder3 = new AlertDialog.Builder(HIVConsentOver64.this);
                                                                                builder3.setTitle("Select Visit 2 Result");
                                                                                builder3.setSingleChoiceItems(
                                                                                        results, // Items list
                                                                                        -1, // Index of checked item (-1 = no selection)
                                                                                        new DialogInterface.OnClickListener() // Item click listener
                                                                                        {
                                                                                            @Override
                                                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                                                //save the selected results
                                                                                                SQLiteDatabase db = myDB.getWritableDatabase();
                                                                                                ContentValues hhValues = new ContentValues();
                                                                                                hhValues.put("VISIT2", i + 1);
                                                                                                Date d = new Date();
                                                                                                CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss", d.getTime());
                                                                                                hhValues.put("DATE2", s.toString());
                                                                                                i = db.update
                                                                                                        ("Individual", // table
                                                                                                                hhValues, // column/value
                                                                                                                "Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                                                                                                                new String[]{String.valueOf(tempIndiv.getAssignmentID()), String.valueOf(tempIndiv.getBatch()), String.valueOf(tempIndiv.getSRNO())}
                                                                                                        );

                                                                                                /*******UPDATE HOUSE FOR PARTIAL SEND*****************/

                                                                                                //UPDATE HOUSEHOLD
                                                                                                myDB.updateHousehold(myDB.getReadableDatabase(), thisHouse.getAssignment_ID(), thisHouse.getBatchNumber(), "Clear", "3");
                                                                                                myDB.close();
                                                                                                /********************END PARTIAL****************/


                                                                                                //Restart the current activity
                                                                                                Intent intent = new Intent(HIVConsentOver64.this, started_household.class);
                                                                                                intent.putExtra("Household", thisHouse);
                                                                                                startActivity(intent);
                                                                                                finish();


                                                                                            }
                                                                                        });
                                                                                AlertDialog ad2 = builder3.show();

                                                                                //SET DIVIDER
                                                                                ListView listView = ad2.getListView();
                                                                                listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                                                                listView.setDividerHeight(3);

                                                                            } else {

                                                                                final AlertDialog.Builder builder3 = new AlertDialog.Builder(HIVConsentOver64.this);
                                                                                builder3.setTitle("Select Visit 3 Result");
                                                                                builder3.setSingleChoiceItems(
                                                                                        results, // Items list
                                                                                        -1, // Index of checked item (-1 = no selection)
                                                                                        new DialogInterface.OnClickListener() // Item click listener
                                                                                        {
                                                                                            @Override
                                                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                                                //save the selected results
                                                                                                SQLiteDatabase db = myDB.getWritableDatabase();
                                                                                                ContentValues hhValues = new ContentValues();
                                                                                                hhValues.put("VISIT3", i + 1);
                                                                                                Date d = new Date();
                                                                                                CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss", d.getTime());
                                                                                                hhValues.put("DATE3", s.toString());
                                                                                                i = db.update
                                                                                                        ("Individual", // table
                                                                                                                hhValues, // column/value
                                                                                                                "Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                                                                                                                new String[]{String.valueOf(tempIndiv.getAssignmentID()), String.valueOf(tempIndiv.getBatch()), String.valueOf(tempIndiv.getSRNO())}
                                                                                                        );

                                                                                                /*******UPDATE HOUSE FOR PARTIAL SEND*****************/

                                                                                                //UPDATE HOUSEHOLD
                                                                                                myDB.updateHousehold(myDB.getReadableDatabase(), thisHouse.getAssignment_ID(), thisHouse.getBatchNumber(), "Clear", "3");
                                                                                                myDB.close();
                                                                                                /********************END PARTIAL****************/


                                                                                                //Restart the current activity
                                                                                                Intent intent = new Intent(HIVConsentOver64.this, started_household.class);
                                                                                                intent.putExtra("Household", thisHouse);
                                                                                                startActivity(intent);
                                                                                                finish();


                                                                                            }
                                                                                        });
                                                                                AlertDialog ad2 = builder3.show();

                                                                                //SET DIVIDER
                                                                                ListView listView = ad2.getListView();
                                                                                listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                                                                listView.setDividerHeight(3);


                                                                            }


                                                                        }
                                                                    });

                                                            AlertDialog ad = builder2.show();

                                                            //SET DIVIDER
                                                            ListView listView = ad.getListView();
                                                            //listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));


                                                            listView.setDividerHeight(3);


                                                            //OK Button layout
                                                            final Button positiveButton = ad.getButton(AlertDialog.BUTTON_POSITIVE);
                                                            LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                                                            positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                                                            positiveButton.setTextColor(Color.WHITE);
                                                            positiveButton.setBackgroundColor(Color.parseColor("#3FC0FF"));
                                                            positiveButton.setLayoutParams(positiveButtonLL);

                                                        }else{

                                                            //Restart the current activity
                                                            Intent intent = new Intent(HIVConsentOver64.this, started_household.class);
                                                            intent.putExtra("Household", thisHouse);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                        /******************************END SET STATUS************************************************/


                                                        /*Intent intent = new Intent(HIVConsentOver64.this, Dashboard.class);
                                                        intent.putExtra("Personroster", p1);
                                                        startActivity(intent);*/

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
        Intent intent = new Intent(HIVConsentOver64.this, started_household.class);
        intent.putExtra("Household", thisHouse);
        startActivity(intent);
    }

}

