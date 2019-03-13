package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class q1114 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    private DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2;
    protected RadioButton selectedRbtn;
    protected EditText edittext;
    protected Individual individual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1114);
        setTitle("Q1114 ");
        Button btnnext = (Button)findViewById(R.id.btnNext);

            rbtn1 = (RadioButton) findViewById(R.id.q1114_1);
            rbtn2 = (RadioButton) findViewById(R.id.q1114_2);

            final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupq1114);

        Intent ii = getIntent();
        p1 = (PersonRoster) ii.getSerializableExtra("Personroster");




        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ1114()!= null &&  !ind.getQ1114().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1114())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }



        Button btnext = findViewById(R.id.btnnext);

        btnnext.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View v) {


                    int selectedId = rg.getCheckedRadioButtonId();
                    selectedRbtn = (RadioButton) findViewById(selectedId);

                    if (selectedRbtn == null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q1114.this);
                        builder.setTitle("Q114");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("Have \"+ p1.getP01() + \"been diagonised with  diabetes");
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

                    else {
                        //Set q101 for the current individual

                       individual.setQ1114(selectedRbtn.getText().toString().substring(0,1));

                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        myDB.close();

                        Intent intent = new Intent(q1114.this, HIVChildParentalConsent15_17.class);
                        intent.putExtra("Individual",  individual);
                        intent.putExtra("Personroster", p1);
                        startActivity(intent);

                    }

                }
            });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (individual.getQ1101().equals("1") || individual.getQ1101().equals("2")) {
                    Intent q1o3 = new Intent(q1114.this, q1102.class);
                    q1o3.putExtra("Individual", individual);
                    startActivity(q1o3);
                    finish();

                } else {
                    if (individual.getQ1103().equals("2") && individual.getQ1107().equals("2") && individual.getQ1108().equals("2") && individual.getQ1109().equals("2")) {
                        Intent intent = new Intent(q1114.this, q1109.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
                    } else {
                        q1114.super.onBackPressed();
                        finish();
                    }

                }
            }

        });
    }





    @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.q1114_1:

                    break;

                case R.id.q1114_2:
                    break;


                default:
                    break;

        }
    }

}
