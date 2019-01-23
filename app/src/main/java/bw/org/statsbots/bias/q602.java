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

import java.io.Serializable;

public class q602 extends AppCompatActivity implements Serializable{

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt, ck3txt, ck4txt, ck5txt, ck6txt, ck7txt, ck8txt, ck10txt,ck11txt, ck12txt, ck13txt, ck14txt, ck15txt,chkOther, selected = null;
    protected Button btn;
    protected EditText Q602edt;
    HouseHold thisHose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q602);


        setTitle("Q602: KNOWLEDGE ABOUT HIV/AIDS AND TB");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);

        ck1txt = findViewById(R.id.q602_1);
        ck2txt = findViewById(R.id.q602_2);
        ck3txt = findViewById(R.id.q602_3);
        ck4txt = findViewById(R.id.q602_4);
        ck5txt = findViewById(R.id.q602_5);
        ck6txt = findViewById(R.id.q602_6);
        ck7txt = findViewById(R.id.q602_7);
        ck8txt = findViewById(R.id.q602_8);
        ck10txt = findViewById(R.id.q602_10);

        ck11txt = findViewById(R.id.q602_11);
        ck12txt = findViewById(R.id.q602_12);
        ck13txt = findViewById(R.id.q602_13);
        ck14txt = findViewById(R.id.q602_14);
        ck15txt = findViewById(R.id.q602_15);

        chkOther = findViewById(R.id.q602_Other);
        Q602edt = findViewById(R.id.q602edt_Other);


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((((!ck1txt.isChecked() && !ck2txt.isChecked() && !ck3txt.isChecked() && !ck4txt.isChecked() && !ck5txt.isChecked() && !ck6txt.isChecked() && !ck7txt.isChecked() && !ck8txt.isChecked() && !ck10txt.isChecked() && !ck11txt.isChecked() && !ck12txt.isChecked() && !ck13txt.isChecked() && !ck14txt.isChecked() && !ck15txt.isChecked() && !chkOther.isChecked())))) {
                    lib.showError(q602.this, "Q602:", "From what source(s) did you receive information about HIV and AIDS?" +
                            "Please select atleast one checkbox");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    if ((((chkOther.isChecked() && Q602edt.length() == 0)))) {
                        lib.showError(q602.this, "Q602:", "Please specify other or uncheck Other specify");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        Intent intent = new Intent(q602.this, q603.class);
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
            case R.id.q602_1:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;

            case R.id.q602_2:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_3:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_4:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_5:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_6:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_7:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_8:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_10:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_Other:
                if (checked) {
                    if (chkOther.isChecked()) {
                        Q602edt.setVisibility(View.VISIBLE);
                    }

                    // Put some meat on the sandwich
                    else {
                        // Remove the meat
                        Q602edt.setVisibility(View.INVISIBLE);
                    }
                    break;


                }
        }
    }
}