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
import java.util.List;

public class q602 extends AppCompatActivity implements Serializable{

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected CheckBox ck1txt, ck2txt, ck3txt, ck4txt, ck5txt, ck6txt, ck7txt, ck8txt, ck10txt,ck11txt, ck12txt, ck13txt, ck14txt, ck15txt,chkOther, selected = null;
    protected Button btn;
    protected EditText Q602edt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q602);


        setTitle("Q602: KNOWLEDGE ABOUT HIV/AIDS AND TB");
        lib = new LibraryClass();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        if (individual.getQ601a().equals("2"))
        {
            Intent intent = new Intent(q602.this, q603.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }


        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();
        //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());



        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

        if((Integer.valueOf(individual.getQ102()) > 64 && (sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1"))))
        {

            Intent q1o2 = new Intent(q602.this, q604.class);
            q1o2.putExtra("Individual", individual);
            startActivity(q1o2);
        }

        if((sample.getStatusCode().equals("3") || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0"))) ) )
        {

            Intent q1o2 = new Intent(q602.this, q604.class);
            q1o2.putExtra("Individual", individual);
            startActivity(q1o2);
        }





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

                if ((((!ck1txt.isChecked() && !ck2txt.isChecked() && !ck3txt.isChecked() && !ck4txt.isChecked() && !ck5txt.isChecked() && !ck6txt.isChecked() && !ck7txt.isChecked() && !ck8txt.isChecked()
                        && !ck10txt.isChecked() && !ck11txt.isChecked() && !ck12txt.isChecked() && !ck13txt.isChecked() && !ck14txt.isChecked() && !ck15txt.isChecked() && !chkOther.isChecked())))) {
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
                       /* individual.setQ602_1(ck1txt.getText().toString().substring(0,1));
                        individual.setQ602_2(ck2txt.getText().toString().substring(0,1));
                        individual.setQ602_3(ck3txt.getText().toString().substring(0,1));
                        individual.setQ602_4(ck4txt.getText().toString().substring(0,1));
                        individual.setQ602_5(ck5txt.getText().toString().substring(0,1));
                        individual.setQ602_6(ck6txt.getText().toString().substring(0,1));
                        individual.setQ602_7(ck7txt.getText().toString().substring(0,1));
                        individual.setQ602_8(ck8txt.getText().toString().substring(0,1));
                        individual.setQ602_10(ck10txt.getText().toString().substring(0,1));
                       individual.setQ602_11(ck11txt.getText().toString().substring(0,1));
                       individual.setQ602_12(ck12txt.getText().toString().substring(0,1));
                       individual.setQ602_13(ck13txt.getText().toString().substring(0,1));
                        individual.setQ602_14(ck14txt.getText().toString().substring(0,1));
                        individual.setQ602_15(ck15txt.getText().toString().substring(0,1));
                       individual.setQ602_Other(chkOther.getText().toString().substring(0,1));
                        individual.setQ602_Otherspecify(Q602edt.getText().toString());
*/

                        if (ck1txt.isChecked()) {
                            individual.setQ602_1("1");
                        } else {
                            individual.setQ602_1("2");
                        }

                        if (ck2txt.isChecked()) {
                            individual.setQ602_2("1");
                        } else {
                            individual.setQ602_2("2");
                        }
                        if (ck3txt.isChecked()) {
                            individual.setQ602_3("1");
                        } else {
                            individual.setQ602_3("2");
                        }
                        if (ck4txt.isChecked()) {
                            individual.setQ602_4("1");
                        } else {
                            individual.setQ602_4("2");
                        }
                        if (ck5txt.isChecked()) {
                            individual.setQ602_5("1");
                        } else {
                            individual.setQ602_5("2");
                        }
                        if (ck6txt.isChecked()) {
                            individual.setQ602_6("1");
                        } else {
                            individual.setQ602_6("2");
                        } if (ck7txt.isChecked()) {
                            individual.setQ602_7("1");
                        } else {
                            individual.setQ602_7("2");
                        }
                        if (ck8txt.isChecked()) {
                            individual.setQ602_8("1");
                        } else {
                            individual.setQ602_8("2");
                        } if (ck10txt.isChecked()) {
                            individual.setQ602_10("1");
                        } else {
                            individual.setQ602_10("2");
                        } if (ck11txt.isChecked()) {
                            individual.setQ602_11("1");
                        } else {
                            individual.setQ602_11("2");
                        } if (ck12txt.isChecked()) {
                            individual.setQ602_12("1");
                        } else {
                            individual.setQ602_12("2");
                        } if (ck13txt.isChecked()) {
                            individual.setQ602_13("1");
                        } else {
                            individual.setQ602_13("2");
                        } if (ck14txt.isChecked()) {
                            individual.setQ602_14("1");
                        } else {
                            individual.setQ602_14("2");
                        } if (ck15txt.isChecked()) {
                            individual.setQ602_15("1");
                        } else {
                            individual.setQ602_15("2");
                        } if (chkOther.isChecked()) {
                            individual.setQ602_Other("1");
                            individual.setQ602_Otherspecify(Q602edt.getText().toString());
                        } else {
                            individual.setQ602_Other("2");
                        }
                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        myDB.close();

                        Intent intent = new Intent(q602.this, q603.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
                    }

                }
            }

        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q602.super.onBackPressed();
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
            case R.id.q602_11:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_12:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_13:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_14:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.q602_15:
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
                        Q602edt.setText("");
                    }
                    break;


                }
        }
    }
}