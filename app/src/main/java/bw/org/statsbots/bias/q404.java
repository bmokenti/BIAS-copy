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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class q404 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1=null;
    protected String currentHH=null;protected DatabaseHelper myDB;
    protected LibraryClass lib;
    protected RadioButton rbtn1,rbtn2,rbtn3, selected=null;
    protected CheckBox chk1, chk2, chk3 ;

    protected RadioGroup rg;
    protected RadioButton selectedRbtn;
    HouseHold thisHose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q404);


        setTitle("Q404: SEXUAL HISTORY");
        lib = new LibraryClass();
        chk1 =  (CheckBox) findViewById(R.id.Q404_1);
        chk2 =  (CheckBox) findViewById(R.id.Q404_2);
        chk3 = (CheckBox) findViewById(R.id.Q404_3);

        rbtn1 =  (RadioButton)findViewById(R.id.q404a_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q404a_2);
        // rbtn3 =  (RadioButton)findViewById(R.id.q403_3);

        rg = (RadioGroup)findViewById(R.id.q404radioGroupa) ;
        //rbtn1 =  (RadioButton)findViewById(R.id.q403_1);
        //rbtn2 =  (RadioButton)findViewById(R.id.q403_2);

        //rbtn1.setOnClickListener(this);
        //rbtn2.setOnClickListener(this);

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
       // int p = 0;

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
        if (individual.getQ403().equals("2") && individual.getQ101().equals("1")) {
            individual.setQ404_1(null);
            individual.setQ404_2(null);
            individual.setQ404_3(null);
            individual.setQ404a(null);
            individual.setQ405(null);
            individual.setQ406("00");
            individual.setQ407(null);
            individual.setQ408(null);
            individual.setQ408a(null);
            individual.setQ410MadeAfraid(null);
            individual.setQ410Forced(null);
            individual.setQ410Physical(null);
            individual.setQ410Threatened(null);
            individual.setQ410Choked(null);
            individual.setQ410Pushed(null);
            individual.setQ410Slapped(null);

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q404.this, q501.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }

        if (individual.getQ403().equals("2") && individual.getQ101().equals("1")) {

            individual.setQ404_1(null);
            individual.setQ404_2(null);
            individual.setQ404_3(null);
            individual.setQ404a(null);
            individual.setQ405(null);
            individual.setQ406("00");
            individual.setQ407(null);
            individual.setQ408(null);
            individual.setQ408a(null);
            individual.setQ410MadeAfraid(null);
            individual.setQ410Forced(null);
            individual.setQ410Physical(null);
            individual.setQ410Threatened(null);
            individual.setQ410Choked(null);
            individual.setQ410Pushed(null);
            individual.setQ410Slapped(null);

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q404.this, q501.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }


//        if( ((sample.getStatusCode().equals("3") )  || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0") )
//                || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) && Integer.valueOf(individual.getQ102()) > 64
//        ) ||((sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1")) &&
//                p1.getP06().equals("2") ) ))
       //skip
        if ( individual.getQ403().equals("2")  && individual.getQ101().equals("2") &&
                (individual.getQ201().equals("2") || individual.getQ201().equals("3") ||individual.getQ201().equals("4") || individual.getQ201().equals("5") ||
                        (individual.getQ202() != null && individual.getQ202().equals("1"))))
        {


            individual.setQ404_1(null);
            individual.setQ404_2(null);
            individual.setQ404_3(null);
            individual.setQ404a(null);
            individual.setQ405(null);
            individual.setQ406("00");
            individual.setQ407(null);
            individual.setQ408(null);
            individual.setQ408a(null);
//            individual.setQ410MadeAfraid(null);
//            individual.setQ410Forced(null);
//            individual.setQ410Physical(null);
//            individual.setQ410Threatened(null);
//            individual.setQ410Choked(null);
//            individual.setQ410Pushed(null);
//            individual.setQ410Slapped(null);

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent intent = new Intent(q404.this, q410.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }

        if ( individual.getQ403().equals("2")  && individual.getQ101().equals("2") &&
                (individual.getQ201().equals("1") || individual.getQ201().equals("6") ))
        {

            individual.setQ404_1(null);
            individual.setQ404_2(null);
            individual.setQ404_3(null);
            individual.setQ404a(null);
            individual.setQ405(null);
            individual.setQ406("00");
            individual.setQ407(null);
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

            individual.setQ501(null);
            individual.setQ502(null);
            individual.setQ503(null);
            individual.setQ504_1(null);
            individual.setQ504_2(null);
            individual.setQ504_3(null);
            individual.setQ504_4(null);
            individual.setQ504_5(null);
            individual.setQ504_6(null);
            individual.setQ504_7(null);
            individual.setQ504_8(null);
            individual.setQ504_10(null);
            individual.setQ504_Other(null);
            individual.setQ504_OtherSpecify(null);



            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();


            Intent intent = new Intent(q404.this, q601.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }



        if(individual.getQ404_1()!= null &&  !individual.getQ404_1().equals(""))
        {
            if(Integer.parseInt(individual.getQ404_1())== 1)
            {
                chk1.setChecked(true);

            }else
            {
                chk1.setChecked(false);
            }
        }

        if(ind.getQ404_2()!= null &&  !ind.getQ404_2().equals(""))
        {
            if(Integer.parseInt(ind.getQ404_2())== 1)
            {
                chk2.setChecked(true);

            }else
            {
                chk2.setChecked(false);
            }
        }

        if(ind.getQ404_3()!= null &&  !ind.getQ404_3().equals(""))
        {
            if(Integer.parseInt(ind.getQ404_3())== 1)
            {
                chk3.setChecked(true);

            }else
            {
                chk3.setChecked(false);
            }
        }

        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ404a()!= null &&  !ind.getQ404a().equals(""))
                {
                    if(Integer.parseInt(ind.getQ404a())==f+1)
                    {
                        RadioButton radioButton = bt[f];
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

                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (!chk1.isChecked() && !chk2.isChecked() && !chk3.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q404.this);
                    builder.setTitle("Q404 Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Which type of sex have you had in the past 12 months.: multiple response?");
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


                } else {
                    if (selectedRbtn == null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q404.this);
                        builder.setTitle("Q404a: Error");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("Was a condom used the last time you had sex in the past 12 months?");
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


                    } else {
                        //Set q404 for the current individual



                        if(chk1.isChecked()){
                            individual.setQ404_1("1");
                        }else{
                            individual.setQ404_1("2");
                        }

                        if(chk2.isChecked()){
                            individual.setQ404_2("1");
                        }else{
                            individual.setQ404_2("2");
                        }


                        if(chk3.isChecked()){
                            individual.setQ404_3("1");
                        }else{
                            individual.setQ404_3("2");
                        }

                        individual.setQ404a(selectedRbtn.getText().toString().substring(0,1));

                        myDB.onOpen(myDB.getReadableDatabase());
                        myDB.getWritableDatabase();
                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                        myDB.close();

                        Intent q1o2 = new Intent(q404.this, q405.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);

                    }

                }
            }
        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent q1o3 = new Intent(q404.this, q403.class);
                q1o3.putExtra("Individual", individual);
                q1o3.putExtra("Personroster", p1);
                startActivity(q1o3);
            }


        });

    }


    @Override
    public void onClick(View view) {

        //
        switch (view.getId()) {

            case R.id.q404a_1:
                //Intent intent3 = new Intent(this, q501.class);
                //startActivity(intent3);
                break;

            case R.id.q404a_2:
                //Intent intent4 = new Intent(this, q501.class);
                // startActivity(intent4);
                break;


            default:
                break;

        }
    }


    //   thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q404.this).toBundle());

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
