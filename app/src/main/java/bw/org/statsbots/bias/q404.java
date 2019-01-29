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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class q404 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected RadioButton rbtn1,rbtn2,rbtn3, selected=null;
    protected CheckBox chk1, chk2, chk3 ;

    protected RadioGroup rg;
    protected RadioButton selectedRbtn;
    HouseHold thisHose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q404);


        setTitle("Q404: SEXUAL HISTORY");
        lib = new LibraryClass();
        chk1 =  (CheckBox) findViewById(R.id.Q404_1);
        chk2 =  (CheckBox) findViewById(R.id.Q404_2);
        chk3 = (CheckBox) findViewById(R.id.Q404_3);
        rbtn1 =  (RadioButton)findViewById(R.id.q404a_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q404a_2);
        // rbtn3 =  (RadioButton)findViewById(R.id.q403_3);

        rg = (RadioGroup)findViewById(R.id.q404radioGroupa) ;
        //rbtn1 =  (RadioButton)findViewById(R.id.q403_1);
        //rbtn2 =  (RadioButton)findViewById(R.id.q403_2);

        //rbtn1.setOnClickListener(this);
        //rbtn2.setOnClickListener(this);


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;



        if (individual.getQ403().equals("2") && individual.getQ101().equals("1")) {
            Intent intent = new Intent(q404.this, q501.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }

       //skip


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (!chk1.isChecked() && !chk2.isChecked() && !chk3.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q404.this);
                    builder.setTitle("Q404 Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Which type of sex have you had in the past 12 months.: multiple response?");
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
                    if (selectedRbtn == null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q404.this);
                        builder.setTitle("Q404a: Error");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("Was a condom used the last time you had sex in the past 12 months?");
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
                        //Set q404 for the current individual

                      individual.setQ404_1(chk1.getText().toString().substring(0,1));
                       individual.setQ404_2(chk2.getText().toString().substring(0, 1));
                        individual.setQ404_3(chk3.getText().toString().substring(0,1));
                       individual.setQ404a(selectedRbtn.getText().toString().substring(0,1));



                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());

                        //Next question q102


                        Intent q1o2 = new Intent(q404.this, q405.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);

                    }

                }
            }
        });
    }


    @Override
    public void onClick(View view) {

        //
        switch (view.getId()) {

            case R.id.q404a_1:
                //Intent intent3 = new Intent(this, q501.class);
                //startActivity(intent3);
                break;

            case R.id.q404a_2:
                //Intent intent4 = new Intent(this, q501.class);
                // startActivity(intent4);
                break;


            default:
                break;

        }
    }
}
