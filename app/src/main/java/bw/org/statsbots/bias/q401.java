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

import java.io.Serializable;
import java.util.List;

public class q401 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected Individual individual;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected DatabaseHelper myDB;
    protected  RadioButton rbtn1,rbtn2,rbtn3, selected=null;
    protected RadioGroup rbtngroup;
    protected RadioButton selectedRbtn;
    protected q102 age;

    Individual pp1 = null;
    protected q101 sex;
    HouseHold thisHose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q401);

        setTitle("Q401 ");
       // lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q401_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q401_2);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.q401radioGroup);

        //rbtngroup = (RadioGroup)findViewById(R.id.q401radioGroup) ;

        //rbtn1.setOnClickListener(this);
        //rbtn2.setOnClickListener(this);


        //final int selectedId = rbtngroup.getCheckedRadioButtonId();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        //int p = 0;


        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;
        //myDB.getdataHhP(p1.getAssignmentID(), p1.getBatch());

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);

        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }

        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ401()!= null &&  !ind.getQ401().equals(""))
                {
                    if(Integer.parseInt(ind.getQ401())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        if( ((sample.getStatusCode().equals("3") )  || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0") )
                || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1")) && Integer.valueOf(individual.getQ102()) > 64
        ) ||((sample.getStatusCode().equals("2")  && thisHous.get(0).getHIVTB40().equals("1")) &&
                p1.getP06().equals("2") ) ))
        {

            individual.setQ401(null);
            individual.setQ402("00");
            individual.setQ402a(null);
            individual.setQ402b(null);

            individual.setQ403(null);
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


            Intent q1o2 = new Intent(q401.this, q601.class);
            q1o2.putExtra("Individual", individual);
            startActivity(q1o2);
        }


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q401.this);
                    builder.setTitle("Seaxual intercourse");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Have ever had sexual intercourse?"
                            + "i.e. vaginal sex, oral sex, anal sex: consented or non-consented");
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


                } else{
                                //Set q401 for the current individual
                               individual.setQ401(selectedRbtn.getText().toString().substring(0,1));

                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.getWritableDatabase();
                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                    myDB.close();

                                Intent q1o2 = new Intent(q401.this, q402.class);
                                q1o2.putExtra("Individual", individual);
                                startActivity(q1o2);

                            }

                        }



        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent q1o3 = new Intent(q401.this, q307.class);
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

            case R.id.q401_1:
                //Intent intent3 = new Intent(this, q402.class);
               //startActivity(intent3);
                break;
            case R.id.q401_2:
               // Intent intent4 = new Intent(this, q402.class);
                //startActivity(intent4);
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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q401.this).toBundle());

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
