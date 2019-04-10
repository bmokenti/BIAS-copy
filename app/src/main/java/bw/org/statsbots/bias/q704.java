package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class q704 extends AppCompatActivity implements Serializable {

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
        setContentView(R.layout.activity_q704);

        setTitle("q704  HIV, AIDS and TB ATTITUDES ");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q704_1);
        rbtn2 = (RadioButton) findViewById(R.id.q704_2);

        rbtngroup = (RadioGroup) findViewById(R.id.q704radioGroup);


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

           thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);


        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();
        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }
//
//
//        if (sample.getStatusCode().equals("1") || (sample.getStatusCode().equals("2") &&thisHous.get(0).getHIVTB40().equals("1") &&
//                (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 )))

        if( sample.getStatusCode().equals("1") || ((sample.getStatusCode().equals("2") &&thisHous.get(0).getHIVTB40().equals("1")) &&
               (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 ) ))
        {

            individual.setQ704(null);
            individual.setQ705(null);


            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();


            Intent q1o2 = new Intent(q704.this, q801.class);
            q1o2.putExtra("Individual", individual);
            startActivity(q1o2);
        }

        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rbtngroup.getChildCount();f++)
        {
            View o = rbtngroup.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ704()!= null)
                {
                    if(Integer.parseInt(ind.getQ704())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }


        /**
         * NEXT question
         */
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
                    lib.showError(q704.this, "Q704 Error", "Please select an option for q704");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    individual.setQ704(selected.getText().toString().substring(0, 1));
                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.getWritableDatabase();
                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                    myDB.close();
                    Intent intent = new Intent(q704.this, q705.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);

                }
            }
        });

        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(( individual.getQ604() != null && individual.getQ604().equals("2")) && ((sample.getStatusCode().equals("3") )  || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0") )
                        || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) && Integer.valueOf(individual.getQ102()) > 64
                ) ||((sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1")) &&
                        p1.getP06().equals("2")  ) ))
                    {
                        Intent q1o2 = new Intent(q704.this, q604.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);
                        finish();

                    }
                    else
                    {
                        if( ((sample.getStatusCode().equals("3") )  || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0") )
                                || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) && Integer.valueOf(individual.getQ102()) > 64
                        ) ||((sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1")) &&
                                p1.getP06().equals("2")  ) ))
                        {
                            finish();
                            Intent q1o2 = new Intent(q704.this, q622.class);
                            q1o2.putExtra("Individual", individual);
                            startActivity(q1o2);

                        }
                        else
                        {
                            finish();
                            Intent intent = new Intent(q704.this, q703.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);
                        }
                    }
                }

        });
    }

    //   thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

//    final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
//        for (PersonRoster p: roster
//        ) {
//        if (p.getSRNO() == ind.getSRNO()){
//            p1 = p;
//            break;
//        }
//    }


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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q704.this).toBundle());

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