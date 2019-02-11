package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class q617 extends AppCompatActivity {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    private EditText edtOther;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtn8, rbtn9, rbtn10, rbtn11, rbtn12, rbtn13, rbtn14, rbtn15, rbtn16, rbtn17, rbtn18, rbtn19, rbtn20, rbtn21, rbtn22, rbtn23, rbtn24, rbtnOther;
    protected RadioGroup  rg1, rg2, rg3, rg4, rg5, rg6, rg7, rg8;
    protected RadioButton selected1, selected2, selected3, selected4, selected5, selected6, selected7, selected8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q617);



        rg1 = (RadioGroup) findViewById(R.id.rg1) ;
        rg2 = (RadioGroup) findViewById(R.id.rg2) ;
        rg3 = (RadioGroup) findViewById(R.id.rg3) ;
        rg4 = (RadioGroup) findViewById(R.id.rg4) ;
        rg5 = (RadioGroup) findViewById(R.id.rg5) ;
        rg6 = (RadioGroup) findViewById(R.id.rg6) ;
        rg7 = (RadioGroup) findViewById(R.id.rg7) ;


        rbtn1 = (RadioButton) findViewById(R.id.rg1_01) ;
        rbtn2 = (RadioButton) findViewById(R.id.rg1_02) ;
        rbtn3 = (RadioButton) findViewById(R.id.rg1_03) ;

        rbtn4 = (RadioButton) findViewById(R.id.rg2_1) ;
        rbtn5 = (RadioButton) findViewById(R.id.rg2_2) ;
        rbtn6 = (RadioButton) findViewById(R.id.rg2_3) ;

        rbtn7 = (RadioButton) findViewById(R.id.rg3_1) ;
        rbtn8 = (RadioButton) findViewById(R.id.rg3_2) ;
        rbtn9 = (RadioButton) findViewById(R.id.rg3_3) ;

        rbtn10 = (RadioButton) findViewById(R.id.rg4_1) ;
        rbtn11 = (RadioButton) findViewById(R.id.rg4_2) ;
        rbtn12 = (RadioButton) findViewById(R.id.rg4_3) ;

        rbtn13 = (RadioButton) findViewById(R.id.rg5_1) ;
        rbtn14 = (RadioButton) findViewById(R.id.rg5_2) ;
        rbtn15 = (RadioButton) findViewById(R.id.rg5_3) ;

        rbtn16 = (RadioButton) findViewById(R.id.rg6_1) ;
        rbtn17 = (RadioButton) findViewById(R.id.rg6_2) ;
        rbtn18 = (RadioButton) findViewById(R.id.rg6_3) ;

        rbtn19 = (RadioButton) findViewById(R.id.rg7_1) ;
        rbtn20 = (RadioButton) findViewById(R.id.rg7_2) ;
        rbtn21 = (RadioButton) findViewById(R.id.rg7_3) ;

        rbtn22 = (RadioButton) findViewById(R.id.rg8_1) ;
        rbtn23 = (RadioButton) findViewById(R.id.rg8_2) ;
        rbtn24 = (RadioButton) findViewById(R.id.rg8_3) ;
        rbtnOther = (RadioButton) findViewById(R.id.rgOther) ;

        edtOther = (EditText) findViewById(R.id.Othertxt) ;


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg1.getCheckedRadioButtonId();
                selected1 = (RadioButton) findViewById(selectedId);

                if (selected1 == null) {
                    lib.showError(q617.this, "Q617: ERROR", "SHARING A MEAL WITH A PERSON WHO HAS TB");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    int selectedId2 = rg2.getCheckedRadioButtonId();
                    selected2 = (RadioButton) findViewById(selectedId2);

                    if (selected2 == null) {
                        lib.showError(q617.this, "Q617: ERROR", "SHARING CLOTHES WITH A PERSON WHO HAS TB");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    } else {
                        int selectedId3 = rg3.getCheckedRadioButtonId();
                        selected3 = (RadioButton) findViewById(selectedId3);
                    }
                    if (selected3 == null) {
                        lib.showError(q617.this, "Q617: ERROR", "SEX WITH A WOMAN WHO MISCARRIED");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        int selectedId4 = rg4.getCheckedRadioButtonId();
                        selected4 = (RadioButton) findViewById(selectedId4);

                        if (selected4 == null) {
                            lib.showError(q617.this, "Q617: ERROR", "SEX WITH A WIDOW/ER");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);

                        } else {
                            int selectedId5 = rg5.getCheckedRadioButtonId();
                            selected5 = (RadioButton) findViewById(selectedId5);

                            if (selected5 == null) {
                                lib.showError(q617.this, "Q617: ERROR", "BORN IN A FAMILY WITH HIV");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);

                            } else {
                                int selectedId6 = rg6.getCheckedRadioButtonId();
                                selected6 = (RadioButton) findViewById(selectedId6);

                            if (selected6 == null) {
                                lib.showError(q617.this, "Q617: ERROR", " SEJESO ");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {
                                int selectedId7 = rg7.getCheckedRadioButtonId();
                                selected7 = (RadioButton) findViewById(selectedId7);

                                if (selected7 == null) {
                                    lib.showError(q617.this, "Q617: ERROR", "TOUCHING ITEMS IN PUBLIC PLACES");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);

                                } else {
                                    int selectedId8 = rg8.getCheckedRadioButtonId();
                                    selected8 = (RadioButton) findViewById(selectedId8);

                                    if (selected8 == null) {
                                        lib.showError(q617.this, "Q617: ERROR", "BEING IN CONTACT WITH SOMEONE WITH TB");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);

                                    }
                                    if (rbtnOther.isChecked() && edtOther.length() == 0) {
                                        lib.showError(q617.this, "Q617: ERROR: Other", "Other specify");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);

                                    } else {
                                        //thisHouse.getIndividual()[p1.getLineNumber()].setQ617a(selected1.getText().toString().substring(0,1));
                                        // thisHouse.getIndividual()[p1.getLineNumber()].setQ617b(selected2.getText().toString().substring(0,1));
                                        // thisHouse.getIndividual()[p1.getLineNumber()].setQ617c(selected3.getText().toString().substring(0,1));
                                        // thisHouse.getIndividual()[p1.getLineNumber()].setQ617d(selected4.getText().toString().substring(0,1));
                                        // thisHouse.getIndividual()[p1.getLineNumber()].setQ617f(selected6.getText().toString().substring(0,1));
                                        // thisHouse.getIndividual()[p1.getLineNumber()].setQ617g(selected7.getText().toString().substring(0,1));
                                        //thisHouse.getIndividual()[p1.getLineNumber()].setQ617h(selected8.getText().toString().substring(0,1));
                                        //thisHouse.getIndividual()[p1.getLineNumber()].setQ617_0ther(edtOther.getText().toString());







                                        Intent intent = new Intent(q617.this, q618.class);
                                        intent.putExtra("Household", thisHouse);
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
