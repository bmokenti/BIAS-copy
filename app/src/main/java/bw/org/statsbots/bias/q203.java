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
import java.util.List;

public class q203 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    PersonRoster p1 = null;
    Individual pp1 = null; protected  DatabaseHelper myDB;
    protected EditText edt;
    protected RadioButton selectedRbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q203);

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        setTitle("Q203: MARITAL STATUS AND RELATIONSHIP");
        lib = new LibraryClass();
        edt = (EditText) findViewById(R.id.q203_Partners);


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        Button btnext = findViewById(R.id.button);
//        PersonRoster pr[] = thisHouse.getPersons();

        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;
        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        if(individual.getQ203()!=null){
            edt.setText(individual.getQ203());
        }


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
                  individual.setQ203(edt.getText().toString());

                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());

                        //Next question q102
                    myDB = new DatabaseHelper(q203.this);
                    myDB.onOpen(myDB.getReadableDatabase());

                    myDB.updateInd("Q203",individual.getAssignmentID(),individual.getBatch(),ind.getQ203(),String.valueOf(individual.getSRNO()));

                    myDB.close();

                        Intent q1o2 = new Intent(q203.this, q204.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);

                    }

                }

        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent q1o2 = new Intent(q203.this, q202.class);
                q1o2.putExtra("Individual", individual);
                startActivity(q1o2);

                q203.super.onBackPressed();
            }


        });

    }





}

