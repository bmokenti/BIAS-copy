package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import java.io.Serializable;
import java.util.List;

public class q101 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, selected = null;
    protected RadioGroup rbtngroup;
    protected DatabaseHelper myDB;
    protected RadioButton selectedRbtn;
    protected PersonRoster p1;
    Individual pp1 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q101);

        setTitle("Q101: SEX");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q101_1);
        rbtn2 = (RadioButton) findViewById(R.id.q101_2);
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        final RadioGroup rg = (RadioGroup) findViewById(R.id.q101radioGroup);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;

        Intent h = getIntent();
        thisHouse = (HouseHold) h.getSerializableExtra("Household");


        Intent intent = getIntent();
        p1 = (PersonRoster) intent.getSerializableExtra("Personroster");
        Log.d("Person Roster",p1.getSRNO()+"");

        Individual ind = myDB.getdataIndivisual(p1.getAssignmentID(),p1.getBatch(),p1.getSRNO());
        individual = ind;
        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }

                    RadioButton[] bt = new RadioButton[2];
                    for(int f=0;f<rg.getChildCount();f++)
                    {
                        View o = rg.getChildAt(f);
                        if (o instanceof RadioButton)
                        {
                            bt[f]=((RadioButton)o);
                            if(ind.getQ101()!= null &&  !ind.getQ101().equals(""))
                            {
                                if(Integer.parseInt(ind.getQ101())==f+1)
                                {
                                    RadioButton radioButton = bt[f];
                                    radioButton.setChecked(true);
                                    break;
                                }
                            }else{
                                Log.d("h1333333 Lost Here","**********    " + ind.getQ101());
                            }
                        }
                        else
                        {
                            Log.d("h13 Lost Here","**********");
                        }
                    }



        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();


        Button btnext = findViewById(R.id.btnnext);
        Button btprev= findViewById(R.id.button3);
//        PersonRoster pr[] = thisHouse.getPersons();
        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HouseHold thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

                Intent q1o2 = new Intent(q101.this, started_household.class);
                q1o2.putExtra("Household", thisHouse);

                startActivity(q1o2);
            }
        });
        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q101.this);
                    builder.setTitle("Select sex of respondent");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Please select gender");
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


                    if ((p1.getP03().equals("1") && rbtn2.isChecked())  || (p1.getP03().equals("2") && rbtn1.isChecked())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q101.this);
                        builder.setTitle("Confirm Sex");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);
                        builder.setMessage("Sex does not match with Sex at Household");
                        /*builder.setPositiveButton("Ignore", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                individual.setQ101(selectedRbtn.getText().toString().substring(0, 1));

                                    myDB = new DatabaseHelper(q101.this);
                                    myDB.onOpen(myDB.getReadableDatabase());

                                    if (myDB.checkIndividual(individual)) {
                                        //Update
                                        myDB.updateIndividual(myDB.getWritableDatabase(), individual);

                                    } else {
                                        //Insert
                                        myDB.insertIndividual(individual);

                                    }
                                Intent q1o2 = new Intent(q101.this, q102.class);
                                q1o2.putExtra("Individual", individual);
                                q1o2.putExtra("Personroster", p1);
                                startActivity(q1o2);
                            }

                                });*/
                        builder.setNegativeButton("Override Sex at Household", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                myDB = new DatabaseHelper(q101.this);
                                myDB.onOpen(myDB.getReadableDatabase());

                                p1.setP03(selectedRbtn.getText().toString().substring(0, 1));
                                myDB.updateConsents("P03", p1.getAssignmentID(), p1.getBatch(), p1.getP03(), String.valueOf(p1.getSRNO()));
                                individual.setQ101(selectedRbtn.getText().toString().substring(0, 1));
                                    if (myDB.checkIndividual(individual)) {
                                        //Update

                                        myDB.updateInd("Q101",individual.getAssignmentID(),individual.getBatch(),individual.getQ101(),String.valueOf(individual.getSRNO()));
                                    } /*else {
                                        //Insert
                                        myDB.insertIndividual(individual);

                                    }*/

                            }
                        });

                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                        AlertDialog alertDialog =  builder.show();
                        final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        final Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                        LinearLayout.LayoutParams negativeButtonLL = (LinearLayout.LayoutParams) negativeButton.getLayoutParams();

                        positiveButton.setTextColor(Color.WHITE);
                        negativeButton.setTextColor(Color.WHITE);

                        positiveButton.setBackgroundColor(Color.parseColor("#3180e9"));
                        negativeButton.setBackgroundColor(Color.parseColor("#3180e9"));

                        positiveButtonLL.leftMargin=10;

                        negativeButtonLL.weight = 10;
                        positiveButtonLL.weight = 10;

                        positiveButton.setLayoutParams(positiveButtonLL);
                        negativeButton.setLayoutParams(negativeButtonLL);

                    }

                        else {
                            //Set q101 for the current individual
                            individual.setQ101(selectedRbtn.getText().toString().substring(0, 1));


                            //Check if individual already been saved and update
                            myDB = new DatabaseHelper(q101.this);
                            myDB.onOpen(myDB.getReadableDatabase());

                            if (myDB.checkIndividual(individual)) {
                                //Update
                                myDB.updateInd("Q101",individual.getAssignmentID(),individual.getBatch(),individual.getQ101(),String.valueOf(individual.getSRNO()));

                            } /*else {
                                //Insert
                                myDB.insertIndividual(individual);

                            }*/


                            /**
                             * If current person LineNumber is equal to TotalPersons-1
                             * Proceed to next Question in the roster
                             */
                            // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());

                            //Next question q102
                            Intent q1o2 = new Intent(q101.this, q102.class);
                            q1o2.putExtra("Individual", individual);
                        q1o2.putExtra("Personroster", p1);
                            startActivity(q1o2);

                        }
                    }

            }
        });


    }


        @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.q101_1:
                selectedRbtn = (RadioButton)findViewById(R.id.q101_1);
                break;

            case R.id.q101_2:
                selectedRbtn = (RadioButton)findViewById(R.id.q101_2);
              break;


            default:
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intervie_control, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {

            case R.id.pause:
                // Show the settings activity
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(q101.this);
                alertDialogBuilder.setMessage("[Demo!] Are you sure you want to pause the interview");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(q101.this, started_household.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q101.this).toBundle());

                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            return  true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}







