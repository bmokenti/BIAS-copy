package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.Serializable;
import java.util.List;

public class q605 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
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
        //int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }




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


        if(ind.getQ605_1()!= null ) {
            if (!ind.getQ605_1().equals("")) {
                if (Integer.parseInt(ind.getQ605_1()) == 1) {
                    ck1txt.setChecked(true);

                } else {
                    ck1txt.setChecked(false);
                }
            }
        }



        if(ind.getQ605_2()!= null )
        {
            if(!ind.getQ605_2().equals("")) {
                if (Integer.parseInt(ind.getQ605_2()) == 1) {
                    ck2txt.setChecked(true);

                } else {
                    ck2txt.setChecked(false);
                }
            }
        }

        if(ind.getQ605_3()!= null ) {
            if (!ind.getQ605_3().equals("")) {
                if (Integer.parseInt(ind.getQ605_3()) == 1) {
                    ck3txt.setChecked(true);

                } else {
                    ck3txt.setChecked(false);
                }
            }
        }

        if(ind.getQ605_4()!= null ) {
            if (!ind.getQ605_4().equals("")) {
                if (Integer.parseInt(ind.getQ605_4()) == 1) {
                    ck4txt.setChecked(true);

                } else {
                    ck4txt.setChecked(false);
                }
            }
        }

        if(ind.getQ605_5()!= null ) {
            if (!ind.getQ605_5().equals("")) {
                if (Integer.parseInt(ind.getQ605_5()) == 1) {
                    ck5txt.setChecked(true);

                } else {
                    ck5txt.setChecked(false);
                }
            }
        }


        if(ind.getQ605_9()!= null ) {
            if (!ind.getQ605_9().equals("")) {
                if (Integer.parseInt(ind.getQ605_9()) == 1) {
                    ck9txt.setChecked(true);

                } else {
                    ck9txt.setChecked(false);
                }
            }
        }



        if(ind.getQ605_Other() != null )
        {
            if(!ind.getQ605_Other().equals("")) {
                if (Integer.parseInt(ind.getQ605_Other().substring(0, 1)) == 1) {
                    chkOther.setChecked(true);
                    q605edt.setVisibility(View.VISIBLE);
                    q605edt.setText(ind.getQ605_Other().substring(1, ind.getQ605_Other().length()));

                } else {
                    chkOther.setChecked(false);
                }
            }
        }

//        if(ind.getQ605_Otherspecify()!= null )
//        {
//            q605edt.setVisibility(View.VISIBLE);
//            q605edt.setText(ind.getQ605_Otherspecify());
//        }


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
                    if (sample.getStatusCode().equals("3") || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0"))
                            || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") && Integer.valueOf(individual.getQ102()) > 64)) {


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
                            individual.setQ605_Other("1"+q605edt.getText().toString());
                        } else {
                            individual.setQ605_Other("2");
                        }


                        //Check if individual already been saved and update
                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                        myDB.close();

                        Intent intent = new Intent(q605.this, q616.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
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
                            individual.setQ605_Other("1"+q605edt.getText().toString());
                        } else {
                            individual.setQ605_Other("2");
                        }


                        //Check if individual already been saved and update
                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                        myDB.close();

                        Intent intent = new Intent(q605.this, q606.class);
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
                finish();
                Intent intent = new Intent(q605.this, q604.class);
                intent.putExtra("Individual", individual);
                startActivity(intent);
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

    //   thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

//    final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
//        for (PersonRoster p: roster
//        ) {
//        if (p.getSRNO() == ind.getSRNO()){
//            p1 = p;
//            break;
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intervie_control, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {

            case R.id.pause:
                // Show the settings activity
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("[Demo!] Are you sure you want to pause the interview");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(getApplicationContext(), started_household.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q605.this).toBundle());

                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

