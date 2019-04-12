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

public class q901 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, rbtna3, rbtna4, rbtna5, rbtna6, rbtna7, rbtna8, rbtna10, rbtna11, rbtnaOther, selected = null;
    protected RadioGroup rg, rga;
    protected EditText edt;
    protected TextView t1;
    protected RadioButton selectedRbtn, selectedRbtna;
    HouseHold thisHose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q901);


        setTitle("Q901: HIV SUPPORT, CARE ANF TREATMENT");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q901_1);
        rbtn2 = (RadioButton) findViewById(R.id.q901_2);
        rbtna1 = (RadioButton) findViewById(R.id.q901a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q901a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q901a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q901a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q901a_5);
        rbtna6 = (RadioButton) findViewById(R.id.q901a_6);
        rbtna7 = (RadioButton) findViewById(R.id.q901a_7);
        rbtna8 = (RadioButton) findViewById(R.id.q901a_8);
        rbtna10 = (RadioButton) findViewById(R.id.q901a_10);
        rbtna11 = (RadioButton) findViewById(R.id.q901a_11);
        rbtnaOther = (RadioButton) findViewById(R.id.q901a_other);
        edt = (EditText) findViewById(R.id.q901a_othertxt);
        t1 = (TextView) findViewById(R.id.q901aatxt);

        rg = (RadioGroup) findViewById(R.id.q901radioGroup);
        rga = (RadioGroup) findViewById(R.id.q901aradioGroupa);


        final int selectedId = rg.getCheckedRadioButtonId();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;


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



        //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());


        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();



        if((individual.getQ801f() != null && !(individual.getQ801f().equals("1")) ))
        {
            individual.setQ901(null);
            individual.setQ901a(null);
            individual.setQ901aOther(null);
            individual.setQ902Month("00");
            individual.setQ902Year("0000");
            individual.setQ903a(null);
            individual.setQ903b(null);
            individual.setQ903c(null);
            individual.setQ903d(null);
            individual.setQ903e(null);
            individual.setQ903f(null);
            individual.setQ903g(null);
            individual.setQ903h(null);

            individual.setQ904(null);
            individual.setQ904a(null);
            individual.setQ904aOther(null);
            individual.setQ904bMM("00");
            individual.setQ904bYYYY("0000");
            individual.setQ904c(null);
            individual.setQ904cOther(null);
            individual.setQ905(null);
            individual.setQ905a(null);
            individual.setQ905aOther(null);

            myDB = new DatabaseHelper(q901.this);
            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q901.this, q1001.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }
        else
            {

        }

        if((individual.getQ801f().equals("2") || individual.getQ801f().equals("3") ||individual.getQ801f().equals("4")
                || individual.getQ801f().equals("9"))&& individual.getQ101().equals("1")
       )
        {

            individual.setQ901(null);
            individual.setQ901a(null);
            individual.setQ901aOther(null);
            individual.setQ902Month("00");
            individual.setQ902Year("0000");
            individual.setQ903a(null);
            individual.setQ903b(null);
            individual.setQ903c(null);
            individual.setQ903d(null);
            individual.setQ903e(null);
            individual.setQ903f(null);
            individual.setQ903g(null);
            individual.setQ903h(null);

            individual.setQ904(null);
            individual.setQ904a(null);
            individual.setQ904aOther(null);
            individual.setQ904bMM("00");
            individual.setQ904bYYYY("0000");
            individual.setQ904c(null);
            individual.setQ904cOther(null);
            individual.setQ905(null);
            individual.setQ905a(null);
            individual.setQ905aOther(null);
            individual.setQ1001(null);


            individual.setQ1002(null);
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
            individual.setQ1002b(null);
            individual.setQ1002b_Other(null);
            individual.setQ1003(null);
            individual.setQ1004_Year("0000");
            individual.setQ1004_Month("00");
            individual.setQ1004_Day("00");
            individual.setQ1004a(null);
            individual.setQ1004a(null);
            individual.setQ1004b(null);
            individual.setQ1004b_Other(null);
            individual.setQ1005(null);
            individual.setQ1005a(null);
            individual.setQ1006(null);
            individual.setQ1007(null);
            individual.setQ1007a(null);
            individual.setQ1008(null);
            individual.setQ1008a(null);
            individual.setQ1008a_Other(null);
            individual.setQ1009(null);
            individual.setQ1009a(null);
            individual.setQ1010(null);
            individual.setQ1010_Other(null);
            individual.setQ1011(null);
            individual.setQ1011_Other(null);
            individual.setQ1012_Year("00");
            individual.setQ1012_Month("00");
            individual.setQ1012_Week("00");
            individual.setQ1013(null);
            individual.setQ1014(null);
            individual.setQ1014a(null);
            individual.setQ1014b(null);
            individual.setQ1015(null);
            individual.setQ1015a(null);
            individual.setQ1015b(null);
            individual.setQ1016(null);
            individual.setQ1017(null);

            myDB = new DatabaseHelper(q901.this);
            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q901.this, q1101.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }
        else {

        }

