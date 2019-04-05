package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q604 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt, ck3txt, ck4txt, ck5txt, ck6txt, ck7txt, ck8txt, ck10txt, ck11txt, ck12txt, ck13txt, ck14txt, ck15txt, chkOther;
    protected Button btn;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, selectedRbtn, selectedRbtna;
    protected RadioGroup rg, rga;
    protected EditText q604edt;
    protected  DatabaseHelper myDB;
    protected TextView t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q604);


        setTitle("Q604: KNOWLEDGE ABOUT HIV/AIDS AND TB");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        //int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }



        rbtn1 = findViewById(R.id.q604_1);
        rbtn2 = findViewById(R.id.q604_2);

        rbtna1 = findViewById(R.id.q604a_1);
        rbtna2 = findViewById(R.id.q604a_2);
        rg = findViewById(R.id.q604radioGroup);
        rga = findViewById(R.id.q604aGroup1);

        ck1txt = findViewById(R.id.q604b_1);
        ck2txt = findViewById(R.id.q604b_2);
        ck3txt = findViewById(R.id.q604b_3);
        ck4txt = findViewById(R.id.q604b_4);
        ck5txt = findViewById(R.id.q604b_5);
        ck6txt = findViewById(R.id.q604b_6);
        ck7txt = findViewById(R.id.q604b_7);
        ck8txt = findViewById(R.id.q604b_8);
        ck10txt = findViewById(R.id.q604b_10);

        ck11txt = findViewById(R.id.q604b_11);
        ck12txt = findViewById(R.id.q604b_12);
        ck13txt = findViewById(R.id.q604b_13);
        ck14txt = findViewById(R.id.q604b_14);
        ck15txt = findViewById(R.id.q604b_15);


        chkOther = findViewById(R.id.q604b_Other);
        q604edt = findViewById(R.id.q604b_othertxt);
        t1 = findViewById(R.id.q604a);
        t2 = findViewById(R.id.q604b);


        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ604()!= null &&  !ind.getQ604().equals(""))
                {
                    if(Integer.parseInt(ind.getQ604())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bta = new RadioButton[2];
        for(int f=0;f<rga.getChildCount();f++)
        {
            View o = rga.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bta[f]=((RadioButton)o);
                if(ind.getQ604a()!= null &&  !ind.getQ604a().equals(""))
                {
                    if(Integer.parseInt(ind.getQ604a())==f+1)
                    {
                        RadioButton radioButton = bta[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }


        if(ind.getQ604b_1()!= null &&  !ind.getQ604b_1().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_1())== 1)
            {
                ck1txt.setChecked(true);

            }else
            {
                ck1txt.setChecked(false);
            }
        }



        if(ind.getQ604b_2()!= null &&  !ind.getQ604b_2().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_2())== 1)
            {
                ck2txt.setChecked(true);

            }else
            {
                ck2txt.setChecked(false);
            }
        }
        if(ind.getQ604b_3()!= null &&  !ind.getQ604b_3().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_3())== 1)
            {
                ck3txt.setChecked(true);

            }else
            {
                ck3txt.setChecked(false);
            }
        }

        if(ind.getQ604b_4()!= null &&  !ind.getQ604b_4().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_4())== 1)
            {
                ck4txt.setChecked(true);

            }else
            {
                ck4txt.setChecked(false);
            }
        }

        if(ind.getQ604b_5()!= null &&  !ind.getQ604b_5().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_5())== 1)
            {
                ck5txt.setChecked(true);

            }else
            {
                ck5txt.setChecked(false);
            }
        }


        if(ind.getQ604b_6()!= null &&  !ind.getQ604b_6().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_6())== 1)
            {
                ck6txt.setChecked(true);

            }else
            {
                ck6txt.setChecked(false);
            }
        }

        if(ind.getQ604b_7()!= null &&  !ind.getQ604b_7().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_7())== 1)
            {
                ck7txt.setChecked(true);

            }else
            {
                ck7txt.setChecked(false);
            }
        }

        if(ind.getQ604b_8()!= null &&  !ind.getQ604b_8().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_8())== 1)
            {
                ck8txt.setChecked(true);

            }else
            {
                ck8txt.setChecked(false);
            }
        }

        if(ind.getQ604b_10()!= null &&  !ind.getQ604b_10().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_10())== 1)
            {
                ck10txt.setChecked(true);

            }else
            {
                ck10txt.setChecked(false);
            }
        }
        if(ind.getQ604b_11()!= null &&  !ind.getQ604b_11().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_11())== 1)
            {
                ck11txt.setChecked(true);

            }else
            {
                ck11txt.setChecked(false);
            }
        }

        if(ind.getQ604b_12()!= null &&  !ind.getQ604b_12().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_12())== 1)
            {
                ck12txt.setChecked(true);

            }else
            {
                ck12txt.setChecked(false);
            }
        }
        if(ind.getQ604b_13()!= null &&  !ind.getQ604b_13().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_13())== 1)
            {
                ck13txt.setChecked(true);

            }else
            {
                ck13txt.setChecked(false);
            }
        }

        if(ind.getQ604b_14()!= null &&  !ind.getQ604b_14().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_14())== 1)
            {
                ck14txt.setChecked(true);

            }else
            {
                ck14txt.setChecked(false);
            }
        }

        if(ind.getQ604b_15()!= null &&  !ind.getQ604b_15().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_15())== 1)
            {
                ck15txt.setChecked(true);

            }else
            {
                ck15txt.setChecked(false);
            }
        }


        if(ind.getQ604b_Other() != null &&  !ind.getQ604b_Other().equals(""))
        {
            if(Integer.parseInt(ind.getQ604b_Other().substring(0,1))== 1)
            {
                ck15txt.setChecked(true);
                q604edt.setVisibility(View.VISIBLE);
                q604edt.setText(ind.getQ604b_Other().substring(1,ind.getQ604b_Other().length()));

            }else
            {
                ck15txt.setChecked(false);
            }
        }



