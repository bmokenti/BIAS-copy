package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class P12 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected RadioButton selectedRbtn;
    protected DatabaseHelper myDB;
    PersonRoster p1=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p12);

        setTitle("P12 TYPE OF ECONOMIC ACTIVITY");
        final RadioGroup rg = (RadioGroup) findViewById(R.id.P12radioGroup);

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
            Log.d("p01",p1.getP01());

        }else if(thisHouse.previous!=null){
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];

        }

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(),thisHouse.getAssignment_ID());

        if(thisHouse.next!=null && p1.getP12()!=null){

            if(Integer.parseInt(p1.getP04YY()) > 11){
                p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];


                    RadioButton[] bt = new RadioButton[2];
                    for(int f=0;f<rg.getChildCount();f++)
                    {
                        View o = rg.getChildAt(f);
                        if (o instanceof RadioButton)
                        {
                            bt[f]=((RadioButton)o);
                            if(p1.getP12()!= null)
                            {
                                if(Integer.parseInt(p1.getP12())==f+1)
                                {
                                    RadioButton radioButton = bt[f];
                                    radioButton.setChecked(true);
                                    selectedRbtn = radioButton;
                                    break;
                                }
                            }
                        }
                    }
                }

        }
        else if(thisHouse.previous!=null && p1.getP12()!=null) {

            if(Integer.parseInt(p1.getP04YY()) > 11){

                    RadioButton[] bt = new RadioButton[2];
                    for(int f=0;f<rg.getChildCount();f++)
                    {
                        View o = rg.getChildAt(f);
                        if (o instanceof RadioButton)
                        {
                            bt[f]=((RadioButton)o);
                            if(p1.getP12()!= null)
                            {
                                if(Integer.parseInt(p1.getP12())==f+1)
                                {
                                    RadioButton radioButton = bt[f];
                                    radioButton.setChecked(true);
                                    selectedRbtn = radioButton;
                                    break;
                                }
                            }
                        }

                }
            }
        }else {

            if (thisHouse.next != null &&  Integer.parseInt(p1.getP04YY()) < 12) {

                  if (p1.getLineNumber() == thisHouse.getTotalPersons() - 1) {

                        thisHouse.next = String.valueOf(0);
                        thisHouse.previous = String.valueOf(thisHouse.getTotalPersons() - 1);

                        if(sample.getStatusCode().equals("1") )
                        {
                            //HIV ONLY
                            Intent intent = new Intent(P12.this, P17.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);
                        }
                        else if(sample.getStatusCode().equals("2") )
                        {
                            if (thisHouse.getHIVTB40().equals("0")) {
                                //TB ONLY
                                Intent intent = new Intent(P12.this, P18.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent);
                            } else {
                                //HIV+TB
                                Intent intent = new Intent(P12.this, P19.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent);
                            }
                        }
                        else{
                            //TB ONLY
                            Intent intent = new Intent(P12.this, P18.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);

                        }

                    } else {
                        //Restart the current activity for next individual
                        if (p1.getSRNO() >= 0 && p1.getSRNO() < thisHouse.getPersons().length) {

                            thisHouse.next = String.valueOf(p1.getSRNO() + 1);

                            //Restart the current activity for next individual
                            finish();

                            Intent intent = new Intent(P12.this, P12.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);
                        }

                    }






            }
            else if (thisHouse.previous != null && Integer.parseInt(p1.getP04YY()) < 12) {
                if (p1.getSRNO() == 0) {
                    //Next question P03
                    //thisHouse.previous=String.valueOf(p1.getLineNumber());//set previous to last person covered
                    thisHouse.previous = String.valueOf(thisHouse.getTotalPersons() - 1);
                    thisHouse.next = null;
                    finish();

                    Intent intent = new Intent(P12.this, P09.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);

                } else if (p1.getSRNO() >= 0 && p1.getSRNO() < thisHouse.getPersons().length) {
                    //Restart the current activity for previous individual
                    int n = p1.getSRNO() - 1;
                    thisHouse.previous = String.valueOf(n);

                    finish();

                    Intent intent = new Intent(P12.this, P12.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);

                }
            }




        }



            TextView textView = (TextView)findViewById(R.id.P12);
            String s = getResources().getString(R.string.P12);
            int t = s.indexOf("#");
            s=s.replace("#","<b>" +p1.getP01() + "</b> ");

            textView.setText(Html.fromHtml(s));

            /**
             * NEXT Person BUTTON
             */
            Button btnNext = (Button)findViewById(R.id.p12_btnnext);
            Button btnPrev = (Button)findViewById(R.id.p12_btnprev);
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
                    int selectedId =  rg.getCheckedRadioButtonId();
                    selectedRbtn = (RadioButton) findViewById(selectedId);

                    if(selectedRbtn==null)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(P12.this);
                        builder.setTitle("Select option");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("In the past 7 days did "+ p1.getP01() + "work for payment, profit or home use for at least 1 hour?");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Do nothing only when the Head of House is selected we proceed.

                            }
                        });

                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                        AlertDialog alertDialog =  builder.show();
                        final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                        positiveButtonLL.width=ViewGroup.LayoutParams.MATCH_PARENT;
                        positiveButton.setTextColor(Color.WHITE);
                        positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                        positiveButton.setLayoutParams(positiveButtonLL);


                    }else{
                        //Set P03 for the current individual
                        thisHouse.getPersons()[p1.getLineNumber()].setP12(selectedRbtn.getText().toString().substring(0,1));

                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        Log.d("Current Person: ",p1.getLineNumber() + "===" + p1.getP12());


                            //Restart the current activity for next individual
                                if(p1.getP12().equals("1"))
                                {
                                    thisHouse.setCurrent(String.valueOf(p1.getSRNO()));
                                    thisHouse.next = String.valueOf(p1.getSRNO());

                                    myDB = new DatabaseHelper(P12.this);
                                    myDB.onOpen(myDB.getWritableDatabase());

                                    //UPDATE HOUSEHOLD
                                    List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                    if(ll.size()>0){
                                        myDB.updateRoster(thisHouse,"P12",p1.getP12(), String.valueOf(p1.getSRNO()));
                                        myDB.updateRoster(thisHouse,"P13",null, String.valueOf(p1.getSRNO()));
                                        myDB.updateRoster(thisHouse,"P13Other",null, String.valueOf(p1.getSRNO()));
                                        myDB.close();
                                    }


                                    Intent intent = new Intent(P12.this,P14.class);
                                    intent.putExtra("Household",  thisHouse);
                                    startActivity(intent);
                                }
                                else
                                {

                                    thisHouse.setCurrent(String.valueOf(p1.getSRNO()));
                                    thisHouse.next = String.valueOf(p1.getSRNO());

                                    myDB = new DatabaseHelper(P12.this);
                                    myDB.onOpen(myDB.getWritableDatabase());

                                    //UPDATE HOUSEHOLD
                                    List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                    if(ll.size()>0){
                                        myDB.updateRoster(thisHouse,"P12",p1.getP12(), String.valueOf(p1.getSRNO()));
                                        myDB.close();
                                    }



                                    Intent intent = new Intent(P12.this,P13.class);
                                    intent.putExtra("Household",  thisHouse);
                                    Log.d("P12:: ",p1.getP12());
                                    startActivity(intent);
                                }
                    }
                }
            });

            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (p1.getSRNO() == 0) {
                        //Next question P03
                        //thisHouse.previous=String.valueOf(p1.getLineNumber());//set previous to last person covered
                        thisHouse.previous = String.valueOf(thisHouse.getTotalPersons() - 1);
                        thisHouse.next = null;
                        finish();

                        Intent intent = new Intent(P12.this, P09.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);

                    } else if (p1.getSRNO() >= 0 && p1.getSRNO() < thisHouse.getPersons().length) {
                        //Restart the current activity for previous individual
                        int n = p1.getSRNO() - 1;
                        thisHouse.previous = String.valueOf(n);
                        thisHouse.next = null;

                        finish();

                        Intent intent = new Intent(P12.this, P12.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);

                    }
                }
            });


    }
}
