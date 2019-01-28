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

import java.io.Serializable;

public class q203 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    PersonRoster p1 = null;
    Individual pp1 = null;
    protected EditText edt;
    protected RadioButton selectedRbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q203);


        setTitle("Q203: MARITAL STATUS AND RELATIONSHIP");
        lib = new LibraryClass();
        edt = (EditText) findViewById(R.id.q203_Partners);


        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;
        Button btnext = findViewById(R.id.button);
//        PersonRoster pr[] = thisHouse.getPersons();



        btnext.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {




                if (edt.getText() == null || edt.length()==0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q203.this);
                    builder.setTitle("Marital Status?");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Altogether, how many wives/husbands or live in partners have you had?");
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
                    //thisHouse.getIndividual()[p1.getLineNumber()].setQ203(edt.getText().toString());

                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());

                        //Next question q102


                        Intent q1o2 = new Intent(q203.this, q204.class);
                        q1o2.putExtra("Household", thisHouse);
                        startActivity(q1o2);

                    }

                }

        });
    }





}

