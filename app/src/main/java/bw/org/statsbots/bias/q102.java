package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q102 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected  PersonRoster p1;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected EditText edt;
    protected DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q102);

        setTitle("Q102 Age");

        lib = new LibraryClass();
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        //edittext = (EditText) findViewById(R.id.q102_years);
        //edittext.setVisibility(View.VISIBLE);
        edt = (EditText) findViewById(R.id.q102_years);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;




        Intent intent = getIntent();
        p1 = (PersonRoster) intent.getSerializableExtra("Personroster");

        myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());


        final Individual ind = myDB.getdataIndivisual(p1.getAssignmentID(),p1.getBatch(),p1.getSRNO());
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


       // Log.d("adasdasd",ind.getQ101()+"");
         if(ind.getQ102()!= null)
         {
                edt.setText(ind.getQ102());
         }





        //myDB.getIndividualdt();



/*
        final Sample sample = myDB.getSample(myDB.getReadableDatabase(),individual.getAssignmentID());
        sample.getStatusCode();



        if(sample.getStatusCode().equals("2"))

        {
            Intent intent = new Intent(q102.this, q104.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

        if(sample.getStatusCode().equals("3"))

        {
            Intent intent = new Intent(q102.this, q105.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

*/

        /**
         * NEXT Person BUTTON
         */
        Button btnNext = (Button) findViewById(R.id.button);


        //btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Years;
                edt = (EditText) findViewById(R.id.q102_years);
                String years = edt.getText().toString();

                if (years.length() == 0 || years.equals("00") || years.equals("") || Integer.valueOf(edt.getText().toString()) <= 14) {
                    lib.showError(q102.this, "q102 Error", "Please enter age in completd years, Age must be 15 and above");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                }
                else {


                    if ((Integer.parseInt(p1.getP04YY()) < Integer.valueOf(edt.getText().toString())) || (Integer.parseInt(p1.getP04YY()) > Integer.valueOf(edt.getText().toString()))) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q102.this);
                        builder.setTitle("Confirm Age");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);
                        builder.setMessage("Age entered does not match with P04 Age ("+ p1.getP04YY()+ "). " +
                                "Do you want to overide P04 age?");
                        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                individual.setQ102(edt.getText().toString());

                                //Restart the current activity for next individual


                                //Check if individual already been saved and update
                                myDB = new DatabaseHelper(q102.this);
                                myDB.onOpen(myDB.getReadableDatabase());


                                    //Update
                                    myDB.updateIndividual(myDB.getWritableDatabase(), individual);



                            }

                        });
                        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                myDB = new DatabaseHelper(q102.this);
                                myDB.onOpen(myDB.getReadableDatabase());

                                p1.setP04YY(edt.getText().toString());
                                myDB.updateConsents("P04_YY", p1.getAssignmentID(), p1.getBatch(), p1.getP04YY(), String.valueOf(p1.getSRNO()));
                                //Restart the current activity for next individual

                                //Check if individual already been saved and update

                                individual.setQ102(edt.getText().toString());
                                myDB.updateIndividual(myDB.getWritableDatabase(), individual);


                                Intent intent = new Intent(q102.this, q103.class);
                                intent.putExtra("Individual", individual);
                                intent.putExtra("Personroster", p1);
                                startActivity(intent);

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

                    } else {
                        //Set Q102 for the current individual

                        individual.setQ102(edt.getText().toString());

                        if (myDB.checkIndividual(individual)) {
                            //Update
                                myDB.updateInd("Q102",individual.getAssignmentID(),individual.getBatch(),individual.getQ102(),String.valueOf(individual.getSRNO()));
                                myDB.close();
                        }
                        //Next question P17
                        Intent intent = new Intent(q102.this, q103.class);
                        intent.putExtra("Individual", individual);
                        intent.putExtra("Personroster", p1);
                        startActivity(intent);


                    }

                }

            }
        });


        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent q1o2 = new Intent(q102.this, q101.class);
                q1o2.putExtra("Personroster", p1);
                startActivity(q1o2);
            }


        });
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("[Demo!] Are you sure you want to pause the interview");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(getApplicationContext(), started_household.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q102.this).toBundle());

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


/*
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.q102_years:
                Intent intent4 = new Intent(this, q103.class);
                startActivity(intent4);
                break;


        }
    }*/
