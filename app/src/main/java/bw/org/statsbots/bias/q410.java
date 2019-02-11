package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class q410 extends AppCompatActivity {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtn8, rbtn9, rbtn10, rbtn11, rbtn12, rbtn13, rbtn14;
    protected RadioGroup  rg1, rg2, rg3, rg4, rg5, rg6, rg7;
    protected RadioButton selected1, selected2, selected3, selected4, selected5, selected6, selected7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q410);


        setTitle("Q410: SEXUAL HISTORY");
        lib = new LibraryClass();

        rg1 = (RadioGroup) findViewById(R.id.rg1) ;
        rg2 = (RadioGroup) findViewById(R.id.rg2) ;
        rg3 = (RadioGroup) findViewById(R.id.rg3) ;
        rg4 = (RadioGroup) findViewById(R.id.rg4) ;
        rg5 = (RadioGroup) findViewById(R.id.rg5) ;
        rg6 = (RadioGroup) findViewById(R.id.rg6) ;
        rg7 = (RadioGroup) findViewById(R.id.rg7) ;


        rbtn1 = (RadioButton) findViewById(R.id.rg1_01) ;
        rbtn2 = (RadioButton) findViewById(R.id.rg1_02) ;


        rbtn3 = (RadioButton) findViewById(R.id.rg2_1) ;
        rbtn4 = (RadioButton) findViewById(R.id.rg2_2) ;

        rbtn5 = (RadioButton) findViewById(R.id.rg3_1) ;
        rbtn6 = (RadioButton) findViewById(R.id.rg3_2) ;


        rbtn7 = (RadioButton) findViewById(R.id.rg4_1) ;
        rbtn8 = (RadioButton) findViewById(R.id.rg4_2) ;


        rbtn9 = (RadioButton) findViewById(R.id.rg5_1) ;
        rbtn10 = (RadioButton) findViewById(R.id.rg5_2) ;


        rbtn11 = (RadioButton) findViewById(R.id.rg6_1) ;
        rbtn12 = (RadioButton) findViewById(R.id.rg6_2) ;


        rbtn13 = (RadioButton) findViewById(R.id.rg7_1) ;
        rbtn14 = (RadioButton) findViewById(R.id.rg7_2) ;


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;



        if (individual.getQ101().equals("1"))
        {

            Intent intent = new Intent(q410.this, q501.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }

        if (individual.getQ101().equals("2") && ((individual.getQ201().equals("1")  && individual.getQ202().equals("2")) || individual.getQ201().equals("6")) )
        {

            Intent intent = new Intent(q410.this, q601.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }

        if (individual.getQ101().equals("2") && (individual.getQ201().equals("2") || individual.getQ201().equals("3") || individual.getQ201().equals("4") || individual.getQ201().equals("5") )
                && Integer.valueOf(individual.getQ102()) >= 50)
        {

            Intent intent = new Intent(q410.this, q601.class);
            intent.putExtra("Individual", individual);
            startActivity(intent);

        } else {
            //do nothing
        }





        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg1.getCheckedRadioButtonId();
                selected1 = (RadioButton) findViewById(selectedId);

                if (selected1 == null) {
                    lib.showError(q410.this, "Q410: ERROR", "Slapped or threw something that could hurt you");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    if(selected1 == rbtn1){
                        Log.d("Option 1", "1");
                        individual.setQ410Slapped("1");
                    }else{
                        Log.d("Option 1", "2");
                        individual.setQ410Slapped("2");
                    }
                }



                int selectedId2 = rg2.getCheckedRadioButtonId();
                selected2 = findViewById(selectedId2);
                if(selected2==null){
                    lib.showError(q410.this, "Q410: ERROR", "Slapped or threw something that could hurt you");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }else{
                    selected2 = (RadioButton) findViewById(selectedId2);
                    if(selected2 == rbtn3){
                        Log.d("Option 2", "1");
                        individual.setQ410Pushed("1");
                    }else{
                        Log.d("Option 2", "2");
                        individual.setQ410Pushed("2");
                    }

                }

                int selectedId3 = rg3.getCheckedRadioButtonId();
                selected3 = (RadioButton) findViewById(selectedId3);

                if (selected3 == null) {
                    lib.showError(q410.this, "Q410: ERROR", "Chocked or burned you");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    selected3 = (RadioButton) findViewById(selectedId3);
                    if(selected3 == rbtn5){
                        Log.d("Option 3", "1");
                        individual.setQ410Choked("1");
                    }else{
                        Log.d("Option 3", "2");
                        individual.setQ410Choked("2");
                    }

                }


                int selectedId4 = rg4.getCheckedRadioButtonId();
                selected4 = (RadioButton) findViewById(selectedId4);
                if (selected4 == null) {
                    lib.showError(q410.this, "Q410: ERROR", "Chocked or burned you");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    selected4 = (RadioButton) findViewById(selectedId4);
                    if(selected4 == rbtn7){
                        Log.d("Option 4", "1");
                        individual.setQ410Threatened("1");
                    }else{
                        Log.d("Option 4", "2");
                        individual.setQ410Threatened("2");
                    }

                }


                int selectedId5 = rg5.getCheckedRadioButtonId();
                selected5 = (RadioButton) findViewById(selectedId5);
                if (selected5 == null) {
                    lib.showError(q410.this, "Q410: ERROR", "Chocked or burned you");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    selected5 = (RadioButton) findViewById(selectedId5);
                    if(selected5 == rbtn9){
                        Log.d("Option 5", "1");
                        individual.setQ410Physical("1");
                    }else{
                        Log.d("Option 5", "2");
                        individual.setQ410Physical("2");
                    }

                }


                int selectedId6 = rg6.getCheckedRadioButtonId();
                selected6 = (RadioButton) findViewById(selectedId6);
                if (selected6 == null) {
                    lib.showError(q410.this, "Q410: ERROR", "Chocked or burned you");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    selected6 = (RadioButton) findViewById(selectedId6);
                    if(selected6 == rbtn11){
                        Log.d("Option 6", "1");
                        individual.setQ410Forced("1");
                    }else{
                        Log.d("Option 6", "2");
                        individual.setQ410Forced("2");
                    }

                }

                int selectedId7 = rg7.getCheckedRadioButtonId();
                selected7 = (RadioButton) findViewById(selectedId7);
                if (selected7 == null) {
                    lib.showError(q410.this, "Q410: ERROR", "Chocked or burned you");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {
                    selected7 = (RadioButton) findViewById(selectedId7);
                    if(selected7 == rbtn13){
                        Log.d("Option 7", "1");
                        individual.setQ410MadeAfraid("1");
                    }else{
                        Log.d("Option 7", "2");
                        individual.setQ410MadeAfraid("2");
                    }

                }


                Intent intent = new Intent(q410.this, q501.class);
                intent.putExtra("Individual", individual);
                startActivity(intent);




            }



        });
    }




}
