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
    protected RadioButton rbtn1,rbtn2,rbtn3, rbtn4, rbtn5, rbtn6,rbtn7,rbtn8,rbtn9, selected=null;
    protected RadioGroup rbtngroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p14);


        setTitle("P14 ECONOMIC ACTIVITY");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.P14_1);
        rbtn2 =  (RadioButton)findViewById(R.id.P14_2);
        rbtn3 =  (RadioButton)findViewById(R.id.P14_3);
        rbtn4 =  (RadioButton)findViewById(R.id.P14_4);
        rbtn5 =  (RadioButton)findViewById(R.id.P14_5);

        rbtn6 =  (RadioButton)findViewById(R.id.P14_6);
        rbtn7 =  (RadioButton)findViewById(R.id.P14_7);
        rbtn8 =  (RadioButton)findViewById(R.id.P14_8);
        rbtn9 =  (RadioButton)findViewById(R.id.P14_9);

        rbtngroup = (RadioGroup)findViewById(R.id.P14radioGroup) ;



        final int selectedId = rbtngroup.getCheckedRadioButtonId();

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        int p=0;

        /**
         * Loop through the house hold members to check if hh member 's P02 is answered
         * If P02 is null then ask the individual
         */
        for(int r=0; r<thisHouse.getTotalPersons();r++)
        {
            p1= thisHouse.getPersons()[r];
            if(p1.getP14()==null)
            {
                break;
            }else{
                continue;
            }
        }

        //Disable for head of House


        if(p1.getP13()==null) {

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
                        lib.showError(P14.this,"P14 Error","What was"+ p1.getP01() + " 'S mainly working as during the past 7 days?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else{

                        //Set P02 fir the current individual
                        thisHouse.getPersons()[p1.getLineNumber()].setP14(selected.getText().toString().substring(0,1));
                        //Restart the current activity for next individual
                        if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){

                            //Next question P07
                            Intent intent = new Intent(P14.this,P15.class);
                            intent.putExtra("Household",  thisHouse);
                            startActivity(intent);

                        }else{
                            //Restart the current activity for next individual
                            finish();
                            startActivity(getIntent());
                        }

                    }




                }
            });







        }





    }



}






