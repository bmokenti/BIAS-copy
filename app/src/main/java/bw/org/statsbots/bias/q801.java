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
import android.util.Log;
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

public class q801 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, rbtnb1, rbtnb2, rbtnb3, rbtnd1, rbtnd2, rbtnd3, rbtnd4, rbtnd5, rbtnd6, rbtnd7, rbtnd8, rbtnd10, rbtnd11, rbtnd12, rbtndOther, rbtne1,
            rbtne2, rbtne3, rbtne4, rbtne5, rbtne6, rbtne7, rbtne8, rbtne9, rbtneOther, rbtnf1, rbtnf2, rbtnf3, rbtnf4, rbtnf5;
    protected RadioGroup rg, rga, rgb, rgc, rgd, rge, rgf;
    protected EditText edtcmnths, edtcyear, edtdother, edteother;
    protected TextView t801a, t801b, t801c, t801d, t801e, t801f;
    protected CheckBox chkc99, chkc9999;
    protected DatabaseHelper myDB;
    protected RadioButton selectedRbtn, selectedRbtnb, selectedRbtnd, selectedRbtne, selectedRbtnf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q801);


        setTitle("Q801: HIV TESTING(15-64 Years");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q801_1);
        rbtn2 = (RadioButton) findViewById(R.id.q801_2);
        rbtna1 = (RadioButton) findViewById(R.id.q801a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q801a_2);

        rbtnb1 = (RadioButton) findViewById(R.id.q801b_1);
        rbtnb2 = (RadioButton) findViewById(R.id.q801b_2);
        rbtnb3 = (RadioButton) findViewById(R.id.q801b_3);

        rbtnd1 = (RadioButton) findViewById(R.id.q801d_1);
        rbtnd2 = (RadioButton) findViewById(R.id.q801d_2);
        rbtnd3 = (RadioButton) findViewById(R.id.q801d_3);
        rbtnd4 = (RadioButton) findViewById(R.id.q801d_4);
        rbtnd5 = (RadioButton) findViewById(R.id.q801d_5);
        rbtnd6 = (RadioButton) findViewById(R.id.q801d_6);
        rbtnd7 = (RadioButton) findViewById(R.id.q801d_7);
        rbtnd8 = (RadioButton) findViewById(R.id.q801d_8);
        rbtnd10 = (RadioButton) findViewById(R.id.q801d_10);
        rbtnd11 = (RadioButton) findViewById(R.id.q801d_11);
        rbtnd12 = (RadioButton) findViewById(R.id.q801d_9);
        rbtndOther = (RadioButton) findViewById(R.id.q801d_other);

        rbtne1 = (RadioButton) findViewById(R.id.q801e_1);
        rbtne2 = (RadioButton) findViewById(R.id.q801e_2);
        rbtne3 = (RadioButton) findViewById(R.id.q801e_3);
        rbtne4 = (RadioButton) findViewById(R.id.q801e_4);
        rbtne5 = (RadioButton) findViewById(R.id.q801e_5);
        rbtne6 = (RadioButton) findViewById(R.id.q801e_6);
        rbtne7 = (RadioButton) findViewById(R.id.q801e_7);
        rbtne8 = (RadioButton) findViewById(R.id.q801e_8);
        rbtne9 = (RadioButton) findViewById(R.id.q801e_9);
        rbtneOther = (RadioButton) findViewById(R.id.q801e_other);

        rbtnf1 = (RadioButton) findViewById(R.id.q801f_1);
        rbtnf2 = (RadioButton) findViewById(R.id.q801f_2);
        rbtnf3 = (RadioButton) findViewById(R.id.q801f_3);
        rbtnf4 = (RadioButton) findViewById(R.id.q801f_4);
        rbtnf5 = (RadioButton) findViewById(R.id.q801f_9);

        chkc99 = (CheckBox) findViewById(R.id.q801c_99);
        chkc9999 = (CheckBox) findViewById(R.id.q801c_9999);

        edtcmnths = (EditText) findViewById(R.id.q801c_months);
        edtcyear = (EditText) findViewById(R.id.q801c_year);
        edtdother = (EditText) findViewById(R.id.q801d_other1);
        edteother = (EditText) findViewById(R.id.q801e_other1);


        t801a = (TextView) findViewById(R.id.q801atxt);
        t801b = (TextView) findViewById(R.id.q801btxt);
        t801c = (TextView) findViewById(R.id.q801ctxt);
        t801d = (TextView) findViewById(R.id.q801dtxt);
        t801e = (TextView) findViewById(R.id.q801etxt);
        t801f = (TextView) findViewById(R.id.q801ftxt);


        rg = (RadioGroup) findViewById(R.id.q801radioGroup);
        rga = (RadioGroup) findViewById(R.id.q801aradioGroup);
        rgb = (RadioGroup) findViewById(R.id.q801bradioGroup);
        rgd = (RadioGroup) findViewById(R.id.q801dradioGroup);
        rge = (RadioGroup) findViewById(R.id.q801eradioGroup);
        rgf = (RadioGroup) findViewById(R.id.q801fradioGroup);

        final int selectedId = rg.getCheckedRadioButtonId();



        Intent i = getIntent();
        individual =(Individual) i.getSerializableExtra("Individual");
        //  int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;


        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);


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

        if(Integer.valueOf(individual.getQ102()) >=65 &&
                (sample.getStatusCode().equals("3") ||
                        (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1"))
                        || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0"))))
        {

            Intent intent = new Intent(q801.this, Q801Tb.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

        if((sample.getStatusCode().equals("3") || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") && p1.getP06() != null && p1.getP06().equals("2"))
                        || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0"))))
        {

            Intent intent = new Intent(q801.this, Q801Tb.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }




        rgd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q801d_other)
                {
                    // is checked
                    edtdother.setVisibility(View.VISIBLE);
                }
                else
                {
                    // not checked
                    edtdother.setVisibility(View.INVISIBLE);
                    edtdother.setText("");
                }
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q801_1  )
                {

                    rbtna1.setEnabled(true);
                    rbtna2.setEnabled(true);
                    rbtnb1.setEnabled(true);

                    rbtnb2.setEnabled(true);
                    rbtnb3.setEnabled(true);

                    edtcmnths.setEnabled(true);
                    edtcyear.setEnabled(true);
                    chkc99.setEnabled(true);
                    chkc9999.setEnabled(true);

                    rbtnd1.setEnabled(true);
                    rbtnd2.setEnabled(true);
                    rbtnd3.setEnabled(true);
                    rbtnd4.setEnabled(true);
                    rbtnd5.setEnabled(true);
                    rbtnd6.setEnabled(true);
                    rbtnd7.setEnabled(true);
                    rbtnd8.setEnabled(true);
                    rbtnd10.setEnabled(true);
                    rbtnd11.setEnabled(true);
                    rbtnd12.setEnabled(true);
                    rbtndOther.setEnabled(true);


                    rbtne1.setEnabled(true);
                    rbtne2.setEnabled(true);
                    rbtne3.setEnabled(true);
                    rbtne4.setEnabled(true);
                    rbtne5.setEnabled(true);
                    rbtne6.setEnabled(true);
                    rbtne7.setEnabled(true);
                    rbtne8.setEnabled(true);
                    rbtne9.setEnabled(true);
                    rbtneOther.setEnabled(true);


                    rbtnf1.setEnabled(true);
                    rbtnf2.setEnabled(true);
                    rbtnf3.setEnabled(true);
                    rbtnf4.setEnabled(true);
                    rbtnf5.setEnabled(true);


                    t801a.setTextColor(Color.BLACK);
                    t801b.setTextColor(Color.BLACK);
                    t801c.setTextColor(Color.BLACK);
                    t801d.setTextColor(Color.BLACK);
                    t801e.setTextColor(Color.BLACK);
                    t801f.setTextColor(Color.BLACK);
                }

                    else {
                        rbtna1.setEnabled(false);
                        rbtna2.setEnabled(false);
                        rbtna1.setChecked(false);
                        rbtna2.setChecked(false);

// set b controls.
                        rbtnb1.setEnabled(false);
                        rbtnb2.setEnabled(false);
                        rbtnb3.setEnabled(false);

                        rbtnb1.setChecked(false);
                        rbtnb2.setChecked(false);
                        rbtnb3.setChecked(false);
                        //set c controls
                        edtcmnths.setEnabled(false);
                        edtcyear.setEnabled(false);
                        chkc99.setEnabled(false);
                        chkc9999.setEnabled(false);

                        edtcmnths.setText("");
                        edtcyear.setText("");
                        chkc99.setChecked(false);
                        chkc9999.setChecked(false);

// set d controls
                        rbtnd1.setEnabled(false);
                        rbtnd2.setEnabled(false);
                        rbtnd3.setEnabled(false);
                        rbtnd4.setEnabled(false);
                        rbtnd5.setEnabled(false);
                        rbtnd6.setEnabled(false);
                        rbtnd7.setEnabled(false);
                        rbtnd8.setEnabled(false);
                        rbtnd10.setEnabled(false);
                        rbtnd11.setEnabled(false);
                        rbtnd12.setEnabled(false);
                        rbtndOther.setEnabled(false);

                        rbtnd1.setChecked(false);
                        rbtnd2.setChecked(false);
                        rbtnd3.setChecked(false);
                        rbtnd4.setChecked(false);
                        rbtnd5.setChecked(false);
                        rbtnd6.setChecked(false);
                        rbtnd7.setChecked(false);
                        rbtnd8.setChecked(false);
                        rbtnd10.setChecked(false);
                        rbtnd11.setChecked(false);
                        rbtnd12.setChecked(false);
                        rbtndOther.setChecked(false);

// set e controls
                        rbtne1.setEnabled(false);
                        rbtne2.setEnabled(false);
                        rbtne3.setEnabled(false);
                        rbtne4.setEnabled(false);
                        rbtne5.setEnabled(false);
                        rbtne6.setEnabled(false);
                        rbtne7.setEnabled(false);
                        rbtne8.setEnabled(false);
                        rbtne9.setEnabled(false);
                        rbtneOther.setEnabled(false);

                        rbtne1.setChecked(false);
                        rbtne2.setChecked(false);
                        rbtne3.setChecked(false);
                        rbtne4.setChecked(false);
                        rbtne5.setChecked(false);
                        rbtne6.setChecked(false);
                        rbtne7.setChecked(false);
                        rbtne8.setChecked(false);
                        rbtne9.setChecked(false);
                        rbtneOther.setChecked(false);

//set f controls
                        rbtnf1.setEnabled(false);
                        rbtnf2.setEnabled(false);
                        rbtnf3.setEnabled(false);
                        rbtnf4.setEnabled(false);
                        rbtnf5.setEnabled(false);

                        rbtnf1.setChecked(false);
                        rbtnf2.setChecked(false);
                        rbtnf3.setChecked(false);
                        rbtnf4.setChecked(false);
                        rbtnf5.setChecked(false);

                        t801a.setTextColor(Color.LTGRAY);
                        t801b.setTextColor(Color.LTGRAY);
                        t801c.setTextColor(Color.LTGRAY);
                        t801d.setTextColor(Color.LTGRAY);
                        t801e.setTextColor(Color.LTGRAY);
                        t801f.setTextColor(Color.LTGRAY);
                    }
                }

        });







        rga.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q801a_1  )
                {
                    rbtnb1.setEnabled(true);
                    rbtnb2.setEnabled(true);
                    rbtnb3.setEnabled(true);
                    t801b.setTextColor(Color.BLACK);
                }
                else {
                        rbtnb1.setEnabled(false);
                        rbtnb2.setEnabled(false);
                        rbtnb3.setEnabled(false);
                        t801b.setTextColor(Color.LTGRAY);


                        rbtnb1.setChecked(false);
                        rbtnb2.setChecked(false);
                        rbtnb3.setChecked(false);
                    }
                }

        });


        rge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q801e_other)
                {
                    // is checked
                    edteother.setVisibility(View.VISIBLE);
                }
                else
                {
                    // not checked
                    edteother.setVisibility(View.INVISIBLE);
                    edteother.setText("");
                }
            }
        });


        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton) {
                bt[f] = ((RadioButton) o);
                if (ind.getQ801() != null) {
                    if (!ind.getQ801().equals("")) {
                        if (Integer.parseInt(ind.getQ801()) == f + 1) {
                            RadioButton radioButton = bt[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }


        RadioButton[] bta = new RadioButton[2];
        for(int f=0;f<rga.getChildCount();f++)
        {
            View o = rga.getChildAt(f);
            if (o instanceof RadioButton) {
                bta[f] = ((RadioButton) o);
                if (ind.getQ801a() != null) {
                    if (!ind.getQ801a().equals("")) {
                        if (Integer.parseInt(ind.getQ801a()) == f + 1) {
                            RadioButton radioButton = bta[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }

        RadioButton[] btb = new RadioButton[3];
        for(int f=0;f<rgb.getChildCount();f++)
        {
            View o = rgb.getChildAt(f);
            if (o instanceof RadioButton)
            {
                btb[f]=((RadioButton)o);
                if(ind.getQ801b()!= null ) {
                    if (!ind.getQ801b().equals("")) {
                        if (Integer.parseInt(ind.getQ801b()) == f + 1) {
                            RadioButton radioButton = btb[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }

        if(ind.getQ801cMonth()!= null)

            {
                edtcmnths.setText(ind.getQ801cMonth());
            }




        if(ind.getQ801cYear()!= null)
        {
            edtcyear.setText(ind.getQ801cYear());
        }

        if(ind.getQ801cMonth() == "99")
        {
            chkc99.setChecked(true);
        }
        if(ind.getQ801cYear() == "9999")
        {
            chkc9999.setChecked(true);
        }


        if (ind.getQ801dOther() != null ) {

            if (ind.getQ801d() != null && ind.getQ801d().equals("O")) {
                rbtndOther.setChecked(true);
                edtdother.setText(ind.getQ801dOther());
            }
        }
        else
        {
        RadioButton[] btd = new RadioButton[12];
        for(int f=0;f<rgd.getChildCount();f++) {
            View o = rgd.getChildAt(f);
            if (o instanceof RadioButton) {
                btd[f] = ((RadioButton) o);

                if (ind.getQ801d() != null ) {
                    if (!ind.getQ801d().equals("")) {
                        if (Integer.parseInt(ind.getQ801d()) == f + 1) {
                            RadioButton radioButton = btd[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }
        }


        if (ind.getQ801eOther() != null ) {

            if (ind.getQ801e() != null && ind.getQ801e().equals("O")) {
                rbtneOther.setChecked(true);
                edteother.setText(ind.getQ801eOther());
            }
        }
        else
        {
        RadioButton[] bte = new RadioButton[10];
        for(int f=0;f<rge.getChildCount();f++)
        {
            View o = rge.getChildAt(f);
            if (o instanceof RadioButton) {
                bte[f] = ((RadioButton) o);

                if (ind.getQ801e() != null ) {
                    if (!ind.getQ801e().equals("")) {
                        if (Integer.parseInt(ind.getQ801e()) == f + 1) {
                            RadioButton radioButton = bte[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
            }
        }

        RadioButton[] btf = new RadioButton[5];
        for(int f=0;f<rgf.getChildCount();f++)
        {
            View o = rgf.getChildAt(f);
            if (o instanceof RadioButton) {
                btf[f] = ((RadioButton) o);
                if (ind.getQ801f() != null) {
                    if (!ind.getQ801f().equals("")) {
                        if (f == 4) {
                            rbtnf5.setChecked(true);
                        }
                        if (Integer.parseInt(ind.getQ801f()) == f + 1) {
                            RadioButton radioButton = btf[f];
                            radioButton.setChecked(true);
                            break;
                        }
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
                    lib.showError(q801.this, "Q801: Error ", " Have you ever been tested for HIV?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);


                } else {

                    int selectedIda = rga.getCheckedRadioButtonId();
                    final RadioButton selectedRbtna = (RadioButton) findViewById(selectedIda);


                    if (selectedRbtna == null && rbtn1.isChecked()) {
                        lib.showError(q801.this, "Q801a: ERROR", "Have you tested for HIV in the past 12 months?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    } else {

                        int selectedIdb = rgb.getCheckedRadioButtonId();
                        selectedRbtnb = (RadioButton) findViewById(selectedIdb);

                        if (selectedRbtnb == null && rbtna1.isChecked() && (sample.getStatusCode().equals("1")  ||
                                (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")
                                        && Integer.valueOf(individual.getQ102()) <= 64) )) {

                            lib.showError(q801.this, "Q801b: ERROR", "Did you test together with your partner in the past 12 months?");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);

                        } else {


                            if (edtcmnths.length() == 0 && !chkc99.isChecked() && rbtn1.isChecked()) {
                                lib.showError(q801.this, "Q801c: ERROR: month", "What month and year was your last HIV Test?, " +
                                        "Please provide month or select Dont know month");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);

                            } else {
                                if (edtcyear.length() == 0 && !chkc9999.isChecked() && rbtn1.isChecked()) {
                                    lib.showError(q801.this, "Q801c: ERROR: year", "What month and year was your last HIV Test?, " +
                                            "Please provide year or select Dont know year");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);

                                } else {

                                    int selectedIdd = rgd.getCheckedRadioButtonId();
                                    selectedRbtnd = (RadioButton) findViewById(selectedIdd);

                                    if (selectedRbtnd == null && rbtn1.isChecked() && (sample.getStatusCode().equals("1")  || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")
                                            && Integer.valueOf(individual.getQ102()) <= 64)))
                                    {
                                        lib.showError(q801.this, "Q801d: ERROR", "What was the MAIN reason for your last HIV test?");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);

                                    } else {

                                        //check other radiobutton

                                        if (rbtndOther.isChecked() && edtdother.length() == 0) {
                                            lib.showError(q801.this, "Q801d: ERROR: Other", "Please specify");
                                            /**
                                             * VIBRATE DEVICE
                                             */
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);

                                        } else {

                                            int selectedIde = rge.getCheckedRadioButtonId();
                                            selectedRbtne = (RadioButton) findViewById(selectedIde);

                                            if (selectedRbtne == null && rbtn1.isChecked() &&  (sample.getStatusCode().equals("1")  ||
                                                    (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")
                                                            && Integer.valueOf(individual.getQ102()) <= 64) )) {
                                                lib.showError(q801.this, "Q801e: ERROR", "Where was the LAST test done?");
                                                /**
                                                 * VIBRATE DEVICE
                                                 */
                                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                vibs.vibrate(100);

                                            } else {

                                                //check other radiobutton

                                                if (rbtneOther.isChecked() && edteother.length() == 0) {
                                                    lib.showError(q801.this, "Q801d: ERROR: Other", "Please specify");
                                                    /**
                                                     * VIBRATE DEVICE
                                                     */
                                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                    vibs.vibrate(100);

                                                } else {

                                                    int selectedIdf = rgf.getCheckedRadioButtonId();
                                                    selectedRbtnf = (RadioButton) findViewById(selectedIdf);
                                                    //   Log.d("results", selectedRbtnf.getText().toString().substring(0, 1));

                                                    if (selectedRbtnf == null && (rbtn1.isChecked())) {
                                                        lib.showError(q801.this, "Q801f: ERROR", "What was the result of your last HIV test?");
                                                        /**
                                                         * VIBRATE DEVICE
                                                         */
                                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                        vibs.vibrate(100);

                                                    } else {
                                                        //if no and is combined  or HIV only
                                                        if (rbtn2.isChecked()) {

                                                            individual.setQ801(selectedRbtn.getText().toString().substring(0, 1));
                                                            individual.setQ801a(null);
                                                            individual.setQ801b(null);
                                                            individual.setQ801cMonth("00");
                                                            individual.setQ801cYear("0000");
                                                            individual.setQ801d(null);
                                                            individual.setQ801dOther(null);
                                                            individual.setQ801e(null);
                                                            individual.setQ801eOther(null);
                                                            individual.setQ801f(null);

                                                            myDB = new DatabaseHelper(q801.this);
                                                            myDB.onOpen(myDB.getReadableDatabase());
                                                            myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                                            myDB.close();

                                                            Intent intent = new Intent(q801.this, q804.class);
                                                            intent.putExtra("Individual", individual);
                                                            startActivity(intent);

                                                        } else {
                                                                    //***************************************************


                                                                        individual.setQ801(selectedRbtn.getText().toString().substring(0, 1));
                                                                        individual.setQ801a(selectedRbtna.getText().toString().substring(0, 1));
                                                                        if(rbtna1.isChecked()) {
                                                                            individual.setQ801b(selectedRbtnb.getText().toString().substring(0, 1));
                                                                        }else
                                                                            {
                                                                                individual.setQ801b(null);
                                                                        }


                                                                        if (edtcmnths.getText().toString().length() == 0) {
                                                                            individual.setQ801cMonth("00");
                                                                        } else if (edtcmnths.getText().toString().length() == 1) {
                                                                            individual.setQ801cMonth("0" + edtcmnths.getText().toString());
                                                                        } else {
                                                                            individual.setQ801cMonth(edtcmnths.getText().toString());
                                                                        }


                                                                        if (edtcyear.getText().toString().length() == 0) {
                                                                            individual.setQ801cYear("0000");
                                                                        } else if (edtcyear.getText().toString().length() == 2) {
                                                                            individual.setQ801cYear("00" + edtcyear.getText().toString());
                                                                        } else {
                                                                            individual.setQ801cYear(edtcyear.getText().toString());
                                                                        }


                                                                        individual.setQ801d(selectedRbtnd.getText().toString().substring(0, 1));
                                                                        if(rbtndOther.isChecked()) {
                                                                            individual.setQ801dOther(edtdother.getText().toString());
                                                                        }
                                                                        else {
                                                                            individual.setQ801dOther(null);
                                                                        }
                                                                        individual.setQ801e(selectedRbtne.getText().toString().substring(0, 1));
                                                                        if(rbtneOther.isChecked()) {
                                                                            individual.setQ801eOther(edteother.getText().toString());
                                                                        }
                                                                        else
                                                                        {
                                                                            individual.setQ801eOther(null);
                                                                        }
                                                                        individual.setQ801f(selectedRbtnf.getText().toString().substring(0, 1));
                                                                        // Log.d("results", selectedRbtnf.getText().toString().substring(0, 1));
                                                                        // Log.d("result", individual.getQ801f());

                                                                        myDB = new DatabaseHelper(q801.this);
                                                                        myDB.onOpen(myDB.getReadableDatabase());
                                                            myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                                                        myDB.updateInd("Q801f", individual.getAssignmentID(), individual.getBatch(), individual.getQ801f(), String.valueOf(individual.getSRNO()));

                                                                        Intent intent = new Intent(q801.this, q802.class);
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
            }
        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(q801.this, q705.class);
                intent.putExtra("Individual", individual);
                startActivity(intent);
            }


        });
    }


    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q801_1:
                if (checked ) {


                } else {

                }
                break;

            case R.id.q801_2:
                if (checked) {
                    // set a controls

                }
                else
                {

                }

                break;

            case R.id.q801a_1:
                if (checked) {

                }
                // Put some meat on the sandwich
                else {


                }
                break;

            case R.id.q801a_2:
                if (checked) {

                }
                // Put some meat on the sandwich
                else {
                    // Remove the meat
                }
                break;

            case R.id.q801d_other:
                if (checked) {
                    //edtdother.setVisibility(View.VISIBLE);
                }
                // Put some meat on the sandwich
                else {
                    //edtdother.setVisibility(View.INVISIBLE);
                    //  edtdother.setText("");

                }
                break;

            case R.id.q801e_other:
                if (checked) {
                    //  edteother.setVisibility(View.VISIBLE);
                }
                // Put some meat on the sandwich
                else {
                    // edteother.setVisibility(View.INVISIBLE);
                    // edteother.setText("");
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

            case R.id.q801c_99:
                if (checked) {
                    edtcmnths.setEnabled(false);
                    edtcmnths.setText("99");




                } else {
                    edtcmnths.setEnabled(true);
                    edtcmnths.setText("");
                }
                break;
            case R.id.q801c_9999:
                if (checked) {

                    edtcyear.setEnabled(false);



                    edtcyear.setText("9999");


                } else {
                    edtcyear.setEnabled(true);



                    edtcyear.setText("");
                }

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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q801.this).toBundle());

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
