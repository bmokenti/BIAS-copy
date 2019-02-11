package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class P03 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected RadioButton selectedRbtn;
    PersonRoster p1=null;
    protected DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p03);
        setTitle("P03 SEX");

        final RadioGroup rg = (RadioGroup) findViewById(R.id.P03radioGroup);

        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        p1 = thisHouse.getPersons()[0];

        //***************************Read Roster from Database and load it into Object thisHouse
        List<PersonRoster> list = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
        thisHouse.setHouseHoldeMembers(list.toArray(thisHouse.getHouseHoldeMembers()));

        /**
         * *IF NEXT PERSON IS NOT NULL
         **/
        if(thisHouse.next!=null)
        {
            //Next Members
            p1=thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];
            thisHouse.setCurrentPerson(p1.getSRNO());


            //OPTIONS
            RadioButton[] bt = new RadioButton[2];
            //CHECK WHICH BUTTON WAS SELECTED
            for(int f=0;f<rg.getChildCount();f++)
            {
                View o = rg.getChildAt(f);
                if (o instanceof RadioButton)
                {
                    bt[f]=((RadioButton)o);
                    if(p1.getP03()!= null)
                    {
                        Log.d("Check",p1.getP03());
                        if(Integer.parseInt(p1.getP03())==f+1)
                        {
                            RadioButton radioButton = bt[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
                else
                {
                    Log.d("Lost Here","**********");
                }
            }
        }

        /**
         ** IF PREVIOUS PERSON IS SELECTED
         * **/
        else if(thisHouse.previous!=null)
        {
            //Next Members
            p1=thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];


            //OPTIONS
            RadioButton[] bt = new RadioButton[2];

            //CHECK WHICH BUTTON WAS SELECTED
            for(int f=0;f<rg.getChildCount();f++)
            {
                View o = rg.getChildAt(f);
                if (o instanceof RadioButton)
                {
                    bt[f]=((RadioButton)o);
                    if(p1.getP03()!= null)
                    {
                        Log.d("Check",p1.getP03());
                        if(Integer.parseInt(p1.getP03())==f+1)
                        {
                            RadioButton radioButton = bt[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }


                /**
                 * PRESELECTED OPTION
                 */
                else
                {

                }
            }

        }
            TextView textView = (TextView)findViewById(R.id.P03);
            String s = getResources().getString(R.string.P03);
            int t = s.indexOf("#");
            s=s.replace("#","<b>" +p1.getP01() + "</b> ");

            textView.setText(Html.fromHtml(s));

            /**
             * NEXT Person BUTTON
             */
            Button btnNext = (Button)findViewById(R.id.p03_btnNext);
            Button btnPrev = (Button)findViewById(R.id.p03_btnPrev);
            String btnLabel="";

            if(p1.getLineNumber()+1==thisHouse.getTotalPersons()){
                btnLabel="Next";
            }else{
                btnLabel="Next > "+ thisHouse.getPersons()[p1.getLineNumber()+1].getP01();
            }

            btnNext.setText(btnLabel);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedId =  rg.getCheckedRadioButtonId();
                    selectedRbtn = (RadioButton) findViewById(selectedId);

                    if(selectedRbtn==null)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(P03.this);
                        builder.setTitle("Select Sex");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("Please select "+ p1.getP01() + "'s gender");
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
                        thisHouse.getPersons()[p1.getLineNumber()].setP03(selectedRbtn.getText().toString().substring(0,1));

                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */

                        if(p1.getLineNumber() == thisHouse.getTotalPersons()-1)
                        {
                            //Next question P04
                            thisHouse.next =String.valueOf(0);
                            thisHouse.previous = String.valueOf(thisHouse.getTotalPersons()-1);

                            myDB = new DatabaseHelper(P03.this);
                            myDB.onOpen(myDB.getWritableDatabase());

                            //UPDATE HOUSEHOLD
                            List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                            if(ll.size()>0){
                                myDB.updateRoster(thisHouse,"P03",p1.getP03(), String.valueOf(p1.getSRNO()));
                                myDB.close();
                            }


                            Intent intent = new Intent(P03.this,P04.class);
                            intent.putExtra("Household",  thisHouse);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length)
                            {
                                myDB = new DatabaseHelper(P03.this);
                                myDB.onOpen(myDB.getWritableDatabase());

                                //UPDATE HOUSEHOLD
                                List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                if(ll.size()>0){
                                    myDB.updateRoster(thisHouse,"P03",p1.getP03(), String.valueOf(p1.getSRNO()));
                                    myDB.close();
                                }
                                thisHouse.next=String.valueOf(p1.getSRNO()+1);

                                //Restart the current activity for next individual
                                finish();
                                Intent intent = new Intent(P03.this,P03.class);
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

                    if(p1.getSRNO() == 0){
                        //Next question P03
                        //thisHouse.previous=String.valueOf(p1.getLineNumber());//set previous to last person covered
                        thisHouse.previous=String.valueOf(thisHouse.getTotalPersons()-1);
                        thisHouse.next=null;
                        finish();

                        Intent intent = new Intent(P03.this,P02.class);
                        intent.putExtra("Household",  thisHouse);
                        startActivity(intent);


                    }else if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length){
                        //Restart the current activity for previous individual
                        thisHouse.previous = String.valueOf(p1.getSRNO()-1);
                        thisHouse.next=null;
                        finish();

                        Intent intent = new Intent(P03.this, P03.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);


                    }
                }
            });





    }
}
