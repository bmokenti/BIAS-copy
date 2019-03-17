package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q1101 extends AppCompatActivity implements  Serializable {


    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected RadioButton rbtn1,rbtn2,rbtn3, rbtn4, rbtna1, rbtna2, rbtna3 ,rbtna4 , rbtna5, rbtnaOther, selectedRbtn, selectedRbtn1;
    //protected RadioGroup rg, rg1;
    TextView q1101atext;
    protected DatabaseHelper myDB;
    EditText q1101aOther;
    protected RadioGroup rg1, rg2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1101);

        setTitle("Q1101 TB TREATMENT");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q1101_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1101_2);
        rbtn3 = (RadioButton) findViewById(R.id.q1101_3);
        rbtn4 = (RadioButton) findViewById(R.id.q1101_4);
        q1101atext = findViewById(R.id.q1101atxt);
        rg1 = (RadioGroup) findViewById(R.id.q1101radioGroup);
        rg2 = (RadioGroup) findViewById(R.id.q1101radioGroupa);




        lib = new LibraryClass();



        rbtna1 = (RadioButton) findViewById(R.id.q1101a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q1101a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q1101a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q1101a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q1101a_5);
        rbtnaOther = (RadioButton) findViewById(R.id.q1101aOther);
        q1101aOther = (EditText) findViewById(R.id.q1101a_Other);


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
      //  int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();



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
//
//
//        if (sample.getStatusCode().equals("1") || (sample.getStatusCode().equals("2") &&thisHous.get(0).getHIVTB40().equals("1") &&
//                (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 )))

        if( sample.getStatusCode().equals("1") || (sample.getStatusCode().equals("2") &&thisHous.get(0).getHIVTB40().equals("1") &&
              (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 ) ))
        {

            rbtna1.setEnabled(false);
            rbtna2.setEnabled(false);
            rbtna3.setEnabled(false);
            rbtna4.setEnabled(false);
            rbtna5.setEnabled(false);
            rbtnaOther.setEnabled(false);
            q1101aOther.setEnabled(false);

            rbtna1.setChecked(false);
            rbtna2.setChecked(false);
            rbtna3.setChecked(false);
            rbtna4.setEnabled(false);
            rbtna5.setChecked(false);
            rbtnaOther.setChecked(false);

        }






        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.H11_Other)
                {
                    // is checked
                    q1101aOther.setVisibility(View.VISIBLE);
                }
                else
                {
                    // not checked
                    q1101aOther.setVisibility(View.INVISIBLE);
                    q1101aOther.setText("");
                }
            }
        });



        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if((i == R.id.q1101_3 ||i == R.id.q1101_4 ) && (sample.getStatusCode().equals("2") || sample.getStatusCode().equals("3")))
                {
                    // is checked
                    rbtna1.setEnabled(false);
                    rbtna2.setEnabled(false);
                    rbtna3.setEnabled(false);
                    rbtna4.setEnabled(false);
                    rbtna5.setEnabled(false);
                    rbtnaOther.setEnabled(false);
                    q1101aOther.setEnabled(false);

                    rbtna1.setChecked(false);
                    rbtna2.setChecked(false);
                    rbtna3.setChecked(false);
                    rbtna4.setChecked(false);
                    rbtna5.setChecked(false);

                    q1101atext.setTextColor(Color.LTGRAY);

                }
                else
                {
                    rbtna1.setEnabled(true);
                    rbtna2.setEnabled(true);
                    rbtna3.setEnabled(true);
                    rbtna4.setEnabled(true);
                    rbtna5.setEnabled(true);
                    rbtnaOther.setEnabled(true);
                    q1101aOther.setEnabled(true);
                    q1101atext.setTextColor(Color.BLACK);
                }
            }
        });



        RadioButton[] bt = new RadioButton[4];
        for(int f=0;f<rg1.getChildCount();f++)
        {
            View o = rg1.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ1101()!= null &&  !ind.getQ1101().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1101())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] bta = new RadioButton[6];
        for(int f=0;f<rg2.getChildCount();f++)
        {
            View o = rg2.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bta[f]=((RadioButton)o);
                if(ind.getQ1101a()!= null &&  !ind.getQ1101a().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1101a())==f+1)
                    {
                        RadioButton radioButton = bta[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }
        if(ind.getQ1101aOther()!= null)
        {
            q1101aOther.setText(ind.getQ1101aOther());
        }

        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg1.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q1101.this);
                    builder.setTitle("Q1101: Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Are you in TB treatment");
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
                    // individual.setQ1101(selectedRbtn.getText().toString().substring(0,1));
                    int selectedId1 = rg2.getCheckedRadioButtonId();
                    selectedRbtn1 = (RadioButton) findViewById(selectedId1);

                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.getWritableDatabase();
                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                    myDB.close();

                    if ((selectedRbtn1 == null) && (rbtn1.isChecked() || rbtn2.isChecked()) && (sample.getStatusCode().equals("2") || sample.getStatusCode().equals("3")) ) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q1101.this);
                        builder.setTitle("Q1101: Error");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("a): Where are you getting  your TB treatment");
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
                        // individual.setQ1101(selectedRbtn.getText().toString().substring(0,1));


                        if (rbtnaOther.isChecked() && q1101aOther.length() == 0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(q1101.this);
                            builder.setTitle("Q1101: Error: Other");
                            builder.setIcon(R.drawable.ic_warning_orange_24dp);

                            builder.setMessage("a): Please specify");
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


                            if (rbtn3.isChecked() || rbtn4.isChecked() ) {

                                individual.setQ1101(selectedRbtn.getText().toString().substring(0, 1));

                                myDB.onOpen(myDB.getReadableDatabase());
                                myDB.getWritableDatabase();
                                myDB.updateInd("Q1101",individual.getAssignmentID(),individual.getBatch(),ind.getQ1101(),String.valueOf(individual.getSRNO()));
                                myDB.close();


                                Intent intent = new Intent(q1101.this, q1102.class);
                                intent.putExtra("Individual", individual);
                                startActivity(intent);

                            }  else {
                                    if ((rbtn1.isChecked() || rbtn2.isChecked()) && sample.getStatusCode().equals("1")) {

                                        individual.setQ1101(selectedRbtn.getText().toString().substring(0, 1));

                                        myDB.onOpen(myDB.getReadableDatabase());
                                        myDB.getWritableDatabase();
                                        myDB.updateInd("Q1101",individual.getAssignmentID(),individual.getBatch(),ind.getQ1101(),String.valueOf(individual.getSRNO()));
                                       // myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                        myDB.close();

                                        Intent intent = new Intent(q1101.this, HIVChildParentalConsent15_17.class);
                                        intent.putExtra("Individual", individual);
                                        startActivity(intent);

                                    } else {
                                        //Set q1101 for the current individual
                                        individual.setQ1101(selectedRbtn.getText().toString().substring(0, 1));
                                        individual.setQ1101a(selectedRbtn1.getText().toString().substring(0, 1));
                                        individual.setQ1101aOther(q1101aOther.getText().toString());


                                        myDB.onOpen(myDB.getReadableDatabase());
                                        myDB.getWritableDatabase();
                                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                        myDB.updateInd("Q1101",individual.getAssignmentID(),individual.getBatch(),ind.getQ1101(),String.valueOf(individual.getSRNO()));
                                        myDB.updateInd("Q1101a",individual.getAssignmentID(),individual.getBatch(),ind.getQ1101a(),String.valueOf(individual.getSRNO()));
                                        myDB.updateInd("Q1101aOther",individual.getAssignmentID(),individual.getBatch(),ind.getQ1101aOther(),String.valueOf(individual.getSRNO()));
                                        myDB.close();
                                        Intent q1o3 = new Intent(q1101.this, q1102.class);
                                        q1o3.putExtra("Individual", individual);
                                        startActivity(q1o3);
                                        //setting q103 value
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
                if( ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0")) || sample.getStatusCode().equals("3")
                        || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") && p1.getP06().equals("2")))
                        && (individual.getQ801() != null &&  individual.getQ801().equals("2") ) ) {
                    finish();
                    Intent intent = new Intent(q1101.this, q801.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                }
                    else {
                    if (Integer.valueOf(individual.getQ102()) >= 65 &&
                            (sample.getStatusCode().equals("3") ||
                                    (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1"))
                                    || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("0"))))
                    {
                        finish();
                        Intent intent = new Intent(q1101.this, q705.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);
                    } else {

                        if ((individual.getQ801f().equals("2") || individual.getQ801f().equals("3") ||
                                individual.getQ801f().equals("4") || individual.getQ801f().equals("9")) && individual.getQ801a().equals("1")
                                && individual.getQ101().equals("1"))
                        {
                            finish();
                            Intent intent = new Intent(q1101.this, q801.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);
                        } else {
                            finish();
                            Intent intent = new Intent(q1101.this, q1017.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);



                        }

                    }
                }
            }

        });
    }
}
