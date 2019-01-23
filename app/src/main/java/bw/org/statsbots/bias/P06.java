package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class P06 extends AppCompatActivity implements Serializable, View.OnClickListener {
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected  RadioButton rbtn1,rbtn2,rbtn3, selected=null;
    protected RadioGroup rbtngroup;


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
            if(p1.getP06()==null)
            {
                break;
            }else{
                continue;
            }
        }

        //Disable for head of House
        if(p1.getP02().equals("00")){

            rbtn1.setChecked(true);
            rbtn2.setEnabled(false);
            rbtn3.setEnabled(false);
            selected=rbtn1;
        }


        if(p1.getP06()==null) {

            TextView textView = (TextView) findViewById(R.id.P06);
            String s = getResources().getString(R.string.P06);
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
                            lib.showError(P06.this,"P06 Error","Please select Place of Usual Residence");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        }
                        else{

                            //Set P02 fir the current individual
                            thisHouse.getPersons()[p1.getLineNumber()].setP06(selected.getText().toString().substring(0,1));
                            //Restart the current activity for next individual
                            if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){

                                //Next question P07
                                Intent intent = new Intent(P06.this,P07.class);
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






