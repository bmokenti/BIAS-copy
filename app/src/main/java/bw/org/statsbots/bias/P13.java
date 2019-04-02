package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class P13 extends AppCompatActivity implements Serializable, View.OnClickListener {
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected RadioButton rbtn1,rbtn2,rbtn3, rbtn4, rbtn5, rbtn6, selected=null;
    protected RadioGroup rbtngroup;
    protected EditText edt;
    protected DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p13);
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();


        setTitle("P13 ECONOMIC ACTIVITY");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.P13_1);
        rbtn2 = (RadioButton) findViewById(R.id.P13_2);
        rbtn3 = (RadioButton) findViewById(R.id.P13_3);
        rbtn4 = (RadioButton) findViewById(R.id.P13_4);
        rbtn5 = (RadioButton) findViewById(R.id.P13_5);
        rbtn6 = (RadioButton) findViewById(R.id.P13_other);
        rbtngroup = (RadioGroup) findViewById(R.id.P13radioGroup);
        edt = (EditText) findViewById(R.id.P13_txtOther);

        rbtn1.setOnClickListener(this);
        rbtn2.setOnClickListener(this);
        rbtn3.setOnClickListener(this);
        rbtn4.setOnClickListener(this);
        rbtn5.setOnClickListener(this);
        rbtn6.setOnClickListener(this);

        final int selectedId = rbtngroup.getCheckedRadioButtonId();

        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;

//        thisHouse.setHIVTB40("1");

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), thisHouse.getAssignment_ID());

//        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
//        thisHous.get(0).getHIVTB40();

        List<PersonRoster> list = myDB.getdataHhP(thisHouse.getAssignment_ID(), thisHouse.getBatchNumber());
        thisHouse.setHouseHoldeMembers(list.toArray(thisHouse.getHouseHoldeMembers()));

        if (thisHouse.next != null) {
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];

        } else if (thisHouse.previous != null) {
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];

        }


