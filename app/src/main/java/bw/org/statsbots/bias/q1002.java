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

public class q1002 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected RadioButton rbtn1, rbtn2, rbtn3,rbtnb1, rbtnb2,  rbtnb3, rbtnb4,  rbtnb5, rbtnb6, rbtnbOther ;
    protected CheckBox chka1, chka2, chka3,chka4, chka5,  chka6, chka7,  chka8, chka10, chka11, chka12, chka13, chka14, chka15, chka16, chka17, chka18, chkaOther ;
    protected RadioGroup rg, rga, rgb;
    protected TextView ta, tb;
    protected  Individual individual;protected  DatabaseHelper myDB;
    protected EditText edtOthertxt, edtbOther;
    protected RadioButton selectedRbtn, selectedRbtna, selectedRbtnb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1002);


        setTitle("Q1002: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1002radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q1002_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1002_2);
        rbtn3 = (RadioButton) findViewById(R.id.q1002_9);


        chka1 = (CheckBox) findViewById(R.id.q1002a_1);
        chka2 = (CheckBox) findViewById(R.id.q1002a_2);
        chka3 = (CheckBox) findViewById(R.id.q1002a_3);
        chka4 = (CheckBox) findViewById(R.id.q1002a_4);
        chka5 = (CheckBox) findViewById(R.id.q1002a_5);
        chka6 = (CheckBox) findViewById(R.id.q1002a_6);
        chka7 = (CheckBox) findViewById(R.id.q1002a_7);
        chka8 = (CheckBox) findViewById(R.id.q1002a_8);
        chka10 = (CheckBox) findViewById(R.id.q1002a_10);
        chka11 = (CheckBox) findViewById(R.id.q1002a_11);

        chka12 = (CheckBox) findViewById(R.id.q1002a_12);
        chka13 = (CheckBox) findViewById(R.id.q1002a_13);
        chka14 = (CheckBox) findViewById(R.id.q1002a_14);
        chka15 = (CheckBox) findViewById(R.id.q1002a_15);
        chka16 = (CheckBox) findViewById(R.id.q1002a_16);

        chka17 = (CheckBox) findViewById(R.id.q1002a_17);
        chka18 = (CheckBox) findViewById(R.id.q1002a_18);

        chkaOther = (CheckBox) findViewById(R.id.q1002a_Other);

        edtOthertxt = (EditText) findViewById(R.id.q1002a_Other1);
        edtbOther = (EditText) findViewById(R.id.q1002b_other1);


        rgb = (RadioGroup)findViewById(R.id.q1002bGroup);

        rbtnb1 = (RadioButton) findViewById(R.id.q1002b_1);
        rbtnb2 = (RadioButton) findViewById(R.id.q1002b_2);
        rbtnb3 = (RadioButton) findViewById(R.id.q1002b_3);
        rbtnb4 = (RadioButton) findViewById(R.id.q1002b_4);
        rbtnb5 = (RadioButton) findViewById(R.id.q1002b_5);
        rbtnb6 = (RadioButton) findViewById(R.id.q1002b_6);
        rbtnbOther = (RadioButton) findViewById(R.id.q1002b_other);


        ta = (TextView) findViewById(R.id.q1002a_text) ;
        tb = (TextView) findViewById(R.id.q1002b) ;


        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;


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


        RadioButton[] bt = new RadioButton[3];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ1002()!= null &&  !ind.getQ1002().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1002())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }



        if(ind.getQ1002a_1()!= null &&  !ind.getQ1002a_1().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_1())== 1)
            {
                chka1.setChecked(true);

            }else
            {
                chka1.setChecked(false);
            }
        }



        if(ind.getQ1002a_2()!= null &&  !ind.getQ1002a_2().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_2())== 1)
            {
                chka2.setChecked(true);

            }else
            {
                chka2.setChecked(false);
            }
        }


        if(ind.getQ1002a_3()!= null &&  !ind.getQ1002a_3().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_3())== 1)
            {
                chka3.setChecked(true);

            }else
            {
                chka3.setChecked(false);
            }
        }

        if(ind.getQ1002a_4()!= null &&  !ind.getQ1002a_4().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_4())== 1)
            {
                chka4.setChecked(true);

            }else
            {
                chka4.setChecked(false);
            }
        }

        if(ind.getQ1002a_5()!= null &&  !ind.getQ1002a_5().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_5())== 1)
            {
                chka5.setChecked(true);

            }else
            {
                chka5.setChecked(false);
            }
        }


        if(ind.getQ1002a_6()!= null &&  !ind.getQ1002a_6().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_6())== 1)
            {
                chka6.setChecked(true);

            }else
            {
                chka6.setChecked(false);
            }
        }


        if(ind.getQ1002a_7()!= null &&  !ind.getQ1002a_7().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_7())== 1)
            {
                chka7.setChecked(true);

            }else
            {
                chka7.setChecked(false);
            }
        }

        if(ind.getQ1002a_8()!= null &&  !ind.getQ1002a_8().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_8())== 1)
            {
                chka8.setChecked(true);

            }else
            {
                chka8.setChecked(false);
            }
        }


        if(ind.getQ1002a_10()!= null &&  !ind.getQ1002a_10().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_10())== 1)
            {
                chka10.setChecked(true);

            }else
            {
                chka10.setChecked(false);
            }
        }

        if(ind.getQ1002a_11()!= null &&  !ind.getQ1002a_11().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_11())== 1)
            {
                chka11.setChecked(true);

            }else
            {
                chka11.setChecked(false);
            }
        }

        if(ind.getQ1002a_12()!= null &&  !ind.getQ1002a_12().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_12())== 1)
            {
                chka12.setChecked(true);

            }else
            {
                chka12.setChecked(false);
            }
        }
        if(ind.getQ1002a_13()!= null &&  !ind.getQ1002a_13().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_13())== 1)
            {
                chka13.setChecked(true);

            }else
            {
                chka13.setChecked(false);
            }
        }
        if(ind.getQ1002a_14()!= null &&  !ind.getQ1002a_14().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_14())== 1)
            {
                chka14.setChecked(true);

            }else
            {
                chka14.setChecked(false);
            }
        }

        if(ind.getQ1002a_15()!= null &&  !ind.getQ1002a_15().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_15())== 1)
            {
                chka15.setChecked(true);

            }else
            {
                chka15.setChecked(false);
            }
        }

        if(ind.getQ1002a_16()!= null &&  !ind.getQ1002a_16().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_16())== 1)
            {
                chka16.setChecked(true);

            }else
            {
                chka16.setChecked(false);
            }
        }

        if(ind.getQ1002a_17()!= null &&  !ind.getQ1002a_17().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_17())== 1)
            {
                chka17.setChecked(true);

            }else
            {
                chka17.setChecked(false);
            }
        }

        if(ind.getQ1002a_18()!= null &&  !ind.getQ1002a_18().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_18())== 1)
            {
                chka18.setChecked(true);

            }else
            {
                chka18.setChecked(false);
            }
        }

        if(ind.getQ1002a_18()!= null &&  !ind.getQ1002a_18().equals(""))
        {
            if(Integer.parseInt(ind.getQ1002a_18().substring(0,1))== 1)
            {
                chkaOther.setChecked(true);
                edtOthertxt.setVisibility(View.VISIBLE);
                edtOthertxt.setText(ind.getQ1002a_18().substring(1,ind.getQ1002a_18().length()));
                //individual.setQ616_Other("1"+edt616Other.getText().toString());

            }else
            {
                chkaOther.setChecked(false);
            }
        }

