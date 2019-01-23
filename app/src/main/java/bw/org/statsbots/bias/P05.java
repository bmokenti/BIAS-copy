package bw.org.statsbots.bias;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import static java.security.AccessController.getContext;

public class P05 extends AppCompatActivity implements Serializable {
    //Member Variables
    protected AutoCompleteTextView txtcountry;
    protected  ArrayAdapter <String> adapter;
    String bus;
    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected AutoCompleteTextView autoCountry;
    protected LibraryClass lib;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("P05 CITIZENSHIP");
        setContentView(R.layout.activity_p05);
        lib = new LibraryClass();

       // txtcountry =(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        final String[] lst = getResources().getStringArray(R.array.country_list);



        adapter = new ArrayAdapter<String>(this,R.layout.hint_completion_layout,R.id.tvHintCompletion,lst);
        autoCountry = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);

        autoCountry.setAdapter(adapter);
        autoCountry.setThreshold(1);

        autoCountry.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                if(b)
                {
                    autoCountry.showDropDown();
                }
            }
        });

        autoCountry.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                autoCountry.showDropDown();
            }
        });

        Button btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                autoCountry.setText("");
            }
        });


        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");

        /**
         * Loop through the house hold members to check if hh member 's P05 is answered
         * If P05 is null then ask the individual
         */
        for(int r=0; r<thisHouse.getTotalPersons();r++)
        {
            p1= thisHouse.getPersons()[r];
            if(p1.getP05()==null)
            {
                break;
            }
            else {
                continue;
            }
        }

        if(p1.getP05()==null)
        {

            TextView textView = (TextView) findViewById(R.id.P05);
            String s = getResources().getString(R.string.P05);
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
            btnNext.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    String Selected = autoCountry.getText().toString();
                    if(Selected==null || Selected.length()==0)
                    {
                        lib.showError(P05.this,"Citizenship Error","Please select " + p1.getP01()+"'s citizenship");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }else{
                        //Check if country entered is in the list
                        boolean exists = false;
                        for (String s:lst) {
                            if(s.equals(Selected)){

                                exists = true;
                                break;
                            }
                        }
                        //Log.d("P05", String.valueOf(exists));
                        if(exists)
                        {
                            //Set P05 fir the current individual
                            p1.setP05(Selected.substring(0,3));
                            //Log.d("P05", Selected.substring(0,3));
                            //Got to next Person / question

                            if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){
                                //Next question P06
                                Intent intent = new Intent(P05.this,P06.class);
                                intent.putExtra("Household",  thisHouse);
                                startActivity(intent);

                            }else{
                                //Restart the current activity for next individual
                                finish();
                                startActivity(getIntent());
                            }

                        }else{
                            lib.showError(P05.this,"Citizenship Error","Country of citizenship selected does not exist");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        }

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

