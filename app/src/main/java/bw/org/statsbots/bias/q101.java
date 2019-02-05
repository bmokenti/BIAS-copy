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
import java.util.List;

public class q101 extends AppCompatActivity implements View.OnClickListener {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, selected = null;
    protected RadioGroup rbtngroup;
    protected DatabaseHelper myDB;
    protected RadioButton selectedRbtn;
    Individual p1 = null;
    Individual pp1 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q101);

        setTitle("q101: SEX");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q101_1);
        rbtn2 = (RadioButton) findViewById(R.id.q101_2);
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
       final RadioGroup rg = (RadioGroup) findViewById(R.id.q101radioGroup);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(),individual.getAssignmentID());
        sample.getSTATUS();
        Button btnext = findViewById(R.id.btnnext);
//        PersonRoster pr[] = thisHouse.getPersons();






        btnext.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q101.this);
                    builder.setTitle("Select sex of respondent");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Please select gender");
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
                            individual.setQ101(selectedRbtn.getText().toString().substring(0, 1));


                    //Check if individual already been saved and update
                    myDB = new DatabaseHelper(q101.this);
                    myDB.onOpen(myDB.getReadableDatabase());

                    if(myDB.checkIndividual(individual))
                    {
                        //Update
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);

                    }
                    else{
                        //Insert
                        myDB.insertIndividual(individual);

                    }





                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());

                        //Next question q102
                        Intent q1o2 = new Intent(q101.this, q102.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);

                    }
                }


    });
        }




        @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.q101_1:
                selectedRbtn = (RadioButton)findViewById(R.id.q101_1);
                break;

            case R.id.q101_2:
                selectedRbtn = (RadioButton)findViewById(R.id.q101_2);
              break;


            default:
                break;

        }
    }

}







