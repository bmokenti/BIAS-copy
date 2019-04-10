package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class q1114 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    private DatabaseHelper myDB;
    protected RadioButton rbtn1, rbtn2;
    protected RadioButton selectedRbtn;
    protected EditText edittext;
    protected Individual individual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1114);
        setTitle("Q1114 ");
        Button btnnext = (Button)findViewById(R.id.btnNext);

            rbtn1 = (RadioButton) findViewById(R.id.q1114_1);
            rbtn2 = (RadioButton) findViewById(R.id.q1114_2);

            final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupq1114);

        Intent ii = getIntent();
        p1 = (PersonRoster) ii.getSerializableExtra("Personroster");




        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);


        List<HouseHold> hhh = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHouse=hhh.get(0);

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), ind.getAssignmentID());
        sample.getSTATUS();

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == individual.getSRNO()){
                p1 = p;
                break;
            }


        }

        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++) {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton) {
                bt[f] = ((RadioButton) o);
                if (ind.getQ1114() != null) {
                    if (!ind.getQ1114().equals("")) {
                        if (Integer.parseInt(ind.getQ1114()) == f + 1) {
                            RadioButton radioButton = bt[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }



        Button btnext = findViewById(R.id.btnnext);

        btnnext.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View v) {


                    int selectedId = rg.getCheckedRadioButtonId();
                    selectedRbtn = (RadioButton) findViewById(selectedId);

                    if (selectedRbtn == null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q1114.this);
                        builder.setTitle("Q114");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("Have \"+ p1.getP01() + \"been diagonised with  diabetes");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                        AlertDialog alertDialog = builder.show();
                        final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                        positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        positiveButton.setTextColor(Color.WHITE);
                        positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                        positiveButton.setLayoutParams(positiveButtonLL);


                    }

                    else {
                        //Set q101 for the current individual

                        Sample s = myDB.getSample(myDB.getReadableDatabase(),individual.getAssignmentID());
                        if(s.getStatusCode().equals("3") || (s.getStatusCode().equals("2") &&   thisHous.get(0).getHIVTB40().equals("0"))
                        || ((s.getStatusCode().equals("2") &&   thisHous.get(0).getHIVTB40().equals("1")) && p1.getP06().equals("2"))){
                            /*******************Launch VISIT***************************/


                            individual.setQ1114(selectedRbtn.getText().toString().substring(0,1));

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();

                            final CharSequence[] list1 = new String[3];
                            final ArrayList<String> list = new ArrayList<>();
                            try {
                                if (individual.getVISIT1() != null && individual.getVISIT2() == null && individual.getVISIT3() == null) {

                                    list1[0] = "Visit 1 : " + individual.getDATE1();
                                    list.add("Visit 1 : " + individual.getDATE1());
                                    list1[1] = "Visit 2 : ";
                                    list.add("Visit 2 : ");
                                } else if (individual.getVISIT1() != null && individual.getVISIT2() != null && individual.getVISIT3() == null) {

                                    list1[0] = "Visit 1 : " + individual.getDATE1();
                                    list.add("Visit 1 : " + individual.getDATE1());
                                    list1[1] = "Visit 2 : " + individual.getDATE2();
                                    list.add("Visit 2 : " + individual.getDATE2());
                                    list1[2] = "Visit 3 : ";
                                    list.add("Visit 3 : ");
                                } else if (individual.getVISIT1() != null && individual.getVISIT2() != null && individual.getVISIT3() != null) {

                                    list1[0] = "Visit 1 : " + individual.getDATE1();
                                    list.add("Visit 1 : " + individual.getDATE1());
                                    list1[1] = "Visit 2 : " + individual.getDATE2();
                                    list.add("Visit 2 : " + individual.getDATE2());
                                    list1[2] = "Visit 3 : ";
                                    list.add("Visit 3 : " + individual.getDATE3());
                                } else {
                                    list.add("Visit 1 ");
                                }


                            } catch (Exception f) {
                                f.printStackTrace();
                            }


                            final int FinalResult[] = new int[1];

                            final AlertDialog.Builder builder2 = new AlertDialog.Builder(q1114.this);
                            builder2.setTitle("Select Visit Number");

                            int i = 0;

                            final String[] ss = list.toArray(new String[list.size()]);
                            final Individual tempIndiv = individual;
                            builder2.setSingleChoiceItems(
                                    ss, // Items list
                                    -1, // Index of checked item (-1 = no selection)
                                    new DialogInterface.OnClickListener() // Item click listener
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            // Get the alert dialog selected item's text
                                            final CharSequence[] results = new String[6];
                                            results[0] = "1. Completed";
                                            results[1] = "2. Partially Completed";
                                            results[2] = "3. Present but not available for interviews";
                                            results[3] = "4. Refused";
                                            results[4] = "5. Postponed";
                                            results[5] = "6. Other (Specify)";


                                            if (i == 0) {
                                                //SHOW LIST FOR RESULTS
                                                final AlertDialog.Builder builder3 = new AlertDialog.Builder(q1114.this);
                                                builder3.setTitle("Select Visit 1 Result");
                                                builder3.setSingleChoiceItems(
                                                        results, // Items list
                                                        -1, // Index of checked item (-1 = no selection)
                                                        new DialogInterface.OnClickListener() // Item click listener
                                                        {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                //save the selected results
                                                                SQLiteDatabase db = myDB.getWritableDatabase();
                                                                ContentValues hhValues = new ContentValues();
                                                                hhValues.put("VISIT1", i + 1);
                                                                Date d = new Date();
                                                                CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss", d.getTime());
                                                                hhValues.put("DATE1", s.toString());
                                                                i = db.update
                                                                        ("Individual", // table
                                                                                hhValues, // column/value
                                                                                "Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                                                                                new String[]{String.valueOf(tempIndiv.getAssignmentID()), String.valueOf(tempIndiv.getBatch()), String.valueOf(tempIndiv.getSRNO())}
                                                                        );

                                                                /**************************************************/


                                                                //UPDATE HOUSEHOLD
                                                                myDB.updateHousehold(db,thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"Clear", "3");
                                                                myDB.close();


                                                                /*************************************************/

                                                                db.close();


                                                                //Restart the current activity
                                                                Intent intent = new Intent(q1114.this, started_household.class);
                                                                intent.putExtra("Household", thisHouse);
                                                                startActivity(intent);
                                                                finish();


                                                            }
                                                        });
                                                AlertDialog ad2 = builder3.show();

                                                //SET DIVIDER
                                                ListView listView = ad2.getListView();
                                                listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                                listView.setDividerHeight(3);


                                            } else if (ss.length == 2) {

                                                final AlertDialog.Builder builder3 = new AlertDialog.Builder(q1114.this);
                                                builder3.setTitle("Select Visit 2 Result");
                                                builder3.setSingleChoiceItems(
                                                        results, // Items list
                                                        -1, // Index of checked item (-1 = no selection)
                                                        new DialogInterface.OnClickListener() // Item click listener
                                                        {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                //save the selected results
                                                                SQLiteDatabase db = myDB.getWritableDatabase();
                                                                ContentValues hhValues = new ContentValues();
                                                                hhValues.put("VISIT2", i + 1);
                                                                Date d = new Date();
                                                                CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss", d.getTime());
                                                                hhValues.put("DATE2", s.toString());
                                                                i = db.update
                                                                        ("Individual", // table
                                                                                hhValues, // column/value
                                                                                "Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                                                                                new String[]{String.valueOf(tempIndiv.getAssignmentID()), String.valueOf(tempIndiv.getBatch()), String.valueOf(tempIndiv.getSRNO())}
                                                                        );

                                                                db.close();


                                                                //Restart the current activity
                                                                Intent intent = new Intent(q1114.this, started_household.class);
                                                                intent.putExtra("Household", thisHouse);
                                                                startActivity(intent);
                                                                finish();


                                                            }
                                                        });
                                                AlertDialog ad2 = builder3.show();

                                                //SET DIVIDER
                                                ListView listView = ad2.getListView();
                                                listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                                listView.setDividerHeight(3);

                                            } else {

                                                final AlertDialog.Builder builder3 = new AlertDialog.Builder(q1114.this);
                                                builder3.setTitle("Select Visit 3 Result");
                                                builder3.setSingleChoiceItems(
                                                        results, // Items list
                                                        -1, // Index of checked item (-1 = no selection)
                                                        new DialogInterface.OnClickListener() // Item click listener
                                                        {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                //save the selected results
                                                                SQLiteDatabase db = myDB.getWritableDatabase();
                                                                ContentValues hhValues = new ContentValues();
                                                                hhValues.put("VISIT3", i + 1);
                                                                Date d = new Date();
                                                                CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss", d.getTime());
                                                                hhValues.put("DATE3", s.toString());
                                                                i = db.update
                                                                        ("Individual", // table
                                                                                hhValues, // column/value
                                                                                "Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                                                                                new String[]{String.valueOf(tempIndiv.getAssignmentID()), String.valueOf(tempIndiv.getBatch()), String.valueOf(tempIndiv.getSRNO())}
                                                                        );

                                                                db.close();


                                                                //Restart the current activity
                                                                Intent intent = new Intent(q1114.this, started_household.class);
                                                                intent.putExtra("Household", thisHouse);
                                                                startActivity(intent);
                                                                finish();


                                                            }
                                                        });
                                                AlertDialog ad2 = builder3.show();

                                                //SET DIVIDER
                                                ListView listView = ad2.getListView();
                                                listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                                listView.setDividerHeight(3);


                                            }


                                        }
                                    });

                            AlertDialog ad = builder2.show();

                            //SET DIVIDER
                            ListView listView = ad.getListView();
                            //listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));


                            listView.setDividerHeight(3);


                            //OK Button layout
                            final Button positiveButton = ad.getButton(AlertDialog.BUTTON_POSITIVE);
                            LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                            positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                            positiveButton.setTextColor(Color.WHITE);
                            positiveButton.setBackgroundColor(Color.parseColor("#3FC0FF"));
                            positiveButton.setLayoutParams(positiveButtonLL);

                            /******************************END SET STATUS************************************************/





                        }else{
                            individual.setQ1114(selectedRbtn.getText().toString().substring(0,1));

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();

                            Intent intent = new Intent(q1114.this, HIVChildParentalConsent15_17.class);
                            intent.putExtra("Individual",  individual);
                            intent.putExtra("Personroster", p1);
                            startActivity(intent);
                        }




                    }

                }
            });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (individual.getQ1101().equals("1") || individual.getQ1101().equals("2")) {

                    finish();
                    Intent q1o3 = new Intent(q1114.this, q1102.class);
                    q1o3.putExtra("Individual", individual);
                    startActivity(q1o3);
                    finish();

                }
                else
                {
                    if (individual.getQ1101().equals("1") || individual.getQ1101().equals("2")) {

                        finish();
                        Intent q1o3 = new Intent(q1114.this, q1102.class);
                        q1o3.putExtra("Individual", individual);
                        startActivity(q1o3);
                        finish();

                    }
                    else
                    {

                        if (individual.getQ1103().equals("2") && individual.getQ1107().equals("2") && individual.getQ1108().equals("2") && individual.getQ1109().equals("2")) {
                            finish();
                            Intent intent = new Intent(q1114.this, q1109.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);
                        }
                        else {

                            finish();
                            Intent intent = new Intent(q1114.this, q1113.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);
                        }
                    }
                }

            }

        });
    }





    @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.q1114_1:

                    break;

                case R.id.q1114_2:
                    break;


                default:
                    break;

        }
    }
    //   thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

//    final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
//        for (PersonRoster p: roster
//        ) {
//        if (p.getSRNO() == ind.getSRNO()){
//            p1 = p;
//            break;
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intervie_control, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q1114.this).toBundle());

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


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
