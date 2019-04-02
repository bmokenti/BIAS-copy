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
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class q902 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;protected DatabaseHelper myDB;
    protected Button btn;
    protected  Individual individual;
    protected EditText edtmnths, edtyear;
    protected RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q902);


        setTitle("Q902:  HIV SUPPORT, CARE ANF TREATMENT");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        edtmnths = (EditText) findViewById(R.id.q902_months);
        edtyear = (EditText) findViewById(R.id.q902_year);
        ck1txt = findViewById(R.id.q902_99);
        ck2txt = findViewById(R.id.q902_9999);

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

        if( ind.getQ902Month() != null)
        {
            edtmnths.setText(ind.getQ902Month());
        }

        if( ind.getQ902Year() != null)
        {
            edtmnths.setText(ind.getQ902Year());
        }

        if( ind.getQ902Month() == "99")
        {
            ck1txt.setChecked(true);
        }

        if( ind.getQ902Year()  == "9999")
        {
            ck2txt.setChecked(true);
        }




        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!ck1txt.isChecked() && ((edtmnths.length() == 0 || Integer.valueOf(edtmnths.getText().toString()) >= 13))) {

                    lib.showError(q902.this, "Q902: month", "Please input month or select Dont know months: months should be 1-12");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {


                    if (!ck2txt.isChecked() && ((edtyear.length() < 4 || Integer.valueOf(edtyear.getText().toString()) <= 1988 ||Integer.valueOf(edtyear.getText().toString()) >= 2019))) {
                        lib.showError(q902.this, "Q902: year", "Please input year or select Dont know Year: Year shoud be between greater than 2019");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                            else {
                                String m = edtmnths.getText().toString();
                                String y = edtyear.getText().toString();
                                if(m.length()==1){
                                    m="0"+m;
                                }
                                if(y.length()==1){
                                    y="000"+y;
                                }
                        if(y.length()==2){
                            y="00"+y;
                        }
                                   individual.setQ902Month(m);
                                   individual.setQ902Year(y);

                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                myDB.close();

                                Intent intent = new Intent(q902.this, q903.class);
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
                Intent intent = new Intent(q902.this, q901.class);
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
            case R.id.q902_99:
                if (checked) {
                    edtmnths.setText("99");
                    edtmnths.setEnabled(false);

                }
                // Put some meat on the sandwich
                else {
                    edtmnths.setText("");
                    edtmnths.setEnabled(true);
                    // Remove the meat
                }
                    break;

            case R.id.q902_9999:
                if (checked) {
                    edtyear.setText("9999");
                    edtyear.setEnabled(false);

                        // Put some meat on the sandwich
                    }
                else
                {
                edtyear.setText("");
                edtyear.setEnabled(true);
            }
                    // Remove the meat
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q902.this).toBundle());

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


