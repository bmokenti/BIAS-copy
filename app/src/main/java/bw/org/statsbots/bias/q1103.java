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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q1103 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected RadioButton rbtnY,rbtnN,  selectedRbtn;protected DatabaseHelper myDB;
    protected RadioGroup rg;
    protected CheckBox chkb99;
    protected TextView q1103aQues, txt1103dd, txt1103wks;
    protected EditText q1103dd, q1103wks;
    protected LinearLayout Q1103linear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1103);

        setTitle("Q1103:");
        lib = new LibraryClass();
        rbtnY = (RadioButton) findViewById(R.id.q1103_1);
        rbtnN = (RadioButton) findViewById(R.id.q1103_2);
        q1103aQues = findViewById(R.id.q1103a);
        q1103dd = findViewById(R.id.q1103a_days);
        q1103wks = findViewById(R.id.q1103a_weeks);
        chkb99 = findViewById(R.id.q1103a_99);
        Q1103linear = findViewById(R.id.q1103alinear);
        txt1103dd = findViewById(R.id.dd);
        txt1103wks = findViewById(R.id.wks);


        rg = (RadioGroup) findViewById(R.id.q1103radioGroup);

        /*rbtn1.setOnClickListener(this);
        rbtn2.setOnClickListener(this);*/


        final int selectedId = rg.getCheckedRadioButtonId();

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

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();


        final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
        ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }


        }
