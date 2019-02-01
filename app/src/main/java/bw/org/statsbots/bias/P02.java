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

        /**
         * Loop through the house hold members to check if hh member 's P02 is answered
         * If P02 is null then ask the individual
         */


    /*    if(thisHouse.previous!=null){
            p1=thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];
            thisHouse.next=null;

        }


        if(thisHouse.next!=null && !thisHouse.previous.equals(thisHouse.getPersons().length))
        {
            p1=thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];
            thisHouse.previous=null;

        }
*/




            for(int r=0; r<thisHouse.getTotalPersons();r++)
            {

                p1= thisHouse.getPersons()[r];
                if(p1.getP02()==null)
                {
                    break;
                }else{

                    continue;
                }


            }


        /**Make Head of House preselected for P02
         *  due to the popup asking for head of house confirmation
         **/
        RadioButton[] bt = new RadioButton[13];
        for(int f=0;f<rg.getChildCount();f++){
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton) {
                bt[f]=((RadioButton)o);
            }
        }

        /**
         * To filter P02 Responses
         */
        if(thisHouse.getHead()==Integer.valueOf(p1.getLineNumber())){
            bt[0].setChecked(true);
            for (RadioButton rb:bt) {
                rb.setEnabled(false);
            }
            bt[0].setEnabled(true);
            currentHH=String.valueOf(p1.getLineNumber());
        }else{
            bt[0].setVisibility(View.INVISIBLE);
        }


        /**
         * If P02 for the current loop is null, request enumerator to capture there sponse
         */
        if(p1.getP02()==null)
        {

            TextView textView = (TextView)findViewById(R.id.P02);
            String s = getResources().getString(R.string.P02);
            int t = s.indexOf("#");

            s=s.replace("#","<b>" + p1.getP01() + "</b> ");
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

                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){
                            //Next question P03
                            thisHouse.previous=String.valueOf(p1.getLineNumber());//set previous to last person covered

                            Intent intent = new Intent(P02.this,P03.class);
                            intent.putExtra("Household",  thisHouse);
                            startActivity(intent);

                        }else{
                            //Restart the current activity for next individual
                            if(p1.getSRNO()==0)
                            {
                                /**
                                 * IF THIS IS THE LAST PERSON IN THE ROSTER, SET PREVIOUS PERSON TO
                                 * THIS PERSON
                                 */

                                thisHouse.previous=String.valueOf(p1.getSRNO());

                            }else{
                                /**
                                 *  SET PREVIOUS PERSON TO THE CURRENT PERSON - 1
                                 * THIS PERSON
                                 */
                                thisHouse.previous=String.valueOf(p1.getLineNumber()-1);
                            }

                            finish();
                            startActivity(getIntent());
                        }


                    }


                }
            });
                /*
            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    if(thisHouse.previous == null)
                    {
                        if(p1.getSRNO()==0)
                        {
                            thisHouse.previous = null;
                            finish();

                        }else {
                            thisHouse.previous = String.valueOf(p1.getSRNO()-1);
                            thisHouse.next = String.valueOf(p1.getSRNO());


                            Log.d("Previous ", thisHouse.previous);
                            Intent intent = new Intent(P02.this,P02.class);
                            intent.putExtra("Household",  thisHouse);

                            finish();
                            startActivity(intent);
                        }


                    }else{

                        Log.d("Previous 1 " , "not null" + p1);
                        if(p1.getSRNO()==0){

                        }else{
                            thisHouse.previous = String.valueOf(p1.getSRNO()-1);
                            thisHouse.next = String.valueOf(p1.getSRNO());
                        }



                        Log.d("Previous 2", thisHouse.previous);

                        Intent intent = new Intent(P02.this,P02.class);
                        intent.putExtra("Household",  thisHouse);

                        finish();
                        startActivity(intent);


                    }
                }
            });

                */
        }else{
            /**
             * This is reserved for loading existing data
             */
            TextView textView = (TextView)findViewById(R.id.P02);
            String s = getResources().getString(R.string.P02);
            int t = s.indexOf("#");

            s=s.replace("#","<b>" + p1.getP01() + "</b> ");
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

                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){
                            //Next question P03
                            thisHouse.previous=String.valueOf(p1.getLineNumber());//set previous to last person covered

                            Intent intent = new Intent(P02.this,P03.class);
                            intent.putExtra("Household",  thisHouse);
                            startActivity(intent);

                        }else{
                            //Restart the current activity for next individual
                            if(p1.getSRNO()==0)
                            {
                                /**
                                 * IF THIS IS THE LAST PERSON IN THE ROSTER, SET PREVIOUS PERSON TO
                                 * THIS PERSON
                                 */

                                thisHouse.previous=String.valueOf(p1.getSRNO());

                            }else{
                                /**
                                 *  SET PREVIOUS PERSON TO THE CURRENT PERSON - 1
                                 * THIS PERSON
                                 */
                                thisHouse.previous=String.valueOf(p1.getLineNumber()-1);
                            }

                            finish();
                            startActivity(getIntent());
                        }


                    }


                }
            });

            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    if(thisHouse.previous == null)
                    {
                        if(p1.getSRNO()==0)
                        {
                            thisHouse.previous = null;
                            finish();

                        }else {
                            thisHouse.previous = String.valueOf(p1.getSRNO()-1);
                            thisHouse.next = String.valueOf(p1.getSRNO());


                            Log.d("Previous 2", thisHouse.previous);
                            Intent intent = new Intent(P02.this,P02.class);
                            intent.putExtra("Household",  thisHouse);

                            finish();
                            startActivity(intent);
                        }


                    }else {
                        Log.d("HERE 2","#####" + thisHouse.next + "-----"+ thisHouse.previous);
                        if(p1.getSRNO()==0){
                            thisHouse.previous = String.valueOf(p1.getSRNO());
                            thisHouse.next = String.valueOf(p1.getSRNO()+1);

                            finish();


                        }else {
                            thisHouse.previous = String.valueOf(p1.getSRNO()-1);
                            thisHouse.next = String.valueOf(p1.getSRNO());

                        }

                    }
                }
            });



        }






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
