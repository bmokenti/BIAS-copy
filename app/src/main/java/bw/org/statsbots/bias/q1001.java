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

public class q1001 extends AppCompatActivity {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected RadioButton rbtn1, rbtn2, rbtn3;
    protected RadioGroup rg;
    protected RadioButton selectedRbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1001);

        setTitle("Q1001: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1001radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q1001_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1001_2);
        rbtn3 = (RadioButton) findViewById(R.id.q1001_3);

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
                    lib.showError(q1001.this, "Q1001: ERROR", "Are you currently pregnant?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {


                    if (rbtn1.isChecked()) {

                        Intent skipto1003 = new Intent(q1001.this, q1003.class);
                        //skipto1003.putExtra("Household", thisHose);
                        startActivity(skipto1003);
                    } else {
                       // thisHouse.getIndividual()[p1.getLineNumber()].setQ1001(selectedRbtn.getText().toString().substring(0,1));

                        Intent intent = new Intent(q1001.this, q1002.class);
                        intent.putExtra("Household", thisHouse);
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


