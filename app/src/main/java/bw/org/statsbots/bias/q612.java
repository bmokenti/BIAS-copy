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

public class q612 extends AppCompatActivity  implements Serializable{
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected Individual indv;
    protected LibraryClass lib;
    protected RadioButton rbtn1,rbtn2,rbtn9,rbtna1, rbtna2, rbtna9, rbtnaOther, selected, selected1;
    protected RadioGroup rbtngroup, rbtngroup1;
    protected EditText edt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q612);



        setTitle("q612 HIV PREGNANCY TRANSMISSION");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q612_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q612_2);
        rbtn9 =  (RadioButton)findViewById(R.id.q612_9);
        rbtngroup = (RadioGroup)findViewById(R.id.q612radioGroup) ;


        rbtngroup1 = (RadioGroup)findViewById(R.id.q612radioGroupa) ;
        rbtna1 =  (RadioButton)findViewById(R.id.q612a_1);
        rbtna2 =  (RadioButton)findViewById(R.id.q612a_2);
        rbtna9 =  (RadioButton)findViewById(R.id.q612a_9);
        rbtnaOther =  (RadioButton)findViewById(R.id.q612a_other);

        edt = (EditText) findViewById(R.id.q612_other);

        //final int selectedId1 = rbtngroup1.getCheckedRadioButtonId();

        //Intent i = getIntent();
       // thisHouse = (HouseHold)i.getSerializableExtra("Household");
        //int p=0;


        /**
         * NEXT question
         */
        Button btnNext = (Button)findViewById(R.id.button);


        /**
         * NEXT and SAVE BUTTON
         */
        // btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                int selectedId = rbtngroup.getCheckedRadioButtonId();
                selected = (RadioButton) findViewById(selectedId);

                if (selected == null) {
                    lib.showError(q612.this, "Q612 Error", "Please select a value for q612");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
               } else {
                    int selectedId1 = rbtngroup1.getCheckedRadioButtonId();
                    selected1 = (RadioButton) findViewById(selectedId1);
                    if (selected1  == null && rbtn1.isChecked() ) {
                        lib.showError(q612.this, "Q612a Error", "Please select a value for q612a");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {

                        if (selected1 == rbtnaOther && edt.length() == 0 || edt.getText() == null) {
                            lib.showError(q612.this, "Q612a Error", "Please specify");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {

                            //Set Q612 and Q612a for the current individual
                           // indv.setQ612(selected.getText().toString().substring(0, 1));
                            //indv.setQ612a(selected.getText().toString().substring(0, 1));

                            Intent intent = new Intent(q612.this, q613.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);
                        }

                    }


                }
            }

        });

    }


    public void onRadioButtonClicked(View v) {

        TextView q612atext = findViewById(R.id.q612atxt);
        RadioGroup rg1 = (RadioGroup) findViewById(R.id.q612radioGroupa);
        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q612_1:
                if (checked)
                    for (int i = 0; i < rbtngroup1.getChildCount(); i++) {
                        ((RadioButton) rbtngroup1.getChildAt(i)).setEnabled(true);
                        q612atext.setTextColor(Color.BLACK);

                    }
                break;


            case R.id.q612_2:
                if (checked)
                    for (int i = 0; i < rbtngroup1.getChildCount(); i++) {
                        ((RadioButton) rbtngroup1.getChildAt(i)).setEnabled(false);
                        rbtna1.setChecked(false);
                        rbtna2.setChecked(false);
                        rbtna9.setChecked(false);
                        rbtnaOther.setChecked(false);
                        q612atext.setTextColor(Color.LTGRAY);

                    }

                break;
            case R.id.q612_9:
                if (checked)
                    for (int i = 0; i < rbtngroup1.getChildCount(); i++) {
                        ((RadioButton) rbtngroup1.getChildAt(i)).setEnabled(false);
                        rbtna1.setChecked(false);
                        rbtna2.setChecked(false);
                        rbtna9.setChecked(false);
                        rbtnaOther.setChecked(false);
                        q612atext.setTextColor(Color.LTGRAY);

                    }
                break;
            case R.id.q612a_1:
                if (checked)

                    edt.setVisibility(View.INVISIBLE);

                break;
            case R.id.q612a_2:
                if (checked)

                    edt.setVisibility(View.INVISIBLE);

                break;
            case R.id.q612a_9:
                if (checked)

                    edt.setVisibility(View.INVISIBLE);


                break;
            case R.id.q612a_other:
                if (checked)

                    edt.setVisibility(View.VISIBLE);






                break;
                }
        }
    }







