package bw.org.statsbots.bias;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.Serializable;



public class InterviewersVisits extends Activity implements View.OnClickListener , Serializable{
    protected HouseHold thisHouse;
    protected LibraryClass lib;
    String[] results = {"","COMPLETED","PARTIALLY COMPLETED","PRESENT BUT NOT AVAILABLE FOR INTERVIEWS","REFUSED","POSTPONED","OTHER (Specify)","","","STARTED"};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("Interviwer's visits");
        setContentView(R.layout.activity_interviewers_visits);
        TextView textView = (TextView) findViewById(R.id.InterviewersVisits);
        textView.setText("INTERVIEWERS VISITS");

        Button visit1Click = (Button) findViewById(R.id.Visit1);
        Button visit2Click = (Button) findViewById(R.id.Visit2);
        Button visit3Click = (Button) findViewById(R.id.Visit3);

        TextView tvist1 = (TextView)findViewById(R.id.textView2);
        TextView tvist2 = (TextView)findViewById(R.id.textView3);
        TextView tvist3 = (TextView)findViewById(R.id.textView4);


        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");

        visit1Click.setOnClickListener(this);
        visit2Click.setOnClickListener(this);
        visit3Click.setOnClickListener(this);

        /**
         * CHECK IF THIS HOUSE HAS VISITS
         */

        if(thisHouse != null )
        {

            if(thisHouse.getVISIT1_RESULT() != null && !thisHouse.getVISIT1_RESULT().equals("") ){
                int result=Integer.parseInt(thisHouse.getVISIT1_RESULT());

                visit1Click.setBackgroundColor(Color.GRAY);
                visit1Click.setText(thisHouse.getDATE1());
                tvist1.setText("Visit Results: "+thisHouse.getVISIT1_RESULT() + " " + results[result]);

                visit2Click.setVisibility(View.VISIBLE);
                visit2Click.setEnabled(true);
                tvist2.setVisibility(View.VISIBLE);

                visit3Click.setVisibility(View.INVISIBLE);
                tvist3.setVisibility(View.INVISIBLE);


                if(thisHouse.getVISIT2_RESULT() != null && !thisHouse.getVISIT2_RESULT().equals(""))
                {
                    int result2=Integer.parseInt(thisHouse.getVISIT2_RESULT());


                    visit2Click.setBackgroundColor(Color.GRAY);
                    visit2Click.setText(thisHouse.getDATE2());
                    tvist2.setText("Visit Results: "+thisHouse.getVISIT2_RESULT() + "  "+results[result2]);

                    visit3Click.setVisibility(View.VISIBLE);
                    visit3Click.setEnabled(true);
                    tvist3.setVisibility(View.VISIBLE);


                    if(thisHouse.getVISIT3_RESULT() != null && !thisHouse.getVISIT3_RESULT().equals("")) {
                        int result3=Integer.parseInt(thisHouse.getVISIT3_RESULT());

                        visit3Click.setBackgroundColor(Color.GRAY);
                        visit3Click.setText(thisHouse.getDATE3());
                        tvist3.setText("Visit Results: "+thisHouse.getVISIT3_RESULT() + "  "+results[result3]);


                    }





                }

            }else{
                visit1Click.setVisibility(View.VISIBLE);
                tvist1.setVisibility(View.VISIBLE);
                visit2Click.setVisibility(View.INVISIBLE);
                tvist2.setVisibility(View.INVISIBLE);
                visit3Click.setVisibility(View.INVISIBLE);
                tvist3.setVisibility(View.INVISIBLE);
            }

        }else{
            visit1Click.setVisibility(View.VISIBLE);
            tvist1.setVisibility(View.VISIBLE);
            visit2Click.setVisibility(View.INVISIBLE);
            tvist2.setVisibility(View.INVISIBLE);
            visit3Click.setVisibility(View.INVISIBLE);
            tvist3.setVisibility(View.INVISIBLE);
        }

        Button btnPrev = (Button)findViewById(R.id.button3);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Visit1:
                Intent intent1 = new Intent(this, Visit1.class);
               intent1.putExtra("Household",thisHouse);
               startActivity(intent1);
                break;
            case R.id.Visit2:
               Intent intent2 = new Intent(this, Visit2.class);
               intent2.putExtra("Household",thisHouse);
               startActivity(intent2);
                break;

            case R.id.Visit3:
               Intent intent3 = new Intent(this, Visit3.class);
               intent3.putExtra("Household",thisHouse);
               startActivity(intent3);
                break;

            default:
                break;
        }
    }
}