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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class q1110 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, selected = null;
    protected RadioGroup rbtngroup;
    protected RadioButton selectedRbtn;
    PersonRoster p1 = null;
    Individual pp1 = null;
    protected Individual individual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1110);

        setTitle("Q1110:");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.q1110_y);
        rbtn2 = (RadioButton) findViewById(R.id.q1110_n);

        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);

        //rbtn1.setOnClickListener(this);
        //rbtn2.setOnClickListener(this);

        // final int selectedId = rbtngroup.getCheckedRadioButtonId();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        if (individual.getQ1103().equals("2") && individual.getQ1107().equals("2") && individual.getQ1108().equals("2") && individual.getQ1109().equals("2") )
        {
            Intent intent = new Intent(q1110.this, q1114.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);
        }

        Button btnext = findViewById(R.id.btnNext);


        btnext.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q1110.this);
                    builder.setTitle("Q1110:");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Did you seek medical attention?");
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


                }else {
                   // individual.setQ1110(selectedRbtn.getText().toString().substring(0,1));
                    if (rbtn2.isChecked()) {

                        individual.setQ1110(selectedRbtn.getText().toString().substring(0,1));

                        Intent intent = new Intent(q1110.this, q1113.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);

                    } else {

                        individual.setQ1110(selectedRbtn.getText().toString().substring(0,1));


                        //Next question q102


                        Intent intent = new Intent(q1110.this, q1111.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);

                    }
                }
            }
        });
    }
}


