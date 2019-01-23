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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class q107 extends AppCompatActivity implements Serializable{
    protected  HouseHold thisHouse;
    protected  Individual idv;
    protected RadioGroup rg, rg1, rg2,rg3;
    protected RadioButton rbtn1, rbtn2, rbtn99, rbtnb1, rbtnb2 ,rbtnb3, rbtnb4, rbtnb5, rbtnbOther, rbtnc1, rbtnc2, rbtnc3,rbtnc4, rbtnc5,rbtncOther,selectedRbtn, selectedRbtn1, selectedRbtn2, selectedRbtn3;
    protected EditText txtyy, txtmnth;
    protected LibraryClass lib;
    protected Individual individual;
    protected TextView txta, txtb, txtc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q107);

        setTitle("Q107: WORKED IN MINE");
        lib = new LibraryClass();

        rg = findViewById(R.id.q107radioGroup);
        //rg1 = findViewById(R.id.q107aradioGroup);
        rg2 = findViewById(R.id.q107bradioGroup);
        rg3 = findViewById(R.id.q107cradioGroup);


        rbtn1 = findViewById(R.id.q107_1);
        rbtn2 = findViewById(R.id.q107_2);
        rbtn99 = findViewById(R.id.q107_99);

        rbtnb1 = findViewById(R.id.q107b_1);
        rbtnb2 = findViewById(R.id.q107b_2);
        rbtnb3 = findViewById(R.id.q107b_3);
        rbtnb4 = findViewById(R.id.q107b_4);
        rbtnb5 = findViewById(R.id.q107b_5);
        rbtnbOther = findViewById(R.id.q107b_other);

        rbtnc1 = findViewById(R.id.q107c_1);
        rbtnc2 = findViewById(R.id.q107c_2);
        rbtnc3 = findViewById(R.id.q107c_3);
        rbtnc4 = findViewById(R.id.q107c_4);
        rbtnc5 = findViewById(R.id.q107c_5);
        rbtncOther = findViewById(R.id.q107c_other);

        txtyy = findViewById(R.id.q107a_years);
        txtmnth = findViewById(R.id.q107_months);

        txta = (TextView) findViewById(R.id.q107atxt) ;
                txtb = (TextView) findViewById(R.id.q107btxt) ;
                txtc = (TextView) findViewById(R.id.q107ctxt) ;

        Button btnnext = findViewById(R.id.button);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(q1106.this, q1107.class);
                //startActivity(intent);
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q107.this);
                    builder.setTitle("Q107: Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Have you ever worked in a mine?");
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
                    // individual.setQ1106(selectedRbtn1.getText().toString().substring(0,1));
                    //int selectedId1 = rg1.getCheckedRadioButtonId();
                    //selectedRbtn1 = (RadioButton) findViewById(selectedId1);

                    if (!rbtn99.isChecked() && txtyy.getText().equals(null) || txtmnth.getText().equals(null)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q107.this);
                        builder.setTitle("Q107: Error");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("For how long have you worked in a mine?");
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
                        // individual.setQ1106(selectedRbtn1.getText().toString().substring(0,1));
                        //int selectedId1 = rg1.getCheckedRadioButtonId();
                        //selectedRbtn1 = (RadioButton) findViewById(selectedId1);

                        if ((Integer.valueOf(txtmnth.getText().toString()) >= 13)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(q107.this);
                            builder.setTitle("Q107: Error");
                            builder.setIcon(R.drawable.ic_warning_orange_24dp);

                            builder.setMessage("Months cannot be more than 13?");
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
                            //individual.setQ1106a(selectedRbtn2.getText().toString().substring(0,1));
                            int selectedId2 = rg2.getCheckedRadioButtonId();
                            selectedRbtn2 = (RadioButton) findViewById(selectedId2);

                            if (selectedRbtn2 == null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(q107.this);
                                builder.setTitle("Q107: Error");
                                builder.setIcon(R.drawable.ic_warning_orange_24dp);
                                builder.setMessage("b) What was your occupation?");
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
                                //individual.setQ1106b(selectedRbtn3.getText().toString().substring(0,1));
                                int selectedId3 = rg3.getCheckedRadioButtonId();
                                selectedRbtn3 = (RadioButton) findViewById(selectedId3);
                                if (selectedRbtn3 == null) {
                                    lib.showError(q107.this, "Q107 Other", "Please specify");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);
                                } else {
                                    //individual.setQ1106b(selectedRbtn3.getText().toString().substring(0,1));

                                    if (rbtn2.isChecked()) {
                                        Intent skip201 = new Intent(q107.this, Q201.class);
                                        skip201.putExtra("Household", thisHouse);
                                        startActivity(skip201);
                                    } else {
                                        //  individual.setQ1106bOther(q1106btxtOther.getText().toString());
                                        //Check if country entered is in the list
                                        // Intent intent = new Intent(q1102.this, q1103.class);
                                        //intent.putExtra("Household", thisHouse);
                                        //startActivity(intent);
                                        Intent q1o3 = new Intent(q107.this, Q201.class);
                                        q1o3.putExtra("Household", thisHouse);
                                        startActivity(q1o3);

                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }


    public void onRadioButtonClicked(View v){



        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()){
            case R.id.q107_1:

                if(checked)

                rbtn99.setEnabled(true);
                rbtnb1.setEnabled(true);
                rbtnb2.setEnabled(true);
                rbtnb3.setEnabled(true);
                rbtnb4.setEnabled(true);
                rbtnb5.setEnabled(true);
                rbtnbOther.setEnabled(true);
                rbtnc1.setEnabled(true);
                rbtnc2.setEnabled(true);
                rbtnc3.setEnabled(true);
                rbtnc4.setEnabled(true);
                rbtnc5.setEnabled(true);
                rbtncOther.setEnabled(true);
                txtyy.setEnabled(true);
                txtmnth.setEnabled(true);
                txta.setTextColor(Color.BLACK);
                txtb.setTextColor(Color.BLACK);
                txtc.setTextColor(Color.BLACK);
                //rbtn99.setChecked(true);





                //  rGroup2.setVisibility(View.VISIBLE);

                break;

            case R.id.q107_2:
                if(checked)

                    rbtn99.setChecked(false);
                rbtn99.setEnabled(false);
                rbtnb1.setEnabled(false);
                rbtnb2.setEnabled(false);
                rbtnb3.setEnabled(false);
                rbtnb4.setEnabled(false);
                rbtnb5.setEnabled(false);
                rbtnbOther.setEnabled(false);
                rbtnc1.setEnabled(false);
                rbtnc2.setEnabled(false);
                rbtnc3.setEnabled(false);
                rbtnc4.setEnabled(false);
                rbtnc5.setEnabled(false);
                rbtncOther.setEnabled(false);
                txtyy.setEnabled(false);
                txtmnth.setEnabled(false);

                rbtn99.setChecked(false);
                rbtnb1.setChecked(false);
                rbtnb2.setChecked(false);
                rbtnb3.setChecked(false);
                rbtnb4.setChecked(false);
                rbtnb5.setChecked(false);
                rbtnbOther.setChecked(false);
                rbtnc1.setChecked(false);
                rbtnc2.setChecked(false);
                rbtnc3.setChecked(false);
                rbtnc4.setChecked(false);
                rbtnc5.setChecked(false);
                rbtncOther.setChecked(false);
                txtyy.setText("");
                txtmnth.setText("");

                txta.setTextColor(Color.LTGRAY);
                txtb.setTextColor(Color.LTGRAY);
                txtc.setTextColor(Color.LTGRAY);



                break;

            case R.id.q107b_other:
                if(checked)




                txtyy.setEnabled(false);
                txtmnth.setEnabled(false);




                break;

            case R.id.q107c_other:
                if(checked)



                    txtyy.setEnabled(false);
                txtmnth.setEnabled(false);




                break;
            case R.id.q107_99:
                if(checked)



                    txtyy.setEnabled(false);
                txtmnth.setEnabled(false);
                txtyy.setText("");
                txtmnth.setText("");




                break;
        }
    }


}
