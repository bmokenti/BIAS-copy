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

import java.io.Serializable;

public class q605 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt, ck3txt, ck4txt, ck5txt, ck9txt, chkOther, selected = null;
    protected Button btn;
    protected EditText q605edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q605);


        setTitle("Q605: KNOWLEDGE ABOUT HIV/AIDS AND TB");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);

        ck1txt = findViewById(R.id.q605_1);
        ck2txt = findViewById(R.id.q605_2);
        ck3txt = findViewById(R.id.q605_3);
        ck4txt = findViewById(R.id.q605_4);
        ck5txt = findViewById(R.id.q605_5);
        ck9txt = findViewById(R.id.q605_9);
        chkOther = findViewById(R.id.q605_other);
        q605edt = findViewById(R.id.q605edt_Other);


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((((!ck1txt.isChecked() && !ck2txt.isChecked() && !ck3txt.isChecked() && !ck4txt.isChecked() && !ck5txt.isChecked() && !ck9txt.isChecked() && !chkOther.isChecked())))) {
                    lib.showError(q605.this, "Q605:", "How can people prevent becoming infected with TB?"
                            + "Please select atleast one checkbox");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else if ((((chkOther.isChecked() && q605edt.length() == 0)))) {
                    lib.showError(q605.this, "Q605:", "Please specify other or uncheck Other specify");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    //thisHouse.getIndividual()[p1.getLineNumber()].setQ605_1(ck1txt.getText().toString().substring(0,1));
                    // thisHouse.getIndividual()[p1.getLineNumber()].setQ605_2(ck2txt.getText().toString().substring(0,1));
                    // thisHouse.getIndividual()[p1.getLineNumber()].setQ605_3(ck3txt.getText().toString().substring(0,1));
                    //  thisHouse.getIndividual()[p1.getLineNumber()].setQ605_4(ck4txt.getText().toString().substring(0,1));
                    //  thisHouse.getIndividual()[p1.getLineNumber()].setQ605_5(ck5txt.getText().toString().substring(0,1));
                     //thisHouse.getIndividual()[p1.getLineNumber()].setQ605_5(ck9txt.getText().toString().substring(0,1));

                    //  thisHouse.getIndividual()[p1.getLineNumber()].setQ605_Other(chkOther.getText().toString().substring(0,1));
                    // thisHouse.getIndividual()[p1.getLineNumber()].setQ605_Otherspecify(q605edt.getText().toString());

                    Intent intent = new Intent(q605.this, q606.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);
                }

            }

        });
    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.q605_1:
                if (checked)
                    // Remove the meat
                    break;

            case R.id.q605_2:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q605_3:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q605_4:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q605_5:
                if (checked)
                    // Remove the meat
                    break;

            case R.id.q605_9:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q605_other:
                if (checked) {
                    if (chkOther.isChecked())
                        q605edt.setVisibility(View.VISIBLE);


                    // Put some meat on the sandwich
                    else
                        // Remove the meat
                        q605edt.setVisibility(View.INVISIBLE);
                    q605edt.setText("");

                    break;


                }
        }
    }
}
