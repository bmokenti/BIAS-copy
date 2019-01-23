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

public class q1101 extends AppCompatActivity implements View.OnClickListener, Serializable {


    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected RadioButton rbtn1,rbtn2,rbtn3, rbtn4, rbtna1, rbtna2, rbtna3 ,rbtna4 , rbtna5, selectedRbtn, selectedRbtn1;
    //protected RadioGroup rg, rg1;
    TextView q1101atext;
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
        //q1101aOther = findViewById(R.id.q1101)

        //rbtn1.setOnClickListener(this);
        //rbtn2.setOnClickListener(this);
        // rbtn3.setOnClickListener(this);
        // rbtn4.setOnClickListener(this);

        // final int selectedId = rbtngroup.getCheckedRadioButtonId();


        // setTitle("Q1101a Where are you getting your TB treatment");

        //lib = new LibraryClass();

        //q1101atext.setVisibility(View.INVISIBLE);


        //rg1.setVisibility(View.INVISIBLE);

        rbtna1 = (RadioButton) findViewById(R.id.q1101a_1);
        rbtna2 = (RadioButton) findViewById(R.id.q1101a_2);
        rbtna3 = (RadioButton) findViewById(R.id.q1101a_3);
        rbtna4 = (RadioButton) findViewById(R.id.q1101a_4);
        rbtna5 = (RadioButton) findViewById(R.id.q1101a_5);

        // rbtn1.setOnClickListener(this);
        //rbtn2.setOnClickListener(this);
        // rbtn3.setOnClickListener(this);
        // rbtn4.setOnClickListener(this);

        // final int selectedId = rbtngroup.getCheckedRadioButtonId();


        //Intent i = getIntent();
        //thisHouse = (HouseHold)i.getSerializableExtra("Household");
        // int p=0;
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

                    if ((selectedRbtn1 == null) && ((rbtn1.isChecked() || rbtn2.isChecked()))) {
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
                        //Set q101 for the current individual
                        // thisHouse.getIndividual()[p1.getLineNumber()].setQ103(selectedRbtn.getText().toString().substring(0,1));

                       // individual.setQ1101a(selectedRbtn1.getText().toString().substring(0,1));
                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());
                        // selectedRbtn.getText().toString().substring(0, 1);
                        Intent q1o3 = new Intent(q1101.this, q1102.class);
                        q1o3.putExtra("Household", thisHouse);
                        startActivity(q1o3);
                        //setting q103 value
                    }


                }
            }
        });
    }






    public void onRadioButtonClicked(View v) {
        TextView q1101atext = findViewById(R.id.q1101atxt);
        RadioGroup rg1 = (RadioGroup) findViewById(R.id.q1101radioGroupa);
        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q1101_1:
                if (checked)
                    for (int i = 0; i < rg1.getChildCount(); i++) {
                        ((RadioButton) rg1.getChildAt(i)).setEnabled(checked);
                        q1101atext.setTextColor(Color.BLACK);

                    }
                    break;


            case R.id.q1101_2:
                if (checked)
                    for (int i = 0; i < rg1.getChildCount(); i++) {
                        ((RadioButton) rg1.getChildAt(i)).setEnabled(checked);
                        q1101atext.setTextColor(Color.BLACK);
                    }

                break;
            case R.id.q1101_3:
                if (checked)
                    for (int i = 0; i < rg1.getChildCount(); i++) {
                        ((RadioButton) rg1.getChildAt(i)).setEnabled(false);
                        rbtna1.setChecked(false);
                        rbtna2.setChecked(false);
                        rbtna3.setChecked(false);
                        rbtna4.setChecked(false);
                        rbtna5.setChecked(false);
                        q1101atext.setTextColor(Color.LTGRAY);
                    }
                break;
            case R.id.q1101_4:
                if (checked) {
                    for (int i = 0; i < rg1.getChildCount(); i++) {
                        ((RadioButton) rg1.getChildAt(i)).setEnabled(false);
                        rbtna1.setChecked(false);
                        rbtna2.setChecked(false);
                        rbtna3.setChecked(false);
                        rbtna4.setChecked(false);
                        rbtna5.setChecked(false);
                        q1101atext.setTextColor(Color.LTGRAY);
                    }
                    break;
                }
        }
    }


    @Override
    public void onClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId())
        {
            case R.id.q1101_1:
                //if(checked)
                   // q1101atext.setVisibility(View.VISIBLE);
                //rg1.setVisibility(View.VISIBLE);
               // rbtna1.setVisibility(View.VISIBLE);


                break;

            case R.id.q1101_2:
               // if(checked)
                   // q1101atext.setVisibility(View.VISIBLE);
               // rg1.setVisibility(View.VISIBLE);
                //rbtna1.setVisibility(View.VISIBLE);
                break;

            case R.id.q1101_3:
              //  Intent intent2 = new Intent(this, q1102.class);
               // startActivity(intent2);
                break;
            case R.id.q1101_4:
                //Intent intent3 = new Intent(this, q1102.class);
                //startActivity(intent3);
                break;

            case R.id.q1101a_1:
              //  Intent intent4 = new Intent(this, q1102.class);
                //startActivity(intent4);

                break;
            case R.id.q1101a_2:
                //Intent intent5 = new Intent(this, q1102.class);
                //startActivity(intent5);
                break;


            case R.id.q1101a_3:
                //Intent intent6 = new Intent(this, q1102.class);
               // startActivity(intent6);
                break;

            case R.id.q1101a_4:
               // Intent intent7 = new Intent(this, q1102.class);
                //startActivity(intent7);
                break;

            case R.id.q1101a_5:
               // Intent intent8 = new Intent(this, q1102.class);
                //startActivity(intent8);
                break;

            default:
                break;
        }




    }
}
