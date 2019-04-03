package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class Consents_Info_6wks_9years extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, selectedRbtn;
    protected RadioGroup rbtngroup;
    protected DatabaseHelper myDB;
    protected PersonRoster p1;
    Individual pp1 = null;
    TextView descText;
    protected Button btnnext, btnprev, btnCforms;
    ImageButton plus, minus;

    protected TextView txt_help_gest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consents__info_6wks_9years);


        lib = new LibraryClass();

        final RadioGroup rg = (RadioGroup) findViewById(R.id.indvradioGroup);
        btnnext = (Button) findViewById(R.id.btnnext);
        btnprev = (Button) findViewById(R.id.button3);




        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        Intent h = getIntent();
        thisHouse = (HouseHold) h.getSerializableExtra("Household");

        Intent r = getIntent();
        p1 = (PersonRoster) r.getSerializableExtra("Personroster");

        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());

        myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());

        Individual ind = myDB.getdataIndivisual(p1.getAssignmentID(),p1.getBatch(),p1.getSRNO());
        List<PersonRoster> pp = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());

        for (PersonRoster p2:pp
        ) {
            if(p2.getSRNO()==ind.getSRNO()){
                p1=p2;
            }
        }


        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), p1.getAssignmentID());
        sample.getSample();

        thisHouse = myDB.getHouseForUpdate(p1.getAssignmentID(),p1.getBatch()).get(0);


        if(Integer.valueOf(p1.getP04YY()) <=17 && sample.getStatusCode().equals("2") )
        {
            setTitle("Questionnaire Assent age 15-17 years");
        }


        if((Integer.valueOf(p1.getP04YY()) >=18 && Integer.valueOf(p1.getP04YY()) <=64) && sample.getStatusCode().equals("2") )
        {

        }

        if(Integer.valueOf(p1.getP04YY()) >64 && sample.getStatusCode().equals("2") )
        {

        }


        if(Integer.valueOf(p1.getP04YY()) <=17 && sample.getStatusCode().equals("3") )
        {

        }


        if(Integer.valueOf(p1.getP04YY()) <=17 && sample.getStatusCode().equals("1") )


        {

        }




        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();


        descText = (TextView) findViewById(R.id.description_text);
        plus = (ImageButton) findViewById(R.id.plus);
        minus = (ImageButton) findViewById(R.id.minus);
        btnCforms = (Button) findViewById(R.id.LoadConset);

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

        btnCforms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create Date Object
                if (Integer.valueOf(p1.getP04YY()) <= 9 ) {

                    Intent intent = new Intent(Consents_Info_6wks_9years.this, HIVParentalConsent6wks_9y.class);
                    intent.putExtra("Individual", individual);
                    intent.putExtra("Personroster", p1);
                    startActivity(intent);

                }
                else {
                    if (Integer.valueOf(p1.getP04YY()) >= 15 ) {
                        Intent intent = new Intent(Consents_Info_6wks_9years.this, HIVChildParentalConsent15_17.class);
                        intent.putExtra("Individual", individual);
                        intent.putExtra("Personroster", p1);
                        startActivity(intent);
                    }
                    else {
                        if (Integer.valueOf(p1.getP04YY()) >= 10 && Integer.valueOf(p1.getP04YY()) <= 14) {
                            Intent intent = new Intent(Consents_Info_6wks_9years.this, HIVParentalConsent10_14yrs.class);
                            intent.putExtra("Individual", individual);
                            intent.putExtra("Personroster", p1);
                            startActivity(intent);
                        }
                        else
                        {

                        }
                    }


                }
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Integer.valueOf(p1.getP04YY()) <= 9 ) {

                    Intent intent = new Intent(Consents_Info_6wks_9years.this, HIVParentalConsent6wks_9y.class);
                    intent.putExtra("Individual", individual);
                    intent.putExtra("Personroster", p1);
                    startActivity(intent);

                }
                else {
                    if (Integer.valueOf(p1.getP04YY()) >= 15) {
                        Intent intent = new Intent(Consents_Info_6wks_9years.this, HIVChildParentalConsent15_17.class);
                        intent.putExtra("Individual", individual);
                        intent.putExtra("Personroster", p1);
                        startActivity(intent);
                    } else {
                        if (Integer.valueOf(p1.getP04YY()) >= 10 && Integer.valueOf(p1.getP04YY()) <= 14) {
                            Intent intent = new Intent(Consents_Info_6wks_9years.this, HIVParentalConsent10_14yrs.class);
                            intent.putExtra("Individual", individual);
                            intent.putExtra("Personroster", p1);
                            startActivity(intent);
                        } else {

                        }
                    }
                }
                }
        });


        btnprev = findViewById(R.id.button3);

        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(p1.getP04YY()) <= 9 ) {

                    Intent intent = new Intent(Consents_Info_6wks_9years.this, HIVParentalConsent6wks_9y.class);
                    intent.putExtra("Individual", individual);
                    intent.putExtra("Personroster", p1);
                    startActivity(intent);

                }
                else {
                    if (Integer.valueOf(p1.getP04YY()) >= 15) {
                        Intent intent = new Intent(Consents_Info_6wks_9years.this, HIVChildParentalConsent15_17.class);
                        intent.putExtra("Individual", individual);
                        intent.putExtra("Personroster", p1);
                        startActivity(intent);
                    } else {
                        if (Integer.valueOf(p1.getP04YY()) >= 10 && Integer.valueOf(p1.getP04YY()) <= 14) {
                            Intent intent = new Intent(Consents_Info_6wks_9years.this, HIVParentalConsent10_14yrs.class);
                            intent.putExtra("Individual", individual);
                            intent.putExtra("Personroster", p1);
                            startActivity(intent);
                        } else {

                        }
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(IndividualQuestionaireConsent.this, started_household.class);
//        intent.putExtra("Household", thisHouse);
//        startActivity(intent);
    }

}


