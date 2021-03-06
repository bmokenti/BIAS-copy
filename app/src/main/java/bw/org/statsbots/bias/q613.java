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

public class q613 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected Individual individual;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected RadioButton rbtn1,rbtn2,rbtn9,rbtna1, rbtna2, rbtna3,rbtna9, rbtnaOther, selected, selected1;
    protected RadioGroup rbtngroup, rbtngroup1;
    protected EditText edt;
    protected TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q613);

        setTitle("q613 HIV BABY TRANSMISSION");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q613_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q613_2);
        rbtn9 =  (RadioButton)findViewById(R.id.q613_9);
        rbtngroup = (RadioGroup)findViewById(R.id.q613radioGroup) ;


        rbtngroup1 = (RadioGroup)findViewById(R.id.q613radioGroupa) ;
        rbtna1 =  (RadioButton)findViewById(R.id.q613a_1);
        rbtna2 =  (RadioButton)findViewById(R.id.q613a_2);
        rbtna3 =  (RadioButton)findViewById(R.id.q613a_3);
        rbtna9 =  (RadioButton)findViewById(R.id.q613a_9);
        rbtnaOther =  (RadioButton)findViewById(R.id.q613a_other);

        edt = (EditText) findViewById(R.id.q613_other);
        txt1 = (TextView)  findViewById(R.id.q613atxt);


                //final int selectedId1 = rbtngroup1.getCheckedRadioButtonId();
        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        //int p = 0;
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
                if(ind.getQ613()!= null )
                {
                    if(!ind.getQ613().equals(""))
                    {
                        if (f == 2) {
                            rbtn9.setChecked(true);
                        }
                    if(Integer.parseInt(ind.getQ613())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }
        }

        if(  ind.getQ613aOther() != null )
        {
            if(  !ind.getQ613aOther().equals("") ) {
            if (ind.getQ613a() != null && ind.getQ613a().equals("O")) {
                rbtnaOther.setChecked(true);
                edt.setText(ind.getQ613aOther());
                edt.setVisibility(View.VISIBLE);
            }
        }
        }
        else
        {

        RadioButton[] bta = new RadioButton[5];
        for(int f=0;f<rbtngroup1.getChildCount();f++)
        {
            View o = rbtngroup1.getChildAt(f);
            if (o instanceof RadioButton) {
                bta[f] = ((RadioButton) o);

                if (ind.getQ613a() != null ) {
                    if (!ind.getQ613a().equals("")){
                        if (f == 3) {
                            rbtna9.setChecked(true);
                        }
                    if (Integer.parseInt(ind.getQ613a()) == f + 1) {
                        RadioButton radioButton = bta[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
                }
            }
            }
        }




//        if(ind.getQ613aOther()!= null)
//        {
//            edt.setText(ind.getQ613aOther());
//        }

        if (individual.getQ613() != null &&( individual.getQ613().equals("2") ||  individual.getQ613().equals("9")) )
        {
            rbtna1.setEnabled(false);
            rbtna2.setEnabled(false);
            rbtna3.setEnabled(false);
            rbtna9.setEnabled(false);
            rbtnaOther.setEnabled(false);
            edt.setVisibility(View.INVISIBLE);
            edt.setText("");
            rbtna2.setChecked(false);
            rbtna3.setChecked(false);
            rbtna9.setChecked(false);
            rbtnaOther.setChecked(false);
            txt1.setTextColor(Color.LTGRAY);
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
                            lib.showError(bw.org.statsbots.bias.q613.this, "Q613 Error", "Please select an option for q613");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {
                            int selectedId1 = rbtngroup1.getCheckedRadioButtonId();
                            selected1 = (RadioButton) findViewById(selectedId1);

                            if (selected1 == null && rbtn1.isChecked()) {
                                lib.showError(bw.org.statsbots.bias.q613.this, "Q613a Error", "Please select an option for q613a");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {

                                if (rbtnaOther.isChecked() && edt.length() == 0 || edt.getText() == null) {
                                    lib.showError(bw.org.statsbots.bias.q613.this, "Q613a Error", "Please specify");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);
                                } else {


                                    if (rbtn2.isChecked() || rbtn9.isChecked()) {
                                        individual.setQ613(selected.getText().toString().substring(0, 1));

//                                        individual.setQ613a(null);
//                                        individual.setQ613aOther(null);

                                        myDB.onOpen(myDB.getReadableDatabase());
                                        myDB.getWritableDatabase();
                                        myDB.updateInd("Q613", individual.getAssignmentID(), individual.getBatch(), individual.getQ613(), String.valueOf(individual.getSRNO()));
                                        myDB.updateInd("Q613a", individual.getAssignmentID(), individual.getBatch(), null, String.valueOf(individual.getSRNO()));
                                        myDB.updateInd("Q613aOther", individual.getAssignmentID(), individual.getBatch(), null, String.valueOf(individual.getSRNO()));
                                        myDB.close();

                                        Intent intent = new Intent(q613.this, q614.class);
                                        intent.putExtra("Individual", individual);
                                        startActivity(intent);
                                    } else {

                                        individual.setQ613(selected.getText().toString().substring(0, 1));
                                        individual.setQ613a(selected1.getText().toString().substring(0, 1));
                                        individual.setQ613aOther(edt.getText().toString());

                                        myDB.onOpen(myDB.getReadableDatabase());
                                        myDB.getWritableDatabase();
                                        myDB.updateInd("Q613", individual.getAssignmentID(), individual.getBatch(), individual.getQ613(), String.valueOf(individual.getSRNO()));
                                        myDB.updateInd("Q613a", individual.getAssignmentID(), individual.getBatch(), individual.getQ613a(), String.valueOf(individual.getSRNO()));
                                        if(rbtnaOther.isChecked()) {
                                            myDB.updateInd("Q613aOther", individual.getAssignmentID(), individual.getBatch(), individual.getQ613aOther(), String.valueOf(individual.getSRNO()));

                                        }else
                                        {
                                            myDB.updateInd("Q613aOther", individual.getAssignmentID(), individual.getBatch(), null, String.valueOf(individual.getSRNO()));

                                        }

                                        myDB.close();
                                        //Next question q614


                                        Intent intent = new Intent(q613.this, q614.class);
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
                Intent intent = new Intent(q613.this, q612.class);
                intent.putExtra("Individual", individual);
                startActivity(intent);
            }


        });
    }



    public void onRadioButtonClicked(View v) {

        TextView q613atxt = findViewById(R.id.q613a_other);
        RadioGroup rg1 = (RadioGroup) findViewById(R.id.q613radioGroupa);
        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q613_1:
                if (checked)
                    rbtna1.setEnabled(true);
                    rbtna2.setEnabled(true);
                rbtna3.setEnabled(true);
                rbtna9.setEnabled(true);
                rbtnaOther.setEnabled(true);
                txt1.setTextColor(Color.BLACK);


                break;


            case R.id.q613_2:
                if (checked)

                    rbtna1.setEnabled(false);
                    rbtna2.setEnabled(false);
                rbtna3.setEnabled(false);
                rbtna9.setEnabled(false);
                rbtnaOther.setEnabled(false);
                edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                rbtna2.setChecked(false);
                rbtna3.setChecked(false);
                rbtna9.setChecked(false);
                rbtnaOther.setChecked(false);
                txt1.setTextColor(Color.LTGRAY);


            case R.id.q613_9:
                if (checked)

                    rbtna1.setEnabled(false);
                rbtna2.setEnabled(false);
                rbtna3.setEnabled(false);
                rbtna9.setEnabled(false);
                rbtnaOther.setEnabled(false);
                edt.setVisibility(View.INVISIBLE);
                edt.setText("");
                rbtna2.setChecked(false);
                rbtna3.setChecked(false);
                rbtna9.setChecked(false);
                rbtnaOther.setChecked(false);
                txt1.setTextColor(Color.LTGRAY);


                break;
            case R.id.q613a_1:
                if (checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");


                break;
            case R.id.q613a_2:
                if (checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");


                break;
            case R.id.q613a_3:
                if (checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");


                break;
            case R.id.q613a_9:
                if (checked)
                    edt.setVisibility(View.INVISIBLE);
                edt.setText("");


                break;
            case R.id.q613a_other:
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q613.this).toBundle());

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
