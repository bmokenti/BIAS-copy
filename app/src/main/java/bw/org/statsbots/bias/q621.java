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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;


public class q621 extends AppCompatActivity implements Serializable {


    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtb1,rbtb2,rbtbOther;
    protected RadioGroup rg1,rg2;
    protected  DatabaseHelper myDB;
    protected EditText edtbOther, edtaOther;
    protected RadioButton selectedRbtn,selectedRbtn2;
    protected TextView txta, txtb;
    protected CheckBox chka1,chka2,chka3,chka4,chka5,chka6,chka7,chkaOther;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q621);
        setTitle("q621: KNOWLEDGE ABOUT HIV/AIDS AND TB ");
        lib = new LibraryClass();

        rg1 = (RadioGroup) findViewById(R.id.q621radioGroup) ;
        rg2 = (RadioGroup) findViewById(R.id.q621bGroup) ;



        rbtn1 = (RadioButton) findViewById(R.id.q621_1);
        rbtn2 = (RadioButton) findViewById(R.id.q621_2);

        chka1 = (CheckBox) findViewById(R.id.Q621a_1);
        chka2 =  (CheckBox)findViewById(R.id.Q621a_2);
        chka3 = (CheckBox) findViewById(R.id.Q621a_3);
        chka4 = (CheckBox) findViewById(R.id.Q621a_4);
        chka5 = (CheckBox) findViewById(R.id.Q621a_5);
        chka6 = (CheckBox) findViewById(R.id.Q621a_6);
        chka7 = (CheckBox) findViewById(R.id.Q621a_7);
        chkaOther =(CheckBox) findViewById(R.id.Q621a_Other);
        edtaOther = (EditText) findViewById(R.id.Q621a_Other1) ;


        rbtb1 =  (RadioButton) findViewById(R.id.q621b_1);
        rbtb2 =  (RadioButton) findViewById(R.id.q621b_2);
        rbtbOther = (RadioButton) findViewById(R.id.q621b_other);
        edtbOther = (EditText) findViewById(R.id.q621b_other1) ;

        txta = (TextView) findViewById(R.id.Q621atxt) ;
        txtb = (TextView) findViewById(R.id.q621b) ;



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


        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q621b_other)
                {
                    // is checked
                    edtbOther.setVisibility(View.VISIBLE);
                }
                else
                {
                    // not checked
                    edtbOther.setVisibility(View.INVISIBLE);
                    edtbOther.setText("");
                }
            }
        });



        chkaOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chkaOther.isChecked()) {
                    edtaOther.setVisibility(View.VISIBLE);
                }
                else
                {
                    edtaOther.setVisibility(View.INVISIBLE);
                    edtaOther.setText("");
                }

            }
        });

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q621_2)
                {
                    chka1.setEnabled(false);
                    chka2.setEnabled(false);
                    chka3.setEnabled(false);
                    chka4.setEnabled(false);
                    chka5.setEnabled(false);
                    chka6.setEnabled(false);
                    chka7.setEnabled(false);
                    chkaOther.setEnabled(false);

                    chka1.setChecked(false);
                    chka2.setChecked(false);
                    chka3.setChecked(false);
                    chka4.setChecked(false);
                    chka5.setChecked(false);
                    chka6.setChecked(false);
                    chka7.setChecked(false);
                    chkaOther.setChecked(false);
                    txta.setTextColor(Color.LTGRAY);

                    rbtb1.setEnabled(true);
                    rbtb2.setEnabled(true);
                    txtb.setTextColor(Color.BLACK);
                    rbtbOther.setEnabled(true);
                }
                else
                {
                    // not checked
                    chka1.setEnabled(true);
                    chka2.setEnabled(true);
                    chka3.setEnabled(true);
                    chka4.setEnabled(true);
                    chka5.setEnabled(true);
                    chka6.setEnabled(true);
                    chka7.setEnabled(true);
                    chkaOther.setEnabled(true);

                    rbtb1.setEnabled(false);
                    rbtb2.setEnabled(false);
                    rbtbOther.setEnabled(false);
                    txta.setTextColor(Color.BLACK);
                    txtb.setTextColor(Color.LTGRAY);

                    rbtb1.setChecked(false);
                    rbtb2.setChecked(false);
                    rbtbOther.setChecked(false);
                    edtbOther.setText("");

                }
            }
        });

        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg1.getChildCount();f++)
        {
            View o = rg1.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ621()!= null &&  !ind.getQ621().equals(""))
                {
                    if(Integer.parseInt(ind.getQ621())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }



        if(  ind.getQ621bOther() != null )
        {
            if ( ind.getQ621b() != null &&  ind.getQ621b().equals("O") )
            {
                rbtbOther.setChecked(true);
                edtbOther.setText(ind.getQ621bOther());
                edtbOther.setVisibility(View.VISIBLE);
            }
        }
        else
        {
        RadioButton[] btb = new RadioButton[3];
        for(int f=0;f<rg2.getChildCount();f++)
        {
            View o = rg2.getChildAt(f);
            if (o instanceof RadioButton) {
                btb[f] = ((RadioButton) o);
                if (ind.getQ621b() != null && !ind.getQ621b().equals("")) {
                    if (Integer.parseInt(ind.getQ621b()) == f + 1) {
                        RadioButton radioButton = btb[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
            }
        }



        if(ind.getQ621a_1()!= null &&  !ind.getQ621a_1().equals(""))
        {
            if(Integer.parseInt(ind.getQ621a_1())== 1)
            {
                chka1.setChecked(true);

            }else
            {
                chka1.setChecked(false);
            }
        }



        if(ind.getQ621a_2()!= null &&  !ind.getQ621a_2().equals(""))
        {
            if(Integer.parseInt(ind.getQ621a_2())== 1)
            {
                chka2.setChecked(true);

            }else
            {
                chka2.setChecked(false);
            }
        }
        if(ind.getQ621a_3()!= null &&  !ind.getQ621a_3().equals(""))
        {
            if(Integer.parseInt(ind.getQ621a_3())== 1)
            {
                chka3.setChecked(true);

            }else
            {
                chka3.setChecked(false);
            }
        }

        if(ind.getQ621a_4()!= null &&  !ind.getQ621a_4().equals(""))
        {
            if(Integer.parseInt(ind.getQ621a_4())== 1)
            {
                chka4.setChecked(true);

            }else
            {
                chka4.setChecked(false);
            }
        }

        if(ind.getQ621a_5()!= null &&  !ind.getQ621a_5().equals(""))
        {
            if(Integer.parseInt(ind.getQ621a_5())== 1)
            {
                chka5.setChecked(true);

            }else
            {
                chka5.setChecked(false);
            }
        }


        if(ind.getQ621a_6()!= null &&  !ind.getQ621a_6().equals(""))
        {
            if(Integer.parseInt(ind.getQ621a_6())== 1)
            {
                chka6.setChecked(true);

            }else
            {
                chka6.setChecked(false);
            }
        }

        if(ind.getQ621a_7()!= null &&  !ind.getQ621a_7().equals(""))
        {
            if(Integer.parseInt(ind.getQ621a_7())== 1)
            {
                chka7.setChecked(true);

            }else
            {
                chka7.setChecked(false);
            }
        }




        if(ind.getQ621a_Other() != null &&  !ind.getQ621a_Other().equals(""))
        {
            if(Integer.parseInt(ind.getQ621a_Other().substring(0,1))== 1)
            {
                chkaOther.setChecked(true);
                edtaOther.setVisibility(View.VISIBLE);
                edtaOther.setText(ind.getQ621a_Other().substring(1,ind.getQ621a_Other().length()));
                //individual.setQ616_Other("1"+edt616Other.getText().toString());

            }else
            {
                chkaOther.setChecked(false);
            }
        }

//        if(ind.getQ621a_Other1() != null )
//        {
//            edtaOther.setText(ind.getQ621a_Other1());
//        }


        if(ind.getQ621bOther() != null)
        {
            edtbOther.setText(ind.getQ621bOther());
        }


        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = rg1.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);
                if (selectedRbtn == null) {
                    lib.showError(q621.this, "Q621: ERROR", "If you were diagnosed with TB,would you tell anyone?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    if (((rbtn1.isChecked() && !chka1.isChecked() && !chka2.isChecked() && !chka3.isChecked() && !chka4.isChecked() && !chka5.isChecked() && !chka6.isChecked() && !chka7.isChecked() && !chkaOther.isChecked()
                    ))) {
                        lib.showError(q621.this, "Q621a: ERROR", "If yes, whom would you tell? Please select atleast one option");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {

                        if (chkaOther.isChecked() && edtaOther.length() == 0) {
                            lib.showError(q621.this, "Q621a: ERROR : Other", "Please specify");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {
                            int selectedId1 = rg2.getCheckedRadioButtonId();
                            selectedRbtn2 = (RadioButton) findViewById(selectedId1);
                            if (selectedRbtn2 == null && rbtn2.isChecked()) {
                                lib.showError(q621.this, "Q621b: ERROR", "If no,why not?");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {

                                if (rbtbOther.isChecked() && edtbOther.length() == 0) {
                                    lib.showError(q621.this, "Q620: ERROR: Other", "Please specify other or deselect other specify");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);
                                } else {

                                    if (rbtn2.isChecked()) {
                                        individual.setQ621(selectedRbtn.getText().toString().substring(0, 1));
                                        individual.setQ621b(selectedRbtn2.getText().toString().substring(0, 1));
                                        individual.setQ621bOther(edtbOther.getText().toString());

                                        individual.setQ621a_1(null);
                                        individual.setQ621a_2(null);
                                        individual.setQ621a_3(null);
                                        individual.setQ621a_4(null);
                                        individual.setQ621a_5(null);
                                        individual.setQ621a_6(null);
                                        individual.setQ621a_7(null);
                                        individual.setQ621a_Other(null);


                                        myDB.onOpen(myDB.getReadableDatabase());
                                        myDB.getWritableDatabase();
                                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                        myDB.close();

                                        Intent intent = new Intent(q621.this, q622.class);
                                        intent.putExtra("Individual", individual);
                                        startActivity(intent);
                                    } else {

                                        individual.setQ621(selectedRbtn.getText().toString().substring(0, 1));
                                        individual.setQ621b(null);

                                        if (chka1.isChecked()) {
                                            individual.setQ621a_1("1");

                                        } else {
                                            individual.setQ621a_1("2");
                                        }

                                        if (chka2.isChecked()) {
                                            individual.setQ621a_2("1");

                                        } else {
                                            individual.setQ621a_2("2");
                                        }

                                        if (chka3.isChecked()) {
                                            individual.setQ621a_3("1");

                                        } else {
                                            individual.setQ621a_3("2");
                                        }

                                        if (chka4.isChecked()) {
                                            individual.setQ621a_4("1");

                                        } else {
                                            individual.setQ621a_4("2");
                                        }

                                        if (chka5.isChecked()) {
                                            individual.setQ621a_5("1");

                                        } else {
                                            individual.setQ621a_5("2");
                                        }

                                        if (chka6.isChecked()) {
                                            individual.setQ621a_6("1");

                                        } else {
                                            individual.setQ621a_6("2");
                                        }
                                        if (chka7.isChecked()) {
                                            individual.setQ621a_7("1");

                                        } else {
                                            individual.setQ621a_7("2");
                                        }
                                        if (chkaOther.isChecked()) {
                                            individual.setQ621a_Other("1");
                                            //individual.setQ621a_Other1(edtaOther.getText().toString());
                                            individual.setQ621a_Other("1"+edtaOther.getText().toString());
                                        } else {
                                            individual.setQ621a_Other("2");
                                        }

                                        myDB.onOpen(myDB.getReadableDatabase());
                                        myDB.getWritableDatabase();
                                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                        myDB.close();

                                        Intent intent = new Intent(q621.this, q622.class);
                                        intent.putExtra("Individual", individual);
                                        startActivity(intent);

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
                if (individual.getQ619_9() != null && individual.getQ619_9().equals("1"))
                {finish();
                    Intent intent = new Intent(q621.this, q619.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                }
                else
                {
                    finish();
                    Intent intent = new Intent(q621.this, q620.class);
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q621.this).toBundle());

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

