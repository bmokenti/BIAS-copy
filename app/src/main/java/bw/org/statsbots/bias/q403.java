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

public class q403 extends AppCompatActivity implements View.OnClickListener, Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected  RadioButton rbtn1,rbtn2,rbtn3, selected=null;
    protected RadioGroup rg;
    protected RadioButton selectedRbtn;
    HouseHold thisHose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q403);

        setTitle("Q403:");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q403_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q403_2);
       // rbtn3 =  (RadioButton)findViewById(R.id.q403_3);

        rg = (RadioGroup)findViewById(R.id.q403radioGroup) ;
        //rbtn1 =  (RadioButton)findViewById(R.id.q403_1);
        //rbtn2 =  (RadioButton)findViewById(R.id.q403_2);

        //rbtn1.setOnClickListener(this);
        //rbtn2.setOnClickListener(this);


        // final int selectedId = rbtngroup.getCheckedRadioButtonId();

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        int p=0;

        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q403.this);
                    builder.setTitle("Q403 Error");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Have \"+ p1.getP01() + \"had sex in the past 12 months");
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
                    //Set q402 for the current individual
                 // thisHouse.getIndividual()[p1.getLineNumber()].setQ403(selectedRbtn.getText().toString().substring(0,1));



                    Intent q1o2 = new Intent(q403.this, q404.class);
                    q1o2.putExtra("Household",  thisHouse);
                    startActivity(q1o2);

                }

            }
        });
    }


    @Override
    public void onClick(View view) {

        //
        switch (view.getId()) {

            case R.id.q403_1:
                //Intent intent3 = new Intent(this, q501.class);
                //startActivity(intent3);
                break;

            case R.id.q403_2:
                //Intent intent4 = new Intent(this, q501.class);
               // startActivity(intent4);
                break;


            default:
                break;

        }
    }
}
