package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class q804 extends AppCompatActivity implements Serializable{

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual indv;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtnother, selected;
    protected RadioGroup rbtngroup;
    protected EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q804);


        setTitle("q804  EVER TESTED FOR HIV");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q804_1);
        rbtn2 = (RadioButton) findViewById(R.id.q804_2);
        rbtn3 = (RadioButton) findViewById(R.id.q804_3);
        rbtn4 = (RadioButton) findViewById(R.id.q804_4);
        rbtn5 = (RadioButton) findViewById(R.id.q804_5);
        rbtn6 = (RadioButton) findViewById(R.id.q804_6);
        rbtn7 = (RadioButton) findViewById(R.id.q804_7);
        rbtnother = (RadioButton) findViewById(R.id.q804_other);
        rbtngroup = (RadioGroup) findViewById(R.id.q804radioGroup);


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
                    lib.showError(q804.this, "Q804 Error", "Please select an option for q804");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    if (selected == rbtnother && edt.length() == 0 || edt.getText() == null) {
                        lib.showError(q804.this, "Q804 Error", "Please type OTHER specify for q804");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);


                        Intent intent = new Intent(q804.this, q901.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);

                    }
                }
            }
        });

    }
}


