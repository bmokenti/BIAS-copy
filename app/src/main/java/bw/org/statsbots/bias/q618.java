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

public class q618 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn9;
    protected RadioGroup rg1;
    protected RadioButton selectedRbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q618);

        setTitle("q618: KNOWLEDGE ABOUT HIV/AIDS AND TB ");
        lib = new LibraryClass();
        rg1 = (RadioGroup) findViewById(R.id.q618radioGroup) ;

        rbtn1 = (RadioButton) findViewById(R.id.q618_1) ;
        rbtn2 = (RadioButton) findViewById(R.id.q618_2) ;
        rbtn9 = (RadioButton) findViewById(R.id.q618_9) ;

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg1.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q618.this, "Q618: ERROR", "Do you think TB can be treated?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }
                else {
                             individual.setQ618(selectedRbtn.getText().toString().substring(0,1));

                                        Intent intent = new Intent(q618.this, q619.class);
                                        intent.putExtra("Individual", individual);
                                        startActivity(intent);
                                    }
                                }

        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q618.super.onBackPressed();
            }


        });
    }
}
