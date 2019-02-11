package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class q104 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn3, selected = null;
    protected RadioGroup rbtngroup;
    protected EditText edtq104c;
    HouseHold thisHose;
    protected DatabaseHelper myDB;
    TextView txtq104text, q104atxt, q104btxt;
    protected ArrayAdapter<String> adapter, adapter1, adapter2;
    protected AutoCompleteTextView autoTypeEducation, autoLevel, autoYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q104);


        setTitle("Q104 Type of Education");
        lib = new LibraryClass();
        q104atxt = (TextView) findViewById(R.id.q104a) ;
                q104btxt = (TextView) findViewById(R.id.q104b) ;
        edtq104c = (EditText) findViewById(R.id.txtq104c) ;
        final String[] lst = getResources().getStringArray(R.array.type_of_education);


        adapter = new ArrayAdapter<String>(this, R.layout.hint_completion_layout, R.id.tvHintCompletion, lst);
        autoTypeEducation = (AutoCompleteTextView) findViewById(R.id.autoTypeOfEducation);

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
        autoLevel = (AutoCompleteTextView) findViewById(R.id.autoLevelOfEducation);

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
        autoYear = (AutoCompleteTextView) findViewById(R.id.autoYear);

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
        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectedtype = autoTypeEducation.getText().toString();
                String Selectedlevel = autoLevel.getText().toString();
                String Selectedyear = autoYear.getText().toString();

                txtq104text = (TextView) findViewById(R.id.txtq104c);

                String txtq104cvalue = txtq104text.getText().toString();
                if (txtq104cvalue == null || txtq104cvalue.length() == 0)
                {
                    lib.showError(q104.this, " Field of Education ", "Please enter description");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    //Check if country entered is in the list

                    if (Selectedtype == null || Selectedtype.length() == 0) {
                        lib.showError(q104.this, "Education Error", "Please select Type of education");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        //Check if country entered is in the list
                        boolean exists = false;
                        for (String s : lst) {
                            if (s.matches(Selectedtype)) {

                                exists = true;
                                break;
                            }
                        }

                        if (Selectedtype == "00" || Selectedtype == "01" || Selectedtype == "02" || Selectedtype == "03") {
                            q104atxt.setTextColor(Color.LTGRAY);
                            q104btxt.setTextColor(Color.LTGRAY);
                            autoLevel.setEnabled(false);
                            autoYear.setEnabled(false);
                        }

                        if (Selectedlevel == null || Selectedlevel.length() == 0) {
                            lib.showError(q104.this, "Level Error", "Please select Level of education");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {
                            //Check if country entered is in the list
                            boolean exist = false;
                            for (String l : lst1) {
                                if (l.matches(Selectedlevel)) {

                                    exist = true;
                                    break;
                                }
                            }
                            if (edtq104c == null || edtq104c.length() == 0) {
                                lib.showError(q104.this, "Field of education", "Please type field of education");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            }

                            if (Selectedyear == null || Selectedyear.length() == 0) {
                                lib.showError(q104.this, "Year Error", "Please select Year");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {
                                //Check if country entered is in the list
                                boolean existsY = false;
                                for (String y : lst2) {
                                    if (Integer.valueOf(y) == Integer.valueOf(Selectedyear)) {
                                        existsY = true;
                                        break;
                                    }
                                }
                                //Log.d("P05", String.valueOf(exists));
                                if (exists && exist && existsY) {
                                    //Set q104 fir the current individual
                                    individual.setQ104(autoTypeEducation.getText().toString().substring(0,1));
                                    individual.setQ104a(autoLevel.getText().toString().substring(0,1));
                                    individual.setQ104b(autoYear.getText().toString().substring(0,1));
                                    individual.setQ104c(edtq104c.getText().toString());

                                    myDB = new DatabaseHelper(q104.this);
                                    myDB.onOpen(myDB.getReadableDatabase());
                                    myDB.getWritableDatabase();

                                    if(myDB.checkIndividual(individual)){
                                        //Update
                                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);

                                    }else{
                                        //Insert
                                        myDB.insertIndividual(individual);

                                    }


                                    Intent intent = new Intent(q104.this, q105.class);
                                    intent.putExtra("Individual", individual);
                                    startActivity(intent);


                                } else {


                                    lib.showError(q104.this, "Education Error", "Type, Level or Year of education does not exist");
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
            }
        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q104.super.onBackPressed();
            }


        });
    }




}



/*
*
*  boolean exists = false;
                    for (String s : lst) {
                        if (s.equals(Selectedtype)) {

                            exists = true;
                            break;
                        }
                    }
 */
