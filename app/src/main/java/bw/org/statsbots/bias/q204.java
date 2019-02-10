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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import java.io.Serializable;

public class q204 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    PersonRoster p1 = null;
    Individual pp1 = null;
    protected EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q204);


        setTitle("Q204: MARITAL STATUS AND RELATIONSHIP");
        lib = new LibraryClass();
        edt = (EditText) findViewById(R.id.q204_years);


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        Button btnext = findViewById(R.id.button);

//        PersonRoster pr[] = thisHouse.getPersons();
// skip condition


        if ( individual.getQ201().equals("1") || individual.getQ201().equals("4")|| individual.getQ201().equals("5")|| individual.getQ201().equals("6"))
        {
            Intent skipto301 = new Intent(q204.this, q301.class);
            skipto301.putExtra("Individual", individual);
            startActivity(skipto301);

        } else {

//break and do nothing
        }


        btnext.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {


                if (edt.getText() == null || edt.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q204.this);
                    builder.setTitle("Marital Status?");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("For how many years have you been married or living together?");
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
                    individual.setQ204(edt.getText().toString());
                    Integer age = Integer.parseInt(individual.getQ102());
                    Integer years = Integer.parseInt(individual.getQ204());
                    if ((age - years <= 12) || (years >= age))
                    {
                        lib.showError(q204.this, "Q204: Error", "Check age and years difference");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    }

                    else
                   {
                        //Set q101 for the current individual
                       // individual.setQ204(edt.getText().toString());


                        //update individual
                        DatabaseHelper myDB = new DatabaseHelper(q204.this);

                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                        myDB.close();

                        Intent q1o2 = new Intent(q204.this, q205.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);

                    }

                }
            }

        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q204.super.onBackPressed();
            }

        });

    }

}