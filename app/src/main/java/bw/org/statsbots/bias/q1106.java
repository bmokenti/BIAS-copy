package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
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

public class q1106 extends AppCompatActivity implements Serializable {
    protected  HouseHold thisHouse;
    protected  Individual idv;
    protected RadioGroup rGroup1, rGroup2,rGroup3;
    protected RadioButton rbtn1, rbtn2, rbtn6a1 ,rbtn6a2, rbtn6a3, rbtn6a9, rbtn6b1, rbtn6b2, rbtn6b3, rbtn6b4,rbtn6b5other, selectedRbtn1, selectedRbtn2, selectedRbtn3;
    protected EditText q1106btxtOther;
    protected LibraryClass lib;protected DatabaseHelper myDB;
    PersonRoster p1 = null;
    protected Individual individual;
    protected TextView txt1, txt2, txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1106);
        setTitle("Q1106:");
        lib = new LibraryClass();

        rGroup1 = findViewById(R.id.q1106rgruop1);
        rGroup2 = findViewById(R.id.q1106rgruop2);
        rGroup3 = findViewById(R.id.q1106rgruop3);
        q1106btxtOther = findViewById(R.id.q1106btxtOther);
        rbtn1 = findViewById(R.id.q1106_1);
        rbtn2 = findViewById(R.id.q1106_2);

        rbtn6a1 = findViewById(R.id.q1106a_1);
        rbtn6a2 = findViewById(R.id.q1106a_2);
        rbtn6a3 = findViewById(R.id.q1106a_3);
        rbtn6a9 = findViewById(R.id.q1106a_9);
        rbtn6b1 = findViewById(R.id.q1106b_1);
        rbtn6b2 = findViewById(R.id.q1106b_2);
        rbtn6b3 = findViewById(R.id.q1106b_3);
        rbtn6b4 = findViewById(R.id.q1106b_4);
        rbtn6b5other = findViewById(R.id.q1106b_Other);

        txt2 = findViewById(R.id.txt1106q2);
        txt3 = findViewById(R.id.txt1106q3);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();


        RadioButton[] bt = new RadioButton[2];
        for(int f=0;f<rGroup1.getChildCount();f++)
        {
            View o = rGroup1.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ1106()!= null &&  !ind.getQ1106().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1106())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }


        RadioButton[] bta = new RadioButton[4];
        for(int f=0;f<rGroup2.getChildCount();f++)
        {
            View o = rGroup2.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bta[f]=((RadioButton)o);
                if(ind.getQ1106a()!= null &&  !ind.getQ1106a().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1106a())==f+1)
                    {
                        RadioButton radioButton = bta[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        RadioButton[] btb = new RadioButton[5];
        for(int f=0;f<rGroup2.getChildCount();f++)
        {
            View o = rGroup2.getChildAt(f);
            if (o instanceof RadioButton)
            {
                btb[f]=((RadioButton)o);
                if(ind.getQ1106b()!= null &&  !ind.getQ1106b().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1106b())==f+1)
                    {
                        RadioButton radioButton = btb[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        if(ind.getQ1106bOther()!= null)
        {
            rbtn6b5other.setText(ind.getQ1106bOther());
        }



        Button btnnext = findViewById(R.id.btnNext);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(q1106.this, q1107.class);
                //startActivity(intent);
                int selectedId = rGroup1.getCheckedRadioButtonId();
                selectedRbtn1 = (RadioButton) findViewById(selectedId);

                if (selectedRbtn1 == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q1106.this);
                    builder.setTitle("Q1106: Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Did you submit sputum?");
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

                    int selectedId1 = rGroup2.getCheckedRadioButtonId();
                    selectedRbtn2 = (RadioButton) findViewById(selectedId1);

                    if (selectedRbtn2 == null && rbtn1.isChecked()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q1106.this);
                        builder.setTitle("Q1106: Error");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("If YES, what was the result?");
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

                        int selectedId3 = rGroup3.getCheckedRadioButtonId();
                        selectedRbtn3 = (RadioButton) findViewById(selectedId3);

                        if (rbtn2.isChecked() && selectedRbtn3 == null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(q1106.this);
                            builder.setTitle("Q1106: Error");
                            builder.setIcon(R.drawable.ic_warning_orange_24dp);
                            builder.setMessage("b) If NO, why not?");
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


                            if (rbtn2.isChecked() && q1106btxtOther.length() == 0 && rbtn6b5other.isChecked()) {
                                lib.showError(q1106.this, "Q1106 Other", "Please specify");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                            } else {


                                if (rbtn2.isChecked()) {


                                    individual.setQ1106(selectedRbtn1.getText().toString().substring(0, 1));
                                    individual.setQ1106b(selectedRbtn3.getText().toString().substring(0, 1));
                                    individual.setQ1106bOther(q1106btxtOther.getText().toString());

                                    myDB.onOpen(myDB.getReadableDatabase());
                                    myDB.getWritableDatabase();
                                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                    myDB.close();

                                    Intent q1o3 = new Intent(q1106.this, q1107.class);
                                    q1o3.putExtra("Individual", individual);
                                    startActivity(q1o3);

                                } else {


                                    if (rbtn1.isChecked() && selectedRbtn2 != null) {


                                        individual.setQ1106(selectedRbtn1.getText().toString().substring(0, 1));
                                        individual.setQ1106a(selectedRbtn2.getText().toString().substring(0, 1));

                                        myDB.onOpen(myDB.getReadableDatabase());
                                        myDB.getWritableDatabase();
                                        myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                        myDB.close();

                                        Intent q1o3 = new Intent(q1106.this, q1107.class);
                                        q1o3.putExtra("Individual", individual);
                                        startActivity(q1o3);

                                    }
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
                q1106.super.onBackPressed();
            }


        });
    }



    public void onRadioButtonClicked(View v){



        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()){
            case R.id.q1106_1:
                if(checked)

                    for(int i = 0; i < rGroup3.getChildCount(); i++){
                        ((RadioButton)rGroup3.getChildAt(i)).setEnabled(false);
                        txt3.setTextColor(Color.LTGRAY);
                        rbtn6b1.setChecked(false);
                        rbtn6b2.setChecked(false);
                        rbtn6b3.setChecked(false);
                        rbtn6b4.setChecked(false);
                        rbtn6b5other.setChecked(false);
                        q1106btxtOther.setVisibility(View.INVISIBLE);

                    }

                for(int i = 0; i < rGroup2.getChildCount(); i++){
                    ((RadioButton)rGroup2.getChildAt(i)).setEnabled(checked);
                    txt2.setTextColor(Color.BLACK);


                }
                  //  rGroup2.setVisibility(View.VISIBLE);

                break;

            case R.id.q1106_2:
                if(checked)

                    q1106btxtOther.setVisibility(View.INVISIBLE);
                    for(int i = 0; i < rGroup3.getChildCount(); i++){
                        ((RadioButton)rGroup3.getChildAt(i)).setEnabled(checked);
                        txt3.setTextColor(Color.BLACK);
                    }

                for(int i = 0; i < rGroup2.getChildCount(); i++){
                    ((RadioButton)rGroup2.getChildAt(i)).setEnabled(false);
                    txt2.setTextColor(Color.LTGRAY);
                    rbtn6a1.setChecked(false);
                    rbtn6a2.setChecked(false);
                    rbtn6a3.setChecked(false);
                    rbtn6a9.setChecked(false);

                }
                rGroup3.setVisibility(View.VISIBLE);
                break;
            case R.id.q1106b_Other:
                if(checked)
                    q1106btxtOther.setVisibility(View.VISIBLE);
                q1106btxtOther.setText("");

                break;
            case R.id.q1106b_1:
                if(checked)
                    q1106btxtOther.setVisibility(View.INVISIBLE);
                q1106btxtOther.setText("");

                break;
            case R.id.q1106b_2:
                if(checked)
                    q1106btxtOther.setVisibility(View.INVISIBLE);
                q1106btxtOther.setText("");

                break;
            case R.id.q1106b_3:
                if(checked)
                    q1106btxtOther.setVisibility(View.INVISIBLE);
                q1106btxtOther.setText("");

                break;
            case R.id.q1106b_4:
                if(checked)
                    q1106btxtOther.setVisibility(View.INVISIBLE);
                q1106btxtOther.setText("");

                break;
        }
    }


}
