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

public class q623 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2;
    protected RadioGroup rg1;
    protected DatabaseHelper myDB;
    protected EditText edtOther;
    protected RadioButton selectedRbtn,selectedRbtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q623);

        setTitle("q623: KNOWLEDGE ABOUT HIV/AIDS AND TB ");
        lib = new LibraryClass();
        rg1 = (RadioGroup) findViewById(R.id.q623radioGroup) ;

        rbtn1 = (RadioButton) findViewById(R.id.q623_1);
        rbtn2 = (RadioButton) findViewById(R.id.q623_2);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());



        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

        if((Integer.valueOf(individual.getQ102()) > 64 && (sample.getStatusCode().equals("2")) && (individual.getQ622().equals("9") || individual.getQ622().equals("1") || individual.getQ622().equals("2") )) ||
                ((Integer.valueOf(individual.getQ102()) >=15 && (sample.getStatusCode().equals("3"))) && (individual.getQ622().equals("9") || individual.getQ622().equals("1") || individual.getQ622().equals("2") )))
        {

            Intent q1o2 = new Intent(q623.this, q704.class);
            q1o2.putExtra("Individual", individual);
            startActivity(q1o2);
        }

        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg1.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);
                if (selectedRbtn == null) {
                    lib.showError(q623.this, "Q623: ERROR", "Have you ever heard of Safe Male Circumcision or SMC programme?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    if (rbtn2.isChecked()) {
                        individual.setQ623(selectedRbtn.getText().toString().substring(0, 1));
                        Intent intent = new Intent(q623.this, q701.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
                    } else {
                        individual.setQ623(selectedRbtn.getText().toString().substring(0, 1));

                        Intent intent = new Intent(q623.this, q624.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
                    }
                }
            }

             });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q623.super.onBackPressed();
            }


        });
    }
    }
