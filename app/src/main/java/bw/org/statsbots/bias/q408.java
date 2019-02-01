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

public class q408 extends AppCompatActivity implements View.OnClickListener, Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected RadioButton rbtn1,rbtn2, rbtna1,rbtna2;
    protected RadioGroup rg, rga;
    protected RadioButton selectedRbtn, selectedRbtna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q408);


        setTitle("Q408: SEXUAL HISTORY");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q408_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q408_2);

        rbtna1 =  (RadioButton)findViewById(R.id.q408a_1);
        rbtna2 =  (RadioButton)findViewById(R.id.q408a_2);

        rg = (RadioGroup)findViewById(R.id.q408radioGroup) ;
        rga = (RadioGroup)findViewById(R.id.q408radioGroupa) ;

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        if (individual.getQ101().equals("2") && (individual.getQ201().equals("2") || individual.getQ201().equals("3")) && individual.getQ401().equals("1") && Integer.valueOf(individual.getQ102()) >= 50 ) {
            Intent intent = new Intent(q408.this, q601.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }



        if (individual.getQ101().equals("1") && (individual.getQ201().equals("1") || individual.getQ201().equals("4") || individual.getQ201().equals("5") || individual.getQ201().equals("6")) || Integer.valueOf(individual.getQ102()) >= 50 && individual.getQ401().equals("1") ) {
            Intent intent = new Intent(q408.this, q501.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }


        if (individual.getQ101().equals("1") && (individual.getQ201().equals("1") || individual.getQ201().equals("4") || individual.getQ201().equals("5") || individual.getQ201().equals("6")) && Integer.valueOf(individual.getQ102()) < 50 && individual.getQ401().equals("1") ) {
            Intent intent = new Intent(q408.this, q501.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                {
                    int selectedId = rg.getCheckedRadioButtonId();
                    selectedRbtn = (RadioButton) findViewById(selectedId);
                    if (selectedRbtn == null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(q408.this);
                        builder.setTitle("Q408: Error");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("In the past 12 months have you had sex with someone you are not married to or living together?");
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
                        int selectedIda = rga.getCheckedRadioButtonId();
                        selectedRbtna = (RadioButton) findViewById(selectedIda);
                        if (selectedRbtna == null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(q408.this);
                            builder.setTitle("Q408a: Error");
                            builder.setIcon(R.drawable.ic_warning_orange_24dp);

                            builder.setMessage("Did you use a condom the last time you had sex with this partner?");
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
                            //Set q408 for the current individual

                           individual.setQ408(selectedRbtn.getText().toString().substring(0,1));
                           individual.setQ408a(selectedRbtna.getText().toString().substring(0,1));


                            Intent q1o2 = new Intent(q408.this, q410.class);
                            q1o2.putExtra("Individual", individual);
                            startActivity(q1o2);

                        }

                    }
                }
            }
        });
    }


    @Override
    public void onClick(View view) {

        //
        switch (view.getId()) {

            case R.id.q408_1:
                //Intent intent3 = new Intent(this, q501.class);
                //startActivity(intent3);
                break;

            case R.id.q408_2:
                //Intent intent4 = new Intent(this, q501.class);
                // startActivity(intent4);
                break;
            case R.id.q408a_1:
                //Intent intent4 = new Intent(this, q501.class);
                // startActivity(intent4);
                break;
            case R.id.q408a_2:
                //Intent intent4 = new Intent(this, q501.class);
                // startActivity(intent4);
                break;


            default:
                break;

        }
    }
}
