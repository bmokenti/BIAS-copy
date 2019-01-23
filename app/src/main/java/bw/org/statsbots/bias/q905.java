package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class q905 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected EditText edtdays, edtaother;
    protected RadioButton rbtna1, rbtna2, rbtna3, rbtna4, rbtna5, rbtna6, rbtnaother, selectedRbtna;
    protected RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q905);


        setTitle("Q905: HIV SUPPORT, CARE AND TREATMENT");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);

        edtdays = (EditText) findViewById(R.id.q905_days);

        ck1txt = (CheckBox) findViewById(R.id.q905_99);
        ck2txt = (CheckBox) findViewById(R.id.q905_00);

        rbtna1 = (RadioButton) findViewById(R.id.q905a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q905a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q905a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q905a_4);

        rbtna5 = (RadioButton) findViewById(R.id.q905a_5);
        rbtna6 = (RadioButton) findViewById(R.id.q905a_6);
        rbtnaother = (RadioButton) findViewById(R.id.q905a_other);
        edtaother = (EditText) findViewById(R.id.q905a_other1);


        rg = (RadioGroup) findViewById(R.id.q905rGroup);

        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;


        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!ck1txt.isChecked() && !ck2txt.isChecked() && ((edtdays.length() == 0 || Integer.valueOf(edtdays.getText().toString()) >= 30))) {

                    lib.showError(q905.this, "Q905: days", "Please input number of days or select Dont know or none: Number of days should be less than 30");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }
                else

                if (ck1txt.isChecked() && ck2txt.isChecked() ) {

                    lib.showError(q905.this, "Q905: ERROR", "Only one check box should be selected");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }

                else{
                    int selectedIda = rg.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedIda);


                    if (selectedRbtna == null) {
                        lib.showError(q905.this, "Q905a: year", "What are the main reasons that you forget or do not take your ARV pills every day?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else

                    if (edtaother.length() == 0 && rbtnaother.isChecked()) {

                        lib.showError(q905.this, "Q905a: ERROR", "Please specify");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else {

                        Intent intent = new Intent(q905.this, q1001.class);
                        //intent.putExtra("Household", thisHose);
                        startActivity(intent);
                    }

                }
            }


        });
    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.q905_99:
                if (checked) {
                    edtdays.setText("99");
                    edtdays.setEnabled(false);

                }
                // Put some meat on the sandwich
                else {
                    edtdays.setText("");
                    edtdays.setEnabled(true);
                    // Remove the meat
                }
                break;

            case R.id.q905_00:
                if (checked) {
                    edtdays.setText("00");
                    edtdays.setEnabled(false);

                    // Put some meat on the sandwich
                } else {
                    edtdays.setText("");
                    edtdays.setEnabled(true);
                }
                // Remove the meat
                break;
            default:
                break;


        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the view now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.q905a_other:
                if (checked) {

                    edtaother.setVisibility(View.VISIBLE);

                }
                // Put some meat on the sandwich
                else {
                    edtaother.setText("");
                    edtaother.setVisibility(View.INVISIBLE);
                    // Remove the meat
                }
                break;
        }
    }
}

/*
Intent intent = new Intent(q504.this, q1101.class);
                //intent.putExtra("Household", thisHose);
                startActivity(intent);
 */


