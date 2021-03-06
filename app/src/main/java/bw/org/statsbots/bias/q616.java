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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class q616 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected CheckBox chk1, chk2, chk3, chk4, chk5, chk6, chk7, chk8, chk9, chkOther;
    protected EditText edt616Other;
    protected RadioGroup rbtngroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q616);

        setTitle("q616: KNOWLEDGE ABOUT HIV/AIDS AND TB ");
        lib = new LibraryClass();
        chk1 = (CheckBox) findViewById(R.id.Q616_1);
        chk2 = (CheckBox) findViewById(R.id.Q616_2);
        chk3 = (CheckBox) findViewById(R.id.Q616_3);
        chk4 = (CheckBox) findViewById(R.id.Q616_4);
        chk5 = (CheckBox) findViewById(R.id.Q616_5);
        chk6 = (CheckBox) findViewById(R.id.Q616_6);
        chk7 = (CheckBox) findViewById(R.id.Q616_7);
        chk8 = (CheckBox) findViewById(R.id.Q616_8);
        chk9 = (CheckBox) findViewById(R.id.Q616_9);
        chkOther = (CheckBox) findViewById(R.id.Q616_Other);
        edt616Other = (EditText) findViewById(R.id.Q616_OtherEdt);




        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

            //do nothing

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());
   thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);



        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }
        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

        if( sample.getStatusCode().equals("1") && individual.getQ604().equals("2") )
        {


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



            Intent q1o2 = new Intent(q616.this, q623.class);
            q1o2.putExtra("Individual", individual);
            startActivity(q1o2);
        }

        if (individual.getQ604().equals("2") && sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")){


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


            Intent intent = new Intent(q616.this, q623.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {

        }

        if( sample.getStatusCode().equals("1") && individual.getQ604().equals("1") )
        {

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
//            individual.setQ622(null);
//            individual.setQ622a(null);
//            individual.setQ622aOther(null);
//            individual.setQ622b(null);
//            individual.setQ622bOther(null);

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();


            Intent q1o2 = new Intent(q616.this, q622.class);
            q1o2.putExtra("Individual", individual);
            startActivity(q1o2);
        }


        chk9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chk9.isChecked()) {
                    chk1.setEnabled(false);
                    chk2.setEnabled(false);
                    chk3.setEnabled(false);
                    chk4.setEnabled(false);
                    chk5.setEnabled(false);
                    chk6.setEnabled(false);
                    chk7.setEnabled(false);
                    chk8.setEnabled(false);
                    chkOther.setEnabled(false);
                    edt616Other.setVisibility(View.INVISIBLE);

                    chk1.setChecked(false);
                    chk2.setChecked(false);
                    chk3.setChecked(false);
                    chk4.setChecked(false);
                    chk5.setChecked(false);
                    chk6.setChecked(false);
                    chk7.setChecked(false);
                    chk8.setChecked(false);
                   // chk1.setChecked(false);
                    chkOther.setChecked(false);
                    edt616Other.setText("");
                }
                else
                {
                    chk1.setEnabled(true);
                    chk2.setEnabled(true);
                    chk3.setEnabled(true);
                    chk4.setEnabled(true);
                    chk5.setEnabled(true);
                    chk6.setEnabled(true);
                    chk7.setEnabled(true);
                    chk8.setEnabled(true);
                    chkOther.setEnabled(true);

                }

            }
        });


        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chk1.isChecked()) {

                    chk2.setEnabled(false);
                    chk3.setEnabled(false);
                    chk4.setEnabled(false);
                    chk5.setEnabled(false);
                    chk6.setEnabled(false);
                    chk7.setEnabled(false);
                    chk8.setEnabled(false);
                    chk9.setEnabled(false);
                    chkOther.setEnabled(false);
                    edt616Other.setVisibility(View.INVISIBLE);

                    chk9.setChecked(false);
                    chk2.setChecked(false);
                    chk3.setChecked(false);
                    chk4.setChecked(false);
                    chk5.setChecked(false);
                    chk6.setChecked(false);
                    chk7.setChecked(false);
                    chk8.setChecked(false);
                   // chk1.setChecked(false);
                    chkOther.setChecked(false);
                    edt616Other.setText("");
                }
                else
                {
                    chk9.setEnabled(true);
                    chk2.setEnabled(true);
                    chk3.setEnabled(true);
                    chk4.setEnabled(true);
                    chk5.setEnabled(true);
                    chk6.setEnabled(true);
                    chk7.setEnabled(true);
                    chk8.setEnabled(true);
                    chkOther.setEnabled(true);

                }

            }
        });






        chkOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chkOther.isChecked()) {
                    edt616Other.setVisibility(View.VISIBLE);
                }
                else
                {
                    edt616Other.setVisibility(View.INVISIBLE);
                    edt616Other.setText("");
                }

            }
        });

        if(ind.getQ616_1()!= null &&  !ind.getQ616_1().equals(""))
        {
            if(Integer.parseInt(ind.getQ616_1())== 1)
            {
                chk1.setChecked(true);

            }else
            {
                chk1.setChecked(false);
            }
        }



        if(ind.getQ616_2()!= null &&  !ind.getQ616_2().equals(""))
        {
            if(Integer.parseInt(ind.getQ616_2())== 1)
            {
                chk2.setChecked(true);

            }else
            {
                chk2.setChecked(false);
            }
        }
        if(ind.getQ616_3()!= null &&  !ind.getQ616_3().equals(""))
        {
            if(Integer.parseInt(ind.getQ616_3())== 1)
            {
                chk3.setChecked(true);

            }else
            {
                chk3.setChecked(false);
            }
        }

        if(ind.getQ616_4()!= null &&  !ind.getQ616_4().equals(""))
        {
            if(Integer.parseInt(ind.getQ616_4())== 1)
            {
                chk4.setChecked(true);

            }else
            {
                chk4.setChecked(false);
            }
        }

        if(ind.getQ616_5()!= null &&  !ind.getQ616_5().equals(""))
        {
            if(Integer.parseInt(ind.getQ616_5())== 1)
            {
                chk5.setChecked(true);

            }else
            {
                chk5.setChecked(false);
            }
        }


        if(ind.getQ616_6()!= null &&  !ind.getQ616_6().equals(""))
        {
            if(Integer.parseInt(ind.getQ616_6())== 1)
            {
                chk6.setChecked(true);

            }else
            {
                chk6.setChecked(false);
            }
        }

        if(ind.getQ616_7()!= null &&  !ind.getQ616_7().equals(""))
        {
            if(Integer.parseInt(ind.getQ616_7())== 1)
            {
                chk7.setChecked(true);

            }else
            {
                chk7.setChecked(false);
            }
        }

        if(ind.getQ616_8()!= null &&  !ind.getQ616_8().equals(""))
        {
            if(Integer.parseInt(ind.getQ616_8())== 1)
            {
                chk8.setChecked(true);

            }else
            {
                chk8.setChecked(false);
            }
        }

        if(ind.getQ616_9()!= null &&  !ind.getQ616_9().equals(""))
        {
            if(Integer.parseInt(ind.getQ616_9())== 1)
            {
                chk9.setChecked(true);

            }else
            {
                chk9.setChecked(false);
            }
        }



        if(ind.getQ616_Other() != null &&  !ind.getQ616_Other().equals(""))
        {
            if(Integer.parseInt(ind.getQ616_Other().substring(0,1))== 1)
            {
                chkOther.setChecked(true);

                edt616Other.setVisibility(View.VISIBLE);
                edt616Other.setText(ind.getQ616_Other().substring(1,ind.getQ616_Other().length()));
                //individual.setQ616_Other("1"+edt616Other.getText().toString());


            }else
            {
                chkOther.setChecked(false);
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



                if (!chk1.isChecked() && !chk2.isChecked() && !chk3.isChecked() && !chk4.isChecked() && !chk5.isChecked() && !chk6.isChecked() && !chk7.isChecked() && !chk8.isChecked() &&
                        !chk9.isChecked() && !chkOther.isChecked()) {
                    lib.showError(q616.this, "Q616 Error", "Please select atleast one option");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }
                else {
                    if ((((chkOther.isChecked() && edt616Other.length() == 0)))) {
                        lib.showError(q616.this, "Q616: ERROR: Other", "Please specify?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {

                        if (chk1.isChecked()) {
                            individual.setQ616_1("1");
                        } else {
                            individual.setQ616_1("2");

                        }

                        if (chk2.isChecked()) {
                            individual.setQ616_2("1");
                        } else {
                            individual.setQ616_2("2");

                        }
                        if (chk3.isChecked()) {
                            individual.setQ616_3("1");
                        } else {
                            individual.setQ616_3("2");

                        }
                        if (chk4.isChecked()) {
                            individual.setQ616_4("1");
                        } else {
                            individual.setQ616_4("2");

                        }
                        if (chk5.isChecked()) {
                            individual.setQ616_5("1");
                        } else {
                            individual.setQ616_5("2");

                        }
                        if (chk6.isChecked()) {
                            individual.setQ616_6("1");
                        } else {
                            individual.setQ616_6("2");

                        }
                        if (chk7.isChecked()) {
                            individual.setQ616_7("1");
                        } else {
                            individual.setQ616_7("2");

                        }
                        if (chk8.isChecked()) {
                            individual.setQ616_8("1");
                        } else {
                            individual.setQ616_8("2");

                        }
                        if (chk9.isChecked()) {
                            individual.setQ616_9("1");
                        } else {
                            individual.setQ616_9("2");

                        }

                        if (chkOther.isChecked()) {
                            individual.setQ616_Other("1");

                            individual.setQ616_Other(edt616Other.getText().toString());
                            individual.setQ616_Other("1" + edt616Other.getText().toString());

                        } else
                            {
                            individual.setQ616_Other("2");

                        }

                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                        myDB.close();

                        Intent intent = new Intent(q616.this, q617.class);
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

                if( ((sample.getStatusCode().equals("3") )  || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0") )
                        || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) && Integer.valueOf(individual.getQ102()) > 64
                ) ||((sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1")) &&
                        p1.getP06().equals("2")  ) )&& (individual.getQ604() != null && individual.getQ604().equals("2")) )
                {

                    Intent intent1 = new Intent(q616.this, q604.class);
                    intent1.putExtra("Individual", individual);
                    startActivity(intent1);
                }
                if( ((sample.getStatusCode().equals("3") )  || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0") )
                        || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) && Integer.valueOf(individual.getQ102()) > 64
                ) ||((sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1")) &&
                        p1.getP06().equals("2")  ) ) && (individual.getQ604() != null && individual.getQ604().equals("1")))
                {



                    Intent intent1 = new Intent(q616.this, q605.class);
                    intent1.putExtra("Individual", individual);
                    startActivity(intent1);
                }else {
                    finish();
                    Intent intent = new Intent(q616.this, q615.class);
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q616.this).toBundle());

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