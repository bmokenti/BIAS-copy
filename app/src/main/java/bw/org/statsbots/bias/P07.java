package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.Serializable;
import java.util.List;

public class P07 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected DatabaseHelper  myDB;
    protected EditText edt;
    protected activity_general_information assginfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p07);
        lib = new LibraryClass();

        setTitle("P07 PLACE OF USUAL RESIDENCE");


        final EditText edt = (EditText)findViewById(R.id.P07_days);

        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        //p1 = thisHouse.getPersons()[0];

        //***************************Read Roster from Database and load it into Object thisHouse
        List<PersonRoster> list = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
        thisHouse.setHouseHoldeMembers(list.toArray(thisHouse.getHouseHoldeMembers()));

        if(thisHouse.next!=null){
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];

        }else if(thisHouse.previous!=null){
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];

        }



        if(thisHouse.next!=null && p1.getP06().equals("3")) {
            //Next Members
            thisHouse.setCurrentPerson(p1.getSRNO());
            Log.d("Display: ",thisHouse.next);
            edt.setText(p1.getP07());


        }
        else if(thisHouse.previous!=null && p1.getP06().equals("3"))
        {
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];
            thisHouse.setCurrentPerson(p1.getSRNO());

            edt.setText(p1.getP07());
        }

        else if(!p1.getP06().equals("3"))
        {

            if(thisHouse.next!=null){
                //Usuall visitor skip ---- next person behavior
                if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){
                    thisHouse.next =String.valueOf(0);
                    thisHouse.previous = String.valueOf(thisHouse.getTotalPersons()-1);
                    finish();

                    //Next question P17
                    Intent intent = new Intent(P07.this, p08.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);

                }else{
                    //Restart the current activity for next individual
                    if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length) {

                        thisHouse.next=String.valueOf(p1.getSRNO()+1);
                        Log.d("Print skip to next person: ",thisHouse.next);
                        //Restart the current activity for next individual
                        finish();

                        Intent intent = new Intent(P07.this,P07.class);
                        intent.putExtra("Household",  thisHouse);
                        startActivity(intent);
                    }

                }



            }
            else if(thisHouse.previous!=null){
                //Usuall visitor skip ---- next person behavior

                if(p1.getSRNO() == 0)
                {
                    //Next question P03
                    //thisHouse.previous=String.valueOf(p1.getLineNumber());//set previous to last person covered
                    thisHouse.previous=String.valueOf(thisHouse.getTotalPersons()-1);
                    thisHouse.next=null;
                    finish();

                    Intent intent = new Intent(P07.this,P06.class);
                    intent.putExtra("Household",  thisHouse);
                    startActivity(intent);

                }
                else if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length)
                {
                    //Restart the current activity for previous individual
                    int n = p1.getSRNO()-1;
                    thisHouse.previous = String.valueOf(n);

                    finish();

                    Intent intent = new Intent(P07.this, P07.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);

                }




            }







        }


            TextView textView = (TextView) findViewById(R.id.P07);
            String s = getResources().getString(R.string.P07);
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
                    int Days;

                    String days = edt.getText().toString();

                    if(days.length()==0 )
                    {
                        lib.showError(P07.this,"P07 Error","Please select enter number of days for the visitor "+p1.getP01());
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    }
                    else{

                        //Set P02 fir the current individual
                        p1.setP07(days);
                        thisHouse.getPersons()[p1.getLineNumber()].setP07(days);
                        //Restart the current activity for next individual

                        Log.d("P07-SRNO",p1.getSRNO() + " == " + p1.getP07() );

                        if(p1.getLineNumber() == thisHouse.getTotalPersons()-1)
                        {

                            thisHouse.next =String.valueOf(0);
                            thisHouse.previous = String.valueOf(thisHouse.getTotalPersons()-1);

                            myDB = new DatabaseHelper(P07.this);
                            myDB.onOpen(myDB.getWritableDatabase());

                            //UPDATE HOUSEHOLD
                            List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                            if(ll.size()>0){
                                if(p1.getP06().equals("3")){
                                    myDB.updateRoster(thisHouse,"P07",p1.getP07(), String.valueOf(p1.getSRNO()));
                                    myDB.close();
                                }

                            }


                            //Next question P17
                            Intent intent = new Intent(P07.this, p08.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);

                        }else{
                            //Restart the current activity for next individual
                            if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length) {

                                thisHouse.next=String.valueOf(p1.getSRNO()+1);
                                myDB = new DatabaseHelper(P07.this);
                                myDB.onOpen(myDB.getWritableDatabase());

                                //UPDATE HOUSEHOLD
                                List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                if(ll.size()>0){
                                    if(p1.getP06().equals("3")) {
                                        myDB.updateRoster(thisHouse, "P07", p1.getP07(), String.valueOf(p1.getSRNO()));
                                        myDB.close();
                                    }
                                }
                                //Restart the current activity for next individual
                                finish();

                                Intent intent = new Intent(P07.this,P07.class);
                                intent.putExtra("Household",  thisHouse);
                                startActivity(intent);



                            }

                        }




                    }





                }
            });

            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(p1.getSRNO() == 0)
                    {
                        //Next question P03
                        //thisHouse.previous=String.valueOf(p1.getLineNumber());//set previous to last person covered
                        thisHouse.previous=String.valueOf(thisHouse.getTotalPersons()-1);
                        thisHouse.next=null;
                        finish();

                        Intent intent = new Intent(P07.this,P06.class);
                        intent.putExtra("Household",  thisHouse);
                        startActivity(intent);

                    }
                    else if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length)
                    {
                        //Restart the current activity for previous individual
                        int n = p1.getSRNO()-1;
                        thisHouse.previous = String.valueOf(n);
                        thisHouse.next=null;
                        finish();

                        Intent intent = new Intent(P07.this, P07.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);

                    }
                }
            });


        }

    }






