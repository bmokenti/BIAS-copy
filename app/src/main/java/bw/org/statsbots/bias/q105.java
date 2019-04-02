package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q105 extends AppCompatActivity implements Serializable{


    protected LibraryClass lib;
    protected  RadioButton rbtn1,rbtn2,rbtn3, rbtn4,rbtn5,rbtn6, rbtn7,rbtn8, rbtn9 ;
    protected RadioGroup rbtngroup;
    protected  HouseHold thisHouse;
    protected Individual individual;
    protected RadioButton selectedRbtn;
    protected DatabaseHelper myDB;
    protected EditText edt, edt1, edt2;
    TextView q105text, txta, txtb;
   protected  PersonRoster p1 ;
    Individual pp1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q105);


        setTitle("Q105. EMPLOYMENT STATUS ");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q105_1a);
        rbtn2 =  (RadioButton)findViewById(R.id.q105_1b);
        rbtn3 =  (RadioButton)findViewById(R.id.q105_1c);
        rbtn4 =  (RadioButton)findViewById(R.id.q105_1d);
        rbtn5 =  (RadioButton)findViewById(R.id.q105_1e);
        rbtn6 =  (RadioButton)findViewById(R.id.q105_1f);
        rbtn7 =  (RadioButton)findViewById(R.id.q105_1g);
        rbtn8 =  (RadioButton)findViewById(R.id.q105_1h);
        rbtn9 = findViewById(R.id.q105_other);


        edt = (EditText) findViewById(R.id.Q105_txtOther);

        edt1 = (EditText) findViewById(R.id.Q105atxt);
        edt2 = (EditText) findViewById(R.id.Q105btxt);
        txta = (TextView) findViewById(R.id.q105type);
        txtb = (TextView) findViewById(R.id.q105b);

        final RadioGroup rg = (RadioGroup)findViewById(R.id.q105radioGroup);


        lib = new LibraryClass();

        //edittext = (EditText) findViewById(R.id.q102_years);
        //edittext.setVisibility(View.VISIBLE);
        Intent ii = getIntent();
        p1 = (PersonRoster) ii.getSerializableExtra("Personroster");

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");


        myDB = new DatabaseHelper(q105.this);
        myDB.onOpen(myDB.getReadableDatabase());

        myDB.getWritableDatabase();
//        myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());
      //  int p = 0;

        edt1.clearFocus();
        edt2.clearFocus();

        myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());
        //int p = 0;

        final Individual ind = myDB.getdataIndivisual(p1.getAssignmentID(),p1.getBatch(),p1.getSRNO());
        individual = ind;


        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

        final List<PersonRoster> roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }



