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

public class q307 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2 ;
    protected RadioGroup rbtngroup;
    protected RadioButton selectedRbtn;protected DatabaseHelper myDB;
    PersonRoster p1 = null;
    Individual pp1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q307);




        setTitle("Q307: ALCOHOL CONSUMPTION AND SUBSTANCE USE");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q307_1);
        rbtn2 = (RadioButton) findViewById(R.id.q307_2);



        final RadioGroup rg = (RadioGroup) findViewById(R.id.q307radioGroup);
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;

        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), ind.getAssignmentID());
        sample.getSTATUS();

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }
        Button btnext = findViewById(R.id.button);
//        PersonRoster pr[] = thisHouse.getPersons();


        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ307()!= null &&  !ind.getQ307().equals(""))
                {
                    if(Integer.parseInt(ind.getQ307())==f+1)
                    {
                        RadioButton radioButton = bt[f];
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(q307.this);
                    builder.setTitle("Q307: ERROR?");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("In the past 12 months have you ever seen or heard any anti-alcohol & substance abuse messages?");
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
                        //Set q101 for the current individual
                       individual.setQ307(selectedRbtn.getText().toString().substring(0, 1));

                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.getWritableDatabase();
                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                    myDB.close();

                    /**
                     * If current person LineNumber is equal to TotalPersons-1
                     * Proceed to next Question in the roster
                     */
                        // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());

                        //Next question q102


                        Intent q1o2 = new Intent(q307.this, q401.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);

                    }

                }

        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (sample.getStatusCode().equals("1") || (sample.getStatusCode().equals("2") &&thisHous.get(0).getHIVTB40().equals("1") &&
                        (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 )))
                {
                    finish();
                    Intent q1o2 = new Intent(q307.this, q304.class);
                    q1o2.putExtra("Individual", individual);
                    startActivity(q1o2);
                }
                else {
                finish();
                Intent q1o2 = new Intent(q307.this, q306.class);
                q1o2.putExtra("Individual", individual);
                startActivity(q1o2);
            }





                //q307.super.onBackPressed();
            }


        });

    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.q307_1:

                break;

            case R.id.q307_2:
                break;


            default:
                break;

        }
    }
    //   thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q307.this).toBundle());

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










