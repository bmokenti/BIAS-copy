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

public class q107 extends AppCompatActivity implements Serializable{
    protected  HouseHold thisHouse;
    protected RadioGroup rg, rg1, rg2,rg3;
    protected RadioButton rbtn1, rbtn2, rbtnb1, rbtnb2 ,rbtnb3, rbtnb4, rbtnb5, rbtnbOther, rbtnc1, rbtnc2, rbtnc3,rbtnc4, rbtnc5,rbtncOther,selectedRbtn, selectedRbtn1, selectedRbtn2, selectedRbtn3;
    protected EditText txtyy, txtmnth, edtbOther, edtcOther;
    protected CheckBox chk99;
    protected LibraryClass lib;
    protected Individual individual;
    PersonRoster p1 = null;
    protected TextView txta, txtb, txtc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q107);

        setTitle("Q107: WORKED IN MINE");
        lib = new LibraryClass();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;


        rg = findViewById(R.id.q107radioGroup);
        rg1 = findViewById(R.id.q107bradioGroup);
        rg2 = findViewById(R.id.q107cradioGroup);



        rbtn1 = findViewById(R.id.q107_1);
        rbtn2 = findViewById(R.id.q107_2);

        chk99 = findViewById(R.id.q107_99);

        rbtnb1 = findViewById(R.id.q107b_1);
        rbtnb2 = findViewById(R.id.q107b_2);
        rbtnb3 = findViewById(R.id.q107b_3);
        rbtnb4 = findViewById(R.id.q107b_4);
        rbtnb5 = findViewById(R.id.q107b_5);
        rbtnbOther = (RadioButton) findViewById(R.id.q107b_other);
        edtbOther = (EditText) findViewById(R.id.q107b_other1);


        rbtnc1 = findViewById(R.id.q107c_1);
        rbtnc2 = findViewById(R.id.q107c_2);
        rbtnc3 = findViewById(R.id.q107c_3);
        rbtnc4 = findViewById(R.id.q107c_4);
        rbtnc5 = findViewById(R.id.q107c_5);
        rbtncOther = findViewById(R.id.q107c_other);
        edtcOther = (EditText) findViewById(R.id.q107c_other1);

        txtyy = findViewById(R.id.q107a_years);
        txtmnth = findViewById(R.id.q107_months);

        txta = (TextView) findViewById(R.id.q107atxt) ;
        txtb = (TextView) findViewById(R.id.q107btxt) ;
         txtc = (TextView) findViewById(R.id.q107ctxt) ;

        chk99.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chk99.isChecked()) {
                    txtmnth.setEnabled(false);
                    txtyy.setEnabled(false);
                    txtmnth.setText("99");
                    txtyy.setText("99");



                }
                else
                {
                    txtmnth.setEnabled(true);
                    txtyy.setEnabled(true);
                    txtmnth.setText("");
                    txtyy.setText("");
                }
            }
        });


        rbtnbOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnbOther.isChecked()) {

                    edtbOther.setVisibility(View.VISIBLE);
                    // edtbOther.setText("");


                }
            }
        });

        rbtnb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnb1.isChecked()) {

                    edtbOther.setVisibility(View.INVISIBLE);
                  edtbOther.setText("");


                }
            }
        });
        rbtnb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnb2.isChecked()) {

                    edtbOther.setVisibility(View.INVISIBLE);
                   edtbOther.setText("");


                }
            }
        });
        rbtnb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnb3.isChecked()) {

                    edtbOther.setVisibility(View.INVISIBLE);
                    edtbOther.setText("");


                }
            }
        });
        rbtnb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnb4.isChecked()) {

                    edtbOther.setVisibility(View.INVISIBLE);
                    // edtbOther.setText("");


                }
            }
        });
        rbtnb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnb5.isChecked()) {

                    edtbOther.setVisibility(View.INVISIBLE);
                    edtbOther.setText("");


                }
            }
        });



        rbtncOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtncOther.isChecked()) {

                    edtcOther.setVisibility(View.VISIBLE);
                    //edtcOther.setText("");

                }
            }
        });

        rbtnc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnc1.isChecked()) {

                    edtcOther.setVisibility(View.INVISIBLE);
                    edtcOther.setText("");

                }
            }
        });

        rbtnc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnc2.isChecked()) {

                    edtcOther.setVisibility(View.INVISIBLE);
                    edtcOther.setText("");

                }
            }
        });

        rbtnc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnc3.isChecked()) {

                    edtcOther.setVisibility(View.INVISIBLE);
                    edtcOther.setText("");

                }
            }
        });
        rbtnc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnc4.isChecked()) {

                    edtcOther.setVisibility(View.INVISIBLE);
                    edtcOther.setText("");

                }
            }
        });
        rbtnc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnc5.isChecked()) {

                    edtcOther.setVisibility(View.INVISIBLE);
                    edtcOther.setText("");

                }
            }
        });
        Button btnnext = findViewById(R.id.button);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {

                    lib.showError(q107.this, "Q107: Error", "Have you ever worked in a mine?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);


                } else {
                    // individual.setQ1106(selectedRbtn1.getText().toString().substring(0,1));
                    //int selectedId1 = rg1.getCheckedRadioButtonId();
                    //selectedRbtn1 = (RadioButton) findViewById(selectedId1);

                    if (rbtn1.isChecked() && !chk99.isChecked() && txtyy.length() == 0) {


                        lib.showError(q107.this, "Q107: Error: Year", "For how long have you worked in a mine?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);


                    }
                    else {
                        // individual.setQ1106(selectedRbtn1.getText().toString().substring(0,1));
                        //int selectedId1 = rg1.getCheckedRadioButtonId();
                        //selectedRbtn1 = (RadioButton) findViewById(selectedId1);

                        if (rbtn1.isChecked() && !chk99.isChecked() && txtmnth.length() == 0) {


                            lib.showError(q107.this, "Q107: Error: Months", "Please provide months ");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);


                        } else {
                            // individual.setQ1106(selectedRbtn1.getText().toString().substring(0,1));
                            //int selectedId1 = rg1.getCheckedRadioButtonId();
                            //selectedRbtn1 = (RadioButton) findViewById(selectedId1);

                            if (!chk99.isChecked() && txtmnth.length() > 0 && Integer.valueOf(txtmnth.getText().toString()) > 11) {


                                lib.showError(q107.this, "Q107: Error: Months", "Months should be 12 or less ");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);


                            } else {
                                //individual.setQ1106a(selectedRbtn2.getText().toString().substring(0,1));
                                int selectedId1 = rg1.getCheckedRadioButtonId();
                                selectedRbtn1 = (RadioButton) findViewById(selectedId1);

                                if (selectedRbtn1 == null && rbtn1.isChecked()) {

                                    lib.showError(q107.this, "Q107: Error", "b) What was your occupation?");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);


                                } else {
                                    //individual.setQ1106a(selectedRbtn2.getText().toString().substring(0,1));


                                    if (rbtnbOther.isChecked() && edtbOther.length() == 0) {

                                        lib.showError(q107.this, "Q107b: Error", "Please specify");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);


                                    } else {
                                        //individual.setQ1106b(selectedRbtn3.getText().toString().substring(0,1));
                                        int selectedId2 = rg2.getCheckedRadioButtonId();
                                        selectedRbtn2 = (RadioButton) findViewById(selectedId2);
                                        if (selectedRbtn2 == null && rbtn1.isChecked()) {
                                            lib.showError(q107.this, "Q107 Error", "What is /was the type of the commodity mined?");
                                            /**
                                             * VIBRATE DEVICE
                                             */
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);
                                        } else {
                                            //individual.setQ1106a(selectedRbtn2.getText().toString().substring(0,1));


                                            if (rbtncOther.isChecked() && edtcOther.length() == 0) {

                                                lib.showError(q107.this, "Q107c: Error", "Please specify");
                                                /**
                                                 * VIBRATE DEVICE
                                                 */
                                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                vibs.vibrate(100);


                                            } else {
                                                if (rbtn2.isChecked()) {
                                                    individual.setQ107(selectedRbtn.getText().toString().substring(0, 1));

                                                    Intent q1o3 = new Intent(q107.this, Q201.class);
                                                    q1o3.putExtra("Individual", individual);
                                                    startActivity(q1o3);
                                                } else {
                                                    individual.setQ107(selectedRbtn.getText().toString().substring(0, 1));
                                                    individual.setQ107aYY(txtyy.getText().toString());
                                                    individual.setQ107aMnth(txtmnth.getText().toString());
                                                    individual.setQ107b(selectedRbtn1.getText().toString().substring(0, 1));
                                                    individual.setQ107bOther(edtbOther.getText().toString());
                                                    individual.setQ107c(selectedRbtn2.getText().toString().substring(0, 1));
                                                    individual.setQ107cOther(edtcOther.getText().toString());


                                                    Intent q1o3 = new Intent(q107.this, Q201.class);
                                                    q1o3.putExtra("Individual", individual);
                                                    startActivity(q1o3);
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


    public void onRadioButtonClicked(View v){



        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()){
            case R.id.q107_1:

                if(checked)

                chk99.setEnabled(true);
                rbtnb1.setEnabled(true);
                rbtnb2.setEnabled(true);
                rbtnb3.setEnabled(true);
                rbtnb4.setEnabled(true);
                rbtnb5.setEnabled(true);
                rbtnbOther.setEnabled(true);
                rbtnc1.setEnabled(true);
                rbtnc2.setEnabled(true);
                rbtnc3.setEnabled(true);
                rbtnc4.setEnabled(true);
                rbtnc5.setEnabled(true);
                rbtncOther.setEnabled(true);
                txtyy.setEnabled(true);
                txtmnth.setEnabled(true);
                txta.setTextColor(Color.BLACK);
                txtb.setTextColor(Color.BLACK);
                txtc.setTextColor(Color.BLACK);
                //rbtn99.setChecked(true);





                //  rGroup2.setVisibility(View.VISIBLE);

                break;

            case R.id.q107_2:
                if(checked)

                    chk99.setChecked(false);
                chk99.setEnabled(false);
                rbtnb1.setEnabled(false);
                rbtnb2.setEnabled(false);
                rbtnb3.setEnabled(false);
                rbtnb4.setEnabled(false);
                rbtnb5.setEnabled(false);
                rbtnbOther.setEnabled(false);
                rbtnc1.setEnabled(false);
                rbtnc2.setEnabled(false);
                rbtnc3.setEnabled(false);
                rbtnc4.setEnabled(false);
                rbtnc5.setEnabled(false);
                rbtncOther.setEnabled(false);
                txtyy.setEnabled(false);
                txtmnth.setEnabled(false);

                chk99.setChecked(false);
                rbtnb1.setChecked(false);
                rbtnb2.setChecked(false);
                rbtnb3.setChecked(false);
                rbtnb4.setChecked(false);
                rbtnb5.setChecked(false);
                rbtnbOther.setChecked(false);
                rbtnc1.setChecked(false);
                rbtnc2.setChecked(false);
                rbtnc3.setChecked(false);
                rbtnc4.setChecked(false);
                rbtnc5.setChecked(false);
                rbtncOther.setChecked(false);
                txtyy.setText("");
                txtmnth.setText("");

                txta.setTextColor(Color.LTGRAY);
                txtb.setTextColor(Color.LTGRAY);
                txtc.setTextColor(Color.LTGRAY);



                break;

            case R.id.q107b_other:
                if(checked)




                txtyy.setEnabled(false);
                txtmnth.setEnabled(false);


                break;

            case R.id.q107c_other:
                if(checked)



                    txtyy.setEnabled(false);
                txtmnth.setEnabled(false);




                break;
            case R.id.q107_99:
                if(checked)



                 txtyy.setEnabled(false);
                txtmnth.setEnabled(false);
                txtyy.setText("");
                txtmnth.setText("");




                break;
        }
    }

    public void onCheckboxClicked(View v) {


        // Is the current Radio Button checked?
        boolean checked = ((CheckBox) v).isChecked();

        switch (v.getId()) {
            case R.id.q107_99:

                if (checked)
                {

                }
                else {


                }


                    break;
        }
    }

}
