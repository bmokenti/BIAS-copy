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

public class q103 extends AppCompatActivity implements View.OnClickListener{


    protected LibraryClass lib;
    protected  RadioButton rbtn1,rbtn2,rbtn3, selected=null;
    protected RadioGroup rbtngroup;
    protected HouseHold thisHouse;
    protected Individual individual;
    protected RadioButton selectedRbtn;
    PersonRoster p1 = null;
    Individual pp1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q103);
        setTitle("Q103: EDUCATION");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q103_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q103_2);
        rbtn3 =  (RadioButton)findViewById(R.id.q103_3);
        final RadioGroup rg = (RadioGroup)findViewById(R.id.q103radioGroup);



//        final int selectedId = rbtngroup.getCheckedRadioButtonId();

       // Intent i = getIntent();
       // thisHouse = (HouseHold)i.getSerializableExtra("Household");
        int p=0;
        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q103.this);
                    builder.setTitle("Q103: Attended school?");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Have \"+ p1.getP01() + \"' ever attended school?");
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

                    individual.setQ103(selectedRbtn.getText().toString().substring(0, 1));
                    if (individual.getQ103().equals("3")) {
                        Intent q1o3 = new Intent(q103.this, q105.class);
                        q1o3.putExtra("Household", thisHouse);
                        startActivity(q1o3);

                    } else {
                        //Set q101 for the current individual
                        //thisHouse.getIndividual()[p1.getLineNumber()].setQ103(selectedRbtn.getText().toString().substring(0,1));


                        /**
                         * If current person LineNumber is equal to TotalPersons-1
                         * Proceed to next Question in the roster
                         */
                        // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());
                        // selectedRbtn.getText().toString().substring(0, 1);
                        Intent q1o3 = new Intent(q103.this, q104.class);
                        q1o3.putExtra("Household", thisHouse);
                        startActivity(q1o3);
                        //setting q103 value
                    }
                }


            }
        });
        }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.q106_1:
                selected = (RadioButton)findViewById(R.id.q103_1);

                //startActivity(intent3);
                break;

            case R.id.q106_2:
                selected = (RadioButton)findViewById(R.id.q103_2);
               // startActivity(intent4);
                break;


            default:
                break;

        }
    }
}

