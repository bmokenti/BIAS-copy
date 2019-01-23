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

public class q1104 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected  Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, selectedRbtn, selected = null;
    protected RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1104);


        setTitle("Q1104:");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q1104_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1104_2);
        rg = (RadioGroup) findViewById(R.id.q1104radioGroup);

        rbtn1.setOnClickListener(this);
        rbtn2.setOnClickListener(this);


        final int selectedId = rg.getCheckedRadioButtonId();

        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;
        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q1104.this);
                    builder.setTitle("Q1104:");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Do you cough up sputum? (FIELD WORKER EXPLAIN)");
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

                   // individual.setQ1104(selectedRbtn.getText().toString().substring(0,1));
                    if (rbtn2.isChecked()) {
                        Intent q1o3 = new Intent(q1104.this, q1107.class);
                        q1o3.putExtra("Household", thisHouse);
                        startActivity(q1o3);

                    } else {
                        //Set q1105 for the current individual
                        //thisHouse.getPersons()[p1.getLineNumber()].setq1105(selectedRbtn.getText().toString().substring(0,1));
                        //individual.setQ1104(selectedRbtn.getText().toString().substring(0,1));
                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());

                        //Next question P04
                        Intent intent = new Intent(q1104.this, q1105.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);


                    }
                }
            }
        });


    }






    @Override
    public void onClick(View view) {
        //
        switch (view.getId()) {
            case R.id.q1104_1:
                //Intent intent3 = new Intent(this, q1104.class);
                //startActivity(intent3);
                break;


            case R.id.q1104_2:
                //Intent intent4 = new Intent(this, q1104.class);
                //startActivity(intent4);
                break;



        }
    }


}

