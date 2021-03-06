package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

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

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        //int p = 0;

        myDB = new DatabaseHelper(q104.this);
        myDB.onOpen(myDB.getReadableDatabase());

        Intent ii = getIntent();
        p1 = (PersonRoster) ii.getSerializableExtra("Personroster");

        myDB.getWritableDatabase();
        myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());


        final Individual ind = myDB.getdataIndivisual(p1.getAssignmentID(),p1.getBatch(),p1.getSRNO());
        individual = ind;

        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

        final List<PersonRoster> roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }

        //int p = 0;

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


        if(ind.getQ104()!=null){
            String type = ind.getQ104();
            int pos = 0;
            for(int o = 0;o<lst.length;o++){

                if(type.matches(lst[o].substring(0,2))){
                    pos=o;
                }
            }


            autoTypeEducation.setText(lst[pos]);



        }


        if(ind.getQ104a()!=null){
            String type = ind.getQ104a();
            int pos = 0;
            for(int o = 0;o<lst1.length;o++){

                if(type.matches(lst1[o].substring(0,1))){
                    pos=o;
                }
            }
            autoLevel.setText(lst1[pos]);

        }

        if(ind.getQ104b()!=null){
            String type = ind.getQ104b();
            int pos = 0;
            for(int o = 0;o<lst2.length;o++){

                if(type.matches(lst2[o].substring(0,1))){
                    pos=o;
                }
            }
            autoYear.setText(lst2[pos]);

        }

        if(ind.getQ104c() !=null && !ind.getQ104c().equals("") )
        {
            edtq104c.setText(ind.getQ104c());
        }



        Button btnClear2 = (Button) findViewById(R.id.btnClear2);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoYear.setText("");
            }
        });





        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectedtype = autoTypeEducation.getText().toString();
                String Selectedlevel = autoLevel.getText().toString();
                String Selectedyear = autoYear.getText().toString();

                txtq104text = (TextView) findViewById(R.id.txtq104c);

                String txtq104cvalue = txtq104text.getText().toString();


//                if (edtq104c == null || edtq104c.length() == 0) {
//                    int typ= Integer.parseInt(autoTypeEducation.getText().toString().substring(0,2));
//                    if(typ > 10){
//                        lib.showError(q104.this, "Field of education", "Please type field of education");
//                        /**
//                         * VIBRATE DEVICE
//                         */
//                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//                        vibs.vibrate(100);
//                    }
//                }else {
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

//                    if (Selectedtype == "00" || Selectedtype == "01" || Selectedtype == "02" || Selectedtype == "03") {
//                        q104atxt.setTextColor(Color.LTGRAY);
//                        q104btxt.setTextColor(Color.LTGRAY);
//                        edtq104c.setEnabled(false);
//                        autoYear.setEnabled(false);
//                    }

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
                            int typ = Integer.parseInt(autoTypeEducation.getText().toString().substring(0, 2));
//                            if ((typ < 10) && (edtq104c != null || edtq104c.length() > 0)) {
//
//
//                                lib.showError(q104.this, "Field of education", "Please clear field of education ");
//                                /**
//                                 * VIBRATE DEVICE
//                                 */
//                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//                                vibs.vibrate(100);
//                            } else
                                if ((typ > 10) && (edtq104c == null || edtq104c.length() == 0)) {


                                lib.showError(q104.this, "Field of education", "Please type field of education");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {

                                //Log.d("P05", String.valueOf(exists));
                                if (exists && exist && existsY) {
                                    int typ1 = Integer.parseInt(autoTypeEducation.getText().toString().substring(0, 2));
                                    int lvl = Integer.parseInt(autoLevel.getText().toString().substring(0, 1));
                                    int yr = Integer.parseInt(autoYear.getText().toString().substring(0, 1));


                                    //Set q104 fir the current individual
                                    individual.setQ104(autoTypeEducation.getText().toString().substring(0, 2));
                                    individual.setQ104a(autoLevel.getText().toString().substring(0, 1));
                                    individual.setQ104b(autoYear.getText().toString().substring(0, 1));
                                    if (typ1 > 10) {
                                        individual.setQ104c(edtq104c.getText().toString());
                                    } else {
                                        individual.setQ104c(null);
                                    }
                                    myDB = new DatabaseHelper(q104.this);
                                    myDB.onOpen(myDB.getReadableDatabase());
                                    myDB.getWritableDatabase();


                                    myDB.updateInd("Q104", individual.getAssignmentID(), individual.getBatch(), individual.getQ104(), String.valueOf(individual.getSRNO()));
                                    myDB.updateInd("Q104a", individual.getAssignmentID(), individual.getBatch(), individual.getQ104a(), String.valueOf(individual.getSRNO()));
                                    myDB.updateInd("Q104b", individual.getAssignmentID(), individual.getBatch(), individual.getQ104b(), String.valueOf(individual.getSRNO()));
                                    myDB.updateInd("Q104c", individual.getAssignmentID(), individual.getBatch(), individual.getQ104c(), String.valueOf(individual.getSRNO()));

                                    myDB.close();
                                    Intent intent = new Intent(q104.this, q105.class);
                                    intent.putExtra("Individual", individual);
                                    intent.putExtra("Personroster", p1);
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

                Intent q1o2 = new Intent(q104.this, q103.class);
                q1o2.putExtra("Personroster", p1);
                startActivity(q1o2);
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intervie_control, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.pause:
                // Show the settings activity
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("[Demo!] Are you sure you want to pause the interview");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(getApplicationContext(), started_household.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q104.this).toBundle());
                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
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