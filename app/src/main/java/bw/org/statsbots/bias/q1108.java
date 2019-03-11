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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q1108 extends AppCompatActivity implements  Serializable {
protected HouseHold thisHouse;
protected RadioGroup rg;
protected RadioButton rbty, rbtn, selectedRbtn;
protected EditText txtq1108dd, txtq1108wks;
protected TextView q1108aques, t1108dd, t1108wks;
    protected CheckBox chkq1108;protected DatabaseHelper myDB;
    protected Individual individual;
    PersonRoster p1 = null;

protected  LinearLayout viewa;
protected LibraryClass lib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1108);
        Button btnnext = findViewById(R.id.btnNext);
        setTitle("Q1108:");

        lib = new LibraryClass();
        rg = findViewById(R.id.q1108radiogroup);
        rbty = findViewById(R.id.q1108_y);
        rbtn = findViewById(R.id.q1108_n);
        txtq1108dd = findViewById(R.id.txt1108dd);
        txtq1108wks = findViewById(R.id.txt1108wks);
        q1108aques = findViewById(R.id.txtq1108aq);
        viewa = findViewById(R.id.q1108alinear);
        chkq1108 = findViewById(R.id.q1108a_99);
        t1108dd = findViewById(R.id.dd);
        t1108wks = findViewById(R.id.tvwks);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }
        RadioButton[] bt = new RadioButton[3];
        for(int f=1;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ1108()!= null &&  !ind.getQ1108().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1108())==f)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        if(ind.getQ1108aDD()!= null)
        {
            txtq1108dd.setText(ind.getQ1107aDD());
        }


        if(ind.getQ1108aWks()!= null)
        {
            txtq1108wks.setText(ind.getQ1107aWks());
        }



        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q1108.this);
                    builder.setTitle("Q1108: Error select 'Yes/No'");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("In the past month, have you unexpectedly lost weight");
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


                    if ((((txtq1108dd.length() == 0 && txtq1108wks.length() == 0 && !chkq1108.isChecked()))) && (rbty.isChecked())) {
                        lib.showError(q1108.this, "Q1108:", "How long have you had the fever");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {


                        if (((txtq1108dd.getText().toString().equals("0") || txtq1108dd.getText().toString().equals("00")) &&
                                (txtq1108wks.getText().toString().equals("0") || txtq1108wks.getText().toString().equals("00")) && rbty.isChecked())) {
                            lib.showError(q1108.this, "Q1107", "a) Days and weeks can not be both 0/00");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {


                            if (rbtn.isChecked()) {
                                individual.setQ1108(selectedRbtn.getText().toString().substring(0, 1));
                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                myDB.close();
                                Intent intent = new Intent(q1108.this, q1109.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);
                            } else {

                                individual.setQ1108(selectedRbtn.getText().toString().substring(0, 1));


                                if (txtq1108dd.getText().toString().length() == 0) {
                                    individual.setQ1108aDD("00");
                                } else if (txtq1108dd.getText().toString().length() == 1) {
                                    individual.setQ1108aDD("0" + txtq1108dd.getText().toString());
                                } else {
                                    individual.setQ1108aDD(txtq1108dd.getText().toString());
                                }


                                if (txtq1108wks.getText().toString().length() == 0) {
                                    individual.setQ1108aWks("00");
                                } else if (txtq1108wks.getText().toString().length() == 1) {
                                    individual.setQ1108aWks("0" + txtq1108wks.getText().toString());
                                } else {
                                    individual.setQ1108aWks(txtq1108wks.getText().toString());
                                }

                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                                myDB.close();


                                Intent intent = new Intent(q1108.this, q1109.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);
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
                q1108.super.onBackPressed();
            }


        });
    }




    public void onRadioButtonClicked(View v) {


        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q1108_y:
                if (checked) {
                    q1108aques.setTextColor(Color.BLACK);
                    //viewa.setEnabled(true);
                    chkq1108.setEnabled(true);
                    txtq1108dd.setEnabled(true);
                    txtq1108wks.setEnabled(true);
                    t1108dd.setTextColor(Color.BLACK);
                            t1108wks.setTextColor(Color.BLACK);
                    //rb99.setVisibility(View.VISIBLE);
                }


                break;

            case R.id.q1108_n:
                if (checked) {


                    q1108aques.setTextColor(Color.LTGRAY);
                    txtq1108dd.setText("");
                    txtq1108wks.setText("");
                    txtq1108dd.setEnabled(false);
                    txtq1108wks.setEnabled(false);
                    //chkq1108.setVisibility(View.INVISIBLE);
                    chkq1108.setChecked(false);
                    chkq1108.setEnabled(false);
                    t1108dd.setTextColor(Color.LTGRAY);
                    t1108wks.setTextColor(Color.LTGRAY);
                    //rb99.setVisibility(View.INVISIBLE);
                }

                //txtdays.setVisibility(View.INVISIBLE);
                // txtweeks.setVisibility(View.INVISIBLE);


        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked

        if (checked) {
           // viewa.setEnabled(true);

            txtq1108dd.setEnabled(false);
            txtq1108wks.setEnabled(false);
            txtq1108dd.setTextColor(Color.LTGRAY);
            txtq1108wks.setTextColor(Color.LTGRAY);
            txtq1108dd.setText("99");
            txtq1108wks.setText("99");
            t1108dd.setTextColor(Color.LTGRAY);
            t1108wks.setTextColor(Color.LTGRAY);
        }
        // Put some meat on the sandwich
        else {
            // Remove the meat
            txtq1108dd.setEnabled(true);
            txtq1108wks.setEnabled(true);
            txtq1108dd.setText("");
            txtq1108wks.setText("");


        }
    }

}


