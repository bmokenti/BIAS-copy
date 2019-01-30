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

public class q701 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, selected;
    protected RadioGroup rbtngroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q701);

        setTitle("q701  HIV, AIDS and TB ATTITUDES ");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q701_1);
        rbtn2 = (RadioButton) findViewById(R.id.q701_2);

        rbtngroup = (RadioGroup) findViewById(R.id.q701radioGroup);


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
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
                    lib.showError(q701.this, "Q701 Error", "Please select an option for q701");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                   individual.setQ701(selected.getText().toString().substring(0, 1));

                   Intent intent = new Intent(q701.this, q702.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);

                }
            }
        });

    }
}