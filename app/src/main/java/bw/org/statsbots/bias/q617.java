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

public class q617 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    private EditText edtOther;
    protected CheckBox chkOther;
    protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtn8, rbtn9, rbtn10, rbtn11, rbtn12, rbtn13, rbtn14, rbtn15, rbtn16, rbtn17, rbtn18, rbtn19, rbtn20, rbtn21, rbtn22, rbtn23, rbtn24, rbtnOther;
    protected RadioGroup  rg1, rg2, rg3, rg4, rg5, rg6, rg7, rg8;
    protected RadioButton selected1, selected2, selected3, selected4, selected5, selected6, selected7, selected8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q617);

        setTitle("Q617: KNOWLEDGE ABOUT HIV/AIDS AND TB ");
        lib = new LibraryClass();

        rg1 = (RadioGroup) findViewById(R.id.rg1) ;
        rg2 = (RadioGroup) findViewById(R.id.rg2) ;
        rg3 = (RadioGroup) findViewById(R.id.rg3) ;
        rg4 = (RadioGroup) findViewById(R.id.rg4) ;
        rg5 = (RadioGroup) findViewById(R.id.rg5) ;
        rg6 = (RadioGroup) findViewById(R.id.rg6) ;
        rg7 = (RadioGroup) findViewById(R.id.rg7) ;
        rg8 = (RadioGroup) findViewById(R.id.rg8) ;


        rbtn1 = (RadioButton) findViewById(R.id.rg1_01) ;
        rbtn2 = (RadioButton) findViewById(R.id.rg1_02) ;
        rbtn3 = (RadioButton) findViewById(R.id.rg1_03) ;

        rbtn4 = (RadioButton) findViewById(R.id.rg2_1) ;
        rbtn5 = (RadioButton) findViewById(R.id.rg2_2) ;
        rbtn6 = (RadioButton) findViewById(R.id.rg2_3) ;

        rbtn7 = (RadioButton) findViewById(R.id.rg3_1) ;
        rbtn8 = (RadioButton) findViewById(R.id.rg3_2) ;
        rbtn9 = (RadioButton) findViewById(R.id.rg3_3) ;

        rbtn10 = (RadioButton) findViewById(R.id.rg4_1) ;
        rbtn11 = (RadioButton) findViewById(R.id.rg4_2) ;
        rbtn12 = (RadioButton) findViewById(R.id.rg4_3) ;

        rbtn13 = (RadioButton) findViewById(R.id.rg5_1) ;
        rbtn14 = (RadioButton) findViewById(R.id.rg5_2) ;
        rbtn15 = (RadioButton) findViewById(R.id.rg5_3) ;

        rbtn16 = (RadioButton) findViewById(R.id.rg6_1) ;
        rbtn17 = (RadioButton) findViewById(R.id.rg6_2) ;
        rbtn18 = (RadioButton) findViewById(R.id.rg6_3) ;

        rbtn19 = (RadioButton) findViewById(R.id.rg7_1) ;
        rbtn20 = (RadioButton) findViewById(R.id.rg7_2) ;
        rbtn21 = (RadioButton) findViewById(R.id.rg7_3) ;

        rbtn22 = (RadioButton) findViewById(R.id.rg8_1) ;
        rbtn23 = (RadioButton) findViewById(R.id.rg8_2) ;
        rbtn24 = (RadioButton) findViewById(R.id.rg8_3) ;

        chkOther = (CheckBox) findViewById(R.id.checkOther) ;

        edtOther = (EditText) findViewById(R.id.Othertxt) ;


        if (chkOther.isChecked())
        {
            edtOther.setVisibility(View.VISIBLE);
        }
        else
        {
            edtOther.setVisibility(View.INVISIBLE);
            edtOther.setText("");
        }

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








        final String[] s1 = new String[1];
        final String[] s2 = new String[1];
        final String[] s3 = new String[1];
        final String[] s4 = new String[1];
        final String[] s5 = new String[1];
        final String[] s6 = new String[1];
        final String[] s7  = new String[1];
        final String[] s8  = new String[1];


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
                else if(i == R.id.rg1_03)
                {
                    s1[0] = "9";
                }
            }
        });
        rg2 .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
                else if(i == R.id.rg2_3)
                {
                    s2[0] = "9";
                }
            }
        });
        rg3 .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
                else if(i == R.id.rg3_3)
                {
                    s3[0] = "9";
                }
            }
        });
        rg4 .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
                else if(i == R.id.rg4_3)
                {
                    s4[0] = "9";
                }
            }
        });
        rg5 .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
                else if(i == R.id.rg5_3)
                {
                    s5[0] = "9";
                }
            }
        });
        rg6 .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
                else if(i == R.id.rg6_3)
                {
                    s6[0] = "9";
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
                else if(i == R.id.rg7_3)
                {
                    s7[0] = "9";
                }
            }
        });
        rg8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rg8_1)
                {
                    // is checked
                    s8[0] = "1";
                }
                else if(i == R.id.rg8_2)
                {
                    s8[0] = "2";
                }
                else if(i == R.id.rg8_3)
                {
                    s8[0] = "9";
                }
            }
        });

        RadioButton[] bt1 = new RadioButton[4];
        for(int f=1;f<rg1.getChildCount();f++)
        {
            View o = rg1.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt1[f]=((RadioButton)o);
                if(ind.getQ617a()!= null &&  !ind.getQ617a().equals(""))
                {
                    if(Integer.parseInt(ind.getQ617a())==f)
                    {
                        RadioButton radioButton = bt1[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bt2 = new RadioButton[4];
        for(int f=1;f<rg2.getChildCount();f++)
        {
            View o = rg2.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt2[f]=((RadioButton)o);
                if(ind.getQ617b()!= null &&  !ind.getQ617b().equals(""))
                {
                    if(Integer.parseInt(ind.getQ617b())==f)
                    {
                        RadioButton radioButton = bt2[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }


        RadioButton[] bt3 = new RadioButton[4];
        for(int f=1;f<rg3.getChildCount();f++)
        {
            View o = rg3.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt3[f]=((RadioButton)o);
                if(ind.getQ617c()!= null &&  !ind.getQ617c().equals(""))
                {
                    if(Integer.parseInt(ind.getQ617c())==f)
                    {
                        RadioButton radioButton = bt3[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }


        RadioButton[] bt4 = new RadioButton[4];
        for(int f=1;f<rg4.getChildCount();f++)
        {
            View o = rg4.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt4[f]=((RadioButton)o);
                if(ind.getQ617d()!= null &&  !ind.getQ617d().equals(""))
                {
                    if(Integer.parseInt(ind.getQ617d())==f)
                    {
                        RadioButton radioButton = bt4[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }
        RadioButton[] bt5 = new RadioButton[4];
        for(int f=1;f<rg5.getChildCount();f++)
        {
            View o = rg5.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt5[f]=((RadioButton)o);
                if(ind.getQ617e()!= null &&  !ind.getQ617e().equals(""))
                {
                    if(Integer.parseInt(ind.getQ617e())==f)
                    {
                        RadioButton radioButton = bt5[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }


        RadioButton[] bt6 = new RadioButton[4];
        for(int f=1;f<rg6.getChildCount();f++)
        {
            View o = rg6.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt6[f]=((RadioButton)o);
                if(ind.getQ617f()!= null &&  !ind.getQ617f().equals(""))
                {
                    if(Integer.parseInt(ind.getQ617f())==f)
                    {
                        RadioButton radioButton = bt6[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bt7= new RadioButton[4];
        for(int f=1;f<rg7.getChildCount();f++)
        {
            View o = rg7.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt7[f]=((RadioButton)o);
                if(ind.getQ617g()!= null &&  !ind.getQ617g().equals(""))
                {
                    if(Integer.parseInt(ind.getQ617g())==f)
                    {
                        RadioButton radioButton = bt7[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bt8= new RadioButton[4];
        for(int f=1;f<rg8.getChildCount();f++)
        {
            View o = rg8.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt8[f]=((RadioButton)o);
                if(ind.getQ617h()!= null &&  !ind.getQ617h().equals(""))
                {
                    if(Integer.parseInt(ind.getQ617h())==f)
                    {
                        RadioButton radioButton = bt8[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        if(ind.getQ617_0ther()!= null)
        {
            edtOther.setText(ind.getQ617_0ther());
        }



        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg1.getCheckedRadioButtonId();
                selected1 = (RadioButton) findViewById(selectedId);

                if (selected1 == null) {
                    lib.showError(q617.this, "Q617: ERROR", "SHARING A MEAL WITH A PERSON WHO HAS TB");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    int selectedId2 = rg2.getCheckedRadioButtonId();
                    selected2 = (RadioButton) findViewById(selectedId2);

                    if (selected2 == null) {
                        lib.showError(q617.this, "Q617: ERROR", "SHARING CLOTHES WITH A PERSON WHO HAS TB");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    } else {
                        int selectedId3 = rg3.getCheckedRadioButtonId();
                        selected3 = (RadioButton) findViewById(selectedId3);
                    }
                    if (selected3 == null) {
                        lib.showError(q617.this, "Q617: ERROR", "SEX WITH A WOMAN WHO MISCARRIED");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        int selectedId4 = rg4.getCheckedRadioButtonId();
                        selected4 = (RadioButton) findViewById(selectedId4);

                        if (selected4 == null) {
                            lib.showError(q617.this, "Q617: ERROR", "SEX WITH A WIDOW/ER");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);

                        } else {
                            int selectedId5 = rg5.getCheckedRadioButtonId();
                            selected5 = (RadioButton) findViewById(selectedId5);

                            if (selected5 == null) {
                                lib.showError(q617.this, "Q617: ERROR", "BORN IN A FAMILY WITH HIV");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);

                            } else {
                                int selectedId6 = rg6.getCheckedRadioButtonId();
                                selected6 = (RadioButton) findViewById(selectedId6);

                            if (selected6 == null) {
                                lib.showError(q617.this, "Q617: ERROR", " SEJESO ");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {
                                int selectedId7 = rg7.getCheckedRadioButtonId();
                                selected7 = (RadioButton) findViewById(selectedId7);

                                if (selected7 == null) {
                                    lib.showError(q617.this, "Q617: ERROR", "TOUCHING ITEMS IN PUBLIC PLACES");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);

                                } else {
                                    int selectedId8 = rg8.getCheckedRadioButtonId();
                                    selected8 = (RadioButton) findViewById(selectedId8);

                                    if (selected8 == null) {
                                        lib.showError(q617.this, "Q617: ERROR", "BEING IN CONTACT WITH SOMEONE WITH TB");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);

                                    }
                                    if (chkOther.isChecked() && edtOther.length() == 0) {
                                        lib.showError(q617.this, "Q617: ERROR: Other", "Other specify");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);

                                    } else {
                                        individual.setQ617a(s1[0]);
                                        individual.setQ617b(s2[0]);
                                        individual.setQ617c(s3[0]);
                                        individual.setQ617d(s4[0]);
                                        individual.setQ617e(s5[0]);
                                        individual.setQ617f(s6[0]);
                                        individual.setQ617g(s7[0]);
                                        individual.setQ617h(s8[0]);
                                        individual.setQ617_0ther(edtOther.getText().toString());

                                        myDB.onOpen(myDB.getReadableDatabase());
                                        myDB.getWritableDatabase();
                                        myDB.updateInd("Q617Meal", individual.getAssignmentID(), individual.getBatch(), individual.getQ617a(), String.valueOf(individual.getSRNO()));
                                        myDB.updateInd("Q617Clothes", individual.getAssignmentID(), individual.getBatch(), individual.getQ617b(), String.valueOf(individual.getSRNO()));
                                        myDB.updateInd("Q617Miscarried", individual.getAssignmentID(), individual.getBatch(), individual.getQ617c(), String.valueOf(individual.getSRNO()));
                                        myDB.updateInd("Q617Widow", individual.getAssignmentID(), individual.getBatch(), individual.getQ617d(), String.valueOf(individual.getSRNO()));
                                        myDB.updateInd("Q617FamilyHIV", individual.getAssignmentID(), individual.getBatch(), individual.getQ617e(), String.valueOf(individual.getSRNO()));
                                        myDB.updateInd("Q617Sejeso", individual.getAssignmentID(), individual.getBatch(), individual.getQ617f(), String.valueOf(individual.getSRNO()));
                                        myDB.updateInd("Q617Touching", individual.getAssignmentID(), individual.getBatch(), individual.getQ617g(), String.valueOf(individual.getSRNO()));
                                        myDB.updateInd("Q617Someone", individual.getAssignmentID(), individual.getBatch(), individual.getQ617h(), String.valueOf(individual.getSRNO()));
                                        myDB.updateInd("Q617Other", individual.getAssignmentID(), individual.getBatch(), individual.getQ617_0ther(), String.valueOf(individual.getSRNO()));
                                       // myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                        myDB.close();

                                        Intent intent = new Intent(q617.this, q618.class);
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
                finish();
                Intent intent = new Intent(q617.this, q616.class);
                intent.putExtra("Individual", individual);
                startActivity(intent);
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
                alertDialogBuilder.setMessage(" Are you sure you want to pause the interview");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(getApplicationContext(), started_household.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q617.this).toBundle());

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



