package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class q1017 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected RadioButton rbtn1, rbtn2, rbtna1, rbtna2, rbtna3, rbtna4, rbtna5 ;
    protected RadioGroup rg, rga;
    protected TextView t1;
    protected Individual individual;
    protected EditText edtOther;
    protected RadioButton selectedRbtn, selectedRbtna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1017);



        setTitle("Q1017: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1017radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q1017_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1017_2);







        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);

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
                    lib.showError(q1017.this, "Q1017: ERROR", "Have you ever been screened for cervical cancer in the past 12 months?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }  else {

                    individual.setQ1017(selectedRbtn.getText().toString().substring(0,1));

                    Intent intent = new Intent(q1017.this, q1101.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);



                }
            }



        });
    }


    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q1017_1:
                if(checked)





                    break;

            case R.id.q1017_2:
                if(checked)




                    break;



            default:

                break;

        }

    }


}





/*
Intent intent = new Intent(q504.this, q1101.class);
                //intent.putExtra("Household", thisHose);
                startActivity(intent);
 */


