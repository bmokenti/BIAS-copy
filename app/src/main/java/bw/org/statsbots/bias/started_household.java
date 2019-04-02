package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.sql.Types.NULL;

public class started_household extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected LibraryClass lib;
    protected TextView Started_Locality,Timestarted,tvcompleted,startedStatus,startedDwelling,HH_NO,more,commentHead, commenttxt;
    protected LinearLayout lcomment ;
    protected Button btnUpdate,btnComplete;
    protected LinearLayout tb,hiv;
    protected PersonRoster p1=null;
    protected DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_household);

        Started_Locality = findViewById(R.id.Started_Locality);
        Timestarted= findViewById(R.id.Timestarted);
        startedDwelling = findViewById(R.id.startedDwelling);
        HH_NO= findViewById(R.id.HH_NO);
        more= findViewById(R.id.more);
        lcomment=findViewById(R.id.superComment);
        commentHead=findViewById(R.id.commentHead);
        commenttxt=findViewById(R.id.Comment);
        //tvcompleted=findViewById(R.id.tvcompleted);

        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());

        Intent i = getIntent();

        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        thisHouse = myDB.getHouseForUpdate(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber()).get(0);


        if(thisHouse.getSuperComment()!=null){
            commenttxt.setVisibility(View.VISIBLE);
            commenttxt.setText(thisHouse.getSuperComment());
        }


        btnComplete=findViewById(R.id.btnComplete);

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final CharSequence[] list1 = new String[3];
                final ArrayList<String> list=new ArrayList<>();
                try {
                    if (thisHouse.getVISIT1_RESULT()!=null   && thisHouse.getVISIT2_RESULT()==null && thisHouse.getVISIT3_RESULT()==null)
                    {

                        String mytime=thisHouse.getDATE1();

                        Date date=null;
                        SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
                        try
                        {
                            date = format.parse(mytime);
                            System.out.println(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        list1[0] = "Visit 1 : " + thisHouse.getDATE1();
                        list.add("Visit 1 : " + thisHouse.getDATE1());
                        list1[1] = "Visit 2 : ";
                        list.add("Visit 2 : ");
                    } else if (thisHouse.getVISIT1_RESULT()!=null   && thisHouse.getVISIT2_RESULT()!=null && thisHouse.getVISIT3_RESULT()==null) {

                        String mytime=thisHouse.getDATE2();

                        Date date=null;
                        SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
                        try
                        {
                            date = format.parse(mytime);
                            System.out.println(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        list1[0] = "Visit 1 : " + thisHouse.getDATE1();
                        list.add("Visit 1 : " + thisHouse.getDATE1());
                        list1[1] = "Visit 2 : " + thisHouse.getDATE2();
                        list.add("Visit 2 : " + thisHouse.getDATE2());
                        list1[2] = "Visit 3 : ";
                        list.add("Visit 3 : ");
                    } else if (thisHouse.getVISIT1_RESULT()!=null   && thisHouse.getVISIT2_RESULT()!=null && thisHouse.getVISIT3_RESULT()!=null) {

                        list1[0] = "Visit 1 : " + thisHouse.getDATE1();
                        list.add("Visit 1 : " + thisHouse.getDATE1());
                        list1[1] = "Visit 2 : " + thisHouse.getDATE2();
                        list.add("Visit 2 : " + thisHouse.getDATE2());
                        list1[2] = "Visit 3 : "+ thisHouse.getDATE3();
                        list.add("Visit 3 : "+ thisHouse.getDATE3());
                    } else {
                        list.add("Visit 1 ");
                    }




                }catch (Exception f){
                    f.printStackTrace();
                }


                final  int FinalResult[] = new int[1];

                final AlertDialog.Builder builder2 = new AlertDialog.Builder(started_household.this);
                builder2.setTitle("Select Visit Number (Household Questionnaire)");

                int i = 0;

                final String[] ss = list.toArray(new String[list.size()]);
                final HouseHold tempIndiv = thisHouse;
                builder2.setSingleChoiceItems(
                        ss, // Items list
                        -1, // Index of checked item (-1 = no selection)
                        new DialogInterface.OnClickListener() // Item click listener
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Get the alert dialog selected item's text
                                final CharSequence[] results = new String[6];
                                results[0] = "1. Completed";
                                results[1] = "2. Partially Completed";
                                results[2] = "3. Present but not available for interviews";
                                results[3] = "4. Refused";
                                results[4] = "5. Postponed";
                                results[5] = "6. Other (Specify)";


                                if (i == 0) {
                                    //SHOW LIST FOR RESULTS
                                    final AlertDialog.Builder builder3 = new AlertDialog.Builder(started_household.this);
                                    builder3.setTitle("Select Visit 1 Result");
                                    builder3.setSingleChoiceItems(
                                            results, // Items list
                                            -1, // Index of checked item (-1 = no selection)
                                            new DialogInterface.OnClickListener() // Item click listener
                                            {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //save the selected results
                                                    final SQLiteDatabase db = myDB.getWritableDatabase();
                                                    ContentValues hhValues = new ContentValues();
                                                    hhValues.put("VISIT1_RESULT", i + 1);
                                                    Date d = new Date();
                                                    CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss", d.getTime());
                                                    //hhValues.put("DATE1", s.toString());


                                                    final int result = i+1;
                                                    if(result==6){

                                                        final ContentValues cvalues = hhValues;

                                                        final EditText taskEditText = new EditText(started_household.this);
                                                        AlertDialog dialog = new AlertDialog.Builder(started_household.this)
                                                                .setTitle("Other Specify")
                                                                .setMessage("Enter Other Specify")
                                                                .setView(taskEditText)
                                                                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        String val = String.valueOf(taskEditText.getText());

                                                                        if(TextUtils.isEmpty(val) && val==null && val.contentEquals(null)){

                                                                            final AlertDialog.Builder builder1 = new AlertDialog.Builder(started_household.this);
                                                                            builder1.setTitle("Other Specify");
                                                                            builder1.setIcon(R.drawable.ic_warning_orange_24dp);
                                                                            builder1.setMessage("Other Specify cannot be empty, Retry");
                                                                            builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                                public void onClick(DialogInterface dialog, int id) {
                                                                                    Intent intent = new Intent(started_household.this, started_household.class);
                                                                                    intent.putExtra("Household", thisHouse);
                                                                                    startActivity(intent);
                                                                                    finish();
                                                                                }
                                                                            });
                                                                            AlertDialog alert = builder1.create();
                                                                            alert.show();



                                                                        }else {
//save household visit status
                                                                            cvalues.put("COMMENT1",val);
                                                                            int i = db.update
                                                                                    ("House_Hold_Assignments", // table
                                                                                            cvalues, // column/value
                                                                                            "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                                                                                            new String[]{String.valueOf(tempIndiv.getAssignment_ID()), String.valueOf(tempIndiv.getBatchNumber())}
                                                                                    );

                                                                            db.close();


                                                                            ContentValues hhValues = new ContentValues();
                                                                            hhValues.put("VISIT1_RESULT", i + 1);


                                                                            //Restart the current activity
                                                                            Intent intent = new Intent(started_household.this, started_household.class);
                                                                            intent.putExtra("Household", thisHouse);
                                                                            startActivity(intent);
                                                                            finish();


                                                                        }


                                                                    }
                                                                })
                                                                .setNegativeButton("Cancel", null)
                                                                .create();
                                                                dialog.show();

                                                    }else{

                                                        i = db.update
                                                                ("House_Hold_Assignments", // table
                                                                        hhValues, // column/value
                                                                        "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                                                                        new String[]{String.valueOf(tempIndiv.getAssignment_ID()), String.valueOf(tempIndiv.getBatchNumber())}
                                                                );

                                                        /*******UPDATE HOUSE FOR PARTIAL SEND*****************/

                                                        //UPDATE HOUSEHOLD
                                                        myDB.updateHousehold(db,thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"Clear", "3");
                                                        myDB.close();
                                                        /********************END PARTIAL****************/


                                                        //Restart the current activity
                                                        Intent intent = new Intent(started_household.this, started_household.class);
                                                        intent.putExtra("Household", thisHouse);
                                                        startActivity(intent);
                                                        finish();

                                                    }
                                                }
                                            });
                                    AlertDialog ad2 = builder3.show();

                                    //SET DIVIDER
                                    ListView listView = ad2.getListView();
                                    listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                    listView.setDividerHeight(3);


                                } else if (ss.length == 2) {

                                    final AlertDialog.Builder builder3 = new AlertDialog.Builder(started_household.this);
                                    builder3.setTitle("Select Visit 2 Result");
                                    builder3.setSingleChoiceItems(
                                            results, // Items list
                                            -1, // Index of checked item (-1 = no selection)
                                            new DialogInterface.OnClickListener() // Item click listener
                                            {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //save the selected results
                                                    final SQLiteDatabase db = myDB.getWritableDatabase();
                                                    ContentValues hhValues = new ContentValues();

                                                    hhValues.put("VISIT2_RESULT", i + 1);
                                                    Date d = new Date();
                                                    CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss", d.getTime());
                                                    hhValues.put("DATE2", s.toString());

                                                    final int result = i+1;
                                                    if(result==6){

                                                        final ContentValues cvalues = hhValues;

                                                        final EditText taskEditText = new EditText(started_household.this);
                                                        AlertDialog dialog = new AlertDialog.Builder(started_household.this)
                                                                .setTitle("Other Specify")
                                                                .setMessage("Enter Other Specify")
                                                                .setView(taskEditText)
                                                                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        String val = String.valueOf(taskEditText.getText());

                                                                        if(TextUtils.isEmpty(val) && val==null && val.contentEquals(null)){

                                                                            final AlertDialog.Builder builder1 = new AlertDialog.Builder(started_household.this);
                                                                            builder1.setTitle("Other Specify");
                                                                            builder1.setIcon(R.drawable.ic_warning_orange_24dp);
                                                                            builder1.setMessage("Other Specify cannot be empty, Retry");
                                                                            builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                                public void onClick(DialogInterface dialog, int id) {
                                                                                    Intent intent = new Intent(started_household.this, started_household.class);
                                                                                    intent.putExtra("Household", thisHouse);
                                                                                    startActivity(intent);
                                                                                    finish();
                                                                                }
                                                                            });
                                                                            AlertDialog alert = builder1.create();
                                                                            alert.show();



                                                                        }else {
//save household visit status
                                                                            cvalues.put("COMMENT2",val);
                                                                            int i = db.update
                                                                                    ("House_Hold_Assignments", // table
                                                                                            cvalues, // column/value
                                                                                            "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                                                                                            new String[]{String.valueOf(tempIndiv.getAssignment_ID()), String.valueOf(tempIndiv.getBatchNumber())}
                                                                                    );

                                                                            db.close();


                                                                            ContentValues hhValues = new ContentValues();
                                                                            hhValues.put("VISIT1_RESULT", i + 1);


                                                                            //Restart the current activity
                                                                            Intent intent = new Intent(started_household.this, started_household.class);
                                                                            intent.putExtra("Household", thisHouse);
                                                                            startActivity(intent);
                                                                            finish();


                                                                        }


                                                                    }
                                                                })
                                                                .setNegativeButton("Cancel", null)
                                                                .create();
                                                        dialog.show();

                                                    }else{

                                                        i = db.update
                                                                ("House_Hold_Assignments", // table
                                                                        hhValues, // column/value
                                                                        "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                                                                        new String[]{String.valueOf(tempIndiv.getAssignment_ID()), String.valueOf(tempIndiv.getBatchNumber())}
                                                                );

                                                        /*******UPDATE HOUSE FOR PARTIAL SEND*****************/

                                                        //UPDATE HOUSEHOLD
                                                        myDB.updateHousehold(db,thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"Clear", "3");
                                                        myDB.close();
                                                        /********************END PARTIAL****************/


                                                        //Restart the current activity
                                                        Intent intent = new Intent(started_household.this, started_household.class);
                                                        intent.putExtra("Household", thisHouse);
                                                        startActivity(intent);
                                                        finish();

                                                    }





                                                }
                                            });
                                    AlertDialog ad2 = builder3.show();

                                    //SET DIVIDER
                                    ListView listView = ad2.getListView();
                                    listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                    listView.setDividerHeight(3);

                                } else {

                                    final AlertDialog.Builder builder3 = new AlertDialog.Builder(started_household.this);
                                    builder3.setTitle("Select Visit 3 Result");
                                    builder3.setSingleChoiceItems(
                                            results, // Items list
                                            -1, // Index of checked item (-1 = no selection)
                                            new DialogInterface.OnClickListener() // Item click listener
                                            {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //save the selected results
                                                    final SQLiteDatabase db = myDB.getWritableDatabase();
                                                    ContentValues hhValues = new ContentValues();
                                                    hhValues.put("VISIT3_RESULT", i + 1);
                                                    Date d = new Date();
                                                    CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss", d.getTime());
                                                    hhValues.put("DATE3", s.toString());
                                                    final int result = i+1;
                                                    if(result==6){

                                                        final ContentValues cvalues = hhValues;

                                                        final EditText taskEditText = new EditText(started_household.this);
                                                        AlertDialog dialog = new AlertDialog.Builder(started_household.this)
                                                                .setTitle("Other Specify")
                                                                .setMessage("Enter Other Specify")
                                                                .setView(taskEditText)
                                                                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        String val = String.valueOf(taskEditText.getText());

                                                                        if(TextUtils.isEmpty(val) && val==null && val.contentEquals(null)){

                                                                            final AlertDialog.Builder builder1 = new AlertDialog.Builder(started_household.this);
                                                                            builder1.setTitle("Other Specify");
                                                                            builder1.setIcon(R.drawable.ic_warning_orange_24dp);
                                                                            builder1.setMessage("Other Specify cannot be empty, Retry");
                                                                            builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                                public void onClick(DialogInterface dialog, int id) {
                                                                                    Intent intent = new Intent(started_household.this, started_household.class);
                                                                                    intent.putExtra("Household", thisHouse);
                                                                                    startActivity(intent);
                                                                                    finish();
                                                                                }
                                                                            });
                                                                            AlertDialog alert = builder1.create();
                                                                            alert.show();



                                                                        }else {
//save household visit status
                                                                            cvalues.put("COMMENT1",val);
                                                                            int i = db.update
                                                                                    ("House_Hold_Assignments", // table
                                                                                            cvalues, // column/value
                                                                                            "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                                                                                            new String[]{String.valueOf(tempIndiv.getAssignment_ID()), String.valueOf(tempIndiv.getBatchNumber())}
                                                                                    );

                                                                            db.close();


                                                                            ContentValues hhValues = new ContentValues();
                                                                            hhValues.put("VISIT1_RESULT", i + 1);


                                                                            //Restart the current activity
                                                                            Intent intent = new Intent(started_household.this, started_household.class);
                                                                            intent.putExtra("Household", thisHouse);
                                                                            startActivity(intent);
                                                                            finish();


                                                                        }


                                                                    }
                                                                })
                                                                .setNegativeButton("Cancel", null)
                                                                .create();
                                                        dialog.show();

                                                    }else{

                                                        i = db.update
                                                                ("House_Hold_Assignments", // table
                                                                        hhValues, // column/value
                                                                        "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                                                                        new String[]{String.valueOf(tempIndiv.getAssignment_ID()), String.valueOf(tempIndiv.getBatchNumber())}
                                                                );

                                                        /*******UPDATE HOUSE FOR PARTIAL SEND*****************/

                                                        //UPDATE HOUSEHOLD
                                                        myDB.updateHousehold(db,thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"Clear", "3");
                                                        myDB.close();
                                                        /********************END PARTIAL****************/


                                                        //Restart the current activity
                                                        Intent intent = new Intent(started_household.this, started_household.class);
                                                        intent.putExtra("Household", thisHouse);
                                                        startActivity(intent);
                                                        finish();

                                                    }

                                                }
                                            });
                                    AlertDialog ad2 = builder3.show();

                                    //SET DIVIDER
                                    ListView listView = ad2.getListView();
                                    listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                    listView.setDividerHeight(3);


                                }


                            }
                        });

                AlertDialog ad = builder2.show();

                //SET DIVIDER
                ListView listView = ad.getListView();
                //listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));


                listView.setDividerHeight(3);


                //OK Button layout
                final Button positiveButton = ad.getButton(AlertDialog.BUTTON_POSITIVE);
                LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                positiveButton.setTextColor(Color.WHITE);
                positiveButton.setBackgroundColor(Color.parseColor("#3FC0FF"));
                positiveButton.setLayoutParams(positiveButtonLL);

                /******************************END SET STATUS************************************************/

            }
        });

        Sample s =  myDB.getSample(myDB.getReadableDatabase(), thisHouse.getAssignment_ID());
        String s1[] = s.getDistrictEAVillageLocality().split(":");
        Started_Locality.setText("Locality: "+s1[1]);//thisHouse.getLOCALITY_NO());
        Timestarted.setText(Timestarted.getText()+ thisHouse.getDATE1());
        startedDwelling.setText("Dwelling No: "+thisHouse.getDWELLING_NO());

        HH_NO.setText("House No: "+thisHouse.getHH_NO());

        if(thisHouse.getSuperComment()!=null && !thisHouse.getSuperComment().isEmpty() ){
            lcomment.setVisibility(View.VISIBLE);
            commenttxt.setText(thisHouse.getSuperComment());
        }

        btnUpdate = findViewById(R.id.btnUpdate);



        final LinearLayout llbutton = (LinearLayout)findViewById(R.id.RosterButton);
        llbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(findViewById(R.id.Roster).getVisibility() == View.GONE){
                    findViewById(R.id.Roster).setVisibility(View.VISIBLE);
                    Drawable drawable = ContextCompat.getDrawable(started_household.this, R.drawable.ic_expand_less_black_24dp);
                    TextView tv = findViewById(R.id.rosterHead);
                    tv.setCompoundDrawablesWithIntrinsicBounds( null,null, drawable, null);


                }else{
                    findViewById(R.id.Roster).setVisibility(View.GONE);
                    Drawable drawable = ContextCompat.getDrawable(started_household.this, R.drawable.ic_expand_more_black_24dp);
                    TextView tv = findViewById(R.id.rosterHead);
                    tv.setCompoundDrawablesWithIntrinsicBounds( null,null, drawable, null);

                }
            }
        });

        /**
         * fold for HIV Inidividuals
         */
        final LinearLayout hivHead = (LinearLayout)findViewById(R.id.hivHead);
        hivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(findViewById(R.id.hiv).getVisibility() == View.GONE){
                    findViewById(R.id.hiv).setVisibility(View.VISIBLE);
                    Drawable drawable = ContextCompat.getDrawable(started_household.this, R.drawable.ic_expand_less_black_24dp);
                    TextView tv = findViewById(R.id.tv_HIV);
                    tv.setCompoundDrawablesWithIntrinsicBounds( null,null, drawable, null);


                }else{
                    findViewById(R.id.hiv).setVisibility(View.GONE);
                    Drawable drawable = ContextCompat.getDrawable(started_household.this, R.drawable.ic_expand_more_black_24dp);
                    TextView tv = findViewById(R.id.tv_HIV);
                    tv.setCompoundDrawablesWithIntrinsicBounds( null,null, drawable, null);

                }
            }
        });



        /**
         * fold for TB Inidivudals
         */
        final LinearLayout tbHead = (LinearLayout)findViewById(R.id.tbHead);
        tbHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(findViewById(R.id.tb).getVisibility() == View.GONE){
                    findViewById(R.id.tb).setVisibility(View.VISIBLE);
                    Drawable drawable = ContextCompat.getDrawable(started_household.this, R.drawable.ic_expand_less_black_24dp);
                    TextView tv = findViewById(R.id.tv_TB);
                    tv.setCompoundDrawablesWithIntrinsicBounds( null,null, drawable, null);


                }else{
                    findViewById(R.id.tb).setVisibility(View.GONE);
                    Drawable drawable = ContextCompat.getDrawable(started_household.this, R.drawable.ic_expand_more_black_24dp);
                    TextView tv = findViewById(R.id.tv_TB);
                    tv.setCompoundDrawablesWithIntrinsicBounds( null,null, drawable, null);

                }
            }
        });



        LinearLayout hiv = (LinearLayout)findViewById(R.id.hiv);
        hiv.setVisibility(View.GONE);

        LinearLayout tb = (LinearLayout)findViewById(R.id.tb);
        tb.setVisibility(View.GONE);

        LinearLayout ll = (LinearLayout)findViewById(R.id.Roster);
        ll.setVisibility(View.VISIBLE);




        //############################populate Roster
        final List<PersonRoster> r = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
        List<Button> btnPersons = new ArrayList<>();



        if(r.size() !=0) {
            /***Script to check completion of roster***/
            int yyy = 0;
            for (PersonRoster pp:r) {
                if(pp.getP20()!=null || pp.getP21()!=null){
                    yyy++;
                }else{
                    if(pp.getP04YY() !=null && pp.getP09()!=null){
                        yyy++;
                    }
                }
            }
            //error for incomplete Roster
            if(yyy != r.size()){
                TextView t = new TextView(this);
                t.setPadding(5,5,5,5);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(10,10,10,10);
                t.setLayoutParams(params);
                t.setBackgroundResource(R.drawable.error_txt);
                Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_error_red_24dp);
                t.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
                t.setText("You did not finish Household member, Tap on Update Household to review all members");
                ll.addView(t);
            }

            //Error for incomplete House
            if(thisHouse.getH13Camels() == null){
                TextView t = new TextView(this);
                t.setPadding(5,5,5,5);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(10,10,10,10);
                t.setLayoutParams(params);
                t.setBackgroundResource(R.drawable.error_txt);
                t.setText("You did not finish Household H Section, Tap on Update Household to attempt pending questions");
                Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_error_red_24dp);
                t.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
                ll.addView(t);
            }




            for (int o = 0; o < r.size(); o++) {
                /****CHEK IF SOME BASIC VARIABLES ARE FILLED IF NOT LAUNCH P01****/
                if((r.get(o).getP06()==null) || (((r.get(o).getP06().equals("3")) &&  r.get(o).getP07()==null))){
                    //Request the user to enable network settings. Build the AlertDialog first.
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                    AlertDialog.Builder adBuilder = new AlertDialog.Builder(started_household.this)
                            .setTitle("Incomplete Household Questionnaire")
                            .setMessage("An incomplete household questionnaire has been detected, Please complete household first.")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    Intent intent = new Intent(started_household.this, MainActivity.class);
                                    intent.putExtra("Household",  thisHouse);
                                    startActivity(intent);

                                }
                            });

                    //call show() to build and show the AlertDialog.
                    adBuilder.setCancelable(false);

                    AlertDialog ad = adBuilder.show();
                    break;
                }




                Button btn = new Button(this);
                final int position=o;


                /**********************************************************************************************/
                /************************HANDLE SEND QUESTIONNAIRE *****************************/
                btn.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        List<Individual> Ind = myDB.getdataIndivisual(thisHouse);
                        Individual indiv = null;
                        for (Individual ii : Ind) {
                            if (ii.getSRNO() == r.get(position).getSRNO()) {
                                indiv = ii;
                            }
                        }

                        if(indiv==null){
                            //handle those without individual questionnaire




                        }else {

                            final CharSequence[] list1 = new String[3];
                            final ArrayList<String> list=new ArrayList<>();
                            try {
                                if (indiv.getVISIT1() != null && indiv.getVISIT2() == null && indiv.getVISIT3() == null) {

                                    list1[0] = "Visit 1 : " + indiv.getDATE1();
                                    list.add("Visit 1 : " + indiv.getDATE1());
                                    list1[1] = "Visit 2 : ";
                                    list.add("Visit 2 : ");
                                } else if (indiv.getVISIT1() != null && indiv.getVISIT2() != null && indiv.getVISIT3() == null) {

                                    list1[0] = "Visit 1 : " + indiv.getDATE1();
                                    list.add("Visit 1 : " + indiv.getDATE1());
                                    list1[1] = "Visit 2 : " + indiv.getDATE2();
                                    list.add("Visit 2 : " + indiv.getDATE2());
                                    list1[2] = "Visit 3 : ";
                                    list.add("Visit 3 : ");
                                } else if (indiv.getVISIT1() != null && indiv.getVISIT2() != null && indiv.getVISIT3() != null) {

                                    list1[0] = "Visit 1 : " + indiv.getDATE1();
                                    list.add("Visit 1 : " + indiv.getDATE1());
                                    list1[1] = "Visit 2 : " + indiv.getDATE2();
                                    list.add("Visit 2 : " + indiv.getDATE2());
                                    list1[2] = "Visit 3 : ";
                                    list.add("Visit 3 : "+ indiv.getDATE3());
                                } else {
                                    list.add("Visit 1 ");
                                }




                            }catch (Exception f){
                                f.printStackTrace();
                            }


                            final  int FinalResult[] = new int[1];

                            final AlertDialog.Builder builder2 = new AlertDialog.Builder(started_household.this);
                            builder2.setTitle("Select Visit Number for : " + r.get(position).getP01());

                            int  i=0;

                            final String[] s = list.toArray(new String[list.size()]);
                            final Individual tempIndiv = indiv;
                            builder2.setSingleChoiceItems(
                                    s, // Items list
                                    -1, // Index of checked item (-1 = no selection)
                                    new DialogInterface.OnClickListener() // Item click listener
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            // Get the alert dialog selected item's text
                                            final CharSequence[] results = new String[6];
                                            results[0] = "1. Completed";
                                            results[1] = "2. Partially Completed";
                                            results[2] = "3. Present but not available for interviews";
                                            results[3] = "4. Refused";
                                            results[4] = "5. Postponed";
                                            results[5] = "6. Other (Specify)";


                                            if(i==0){
                                                //SHOW LIST FOR RESULTS
                                                final AlertDialog.Builder builder3 = new AlertDialog.Builder(started_household.this);
                                                builder3.setTitle("Select Visit 1 Result");
                                                builder3.setSingleChoiceItems(
                                                        results, // Items list
                                                        -1, // Index of checked item (-1 = no selection)
                                                        new DialogInterface.OnClickListener() // Item click listener
                                                        {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                //save the selected results
                                                                SQLiteDatabase db = myDB.getWritableDatabase();
                                                                ContentValues hhValues = new ContentValues();
                                                                hhValues.put("VISIT1",i+1);
                                                                Date d = new Date();
                                                                CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss",d.getTime());
                                                                hhValues.put("DATE1",s.toString());
                                                                i = db.update
                                                                        (   "Individual", // table
                                                                                hhValues, // column/value
                                                                                "Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                                                                                new String[]{ String.valueOf(tempIndiv.getAssignmentID()),String.valueOf(tempIndiv.getBatch()), String.valueOf(tempIndiv.getSRNO()) }
                                                                        );

                                                                db.close();
                                                                /*******UPDATE HOUSE FOR PARTIAL SEND*****************/

                                                                //UPDATE HOUSEHOLD
                                                                myDB.updateHousehold(db,thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"Clear", "3");
                                                                myDB.close();


                                                                //Restart the current activity
                                                                Intent intent=new Intent(started_household.this,started_household.class);
                                                                intent.putExtra("Household", thisHouse);
                                                                startActivity(intent);
                                                                finish();



                                                                }
                                                            });
                                                AlertDialog ad2 = builder3.show();

                                                //SET DIVIDER
                                                ListView listView = ad2.getListView();
                                                listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                                listView.setDividerHeight(3);








                                            }else if(s.length==2){

                                                final AlertDialog.Builder builder3 = new AlertDialog.Builder(started_household.this);
                                                builder3.setTitle("Select Visit 2 Result");
                                                builder3.setSingleChoiceItems(
                                                        results, // Items list
                                                        -1, // Index of checked item (-1 = no selection)
                                                        new DialogInterface.OnClickListener() // Item click listener
                                                        {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                //save the selected results
                                                                SQLiteDatabase db = myDB.getWritableDatabase();
                                                                ContentValues hhValues = new ContentValues();
                                                                hhValues.put("VISIT2",i+1);
                                                                Date d = new Date();
                                                                CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss",d.getTime());
                                                                hhValues.put("DATE2",s.toString());
                                                                i = db.update
                                                                        (   "Individual", // table
                                                                                hhValues, // column/value
                                                                                "Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                                                                                new String[]{ String.valueOf(tempIndiv.getAssignmentID()),String.valueOf(tempIndiv.getBatch()), String.valueOf(tempIndiv.getSRNO()) }
                                                                        );

                                                                /*******UPDATE HOUSE FOR PARTIAL SEND*****************/

                                                                //UPDATE HOUSEHOLD
                                                                myDB.updateHousehold(db,thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"Clear", "3");
                                                                myDB.close();
                                                                /********************END PARTIAL****************/

                                                                //Restart the current activity
                                                                Intent intent=new Intent(started_household.this,started_household.class);                               intent.putExtra("Household", thisHouse);
                                                                startActivity(intent);
                                                                finish();



                                                            }
                                                        });
                                                AlertDialog ad2 = builder3.show();

                                                //SET DIVIDER
                                                ListView listView = ad2.getListView();
                                                listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                                listView.setDividerHeight(3);

                                            }else{

                                                final AlertDialog.Builder builder3 = new AlertDialog.Builder(started_household.this);
                                                builder3.setTitle("Select Visit 3 Result");
                                                builder3.setSingleChoiceItems(
                                                        results, // Items list
                                                        -1, // Index of checked item (-1 = no selection)
                                                        new DialogInterface.OnClickListener() // Item click listener
                                                        {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                //save the selected results
                                                                SQLiteDatabase db = myDB.getWritableDatabase();
                                                                ContentValues hhValues = new ContentValues();
                                                                hhValues.put("VISIT3",i+1);
                                                                Date d = new Date();
                                                                CharSequence s = android.text.format.DateFormat.format("yyyy/MM/dd hh:mm:ss",d.getTime());
                                                                hhValues.put("DATE3",s.toString());
                                                                i = db.update
                                                                        (   "Individual", // table
                                                                                hhValues, // column/value
                                                                                "Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                                                                                new String[]{ String.valueOf(tempIndiv.getAssignmentID()),String.valueOf(tempIndiv.getBatch()), String.valueOf(tempIndiv.getSRNO()) }
                                                                        );

                                                                /*******UPDATE HOUSE FOR PARTIAL SEND*****************/

                                                                //UPDATE HOUSEHOLD
                                                                myDB.updateHousehold(db,thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),"Clear", "3");
                                                                myDB.close();
                                                                /********************END PARTIAL****************/


                                                                //Restart the current activity
                                                                Intent intent=new Intent(started_household.this,started_household.class);intent.putExtra("Household", thisHouse);
                                                                startActivity(intent);
                                                                finish();



                                                            }
                                                        });
                                                AlertDialog ad2 = builder3.show();

                                                //SET DIVIDER
                                                ListView listView = ad2.getListView();
                                                listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                                listView.setDividerHeight(3);



                                            }




                                        }
                                    });

