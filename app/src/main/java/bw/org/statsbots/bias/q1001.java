package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class q1001 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected DatabaseHelper myDB;
    protected Individual individual;
    protected RadioButton rbtn1, rbtn2, rbtn3;
    protected RadioGroup rg;
    protected RadioButton selectedRbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1001);

        setTitle("Q1001: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1001radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q1001_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1001_2);
        rbtn3 = (RadioButton) findViewById(R.id.q1001_3);

        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);
        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
     //   int p = 0;

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

        if ((individual.getQ102() != null && Integer.parseInt(individual.getQ102()) < 49) && (individual.getQ401() != null && individual.getQ401().equals("2")))
        {

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
            individual.setQ1004_Year("0000");
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

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q1001.this, q1101.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);



        }

if (individual.getQ101() != null && individual.getQ101().equals("1"))
{

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
    individual.setQ1004_Year("0000");
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

    myDB.onOpen(myDB.getReadableDatabase());
    myDB.getWritableDatabase();
    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
    myDB.close();

    Intent intent = new Intent(q1001.this, q1101.class);
    intent.putExtra("Individual", individual);
    startActivity(intent);



}
        if( ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0")) || sample.getStatusCode().equals("3")
                || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") && p1.getP06().equals("2"))))
        {



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
            individual.setQ1004_Year("0000");
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
            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q1001.this, q1101.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

        if( sample.getStatusCode().equals("3") || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0"))
                || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") && Integer.parseInt(individual.getQ102()) >49)
        || (sample.getStatusCode().equals("1")  && Integer.parseInt(individual.getQ102()) >49))
        {

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
            individual.setQ1004_Year("0000");
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

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q1001.this, q1101.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }


        if(  individual.getQ102() != null && Integer.parseInt(individual.getQ102()) > 49)
        {

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
            individual.setQ1004_Year("0000");
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

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q1001.this, q1101.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

        if((individual.getQ101().equals("2")) &&
                ((Integer.parseInt( individual.getQ102() ) >49 ) || (individual.getQ401().equals("2")))
                && ( sample.getStatusCode().equals("1") || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) )
        )
        {
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
            individual.setQ1004_Year("0000");
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

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q1001.this, q1101.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

        RadioButton[] bt = new RadioButton[3];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ1001()!= null )
                {
                    if(Integer.parseInt(ind.getQ1001())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }
//        if( ind.getQ905aOther() != null)
//        {
//            edtaother.setText(ind.getQ905aOther());
//        }



        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q1001.this, "Q1001: ERROR", "Are you currently pregnant?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {


                    if (rbtn1.isChecked()) {
                        individual.setQ1001(selectedRbtn.getText().toString().substring(0,1));
                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        myDB.close();
                        Intent skipto1003 = new Intent(q1001.this, q1003.class);
                        skipto1003.putExtra("Individual", individual);
                        startActivity(skipto1003);
                    }
                    else
                        {

                        individual.setQ1001(selectedRbtn.getText().toString().substring(0,1));
                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();
                        Intent intent = new Intent(q1001.this, q1002.class);
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
                if
                ((individual.getQ801f().equals("2") || individual.getQ801f().equals("3") || individual.getQ801f().equals("4") ||
                        individual.getQ801f().equals("9")) && individual.getQ801a().equals("1")
                       ) {
                    finish();
                    Intent intent = new Intent(q1001.this, q801.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                } else {
                    if ((individual.getQ801a().equals("1")) && individual.getQ101().equals("2") && individual.getQ401().equals("1") &&
                            (Integer.parseInt(individual.getQ102()) > 14
                                    && Integer.parseInt(individual.getQ102()) < 50) && !individual.getQ801f().equals("1")) {
                        Intent intent = new Intent(q1001.this, q802.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
                    } else {
                        if (individual.getQ901() != null && individual.getQ901().equals("2")) {
                            Intent intent = new Intent(q1001.this, q901.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);
                        } else {
                            if (individual.getQ904() != null && individual.getQ904().equals("4")) {
                                Intent intent = new Intent(q1001.this, q904.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(q1001.this, q905.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);
                            }
                        }
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q1001.this).toBundle());

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


