package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Visit3 extends AppCompatActivity {
    protected HouseHold thisHouse;
    protected LibraryClass lib;
    Button btndate;
    TextView txtshowdate;
    Button btnEndVisits;
    RadioGroup visits;
    Button btnBegin;
    EditText other;
    Button appointment;
    EditText editxtComment;
    Button btnNext, btnStart;
    Button btnPrevious;
    String radiovalue=null;
    private DatabaseHelper myDB;



    TextView nxtVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit3);


        setTitle("Visit 3");
        txtshowdate = (TextView) findViewById(R.id.txtdate);
        btndate = (Button) findViewById(R.id.TodayDate);
        visits = (RadioGroup) findViewById(R.id.radioGroup);
        visits.setVisibility(View.INVISIBLE);
        btnBegin = (Button) findViewById(R.id.btnStart);
        btnEndVisits = (Button) findViewById(R.id.btnEnd);
        btnEndVisits.setVisibility(View.INVISIBLE);
        btnBegin.setVisibility(View.INVISIBLE);
        other = (EditText) findViewById(R.id.txtOther);
        other.setVisibility(View.INVISIBLE);
        editxtComment = findViewById(R.id.editxtComment);
        editxtComment.setVisibility(View.INVISIBLE);
        btnNext=findViewById(R.id.btnNext);
        btnPrevious=findViewById(R.id.button3);
        btnStart = findViewById(R.id.btnStart);
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();



        btndate.setOnClickListener(new Visit3.DateBtnClick());
        btnEndVisits.setOnClickListener(new Visit3.EndIntervieBtn());

        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String d = dateFormat.format(date);

                //********************************************SAVE DATE FOR VISIT 2
                if(thisHouse.getDATE1().equals("")){
                    thisHouse.setDATE2(d);
                    thisHouse.setCOMMENT2(editxtComment.getText().toString());
                }else {
                    if (thisHouse.getDATE2().equals("")) {
                        thisHouse.setDATE2(d);
                        thisHouse.setCOMMENT2(editxtComment.getText().toString());
                    } else {
                        if (thisHouse.getDATE3().equals("")) {
                            thisHouse.setDATE2(d);
                            thisHouse.setCOMMENT2(editxtComment.getText().toString());
                        }
                    }
                }




                Intent intent = new Intent(Visit3.this, MainActivity.class);
                intent.putExtra("Household", thisHouse);
                startActivity(intent);
            }
        });

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");



        btnNext.setOnClickListener(new View.OnClickListener()
        {
            //Save all responses
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String d = dateFormat.format(date);

            @Override
            public void onClick(View view)
            {
                if(radiovalue==null)
                {
                    new LibraryClass().showError(Visit3.this,"Visit Result Error","Please select visit result");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);


                }
                else
                {
                    //check the actual values
                    if(radiovalue.equals("6"))
                    {
                        String s = other.getText().toString();
                        if(s.length()<5)
                        {
                            new LibraryClass().showError(Visit3.this,"Result Code 6. Other Error","Please enter more than 6 characters");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                            other.requestFocus();

                        }
                        else{


                            thisHouse.setVISIT3_RESULT(radiovalue);
                            thisHouse.setVisit3Other(s);
                            thisHouse.setDATE3(d);
                            thisHouse.setCOMMENT3(editxtComment.getText().toString());

                            /**
                             * SAVE OR UPDATE THE HOUSE HOLD BASED
                             */

                            myDB.updateHHStatus(thisHouse);
                            //go to next activity
                            Intent intent = new Intent(Visit3.this,Dashboard.class);
                            intent.putExtra("Household",  thisHouse);
                            startActivity(intent);

                        }
                    }else{
                        //Save all responses
                        thisHouse.setVISIT3_RESULT(radiovalue);
                        thisHouse.setDATE3(d);
                        thisHouse.setCOMMENT3(editxtComment.getText().toString());
                        /**
                         * SAVE OR UPDATE THE HOUSE HOLD BASED
                         */

                        myDB.updateHHStatus(thisHouse);

                        //go to next activity
                        Intent intent = new Intent(Visit3.this,Dashboard.class);
                        intent.putExtra("Household",  thisHouse);
                        startActivity(intent);
                    }

                }


            }
        });




    }

    class DateBtnClick implements Button.OnClickListener
    {
        public void onClick(View v)
        {
            Date d = new Date();

            txtshowdate.setText(d.toString());
            thisHouse.setDATE3(txtshowdate.getText().toString());
            thisHouse.setInterview_Status("9");

            btnEndVisits.setVisibility(View.VISIBLE);
            btnBegin.setVisibility(View.VISIBLE);

        }
    }

    class EndIntervieBtn implements Button.OnClickListener
    {
        public void onClick(View v)
        {
            visits.setVisibility(View.VISIBLE);
            // btnBegin.setVisibility(View.VISIBLE);
        }
    }



    public void onRadioButtonClicked(View view)
    {
        // Is the button now checked?
        Intent intentAp = new Intent(Visit3.this,NextVisitAppointment.class);
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked

        switch (view.getId()) {
            case R.id.ResultCode1:
                if (checked)
                    radiovalue = "1";



                // If completed on first visit, don't set next appointment date
                break;
            case R.id.ResultCode2:
                if (checked)
                    // If partially completed on first visit, set next appointment date and time
                    radiovalue = "2";

                //startActivity(intent4);
                break;
            //
            case R.id.ResultCode3:
                if (checked)
                    // If present but not available for interview,on first visit, set next appointment date and time
                    radiovalue = "3";

                //Intent intentAp = new Intent(Visit1.this,NextVisitAppointment.class);
                editxtComment.setVisibility(View.VISIBLE);
                other.setVisibility(View.INVISIBLE);
                editxtComment.requestFocus();

                //startActivity(intentAp);

                break;
            case R.id.ResultCode4:
                if (checked)
                    // If refused on first visit, set next appointment date and time,report and flag to supervisor to note
                    radiovalue = "4";

                editxtComment.setVisibility(View.VISIBLE);
                other.setVisibility(View.INVISIBLE);
                editxtComment.requestFocus();

                break;
            //
            case R.id.ResultCode5:
                if (checked)
                    // If postponed, set next appointment date and time
                    radiovalue = "5";
                editxtComment.setVisibility(View.VISIBLE);
                other.setVisibility(View.INVISIBLE);
                editxtComment.requestFocus();

                break;
            case R.id.ResultCode6:
                if (checked)
                    radiovalue="6";
                // If other, specify reason ,don't set next appointment
                other.setVisibility(View.VISIBLE);
                other.requestFocus();
                editxtComment.setVisibility(View.INVISIBLE);


                break;
        }

        //Record Result as the options are boeing clicked
        thisHouse.setVISIT1_RESULT(radiovalue);
    }





}
