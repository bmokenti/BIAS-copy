package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class q202 extends AppCompatActivity implements View.OnClickListener, Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;

    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2;
    protected RadioGroup rbtngroup;
    PersonRoster p1 = null;
    protected RadioButton selectedRbtn;
    protected  DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q202);



           setTitle("Q202: MARITAL STATUS AND RELATIONSHIP");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q202_1);
        rbtn2 = (RadioButton) findViewById(R.id.q202_2);



        final RadioGroup rg = (RadioGroup) findViewById(R.id.q202radioGroup);



        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        //int p = 0;



        Button btnext = findViewById(R.id.btnnext);
//        PersonRoster pr[] = thisHouse.getPersons();
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }


        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();
        RadioButton[] bt1 = new RadioButton[2];

        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt1[f]=((RadioButton)o);
                if(ind.getQ202()!= null &&  !ind.getQ202().equals(""))
                {
                    if(Integer.parseInt(ind.getQ202())==f+1)
                    {
                        RadioButton radioButton = bt1[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }


        btnext.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q202.this);
                    builder.setTitle("Marital Status?");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Have you ever lived together with a partner as if you were married?");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                    AlertDialog alertDialog = builder.show();
                    final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                    positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    positiveButton.setTextColor(Color.WHITE);
                    positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                    positiveButton.setLayoutParams(positiveButtonLL);


                } else {
                    if (rbtn2.isChecked()) {
                        individual.setQ202(selectedRbtn.getText().toString().substring(0, 1));
                        myDB = new DatabaseHelper(q202.this);
                        myDB.onOpen(myDB.getReadableDatabase());

                        myDB.updateInd("Q202",individual.getAssignmentID(),individual.getBatch(),ind.getQ202(),String.valueOf(individual.getSRNO()));
                        myDB.updateInd("Q203",individual.getAssignmentID(),individual.getBatch(),"00",String.valueOf(individual.getSRNO()));
                        myDB.updateInd("Q204",individual.getAssignmentID(),individual.getBatch(),"00",String.valueOf(individual.getSRNO()));
                        myDB.updateInd("Q205",individual.getAssignmentID(),individual.getBatch(),null,String.valueOf(individual.getSRNO()));
                        myDB.updateInd("Q205a",individual.getAssignmentID(),individual.getBatch(),null,String.valueOf(individual.getSRNO()));

                        myDB.close();


                        Intent skipto203 = new Intent(q202.this, q301.class);
                        skipto203.putExtra("Individual", individual);
                        startActivity(skipto203);


                    } else {
                        //Set q201 for the current individual
                        individual.setQ202(selectedRbtn.getText().toString().substring(0, 1));

                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());

                        //Next question q102

                        myDB = new DatabaseHelper(q202.this);
                        myDB.onOpen(myDB.getReadableDatabase());

                        myDB.updateInd("Q202",individual.getAssignmentID(),individual.getBatch(),ind.getQ202(),String.valueOf(individual.getSRNO()));

                        myDB.close();

                        Intent q1o2 = new Intent(q202.this, q203.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);

                    }

                }
            }
            });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent q1o3 = new Intent(q202.this, Q201.class);
                q1o3.putExtra("Individual", individual);
                q1o3.putExtra("Personroster", p1);
                startActivity(q1o3);
            }


        });

        }




        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.q202_1:

                    break;

                case R.id.q202_2:
                    break;


                default:
                    break;

            }
        }

    }










