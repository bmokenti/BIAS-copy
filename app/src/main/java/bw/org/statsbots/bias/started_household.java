package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class started_household extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected LibraryClass lib;
    protected TextView Started_Locality,Timestarted,tvcompleted,startedStatus,startedDwelling,HH_NO,more,commentHead, commenttxt;
    protected LinearLayout lcomment ;
    protected Button btnUpdate,btnComplete;
    protected LinearLayout tb,hiv;
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



        btnComplete=findViewById(R.id.btnComplete);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final CharSequence[] list = new String[4];
                list[0] = "1. Completed";
                list[1] = "2. Partially Completed";
                list[2] = "4. Refused";
                list[3] = "6. Other (Specify)";

                final  int FinalResult[] = new int[1];

                final AlertDialog.Builder builder1 = new AlertDialog.Builder(started_household.this);
                builder1.setTitle("Select Final Result ~ Questionnaire");

                int  i=0;


                builder1.setSingleChoiceItems(
                        list, // Items list
                        -1, // Index of checked item (-1 = no selection)
                        new DialogInterface.OnClickListener() // Item click listener
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Get the alert dialog selected item's text
                                if(i==0){
                                    //1. Completed

                                    //Intent intent = new Intent(started_household.this,MainActivity.class);
                                    //intent.putExtra("Household",thisHouse);
                                    //startActivity(intent);
                                    FinalResult[0] = 1;
                                    SQLiteDatabase db = myDB.getWritableDatabase();
                                    ContentValues hhValues = new ContentValues();
                                    hhValues.put("Interview_Status","10");
                                    hhValues.put("FINAL_RESULT",String.valueOf(FinalResult[0]));

                                    i = db.update
                                            (   "House_Hold_Assignments", // table
                                                    hhValues, // column/value
                                                    "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                                                    new String[]{ String.valueOf(thisHouse.getAssignment_ID()),String.valueOf(thisHouse.getBatchNumber()) }
                                            );

                                    db.close();

                                    if(i==1){

                                        AlertDialog.Builder builder = new AlertDialog.Builder(started_household.this);
                                        builder.setTitle("Done");
                                        builder.setIcon(R.drawable.ic_done_all_black_24dp);

                                        builder.setMessage("House hold Status has been changed to Completed");
                                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //Do nothing only when the Head of House is selected we proceed.
                                                Intent intent = new Intent(started_household.this, Dashboard.class);
                                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(started_household.this).toBundle());

                                            }
                                        });


                                        AlertDialog alertDialog =  builder.show();


                                    }else{

                                        AlertDialog.Builder builder = new AlertDialog.Builder(started_household.this);
                                        builder.setTitle("Failed");
                                        builder.setIcon(R.drawable.ic_error_red_24dp);

                                        builder.setMessage("House hold Status change has failed");
                                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //Do nothing only when the Head of House is selected we proceed.

                                            }
                                        });


                                        AlertDialog alertDialog =  builder.show();


                                    }

                                }else if(i==1){
                                    //2. Partially Completed

                                    //Intent intent = new Intent(started_household.this,H01.class);
                                    //intent.putExtra("Household",thisHouse);
                                    //startActivity(intent);
                                    FinalResult[0] = 2;
                                    SQLiteDatabase db = myDB.getWritableDatabase();
                                    ContentValues hhValues = new ContentValues();
                                    hhValues.put("Interview_Status","10");
                                    hhValues.put("FINAL_RESULT",String.valueOf(FinalResult[0]));

                                    i = db.update
                                            (   "House_Hold_Assignments", // table
                                                    hhValues, // column/value
                                                    "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                                                    new String[]{ String.valueOf(thisHouse.getAssignment_ID()),String.valueOf(thisHouse.getBatchNumber()) }
                                            );

                                    db.close();
                                    if(i==1){

                                        AlertDialog.Builder builder = new AlertDialog.Builder(started_household.this);
                                        builder.setTitle("Done");
                                        builder.setIcon(R.drawable.ic_done_all_black_24dp);

                                        builder.setMessage("House hold Status has been changed to Partially Completed");
                                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //Do nothing only when the Head of House is selected we proceed.
                                                Intent intent = new Intent(started_household.this, Dashboard.class);
                                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(started_household.this).toBundle());

                                            }
                                        });


                                        AlertDialog alertDialog =  builder.show();


                                    }else{

                                        AlertDialog.Builder builder = new AlertDialog.Builder(started_household.this);
                                        builder.setTitle("Failed");
                                        builder.setIcon(R.drawable.ic_error_red_24dp);

                                        builder.setMessage("House hold Status change has failed");
                                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //Do nothing only when the Head of House is selected we proceed.

                                            }
                                        });


                                        AlertDialog alertDialog =  builder.show();


                                    }

                                }else if(i==2){
                                    //2. Partially Completed

                                    //Intent intent = new Intent(started_household.this,H01.class);
                                    //intent.putExtra("Household",thisHouse);
                                    //startActivity(intent);
                                    FinalResult[0] = 4;
                                    SQLiteDatabase db = myDB.getWritableDatabase();
                                    ContentValues hhValues = new ContentValues();
                                    hhValues.put("Interview_Status","10");
                                    hhValues.put("FINAL_RESULT",String.valueOf(FinalResult[0]));

                                    i = db.update
                                            (   "House_Hold_Assignments", // table
                                                    hhValues, // column/value
                                                    "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                                                    new String[]{ String.valueOf(thisHouse.getAssignment_ID()),String.valueOf(thisHouse.getBatchNumber()) }
                                            );

                                    db.close();
                                    if(i==1){

                                        AlertDialog.Builder builder = new AlertDialog.Builder(started_household.this);
                                        builder.setTitle("Done");
                                        builder.setIcon(R.drawable.ic_done_all_black_24dp);

                                        builder.setMessage("House hold Status has been changed to Refused");
                                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //Do nothing only when the Head of House is selected we proceed.
                                                Intent intent = new Intent(started_household.this, Dashboard.class);
                                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(started_household.this).toBundle());

                                            }
                                        });


                                        AlertDialog alertDialog =  builder.show();


                                    }else{

                                        AlertDialog.Builder builder = new AlertDialog.Builder(started_household.this);
                                        builder.setTitle("Failed");
                                        builder.setIcon(R.drawable.ic_error_red_24dp);

                                        builder.setMessage("House hold Status change has failed");
                                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //Do nothing only when the Head of House is selected we proceed.

                                            }
                                        });


                                        AlertDialog alertDialog =  builder.show();


                                    }


                                }
                                else if(i==3){
                                    //2. Partially Completed

                                    //Intent intent = new Intent(started_household.this,H01.class);
                                    //intent.putExtra("Household",thisHouse);
                                    //startActivity(intent);
                                    FinalResult[0] = 6;

                                    final String OtherSpecify[]= new String[1];

                                    if(OtherSpecify[0]== null){

                                        AlertDialog.Builder builder = new AlertDialog.Builder(started_household.this);

                                        LayoutInflater inflater = getLayoutInflater();
                                        View dialogView = inflater.inflate(R.layout.alertdialog_custom_view,null);

                                        // Specify alert dialog is not cancelable/not ignorable
                                        builder.setCancelable(false);

                                        // Set the custom layout as alert dialog view
                                        builder.setView(dialogView);

                                        // Get the custom alert dialog view widgets reference
                                        Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                                        Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                                        final EditText et_name = (EditText) dialogView.findViewById(R.id.et_name);

                                        // Create the alert dialog
                                        final AlertDialog dialog = builder.create();

                                        // Set positive/yes button click listener
                                        btn_positive.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                // Dismiss the alert dialog
                                                dialog.cancel();
                                                String specify = et_name.getText().toString();
                                                if(specify.isEmpty()){
                                                    //stay here
                                                }else{
                                                    //save

                                                    SQLiteDatabase db = myDB.getWritableDatabase();
                                                    ContentValues hhValues = new ContentValues();
                                                    hhValues.put("Interview_Status","10");

                                                    hhValues.put("FINAL_RESULT",String.valueOf(FinalResult[0]));
                                                    hhValues.put("FINAL_OTHER",specify);

                                                    final int i = db.update
                                                            (   "House_Hold_Assignments", // table
                                                                    hhValues, // column/value
                                                                    "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                                                                    new String[]{ String.valueOf(thisHouse.getAssignment_ID()),String.valueOf(thisHouse.getBatchNumber()) }
                                                            );

                                                    db.close();
                                                    if(i==1){

                                                        AlertDialog.Builder builder = new AlertDialog.Builder(started_household.this);
                                                        builder.setTitle("Done");
                                                        builder.setIcon(R.drawable.ic_done_all_black_24dp);

                                                        builder.setMessage("House hold Status has been changed to Other");
                                                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                //Do nothing only when the Head of House is selected we proceed.
                                                                Intent intent = new Intent(started_household.this, Dashboard.class);
                                                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(started_household.this).toBundle());

                                                            }
                                                        });


                                                        AlertDialog alertDialog =  builder.show();


                                                    }else{

                                                        AlertDialog.Builder builder = new AlertDialog.Builder(started_household.this);
                                                        builder.setTitle("Failed");
                                                        builder.setIcon(R.drawable.ic_error_red_24dp);

                                                        builder.setMessage("House hold Status change has failed");
                                                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                //Do nothing only when the Head of House is selected we proceed.

                                                            }
                                                        });


                                                        AlertDialog alertDialog =  builder.show();


                                                    }








                                                }
                                            }
                                        });

                                        // Set negative/no button click listener
                                        btn_negative.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                // Dismiss/cancel the alert dialog
                                                //dialog.cancel();
                                                dialog.dismiss();

                                            }
                                        });

                                        // Display the custom alert dialog on interface
                                        dialog.show();
                                    }
                                }

                            }
                        });

                //builder.setIcon(R.drawable.ic_person_black_24dp);
                AlertDialog ad = builder1.show();

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

        if(r.size() !=0)
        {
            for(int o=0;o<r.size();o++)
            {
                Button btn = new Button(this);

                Drawable drawable = ContextCompat.getDrawable(started_household.this, R.drawable.ic_person_black_24dp);
                btn.setCompoundDrawablesWithIntrinsicBounds( drawable,null, null, null);
                final PersonRoster person = r.get(o);
                final String gender, srno;
                if(r.get(o).getP03().equals("1")){gender="Male";}else{gender="Female";}
                srno= String.valueOf(r.get(o).getSRNO());
                String p="";
                if(String.valueOf(thisHouse.getHead()).equals(r.get(o).getSRNO())){p="(Head of House)";}
                String Info="";
                //btn.setEnabled(false);
                final  PersonRoster temp = r.get(o);
                s.getStatusCode();



                if(s.getStatusCode().equals("1")){
                    //************************************HIV
                    if(Integer.parseInt(r.get(o).getP04YY()) <15 || Integer.parseInt(r.get(o).getP04YY()) > 64)
                    {

                        /**
                         * FOR UNDER 15 AND OVER 64
                         *
                         * rapid - blood
                         */

                        if(r.get(o).getU15Rapid_Results()==null || r.get(o).getU15Rapid_Results().equals("null") || r.get(o).getU15Rapid_Results().equals("") )
                        {

                            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_filter_center_focus_blue_24dp);
                            btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);



                            int mm=0;
                            int yy = Integer.parseInt(r.get(o).getP04YY());
                           final PersonRoster person1 = r.get(o);
                            if(r.get(o).getP04MM()==null){}
                            else{ mm= Integer.parseInt(r.get(o).getP04MM());
                                int wks = Integer.parseInt(r.get(o).getP04WKS());}


                            if((yy >64)){
                                //nkuku
                                //Log.d("18 MONTHS TO 14 YEARS", r.get(o).getP01() + "  " + r.get(o).getP04YY() +"/"+ r.get(o).getP04MM()+"/"+r.get(o).getP04WKS());


                                Info="Pending Blood Collection & Rapid";
                                Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                btn.setCompoundDrawablesWithIntrinsicBounds( d1,null, null, null);
                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //**Replace This house with 1 individual

                                ////replaced HIVConsentOver64
                                       Intent q1o2 = new Intent(started_household.this, HIVConsentOver64.class);
                                        q1o2.putExtra("Personroster", person1);
                                        startActivity(q1o2);


                                    }
                                });

                            }
                            if(yy<15){
                                //Less than  15
                                if(yy>=1 && mm >=6){
                                    Info="Pending Blood Collection & Rapid";
                                    Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                    btn.setCompoundDrawablesWithIntrinsicBounds( d1,null, null, null);
                                    btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent q1o2 = new Intent(started_household.this, HIVParentalConsent6wks_9y.class);
                                            q1o2.putExtra("Personroster", person1);
                                            startActivity(q1o2);
                                        }
                                    });

                                }
                                else
                                {
                                    if(yy==0 && mm <=2  ){
                                        btn.setEnabled(false);
                                        Info="Under 6 Weeks, No test required";
                                        Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                        btn.setCompoundDrawablesWithIntrinsicBounds( d1,null, null, null);


                                        Intent q1o2 = new Intent(started_household.this, HIVParentalConsent6wks_9y.class);
                                        q1o2.putExtra("Personroster", person1);
                                        startActivity(q1o2);

                                    }
                                    else {
                                        Info="Pending Blood Collection";
                                        Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                        btn.setCompoundDrawablesWithIntrinsicBounds( d1,null, null, null);
                                        btn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //**Replace This house with 1 individual
                                                Intent q1o2 = new Intent(started_household.this, HIVParentalConsent10_14yrs.class);

                                                q1o2.putExtra("Personroster", person1);
                                                startActivity(q1o2);


                                            }
                                        });

                                    }
                                }

                            }


                        }
                        else {
                            Log.d("Over 15", r.get(o).getP01() + "  " + r.get(o).getP04YY() +"/"+ r.get(o).getP04MM()+"/"+r.get(o).getP04WKS());
                            if(r.get(o).getU15Rapid_Results().equals("4"))
                            {
                                Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_library_books_blue_24dp);
                                btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);
                                //btn.setEnabled(false);
                                Info="Has Documentation";

                            }else {
                                Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                                btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);
                                Info="Rapid Test Done";
                            }
                        }

                    }else{
                        //15 years and over

                        List<Individual> Ind = myDB.getdataIndivisual(thisHouse);
                        Individual individual = null;
                        for(Individual ii : Ind){
                            if(ii.getSRNO()==r.get(o).getSRNO()){
                                individual = ii;
                            }
                        }
                        if(individual==null || individual.getIndRapidResults() == null)
                        {
                            individual = new Individual();
                            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_person_black_24dp);
                            btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);

                            individual = new Individual();
                            Info="Pending Questionnaire, Blood Collection";

                            individual.setSRNO(r.get(o).getSRNO());
                            individual.setAssignmentID(r.get(o).getAssignmentID());
                            individual.setBatch(r.get(o).getBatch());



                            List<HouseHold> h = myDB.getHouseHold(myDB.getReadableDatabase(),"",individual.getAssignmentID());
                            for (HouseHold hhh:h)
                            {
                                if(hhh.getAssignment_ID().equals(individual.getAssignmentID()) &&hhh.getBatchNumber().equals(individual.getBatch())  && hhh.getIsHIVTB40().equals("True")){
                                    if(thisHouse.getIsHIVTB40().equals("True"))
                                    {
                                        Info=Info+", Blood Collection";
                                    }
                                }
                            }



                            final Individual temp1 = individual;
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //**Replace This house with 1 individual
                                    Intent q1o2 = new Intent(started_household.this, IndQuetParentalConsent.class);
                                    q1o2.putExtra("Personroster", temp);
                                    q1o2.putExtra("Individual", temp1);
                                    //Log.d("HHHHH",temp1.getQ101());
                                    q1o2.putExtra("Household", thisHouse);
                                    startActivity(q1o2);


                                }
                            });

                        }else{

                            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                            btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);
                            Info="Questionnaire, Rapid Test Done";


                        }





                    }


                }else if(s.getStatusCode().equals("2")){//***************************************************************************COMBINDE

//************************************HIV
                    if((Integer.parseInt(r.get(o).getP04YY()) <15 && thisHouse.getHIVTB40().equals("1")))
                    {

                        /**
                         * FOR UNDER 15 AND OVER 64
                         *
                         * rapid - blood
                         */

                        if(r.get(o).getU15Rapid_Results()==null || r.get(o).getU15Rapid_Results().equals("null") || r.get(o).getU15Rapid_Results().equals("") )
                        {

                            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_filter_center_focus_blue_24dp);
                            btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);



                            int mm=0;
                            int yy = Integer.parseInt(r.get(o).getP04YY());
                            final PersonRoster person1 = r.get(o);
                            if(r.get(o).getP04MM()==null){}
                            else{ mm= Integer.parseInt(r.get(o).getP04MM());
                                int wks = Integer.parseInt(r.get(o).getP04WKS());}


//                            if((yy >64 )){
//                                //nkuku
//                                //Log.d("18 MONTHS TO 14 YEARS", r.get(o).getP01() + "  " + r.get(o).getP04YY() +"/"+ r.get(o).getP04MM()+"/"+r.get(o).getP04WKS());
//
//
//                                Info="Pending Blood Collection & Rapid";
//                                Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
//                                btn.setCompoundDrawablesWithIntrinsicBounds( d1,null, null, null);
//                                btn.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        //**Replace This house with 1 individual
//
//                                        ////replaced HIVConsentOver64
//                                        Intent q1o2 = new Intent(started_household.this, HIVParentalConsent6wks_9y.class);
//                                        q1o2.putExtra("Personroster", person1);
//                                        startActivity(q1o2);
//
//
//                                    }
//                                });
//
//                            }
                            if(yy<15){
                                //Less than  15
                                if(yy>=1 && mm >=6){
                                    Info="Pending Blood Collection & Rapid";
                                    Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                    btn.setCompoundDrawablesWithIntrinsicBounds( d1,null, null, null);
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

                                }
                                else
                                {
                                    if(yy==0 && mm <=2  ){
                                        btn.setEnabled(false);
                                        Info="Under 6 Weeks, No test required";
                                        Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                        btn.setCompoundDrawablesWithIntrinsicBounds( d1,null, null, null);
//
//                                        Intent b = new Intent(started_household.this, Barcode.class);
//                                        b.putExtra("Personroster", person1);
//                                        startActivity(b);


                                        Intent q1o2 = new Intent(started_household.this, Barcode.class);
                                        q1o2.putExtra("Personroster", person1);
                                        startActivity(q1o2);

                                    }
                                    else {
                                        Info="Pending Blood Collection";
                                        Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
                                        btn.setCompoundDrawablesWithIntrinsicBounds( d1,null, null, null);
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
                            Log.d("Over 15", r.get(o).getP01() + "  " + r.get(o).getP04YY() +"/"+ r.get(o).getP04MM()+"/"+r.get(o).getP04WKS());
                            if(r.get(o).getU15Rapid_Results().equals("4"))
                            {
                                Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_library_books_blue_24dp);
                                btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);
                                //btn.setEnabled(false);
                                Info="Has Documentation";

                            }else {
                                Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                                btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);
                                Info="Rapid Test Done";
                            }
                        }

                    }


                    else{
                        //15 years and over****************************************************************************************************************

                        List<Individual> Ind = myDB.getdataIndivisual(thisHouse);
                        Individual individual = null;
                        for(Individual ii : Ind){
                            if(ii.getSRNO()==r.get(o).getSRNO()){
                                individual = ii;
                            }
                        }
                        if(individual==null || individual.getIndRapidResults() == null)
                        {
                            individual = new Individual();
                            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_person_black_24dp);
                            btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);

                            individual = new Individual();
                            int yy = Integer.parseInt(r.get(o).getP04YY());
                            if(yy >64){
                                Info="Pending Questionnaire TB and X-Ray";
                            }
                            else
                            {
                                Info="Pending Questionnaire HIV&TB, X-Ray";
                            }


                            individual.setSRNO(r.get(o).getSRNO());
                            individual.setAssignmentID(r.get(o).getAssignmentID());
                            individual.setBatch(r.get(o).getBatch());



                            List<HouseHold> h = myDB.getHouseHold(myDB.getReadableDatabase(),"",individual.getAssignmentID());
                            for (HouseHold hhh:h)
                            {
                                if(hhh.getAssignment_ID().equals(individual.getAssignmentID()) &&hhh.getBatchNumber().equals(individual.getBatch())  && hhh.getIsHIVTB40().equals("True")){
                                    if(thisHouse.getIsHIVTB40().equals("True"))
                                    {
                                        Info=Info+", Blood Collection";
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

                        }else{

                            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                            btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);
                            Info="Questionnaire, Rapid Test Done";


                        }





                    }



                }else if(s.getStatusCode().equals("3")){
                    //TB
                        //************************************HIV

//
//                    if(Integer.parseInt(r.get(o).getP04YY()) <15 || Integer.parseInt(r.get(o).getP04YY()) > 64)
//                    {
//
//                        /**
//                         * FOR UNDER 15 AND OVER 64
//                         *
//                         * rapid - blood
//                         */
//
//                        if(r.get(o).getU15Rapid_Results()==null || r.get(o).getU15Rapid_Results().equals("null") || r.get(o).getU15Rapid_Results().equals("") )
//                        {
//
//                            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_filter_center_focus_blue_24dp);
//                            btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);
//
//
//
//                            int mm=0;
//                           int yy = Integer.parseInt(r.get(o).getP04YY());
////                            final PersonRoster person1 = r.get(o);
////                            if(r.get(o).getP04MM()==null){}
////                            else{ mm= Integer.parseInt(r.get(o).getP04MM());
////                                int wks = Integer.parseInt(r.get(o).getP04WKS());}
////
////
//                            if((yy >64)){
//                                //nkuku
//                                //Log.d("18 MONTHS TO 14 YEARS", r.get(o).getP01() + "  " + r.get(o).getP04YY() +"/"+ r.get(o).getP04MM()+"/"+r.get(o).getP04WKS());
//
//
//                                Info="Pending Blood Collection & Rapid";
//                                Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
//                                btn.setCompoundDrawablesWithIntrinsicBounds( d1,null, null, null);
//                                btn.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        //**Replace This house with 1 individual
//
//                                        ////replaced HIVConsentOver64
//                                        Intent q1o2 = new Intent(started_household.this, HIVParentalConsent6wks_9y.class);
//                                        q1o2.putExtra("Personroster", person1);
//                                        startActivity(q1o2);
//
//
//                                    }
//                                });
//
//                            }
//                            if(yy<15){
//                                //Less than  15
//                                if(yy>=1 && mm >=6){
//                                    Info="Pending Blood Collection & Rapid";
//                                    Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
//                                    btn.setCompoundDrawablesWithIntrinsicBounds( d1,null, null, null);
//                                    btn.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            Intent q1o2 = new Intent(started_household.this, HIVParentalConsent6wks_9y.class);
//                                            q1o2.putExtra("Personroster", person1);
//                                            startActivity(q1o2);
//                                        }
//                                    });
//
//                                }
//                                else
//                                {
//                                    if(yy==0 && mm <=2  ){
//                                        btn.setEnabled(false);
//                                        Info="Under 6 Weeks, No test required";
//                                        Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
//                                        btn.setCompoundDrawablesWithIntrinsicBounds( d1,null, null, null);
//
//
//                                        Intent q1o2 = new Intent(started_household.this, HIVParentalConsent6wks_9y.class);
//                                        q1o2.putExtra("Personroster", person1);
//                                        startActivity(q1o2);
//
//                                    }
//                                    else {
//                                        Info="Pending Blood Collection";
//                                        Drawable d1 = ContextCompat.getDrawable(started_household.this, R.drawable.ic_face_blue_24dp);
//                                        btn.setCompoundDrawablesWithIntrinsicBounds( d1,null, null, null);
//                                        btn.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View view) {
//                                                //**Replace This house with 1 individual
//                                                Intent q1o2 = new Intent(started_household.this, HIVParentalConsent6wks_9y.class);
//
//                                                q1o2.putExtra("Personroster", person1);
//                                                startActivity(q1o2);
//
//
//                                            }
//                                        });
//
//                                    }
//                                }
//
//                            }
//
//
//                        }
//                        else {
//                            Log.d("Over 15", r.get(o).getP01() + "  " + r.get(o).getP04YY() +"/"+ r.get(o).getP04MM()+"/"+r.get(o).getP04WKS());
//                            if(r.get(o).getU15Rapid_Results().equals("4"))
//                            {
//                                Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_library_books_blue_24dp);
//                                btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);
//                                //btn.setEnabled(false);
//                                Info="Has Documentation";
//
//                            }else {
//                                Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
//                                btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);
//                                Info="Rapid Test Done";
//                            }
//                        }
//
//                    }
//                    */
//

                        //15 years and over

                        List<Individual> Ind = myDB.getdataIndivisual(thisHouse);
                        Individual individual = null;
                        for(Individual ii : Ind){
                            if(ii.getSRNO()==r.get(o).getSRNO()){
                                individual = ii;
                            }
                        }
                        if(individual==null || individual.getIndRapidResults() == null)
                        {
                            individual = new Individual();
                            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_person_black_24dp);
                            btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);

                            individual = new Individual();
                            Info="Pending Questionnaire, X-Ray";

                            individual.setSRNO(r.get(o).getSRNO());
                            individual.setAssignmentID(r.get(o).getAssignmentID());
                            individual.setBatch(r.get(o).getBatch());



                            List<HouseHold> h = myDB.getHouseHold(myDB.getReadableDatabase(),"",individual.getAssignmentID());
                            for (HouseHold hhh:h)
                            {
                                if(hhh.getAssignment_ID().equals(individual.getAssignmentID()) &&hhh.getBatchNumber().equals(individual.getBatch())  && hhh.getIsHIVTB40().equals("True")){
                                    if(thisHouse.getIsHIVTB40().equals("True"))
                                    {
                                        Info=Info+", TB QUESTIONNIRE";
                                    }
                                }
                            }



                            final Individual temp1 = individual;
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //**Replace This house with 1 individual
                                    Intent q1o2 = new Intent(started_household.this, IndQuetParentalConsent.class);
                                    q1o2.putExtra("Personroster", temp);
                                    q1o2.putExtra("Individual", temp1);
                                    //Log.d("HHHHH",temp1.getQ101());
                                    q1o2.putExtra("Household", thisHouse);

                                    startActivity(q1o2);


                                }
                            });

                        }else{

                            Drawable d = ContextCompat.getDrawable(started_household.this, R.drawable.ic_check_completed_24dp);
                            btn.setCompoundDrawablesWithIntrinsicBounds( d,null, null, null);
                            Info="QESTIONNAIRE, TB OLNY";


                        }



                }


                btn.setText("SRNO: "+srno + " - " + r.get(o).getP01()+" "+ p + " "+Info);
                btn.setGravity(Gravity.START);




                ll.addView(btn);
            }
        }else {
            TextView t = new TextView(this);
            t.setText("This house hold has no members");
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
}
