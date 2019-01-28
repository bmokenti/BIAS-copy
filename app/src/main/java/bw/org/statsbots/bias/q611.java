package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class q611 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual indv;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn9, rbtn1b, rbtnb2, rbtnb9, rbtnc1, rbtnc2, rbtnc9, selected, selected1, selected2;
    protected RadioGroup rbtngroup, rbtngroupb, rbtngroupc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q611);


        setTitle("q611 HIV TRANSMISSION- MOTHER TO CHILD");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q611a_1);
        rbtn2 = (RadioButton) findViewById(R.id.q611a_2);
        rbtn9 = (RadioButton) findViewById(R.id.q611a_9);
        rbtngroup = (RadioGroup) findViewById(R.id.q611aradioGroup);

        rbtn1 = (RadioButton) findViewById(R.id.q611b_1);
        rbtn2 = (RadioButton) findViewById(R.id.q611b_2);
        rbtn9 = (RadioButton) findViewById(R.id.q611b_9);
        rbtngroupb = (RadioGroup) findViewById(R.id.q611bradioGroup);

        rbtn1 = (RadioButton) findViewById(R.id.q611c_1);
        rbtn2 = (RadioButton) findViewById(R.id.q611c_2);
        rbtn9 = (RadioButton) findViewById(R.id.q611c_9);
        rbtngroupc = (RadioGroup) findViewById(R.id.q611cradioGroup);


        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;


        /**
         * NEXT question
         */
        Button btnNext = (Button) findViewById(R.id.button);

        /**
         * NEXT and SAVE BUTTON
         */
        // btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = rbtngroup.getCheckedRadioButtonId();
                selected = (RadioButton) findViewById(selectedId);

                if (selected == null) {
                    lib.showError(q611.this, "Q611a Error", "Please select a value for q611a");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    int selectedId2 = rbtngroupb.getCheckedRadioButtonId();
                    selected1 = (RadioButton) findViewById(selectedId2);

                    if (selected1 == null) {
                        lib.showError(q611.this, "Q611b Error", "Please select a value for q611b");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    }
                    else
                    {
                        int selectedId3 = rbtngroupc.getCheckedRadioButtonId();
                        selected2 = (RadioButton) findViewById(selectedId3);
                        if (selected2 == null) {
                            lib.showError(q611.this, "Q611c Error", "Please select a value for q611c");

                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        }
                        else {

                            //Set Q611 and Q611a, 611b, 611c for the current individual
                            //thisHouse.getIndividual()[p1.getLineNumber()].setQ611a(selected.getText().toString().substring(0, 1));
                            //thisHouse.getIndividual()[p1.getLineNumber()].setQ611b(selected1.getText().toString().substring(0, 1));
                            //thisHouse.getIndividual()[p1.getLineNumber()].setQ611c(selected2.getText().toString().substring(0, 1));

                            Intent intent = new Intent(q611.this, q612.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);


                        }

                    }


                }
            }

        });

    }
}

