package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class P10 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    HouseHold thisHose;
    TextView txtq104text;
    TextView q;

    protected ArrayAdapter<String> adapter, adapter1, adapter2;
    protected AutoCompleteTextView autoTypeEducation, autoLevel, autoYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p10);

        q= findViewById(R.id.P10text);



        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;


        for (int r = 0; r < thisHouse.getTotalPersons(); r++) {
            p1 = thisHouse.getPersons()[r];
            /*String code1=p1.getP10().substring(0,2);
            String code2=p1.getP10().substring(2,3);
            String code3=p1.getP10().substring(3,4);*/

            if(p1.getP10()==null){
                Log.d("Education ","Check  " + p1.getP01()+ " --- "+ p1.getP10());
                break;
            }else{
                if (p1.getSRNO() == Integer.parseInt(thisHouse.getCurrent()))
                {
                    Log.d("Education ","Check lAST " + p1.getP01()+ " --- "+ p1.getP10());
                    break;
                }
                else
                {

                        Log.d("Education ","Check  CONTINUED" + p1.getP01()+ " --- "+ p1.getP10());
                        continue;

                }
            }

        }

        String s = q.getText().toString();
        int t = s.indexOf("#");
        s = s.replace("#", "<b>" + p1.getP01() +"</b> ");

        q.setText(Html.fromHtml(s));

        if (p1.getP10() == null)
        {
            setTitle("P10: Educational attainment");
            lib = new LibraryClass();

            final String[] lst = getResources().getStringArray(R.array.type_of_education);

            adapter = new ArrayAdapter<String>(this, R.layout.hint_completion_layout, R.id.tvHintCompletion, lst);

            autoTypeEducation = (AutoCompleteTextView) findViewById(R.id.P10autoTypeOfEducation);

            autoTypeEducation.setAdapter(adapter);

            autoTypeEducation.setThreshold(1);

            autoTypeEducation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        autoTypeEducation.showDropDown();
                    }
                }
            });

            autoTypeEducation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    autoTypeEducation.showDropDown();
                }
            });

            Button btnClear = (Button) findViewById(R.id.btnClear);
            btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    autoTypeEducation.setText("");
                }
            });


            // final int selectedId = rbtngroup.getCheckedRadioButtonId();

            // setTitle("Q104a LEVEL of Education");

            lib = new LibraryClass();
            final String[] lst1 = getResources().getStringArray(R.array.level_of_education);


            adapter1 = new ArrayAdapter<String>(this, R.layout.hint_completion_layout, R.id.tvHintCompletion, lst1);
            autoLevel = (AutoCompleteTextView) findViewById(R.id.P10autoLevelOfEducation);

            autoLevel.setAdapter(adapter1);
            autoLevel.setThreshold(1);

            autoLevel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        autoLevel.showDropDown();
                    }
                }
            });

            autoLevel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    autoLevel.showDropDown();
                }
            });

            Button btnClear1 = (Button) findViewById(R.id.btnClear1);
            btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    autoLevel.setText("");
                }
            });


            //setTitle("Q104b Year");

            lib = new LibraryClass();

            final String[] lst2 = getResources().getStringArray(R.array.Year);


            adapter2 = new ArrayAdapter<String>(this, R.layout.hint_completion_layout, R.id.tvHintCompletion, lst2);
            autoYear = (AutoCompleteTextView) findViewById(R.id.P10autoYear);

            autoYear.setAdapter(adapter2);
            autoYear.setThreshold(1);

            autoYear.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        autoYear.showDropDown();
                    }
                }
            });

            autoYear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    autoLevel.showDropDown();
                }
            });

            Button btnClear2 = (Button) findViewById(R.id.btnClear2);
            btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    autoYear.setText("");
                }
            });


            // final int selectedId = rbtngroup.getCheckedRadioButtonId();


            Button btnnext = findViewById(R.id.button);
            btnnext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    String Selectedtype = autoTypeEducation.getText().toString();
                    String Selectedlevel = autoLevel.getText().toString();
                    String Selectedyear = autoYear.getText().toString();

                    txtq104text = (TextView) findViewById(R.id.txtq104c);


                /*String txtq104cvalue = txtq104text.getText().toString();
                if (txtq104cvalue == null || txtq104cvalue.length() == 0) {
                    lib.showError(P10.this, " Field of Education ", "Please enter description");
                    *//**
                     * VIBRATE DEVICE
                     *//*
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    //Check if country entered is in the list*/

                    if (Selectedtype == null || Selectedtype.length() == 0)
                    {
                        lib.showError(P10.this, "Education Error", "Please select Type of education");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else
                        {
                        //Check if country entered is in the list
                        boolean exists = false;
                        for (String s : lst)
                        {
                            if (s.matches(Selectedtype)) {
                                exists = true;
                                break;
                            }
                        }


                        if (Selectedlevel == null || Selectedlevel.length() == 0)
                        {
                            lib.showError(P10.this, "Level Error", "Please select Level of education");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        }
                        else
                            {
                            //Check if country entered is in the list
                            boolean exist = false;
                            for (String l : lst1) {
                                if (l.matches(Selectedlevel)) {
                                    exist = true;
                                    break;
                                }
                            }


                            if (Selectedyear == null || Selectedyear.length() == 0)
                            {
                                lib.showError(P10.this, "Error", "Please select Year");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            }
                            else
                                {
                                //Check if country entered is in the list
                                boolean existsY = false;
                                for (String y : lst2) {
                                    if (Integer.valueOf(y) == Integer.valueOf(Selectedyear)) {
                                        existsY = true;
                                        break;
                                    }
                                }
                                //Log.d("P05", String.valueOf(exists));
                                if (exists && exist && existsY)
                                {
                                    String type = autoTypeEducation.getText().toString();
                                    String code1=autoTypeEducation.getText().toString().substring(0,2);
                                    int typ = Integer.parseInt(code1);

                                    String level = autoLevel.getText().toString();
                                    String code2=autoLevel.getText().toString().substring(0,1);
                                    int lvl = Integer.parseInt(code2);

                                    String Year = autoYear.getText().toString();
                                    String code3=autoYear.getText().toString().substring(0,1);
                                    int yr = Integer.parseInt(code3);


                                    if(isValid(typ,lvl,yr)){
                                        thisHouse.getPersons()[p1.getLineNumber()].setP10(code1+code2+code3);


                                        //Next question P07
                                        if(Integer.parseInt(code1)>10){
                                            Log.d("Education ","Over 10");
                                            thisHouse.setCurrent(String.valueOf(p1.getSRNO()));
                                            Intent intent = new Intent(P10.this, P11.class);
                                            intent.putExtra("Household", thisHouse);

                                            startActivity(intent);

                                        }else{
                                            //this is the last person, go to P12

                                            if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){
                                                Log.d("Education ","Last person  " + p1.getP10());
                                                Intent intent = new Intent(P10.this, P12.class);
                                                intent.putExtra("Household", thisHouse);
                                                startActivity(intent);

                                            }
                                            //else loop p10
                                            else{
                                                //Restart the current activity for next individual
                                                Intent intent = new Intent(P10.this, P09.class);
                                                intent.putExtra("Household", thisHouse);
                                                startActivity(intent);
                                            }
                                        }



                                    }else{
                                        lib.showError(P10.this, "Education Error", "Type Selectd should match the Level and Year");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);
                                    }










                                }
                                else
                                    {


                                    lib.showError(P10.this, "Education Error", "Type, Level or Year of education does not exist");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);

                                }

                            }


                        }
                    }


                }

                /**Method to check is P10 is valid**/
                private boolean isValid(int typ, int lvl, int yr) {
                    boolean valid= false;
                    //Priamry
                    if(typ == 0 || typ == 1 || typ==2|| typ==3){
                        if(lvl==0){
                            valid = true;
                        }
                    }else{
                        //Teartiary
                        if(typ > 10){
                            if(lvl==1 || lvl==2 || lvl==3 || lvl==4 || lvl==5){
                                valid = true;
                            }

                        }


                    }





                    return valid;
                }
            });
        }
    }
}