//builder.setIcon(R.drawable.ic_person_black_24dp);
                            AlertDialog ad = builder2.show();

                            //SET DIVIDER
                            ListView listView = ad.getListView();
                            //listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));




                            listView.setDividerHeight(3);


                            //OK Button layout
                            final Button positiveButton = ad.getButton(AlertDialog.BUTTON_POSITIVE);
                            LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                            positiveButtonLL.width=ViewGroup.LayoutParams.MATCH_PARENT;
                            positiveButton.setTextColor(Color.WHITE);
                            positiveButton.setBackgroundColor(Color.parseColor("#3FC0FF"));
                            positiveButton.setLayoutParams(positiveButtonLL);



                        }



                        /*---------------------------------------*/








                        return true;
                    }
                });



                /********************************END**********************************************/
                Drawable drawable = ContextCompat.getDrawable(started_household.this, R.drawable.ic_person_black_24dp);
                btn.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                final PersonRoster person = r.get(o);
                final String gender, srno;
                if (r.get(o).getP03().equals("1")) {
                    gender = "Male";
                } else {
                    gender = "Female";
                }
                srno = String.valueOf(r.get(o).getSRNO());
                String p = "";
                if (String.valueOf(thisHouse.getHead()).equals(r.get(o).getSRNO())) {
                    p = "(Head of House)";
                }
                String Info = "";
                //btn.setEnabled(false);
                final PersonRoster temp = r.get(o);
                s.getStatusCode();


                if (s.getStatusCode().equals("1")) {
                    //************************************HIV
                    if (Integer.parseInt(r.get(o).getP04YY()) < 15 || Integer.parseInt(r.get(o).getP04YY()) > 64) {

                        /**
                         * FOR UNDER 15 AND OVER 64
                         *
                         * rapid - blood
                         */

                        if (r.get(o).getRapidResults() == null || r.get(o).getRapidResults().equals("null") || r.get(o).getRapidResults().equals("")) {

                            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_filter_center_focus_blue_24dp);
                            btn.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);


                            int mm = 0;
                            int yy = Integer.parseInt(r.get(o).getP04YY());
                            final PersonRoster person1 = r.get(o);
                            if (r.get(o).getP04MM() == null) {
                            } else {
                                mm = Integer.parseInt(r.get(o).getP04MM());
                                int wks = Integer.parseInt(r.get(o).getP04WKS());
                            }


                            if ((yy > 64)) {
                                //nkuku
                                //Log.d("18 MONTHS TO 14 YEARS", r.get(o).getP01() + "  " + r.get(o).getP04YY() +"/"+ r.get(o).getP04MM()+"/"+r.get(o).getP04WKS());


                                Info = "Pending Blood Collection & Rapid";
                                Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                btn.setCompoundDrawablesWithIntrinsicBounds(d1, null, null, null);
                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //**Replace This house with 1 individual

                                        ////replaced HIVConsentOver64
                                        Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                        q1o2.putExtra("Personroster", person1);
                                        startActivity(q1o2);


                                    }
                                });

                            }
                            if (yy < 15) {
                                //Less than  15
                                if (yy >= 1 && mm >= 6) {
                                    Info = "Pending Blood Collection & Rapid";
                                    Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                    btn.setCompoundDrawablesWithIntrinsicBounds(d1, null, null, null);
                                    btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                            q1o2.putExtra("Personroster", person1);
                                            startActivity(q1o2);
                                        }
                                    });

                                } else {
                                    if (yy == 0 && mm <= 2) {
                                        btn.setEnabled(false);
                                        Info = "Under 6 Weeks, No test required";
                                        Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                        btn.setCompoundDrawablesWithIntrinsicBounds(d1, null, null, null);


                                        Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                        q1o2.putExtra("Personroster", person1);
                                        //startActivity(q1o2);

                                    } else {
                                        Info = "Pending Blood Collection";
                                        Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                        btn.setCompoundDrawablesWithIntrinsicBounds(d1, null, null, null);
                                        btn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //**Replace This house with 1 individual
                                                Intent q1o2 = new Intent(started_household.this, Barcode.class);

                                                q1o2.putExtra("Personroster", person1);
                                                startActivity(q1o2);


                                            }
                                        });

                                    }
                                }

                            }


                        } else {
                            Log.d("Over 15", r.get(o).getP01() + "  " + r.get(o).getP04YY() + "/" + r.get(o).getP04MM() + "/" + r.get(o).getP04WKS());
                            if (r.get(o).getRapidResults().equals("4") ) {
                                Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_library_books_blue_24dp);
                                btn.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
                                //btn.setEnabled(false);
                                Info = "Has Documentation";

                            } else {
                                if (r.get(o).getRapidDate() != null || r.get(o).getBloodDraw() != null || r.get(o).getChPrntlConsentBloodDraw() != null ) {
                                    Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                                    btn.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
                                    Info = "Rapid Test Done";
                                }
                            }
                        }

                    } else {
                        //15 years and over

                        List<Individual> Ind = myDB.getdataIndivisual(thisHouse);
                        Individual individual = null;
                        for (Individual ii : Ind) {
                            if (ii.getSRNO() == r.get(o).getSRNO()) {
                                individual = ii;
                            }
                        }
                        if (individual == null || individual.getIndRapidResults() == null) {
                            individual = new Individual();
                            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_person_black_24dp);
                            btn.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);

                            individual = new Individual();
                            Info = "Pending Questionnaire, Blood Collection";

                            individual.setSRNO(r.get(o).getSRNO());
                            individual.setAssignmentID(r.get(o).getAssignmentID());
                            individual.setBatch(r.get(o).getBatch());

                            if (myDB.checkIndividual(individual)) {

                            } else {
                                //Insert
                                myDB.insertIndividual(individual);

                            }

                            List<HouseHold> h = myDB.getHouseHold(myDB.getReadableDatabase(), "", individual.getAssignmentID());
                            for (HouseHold hhh : h) {
                                if (hhh.getAssignment_ID().equals(individual.getAssignmentID()) && hhh.getBatchNumber().equals(individual.getBatch()) && hhh.getIsHIVTB40().equals("True")) {
                                    if (thisHouse.getIsHIVTB40().equals("True")) {
                                        Info = Info + ", Blood Collection";
                                    }
                                }
                            }


                            final Individual temp1 = individual;
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //**Replace This house with 1 individual
                                    Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                    q1o2.putExtra("Personroster", temp);
                                    q1o2.putExtra("Individual", temp1);
                                    //Log.d("HHHHH",temp1.getQ101());
                                    q1o2.putExtra("Household", thisHouse);
                                    startActivity(q1o2);


                                }
                            });

                        } else {
                            final Individual temp1 = individual;
                            if(individual.getIndvBloodDraw() != null || individual.getPrntlConsentBloodDraw() != null) {
                                Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                                btn.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
                                Info = "HIV Questionnaire, Blood Collection  Done";
                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //**Replace This house with 1 individual
                                        Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                        q1o2.putExtra("Personroster", temp);
                                        q1o2.putExtra("Individual", temp1);
                                        //Log.d("HHHHH",temp1.getQ101());
                                        q1o2.putExtra("Household", thisHouse);
                                        startActivity(q1o2);


                                    }
                                });
                            }

                        }


                    }


                }

                //***************************************************************************COMBINED
                //***************************************************************************COMBINED
                //***************************************************************************COMBINED

                else if (s.getStatusCode().equals("2")  && thisHouse.getHIVTB40().equals("1")) {


                    if ((Integer.parseInt(r.get(o).getP04YY()) < 15 )) {

                        /**
                         * FOR UNDER 15 AND OVER 64
                         *
                         * rapid - blood
                         */

                        if (r.get(o).getRapidResults() == null || r.get(o).getRapidResults().equals("null") || r.get(o).getRapidResults().equals("")) {

                            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_filter_center_focus_blue_24dp);
                            btn.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);


                            int mm = 0;
                            int yy = Integer.parseInt(r.get(o).getP04YY());
                            final PersonRoster person1 = r.get(o);
                            if (r.get(o).getP04MM() == null) {
                            } else {
                                mm = Integer.parseInt(r.get(o).getP04MM());
                                int wks = Integer.parseInt(r.get(o).getP04WKS());
                            }


                            if ((yy > 64  && r.get(o).getP06().equals("3") && Integer.valueOf(r.get(o).getP07()) <14 )) {
                                //nkuku
                                //Log.d("18 MONTHS TO 14 YEARS", r.get(o).getP01() + "  " + r.get(o).getP04YY() +"/"+ r.get(o).getP04MM()+"/"+r.get(o).getP04WKS());


                                Info = "Pending Blood Collection & Rapid";
                                Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                btn.setCompoundDrawablesWithIntrinsicBounds(d1, null, null, null);
                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //**Replace This house with 1 individual

                                        ////replaced HIVConsentOver64
                                        Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                        q1o2.putExtra("Personroster", person1);
                                        startActivity(q1o2);
                                    }


                                });

                            }




                            if (yy < 15) {
                                //Less than  15
                                if (yy >= 1 && mm >= 6) {
                                    Info = "Pending Blood Collection & Rapid";
                                    Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                    btn.setCompoundDrawablesWithIntrinsicBounds(d1, null, null, null);
                                    btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

//
//                                            Intent b = new Intent(started_household.this, Barcode.class);
//                                            b.putExtra("Personroster", person1);
//                                            startActivity(b);

                                            Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                            q1o2.putExtra("Personroster", person1);
                                            startActivity(q1o2);
                                        }
                                    });

                                } else {
                                    if (yy == 0 && mm <= 2) {
                                        btn.setEnabled(false);
                                        Info = "Under 6 Weeks, No test required";
                                        Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                        btn.setCompoundDrawablesWithIntrinsicBounds(d1, null, null, null);
//
//                                        Intent b = new Intent(started_household.this, Barcode.class);
//                                        b.putExtra("Personroster", person1);
//                                        startActivity(b);

                                        Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                        q1o2.putExtra("Personroster", person1);
                                        //startActivity(q1o2);

                                    } else {
                                        Info = "Pending Blood Collection";
                                        Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                        btn.setCompoundDrawablesWithIntrinsicBounds(d1, null, null, null);
                                        btn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //**Replace This house with 1 individual
//
//                                                Intent b = new Intent(started_household.this, Barcode.class);
//                                                b.putExtra("Personroster", person1);
//                                                startActivity(b);

                                                Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                                q1o2.putExtra("Personroster", person1);
                                                startActivity(q1o2);


                                            }
                                        });

                                    }
                                }

                            }


                        } else {
                            Log.d("Over 15", r.get(o).getP01() + "  " + r.get(o).getP04YY() + "/" + r.get(o).getP04MM() + "/" + r.get(o).getP04WKS());
                            if (r.get(o).getRapidResults().equals("4")) {
                                Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_library_books_blue_24dp);
                                btn.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
                                //btn.setEnabled(false);
                                Info = "Has Documentation";

                            } else {
                                final PersonRoster person1 = r.get(o);
                                if (r.get(o).getRapidDate() != null || r.get(o).getBloodDraw() != null || r.get(o).getChPrntlConsentBloodDraw() != null ) {
                                    Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                                    btn.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
                                    Info = "Blood collection Done";
                                    btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            //**Replace This house with 1 individual
//
//                                                Intent b = new Intent(started_household.this, Barcode.class);
//                                                b.putExtra("Personroster", person1);
//                                                startActivity(b);

                                            Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                            q1o2.putExtra("Personroster", person1);
                                            startActivity(q1o2);


                                        }
                                    });

                                }
                            }
                        }

                    }
                    else {
                        //15 years and over****************************************************************************************************************

                        List<Individual> Ind = myDB.getdataIndivisual(thisHouse);
                        Individual individual = null;
                        for (Individual ii : Ind) {


                            if (ii.getSRNO() == r.get(o).getSRNO()) {
                                individual = ii;
                            }
                        }
                        if (individual == null || individual.getIndRapidResults() == null) {
                            individual = new Individual();
                            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_person_black_24dp);
                            btn.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);

                            individual = new Individual();
                            int yy = Integer.parseInt(r.get(o).getP04YY());

