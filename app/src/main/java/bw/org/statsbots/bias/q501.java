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

import java.io.Serializable;
import java.util.List;

public class q501 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn3, selected = null;
    protected RadioGroup rg;protected DatabaseHelper myDB;
    protected RadioButton selectedRbtn;
    HouseHold thisHose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q501);


        setTitle("Q501: MALE CIRCUMICISION");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q501_1);
        rbtn2 = (RadioButton) findViewById(R.id.q501_2);
        rbtn3 = (RadioButton) findViewById(R.id.q501_9);
        rg = (RadioGroup) findViewById(R.id.q501radioGroup);

        rbtn1.setOnClickListener(this);
        rbtn2.setOnClickListener(this);
        rbtn3.setOnClickListener(this);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        //int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;
        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }

        if(individual.getQ101().equals("2"))
        {
            Intent intent = new Intent(q501.this, q601.class);
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
                if(ind.getQ501()!= null &&  !ind.getQ501().equals(""))
                {
                    if(Integer.parseInt(ind.getQ501())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q501.this);
                    builder.setTitle("Q501 Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Are you circumcised");
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


                }
                else

                {


                    if (rbtn2.isChecked() || rbtn3.isChecked()) {

                        individual.setQ501(selectedRbtn.getText().toString().substring(0,1));

                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        myDB.close();
                        Intent q1o2 = new Intent(q501.this, q503.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);
                    }
                    else
                    {
                        //Set q501 for the current individual
                       individual.setQ501(selectedRbtn.getText().toString().substring(0,1));

                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        myDB.close();

                        Intent intent = new Intent(q501.this, q502.class);
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
                q501.super.onBackPressed();
            }


        });

    }



    @Override
    public void onClick(View view) {

        //
        switch (view.getId()) {
            case R.id.q501_1:
                //Intent intent1 = new Intent(this, q502.class);
               // startActivity(intent1);
                break;



            case R.id.q501_2:
                //Intent intent2 = new Intent(this, q503.class);
                //startActivity(intent2);
                break;
            case R.id.q501_9:
                //Intent intent3 = new Intent(this, q503.class);
                //startActivity(intent3);
                break;
            default:
                break;
        }
    }
}