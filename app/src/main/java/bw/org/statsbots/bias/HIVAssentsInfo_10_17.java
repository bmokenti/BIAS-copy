package bw.org.statsbots.bias;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class HIVAssentsInfo_10_17  extends AppCompatActivity implements Serializable {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hivassents_info_10_17);

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

        setTitle("HIV Consents Information for Adults 64 + years");

//        if(Integer.valueOf(p1.getP04YY()) <=17 && sample.getStatusCode().equals("2") )
//        {
//            //setTitle("Questionnaire Assent age 15-17 years");
//        }




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
                if (Integer.valueOf(p1.getP04YY()) <= 14 ) {

                    Intent intent = new Intent(HIVAssentsInfo_10_17.this, HIVConsentOver64.class);
                    intent.putExtra("Individual", individual);
                    intent.putExtra("Personroster", p1);
                    startActivity(intent);

                }
                else {
                    if (Integer.valueOf(p1.getP04YY()) >= 15 && Integer.valueOf(p1.getP04YY()) <= 17) {
                        Intent intent = new Intent(HIVAssentsInfo_10_17.this, HIVAdultsConsent18Plus.class);
                        intent.putExtra("Individual", individual);
                        intent.putExtra("Personroster", p1);
                        startActivity(intent);
                    }
                }
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Integer.valueOf(p1.getP04YY()) <= 14) {

                    Intent intent = new Intent(HIVAssentsInfo_10_17.this, HIVConsentOver64.class);
                    intent.putExtra("Individual", individual);
                    intent.putExtra("Personroster", p1);
                    startActivity(intent);

                } else {
                    if (Integer.valueOf(p1.getP04YY()) >= 15 && Integer.valueOf(p1.getP04YY()) <= 17) {
                        Intent intent = new Intent(HIVAssentsInfo_10_17.this, HIVAdultsConsent18Plus.class);
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


                if (Integer.valueOf(p1.getP04YY()) <= 14 ) {

                    Intent intent = new Intent(HIVAssentsInfo_10_17.this, HIVConsentOver64.class);
                    intent.putExtra("Individual", individual);
                    intent.putExtra("Personroster", p1);
                    startActivity(intent);

                }
                else {
                    if (Integer.valueOf(p1.getP04YY()) >= 15 && Integer.valueOf(p1.getP04YY()) <= 17) {
                        Intent intent = new Intent(HIVAssentsInfo_10_17.this, HIVAdultsConsent18Plus.class);
                        intent.putExtra("Individual", individual);
                        intent.putExtra("Personroster", p1);
                        startActivity(intent);
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

