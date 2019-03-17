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

import java.io.Serializable;
import java.util.List;

public class q402 extends AppCompatActivity implements Serializable {


    protected HouseHold thisHouse;
    protected Individual individual;
    Individual pp1 = null;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib, lib2;protected DatabaseHelper myDB;
    protected RadioButton rbtn99, rbtn1a, rbtn2a, rbtn4, rbtn1b, rbtn2b, rbtn3b, selectedRbtn, selectedRbtn1;
    protected RadioGroup rbtngroupa, rbtngroupb;
    protected EditText edtq402;
    protected CheckBox chk99;
    HouseHold thisHose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q402);

        setTitle("Q402");
        lib = new LibraryClass();
        chk99 = (CheckBox) findViewById(R.id.q402_99);
        edtq402 = (EditText) findViewById(R.id.q402_years);
        rbtngroupa = findViewById(R.id.q402rGroup1);
        rbtngroupb = findViewById(R.id.q402rGroup2);
        //rbtn1.setVisibility(View.INVISIBLE);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
      //  int p = 0;


        // final int selectedId = rbtngroup.getCheckedRadioButtonId();

        //setTitle("Q402a Did you give consent at the time of intercourse?");
        lib2 = new LibraryClass();
        rbtn1a = (RadioButton) findViewById(R.id.q402a_1);
        rbtn2a = (RadioButton) findViewById(R.id.q402a_2);


        //final int selectedId = rbtngroup.getCheckedRadioButtonId();
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }
        }

        //setTitle("Q402b Was the condom used during sexual intercourse?");
        lib = new LibraryClass();
        rbtn1b = (RadioButton) findViewById(R.id.q402b_1);
        rbtn2b = (RadioButton) findViewById(R.id.q402b_2);
        rbtn3b = (RadioButton) findViewById(R.id.q402b_9);

        //Intent i = getIntent();
        //thisHouse = (HouseHold) i.getSerializableExtra("Household");
        //int p = 0;
        if (individual.getQ401().equals("1") && Integer.valueOf(individual.getQ102()) >= 24) {

            Intent intent = new Intent(q402.this, q403.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

        else {
            //do nothing


        }





        if (individual.getQ401().equals("2") && individual.getQ101().equals("1") &&
                (Integer.valueOf(individual.getQ102()) >= 15 || Integer.valueOf(individual.getQ102()) <= 64))
        {
            Intent intent = new Intent(q402.this, q501.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {


            }


        if (individual.getQ401().equals("2") && individual.getQ101().equals("2") && (Integer.valueOf(individual.getQ102()) >= 15 || Integer.valueOf(individual.getQ102()) <= 64))
        {
            Intent intent = new Intent(q402.this, q601.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else

            {


        }

        if(ind.getQ402()!= null)
        {
            edtq402.setText(ind.getQ402());
        }


        if(ind.getQ402() == "99" )

        {
            chk99.setChecked(true);

        }else
        {
            chk99.setChecked(false);
        }


        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rbtngroupa.getChildCount();f++)
        {
            View o = rbtngroupa.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ402a()!= null &&  !ind.getQ402a().equals(""))
                {
                    if(Integer.parseInt(ind.getQ402a())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bt1 = new RadioButton[3];
        for(int f=0;f<rbtngroupb.getChildCount();f++)
        {
            View o = rbtngroupb.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt1[f]=((RadioButton)o);
                if(ind.getQ402b()!= null &&  !ind.getQ402b().equals(""))
                {
                    if(Integer.parseInt(ind.getQ402b())==f+1)
                    {
                        RadioButton radioButton = bt1[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }






        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  individual.setQ402(edtq402.getText().toString());

                if (!chk99.isChecked() && edtq402.length() == 0) {
                    lib.showError(q402.this, "Q402 Other", "Please enter age or select Don't know");

                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    //individual.setQ402(selectedRbtn.getText().toString().substring(0,1));
                    int selectedId = rbtngroupa.getCheckedRadioButtonId();
                    selectedRbtn = (RadioButton) findViewById(selectedId);


                    if (selectedRbtn == null) {
                        lib.showError(q402.this, "Q402A: Error ", "Q402a Please select an answer");

                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        //individual.setQ402(selectedRbtn.getText().toString().substring(0,1));


                        int selectedId1 = rbtngroupb.getCheckedRadioButtonId();
                        selectedRbtn1 = (RadioButton) findViewById(selectedId1);

                        if (selectedRbtn1 == null) {
                            lib.showError(q402.this, "Q402B: Error ", "Q402b Please select an answer");

                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {
                            int year = Integer.parseInt(edtq402.getText().toString());

                            if(year > Integer.parseInt(individual.getQ102()) && !chk99.isChecked()){
                                lib.showError(q402.this, "Q402: Error ", "Q402 Age cannot be greater than age of individual which is : " + individual.getQ102());

                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            }
                            else {

                                if(edtq402.getText().toString().length()==1){
                                    individual.setQ402("0"+edtq402.getText().toString());
                                }else {
                                    individual.setQ402(edtq402.getText().toString());
                                }


                                individual.setQ402a(selectedRbtn.getText().toString().substring(0, 1));
                                individual.setQ402b(selectedRbtn1.getText().toString().substring(0, 1));
                                String i = individual.getQ101();

                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                myDB.close();

                                //Check if country entered is in the list
                                Intent intent = new Intent(q402.this, q403.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);

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
                finish();
                Intent q1o3 = new Intent(q402.this, q401.class);
                q1o3.putExtra("Individual", individual);
                q1o3.putExtra("Personroster", p1);
                startActivity(q1o3);
            }


        });

    }

    public void onRadioButtonClicked(View v) {


        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q402_99:
                if (checked) {
                    //edtq402.setText("");

                    // q1108aques.setVisibility(View.VISIBLE);
                    //viewa.setVisibility(View.VISIBLE);
                    //chkq1108.setVisibility(View.VISIBLE);
                    //rb99.setVisibility(View.VISIBLE);
                }


                break;

            case R.id.q1108_n:
                if (checked) {


                    //rb99.setVisibility(View.INVISIBLE);
                }

                //txtdays.setVisibility(View.INVISIBLE);
                // txtweeks.setVisibility(View.INVISIBLE);


        }
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked

        if (checked) {
            // viewa.setEnabled(true);

            edtq402.setEnabled(false);
            edtq402.setText("99");

        }
        // Put some meat on the sandwich
        else {
            // Remove the meat
            edtq402.setEnabled(true);
            edtq402.setText("");


        }
    }


}






