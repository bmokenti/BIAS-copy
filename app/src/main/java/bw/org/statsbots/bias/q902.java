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
import android.widget.RadioGroup;

public class q902 extends AppCompatActivity {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected EditText edtmnths, edtyear;
    protected RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q902);


        setTitle("Q902:  HIV SUPPORT, CARE ANF TREATMENT");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        edtmnths = (EditText) findViewById(R.id.q902_months);
        edtyear = (EditText) findViewById(R.id.q902_year);
        ck1txt = findViewById(R.id.q902_99);
        ck2txt = findViewById(R.id.q902_9999);

        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        int p=0;


        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!ck1txt.isChecked() && ((edtmnths.length() == 0 || Integer.valueOf(edtmnths.getText().toString()) >= 13))) {

                    lib.showError(q902.this, "Q902: month", "Please input month or select Dont know months: months should be 1-12");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {


                    if (!ck2txt.isChecked() && ((edtyear.length() == 0 || Integer.valueOf(edtyear.getText().toString()) <= 1988 ||Integer.valueOf(edtyear.getText().toString()) >= 2020))) {
                        lib.showError(q902.this, "Q902: year", "Please input year or select Dont know Year: Year shoud be btween 1988 and 2020");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                            else {
                        //thisHouse.getIndividual()[p1.getLineNumber()].setQ902Month(edtmnths.getText().toString().substring(0,1));
                        //thisHouse.getIndividual()[p1.getLineNumber()].setQ902Year(edtyear.getText().toString().substring(0,1));


                                Intent intent = new Intent(q902.this, q903.class);
                                intent.putExtra("Household", thisHouse);
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
            case R.id.q902_99:
                if (checked) {
                    edtmnths.setText("99");
                    edtmnths.setEnabled(false);

                }
                // Put some meat on the sandwich
                else {
                    edtmnths.setText("");
                    edtmnths.setEnabled(true);
                    // Remove the meat
                }
                    break;

            case R.id.q902_9999:
                if (checked) {
                    edtyear.setText("9999");
                    edtyear.setEnabled(false);

                        // Put some meat on the sandwich
                    }
                else
                {
                edtyear.setText("");
                edtyear.setEnabled(true);
            }
                    // Remove the meat
                    break;
                    default:
                        break;


                }
        }
    }

/*
Intent intent = new Intent(q504.this, q1101.class);
                //intent.putExtra("Household", thisHose);
                startActivity(intent);
 */


