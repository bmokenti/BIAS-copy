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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class q1111 extends AppCompatActivity implements  Serializable {
    protected HouseHold thisHouse;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4,rbtn5, rbtn5ot, selected = null;
    protected  RadioButton selectedRbtn;
    protected EditText text1111other;
    protected Individual individual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1111);

        setTitle("Q1111 ");
        Button btnnext = findViewById(R.id.button);
        rbtn1 = (RadioButton) findViewById(R.id.q1111_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1111_2);
        rbtn3 = (RadioButton) findViewById(R.id.q1111_3);
        rbtn4 = (RadioButton) findViewById(R.id.q1111_4);
        rbtn5 = findViewById(R.id.q1111_5);
        rbtn5ot = (RadioButton) findViewById(R.id.q1111_ot);
        text1111other= findViewById(R.id.txtother);


        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);

        //rbtn1.setOnClickListener(this);
        //rbtn2.setOnClickListener(this);

        // final int selectedId = rbtngroup.getCheckedRadioButtonId();

        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;
        Button btnext = findViewById(R.id.btnNext);



        btnext.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q1111.this);
                    builder.setTitle("Q1111");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Where did you FIRST go for help?");
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
                    //thisHouse.getPersons()[p1.getLineNumber()].setq1105(selectedRbtn.getText().toString().substring(0,1));
                   // individual.setQ1111(selectedRbtn.getText().toString().substring(0,1));
                    /**
                     * If current person LineNumber is equal to TotalPersons-1
                     * Proceed to next Question in the roster
                     */
                    // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());

                    //Next question q102


                    Intent intent = new Intent(q1111.this, q1112.class);
                    intent.putExtra("Household",  thisHouse);
                    startActivity(intent);

                }

            }
        });
    }

    public void onRadioButtonClicked(View v) {


        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q1111_ot:
                if (checked) {
                    text1111other.setVisibility(View.VISIBLE);
                   // individual.setQ1111Other(text1111other.getText().toString());
                }


                break;

            case R.id.q1111_1:
                if (checked) {
                    text1111other.setText("");
                    text1111other.setVisibility(View.INVISIBLE);

                }

            case R.id.q1111_2:
                if (checked) {
                    text1111other.setText("");
                    text1111other.setVisibility(View.INVISIBLE);

                }

            case R.id.q1111_3:
                if (checked) {
                    text1111other.setText("");
                    text1111other.setVisibility(View.INVISIBLE);

                }

            case R.id.q1111_4:
                if (checked) {
                    text1111other.setText("");
                    text1111other.setVisibility(View.INVISIBLE);

                }

            case R.id.q1111_5:
                if (checked) {
                    text1111other.setText("");
                    text1111other.setVisibility(View.INVISIBLE);

                }


                //txtdays.setVisibility(View.INVISIBLE);
                // txtweeks.setVisibility(View.INVISIBLE);


        }
    }




   }