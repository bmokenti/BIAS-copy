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

public class q106 extends AppCompatActivity implements Serializable {
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, rbtna3, rbtna4, rbtna5, rbtna6, rbtnb1, rbtnb2, rbtnb3, rbtnb4, rbtnb5, rbtnb6, rbtnb7, rbtnb8, rbtnb10;
    protected RadioGroup rg, rga, rgb;
    protected HouseHold thisHouse;
    protected Individual individual;
    protected RadioButton selectedRbtn, selectedRbtna, selectedRbtnb;
    protected EditText edt, edt1, edt2;
    protected DatabaseHelper myDB;
    TextView q106atext, q106dtext, q106btext, q106ctext;
    PersonRoster p1 = null;
    Individual pp1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q106);


        setTitle("Q106. WORK FOR PROFIT ");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q106_1);
        rbtn2 = (RadioButton) findViewById(R.id.q106_2);

        rbtna1 = (RadioButton) findViewById(R.id.q106a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q106a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q106a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q106a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q106a_5);
        rbtna6 = (RadioButton) findViewById(R.id.q106a_Other);

        edt = (EditText) findViewById(R.id.q106a_txtother);


        rbtnb1 = (RadioButton) findViewById(R.id.q106b_1);
        rbtnb2 = (RadioButton) findViewById(R.id.q106b_2);
        rbtnb3 = (RadioButton) findViewById(R.id.q106b_3);
        rbtnb4 = (RadioButton) findViewById(R.id.q106b_4);
        rbtnb5 = (RadioButton) findViewById(R.id.q106b_5);
        rbtnb6 = (RadioButton) findViewById(R.id.q106b_6);
        rbtnb7 = (RadioButton) findViewById(R.id.q106b_7);
        rbtnb8 = (RadioButton) findViewById(R.id.q106b_8);

        rbtnb10 = (RadioButton) findViewById(R.id.q106b_10);

        q106atext = (TextView) findViewById(R.id.q106atxt);
        q106dtext = (TextView) findViewById(R.id.q106dtxt);
        q106btext = (TextView) findViewById(R.id.q106btxt);
        q106ctext = (TextView) findViewById(R.id.q106ctxt);

        rg = (RadioGroup) findViewById(R.id.q106radioGroup);
        rga = (RadioGroup) findViewById(R.id.q106aradioGroup);
        rgb = (RadioGroup) findViewById(R.id.q106bradioGroup);


        edt1 = (EditText) findViewById(R.id.q106c);
        edt2 = (EditText) findViewById(R.id.q106d);
        lib = new LibraryClass();

        //edittext = (EditText) findViewById(R.id.q102_years);
        //edittext.setVisibility(View.VISIBLE);

        myDB = new DatabaseHelper(q106.this);
        myDB.onOpen(myDB.getReadableDatabase());
        myDB.getWritableDatabase();


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;


        Intent ii = getIntent();
        p1 = (PersonRoster) ii.getSerializableExtra("Personroster");
        myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());

        final Individual ind = myDB.getdataIndivisual(p1.getAssignmentID(),p1.getBatch(),p1.getSRNO());
        individual = ind;

        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

        final List<HouseHold>  thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

       // myDB.close();
        final List<PersonRoster> roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }

        if(individual.getQ105() != null && (individual.getQ105().equals("1") || individual.getQ105().equals("2") || individual.getQ105().equals("3") || individual.getQ105().equals("4")))
        {

            myDB = new DatabaseHelper(q106.this);
            myDB.onOpen(myDB.getReadableDatabase());

            myDB.updateInd("Q106",individual.getAssignmentID(),individual.getBatch(),null,String.valueOf(individual.getSRNO()));
            myDB.updateInd("Q106a",individual.getAssignmentID(),individual.getBatch(),null,String.valueOf(individual.getSRNO()));
            myDB.updateInd("Q106c",individual.getAssignmentID(),individual.getBatch(),null,String.valueOf(individual.getSRNO()));
            myDB.updateInd("Q106b",individual.getAssignmentID(),individual.getBatch(),null,String.valueOf(individual.getSRNO()));
            myDB.updateInd("Q106d",individual.getAssignmentID(),individual.getBatch(),null,String.valueOf(individual.getSRNO()));

            myDB.close();

            Intent intent = new Intent(q106.this, q107.class);
            intent.putExtra("Individual", individual);
            intent.putExtra("Personroster", p1);
            startActivity(intent);
        }

        if (individual.getQ105() != null && (individual.getQ105().equals("8") ))
        {

            myDB = new DatabaseHelper(q106.this);
            myDB.onOpen(myDB.getReadableDatabase());

            myDB.updateInd("Q106",individual.getAssignmentID(),individual.getBatch(),"2",String.valueOf(individual.getSRNO()));
            myDB.updateInd("Q106a",individual.getAssignmentID(),individual.getBatch(),"3",String.valueOf(individual.getSRNO()));
            myDB.updateInd("Q106c",individual.getAssignmentID(),individual.getBatch(),null,String.valueOf(individual.getSRNO()));
            myDB.updateInd("Q106b",individual.getAssignmentID(),individual.getBatch(),null,String.valueOf(individual.getSRNO()));
            myDB.updateInd("Q106d",individual.getAssignmentID(),individual.getBatch(),null,String.valueOf(individual.getSRNO()));

            myDB.close();

            Intent intent = new Intent(q106.this, q107.class);
            intent.putExtra("Individual", individual);
            intent.putExtra("Personroster", p1);
            startActivity(intent);
        }


        rbtna1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtna1.isChecked()) {
                    edt.setVisibility(View.INVISIBLE);
                    edt.setText("");

                }
            }
        });
        rbtna3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtna3.isChecked()) {
                    edt.setVisibility(View.INVISIBLE);
                    edt.setText("");

                }
            }
        });
        rbtna4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtna4.isChecked()) {
                    edt.setVisibility(View.INVISIBLE);
                    edt.setText("");

                }
            }
        });
        rbtna5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtna5.isChecked()) {
                    edt.setVisibility(View.INVISIBLE);
                    edt.setText("");

                }
            }
        });
        rbtna6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtna6.isChecked()) {
                    edt.setVisibility(View.VISIBLE);
                    //edt.setText("");

                }
            }
        });









        RadioButton[] bt = new RadioButton[2];
        RadioGroup rg1 = findViewById(R.id.q106radioGroup);

        for(int f=0;f<rg1.getChildCount();f++)
        {
            View o = rg1.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ106()!= null &&  !ind.getQ106().equals(""))
                {
                    if(Integer.parseInt(ind.getQ106())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);


                        boolean checked = radioButton.isChecked();

                        switch (radioButton.getId()) {
                            case R.id.q106_1:
                                if (checked) {
                                    rbtna1.setEnabled(false);
                                    rbtna2.setEnabled(false);
                                    rbtna3.setEnabled(false);
                                    rbtna4.setEnabled(false);
                                    rbtna5.setEnabled(false);
                                    rbtna6.setEnabled(false);

                                    rbtna1.setChecked(false);
                                    rbtna2.setChecked(false);
                                    rbtna3.setChecked(false);
                                    rbtna4.setChecked(false);
                                    rbtna5.setChecked(false);
                                    rbtna6.setChecked(false);
                                    q106atext.setTextColor(Color.LTGRAY);


                                    q106btext.setTextColor(Color.BLACK);
                                    rbtnb1.setEnabled(true);
                                    rbtnb2.setEnabled(true);
                                    rbtnb3.setEnabled(true);
                                    rbtnb4.setEnabled(true);
                                    rbtnb5.setEnabled(true);
                                    rbtnb6.setEnabled(true);
                                    rbtnb7.setEnabled(true);
                                    rbtnb8.setEnabled(true);
                                    rbtnb10.setEnabled(true);

                                    q106dtext.setTextColor(Color.BLACK);
                                    q106ctext.setTextColor(Color.BLACK);
                                    //edt,
                                    edt1.setEnabled(true);
                                    edt2.setEnabled(true);

                                }


                                break;


                            case R.id.q106_2: {
                                //if (checked)
                                // for (int i = 0; i < rga.getChildCount(); i++) {
                                //  ((RadioButton) rga.getChildAt(i)).setEnabled(true);




                                    rbtna1.setEnabled(true);
                                    rbtna2.setEnabled(true);
                                    rbtna3.setEnabled(false);

                                    rbtna4.setEnabled(true);
                                    rbtna5.setEnabled(true);
                                    rbtna6.setEnabled(true);
                                    q106atext.setTextColor(Color.BLACK);


                                q106btext.setTextColor(Color.LTGRAY);
                                rbtnb1.setEnabled(false);
                                rbtnb2.setEnabled(false);
                                rbtnb3.setEnabled(false);
                                rbtnb4.setEnabled(false);
                                rbtnb5.setEnabled(false);
                                rbtnb6.setEnabled(false);
                                rbtnb7.setEnabled(false);
                                rbtnb8.setEnabled(false);
                                rbtnb10.setEnabled(false);

                                rbtnb1.setChecked(false);
                                rbtnb2.setChecked(false);
                                rbtnb3.setChecked(false);
                                rbtnb4.setChecked(false);
                                rbtnb5.setChecked(false);
                                rbtnb6.setChecked(false);
                                rbtnb7.setChecked(false);
                                rbtnb8.setChecked(false);
                                rbtnb10.setChecked(false);


                                q106dtext.setTextColor(Color.LTGRAY);
                                q106ctext.setTextColor(Color.LTGRAY);
                                //edt,
                                edt1.setEnabled(false);
                                edt2.setEnabled(false);
                                edt1.setText("");
                                edt2.setText("'");


                            }
                            // }

                        }




                        break;
                    }
                }
            }
            else
            {
                Log.d("h13 Lost Here","**********");
            }
        }


        RadioButton[] bt1 = new RadioButton[6];
        RadioGroup rg2 = findViewById(R.id.q106aradioGroup);

        for(int f=0;f<rg2.getChildCount();f++)
        {
            View o = rg2.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt1[f]=((RadioButton)o);
                if(ind.getQ106a()!= null &&  !ind.getQ106a().equals(""))
                {
                    if(Integer.parseInt(ind.getQ106a())==f+1)
                    {
                        RadioButton radioButton = bt1[f];
                        radioButton.setChecked(true);





                            break;
                    }
                }
            }
            else
            {
                Log.d("h13 Lost Here","**********");
            }
        }

        if(ind.getQ106aOther() !=null){
            rbtna6.setChecked(true);
            edt.setText(ind.getQ106aOther());
        }

        RadioButton[] bt2 = new RadioButton[9];
        RadioGroup rg3 = findViewById(R.id.q106bradioGroup);

        for(int f=0;f<rg3.getChildCount();f++)
        {
            View o = rg3.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt2[f]=((RadioButton)o);
                if(ind.getQ106b()!= null &&  !ind.getQ106b().equals(""))
                {
                    if(Integer.parseInt(ind.getQ106b())==f+1)
                    {
                        RadioButton radioButton = bt2[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
            else
            {
                Log.d("h13 Lost Here","**********");
            }
        }

        if(ind.getQ106c()!=null){
            edt1.setText(ind.getQ106c());
        }

        if(ind.getQ106d()!=null){
            edt2.setText(ind.getQ106d());
        }

        /**
         * NEXT Person BUTTON
         */
        Button btnNext = (Button) findViewById(R.id.button);


        //btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q106.this, "q106 Error", "In the past 7 days did  work for payment, profit or home use for atleast 1 hour");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                } else {
                    int selectedIda = rga.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedIda);

                    if (selectedRbtna == null && rbtn2.isChecked()) {
                        lib.showError(q106.this, "q106a Error", "Since did not work for payment, profit or home use, what did you do?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);


                    } else {


                        if (rbtna6.isChecked() && edt.getText() == null) {
                            lib.showError(q106.this, "q106a Error", "Other specify OR uncheck other specify option?");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);


                        } else {
                            int selectedIdb = rgb.getCheckedRadioButtonId();
                            selectedRbtnb = (RadioButton) findViewById(selectedIdb);

                            if (selectedRbtnb == null && rbtn1.isChecked()) {
                                lib.showError(q106.this, "q106b Error", "What were you mainly working as in the past 7 days?");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);


                            } else {


                                if ((edt1.length() == 0 || edt1.getText() == null) && rbtn1.isChecked()) {
                                    lib.showError(q106.this, "q106c Error", "What is your current occupation?");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);


                                } else {
                                    if ((edt2.length() == 0 || edt2.getText() == null) && rbtn1.isChecked()) {
                                        lib.showError(q106.this, "q106d Error", "What were you mainly working as during the past 7 days?");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);


                                  } else {


                                                if (rbtn1.isChecked()) {

                                                    individual.setQ106(selectedRbtn.getText().toString().substring(0, 1));
                                                    individual.setQ106b(selectedRbtnb.getText().toString().substring(0, 1));
                                                    individual.setQ106c(edt1.getText().toString());
                                                    individual.setQ106d(edt2.getText().toString());

                                                    myDB = new DatabaseHelper(q106.this);
                                                    myDB.onOpen(myDB.getReadableDatabase());

                                                    myDB.updateInd("Q106",individual.getAssignmentID(),individual.getBatch(),ind.getQ106(),String.valueOf(individual.getSRNO()));

                                                    myDB.updateInd("Q106d",individual.getAssignmentID(),individual.getBatch(),ind.getQ106d(),String.valueOf(individual.getSRNO()));

                                                    myDB.updateInd("Q106a",individual.getAssignmentID(),individual.getBatch(),null,String.valueOf(individual.getSRNO()));

                                                    myDB.updateInd("Q106c",individual.getAssignmentID(),individual.getBatch(),ind.getQ106c(),String.valueOf(individual.getSRNO()));

                                                    myDB.updateInd("Q106b",individual.getAssignmentID(),individual.getBatch(),ind.getQ106b(),String.valueOf(individual.getSRNO()));


                                                    myDB.close();


                                                    Intent intent = new Intent(q106.this, q107.class);
                                                    intent.putExtra("Individual", individual);
                                                    intent.putExtra("Personroster", p1);
                                                    startActivity(intent);
                                                } else {

                                                    individual.setQ106(selectedRbtn.getText().toString().substring(0, 1));
                                                    individual.setQ106a(selectedRbtna.getText().toString().substring(0, 1));
                                                    if (rbtna6.isChecked())
                                                    {
                                                        individual.setQ106aOther(edt.getText().toString());
                                                    }else{
                                                        individual.setQ106aOther(null);
                                                    }

                                                    myDB = new DatabaseHelper(q106.this);
                                                    myDB.onOpen(myDB.getReadableDatabase());

                                                    myDB.updateInd("Q106",individual.getAssignmentID(),individual.getBatch(),ind.getQ106(),String.valueOf(individual.getSRNO()));

                                                    myDB.updateInd("Q106a",individual.getAssignmentID(),individual.getBatch(),ind.getQ106a(),String.valueOf(individual.getSRNO()));

                                                    myDB.updateInd("Q106aOther",individual.getAssignmentID(),individual.getBatch(),ind.getQ106aOther(),String.valueOf(individual.getSRNO()));



                                                    myDB.close();

                                                    Intent intent = new Intent(q106.this, q107.class);
                                                    intent.putExtra("Individual", individual);
                                                    intent.putExtra("Personroster", p1);
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
            public void onClick(View v)
            {
                finish();
                Intent q1o2 = new Intent(q106.this, q105.class);
                q1o2.putExtra("Personroster", p1);
                startActivity(q1o2);
            }


        });
    }


    public void onRadioButtonClicked(View v) {
        //TextView q1101atext = findViewById(R.id.q1101atxt);
        //RadioGroup rg1 = (RadioGroup) findViewById(R.id.q1101radioGroupa);
        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q106_1:
                if (checked) {
                   rbtna1.setEnabled(false);
                    rbtna2.setEnabled(false);
                    rbtna3.setEnabled(false);
                    rbtna4.setEnabled(false);
                    rbtna5.setEnabled(false);
                    rbtna6.setEnabled(false);

                    rbtna1.setChecked(false);
                    rbtna2.setChecked(false);
                    rbtna3.setChecked(false);
                    rbtna4.setChecked(false);
                    rbtna5.setChecked(false);
                    rbtna6.setChecked(false);
                    q106atext.setTextColor(Color.LTGRAY);


                    q106btext.setTextColor(Color.BLACK);
                    rbtnb1.setEnabled(true);
                    rbtnb2.setEnabled(true);
                    rbtnb3.setEnabled(true);
                    rbtnb4.setEnabled(true);
                    rbtnb5.setEnabled(true);
                    rbtnb6.setEnabled(true);
                    rbtnb7.setEnabled(true);
                    rbtnb8.setEnabled(true);
                    rbtnb10.setEnabled(true);

                    q106dtext.setTextColor(Color.BLACK);
                    q106ctext.setTextColor(Color.BLACK);
                    //edt,
                            edt1.setEnabled(true);
                            edt2.setEnabled(true);

                    }


                break;



            case R.id.q106_2: {
                //if (checked)
                // for (int i = 0; i < rga.getChildCount(); i++) {
                //  ((RadioButton) rga.getChildAt(i)).setEnabled(true);
                rbtna1.setEnabled(true);
                rbtna2.setEnabled(true);
                rbtna3.setEnabled(false);
                rbtna4.setEnabled(true);
                rbtna5.setEnabled(true);
                rbtna6.setEnabled(true);
                q106atext.setTextColor(Color.BLACK);


                q106btext.setTextColor(Color.LTGRAY);
                rbtnb1.setEnabled(false);
                rbtnb2.setEnabled(false);
                rbtnb3.setEnabled(false);
                rbtnb4.setEnabled(false);
                rbtnb5.setEnabled(false);
                rbtnb6.setEnabled(false);
                rbtnb7.setEnabled(false);
                rbtnb8.setEnabled(false);
                rbtnb10.setEnabled(false);

                rbtnb1.setChecked(false);
                rbtnb2.setChecked(false);
                rbtnb3.setChecked(false);
                rbtnb4.setChecked(false);
                rbtnb5.setChecked(false);
                rbtnb6.setChecked(false);
                rbtnb7.setChecked(false);
                rbtnb8.setChecked(false);
                rbtnb10.setChecked(false);


                q106dtext.setTextColor(Color.LTGRAY);
                q106ctext.setTextColor(Color.LTGRAY);
                //edt,
                edt1.setEnabled(false);
                edt2.setEnabled(false);
                edt1.setText("");
                edt2.setText("'");



                }
                // }

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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("[Demo!] Are you sure you want to pause the interview");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(getApplicationContext(), started_household.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q106.this).toBundle());

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