//        if (thisHouse.next != null && (p1.getP13() != null || p1.getP13Other() != null)) {
//            RadioButton[] bt = new RadioButton[5];
//            for (int f = 0; f < rbtngroup.getChildCount(); f++) {
//
//                View o = rbtngroup.getChildAt(f);
//                if (o instanceof RadioButton) {
//                    bt[f] = ((RadioButton) o);
//
//                    if (p1.getP13Other() != null || !p1.getP13Other().equals("") && p1.getP13().equals("O") || p1.getP13().equals("o") ) {
//
//                      RadioButton radioButton = bt[5];
//                        rbtn6.setChecked(true);
//                        selected = radioButton;
//                        edt.setText(p1.getP13Other());
//                        break;
//
//                    }
//                    else
//                    {
//                        if(p1.getP13() != null || !p1.getP13().equals("") ) {
//                            if (Integer.parseInt(p1.getP13()) == f + 1) {
//                                RadioButton radioButton = bt[f];
//                                radioButton.setChecked(true);
//                                selected = radioButton;
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        }
        if (thisHouse.next != null && (p1.getP13() != null || p1.getP13Other() != null)) {
            RadioButton[] bt = new RadioButton[5];
            for (int f = 0; f < rbtngroup.getChildCount(); f++) {

                View o = rbtngroup.getChildAt(f);
                if (o instanceof RadioButton) {
                    bt[f] = ((RadioButton) o);
                    if (p1.getP13Other() != null) {
                        RadioButton radioButton = bt[5];
                        radioButton.setChecked(true);
                        selected = radioButton;
                        edt.setText(p1.getP13Other());
                        break;
                    } else {
                        if (p1.getP13() != null) {
                            if (f == 5) {
                                if (p1.getP13Other() != null) {

                                    RadioButton radioButton = bt[5];
                                    radioButton.setChecked(true);
                                    selected = radioButton;
                                    edt.setText(p1.getP13Other());
                                    break;

                                } else {
                                    if (Integer.parseInt(p1.getP13()) == f + 1) {

                                        RadioButton radioButton = bt[f];
                                        radioButton.setChecked(true);
                                        selected = radioButton;
                                        break;
                                    }
                                }

                            } else {
                                if (Integer.parseInt(p1.getP13()) == f + 1) {

                                    RadioButton radioButton = bt[f];
                                    radioButton.setChecked(true);
                                    selected = radioButton;
                                    break;
                                }
                            }

                        }
                    }
                }
            }
        }


    TextView textView = (TextView) findViewById(R.id.P13);
        String s = getResources().getString(R.string.P13);
        int t = s.indexOf("#");
        s = s.replace("#", "<b>" + p1.getP01() + "</b> ");

        textView.setText(Html.fromHtml(s));

        /**
         * NEXT Person BUTTON
         */
        Button btnNext = (Button) findViewById(R.id.button);
        Button btnPrev = (Button) findViewById(R.id.button3);
        String btnLabel = "";

        if (p1.getLineNumber() + 1 == thisHouse.getTotalPersons()) {
            btnLabel = "Next";
        } else {
            btnLabel = "Next > ";
        }

        /**
         * NEXT BUTTON
         */
        btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected == null) {
                    lib.showError(P13.this, "P13 Error", "Since" + p1.getP01() + "did not work for payment, profit or home use, what did he/she do?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    //Set P02 fir the current individual
                    thisHouse.getPersons()[p1.getLineNumber()].setP13(selected.getText().toString().substring(0, 1));
                    thisHouse.getPersons()[p1.getLineNumber()].setP13Other(edt.getText().toString());
                    //Restart the current activity for next individual

                    int total = thisHouse.getTotalPersons();


                    if (p1.getLineNumber() == thisHouse.getTotalPersons() - 1) {
                        //Next question P07
                        if ((sample.getStatusCode().equals("1") )|| (sample.getStatusCode().equals("2") && thisHouse.getHIVTB40().equals("1"))) {
                            //HIV ONLY or combined that is part of 40
                            myDB = new DatabaseHelper(P13.this);
                            myDB.onOpen(myDB.getWritableDatabase());

                            //UPDATE HOUSEHOLD
                            List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(), thisHouse.getBatchNumber());

                            if (ll.size() > 0) {
                                myDB.updateRoster(thisHouse, "P13", p1.getP13(), String.valueOf(p1.getSRNO()));
                                myDB.updateRoster(thisHouse, "P13Other", p1.getP13Other(), String.valueOf(p1.getSRNO()));
                                myDB.close();
                            }


                            Intent intent = new Intent(P13.this, P17.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);
                        } else if ((sample.getStatusCode().equals("2") && thisHouse.getHIVTB40().equals("0"))
                                || (sample.getStatusCode().equals("3"))) {
                            //TB ONLY
                            myDB = new DatabaseHelper(P13.this);
                            myDB.onOpen(myDB.getWritableDatabase());

                            //UPDATE HOUSEHOLD
                            List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(), thisHouse.getBatchNumber());
                            if (ll.size() > 0) {
                                myDB.updateRoster(thisHouse, "P13", p1.getP13(), String.valueOf(p1.getSRNO()));
                                myDB.updateRoster(thisHouse, "P13Other", p1.getP13Other(), String.valueOf(p1.getSRNO()));
                                myDB.close();
                            }

                            Intent intent = new Intent(P13.this, P18.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);
                        } else {

                            //HIV+TB
                            if ((sample.getStatusCode().equals("2") && thisHouse.getHIVTB40().equals("1"))) {
                                myDB = new DatabaseHelper(P13.this);
                                myDB.onOpen(myDB.getWritableDatabase());

                                //UPDATE HOUSEHOLD
                                List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(), thisHouse.getBatchNumber());
                                if (ll.size() > 0) {
                                    myDB.updateRoster(thisHouse, "P13", p1.getP13(), String.valueOf(p1.getSRNO()));
                                    myDB.updateRoster(thisHouse, "P13Other", p1.getP13Other(), String.valueOf(p1.getSRNO()));
                                    myDB.close();
                                }

                                Intent intent = new Intent(P13.this, P19.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent);
                            } else {
                                if ((sample.getStatusCode().equals("2") && thisHouse.getHIVTB40().equals("0")) || (sample.getStatusCode().equals("3"))) {
                                    //TB ONLY

                                    myDB = new DatabaseHelper(P13.this);
                                    myDB.onOpen(myDB.getWritableDatabase());

                                    //UPDATE HOUSEHOLD
                                    List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(), thisHouse.getBatchNumber());
                                    if (ll.size() > 0) {
                                        myDB.updateRoster(thisHouse, "P13", p1.getP13(), String.valueOf(p1.getSRNO()));
                                        myDB.updateRoster(thisHouse, "P13Other", p1.getP13Other(), String.valueOf(p1.getSRNO()));
                                        myDB.close();
                                    }
                                    thisHouse = myDB.getHouseForUpdate(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber()).get(0);



                                    Intent intent = new Intent(P13.this, P18.class);
                                    intent.putExtra("Household", thisHouse);
                                    startActivity(intent);

                                }
                            }
                        }
                    }else {
                                    //Restart the current activity for next individual
                                    thisHouse.next = String.valueOf(p1.getSRNO() + 1);
                                    myDB = new DatabaseHelper(P13.this);
                                    myDB.onOpen(myDB.getWritableDatabase());

                                    //UPDATE HOUSEHOLD
                                    List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(), thisHouse.getBatchNumber());
                                    if (ll.size() > 0) {
                                        myDB.updateRoster(thisHouse, "P13", p1.getP13(), String.valueOf(p1.getSRNO()));
                                        myDB.updateRoster(thisHouse, "P13Other", p1.getP13Other(), String.valueOf(p1.getSRNO()));
                                        myDB.close();
                                    }


                                    finish();
                                    Intent intent = new Intent(P13.this, P12.class);
                                    intent.putExtra("Household", thisHouse);
                                    startActivity(intent);
                                }

                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisHouse.previous = String.valueOf(p1.getSRNO());

                Log.d("P13 Prev", thisHouse.previous);
                finish();

                Intent intent = new Intent(P13.this, P12.class);
                intent.putExtra("Household", thisHouse);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.P13_1:
                selected = (RadioButton)findViewById(R.id.P13_1);
                break;
            case R.id.P13_2:
                selected = (RadioButton)findViewById(R.id.P13_2);
                break;
            case R.id.P13_3:
                selected = (RadioButton)findViewById(R.id.P13_3);
                break;
            case R.id.P13_4:
                selected = (RadioButton)findViewById(R.id.P13_4);
                break;
            case R.id.P13_5:
                selected = (RadioButton)findViewById(R.id.P13_5);
                break;
            case R.id.P13_other:
                selected = (RadioButton)findViewById(R.id.P13_other);
                break;
            default:
                break;
        }
    }
}





