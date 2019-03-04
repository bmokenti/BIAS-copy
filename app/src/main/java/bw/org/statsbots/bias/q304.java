package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q304 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2;
    protected RadioGroup rg, rga;
    protected TextView txt1;
    PersonRoster p1 = null;protected DatabaseHelper myDB;
    Individual pp1 = null;
    protected RadioButton selectedRbtn1, selectedRbtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q304);



        setTitle("Q304: ALCOHOL CONSUMPTION AND SUBSTANCE USE");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q304_1);
        rbtn2 = (RadioButton) findViewById(R.id.q304_2);

        rbtna1 = (RadioButton) findViewById(R.id.q304a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q304a_2);


        txt1 = (TextView) findViewById(R.id.q304atxt);
        // txt2 = (TextView) findViewById(R.id.q205atxt);


        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();


        rg = (RadioGroup) findViewById(R.id.q304radioGroup);
        rga = (RadioGroup) findViewById(R.id.q304aradioGroup);



        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();


        RadioButton[] bt1 = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt1[f]=((RadioButton)o);
                if(ind.getQ304()!= null &&  !ind.getQ304().equals(""))
                {
                    if(Integer.parseInt(ind.getQ304())==f+1)
                    {
                        RadioButton radioButton = bt1[f];
                        radioButton.setChecked(true);
                        onRadioButtonClicked(radioButton);
                        break;
                    }
                }
            }
        }


        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rga.getChildCount();f++)
        {
            View o = rga.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ304a()!= null &&  !ind.getQ304a().equals(""))
                {
                    if(Integer.parseInt(ind.getQ304a())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }



        Button btnext = findViewById(R.id.button);
//        PersonRoster pr[] = thisHouse.getPersons();



        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn1 = (RadioButton) findViewById(selectedId);

                if (selectedRbtn1 == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q304.this);
                    builder.setTitle("Q304: Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Have you ever used any of the following drugs for Recreation: " +
                            "(By drugs we mean cocaine, glue, mandrax, ecstasy, marijuana (motokwane), etc.?)");
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

                    int selectedId1 = rga.getCheckedRadioButtonId();
                    selectedRbtn2 = (RadioButton) findViewById(selectedId1);

                    if (selectedRbtn2 == null && rbtn1.isChecked()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q304.this);
                        builder.setTitle("Q304a: Error");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("Have you used any of these drugs in the past 3 months?");
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
                        //Set q301 for the current individual
                        if (rbtn2.isChecked()) {
                            individual.setQ304(selectedRbtn1.getText().toString().substring(0, 1));
                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();

                            Intent q1o2 = new Intent(q304.this, q305.class);
                            q1o2.putExtra("Individual", individual);
                            startActivity(q1o2);
                        } else {
                            individual.setQ304(selectedRbtn1.getText().toString().substring(0, 1));

                            individual.setQ304a(selectedRbtn2.getText().toString().substring(0, 1));

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();

                            Intent q1o2 = new Intent(q304.this, q305.class);
                            q1o2.putExtra("Individual", individual);
                            startActivity(q1o2);

                        }

                    }
                }
            }

        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q304.super.onBackPressed();
            }


        });

    }

    public void onRadioButtonClicked(View v) {


        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q304_1:
                if (checked) {
                    txt1.setTextColor(Color.BLACK);
                    rbtna1.setEnabled(true);
                    rbtna2.setEnabled(true);
                }


                break;

            case R.id.q304_2:
                if (checked) {

                    txt1.setTextColor(Color.LTGRAY);
                    rbtna1.setEnabled(false);
                    rbtna2.setEnabled(false);

                    rbtna1.setChecked(false);
                    rbtna2.setChecked(false);
                }
                break;

            case R.id.q304a_1:
                if (checked) {


                }
                break;
            case R.id.q304a_2:
                if (checked) {


                }

                break;








        }
    }




}


