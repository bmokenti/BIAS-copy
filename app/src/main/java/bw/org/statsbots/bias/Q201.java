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

public class Q201 extends AppCompatActivity implements View.OnClickListener  {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2,  rbtn3, rbtn4,  rbtn5, rbtn6,selected = null;
    protected RadioGroup rbtngroup;
    protected RadioButton selectedRbtn;
    PersonRoster p1 = null;
    Individual pp1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q201);



        setTitle("Q201: MARITAL STATUS AND RELATIONSHIP");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q201_1);
        rbtn2 = (RadioButton) findViewById(R.id.q201_2);
        rbtn3 = (RadioButton) findViewById(R.id.q201_3);
        rbtn4 = (RadioButton) findViewById(R.id.q201_4);
        rbtn5 = (RadioButton) findViewById(R.id.q201_5);
        rbtn6 = (RadioButton) findViewById(R.id.q201_6);


        final RadioGroup rg = (RadioGroup) findViewById(R.id.q201radioGroup);



        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;
        Button btnext = findViewById(R.id.btnnext);
//        PersonRoster pr[] = thisHouse.getPersons();



        btnext.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Q201.this);
                    builder.setTitle("Marital Status?");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("What is your current marital status?");
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
                    if (rbtn2.isChecked() || rbtn3.isChecked() || rbtn4.isChecked() || rbtn5.isChecked() || rbtn5.isChecked()) {

                        Intent skipto203 = new Intent(Q201.this, q203.class);
                        skipto203.putExtra("Household", thisHouse);
                        startActivity(skipto203);


                    } else {
                        //Set q101 for the current individual
                        //thisHouse.getIndividual()[p1.getLineNumber()].setQ201(selectedRbtn.getText().toString().substring(0, 1));



                        Intent q1o2 = new Intent(Q201.this, q202.class);
                        q1o2.putExtra("Household", thisHouse);
                        startActivity(q1o2);

                    }

                }
            }
        });
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.q101_1:

                break;

            case R.id.q101_2:
                break;


            default:
                break;

        }
    }

}










