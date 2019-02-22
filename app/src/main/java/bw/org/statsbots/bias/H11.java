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

public class H11 extends AppCompatActivity implements View.OnClickListener, Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2,rbtn3, rbtn4,rbtn5, rbtn6,rbtn7, selected = null;
    protected RadioGroup rbtngroup;
    protected EditText edt;
    protected RadioButton selectedRbtn;protected DatabaseHelper myDB;
    PersonRoster p1 = null;
    Individual pp1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h11);



        setTitle("H11: REFUSE DISPOSAL");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.H11_1);
        rbtn2 = (RadioButton) findViewById(R.id.H11_2);
        rbtn3 = (RadioButton) findViewById(R.id.H11_3);
        rbtn4 = (RadioButton) findViewById(R.id.H11_4);
        rbtn5 = (RadioButton) findViewById(R.id.H11_Other);
        edt = (EditText) findViewById(R.id.H11_txtOther);
        edt.setVisibility(View.INVISIBLE);

        final RadioGroup rg = (RadioGroup) findViewById(R.id.H11radioGroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.H11_Other)
                {
                    // is checked
                    edt.setVisibility(View.VISIBLE);
                }
                else
                {
                    // not checked
                    edt.setVisibility(View.INVISIBLE);
                    edt.setText("");
                }
            }
        });

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


        RadioButton[] bt = new RadioButton[5];

        //CHECK WHICH BUTTON WAS SELECTED
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(thisHouse.getH11()!= null)
                {

                    if(Integer.parseInt(thisHouse.getH11())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }

                    if(thisHouse.getH11Other()!=null){
                        rbtn5.setChecked(true);
                        edt.setVisibility(View.VISIBLE);
                        edt.setText(thisHouse.getH11Other());
                    }
                }
                else {
                    if(thisHouse.getH11Other() !=null){
                        rbtn5.setChecked(true);
                        edt.setVisibility(View.VISIBLE);
                        edt.setText(thisHouse.getH11Other());
                    }
                }
            }
            else
            {
                Log.d("Lost Here","**********");
            }
        }



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
                    AlertDialog.Builder builder = new AlertDialog.Builder(bw.org.statsbots.bias.H11.this);
                    builder.setTitle("REFUSE DISPOSAL");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);
                    builder.setMessage("How do you dispose off refuse in this household?");
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


                    if(selectedId == R.id.H11_Other)
                    {
                        if(edt.getText().toString().equals(""))
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(bw.org.statsbots.bias.H11.this);
                            builder.setTitle("REFUSE DISPOSAL");
                            builder.setIcon(R.drawable.ic_warning_orange_24dp);
                            builder.setMessage("How do you dispose off refuse in this household?");
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
                            //Set q101 for the current individual
                            thisHouse.setH11Other(edt.getText().toString());

                            DatabaseHelper myDB = new DatabaseHelper(H11.this);
                            myDB.onOpen(myDB.getWritableDatabase());

                            //UPDATE HOUSEHOLD
                            myDB.updateHousehold(myDB.getWritableDatabase(),thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"H11Other", thisHouse.getH11Other());
                            myDB.updateHousehold(myDB.getWritableDatabase(),thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"H11", null);

                            myDB.close();


                            Intent q1o2 = new Intent(bw.org.statsbots.bias.H11.this, H12.class);
                            q1o2.putExtra("Household",  thisHouse);
                            startActivity(q1o2);
                        }

                    }else{

                        //Set q101 for the current individual
                        thisHouse.setH11(selectedRbtn.getText().toString().substring(0,1));

                        DatabaseHelper myDB = new DatabaseHelper(H11.this);
                        myDB.onOpen(myDB.getWritableDatabase());

                        //UPDATE HOUSEHOLD
                        myDB.updateHousehold(myDB.getWritableDatabase(),thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"H11Other", null);
                        myDB.updateHousehold(myDB.getWritableDatabase(),thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"H11", thisHouse.getH11());

                        myDB.close();
                        Intent q1o2 = new Intent(bw.org.statsbots.bias.H11.this, H12.class);
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

