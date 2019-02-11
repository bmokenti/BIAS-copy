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
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class P16 extends AppCompatActivity implements Serializable {
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
        setContentView(R.layout.activity_p16);

        lib = new LibraryClass();

        setTitle("P16 INDUSTRY");

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        thisHouse.setIsHIVTB40("2");

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Sample sample = myDB.getSample(myDB.getReadableDatabase(),thisHouse.getAssignment_ID());

        final EditText edt = (EditText)findViewById(R.id.P16_txt);
        /**
         * Loop through the house hold members to check if hh member 's P02 is answered
         * If P02 is null then ask the individual
         */


            p1 = thisHouse.getPersons()[Integer.valueOf(thisHouse.getCurrent())];

        List<PersonRoster> list = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
        thisHouse.setHouseHoldeMembers(list.toArray(thisHouse.getHouseHoldeMembers()));

        if(thisHouse.next!=null){
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];

        }else if(thisHouse.previous!=null){
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];

        }

        if(thisHouse.next!=null && p1.getP16()!=null){
            edt.setText(p1.getP16());
        }else if(thisHouse.previous!=null && p1.getP16()!=null){
            edt.setText(p1.getP16());
        }





            TextView textView = (TextView) findViewById(R.id.P16txt);
            String s = getResources().getString(R.string.P16);
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
                        lib.showError(P16.this,"P16 Error","What was the main product,service or activity at "+p1.getP01()+"place of work?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    }
                    else{

                        //Set P02 fir the current individual
                        thisHouse.getPersons()[p1.getLineNumber()].setP16(edt.getText().toString());
                        //Restart the current activity for next individual

                        myDB = new DatabaseHelper(P16.this);
                        myDB.onOpen(myDB.getWritableDatabase());

                        //UPDATE HOUSEHOLD
                        List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                        if(ll.size()>0){
                            myDB.updateRoster(thisHouse,"P16",p1.getP16(), String.valueOf(p1.getSRNO()));
                            myDB.close();
                        }


                        int total = thisHouse.getTotalPersons();

                        if(thisHouse.getPersons()[total-1].getSRNO() == p1.getSRNO())
                        {

                            if(sample.getStatusCode().equals("1") )
                            {



                                //HIV ONLY
                                Intent intent = new Intent(P16.this, P17.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent);
                            }
                            else if(sample.getStatusCode().equals("2") )
                            {
                                if(thisHouse.getIsHIVTB40().equals("2")){
                                    //TB ONLY
                                    Intent intent = new Intent(P16.this, P18.class);
                                    intent.putExtra("Household", thisHouse);
                                    startActivity(intent);
                                }else{
                                    //HIV+TB
                                    Intent intent = new Intent(P16.this, P19.class);
                                    intent.putExtra("Household", thisHouse);
                                    startActivity(intent);
                                }



                            }
                            else{
                                //TB ONLY
                                Intent intent = new Intent(P16.this, P18.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent);

                            }



                        }else{

                            //Next question P17
                            thisHouse.next = String.valueOf(p1.getSRNO() + 1);

                            Intent intent = new Intent(P16.this, P12.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);

                        }




                    }





                }
            });

            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    thisHouse.previous = String.valueOf(p1.getSRNO());


                    finish();

                    Intent intent = new Intent(P16.this, P15.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);
                }
            });
        }


}







