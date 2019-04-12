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

public class q803 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtn8, rbtnother, selected;
    protected RadioGroup rbtngroup;
    protected EditText edtother;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q803);


        setTitle("q803  WHY NOT TESTED FOR HIV");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q803_1);
        rbtn2 = (RadioButton) findViewById(R.id.q803_2);
        rbtn3 = (RadioButton) findViewById(R.id.q803_3);
        rbtn4 = (RadioButton) findViewById(R.id.q803_4);
        rbtn5 = (RadioButton) findViewById(R.id.q803_5);
        rbtn6 = (RadioButton) findViewById(R.id.q803_6);
        rbtn7 = (RadioButton) findViewById(R.id.q803_7);
        rbtn8 = (RadioButton) findViewById(R.id.q803_8);
        rbtnother = (RadioButton) findViewById(R.id.q803_other);
        rbtngroup = (RadioGroup) findViewById(R.id.q803radioGroup);
        edtother = (EditText) findViewById(R.id.q803_otherr) ;


        rbtnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbtnother.isChecked()) {
                    edtother.setVisibility(View.VISIBLE);
                }

                    else{
                        edtother.setVisibility(View.INVISIBLE);
                        edtother.setText("");

                    }


            }
        });
        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn1.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });
        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn2.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn3.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });
        rbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn4.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });
        rbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn5.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });
        rbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn6.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });
        rbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn7.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });
        rbtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtn8.isChecked()) {
                    edtother.setVisibility(View.INVISIBLE);
                    edtother.setText("");

                }
            }
        });

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


        if((individual.getQ801a() != null && individual.getQ801a().equals("1"))  )
        {

            individual.setQ803(null);
            individual.setQ803Other(null);
            individual.setQ804(null);
            individual.setQ804Other(null);

            myDB = new DatabaseHelper(q803.this);
            myDB.onOpen(myDB.getReadableDatabase());
            myDB.updateIndividual(myDB.getWritableDatabase(), individual);
            myDB.close();

            Intent intent = new Intent(q803.this, q901.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }
        else {

        }


        if((individual.getQ801a().equals("1")) && individual.getQ101().equals("2") && individual.getQ401().equals("1") &&
                (Integer.parseInt( individual.getQ102() )>14
                && Integer.parseInt( individual.getQ102() )<50) && !individual.getQ801f().equals("1"))

        {

            individual.setQ803(null);
            individual.setQ803Other(null);
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

            myDB = new DatabaseHelper(q803.this);
            myDB.onOpen(myDB.getReadableDatabase());
            myDB.updateIndividual(myDB.getWritableDatabase(), individual);
            myDB.close();

            Intent intent = new Intent(q803.this, q1001.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }
        else {

        }



        if (ind.getQ803Other() != null ) {

                if (ind.getQ803() != null && ind.getQ803().equals("O")) {
                    rbtnother.setChecked(true);
                    edtother.setText(ind.getQ803Other());
                    edtother.setVisibility(View.VISIBLE);
                }
            }
            else {
        RadioButton[] bt = new RadioButton[9];
        for(int f=0;f<rbtngroup.getChildCount();f++)
        {
            View o = rbtngroup.getChildAt(f);
            if (o instanceof RadioButton) {
                bt[f] = ((RadioButton) o);

                if (ind.getQ803() != null ) {
                    if (ind.getQ803().equals("")) {
                        if (Integer.parseInt(ind.getQ803()) == f + 1) {
                            RadioButton radioButton = bt[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
            }
        }
//        if( ind.getQ803Other() != null)
//        {
//            edtother.setText(ind.getQ803Other());
//        }


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
                    lib.showError(q803.this, "Q803 Error", "Please select an option for q803");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                } else {
                    if (rbtnother.isChecked() && (edtother.length() == 0 || edtother.getText() == null)) {
                        lib.showError(q803.this, "Q803 Error: Other", "Please type OTHER specify for q803");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    } else {


                    if (rbtn8.isChecked() ) {
                      //  Intent intent = new Intent(q803.this, q901.class);;
                        individual.setQ803(rbtn8.getText().toString().substring(0, 1));


                        individual.setQ804(null);
                        individual.setQ804Other(null);


                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        myDB.close();


                        Intent intent = new Intent(q803.this, q901.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
                    }
                    else {


                            //  Intent intent = new Intent(q803.this, q901.class);;
                      individual.setQ803(selected.getText().toString().substring(0, 1));
                      if(rbtnother.isChecked()) {
                          individual.setQ803Other(edtother.getText().toString());
                      }
                      else
                      {
                          individual.setQ803Other(null);
                      }
                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        myDB.close();


                            Intent intent = new Intent(q803.this, q804.class);
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
                if((individual.getQ801f().equals("2") || individual.getQ801f().equals("3") || individual.getQ801f().equals("4")
                        || individual.getQ801f().equals("9")) && individual.getQ801a().equals("2"))

                {
                    finish();
                    Intent intent = new Intent(q803.this, q801.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                }
else
                {
                    finish();
                    Intent intent = new Intent(q803.this, q802.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q803.this).toBundle());

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


