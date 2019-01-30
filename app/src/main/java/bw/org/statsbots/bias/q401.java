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

public class q401 extends AppCompatActivity implements View.OnClickListener {

    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected Individual individual;
    protected String currentHH=null;
    protected LibraryClass lib;
    protected  RadioButton rbtn1,rbtn2,rbtn3, selected=null;
    protected RadioGroup rbtngroup;
    protected RadioButton selectedRbtn;
    protected q102 age;

    Individual pp1 = null;
    protected q101 sex;
    HouseHold thisHose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q401);

        setTitle("Q401 ");
       // lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q401_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q401_2);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.q401radioGroup);

        //rbtngroup = (RadioGroup)findViewById(R.id.q401radioGroup) ;

        //rbtn1.setOnClickListener(this);
        //rbtn2.setOnClickListener(this);


        //final int selectedId = rbtngroup.getCheckedRadioButtonId();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;



        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q401.this);
                    builder.setTitle("Seaxual intercourse");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Have \"+ p1.getP01() + \"  ever had sexual intercourse?"
                            + "i.e. vaginal sex, oral sex, anal sex: consented or non-consented");
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


                } else{
                                //Set q401 for the current individual
                               individual.setQ401(selectedRbtn.getText().toString().substring(0,1));

                                /**
                                 * If current person LineNumber is equal to TotalPersons-1
                                 * Proceed to next Question in the roster
                                 */
                                // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());

                                //Next question q102


                                Intent q1o2 = new Intent(q401.this, q402.class);
                                q1o2.putExtra("Individual", individual);
                                startActivity(q1o2);

                            }

                        }



        });
    }


    @Override
    public void onClick(View view) {


        //
        switch (view.getId()) {

            case R.id.q401_1:
                //Intent intent3 = new Intent(this, q402.class);
               //startActivity(intent3);
                break;

            case R.id.q401_2:
               // Intent intent4 = new Intent(this, q402.class);
                //startActivity(intent4);
                break;


            default:
                break;


        }
    }
}
