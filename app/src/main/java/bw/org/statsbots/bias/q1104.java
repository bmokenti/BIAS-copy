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

public class q1104 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected  Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2, selectedRbtn, selected = null;
    protected RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1104);


        setTitle("Q1104:");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q1104_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1104_2);
        rg = (RadioGroup) findViewById(R.id.q1104radioGroup);

        rbtn1.setOnClickListener(this);
        rbtn2.setOnClickListener(this);


        final int selectedId = rg.getCheckedRadioButtonId();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        //int p = 0;

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

        if (sample.getStatusCode().equals("1") || (sample.getStatusCode().equals("2") &&thisHous.get(0).getHIVTB40().equals("1") &&
                (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 )))
        {


            individual.setQ1104(null);
            individual.setQ1105(null);
            individual.setQ1106(null);
            individual.setQ1106a(null);
            individual.setQ1106b(null);
            individual.setQ1106bOther(null);

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q1104.this, q1107.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        }
        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ1104()!= null )
                {
                    if(Integer.parseInt(ind.getQ1104())==f+1)
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(q1104.this);
                    builder.setTitle("Q1104:");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Do you cough up sputum? (FIELD WORKER EXPLAIN)");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id)
                        {

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

                   // individual.setQ1104(selectedRbtn.getText().toString().substring(0,1));
                    if (rbtn2.isChecked()) {
                        individual.setQ1104(selectedRbtn.getText().toString().substring(0,1));

                        individual.setQ1105(null);
                        individual.setQ1106(null);
                        individual.setQ1106a(null);
                        individual.setQ1106b(null);
                        individual.setQ1106bOther(null);
                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        myDB.close();
                        Intent q11047 = new Intent(q1104.this, q1107.class);
                        q11047.putExtra("Individual", individual);
                        startActivity(q11047);

                    } else {
                        //Set q1105 for the current individual

                        individual.setQ1104(selectedRbtn.getText().toString().substring(0,1));

                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        myDB.close();
                        //Next question P04
                        Intent intent = new Intent(q1104.this, q1105.class);
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
                finish();
                Intent intent = new Intent(q1104.this, q1103.class);
                intent.putExtra("Individual", individual);
                startActivity(intent);
            }


        });
    }







    @Override
    public void onClick(View view) {
        //
        switch (view.getId()) {
            case R.id.q1104_1:
                //Intent intent3 = new Intent(this, q1104.class);
                //startActivity(intent3);
                break;


            case R.id.q1104_2:
                //Intent intent4 = new Intent(this, q1104.class);
                //startActivity(intent4);
                break;



        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intervie_control, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q1104.this).toBundle());

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


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

