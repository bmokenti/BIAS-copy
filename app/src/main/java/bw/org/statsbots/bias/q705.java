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
import java.util.List;

public class q705 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2, selected;
    protected RadioGroup rbtngroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q705);


        setTitle("q705  HIV, AIDS and TB ATTITUDES ");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q705_1);
        rbtn2 = (RadioButton) findViewById(R.id.q705_2);

        rbtngroup = (RadioGroup) findViewById(R.id.q705radioGroup);


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();
        /**
         * NEXT question
         */

        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rbtngroup.getChildCount();f++)
        {
            View o = rbtngroup.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ705()!= null &&  !ind.getQ705().equals(""))
                {
                    if(Integer.parseInt(ind.getQ705())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }


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
                    lib.showError(q705.this, "Q705 Error", "Please select an option for q705");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                   individual.setQ705(selected.getText().toString().substring(0, 1));

                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.getWritableDatabase();
                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                    myDB.close();


                    Intent intent = new Intent(q705.this, q801.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);

                }
            }
        });

        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q705.super.onBackPressed();
            }


        });
    }
}