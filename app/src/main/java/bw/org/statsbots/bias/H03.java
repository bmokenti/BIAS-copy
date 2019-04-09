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

public class H03 extends AppCompatActivity implements View.OnClickListener, Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2,rbtn3, rbtn4,rbtn5, rbtn6,rbtn7, rbtn8,rbtn9, rbtn10, selected = null;
    protected RadioGroup rbtngroup;
    protected EditText edt;
    protected RadioButton selectedRbtn;
    PersonRoster p1 = null;
    Individual pp1 = null;protected DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h03);



        setTitle("H03: MATERIAL OF CONSTRUCTION");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.H03_1);
        rbtn2 = (RadioButton) findViewById(R.id.H03_2);
        rbtn3 = (RadioButton) findViewById(R.id.H03_3);
        rbtn4 = (RadioButton) findViewById(R.id.H03_4);
        rbtn5 = (RadioButton) findViewById(R.id.H03_5);
        rbtn6 = (RadioButton) findViewById(R.id.H03_6);
        rbtn7 = (RadioButton) findViewById(R.id.H03_7);
        rbtn8 = (RadioButton) findViewById(R.id.H03_8);
        rbtn9 = (RadioButton) findViewById(R.id.H03_other);
        edt = (EditText) findViewById(R.id.H03_txtOther);
        edt.setVisibility(View.INVISIBLE);

        final RadioGroup rg = (RadioGroup) findViewById(R.id.H03radioGroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.H03_other)
                {
                    // is checked
                    edt.setVisibility(View.VISIBLE);
                }
                else
                {
                    // not checked
                    edt.setVisibility(View.INVISIBLE);
                }
            }
        });

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();


        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");

        List<HouseHold> houseList = myDB.getHouseForUpdate(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
        myDB.close();

        if(houseList.size()>0){
            thisHouse=houseList.get(0);
        }




        //CHECK WHICH BUTTON WAS SELECTED
        if(  thisHouse.getH03Other()!= null )
        {
            if (thisHouse.getH03() != null && thisHouse.getH03().equals("Ot") )
            {
                rbtn9.setChecked(true);
                edt.setText(thisHouse.getH03Other());
            }
        }
        else
        {
            RadioButton[] bt = new RadioButton[9];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);


                if( (thisHouse.getH03()!= null ) ) {
                    if(!thisHouse.getH03().equals("")) {
                        if (Integer.parseInt(thisHouse.getH03()) == f + 1) {
                            RadioButton radioButton = bt[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }

                }
            }
//            else
//            {
//                Log.d("Lost Here","**********");
//            }
        }






        int p = 0;
        Button btnext = findViewById(R.id.button);
//        PersonRoster pr[] = thisHouse.getPersons();

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


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(H03.this);
                    builder.setTitle("MATERIAL OF CONSTRUCTION");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);
                    builder.setMessage("Please select the main material of construction of WALL");
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
                    //Set q101 for the current individual
                    if(selectedId==R.id.H03_other){
                        if(edt.getText().toString().equals("")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(H03.this);
                            builder.setTitle("MATERIAL OF CONSTRUCTION");
                            builder.setIcon(R.drawable.ic_warning_orange_24dp);
                            builder.setMessage("Please select the main material of construction of WALL");
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
                        }else{
                            thisHouse.setH03Other(edt.getText().toString());
                            thisHouse.setH03(selectedRbtn.getText().toString().substring(0,2));

                            DatabaseHelper myDB = new DatabaseHelper(H03.this);
                            myDB.onOpen(myDB.getWritableDatabase());

                            //UPDATE HOUSEHOLD
                            myDB.updateHousehold(myDB.getWritableDatabase(),thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"H03", thisHouse.getH03());
                            myDB.updateHousehold(myDB.getWritableDatabase(),thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"H03Other", thisHouse.getH03Other());
                            myDB.close();


                            Intent q1o2 = new Intent(H03.this, H04.class);
                            q1o2.putExtra("Household",  thisHouse);
                            startActivity(q1o2);
                        }
                    }else{
                        thisHouse.setH03(selectedRbtn.getText().toString().substring(0,2));

                        DatabaseHelper myDB = new DatabaseHelper(H03.this);
                        myDB.onOpen(myDB.getWritableDatabase());

                        //UPDATE HOUSEHOLD
                        myDB.updateHousehold(myDB.getWritableDatabase(),thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"H03", thisHouse.getH03());
                        myDB.updateHousehold(myDB.getWritableDatabase(),thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"H03Other", null);
                        myDB.close();

                        Intent q1o2 = new Intent(H03.this, H04.class);
                        q1o2.putExtra("Household",  thisHouse);
                        startActivity(q1o2);
                    }


                }

            }
        });
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.q101_1:

                break;

            case R.id.q101_2:
                break;


            default:
                break;

        }
    }

}




