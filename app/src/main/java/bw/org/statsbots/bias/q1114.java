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

                        //update individual
                        DatabaseHelper myDB = new DatabaseHelper(q1114.this);
                        myDB.onOpen(myDB.getReadableDatabase());

                        if(myDB.checkIndividual(individual)){
                            //Update
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);

                        }

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
                q1114.super.onBackPressed();
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
