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

public class H08 extends AppCompatActivity implements View.OnClickListener {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2,rbtn3, rbtn4,rbtn5, rbtn6,rbtn7, selected = null;
    protected RadioGroup rbtngroup;
    protected EditText edt;
    protected RadioButton selectedRbtn;
    PersonRoster p1 = null;
    Individual pp1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h08);

            setTitle("H08: SOURCE OF ENERGY");
            lib = new LibraryClass();
            rbtn1 = (RadioButton) findViewById(R.id.H08_1);
            rbtn2 = (RadioButton) findViewById(R.id.H08_2);
            rbtn3 = (RadioButton) findViewById(R.id.H08_3);
            rbtn4 = (RadioButton) findViewById(R.id.H08_4);
            rbtn5 = (RadioButton) findViewById(R.id.H08_5);
            rbtn6 = (RadioButton) findViewById(R.id.H08_6);
            rbtn7 = (RadioButton) findViewById(R.id.H08_other);
            edt = (EditText) findViewById(R.id.H08_txtOther);
            final RadioGroup rg = (RadioGroup) findViewById(R.id.H08radioGroup);

            //rbtn1.setOnClickListener(this);
            //rbtn2.setOnClickListener(this);

            // final int selectedId = rbtngroup.getCheckedRadioButtonId();

            Intent i = getIntent();
            thisHouse = (HouseHold) i.getSerializableExtra("Household");
            int p = 0;
            Button btnext = findViewById(R.id.btnnext);
//        PersonRoster pr[] = thisHouse.getPersons();



            btnext.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View v) {


                    int selectedId = rg.getCheckedRadioButtonId();
                    selectedRbtn = (RadioButton) findViewById(selectedId);

                    if (selectedRbtn == null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(bw.org.statsbots.bias.H08.this);
                        builder.setTitle("SOURCE OF ENERGY");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);
                        builder.setMessage("What is the main source of energy used for: COOKING?");
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
                        thisHouse.setH08(selectedRbtn.getText().toString().substring(0,1));

                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());

                        //Next question q102


                        Intent q1o2 = new Intent(bw.org.statsbots.bias.H08.this, H09.class);
                        q1o2.putExtra("Household",  thisHouse);
                        startActivity(q1o2);

                    }

                }
            });
        }




        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.q101_1:

                    break;

                case R.id.q101_2:
                    break;


                default:
                    break;

            }
        }

    }

