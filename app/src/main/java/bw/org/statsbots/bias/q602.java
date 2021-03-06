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

import java.io.Serializable;
import java.util.List;

public class q602 extends AppCompatActivity implements Serializable{

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected CheckBox ck1txt, ck2txt, ck3txt, ck4txt, ck5txt, ck6txt, ck7txt, ck8txt, ck10txt,ck11txt, ck12txt, ck13txt, ck14txt, ck15txt,chkOther, selected = null;
    protected Button btn;
    protected EditText Q602edt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q602);


        setTitle("Q602: KNOWLEDGE ABOUT HIV/AIDS AND TB");
        lib = new LibraryClass();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();
        //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());



        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

           thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);



        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }
        }


        if (individual.getQ601a() != null && individual.getQ601a().equals("2"))
        {

            individual.setQ602_1(null);
            individual.setQ602_2(null);
            individual.setQ602_3(null);
            individual.setQ602_4(null);
            individual.setQ602_5(null);
            individual.setQ602_6(null);
            individual.setQ602_7(null);
            individual.setQ602_8(null);
            individual.setQ602_10(null);
            individual.setQ602_11(null);
            individual.setQ602_12(null);
            individual.setQ602_13(null);
            individual.setQ602_14(null);
            individual.setQ602_15(null);
            individual.setQ602_Other(null);


//                            individual.setQ604(null);
//                            individual.setQ604a(null);
//                            individual.setQ604a(null);
//                            individual.setQ604b_1(null);
//                            individual.setQ604b_2(null);
//                            individual.setQ604b_3(null);
//                            individual.setQ604b_4(null);
//                            individual.setQ604b_5(null);
//                            individual.setQ604b_6(null);
//                            individual.setQ604b_7(null);
//
//                            individual.setQ604b_8(null);
//                            individual.setQ604b_10(null);
//                            individual.setQ604b_11(null);
//                            individual.setQ604b_12(null);
//                            individual.setQ604b_13(null);
//                            individual.setQ604b_14(null);
//                            individual.setQ604b_15(null);
//                            individual.setQ604b_Other(null);
//
//                            individual.setQ605_1(null);
//                            individual.setQ605_2(null);
//                            individual.setQ605_3(null);
//                            individual.setQ605_4(null);
//                            individual.setQ605_5(null);
//                            individual.setQ605_9(null);
//                            individual.setQ605_Other(null);
//




            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q602.this, q603.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }




        if((Integer.valueOf(individual.getQ102()) > 64 && (sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1"))))
        {

            individual.setQ602_1(null);
            individual.setQ602_2(null);
            individual.setQ602_3(null);
            individual.setQ602_4(null);
            individual.setQ602_5(null);
            individual.setQ602_6(null);
            individual.setQ602_7(null);
            individual.setQ602_8(null);
            individual.setQ602_10(null);
            individual.setQ602_11(null);
            individual.setQ602_12(null);
            individual.setQ602_13(null);
            individual.setQ602_14(null);
            individual.setQ602_15(null);
            individual.setQ602_Other(null);
            individual.setQ603_1(null);
            individual.setQ603_2(null);
            individual.setQ603_3(null);
            individual.setQ603_4(null);
            individual.setQ603_5(null);
            individual.setQ603_6(null);
            individual.setQ603_7(null);
            individual.setQ603_9(null);
            individual.setQ603_Other(null);

//                            individual.setQ604(null);
//                            individual.setQ604a(null);
//                            individual.setQ604a(null);
//                            individual.setQ604b_1(null);
//                            individual.setQ604b_2(null);
//                            individual.setQ604b_3(null);
//                            individual.setQ604b_4(null);
//                            individual.setQ604b_5(null);
//                            individual.setQ604b_6(null);
//                            individual.setQ604b_7(null);
//
//                            individual.setQ604b_8(null);
//                            individual.setQ604b_10(null);
//                            individual.setQ604b_11(null);
//                            individual.setQ604b_12(null);
//                            individual.setQ604b_13(null);
//                            individual.setQ604b_14(null);
//                            individual.setQ604b_15(null);
//                            individual.setQ604b_Other(null);
//
//                            individual.setQ605_1(null);
//                            individual.setQ605_2(null);
//                            individual.setQ605_3(null);
//                            individual.setQ605_4(null);
//                            individual.setQ605_5(null);
//                            individual.setQ605_9(null);
//                            individual.setQ605_Other(null);
//




            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent q1o2 = new Intent(q602.this, q604.class);
            q1o2.putExtra("Individual", individual);
            startActivity(q1o2);
        }

        if((sample.getStatusCode().equals("3") || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0")))
        || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) &&(p1.getP06().equals("2"))) ))
        {


            individual.setQ602_1(null);
            individual.setQ602_2(null);
            individual.setQ602_3(null);
            individual.setQ602_4(null);
            individual.setQ602_5(null);
            individual.setQ602_6(null);
            individual.setQ602_7(null);
            individual.setQ602_8(null);
            individual.setQ602_10(null);
            individual.setQ602_11(null);
            individual.setQ602_12(null);
            individual.setQ602_13(null);
            individual.setQ602_14(null);
            individual.setQ602_15(null);
            individual.setQ602_Other(null);
            individual.setQ603_1(null);
            individual.setQ603_2(null);
            individual.setQ603_3(null);
            individual.setQ603_4(null);
            individual.setQ603_5(null);
            individual.setQ603_6(null);
            individual.setQ603_7(null);
            individual.setQ603_9(null);
            individual.setQ603_Other(null);

//                            individual.setQ604(null);
//                            individual.setQ604a(null);
//                            individual.setQ604a(null);
//                            individual.setQ604b_1(null);
//                            individual.setQ604b_2(null);
//                            individual.setQ604b_3(null);
//                            individual.setQ604b_4(null);
//                            individual.setQ604b_5(null);
//                            individual.setQ604b_6(null);
//                            individual.setQ604b_7(null);
//
//                            individual.setQ604b_8(null);
//                            individual.setQ604b_10(null);
//                            individual.setQ604b_11(null);
//                            individual.setQ604b_12(null);
//                            individual.setQ604b_13(null);
//                            individual.setQ604b_14(null);
//                            individual.setQ604b_15(null);
//                            individual.setQ604b_Other(null);
//
//                            individual.setQ605_1(null);
//                            individual.setQ605_2(null);
//                            individual.setQ605_3(null);
//                            individual.setQ605_4(null);
//                            individual.setQ605_5(null);
//                            individual.setQ605_9(null);
//                            individual.setQ605_Other(null);
//




            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent q1o2 = new Intent(q602.this, q604.class);
            q1o2.putExtra("Individual", individual);
            startActivity(q1o2);
        }





//btn = findViewById(R.id.btn);

        ck1txt = findViewById(R.id.q602_1);
        ck2txt = findViewById(R.id.q602_2);
        ck3txt = findViewById(R.id.q602_3);
        ck4txt = findViewById(R.id.q602_4);
        ck5txt = findViewById(R.id.q602_5);
        ck6txt = findViewById(R.id.q602_6);
        ck7txt = findViewById(R.id.q602_7);
        ck8txt = findViewById(R.id.q602_8);
        ck10txt = findViewById(R.id.q602_10);

        ck11txt = findViewById(R.id.q602_11);
        ck12txt = findViewById(R.id.q602_12);
        ck13txt = findViewById(R.id.q602_13);
        ck14txt = findViewById(R.id.q602_14);
        ck15txt = findViewById(R.id.q602_15);

        chkOther = findViewById(R.id.q602_Other);
        Q602edt = findViewById(R.id.q602edt_Other);

        if(ind.getQ602_1()!= null &&  !ind.getQ602_1().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_1())== 1)
            {
                ck1txt.setChecked(true);

            }else
            {
                ck1txt.setChecked(false);
            }
        }



        if(ind.getQ602_2()!= null &&  !ind.getQ602_2().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_2())== 1)
            {
                ck2txt.setChecked(true);

            }else
            {
                ck2txt.setChecked(false);
            }
        }
        if(ind.getQ602_3()!= null &&  !ind.getQ602_3().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_3())== 1)
            {
                ck3txt.setChecked(true);

            }else
            {
                ck3txt.setChecked(false);
            }
        }

        if(ind.getQ602_4()!= null &&  !ind.getQ602_4().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_4())== 1)
            {
                ck4txt.setChecked(true);

            }else
            {
                ck4txt.setChecked(false);
            }
        }

        if(ind.getQ602_5()!= null &&  !ind.getQ602_5().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_5())== 1)
            {
                ck5txt.setChecked(true);

            }else
            {
                ck5txt.setChecked(false);
            }
        }


        if(ind.getQ602_6()!= null &&  !ind.getQ602_6().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_6())== 1)
            {
                ck6txt.setChecked(true);

            }else
            {
                ck6txt.setChecked(false);
            }
        }

        if(ind.getQ602_7()!= null &&  !ind.getQ602_7().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_7())== 1)
            {
                ck7txt.setChecked(true);

            }else
            {
                ck7txt.setChecked(false);
            }
        }

        if(ind.getQ602_8()!= null &&  !ind.getQ602_8().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_8())== 1)
            {
                ck8txt.setChecked(true);

            }else
            {
                ck8txt.setChecked(false);
            }
        }

        if(ind.getQ602_10()!= null &&  !ind.getQ602_10().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_10())== 1)
            {
                ck10txt.setChecked(true);

            }else
            {
                ck10txt.setChecked(false);
            }
        }
        if(ind.getQ602_11()!= null &&  !ind.getQ602_11().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_11())== 1)
            {
                ck11txt.setChecked(true);

            }else
            {
                ck11txt.setChecked(false);
            }
        }

        if(ind.getQ602_12()!= null &&  !ind.getQ602_12().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_12())== 1)
            {
                ck12txt.setChecked(true);

            }else
            {
                ck12txt.setChecked(false);
            }
        }
        if(ind.getQ602_13()!= null &&  !ind.getQ602_13().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_13())== 1)
            {
                ck13txt.setChecked(true);

            }else
            {
                ck13txt.setChecked(false);
            }
        }

        if(ind.getQ602_14()!= null &&  !ind.getQ602_14().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_14())== 1)
            {
                ck14txt.setChecked(true);

            }else
            {
                ck14txt.setChecked(false);
            }
        }

        if(ind.getQ602_15()!= null &&  !ind.getQ602_15().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_15())== 1)
            {
                ck15txt.setChecked(true);

            }else
            {
                ck15txt.setChecked(false);
            }
        }


        if(ind.getQ602_Other()!= null &&  !ind.getQ602_Other().equals(""))
        {
            if(Integer.parseInt(ind.getQ602_Other().substring(0,1)) == 1)
            {
                ck15txt.setChecked(true);
                Q602edt.setVisibility(View.VISIBLE);
                Q602edt.setText(ind.getQ602_Other().substring(1,ind.getQ602_Other().length()));

            }else
            {
                ck15txt.setChecked(false);
            }
        }




        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((((!ck1txt.isChecked() && !ck2txt.isChecked() && !ck3txt.isChecked() && !ck4txt.isChecked() && !ck5txt.isChecked() && !ck6txt.isChecked() && !ck7txt.isChecked() && !ck8txt.isChecked()
                        && !ck10txt.isChecked() && !ck11txt.isChecked() && !ck12txt.isChecked() && !ck13txt.isChecked() && !ck14txt.isChecked() && !ck15txt.isChecked() && !chkOther.isChecked())))) {
                    lib.showError(q602.this, "Q602:", "From what source(s) did you receive information about HIV and AIDS?" +
                            "Please select atleast one checkbox");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    if ((((chkOther.isChecked() && Q602edt.length() == 0)))) {
                        lib.showError(q602.this, "Q602:", "Please specify other or uncheck Other specify");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                       /* individual.setQ602_1(ck1txt.getText().toString().substring(0,1));
                        individual.setQ602_2(ck2txt.getText().toString().substring(0,1));
                        individual.setQ602_3(ck3txt.getText().toString().substring(0,1));
                        individual.setQ602_4(ck4txt.getText().toString().substring(0,1));
                        individual.setQ602_5(ck5txt.getText().toString().substring(0,1));
                        individual.setQ602_6(ck6txt.getText().toString().substring(0,1));
                        individual.setQ602_7(ck7txt.getText().toString().substring(0,1));
                        individual.setQ602_8(ck8txt.getText().toString().substring(0,1));
                        individual.setQ602_10(ck10txt.getText().toString().substring(0,1));
                       individual.setQ602_11(ck11txt.getText().toString().substring(0,1));
                       individual.setQ602_12(ck12txt.getText().toString().substring(0,1));
                       individual.setQ602_13(ck13txt.getText().toString().substring(0,1));
                        individual.setQ602_14(ck14txt.getText().toString().substring(0,1));
                        individual.setQ602_15(ck15txt.getText().toString().substring(0,1));
                       individual.setQ602_Other(chkOther.getText().toString().substring(0,1));
                        individual.setQ602_Otherspecify(Q602edt.getText().toString());
*/

                        if (ck1txt.isChecked()) {
                            individual.setQ602_1("1");
                        } else {
                            individual.setQ602_1("2");
                        }

                        if (ck2txt.isChecked()) {
                            individual.setQ602_2("1");
                        } else {
                            individual.setQ602_2("2");
                        }
                        if (ck3txt.isChecked()) {
                            individual.setQ602_3("1");
                        } else {
                            individual.setQ602_3("2");
                        }
                        if (ck4txt.isChecked()) {
                            individual.setQ602_4("1");
                        } else {
                            individual.setQ602_4("2");
                        }
                        if (ck5txt.isChecked()) {
                            individual.setQ602_5("1");
                        } else {
                            individual.setQ602_5("2");
                        }
                        if (ck6txt.isChecked()) {
                            individual.setQ602_6("1");
                        } else {
                            individual.setQ602_6("2");
                        } if (ck7txt.isChecked()) {
                            individual.setQ602_7("1");
                        } else {
                            individual.setQ602_7("2");
                        }
                        if (ck8txt.isChecked()) {
                            individual.setQ602_8("1");
                        } else {
                            individual.setQ602_8("2");
                        } if (ck10txt.isChecked()) {
                            individual.setQ602_10("1");
                        } else {
                            individual.setQ602_10("2");
                        } if (ck11txt.isChecked()) {
                            individual.setQ602_11("1");
                        } else {
                            individual.setQ602_11("2");
                        } if (ck12txt.isChecked()) {
                            individual.setQ602_12("1");
                        } else {
                            individual.setQ602_12("2");
                        } if (ck13txt.isChecked()) {
                            individual.setQ602_13("1");
                        } else {
                            individual.setQ602_13("2");
                        } if (ck14txt.isChecked()) {
                            individual.setQ602_14("1");
                        } else {
                            individual.setQ602_14("2");
                        } if (ck15txt.isChecked()) {
                            individual.setQ602_15("1");
                        } else {
                            individual.setQ602_15("2");
                        } if (chkOther.isChecked()) {
                            individual.setQ602_Other("1");
                            individual.setQ602_Other("1"+Q602edt.getText().toString());

                        } else {
                            individual.setQ602_Other("2");
                        }
                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        myDB.close();

                        Intent intent = new Intent(q602.this, q603.class);
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
                finish();
                Intent intent = new Intent(q602.this, q601.class);
                intent.putExtra("Individual", individual);
                startActivity(intent);
            }


        });

    }



    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.q602_1:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;

            case R.id.q602_2:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_3:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_4:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_5:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_6:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_7:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_8:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_10:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_11:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_12:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_13:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_14:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_15:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_Other:
                if (checked) {
                    if (chkOther.isChecked()) {
                        Q602edt.setVisibility(View.VISIBLE);
                    }

                    // Put some meat on the sandwich
                    else {
                        // Remove the meat
                        Q602edt.setVisibility(View.INVISIBLE);
                        Q602edt.setText("");
                    }
                    break;


                }
        }
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q602.this).toBundle());

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