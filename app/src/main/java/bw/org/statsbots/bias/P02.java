package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class P02 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected RadioButton selectedRbtn;
    PersonRoster p1=null;
    String currentHH=null;
    protected DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p02);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        setTitle("P02 Relationship");
        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");


        //***************************Read Roster from Database and load it into Object thisHouse
        List<PersonRoster> list = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());

        String Head="";
        for (PersonRoster pp:list
             ) {
            if(pp.getP02().equals("00")){
                Head=pp.getP01();
            }
        }

        /**
        * *IF NEXT PERSON IS NOT NULL
        **/
        if(thisHouse.next!=null)
        {
            //Next Members
            p1=thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];
            thisHouse.setCurrentPerson(p1.getSRNO());

            //OPTIONS
            RadioButton[] bt = new RadioButton[13];

            //CHECK WHICH BUTTON WAS SELECTED
            for(int f=0;f<rg.getChildCount();f++)
            {
                View o = rg.getChildAt(f);
                if (o instanceof RadioButton)
                {
                    bt[f]=((RadioButton)o);
                }

                /**
                 * IF CURRENT PERSON IS HEAD OF HOUSE
                 */
                if(thisHouse.getHead()==Integer.valueOf(p1.getLineNumber())){
                    bt[0].setChecked(true);
                    bt[0].setEnabled(true);
                    bt[f].setEnabled(false);
                    currentHH=String.valueOf(p1.getLineNumber());
                }
                /**
                 * IF ITS NOT HEAD OF HOUSE
                 */
                else
                {
                    bt[0].setVisibility(View.INVISIBLE);
                    //Check the radio button that was selected before

                    if(p1.getP02()!= null)
                    {
                        if(Integer.parseInt(p1.getP02())==f)
                        {
                            RadioButton radioButton =bt[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }

        /**
        ** IF PREVIOUS PERSON IS SELECTED
        * **/
        else if(thisHouse.previous!=null)
        {
            //SET P1
            p1=thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];

             //OPTIONS
            RadioButton[] bt = new RadioButton[13];

            //CHECK WHICH OPTION WAS SELECTED
            for(int f=0;f<rg.getChildCount();f++)
            {
                View o = rg.getChildAt(f);

                if (o instanceof RadioButton) {
                    bt[f]=((RadioButton)o);
                }

                /**
                 * IF CURRENT PERSON IS HEAD OF HOUSE
                 */
                if(thisHouse.getHead()==Integer.valueOf(p1.getLineNumber())){
                    bt[0].setChecked(true);
                    bt[0].setEnabled(true);
                    bt[f].setEnabled(false);
                    currentHH=String.valueOf(p1.getLineNumber());
                }
                /**
                 * IF ITS NOT HEAD OF HOUSE
                 */
                else{
                    bt[0].setVisibility(View.INVISIBLE);
                    //Check the radio button that was selected before
                    if(p1.getP02()!=null)
                    {
                        if(Integer.parseInt(p1.getP02())==f)
                        {
                            RadioButton radioButton =bt[f];
                            radioButton.setChecked(true);
                            break;
                        }

                    }
                }
            }

        }

        /**Make Head of House preselected for P02
         *  due to the popup asking for head of house confirmation
         **/


        /**
         * To filter P02 Responses
         */




            TextView textView = (TextView)findViewById(R.id.P02);
            String s = getResources().getString(R.string.P02);

            int t = s.indexOf("#");
            int tt = s.indexOf("*");

            s=s.replace("#","<b>" + p1.getP01() + "</b> ");

            if(p1.getSRNO()==0){
                s=s.replace("*","");
                s=s.replace("(","");
                s=s.replace(")","");
            }else{
                s=s.replace("*","<b>" + Head + "</b> ");
            }



            textView.setText(Html.fromHtml(s));

            //Check if the variable has data and display it
            if(p1.getP02()!=null){





            }

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
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    int selectedId =  rg.getCheckedRadioButtonId();
                    selectedRbtn = (RadioButton) findViewById(selectedId);

                    if(selectedRbtn==null)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(P02.this);
                        builder.setTitle("Select Relationship");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("Please select "+ p1.getP01() + "'s relationship to Head of House");
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
                        //Set P02 fir the current individual
                        thisHouse.getPersons()[p1.getLineNumber()].setP02(selectedRbtn.getText().toString().substring(0,2));

                        Log.d("Data", thisHouse.getPersons()[p1.getLineNumber()].getP01());
                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        if(p1.getLineNumber() == thisHouse.getTotalPersons()-1)
                        {
                            //Next question P03
                            thisHouse.next =String.valueOf(0);
                            thisHouse.previous = String.valueOf(thisHouse.getTotalPersons()-1);

                            myDB = new DatabaseHelper(P02.this);
                            myDB.onOpen(myDB.getWritableDatabase());

                            //UPDATE HOUSEHOLD
                            List<PersonRoster> ll = new ArrayList<>();
                            ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                            if(ll.size()>0){
                                myDB.updateRoster(thisHouse,"P02",p1.getP02(), String.valueOf(p1.getSRNO()));
                            }else{
                                myDB.insertHhroster(thisHouse);

                            }

                            myDB.close();

                            Log.d("All",thisHouse.getPersons().length+"");

                            Intent intent = new Intent(P02.this,P03.class);
                            intent.putExtra("Household",  thisHouse);
                            startActivity(intent);
                            finish();

                        }else{
                            //Restart the current activity for next individual
                            if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length)
                            {
                                /**
                                 * IF THIS IS THE LAST PERSON IN THE ROSTER, SET PREVIOUS PERSON TO
                                 * THIS PERSON
                                 */
                                myDB = new DatabaseHelper(P02.this);
                                myDB.onOpen(myDB.getWritableDatabase());

                                //UPDATE HOUSEHOLD
                                List<PersonRoster> ll = new ArrayList<>();
                                ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());

                                if(ll.size()>0)
                                {
                                    myDB.updateRoster(thisHouse,"P02",p1.getP02(), String.valueOf(p1.getSRNO()));
                                    myDB.close();
                                }
                                else
                                    {
                                    myDB.insertHhroster(thisHouse);
                                }

                                thisHouse.next=String.valueOf(p1.getSRNO()+1);



                                finish();
                                Intent intent = new Intent(P02.this,P02.class);
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
                        finish();

                        Intent intent = new Intent(P02.this,MainActivity.class);
                        intent.putExtra("Household",  thisHouse);
                        startActivity(intent);


                    }else if(p1.getSRNO()>0 && p1.getSRNO()<thisHouse.getPersons().length){
                        //Restart the current activity for previous individual
                         thisHouse.previous = String.valueOf(p1.getSRNO()-1);
                        thisHouse.next=null;

                         finish();

                         Intent intent = new Intent(P02.this, P02.class);
                         intent.putExtra("Household", thisHouse);
                         startActivity(intent);


                    }
                }
            });





    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        /**Check which radio button was clicked
         * Compare with the exisisting Persons relationship
         * provide appropriate feedback if need be
         */
        switch(view.getId()) {
            case R.id.P02_00:
                if (checked){
                    }
                    break;
            case R.id.P02_01:
                if (checked){
                    }
                    break;
            case R.id.P02_02:
                if (checked)

                    break;
            case R.id.P02_03:
                if (checked){
                    }
                    break;
            case R.id.P02_04:
                if (checked){
                    }
                    break;
            case R.id.P02_05:
                if (checked){

                }
                    break;
            case R.id.P02_06:
                if (checked){

                }
                    break;
            case R.id.P02_07:
                if (checked){

                }
                    break;
            case R.id.P02_08:
                if (checked)


                    break;
            case R.id.P02_09:
                if (checked){

                }
                    break;
            case R.id.P02_10:
                if (checked){

                }
                    break;
            case R.id.P02_11:
                if (checked){

                }
                    break;
            case R.id.P02_12:
                if (checked) {


                }
                    break;

        }
    }




}
