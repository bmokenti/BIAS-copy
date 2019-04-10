package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.annotation.ColorLong;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q1102 extends AppCompatActivity implements  Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn3, selectedRbtn;
    protected RadioGroup rg;protected DatabaseHelper myDB;
    protected EditText q1102ay;
    protected TextView q1102aylbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1102);

        setTitle("Q1102: TB Treatment");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q1102_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1102_2);
        rbtn3 = (RadioButton) findViewById(R.id.q1102_9);
        q1102ay = findViewById(R.id.q1102_years);
        q1102aylbl = findViewById(R.id.q1101atext);

        rg = (RadioGroup) findViewById(R.id.q1102radioGroup);

        /*rbtn1.setOnClickListener(this);
        rbtn2.setOnClickListener(this);*/


        final int selectedId = rg.getCheckedRadioButtonId();

        //setTitle("Q1102a When were you treated for tuberculosis?");

        lib = new LibraryClass();


        // final int selectedId = rbtngroup.getCheckedRadioButtonId();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        //int p = 0;
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
//
//
//        if (sample.getStatusCode().equals("1") || (sample.getStatusCode().equals("2") &&thisHous.get(0).getHIVTB40().equals("1") &&
//                (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 )))

        if( sample.getStatusCode().equals("1") || (sample.getStatusCode().equals("2") &&thisHous.get(0).getHIVTB40().equals("1") &&
            (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 )))
        {
            Intent q1o3 = new Intent(q1102.this, q1103.class);
            q1o3.putExtra("Individual", individual);
            startActivity(q1o3);

        }

        RadioButton[] bt = new RadioButton[3];
        for(int f=0;f<rg.getChildCount();f++) {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton) {
                bt[f] = ((RadioButton) o);
                if (ind.getQ1102() != null ) {
                    if (Integer.parseInt(ind.getQ1102()) == f + 1) {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        // TextView q802atext = findViewById(R.id.q802a_other);
                        // RadioGroup rg1 = (RadioGroup) findViewById(R.id.q802radioGroupa);
                        // Is the current Radio Button checked?
                        boolean checked = radioButton.isChecked();
                        View v = radioButton;
                        switch (v.getId()) {

                            case R.id.q1102_1:
                                if (checked)
                                    q1102ay.setEnabled(true);
                                q1102ay.setBackgroundResource(android.R.drawable.edit_text);
                                q1102aylbl.setTextColor(Color.BLACK);


                                break;


                            case R.id.q1102_2:
                                if (checked)
                                    q1102ay.setEnabled(false);
                                q1102ay.setBackgroundColor(Color.LTGRAY);
                                q1102ay.setText("");
                                q1102aylbl.setTextColor(Color.LTGRAY);

                                break;
                            case R.id.q1102_9:
                                if (checked)
                                    q1102ay.setEnabled(false);
                                q1102ay.setBackgroundColor(Color.LTGRAY);
                                q1102ay.setText("");
                                q1102aylbl.setTextColor(Color.LTGRAY);
                                break;

                        }
                    }
                }
            }
        }

        if(ind.getQ1102a()!= null)
        {
            q1102ay.setText(ind.getQ1102a());
        }




        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q1102.this);
                    builder.setTitle("Q1102: Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Have you been on TB treatment before?");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                    AlertDialog alertDialog = builder.show();
                    final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                    positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    positiveButton.setTextColor(Color.WHITE);
                    positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                    positiveButton.setLayoutParams(positiveButtonLL);


                } else {


                    if ((q1102ay.getText().equals(null) || ((q1102ay.length() == 0)) && (rbtn1.isChecked()))) {
                        lib.showError(q1102.this, "Q1102", "a) When were you treated for TB?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {


                        if (rbtn2.isChecked() && rbtn3.isChecked()) {

                            individual.setQ1102(selectedRbtn.getText().toString().substring(0, 1));

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.updateInd("Q1102a",individual.getAssignmentID(),individual.getBatch(),null,String.valueOf(individual.getSRNO()));
                            myDB.close();

                            Intent q1o3 = new Intent(q1102.this, q1103.class);
                            q1o3.putExtra("Individual", individual);
                            startActivity(q1o3);

                        } else {

                            //startActivity(intent);
                            individual.setQ1102(selectedRbtn.getText().toString().substring(0, 1));
                            individual.setQ1102a(q1102ay.getText().toString());

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();

                            Intent q1o3 = new Intent(q1102.this, q1103.class);
                            q1o3.putExtra("Individual", individual);
                            startActivity(q1o3);

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
                Intent q1o3 = new Intent(q1102.this, q1101.class);
                q1o3.putExtra("Individual", individual);
                startActivity(q1o3);

            }


        });
    }



    public void onRadioButtonClicked(View v) {

        RadioGroup rg1 = (RadioGroup) findViewById(R.id.q1101radioGroupa);
        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q1102_1:
                if (checked)
                    q1102ay.setEnabled(true);
                q1102ay.setBackgroundResource(android.R.drawable.edit_text);
                q1102aylbl.setTextColor(Color.BLACK);


                break;


            case R.id.q1102_2:
                if (checked)
                    q1102ay.setEnabled(false);
                q1102ay.setBackgroundColor(Color.LTGRAY);
                q1102ay.setText("");
                q1102aylbl.setTextColor(Color.LTGRAY);

                break;
            case R.id.q1102_9:
                if (checked)
                    q1102ay.setEnabled(false);
                q1102ay.setBackgroundColor(Color.LTGRAY);
                q1102ay.setText("");
                q1102aylbl.setTextColor(Color.LTGRAY);
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q1102.this).toBundle());

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
