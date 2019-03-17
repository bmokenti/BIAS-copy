package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
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

public class q1017 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, rbtna3, rbtna4, rbtna5 ;
    protected RadioGroup rg, rga;
    protected TextView t1;
    protected DatabaseHelper myDB;
    protected Individual individual;
    protected EditText edtOther;
    protected RadioButton selectedRbtn, selectedRbtna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1017);



        setTitle("Q1017: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1017radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q1017_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1017_2);







        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();


        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ1017()!= null &&  !ind.getQ1017().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1017())==f+1)
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

                if (selectedRbtn == null) {
                    lib.showError(q1017.this, "Q1017: ERROR", "Have you ever been screened for cervical cancer in the past 12 months?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }  else {

                    individual.setQ1017(selectedRbtn.getText().toString().substring(0,1));
                    myDB = new DatabaseHelper(q1017.this);
                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.getWritableDatabase();

                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);

                    Intent intent = new Intent(q1017.this, q1101.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);



                }
            }



        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (individual.getQ1003() != null && individual.getQ1003().equals("2")) {

                    Intent skipto1017 = new Intent(q1017.this, q1003.class);
                    skipto1017.putExtra("Individual", individual);
                    startActivity(skipto1017);
                } else {
                    if (individual.getQ1005() != null && individual.getQ1005().equals("2")) {

                        Intent skipto1017 = new Intent(q1017.this, q1005.class);
                        skipto1017.putExtra("Individual", individual);
                        startActivity(skipto1017);
                    } else {
                        if (individual.getQ1011() != null && individual.getQ1011().equals("8")) {

                            Intent skipto1017 = new Intent(q1017.this, q1011.class);
                            skipto1017.putExtra("Individual", individual);
                            startActivity(skipto1017);
                        } else {

                            Intent skipto1017 = new Intent(q1017.this, q1016.class);
                            skipto1017.putExtra("Individual", individual);
                            startActivity(skipto1017);
                        }
                    }
                }

            }
        });
    }




    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q1017_1:
                if(checked)





                    break;

            case R.id.q1017_2:
                if(checked)




                    break;



            default:

                break;

        }

    }


}





/*
Intent intent = new Intent(q504.this, q1101.class);
                //intent.putExtra("Household", thisHose);
                startActivity(intent);
 */


