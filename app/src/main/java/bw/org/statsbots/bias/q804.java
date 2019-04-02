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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class q804 extends AppCompatActivity implements Serializable{

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtnother, selected;
    protected RadioGroup rbtngroup;
    protected EditText edt804other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q804);


        setTitle("q804  EVER TESTED FOR HIV");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q804_1);
        rbtn2 = (RadioButton) findViewById(R.id.q804_2);
        rbtn3 = (RadioButton) findViewById(R.id.q804_3);
        rbtn4 = (RadioButton) findViewById(R.id.q804_4);
        rbtn5 = (RadioButton) findViewById(R.id.q804_5);
        rbtn6 = (RadioButton) findViewById(R.id.q804_6);
        rbtn7 = (RadioButton) findViewById(R.id.q804_7);
        rbtnother = (RadioButton) findViewById(R.id.q804_other);
        edt804other = (EditText) findViewById(R.id.q804_otherr);
        rbtngroup = (RadioGroup) findViewById(R.id.q804radioGroup);


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
    //    int p = 0;

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

        if(individual.getQ801() != null && individual.getQ801().equals("1") )
        {

            individual.setQ804(null);
            individual.setQ804Other(null);
            myDB = new DatabaseHelper(q804.this);
            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q804.this, q901.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }
        else {

        }

        if((individual.getQ801() != null && individual.getQ801().equals("1")) && individual.getQ801f().equals("1") && individual.getQ101().equals("2")
                && Integer.parseInt( individual.getQ102()) <50)
        {

            individual.setQ804(null);
            individual.setQ804Other(null);

            individual.setQ901(null);
            individual.setQ901a(null);
            individual.setQ901aOther(null);
            individual.setQ902Month("00");
            individual.setQ902Year("0000");
            individual.setQ903a(null);
            individual.setQ903b(null);
            individual.setQ903c(null);
            individual.setQ903d(null);
            individual.setQ903e(null);
            individual.setQ903f(null);
            individual.setQ903g(null);
            individual.setQ903h(null);

            individual.setQ904(null);
            individual.setQ904a(null);
            individual.setQ904aOther(null);
            individual.setQ904bMM("00");
            individual.setQ904bYYYY("0000");
            individual.setQ904c(null);
            individual.setQ904cOther(null);
            individual.setQ905(null);
            individual.setQ905a(null);
            individual.setQ905aOther(null);
            myDB = new DatabaseHelper(q804.this);
            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);

