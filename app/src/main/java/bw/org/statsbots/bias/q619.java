package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.io.Serializable;

public class q619 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected  Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt, ck3txt, ck4txt, ck5txt, ck6txt, ck7txt, ck8txt, ck10txt, ck11txt, ck12txt, ck13txt, ck14txt, ck9txt, chkOther;
    protected Button btn;
    protected EditText q619edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q619);


            setTitle("Q619: KNOWLEDGE ABOUT HIV/AIDS AND TB");
            lib = new LibraryClass();

//btn = findViewById(R.id.btn);


            ck1txt = (CheckBox) findViewById(R.id.Q619_1);
            ck2txt = (CheckBox) findViewById(R.id.Q619_2);
            ck3txt =(CheckBox)  findViewById(R.id.Q619_3);
            ck4txt = (CheckBox) findViewById(R.id.Q619_4);
            ck5txt =(CheckBox)  findViewById(R.id.Q619_5);
            ck6txt = (CheckBox) findViewById(R.id.Q619_6);
            ck7txt = (CheckBox) findViewById(R.id.Q619_7);
            ck8txt =(CheckBox)  findViewById(R.id.Q619_8);
             ck10txt =(CheckBox)  findViewById(R.id.Q619_10);
             ck11txt =(CheckBox)  findViewById(R.id.Q619_11);
            ck12txt = (CheckBox) findViewById(R.id.Q619_12);
            ck13txt =(CheckBox)  findViewById(R.id.Q619_13);
            ck14txt =(CheckBox)  findViewById(R.id.Q619_14);
            ck9txt = (CheckBox) findViewById(R.id.Q619_9);
            chkOther = (CheckBox)  findViewById(R.id.Q619_Other);

        q619edt =(EditText)  findViewById(R.id.Q619edt_Other);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;


        chkOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chkOther.isChecked()) {
                    q619edt.setVisibility(View.VISIBLE);
                }
                else
                {
                    q619edt.setVisibility(View.INVISIBLE);
                    q619edt.setText("");
                }

            }
        });

        ck9txt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ck9txt.isChecked()) {
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
                    chkOther.setEnabled(false);
                    q619edt.setVisibility(View.INVISIBLE);

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
                    chkOther.setChecked(false);
                    q619edt.setText("");
                }
                else
                {
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
                    chkOther.setEnabled(true);
                }

            }
        });
        Button btnNext = (Button) findViewById(R.id.button);
        btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if ((((!ck1txt.isChecked() && !ck2txt.isChecked() && !ck3txt.isChecked() && !ck4txt.isChecked() && !ck5txt.isChecked() && !ck6txt.isChecked() && !ck7txt.isChecked() && !ck8txt.isChecked() && !ck10txt.isChecked() && !ck11txt.isChecked()
                            && !ck12txt.isChecked() && !ck13txt.isChecked() && !ck14txt.isChecked() && !ck9txt.isChecked() && !chkOther.isChecked()))))
                    {
                        lib.showError(q619.this, "Q619: ERROR:", "What are the signs and symptoms of TB?"
                                + "Please select atleast one checkbox");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else {
                        if ((((chkOther.isChecked() && q619edt.length()==0 )))) {
                            lib.showError(q619.this, "Q619: ERROR: Other", "Please specify?"
                                    + "Please select atleast one checkbox");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        }
                        else {


                            if (ck1txt.isChecked()) {
                                individual.setQ619_1("1");
                            } else {
                                individual.setQ619_1("2");
                            }


                            if (ck2txt.isChecked()) {
                                individual.setQ619_2("1");
                            } else {
                                individual.setQ619_2("2");
                            }
                            if (ck3txt.isChecked()) {
                                individual.setQ619_3("1");
                            } else {
                                individual.setQ619_3("2");
                            }

                            if (ck4txt.isChecked()) {
                                individual.setQ619_4("1");
                            } else {
                                individual.setQ619_4("2");
                            }
                            if (ck5txt.isChecked()) {
                                individual.setQ619_5("1");
                            } else {
                                individual.setQ619_5("2");
                            }
                            if (ck6txt.isChecked()) {
                                individual.setQ619_6("1");
                            } else {
                                individual.setQ619_6("2");
                            }
                            if (ck7txt.isChecked()) {
                                individual.setQ619_7("1");
                            } else {
                                individual.setQ619_7("2");
                            }
                            if (ck8txt.isChecked()) {
                                individual.setQ619_8("1");
                            } else {
                                individual.setQ619_8("2");
                            }
                            if (ck10txt.isChecked()) {
                                individual.setQ619_10("1");
                            } else {
                                individual.setQ619_10("2");
                            }
                            if (ck11txt.isChecked()) {
                                individual.setQ619_11("1");
                            } else {
                                individual.setQ619_11("2");
                            }
                            if (ck12txt.isChecked()) {
                                individual.setQ619_12("1");
                            } else {
                                individual.setQ619_12("2");
                            }
                            if (ck13txt.isChecked()) {
                                individual.setQ619_13("1");
                            } else {
                                individual.setQ619_13("2");
                            }
                            if (ck14txt.isChecked()) {
                                individual.setQ619_14("1");
                            } else {
                                individual.setQ619_14("2");
                            }

                            if (ck9txt.isChecked()) {
                                individual.setQ619_15("1");
                            } else {
                                individual.setQ619_15("2");
                            }
                            if (chkOther.isChecked()) {
                                individual.setQ619_Other("1");
                                individual.setQ619_Other(q619edt.getText().toString());

                            } else {
                                individual.setQ619_Other("");
                            }
                            if (!ck9txt.isChecked()) {
                                Intent intent = new Intent(q619.this, q620.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(q619.this, q621.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);
                            }

                        }

                    }
                }
            });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                q619.super.onBackPressed();
            }



        });
    }
    }