//

        if(( individual.getQ801().equals("1") && !(individual.getQ801f().equals("1"))) &&
                ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") )
         || sample.getStatusCode().equals("1")  ))

        {
            individual.setQ901(null);
            individual.setQ901a(null);
            individual.setQ901aOther(null);
            individual.setQ902Month("00");
            individual.setQ902Year("0000");
            individual.setQ903a(null);
            individual.setQ903b(null);
            individual.setQ903c(null);
            individual.setQ903d(null);
            individual.setQ903e(null);
            individual.setQ903f(null);
            individual.setQ903g(null);
            individual.setQ903h(null);

            individual.setQ904(null);
            individual.setQ904a(null);
            individual.setQ904aOther(null);
            individual.setQ904bMM("00");
            individual.setQ904bYYYY("0000");
            individual.setQ904c(null);
            individual.setQ904cOther(null);
            individual.setQ905(null);
            individual.setQ905a(null);
            individual.setQ905aOther(null);

//            individual.setQ1001(null);
//            individual.setQ1002(null);
//            individual.setQ1002a_1(null);
//            individual.setQ1002a_2(null);
//            individual.setQ1002a_3(null);
//            individual.setQ1002a_4(null);
//            individual.setQ1002a_5(null);
//            individual.setQ1002a_6(null);
//            individual.setQ1002a_7(null);
//            individual.setQ1002a_8(null);
//            // individual.setQ1002a_9(null);
//            individual.setQ1002a_10(null);
//            individual.setQ1002a_11(null);
//            individual.setQ1002a_12(null);
//            individual.setQ1002a_13(null);
//            individual.setQ1002a_14(null);
//            individual.setQ1002a_15(null);
//            individual.setQ1002a_16(null);
//            individual.setQ1002a_17(null);
//            individual.setQ1002a_18(null);
//            individual.setQ1002a_Other(null);
//            individual.setQ1002b(null);
//            individual.setQ1002b_Other(null);
//            individual.setQ1003(null);
//            individual.setQ1004_Year("00");
//            individual.setQ1004_Month("00");
//            individual.setQ1004_Day("00");
//            individual.setQ1004a(null);
//            individual.setQ1004a(null);
//            individual.setQ1004b(null);
//            individual.setQ1004b_Other(null);
//            individual.setQ1005(null);
//            individual.setQ1005a(null);
//            individual.setQ1006(null);
//            individual.setQ1007(null);
//            individual.setQ1007a(null);
//            individual.setQ1008(null);
//            individual.setQ1008a(null);
//            individual.setQ1008a_Other(null);
//            individual.setQ1009(null);
//            individual.setQ1009a(null);
//            individual.setQ1010(null);
//            individual.setQ1010_Other(null);
//            individual.setQ1011(null);
//            individual.setQ1011_Other(null);
//            individual.setQ1012_Year("00");
//            individual.setQ1012_Month("00");
//            individual.setQ1012_Week("00");
//            individual.setQ1013(null);
//            individual.setQ1014(null);
//            individual.setQ1014a(null);
//            individual.setQ1014b(null);
//            individual.setQ1015(null);
//            individual.setQ1015a(null);
//            individual.setQ1015b(null);
//            individual.setQ1016(null);
//            individual.setQ1017(null);

            myDB = new DatabaseHelper(q901.this);
            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q901.this, q1001.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }
        else {

        }


        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ901()!= null ) {
                    if (!ind.getQ901().equals("")) {
                        if (Integer.parseInt(ind.getQ901()) == f + 1) {
                            RadioButton radioButton = bt[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }

        if (ind.getQ901aOther() != null ) {

            if (ind.getQ901a() != null && ind.getQ901a().equals("O")) {
                rbtnaOther.setChecked(true);
                edt.setText(ind.getQ901aOther());
            }
        }
        else {

            RadioButton[] bta = new RadioButton[11];
            for (int f = 0; f < rga.getChildCount(); f++) {
                View o = rga.getChildAt(f);
                if (o instanceof RadioButton) {
                    bta[f] = ((RadioButton) o);
                    if (ind.getQ901a() != null ) {
                        if (!ind.getQ901a().equals("")) {
                            if (Integer.parseInt(ind.getQ901a()) == f + 1) {
                                RadioButton radioButton = bta[f];
                                radioButton.setChecked(true);
                                break;
                            }
                        }
                    }
                }
            }
        }

//
//        if( ind.getQ901aOther() != null)
//        {
//            edt.setText(ind.getQ901aOther());
//        }


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q901.this);
                    builder.setTitle("Q901: Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("After learning you had HIV, have you EVER received HIV medical care from a doctor, clinical officer or nurse?");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                    AlertDialog alertDialog = builder.show();
                    final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                    positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    positiveButton.setTextColor(Color.WHITE);
                    positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                    positiveButton.setLayoutParams(positiveButtonLL);


                } else {

                    int selectedIda = rga.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedIda);

                    if (selectedRbtna == null && rbtn2.isChecked()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q901.this);
                        builder.setTitle("Q901: Error");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("What is the MAIN reason why you have never received HIV medical care from a doctor, clinical officer, or nurse?");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                        AlertDialog alertDialog = builder.show();
                        final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                        positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        positiveButton.setTextColor(Color.WHITE);
                        positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                        positiveButton.setLayoutParams(positiveButtonLL);


                    }
                    else {

                        //individual.setQ503(selectedRbtn.getText().toString().substring(0,1));

                        if (rbtnaOther.isChecked() && edt.length() == 0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(q901.this);
                            builder.setTitle("Q901aOther: Error");
                            builder.setIcon(R.drawable.ic_warning_orange_24dp);

                            builder.setMessage("Please specify or deselect other specify option?");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });

                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);

                            AlertDialog alertDialog = builder.show();
                            final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                            LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                            positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                            positiveButton.setTextColor(Color.WHITE);
                            positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                            positiveButton.setLayoutParams(positiveButtonLL);
                        } else {

                            //individual.setQ503(selectedRbtn.getText().toString().substring(0,1));

                            if (rbtn2.isChecked() && (rbtna1.isChecked() || rbtna2.isChecked() || rbtna3.isChecked() || rbtna4.isChecked() || rbtna5.isChecked() || rbtna6.isChecked() || rbtna7.isChecked() || rbtna8.isChecked() || rbtna10.isChecked() || rbtna11.isChecked() || rbtnaOther.isChecked())) {
                                // to include ea status code on the check

                                individual.setQ901(selectedRbtn.getText().toString().substring(0, 1));
                                individual.setQ901a(selectedRbtna.getText().toString().substring(0, 1));
                                individual.setQ901a(edt.getText().toString());

                                individual.setQ901(null);
                                individual.setQ901a(null);
                                individual.setQ901aOther(null);
                                individual.setQ902Month("00");
                                individual.setQ902Year("0000");
                                individual.setQ903a(null);
                                individual.setQ903b(null);
                                individual.setQ903c(null);
                                individual.setQ903d(null);
                                individual.setQ903e(null);
                                individual.setQ903f(null);
                                individual.setQ903g(null);
                                individual.setQ903h(null);

                                individual.setQ904(null);
                                individual.setQ904a(null);
                                individual.setQ904aOther(null);
                                individual.setQ904bMM("00");
                                individual.setQ904bYYYY("0000");
                                individual.setQ904c(null);
                                individual.setQ904cOther(null);
                                individual.setQ905(null);
                                individual.setQ905a(null);
                                individual.setQ905aOther(null);

                                individual.setQ1001(null);
                                individual.setQ1002(null);
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
                                individual.setQ1002b(null);
                                individual.setQ1002b_Other(null);
                                individual.setQ1003(null);
                                individual.setQ1004_Year("00");
                                individual.setQ1004_Month("00");
                                individual.setQ1004_Day("00");
                                individual.setQ1004a(null);
                                individual.setQ1004a(null);
                                individual.setQ1004b(null);
                                individual.setQ1004b_Other(null);
                                individual.setQ1005(null);
                                individual.setQ1005a(null);
                                individual.setQ1006(null);
                                individual.setQ1007(null);
                                individual.setQ1007a(null);
                                individual.setQ1008(null);
                                individual.setQ1008a(null);
                                individual.setQ1008a_Other(null);
                                individual.setQ1009(null);
                                individual.setQ1009a(null);
                                individual.setQ1010(null);
                                individual.setQ1010_Other(null);
                                individual.setQ1011(null);
                                individual.setQ1011_Other(null);
                                individual.setQ1012_Year("00");
                                individual.setQ1012_Month("00");
                                individual.setQ1012_Week("00");
                                individual.setQ1013(null);
                                individual.setQ1014(null);
                                individual.setQ1014a(null);
                                individual.setQ1014b(null);
                                individual.setQ1015(null);
                                individual.setQ1015a(null);
                                individual.setQ1015b(null);
                                individual.setQ1016(null);
                                individual.setQ1017(null);

                                myDB = new DatabaseHelper(q901.this);
                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                myDB.close();

                                Intent q1o2 = new Intent(q901.this, q1001.class);
                                q1o2.putExtra("Individual", individual);
                                startActivity(q1o2);

                            } else {

                                //individual.setQ503(selectedRbtn.getText().toString().substring(0,1));

                                if (rbtn1.isChecked()) {
                                    // to include ea status code on the check
                                    individual.setQ901(selectedRbtn.getText().toString().substring(0, 1));
                                    individual.setQ901a(null);
                                    individual.setQ901aOther(null);

                                    myDB.onOpen(myDB.getReadableDatabase());
                                    myDB.getWritableDatabase();
                                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                    myDB.close();

                                    Intent q1o2 = new Intent(q901.this, q902.class);
                                    q1o2.putExtra("Individual", individual);
                                    startActivity(q1o2);

                                } else {
                                    //Set q101 for the current individual

                                    individual.setQ901(selectedRbtn.getText().toString().substring(0, 1));
                                    individual.setQ901a(selectedRbtna.getText().toString().substring(0, 1));
                                    if(rbtnaOther.isChecked())
                                    {
                                        individual.setQ901a(edt.getText().toString());
                                    }
                                    else
                                    {
                                        individual.setQ901a(null);
                                    }



                                    myDB.onOpen(myDB.getReadableDatabase());
                                    myDB.getWritableDatabase();
                                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                    myDB.close();

                                    Intent intent = new Intent(q901.this, q902.class);
                                    intent.putExtra("Individual", individual);
                                    startActivity(intent);

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
                if (individual.getQ803() != null ) {
                    finish();
                    Intent intent = new Intent(q901.this, q803.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                }  else {
                        if ((individual.getQ801a() != null && individual.getQ801a().equals("1")) && individual.getQ801f().equals("1")) {
                            finish();
                            Intent intent = new Intent(q901.this, q802.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);
                        }
                        else {
                            if (individual.getQ801() != null && individual.getQ801().equals("1")) {
                                finish();

                                Intent intent = new Intent(q901.this, q801.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);
                            }else
                            {
                            finish();
                                Intent intent = new Intent(q901.this, q804.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);
                        }

                    }
                }
            }

        });
    }


    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q901_1:
                if(checked)
                    rbtna1.setEnabled(false);
                    rbtna1.setEnabled(false);
                    rbtna2.setEnabled(false);
                    rbtna3.setEnabled(false);
                    rbtna4.setEnabled(false);
                    rbtna5.setEnabled(false);
                    rbtna6.setEnabled(false);
                    rbtna7.setEnabled(false);
                    rbtna8.setEnabled(false);
                    rbtna10.setEnabled(false);
                    rbtna11.setEnabled(false);
                    rbtnaOther.setEnabled(false);
                    t1.setTextColor(Color.LTGRAY);
                    rbtna1.setChecked(false);
                    rbtna1.setChecked(false);
                    rbtna2.setChecked(false);
                    rbtna3.setChecked(false);
                    rbtna4.setChecked(false);
                    rbtna5.setChecked(false);
                    rbtna6.setChecked(false);
                    rbtna7.setChecked(false);
                    rbtna8.setChecked(false);
                    rbtna10.setChecked(false);
                    rbtna11.setChecked(false);
                    rbtnaOther.setChecked(false);









                break;

            case R.id.q901_2:
                if(checked)
                    rbtna1.setEnabled(true);
                rbtna1.setEnabled(true);
                rbtna2.setEnabled(true);
                rbtna3.setEnabled(true);
                rbtna4.setEnabled(true);
                rbtna5.setEnabled(true);
                rbtna6.setEnabled(true);
                rbtna7.setEnabled(true);
                rbtna8.setEnabled(true);
                rbtna10.setEnabled(true);
                rbtna11.setEnabled(true);
                rbtnaOther.setEnabled(true);
                t1.setTextColor(Color.BLACK);
                break;

            case R.id.q901a_1:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;

            case R.id.q901a_2:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;

            case R.id.q901a_3:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;

            case R.id.q901a_4:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;
            case R.id.q901a_5:

                break;
            case R.id.q901a_6:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;

            case R.id.q901a_7:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;

            case R.id.q901a_8:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;

            case R.id.q901a_10:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;
            case R.id.q901a_11:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;
            case R.id.q901a_other:
                if(checked)
                    edt.setVisibility(View.VISIBLE);
                else


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
                alertDialogBuilder.setMessage("Are you sure you want to pause the interview");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(getApplicationContext(), started_household.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q901.this).toBundle());

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

