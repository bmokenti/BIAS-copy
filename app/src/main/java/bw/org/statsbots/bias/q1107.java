package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
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

public class q1107 extends AppCompatActivity implements  Serializable {

    protected RadioButton rbtn1, rbtn2, rb3, selectedRbtn;
    protected RadioGroup rg1;
    protected EditText txtweeks, txtdays;
    protected TextView txtq1107aq, txt1107dd, txt1107wks;
    protected LinearLayout viewa;
    protected Individual individual;
    PersonRoster p1 = null;protected DatabaseHelper myDB;
    protected  HouseHold thisHouse;
    protected CheckBox chk1107a, selectedchk;
    protected LibraryClass lib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1107);
        Button btnnext = findViewById(R.id.btnNext);
        setTitle("Q1107:");

        rg1 = findViewById(R.id.q1107radiogroup);
        rbtn1 = findViewById(R.id.q1107_y);
        rbtn2 = findViewById(R.id.q1107_n);
        txtdays = findViewById(R.id.txtdd);
        txtweeks = findViewById(R.id.txtwks);
        txtq1107aq = findViewById(R.id.q1107aques);
        viewa = findViewById(R.id.q1107alinear);
        chk1107a = findViewById(R.id.q1107a_99);
        lib = new LibraryClass();
        txt1107dd = findViewById(R.id.dd);
        txt1107wks = findViewById(R.id.tvwks);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;
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

        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg1.getChildCount();f++)
        {
            View o = rg1.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ1107()!= null &&  !ind.getQ1107().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1107())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

if (individual.getQ1107() != null && individual.getQ1107().equals("2"))
{
    txtq1107aq.setTextColor(Color.LTGRAY);
                                    txtweeks.setEnabled(false);
                                    txtdays.setEnabled(false);
                                    chk1107a.setEnabled(false);
                                    // viewa.setVisibility(View.VISIBLE);
                                    txtweeks.setText("00");
                                    txtdays.setText("00");
                                    txt1107dd.setTextColor(Color.LTGRAY);
                                    txt1107wks.setTextColor(Color.LTGRAY);
                                    chk1107a.setChecked(false);
                                    //rb99.setVisibility(View.INVISIBLE);
}


