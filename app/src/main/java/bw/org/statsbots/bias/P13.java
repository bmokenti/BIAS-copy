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

public class P13 extends AppCompatActivity implements Serializable, View.OnClickListener {
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected RadioButton rbtn1,rbtn2,rbtn3, rbtn4, rbtn5, rbtn6, selected=null;
    protected RadioGroup rbtngroup;
    protected EditText edt;
    protected DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p13);
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();


        setTitle("P13 ECONOMIC ACTIVITY");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.P13_1);
        rbtn2 =  (RadioButton)findViewById(R.id.P13_2);
        rbtn3 =  (RadioButton)findViewById(R.id.P13_3);
        rbtn4 =  (RadioButton)findViewById(R.id.P13_4);
        rbtn5 =  (RadioButton)findViewById(R.id.P13_5);
        rbtn6 =  (RadioButton)findViewById(R.id.P13_other);
        rbtngroup = (RadioGroup)findViewById(R.id.P13radioGroup) ;
        edt = (EditText) findViewById(R.id.P13_txtOther);

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

        thisHouse.setIsHIVTB40("2");

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(),thisHouse.getAssignment_ID());
        /**
         * Loop through the house hold members to check if hh member 's P02 is answered
         * If P02 is null then ask the individual
         */
        for(int r=0; r<thisHouse.getTotalPersons();r++)
        {
            p1 = thisHouse.getPersons()[r];

            if(p1.getP12()==null){
                //Next question P07
                if(sample.getStatusCode().equals("1") )
                {
                    //HIV ONLY
                    Intent intent = new Intent(P13.this, P17.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);
                }
                else if(sample.getStatusCode().equals("2") )
                {
                    if(thisHouse.getIsHIVTB40().equals("2")){
                        //TB ONLY
                        Intent intent = new Intent(P13.this, P18.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);
                    }else{
                        //HIV+TB
                        Intent intent = new Intent(P13.this, P19.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);
                    }

                }
                else{
                    //TB ONLY
                    Intent intent = new Intent(P13.this, P18.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);

                }
            }else {
                if(p1.getP13()==null && p1.getP12().equals("2"))
                {
                    break;
                }else{
                    continue;
                }
            }

        }

        //Disable for head of House


        if(p1.getP13()==null) {

            TextView textView = (TextView) findViewById(R.id.P13);
            String s = getResources().getString(R.string.P13);
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
                        lib.showError(P13.this,"P13 Error","Since"+ p1.getP01() + "did not work for payment, profit or home use, what did he/she do?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else
                        {

                        //Set P02 fir the current individual
                        thisHouse.getPersons()[p1.getLineNumber()].setP13(selected.getText().toString().substring(0,1));
                        //Restart the current activity for next individual

                        int total = thisHouse.getTotalPersons();
                        if((p1.getP13() != null && thisHouse.getPersons()[total-1].getSRNO() == p1.getSRNO() ) || p1.getLineNumber() == thisHouse.getTotalPersons()-1 ){
                            //Next question P07
                            if(sample.getStatusCode().equals("1") )
                            {
                                //HIV ONLY
                                Intent intent = new Intent(P13.this, P17.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent);
                            }
                            else if(sample.getStatusCode().equals("2") )
                            {
                                if(thisHouse.getIsHIVTB40().equals("2")){
                                    //TB ONLY
                                    Intent intent = new Intent(P13.this, P18.class);
                                    intent.putExtra("Household", thisHouse);
                                    startActivity(intent);
                                }else{
                                    //HIV+TB
                                    Intent intent = new Intent(P13.this, P19.class);
                                    intent.putExtra("Household", thisHouse);
                                    startActivity(intent);
                                }



                            }
                            else{
                                //TB ONLY
                                Intent intent = new Intent(P13.this, P18.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent);

                            }

                        }else{
                            //Restart the current activity for next individual
                            Intent intent = new Intent(P13.this,P12.class);

                            intent.putExtra("Household",  thisHouse);
                            startActivity(intent);
                        }

                    }




                }
            });







        }





    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.P13_1:
                selected = (RadioButton)findViewById(R.id.P13_1);
                break;
            case R.id.P13_2:
                selected = (RadioButton)findViewById(R.id.P13_2);
                break;
            case R.id.P13_3:
                selected = (RadioButton)findViewById(R.id.P13_3);
                break;
            case R.id.P13_4:
                selected = (RadioButton)findViewById(R.id.P13_4);
                break;
            case R.id.P13_5:
                selected = (RadioButton)findViewById(R.id.P13_5);
                break;
            case R.id.P13_other:
                selected = (RadioButton)findViewById(R.id.P13_other);
                break;
            default:
                break;
        }
    }
}






