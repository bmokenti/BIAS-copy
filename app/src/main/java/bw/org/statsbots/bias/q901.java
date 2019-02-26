package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class q901 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, rbtna3, rbtna4, rbtna5, rbtna6, rbtna7, rbtna8, rbtna10, rbtna11, rbtnaOther, selected = null;
    protected RadioGroup rg, rga;
    protected EditText edt;
    protected TextView t1;
    protected RadioButton selectedRbtn, selectedRbtna;
    HouseHold thisHose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q901);


        setTitle("Q901: HIV SUPPORT, CARE ANF TREATMENT");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q901_1);
        rbtn2 = (RadioButton) findViewById(R.id.q901_2);
        rbtna1 = (RadioButton) findViewById(R.id.q901a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q901a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q901a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q901a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q901a_5);
        rbtna6 = (RadioButton) findViewById(R.id.q901a_6);
        rbtna7 = (RadioButton) findViewById(R.id.q901a_7);
        rbtna8 = (RadioButton) findViewById(R.id.q901a_8);
        rbtna10 = (RadioButton) findViewById(R.id.q901a_10);
        rbtna11 = (RadioButton) findViewById(R.id.q901a_11);
        rbtnaOther = (RadioButton) findViewById(R.id.q901a_other);
        edt = (EditText) findViewById(R.id.q901a_othertxt);
        t1 = (TextView) findViewById(R.id.q901aatxt);

        rg = (RadioGroup) findViewById(R.id.q901radioGroup);
        rga = (RadioGroup) findViewById(R.id.q901aradioGroupa);


        final int selectedId = rg.getCheckedRadioButtonId();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;


        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());

        Log.d("age",individual.getQ102());

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();



//        if((individual.getQ801f().equals("2") || individual.getQ801f().equals("3") ||individual.getQ801f().equals("4") || individual.getQ801f().equals("9")) && individual.getQ101().equals("2")
//        && (Integer.valueOf(individual.getQ102()) < 65 && (sample.getStatusCode().equals("1") || sample.getStatusCode().equals("2"))))
//        {
//            Intent intent = new Intent(q901.this, q1001.class);
//            intent.putExtra("Individual", individual);
//            startActivity(intent);
//        }
//        else {
//
//        }

