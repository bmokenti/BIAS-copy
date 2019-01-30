package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class q1003 extends AppCompatActivity {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected Button btn;
    protected Individual individual;
    protected RadioButton rbtn1, rbtn2, rbtn3;
    protected RadioGroup rg;
    protected RadioButton selectedRbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1003);


        setTitle("Q1003: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1003radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q1003_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1003_2);


        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);
        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q1003.this, "Q1003: ERROR", "Are you currently pregnant?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {


                    if (rbtn2.isChecked()) {

                        individual.setQ1003(selectedRbtn.getText().toString().substring(0,1));

                        Intent skipto1017 = new Intent(q1003.this, q1017.class);
                        skipto1017.putExtra("Individual", individual);
                        startActivity(skipto1017);
                    } else {

                       individual.setQ1003(selectedRbtn.getText().toString().substring(0,1));

                        Intent intent = new Intent(q1003.this, q1004.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
                    }
                }


            }




        });
    }




}

/*
Intent intent = new Intent(q504.this, q1101.class);
                //intent.putExtra("Household", thisHose);
                startActivity(intent);
 */