if(individual.getQ1101() != null && individual.getQ1101().equals("1") )
{


    individual.setQ202(null);
        individual.setQ1103(null);
    individual.setQ1103(null);
    individual.setQ1103aDD("00");
    individual.setQ1103aWks("00");
        individual.setQ1104(null);
        individual.setQ1105(null);
        individual.setQ1106(null);
        individual.setQ1106a(null);
    individual.setQ1106b(null);
    individual.setQ1106bOther(null);
        individual.setQ1107(null);
        individual.setQ1107aWks("00");
    individual.setQ1107aDD("00");
        individual.setQ1108(null);
    individual.setQ1108aWks("00");
    individual.setQ1108aDD("00");
    individual.setQ1109(null);
    individual.setQ1110(null);
    individual.setQ1111(null);
    individual.setQ1111Other(null);
    individual.setQ1112(null);
    individual.setQ1112_Other(null);

    individual.setQ1113(null);
    individual.setQ1113Other(null);



    myDB.onOpen(myDB.getReadableDatabase());
        myDB.getWritableDatabase();
        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
        myDB.close();
    Intent q1o3 = new Intent(q1103.this, q1114.class);
    q1o3.putExtra("Individual", individual);
    startActivity(q1o3);

}

        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rg.getChildCount();f++) {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton) {
                bt[f] = ((RadioButton) o);
                if (ind.getQ1103() != null ) {
                    if (Integer.parseInt(ind.getQ1103()) == f + 1) {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        TextView q1101atext = findViewById(R.id.q1101atxt);
                        RadioGroup rg1 = (RadioGroup) findViewById(R.id.q1103radioGroup);
                        // Is the current Radio Button checked?
                        boolean checked = radioButton.isChecked();
                        View v = radioButton;
                        switch (v.getId()) {
                            case R.id.q1103_1:
                                if (checked) {
                                    q1103aQues.setTextColor(Color.BLACK);

                                    q1103dd.setEnabled(true);
                                    q1103dd.setBackgroundResource(android.R.drawable.edit_text);
                                    // Q1103linear.setActivated(true);
                                    txt1103dd.setTextColor(Color.BLACK);
                                    txt1103wks.setTextColor(Color.BLACK);
                                    chkb99.setEnabled(true);
                                    q1103wks.setEnabled(true);
                                    q1103wks.setBackgroundResource(android.R.drawable.edit_text);


                                }
                                break;


                            case R.id.q1103_2:
                                if (checked) {
                                    // Q1103linear.setActivated(false);
                                    q1103aQues.setTextColor(Color.LTGRAY);
                                    chkb99.setChecked(false);
                                    chkb99.setEnabled(false);
                                    q1103dd.setEnabled(false);
                                    q1103dd.setText("");
                                    // q1103dd.setBackgroundColor(Color.LTGRAY);
                                    txt1103dd.setTextColor(Color.LTGRAY);
                                    txt1103wks.setTextColor(Color.LTGRAY);
                                    q1103wks.setEnabled(false);
                                    q1103wks.setText("");
                                    // q1103wks.setBackgroundColor(Color.LTGRAY);
                                }
                                break;
                        }
                    }
                }
            }
        }

        if(ind.getQ1103aDD()!= null)
        {
            q1103dd.setText(ind.getQ1103aDD());
        }

        if(ind.getQ1103aWks()!= null)
        {
            q1103wks.setText(ind.getQ1103aWks());
        }

        Button btnnext = findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q1103.this);
                    builder.setTitle("Q1103: Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Do you have a cough?");
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


                    if ((((q1103dd.length() == 0 && q1103wks.length() == 0 && !chkb99.isChecked()))) && (rbtnY.isChecked())) {

                        lib.showError(q1103.this, "Q1103", "a) How long have you had this cough?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {


                        if (((q1103dd.getText().toString().equals("0")|| q1103dd.getText().toString().equals("00"))   &&
                                        ( q1103wks.getText().toString().equals("0") || q1103wks.getText().toString().equals("00")) && rbtnY.isChecked()) )
                        {
                            lib.showError(q1103.this, "Q1103", "a) Days and weeks can not be 0/00");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {


                            if (rbtnN.isChecked()) {
                                individual.setQ1103(selectedRbtn.getText().toString().substring(0,1));
                                individual.setQ1103aDD("00");
                                individual.setQ1103aWks("00");
                                individual.setQ1104(null);
                                individual.setQ1105(null);
                                individual.setQ1106(null);
                                individual.setQ1106a(null);
                                individual.setQ1106b(null);
                                individual.setQ1106bOther(null);

                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                myDB.close();

                                Intent q1o3i = new Intent(q1103.this, q1107.class);
                                q1o3i.putExtra("Individual", individual);
                                startActivity(q1o3i);

                            } else {

                                individual.setQ1103(selectedRbtn.getText().toString().substring(0,1));


                                if(q1103dd.getText().toString().length()==0)
                                {
                                    individual.setQ1103aDD("00");
                                }
                                else if(q1103dd.getText().toString().length()==1)
                                {
                                    individual.setQ1103aDD("0"+q1103dd.getText().toString());
                                }
                                else{
                                    individual.setQ1103aDD(q1103dd.getText().toString());
                                }


                                if(q1103wks.getText().toString().length()==0){
                                    individual.setQ1103aWks("00");
                                }else if(q1103wks.getText().toString().length()==1){
                                    individual.setQ1103aWks("0"+q1103wks.getText().toString());
                                }else{
                                    individual.setQ1103aWks(q1103wks.getText().toString());
                                }

                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                myDB.close();

                                Intent q1o3 = new Intent(q1103.this, q1104.class);
                                q1o3.putExtra("Individual", individual);
                                startActivity(q1o3);

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

                if( sample.getStatusCode().equals("1") || (sample.getStatusCode().equals("2") &&thisHous.get(0).getHIVTB40().equals("1") &&
                        (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 )))
                {
                    Intent q1o3 = new Intent(q1103.this, q1101.class);
                    q1o3.putExtra("Individual", individual);
                    startActivity(q1o3);

                }
                else
                {
                    finish();
                    Intent q1o3 = new Intent(q1103.this, q1102.class);
                    q1o3.putExtra("Individual", individual);
                    startActivity(q1o3);;
                }

            }


        });
    }


    public void onRadioButtonClicked(View v) {
        TextView q1101atext = findViewById(R.id.q1101atxt);
        RadioGroup rg1 = (RadioGroup) findViewById(R.id.q1103radioGroup);
        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q1103_1:
                if (checked) {
                    q1103aQues.setTextColor(Color.BLACK);

                    q1103dd.setEnabled(true);
                    q1103dd.setBackgroundResource(android.R.drawable.edit_text);
                    // Q1103linear.setActivated(true);
                    txt1103dd.setTextColor(Color.BLACK);
                    txt1103wks.setTextColor(Color.BLACK);
                    chkb99.setEnabled(true);
                    q1103wks.setEnabled(true);
                   q1103wks.setBackgroundResource(android.R.drawable.edit_text);


                }
                break;


            case R.id.q1103_2:
                if (checked) {
                    // Q1103linear.setActivated(false);
                    q1103aQues.setTextColor(Color.LTGRAY);
                    chkb99.setChecked(false);
                    chkb99.setEnabled(false);
                    q1103dd.setEnabled(false);
                    q1103dd.setText("");
                   // q1103dd.setBackgroundColor(Color.LTGRAY);
                    txt1103dd.setTextColor(Color.LTGRAY);
                    txt1103wks.setTextColor(Color.LTGRAY);
                    q1103wks.setEnabled(false);
                    q1103wks.setText("");
                   // q1103wks.setBackgroundColor(Color.LTGRAY);
                }
                break;
        }
    }


                /*
            case R.id.q1103a_99:
                if (checked){
                    q1103aQues.setTextColor(Color.BLACK);
                    //Q1103linear.setActivated(false);
            q1103dd.setEnabled(false);
                    q1103dd.setText("");
            q1103dd.setBackgroundColor(Color.LTGRAY);


         q1103wks.setEnabled(false);
                    q1103wks.setText("");
                q1103wks.setBackgroundColor(Color.LTGRAY);
                    }
                break;

                }
        }*/

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked

        if (checked) {

            q1103dd.setEnabled(false);
            q1103wks.setEnabled(false);
            q1103dd.setText("99");
            q1103wks.setText("99");
            txt1103dd.setTextColor(Color.LTGRAY);
            txt1103wks.setTextColor(Color.LTGRAY);
        }
        // Put some meat on the sandwich
        else {
            // Remove the meat
            txt1103dd.setTextColor(Color.LTGRAY);
            txt1103wks.setTextColor(Color.LTGRAY);
            q1103dd.setEnabled(true);
            q1103wks.setEnabled(true);


        }
    }


    @Override
    public void onClick(View view) {

        //
        switch (view.getId())
        {
            case R.id.q1103_1:

                break;

            case R.id.q1103_2:

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
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q1103.this).toBundle());

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

/*
Intent intent = new Intent(q1103.this, q1104.class );
                intent.putExtra("Household", thisHouse);
                startActivity(intent);
 */