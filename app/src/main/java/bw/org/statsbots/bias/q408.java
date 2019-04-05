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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q408 extends AppCompatActivity implements View.OnClickListener, Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1=null;
    protected String currentHH=null;protected DatabaseHelper myDB;
    protected LibraryClass lib;
    protected RadioButton rbtn1,rbtn2, rbtna1,rbtna2;
    protected RadioGroup rg, rga;
    protected  TextView   q408alabel;
    protected RadioButton selectedRbtn, selectedRbtna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q408);


        setTitle("Q408: SEXUAL HISTORY");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q408_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q408_2);

        rbtna1 =  (RadioButton)findViewById(R.id.q408a_1);
        rbtna2 =  (RadioButton)findViewById(R.id.q408a_2);

        rg = (RadioGroup)findViewById(R.id.q408radioGroup) ;
        rga = (RadioGroup)findViewById(R.id.q408radioGroupa) ;
        q408alabel = (TextView)findViewById(R.id.q408atxt);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        //int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }
        }


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q408_2)
                {
                    // is checked
                    rbtna1.setEnabled(false);
                    rbtna2.setEnabled(false);
                    q408alabel.setTextColor(Color.LTGRAY);
                    rbtna1.setChecked(false);
                    rbtna2.setChecked(false);
                }
                else
                {
                    // not checked
                    rbtna1.setEnabled(true);
                    rbtna2.setEnabled(true);
                    q408alabel.setTextColor(Color.BLACK);
                }
            }
        });

        if ((individual.getQ101().equals("2") &&  individual.getQ401().equals("1")) && (individual.getQ201().equals("1")
                || individual.getQ201().equals("4") ||
                individual.getQ201().equals("5") || individual.getQ201().equals("6")) || (Integer.parseInt(individual.getQ102()) > 49 ))
        {


            individual.setQ408(null);
            individual.setQ408a(null);
            individual.setQ410MadeAfraid(null);
            individual.setQ410Forced(null);
            individual.setQ410Physical(null);
            individual.setQ410Threatened(null);
            individual.setQ410Choked(null);
            individual.setQ410Pushed(null);
            individual.setQ410Slapped(null);
            //individual.setQ410(null);

//            individual.setQ501(null);
//            individual.setQ502(null);
//            individual.setQ503(null);
//            individual.setQ504_1(null);
//            individual.setQ504_2(null);
//            individual.setQ504_3(null);
//            individual.setQ504_4(null);
//            individual.setQ504_5(null);
//            individual.setQ504_6(null);
//            individual.setQ504_7(null);
//            individual.setQ504_8(null);
//            individual.setQ504_10(null);
//            individual.setQ504_Other(null);
//            individual.setQ504_OtherSpecify(null);



            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q408.this, q601.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {

                        //do nothing
        }

        if ((individual.getQ101().equals("1") && individual.getQ401().equals("1") )  && (individual.getQ201().equals("1") || individual.getQ201().equals("4") ||
                individual.getQ201().equals("5") || individual.getQ201().equals("6")) || Integer.valueOf(individual.getQ102()) >= 50 )
        {


            individual.setQ408(null);
            individual.setQ408a(null);
            individual.setQ410MadeAfraid(null);
            individual.setQ410Forced(null);
            individual.setQ410Physical(null);
            individual.setQ410Threatened(null);
            individual.setQ410Choked(null);
            individual.setQ410Pushed(null);
            individual.setQ410Slapped(null);
            //individual.setQ410(null);



            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();


            Intent intent = new Intent(q408.this, q501.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }
        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ408()!= null &&  !ind.getQ408().equals(""))
                {
                    if(Integer.parseInt(ind.getQ408())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bt1 = new RadioButton[2];
        for(int f=0;f<rga.getChildCount();f++)
        {
            View o = rga.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt1[f]=((RadioButton)o);
                if(ind.getQ408a()!= null &&  !ind.getQ408a().equals(""))
                {
                    if(Integer.parseInt(ind.getQ408a())==f+1)
                    {
                        RadioButton radioButton = bt1[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }



        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                {
                    int selectedId = rg.getCheckedRadioButtonId();
                    selectedRbtn = (RadioButton) findViewById(selectedId);
                    if (selectedRbtn == null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q408.this);
                        builder.setTitle("Q408: Error");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("In the past 12 months have you had sex with someone you are not married to or living together?");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id)
                            {

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



                    } else {
                        int selectedIda = rga.getCheckedRadioButtonId();
                        selectedRbtna = (RadioButton) findViewById(selectedIda);
                        if (selectedRbtna == null && rbtn1.isChecked()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(q408.this);
                            builder.setTitle("Q408a: Error");
                            builder.setIcon(R.drawable.ic_warning_orange_24dp);

                            builder.setMessage("Did you use a condom the last time you had sex with this partner?");
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
                    else
                        {if(rbtn2.isChecked())
                        {
                            individual.setQ408(selectedRbtn.getText().toString().substring(0,1));
                            individual.setQ408a(null);

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();

                            Intent q1o2 = new Intent(q408.this, q410.class);
                            q1o2.putExtra("Individual", individual);
                            startActivity(q1o2);

                        }
                        else{
                            //Set q408 for the current individual

                           individual.setQ408(selectedRbtn.getText().toString().substring(0,1));
                           individual.setQ408a(selectedRbtna.getText().toString().substring(0,1));

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();


                            Intent q1o2 = new Intent(q408.this, q410.class);
                            q1o2.putExtra("Individual", individual);
                            startActivity(q1o2);

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
                if (individual.getQ301().equals("2") && individual.getQ302().equals("2")) {

                    Intent intent = new Intent(q408.this, q406.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);

                }else
                {
                    finish();
                    Intent intent = new Intent(q408.this, q407.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                }

            }


        });

    }


    @Override
    public void onClick(View view) {

        //
        switch (view.getId()) {

            case R.id.q408_1:
                //Intent intent3 = new Intent(this, q501.class);
                //startActivity(intent3);
                break;

            case R.id.q408_2:
                //Intent intent4 = new Intent(this, q501.class);
                // startActivity(intent4);
                break;
            case R.id.q408a_1:
                //Intent intent4 = new Intent(this, q501.class);
                // startActivity(intent4);
                break;
            case R.id.q408a_2:
                //Intent intent4 = new Intent(this, q501.class);
                // startActivity(intent4);
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q408.this).toBundle());

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
