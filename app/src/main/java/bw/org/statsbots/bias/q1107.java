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

public class q1107 extends AppCompatActivity implements  Serializable {

    protected RadioButton rb1, rb2, rb3, selectedRbtn;
    protected RadioGroup rg1;
    protected EditText txtweeks, txtdays;
    protected TextView txtq1107aq, txt1107dd, txt1107wks;
    protected LinearLayout viewa;
    protected Individual individual;
    PersonRoster p1 = null;
    protected  HouseHold thisHouse;
    protected CheckBox chk1107a, selectedchk;
    protected LibraryClass lib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1107);
        Button btnnext = findViewById(R.id.btnNext);
        setTitle("Q1107:");

        rg1 = findViewById(R.id.q1107radiogroup);
        rb1 = findViewById(R.id.q1107_y);
        rb2 = findViewById(R.id.q1107_n);
        txtdays = findViewById(R.id.txtdd);
        txtweeks = findViewById(R.id.txtwks);
        txtq1107aq = findViewById(R.id.q1107aques);
        viewa = findViewById(R.id.q1107alinear);
        chk1107a = findViewById(R.id.q1107a_99);
        lib = new LibraryClass();
        txt1107dd = findViewById(R.id.dd);
        txt1107wks = findViewById(R.id.tvwks);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg1.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q1107.this);
                    builder.setTitle("Q1107 Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Do you currently have a fever");
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


                    if ((((txtdays.length() == 0 && txtweeks.length() == 0 && !chk1107a.isChecked()))) && (rb1.isChecked())) {
                        lib.showError(q1107.this, "Q1107:", "How long have you had the fever");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                        else {
                        individual.setQ1107(selectedRbtn.getText().toString().substring(0,1));
                      individual.setQ1107aDD(txtdays.getText().toString());
                        individual.setQ1107aWks(txtweeks.getText().toString());

                            Intent intent = new Intent(q1107.this, q1108.class);
                        intent.putExtra("Individual", individual);
                            startActivity(intent);
                        }
                    }
                }




        });
    }


    public void onRadioButtonClicked(View v) {


        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q1107_y:
                if (checked) {
                    txtq1107aq.setTextColor(Color.BLACK);
                    txtweeks.setEnabled(true);
                    txtdays.setEnabled(true);
                    chk1107a.setEnabled(true);
                   // viewa.setVisibility(View.VISIBLE);

                    txt1107dd.setTextColor(Color.BLACK);
                            txt1107wks.setTextColor(Color.BLACK);


                    //rb99.setVisibility(View.VISIBLE);
                }


                break;

            case R.id.q1107_n:
                if (checked) {

                    txtq1107aq.setTextColor(Color.LTGRAY);
                    txtweeks.setEnabled(false);
                    txtdays.setEnabled(false);
                    chk1107a.setEnabled(false);
                    // viewa.setVisibility(View.VISIBLE);
                    txtweeks.setText("");
                    txtdays.setText("");
                    txt1107dd.setTextColor(Color.LTGRAY);
                    txt1107wks.setTextColor(Color.LTGRAY);
                    chk1107a.setChecked(false);
                    //rb99.setVisibility(View.INVISIBLE);
                }
                break;





        }
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked

        if (checked) {
            txtq1107aq.setTextColor(Color.BLACK);
            txtweeks.setEnabled(false);
            txtdays.setEnabled(false);
            txtweeks.setText("");
            txtdays.setText("");


            // viewa.setVisibility(View.VISIBLE);
            txt1107dd.setTextColor(Color.LTGRAY);
            txt1107wks.setTextColor(Color.LTGRAY);
        }
        // Put some meat on the sandwich
        else {
            // Remove the meat
           // txtq1107aq.setTextColor(Color.BLACK);
            txtweeks.setEnabled(true);
            txtdays.setEnabled(true);
           // txtweeks.setText("");
           // txtdays.setText("");


            // viewa.setVisibility(View.VISIBLE);
            txt1107dd.setTextColor(Color.BLACK);
            txt1107wks.setTextColor(Color.BLACK);

            // TODO: Veggie sandwich
        }
    }

}