myDB.close();
            Intent intent = new Intent(q804.this, q1001.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }
        else {

        }




        rbtnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnother.isChecked()) {
                    edt804other.setVisibility(View.VISIBLE);
                    //edt804other.setText("");


                }
            }
        });

        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn1.isChecked()) {
                    edt804other.setVisibility(View.INVISIBLE);
                    edt804other.setText("");


                }
            }
        });
        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn2.isChecked()) {
                    edt804other.setVisibility(View.INVISIBLE);
                    edt804other.setText("");


                }
            }
        });
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn3.isChecked()) {
                    edt804other.setVisibility(View.INVISIBLE);
                    edt804other.setText("");


                }
            }
        });

        rbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn4.isChecked()) {
                    edt804other.setVisibility(View.INVISIBLE);
                    edt804other.setText("");


                }
            }
        });
        rbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn5.isChecked()) {
                    edt804other.setVisibility(View.INVISIBLE);
                    edt804other.setText("");


                }
            }
        });
        rbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn6.isChecked()) {
                    edt804other.setVisibility(View.INVISIBLE);
                    edt804other.setText("");


                }
            }
        });
        rbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn7.isChecked()) {
                    edt804other.setVisibility(View.INVISIBLE);
                    edt804other.setText("");


                }
            }
        });

        RadioButton[] bt = new RadioButton[8];
        for(int f=0;f<rbtngroup.getChildCount();f++)
        {
            View o = rbtngroup.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ804()!= null &&  !ind.getQ804().equals(""))
                {
                    if(Integer.parseInt(ind.getQ804())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }
        if( ind.getQ804Other() != null)
        {
            edt804other.setText(ind.getQ804Other());
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
                    lib.showError(q804.this, "Q804 Error", "Please select an option for q804");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    if (selected == rbtnother && (edt804other.length() == 0 || edt804other.getText() == null)) {
                        lib.showError(q804.this, "Q804 Error", "Please specify Other for q804");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else {
                        if ((individual.getQ101() != null && individual.getQ101().equals("2")) && (Integer.valueOf(individual.getQ102()) > 14 && (Integer.valueOf(individual.getQ102()) < 50))
                                &&  (individual.getQ401() != null && individual.getQ401().equals("1")))
                        {
                            individual.setQ804(selected.getText().toString().substring(0, 1));
                            individual.setQ804Other(edt804other.getText().toString());


                            individual.setQ901(null);
                            individual.setQ901a(null);
                            individual.setQ901aOther(null);
                            individual.setQ902Month("00");
                            individual.setQ902Year("0000");
                            individual.setQ903a(null);
                            individual.setQ903b(null);
                            individual.setQ903c(null);
                            individual.setQ903d(null);
                            individual.setQ903e(null);
                            individual.setQ903f(null);
                            individual.setQ903g(null);
                            individual.setQ903h(null);

                            individual.setQ904(null);
                            individual.setQ904a(null);
                            individual.setQ904aOther(null);
                            individual.setQ904bMM("00");
                            individual.setQ904bYYYY("0000");
                            individual.setQ904c(null);
                            individual.setQ904cOther(null);
                            individual.setQ905(null);
                            individual.setQ905a(null);
                            individual.setQ905aOther(null);
                            myDB = new DatabaseHelper(q804.this);
                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);

                            Intent intent = new Intent(q804.this, q1001.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);
                        } else {
                            individual.setQ804(selected.getText().toString().substring(0, 1));
                            individual.setQ804Other(edt804other.getText().toString());
                            individual.setQ901(null);
                            individual.setQ901a(null);
                            individual.setQ901aOther(null);
                            individual.setQ902Month("00");
                            individual.setQ902Year("0000");
                            individual.setQ903a(null);
                            individual.setQ903b(null);
                            individual.setQ903c(null);
                            individual.setQ903d(null);
                            individual.setQ903e(null);
                            individual.setQ903f(null);
                            individual.setQ903g(null);
                            individual.setQ903h(null);

                            individual.setQ904(null);
                            individual.setQ904a(null);
                            individual.setQ904aOther(null);
                            individual.setQ904bMM("00");
                            individual.setQ904bYYYY("0000");
                            individual.setQ904c(null);
                            individual.setQ904cOther(null);
                            individual.setQ905(null);
                            individual.setQ905a(null);
                            individual.setQ905aOther(null);

                            individual.setQ1001(null);
                            individual.setQ1002(null);
                            individual.setQ1002a_1(null);
                            individual.setQ1002a_2(null);
                            individual.setQ1002a_3(null);
                            individual.setQ1002a_4(null);
                            individual.setQ1002a_5(null);
                            individual.setQ1002a_6(null);
                            individual.setQ1002a_7(null);
                            individual.setQ1002a_8(null);
                            // individual.setQ1002a_9(null);
                            individual.setQ1002a_10(null);
                            individual.setQ1002a_11(null);
                            individual.setQ1002a_12(null);
                            individual.setQ1002a_13(null);
                            individual.setQ1002a_14(null);
                            individual.setQ1002a_15(null);
                            individual.setQ1002a_16(null);
                            individual.setQ1002a_17(null);
                            individual.setQ1002a_18(null);
                            individual.setQ1002a_Other(null);
                            individual.setQ1002b(null);
                            individual.setQ1002b_Other(null);
                            individual.setQ1003(null);
                            individual.setQ1004_Year("00");
                            individual.setQ1004_Month("00");
                            individual.setQ1004_Day("00");
                            individual.setQ1004a(null);
                            individual.setQ1004a(null);
                            individual.setQ1004b(null);
                            individual.setQ1004b_Other(null);
                            individual.setQ1005(null);
                            individual.setQ1005a(null);
                            individual.setQ1006(null);
                            individual.setQ1007(null);
                            individual.setQ1007a(null);
                            individual.setQ1008(null);
                            individual.setQ1008a(null);
                            individual.setQ1008a_Other(null);
                            individual.setQ1009(null);
                            individual.setQ1009a(null);
                            individual.setQ1010(null);
                            individual.setQ1010_Other(null);
                            individual.setQ1011(null);
                            individual.setQ1011_Other(null);
                            individual.setQ1012_Year("00");
                            individual.setQ1012_Month("00");
                            individual.setQ1012_Week("00");
                            individual.setQ1013(null);
                            individual.setQ1014(null);
                            individual.setQ1014a(null);
                            individual.setQ1014b(null);
                            individual.setQ1015(null);
                            individual.setQ1015a(null);
                            individual.setQ1015b(null);
                            individual.setQ1016(null);
                            individual.setQ1017(null);

                            myDB = new DatabaseHelper(q804.this);
                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();
                            Intent intent = new Intent(q804.this, q1101.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);

                        }
                    }
                }
            }
        });

        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q804.super.onBackPressed();
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q804.this).toBundle());

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


