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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.List;

public class H01 extends AppCompatActivity implements Serializable, View.OnClickListener {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton rbtn1, rbtn2,rbtn3, rbtn4,rbtn5, rbtn6,rbtn7, rbtn8,rbtn9, rbtn10, selected = null;
    protected RadioGroup rbtngroup;
    protected RadioButton selectedRbtn;
    PersonRoster p1 = null;
    Individual pp1 = null;
    protected DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h01);


        setTitle("H01: TYPE OF HOUSING UNIT");
        lib = new LibraryClass();
        rbtn1 = (RadioButton) findViewById(R.id.H01_1);
        rbtn2 = (RadioButton) findViewById(R.id.H01_2);
        rbtn3 = (RadioButton) findViewById(R.id.H01_3);
        rbtn4 = (RadioButton) findViewById(R.id.H01_4);
        rbtn5 = (RadioButton) findViewById(R.id.H01_5);
        rbtn6 = (RadioButton) findViewById(R.id.H01_6);
        rbtn7 = (RadioButton) findViewById(R.id.H01_7);
        rbtn8 = (RadioButton) findViewById(R.id.H01_8);
        rbtn9 = (RadioButton) findViewById(R.id.H01_9);
        rbtn10 = (RadioButton) findViewById(R.id.H01_10);

        final RadioGroup rg = (RadioGroup) findViewById(R.id.H01radioGroup);


        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");

       List<HouseHold>  houseList = myDB.getHouseForUpdate(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
       myDB.close();

       if(houseList.size()>0){


           thisHouse=houseList.get(0);

              if(thisHouse.getH13Camels()==null ){}
           else{

               AlertDialog.Builder builder = new AlertDialog.Builder(H01.this);
               builder.setTitle("Confirm");
               builder.setMessage("You have already finished this section, Do you want to continue editing it?");
               builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.cancel();
                   }
               });
               builder.setNegativeButton("No - Got to Started", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Intent q1o2 = new Intent(H01.this, started_household.class);
                       q1o2.putExtra("Household",  thisHouse);
                       startActivity(q1o2);
                   }
               });
               AlertDialog dialog = builder.create();
               dialog.show();



           }


       }




            RadioButton[] bt = new RadioButton[10];

            //CHECK WHICH BUTTON WAS SELECTED
            for(int f=0;f<rg.getChildCount();f++)
            {
                View o = rg.getChildAt(f);
                if (o instanceof RadioButton)
                {
                    bt[f]=((RadioButton)o);
                    if(thisHouse.getH01()!= null &&  !thisHouse.getH01().equals(""))
                    {

                        if(Integer.parseInt(thisHouse.getH01())==f+1)
                        {
                            RadioButton radioButton = bt[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    }
                }
                else
                {
                    Log.d("Lost Here","**********");
                }
            }













        int p = 0;
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


                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(H01.this);
                    builder.setTitle("Housing Unit");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);
                    builder.setMessage("Please select type of housing unit");
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
                    thisHouse.setH01(selectedRbtn.getText().toString().substring(0,2));

                    /**
                     * If current person LineNumber is equal to TotalPersons-1
                     * Proceed to next Question in the roster
                     */
                    // Log.d("Current Person: ", p1.getLineNumber() + "===" + p1.getP01());

                    //Next question q102

                    myDB = new DatabaseHelper(H01.this);
                    myDB.onOpen(myDB.getWritableDatabase());

                    //UPDATE HOUSEHOLD

                    myDB.updateHousehold(myDB.getWritableDatabase(),thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"H01", thisHouse.getH01());
                    myDB.close();


                    Intent q1o2 = new Intent(H01.this, H02.class);
                    q1o2.putExtra("Household",  thisHouse);
                    startActivity(q1o2);

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




