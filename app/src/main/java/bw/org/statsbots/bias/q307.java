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

public class q307 extends AppCompatActivity implements View.OnClickListener{

    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2 ;
    protected RadioGroup rbtngroup;
    protected RadioButton selectedRbtn;
    PersonRoster p1 = null;
    Individual pp1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q307);




        setTitle("Q307: ALCOHOL CONSUMPTION AND SUBSTANCE USE");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q307_1);
        rbtn2 = (RadioButton) findViewById(R.id.q307_2);



        final RadioGroup rg = (RadioGroup) findViewById(R.id.q307radioGroup);



        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;
        Button btnext = findViewById(R.id.button);
//        PersonRoster pr[] = thisHouse.getPersons();



        btnext.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q307.this);
                    builder.setTitle("Q307: ERROR?");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("In the past 12 months have you ever seen or heard any anti-alcohol & substance abuse messages?");
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
                        //Set q101 for the current individual
                       //thisHouse.getIndividual()[p1.getLineNumber()].setQ307(selectedRbtn.getText().toString().substring(0, 1));

                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());

                        //Next question q102


                        Intent q1o2 = new Intent(q307.this, q401.class);
                        q1o2.putExtra("Household", thisHouse);
                        startActivity(q1o2);

                    }

                }

        });
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.q307_1:

                break;

            case R.id.q307_2:
                break;


            default:
                break;

        }
    }

}










