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

public class q619 extends AppCompatActivity {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt, ck3txt, ck4txt, ck5txt, ck6txt, ck7txt, ck8txt, ck10txt, ck11txt, ck12txt, ck13txt, ck14txt, ck15txt, chkOther;
    protected Button btn;
    protected EditText q619edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q619);


            setTitle("Q619: KNOWLEDGE ABOUT HIV/AIDS AND TB");
            lib = new LibraryClass();

//btn = findViewById(R.id.btn);


            ck1txt = findViewById(R.id.Q619_1);
            ck2txt = findViewById(R.id.Q619_2);
            ck3txt = findViewById(R.id.Q619_3);
            ck4txt = findViewById(R.id.Q619_4);
            ck5txt = findViewById(R.id.Q619_5);
            ck6txt = findViewById(R.id.Q619_6);
            ck7txt = findViewById(R.id.Q619_7);
            ck8txt = findViewById(R.id.Q619_8);
             ck10txt = findViewById(R.id.Q619_10);
             ck11txt = findViewById(R.id.Q619_11);
            ck12txt = findViewById(R.id.Q619_12);
            ck13txt = findViewById(R.id.Q619_13);
            ck14txt = findViewById(R.id.Q619_14);
            ck15txt = findViewById(R.id.Q619_9);
            chkOther = findViewById(R.id.Q619_Other);

        q619edt = findViewById(R.id.Q619edt_Other);


            Button btnnext = findViewById(R.id.button);
            btnnext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if ((((!ck1txt.isChecked() && !ck2txt.isChecked() && !ck3txt.isChecked() && !ck4txt.isChecked() && !ck5txt.isChecked() && !ck6txt.isChecked() && !ck7txt.isChecked() && !ck8txt.isChecked() && !ck10txt.isChecked() &&!ck11txt.isChecked()
                            &&!ck12txt.isChecked()&& !ck1txt.isChecked() && !ck14txt.isChecked() && !ck15txt.isChecked() && !chkOther.isChecked())))) {
                        lib.showError(q619.this, "Q619: ERROR:", "What are the signs and symptoms of TB?"
                                +"Please select atleast one checkbox");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else
                    if ((((chkOther.isChecked() )))) {
                       q619edt.setVisibility(View.VISIBLE);
                    }
                    else
                    if ((!chkOther.isChecked() )) {
                        q619edt.setVisibility(View.INVISIBLE);
                        q619edt.setText("");
                    }
                    else
                    if ((((chkOther.isChecked() && q619edt.length() ==0 )))) {
                        lib.showError(q619.this, "Q619:ERROR:", "Please specify other or uncheck Other specify");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }  else{
                        //thisHouse.getIndividual()[p1.getLineNumber()].setQ619_1(ck1txt.getText().toString().substring(0,1));
                        // thisHouse.getIndividual()[p1.getLineNumber()].setQ619_2(ck2txt.getText().toString().substring(0,1));
                        // thisHouse.getIndividual()[p1.getLineNumber()].setQ619_3(ck3txt.getText().toString().substring(0,1));
                        //  thisHouse.getIndividual()[p1.getLineNumber()].setQ619_4(ck4txt.getText().toString().substring(0,1));
                        //  thisHouse.getIndividual()[p1.getLineNumber()].setQ619_5(ck5txt.getText().toString().substring(0,1));
                        //  thisHouse.getIndividual()[p1.getLineNumber()].setQ619_6(ck6txt.getText().toString().substring(0,1));
                        // thisHouse.getIndividual()[p1.getLineNumber()].setQ619_7(ck7txt.getText().toString().substring(0,1));
                        // thisHouse.getIndividual()[p1.getLineNumber()].setQ619_8(ck8txt.getText().toString().substring(0,1));
                        // thisHouse.getIndividual()[p1.getLineNumber()].setQ619_10(ck10txt.getText().toString().substring(0,1));
                        //  thisHouse.getIndividual()[p1.getLineNumber()].setQ619_11(chk11txt.getText().toString().substring(0,1));
                        //  thisHouse.getIndividual()[p1.getLineNumber()].setQ619_12(ck12txt.getText().toString().substring(0,1));
                        //  thisHouse.getIndividual()[p1.getLineNumber()].setQ619_13(ck13txt.getText().toString().substring(0,1));
                        // thisHouse.getIndividual()[p1.getLineNumber()].setQ619_14(ck14txt.getText().toString().substring(0,1));
                        // thisHouse.getIndividual()[p1.getLineNumber()].setQ619_15(ck15txt.getText().toString().substring(0,1));
                        //  thisHouse.getIndividual()[p1.getLineNumber()].setQ619_Other(q619edt.getText().toString());


                        Intent intent = new Intent(q619.this, q620.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);
                    }

                }

            });
        }




    }
