package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class q1113 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, selected = null;
    protected  RadioButton selectedRbtn;
    protected EditText text1113other;
    protected PersonRoster p1 = null;protected DatabaseHelper myDB;
    protected Individual individual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1113);

        setTitle("Q1113 ");
        Button btnnext = findViewById(R.id.btnNext);


        rbtn1 = (RadioButton) findViewById(R.id.q1113_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1113_2);
        rbtn3 = (RadioButton) findViewById(R.id.q1113_3);
        rbtn4 = (RadioButton) findViewById(R.id.q1113_4);
        rbtn5 = (RadioButton) findViewById(R.id.q1113_ot);
        text1113other= findViewById(R.id.txt1113other);


        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupq1113);

        //rbtn1.setOnClickListener(this);
        //rbtn2.setOnClickListener(this);

        // final int selectedId = rbtngroup.getCheckedRadioButtonId();

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
        //individual.setQ1113Other("other");

        if (ind.getQ1113() != null ) {

            if (ind.getQ1113() != null && ind.getQ1113().equals("O")) {
                rbtn5.setChecked(true);
                text1113other.setText(ind.getQ1113Other());
                text1113other.setVisibility(View.VISIBLE);
            }
        }
        else {
        RadioButton[] bt = new RadioButton[5];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton) {
                bt[f] = ((RadioButton) o);
                if (ind.getQ1113() != null) {
                    if (!ind.getQ1113().equals("")) {
                        if (Integer.parseInt(ind.getQ1113()) == f + 1) {
                            RadioButton radioButton = bt[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
            }
        }

//        if(ind.getQ1113Other()!= null)
//        {
//            text1113other.setText(ind.getQ1113Other());
//        }




        Button btnext = findViewById(R.id.btnnext);



        btnnext.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q1113.this);
                    builder.setTitle("Q1113");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("if you did not seek help, what were the reasons?");
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

                  individual.setQ1113(selectedRbtn.getText().toString().substring(0,1));
                    if(rbtn5.isChecked()) {
                        individual.setQ1113Other(text1113other.getText().toString());
                    }
                    else {
                        individual.setQ1113Other(null);
                    }
                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.getWritableDatabase();
                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                    myDB.updateInd("Q1113Other", individual.getAssignmentID(), individual.getBatch(), individual.getQ1113Other(), String.valueOf(individual.getSRNO()));

                    myDB.close();

                    Intent intent = new Intent(q1113.this, q1114.class);
                    intent.putExtra("Individual",  individual);
                    startActivity(intent);

                }

            }
        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (individual.getQ1110() != null && individual.getQ1110().equals("2")) {

                    finish();

                    Intent intent = new Intent(q1113.this, q1110.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);

                }
                // q1113.super.onBackPressed();
            }


        });
    }


    public void onRadioButtonClicked(View v) {


        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q1113_ot:
                if (checked) {
                    text1113other.setVisibility(View.VISIBLE);
                   // individual.setQ1113Other(text1113other.getText().toString());
                }


                break;

            case R.id.q1113_1:
                if (checked) {
                    text1113other.setText("");
                    text1113other.setVisibility(View.INVISIBLE);

                }

            case R.id.q1113_2:
                if (checked) {
                    text1113other.setText("");
                    text1113other.setVisibility(View.INVISIBLE);

                }

            case R.id.q1113_3:
                if (checked) {
                    text1113other.setText("");
                    text1113other.setVisibility(View.INVISIBLE);

                }

            case R.id.q1113_4:
                if (checked) {
                    text1113other.setText("");
                    text1113other.setVisibility(View.INVISIBLE);

                }

        }
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.q1113_1:

                break;

            case R.id.q1113_2:
                break;


            case R.id.q1113_3:

                break;

            case R.id.q1113_4:
                break;


            case R.id.txt1113other:


            text1113other.setVisibility(View.VISIBLE);
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q1113.this).toBundle());

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

