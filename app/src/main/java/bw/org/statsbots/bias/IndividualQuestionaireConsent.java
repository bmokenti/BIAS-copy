package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class IndividualQuestionaireConsent extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, selectedRbtn;
    protected RadioGroup rbtngroup;
    protected DatabaseHelper myDB;
    protected PersonRoster p1;
    Individual pp1 = null;
    TextView descText;
    protected Button btnnext, btnprev;
    ImageButton plus, minus;

    protected TextView txt_help_gest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_questionaire_consent);



        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.indqc_1);
        rbtn2 = (RadioButton) findViewById(R.id.indqc_2);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.indvradioGroup);
        btnnext = (Button) findViewById(R.id.btnnext);
        btnprev = (Button) findViewById(R.id.button3);




        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
      //  int p = 0;

        Intent h = getIntent();
        thisHouse = (HouseHold) h.getSerializableExtra("Household");

        Intent r = getIntent();
        p1 = (PersonRoster) r.getSerializableExtra("Personroster");

        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());
        //myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(p1.getAssignmentID(),p1.getBatch());
        thisHous.get(0).getHIVTB40();



        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), thisHous.get(0).getAssignment_ID());
        sample.getStatusCode();

//        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), ind.getAssignmentID());
//        sample.getSTATUS();

        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;


        final List<PersonRoster> roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }

        thisHouse = myDB.getHouseForUpdate(p1.getAssignmentID(),p1.getBatch()).get(0);



        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getIndvQuestionnaireConsent()!= null &&  !ind.getIndvQuestionnaireConsent().equals(""))
                {
                    if(Integer.parseInt(ind.getIndvQuestionnaireConsent())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }else{
                    Log.d("h1333333 Lost Here","**********    " + ind.getQ101());
                }
            }
            else
            {
                Log.d("h13 Lost Here","**********");
            }
        }

        if( sample.getStatusCode().equals("1") && Integer.valueOf(p1.getP04YY()) >=65 )


        {
            Intent intent = new Intent(IndividualQuestionaireConsent.this, HIVConsentOver64.class);
            intent.putExtra("Individual", individual);
            intent.putExtra("Personroster", p1);
            startActivity(intent);

        }



        if(Integer.valueOf(p1.getP04YY()) <=17 && sample.getStatusCode().equals("2") )
        {
            setTitle("Questionnaire Assent age 15-17 years");
        }


        if((Integer.valueOf(p1.getP04YY()) >=18 && Integer.valueOf(p1.getP04YY()) <=64) && sample.getStatusCode().equals("2") )
        {
            setTitle("Individual  18 plus: HIV&TB");
        }

        if(Integer.valueOf(p1.getP04YY()) >64 && sample.getStatusCode().equals("2") )
        {
            setTitle("Questionnaire 65 plus (TB)");
        }


        if(Integer.valueOf(p1.getP04YY()) <=17 && sample.getStatusCode().equals("3") )
        {
            setTitle("Questionnaire Assent age 15-17 years");
        }


        if(Integer.valueOf(p1.getP04YY()) <=17 && sample.getStatusCode().equals("1") )


        {
            setTitle("Questionnaire Assent age 15-17 years");
        }

//        if(Integer.valueOf(p1.getP04YY()) >=65 && sample.getStatusCode().equals("1") )
//
//
//        {
//            Intent intent = new Intent(IndividualQuestionaireConsent.this, HIVConsentOver64.class);
//            intent.putExtra("Individual", individual);
//            intent.putExtra("Personroster", p1);
//            startActivity(intent);
//
//        }






        descText = (TextView) findViewById(R.id.description_text);
        plus = (ImageButton) findViewById(R.id.plus);
        minus = (ImageButton) findViewById(R.id.minus);

        plus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                plus.setVisibility(View.GONE);
                minus.setVisibility(View.VISIBLE);
                descText.setMaxLines(Integer.MAX_VALUE);


            }
        });

        minus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                minus.setVisibility(View.GONE);
                plus.setVisibility(View.VISIBLE);
                descText.setMaxLines(2);

            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Years;
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(IndividualQuestionaireConsent.this, "IndividualQuestionaireConsent Error", "Please give answer to consent");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                }
                    else {
                    if (rbtn2.isChecked()) {
                        individual.setIndvQuestionnaireConsent(selectedRbtn.getText().toString().substring(0, 1));


                        myDB = new DatabaseHelper(IndividualQuestionaireConsent.this);
                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();

                        if (myDB.checkIndividual(individual)) {
                            //Update
                            myDB.updateInd("IndvQuestionnaireConsent",individual.getAssignmentID(),individual.getBatch(),individual.getIndvQuestionnaireConsent(),String.valueOf(individual.getSRNO()));
                            myDB.close();
                        }

                        Intent intent = new Intent(IndividualQuestionaireConsent.this, HIVChildParentalConsent15_17.class);

                        intent.putExtra("Individual", individual);
                        intent.putExtra("Personroster", p1);
                        startActivity(intent);

                    } else {

                        individual.setIndvQuestionnaireConsent(selectedRbtn.getText().toString().substring(0, 1));
                        //Check if individual already been saved and update

                        myDB = new DatabaseHelper(IndividualQuestionaireConsent.this);
                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();


                        if (myDB.checkIndividual(individual)) {
                            //Update
                            myDB.updateInd("IndvQuestionnaireConsent",individual.getAssignmentID(),individual.getBatch(),individual.getIndvQuestionnaireConsent(),String.valueOf(individual.getSRNO()));
                            myDB.close();
                        } else {
                            //Insert
                           // myDB.insertIndividual(individual);

                        }

                        //Next question P17

                        Intent intent = new Intent(IndividualQuestionaireConsent.this, q101.class);
                        intent.putExtra("Individual", individual);
                        intent.putExtra("Personroster", p1);
                        startActivity(intent);


                    }
                }


            }
        });


       btnprev = findViewById(R.id.button3);

        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HouseHold thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

                Intent q1o2 = new Intent(IndividualQuestionaireConsent.this, started_household.class);
                q1o2.putExtra("Household", thisHouse);

                startActivity(q1o2);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(IndividualQuestionaireConsent.this, started_household.class);
        intent.putExtra("Household", thisHouse);
        startActivity(intent);
    }

}


