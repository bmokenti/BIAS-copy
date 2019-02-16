package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class q1103 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected RadioButton rbtnY,rbtnN,  selectedRbtn;
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
        int p = 0;
        
if(individual.getQ1101().equals("1") || individual.getQ1101().equals("2"))
{
    Intent q1o3 = new Intent(q1103.this, q1114.class);
    q1o3.putExtra("Individual", individual);
    startActivity(q1o3);

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


                        if (chkb99.isEnabled() && !chkb99.isChecked() && (q1103dd.length() == 0 && q1103wks.length() == 0 && rbtnY.isChecked())) {
                            lib.showError(q1103.this, "Q1103", "a) How long have you had this cough? Please select Dont know or input days or weeks");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {


                            if (rbtnN.isChecked()) {
                                individual.setQ1103(selectedRbtn.getText().toString().substring(0,1));

                                Intent q1o3i = new Intent(q1103.this, q1107.class);
                                q1o3i.putExtra("Individual", individual);
                                startActivity(q1o3i);

                            } else {

                                individual.setQ1103(selectedRbtn.getText().toString().substring(0,1));

                                if(q1103dd.getText().toString().length()==0){
                                    individual.setQ1103aDD("00");
                                }else if(q1103dd.getText().toString().length()==1){
                                    individual.setQ1103aDD("0"+q1103dd.getText().toString());
                                }else{
                                    individual.setQ1103aDD(q1103dd.getText().toString());
                                }


                                if(q1103wks.getText().toString().length()==0){
                                    individual.setQ1103aWks("00");
                                }else if(q1103wks.getText().toString().length()==1){
                                    individual.setQ1103aWks("0"+q1103wks.getText().toString());
                                }else{
                                    individual.setQ1103aWks(q1103wks.getText().toString());
                                }



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
                q1103.super.onBackPressed();
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
            q1103dd.setText("");
            q1103wks.setText("");
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
}

/*
Intent intent = new Intent(q1103.this, q1104.class );
                intent.putExtra("Household", thisHouse);
                startActivity(intent);
 */