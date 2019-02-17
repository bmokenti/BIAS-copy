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

public class q605 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected Individual individual;
    protected CheckBox ck1txt, ck2txt, ck3txt, ck4txt, ck5txt, ck9txt, chkOther, selected = null;
    protected Button btn;
    protected EditText q605edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q605);


        setTitle("Q605: KNOWLEDGE ABOUT HIV/AIDS AND TB");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        ck1txt = findViewById(R.id.q605_1);
        ck2txt = findViewById(R.id.q605_2);
        ck3txt = findViewById(R.id.q605_3);
        ck4txt = findViewById(R.id.q605_4);
        ck5txt = findViewById(R.id.q605_5);
        ck9txt = findViewById(R.id.q605_9);
        chkOther = findViewById(R.id.q605_other);
        q605edt = findViewById(R.id.q605edt_Other);


        if(ck9txt.isChecked())
        {
            ck1txt.setEnabled(false);
            ck2txt.setEnabled(false);
            ck3txt.setEnabled(false);
            ck4txt.setEnabled(false);
            ck5txt.setEnabled(false);

            chkOther.setEnabled(false);
            q605edt.setText("");

            ck2txt.setChecked(false);
            ck3txt.setChecked(false);
            ck4txt.setChecked(false);
            ck5txt.setChecked(false);
            chkOther.setChecked(false);


        }
        else
        {
            ck2txt.setEnabled(true);
            ck3txt.setEnabled(true);
            ck4txt.setEnabled(true);
            ck5txt.setEnabled(true);
            chkOther.setEnabled(true);
        }


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((((!ck1txt.isChecked() && !ck2txt.isChecked() && !ck3txt.isChecked() && !ck4txt.isChecked() && !ck5txt.isChecked() && !ck9txt.isChecked() && !chkOther.isChecked())))) {
                    lib.showError(q605.this, "Q605:", "How can people prevent becoming infected with TB?"
                            + "Please select atleast one checkbox");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else if ((((chkOther.isChecked() && q605edt.length() == 0)))) {
                    lib.showError(q605.this, "Q605:", "Please specify other or uncheck Other specify");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                   /* individual.setQ605_1(ck1txt.getText().toString().substring(0,1));
                   individual.setQ605_2(ck2txt.getText().toString().substring(0,1));
                    individual.setQ605_3(ck3txt.getText().toString().substring(0,1));
                    individual.setQ605_4(ck4txt.getText().toString().substring(0,1));
                    individual.setQ605_5(ck5txt.getText().toString().substring(0,1));
                     individual.setQ605_5(ck9txt.getText().toString().substring(0,1));

                     individual.setQ605_Other(chkOther.getText().toString().substring(0,1));
                    individual.setQ605_Otherspecify(q605edt.getText().toString());*/

                    if (ck1txt.isChecked()) {
                        individual.setQ605_1("1");
                    } else {
                        individual.setQ605_1("2");
                    }
                    if (ck2txt.isChecked()) {
                        individual.setQ605_2("1");
                    } else {
                        individual.setQ605_2("2");
                    }
                    if (ck3txt.isChecked()) {
                        individual.setQ605_3("1");
                    } else {
                        individual.setQ605_3("2");
                    }
                    if (ck4txt.isChecked()) {
                        individual.setQ605_4("1");
                    } else {
                        individual.setQ605_4("2");
                    }
                    if (ck5txt.isChecked()) {
                        individual.setQ605_5("1");
                    } else {
                        individual.setQ605_5("2");
                    }
                    if (ck9txt.isChecked()) {
                        individual.setQ605_9("1");
                    } else {
                        individual.setQ605_9("2");
                    }
                    if (chkOther.isChecked()) {
                        individual.setQ605_Other("1");
                        individual.setQ605_Otherspecify(q605edt.getText().toString());
                    } else {
                        individual.setQ605_Other("2");
                    }


                    //Check if individual already been saved and update
                    DatabaseHelper myDB = new DatabaseHelper(q605.this);
                    myDB.onOpen(myDB.getReadableDatabase());

                    if(myDB.checkIndividual(individual))
                    {
                        //Update
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        //myDB.close();
                    }


                    Intent intent = new Intent(q605.this, q606.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                }

            }

        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q605.super.onBackPressed();
            }


        });

    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.q605_1:
                if (checked)
                    // Remove the meat


                break;

            case R.id.q605_2:
                if (checked)
                    // Remove the meat


                break;
            case R.id.q605_3:
                if (checked)
                    // Remove the meat


                break;
            case R.id.q605_4:
                if (checked)
                    // Remove the meat


                break;
            case R.id.q605_5:
                if (checked)
                    // Remove the meat


                break;

            case R.id.q605_9:
                if (checked)

                    {
                        ck1txt.setEnabled(false);
                        ck2txt.setEnabled(false);
                        ck3txt.setEnabled(false);
                        ck4txt.setEnabled(false);
                        ck5txt.setEnabled(false);

                        chkOther.setEnabled(false);
                        q605edt.setText("");
                        ck1txt.setChecked(false);
                        ck2txt.setChecked(false);
                        ck3txt.setChecked(false);
                        ck4txt.setChecked(false);
                        ck5txt.setChecked(false);
                        chkOther.setChecked(false);
                        q605edt.setVisibility(View.INVISIBLE);
                        q605edt.setText("");



                    }
                    else
                    { ck1txt.setEnabled(true);
                        ck2txt.setEnabled(true);
                        ck3txt.setEnabled(true);
                        ck4txt.setEnabled(true);
                        ck5txt.setEnabled(true);
                        chkOther.setEnabled(true);

                    }





                break;
            case R.id.q605_other:
                if (checked) {

                    q605edt.setVisibility(View.VISIBLE);
                }

                    // Put some meat on the sandwich
                    else {
                    q605edt.setVisibility(View.INVISIBLE);
                    q605edt.setText("");
                }
                        // Remove the meat

                    break;


                }
        }
    }