//|| yy > //64 && r.get(o).getP06().equals("2")
                            if ((yy > 64 && r.get(o).getP06().equals("3") && Integer.valueOf(r.get(o).getP07()) > 14)
                                    || (yy > 64 && r.get(o).getP06().equals("1") )) {
                                Info = "Pending Questionnaire  TB Only and X-Ray, Blood collection";
                                if (r.get(o).getBloodDraw() != null  || individual.getQ1114() != null ) {
                                    Drawable dd = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                                    btn.setCompoundDrawablesWithIntrinsicBounds(dd, null, null, null);
                                    Info = "Questionnaire, Blood collection Done";
                                }
                            } else {
                                if ((r.get(o).getP07() != null) && (!r.get(o).getP07().trim().equals(""))) {
                                    int dd = Integer.parseInt(r.get(o).getP07());
                                    if (dd <= 13 && yy < 65) {
                                        Info = "Pending Questionnaire  HIV Only and Blood Collection";

                                        if (individual.getIndvBloodDraw() != null  || individual.getQ1109() != null )
                                        {
                                            Drawable dd1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                                            btn.setCompoundDrawablesWithIntrinsicBounds(dd1, null, null, null);
                                            Info = "Questionnaire, Blood collection Done";
                                        }

                                    } else {

                                        // int dd = Integer.parseInt(r.get(o).getP07());
                                        if (dd <= 13 && yy >= 65) {
                                            Info = "Pending  Blood Collection";
                                            if (r.get(o).getBloodDraw() != null   )
                                            {
                                                Drawable dd1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                                                btn.setCompoundDrawablesWithIntrinsicBounds(dd1, null, null, null);
                                                Info = " Blood collection Done";
                                            }
                                        } else {

                                            // int dd = Integer.parseInt(r.get(o).getP07());
                                            if (dd >= 14) {
                                                Info = "Pending Questionnaire HIV&TB, X-Ray and Blood Collection";
                                                if (individual.getIndvBloodDraw() != null  || individual.getQ1114() != null )
                                                {
                                                    Drawable dd1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                                                    btn.setCompoundDrawablesWithIntrinsicBounds(dd1, null, null, null);
                                                    Info = "Questionnaire, Blood collection Done";
                                                }
                                            }
                                        }
                                    }

                                } else {

                                    if (r.get(o).getP06().equals("2")) {
                                        Info = "Pending Questionnaire TB, X-Ray";
                                        if (  individual.getQ1114() != null )
                                        {
                                            Drawable dd1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                                            btn.setCompoundDrawablesWithIntrinsicBounds(dd1, null, null, null);
                                            Info = "Questionnaire Done";
                                        }
                                    } else {

                                        Info = "Pending Questionnaire HIV&TB, X-Ray and Blood Collection";
                                        if (individual.getIndvBloodDraw() != null  || individual.getQ1114() != null )
                                        {
                                            Drawable dd1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                                            btn.setCompoundDrawablesWithIntrinsicBounds(dd1, null, null, null);
                                            Info = "Questionnaire, Blood collection Done";
                                        }
                                    }
                                }
                            }


                            individual.setSRNO(r.get(o).getSRNO());
                            individual.setAssignmentID(r.get(o).getAssignmentID());
                            individual.setBatch(r.get(o).getBatch());

                            if (myDB.checkIndividual(individual)) {

                            } else {
                                //Insert
                                myDB.insertIndividual(individual);

                            }


                            List<HouseHold> h = myDB.getHouseHold(myDB.getReadableDatabase(), "", individual.getAssignmentID());
                            for (HouseHold hhh : h) {
                                if (hhh.getAssignment_ID().equals(individual.getAssignmentID()) && hhh.getBatchNumber().equals(individual.getBatch()) && hhh.getIsHIVTB40().equals("True")) {
                                    if (thisHouse.getHIVTB40().equals("True")) {
                                        Info = Info + ", Blood Collection ";
                                    }
                                }
                            }


                            final Individual temp1 = individual;
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //**Replace This house with 1 individual

