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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class P14 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected RadioButton selectedRbtn;
    protected RadioButton rbtn1,rbtn2,rbtn3, rbtn4, rbtn5, rbtn6,rbtn7,rbtn8,rbtn9, selected=null;
    protected RadioGroup rbtngroup;
    protected DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p14);

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        setTitle("P14 ECONOMIC ACTIVITY");
        lib = new LibraryClass();
        final RadioGroup rg = (RadioGroup) findViewById(R.id.P14radioGroup);

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        int p=0;






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


        if(thisHouse.next!=null && p1.getP14()!=null){
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];


            RadioButton[] bt = new RadioButton[8];
            for(int f=0;f<rg.getChildCount();f++)
            {
                View o = rg.getChildAt(f);
                if (o instanceof RadioButton)
                {
                    bt[f]=((RadioButton)o);
                    if(p1.getP14()!= null)
                    {
                        if(Integer.parseInt(p1.getP14())==f+1)
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
        else if(thisHouse.previous!=null && p1.getP14()!=null) {

            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];


            RadioButton[] bt = new RadioButton[8];
            for(int f=0;f<rg.getChildCount();f++)
            {
                View o = rg.getChildAt(f);
                if (o instanceof RadioButton)
                {
                    bt[f]=((RadioButton)o);
                    if(p1.getP14()!= null)
                    {
                        if(Integer.parseInt(p1.getP14())==f+1)
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


            TextView textView = (TextView) findViewById(R.id.P14);
            String s = getResources().getString(R.string.P14);
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

            /**
             * NEXT BUTTON
             */
            btnNext.setText(btnLabel);
            btnNext.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int selectedId =  rg.getCheckedRadioButtonId();
                    selectedRbtn = (RadioButton) findViewById(selectedId);


                    if(selectedRbtn==null)
                    {
                        lib.showError(P14.this,"P14 Error","What was"+ p1.getP01() + " 'S mainly working as during the past 7 days?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else{
                        thisHouse.getPersons()[p1.getLineNumber()].setP14(selectedRbtn.getText().toString().substring(0,1));
                        thisHouse.setCurrent(String.valueOf(p1.getSRNO()));
                        thisHouse.next = String.valueOf(p1.getSRNO());

                        myDB = new DatabaseHelper(P14.this);
                        myDB.onOpen(myDB.getWritableDatabase());

                        //UPDATE HOUSEHOLD
                        List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                        if(ll.size()>0){
                            myDB.updateRoster(thisHouse,"P14",p1.getP14(), String.valueOf(p1.getSRNO()));
                            myDB.close();
                        }

                        Intent intent = new Intent(P14.this,P15.class);
                        intent.putExtra("Household",  thisHouse);
                        startActivity(intent);
                    }




                }
            });


            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    thisHouse.previous = String.valueOf(p1.getSRNO());

                    Log.d("P13 Prev", thisHouse.previous);
                    finish();

                    Intent intent = new Intent(P14.this, P12.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);
                }
            });






    }



}






