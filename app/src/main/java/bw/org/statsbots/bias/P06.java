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

public class P06 extends AppCompatActivity implements Serializable, View.OnClickListener {
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected  RadioButton rbtn1,rbtn2,rbtn3, selected=null;
    protected RadioGroup rbtngroup;
    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p06);
        setTitle("P06 PLACE OF USUAL RESIDENCE");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.P06_1);
        rbtn2 =  (RadioButton)findViewById(R.id.P06_2);
        rbtn3 =  (RadioButton)findViewById(R.id.P06_3);
        rbtngroup = (RadioGroup)findViewById(R.id.p06radioGroup) ;

        rbtn1.setOnClickListener(this);
        rbtn2.setOnClickListener(this);
        rbtn3.setOnClickListener(this);

        final int selectedId = rbtngroup.getCheckedRadioButtonId();

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


        //Disable for head of House

        if(p1.getP02().equals("00")){

            rbtn1.setChecked(true);
            rbtn2.setEnabled(false);
            rbtn3.setEnabled(false);
            selected=rbtn1;
        }


        if(thisHouse.next!=null)
        {
            //Next Members
            p1=thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];
            thisHouse.setCurrentPerson(p1.getSRNO());

            if(p1.getP06()!=null){
                if(p1.getP02().equals("00")){
                    rbtn1.setChecked(true);
                    rbtn2.setEnabled(false);
                    rbtn3.setEnabled(false);
                    selected=rbtn1;
                }
                else
                    {
                    if(p1.getP06().equals("1"))
                    {
                        rbtn1.setChecked(true);
                        rbtn1.setEnabled(true);
                        rbtn2.setEnabled(true);
                        rbtn3.setEnabled(true);

                        selected=rbtn1;
                    }if(p1.getP06().equals("2"))
                    {
                        rbtn2.setChecked(true);
                        rbtn1.setEnabled(true);
                        rbtn2.setEnabled(true);
                        rbtn3.setEnabled(true);
                        selected=rbtn2;
                    }if(p1.getP06().equals("3"))
                    {
                        rbtn3.setChecked(true);
                        rbtn1.setEnabled(true);
                        rbtn2.setEnabled(true);
                        rbtn3.setEnabled(true);
                        selected=rbtn3;
                    }
                }


            }

        }

        else if(thisHouse.previous!=null)
        {
            //Prev Members
            p1=thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];
            thisHouse.setCurrentPerson(p1.getSRNO());

            if(p1.getP06()!=null)
            {
                if(p1.getP02().equals("00"))
                {
                    rbtn1.setChecked(true);
                    rbtn2.setEnabled(false);
                    rbtn3.setEnabled(false);
                    selected=rbtn1;
                }
                else
                {
                    if(p1.getP06().equals("1"))
                    {
                       rbtn1.setChecked(true);

                       rbtn1.setEnabled(true);
                       rbtn2.setEnabled(true);
                       rbtn3.setEnabled(true);
                       selected=rbtn1;
                    }
                    if(p1.getP06().equals("2"))
                    {
                       rbtn2.setChecked(true);

                       rbtn1.setEnabled(true);
                       rbtn2.setEnabled(true);
                       rbtn3.setEnabled(true);
                       selected=rbtn2;
                    }
                    if(p1.getP06().equals("3"))
                    {
                        rbtn3.setChecked(true);

                        rbtn1.setEnabled(true);
                        rbtn2.setEnabled(true);
                        rbtn3.setEnabled(true);
                        selected=rbtn3;
                    }
                }


            }

        }


            TextView textView = (TextView) findViewById(R.id.P06);
            String s = getResources().getString(R.string.P06);
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
                btnLabel="Next > "+ thisHouse.getPersons()[p1.getLineNumber()+1].getP01();
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
                            lib.showError(P06.this,"P06 Error","Please select Place of Usual Residence");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        }
                        else{

                            //Set P02 fir the current individual
                            p1.setP06(selected.getText().toString().substring(0,1));
                            thisHouse.getPersons()[p1.getLineNumber()].setP06(selected.getText().toString().substring(0,1));
                            //Restart the current activity for next individual
                            if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){
                                thisHouse.next =String.valueOf(0);
                                thisHouse.previous = String.valueOf(thisHouse.getTotalPersons()-1);

                                myDB = new DatabaseHelper(P06.this);
                                myDB.onOpen(myDB.getWritableDatabase());

                                //UPDATE HOUSEHOLD
                                List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                if(ll.size()>0){
                                    myDB.updateRoster(thisHouse,"P06",p1.getP06(), String.valueOf(p1.getSRNO()));
                                    myDB.close();
                                }
                                //Next question P07
                                Intent intent = new Intent(P06.this,P07.class);
                                intent.putExtra("Household",  thisHouse);
                                startActivity(intent);

                            }else{
                                if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length) {

                                    thisHouse.next=String.valueOf(p1.getSRNO()+1);
                                    myDB = new DatabaseHelper(P06.this);
                                    myDB.onOpen(myDB.getWritableDatabase());

                                    //UPDATE HOUSEHOLD
                                    List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                    if(ll.size()>0){
                                        Log.d("P06----",p1.getP06());
                                        myDB.updateRoster(thisHouse,"P06",p1.getP06(), String.valueOf(p1.getSRNO()));
                                        myDB.close();
                                    }
                                    //Restart the current activity for next individual
                                    finish();

                                    Intent intent = new Intent(P06.this,P06.class);
                                    intent.putExtra("Household",  thisHouse);
                                    startActivity(intent);


                                }
                                }
                            }

                        }});

            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("P05 PREV",p1.getSRNO()+"");
                    if(p1.getSRNO() == 0){
                        //Next question P03
                        //thisHouse.previous=String.valueOf(p1.getLineNumber());//set previous to last person covered
                        thisHouse.previous=String.valueOf(thisHouse.getTotalPersons()-1);
                        thisHouse.next=null;
                        finish();

                        Intent intent = new Intent(P06.this,P05.class);
                        intent.putExtra("Household",  thisHouse);
                        startActivity(intent);


                    }else if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length){
                        //Restart the current activity for previous individual
                        int n = p1.getSRNO()-1;
                        thisHouse.previous = String.valueOf(n);
                        thisHouse.next=null;
                        finish();

                        Intent intent = new Intent(P06.this, P06.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);


                    }
                }
            });











        }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.P06_1:
                selected = (RadioButton)findViewById(R.id.P06_1);
                break;
            case R.id.P06_2:
                selected = (RadioButton)findViewById(R.id.P06_2);
                break;
            case R.id.P06_3:
                selected = (RadioButton)findViewById(R.id.P06_3);
                break;
            default:
                break;
        }
    }
}