//                                    Intent b = new Intent(started_household.this, Barcode.class);
//                                    b.putExtra("Personroster", temp);
//                                    startActivity(b);

                                    Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                    q1o2.putExtra("Personroster", temp);
                                    q1o2.putExtra("Individual", temp1);
                                    //Log.d("HHHHH",temp1.getQ101());
                                    q1o2.putExtra("Household", thisHouse);

                                    startActivity(q1o2);


                                }
                            });

                        } else
                            {
                            final Individual temp1 = individual;
                            if (individual.getIndvBloodDraw() != null || individual.getIndvQuestionnairePConsent15_17() != null || individual.getIndvQuestionnaireConsent() != null )
                            {
                                Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                                btn.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
                                Info = "Questionnaire, Blood collection Done";

                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //**Replace This house with 1 individual

//                                    Intent b = new Intent(started_household.this, Barcode.class);
//                                    b.putExtra("Personroster", temp);
//                                    startActivity(b);

                                        Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                        q1o2.putExtra("Personroster", temp);
                                        q1o2.putExtra("Individual", temp1);
                                        //Log.d("HHHHH",temp1.getQ101());
                                        q1o2.putExtra("Household", thisHouse);

                                        startActivity(q1o2);


                                    }
                                });
                            }
                        }

                    }


                }
                //combined not part of 40 and TB only
                //combined not part of 40 and TB only
                //combined not part of 40 and TB only
                //combined not part of 40 and TB only

                else if (s.getStatusCode().equals("3") || (s.getStatusCode().equals("2")  && thisHouse.getHIVTB40().equals("0"))) {
                    //TB
                    //************************************HIV


                    if (Integer.parseInt(r.get(o).getP04YY()) < 15) {
                        int mm = 0;
                        int yy = Integer.parseInt(r.get(o).getP04YY());


                        if (yy < 15) {
                            //Less than  15

                            Info = " Not eligible";
                            Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                            btn.setCompoundDrawablesWithIntrinsicBounds(d1, null, null, null);
                            btn.setBackgroundColor(Color.green(1));
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

                        }

                    } else {

//
//

                        //15 years and over

                        List<Individual> Ind = myDB.getdataIndivisual(thisHouse);
                        Individual individual = null;
                        for (Individual ii : Ind) {
                            if (ii.getSRNO() == r.get(o).getSRNO()) {
                                individual = ii;

                            }
                        }


//                        List<PersonRoster> hous = myDB.getdataHhP(thisHouse);
//                        HouseHold household = null;
//                        for (Individual ii : Ind)
//                        {
//                            if (ii.getSRNO() == r.get(o).getSRNO() ) {
//                                individual = ii;
//                            }
//
//                        }

                        if (individual == null) {
                            individual = new Individual();
                            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_person_black_24dp);
                            btn.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);


//                            individual = new Individual();
                            Info = "Pending Questionnaire TB and X-Ray";

                            individual.setSRNO(r.get(o).getSRNO());
                            individual.setAssignmentID(r.get(o).getAssignmentID());
                            individual.setBatch(r.get(o).getBatch());


                            if (myDB.checkIndividual(individual)) {

                            } else {
                                //Insert
                                myDB.insertIndividual(individual);

                            }

                            List<HouseHold> h = myDB.getHouseHold(myDB.getReadableDatabase(), "", individual.getAssignmentID());
                            for (HouseHold hhh : h) {
                                if (hhh.getAssignment_ID().equals(individual.getAssignmentID()) && hhh.getBatchNumber().equals(individual.getBatch()) && hhh.getIsHIVTB40().equals("True")) {
                                    if (thisHouse.getHIVTB40().equals("1")) {
                                        Info = Info + ", ";
                                    }
                                }
                            }


                            final Individual temp1 = individual;
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //**Replace This house with 1 individual

//                                    Intent b = new Intent(started_household.this, Barcode.class);
//                                    b.putExtra("Personroster", temp);
//                                    startActivity(b);

                                    Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                    q1o2.putExtra("Personroster", temp);
                                    q1o2.putExtra("Individual", temp1);
                                    //Log.d("HHHHH",temp1.getQ101());
                                    q1o2.putExtra("Household", thisHouse);

                                    startActivity(q1o2);


                                }
                            });

                        } else {

                            if(individual.getQ1114() !=null || individual.getIndvQuestionnaireConsent() != null){
                                Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                                btn.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
                            }


                            Info = "Pending Questionnaire TB and X-Ray";

                            final Individual temp1 = individual;
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //**Replace This house with 1 individual

//                                    Intent b = new Intent(started_household.this, Barcode.class);
//                                    b.putExtra("Personroster", temp);
//                                    startActivity(b);

                                    Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                    q1o2.putExtra("Personroster", temp);
                                    q1o2.putExtra("Individual", temp1);
                                    //Log.d("HHHHH",temp1.getQ101());
                                    q1o2.putExtra("Household", thisHouse);

                                    startActivity(q1o2);


                                }
                            });


                        }

                    }



                }



                btn.setText("SRNO: "+srno + " - " + r.get(o).getP01()+" : "+ p + " "+Info);
                btn.setGravity(Gravity.CENTER_VERTICAL);
                btn.setGravity(Gravity.START);
                /*check the visit results of the individual and change the icon */
                List<Individual> Ind1 = myDB.getdataIndivisual(thisHouse);
                Individual indiv = null;
                for (Individual ii : Ind1) {
                    if (ii.getSRNO() == r.get(position).getSRNO()) {
                        indiv = ii;
                        break;
                    }
                }

                if(indiv!=null) {
                    if (indiv.getVISIT1() != null && indiv.getVISIT2() == null && indiv.getVISIT3() == null) {
                        if (indiv.getVISIT1().equals("1")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Completed");
                        } else if (indiv.getVISIT1().equals("2")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Partially Completed");
                        } else if (indiv.getVISIT1().equals("3")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Present but not Available");
                        } else if (indiv.getVISIT1().equals("4")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Present but not Available");
                        } else if (indiv.getVISIT1().equals("5")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Postponed");
                        } else if (indiv.getVISIT1().equals("6")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Other");
                        }
                    } else if (indiv.getVISIT1() != null && indiv.getVISIT2() != null && indiv.getVISIT3() == null) {
                        if (indiv.getVISIT2().equals("1")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Completed");
                        } else if (indiv.getVISIT2().equals("2")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Partially Completed");
                        } else if (indiv.getVISIT2().equals("3")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Present but not Available");
                        } else if (indiv.getVISIT2().equals("4")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Present but not Available");
                        } else if (indiv.getVISIT2().equals("5")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Postponed");
                        } else if (indiv.getVISIT2().equals("6")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Other");
                        }
                    } else if (indiv.getVISIT1() != null && indiv.getVISIT2() != null && indiv.getVISIT3() != null) {
                        if (indiv.getVISIT3().equals("1")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Completed");
                        } else if (indiv.getVISIT3().equals("2")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Partially Completed");
                        } else if (indiv.getVISIT3().equals("3")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Present but not Available");
                        } else if (indiv.getVISIT3().equals("4")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Present but not Available");
                        } else if (indiv.getVISIT3().equals("5")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Postponed");
                        } else if (indiv.getVISIT3().equals("6")) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);
                            btn.setLayoutParams(params);
                            btn.setBackgroundResource(R.drawable.partiallycomplete_btn_bg);
                            btn.setTextColor(Color.WHITE);
                            Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
                            btn.setTypeface(font);
                            String txt = btn.getText().toString();
                            btn.setText(txt + " - Status: Other");
                        }
                    }
                }


                ll.addView(btn);
            }
        }else {
            TextView t = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.setMargins(10,10,10,10);
            t.setLayoutParams(params);
            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_error_red_24dp);
            t.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
            t.setText("This household has no members, Tap on Update Household to Add/Update members");
            ll.addView(t);
        }

        Log.d("Sample",s.getStatusCode());
        Log.d("Total ", String.valueOf(r.size()));
        //##########################################POPULATE COMBINED

