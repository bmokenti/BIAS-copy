package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class p08 extends AppCompatActivity implements Serializable, View.OnClickListener {
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected RadioButton selected=null;

    protected DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p08);
        setTitle("P08 MARITAL STATUS");
        RadioButton rbtn1,rbtn2,rbtn3,rbtn4,rbtn5,rbtn6;
        RadioGroup rbtngroup = (RadioGroup)findViewById(R.id.p08radioGroup) ;

        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.P08_1);
        rbtn2 =  (RadioButton)findViewById(R.id.P08_2);
        rbtn3 =  (RadioButton)findViewById(R.id.P08_3);
        rbtn4 =  (RadioButton)findViewById(R.id.P08_4);
        rbtn5 =  (RadioButton)findViewById(R.id.P08_5);
        rbtn6 =  (RadioButton)findViewById(R.id.P08_6);


        rbtn1.setOnClickListener(this);
        rbtn2.setOnClickListener(this);
        rbtn3.setOnClickListener(this);
        rbtn4.setOnClickListener(this);
        rbtn5.setOnClickListener(this);
        rbtn6.setOnClickListener(this);

        final int selectedId = rbtngroup.getCheckedRadioButtonId();

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

        if(thisHouse.next!=null && Integer.parseInt(p1.getP04YY()) > 11)
        {

            //Next Members
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];
            thisHouse.setCurrentPerson(p1.getSRNO());

            if(p1.getP08()!=null) {

                RadioButton[] bt = new RadioButton[6];
                for(int f=0;f<rbtngroup.getChildCount();f++)
                {
                    View o = rbtngroup.getChildAt(f);
                    if (o instanceof RadioButton)
                    {
                        bt[f]=((RadioButton)o);
                        if(p1.getP08()!= null)
                        {
                            if(Integer.parseInt(p1.getP08())==f+1)
                            {

                                RadioButton radioButton = bt[f];
                                radioButton.setChecked(true);
                                selected = radioButton;
                                break;
                            }
                        }
                    }

                }
            }
        }

        else if(thisHouse.previous!=null && Integer.parseInt(p1.getP04YY()) > 11)
        {
            //Next Members
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];

            RadioButton[] bt = new RadioButton[6];
            for(int f=0;f<rbtngroup.getChildCount();f++)
            {
                View o = rbtngroup.getChildAt(f);
                if (o instanceof RadioButton)
                {
                    bt[f]=((RadioButton)o);
                    if(p1.getP08()!= null)
                    {

                        if(Integer.parseInt(p1.getP08())==f+1)
                        {

                            RadioButton radioButton = bt[f];
                            radioButton.setChecked(true);
                            selected = radioButton;
                            break;
                        }
                    }
                }

            }



        }
        else {
            if (thisHouse.next != null && Integer.parseInt(p1.getP04YY()) < 12) {
                //Usuall visitor skip ---- next person behavior
                if (p1.getLineNumber() == thisHouse.getTotalPersons() - 1) {
                    thisHouse.next = String.valueOf(0);
                    thisHouse.previous = String.valueOf(thisHouse.getTotalPersons() - 1);
                    finish();

                    //Next question P17
                    Intent intent = new Intent(p08.this, P09.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);

                } else {
                    //Restart the current activity for next individual
                    if (p1.getSRNO() >= 0 && p1.getSRNO() < thisHouse.getPersons().length) {

                        thisHouse.next = String.valueOf(p1.getSRNO() + 1);

                        //Restart the current activity for next individual
                        finish();

                        Intent intent = new Intent(p08.this, p08.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);
                    }

                }

            } else if (thisHouse.previous != null && Integer.parseInt(p1.getP04YY()) < 12) {
                //Usuall visitor skip ---- next person behavior

                if (p1.getSRNO() == 0) {
                    //Next question P03
                    //thisHouse.previous=String.valueOf(p1.getLineNumber());//set previous to last person covered
                    thisHouse.previous = String.valueOf(thisHouse.getTotalPersons() - 1);
                    thisHouse.next = null;
                    finish();

                    Intent intent = new Intent(p08.this, P07.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);

                } else if (p1.getSRNO() >= 0 && p1.getSRNO() < thisHouse.getPersons().length) {
                    //Restart the current activity for previous individual
                    int n = p1.getSRNO() - 1;
                    thisHouse.previous = String.valueOf(n);

                    finish();

                    Intent intent = new Intent(p08.this, p08.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);

                }

            }
        }




        TextView textView = (TextView) findViewById(R.id.P08);
            String s = getResources().getString(R.string.P08);
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
                    if(selected==null)
                    {
                        lib.showError(p08.this,"P08 Error","Please select marital status");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else{

                        int age = Integer.parseInt(thisHouse.getPersons()[p1.getLineNumber()].getP04YY());

                        if(age<12){
                            lib.showError(p08.this,"P08 Error",thisHouse.getPersons()[p1.getLineNumber()].getP01() +"" +
                                    " is under 12 and cannot be married");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        }else{
                            thisHouse.getPersons()[p1.getLineNumber()].setP08(selected.getText().toString().substring(0,1));
                            p1.setP08(selected.getText().toString().substring(0,1));


                            //if(p1.getP02().equals("01") && p1.getP08().equals())


                            if(p1.getLineNumber() == thisHouse.getTotalPersons()-1)
                            {
                                thisHouse.next =String.valueOf(0);
                                thisHouse.previous = String.valueOf(thisHouse.getTotalPersons()-1);

                                myDB = new DatabaseHelper(p08.this);
                                myDB.onOpen(myDB.getWritableDatabase());

                                //UPDATE HOUSEHOLD
                                List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                if(ll.size()>0){
                                    //Log.d()
                                    myDB.updateRoster(thisHouse,"P08",p1.getP08(), String.valueOf(p1.getSRNO()));
                                    myDB.close();
                                }


                                //Next question P17
                                Intent intent = new Intent(p08.this, P09.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent);

                            }else{
                                //Restart the current activity for next individual
                                if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length) {

                                    thisHouse.next=String.valueOf(p1.getSRNO()+1);
                                    Log.d("Next Person ",thisHouse.next);
                                    myDB = new DatabaseHelper(p08.this);
                                    myDB.onOpen(myDB.getWritableDatabase());

                                    //UPDATE HOUSEHOLD
                                    List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                    if(ll.size()>0){
                                        Log.d("Inside DB",p1.getSRNO()+" >> " +p1.getP01()+ "" +p1.getP08() );
                                        myDB.updateRoster(thisHouse,"P08",p1.getP08(), String.valueOf(p1.getSRNO()));
                                        myDB.close();
                                    }
                                    //Restart the current activity for next individual
                                    finish();

                                    Intent intent = new Intent(p08.this,p08.class);
                                    intent.putExtra("Household",  thisHouse);
                                    startActivity(intent);



                                }

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

                        Intent intent = new Intent(p08.this,P07.class);
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

                        Intent intent = new Intent(p08.this, p08.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);

                    }




                }
            });





        }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.P08_1:
                selected = (RadioButton)findViewById(R.id.P08_1);
                break;
            case R.id.P08_2:
                selected = (RadioButton)findViewById(R.id.P08_2);
                break;
            case R.id.P08_3:
                selected = (RadioButton)findViewById(R.id.P08_3);
                break;
            case R.id.P08_4:
                selected = (RadioButton)findViewById(R.id.P08_4);
                break;
            case R.id.P08_5:
                selected = (RadioButton)findViewById(R.id.P08_5);
                break;
            case R.id.P08_6:
                selected = (RadioButton)findViewById(R.id.P08_6);
                break;
            default:
                break;
        }
    }
}






