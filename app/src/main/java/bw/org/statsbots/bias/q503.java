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

import java.io.Serializable;

public class q503 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn3, selected = null;
    protected RadioGroup rg;
    protected RadioButton selectedRbtn;
    HouseHold thisHose;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q503);

        setTitle("Q503");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q503_1);
        rbtn2 = (RadioButton) findViewById(R.id.q503_2);
        rbtn3 = (RadioButton) findViewById(R.id.q503_9);
        rg = (RadioGroup) findViewById(R.id.q503radioGroup);

        rbtn1.setOnClickListener(this);
        rbtn2.setOnClickListener(this);
        rbtn3.setOnClickListener(this);


        final int selectedId = rg.getCheckedRadioButtonId();

        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;

        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q503.this);
                    builder.setTitle("Q503 Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Do you intend to get circumcised in the next 12 months");
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


                } else



                    if (rbtn1.isChecked()  || rbtn3.isChecked() ) {
                        // to include ea status code on the check
                        // thisHouse.getIndividual()[p1.getLineNumber()].setQ503(selectedRbtn.getText().toString().substring(0,1));

                        Intent q1o2 = new Intent(q503.this, q601.class);
                        q1o2.putExtra("Household", thisHouse);
                        startActivity(q1o2);
                    } else{
                    //Set q503 for the current individual

                    //thisHouse.getIndividual()[p1.getLineNumber()].setQ503(selectedRbtn.getText().toString().substring(0,1));

                    //Next question q504


                    Intent intent = new Intent(q503.this, q504.class);
                    intent.putExtra("Household",  thisHouse);
                    startActivity(intent);

                }

            }
        });
    }


    @Override
    public void onClick(View view) {


//
        switch (view.getId()) {

            case R.id.q503_1:

                break;

            case R.id.q503_2:

                break;

            case R.id.q503_9:

                break;

            default:
                break;

        }

    }

}