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

public class H13 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected RadioButton r01, r02,t01, t02,tel01, tel02,cell01, cell02,Media01,Media02,Electronic01, Electronic02,Arts01, Arts02 = null;
    protected RadioGroup rbtngroup;
    protected EditText edt;
    private DatabaseHelper myDB;
    protected RadioButton selectedRbtn,selectedRbtn2,selectedRbtn3,selectedRbtn4,selectedRbtn5,selectedRbtn6,selectedRbtn7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h13);



        setTitle("H13: TRANSPORT FACILITIES");
        lib = new LibraryClass();

        r01= (RadioButton) findViewById(R.id.r01);
        r02= (RadioButton) findViewById(R.id.r02);
        t01= (RadioButton) findViewById(R.id.t01);
        t02= (RadioButton) findViewById(R.id.t02);
        tel01= (RadioButton) findViewById(R.id.tel01);
        tel02= (RadioButton) findViewById(R.id.tel02);
        cell01= (RadioButton) findViewById(R.id.cel01);
        cell02= (RadioButton) findViewById(R.id.cel02);
        Media01= (RadioButton) findViewById(R.id.Media01);
        Media02= (RadioButton) findViewById(R.id.Media02);
        Electronic01= (RadioButton) findViewById(R.id.elec01);
        Electronic02= (RadioButton) findViewById(R.id.elec02);
        Arts01= (RadioButton) findViewById(R.id.arts01);
        Arts02= (RadioButton) findViewById(R.id.arts02);

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        final RadioGroup rg = (RadioGroup) findViewById(R.id.H12radioGroup);
        final RadioGroup rg2 = (RadioGroup) findViewById(R.id.rg2);
        final RadioGroup rg3 = (RadioGroup) findViewById(R.id.rg3);
        final RadioGroup rg4 = (RadioGroup) findViewById(R.id.rg4);
        final RadioGroup rg5 = (RadioGroup) findViewById(R.id.rg5);
        final RadioGroup rg6 = (RadioGroup) findViewById(R.id.rg6);
        final RadioGroup rg7 = (RadioGroup) findViewById(R.id.rg7);

        final String[] s1 = new String[1];
        final String[] s2 = new String[1];
        final String[] s3 = new String[1];
        final String[] s4 = new String[1];
        final String[] s5 = new String[1];
        final String[] s6 = new String[1];
        final String[] s7  = new String[1];

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.r01)
                {
                    // is checked
                    s1[0] = "1";
                }
                else if(i == R.id.r02)
                {
                    s1[0] = "2";
                }
            }
        });

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.t01)
                {
                    // is checked
                    s2[0] = "1";
                }
                else if(i == R.id.t02)
                {
                    s2[0] = "2";
                }
            }
        });

        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.tel01)
                {
                    // is checked
                    s3[0] = "1";
                }
                else if(i == R.id.tel02)
                {
                    s3[0] = "2";
                }
            }
        });


        rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.cel01)
                {
                    // is checked
                    s4[0] = "1";
                }
                else if(i == R.id.cel02)
                {
                    s4[0] = "2";
                }
            }
        });


        rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.Media01)
                {
                    // is checked
                    s5[0] = "1";
                }
                else if(i == R.id.Media02)
                {
                    s5[0] = "2";
                }
            }
        });


        rg6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.elec01)
                {
                    // is checked
                    s6[0] = "1";
                }
                else if(i == R.id.elec02)
                {
                    s6[0] = "2";
                }
            }
        });

        rg7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.arts01)
                {
                    // is checked
                    s7[0] = "1";
                }
                else if(i == R.id.arts02)
                {
                    s7[0] = "2";
                }
            }
        });


        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;


        List<HouseHold> houseList = myDB.getHouseForUpdate(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
        myDB.close();

        if(houseList.size()>0){
            thisHouse=houseList.get(0);
        }




        RadioButton[] bt = new RadioButton[2];

        //CHECK WHICH BUTTON WAS SELECTED
        for(int f=0;f<rg.getChildCount();f++)
        {  View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {   bt[f-1]=((RadioButton)o);
                if(thisHouse.getH13()!= null && !thisHouse.getH13().equals(""))
                {
                    if(Integer.parseInt(thisHouse.getH13())==1){
                        r01.setChecked(true);
                        break;

                    }else if(Integer.parseInt(thisHouse.getH13())==2){
                        r02.setChecked(true);
                        break;
                    }

                    /*if(Integer.parseInt(thisHouse.getH13())==f+1)
                    {   RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;}*/}}}


        RadioButton[] bt2 = new RadioButton[2];
        //CHECK WHICH BUTTON WAS SELECTED
        for(int f=0;f<rg2.getChildCount();f++)
        {   View o = rg2.getChildAt(f);
            if (o instanceof RadioButton)
            {   bt[f-1]=((RadioButton)o);
                if(thisHouse.getH13Tractor()!= null && !thisHouse.getH13Tractor().equals(""))
                {
                    if(Integer.parseInt(thisHouse.getH13Tractor())==1){
                        t01.setChecked(true);
                        break;

                    }else if(Integer.parseInt(thisHouse.getH13Tractor())==2){
                        t02.setChecked(true);
                        break;
                    }

                    /* if(Integer.parseInt(thisHouse.getH13Tractor())==f+1)
                {   RadioButton radioButton = bt2[f];
                    radioButton.setChecked(true);
                    break;}*/}}}

        RadioButton[] bt3 = new RadioButton[2];
        //CHECK WHICH BUTTON WAS SELECTED
        for(int f=0;f<rg3.getChildCount();f++)
        {   View o = rg3.getChildAt(f);
            if (o instanceof RadioButton)
            {   bt[f-1]=((RadioButton)o);
                if(thisHouse.getH13Motorcycle()!= null && !thisHouse.getH13Motorcycle().equals(""))
                {
                    if(Integer.parseInt(thisHouse.getH13Motorcycle())==1){
                        tel01.setChecked(true);
                        break;

                    }else if(Integer.parseInt(thisHouse.getH13Motorcycle())==2){
                        tel02.setChecked(true);
                        break;
                    }
                    /*if(Integer.parseInt(thisHouse.getH13Motorcycle())==f+1)
                {   RadioButton radioButton = bt3[f];
                    radioButton.setChecked(true);
                    break;}*/}}}


        RadioButton[] bt4 = new RadioButton[2];
        //CHECK WHICH BUTTON WAS SELECTED
        for(int f=0;f<rg4.getChildCount();f++)
        {   View o = rg4.getChildAt(f);
            if (o instanceof RadioButton)
            {   bt[f-1]=((RadioButton)o);
                if(thisHouse.getH13Bicycle()!= null && !thisHouse.getH13Bicycle().equals(""))
                {
                    if(Integer.parseInt(thisHouse.getH13Bicycle())==1){
                        cell01.setChecked(true);
                        break;

                    }else if(Integer.parseInt(thisHouse.getH13Bicycle())==2){
                        cell02.setChecked(true);
                        break;
                    }

                    /*if(Integer.parseInt(thisHouse.getH13Bicycle())==f+1)
                {   RadioButton radioButton = bt4[f];
                    radioButton.setChecked(true);
                    break;}*/}}}


        RadioButton[] bt5 = new RadioButton[2];
        //CHECK WHICH BUTTON WAS SELECTED
        for(int f=0;f<rg5.getChildCount();f++)
        {   View o = rg5.getChildAt(f);
            if (o instanceof RadioButton)
            {   bt[f-1]=((RadioButton)o);
                if(thisHouse.getH13DonkeyCart()!= null && !thisHouse.getH13DonkeyCart().equals(""))
                {
                    if(Integer.parseInt(thisHouse.getH13DonkeyCart())==1){
                        Media01.setChecked(true);
                        break;

                    }else if(Integer.parseInt(thisHouse.getH13DonkeyCart())==2){
                        Media02.setChecked(true);
                        break;
                    }

                    /*if(Integer.parseInt(thisHouse.getH13DonkeyCart())==f+1)
                {   RadioButton radioButton = bt5[f];
                    radioButton.setChecked(true);
                    break;}*/}}}



        RadioButton[] bt6 = new RadioButton[2];
        //CHECK WHICH BUTTON WAS SELECTED
        for(int f=0;f<rg6.getChildCount();f++)
        {   View o = rg6.getChildAt(f);
            if (o instanceof RadioButton)
            {   bt[f-1]=((RadioButton)o);
                if(thisHouse.getH13DonkeyHorse()!= null && !thisHouse.getH13DonkeyHorse().equals(""))
                {
                    if(Integer.parseInt(thisHouse.getH13DonkeyHorse())==1){
                        Electronic01.setChecked(true);
                        break;

                    }else if(Integer.parseInt(thisHouse.getH13DonkeyHorse())==2){
                        Electronic02.setChecked(true);
                        break;
                    }

                    /*if(Integer.parseInt(thisHouse.getH13DonkeyHorse())==f+1)
                {   RadioButton radioButton = bt6[f];
                    radioButton.setChecked(true);
                    break;}*/}}}


        RadioButton[] bt7 = new RadioButton[2];
        //CHECK WHICH BUTTON WAS SELECTED
        for(int f=0;f<rg7.getChildCount();f++)
        {   View o = rg7.getChildAt(f);
            if (o instanceof RadioButton)
            {   bt[f-1]=((RadioButton)o);
                if(thisHouse.getH13Camels()!= null && !thisHouse.getH13Camels().equals(""))
                {
                    if(Integer.parseInt(thisHouse.getH13Camels())==1){
                        Arts01.setChecked(true);
                        break;

                    }else if(Integer.parseInt(thisHouse.getH13Camels())==2){
                        Arts02.setChecked(true);
                        break;
                    }

                    /*if(Integer.parseInt(thisHouse.getH13Camels())==f+1)
                {   RadioButton radioButton = bt7[f];
                    radioButton.setChecked(true);
                    break;}*/}}}



        Button btnPrev = findViewById(R.id.button3);
//        PersonRoster pr[] = thisHouse.getPersons();

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();



            }
        });



        Button btnext = findViewById(R.id.button);

        btnext.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v)
            {
                int selectedId = rg.getCheckedRadioButtonId();
                int selectedId2 = rg2.getCheckedRadioButtonId();
                int selectedId3 = rg3.getCheckedRadioButtonId();
                int selectedId4 = rg4.getCheckedRadioButtonId();
                int selectedId5 = rg5.getCheckedRadioButtonId();
                int selectedId6 = rg6.getCheckedRadioButtonId();
                int selectedId7 = rg7.getCheckedRadioButtonId();

                selectedRbtn = (RadioButton) findViewById(selectedId); //radio
                selectedRbtn2 = (RadioButton) findViewById(selectedId2);//tv
                selectedRbtn3 = (RadioButton) findViewById(selectedId3);//telephone
                selectedRbtn4 = (RadioButton) findViewById(selectedId4);//cell
                selectedRbtn5 = (RadioButton) findViewById(selectedId5);//print
                selectedRbtn6 = (RadioButton) findViewById(selectedId6);//electronic
                selectedRbtn7 = (RadioButton) findViewById(selectedId7);//arts

                if (selectedRbtn == null || selectedRbtn2 == null || selectedRbtn3 == null || selectedRbtn4 == null || selectedRbtn5== null || selectedRbtn6 == null || selectedRbtn7 == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(bw.org.statsbots.bias.H13.this);
                    builder.setTitle("Transport Facilities");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);
                    builder.setMessage("Please answer all questions");
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

                }
                else {
                    thisHouse.setH13(s1[0]);
                    thisHouse.setH13Tractor(s2[0]);
                    thisHouse.setH13Motorcycle(s3[0]);
                    thisHouse.setH13Bicycle(s4[0]);
                    thisHouse.setH13DonkeyCart(s5[0]);
                    thisHouse.setH13DonkeyHorse(s6[0]);
                    thisHouse.setH13Camels(s7[0]);


                    //Save all responses
                    //thisHouse.setVISIT1_RESULT("9");
                    thisHouse.setInterview_Status("9");
                    myDB.updateHHEnd(myDB.getWritableDatabase(),thisHouse);
                    myDB.updateHHStatus(thisHouse);



                    Intent q1o2 = new Intent(bw.org.statsbots.bias.H13.this, Dashboard.class);
                    q1o2.putExtra("tbNumber", "1");
                    startActivity(q1o2);
                }


            }


        });

















    }

}
