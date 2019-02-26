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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class q406 extends AppCompatActivity implements  Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1=null;protected DatabaseHelper myDB;
    protected String currentHH=null;
    protected LibraryClass lib;

    protected EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q406);


        setTitle("Q406: SEXUAL HISTORY");
        lib = new LibraryClass();
        edt = (EditText) findViewById(R.id.q406_partners) ;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                {
                    if (edt.getText().equals(null) || edt.length()==0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q406.this);
                        builder.setTitle("Q406: Error");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("In the past 12 months with how many people OVERALL have you had sex?");
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
                        //Set q406 for the current individual
                   individual.setQ406(edt.getText().toString());


                        Intent q1o2 = new Intent(q406.this, q407.class);
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
                q406.super.onBackPressed();
            }


        });

    }


}
