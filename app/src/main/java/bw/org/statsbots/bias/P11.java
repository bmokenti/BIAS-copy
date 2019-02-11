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
import java.util.List;

public class P11 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected EditText edt;
    protected DatabaseHelper myDB;
    TextView q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p11);


        setTitle("P11: Field of education");
        edt = (EditText) findViewById(R.id.P11_fieldOfEducation);
        lib = new LibraryClass();

        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        // p1 = thisHouse.getPersons()[0];

        //***************************Read Roster from Database and load it into Object thisHouse
        List<PersonRoster> list = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
        thisHouse.setHouseHoldeMembers(list.toArray(thisHouse.getHouseHoldeMembers()));

        if(thisHouse.next!=null){
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];

        }else if(thisHouse.previous!=null){
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];

        }

        int p = 0;

        if(p1.getP11()!=null) {
            edt.setText(p1.getP11());
        }


            /**
             * Loop through the house hold members to check if hh member 's P02 is answered
             * If P02 is null then ask the individual
             */




        q= findViewById(R.id.P11txt);
        String s = q.getText().toString();

        int t = s.indexOf("#");
        s = s.replace("#", "<b>" + p1.getP01() +"</b> ");
        q.setText(Html.fromHtml(s));







            /**
             * NEXT Person BUTTON
             */
            Button btnNext = (Button) findViewById(R.id.button);
            Button btnPrev = (Button) findViewById(R.id.button3);


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

                        if(p1.getLineNumber() == thisHouse.getTotalPersons()-1) {
                            thisHouse.next =String.valueOf(0);
                            thisHouse.previous = String.valueOf(thisHouse.getTotalPersons()-1);

                            myDB = new DatabaseHelper(P11.this);
                            myDB.onOpen(myDB.getWritableDatabase());

                            //UPDATE HOUSEHOLD
                            List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                            if(ll.size()>0){
                                myDB.updateRoster(thisHouse,"P11",p1.getP11(), String.valueOf(p1.getSRNO()));
                                myDB.close();
                            }

                            Log.d("Education ","Last person  " + p1.getP10());
                            Intent intent = new Intent(P11.this, P12.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);

                        }
                        else{
                            thisHouse.next = String.valueOf(p1.getSRNO() + 1);

                            myDB = new DatabaseHelper(P11.this);
                            myDB.onOpen(myDB.getWritableDatabase());

                            //UPDATE HOUSEHOLD
                            List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                            if(ll.size()>0){
                                myDB.updateRoster(thisHouse,"P11",p1.getP11(), String.valueOf(p1.getSRNO()));
                                myDB.close();
                            }

                            Log.d("Education ","Last person  " + p1.getP10());
                            Intent intent = new Intent(P11.this, P09.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);





                        }






                            Intent intent = new Intent(P11.this,P09.class);
                        intent.putExtra("Household",  thisHouse);
                        startActivity(intent);

                    }


                }
            });


            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //thisHouse.previous = String.valueOf(p1.getSRNO());

                    Log.d("P10 Prev", thisHouse.previous);
                    finish();

                    Intent intent = new Intent(P11.this, P10.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);
                }
            });
        }
    }


