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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q802 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtna2, rbtna3, rbtna4, rbtna5, rbtnaother, selected, selected1;
    protected RadioGroup rbtngroup, rbtngroup1;
    protected EditText edtnaOther;
    protected DatabaseHelper myDB;
    protected TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q802);

        setTitle("q802 HIV SUPPORT, CARE AND TREATMENT");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q802_1);
        rbtn2 = (RadioButton) findViewById(R.id.q802_2);
        rbtngroup = (RadioGroup) findViewById(R.id.q802radioGroup);


        rbtngroup1 = (RadioGroup) findViewById(R.id.q802radioGroupa);
        rbtna2 = (RadioButton) findViewById(R.id.q802a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q802a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q802a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q802a_5);
        rbtnaother = (RadioButton) findViewById(R.id.q802a_other);
        edtnaOther = (EditText) findViewById(R.id.q802a_other1);
        txt1 = (TextView) findViewById(R.id.q802a);

        final int selectedId1 = rbtngroup1.getCheckedRadioButtonId();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
      //  int p = 0;


        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);



        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }



//        if(( (sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1")) &&
//                p1.getP06().equals("2")))
//
//        {
//
//            individual.setQ802(null);
//            individual.setQ802a(null);
//            individual.setQ802aOther(null);
//
//            myDB = new DatabaseHelper(q802.this);
//            myDB.onOpen(myDB.getReadableDatabase());
//            myDB.updateIndividual(myDB.getWritableDatabase(), individual);
//            myDB.close();
//
//            Intent intent = new Intent(q802.this, q904.class);
//            intent.putExtra("Individual", individual);
//            startActivity(intent);
//        }



        if((individual.getQ801f() != null && ( individual.getQ801f().equals("2") || individual.getQ801f().equals("3") || individual.getQ801f().equals("4")
                || individual.getQ801f().equals("9")) && individual.getQ801a().equals("2")))

        {

            individual.setQ802(null);
            individual.setQ802a(null);
            individual.setQ802aOther(null);

            myDB = new DatabaseHelper(q802.this);
            myDB.onOpen(myDB.getReadableDatabase());
            myDB.updateIndividual(myDB.getWritableDatabase(), individual);
            myDB.close();

            Intent intent = new Intent(q802.this, q803.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

        else

                if(( individual.getQ801f() != null && (individual.getQ801f().equals("2") || individual.getQ801f().equals("3") || individual.getQ801f().equals("4") ||
                        individual.getQ801f().equals("9"))) && (individual.getQ801a() != null && individual.getQ801a().equals("1"))
                        && individual.getQ101().equals("2") && (Integer.valueOf(individual.getQ102()) > 14 &&
                        Integer.valueOf(individual.getQ102()) < 50))
                {
                    individual.setQ802(null);
                    individual.setQ802a(null);
                    individual.setQ802aOther(null);
                    individual.setQ803(null);
                    individual.setQ803Other(null);
                    individual.setQ804(null);
                    individual.setQ804Other(null);

                    individual.setQ901(null);
                    individual.setQ901a(null);
                    individual.setQ901aOther(null);
                    individual.setQ902Month("00");
                    individual.setQ902Year("0000");
                    individual.setQ903a(null);
                    individual.setQ903b(null);
                    individual.setQ903c(null);
                    individual.setQ903d(null);
                    individual.setQ903e(null);
                    individual.setQ903f(null);
                    individual.setQ903g(null);
                    individual.setQ903h(null);

                    individual.setQ904(null);
                    individual.setQ904a(null);
                    individual.setQ904aOther(null);
                    individual.setQ904bMM("00");
                    individual.setQ904bYYYY("0000");
                    individual.setQ904c(null);
                    individual.setQ904cOther(null);
                    individual.setQ905(null);
                    individual.setQ905a(null);
                    individual.setQ905aOther(null);

                    myDB = new DatabaseHelper(q802.this);
                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                    myDB.close();

                    Intent intent = new Intent(q802.this, q1001.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                }

    else
        {
                    if ( (individual.getQ801f() != null && (individual.getQ801f().equals("2") || individual.getQ801f().equals("3") ||
                            individual.getQ801f().equals("4") || individual.getQ801f().equals("9"))) &&
                            (individual.getQ801a() != null && individual.getQ801a().equals("1"))
                            && individual.getQ101().equals("1"))
                    {

                        individual.setQ802(null);
                        individual.setQ802a(null);
                        individual.setQ802aOther(null);
                        individual.setQ803(null);
                        individual.setQ803Other(null);
                        individual.setQ804(null);
                        individual.setQ804Other(null);

                        individual.setQ901(null);
                        individual.setQ901a(null);
                        individual.setQ901aOther(null);
                        individual.setQ902Month("00");
                        individual.setQ902Year("0000");
                        individual.setQ903a(null);
                        individual.setQ903b(null);
                        individual.setQ903c(null);
                        individual.setQ903d(null);
                        individual.setQ903e(null);
                        individual.setQ903f(null);
                        individual.setQ903g(null);
                        individual.setQ903h(null);

                        individual.setQ904(null);
                        individual.setQ904a(null);
                        individual.setQ904aOther(null);
                        individual.setQ904bMM("00");
                        individual.setQ904bYYYY("0000");
                        individual.setQ904c(null);
                        individual.setQ904cOther(null);
                        individual.setQ905(null);
                        individual.setQ905a(null);
                        individual.setQ905aOther(null);

                        individual.setQ1001(null);
                        individual.setQ1002(null);
                        individual.setQ1002a_1(null);
                        individual.setQ1002a_2(null);
                        individual.setQ1002a_3(null);
                        individual.setQ1002a_4(null);
                        individual.setQ1002a_5(null);
                        individual.setQ1002a_6(null);
                        individual.setQ1002a_7(null);
                        individual.setQ1002a_8(null);
                        // individual.setQ1002a_9(null);
                        individual.setQ1002a_10(null);
                        individual.setQ1002a_11(null);
                        individual.setQ1002a_12(null);
                        individual.setQ1002a_13(null);
                        individual.setQ1002a_14(null);
                        individual.setQ1002a_15(null);
                        individual.setQ1002a_16(null);
                        individual.setQ1002a_17(null);
                        individual.setQ1002a_18(null);
                        individual.setQ1002a_Other(null);
                        individual.setQ1002b(null);
                        individual.setQ1002b_Other(null);
                        individual.setQ1003(null);
                        individual.setQ1004_Year("00");
                        individual.setQ1004_Month("00");
                        individual.setQ1004_Day("00");
                        individual.setQ1004a(null);
                        individual.setQ1004a(null);
                        individual.setQ1004b(null);
                        individual.setQ1004b_Other(null);
                        individual.setQ1005(null);
                        individual.setQ1005a(null);
                        individual.setQ1006(null);
                        individual.setQ1007(null);
                        individual.setQ1007a(null);
                        individual.setQ1008(null);
                        individual.setQ1008a(null);
                        individual.setQ1008a_Other(null);
                        individual.setQ1009(null);
                        individual.setQ1009a(null);
                        individual.setQ1010(null);
                        individual.setQ1010_Other(null);
                        individual.setQ1011(null);
                        individual.setQ1011_Other(null);
                        individual.setQ1012_Year("00");
                        individual.setQ1012_Month("00");
                        individual.setQ1012_Week("00");
                        individual.setQ1013(null);
                        individual.setQ1014(null);
                        individual.setQ1014a(null);
                        individual.setQ1014b(null);
                        individual.setQ1015(null);
                        individual.setQ1015a(null);
                        individual.setQ1015b(null);
                        individual.setQ1016(null);
                        individual.setQ1017(null);

                        myDB = new DatabaseHelper(q802.this);
                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.updateIndividual(myDB.getWritableDatabase(), individual);
                        myDB.close();

                        Intent intent = new Intent(q802.this, q1101.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
                    }

                }

        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rbtngroup.getChildCount();f++)
        {
            View o = rbtngroup.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ802()!= null &&  !ind.getQ802().equals(""))
                {
                    if(Integer.parseInt(ind.getQ802())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }


        if (ind.getQ802aOther() != null ) {

            if (ind.getQ802a() != null && ind.getQ802a().equals("O")) {
                rbtnaother.setChecked(true);
                edtnaOther.setText(ind.getQ802aOther());
            }
        }
        else {
            RadioButton[] bta = new RadioButton[5];
            for (int f = 0; f < rbtngroup1.getChildCount(); f++) {
                View o = rbtngroup1.getChildAt(f);
                if (o instanceof RadioButton) {
                    bta[f] = ((RadioButton) o);
                    if (ind.getQ802a() != null && !ind.getQ802a().equals("")) {
                        if (Integer.parseInt(ind.getQ802a()) == f + 1) {
                            RadioButton radioButton = bta[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }

//        if( ind.getQ802aOther() != null)
//        {
//            edtnaOther.setText(ind.getQ802aOther());
//        }


        /**
         * NEXT question
         */


        Button btnNext = (Button) findViewById(R.id.button);


        /**
         * NEXT and SAVE BUTTON
         */
        // btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = rbtngroup.getCheckedRadioButtonId();
                selected = (RadioButton) findViewById(selectedId);

                if (selected == null) {
                    lib.showError(q802.this, "Q802 Error", "Please select an option q802");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    int selectedId1 = rbtngroup1.getCheckedRadioButtonId();
                    selected1 = (RadioButton) findViewById(selectedId1);
                    if (selected1 == null && rbtn1.isChecked()) {
                        lib.showError(q802.this, "Q802a Error", "Please select an option for q802a");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {

                        if (rbtnaother.isChecked() && edtnaOther.length()==0) {
                            lib.showError(q802.this, "Q802a Error", "Please please specify or select another");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        }  else {

                            //Set Q802 and Q802a for the current individual

                            if (rbtn2.isChecked()) {
                                individual.setQ802(selected.getText().toString().substring(0, 1));
                                individual.setQ802a(null);
                                individual.setQ802aOther(null);
                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                myDB.close();

                                Intent intent = new Intent(q802.this, q803.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);

                            } else {

                                individual.setQ802(selected.getText().toString().substring(0, 1));
                                individual.setQ802a(selected1.getText().toString().substring(0, 1));
                                individual.setQ802aOther(edtnaOther.getText().toString());

                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                myDB.close();


                                Intent intent = new Intent(q802.this, q803.class);
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
                Intent intent = new Intent(q802.this, q801.class);
                intent.putExtra("Individual", individual);
                startActivity(intent);
            }


        });
    }

    public void onRadioButtonClicked(View v) {

        TextView q802atext = findViewById(R.id.q802a_other);
        RadioGroup rg1 = (RadioGroup) findViewById(R.id.q802radioGroupa);
        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q802_1:
                if (checked)
                    rbtna2.setEnabled(true);
                rbtna3.setEnabled(true);
                rbtna4.setEnabled(true);
                rbtna5.setEnabled(true);
                rbtnaother.setEnabled(true);
                txt1.setTextColor(Color.BLACK);


                break;


            case R.id.q802_2:
                if (checked)

                    rbtna2.setEnabled(false);
                rbtna3.setEnabled(false);
                rbtna4.setEnabled(false);
                rbtna5.setEnabled(false);
                rbtnaother.setEnabled(false);
                edtnaOther.setVisibility(View.INVISIBLE);
                edtnaOther.setText("");
                rbtna2.setChecked(false);
                rbtna3.setChecked(false);
                rbtna4.setChecked(false);
                rbtna5.setChecked(false);
                rbtnaother.setEnabled(false);
                txt1.setTextColor(Color.LTGRAY);



                break;
            case R.id.q802a_2:
                if (checked)
                    edtnaOther.setVisibility(View.INVISIBLE);
                edtnaOther.setText("");
                rbtnaother.setChecked(false);


                break;
            case R.id.q802a_3:
                if (checked)
                    edtnaOther.setVisibility(View.INVISIBLE);
                edtnaOther.setText("");
                rbtnaother.setChecked(false);


                break;
            case R.id.q802a_4:
                if (checked)
                    edtnaOther.setVisibility(View.INVISIBLE);
                edtnaOther.setText("");
                rbtnaother.setChecked(false);


                break;
            case R.id.q802a_5:
                if (checked)
                    edtnaOther.setVisibility(View.INVISIBLE);
                edtnaOther.setText("");
                rbtnaother.setChecked(false);


                break;
            case R.id.q802a_other:
                if (checked)
                    edtnaOther.setVisibility(View.VISIBLE);


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
                alertDialogBuilder.setMessage(" Are you sure you want to pause the interview");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(getApplicationContext(), started_household.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q802.this).toBundle());

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






