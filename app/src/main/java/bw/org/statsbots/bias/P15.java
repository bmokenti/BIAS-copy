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

public class P15 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected DatabaseHelper  myDB;
    protected EditText edt;
   // protected activity_general_information assginfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p15);


        lib = new LibraryClass();

        setTitle("P15 OCCUPATION");

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");

        final EditText edt = (EditText)findViewById(R.id.P15_txt);

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        /**
         * Loop through the house hold members to check if hh member 's P02 is answered
         * If P02 is null then ask the individual
         */

         p1= thisHouse.getPersons()[Integer.valueOf(thisHouse.getCurrent())];

        List<PersonRoster> list = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
        thisHouse.setHouseHoldeMembers(list.toArray(thisHouse.getHouseHoldeMembers()));

        if(thisHouse.next!=null){
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];

        }else if(thisHouse.previous!=null){
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];

        }

        if(thisHouse.next!=null && p1.getP15()!=null){
            edt.setText(p1.getP15());
        }else if(thisHouse.previous!=null && p1.getP15()!=null){
            edt.setText(p1.getP15());
        }



            TextView textView = (TextView) findViewById(R.id.P15txt);
            String s = getResources().getString(R.string.P15);
            int t = s.indexOf("#");
            s = s.replace("#", "<b>" + p1.getP01() +"</b> ");

            textView.setText(Html.fromHtml(s));


            /**
             * NEXT Person BUTTON
             */
            Button btnNext = (Button)findViewById(R.id.button);
            Button btnPrev = (Button)findViewById(R.id.button3);

            String btnLabel="";
            if(p1.getLineNumber()+1==thisHouse.getTotalPersons()){
                btnLabel="Next";
            }else{
                btnLabel="Next > ";
            }


            btnNext.setText(btnLabel);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    edt.getText().toString();

                    if(edt.length()==0 || edt.getText()==null)
                    {
                        lib.showError(P15.this,"P15 Error","What type of work did "+p1.getP01()+"do in the past 7 days?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    }
                    else{

                        //Set P02 fir the current individual
                        thisHouse.getPersons()[p1.getLineNumber()].setP15(edt.getText().toString());
                        //Restart the current activity for next individual
                        thisHouse.setCurrent(String.valueOf(p1.getSRNO()));


                        myDB = new DatabaseHelper(P15.this);
                        myDB.onOpen(myDB.getWritableDatabase());

                        //UPDATE HOUSEHOLD
                        List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                        if(ll.size()>0){
                            myDB.updateRoster(thisHouse,"P15",p1.getP15(), String.valueOf(p1.getSRNO()));
                            myDB.close();
                        }


                        Intent intent = new Intent(P15.this,P16.class);
                        intent.putExtra("Household",  thisHouse);
                        startActivity(intent);




                    }





                }
            });

            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    thisHouse.previous = String.valueOf(p1.getSRNO());


                    finish();

                    Intent intent = new Intent(P15.this, P14.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);
                }
            });

    }

}







