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

public class q903 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtn8, rbtn9, rbtn10, rbtn11, rbtn12, rbtn13, rbtn14;
    protected RadioGroup rg1,  rg2, rg3, rg4, rg5, rg6, rg7;
    protected RadioButton selectedRbtn1, selectedRbtn2, selectedRbtn3, selectedRbtn4, selectedRbtn5, selectedRbtn6, selectedRbtn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q903);



        setTitle("Q903:  HIV SUPPORT, CARE ANF TREATMENT");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg1 = (RadioGroup)findViewById(R.id.q903aGroup1) ;
        rbtn1 = (RadioButton) findViewById(R.id.q903a_1);
        rbtn2 = (RadioButton) findViewById(R.id.q903a_2);

        rg2 = (RadioGroup)findViewById(R.id.q903bGroup2) ;
        rbtn2 = (RadioButton) findViewById(R.id.q903b_1);
        rbtn3 = (RadioButton) findViewById(R.id.q903b_2);

        rg3 = (RadioGroup)findViewById(R.id.q903cGroup) ;
        rbtn5 = (RadioButton) findViewById(R.id.q903c_1);
        rbtn6 = (RadioButton) findViewById(R.id.q903c_2);

        rg4 = (RadioGroup)findViewById(R.id.q903dGroup) ;
        rbtn7 = (RadioButton) findViewById(R.id.q903d_1);
        rbtn8 = (RadioButton) findViewById(R.id.q903d_2);

        rg5 = (RadioGroup)findViewById(R.id.q903eGroup) ;
        rbtn9 = (RadioButton) findViewById(R.id.q903e_1);
        rbtn10 = (RadioButton) findViewById(R.id.q903e_2);

        rg6 = (RadioGroup)findViewById(R.id.q903fGroup) ;
        rbtn11 = (RadioButton) findViewById(R.id.q903f_1);
        rbtn12 = (RadioButton) findViewById(R.id.q903f_2);

        rg7 = (RadioGroup)findViewById(R.id.q903gGroup) ;
        rbtn13 = (RadioButton) findViewById(R.id.q903g_1);
        rbtn14 = (RadioButton) findViewById(R.id.q903g_2);






        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        int p=0;


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg1.getCheckedRadioButtonId();
                selectedRbtn1 = (RadioButton) findViewById(selectedId);

                if (selectedRbtn1 == null) {
                    lib.showError(q903.this, "Q90a: month", "BY BEING DENIED CARE: please select yes/no");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }
                else {

                    int selectedId1 = rg2.getCheckedRadioButtonId();
                    selectedRbtn2 = (RadioButton) findViewById(selectedId1);

                    if (selectedRbtn2 == null) {
                        lib.showError(q903.this, "Q902b: year", "BY BEING THE SUBJECT OF GOSSIP: please select yes/no");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }

                    else {
                        int selectedId2 = rg3.getCheckedRadioButtonId();
                        selectedRbtn3 = (RadioButton) findViewById(selectedId2);


                        if (selectedRbtn3 == null) {
                            lib.showError(q903.this, "Q902c: month", "BY BEING ADVISED NOT TO HAVE SEX: please select yes/no");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {

                            int selectedId3 = rg4.getCheckedRadioButtonId();
                            selectedRbtn4 = (RadioButton) findViewById(selectedId3);

                            if (selectedRbtn4 == null) {
                                lib.showError(q903.this, "Q902d: year", "BY BEING VERBALLY ABUSED");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {
                                int selectedId4 = rg5.getCheckedRadioButtonId();
                                selectedRbtn5 = (RadioButton) findViewById(selectedId4);

                                if (selectedRbtn5 == null) {
                                    lib.showError(q903.this, "Q902e: month", "BY BEING PHYSICALLY ABUSED: please select yes/no");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);
                                } else {

                                    int selectedId5 = rg6.getCheckedRadioButtonId();
                                    selectedRbtn6 = (RadioButton) findViewById(selectedId5);

                                    if (selectedRbtn6 == null) {
                                        lib.showError(q903.this, "Q902f: year", "THROUGH PEOPLE AVOIDING PHYSICAL CONTACT WITH YOU");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);
                                    } else {
                                        int selectedId6 = rg7.getCheckedRadioButtonId();
                                        selectedRbtn7 = (RadioButton) findViewById(selectedId6);


                                        if (selectedRbtn7 == null) {
                                            lib.showError(q903.this, "Q902g: month", "THROUGH SHARING OF HIV STATUS WITHOUT CONSENT");
                                            /**
                                             * VIBRATE DEVICE
                                             */
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);
                                        }
                                        else {

                                            Intent intent = new Intent(q903.this, q904.class);
                                            //intent.putExtra("Household", thisHose);
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




}

/*
Intent intent = new Intent(q504.this, q1101.class);
                //intent.putExtra("Household", thisHose);
                startActivity(intent);
 */


