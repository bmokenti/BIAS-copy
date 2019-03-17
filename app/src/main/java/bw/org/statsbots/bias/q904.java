package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q904 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtna1, rbtna2, rbtna3, rbtna4, rbtna5,rbtnaOther , rbtnc1, rbtnc2, rbtnc3, rbtnc4, rbtnc5, rbtnc6, rbtnc7, rbtnc8, rbtnc10, rbtnc11,  rbtnc12, rbtnc13,rbtncOther  ;
    protected RadioGroup rg, rga, rgc;
    protected EditText edta, edtc, edtbmnths, edtbyear;
    protected TextView t1, t2, t3, t4;
    protected CheckBox chkb99, chkb9999;
    protected RadioButton selectedRbtn, selectedRbtna, selectedRbtnc;
    HouseHold thisHose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q904);



        setTitle("Q904: HIV SUPPORT, CARE ANF TREATMENT");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q904_1);
        rbtn2 = (RadioButton) findViewById(R.id.q904_2);
        rbtn3 = (RadioButton) findViewById(R.id.q904_3);
        rbtn4 = (RadioButton) findViewById(R.id.q904_4);

        chkb99 = (CheckBox) findViewById(R.id.q904b_99);
        chkb9999 = (CheckBox) findViewById(R.id.q904b_9999);

        edtbmnths = (EditText) findViewById(R.id.q904b_months);
        edtbyear = (EditText) findViewById(R.id.q904b_year);

        edta = (EditText) findViewById(R.id.q904a_other1);
        edtc = (EditText) findViewById(R.id.q904c_other1);



        rbtna1 = (RadioButton) findViewById(R.id.q904a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q904a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q904a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q904a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q904a_5);
        rbtnaOther = (RadioButton) findViewById(R.id.q904a_other);
        edta = (EditText) findViewById(R.id.q904a_other1);



        rbtnc1 = (RadioButton) findViewById(R.id.q904c_1);
        rbtnc2 = (RadioButton) findViewById(R.id.q904c_2);
        rbtnc3 = (RadioButton) findViewById(R.id.q904c_3);
        rbtnc4 = (RadioButton) findViewById(R.id.q904c_4);
        rbtnc5 = (RadioButton) findViewById(R.id.q904c_5);

        rbtnc6 = (RadioButton) findViewById(R.id.q904c_6);
        rbtnc7 = (RadioButton) findViewById(R.id.q904c_7);
        rbtnc8 = (RadioButton) findViewById(R.id.q904c_8);
        rbtnc10 = (RadioButton) findViewById(R.id.q904c_10);
        rbtnc11 = (RadioButton) findViewById(R.id.q904c_11);
        rbtnc12 = (RadioButton) findViewById(R.id.q904c_12);
        rbtnc13 = (RadioButton) findViewById(R.id.q904c_13);
        rbtncOther = (RadioButton) findViewById(R.id.q904c_other);
        edtc = (EditText) findViewById(R.id.q904c_other1);




        t1 = (TextView) findViewById(R.id.q904txt);
        t2 = (TextView) findViewById(R.id.q904atxt);
        t3 = (TextView) findViewById(R.id.q904btxt);
        t4 = (TextView) findViewById(R.id.q904ctxt);

        rg = (RadioGroup) findViewById(R.id.q904radioGroup);
        rga = (RadioGroup) findViewById(R.id.q904aradioGroup);
        rgc = (RadioGroup) findViewById(R.id.q904cradioGroup);


       // final int selectedId = rg.getCheckedRadioButtonId();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
      //  int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }

        }



        if( ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0")) || sample.getStatusCode().equals("3")
                || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") && p1.getP06().equals("2")))
                && (individual.getQ801() != null &&  individual.getQ801().equals("2") ) )
        {
            Intent intent = new Intent(q904.this, q1101.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

//        if((sample.getStatusCode().equals("3") || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0") )
//                || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") && p1.getP06().equals("2"))) &&
//                ( individual.getQ801().equals("1") && individual.getQ801f().equals("1")))
//        {
//            Intent intent = new Intent(q901.this, q904.class);
//            intent.putExtra("Individual", individual);
//            startActivity(intent);
//        }

        RadioButton[] bt = new RadioButton[4];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ904()!= null &&  !ind.getQ904().equals(""))
                {
                    if(Integer.parseInt(ind.getQ904())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bta = new RadioButton[6];
        for(int f=0;f<rga.getChildCount();f++)
        {
            View o = rga.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bta[f]=((RadioButton)o);
                if(ind.getQ904a()!= null &&  !ind.getQ904a().equals(""))
                {
                    if(Integer.parseInt(ind.getQ904a())==f+1)
                    {
                        RadioButton radioButton = bta[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        if( ind.getQ904aOther() != null)
        {
            edta.setText(ind.getQ904aOther());
        }



        if( ind.getQ904bMM() != null)
        {
            edtbmnths.setText(ind.getQ904bMM());
        }

        if( ind.getQ904bYYYY() != null)
        {
            edtbyear.setText(ind.getQ904bYYYY());
        }

        if( ind.getQ902Month() == "99")
        {
            chkb99.setChecked(true);
        }

        if( ind.getQ902Year()  == "9999")
        {
            chkb9999.setChecked(true);
        }


        RadioButton[] btc = new RadioButton[13];
        for(int f=0;f<rgc.getChildCount();f++)
        {
            View o = rgc.getChildAt(f);
            if (o instanceof RadioButton)
            {
                btc[f]=((RadioButton)o);
                if(ind.getQ904c()!= null &&  !ind.getQ904c().equals(""))
                {
                    if(Integer.parseInt(ind.getQ904c())==f+1)
                    {
                        RadioButton radioButton = btc[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        if( ind.getQ904cOther() != null)
        {
            edtc.setText(ind.getQ904cOther());
        }

        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q904.this, "Q904: Error ", "Are you currently taking ARVs?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);


                } else {

                    int selectedIda = rga.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedIda);

                    if (selectedRbtna == null && (rbtn2.isChecked() || rbtn1.isChecked())) {
                        lib.showError(q904.this, "Q904a: ERROR", "Where are you getting your ARVs?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    } else
                        {

                        //individual.setQ503(selectedRbtn.getText().toString().substring(0,1));

                        if (!chkb99.isChecked() && (rbtn1.isChecked() || rbtn2.isChecked()) &&
                                ((edtbmnths.length() == 0 || Integer.valueOf(edtbmnths.getText().toString()) >= 13))) {
                            lib.showError(q904.this, "Q904b:ERROR: Month", "What month and year did you first start taking ARVs?" +
                                    "Please provide month or select dont know month");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        }
                        else {

                            //individual.setQ503(selectedRbtn.getText().toString().substring(0,1));

                            if (!chkb9999.isChecked() && (rbtn1.isChecked() || rbtn2.isChecked()) && ((edtbyear.length() == 0 || Integer.valueOf(edtbyear.getText().toString()) >= 2020))) {
                                lib.showError(q904.this, "Q904b: ERROR: Year", "What month and year did you first start taking ARVs?" +
                                        "Please provide YEAR or select dont know year");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {

                                int selectedIdc = rgc.getCheckedRadioButtonId();

                                selectedRbtnc = (RadioButton) findViewById(selectedIdc);

                                if (selectedRbtnc == null &&  rbtn3.isChecked()) {
                                    lib.showError(q904.this, "Q904c: ERROR", "What is the MAIN reason you are not taking ARVs?");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);

                                } else {

                                    //********************************


                                    if (rbtn3.isChecked()) {
                                        // to include ea status code on the check

                                        individual.setQ904(selectedRbtn.getText().toString().substring(0, 1));
                                        individual.setQ904c(selectedRbtnc.getText().toString().substring(0, 1));
                                        individual.setQ904cOther(edtc.getText().toString());


                                        myDB.onOpen(myDB.getReadableDatabase());
                                        myDB.getWritableDatabase();
                                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                        myDB.close();


                                        Intent q1o2 = new Intent(q904.this, q905.class);
                                        q1o2.putExtra("Individual", individual);
                                        startActivity(q1o2);

                                    } else {

                                        //********************************


                                        if (rbtn4.isChecked()) {
                                            // to include ea status code on the check

                                            individual.setQ904(selectedRbtn.getText().toString().substring(0, 1));


                                            myDB.onOpen(myDB.getReadableDatabase());
                                            myDB.getWritableDatabase();
                                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                            myDB.close();
                                            Intent q1o2 = new Intent(q904.this, q1001.class);
                                            q1o2.putExtra("Individual", individual);
                                            startActivity(q1o2);

                                        }
                                        else {

                                            //********************************


                                            if (individual.getQ101().equals("1") || (individual.getQ101().equals("2") && Integer.parseInt(individual.getQ102())>49 )) {
                                                // to include ea status code on the check

                                                individual.setQ904(selectedRbtn.getText().toString().substring(0, 1));
                                                individual.setQ904a(selectedRbtna.getText().toString().substring(0, 1));
                                                individual.setQ904aOther(edta.getText().toString());
                                                individual.setQ904bMM(edtbmnths.getText().toString());
                                                individual.setQ904bYYYY(edtbyear.getText().toString());


                                                myDB.onOpen(myDB.getReadableDatabase());
                                                myDB.getWritableDatabase();
                                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                                myDB.close();
                                                Intent q1o2 = new Intent(q904.this, q1001.class);
                                                q1o2.putExtra("Individual", individual);
                                                startActivity(q1o2);

                                            }
                                        else {
                                            //Set q904 for the current individual
                                            individual.setQ904(selectedRbtn.getText().toString().substring(0, 1));
                                            individual.setQ904a(selectedRbtna.getText().toString().substring(0, 1));
                                            individual.setQ904aOther(edta.getText().toString());
                                            individual.setQ904bMM(edtbmnths.getText().toString());
                                            individual.setQ904bYYYY(edtbyear.getText().toString());

                                                myDB.onOpen(myDB.getReadableDatabase());
                                                myDB.getWritableDatabase();
                                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                                myDB.close();
                                            Intent intent = new Intent(q904.this, q905.class);
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

                if ((sample.getStatusCode().equals("3") || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0")) ||
                        (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") && Integer.valueOf(individual.getQ102()) > 64))
                        && individual.getQ801f().equals("1")) {

                    Intent intent = new Intent(q904.this, q801.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                    finish();
                } else {
finish();
                    q904.super.onBackPressed();

                }
            }


        });
    }




    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q904_1:
                if(checked)

                rbtna1.setEnabled(true);
                rbtna2.setEnabled(true);
                rbtna3.setEnabled(true);
                rbtna4.setEnabled(true);
                rbtna5.setEnabled(true);
                rbtnaOther.setEnabled(true);
                t1.setTextColor(Color.BLACK);

                edtbyear.setText("");
                edtbyear.setEnabled(true);
                chkb9999.setEnabled(true);


                chkb99.setEnabled(true);
                edtbmnths.setText("");
                edtbmnths.setEnabled(true);
                t3.setTextColor(Color.BLACK);

                rbtnc1.setEnabled(false);
                rbtnc1.setEnabled(false);
                rbtnc2.setEnabled(false);
                rbtnc3.setEnabled(false);
                rbtnc4.setEnabled(false);
                rbtnc5.setEnabled(false);
                rbtnc6.setEnabled(false);
                rbtnc7.setEnabled(false);
                rbtnc8.setEnabled(false);
                rbtnc10.setEnabled(false);
                rbtnc11.setEnabled(false);
                rbtnc12.setEnabled(false);
                rbtnc13.setEnabled(false);
                rbtncOther.setEnabled(false);
                t4.setTextColor(Color.LTGRAY);

                rbtnc1.setChecked(false);
                rbtnc1.setChecked(false);
                rbtnc2.setChecked(false);
                rbtnc3.setChecked(false);
                rbtnc4.setChecked(false);
                rbtnc5.setChecked(false);
                rbtnc6.setChecked(false);
                rbtnc7.setChecked(false);
                rbtnc8.setChecked(false);
                rbtnc10.setChecked(false);
                rbtnc11.setChecked(false);
                rbtnc12.setChecked(false);
                rbtnc13.setChecked(false);
                rbtncOther.setChecked(false);


                break;

            case R.id.q904_2:
                if(checked)

                rbtna1.setEnabled(true);
                rbtna2.setEnabled(true);
                rbtna3.setEnabled(true);
                rbtna4.setEnabled(true);
                rbtna5.setEnabled(true);
                rbtnaOther.setEnabled(true);
                t2.setTextColor(Color.BLACK);


                edtbyear.setText("");
                edtbyear.setEnabled(true);
                chkb9999.setEnabled(true);


                chkb99.setEnabled(true);
                edtbmnths.setText("");
                edtbmnths.setEnabled(true);
                t3.setTextColor(Color.BLACK);


                rbtnc1.setEnabled(false);
                rbtnc1.setEnabled(false);
                rbtnc2.setEnabled(false);
                rbtnc3.setEnabled(false);
                rbtnc4.setEnabled(false);
                rbtnc5.setEnabled(false);
                rbtnc6.setEnabled(false);
                rbtnc7.setEnabled(false);
                rbtnc8.setEnabled(false);
                rbtnc10.setEnabled(false);
                rbtnc11.setEnabled(false);
                rbtnc12.setEnabled(false);
                rbtnc13.setEnabled(false);
                rbtncOther.setEnabled(false);
                t4.setTextColor(Color.LTGRAY);

                rbtnc1.setChecked(false);
                rbtnc1.setChecked(false);
                rbtnc2.setChecked(false);
                rbtnc3.setChecked(false);
                rbtnc4.setChecked(false);
                rbtnc5.setChecked(false);
                rbtnc6.setChecked(false);
                rbtnc7.setChecked(false);
                rbtnc8.setChecked(false);
                rbtnc10.setChecked(false);
                rbtnc11.setChecked(false);
                rbtnc12.setChecked(false);
                rbtnc13.setChecked(false);
                rbtncOther.setChecked(false);

                break;

            case R.id.q904_3:
                if(checked)
                {

                rbtna1.setEnabled(false);
                rbtna2.setEnabled(false);
                rbtna3.setEnabled(false);
                rbtna4.setEnabled(false);
                rbtna5.setEnabled(false);
                rbtnaOther.setEnabled(false);
                t2.setTextColor(Color.LTGRAY);


                rbtna1.setChecked(false);
                rbtna2.setChecked(false);
                rbtna3.setChecked(false);
                rbtna4.setChecked(false);
                rbtna5.setChecked(false);
                rbtnaOther.setEnabled(false);

                    rbtnc1.setEnabled(true);
                    rbtnc1.setEnabled(true);
                    rbtnc2.setEnabled(true);
                    rbtnc3.setEnabled(true);
                    rbtnc4.setEnabled(true);
                    rbtnc5.setEnabled(true);
                    rbtnc6.setEnabled(true);
                    rbtnc7.setEnabled(true);
                    rbtnc8.setEnabled(true);
                    rbtnc10.setEnabled(true);
                    rbtnc11.setEnabled(true);
                    rbtnc12.setEnabled(true);
                    rbtnc13.setEnabled(true);
                    rbtncOther.setEnabled(true);
                    t4.setTextColor(Color.BLACK);



                    edtbyear.setText("");
                edtbyear.setEnabled(false);
                    chkb9999.setChecked(false);
                    chkb9999.setEnabled(false);
                    chkb99.setChecked(false);
                    chkb99.setEnabled(false);
                    edtbmnths.setText("");
                    edtbmnths.setEnabled(false);
                    t3.setTextColor(Color.LTGRAY);




            }
            // Put some meat on the sandwich
                else {

                edtbmnths.setEnabled(true);
                // Remove the meat
            }



                break;

            case R.id.q904_4:
                if(checked)
                {

                    rbtna1.setEnabled(false);
                rbtna1.setEnabled(false);
                rbtna2.setEnabled(false);
                rbtna3.setEnabled(false);
                rbtna4.setEnabled(false);
                rbtna5.setEnabled(false);
                rbtnaOther.setEnabled(false);
                t1.setTextColor(Color.LTGRAY);

                    rbtna1.setChecked(false);
                    rbtna2.setChecked(false);
                    rbtna3.setChecked(false);
                    rbtna4.setChecked(false);
                    rbtna5.setChecked(false);
                    rbtnaOther.setEnabled(false);


                    rbtnc1.setEnabled(false);
                    rbtnc1.setEnabled(false);
                    rbtnc2.setEnabled(false);
                    rbtnc3.setEnabled(false);
                    rbtnc4.setEnabled(false);
                    rbtnc5.setEnabled(false);
                    rbtnc6.setEnabled(false);
                    rbtnc7.setEnabled(false);
                    rbtnc8.setEnabled(false);
                    rbtnc10.setEnabled(false);
                    rbtnc11.setEnabled(false);
                    rbtnc12.setEnabled(false);
                    rbtnc13.setEnabled(false);
                    rbtncOther.setEnabled(false);
                    t4.setTextColor(Color.LTGRAY);

                    rbtnc1.setChecked(false);
                    rbtnc1.setChecked(false);
                    rbtnc2.setChecked(false);
                    rbtnc3.setChecked(false);
                    rbtnc4.setChecked(false);
                    rbtnc5.setChecked(false);
                    rbtnc6.setChecked(false);
                    rbtnc7.setChecked(false);
                    rbtnc8.setChecked(false);
                    rbtnc10.setChecked(false);
                    rbtnc11.setChecked(false);
                    rbtnc12.setChecked(false);
                    rbtnc13.setChecked(false);
                    rbtncOther.setChecked(false);




                    edtbyear.setText("");
                    edtbyear.setEnabled(false);
                    edtbmnths.setText("");
                    edtbmnths.setEnabled(false);
                    chkb9999.setChecked(false);
                    chkb9999.setEnabled(false);
                    chkb99.setChecked(false);
                    chkb99.setEnabled(false);
                    t3.setTextColor(Color.LTGRAY);


        }
        // Put some meat on the sandwich
                else {


            // Remove the meat
        }
        break;

            case R.id.q904a_other:
                if(checked)
                {

                    edta.setVisibility(View.VISIBLE);




                }
                // Put some meat on the sandwich
                else {

                    edta.setVisibility(View.INVISIBLE);

                    edta.setText("");
                    // Remove the meat
                }
                break;


            case R.id.q904c_other:
                if(checked)
                {




                    edtc.setVisibility(View.VISIBLE);




                }
                // Put some meat on the sandwich
                else {

                    edtc.setVisibility(View.INVISIBLE);

                    edtc.setText("");
                    // Remove the meat
                }
                break;


            default:

                break;

        }

    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q904b_99:
                if(checked) {

                    edtbmnths.setText("99");
                    edtbmnths.setEnabled(false);
                }

                else {
                    edtbmnths.setText("");
                    edtbmnths.setEnabled(true);
                }
                break;

            case R.id.q904b_9999:
                if(checked) {
                    edtbyear.setText("9999");
                    edtbyear.setEnabled(false);
                }
                else
                {
                    edtbyear.setText("");
                    edtbyear.setEnabled(true);
                }
                break;



            default:

                break;

        }

    }
}