//
//        if(ind.getQ604b_Otherspecify()!= null )
//        {
//            q604edt.setText(ind.getQ604b_Otherspecify());
//        }


         if (individual.getQ604() != null && individual.getQ604().equals("2") )
         {
             rbtna1.setEnabled(false);
             rbtna2.setEnabled(false);

             rbtna1.setChecked(false);
             rbtna2.setChecked(false);

             t1.setTextColor(Color.LTGRAY);
             t2.setTextColor(Color.LTGRAY);

             ck1txt.setEnabled(false);
             ck2txt.setEnabled(false);
             ck3txt.setEnabled(false);
             ck4txt.setEnabled(false);
             ck5txt.setEnabled(false);
             ck6txt.setEnabled(false);
             ck7txt.setEnabled(false);
             ck8txt.setEnabled(false);
             ck10txt.setEnabled(false);

             ck11txt.setEnabled(false);
             ck12txt.setEnabled(false);
             ck13txt.setEnabled(false);
             ck14txt.setEnabled(false);
             ck15txt.setEnabled(false);
             chkOther.setEnabled(false);

             ck1txt.setChecked(false);
             ck2txt.setChecked(false);
             ck3txt.setChecked(false);
             ck4txt.setChecked(false);
             ck5txt.setChecked(false);
             ck6txt.setChecked(false);
             ck7txt.setChecked(false);
             ck8txt.setChecked(false);
             ck10txt.setChecked(false);

             ck11txt.setChecked(false);
             ck12txt.setChecked(false);
             ck13txt.setChecked(false);
             ck14txt.setChecked(false);
             ck15txt.setChecked(false);
             chkOther.setChecked(false);
             q604edt.setText("");
         }

        if (individual.getQ604a() != null && individual.getQ604a().equals("2") )
        {

            t2.setTextColor(Color.LTGRAY);

            ck1txt.setEnabled(false);
            ck2txt.setEnabled(false);
            ck3txt.setEnabled(false);
            ck4txt.setEnabled(false);
            ck5txt.setEnabled(false);
            ck6txt.setEnabled(false);
            ck7txt.setEnabled(false);
            ck8txt.setEnabled(false);
            ck10txt.setEnabled(false);

            ck11txt.setEnabled(false);
            ck12txt.setEnabled(false);
            ck13txt.setEnabled(false);
            ck14txt.setEnabled(false);
            ck15txt.setEnabled(false);
            chkOther.setEnabled(false);

            ck1txt.setChecked(false);
            ck2txt.setChecked(false);
            ck3txt.setChecked(false);
            ck4txt.setChecked(false);
            ck5txt.setChecked(false);
            ck6txt.setChecked(false);
            ck7txt.setChecked(false);
            ck8txt.setChecked(false);
            ck10txt.setChecked(false);

            ck11txt.setChecked(false);
            ck12txt.setChecked(false);
            ck13txt.setChecked(false);
            ck14txt.setChecked(false);
            ck15txt.setChecked(false);
            chkOther.setChecked(false);
            q604edt.setText("");
        }


        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);
                if (selectedRbtn == null) {
                    lib.showError(q604.this, "Q604:", "Have you EVER heard of TB?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    int selectedIda = rga.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedIda);

                    if (selectedRbtna == null && rbtn1.isChecked()) {
                        lib.showError(q604.this, "Q604:", "In the past 4 weeks, have you heard or seen any information about TB?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        if (selectedRbtna == rbtn2) {
                            Intent intent = new Intent(q604.this, q606.class);
                            //intent.putExtra("Household", thisHose);
                            startActivity(intent);
                        } else {
                            if (!ck1txt.isChecked() && !ck2txt.isChecked() && !ck3txt.isChecked() && !ck4txt.isChecked() && !ck5txt.isChecked() && !ck6txt.isChecked() && !ck7txt.isChecked() && !ck8txt.isChecked() && !ck10txt.isChecked() && !ck11txt.isChecked() && !ck12txt.isChecked()
                                    && !ck13txt.isChecked() && !ck14txt.isChecked() && !ck14txt.isChecked() && !chkOther.isChecked() && rbtn1.isChecked() && rbtna1.isChecked()) {
                                lib.showError(q604.this, "Q604:", "From what source(s) did you receive information about TB?"
                                        + "Please select atleast one checkbox");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {
                                if ((((chkOther.isChecked() && q604edt.length() == 0)))) {
                                    lib.showError(q604.this, "Q604:", "Please specify other or uncheck Other specify");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);
                                } else {



                                        if (rbtn2.isChecked() && (sample.getStatusCode().equals("1")) ||
                                            (rbtn2.isChecked() && sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) ||
                                                (sample.getStatusCode().equals("2") &&thisHous.get(0).getHIVTB40().equals("1") &&
                                                        (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 ) &&  Integer.valueOf(individual.getQ102()) < 65) ){

                                        individual.setQ604(selectedRbtn.getText().toString().substring(0, 1));

                            individual.setQ605_1(null);
                            individual.setQ605_2(null);
                            individual.setQ605_3(null);
                            individual.setQ605_4(null);
                            individual.setQ605_5(null);
                            individual.setQ605_9(null);
                            individual.setQ605_Other(null);


                                        myDB.onOpen(myDB.getReadableDatabase());
                                        myDB.getWritableDatabase();
                                        myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                        myDB.close();
                                        Intent intent1 = new Intent(q604.this, q606.class);
                                        intent1.putExtra("Individual", individual);
                                        startActivity(intent1);

                                    } else {

                                            if( rbtn2.isChecked() && ((sample.getStatusCode().equals("3") )  || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0") )
                                                    || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) && Integer.valueOf(individual.getQ102()) > 64
                                            ) ||((sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1")) &&
                                                    p1.getP06().equals("2")  ) ))
                                            {

                                            individual.setQ604(selectedRbtn.getText().toString().substring(0, 1));
                                                individual.setQ605_1(null);
                                                individual.setQ605_2(null);
                                                individual.setQ605_3(null);
                                                individual.setQ605_4(null);
                                                individual.setQ605_5(null);
                                                individual.setQ605_9(null);
                                                individual.setQ605_Other(null);
                                                individual.setQ606(null);
                                                individual.setQ607(null);
                                                individual.setQ608(null);
                                                individual.setQ609(null);
                                                individual.setQ610(null);
                                                individual.setQ611a(null);
                                                individual.setQ611b(null);
                                                individual.setQ611c(null);

                                                individual.setQ612(null);
                                                individual.setQ612a(null);
                                                individual.setQ612aOther(null);
                                                individual.setQ613(null);
                                                individual.setQ613a(null);
                                                individual.setQ613aOther(null);
                                                individual.setQ614(null);
                                                individual.setQ614Other(null);
                                                individual.setQ615(null);
                                                individual.setQ616_1(null);
                                                individual.setQ616_2(null);
                                                individual.setQ616_3(null);
                                                individual.setQ616_4(null);
                                                individual.setQ616_5(null);
                                                individual.setQ616_6(null);
                                                individual.setQ616_7(null);
                                                individual.setQ616_8(null);
                                                individual.setQ616_9(null);
                                                individual.setQ616_Other(null);
                                                individual.setQ617a(null);
                                                individual.setQ617b(null);
                                                individual.setQ617c(null);
                                                individual.setQ617d(null);

                                                individual.setQ617e(null);
                                                individual.setQ617f(null);
                                                individual.setQ617g(null);
                                                individual.setQ617h(null);
                                                individual.setQ617_0ther(null);
                                                individual.setQ618(null);
                                                individual.setQ619_1(null);
                                                individual.setQ619_2(null);
                                                individual.setQ619_3(null);
                                                individual.setQ619_4(null);
                                                individual.setQ619_5(null);
                                                individual.setQ619_6(null);

                                                individual.setQ619_7(null);
                                                individual.setQ619_8(null);
                                                individual.setQ619_9(null);
                                                individual.setQ619_10(null);
                                                individual.setQ619_11(null);
                                                individual.setQ619_12(null);
                                                individual.setQ619_13(null);
                                                individual.setQ619_14(null);
                                                individual.setQ619_Other(null);
                                                individual.setQ620(null);
                                                individual.setQ620_Other(null);
                                                individual.setQ621(null);
                                                individual.setQ621a_1(null);
                                                individual.setQ621a_2(null);
                                                individual.setQ621a_3(null);
                                                individual.setQ621a_4(null);
                                                individual.setQ621a_5(null);
                                                individual.setQ621a_6(null);
                                                individual.setQ621a_7(null);
                                                individual.setQ621a_Other(null);
                                                individual.setQ621b(null);
                                                individual.setQ621bOther(null);
                                                individual.setQ622(null);
                                                individual.setQ622a(null);
                                                individual.setQ622aOther(null);
                                                individual.setQ622b(null);
                                                individual.setQ622bOther(null);
                                                individual.setQ623(null);
                                                individual.setQ624(null);
                                                individual.setQ625(null);
                                                individual.setQ701(null);
                                                individual.setQ702(null);
                                                individual.setQ703(null);
//                                                individual.setQ704(null);
//                                                individual.setQ705(null);






                                                myDB.onOpen(myDB.getReadableDatabase());
                                            myDB.getWritableDatabase();
                                            myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                            myDB.close();

                                            Intent intent1 = new Intent(q604.this, q704.class);
                                            intent1.putExtra("Individual", individual);
                                            startActivity(intent1);

                                        } else {
                                            if (rbtna2.isChecked()) {
                                                individual.setQ604(selectedRbtn.getText().toString().substring(0, 1));
                                                individual.setQ604a(selectedRbtna.getText().toString().substring(0, 1));

                                                //Clear previous results
                                                individual.setQ604b_1(null);
                                                individual.setQ604b_2(null);
                                                individual.setQ604b_3(null);
                                                individual.setQ604b_4(null);
                                                individual.setQ604b_5(null);
                                                individual.setQ604b_6(null);
                                                individual.setQ604b_7(null);
                                                individual.setQ604b_8(null);
                                                individual.setQ604b_10(null);
                                                individual.setQ604b_11(null);
                                                individual.setQ604b_12(null);
                                                individual.setQ604b_13(null);
                                                individual.setQ604b_14(null);
                                                individual.setQ604b_15(null);
                                                individual.setQ604b_Other(null);
                                                individual.setQ604b_Otherspecify(null);

                                                //update individual
                                                DatabaseHelper myDB = new DatabaseHelper(q604.this);

                                                myDB.onOpen(myDB.getReadableDatabase());
                                                myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                                myDB.close();


                                                Intent intent1 = new Intent(q604.this, q605.class);
                                                intent1.putExtra("Individual", individual);
                                                startActivity(intent1);

                                            } else {

                                                individual.setQ604a(selectedRbtna.getText().toString().substring(0, 1));
                                                individual.setQ604(selectedRbtn.getText().toString().substring(0, 1));

                                           /* individual.setQ604b_1(ck1txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_2(ck2txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_3(ck3txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_4(ck4txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_5(ck5txt.getText().toString().substring(0, 1));

                                            individual.setQ604b_6(ck6txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_7(ck7txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_8(ck8txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_10(ck10txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_11(ck11txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_12(ck12txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_13(ck13txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_14(ck14txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_15(ck15txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_Other(chkOther.getText().toString().substring(0, 1));
                                            individual.setQ604b_Otherspecify(q604edt.getText().toString());*/

                                                if (ck1txt.isChecked()) {
                                                    individual.setQ604b_1("1");
                                                } else {
                                                    individual.setQ604b_1("2");
                                                }

                                                if (ck2txt.isChecked()) {
                                                    individual.setQ604b_2("1");
                                                } else {
                                                    individual.setQ604b_2("2");
                                                }
                                                if (ck3txt.isChecked()) {
                                                    individual.setQ604b_3("1");
                                                } else {
                                                    individual.setQ604b_3("2");
                                                }
                                                if (ck4txt.isChecked()) {
                                                    individual.setQ604b_4("1");
                                                } else {
                                                    individual.setQ604b_4("2");
                                                }
                                                if (ck5txt.isChecked()) {
                                                    individual.setQ604b_5("1");
                                                } else {
                                                    individual.setQ604b_5("2");
                                                }
                                                if (ck6txt.isChecked()) {
                                                    individual.setQ604b_6("1");
                                                } else {
                                                    individual.setQ604b_6("2");
                                                }
                                                if (ck7txt.isChecked()) {
                                                    individual.setQ604b_7("1");
                                                } else {
                                                    individual.setQ604b_7("2");
                                                }
                                                if (ck8txt.isChecked()) {
                                                    individual.setQ604b_8("1");
                                                } else {
                                                    individual.setQ604b_8("2");
                                                }
                                                if (ck10txt.isChecked()) {
                                                    individual.setQ604b_10("1");
                                                } else {
                                                    individual.setQ604b_10("2");
                                                }
                                                if (ck11txt.isChecked()) {
                                                    individual.setQ604b_11("1");
                                                } else {
                                                    individual.setQ604b_11("2");
                                                }
                                                if (ck12txt.isChecked()) {
                                                    individual.setQ604b_12("1");
                                                } else {
                                                    individual.setQ604b_12("2");
                                                }
                                                if (ck13txt.isChecked()) {
                                                    individual.setQ604b_13("1");
                                                } else {
                                                    individual.setQ604b_13("2");
                                                }
                                                if (ck14txt.isChecked()) {
                                                    individual.setQ604b_14("1");
                                                } else {
                                                    individual.setQ604b_14("2");
                                                }

                                                if (ck15txt.isChecked()) {
                                                    individual.setQ604b_15("1");
                                                } else {
                                                    individual.setQ604b_15("2");
                                                }

                                                if (chkOther.isChecked()) {
                                                    individual.setQ604b_Other("1");

                                                    individual.setQ604b_Other("1"+q604edt.getText().toString());

                                                } else {
                                                    individual.setQ604b_Other("2");
                                                }

                                                //update individual
                                                myDB.onOpen(myDB.getReadableDatabase());
                                                myDB.getWritableDatabase();
                                                myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                                myDB.close();

                                                Intent intent = new Intent(q604.this, q605.class);
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
        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) &&
                        (Integer.valueOf(individual.getQ102()) > 64)) ||
                        sample.getStatusCode().equals("3") || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0"))
                        || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) &&
                        p1.getP06().equals("2"))) {

                    Intent q1o2 = new Intent(q604.this, q601.class);
                    q1o2.putExtra("Individual", individual);
                    startActivity(q1o2);
                } else {


                    if (individual.getQ601().equals("2")) {
                        Intent q1o2 = new Intent(q604.this, q601.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);
                    } else {
                            Intent q1o2 = new Intent(q604.this, q603.class);
                            q1o2.putExtra("Individual", individual);
                            startActivity(q1o2);
                        }
                    }
                }


        });

    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.q604_1:
                if (checked)
                    // Remove the meat
                   break;

            case R.id.q604_2:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q604b_3:
                if (checked)
                // Put some meat on the sandwich

                    // Remove the meat
                    break;
            case R.id.q604b_4:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q604b_5:
                if (checked)
                    // Remove the meat
                    break;

            case R.id.q604b_6:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q604b_7:
                if (checked)

                        break;
                    case R.id.q604b_8:
                        if (checked)
                            // Remove the meat
                            break;
                    case R.id.q604b_10:
                        if (checked)
                            // Remove the meat
                            break;
                    case R.id.q604b_11:
                        if (checked)
                            // Remove the meat
                            break;

                    case R.id.q604b_12:
                        if (checked)
                            // Remove the meat
                            break;
                    case R.id.q604b_13:
                        if (checked)
                            // Remove the meat
                            break;

                    case R.id.q604b_14:
                        if (checked)
                            // Remove the meat
                            break;
                    case R.id.q604b_15:
                        if (checked)
                            // Remove the meat
                            break;
                    case R.id.q604b_Other:
                        if (checked)
                                q604edt.setVisibility(View.VISIBLE);


                            // Put some meat on the sandwich
                            else
                                // Remove the meat
                                q604edt.setVisibility(View.INVISIBLE);
                                q604edt.setText("");

                            break;


                        }
                }

    public void onRadioButtonClicked(View view) {
        // Is the view now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.q604_1:
                if (checked)
                    rbtna1.setEnabled(true);
                    rbtna2.setEnabled(true);
                    t1.setTextColor(Color.BLACK);
                    t2.setTextColor(Color.BLACK);

                    ck1txt.setEnabled(true);
                    ck2txt.setEnabled(true);
                    ck3txt.setEnabled(true);
                    ck4txt.setEnabled(true);
                    ck5txt.setEnabled(true);
                    ck6txt.setEnabled(true);
                    ck7txt.setEnabled(true);
                    ck8txt.setEnabled(true);
                    ck10txt.setEnabled(true);

                    ck11txt.setEnabled(true);
                    ck12txt.setEnabled(true);
                    ck13txt.setEnabled(true);
                    ck14txt.setEnabled(true);
                    ck15txt.setEnabled(true);
                    chkOther.setEnabled(true);

                    // Remove the meat
                    break;

            case R.id.q604_2:
                if (checked)

                    rbtna1.setEnabled(false);
                rbtna2.setEnabled(false);

                rbtna1.setChecked(false);
                rbtna2.setChecked(false);

                t1.setTextColor(Color.LTGRAY);
                t2.setTextColor(Color.LTGRAY);

                ck1txt.setEnabled(false);
                ck2txt.setEnabled(false);
                ck3txt.setEnabled(false);
                ck4txt.setEnabled(false);
                ck5txt.setEnabled(false);
                ck6txt.setEnabled(false);
                ck7txt.setEnabled(false);
                ck8txt.setEnabled(false);
                ck10txt.setEnabled(false);

                ck11txt.setEnabled(false);
                ck12txt.setEnabled(false);
                ck13txt.setEnabled(false);
                ck14txt.setEnabled(false);
                ck15txt.setEnabled(false);
                chkOther.setEnabled(false);

                ck1txt.setChecked(false);
                ck2txt.setChecked(false);
                ck3txt.setChecked(false);
                ck4txt.setChecked(false);
                ck5txt.setChecked(false);
                ck6txt.setChecked(false);
                ck7txt.setChecked(false);
                ck8txt.setChecked(false);
                ck10txt.setChecked(false);

                ck11txt.setChecked(false);
                ck12txt.setChecked(false);
                ck13txt.setChecked(false);
                ck14txt.setChecked(false);
                ck15txt.setChecked(false);
                chkOther.setChecked(false);
                q604edt.setText("");


                    break;
            case R.id.q604a_1:
                if (checked)
                    ck1txt.setEnabled(true);
                    ck2txt.setEnabled(true);
                    ck3txt.setEnabled(true);
                    ck4txt.setEnabled(true);
                    ck5txt.setEnabled(true);
                    ck6txt.setEnabled(true);
                    ck7txt.setEnabled(true);
                    ck8txt.setEnabled(true);
                    ck10txt.setEnabled(true);

                    ck11txt.setEnabled(true);
                    ck12txt.setEnabled(true);
                    ck13txt.setEnabled(true);
                    ck14txt.setEnabled(true);
                    ck15txt.setEnabled(true);
                    chkOther.setEnabled(true);

                    t2.setTextColor(Color.BLACK);

                    // Remove the meat
                    break;

            case R.id.q604a_2:
                if (checked)
                    ck1txt.setEnabled(false);
                    ck2txt.setEnabled(false);
                    ck3txt.setEnabled(false);
                    ck4txt.setEnabled(false);
                    ck5txt.setEnabled(false);
                    ck6txt.setEnabled(false);
                    ck7txt.setEnabled(false);
                    ck8txt.setEnabled(false);
                    ck10txt.setEnabled(false);

                    ck11txt.setEnabled(false);
                    ck12txt.setEnabled(false);
                    ck13txt.setEnabled(false);
                    ck14txt.setEnabled(false);
                    ck15txt.setEnabled(false);
                    chkOther.setEnabled(false);
                    t2.setTextColor(Color.LTGRAY);
                ck1txt.setChecked(false);
                ck2txt.setChecked(false);
                ck3txt.setChecked(false);
                ck4txt.setChecked(false);
                ck5txt.setChecked(false);
                ck6txt.setChecked(false);
                ck7txt.setChecked(false);
                ck8txt.setChecked(false);
                ck10txt.setChecked(false);

                ck11txt.setChecked(false);
                ck12txt.setChecked(false);
                ck13txt.setChecked(false);
                ck14txt.setChecked(false);
                ck15txt.setChecked(false);
                chkOther.setChecked(false);
                q604edt.setText("");



                    // Remove the meat
                    break;

                }
        }


    //   thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

//    final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
//        for (PersonRoster p: roster
//        ) {
//        if (p.getSRNO() == ind.getSRNO()){
//            p1 = p;
//            break;
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intervie_control, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {

            case R.id.pause:
                // Show the settings activity
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("[Demo!] Are you sure you want to pause the interview");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(getApplicationContext(), started_household.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q604.this).toBundle());

                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


