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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class q903 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;protected DatabaseHelper myDB;
    protected Button btn;
    protected Individual individual;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtn8, rbtn9, rbtn10, rbtn11, rbtn12, rbtn13, rbtn14;
    protected RadioGroup rg1,  rg2, rg3, rg4, rg5, rg6, rg7;
    protected RadioButton selectedRbtn1, selectedRbtn2, selectedRbtn3, selectedRbtn4, selectedRbtn5, selectedRbtn6, selectedRbtn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q903);



        setTitle("Q903:  HIV SUPPORT, CARE ANF TREATMENT");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg1 = (RadioGroup)findViewById(R.id.q903aGroup1) ;
        rbtn1 = (RadioButton) findViewById(R.id.q903a_1);
        rbtn2 = (RadioButton) findViewById(R.id.q903a_2);

        rg2 = (RadioGroup)findViewById(R.id.q903bGroup2) ;
        rbtn2 = (RadioButton) findViewById(R.id.q903b_1);
        rbtn3 = (RadioButton) findViewById(R.id.q903b_2);

        rg3 = (RadioGroup)findViewById(R.id.q903cGroup) ;
        rbtn5 = (RadioButton) findViewById(R.id.q903c_1);
        rbtn6 = (RadioButton) findViewById(R.id.q903c_2);

        rg4 = (RadioGroup)findViewById(R.id.q903dGroup) ;
        rbtn7 = (RadioButton) findViewById(R.id.q903d_1);
        rbtn8 = (RadioButton) findViewById(R.id.q903d_2);

        rg5 = (RadioGroup)findViewById(R.id.q903eGroup) ;
        rbtn9 = (RadioButton) findViewById(R.id.q903e_1);
        rbtn10 = (RadioButton) findViewById(R.id.q903e_2);

        rg6 = (RadioGroup)findViewById(R.id.q903fGroup) ;
        rbtn11 = (RadioButton) findViewById(R.id.q903f_1);
        rbtn12 = (RadioButton) findViewById(R.id.q903f_2);

        rg7 = (RadioGroup)findViewById(R.id.q903gGroup) ;
        rbtn13 = (RadioButton) findViewById(R.id.q903g_1);
        rbtn14 = (RadioButton) findViewById(R.id.q903g_2);






        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

           thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

    final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
        if (p.getSRNO() == ind.getSRNO()){
            p1 = p;
            break;
        }
    }

        RadioButton[] bt1 = new RadioButton[2];
        for(int f=0;f<rg1.getChildCount();f++)
        {
            View o = rg1.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt1[f]=((RadioButton)o);
                if(ind.getQ903a()!= null )
                {
                    if(!ind.getQ903a().equals(""))
                    {
                    if(Integer.parseInt(ind.getQ903a())==f+1) {
                        RadioButton radioButton = bt1[f];
                        radioButton.setChecked(true);
                        break;
                    }
                    }
                }
            }
        }


        RadioButton[] bt2 = new RadioButton[2];
        for(int f=0;f<rg2.getChildCount();f++)
        {
            View o = rg2.getChildAt(f);
            if (o instanceof RadioButton) {
                bt2[f] = ((RadioButton) o);
                if (ind.getQ903b() != null) {
                    if (!ind.getQ903b().equals("")) {
                        if (Integer.parseInt(ind.getQ903b()) == f + 1) {
                            RadioButton radioButton = bt2[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }


        RadioButton[] bt3 = new RadioButton[2];
        for(int f=0;f<rg3.getChildCount();f++) {
            View o = rg3.getChildAt(f);
            if (o instanceof RadioButton) {
                bt3[f] = ((RadioButton) o);
                if (ind.getQ903c() != null) {
                    if (!ind.getQ903c().equals("")) {
                        if (Integer.parseInt(ind.getQ903c()) == f + 1) {
                            RadioButton radioButton = bt3[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }



        RadioButton[] bt4 = new RadioButton[2];
        for(int f=0;f<rg4.getChildCount();f++)
        {
            View o = rg4.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt4[f]=((RadioButton)o);
                if(ind.getQ903d()!= null ) {
                    if (!ind.getQ903d().equals("")) {
                        if (Integer.parseInt(ind.getQ903d()) == f + 1) {
                            RadioButton radioButton = bt4[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }
        RadioButton[] bt5 = new RadioButton[2];
        for(int f=0;f<rg5.getChildCount();f++)
        {
            View o = rg5.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt5[f]=((RadioButton)o);
                if(ind.getQ903e()!= null )
                {
                    if(!ind.getQ903e().equals("")) {
                        if (Integer.parseInt(ind.getQ903e()) == f + 1) {
                            RadioButton radioButton = bt5[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }


        RadioButton[] bt6 = new RadioButton[2];
        for(int f=0;f<rg6.getChildCount();f++)
        {
            View o = rg6.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt6[f]=((RadioButton)o);
                if(ind.getQ903f()!= null ) {
                    if (!ind.getQ903f().equals("")) {
                        if (Integer.parseInt(ind.getQ903f()) == f + 1) {
                            RadioButton radioButton = bt6[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }

        RadioButton[] bt7= new RadioButton[2];
        for(int f=0;f<rg7.getChildCount();f++)
        {
            View o = rg7.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt7[f]=((RadioButton)o);
                if(ind.getQ903g()!= null )
                {
                    if(!ind.getQ903g().equals("")) {
                        if (Integer.parseInt(ind.getQ903g()) == f + 1) {
                            RadioButton radioButton = bt7[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }



        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg1.getCheckedRadioButtonId();
                selectedRbtn1 = (RadioButton) findViewById(selectedId);

                if (selectedRbtn1 == null) {
                    lib.showError(q903.this, "Q90a: month", "BY BEING DENIED CARE: please select yes/no");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }
                else {

                    int selectedId1 = rg2.getCheckedRadioButtonId();
                    selectedRbtn2 = (RadioButton) findViewById(selectedId1);

                    if (selectedRbtn2 == null) {
                        lib.showError(q903.this, "Q902b: year", "BY BEING THE SUBJECT OF GOSSIP: please select yes/no");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }

                    else {
                        int selectedId2 = rg3.getCheckedRadioButtonId();
                        selectedRbtn3 = (RadioButton) findViewById(selectedId2);


                        if (selectedRbtn3 == null) {
                            lib.showError(q903.this, "Q902c: month", "BY BEING ADVISED NOT TO HAVE SEX: please select yes/no");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {

                            int selectedId3 = rg4.getCheckedRadioButtonId();
                            selectedRbtn4 = (RadioButton) findViewById(selectedId3);

                            if (selectedRbtn4 == null) {
                                lib.showError(q903.this, "Q902d: year", "BY BEING VERBALLY ABUSED");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {
                                int selectedId4 = rg5.getCheckedRadioButtonId();
                                selectedRbtn5 = (RadioButton) findViewById(selectedId4);

                                if (selectedRbtn5 == null) {
                                    lib.showError(q903.this, "Q902e: month", "BY BEING PHYSICALLY ABUSED: please select yes/no");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);
                                } else {

                                    int selectedId5 = rg6.getCheckedRadioButtonId();
                                    selectedRbtn6 = (RadioButton) findViewById(selectedId5);

                                    if (selectedRbtn6 == null) {
                                        lib.showError(q903.this, "Q902f: year", "THROUGH PEOPLE AVOIDING PHYSICAL CONTACT WITH YOU");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);
                                    } else {
                                        int selectedId6 = rg7.getCheckedRadioButtonId();
                                        selectedRbtn7 = (RadioButton) findViewById(selectedId6);


                                        if (selectedRbtn7 == null) {
                                            lib.showError(q903.this, "Q902g: month", "THROUGH SHARING OF HIV STATUS WITHOUT CONSENT");
                                            /**
                                             * VIBRATE DEVICE
                                             */
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);
                                        }
                                        else {
                                           individual.setQ903a(selectedRbtn1.getText().toString().substring(0,1));
                                           individual.setQ903b(selectedRbtn2.getText().toString().substring(0,1));
                                            individual.setQ903c(selectedRbtn3.getText().toString().substring(0,1));
                                            individual.setQ903d(selectedRbtn4.getText().toString().substring(0,1));
                                           individual.setQ903e(selectedRbtn5.getText().toString().substring(0,1));
                                            individual.setQ903f(selectedRbtn6.getText().toString().substring(0,1));
                                            individual.setQ903g(selectedRbtn7.getText().toString().substring(0,1));
                                          //  individual.setQ903(selectedRbtn7.getText().toString().substring(0,1));

                                            myDB.onOpen(myDB.getReadableDatabase());
                                            myDB.getWritableDatabase();
                                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                            myDB.close();



                                            Intent intent = new Intent(q903.this, q904.class);
                                            intent.putExtra("Individual", individual);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }

        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q903.super.onBackPressed();
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q903.this).toBundle());

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
Intent intent = new Intent(q504.this, q1101.class);
                //intent.putExtra("Household", thisHose);
                startActivity(intent);
 */


