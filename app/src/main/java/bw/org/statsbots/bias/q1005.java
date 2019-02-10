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

public class q1005 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected Individual individual;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, rbtna3, rbtna4, rbtna5 ;
    protected RadioGroup rga, rg;
    protected TextView ta, tb;
    protected RadioButton selectedRbtna, selectedRbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1005);

        setTitle("Q1005: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);


        rg = (RadioGroup) findViewById(R.id.q1005radioGroup);
        rbtn1 = (RadioButton) findViewById(R.id.q1005_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1005_2);

        rga = (RadioGroup) findViewById(R.id.q1005aGroup1);
        rbtna1 = (RadioButton) findViewById(R.id.q1005a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q1005a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q1005a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q1005a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q1005a_9);

        ta = (TextView) findViewById(R.id.q1005a);

        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        Button btnnext = (Button) findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {

                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q1005.this, "Q1005: ERROR", " Have you ever tested for HIV before your pregnancy with this child?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    int selectedIda = rga.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedIda);

                    if (selectedRbtna == null && rbtn1.isChecked()) {
                        lib.showError(q1005.this, "Q1005a: ERROR", "What was the result of the test?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {


                        if (rbtn2.isChecked()) {
                            individual.setQ1005(selectedRbtn.getText().toString().substring(0, 1));

                            Intent intent = new Intent(q1005.this, q1007.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);
                        } else {


                            if (rbtna2.isChecked() || rbtna3.isChecked() || rbtna4.isChecked() || rbtna5.isChecked()) {


                                individual.setQ1005(selectedRbtn.getText().toString().substring(0, 1));
                                individual.setQ1005a(selectedRbtna.getText().toString().substring(0, 1));

                                Intent intent = new Intent(q1005.this, q1007.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);
                            } else {
                                individual.setQ1005(selectedRbtn.getText().toString().substring(0, 1));
                                individual.setQ1005a(selectedRbtna.getText().toString().substring(0, 1));

                                Intent intent = new Intent(q1005.this, q1006.class);
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
                q1005.super.onBackPressed();
            }


        });
    }



    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q1005_1:
                if(checked)

                    rbtna1.setEnabled(true);
                rbtna2.setEnabled(true);
                rbtna3.setEnabled(true);
                rbtna4.setEnabled(true);
                rbtna5.setEnabled(true);

                ta.setTextColor(Color.BLACK);





                break;

            case R.id.q1005_2:
                if(checked)
                    rbtna1.setEnabled(false);
                rbtna2.setEnabled(false);
                rbtna3.setEnabled(false);
                rbtna4.setEnabled(false);
                rbtna5.setEnabled(false);

                    rbtna1.setChecked(false);
                rbtna2.setChecked(false);
                rbtna3.setChecked(false);
                rbtna4.setChecked(false);
                rbtna5.setChecked(false);
                ta.setTextColor(Color.LTGRAY);


                break;


            default:

                break;

        }

    }


}





/*
Intent intent = new Intent(q504.this, q1101.class);
                //intent.putExtra("Household", thisHose);
                startActivity(intent);
 */


