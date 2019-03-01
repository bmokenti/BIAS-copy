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
import java.util.List;

public class q625 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2,rbtn9;
    protected RadioGroup rg1,rg2,rg3;
    protected EditText edtOther;
    protected RadioButton selectedRbtn,selectedRbtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q625);

        setTitle("q625: KNOWLEDGE ABOUT HIV/AIDS AND TB ");
        lib = new LibraryClass();
        rg1 = (RadioGroup) findViewById(R.id.q625radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q625_1);
        rbtn2 = (RadioButton) findViewById(R.id.q625_2);
        rbtn9 = (RadioButton) findViewById(R.id.q625_9);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg1.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);
                if (selectedRbtn == null) {
                    lib.showError(q625.this, "Q625: ERROR", "Do you think a circumcised male should stop using a condom?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }
                else
                {
                    individual.setQ625(selectedRbtn.getText().toString().substring(0, 1));
                    myDB = new DatabaseHelper(q625.this);
                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.getWritableDatabase();

                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);

                    Intent intent = new Intent(q625.this, q701.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                }
            }
        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q625.super.onBackPressed();
            }


        });
    }
}




