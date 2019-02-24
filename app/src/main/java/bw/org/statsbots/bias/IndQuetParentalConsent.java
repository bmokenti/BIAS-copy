package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class IndQuetParentalConsent extends Activity implements Serializable {


    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, selectedRbtn;
    protected RadioGroup rg;
    protected DatabaseHelper myDB;
    protected PersonRoster p1 ;
  // protected Individual pp1 ;
    TextView descText;

    protected Button btnnext, btnprev;
    ImageButton plus, minus;

    protected TextView txt_help_gest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ind_quet_parental_consent);

        setTitle("Indv Ques Parental Consent 10-14 years");

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        Intent h = getIntent();
        thisHouse = (HouseHold) h.getSerializableExtra("Household");

        Intent r = getIntent();
        p1 = (PersonRoster) r.getSerializableExtra("Personroster");

        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());


        lib = new LibraryClass();
            rbtn1 = (RadioButton) findViewById(R.id.indqc_1);
            rbtn2 = (RadioButton) findViewById(R.id.indqc_2);




         rg = (RadioGroup) findViewById(R.id.indvradioGroup);

            btnnext = (Button) findViewById(R.id.btnnext);
            btnprev = (Button) findViewById(R.id.button3);

            descText = (TextView) findViewById(R.id.description_text);







        myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());


        if(Integer.valueOf(p1.getP04YY()) >= 18 )
        {
            Intent intent = new Intent(IndQuetParentalConsent.this, IndividualQuestionaireConsent.class);
            intent.putExtra("Individual", individual);
            intent.putExtra("Personroster", p1);
            startActivity(intent);

        }





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
                        lib.showError(IndQuetParentalConsent.this, "IndividualQuestionaireConsent Error", "Please give answer to consent");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    } else {
                        if (rbtn2.isChecked()) {

                            individual.setIndvQuestionnairePConsent15_17(selectedRbtn.getText().toString().substring(0, 1));
                            //Check if individual already been saved and update

                            myDB = new DatabaseHelper(IndQuetParentalConsent.this);
                             myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();

                             if (myDB.checkIndividual(individual)) {
                            //Update
                                 myDB.updateInd("IndvQuestionnairePConsent15_17",individual.getAssignmentID(),individual.getBatch(),individual.getIndvQuestionnairePConsent15_17(),String.valueOf(individual.getSRNO()));

                            }


                            //Next question P17

                            Intent intent = new Intent(IndQuetParentalConsent.this, HIVParentalConsent6wks_9y.class);
                            intent.putExtra("Individual", individual);
                            intent.putExtra("Personroster", p1);
                            startActivity(intent);


                        } else {
                            individual.setIndvQuestionnairePConsent15_17(selectedRbtn.getText().toString().substring(0, 1));
                            //Check if individual already been  saved and update
                            myDB = new DatabaseHelper(IndQuetParentalConsent.this);
                             myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();

                            if (myDB.checkIndividual(individual)) {
                            //Update
                                myDB.updateInd("IndvQuestionnairePConsent15_17",individual.getAssignmentID(),individual.getBatch(),individual.getIndvQuestionnairePConsent15_17(),String.valueOf(individual.getSRNO()));

                            }


                            //Next question P17

                            Intent intent = new Intent(IndQuetParentalConsent.this, IndividualQuestionaireConsent.class);
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
                    IndQuetParentalConsent.super.onBackPressed();
                }


            });
        }

    }


