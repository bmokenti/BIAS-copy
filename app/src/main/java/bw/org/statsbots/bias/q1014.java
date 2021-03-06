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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q1014 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtna1, rbtna2, rbtna3, rbtna4, rbtna5, rbtnb1, rbtnb2 ;
    protected RadioGroup rg, rga, rgb;protected  DatabaseHelper myDB;
    protected TextView ta, tb;
    protected Individual individual;
    protected EditText edtOther;
    protected RadioButton selectedRbtn, selectedRbtna, selectedRbtnb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1014);



        setTitle("Q1014: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1014radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q1014_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1014_2);
        rbtn3 = (RadioButton) findViewById(R.id.q1014_9);

        rga = (RadioGroup)findViewById(R.id.q1014aGroup1) ;
        rbtna1 = (RadioButton) findViewById(R.id.q1014a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q1014a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q1014a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q1014a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q1014a_9);

        rgb = (RadioGroup)findViewById(R.id.q1014bGroup2) ;
        rbtnb1 = (RadioButton) findViewById(R.id.q1014b_1);
        rbtnb2 = (RadioButton) findViewById(R.id.q1014b_2);

        ta = (TextView) findViewById(R.id.q1014a) ;
        tb = (TextView) findViewById(R.id.q1014b) ;


        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);

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


        int years = 0;
        int months = 0;
        int days = 0;


        if ( individual.getQ1014() != null && (individual.getQ1014().equals("2") ||  individual.getQ1014().equals("9")))
        {

            rbtna1.setEnabled(false);
            rbtna2.setEnabled(false);
            rbtna3.setEnabled(false);
            rbtna4.setEnabled(false);
            rbtna5.setEnabled(false);


            rbtna1.setChecked(false);
            rbtna2.setChecked(false);
            rbtna3.setChecked(false);
            rbtna4.setChecked(false);
            rbtna5.setChecked(false);
            ta.setTextColor(Color.LTGRAY);

            rbtnb1.setEnabled(false);
            rbtnb2.setEnabled(false);

            rbtnb1.setChecked(false);
            rbtnb2.setChecked(false);

            tb.setTextColor(Color.LTGRAY);

        }


        if ( individual.getQ1014a() != null && !(individual.getQ1014a().equals("1")))
        {


            rbtnb1.setEnabled(false);
            rbtnb2.setEnabled(false);

            rbtnb1.setChecked(false);
            rbtnb2.setChecked(false);

            tb.setTextColor(Color.LTGRAY);

        }


