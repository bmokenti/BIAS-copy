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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q305 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected CheckBox chk1, chk2, chk3, chk4;
    PersonRoster p1 = null;
    protected DatabaseHelper myDB;
    Individual pp1 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q305);


        setTitle("Q305: ALCOHOL CONSUMPTION AND SUBSTANCE USE");
        lib = new LibraryClass();
        chk1 = (CheckBox) findViewById(R.id.q305_1);
        chk2 = (CheckBox) findViewById(R.id.q305_2);
        chk3 = (CheckBox) findViewById(R.id.q305_3);
        chk4 = (CheckBox) findViewById(R.id.q305_4);

        // txt2 = (TextView) findViewById(R.id.q205atxt);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;
        Intent ii = getIntent();
        p1 = (PersonRoster) ii.getSerializableExtra("Personroster");

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());

        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;




        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p : roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }

        if (sample.getStatusCode().equals("1") || (sample.getStatusCode().equals("2") &&thisHous.get(0).getHIVTB40().equals("1") &&
                (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 )))
        {

            Intent q1o2 = new Intent(q305.this, q307.class);
            q1o2.putExtra("Individual", individual);
            startActivity(q1o2);
        }


                if(individual.getQ305_1()!= null &&  !individual.getQ305_1().equals(""))
                {
                    if(Integer.parseInt(individual.getQ305_1())== 1)
                    {
                       chk1.setChecked(true);

                    }else
                    {
                        chk1.setChecked(false);
                }
            }

        if(ind.getQ305_2()!= null &&  !ind.getQ305_2().equals(""))
        {
            if(Integer.parseInt(ind.getQ305_2())== 1)
            {
                chk2.setChecked(true);

            }else
            {
                chk2.setChecked(false);
            }
        }

        if(ind.getQ305_3()!= null &&  !ind.getQ305_3().equals(""))
        {
            if(Integer.parseInt(ind.getQ305_3())== 1)
            {
                chk3.setChecked(true);

            }else
            {
                chk3.setChecked(false);
            }
        }

        if(ind.getQ305_4()!= null &&  !ind.getQ305_4().equals(""))
        {
            if(Integer.parseInt(ind.getQ305_4())== 1)
            {
                chk4.setChecked(true);

            }else
            {
                chk4.setChecked(false);
            }
        }





        Button btnext = findViewById(R.id.button);


        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!chk1.isChecked() && !chk2.isChecked() && !chk3.isChecked() && !chk4.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q305.this);
                    builder.setTitle("Q305: Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage(" Have you used tobacco through smoking, sniffing or chewing in the past 3 months? " +
                            "(By tobacco products we mean as cigarettes, cigars or pipes))");
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
                        //Set q305 for the current individual



                    if(chk1.isChecked()){
                        individual.setQ305_1("1");
                    }else{
                        individual.setQ305_1("2");
                    }

                    if(chk2.isChecked()){
                        individual.setQ305_2("1");
                    }else{
                        individual.setQ305_2("2");
                    }


                    if(chk3.isChecked()){
                        individual.setQ305_3("1");
                    }else{
                        individual.setQ305_3("2");
                    }


                    if(chk4.isChecked()){
                        individual.setQ305_4("1");
                    }else{
                        individual.setQ305_4("2");
                    }

                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.getWritableDatabase();
                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                    myDB.close();

                        Intent q1o2 = new Intent(q305.this, q306.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);

                    }

                }


        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (individual.getQ302() != null && individual.getQ302().equals("2")) {

                    Intent q1o2 = new Intent(q305.this, q302.class);
                    q1o2.putExtra("Individual", individual);
                   // q1o2.putExtra("Personroster", p1);
                    startActivity(q1o2);
                } else {
                    // ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1"))  && (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 ))
                    if ((((sample.getStatusCode().equals("3")) || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0")))
                            || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) && Integer.valueOf(individual.getQ102()) > 64)
                            || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) && p1.getP06().equals("2")))) {


                        Intent q1o2 = new Intent(q305.this, q107.class);
                        q1o2.putExtra("Individual", individual);
                       // q1o2.putExtra("Personroster", p1);
                        startActivity(q1o2);
                    } else {
                        Intent q1o2 = new Intent(q305.this, q304.class);
                        q1o2.putExtra("Individual", individual);
                       //q1o2.putExtra("Personroster", p1);
                        startActivity(q1o2);
                    }
                }
            }

        });

    }

    public void onCheckboxClicked(View v) {
        // Is the view now checked?
        boolean checked = ((CheckBox) v).isChecked();

        switch (v.getId()) {
            case R.id.q305_1:
                if (checked) {

                }


                break;

            case R.id.q305_2:
                if (checked) {

                }
                break;

            case R.id.q305_3:
                if (checked) {
                    
                }
                break;
            case R.id.q305_4:
                if (checked) {

                    chk1.setEnabled(false);
                    chk2.setEnabled(false);
                    chk3.setEnabled(false);

                    chk1.setChecked(false);
                    chk2.setChecked(false);
                    chk3.setChecked(false);
                }else
                {
                    chk1.setEnabled(true);
                    chk2.setEnabled(true);
                    chk3.setEnabled(true);
                }

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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q305.this).toBundle());

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

