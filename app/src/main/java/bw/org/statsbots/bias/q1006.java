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

public class q1006 extends AppCompatActivity {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected Individual individual;
    protected RadioButton rbtn1, rbtn2;
    protected RadioGroup rg;
    protected RadioButton selectedRbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1006);


        setTitle("Q1006: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1006radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q1006_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1006_2);


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
                    lib.showError(q1006.this, "Q1006: ERROR", "At the time of your first antenatal care visit were you taking ARVs?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {


                    if (rbtn1.isChecked()) {
                        Intent skipto1010 = new Intent(q1006.this, q1010.class);
                        skipto1010.putExtra("Individual", individual);
                        startActivity(skipto1010);
                    } else {


                        if (rbtn2.isChecked()) {
                            Intent skipto1008 = new Intent(q1006.this, q1008.class);
                            skipto1008.putExtra("Individual", individual);
                            startActivity(skipto1008);
                        } else {
                            individual.setQ1006(selectedRbtn.getText().toString().substring(0,1));

                            Intent intent = new Intent(q1006.this, q1007.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);
                        }
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


