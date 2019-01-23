package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
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

    protected ArrayAdapter<String> adapter, adapter1, adapter2;
    protected AutoCompleteTextView autoTypeEducation, autoLevel, autoYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p10);


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

        // Intent i = getIntent();
        // thisHouse = (HouseHold) i.getSerializableExtra("Household");
        // int p = 0;
        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectedtype = autoTypeEducation.getText().toString();
                String Selectedlevel = autoLevel.getText().toString();
                String Selectedyear = autoYear.getText().toString();

                txtq104text = (TextView) findViewById(R.id.txtq104c);

                String txtq104cvalue = txtq104text.getText().toString();
                if (txtq104cvalue == null || txtq104cvalue.length() == 0) {
                    lib.showError(P10.this, " Field of Education ", "Please enter description");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    //Check if country entered is in the list

                    if (Selectedtype == null || Selectedtype.length() == 0) {
                        lib.showError(P10.this, "Education Error", "Please select Type of education");
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


                        if (Selectedlevel == null || Selectedlevel.length() == 0) {
                            lib.showError(P10.this,"Level Error", "Please select Level of education");
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


                            if (Selectedyear == null || Selectedyear.length() == 0) {
                                lib.showError(P10.this, "Error", "Please select Year");
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
                                    //p1.setP05(Selectedtype.substring(0, 2));
                                    //Log.d("P05", Selected.substring(0,3));
                                    //Got to next Person / question

                                    //if (p1.getLineNumber() == thisHouse.getTotalPersons() - 1) {
                                    //Next question P06
                                    Intent intent = new Intent(P10.this, P11.class);
                                    intent.putExtra("Household", thisHouse);
                                    startActivity(intent);


                                } else {


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
            }
        });
    }
}
