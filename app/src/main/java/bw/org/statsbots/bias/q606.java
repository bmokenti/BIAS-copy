package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class q606 extends AppCompatActivity implements Serializable{

    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected Individual indv;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected Individual individual;
    protected RadioButton rbtn1,rbtn2,rbtn9,  selected;
    protected RadioGroup rbtngroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q606);
        setTitle("q606 HIV and TB FACTS AND MYTHS");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q606_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q606_2);
        rbtn9 =  (RadioButton)findViewById(R.id.q606_9);
        rbtngroup = (RadioGroup)findViewById(R.id.q606radioGroup) ;



        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
      //  int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();


        //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());



        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

//        if((Integer.valueOf(individual.getQ102()) > 64 && (sample.getStatusCode().equals("2"))))
//        {
//
//            Intent q1o2 = new Intent(q606.this, q616.class);
//            q1o2.putExtra("Individual", individual);
//            startActivity(q1o2);
//        }


        //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());


        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }




        if (individual.getQ601().equals("2") &&   sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1")){

            Intent intent = new Intent(q606.this, q616.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }


        if (individual.getQ601().equals("2") &&   ((sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1"))
                || sample.getStatusCode().equals("1")  ) && (Integer.valueOf(individual.getQ102()) <= 24 )
                && (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 ) ){

            Intent intent = new Intent(q606.this, q623.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }

//        if (individual.getQ601().equals("2") && (sample.getStatusCode().equals("1") ||
//                (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") &&
//                (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 ))))
//
//       {
//
//            Intent intent = new Intent(q606.this, q623.class);
//            intent.putExtra("Individual", individual);
//            startActivity(intent);
//
//        } else {
//            //do nothing
//        }

        if ( ((Integer.valueOf(individual.getQ102()) > 24 && Integer.valueOf(individual.getQ102()) <=64) && individual.getQ601().equals("1")
                && (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") )) || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") &&
                (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 ))|| (sample.getStatusCode().equals("1"))) {

            Intent intent = new Intent(q606.this, q611.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }

        RadioButton[] bt = new RadioButton[3];
        for(int f=0;f<rbtngroup.getChildCount();f++)
        {
            View o = rbtngroup.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ606()!= null &&  !ind.getQ606().equals(""))
                {
                    if(Integer.parseInt(ind.getQ606())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
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
                    lib.showError(q606.this, "Q606 Error", "Please select an option for q606");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    individual.setQ606(selected.getText().toString().substring(0,1));
                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.getWritableDatabase();
                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                    myDB.close();

                    Intent intent = new Intent(q606.this, q607.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);

                }
            }


        });


        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (individual.getQ604().equals("2")) {

                    Intent intent = new Intent(q606.this, q604.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                    finish();
                } else {
                    q606.super.onBackPressed();
                    finish();
                }
            }

        });
    }

}










