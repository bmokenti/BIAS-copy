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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class q410 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;protected DatabaseHelper myDB;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtn8, rbtn9, rbtn10, rbtn11, rbtn12, rbtn13, rbtn14;
    protected RadioGroup  rg1, rg2, rg3, rg4, rg5, rg6, rg7;
    protected RadioButton selected1, selected2, selected3, selected4, selected5, selected6, selected7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q410);


        setTitle("Q410: SEXUAL HISTORY");
        lib = new LibraryClass();

        rg1 = (RadioGroup) findViewById(R.id.rg1) ;
        rg2 = (RadioGroup) findViewById(R.id.rg2) ;
        rg3 = (RadioGroup) findViewById(R.id.rg3) ;
        rg4 = (RadioGroup) findViewById(R.id.rg4) ;
        rg5 = (RadioGroup) findViewById(R.id.rg5) ;
        rg6 = (RadioGroup) findViewById(R.id.rg6) ;
        rg7 = (RadioGroup) findViewById(R.id.rg7) ;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        rbtn1 = (RadioButton) findViewById(R.id.rg1_01) ;
        rbtn2 = (RadioButton) findViewById(R.id.rg1_02) ;


        rbtn3 = (RadioButton) findViewById(R.id.rg2_1) ;
        rbtn4 = (RadioButton) findViewById(R.id.rg2_2) ;

        rbtn5 = (RadioButton) findViewById(R.id.rg3_1) ;
        rbtn6 = (RadioButton) findViewById(R.id.rg3_2) ;


        rbtn7 = (RadioButton) findViewById(R.id.rg4_1) ;
        rbtn8 = (RadioButton) findViewById(R.id.rg4_2) ;


        rbtn9 = (RadioButton) findViewById(R.id.rg5_1) ;
        rbtn10 = (RadioButton) findViewById(R.id.rg5_2) ;


        rbtn11 = (RadioButton) findViewById(R.id.rg6_1) ;
        rbtn12 = (RadioButton) findViewById(R.id.rg6_2) ;


        rbtn13 = (RadioButton) findViewById(R.id.rg7_1) ;
        rbtn14 = (RadioButton) findViewById(R.id.rg7_2) ;


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;

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

        if (individual.getQ101().equals("1"))
        {

            individual.setQ410MadeAfraid(null);
            individual.setQ410Forced(null);
            individual.setQ410Physical(null);
            individual.setQ410Threatened(null);
            individual.setQ410Choked(null);
            individual.setQ410Pushed(null);
            individual.setQ410Slapped(null);


            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q410.this, q501.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }



        if ((individual.getQ101().equals("2")) && ((individual.getQ201().equals("1")  && individual.getQ202().equals("2")) || individual.getQ201().equals("6"))
                || Integer.valueOf(individual.getQ102()) >= 50)
        {

            individual.setQ410MadeAfraid(null);
            individual.setQ410Forced(null);
            individual.setQ410Physical(null);
            individual.setQ410Threatened(null);
            individual.setQ410Choked(null);
            individual.setQ410Pushed(null);
            individual.setQ410Slapped(null);
            //individual.setQ410(null);

            individual.setQ501(null);
            individual.setQ502(null);
            individual.setQ503(null);
            individual.setQ504_1(null);
            individual.setQ504_2(null);
            individual.setQ504_3(null);
            individual.setQ504_4(null);
            individual.setQ504_5(null);
            individual.setQ504_6(null);
            individual.setQ504_7(null);
            individual.setQ504_8(null);
            individual.setQ504_10(null);
            individual.setQ504_Other(null);
            individual.setQ504_OtherSpecify(null);



            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q410.this, q601.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }

        RadioButton[] bt = new RadioButton[3];
        for(int f=1;f<rg1.getChildCount();f++)
        {
            View o = rg1.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ410Slapped()!= null &&  !ind.getQ410Slapped().equals(""))
                {
                    if(Integer.parseInt(ind.getQ410Slapped())== 1)
                    {
                     rbtn1.setChecked(true);

                        break;
                    }
                    else if(Integer.parseInt(ind.getQ410Slapped())==2){
                        rbtn2.setChecked(true);
                        break;
                    }
                }
            }
        }




        RadioButton[] bt1 = new RadioButton[3];
        for(int f=1;f<rg2.getChildCount();f++)
        {
            View o = rg2.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt1[f]=((RadioButton)o);
                if(ind.getQ410Pushed()!= null &&  !ind.getQ410Pushed().equals(""))
                {
                    if(Integer.parseInt(ind.getQ410Pushed())== 1)
                    {

                        rbtn3.setChecked(true);
                        break;
                    }
                    else if(Integer.parseInt(ind.getQ410Pushed())==2){
                        rbtn4.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bt2 = new RadioButton[3];
        for(int f=1;f<rg3.getChildCount();f++)
        {
            View o = rg3.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt2[f]=((RadioButton)o);
                if(ind.getQ410Choked()!= null &&  !ind.getQ410Choked().equals(""))
                {
                    if(Integer.parseInt(ind.getQ410Choked()) == 1)
                    {

                        rbtn5.setChecked(true);
                        break;
                    }
                    else if(Integer.parseInt(ind.getQ410Choked()) == 2){
                        rbtn6.setChecked(true);
                        break;
                    }

                }
            }
        }

        RadioButton[] bt3 = new RadioButton[3];
        for(int f=1;f<rg4.getChildCount();f++)
        {
            View o = rg4.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt3[f]=((RadioButton)o);
                if(ind.getQ410Threatened()!= null &&  !ind.getQ410Threatened().equals(""))
                {
                    if(Integer.parseInt( ind.getQ410Threatened())== 1)
                    {

                        rbtn7.setChecked(true);
                        break;
                    }
                    else if(Integer.parseInt( ind.getQ410Threatened()) == 2){
                        rbtn8.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bt4 = new RadioButton[3];
        for(int f=1;f<rg4.getChildCount();f++)
        {
            View o = rg4.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt4[f]=((RadioButton)o);
                if(ind.getQ410Physical()!= null &&  !ind.getQ410Physical().equals(""))
                {
                    if(Integer.parseInt(ind.getQ410Physical()) == 1)
                    {

                        rbtn9.setChecked(true);
                        break;
                    }
                    else if(Integer.parseInt( ind.getQ410Physical()) == 2){
                        rbtn10.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bt5 = new RadioButton[3];
        for(int f=1;f<rg5.getChildCount();f++)
        {
            View o = rg5.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt5[f]=((RadioButton)o);
                if(ind.getQ410Forced()!= null &&  !ind.getQ410Forced().equals(""))
                {
                    if(Integer.parseInt(ind.getQ410Forced()) == 1)
                    {

                        rbtn11.setChecked(true);
                        break;
                    }
                    else if(Integer.parseInt( ind.getQ410Forced()) == 2){
                        rbtn12.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bt6 = new RadioButton[3];
        for(int f=1;f<rg6.getChildCount();f++)
        {
            View o = rg6.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt6[f]=((RadioButton)o);
                if(ind.getQ410MadeAfraid()!= null &&  !ind.getQ410MadeAfraid().equals(""))
                {
                    if(Integer.parseInt(ind.getQ410MadeAfraid())== 1)
                    {

                        rbtn13.setChecked(true);
                        break;
                    }
                    else if(Integer.parseInt( ind.getQ410MadeAfraid()) == 2){
                        rbtn14.setChecked(true);
                        break;
                    }
                }
            }
        }


        final String[] s1 = new String[1];
        final String[] s2 = new String[1];
        final String[] s3 = new String[1];
        final String[] s4 = new String[1];
        final String[] s5 = new String[1];
        final String[] s6 = new String[1];
        final String[] s7  = new String[1];

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rg1_01)
                {
                    // is checked
                    s1[0] = "1";
                }
                else if(i == R.id.rg1_02)
                {
                    s1[0] = "2";
                }
            }
        });

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rg2_1)
                {
                    // is checked
                    s2[0] = "1";
                }
                else if(i == R.id.rg2_2)
                {
                    s2[0] = "2";
                }
            }
        });

        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rg3_1)
                {
                    // is checked
                    s3[0] = "1";
                }
                else if(i == R.id.rg3_2)
                {
                    s3[0] = "2";
                }
            }
        });


        rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rg4_1)
                {
                    // is checked
                    s4[0] = "1";
                }
                else if(i == R.id.rg4_2)
                {
                    s4[0] = "2";
                }
            }
        });


        rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rg5_1)
                {
                    // is checked
                    s5[0] = "1";
                }
                else if(i == R.id.rg5_2)
                {
                    s5[0] = "2";
                }
            }
        });


        rg6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rg6_1)
                {
                    // is checked
                    s6[0] = "1";
                }
                else if(i == R.id.rg6_2)
                {
                    s6[0] = "2";
                }
            }
        });

        rg7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rg7_1)
                {
                    // is checked
                    s7[0] = "1";
                }
                else if(i == R.id.rg7_2)
                {
                    s7[0] = "2";
                }
            }
        });





        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg1.getCheckedRadioButtonId();
                selected1 = (RadioButton) findViewById(selectedId);

                if (selected1 == null) {
                    lib.showError(q410.this, "Q410: ERROR", "Slapped or threw something that could hurt you");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {


                    int selectedId2 = rg2.getCheckedRadioButtonId();
                    selected2 = findViewById(selectedId2);
                    if (selected2 == null) {
                        lib.showError(q410.this, "Q410: ERROR", "Pushed or shoved you");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
//                    selected2 = (RadioButton) findViewById(selectedId2);
//                    if(selected2 == rbtn3){
//
//                        individual.setQ410Pushed("1");
//                    }else{
//
//                        individual.setQ410Pushed("2");
//                    }
//
//                }

                        int selectedId3 = rg3.getCheckedRadioButtonId();
                        selected3 = (RadioButton) findViewById(selectedId3);

                        if (selected3 == null) {
                            lib.showError(q410.this, "Q410: ERROR", "Chocked or burned you");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {
//                    selected3 = (RadioButton) findViewById(selectedId3);
//                    if(selected3 == rbtn5){
//
//                        individual.setQ410Choked("1");
//                    }else{
//
//                        individual.setQ410Choked("2");
//                    }
//
//                }


                            int selectedId4 = rg4.getCheckedRadioButtonId();
                            selected4 = (RadioButton) findViewById(selectedId4);
                            if (selected4 == null) {
                                lib.showError(q410.this, "Q410: ERROR", "Threatened or used a gun, knife or other weapon against you");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {
//                    selected4 = (RadioButton) findViewById(selectedId4);
//                    if(selected4 == rbtn7){
//
//                        individual.setQ410Threatened("1");
//                    }else{
//
//                        individual.setQ410Threatened("2");
//                    }
//
//                }


                                int selectedId5 = rg5.getCheckedRadioButtonId();
                                selected5 = (RadioButton) findViewById(selectedId5);
                                if (selected5 == null) {
                                    lib.showError(q410.this, "Q410: ERROR", "Physically forced you to have sexual intercourse against your will");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);
                                } else {
//                    selected5 = (RadioButton) findViewById(selectedId5);
//                    if(selected5 == rbtn9){
//
//                        individual.setQ410Physical("1");
//                    }else{
//
//                        individual.setQ410Physical("2");
//                    }
//
//                }


                                    int selectedId6 = rg6.getCheckedRadioButtonId();
                                    selected6 = (RadioButton) findViewById(selectedId6);
                                    if (selected6 == null) {
                                        lib.showError(q410.this, "Q410: ERROR", "Forced you to do something sexual you found degrading or humiliating");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);
                                    } else {
//                    selected6 = (RadioButton) findViewById(selectedId6);
//                    if(selected6 == rbtn11){
//
//                        individual.setQ410Forced("1");
//                    }else{
//
//                        individual.setQ410Forced("2");
//                    }
//
//                }

                                        int selectedId7 = rg7.getCheckedRadioButtonId();
                                        selected7 = (RadioButton) findViewById(selectedId7);
                                        if (selected7 == null) {
                                            lib.showError(q410.this, "Q410: ERROR", "Made you afraid of what would happen if did not have sexual intercorse");
                                            /**
                                             * VIBRATE DEVICE
                                             */
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);
                                        } else {
//                    selected7 = (RadioButton) findViewById(selectedId7);
//                    if(selected7 == rbtn13){
//
//                        individual.setQ410MadeAfraid("1");
//                    }else{


                                            individual.setQ410MadeAfraid(s1[0]);
                                            individual.setQ410Forced(s2[0]);
                                            individual.setQ410Physical(s3[0]);
                                            individual.setQ410Threatened(s4[0]);
                                            individual.setQ410Choked(s5[0]);
                                            individual.setQ410Pushed(s6[0]);
                                            individual.setQ410Slapped(s7[0]);


                                            myDB.onOpen(myDB.getReadableDatabase());
                                            myDB.getWritableDatabase();
                                            myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                            myDB.close();

                                            Intent intent = new Intent(q410.this, q501.class);
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

                if ( individual.getQ403().equals("2")  && individual.getQ101().equals("2") &&
                        (individual.getQ201().equals("2") || individual.getQ201().equals("3") ||individual.getQ201().equals("4") ||
                                individual.getQ201().equals("5") || individual.getQ202().equals("1"))) {

                    Intent intent = new Intent(q410.this, q403.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);

                }
               else
                {
                    Intent intent = new Intent(q410.this, q408.class);
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q410.this).toBundle());

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
