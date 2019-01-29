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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class q410 extends AppCompatActivity {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtn8, rbtn9, rbtn10, rbtn11, rbtn12, rbtn13, rbtn14;
    protected RadioGroup  rg1, rg2, rg3, rg4, rg5, rg6, rg7;
    protected RadioButton selected1, selected2, selected3, selected4, selected5, selected6, selected7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q410);


        setTitle("Q410: SEXUAL HISTORY");
        lib = new LibraryClass();

        rg1 = (RadioGroup) findViewById(R.id.rg1) ;
        rg2 = (RadioGroup) findViewById(R.id.rg2) ;
        rg3 = (RadioGroup) findViewById(R.id.rg3) ;
        rg4 = (RadioGroup) findViewById(R.id.rg4) ;
        rg5 = (RadioGroup) findViewById(R.id.rg5) ;
        rg6 = (RadioGroup) findViewById(R.id.rg6) ;
        rg7 = (RadioGroup) findViewById(R.id.rg7) ;


        rbtn1 = (RadioButton) findViewById(R.id.rg1_01) ;
        rbtn2 = (RadioButton) findViewById(R.id.rg1_02) ;


        rbtn3 = (RadioButton) findViewById(R.id.rg2_1) ;
        rbtn4 = (RadioButton) findViewById(R.id.rg2_2) ;

        rbtn5 = (RadioButton) findViewById(R.id.rg3_1) ;
        rbtn6 = (RadioButton) findViewById(R.id.rg3_2) ;


        rbtn7 = (RadioButton) findViewById(R.id.rg4_1) ;
        rbtn8 = (RadioButton) findViewById(R.id.rg4_2) ;


        rbtn9 = (RadioButton) findViewById(R.id.rg5_1) ;
        rbtn10 = (RadioButton) findViewById(R.id.rg5_2) ;


        rbtn11 = (RadioButton) findViewById(R.id.rg6_1) ;
        rbtn12 = (RadioButton) findViewById(R.id.rg6_2) ;


        rbtn13 = (RadioButton) findViewById(R.id.rg7_1) ;
        rbtn14 = (RadioButton) findViewById(R.id.rg7_2) ;


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;






        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg1.getCheckedRadioButtonId();
                selected1 = (RadioButton) findViewById(selectedId);

                if (selected1 == null) {
                    lib.showError(q410.this, "Q410: ERROR", "Slapped or threw something that could hurt you");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    int selectedId2 = rg2.getCheckedRadioButtonId();
                    selected2 = (RadioButton) findViewById(selectedId2);

                    if (selected2 == null) {
                        lib.showError(q410.this, "Q410: ERROR", "Pushed or shoved you");
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
                        lib.showError(q410.this, "Q410: ERROR", "Chocked or burned you");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        int selectedId4 = rg4.getCheckedRadioButtonId();
                        selected4 = (RadioButton) findViewById(selectedId4);

                        if (selected4 == null) {
                            lib.showError(q410.this, "Q410: ERROR", "Threatened or used a gun, knife or other weapon against you");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);

                        } else {
                            int selectedId5 = rg5.getCheckedRadioButtonId();
                            selected5 = (RadioButton) findViewById(selectedId5);

                            if (selected5 == null) {
                                lib.showError(q410.this, "Q410: ERROR", "Physically forced you to have sexual intercourse against your will");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);

                            } else {
                                int selectedId6 = rg6.getCheckedRadioButtonId();
                                selected6 = (RadioButton) findViewById(selectedId6);
                            }
                            if (selected6 == null) {
                                lib.showError(q410.this, "Q410: ERROR", " Forced you to do something sexual you found degrading or humiliating\t  YES ");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {
                                int selectedId7 = rg7.getCheckedRadioButtonId();
                                selected7 = (RadioButton) findViewById(selectedId7);

                                if (selected7 == null) {
                                    lib.showError(q410.this, "Q410: ERROR", "Made you afraid of what would happen if you did not have sexual intercourse");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);

                                } else {
                                     individual.setQ410Slapped(selected1.getText().toString().substring(0,1));
                                    individual.setQ410Pushed(selected2.getText().toString().substring(0,1));
                                     individual.setQ410Choked(selected3.getText().toString().substring(0,1));
                                   individual.setQ410Threatened(selected4.getText().toString().substring(0,1));
                                    individual.setQ410Physical(selected5.getText().toString().substring(0,1));
                                   individual.setQ410Forced(selected6.getText().toString().substring(0,1));
                                    individual.setQ410MadeAfraid(selected7.getText().toString().substring(0,1));


                                    Intent intent = new Intent(q410.this, q501.class);
                                    intent.putExtra("Individual", individual);
                                    startActivity(intent);
                                }
                            }


                        }
                    }
                }
            }



        });
    }




}
