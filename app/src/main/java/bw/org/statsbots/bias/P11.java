package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class P11 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected EditText edt;
    TextView q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p11);


        setTitle("P11: Field of education");

        lib = new LibraryClass();

        //edittext = (EditText) findViewById(R.id.q102_years);
        //edittext.setVisibility(View.VISIBLE);

        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;

        /**
         * Loop through the house hold members to check if hh member 's P02 is answered
         * If P02 is null then ask the individual
         */
        for (int r = 0; r < thisHouse.getTotalPersons(); r++) {
            p1 = thisHouse.getPersons()[r];

            if(p1.getSRNO()==Integer.parseInt(thisHouse.getCurrent())){
                thisHouse.setCurrent(null);
                break;
            }

        }



        q= findViewById(R.id.P11txt);
        String s = q.getText().toString();

        int t = s.indexOf("#");
        s = s.replace("#", "<b>" + p1.getP01() +"</b> ");
        q.setText(Html.fromHtml(s));


        if (p1.getP11() == null) {


            edt = (EditText) findViewById(R.id.P11_fieldOfEducation);


            /**
             * NEXT Person BUTTON
             */
            Button btnNext = (Button) findViewById(R.id.button);


            //btnNext.setText(btnLabel);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edt.length() == 0 || edt.equals("")) {
                        lib.showError(P11.this, "P11 Error", "Please please type field of education");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        //individual.setQ102(edt.getText().toString());
                        //Set P02 fir the current individual
                        thisHouse.getPersons()[p1.getLineNumber()].setP11(edt.getText().toString());
                        //Restart the current activity for next individual
                        int total = thisHouse.getTotalPersons();

                        Intent intent = new Intent(P11.this,P09.class);
                        intent.putExtra("Household",  thisHouse);
                        startActivity(intent);

                    }


                }
            });

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
