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

import java.io.Serializable;

public class H04 extends AppCompatActivity implements View.OnClickListener, Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2,rbtn3, rbtn4,rbtn5, rbtn6,rbtn7, rbtn8, selected = null;
    protected RadioGroup rbtngroup;
    protected EditText edt;
    protected RadioButton selectedRbtn;
    PersonRoster p1 = null;
    Individual pp1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h04);


        setTitle("H04: MATERIAL OF CONSTRUCTION");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.H04_1);
        rbtn2 = (RadioButton) findViewById(R.id.H04_2);
        rbtn3 = (RadioButton) findViewById(R.id.H04_3);
        rbtn4 = (RadioButton) findViewById(R.id.H04_4);
        rbtn5 = (RadioButton) findViewById(R.id.H04_5);
        rbtn6 = (RadioButton) findViewById(R.id.H04_6);
        rbtn7 = (RadioButton) findViewById(R.id.H04_7);
        rbtn8 = (RadioButton) findViewById(R.id.H04_other);
        edt = (EditText) findViewById(R.id.H04_txtOther);

        final RadioGroup rg = (RadioGroup) findViewById(R.id.H04radioGroup);

        /*rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.H03_other)
                {
                    // is checked
                    edt.setVisibility(View.VISIBLE);
                }
                else
                {
                    // not checked
                    edt.setVisibility(View.INVISIBLE);
                }
            }
        });*/

        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;
        Button btnext = findViewById(R.id.button);
//        PersonRoster pr[] = thisHouse.getPersons();



        btnext.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    if(edt.getText().toString().equals(""))
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(H04.this);
                        builder.setTitle("MATERIAL OF CONSTRUCTION");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);
                        builder.setMessage("Please select the main material of construction of FLOOR");
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
                        thisHouse.setH04Other(edt.getText().toString());
                        Intent q1o2 = new Intent(H04.this, H05.class);
                        q1o2.putExtra("Household",  thisHouse);
                        startActivity(q1o2);
                    }


                } else {
                    //Set q101 for the current individual
                    thisHouse.setH04(selectedRbtn.getText().toString().substring(0,1));
                    Intent q1o2 = new Intent(H04.this, H05.class);
                    q1o2.putExtra("Household",  thisHouse);
                    startActivity(q1o2);

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




