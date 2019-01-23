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

public class q1008 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, rbtna3, rbtna4, rbtna5, rbtna6, rbtna7, rbtna8, rbtna10, rbtnaOther ;
    protected RadioGroup rg, rga;
    protected TextView t1;
    protected EditText edtOther;
    protected RadioButton selectedRbtn, selectedRbtna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1008);

        setTitle("Q1008: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1008radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q1008_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1008_2);

        rga = (RadioGroup)findViewById(R.id.q1008aGroup1) ;
        rbtna1 = (RadioButton) findViewById(R.id.q1008a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q1008a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q1008a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q1008a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q1008a_5);
        rbtna6 = (RadioButton) findViewById(R.id.q1008a_6);
        rbtna7 = (RadioButton) findViewById(R.id.q1008a_7);
        rbtna8 = (RadioButton) findViewById(R.id.q1008a_8);
        rbtna10 = (RadioButton) findViewById(R.id.q1008a_10);
        rbtnaOther = (RadioButton) findViewById(R.id.q1008a_other);
        edtOther = (EditText) findViewById(R.id.q1008a_other1);

        t1 = (TextView) findViewById(R.id.q1008a) ;


        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        int p=0;


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q1008.this, "Q1008: ERROR", " Did you EVER take ARVs during your pregnancy?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    int selectedIda = rga.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedIda);

                    if (selectedRbtna == null) {
                        lib.showError(q1008.this, "Q1008a: ERROR", "What was the MAIN REASON you did not take ARVs while you were pregnant?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {


                        if (rbtn2.isChecked()) {
                            Intent skipto1010 = new Intent(q1008.this, q1010.class);
                            skipto1010.putExtra("Household", thisHouse);
                            startActivity(skipto1010);
                        } else {


                            if (rbtna1.isChecked() || rbtna2.isChecked() || rbtna3.isChecked() || rbtna4.isChecked() || rbtna5.isChecked() || rbtna6.isChecked() || rbtna7.isChecked() || rbtna8.isChecked() || rbtna10.isChecked() || rbtnaOther.isChecked()) {
                                Intent skipto1009 = new Intent(q1008.this, q1010.class);
                                skipto1009.putExtra("Household", thisHouse);
                                startActivity(skipto1009);
                            }
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

            case R.id.q1008_1:
                if(checked)

                    rbtna1.setEnabled(false);
                rbtna2.setEnabled(false);
                rbtna3.setEnabled(false);
                rbtna4.setEnabled(false);
                rbtna5.setEnabled(false);
                rbtna6.setEnabled(false);
                rbtna7.setEnabled(false);
                rbtna8.setEnabled(false);
                rbtna10.setEnabled(false);
                rbtnaOther.setEnabled(false);
                t1.setTextColor(Color.LTGRAY);

                rbtna1.setChecked(false);
                rbtna2.setChecked(false);
                rbtna3.setChecked(false);
                rbtna4.setChecked(false);
                rbtna5.setChecked(false);
                rbtna6.setChecked(false);
                rbtna7.setChecked(false);
                rbtna8.setChecked(false);
                rbtna10.setChecked(false);
                rbtnaOther.setChecked(false);

                break;

            case R.id.q1008_2:
                if(checked)

                    rbtna1.setEnabled(true);
                rbtna2.setEnabled(true);
                rbtna3.setEnabled(true);
                rbtna4.setEnabled(true);
                rbtna5.setEnabled(true);
                rbtna6.setEnabled(true);
                rbtna7.setEnabled(true);
                rbtna8.setEnabled(true);
                rbtna10.setEnabled(true);
                rbtnaOther.setEnabled(true);
                t1.setTextColor(Color.BLACK);





                break;

            case R.id.q1008a_other:
                if(checked)
                edtOther.setVisibility(View.VISIBLE);

                else
                    edtOther.setVisibility(View.INVISIBLE);
                edtOther.setText("");

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


