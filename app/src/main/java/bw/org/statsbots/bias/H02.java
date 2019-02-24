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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class H02 extends AppCompatActivity implements Serializable  {

    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected EditText edt,norooms;
    protected RadioGroup rbtngroup;
    protected RadioButton selectedRbtn;
    PersonRoster p1 = null;
    Individual pp1 = null;
    protected DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h02);

        setTitle("H02: NUMBER OF ROOMS");
        lib = new LibraryClass();


        final RadioGroup rg = (RadioGroup) findViewById(R.id.H01radioGroup);

        //rbtn1.setOnClickListener(this);
        //rbtn2.setOnClickListener(this);

        // final int selectedId = rbtngroup.getCheckedRadioButtonId();

        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;


        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());


        List<HouseHold> houseList = myDB.getHouseForUpdate(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
        myDB.close();

        if(houseList.size()>0){
            thisHouse=houseList.get(0);
        }


        edt = (EditText)findViewById(R.id.H02_ROOMS);

        if(thisHouse.getH02() !=null && !thisHouse.getH02().equals("")){
            edt.setText(thisHouse.getH02());
        }






        Button btnext = findViewById(R.id.button);

        Button btnPrev = findViewById(R.id.button3);
//        PersonRoster pr[] = thisHouse.getPersons();

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();



            }
        });

        btnext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {


               int numRooms =0;

                if (edt.length() == 0 || edt.getText() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(H02.this);
                    builder.setTitle("Number of rooms");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);
                    builder.setMessage("Please enter number of rooms");
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


                } else {
                    numRooms = Integer.parseInt(edt.getText().toString());
                    if(numRooms >=0 && numRooms <= 15){
                        //True SAve
                        thisHouse.setH02(String.valueOf(numRooms));


                        DatabaseHelper myDB = new DatabaseHelper(H02.this);
                        myDB.onOpen(myDB.getWritableDatabase());

                        //UPDATE HOUSEHOLD
                        myDB.updateHousehold(myDB.getWritableDatabase(),thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"H02", thisHouse.getH02());
                        myDB.close();


                        Intent q1o2 = new Intent(H02.this, H03.class);
                        q1o2.putExtra("Household",  thisHouse);
                        startActivity(q1o2);

                    }else{
                        lib.showError(H02.this,"H02 Error","Number of rooms expected 0 to 15 only");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }





                }

            }
        });
    }

}




