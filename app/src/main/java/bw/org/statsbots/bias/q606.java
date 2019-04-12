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

public class q606 extends AppCompatActivity implements Serializable{

    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected Individual indv;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected Individual individual;
    protected RadioButton rbtn1,rbtn2,rbtn9,  selected;
    protected RadioGroup rbtngroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q606);
        setTitle("q606 HIV and TB FACTS AND MYTHS");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q606_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q606_2);
        rbtn9 =  (RadioButton)findViewById(R.id.q606_9);
        rbtngroup = (RadioGroup)findViewById(R.id.q606radioGroup) ;



        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
      //  int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();


        //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());

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


//        if((Integer.valueOf(individual.getQ102()) > 64 && (sample.getStatusCode().equals("2"))))
//        {
//
//            Intent q1o2 = new Intent(q606.this, q616.class);
//            q1o2.putExtra("Individual", individual);
//            startActivity(q1o2);
//        }


        //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());





        if (individual.getQ601().equals("2") &&   sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1")){


            individual.setQ606(null);
            individual.setQ607(null);
            individual.setQ608(null);
            individual.setQ609(null);
            individual.setQ610(null);
            individual.setQ611a(null);
            individual.setQ611b(null);
            individual.setQ611c(null);

            individual.setQ612(null);
            individual.setQ612a(null);
            individual.setQ612aOther(null);
            individual.setQ613(null);
            individual.setQ613a(null);
            individual.setQ613aOther(null);
            individual.setQ614(null);
            individual.setQ614Other(null);
            individual.setQ615(null);
//            individual.setQ616_1(null);
//            individual.setQ616_2(null);
//            individual.setQ616_3(null);
//            individual.setQ616_4(null);
//            individual.setQ616_5(null);
//            individual.setQ616_6(null);
//            individual.setQ616_7(null);
//            individual.setQ616_8(null);
//            individual.setQ616_9(null);
//            individual.setQ616_Other(null);
//            individual.setQ617a(null);
//            individual.setQ617b(null);
//            individual.setQ617c(null);
//            individual.setQ617d(null);
//
//            individual.setQ617e(null);
//            individual.setQ617f(null);
//            individual.setQ617g(null);
//            individual.setQ617h(null);
//            individual.setQ617_0ther(null);
//            individual.setQ618(null);
//            individual.setQ619_1(null);
//            individual.setQ619_2(null);
//            individual.setQ619_3(null);
//            individual.setQ619_4(null);
//            individual.setQ619_5(null);
//            individual.setQ619_6(null);
//
//            individual.setQ619_7(null);
//            individual.setQ619_8(null);
//            individual.setQ619_9(null);
//            individual.setQ619_10(null);
//            individual.setQ619_11(null);
//            individual.setQ619_12(null);
//            individual.setQ619_13(null);
//            individual.setQ619_14(null);
//            individual.setQ619_Other(null);
//            individual.setQ620(null);
//            individual.setQ620_Other(null);
//            individual.setQ621(null);
//            individual.setQ621a_1(null);
//            individual.setQ621a_2(null);
//            individual.setQ621a_3(null);
//            individual.setQ621a_4(null);
//            individual.setQ621a_5(null);
//            individual.setQ621a_6(null);
//            individual.setQ621a_7(null);
//            individual.setQ621a_Other(null);
//            individual.setQ621b(null);
//            individual.setQ621bOther(null);
//            individual.setQ622(null);
//            individual.setQ622a(null);
//            individual.setQ622aOther(null);
//            individual.setQ622b(null);
//            individual.setQ622bOther(null);

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q606.this, q616.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }


        if (individual.getQ601().equals("2") &&   ((sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1"))
                || sample.getStatusCode().equals("1")  ) && (Integer.valueOf(individual.getQ102()) <= 24 )
                && (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 ) )
        {
            individual.setQ606(null);
            individual.setQ607(null);
            individual.setQ608(null);
            individual.setQ609(null);
            individual.setQ610(null);
            individual.setQ611a(null);
            individual.setQ611b(null);
            individual.setQ611c(null);

            individual.setQ612(null);
            individual.setQ612a(null);
            individual.setQ612aOther(null);
            individual.setQ613(null);
            individual.setQ613a(null);
            individual.setQ613aOther(null);
            individual.setQ614(null);
            individual.setQ614Other(null);
            individual.setQ615(null);
            individual.setQ616_1(null);
            individual.setQ616_2(null);
            individual.setQ616_3(null);
            individual.setQ616_4(null);
            individual.setQ616_5(null);
            individual.setQ616_6(null);
            individual.setQ616_7(null);
            individual.setQ616_8(null);
            individual.setQ616_9(null);
            individual.setQ616_Other(null);
            individual.setQ617a(null);
            individual.setQ617b(null);
            individual.setQ617c(null);
            individual.setQ617d(null);

            individual.setQ617e(null);
            individual.setQ617f(null);
            individual.setQ617g(null);
            individual.setQ617h(null);
            individual.setQ617_0ther(null);
            individual.setQ618(null);
            individual.setQ619_1(null);
            individual.setQ619_2(null);
            individual.setQ619_3(null);
            individual.setQ619_4(null);
            individual.setQ619_5(null);
            individual.setQ619_6(null);

            individual.setQ619_7(null);
            individual.setQ619_8(null);
            individual.setQ619_9(null);
            individual.setQ619_10(null);
            individual.setQ619_11(null);
            individual.setQ619_12(null);
            individual.setQ619_13(null);
            individual.setQ619_14(null);
            individual.setQ619_Other(null);
            individual.setQ620(null);
            individual.setQ620_Other(null);
            individual.setQ621(null);
            individual.setQ621a_1(null);
            individual.setQ621a_2(null);
            individual.setQ621a_3(null);
            individual.setQ621a_4(null);
            individual.setQ621a_5(null);
            individual.setQ621a_6(null);
            individual.setQ621a_7(null);
            individual.setQ621a_Other(null);
            individual.setQ621b(null);
            individual.setQ621bOther(null);
            individual.setQ622(null);
            individual.setQ622a(null);
            individual.setQ622aOther(null);
            individual.setQ622b(null);
            individual.setQ622bOther(null);

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();



            Intent intent = new Intent(q606.this, q623.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }



        if ( ((Integer.valueOf(individual.getQ102()) > 24 && Integer.valueOf(individual.getQ102()) <=64) && individual.getQ601().equals("1")
                && (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") )) || (sample.getStatusCode().equals("1"))) {



            individual.setQ606(null);
            individual.setQ607(null);
            individual.setQ608(null);
            individual.setQ609(null);
            individual.setQ610(null);


            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q606.this, q611.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }


        if( ((sample.getStatusCode().equals("3") )  || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0") )
                || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) && Integer.valueOf(individual.getQ102()) > 64
        ) ||((sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1")) &&
                p1.getP06().equals("2")  ) ))
        {

            individual.setQ606(null);
            individual.setQ607(null);
            individual.setQ608(null);
            individual.setQ609(null);
            individual.setQ610(null);
            individual.setQ611a(null);
            individual.setQ611b(null);
            individual.setQ611c(null);

            individual.setQ612(null);
            individual.setQ612a(null);
            individual.setQ612aOther(null);
            individual.setQ613(null);
            individual.setQ613a(null);
            individual.setQ613aOther(null);
            individual.setQ614(null);
            individual.setQ614Other(null);
            individual.setQ615(null);


            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent1 = new Intent(q606.this, q616.class);
            intent1.putExtra("Individual", individual);
            startActivity(intent1);
        }


            RadioButton[] bt = new RadioButton[3];
        for(int f=0;f<rbtngroup.getChildCount();f++)
        {
            View o = rbtngroup.getChildAt(f);
            if (o instanceof RadioButton) {
                bt[f] = ((RadioButton) o);
                if (ind.getQ606() != null) {
                    if (!ind.getQ606().equals("")) {
                        if (f == 2)
                        {
                            rbtn9.setChecked(true);
                        }
                        if (Integer.parseInt(ind.getQ606()) == f + 1) {
                            RadioButton radioButton = bt[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }

        /**
         * NEXT question
         */
        Button btnNext = (Button)findViewById(R.id.button);
        /**
         * NEXT and SAVE BUTTON
         */
        // btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                int selectedId = rbtngroup.getCheckedRadioButtonId();
                selected = (RadioButton) findViewById(selectedId);

                if (selected == null) {
                    lib.showError(q606.this, "Q606 Error", "Please select an option for q606");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    individual.setQ606(selected.getText().toString().substring(0,1));


                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.getWritableDatabase();
                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                    myDB.close();

                    Intent intent = new Intent(q606.this, q607.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);

                }
            }


        });


        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (individual.getQ604().equals("2")) {
                    finish();
                    Intent intent = new Intent(q606.this, q604.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);

                } else {
                    finish();
                    Intent intent = new Intent(q606.this, q605.class);
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q606.this).toBundle());

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