//        RadioButton[] bt = new RadioButton[2];
//        for(int f=0;f<rg1.getChildCount();f++) {
//            View o = rg1.getChildAt(f);
//            if (o instanceof RadioButton) {
//                bt[f] = ((RadioButton) o);
//                if (ind.getQ1107() != null && !ind.getQ1107().equals("")) {
//                    if (Integer.parseInt(ind.getQ1103()) == f + 1) {
//                        RadioButton radioButton = bt[f];
//                        radioButton.setChecked(true);
//                        //TextView q1101atext = findViewById(R.id.q1101atxt);
//                       // RadioGroup rg1 = (RadioGroup) findViewById(R.id.q1103radioGroup);
//                        // Is the current Radio Button checked?
//                        boolean checked = radioButton.isChecked();
//                        View v = radioButton;
//                        switch (v.getId()) {
//                            case R.id.q1107_y:
//                                if (checked) {
//                                    txtq1107aq.setTextColor(Color.BLACK);
//                                    txtweeks.setEnabled(true);
//                                    txtdays.setEnabled(true);
//                                    chk1107a.setEnabled(true);
//                                    // viewa.setVisibility(View.VISIBLE);
//
//                                    txt1107dd.setTextColor(Color.BLACK);
//                                    txt1107wks.setTextColor(Color.BLACK);
//
//
//                                    //rb99.setVisibility(View.VISIBLE);
//                                }
//
//
//                                break;
//
//                            case R.id.q1107_n:
//                                if (checked) {
//
//                                    txtq1107aq.setTextColor(Color.LTGRAY);
//                                    txtweeks.setEnabled(false);
//                                    txtdays.setEnabled(false);
//                                    chk1107a.setEnabled(false);
//                                    // viewa.setVisibility(View.VISIBLE);
//                                    txtweeks.setText("00");
//                                    txtdays.setText("00");
//                                    txt1107dd.setTextColor(Color.LTGRAY);
//                                    txt1107wks.setTextColor(Color.LTGRAY);
//                                    chk1107a.setChecked(false);
//                                    //rb99.setVisibility(View.INVISIBLE);
//                                }
//                                break;
//
//
//                        }
//                    }
//                }
//            }
//        }


        if(ind.getQ1107aDD()!= null)
        {

            txtdays.setText(ind.getQ1107aDD());
        }


        if(ind.getQ1107aWks()!= null)
        {
            txtweeks.setText(ind.getQ1107aWks());
        }



        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg1.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q1107.this);
                    builder.setTitle("Q1107 Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Do you currently have a fever");
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


                    if ((((txtdays.length() == 0 && txtweeks.length() == 0 && !chk1107a.isChecked()))) && (rbtn1.isChecked())) {
                        lib.showError(q1107.this, "Q1107:", "How long have you had the fever");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }  else {


                        if (((txtdays.getText().toString().equals("0") || txtdays.getText().toString().equals("00")) &&
                                (txtweeks.getText().toString().equals("0") || txtweeks.getText().toString().equals("00")) && rbtn1.isChecked())) {
                            lib.showError(q1107.this, "Q1107", "a) Days and weeks can not be both 0/00");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {


                            if (rbtn2.isChecked()) {

                                individual.setQ1107(selectedRbtn.getText().toString().substring(0, 1));
                                individual.setQ1107aWks("00");
                                individual.setQ1107aDD("00");

                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                myDB.close();

                                Intent intent = new Intent(q1107.this, q1108.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);
                            } else {
                                individual.setQ1107(selectedRbtn.getText().toString().substring(0, 1));
                                if (txtdays.getText().toString().length() == 0) {
                                    individual.setQ1107aDD("00");
                                } else if (txtdays.getText().toString().length() == 1) {
                                    individual.setQ1107aDD("0" + txtdays.getText().toString());
                                } else {
                                    individual.setQ1107aDD(txtdays.getText().toString());
                                }


                                if (txtweeks.getText().toString().length() == 0) {
                                    individual.setQ1107aWks("00");
                                } else if (txtweeks.getText().toString().length() == 1) {
                                    individual.setQ1107aWks("0" + txtweeks.getText().toString());
                                } else {
                                    individual.setQ1107aWks(txtweeks.getText().toString());
                                }

                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                myDB.close();

                                Intent intent = new Intent(q1107.this, q1108.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);
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

                if (individual.getQ1103() != null && individual.getQ1103().equals("2")) {


                    finish();
                    Intent q1o3i = new Intent(q1107.this, q1103.class);
                    q1o3i.putExtra("Individual", individual);
                    startActivity(q1o3i);


                } else {
                    if (sample.getStatusCode().equals("1") || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") &&
                            (p1.getP07() != null && Integer.parseInt(p1.getP07()) < 14))) {
                        finish();
                        Intent intent = new Intent(q1107.this, q1103.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
                        finish();

                    } else {
                        if (individual.getQ1104() != null && individual.getQ1104().equals("2"))
                        {
                            finish();
                            Intent intent = new Intent(q1107.this, q1104.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);


                        } else {
                            finish();
                            Intent intent = new Intent(q1107.this, q1106.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);

                        }
                    }
                }
            }
        });
    }



    public void onRadioButtonClicked(View v) {


        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q1107_y:
                if (checked) {
                    txtq1107aq.setTextColor(Color.BLACK);
                    txtweeks.setEnabled(true);
                    txtdays.setEnabled(true);
                    chk1107a.setEnabled(true);
                   // viewa.setVisibility(View.VISIBLE);

                    txt1107dd.setTextColor(Color.BLACK);
                            txt1107wks.setTextColor(Color.BLACK);


                    //rb99.setVisibility(View.VISIBLE);
                }


                break;

            case R.id.q1107_n:
                if (checked) {

                    txtq1107aq.setTextColor(Color.LTGRAY);
                    txtweeks.setEnabled(false);
                    txtdays.setEnabled(false);
                    chk1107a.setEnabled(false);
                    // viewa.setVisibility(View.VISIBLE);
                    txtweeks.setText("");
                    txtdays.setText("");
                    txt1107dd.setTextColor(Color.LTGRAY);
                    txt1107wks.setTextColor(Color.LTGRAY);
                    chk1107a.setChecked(false);
                    //rb99.setVisibility(View.INVISIBLE);
                }
                break;


        }
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked

        if (checked) {
            txtq1107aq.setTextColor(Color.BLACK);
            txtweeks.setEnabled(false);
            txtdays.setEnabled(false);
            txtweeks.setText("99");
            txtdays.setText("99");


            // viewa.setVisibility(View.VISIBLE);
            txt1107dd.setTextColor(Color.LTGRAY);
            txt1107wks.setTextColor(Color.LTGRAY);
        }
        // Put some meat on the sandwich
        else {
            // Remove the meat
           // txtq1107aq.setTextColor(Color.BLACK);
            txtweeks.setEnabled(true);
            txtdays.setEnabled(true);
            txtweeks.setText("");
            txtdays.setText("");


            // viewa.setVisibility(View.VISIBLE);
            txt1107dd.setTextColor(Color.BLACK);
            txt1107wks.setTextColor(Color.BLACK);


        }
    }

}



