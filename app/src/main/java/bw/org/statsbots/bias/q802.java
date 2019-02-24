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

public class q802 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtna2, rbtna3, rbtna4, rbtna5, rbtnaother, selected, selected1;
    protected RadioGroup rbtngroup, rbtngroup1;
    protected EditText edtnaOther;
    protected TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q802);

        setTitle("q802 HIV SUPPORT, CARE AND TREATMENT");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q802_1);
        rbtn2 = (RadioButton) findViewById(R.id.q802_2);
        rbtngroup = (RadioGroup) findViewById(R.id.q802radioGroup);


        rbtngroup1 = (RadioGroup) findViewById(R.id.q802radioGroupa);
        rbtna2 = (RadioButton) findViewById(R.id.q802a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q802a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q802a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q802a_5);
        rbtnaother = (RadioButton) findViewById(R.id.q802a_other);
        edtnaOther = (EditText) findViewById(R.id.q802a_other1);
        txt1 = (TextView) findViewById(R.id.q802a);

        final int selectedId1 = rbtngroup1.getCheckedRadioButtonId();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        if((individual.getQ801f().equals("2") || individual.getQ801f().equals("3") || individual.getQ801f().equals("4") || individual.getQ801f().equals("9")  && individual.getQ801a().equals("2")))

        {
            Intent intent = new Intent(q802.this, q803.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

        else

                if((individual.getQ801f().equals("2") || individual.getQ801f().equals("3") || individual.getQ801f().equals("4") || individual.getQ801f().equals("9")) && individual.getQ801a().equals("1")
                        && individual.getQ101().equals("2") && ((Integer.valueOf(individual.getQ102()) > 14 && (Integer.valueOf(individual.getQ102()) < 50))))
                {
                    Intent intent = new Intent(q802.this, q1001.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                }

    else
                {
                    if((individual.getQ801f().equals("2") || individual.getQ801f().equals("3") || individual.getQ801f().equals("4") || individual.getQ801f().equals("9")) && individual.getQ801a().equals("1")
                            && individual.getQ101().equals("1"))
                    {
                        Intent intent = new Intent(q802.this, q1101.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
                    }

                }


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
                    lib.showError(q802.this, "Q802 Error", "Please select an option q802");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    int selectedId1 = rbtngroup1.getCheckedRadioButtonId();
                    selected1 = (RadioButton) findViewById(selectedId1);
                    if (selected1 == null && rbtn1.isChecked()) {
                        lib.showError(q802.this, "Q802a Error", "Please select an option for q802a");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {

                        if (rbtnaother.isChecked() && edtnaOther.length()==0) {
                            lib.showError(q802.this, "Q802a Error", "Please please specify or select another");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        }  else {

                            //Set Q802 and Q802a for the current individual

                            if (rbtn2.isChecked()) {
                                individual.setQ802(selected.getText().toString().substring(0, 1));


                                Intent intent = new Intent(q802.this, q803.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);

                            } else {

                                individual.setQ802(selected.getText().toString().substring(0, 1));
                                individual.setQ802a(selected1.getText().toString().substring(0, 1));
                                individual.setQ802aOther(edtnaOther.getText().toString());

                                Intent intent = new Intent(q802.this, q803.class);
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
                q802.super.onBackPressed();
            }


        });
    }

    public void onRadioButtonClicked(View v) {

        TextView q802atext = findViewById(R.id.q802a_other);
        RadioGroup rg1 = (RadioGroup) findViewById(R.id.q802radioGroupa);
        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q802_1:
                if (checked)
                    rbtna2.setEnabled(true);
                rbtna3.setEnabled(true);
                rbtna4.setEnabled(true);
                rbtna5.setEnabled(true);
                rbtnaother.setEnabled(true);
                txt1.setTextColor(Color.BLACK);


                break;


            case R.id.q802_2:
                if (checked)

                    rbtna2.setEnabled(false);
                rbtna3.setEnabled(false);
                rbtna4.setEnabled(false);
                rbtna5.setEnabled(false);
                rbtnaother.setEnabled(false);
                edtnaOther.setVisibility(View.INVISIBLE);
                edtnaOther.setText("");
                rbtna2.setChecked(false);
                rbtna3.setChecked(false);
                rbtna4.setChecked(false);
                rbtna5.setChecked(false);
                rbtnaother.setEnabled(false);
                txt1.setTextColor(Color.LTGRAY);



                break;
            case R.id.q802a_2:
                if (checked)
                    edtnaOther.setVisibility(View.INVISIBLE);
                edtnaOther.setText("");
                rbtnaother.setChecked(false);


                break;
            case R.id.q802a_3:
                if (checked)
                    edtnaOther.setVisibility(View.INVISIBLE);
                edtnaOther.setText("");
                rbtnaother.setChecked(false);


                break;
            case R.id.q802a_4:
                if (checked)
                    edtnaOther.setVisibility(View.INVISIBLE);
                edtnaOther.setText("");
                rbtnaother.setChecked(false);


                break;
            case R.id.q802a_5:
                if (checked)
                    edtnaOther.setVisibility(View.INVISIBLE);
                edtnaOther.setText("");
                rbtnaother.setChecked(false);


                break;
            case R.id.q802a_other:
                if (checked)
                    edtnaOther.setVisibility(View.VISIBLE);


                break;


        }
    }
}






