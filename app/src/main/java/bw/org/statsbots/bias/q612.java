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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.List;

public class q612 extends AppCompatActivity  implements Serializable{
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected Individual individual;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected RadioButton rbtn1,rbtn2,rbtn9,rbtna1, rbtna2, rbtna9, rbtnaOther, selected, selected1;
    protected RadioGroup rbtngroup, rbtngroup1;
    protected EditText edt;
    TextView q612atext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q612);



        setTitle("q612 HIV PREGNANCY TRANSMISSION");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q612_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q612_2);
        rbtn9 =  (RadioButton)findViewById(R.id.q612_9);
        rbtngroup = (RadioGroup)findViewById(R.id.q612radioGroup) ;


        rbtngroup1 = (RadioGroup)findViewById(R.id.q612radioGroupa) ;
        rbtna1 =  (RadioButton)findViewById(R.id.q612a_1);
        rbtna2 =  (RadioButton)findViewById(R.id.q612a_2);
        rbtna9 =  (RadioButton)findViewById(R.id.q612a_9);
        rbtnaOther =  (RadioButton)findViewById(R.id.q612a_other);

        edt = (EditText) findViewById(R.id.q612_other);
         q612atext = findViewById(R.id.q612atxt);

        //final int selectedId1 = rbtngroup1.getCheckedRadioButtonId();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

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


        RadioButton[] bt = new RadioButton[3];
        for(int f=0;f<rbtngroup.getChildCount();f++)
        {
            View o = rbtngroup.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ612()!= null &&  !ind.getQ612().equals(""))
                {
                    if(Integer.parseInt(ind.getQ612())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }


        if(  ind.getQ612aOther() != null )
        {
            if ( ind.getQ612a() != null &&  ind.getQ612a().equals("O") )
            {
                rbtnaOther.setChecked(true);
                edt.setText(ind.getQ612aOther());
                edt.setVisibility(View.VISIBLE);
            }
        }
        else
        {
        RadioButton[] bta = new RadioButton[4];
        for(int f=0;f<rbtngroup1.getChildCount();f++) {
            View o = rbtngroup1.getChildAt(f);
            if (o instanceof RadioButton) {
                bta[f] = ((RadioButton) o);
                if (ind.getQ612a() != null && !ind.getQ612a().equals("")) {
                    if (Integer.parseInt(ind.getQ612a()) == f + 1) {
                        RadioButton radioButton = bta[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }
        }

//        if(ind.getQ612aOther()!= null)
//        {
//            edt.setText(ind.getQ612aOther());
//        }


        if (individual.getQ612() != null &&( individual.getQ612().equals("2") ||  individual.getQ612().equals("9")) )
        {
            rbtna1.setEnabled(false);
            rbtna2.setEnabled(false);
            rbtna9.setEnabled(false);
            rbtnaOther.setEnabled(false);
            edt.setVisibility(View.INVISIBLE);
            edt.setText("");
            q612atext.setTextColor(Color.LTGRAY);
        }


        /**
         * NEXT question
         */
        Button btnNext = (Button)findViewById(R.id.button);


        /**
         * NEXT and SAVE BUTTON
         */
        // btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                int selectedId = rbtngroup.getCheckedRadioButtonId();
                selected = (RadioButton) findViewById(selectedId);

                if (selected == null) {
                    lib.showError(q612.this, "Q612 Error", "Please select a value for q612");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    int selectedId1 = rbtngroup1.getCheckedRadioButtonId();
                    selected1 = (RadioButton) findViewById(selectedId1);
                    if (selected1 == null && rbtn1.isChecked()) {

                        lib.showError(q612.this, "Q612a Error", "Please select a value for q612a");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {

                        if (selected1 == rbtnaOther && edt.length() == 0 || edt.getText() == null) {
                            lib.showError(q612.this, "Q612a Error", "Please specify");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {
                            if (rbtn2.isChecked() || rbtn9.isChecked()) {
                                individual.setQ612(selected.getText().toString().substring(0, 1));


                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateInd("Q612", individual.getAssignmentID(), individual.getBatch(), individual.getQ612(), String.valueOf(individual.getSRNO()));
                                myDB.updateInd("Q612a", individual.getAssignmentID(), individual.getBatch(), null, String.valueOf(individual.getSRNO()));
                                myDB.updateInd("Q612Other", individual.getAssignmentID(), individual.getBatch(), null, String.valueOf(individual.getSRNO()));

                                myDB.close();

                                Intent intent = new Intent(q612.this, q613.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);
                            } else {

                                //Set Q612 and Q612a for the current individual

                                individual.setQ612(selected.getText().toString().substring(0, 1));
                                individual.setQ612a(selected1.getText().toString().substring(0, 1));
                                individual.setQ612aOther(edt.getText().toString());

                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateInd("Q612", individual.getAssignmentID(), individual.getBatch(), individual.getQ612(), String.valueOf(individual.getSRNO()));
                                myDB.updateInd("Q612a", individual.getAssignmentID(), individual.getBatch(), individual.getQ612a(), String.valueOf(individual.getSRNO()));
                                myDB.updateInd("Q612Other", individual.getAssignmentID(), individual.getBatch(), individual.getQ612aOther(), String.valueOf(individual.getSRNO()));
                                myDB.close();

                                Intent intent = new Intent(q612.this, q613.class);
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
                Intent intent = new Intent(q612.this, q611.class);
                intent.putExtra("Individual", individual);
                startActivity(intent);
            }


        });
    }


    public void onRadioButtonClicked(View v) {


        RadioGroup rg1 = (RadioGroup) findViewById(R.id.q612radioGroupa);
        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q612_1:
                if (checked)
                    for (int i = 0; i < rbtngroup1.getChildCount(); i++) {
                        ((RadioButton) rbtngroup1.getChildAt(i)).setEnabled(true);
                        q612atext.setTextColor(Color.BLACK);

                    }
                break;


            case R.id.q612_2:
                if (checked)
                    for (int i = 0; i < rbtngroup1.getChildCount(); i++) {
                        ((RadioButton) rbtngroup1.getChildAt(i)).setEnabled(false);
                        rbtna1.setChecked(false);
                        rbtna2.setChecked(false);
                        rbtna9.setChecked(false);
                        rbtnaOther.setChecked(false);
                        edt.setVisibility(View.INVISIBLE);
                        edt.setText("");
                        q612atext.setTextColor(Color.LTGRAY);

                    }

                break;
            case R.id.q612_9:
                if (checked)
                    for (int i = 0; i < rbtngroup1.getChildCount(); i++) {
                        ((RadioButton) rbtngroup1.getChildAt(i)).setEnabled(false);
                        rbtna1.setChecked(false);
                        rbtna2.setChecked(false);
                        rbtna9.setChecked(false);
                        rbtnaOther.setChecked(false);
                        edt.setVisibility(View.INVISIBLE);
                        edt.setText("");
                        q612atext.setTextColor(Color.LTGRAY);

                    }
                break;
            case R.id.q612a_1:
                if (checked)

                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");

                break;
            case R.id.q612a_2:
                if (checked)

                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                break;
            case R.id.q612a_9:
                if (checked)

                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");

                break;
            case R.id.q612a_other:
                if (checked)

                    edt.setVisibility(View.VISIBLE);






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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q612.this).toBundle());

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







