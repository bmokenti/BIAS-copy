package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class q604 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt, ck3txt, ck4txt, ck5txt, ck6txt, ck7txt, ck8txt, ck10txt, ck11txt, ck12txt, ck13txt, ck14txt, ck15txt, chkOther;
    protected Button btn;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, selectedRbtn, selectedRbtna;
    protected RadioGroup rg, rga;
    protected EditText q604edt;
    protected TextView t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q604);


        setTitle("Q604: KNOWLEDGE ABOUT HIV/AIDS AND TB");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        rbtn1 = findViewById(R.id.q604_1);
        rbtn2 = findViewById(R.id.q604_2);

        rbtna1 = findViewById(R.id.q604a_1);
        rbtna2 = findViewById(R.id.q604a_2);
        rg = findViewById(R.id.q604radioGroup);
        rga = findViewById(R.id.q604aGroup1);

        ck1txt = findViewById(R.id.q604b_1);
        ck2txt = findViewById(R.id.q604b_2);
        ck3txt = findViewById(R.id.q604b_3);
        ck4txt = findViewById(R.id.q604b_4);
        ck5txt = findViewById(R.id.q604b_5);
        ck6txt = findViewById(R.id.q604b_6);
        ck7txt = findViewById(R.id.q604b_7);
        ck8txt = findViewById(R.id.q604b_8);
        ck10txt = findViewById(R.id.q604b_10);

        ck11txt = findViewById(R.id.q604b_11);
        ck12txt = findViewById(R.id.q604b_12);
        ck13txt = findViewById(R.id.q604b_13);
        ck14txt = findViewById(R.id.q604b_14);
        ck15txt = findViewById(R.id.q604b_15);


        chkOther = findViewById(R.id.q604b_Other);
        q604edt = findViewById(R.id.q604b_othertxt);
        t1 = findViewById(R.id.q604a);
        t2 = findViewById(R.id.q604b);


        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);
                if (selectedRbtn == null) {
                    lib.showError(q604.this, "Q604:", "Have you EVER heard of TB?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    int selectedIda = rga.getCheckedRadioButtonId();
                    selectedRbtna = (RadioButton) findViewById(selectedIda);

                    if (selectedRbtna == null && rbtn1.isChecked()) {
                        lib.showError(q604.this, "Q604:", "In the past 4 weeks, have you heard or seen any information about TB?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        if (selectedRbtna == rbtn2) {
                            Intent intent = new Intent(q604.this, q606.class);
                            //intent.putExtra("Household", thisHose);
                            startActivity(intent);
                        } else {
                            if (!ck1txt.isChecked() && !ck2txt.isChecked() && !ck3txt.isChecked() && !ck4txt.isChecked() && !ck5txt.isChecked() && !ck6txt.isChecked() && !ck7txt.isChecked() && !ck8txt.isChecked() && !ck10txt.isChecked() && !ck11txt.isChecked() && !ck12txt.isChecked()
                                    && !ck13txt.isChecked() && !ck14txt.isChecked() && !ck14txt.isChecked() && !chkOther.isChecked() && rbtn1.isChecked() && rbtna1.isChecked()) {
                                lib.showError(q604.this, "Q604:", "From what source(s) did you receive information about TB?"
                                        + "Please select atleast one checkbox");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {
                                if ((((chkOther.isChecked() && q604edt.length() == 0)))) {
                                    lib.showError(q604.this, "Q604:", "Please specify other or uncheck Other specify");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);
                                } else {
                                    if (rbtn2.isChecked()) {

                                        individual.setQ604(selectedRbtn.getText().toString().substring(0, 1));

                                        Intent intent1 = new Intent(q604.this, q606.class);
                                        intent1.putExtra("Individual", individual);
                                        startActivity(intent1);

                                    } else {
                                        if (rbtna2.isChecked()) {
                                            individual.setQ604(selectedRbtn.getText().toString().substring(0, 1));
                                            individual.setQ604a(selectedRbtna.getText().toString().substring(0, 1));

                                            Intent intent1 = new Intent(q604.this, q605.class);
                                            intent1.putExtra("Individual", individual);
                                            startActivity(intent1);

                                        } else {
                                            individual.setQ604(selectedRbtn.getText().toString().substring(0, 1));
                                            individual.setQ604a(selectedRbtna.getText().toString().substring(0, 1));
                                            individual.setQ604b_1(ck1txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_2(ck2txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_3(ck3txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_4(ck4txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_5(ck5txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_6(ck6txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_7(ck7txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_8(ck8txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_10(ck10txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_11(ck11txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_12(ck12txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_13(ck13txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_14(ck14txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_15(ck15txt.getText().toString().substring(0, 1));
                                            individual.setQ604b_Other(chkOther.getText().toString().substring(0, 1));
                                            individual.setQ604b_Otherspecify(q604edt.getText().toString());


                                            Intent intent = new Intent(q604.this, q605.class);
                                            intent.putExtra("Individual", individual);
                                            startActivity(intent);
                                        }
                                    }
                                }

                            }
                        }
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
            case R.id.q604_1:
                if (checked)
                    // Remove the meat
                   break;

            case R.id.q604_2:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q604b_3:
                if (checked)
                // Put some meat on the sandwich

                    // Remove the meat
                    break;
            case R.id.q604b_4:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q604b_5:
                if (checked)
                    // Remove the meat
                    break;

            case R.id.q604b_6:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q604b_7:
                if (checked)

                        break;
                    case R.id.q604b_8:
                        if (checked)
                            // Remove the meat
                            break;
                    case R.id.q604b_10:
                        if (checked)
                            // Remove the meat
                            break;
                    case R.id.q604b_11:
                        if (checked)
                            // Remove the meat
                            break;

                    case R.id.q604b_12:
                        if (checked)
                            // Remove the meat
                            break;
                    case R.id.q604b_13:
                        if (checked)
                            // Remove the meat
                            break;

                    case R.id.q604b_14:
                        if (checked)
                            // Remove the meat
                            break;
                    case R.id.q604b_15:
                        if (checked)
                            // Remove the meat
                            break;
                    case R.id.q604b_Other:
                        if (checked)
                                q604edt.setVisibility(View.VISIBLE);


                            // Put some meat on the sandwich
                            else
                                // Remove the meat
                                q604edt.setVisibility(View.INVISIBLE);
                                q604edt.setText("");

                            break;


                        }
                }

    public void onRadioButtonClicked(View view) {
        // Is the view now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.q604_1:
                if (checked)
                    rbtna1.setEnabled(true);
                    rbtna2.setEnabled(true);
                    t1.setTextColor(Color.BLACK);
                    t2.setTextColor(Color.BLACK);

                    ck1txt.setEnabled(true);
                    ck2txt.setEnabled(true);
                    ck3txt.setEnabled(true);
                    ck4txt.setEnabled(true);
                    ck5txt.setEnabled(true);
                    ck6txt.setEnabled(true);
                    ck7txt.setEnabled(true);
                    ck8txt.setEnabled(true);
                    ck10txt.setEnabled(true);

                    ck11txt.setEnabled(true);
                    ck12txt.setEnabled(true);
                    ck13txt.setEnabled(true);
                    ck14txt.setEnabled(true);
                    ck15txt.setEnabled(true);
                    chkOther.setEnabled(true);

                    // Remove the meat
                    break;

            case R.id.q604_2:
                if (checked)

                    rbtna1.setEnabled(false);
                    rbtna2.setEnabled(false);

                rbtna1.setChecked(false);
                rbtna2.setChecked(false);

                    t1.setTextColor(Color.LTGRAY);
                    t2.setTextColor(Color.LTGRAY);

                    ck1txt.setEnabled(false);
                    ck2txt.setEnabled(false);
                    ck3txt.setEnabled(false);
                    ck4txt.setEnabled(false);
                    ck5txt.setEnabled(false);
                    ck6txt.setEnabled(false);
                    ck7txt.setEnabled(false);
                    ck8txt.setEnabled(false);
                    ck10txt.setEnabled(false);

                    ck11txt.setEnabled(false);
                    ck12txt.setEnabled(false);
                    ck13txt.setEnabled(false);
                    ck14txt.setEnabled(false);
                    ck15txt.setEnabled(false);
                    chkOther.setEnabled(false);

                ck1txt.setChecked(false);
                ck2txt.setChecked(false);
                ck3txt.setChecked(false);
                ck4txt.setChecked(false);
                ck5txt.setChecked(false);
                ck6txt.setChecked(false);
                ck7txt.setChecked(false);
                ck8txt.setChecked(false);
                ck10txt.setChecked(false);

                ck11txt.setChecked(false);
                ck12txt.setChecked(false);
                ck13txt.setChecked(false);
                ck14txt.setChecked(false);
                ck15txt.setChecked(false);
                chkOther.setChecked(false);
                q604edt.setText("");


                    break;
            case R.id.q604a_1:
                if (checked)
                    ck1txt.setEnabled(true);
                    ck2txt.setEnabled(true);
                    ck3txt.setEnabled(true);
                    ck4txt.setEnabled(true);
                    ck5txt.setEnabled(true);
                    ck6txt.setEnabled(true);
                    ck7txt.setEnabled(true);
                    ck8txt.setEnabled(true);
                    ck10txt.setEnabled(true);

                    ck11txt.setEnabled(true);
                    ck12txt.setEnabled(true);
                    ck13txt.setEnabled(true);
                    ck14txt.setEnabled(true);
                    ck15txt.setEnabled(true);
                    chkOther.setEnabled(true);

                    t2.setTextColor(Color.BLACK);

                    // Remove the meat
                    break;

            case R.id.q604a_2:
                if (checked)
                    ck1txt.setEnabled(false);
                    ck2txt.setEnabled(false);
                    ck3txt.setEnabled(false);
                    ck4txt.setEnabled(false);
                    ck5txt.setEnabled(false);
                    ck6txt.setEnabled(false);
                    ck7txt.setEnabled(false);
                    ck8txt.setEnabled(false);
                    ck10txt.setEnabled(false);

                    ck11txt.setEnabled(false);
                    ck12txt.setEnabled(false);
                    ck13txt.setEnabled(false);
                    ck14txt.setEnabled(false);
                    ck15txt.setEnabled(false);
                    chkOther.setEnabled(false);
                    t2.setTextColor(Color.LTGRAY);
                ck1txt.setChecked(false);
                ck2txt.setChecked(false);
                ck3txt.setChecked(false);
                ck4txt.setChecked(false);
                ck5txt.setChecked(false);
                ck6txt.setChecked(false);
                ck7txt.setChecked(false);
                ck8txt.setChecked(false);
                ck10txt.setChecked(false);

                ck11txt.setChecked(false);
                ck12txt.setChecked(false);
                ck13txt.setChecked(false);
                ck14txt.setChecked(false);
                ck15txt.setChecked(false);
                chkOther.setChecked(false);
                q604edt.setText("");



                    // Remove the meat
                    break;

                }
        }
    }