//      if(individual.getQ103() != null && individual.getQ103().equals("1"))
//      {
//          rbtn1.setEnabled(false);
//          rbtn2.setEnabled(false);
//          rbtn3.setEnabled(false);
//          rbtn4.setEnabled(false);
//          rbtn5.setEnabled(false);
//          rbtn6.setEnabled(false);
//          rbtn7.setEnabled(false);
//          rbtn8.setEnabled(true);
//          rbtn9.setEnabled(false);
//
//          edt.setVisibility(View.INVISIBLE);
//          edt.setText("");
//          edt1.setText("");
//          edt1.setEnabled(false);
//
//          edt2.setEnabled(false);
//          edt2.setText("");
//          txtb.setTextColor(Color.LTGRAY);
//          txta.setTextColor(Color.LTGRAY);
//      }

        RadioButton[] bt = new RadioButton[9];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);


                if(ind.getQ105Other()!=null){
                    rbtn9.setChecked(true);
                    edt.setText(ind.getQ105Other());
                }else{

                    if(ind.getQ105()!= null &&  !ind.getQ105().equals(""))
                    {
                        if(Integer.parseInt(ind.getQ105())==f+1)
                        {
                            RadioButton radioButton = bt[f];
                            radioButton.setChecked(true);

                            TextView q802atext = findViewById(R.id.q802a_other);
                            RadioGroup rg1 = (RadioGroup) findViewById(R.id.q802radioGroupa);
                            // Is the current Radio Button checked?
                            boolean checked = radioButton.isChecked();
                            View v = radioButton;
                            switch (v.getId()) {
                                case R.id.rbtn1:
                                    if (checked)
                                        edt.setVisibility(View.INVISIBLE);
                                    edt.setText("");
                                    edt2.setEnabled(true);
                                    edt1.setEnabled(true);
                                    txtb.setTextColor(Color.BLACK);
                                    txta.setTextColor(Color.BLACK);



                                    break;


                                case R.id.q105_1b:
                                    if (checked)

                                        edt.setVisibility(View.INVISIBLE);
                                    edt.setText("");
                                    edt2.setEnabled(true);
                                    edt1.setEnabled(true);
                                    txtb.setTextColor(Color.BLACK);
                                    txta.setTextColor(Color.BLACK);




                                    break;
                                case R.id.q105_1c:
                                    if (checked)
                                        edt.setVisibility(View.INVISIBLE);
                                    edt.setText("");
                                    edt2.setEnabled(true);
                                    edt1.setEnabled(true);
                                    txtb.setTextColor(Color.BLACK);
                                    txta.setTextColor(Color.BLACK);



                                    break;
                                case R.id.q105_1d:
                                    if (checked)
                                        edt.setVisibility(View.INVISIBLE);
                                    edt.setText("");
                                    edt2.setEnabled(true);
                                    edt1.setEnabled(true);

                                    txtb.setTextColor(Color.BLACK);
                                    txta.setTextColor(Color.BLACK);

                                    break;
                                case R.id.q105_1e:
                                    if (checked)
                                        edt.setVisibility(View.INVISIBLE);
                                    edt.setText("");
                                    edt1.setText("");

                                    edt1.setEnabled(false);
                                    edt2.setEnabled(false);
                                    edt2.setText("");
                                    txtb.setTextColor(Color.LTGRAY);
                                    txta.setTextColor(Color.LTGRAY);



                                    break;
                                case R.id.q105_1f:
                                    if (checked)
                                        edt.setVisibility(View.INVISIBLE);
                                    edt.setText("");
                                    edt1.setText("");

                                    edt1.setEnabled(false);
                                    edt2.setEnabled(false);
                                    edt2.setText("");
                                    txtb.setTextColor(Color.LTGRAY);
                                    txta.setTextColor(Color.LTGRAY);


                                    break;
                                case R.id.q105_1g:
                                    if (checked)
                                        edt.setVisibility(View.INVISIBLE);
                                    edt.setText("");
                                    edt1.setText("");
                                    edt1.setEnabled(false);
                                    txtb.setTextColor(Color.LTGRAY);
                                    txta.setTextColor(Color.LTGRAY);

                                    edt2.setEnabled(false);
                                    edt2.setText("");

                                    break;

                                case R.id.q105_1h:
                                    if (checked)
                                        edt.setVisibility(View.INVISIBLE);
                                    edt.setText("");
                                    edt1.setText("");
                                    edt1.setEnabled(false);

                                    edt2.setEnabled(false);
                                    edt2.setText("");
                                    txtb.setTextColor(Color.LTGRAY);
                                    txta.setTextColor(Color.LTGRAY);


                                    break;
                                case R.id.q105_other:
                                    if (checked)
                                        edt.setVisibility(View.VISIBLE);

                                    txtb.setTextColor(Color.BLACK);
                                    txta.setTextColor(Color.BLACK);

                                    edt1.setEnabled(true);
                                    edt2.setEnabled(true);
                                    break;

                            }

                            break;
                        }
                    }
                }



            }
            else
            {
                Log.d("h13 Lost Here","**********");
            }
        }

        if(ind.getQ105Other()!=null){
            rbtn9.setChecked(true);
            edt.setText(ind.getQ105Other());
        }
        if(ind.getQ105a()!=null){

            edt1.setText(ind.getQ105a());
        }
        if(ind.getQ105b()!=null)
        {

            edt2.setText(ind.getQ105b());
        }



        //myDB.getIndividualdt();

        /**
         * NEXT Person BUTTON
         */
        Button btnNext = (Button)findViewById(R.id.button);


        //btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Years;


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q105.this, "q105 Error", "Please select employment status");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                } else {
                    if (rbtn9.isChecked() && (edt.getText() == null || edt.length() == 0)) {
                        lib.showError(q105.this, "q105 Other specify", "If the Other specify button is selected, other specify box should have a value");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);


                    } else {
                        if ((rbtn1.isChecked() || rbtn2.isChecked() || rbtn3.isChecked() || rbtn4.isChecked() || rbtn9.isChecked()) && edt1.length() == 0 || edt1.getText() == null) {
                            lib.showError(q105.this, "q105a Error", "Please state current occupation?");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);


                        } else {
                            if ((rbtn1.isChecked() || rbtn2.isChecked() || rbtn3.isChecked() || rbtn4.isChecked() || rbtn9.isChecked()) && edt2.length() == 0 || edt2.getText() == null) {
                                lib.showError(q105.this, "q105b Error", "What is the main product, service or activity at your place of work?");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            }

//                            } else {
//
//
//                                if (((p1.getP15() == null || p1.getP15() != null) && (!edt1.getText().equals("") || edt1.length() > 0) && (rbtn1.isChecked() || rbtn2.isChecked() || rbtn3.isChecked() || rbtn4.isChecked() )) ||
//                                        ((p1.getP16() == null || p1.getP16() != null) && (!edt2.getText().equals("") || edt1.length() >0) && (rbtn1.isChecked() || rbtn2.isChecked() || rbtn3.isChecked() || rbtn4.isChecked() ))) {
//                                    AlertDialog.Builder builder = new AlertDialog.Builder(q105.this);
//                                    builder.setTitle("Work in the past seven days.");
//                                    builder.setIcon(R.drawable.ic_warning_orange_24dp);
//                                    builder.setMessage("Do you want to update P15 and P16");
//                                   /* builder.setPositiveButton("No changes", new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int id) {
//
//                                            individual.setQ105(selectedRbtn.getText().toString().substring(0, 1));
//                                            if(rbtn9.isChecked()){
//                                                individual.setQ105Other(edt.getText().toString());
//                                            }
//
//                                            if(rbtn1.isChecked() || rbtn2.isChecked() || rbtn3.isChecked() || rbtn4.isChecked() || rbtn9.isChecked())
//                                            {
//                                                individual.setQ105a(edt1.getText().toString());
//                                                individual.setQ105a(edt2.getText().toString());
//                                            }
//                                            myDB = new DatabaseHelper(q105.this);
//                                            myDB.onOpen(myDB.getReadableDatabase());
//
//                                            myDB.updateInd("Q105",individual.getAssignmentID(),individual.getBatch(),ind.getQ105(),String.valueOf(individual.getQ105()));
//
//                                            myDB.updateInd("Q105Other",individual.getAssignmentID(),individual.getBatch(),ind.getQ105(),String.valueOf(individual.getQ105Other()));
//
//                                            myDB.updateInd("Q105a",individual.getAssignmentID(),individual.getBatch(),ind.getQ105(),String.valueOf(individual.getQ105a()));
//
//                                            myDB.updateInd("Q105b",individual.getAssignmentID(),individual.getBatch(),ind.getQ105b(),String.valueOf(individual.getQ105b()));
//
//
//                                            myDB.close();
//                                            Intent intent = new Intent(q105.this, q106.class);
//                                            intent.putExtra("Individual", individual);
//                                            intent.putExtra("Personroster", p1);
//                                            startActivity(intent);
//
//                                        }
//
//                                    });*/
//
//                                    builder.setNegativeButton("Ammend", new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int id) {
//
//
//                                            myDB = new DatabaseHelper(q105.this);
//                                            myDB.onOpen(myDB.getReadableDatabase());
//
//
//                                            p1.setP15(edt1.getText().toString());
//
//                                            myDB.updateConsents("P15", p1.getAssignmentID(), p1.getBatch(), p1.getP15(), String.valueOf(p1.getSRNO()));
//
//                                            p1.setP16(edt2.getText().toString());
//
//                                            myDB.updateConsents("P16", p1.getAssignmentID(), p1.getBatch(), p1.getP16(), String.valueOf(p1.getSRNO()));
//
//
//                                            //Restart the current activity for next individual
//
//                                            //Check if individual already been saved and update
//
//                                            individual.setQ105(selectedRbtn.getText().toString().substring(0, 1));
//                                            if(rbtn9.isChecked()){
//                                                individual.setQ105Other(edt.getText().toString());
//                                            }
//
//                                            if(rbtn1.isChecked() || rbtn2.isChecked() || rbtn3.isChecked() || rbtn4.isChecked() || rbtn9.isChecked())
//                                            {
//                                                individual.setQ105a(edt1.getText().toString());
//                                                individual.setQ105b(edt2.getText().toString());
//                                            }
//                                            if(rbtn5.isChecked() || rbtn6.isChecked() || rbtn7.isChecked() || rbtn8.isChecked())
//                                            {
//                                                individual.setQ105a(null);
//                                                individual.setQ105b(null);
//                                            }
//
//
//                                            myDB = new DatabaseHelper(q105.this);
//                                            myDB.onOpen(myDB.getReadableDatabase());
//
//                                            myDB.updateInd("Q105",individual.getAssignmentID(),individual.getBatch(),individual.getQ105(),String.valueOf(individual.getSRNO()));
//
//                                            myDB.updateInd("Q105Other",individual.getAssignmentID(),individual.getBatch(),individual.getQ105Other(),String.valueOf(individual.getSRNO()));
//
//                                            myDB.updateInd("Q105a",individual.getAssignmentID(),individual.getBatch(),individual.getQ105a(),String.valueOf(individual.getSRNO()));
//
//                                            myDB.updateInd("Q105b",individual.getAssignmentID(),individual.getBatch(),individual.getQ105b(),String.valueOf(individual.getSRNO()));
//
//
//                                            myDB.close();
//                                            Intent intent = new Intent(q105.this, q106.class);
//                                            intent.putExtra("Individual", individual);
//                                            intent.putExtra("Personroster", p1);
//                                            startActivity(intent);
//
//                                        }
//                                    });
//
//                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//                                    vibs.vibrate(100);
//
//                                    AlertDialog alertDialog = builder.show();
//                                    final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
//                                    final Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
//
//                                    LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
//                                    LinearLayout.LayoutParams negativeButtonLL = (LinearLayout.LayoutParams) negativeButton.getLayoutParams();
//
//                                    positiveButton.setTextColor(Color.WHITE);
//                                    negativeButton.setTextColor(Color.WHITE);
//
//                                    positiveButton.setBackgroundColor(Color.parseColor("#3180e9"));
//                                    negativeButton.setBackgroundColor(Color.parseColor("#3180e9"));
//
//                                    positiveButtonLL.leftMargin = 10;
//
//                                    negativeButtonLL.weight = 10;
//                                    positiveButtonLL.weight = 10;
//
//                                    positiveButton.setLayoutParams(positiveButtonLL);
//                                    negativeButton.setLayoutParams(negativeButtonLL);
//
//                                }

                                else {
                                    if (rbtn9.isChecked()) {
                                        individual.setQ105(selectedRbtn.getText().toString().substring(0, 1));
                                        individual.setQ105Other(edt.getText().toString());
                                        //individual.setQ105(edt.getText().toString());

                                        individual.setQ105a(edt1.getText().toString());
                                        individual.setQ105b(edt2.getText().toString());


                                        myDB = new DatabaseHelper(q105.this);
                                        myDB.onOpen(myDB.getReadableDatabase());

                                        myDB.updateInd("Q105",individual.getAssignmentID(),individual.getBatch(),individual.getQ105(),String.valueOf(individual.getSRNO()));

                                        myDB.updateInd("Q105Other",individual.getAssignmentID(),individual.getBatch(),individual.getQ105Other(),String.valueOf(individual.getSRNO()));

                                        myDB.updateInd("Q105a",individual.getAssignmentID(),individual.getBatch(),individual.getQ105a(),String.valueOf(individual.getSRNO()));

                                        myDB.updateInd("Q105b",individual.getAssignmentID(),individual.getBatch(),individual.getQ105b(),String.valueOf(individual.getSRNO()));


                                        myDB.close();

                                        Intent intent = new Intent(q105.this, q106.class);
                                        intent.putExtra("Individual", individual);
                                        intent.putExtra("Personroster", p1);
                                        startActivity(intent);


                                    } else {


                                        individual.setQ105(selectedRbtn.getText().toString().substring(0, 1));

                                        individual.setQ105a(edt1.getText().toString());
                                        individual.setQ105b(edt2.getText().toString());

                                        myDB = new DatabaseHelper(q105.this);
                                        myDB.onOpen(myDB.getReadableDatabase());

                                        myDB.updateInd("Q105",individual.getAssignmentID(),individual.getBatch(),individual.getQ105(),String.valueOf(individual.getSRNO()));

                                        myDB.updateInd("Q105Other",individual.getAssignmentID(),individual.getBatch(),individual.getQ105Other(),String.valueOf(individual.getSRNO()));

                                        myDB.updateInd("Q105a",individual.getAssignmentID(),individual.getBatch(),individual.getQ105a(),String.valueOf(individual.getSRNO()));

                                        myDB.updateInd("Q105b",individual.getAssignmentID(),individual.getBatch(),individual.getQ105b(),String.valueOf(individual.getSRNO()));


                                        myDB.close();

                                        //Next question P17
                                        Intent intent = new Intent(q105.this, q106.class);
                                        intent.putExtra("Individual", individual);
                                        intent.putExtra("Personroster", p1);
                                        startActivity(intent);


                                    }
                                }
                            }
                        }

                }
            }
        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ind.getQ103().equals("3")) {
                    Intent q1o2 = new Intent(q105.this, q103.class);
                    q1o2.putExtra("Personroster", p1);

                    startActivity(q1o2);

                } else {
                    if (ind.getQ103().equals("1") || ind.getQ103().equals("2"))
                    {
                        Intent q1o2 = new Intent(q105.this, q104.class);
                        q1o2.putExtra("Personroster", p1);
                        startActivity(q1o2);
                    }

                }

            }
        });
    }


    public void onRadioButtonClicked(View v) {

        TextView q802atext = findViewById(R.id.q802a_other);
        RadioGroup rg1 = (RadioGroup) findViewById(R.id.q802radioGroupa);
        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q105_1a:
                if (checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                edt2.setEnabled(true);
                edt1.setEnabled(true);
                txtb.setTextColor(Color.BLACK);
                txta.setTextColor(Color.BLACK);



                break;


            case R.id.q105_1b:
                if (checked)

                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                edt2.setEnabled(true);
                edt1.setEnabled(true);
                txtb.setTextColor(Color.BLACK);
                txta.setTextColor(Color.BLACK);



                break;
            case R.id.q105_1c:
                if (checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                edt2.setEnabled(true);
                edt1.setEnabled(true);
                txtb.setTextColor(Color.BLACK);
                txta.setTextColor(Color.BLACK);


                break;
            case R.id.q105_1d:
                if (checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                edt2.setEnabled(true);
                edt1.setEnabled(true);
                txtb.setTextColor(Color.BLACK);
                txta.setTextColor(Color.BLACK);


                break;
            case R.id.q105_1e:
                if (checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                edt1.setText("");

                edt1.setEnabled(false);
                edt2.setEnabled(false);
                edt2.setText("");
                txtb.setTextColor(Color.LTGRAY);
                txta.setTextColor(Color.LTGRAY);


                break;
            case R.id.q105_1f:
                if (checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                edt1.setText("");

                edt1.setEnabled(false);
                edt2.setEnabled(false);
                edt2.setText("");
                txtb.setTextColor(Color.LTGRAY);
                txta.setTextColor(Color.LTGRAY);


                break;
            case R.id.q105_1g:
                if (checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                edt1.setText("");
                edt1.setEnabled(false);

                edt2.setEnabled(false);
                edt2.setText("");
                txtb.setTextColor(Color.LTGRAY);
                txta.setTextColor(Color.LTGRAY);

                break;

            case R.id.q105_1h:
                if (checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                edt1.setText("");
                edt1.setEnabled(false);

                edt2.setEnabled(false);
                edt2.setText("");
                txtb.setTextColor(Color.LTGRAY);
                txta.setTextColor(Color.LTGRAY);

                break;
            case R.id.q105_other:
                if (checked)
                    edt.setVisibility(View.VISIBLE);

                txtb.setTextColor(Color.BLACK);
                txta.setTextColor(Color.BLACK);

                edt1.setEnabled(true);
                edt2.setEnabled(true);
                break;

        }
    }
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q105.this).toBundle());

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




/*
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.q102_years:
                Intent intent4 = new Intent(this, q103.class);
                startActivity(intent4);
                break;


        }
    }*/
