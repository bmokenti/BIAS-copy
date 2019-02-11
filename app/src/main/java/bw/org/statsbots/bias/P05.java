package bw.org.statsbots.bias;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import static java.security.AccessController.getContext;

public class P05 extends AppCompatActivity implements Serializable {
    //Member Variables
    protected AutoCompleteTextView txtcountry;
    protected  ArrayAdapter <String> adapter;
    String bus;
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected AutoCompleteTextView autoCountry;
    protected LibraryClass lib;

    protected DatabaseHelper myDB;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("P05 CITIZENSHIP");
        setContentView(R.layout.activity_p05);
        lib = new LibraryClass();

       // txtcountry =(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        final String[] lst = getResources().getStringArray(R.array.country_list);



        adapter = new ArrayAdapter<String>(this,R.layout.hint_completion_layout,R.id.tvHintCompletion,lst);
        autoCountry = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);

        autoCountry.setAdapter(adapter);
        autoCountry.setThreshold(1);

        autoCountry.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                if(b)
                {
                    autoCountry.showDropDown();
                }
            }
        });

        autoCountry.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                autoCountry.showDropDown();
            }
        });

        Button btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                autoCountry.setText("");
            }
        });


        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        //p1 = thisHouse.getPersons()[0];

        //***************************Read Roster from Database and load it into Object thisHouse
        List<PersonRoster> list = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
        thisHouse.setHouseHoldeMembers(list.toArray(thisHouse.getHouseHoldeMembers()));

        if(thisHouse.next!=null)
        {
            //Next Members
            p1=thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];
            thisHouse.setCurrentPerson(p1.getSRNO());
             if(p1.getP05()!=null){
                 String code = p1.getP05();
                 int pos = 0;
                 for(int o = 0;o<lst.length;o++){

                     if(code.matches(lst[o].substring(0,3))){
                        pos=o;
                     }
                 }


                 autoCountry.setText(lst[pos]);
             }


        }
        else if(thisHouse.previous!=null)
        {
            //Prev Members
            p1=thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];
            thisHouse.setCurrentPerson(p1.getSRNO());
            if(p1.getP05()!=null){
                String code = p1.getP05();
                int pos = 0;
                for(int o = 0;o<lst.length;o++){

                    if(code.matches(lst[o].substring(0,3))){
                        pos=o;
                    }
                }


                autoCountry.setText(lst[pos]);
            }

        }



        TextView textView = (TextView) findViewById(R.id.P05);
        String s = getResources().getString(R.string.P05);
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

        btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener()
        {
           @Override
           public void onClick(View v)
           {
                String Selected = autoCountry.getText().toString();
                if(Selected==null || Selected.length()==0)
                {
                    lib.showError(P05.this,"Citizenship Error","Please select " + p1.getP01()+"'s citizenship");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else
                    {
                        //Check if country entered is in the list
                        boolean exists = false;
                        for (String s:lst)
                        {
                            if(s.equals(Selected))
                            {
                                exists = true;
                                break;
                            }
                        }

                        if(exists)
                        {

                            p1.setP05(Selected.substring(0,3));
                            thisHouse.getPersons()[p1.getLineNumber()].setP04WKS(Selected.substring(0,3));

                            if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){
                                thisHouse.next =String.valueOf(0);
                                thisHouse.previous = String.valueOf(thisHouse.getTotalPersons()-1);

                                myDB = new DatabaseHelper(P05.this);
                                myDB.onOpen(myDB.getWritableDatabase());

                                //UPDATE HOUSEHOLD
                                List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                if(ll.size()>0){
                                    myDB.updateRoster(thisHouse,"P05",p1.getP05(), String.valueOf(p1.getSRNO()));
                                    myDB.close();
                                }


                                //Next question P06
                                Intent intent = new Intent(P05.this,P06.class);
                                intent.putExtra("Household",  thisHouse);
                                startActivity(intent);

                            }else
                                {
                                if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length)
                                {
                                    myDB = new DatabaseHelper(P05.this);
                                    myDB.onOpen(myDB.getWritableDatabase());

                                    //UPDATE HOUSEHOLD
                                    List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                    if(ll.size()>0){
                                        myDB.updateRoster(thisHouse,"P05",p1.getP05(), String.valueOf(p1.getSRNO()));

                                        myDB.close();
                                    }
                                    thisHouse.next=String.valueOf(p1.getSRNO()+1);

                                    //Restart the current activity for next individual
                                    finish();
                                    Intent intent = new Intent(P05.this,P05.class);
                                    intent.putExtra("Household",  thisHouse);
                                    startActivity(intent);

                                }
                            }

                        }else{
                            lib.showError(P05.this,"Citizenship Error","Country of citizenship selected does not exist");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        }

                    }


                }
            });

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

                        Intent intent = new Intent(P05.this,P04.class);
                        intent.putExtra("Household",  thisHouse);
                        startActivity(intent);


                    }else if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length){
                        //Restart the current activity for previous individual
                        int n = p1.getSRNO()-1;
                        thisHouse.previous = String.valueOf(n);
                        thisHouse.next=null;

                        finish();

                        Intent intent = new Intent(P05.this, P05.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);


                    }
                }
            });





    }


}

