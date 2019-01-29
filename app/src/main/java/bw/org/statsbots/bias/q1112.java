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

public class q1112 extends AppCompatActivity implements View.OnClickListener, Serializable {
    protected HouseHold thisHouse;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, selected = null;
    protected  RadioButton selectedRbtn;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected EditText text1112other;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1112);
        setTitle("Q1112 ");
        Button btnnext = findViewById(R.id.btnNext);
        rbtn1 = (RadioButton) findViewById(R.id.q1112_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1112_2);
        rbtn3 = (RadioButton) findViewById(R.id.q1112_3);
        rbtn4 = (RadioButton) findViewById(R.id.q1112_4);
        rbtn5 = (RadioButton) findViewById(R.id.q1112_ot);
        text1112other= findViewById(R.id.txt1112other);


        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);

        //rbtn1.setOnClickListener(this);
        //rbtn2.setOnClickListener(this);

        // final int selectedId = rbtngroup.getCheckedRadioButtonId();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;




        btnnext.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q1112.this);
                    builder.setTitle("Q1112");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("if you consulted what happened?");
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

                 individual.setQ1112(selectedRbtn.getText().toString().substring(0,1));
                    individual.setQ1112_Other(text1112other.getText().toString());



                    Intent intent = new Intent(q1112.this, q1114.class);
                    intent.putExtra("Individual",  individual);
                    startActivity(intent);

                }

            }
        });
    }

    public void onRadioButtonClicked(View v) {


        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.q1112_ot:
                if (checked) {
                    text1112other.setVisibility(View.VISIBLE);
                  //  individual.setQ1112_Other(text1112other.getText().toString());
                }


                break;

            case R.id.q1112_1:
                if (checked) {
                    text1112other.setText("");
                    text1112other.setVisibility(View.INVISIBLE);

                }

            case R.id.q1112_2:
                if (checked) {
                    text1112other.setText("");
                    text1112other.setVisibility(View.INVISIBLE);

                }

            case R.id.q1112_3:
                if (checked) {
                    text1112other.setText("");
                    text1112other.setVisibility(View.INVISIBLE);

                }

            case R.id.q1112_4:
                if (checked) {
                    text1112other.setText("");
                    text1112other.setVisibility(View.INVISIBLE);

                }




                //txtdays.setVisibility(View.INVISIBLE);
                // txtweeks.setVisibility(View.INVISIBLE);


        }
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.q1112_1:

                break;

            case R.id.q1112_2:
                break;


            case R.id.q1112_3:

                break;

            case R.id.q1112_4:
                break;


            case R.id.txt1112other:
                text1112other.setVisibility(View.VISIBLE);
                break;
            default:
                break;

        }
    }

}