if ((individual.getQ1004_Month() != null && Integer.valueOf(individual.getQ1004_Month()) <=1 ) &&
        (individual.getQ1004_Day() != null && Integer.valueOf(individual.getQ1004_Day()) >= 13 ))
{


    individual.setQ1014(null);
    individual.setQ1014a(null);
    individual.setQ1014b(null);
    individual.setQ1015(null);
    individual.setQ1015a(null);
    individual.setQ1015b(null);
//        individual.setQ1016(null);
//        individual.setQ1017(null);

    myDB.onOpen(myDB.getReadableDatabase());
    myDB.getWritableDatabase();
    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
    myDB.close();

    Intent intent = new Intent(q1014.this, q1016.class);
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
                if(ind.getQ1014()!= null )
                {  if (f == 2) {
                    rbtn3.setChecked(true);
                }
                    if(Integer.parseInt(ind.getQ1014())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }


        RadioButton[] bta = new RadioButton[5];
        for(int f=0;f<rga.getChildCount();f++)
        {
            View o = rga.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bta[f]=((RadioButton)o);
                if(ind.getQ1014a()!= null )
                {
                    if (f == 4) {
                        rbtna5.setChecked(true);
                    }
                    if(Integer.parseInt(ind.getQ1014a())==f+1)
                    {
                        RadioButton radioButton = bta[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] btb = new RadioButton[2];
        for(int f=0;f<rgb.getChildCount();f++)
        {
            View o = rgb.getChildAt(f);
            if (o instanceof RadioButton)
            {
                btb[f]=((RadioButton)o);
                if(ind.getQ1014b()!= null)
                {
                    if(Integer.parseInt(ind.getQ1014b())==f+1)
                    {
                        RadioButton radioButton = btb[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q1014.this, "Q1014: ERROR", " Was this child tested for HIV by the time he/ she was 6 – 8 weeks old?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    int selectedIda = rga.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedIda);

                    if (selectedRbtna == null && rbtn1.isChecked()) {
                        lib.showError(q1014.this, "Q1014a: ERROR", " What were the results of the child?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {

                        int selectedIdb = rgb.getCheckedRadioButtonId();
                        selectedRbtnb = (RadioButton) findViewById(selectedIdb);

                        if (selectedRbtnb == null && rbtn1.isChecked() && rbtna1.isChecked()) {
                            lib.showError(q1014.this, "Q1014b: ERROR", "Was the child given ARVs?");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {


                            if (rbtn2.isChecked() || rbtn3.isChecked()) {
                                individual.setQ1014(selectedRbtn.getText().toString().substring(0, 1));
                                individual.setQ1014a(null);
                                individual.setQ1014b(null);
                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                myDB.close();

                                Intent intent = new Intent(q1014.this, q1015.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);

                            } else {


                                if (rbtn1.isChecked() && !rbtna1.isChecked()) {
                                    individual.setQ1014(selectedRbtn.getText().toString().substring(0, 1));
                                    individual.setQ1014a(selectedRbtna.getText().toString().substring(0, 1));
                                   // individual.setQ1014a(null);
                                    individual.setQ1014b(selectedRbtnb.getText().toString().substring(0, 1));
                                    myDB.onOpen(myDB.getReadableDatabase());
                                    myDB.getWritableDatabase();
                                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                    myDB.close();

                                    Intent intent = new Intent(q1014.this, q1015.class);
                                    intent.putExtra("Individual", individual);
                                    startActivity(intent);

                                } else {
                                    individual.setQ1014(selectedRbtn.getText().toString().substring(0, 1));
                                    individual.setQ1014a(null);
                                    individual.setQ1014b(selectedRbtnb.getText().toString().substring(0, 1));

                                    myDB.onOpen(myDB.getReadableDatabase());
                                    myDB.getWritableDatabase();
                                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                    myDB.close();

                                    Intent intent = new Intent(q1014.this, q1015.class);
                                    intent.putExtra("Individual", individual);
                                    startActivity(intent);


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
                if(  individual.getQ1011() != null && (individual.getQ1011().equals("1")||individual.getQ1011().equals("4") || individual.getQ1011().equals("7")))
                {
                    finish();
                    Intent intent = new Intent(q1014.this, q1011.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                }
                else
                {
                    finish();
                    Intent intent = new Intent(q1014.this, q1011.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                }

            }


        });
    }




    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q1014_1:
                if(checked)

                rbtna1.setEnabled(true);
                rbtna2.setEnabled(true);
                rbtna3.setEnabled(true);
                rbtna4.setEnabled(true);
                rbtna5.setEnabled(true);
                ta.setTextColor(Color.BLACK);

                rbtnb1.setEnabled(true);
                rbtnb2.setEnabled(true);

                tb.setTextColor(Color.BLACK);





                break;

            case R.id.q1014_2:
                if(checked)

                    rbtna1.setEnabled(false);
                rbtna2.setEnabled(false);
                rbtna3.setEnabled(false);
                rbtna4.setEnabled(false);
                rbtna5.setEnabled(false);


                rbtna1.setChecked(false);
                rbtna2.setChecked(false);
                rbtna3.setChecked(false);
                rbtna4.setChecked(false);
                rbtna5.setChecked(false);
                ta.setTextColor(Color.LTGRAY);

                rbtnb1.setEnabled(false);
                rbtnb2.setEnabled(false);

                rbtnb1.setChecked(false);
                rbtnb2.setChecked(false);

                tb.setTextColor(Color.LTGRAY);


                break;

            case R.id.q1014_9:
                if(checked)

                    rbtna1.setEnabled(false);
                rbtna2.setEnabled(false);
                rbtna3.setEnabled(false);
                rbtna4.setEnabled(false);
                rbtna5.setEnabled(false);


                rbtna1.setChecked(false);
                rbtna2.setChecked(false);
                rbtna3.setChecked(false);
                rbtna4.setChecked(false);
                rbtna5.setChecked(false);
                ta.setTextColor(Color.LTGRAY);

                rbtnb1.setEnabled(false);
                rbtnb2.setEnabled(false);

                rbtnb1.setChecked(false);
                rbtnb2.setChecked(false);

                tb.setTextColor(Color.LTGRAY);


                    break;
            case R.id.q1014a_1:
                if(checked)

                    rbtnb1.setEnabled(true);
                rbtnb2.setEnabled(true);
                tb.setTextColor(Color.BLACK);




                break;

            case R.id.q1014a_2:
                if(checked)

                    rbtnb1.setEnabled(false);
                rbtnb2.setEnabled(false);

                rbtnb1.setChecked(false);
                rbtnb2.setChecked(false);

                tb.setTextColor(Color.LTGRAY);

                break;

            case R.id.q1014a_3:
                if(checked)

                    rbtnb1.setEnabled(false);
                rbtnb2.setEnabled(false);

                rbtnb1.setChecked(false);
                rbtnb2.setChecked(false);

                tb.setTextColor(Color.LTGRAY);
                    break;

            case R.id.q1014a_4:
                if(checked)
                    rbtnb1.setEnabled(false);
                rbtnb2.setEnabled(false);

                rbtnb1.setChecked(false);
                rbtnb2.setChecked(false);

                tb.setTextColor(Color.LTGRAY);

                    break;

            case R.id.q1014a_9:
                if(checked)
                    rbtnb1.setEnabled(false);
                rbtnb2.setEnabled(false);

                rbtnb1.setChecked(false);
                rbtnb2.setChecked(false);

                tb.setTextColor(Color.LTGRAY);

                    break;
            case R.id.q1014b_1:
                if(checked)


                    break;
            case R.id.q1014b_2:
                if(checked)


                    break;

            default:

                break;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intervie_control, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q1014.this).toBundle());

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


                return true;

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


