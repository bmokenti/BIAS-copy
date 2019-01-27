package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

public class q1010 extends AppCompatActivity {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtnOther ;
    protected RadioGroup rg, rga;
    protected TextView t1;
    protected EditText edtOther;
    protected RadioButton selectedRbtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1010);


        setTitle("Q1010: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1010radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q1010_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1010_2);
        rbtn3 = (RadioButton) findViewById(R.id.q1010_3);
        rbtnOther = (RadioButton) findViewById(R.id.q1010_other);
        edtOther = (EditText)  findViewById(R.id.q1010_other1);

        //rga = (RadioGroup)findViewById(R.id.q1010aGroup1) ;







        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);

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
                    lib.showError(q1010.this, "Q1010: ERROR", "Where did you give birth to this child?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    if (rbtnOther.isChecked() && edtOther.length() == 0) {
                        lib.showError(q1010.this, "Q1010: ERROR: Other specify", "Other specify");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        //thisHouse.getIndividual()[p1.getLineNumber()].setQ1010(selectedRbtn.getText().toString().substring(0,1));
                        //thisHouse.getIndividual()[p1.getLineNumber()].setQ1010_Other(edtOther.getText().toString());

                        Intent intent = new Intent(q1010.this, q1011.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);


                    }
                }

            }


        });
    }


    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q1010_1:
                if(checked)
                {
                    edtOther.setVisibility(View.INVISIBLE);
                    edtOther.setText("");

                }

                else

                break;

            case R.id.q1010_2:
                if(checked)

                {
                    edtOther.setVisibility(View.INVISIBLE);
                    edtOther.setText("");

                }

                else


                break;

            case R.id.q1010_3:
                if(checked)
                {
                    edtOther.setVisibility(View.INVISIBLE);
                    edtOther.setText("");

                }

            else

                break;

            case R.id.q1010_other:
                if(checked) {

                    edtOther.setVisibility(View.VISIBLE);
                }
                else

                    break;




            default:

                break;

        }

    }


}

