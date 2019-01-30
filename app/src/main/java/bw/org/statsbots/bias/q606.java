package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class q606 extends AppCompatActivity implements Serializable{

    protected HouseHold thisHouse;
    protected PersonRoster p1=null;
    protected String currentHH=null;
    protected Individual indv;
    protected LibraryClass lib;
    protected Individual individual;
    protected RadioButton rbtn1,rbtn2,rbtn9,  selected;
    protected RadioGroup rbtngroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q606);
        setTitle("q606 HIV and TB FACTS AND MYTHS");
        lib = new LibraryClass();
        rbtn1 =  (RadioButton)findViewById(R.id.q606_1);
        rbtn2 =  (RadioButton)findViewById(R.id.q606_2);
        rbtn9 =  (RadioButton)findViewById(R.id.q606_9);
        rbtngroup = (RadioGroup)findViewById(R.id.q606radioGroup) ;



        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;
/*
        if (individual.getQ601().equals("2") ) {

            Intent intent = new Intent(q606.this, q615.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }
*/

        if ( Integer.valueOf(individual.getQ102()) >= 25) {

            Intent intent = new Intent(q606.this, q611.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }
        /**
         * NEXT question
         */
        Button btnNext = (Button)findViewById(R.id.button);


        /**
         * NEXT and SAVE BUTTON
         */
        // btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                int selectedId = rbtngroup.getCheckedRadioButtonId();
                selected = (RadioButton) findViewById(selectedId);

                if (selected == null) {
                    lib.showError(q606.this, "Q606 Error", "Please select an option for q606");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    individual.setQ606(selected.getText().toString().substring(0,1));

                    Intent intent = new Intent(q606.this, q607.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);

                }
            }


        });







    }





}










