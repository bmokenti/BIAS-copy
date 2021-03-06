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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q306 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected DatabaseHelper myDB;
    protected LibraryClass lib;

    protected CheckBox chkb9;
    protected EditText q306_dd;
    PersonRoster p1 = null;
    Individual pp1 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q306);

        setTitle("Q306: ALCOHOL CONSUMPTION AND SUBSTANCE USE");
        lib = new LibraryClass();
        q306_dd = (EditText) findViewById(R.id.q306_Days);
        chkb9 = (CheckBox) findViewById(R.id.q306_9);

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
      //  int p = 0;
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p : roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }

        if(ind.getQ306()!= null)
        {
            if(ind.getQ306().equals("9") )
            {
                chkb9.setChecked(true);

            }else
            {
                chkb9.setChecked(false);

            }
            q306_dd.setText(ind.getQ306());
        }









        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ((q306_dd.getText() == null || q306_dd.length() == 0) && !chkb9.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q306.this);
                    builder.setTitle("Q306: Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("During the past 7 days, on how many days did someone smoke around you?"
                            + "Please enter number of days or select Dont know");
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


                    if (Integer.valueOf(q306_dd.getText().toString()) >= 8  && !chkb9.isChecked()) {
                        lib.showError(q306.this, "Q306", "Number of days should not be greater than 7: enter number of days  0-7 or select don't know");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {

                         individual.setQ306(q306_dd.getText().toString());

                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        myDB.close();

                        Intent q1o3 = new Intent(q306.this, q307.class);
                        q1o3.putExtra("Individual", individual);
                        startActivity(q1o3);

                    }
                }


            }

        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent q1o3 = new Intent(q306.this, q305.class);
                q1o3.putExtra("Individual", individual);
                q1o3.putExtra("Personroster", p1);
                startActivity(q1o3);
            }


        });

    }



                /*
            case R.id.q1103a_99:
                if (checked){
                    q1103aQues.setTextColor(Color.BLACK);
                    //Q1103linear.setActivated(false);
            q1103dd.setEnabled(false);
                    q1103dd.setText("");
            q1103dd.setBackgroundColor(Color.LTGRAY);


         q1103wks.setEnabled(false);
                    q1103wks.setText("");
                q1103wks.setBackgroundColor(Color.LTGRAY);
                    }
                break;

                }
        }*/

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked

        if (checked) {

            q306_dd.setEnabled(false);

            q306_dd.setText("9");

        }
        // Put some meat on the sandwich
        else {
            // Remove the meat
            q306_dd.setEnabled(true);
            q306_dd.setText("");



        }
    }

    //   thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q306.this).toBundle());

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
