package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q1012 extends AppCompatActivity  implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtn8, rbtnOther;
    protected RadioGroup rg;protected  DatabaseHelper myDB;
    protected TextView t1;
    protected Individual individual;
    protected EditText edtwks, edtmnths, edtyear;
    protected CheckBox chkb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1012);


        setTitle("Q1012: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);

        chkb = (CheckBox) findViewById(R.id.q1012_1);

        edtwks = (EditText) findViewById(R.id.q1012_weeks);
        edtmnths = (EditText) findViewById(R.id.q1012_month);
        edtyear = (EditText) findViewById(R.id.q1012_year);

        //rga = (RadioGroup)findViewById(R.id.q1010aGroup1) ;


        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

if(individual.getQ1011().equals("1")||individual.getQ1011().equals("4") || individual.getQ1011().equals("7"))
{
    Intent intent = new Intent(q1012.this, q1014.class);
    intent.putExtra("Individual", individual);
    startActivity(intent);
}
else{

        }



        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ((edtwks.length() == 0 || edtwks.getText() == null) && !chkb.isChecked()) {
                    lib.showError(q1012.this, "Q1012: ERROR: weeks", "For how long did you breastfeed?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }
                else {


                    if ( !chkb.isChecked() && Integer.valueOf(edtwks.getText().toString()) > 3  ) {
                        lib.showError(q1012.this, "Q1012: ERROR", "  Weeks cannot be more thana 3");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        if ((edtmnths.length() == 0 || edtmnths.getText() == null) && !chkb.isChecked()) {
                            lib.showError(q1012.this, "Q1012: ERROR: months", "For how long did you breastfeed?");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {


                            if ( !chkb.isChecked() && Integer.valueOf(edtmnths.getText().toString()) > 12  ) {
                                lib.showError(q1012.this, "Q1012: ERROR", "  Months cannot be more than 12");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {
                                if ((edtyear.length() == 0 || edtyear.getText() == null) && !chkb.isChecked()) {
                                    lib.showError(q1012.this, "Q1012: ERROR: years", "For how long did you breastfeed?");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);
                                } else {


                                    if (  !chkb.isChecked() && Integer.valueOf(edtwks.getText().toString()) > 5) {
                                        lib.showError(q1012.this, "Q1012a: ERROR", "  Weeks cannot be more thana 5");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);
                                    } else {



                                        if (edtwks.getText().toString().length() == 0) {
                                            individual.setQ1012_Week("00");
                                        } else if (edtwks.getText().toString().length() == 1) {
                                            individual.setQ1012_Week("0" + edtwks.getText().toString());
                                        } else {
                                            individual.setQ1012_Week(edtwks.getText().toString());

                                        }

                                        if (edtyear.getText().toString().length() == 0) {
                                            individual.setQ1012_Month("00");
                                        } else if (edtyear.getText().toString().length() == 1) {
                                            individual.setQ1012_Month("0" + edtyear.getText().toString());
                                        } else {
                                            individual.setQ1012_Month(edtmnths.getText().toString());
                                        }

                                        if (edtyear.getText().toString().length() == 0) {
                                            individual.setQ801cYear("0000");
                                        } else if (edtyear.getText().toString().length() == 3) {
                                            individual.setQ801cYear("000" + edtyear.getText().toString());
                                        }
                                        else if (edtyear.getText().toString().length() == 1) {
                                            individual.setQ801cYear("000" + edtyear.getText().toString());
                                        }
                                        else if (edtyear.getText().toString().length() == 2) {
                                            individual.setQ801cYear("00" + edtyear.getText().toString());
                                        }else {
                                            individual.setQ1012_Year(edtyear.getText().toString());
                                        }



                                        myDB.onOpen(myDB.getReadableDatabase());
                                        myDB.getWritableDatabase();
                                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                        myDB.close();

                                        Intent intent = new Intent(q1012.this, q1013.class);
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
                q1012.super.onBackPressed();
            }


        });
    }



    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q1012_1:
                if (checked) {
                    edtyear.setText("");
                    edtmnths.setText("");
                    edtwks.setText("");

                    edtwks.setEnabled(false);
                    edtmnths.setEnabled(false);
                    edtyear.setEnabled(false);
                }
                else
            {
                edtwks.setEnabled(true);
                edtmnths.setEnabled(true);
                edtyear.setEnabled(true);
            }
                    break;

        }
    }
}
