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
        int p = 0;

        Intent h = getIntent();
        thisHouse = (HouseHold) h.getSerializableExtra("Household");


        Intent intent = getIntent();
        p1 = (PersonRoster) intent.getSerializableExtra("Personroster");

        myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());



        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();


//
//        if(Integer.valueOf(p1.getP04YY()) <=17 && sample.getSTATUS().equals("2") )
//        {
//            setTitle("Questionnaire Assent age 15-17 years");
//        }
//        else {
//            setTitle("Individual  18 plus: Combined");
//        }
//
//
//        if(Integer.valueOf(p1.getP04YY()) <=17 && sample.getSTATUS().equals("3") )
//        {
//            setTitle("Questionnaire Assent age 15-17 years");
//        }
//        else
//        {
//            setTitle("Individual Questionnaire Ov18: TB Only");
//        }

        Button btnext = findViewById(R.id.btnnext);
//        PersonRoster pr[] = thisHouse.getPersons();
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
                        builder.setMessage("Sex does not match?");
                        builder.setPositiveButton("No changes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                individual.setQ101(selectedRbtn.getText().toString().substring(0, 1));

                                //Restart the current activity for next individual

                                //    individual.setQ101(selectedRbtn.getText().toString().substring(0, 1));


                                    //Check if individual already been saved and update
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

                                });
                        builder.setNegativeButton("Ammend", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                myDB = new DatabaseHelper(q101.this);
                                myDB.onOpen(myDB.getReadableDatabase());

                                p1.setP03(selectedRbtn.getText().toString().substring(0, 1));
                                myDB.updateConsents("P03", p1.getAssignmentID(), p1.getBatch(), p1.getP03(), String.valueOf(p1.getSRNO()));
                                //Restart the current activity for next individual

                                    //Check if individual already been saved and update

                                individual.setQ101(selectedRbtn.getText().toString().substring(0, 1));

                                    if (myDB.checkIndividual(individual)) {
                                        //Update

                                        myDB.updateIndividual(myDB.getWritableDatabase(), individual);

                                    } else {
                                        //Insert
                                        myDB.insertIndividual(individual);

                                    }

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
                                myDB.updateIndividual(myDB.getWritableDatabase(), individual);

                            } else {
                                //Insert
                                myDB.insertIndividual(individual);

                            }


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

}







