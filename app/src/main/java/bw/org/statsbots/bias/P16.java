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
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class P16 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected DatabaseHelper  myDB;
    protected EditText edt;
    protected activity_general_information assginfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p16);

        lib = new LibraryClass();

        setTitle("P16 INDUSTRY");

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");

        final EditText edt = (EditText)findViewById(R.id.P16_txt);
        /**
         * Loop through the house hold members to check if hh member 's P02 is answered
         * If P02 is null then ask the individual
         */

        for(int r=0; r<thisHouse.getTotalPersons();r++)
        {
            p1= thisHouse.getPersons()[r];
            if(p1.getLineNumber()==thisHouse.getTotalPersons()-1 ){
                //Next question P05
                //lib.showError(P07.this,"Members Done","HH members are complete");
                // assginfo.EAStatus.getText().toString();
                Intent intent = new Intent(P16.this, P17.class);
                intent.putExtra("Household", thisHouse);
                startActivity(intent);




/*
                insertEAAssignment(
                        String EA_Assignment_ID,String STRATUM,String DISTRICT,String VILLAGE,String
                        LOCALITY,String EA,
                        String BLOCK_NO ,String EA_STATUS)*/
            }




            else{
                if( p1.getP16()==null)
                {
                    // Toast.makeText(P07.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    break;
                }else{
                    continue;
                }
            }

        }

        if( p1.getP16()==null) {

            TextView textView = (TextView) findViewById(R.id.P16txt);
            String s = getResources().getString(R.string.P16);
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


            btnNext.setText(btnLabel);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                edt.getText().toString();

                    if(edt.length()==0 || edt.getText()==null)
                    {
                        lib.showError(P16.this,"P16 Error","What was the main product,service or activity at "+p1.getP01()+"place of work?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    }
                    else{

                        //Set P02 fir the current individual
                        thisHouse.getPersons()[p1.getLineNumber()].setP16(edt.getText().toString());
                        //Restart the current activity for next individual
                        if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){
                            for (PersonRoster p : thisHouse.getPersons())
                            {

                            }

                            //Next question P17
                            Intent intent = new Intent(P16.this, P17.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);


                        }else{
                            //Restart the current activity for next individual

                            finish();
                           // Toast.makeText(P07.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                            startActivity(getIntent());
                        }




                    }





                }
            });

        }

    }

}