//        if(ind.getQ1002_Other()!= null )
//        {
//            q619edt.setText(ind.getQ619_Other());
//        }


        RadioButton[] btb = new RadioButton[7];
        for(int f=0;f<rgb.getChildCount();f++)
        {
            View o = rgb.getChildAt(f);
            if (o instanceof RadioButton)
            {
                btb[f]=((RadioButton)o);
                if(ind.getQ1002b()!= null &&  !ind.getQ1002b().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1002b())==f+1)
                    {
                        RadioButton radioButton = btb[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q1002.this, "Q1002: ERROR", " Are you or your partner using any method to delay / avoid pregnancy?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {


                    if (rbtn1.isChecked() && (!chka1.isChecked() && !chka2.isChecked() && !chka3.isChecked() && !chka4.isChecked() && !chka5.isChecked() && !chka6.isChecked() && !chka7.isChecked() && !chka8.isChecked() && !chka10.isChecked() && !chka11.isChecked()
                            && !chka12.isChecked() && !chka13.isChecked() && !chka14.isChecked() && !chka15.isChecked() && !chka16.isChecked() && !chka17.isChecked() && !chka18.isChecked() && !chkaOther.isChecked())) {
                        lib.showError(q1002.this, "Q1002a: ERROR", "Which method are you or your partner using to delay getting pregnant");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        if ((((chkaOther.isChecked() && edtOthertxt.length() == 0)))) {
                            lib.showError(q1002.this, "Q1002:", "Please specify other or uncheck Other specify");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {

                            int selectedIdb = rgb.getCheckedRadioButtonId();
                            selectedRbtnb = (RadioButton) findViewById(selectedIdb);

                            if (selectedRbtnb == null && rbtn2.isChecked()) {
                                lib.showError(q1002.this, "Q1002b: ERROR", " If NO, why are you not using any method to delay pregnancy?");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {


                                if (rbtn2.isChecked()) {

                                    individual.setQ1002(selectedRbtn.getText().toString().substring(0, 1));
                                    individual.setQ1002b(selectedRbtnb.getText().toString().substring(0, 1));
                                    individual.setQ1002b_Other(edtbOther.getText().toString());
                                    individual.setQ1002a_1(null);
                                    individual.setQ1002a_2(null);
                                    individual.setQ1002a_3(null);
                                    individual.setQ1002a_4(null);
                                    individual.setQ1002a_5(null);
                                    individual.setQ1002a_6(null);
                                    individual.setQ1002a_7(null);
                                    individual.setQ1002a_8(null);
                                    // individual.setQ1002a_9(null);
                                    individual.setQ1002a_10(null);
                                    individual.setQ1002a_11(null);
                                    individual.setQ1002a_12(null);
                                    individual.setQ1002a_13(null);
                                    individual.setQ1002a_14(null);
                                    individual.setQ1002a_15(null);
                                    individual.setQ1002a_16(null);
                                    individual.setQ1002a_17(null);
                                    individual.setQ1002a_18(null);
                                    individual.setQ1002a_Other(null);

                                    Intent intent = new Intent(q1002.this, q1003.class);
                                    intent.putExtra("Individual", individual);
                                    startActivity(intent);
                                } else {
                                    if (rbtn3.isChecked()) {

                                        individual.setQ1002(selectedRbtn.getText().toString().substring(0, 1));
                                        individual.setQ1002b(selectedRbtnb.getText().toString().substring(0, 1));
                                        individual.setQ1002b_Other(edtbOther.getText().toString());
                                        individual.setQ1002a_1(null);
                                        individual.setQ1002a_2(null);
                                        individual.setQ1002a_3(null);
                                        individual.setQ1002a_4(null);
                                        individual.setQ1002a_5(null);
                                        individual.setQ1002a_6(null);
                                        individual.setQ1002a_7(null);
                                        individual.setQ1002a_8(null);
                                        // individual.setQ1002a_9(null);
                                        individual.setQ1002a_10(null);
                                        individual.setQ1002a_11(null);
                                        individual.setQ1002a_12(null);
                                        individual.setQ1002a_13(null);
                                        individual.setQ1002a_14(null);
                                        individual.setQ1002a_15(null);
                                        individual.setQ1002a_16(null);
                                        individual.setQ1002a_17(null);
                                        individual.setQ1002a_18(null);
                                        individual.setQ1002a_Other(null);

                                        Intent intent = new Intent(q1002.this, q1003.class);
                                        intent.putExtra("Individual", individual);
                                        startActivity(intent);
                                    } else {
                                        individual.setQ1002(selectedRbtn.getText().toString().substring(0, 1));

                                        if (chka1.isChecked()) {
                                            individual.setQ1002a_1("1");
                                        } else {
                                            individual.setQ1002a_1("2");
                                        }

                                        if (chka2.isChecked()) {
                                            individual.setQ1002a_2("1");
                                        } else {
                                            individual.setQ1002a_2("2");
                                        }
                                        if (chka3.isChecked()) {
                                            individual.setQ1002a_3("1");
                                        } else {
                                            individual.setQ1002a_3("2");
                                        }
                                        if (chka4.isChecked()) {
                                            individual.setQ1002a_4("1");
                                        } else {
                                            individual.setQ1002a_4("2");
                                        }
                                        if (chka5.isChecked()) {
                                            individual.setQ1002a_5("1");
                                        } else {
                                            individual.setQ1002a_5("2");
                                        }
                                        if (chka6.isChecked()) {
                                            individual.setQ1002a_6("1");
                                        } else {
                                            individual.setQ1002a_6("2");
                                        }
                                        if (chka7.isChecked()) {
                                            individual.setQ1002a_7("1");
                                        } else {
                                            individual.setQ1002a_7("2");
                                        }
                                        if (chka8.isChecked()) {
                                            individual.setQ1002a_8("1");
                                        } else {
                                            individual.setQ1002a_8("2");
                                        }
                                        if (chka10.isChecked()) {
                                            individual.setQ1002a_10("1");
                                        } else {
                                            individual.setQ1002a_10("2");
                                        }
                                        if (chka11.isChecked()) {
                                            individual.setQ1002a_11("1");
                                        } else {
                                            individual.setQ1002a_11("2");
                                        }
                                        if (chka12.isChecked()) {
                                            individual.setQ1002a_12("1");
                                        } else {
                                            individual.setQ1002a_12("2");
                                        }
                                        if (chka13.isChecked()) {
                                            individual.setQ1002a_13("1");
                                        } else {
                                            individual.setQ1002a_13("2");
                                        }
                                        if (chka14.isChecked()) {
                                            individual.setQ1002a_14("1");
                                        } else {
                                            individual.setQ1002a_14("2");
                                        }
                                        if (chka1.isChecked()) {
                                            individual.setQ1002a_15("1");
                                        } else {
                                            individual.setQ1002a_15("2");
                                        }
                                        if (chka16.isChecked()) {
                                            individual.setQ1002a_16("1");
                                        } else {
                                            individual.setQ1002a_16("2");
                                        }
                                        if (chka17.isChecked()) {
                                            individual.setQ1002a_17("1");
                                        } else {
                                            individual.setQ1002a_17("2");
                                        }
                                        if (chka18.isChecked()) {
                                            individual.setQ1002a_18("1");
                                           // individual.setQ1002a_18(edtOthertxt.getText().toString());
                                            individual.setQ1002a_18("1"+edtOthertxt.getText().toString());
                                        } else {
                                            individual.setQ1002a_18("2");
                                        }

                                        individual.setQ1002b(null);
                                        individual.setQ1002b_Other(null);

                                        /*
                                        individual.setQ1002a_1(chka1.getText().toString().substring(0, 1));
                                        individual.setQ1002a_2(chka2.getText().toString().substring(0, 1));
                                        individual.setQ1002a_3(chka3.getText().toString().substring(0, 1));
                                        individual.setQ1002a_3(chka4.getText().toString().substring(0, 1));
                                        individual.setQ1002a_4(chka5.getText().toString().substring(0, 1));
                                        individual.setQ1002a_6(chka6.getText().toString().substring(0, 1));
                                        individual.setQ1002a_7(chka7.getText().toString().substring(0, 1));
                                        individual.setQ1002a_8(chka8.getText().toString().substring(0, 1));
                                        individual.setQ1002a_10(chka10.getText().toString().substring(0, 1));
                                        individual.setQ1002a_11(chka11.getText().toString().substring(0, 1));
                                        individual.setQ1002a_12(chka12.getText().toString().substring(0, 1));
                                        individual.setQ1002a_13(chka13.getText().toString().substring(0, 1));
                                        individual.setQ1002a_14(chka14.getText().toString().substring(0, 1));
                                        individual.setQ1002a_15(chka15.getText().toString().substring(0, 1));
                                        individual.setQ1002a_16(chka16.getText().toString().substring(0, 1));
                                        individual.setQ1002a_17(chka17.getText().toString().substring(0, 1));
                                        individual.setQ1002a_18(chka18.getText().toString().substring(0, 1));
                                        individual.setQ1002a_Other(edtOthertxt.getText().toString());
*/
                                        //individual.setQ1002b(selectedRbtnb.getText().toString().substring(0, 1));
                                       // individual.setQ1002bOther(edtbOther.getText().toString());

                                        myDB.onOpen(myDB.getReadableDatabase());
                                        myDB.getWritableDatabase();
                                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                        myDB.close();


                                        Intent intent = new Intent(q1002.this, q1003.class);
                                        intent.putExtra("Individual", individual);
                                        startActivity(intent);


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
                Intent skipto1017 = new Intent(q1002.this, q1001.class);
                skipto1017.putExtra("Individual", individual);
                startActivity(skipto1017);
            }


        });
    }




    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q1002_1:
                if(checked)

                    chka1.setEnabled(true);
                chka2.setEnabled(true);
                chka3.setEnabled(true);
                chka4.setEnabled(true);
                chka5.setEnabled(true);

                chka6.setEnabled(true);
                chka7.setEnabled(true);
                chka8.setEnabled(true);
                chka10.setEnabled(true);
                chka11.setEnabled(true);
                chka12.setEnabled(true);
                chka13.setEnabled(true);
                chka14.setEnabled(true);
                chka15.setEnabled(true);
                chka16.setEnabled(true);
                chka17.setEnabled(true);
                chka18.setEnabled(true);
                chkaOther.setEnabled(true);

                rbtnb1.setEnabled(false);
                rbtnb2.setEnabled(false);
                rbtnb3.setEnabled(false);
                rbtnb4.setEnabled(false);
                rbtnb5.setEnabled(false);
                rbtnb6.setEnabled(false);
                rbtnbOther.setEnabled(false);
                edtOthertxt.setEnabled(true);

                rbtnb1.setChecked(false);
                rbtnb2.setChecked(false);
                rbtnb3.setChecked(false);
                rbtnb4.setChecked(false);
                rbtnb5.setChecked(false);
                rbtnb6.setChecked(false);
                rbtnbOther.setChecked(false);


                tb.setTextColor(Color.BLACK);









                break;

            case R.id.q1002_2:
                if(checked)

                    chka1.setEnabled(false);
                chka2.setEnabled(false);
                chka3.setEnabled(false);
                chka4.setEnabled(false);
                chka5.setEnabled(false);

                chka6.setEnabled(false);
                chka7.setEnabled(false);
                chka8.setEnabled(false);
                chka10.setEnabled(false);
                chka11.setEnabled(false);
                chka12.setEnabled(false);
                chka13.setEnabled(false);
                chka14.setEnabled(false);
                chka15.setEnabled(false);
                chka16.setEnabled(false);
                chka17.setEnabled(false);
                chka18.setEnabled(false);
                chkaOther.setEnabled(false);


                chka1.setChecked(false);
                chka2.setChecked(false);
                chka3.setChecked(false);
                chka4.setChecked(false);
                chka5.setChecked(false);
                chka6.setChecked(false);
                chka7.setChecked(false);
                chka8.setChecked(false);
                chka10.setChecked(false);
                chka11.setChecked(false);
                chka12.setChecked(false);
                chka13.setChecked(false);
                chka14.setChecked(false);
                chka15.setChecked(false);
                chka16.setChecked(false);
                chka17.setChecked(false);
                chka18.setChecked(false);
                chkaOther.setChecked(false);

                tb.setTextColor(Color.BLACK);
                ta.setTextColor(Color.BLACK);

                rbtnb1.setEnabled(true);
                rbtnb2.setEnabled(true);
                rbtnb3.setEnabled(true);
                rbtnb4.setEnabled(true);
                rbtnb5.setEnabled(true);
                rbtnb6.setEnabled(true);
                rbtnbOther.setEnabled(true);




                break;

            case R.id.q1002_9:
                if(checked)

                    chka1.setEnabled(false);
                chka2.setEnabled(false);
                chka3.setEnabled(false);
                chka4.setEnabled(false);
                chka5.setEnabled(false);
                chka6.setEnabled(false);
                chka7.setEnabled(false);
                chka8.setEnabled(false);
                chka10.setEnabled(false);
                chka11.setEnabled(false);
                chka12.setEnabled(false);
                chka13.setEnabled(false);
                chka14.setEnabled(false);
                chka15.setEnabled(false);
                chka16.setEnabled(false);
                chka17.setEnabled(false);
                chka18.setEnabled(false);
                chkaOther.setEnabled(false);

                chka1.setChecked(false);
                chka2.setChecked(false);
                chka3.setChecked(false);
                chka4.setChecked(false);
                chka5.setChecked(false);
                chka6.setChecked(false);
                chka7.setChecked(false);
                chka8.setChecked(false);
                chka10.setChecked(false);
                chka11.setChecked(false);
                chka12.setChecked(false);
                chka13.setChecked(false);
                chka14.setChecked(false);
                chka15.setChecked(false);
                chka16.setChecked(false);
                chka17.setChecked(false);
                chka18.setChecked(false);
                chkaOther.setChecked(false);
                edtOthertxt.setEnabled(false);

                rbtnb1.setEnabled(false);
                rbtnb2.setEnabled(false);
                rbtnb3.setEnabled(false);
                rbtnb4.setEnabled(false);
                rbtnb5.setEnabled(false);
                rbtnb6.setEnabled(false);
                rbtnbOther.setEnabled(false);
                edtOthertxt.setText("");
                edtOthertxt.setVisibility(View.INVISIBLE);


                rbtnb1.setChecked(false);
                rbtnb2.setChecked(false);
                rbtnb3.setChecked(false);
                rbtnb4.setChecked(false);
                rbtnb5.setChecked(false);
                rbtnb6.setChecked(false);
                rbtnbOther.setChecked(false);

                ta.setTextColor(Color.LTGRAY);
                tb.setTextColor(Color.LTGRAY);

                    break;
            case R.id.q1002b_other:
                if(checked) {
                    edtbOther.setVisibility(View.VISIBLE);
                }
                else
                    edtbOther.setVisibility(View.INVISIBLE);
                edtbOther.setText("");


break;
                    default:

                break;

        }

    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q1002a_Other:
                if(checked)
                {
                    edtOthertxt.setVisibility(View.VISIBLE);
                }

                    else

                    edtOthertxt.setVisibility(View.INVISIBLE);
                    edtOthertxt.setText("");





break;
            default:

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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q1002.this).toBundle());

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






/*
Intent intent = new Intent(q504.this, q1101.class);
                //intent.putExtra("Household", thisHose);
                startActivity(intent);
 */


