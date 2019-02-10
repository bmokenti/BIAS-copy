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

public class q620 extends AppCompatActivity {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn9, rbtnOther;
    protected RadioGroup rg1;
    protected EditText edtOther;
    protected RadioButton selectedRbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q620);

        setTitle("q620: KNOWLEDGE ABOUT HIV/AIDS AND TB ");
        lib = new LibraryClass();

            rg1 = (RadioGroup) findViewById(R.id.q620rGroup) ;

            rbtn1 = (RadioButton) findViewById(R.id.q620_1);
            rbtn2 = (RadioButton) findViewById(R.id.q620_2);
            rbtn3 = (RadioButton) findViewById(R.id.q620_3);
            rbtn4 = (RadioButton) findViewById(R.id.q620_4);
            rbtn9 = (RadioButton) findViewById(R.id.q620_9);
            rbtnOther = (RadioButton) findViewById(R.id.q620_other);
            edtOther = (EditText) findViewById(R.id.q620other);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.q620_other)
                {
                    // is checked
                    edtOther.setVisibility(View.VISIBLE);
                }
                else
                {
                    // not checked
                    edtOther.setVisibility(View.INVISIBLE);
                    edtOther.setText("");
                }
            }
        });
                Button btnnext = findViewById(R.id.btnNext);
            btnnext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int selectedId = rg1.getCheckedRadioButtonId();
                    selectedRbtn = (RadioButton) findViewById(selectedId);

                    if (selectedRbtn == null) {
                        lib.showError(q620.this, "Q620: ERROR", "Do you think TB can be treated?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        if (rbtnOther.isChecked() && edtOther.length() == 0) {
                            lib.showError(q620.this, "Q620: ERROR: Other", "Please specify other or deselect other specify");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        } else {

                            individual.setQ620(selectedRbtn.getText().toString().substring(0, 1));
                            individual.setQ620_Other(edtOther.getText().toString());


                            Intent intent = new Intent(q620.this, q621.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);
                        }
                    }
                }
            });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q620.super.onBackPressed();
            }


        });
    }
    }