//        if((individual.getQ801f().equals("2") || individual.getQ801f().equals("3") ||individual.getQ801f().equals("4") || individual.getQ801f().equals("9"))&& individual.getQ101().equals("1")
//        && (Integer.valueOf(individual.getQ102()) < 64 && (sample.getStatusCode().equals("1") || sample.getStatusCode().equals("2"))))
//        {
//            Intent intent = new Intent(q901.this, q1101.class);
//            intent.putExtra("Individual", individual);
//            startActivity(intent);
//        }
//        else {
//
//        }

        if(!individual.getQ801f().equals("1") && Integer.valueOf(individual.getQ102()) >= 65 && (sample.getStatusCode().equals("2") ))
        {
            Intent intent = new Intent(q901.this, q1101.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }
        else {

        }


        if(sample.getStatusCode().equals("3") && individual.getQ801f().equals("1"))
        {
            Intent intent = new Intent(q901.this, q904.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }



            if(sample.getStatusCode().equals("3") && (individual.getQ801f().equals("2") || individual.getQ801f().equals("3") ||individual.getQ801f().equals("4") || individual.getQ801f().equals("9")))
            {
                Intent intent = new Intent(q901.this, q1101.class);
                intent.putExtra("Individual", individual);
                startActivity(intent);
        }



        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q901.this);
                    builder.setTitle("Q901: Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("After learning you had HIV, have you EVER received HIV medical care from a doctor, clinical officer or nurse?");
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

                    int selectedIda = rga.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedIda);

                    if (selectedRbtna == null && rbtn2.isChecked()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q901.this);
                        builder.setTitle("Q901: Error");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("What is the MAIN reason why you have never received HIV medical care from a doctor, clinical officer, or nurse?");
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

                        //individual.setQ503(selectedRbtn.getText().toString().substring(0,1));

                        if (rbtnaOther.isChecked() && edt.length() == 0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(q901.this);
                            builder.setTitle("Q901aOther: Error");
                            builder.setIcon(R.drawable.ic_warning_orange_24dp);

                            builder.setMessage("Please specify or deselect other specify option?");
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

                            //individual.setQ503(selectedRbtn.getText().toString().substring(0,1));

                            if (rbtn2.isChecked() && (rbtna1.isChecked() || rbtna2.isChecked() || rbtna3.isChecked() || rbtna4.isChecked() || rbtna5.isChecked() || rbtna6.isChecked() || rbtna7.isChecked() || rbtna8.isChecked() || rbtna10.isChecked() || rbtna11.isChecked() || rbtnaOther.isChecked())) {
                                // to include ea status code on the check

                                Intent q1o2 = new Intent(q901.this, q1001.class);
                                q1o2.putExtra("Individual", individual);
                                startActivity(q1o2);

                            } else {

                                //individual.setQ503(selectedRbtn.getText().toString().substring(0,1));

                                if (rbtn1.isChecked()) {
                                    // to include ea status code on the check
                                    individual.setQ901(selectedRbtn.getText().toString().substring(0, 1));

                                    Intent q1o2 = new Intent(q901.this, q902.class);
                                    q1o2.putExtra("Individual", individual);
                                    startActivity(q1o2);

                                } else {
                                    //Set q101 for the current individual

                                    individual.setQ901(selectedRbtn.getText().toString().substring(0, 1));
                                    individual.setQ901a(selectedRbtna.getText().toString().substring(0, 1));
                                    individual.setQ901a(edt.getText().toString());

                                    Intent intent = new Intent(q901.this, q902.class);
                                    intent.putExtra("Individual", individual);
                                    startActivity(intent);

                                }

                            }
                        }
                    }
                }
            }
        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q901.super.onBackPressed();
            }


        });
    }


    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q901_1:
                if(checked)
                    rbtna1.setEnabled(false);
                    rbtna1.setEnabled(false);
                    rbtna2.setEnabled(false);
                    rbtna3.setEnabled(false);
                    rbtna4.setEnabled(false);
                    rbtna5.setEnabled(false);
                    rbtna6.setEnabled(false);
                    rbtna7.setEnabled(false);
                    rbtna8.setEnabled(false);
                    rbtna10.setEnabled(false);
                    rbtna11.setEnabled(false);
                    rbtnaOther.setEnabled(false);
                    t1.setTextColor(Color.LTGRAY);
                    rbtna1.setChecked(false);
                    rbtna1.setChecked(false);
                    rbtna2.setChecked(false);
                    rbtna3.setChecked(false);
                    rbtna4.setChecked(false);
                    rbtna5.setChecked(false);
                    rbtna6.setChecked(false);
                    rbtna7.setChecked(false);
                    rbtna8.setChecked(false);
                    rbtna10.setChecked(false);
                    rbtna11.setChecked(false);
                    rbtnaOther.setChecked(false);









                break;

            case R.id.q901_2:
                if(checked)
                    rbtna1.setEnabled(true);
                rbtna1.setEnabled(true);
                rbtna2.setEnabled(true);
                rbtna3.setEnabled(true);
                rbtna4.setEnabled(true);
                rbtna5.setEnabled(true);
                rbtna6.setEnabled(true);
                rbtna7.setEnabled(true);
                rbtna8.setEnabled(true);
                rbtna10.setEnabled(true);
                rbtna11.setEnabled(true);
                rbtnaOther.setEnabled(true);
                t1.setTextColor(Color.BLACK);
                break;

            case R.id.q901a_1:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;

            case R.id.q901a_2:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;

            case R.id.q901a_3:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;

            case R.id.q901a_4:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;
            case R.id.q901a_5:

                break;
            case R.id.q901a_6:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;

            case R.id.q901a_7:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;

            case R.id.q901a_8:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;

            case R.id.q901a_10:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;
            case R.id.q901a_11:
                if(checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;
            case R.id.q901a_other:
                if(checked)
                    edt.setVisibility(View.VISIBLE);
                else


                break;

            default:

                break;

        }

    }
}

