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

public class q610 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual indv;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn9, selected;
    protected RadioGroup rbtngroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q610);

        setTitle("q610 HIV and TB FACTS AND MYTHS");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q610_1);
        rbtn2 = (RadioButton) findViewById(R.id.q610_2);
        rbtn9 = (RadioButton) findViewById(R.id.q610_9);
        rbtngroup = (RadioGroup) findViewById(R.id.q610radioGroup);


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
                    lib.showError(q610.this, "Q610 Error", "Please select an option for q610");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                   // thisHouse.getIndividual()[p1.getLineNumber()].setQ610(selected.getText().toString().substring(0,1));

                    Intent intent = new Intent(q610.this, q611.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);

                }
            }
        });

    }
}