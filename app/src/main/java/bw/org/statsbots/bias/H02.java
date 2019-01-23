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

public class H02 extends AppCompatActivity   {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected EditText edt;
    protected RadioGroup rbtngroup;
    protected RadioButton selectedRbtn;
    PersonRoster p1 = null;
    Individual pp1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h02);

        setTitle("H02: NUMBER OF ROOMS");
        lib = new LibraryClass();


        final RadioGroup rg = (RadioGroup) findViewById(R.id.H01radioGroup);

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

                edt = (EditText) findViewById(R.id.H02_ROOMS);
               // int selectedId = rg.getCheckedRadioButtonId();
                //selectedRbtn = (RadioButton) findViewById(selectedId);

                if (edt.length() == 0 || edt.getText() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(H02.this);
                    builder.setTitle("Number of rooms");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);
                    builder.setMessage("Please enter number of rooms");
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
                    thisHouse.setH02(selectedRbtn.getText().toString());

                    /**
                     * If current person LineNumber is equal to TotalPersons-1
                     * Proceed to next Question in the roster
                     */
                    // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());

                    //Next question q102


                    Intent q1o2 = new Intent(H02.this, H03.class);
                    q1o2.putExtra("Household",  thisHouse);
                    startActivity(q1o2);

                }

            }
        });
    }

}




