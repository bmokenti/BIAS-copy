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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class q302 extends AppCompatActivity {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2;
    protected RadioGroup rg ;
    protected TextView txt1;
    PersonRoster p1 = null;
    Individual pp1 = null;
    protected RadioButton selectedRbtn1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q302);



        setTitle("Q302: ALCOHOL CONSUMPTION AND SUBSTANCE USE");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q302_1);
        rbtn2 = (RadioButton) findViewById(R.id.q302_2);



        txt1 = (TextView) findViewById(R.id.q301atxt);
        // txt2 = (TextView) findViewById(R.id.q205atxt);





        rg = (RadioGroup) findViewById(R.id.q302radioGroup);




        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;


        Button btnext = findViewById(R.id.button);
//        PersonRoster pr[] = thisHouse.getPersons();



        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn1 = (RadioButton) findViewById(selectedId);

                if (selectedRbtn1 == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q302.this);
                    builder.setTitle("Q302: Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Have you ever used drugs for recreational purposes?");
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
                {
                    if(rbtn2.isChecked()){
                        //Set q302 for the current individual
                       individual.setQ302(selectedRbtn1.getText().toString().substring(0,1));



                        Intent q1o2 = new Intent(q302.this, q305.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);

                    }

                    else {
                        //Set q302 for the current individual
                    individual.setQ302(selectedRbtn1.getText().toString().substring(0,1));



                        Intent q1o2 = new Intent(q302.this, q303.class);
                        q1o2.putExtra("Individual", individual);
                        startActivity(q1o2);

                    }
                }

                }


        });
    }

    public void onRadioButtonClicked(View v) {


        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q302_1:
                if (checked) {


                    //rbtna1.setChecked(true);
                    //rbtna2.setChecked(true);



                    // viewa.setVisibility(View.VISIBLE);




                    //rb99.setVisibility(View.VISIBLE);
                }


                break;

            case R.id.q302_2:
                if (checked) {


                }
                break;










        }
    }




}
