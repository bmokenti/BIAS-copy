package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class q106 extends AppCompatActivity implements Serializable {
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, rbtna3, rbtna4, rbtna5, rbtna6, rbtnb1, rbtnb2, rbtnb3, rbtnb4, rbtnb5, rbtnb6, rbtnb7, rbtnb8, rbtnb10;
    protected RadioGroup rg, rga, rgb;
    protected HouseHold thisHouse;
    protected Individual individual;
    protected RadioButton selectedRbtn, selectedRbtna, selectedRbtnb;
    protected EditText edt, edt1, edt2;
    TextView q106atext, q106dtext, q106btext, q106ctext;
    PersonRoster p1 = null;
    Individual pp1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q106);


        setTitle("Q106. WORK FOR PROFIT ");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q106_1);
        rbtn2 = (RadioButton) findViewById(R.id.q106_2);

        rbtna1 = (RadioButton) findViewById(R.id.q106a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q106a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q106a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q106a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q106a_5);
        rbtna6 = (RadioButton) findViewById(R.id.q106a_Other);
        edt = findViewById(R.id.q106a_txtother);
        edt = (EditText) findViewById(R.id.Q105_txtOther);


        rbtnb1 = (RadioButton) findViewById(R.id.q106b_1);
        rbtnb2 = (RadioButton) findViewById(R.id.q106b_2);
        rbtnb3 = (RadioButton) findViewById(R.id.q106b_3);
        rbtnb4 = (RadioButton) findViewById(R.id.q106b_4);
        rbtnb5 = (RadioButton) findViewById(R.id.q106b_5);
        rbtnb6 = (RadioButton) findViewById(R.id.q106b_6);
        rbtnb7 = (RadioButton) findViewById(R.id.q106b_7);
        rbtnb8 = (RadioButton) findViewById(R.id.q106b_8);

        rbtnb10 = (RadioButton) findViewById(R.id.q106b_10);

        q106atext = (TextView) findViewById(R.id.q106atxt);
        q106dtext = (TextView) findViewById(R.id.q106dtxt);
        q106btext = (TextView) findViewById(R.id.q106btxt);
        q106ctext = (TextView) findViewById(R.id.q106ctxt);

        rg = (RadioGroup) findViewById(R.id.q106radioGroup);
        rga = (RadioGroup) findViewById(R.id.q106aradioGroup);
        rgb = (RadioGroup) findViewById(R.id.q106bradioGroup);


        edt1 = (EditText) findViewById(R.id.q106c);
        edt2 = (EditText) findViewById(R.id.q106d);
        lib = new LibraryClass();

        //edittext = (EditText) findViewById(R.id.q102_years);
        //edittext.setVisibility(View.VISIBLE);

        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;


        /**
         * NEXT Person BUTTON
         */
        Button btnNext = (Button) findViewById(R.id.button);


        //btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q106.this, "q106 Error", "In the past 7 days did " + p1.getP01() + " work for payment, profit or home use for atleast 1 hour");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                } else {
                    int selectedIda = rga.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedIda);

                    if (selectedRbtn == null) {
                        lib.showError(q106.this, "q106a Error", "Since did not work for payment, profit or home use, what did" + p1.getP01() + " do?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);


                    } else {

                        int selectedIdb = rgb.getCheckedRadioButtonId();
                        selectedRbtnb = (RadioButton) findViewById(selectedIdb);

                        if (edt.length() == 0 || edt1.getText() == null) {
                            lib.showError(q106.this, "q106b Error", "What is" + p1.getP01() + "'s current occupation?");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);


                        } else {
                            if (edt2.length() == 0 || edt2.getText() == null) {
                                lib.showError(q106.this, "q106b Error", "What were you mainly working as during the past 7 days?");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);


                            } else {
                                individual.setQ106(edt.getText().toString().substring(0, 1));
                                individual.setQ106a(selectedRbtn.getText().toString());
                                individual.setQ106aOther(selectedRbtn.getText().toString());
                                individual.setQ106b(selectedRbtn.getText().toString());
                                individual.setQ106c(selectedRbtn.getText().toString());
                                individual.setQ106d(selectedRbtn.getText().toString());
                                //Set P02 fir the current individual
                                //thisHouse.getPersons()[p1.getLineNumber()].setP07(years);
                                //Restart the current activity for next individual


                                //Next question P17
                                Intent intent = new Intent(q106.this, q107.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent);


                            }


                        }
                    }
                }

            }
        });

    }

    public void onRadioButtonClicked(View v) {
        //TextView q1101atext = findViewById(R.id.q1101atxt);
        //RadioGroup rg1 = (RadioGroup) findViewById(R.id.q1101radioGroupa);
        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q106_1:
                if (checked) {
                   rbtna1.setEnabled(false);
                    rbtna2.setEnabled(false);
                    rbtna3.setEnabled(false);
                    rbtna4.setEnabled(false);
                    rbtna5.setEnabled(false);
                    rbtna6.setEnabled(false);
                    q106atext.setTextColor(Color.LTGRAY);


                    q106btext.setTextColor(Color.BLACK);
                    rbtnb1.setEnabled(true);
                    rbtnb2.setEnabled(true);
                    rbtnb3.setEnabled(true);
                    rbtnb4.setEnabled(true);
                    rbtnb5.setEnabled(true);
                    rbtnb6.setEnabled(true);
                    rbtnb7.setEnabled(true);
                    rbtnb8.setEnabled(true);
                    rbtnb10.setEnabled(true);

                    q106dtext.setTextColor(Color.BLACK);
                    q106ctext.setTextColor(Color.BLACK);
                    //edt,
                            edt1.setEnabled(true);
                            edt2.setEnabled(true);

                    }


                break;



            case R.id.q106_2: {
                //if (checked)
                // for (int i = 0; i < rga.getChildCount(); i++) {
                //  ((RadioButton) rga.getChildAt(i)).setEnabled(true);
                rbtna1.setEnabled(true);
                rbtna2.setEnabled(true);
                rbtna3.setEnabled(true);
                rbtna4.setEnabled(true);
                rbtna5.setEnabled(true);
                rbtna6.setEnabled(true);
                q106atext.setTextColor(Color.BLACK);


                q106btext.setTextColor(Color.LTGRAY);
                rbtnb1.setEnabled(false);
                rbtnb2.setEnabled(false);
                rbtnb3.setEnabled(false);
                rbtnb4.setEnabled(false);
                rbtnb5.setEnabled(false);
                rbtnb6.setEnabled(false);
                rbtnb7.setEnabled(false);
                rbtnb8.setEnabled(false);
                rbtnb10.setEnabled(false);

                q106dtext.setTextColor(Color.LTGRAY);
                q106ctext.setTextColor(Color.LTGRAY);
                //edt,
                edt1.setEnabled(false);
                edt2.setEnabled(false);


                }
                // }

                break;

            }
        }
    }



/*
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.q102_years:
                Intent intent4 = new Intent(this, q103.class);
                startActivity(intent4);
                break;


        }
    }*/
