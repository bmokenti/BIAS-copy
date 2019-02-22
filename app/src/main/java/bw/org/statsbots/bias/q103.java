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

public class q103 extends AppCompatActivity implements View.OnClickListener, Serializable {


    protected LibraryClass lib;
    protected  RadioButton rbtn1,rbtn2,rbtn3, selected=null;
    protected RadioGroup rbtngroup;
    protected HouseHold thisHouse;
    protected Individual individual;
    protected RadioButton selectedRbtn;
    protected DatabaseHelper myDB;
    protected PersonRoster p1 ;
    Individual pp1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q103);
        setTitle("Q103: EDUCATION");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q103_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q103_2);
        rbtn3 =  (RadioButton)findViewById(R.id.q103_3);
        final RadioGroup rg = (RadioGroup)findViewById(R.id.q103radioGroup);

        myDB = new DatabaseHelper(q103.this);
        myDB.onOpen(myDB.getReadableDatabase());
        myDB.getWritableDatabase();

        //myDB.getIndividualdt();
//        final int selectedId = rbtngroup.getCheckedRadioButtonId();


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");

        Intent ii = getIntent();
        p1 = (PersonRoster) ii.getSerializableExtra("Personroster");




        myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());
        //int p = 0;



        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q103.this);
                    builder.setTitle("Q103: Attended school?");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Have ever attended school?");
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


                }
                else {


                    if ((p1.getP09().equals("1") && !rbtn1.isChecked()) || (p1.getP09().equals("2") && !rbtn2.isChecked())  ||(p1.getP09().equals("3") && !rbtn3.isChecked())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q103.this);
                        builder.setTitle("Confirm School attendance");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);
                        builder.setMessage("School attendance does not match ");
                        builder.setPositiveButton("No changes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                individual.setQ103(selectedRbtn.getText().toString().substring(0, 1));

                                //Restart the current activity for next individual

                                //individual.setQ101(selectedRbtn.getText().toString().substring(0, 1));


                                //Check if individual already been saved and update

                                myDB.onOpen(myDB.getReadableDatabase());

                                if (myDB.checkIndividual(individual)) {

                                    //Update
                                    myDB.updateIndividual(myDB.getWritableDatabase(), individual);

                                } else {
                                    //Insert
                                    myDB.insertIndividual(individual);

                                }
                                if(rbtn3.isChecked()) {
                                    Intent q1o2 = new Intent(q103.this, q105.class);
                                    q1o2.putExtra("Individual", individual);
                                    q1o2.putExtra("Personroster", p1);
                                    startActivity(q1o2);
                                }
                                else
                                {
                                    Intent q1o2 = new Intent(q103.this, q104.class);
                                    q1o2.putExtra("Individual", individual);
                                    q1o2.putExtra("Personroster", p1);
                                    startActivity(q1o2);
                                }
                            }

                        });
                        builder.setNegativeButton("Ammend", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                myDB = new DatabaseHelper(q103.this);
                                myDB.onOpen(myDB.getReadableDatabase());

                                p1.setP09(selectedRbtn.getText().toString().substring(0, 1));

                                myDB.updateConsents("P09", p1.getAssignmentID(), p1.getBatch(), p1.getP09(), String.valueOf(p1.getSRNO()));
                                //Restart the current activity for next individual

                                //Check if individual already been saved and update

                                individual.setQ103(selectedRbtn.getText().toString().substring(0, 1));

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

                        AlertDialog alertDialog = builder.show();
                        final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        final Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                        LinearLayout.LayoutParams negativeButtonLL = (LinearLayout.LayoutParams) negativeButton.getLayoutParams();

                        positiveButton.setTextColor(Color.WHITE);
                        negativeButton.setTextColor(Color.WHITE);

                        positiveButton.setBackgroundColor(Color.parseColor("#3180e9"));
                        negativeButton.setBackgroundColor(Color.parseColor("#3180e9"));

                        positiveButtonLL.leftMargin = 10;

                        negativeButtonLL.weight = 10;
                        positiveButtonLL.weight = 10;

                        positiveButton.setLayoutParams(positiveButtonLL);
                        negativeButton.setLayoutParams(negativeButtonLL);

                    }
                else {


                        if (rbtn3.isChecked()) {
                            individual.setQ103(selectedRbtn.getText().toString().substring(0, 1));

                            if (myDB.checkIndividual(individual)) {
                                //Update
                                myDB.updateIndividual(myDB.getWritableDatabase(), individual);

                            }
                            Intent q1o3 = new Intent(q103.this, q105.class);
                            q1o3.putExtra("Individual", individual);
                            q1o3.putExtra("Personroster", p1);
                            startActivity(q1o3);

                        } else {
                            //Set q101 for the current individual
                            individual.setQ103(selectedRbtn.getText().toString().substring(0, 1));


                            if (myDB.checkIndividual(individual)) {
                                //Update
                                myDB.updateIndividual(myDB.getWritableDatabase(), individual);

                            }


                            ////*********************************************

                            Intent q1o3 = new Intent(q103.this, q104.class);
                            q1o3.putExtra("Individual", individual);
                            q1o3.putExtra("Personroster", p1);
                            startActivity(q1o3);
                            //setting q103 value
                        }
                    }

                }
            }
        });

        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q103.super.onBackPressed();
            }


        });
    }








    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.q103_1:
                selected = (RadioButton)findViewById(R.id.q103_1);

                //startActivity(intent3);
                break;

            case R.id.q103_2:
                selected = (RadioButton)findViewById(R.id.q103_2);
               // startActivity(intent4);
                break;


            default:
                break;

        }
    }
}

