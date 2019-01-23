package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RapidHIVTest extends Activity {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected EditText edtDate, edtComments;
    protected activity_general_information assginfo;
    protected RadioButton rb1, rb2, rb3, rb4, selectedRbtn, selectedRbtn1;
    protected RadioGroup rg1, rg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapid_hivtest);
        rb1 = findViewById(R.id.rbtn1);
        rb2 = findViewById(R.id.rbtn2);
        rb3 = findViewById(R.id.rbtn3);
        rb4 = findViewById(R.id.rbtn4);
        rg1 = findViewById(R.id.RapidTestradioGroup);
        rg2 = findViewById(R.id.RapidTestradioGroup1);
        edtDate = findViewById(R.id.EdtDaterec);
        edtComments = findViewById(R.id.EdtComments);

        lib = new LibraryClass();

        setTitle("HIV Rapid Test");

        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        final EditText edt = (EditText) findViewById(R.id.EdtDaterec);
        /**
         * Loop through the house hold members to check if hh member 's P02 is answered
         * If P02 is null then ask the individual
         */

        for (int r = 0; r < thisHouse.getTotalPersons(); r++) {
            p1 = thisHouse.getPersons()[r];
            if (((Integer.parseInt(p1.getP04YY()) >=15 && Integer.parseInt(p1.getP04YY()) <=64) || (Integer.parseInt(p1.getP04MM()) <=1 && Integer.parseInt(p1.getP04WKS()) <= 2)))
            {
                //Next question P05
                //lib.showError(P07.this,"Members Done","HH members are complete");
                // assginfo.EAStatus.getText().toString();
                Intent intent = new Intent(RapidHIVTest.this, P17.class);
                intent.putExtra("Household", thisHouse);
                startActivity(intent);




/*
                insertEAAssignment(
                        String EA_Assignment_ID,String STRATUM,String DISTRICT,String VILLAGE,String
                        LOCALITY,String EA,
                        String BLOCK_NO ,String EA_STATUS)*/
            } else {

                    break;

            }

        }

        //if ((Integer.valueOf(p1.getP04MM().toString()) >= 1 && Integer.valueOf(p1.getP04WKS().toString()) >= 2) || Integer.valueOf(p1.getP04YY().toString()) >= 15 && p1.getBloodSampleCollected() == null || p1.getRapidResults() == null) {

           // TextView textView = (TextView) findViewById(R.id.P07);
           // String s = getResources().getString(R.string.P07);
           // int t = s.indexOf("#");
           // s = s.replace("#", "<b>" + p1.getP01() + "</b> ");

           // textView.setText(Html.fromHtml(s));


            /**
             * NEXT Person BUTTON
             */
            Button btnNext = (Button) findViewById(R.id.button);
            String btnLabel = "";
            if (p1.getLineNumber() + 1 == thisHouse.getTotalPersons()) {
                btnLabel = "Next";
            } else {
                btnLabel = "Next > " + thisHouse.getPersons()[p1.getLineNumber() + 1].getP01();
            }


            btnNext.setText(btnLabel);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int Days;

                    String date = edtDate.getText().toString();

                    if (date.length() == 0) {
                        lib.showError(RapidHIVTest.this, "RapidHIVTest Error", "Please select enter number of days for the visitor " + p1.getP01());
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    } else {

                        //Set BlodSampleCollected for the current individual

                        thisHouse.getPersons()[p1.getLineNumber()].setBloodSampleCollected(selectedRbtn.getText().toString().substring(0, 1));
                        thisHouse.getPersons()[p1.getLineNumber()].setU15Rapid_Results(selectedRbtn1.getText().toString().substring(0, 1));
                        thisHouse.getPersons()[p1.getLineNumber()].setRapidDate(edtDate.getText().toString());
                        thisHouse.getPersons()[p1.getLineNumber()].setRapid_Comment(edtComments.getText().toString());

                        //Restart the current activity for next individual
                        if (p1.getLineNumber() == thisHouse.getTotalPersons() - 1) {
                            for (PersonRoster p : thisHouse.getPersons()) {
                                thisHouse.writeData(
                                       (p1.getP01() + p1.getP02() + p1.getP03() + p1.getP04YY() + p1.getP04MM() + p1.getP04WKS() +
                                                p1.getP05() + p1.getP06() + p1.getP07()  + p1.getBloodSampleCollected()+ p1.getRapid_Comment()), RapidHIVTest.this);
                            }

                            //Next question P17
                            Intent intent = new Intent(RapidHIVTest.this, P17.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);


                        } else {
                            //Restart the current activity for next individual

                            finish();
                            //Toast.makeText(P07.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                            startActivity(getIntent());
                        }


                    }


                    int selectedId = rg1.getCheckedRadioButtonId();
                    selectedRbtn = (RadioButton) findViewById(selectedId);

                    if (selectedRbtn == null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RapidHIVTest.this);
                        builder.setTitle("RapidHIVTest: Error");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("Did you submit sputum?");
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

                        int selectedId1 = rg2.getCheckedRadioButtonId();
                        selectedRbtn1 = (RadioButton) findViewById(selectedId1);

                        if (selectedRbtn1 == null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RapidHIVTest.this);
                            builder.setTitle("RapidHIVTest: Error");
                            builder.setIcon(R.drawable.ic_warning_orange_24dp);

                            builder.setMessage("If YES, what was the result?");
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
                    }

                }

            });

        }
    }


