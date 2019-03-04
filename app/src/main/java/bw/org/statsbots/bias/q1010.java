package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q1010 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected Button btn;
    protected Individual individual;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtnOther, selectedRbtn ;
    protected RadioGroup rg;
    protected TextView t1;protected  DatabaseHelper myDB;
    protected EditText edtOther;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1010);


        setTitle("Q1010: CHILD BEARING");
        lib = new LibraryClass();


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1010radioGroup);
        rbtn1 = (RadioButton) findViewById(R.id.q1010_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1010_2);
        rbtn3 = (RadioButton) findViewById(R.id.q1010_3);
        rbtnOther = (RadioButton) findViewById(R.id.q1010_other);
        edtOther = (EditText)  findViewById(R.id.q1010_other1);




        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q1010_other)
                {
                    // is checked
                    edtOther.setVisibility(View.VISIBLE);
                }
                else
                {
                    // not checked
                    edtOther.setVisibility(View.INVISIBLE);
                    edtOther.setText("");
                }
            }
        });

        RadioButton[] bt = new RadioButton[4];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ1010()!= null &&  !ind.getQ1010().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1010())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null)
                {
                    lib.showError(q1010.this, "Q1010: ERROR", "Where did you give birth to this child?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else
                    {

                    if (rbtnOther.isChecked() && edtOther.length() == 0)
                    {
                        lib.showError(q1010.this, "Q1010: ERROR: Other specify", "Other specify");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else
                        {
                        individual.setQ1010(selectedRbtn.getText().toString().substring(0, 1));
                        individual.setQ1010_Other(edtOther.getText().toString());

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();

                        Intent intent = new Intent(q1010.this, q1011.class);
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
                q1010.super.onBackPressed();
            }


        });
    }



}

