package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q614 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn9, rbtnother, selected;
    protected RadioGroup rbtngroup;
    protected EditText edt;
    protected DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q614);

        setTitle("q614: KNOWLEDGE ABOUT HIV/AIDS AND TB ");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q614_1);
        rbtn2 = (RadioButton) findViewById(R.id.q614_2);
        rbtn3 = (RadioButton) findViewById(R.id.q614_3);
        rbtn4 = (RadioButton) findViewById(R.id.q614_4);
        rbtn9 = (RadioButton) findViewById(R.id.q614_9);
        rbtnother = (RadioButton) findViewById(R.id.q614_other);
        rbtngroup = (RadioGroup) findViewById(R.id.q614radioGroup);
        edt = (EditText) findViewById(R.id.q614_other1) ;

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

        rbtngroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q614_other)
                {
                    // is checked
                    edt.setVisibility(View.VISIBLE);
                }
                else
                {
                    // not checked
                    edt.setVisibility(View.INVISIBLE);
                    edt.setText("");
                }
            }
        });




        if(  ind.getQ614Other() != null )
        { if(  !ind.getQ614Other().equals("")) {
            if (ind.getQ614() != null && ind.getQ614().equals("O")) {
                rbtnother.setChecked(true);
                edt.setText(ind.getQ614Other());
            }
        }
        }
        else
        {
        RadioButton[] bt = new RadioButton[6];
        for(int f=0;f<rbtngroup.getChildCount();f++)
        {
            View o = rbtngroup.getChildAt(f);
            if (o instanceof RadioButton) {
                bt[f] = ((RadioButton) o);

                if (ind.getQ614() != null ) {
                    if (!ind.getQ614().equals("")) {
                        if (f == 4) {
                            rbtn9.setChecked(true);
                        }
                    if (Integer.parseInt(ind.getQ614()) == f + 1) {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
                }
            }
        }
        }

//        if(ind.getQ614Other()!= null)
//        {
//            edt.setText(ind.getQ614Other());
//        }




        /**
         * NEXT question
         */
        Button btnNext = (Button) findViewById(R.id.btnNext);

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
                    lib.showError(q614.this, "Q614 Error", "Please select an option for q614");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    if (rbtnother.isChecked() && edt.length() == 0 || edt.getText() == null) {
                        lib.showError(q614.this, "Q614 Error", "Please specify");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else {
                        individual.setQ614(selected.getText().toString().substring(0, 1));
                        individual.setQ614Other(edt.getText().toString());

                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.getWritableDatabase();
                    myDB.updateInd("Q614", individual.getAssignmentID(), individual.getBatch(), individual.getQ614(), String.valueOf(individual.getSRNO()));
                        if(rbtnother.isChecked()) {
                            myDB.updateInd("Q614Other", individual.getAssignmentID(), individual.getBatch(), individual.getQ614Other(), String.valueOf(individual.getSRNO()));
                        }
                        else
                        {
                            myDB.updateInd("Q614Other", individual.getAssignmentID(), individual.getBatch(), null, String.valueOf(individual.getSRNO()));

                        }
                    myDB.close();
//
//                        myDB.onOpen(myDB.getReadableDatabase());
//                        myDB.getWritableDatabase();
//                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
//                        myDB.close();
//
//                        SQLiteDatabase db1 = myDB.getWritableDatabase();
//                        ContentValues hhValues = new ContentValues();
//                        hhValues.put("Q614", selected.getText().toString().substring(0, 1));
//
//                        int i = db1.update
//                                ("Individual", // table
//                                        hhValues, // column/value
//                                        "Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
//                                        new String[]{individual.getAssignmentID(), individual.getBatch(), String.valueOf(individual.getSRNO())}
//                                );
//                        db1.close();


                    Intent intent = new Intent(q614.this, q615.class);
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
                Intent intent = new Intent(q614.this, q613.class);
                intent.putExtra("Individual", individual);
                startActivity(intent);
            }


        });
    }

    public void onRadioButtonClicked(View v) {


        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q614_other:
                if (checked)
                {

                }
                else
                {

                }


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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q614.this).toBundle());

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


