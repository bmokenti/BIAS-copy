package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class q504 extends AppCompatActivity implements Serializable {


    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    private DatabaseHelper myDB;
    protected CheckBox ck1txt, ck2txt, ck3txt, ck4txt, ck5txt, ck6txt, ck7txt, ck8txt, ck10txt,chkOther, selected = null;
    protected Button btn;
    protected EditText Q504edt;

    HouseHold thisHose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q504);

        setTitle("Q504: MALE CIRCUMICISION");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);

        ck1txt = findViewById(R.id.Q504_1);
        ck2txt = findViewById(R.id.Q504_2);
       ck3txt = findViewById(R.id.Q504_3);
        ck4txt = findViewById(R.id.Q504_4);
        ck5txt = findViewById(R.id.Q504_5);
        ck6txt = findViewById(R.id.Q504_6);
        ck7txt = findViewById(R.id.Q504_7);
        ck8txt = findViewById(R.id.Q504_8);
        ck10txt = findViewById(R.id.Q504_10);
        chkOther = findViewById(R.id.Q504_Other);
        Q504edt = findViewById(R.id.Q504edt_Other);

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;


        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((((!ck1txt.isChecked() && !ck2txt.isChecked() && !ck3txt.isChecked() && !ck4txt.isChecked() && !ck5txt.isChecked() && !ck6txt.isChecked() && !ck7txt.isChecked() && !ck8txt.isChecked() && !ck10txt.isChecked() && !chkOther.isChecked())))) {
                    lib.showError(q504.this, "Q504:", "Please select atleast one checkbox");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }  else
                if ((((chkOther.isChecked() && Q504edt.length() ==0 )))) {
                    lib.showError(q504.this, "Q504:", "Please specify other or uncheck Other specify");
                    /**
                     * VIBRATE DEVICE
                     */

                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }  else{


                    if(ck1txt.isChecked()){
                        individual.setQ504_1("1");
                    }else{
                        individual.setQ504_1("2");
                    }


                    if(ck2txt.isChecked()){
                        individual.setQ504_2("1");
                    }else{
                        individual.setQ504_2("2");
                    }

                    if(ck3txt.isChecked()){
                        individual.setQ504_3("1");
                    }else{
                        individual.setQ504_3("2");
                    }

                    if(ck4txt.isChecked()){
                        individual.setQ504_4("1");
                    }else{
                        individual.setQ504_4("2");
                    }

                    if(ck5txt.isChecked()){
                        individual.setQ504_5("1");
                    }else{
                        individual.setQ504_5("2");
                    }
                    if(ck6txt.isChecked()){
                        individual.setQ504_6("1");
                    }else{
                        individual.setQ504_6("2");
                    }
                    if(ck7txt.isChecked()){
                        individual.setQ504_7("1");
                    }else{
                        individual.setQ504_7("2");
                    }

                    if(ck8txt.isChecked()){
                        individual.setQ504_8("1");
                    }else{
                        individual.setQ504_8("2");
                    }

                    if(ck10txt.isChecked()){
                        individual.setQ504_10("1");
                    }else{
                        individual.setQ504_10("2");
                    }


                    if(chkOther.isChecked()){
                        individual.setQ504_Other("1");
                        individual.setQ504_OtherSpecify(Q504edt.getText().toString());
                    }else{
                        individual.setQ504_Other("2");
                    }



                    Intent intent = new Intent(q504.this, q601.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);

                }

            }

        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q504.super.onBackPressed();
            }


        });

    }



    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.Q504_1:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;

            case R.id.Q504_2:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.Q504_3:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.Q504_4:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.Q504_5:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.Q504_6:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.Q504_7:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.Q504_8:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.Q504_10:
                if (checked) {

                }
                // Put some meat on the sandwich
                else
                    // Remove the meat
                    break;
            case R.id.Q504_Other:
                if (checked) {
                    if (chkOther.isChecked()) {
                        Q504edt.setVisibility(View.VISIBLE);
                    }


                    else

                        Q504edt.setVisibility(View.INVISIBLE);
                      Q504edt.setText("");

                    break;


                }
        }
    }
}
/*
Intent intent = new Intent(q504.this, q1101.class);
                //intent.putExtra("Household", thisHose);
                startActivity(intent);
 */


