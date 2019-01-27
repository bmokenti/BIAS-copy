package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class P12 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected RadioButton selectedRbtn;
    protected DatabaseHelper myDB;
    PersonRoster p1=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p12);
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        setTitle("P12 TYPE OF ECONOMIC ACTIVITY");
        final RadioGroup rg = (RadioGroup) findViewById(R.id.P12radioGroup);

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(),thisHouse.getAssignment_ID());

        for(int r=0; r<thisHouse.getTotalPersons();r++)
        {

            p1= thisHouse.getPersons()[r];

            if(p1.getP12()==null && Integer.parseInt(p1.getP04YY()) >= 12)
            {
                Log.d("Minor age",p1.getP04YY());
                break;
            }
            else{
                if(p1.getLineNumber() == thisHouse.getTotalPersons()-1 && Integer.parseInt(p1.getP04YY()) < 12){
                    if(sample.getStatusCode().equals("1") )
                    {
                        //HIV ONLY
                        Intent intent = new Intent(P12.this, P17.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);
                    }
                    else if(sample.getStatusCode().equals("2") )
                    {
                        if (thisHouse.getIsHIVTB40().equals("2")) {
                            //TB ONLY
                            Intent intent = new Intent(P12.this, P18.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);
                        } else {
                            //HIV+TB
                            Intent intent = new Intent(P12.this, P19.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);
                        }
                    }
                    else{
                        //TB ONLY
                        Intent intent = new Intent(P12.this, P18.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);

                    }

                }
                else{
                    continue;
                }
            }
        }


        /**
         * If P03 for the current loop is null, request enumerator to capture there sponse
         */
        if(p1.getP12()==null)
        {
            TextView textView = (TextView)findViewById(R.id.P12);
            String s = getResources().getString(R.string.P12);
            int t = s.indexOf("#");
            s=s.replace("#","<b>" +p1.getP01() + "</b> ");

            textView.setText(Html.fromHtml(s));

            /**
             * NEXT Person BUTTON
             */
            Button btnNext = (Button)findViewById(R.id.p12_btnnext);
            String btnLabel="";

            if(p1.getLineNumber()+1==thisHouse.getTotalPersons()){
                btnLabel="Next";
            }else{
                btnLabel="Next > ";
            }

            btnNext.setText(btnLabel);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedId =  rg.getCheckedRadioButtonId();
                    selectedRbtn = (RadioButton) findViewById(selectedId);

                    if(selectedRbtn==null)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(P12.this);
                        builder.setTitle("Select option");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("In the past 7 days did "+ p1.getP01() + "work for payment, profit or home use for at least 1 hour?");
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
                        thisHouse.getPersons()[p1.getLineNumber()].setP12(selectedRbtn.getText().toString().substring(0,1));

                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        Log.d("Current Person: ",p1.getLineNumber() + "===" + p1.getP12());


                            //Restart the current activity for next individual
                                if(p1.getP12().equals("1"))
                                {
                                    thisHouse.setCurrent(String.valueOf(p1.getSRNO()));
                                    Intent intent = new Intent(P12.this,P14.class);
                                    intent.putExtra("Household",  thisHouse);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Intent intent = new Intent(P12.this,P13.class);
                                    intent.putExtra("Household",  thisHouse);
                                    thisHouse.setCurrent(String.valueOf(p1.getSRNO()));
                                    Log.d("P12:: ",p1.getP12());
                                    startActivity(intent);
                                }
                            //finish();
                            //startActivity(getIntent());



                    }
                }
            });


        }else{

            /**
             * This is reserved for loading existing data
             */


        }




    }
}
