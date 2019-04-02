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
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q205 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, rbtna3, rbtna4, rbtna5, rbtna6, rbtna7;
    protected RadioGroup rg, rga;
    protected TextView txt1, txt2;
    PersonRoster p1 = null;
    Individual pp1 = null;protected  DatabaseHelper myDB;
    protected RadioButton selectedRbtn1, selectedRbtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q205);
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        setTitle("Q205: MARITAL STATUS AND RELATIONSHIP");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q205_1);
        rbtn2 = (RadioButton) findViewById(R.id.q205_2);
        rbtna1 = (RadioButton) findViewById(R.id.q205a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q205a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q205a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q205a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q205a_5);
        rbtna6 = (RadioButton) findViewById(R.id.q205a_6);
        rbtna7 = (RadioButton) findViewById(R.id.q205a_7);

        txt1 = (TextView) findViewById(R.id.q205txt);
        txt2 = (TextView) findViewById(R.id.q205atxt);





        rg = (RadioGroup) findViewById(R.id.q205radioGroup);
        rga = (RadioGroup) findViewById(R.id.q205aradioGroup);



        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;

        Button btnext = findViewById(R.id.button);
//        PersonRoster pr[] = thisHouse.getPersons();

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
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt1[f]=((RadioButton)o);
                if(ind.getQ205()!= null &&  !ind.getQ205().equals(""))
                {
                    if(Integer.parseInt(ind.getQ205())==f+1)
                    {
                        RadioButton radioButton = bt1[f];
                        radioButton.setChecked(true);

                        boolean checked = radioButton.isChecked();




                        switch (radioButton.getId()) {
                            case R.id.q205_1:
                                if (checked) {
                                    txt2.setTextColor(Color.LTGRAY);
                                    rbtna1.setEnabled(false);
                                    rbtna2.setEnabled(false);
                                    rbtna3.setEnabled(false);
                                    rbtna4.setEnabled(false);
                                    rbtna5.setEnabled(false);
                                    rbtna6.setEnabled(false);
                                    rbtna7.setEnabled(false);

                                    rbtna1.setChecked(false);
                                    rbtna2.setChecked(false);
                                    rbtna3.setChecked(false);
                                    rbtna4.setChecked(false);
                                    rbtna5.setChecked(false);
                                    rbtna6.setChecked(false);
                                    rbtna7.setChecked(false);

                                }


                                break;

                            case R.id.q205_2:
                                if (checked) {

                                    txt2.setTextColor(Color.BLACK);
                                    rbtna1.setEnabled(true);
                                    rbtna2.setEnabled(true);
                                    rbtna3.setEnabled(true);
                                    rbtna4.setEnabled(true);
                                    rbtna5.setEnabled(true);
                                    rbtna6.setEnabled(true);
                                    rbtna7.setEnabled(true);
                                }
                                break;

                            case R.id.q205a_1:
                                if (checked) {


                                }
                                break;
                            case R.id.q205a_2:
                                if (checked) {


                                }
                                break;

                            case R.id.q205a_3:
                                if (checked) {


                                }
                                break;

                            case R.id.q205a_4:
                                if (checked) {


                                }
                                break;

                            case R.id.q205a_5:
                                if (checked) {


                                }
                                break;

                            case R.id.q205a_6:
                                if (checked) {


                                }
                                break;

                            case R.id.q205a_7:
                                if (checked) {


                                }
                                break;


                        }


                        break;
                    }
                }
            }
        }


        RadioButton[] bt2 = new RadioButton[7];
        for(int f=0;f<rga.getChildCount();f++)
        {
            View o = rga.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt2[f]=((RadioButton)o);
                if(ind.getQ205a()!= null &&  !ind.getQ205a().equals(""))
                {
                    if(Integer.parseInt(ind.getQ205a())==f+1)
                    {
                        RadioButton radioButton = bt2[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn1 = (RadioButton) findViewById(selectedId);

                if (selectedRbtn1 == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q205.this);
                    builder.setTitle("Q205: Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Does your husband/wife/partner live with you now or he/she stays elsewhere?");
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
                    // individual.setQ1106(selectedRbtn1.getText().toString().substring(0,1));
                    int selectedId1 = rga.getCheckedRadioButtonId();
                    selectedRbtn2 = (RadioButton) findViewById(selectedId1);

                    if (selectedRbtn2 == null && rbtn2.isChecked()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q205.this);
                        builder.setTitle("Q205: Error");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("How often do you see/visit each other?");
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
                        if (rbtn1.isChecked()) {
                            //Set q205 for the current individual
                            individual.setQ205(selectedRbtn1.getText().toString().substring(0, 1));


                            myDB = new DatabaseHelper(q205.this);
                            myDB.onOpen(myDB.getReadableDatabase());

                            myDB.updateInd("Q205",individual.getAssignmentID(),individual.getBatch(),ind.getQ205(),String.valueOf(individual.getSRNO()));
                            myDB.updateInd("Q205a",individual.getAssignmentID(),individual.getBatch(),null,String.valueOf(individual.getSRNO()));

                            myDB.close();




                            Intent q1o2 = new Intent(q205.this, q301.class);
                            q1o2.putExtra("Individual", individual);
                            startActivity(q1o2);
                        }
                        else {


                            individual.setQ205(selectedRbtn1.getText().toString().substring(0, 1));
                            individual.setQ205a(selectedRbtn2.getText().toString().substring(0, 1));


                            myDB = new DatabaseHelper(q205.this);
                            myDB.onOpen(myDB.getReadableDatabase());

                            myDB.updateInd("Q205",individual.getAssignmentID(),individual.getBatch(),ind.getQ205(),String.valueOf(individual.getSRNO()));
                            myDB.updateInd("Q205a",individual.getAssignmentID(),individual.getBatch(),ind.getQ205a(),String.valueOf(individual.getSRNO()));

                            myDB.close();

                            Intent q1o2 = new Intent(q205.this, q301.class);
                            q1o2.putExtra("Individual", individual);
                            startActivity(q1o2);

                        }

                    }

                }
            }


        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent q1o2 = new Intent(q205.this, q204.class);
                q1o2.putExtra("Individual", individual);
                startActivity(q1o2);
                //q205.super.onBackPressed();
            }

        });

    }

    public void onRadioButtonClicked(View v) {


        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q205_1:
                if (checked) {
                    txt2.setTextColor(Color.LTGRAY);
                    rbtna1.setEnabled(false);
                    rbtna2.setEnabled(false);
                    rbtna3.setEnabled(false);
                    rbtna4.setEnabled(false);
                    rbtna5.setEnabled(false);
                    rbtna6.setEnabled(false);
                    rbtna7.setEnabled(false);

                    rbtna1.setChecked(false);
                    rbtna2.setChecked(false);
                    rbtna3.setChecked(false);
                    rbtna4.setChecked(false);
                    rbtna5.setChecked(false);
                    rbtna6.setChecked(false);
                    rbtna7.setChecked(false);


                    // viewa.setVisibility(View.VISIBLE);




                    //rb99.setVisibility(View.VISIBLE);
                }


                break;

            case R.id.q205_2:
                if (checked) {

                    txt2.setTextColor(Color.BLACK);
                    rbtna1.setEnabled(true);
                    rbtna2.setEnabled(true);
                    rbtna3.setEnabled(true);
                    rbtna4.setEnabled(true);
                    rbtna5.setEnabled(true);
                    rbtna6.setEnabled(true);
                    rbtna7.setEnabled(true);
                }
                break;

            case R.id.q205a_1:
                if (checked) {


                }
                break;
            case R.id.q205a_2:
                if (checked) {


                }
                break;

            case R.id.q205a_3:
                if (checked) {


                }
                break;

            case R.id.q205a_4:
                if (checked) {


                }
                break;

            case R.id.q205a_5:
                if (checked) {


                }
                break;

            case R.id.q205a_6:
                if (checked) {


                }
                break;

            case R.id.q205a_7:
                if (checked) {



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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q205.this).toBundle());

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

