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

public class q608 extends AppCompatActivity implements Serializable {
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
        setContentView(R.layout.activity_q608);


        setTitle("q608 HIV and TB FACTS AND MYTHS");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q608_1);
        rbtn2 = (RadioButton) findViewById(R.id.q608_2);
        rbtn9 = (RadioButton) findViewById(R.id.q608_9);
        rbtngroup = (RadioGroup) findViewById(R.id.q608radioGroup);


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
                    lib.showError(q608.this, "Q608 Error", "Please select an option for q608");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    Intent intent = new Intent(q608.this, q609.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);

                }
            }
        });

    }
}