//        if(s.getStatusCode().equals("2"))
//        {
//            //show HIV PLUS TB COMBINED
//            LinearLayout tbH = findViewById(R.id.tbHead);
//            tbH.setVisibility(View.VISIBLE);
//
//            LinearLayout hivH = findViewById(R.id.hivHead);
//            hivH.setVisibility(View.VISIBLE);
//
//            //populate Roster
//            List<Individual> Ind = myDB.getdataIndivisual(thisHouse);
//            List<Button> btnIndividuals = new ArrayList<>();
//            Log.d("Total ", String.valueOf(r.size()));
//
//            if(Ind.size() !=0)
//            {
//                PersonRoster tmp= null;
//                for(int o=0;o<Ind.size();o++)
//                {
//                    Button btn = new Button(this);
//                    String  name="";
//
//                    for (PersonRoster personRoster:r) {
//                        if(personRoster.getSRNO()==Ind.get(o).getSRNO()){
//                            name=personRoster.getP01();
//                            tmp = personRoster;
//                            Log.d("Person : ",tmp.getP01());
//                            break;
//                        }
//                    }
//
//                    btn.setText("Person SRNO: "+ Ind.get(o).getSRNO() + " - " + name +" ");
//                    btn.setGravity(Gravity.START);
//                    Log.d("Here",tmp.getP19());
//                    //if(tmp.getP17().equals("1")){hiv.addView(btn);} //HIV
//                    //if(tmp.getP18().equals("1")){tb.addView(btn);} //TB
//                    if(tmp.getP19().equals("1")){tb.addView(btn);hiv.addView(btn);} //HIV/TB
//
//
//                }
//            }else{
//                Log.d("Here","Failed");
//            }
//
//
//
//
//        }
//        //##################################POPULATE HIV
//        else if(s.getStatusCode().equals("1")){
//            //HIV ONLY
//            hiv = findViewById(R.id.hiv);
//            hiv.setVisibility(View.VISIBLE);
//            tb = findViewById(R.id.tb);
//            tb.setVisibility(View.INVISIBLE);
//
//        }
//        //#####################################POPULATE TB
//        else if(s.getStatusCode().equals("3")){
//            //TB ONLY
//            hiv = findViewById(R.id.hiv);
//            hiv.setVisibility(View.INVISIBLE);
//            tb = findViewById(R.id.tb);
//            tb.setVisibility(View.VISIBLE);
//
//        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(started_household.this);

        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                final CharSequence[] list = new String[2];
                list[0] = "Person Roster";
                list[1] = "House Hold";


                builder.setTitle("Select the Section to Update");
                builder.setIcon(R.drawable.ic_edit_blue_16dp);

                //builder.setIcon(R.drawable.ic_person_black_24dp);
                builder.setSingleChoiceItems(
                        list, // Items list
                        -1, // Index of checked item (-1 = no selection)
                        new DialogInterface.OnClickListener() // Item click listener
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Get the alert dialog selected item's text
                                if(i==0){
                                    //Launch Person Roster

                                    Intent intent = new Intent(started_household.this,MainActivity.class);
                                    intent.putExtra("Household",thisHouse);
                                    startActivity(intent);




                                }else if(i==1){
                                    //Launch Household

                                    Intent intent = new Intent(started_household.this,H01.class);
                                    intent.putExtra("Household",thisHouse);
                                    startActivity(intent);




                                }


                            }
                        });


                AlertDialog ad = builder.show();

                //SET DIVIDER
                ListView listView = ad.getListView();
                listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                listView.setDividerHeight(3);


                //OK Button layout
                final Button positiveButton = ad.getButton(AlertDialog.BUTTON_POSITIVE);
                LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                positiveButtonLL.width=ViewGroup.LayoutParams.MATCH_PARENT;
                positiveButton.setTextColor(Color.WHITE);
                positiveButton.setBackgroundColor(Color.parseColor("#3FC0FF"));
                positiveButton.setLayoutParams(positiveButtonLL);



            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(started_household.this,Dashboard.class);
        intent.putExtra("tbNumber", "1");
        startActivity(intent);
    }
}
