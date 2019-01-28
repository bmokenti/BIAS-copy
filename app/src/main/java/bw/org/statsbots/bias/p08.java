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

public class p08 extends AppCompatActivity implements Serializable, View.OnClickListener {
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected  RadioButton rbtn1,rbtn2,rbtn3,rbtn4,rbtn5,rbtn6, selected=null;
    protected RadioGroup rbtngroup;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p08);
        setTitle("P08 MARITAL STATUS");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.P08_1);
        rbtn2 =  (RadioButton)findViewById(R.id.P08_2);
        rbtn3 =  (RadioButton)findViewById(R.id.P08_3);
        rbtn4 =  (RadioButton)findViewById(R.id.P08_4);
        rbtn5 =  (RadioButton)findViewById(R.id.P08_5);
        rbtn6 =  (RadioButton)findViewById(R.id.P08_6);
        rbtngroup = (RadioGroup)findViewById(R.id.p08radioGroup) ;

        rbtn1.setOnClickListener(this);
        rbtn2.setOnClickListener(this);
        rbtn3.setOnClickListener(this);
        rbtn4.setOnClickListener(this);
        rbtn5.setOnClickListener(this);
        rbtn6.setOnClickListener(this);

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
            if(p1.getP08()==null)
            {
                int age = Integer.parseInt(thisHouse.getPersons()[p1.getLineNumber()].getP04YY());
                int age1 = Integer.parseInt(thisHouse.getPersons()[p1.getSRNO()].getP04YY());
                if(p1.getLineNumber() == thisHouse.getTotalPersons()-1 && age<12 ){

                    Intent intent = new Intent(p08.this,P09.class);
                    intent.putExtra("Household",  thisHouse);
                    startActivity(intent);

                }else{
                    if(age<12){
                        Log.d(" Continue Age: ",String.valueOf(age) + " - " + String.valueOf(r));
                        if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){

                            Intent intent = new Intent(p08.this,P09.class);
                            intent.putExtra("Household",  thisHouse);
                            startActivity(intent);

                        }

                    }else{
                        Log.d(" break Age: ",String.valueOf(age)+ " - " + String.valueOf(r));
                        break;
                    }

                }

            }
        }

        //Disable for head of House
      /*  if(p1.getP02().equals("00")){

            rbtn1.setChecked(true);
            rbtn2.setEnabled(false);
            rbtn3.setEnabled(false);
            selected=rbtn1;
        }*/


        if(p1.getP08()==null) {

            TextView textView = (TextView) findViewById(R.id.P08);
            String s = getResources().getString(R.string.P08);
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
                            lib.showError(p08.this,"P08 Error",thisHouse.getPersons()[p1.getLineNumber()].getP01() +" is under 12 and cannot be married");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        }else{
                            //Set P02 fir the current individual
                            thisHouse.getPersons()[p1.getLineNumber()].setP08(selected.getText().toString().substring(0,1));
                            //Restart the current activity for next individual
                            if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){

                                //Next question P07
                                Intent intent = new Intent(p08.this,P09.class);
                                intent.putExtra("Household",  thisHouse);
                                startActivity(intent);

                            }else{
                                //Restart the current activity for next individual
                                finish();
                                startActivity(getIntent());
                            }
                        }


                    }




                }
            });







        }





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






