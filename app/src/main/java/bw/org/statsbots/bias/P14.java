package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class P14 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected RadioButton selectedRbtn;
    protected RadioButton rbtn1,rbtn2,rbtn3, rbtn4, rbtn5, rbtn6,rbtn7,rbtn8,rbtn9, selected=null;
    protected RadioGroup rbtngroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p14);


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

        //Disable for head of House


        if(p1.getP14()==null) {

            TextView textView = (TextView) findViewById(R.id.P14);
            String s = getResources().getString(R.string.P14);
            int t = s.indexOf("#");
            s = s.replace("#", "<b>" + p1.getP01() +"</b> ");

            textView.setText(Html.fromHtml(s));

            /**
             * NEXT Person BUTTON
             */
            Button btnNext = (Button)findViewById(R.id.button);
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
                        //Restart the current activity for next individual
                        //if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){

                            //Next question P07
                        thisHouse.setCurrent(String.valueOf(p1.getSRNO()));
                            Intent intent = new Intent(P14.this,P15.class);
                            intent.putExtra("Household",  thisHouse);
                            startActivity(intent);

                        /*}else{
                            //Restart the current activity for next individual
                            finish();
                            startActivity(getIntent());
                        }*/

                    }




                }
            });







        }





    }



}






