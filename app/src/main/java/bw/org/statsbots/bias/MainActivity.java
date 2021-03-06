package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements Serializable{
    
    public transient  EditText[] hhArray;//Hold list of HouseHold members
    public EditText[] hhArrayTemp;//Temp array to reorder the Household members upon removal of one member
    public Button[] removeArray;//Remove button
    public Button[] removeArrayTemp;//Temp array to reorder the remove buttons
    public int HeadofHouse;
    int counter = 0;
    private DatabaseHelper myDB;


    public HouseHold thisHouse;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Drawable deleteIcon=this.getResources(). getDrawable( R.drawable.ic_delete_forever_black_24dp);
        hhArray = new EditText[30]; //Max number of house hold members
        removeArray = new Button[30];
        removeArrayTemp = new Button[30];
        hhArrayTemp = new EditText[30];

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getWritableDatabase());


        //***************************Read Roster from Database and load it into Object thisHouse
        //List<PersonRoster> list = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());


        List<PersonRoster> list = myDB.getPersonP01(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());


        final int memberExist[]= new int[1];//tracker of exist roster

        if(list.size()>0)
        {
            PersonRoster[] pp = new PersonRoster[list.size()];
            pp = list.toArray(pp);
            thisHouse.setHouseHoldeMembers(pp);
            memberExist[0] = 1;

        }


        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.memberlist);
        linearLayout.setPadding(40,2,2,2);

        if(thisHouse.getPersons() !=null){

        Log.d("Size",thisHouse.getPersons().length+"");
        for(int ii = 0;ii<thisHouse.getPersons().length;ii++)
        {
            if(thisHouse.getPersons()[ii] != null){
                final EditText personName = new EditText(MainActivity.this);
                // Create a border programmatically
                GradientDrawable shape = new GradientDrawable();
                int myColor = getResources().getColor(R.color.colorPrimaryDark);

                //shape.setColor(myColor);
                shape.setStroke(2, myColor);
                shape.setCornerRadius(5);


                //Assign the created border to EditText widget
                personName.setBackground(shape);
                personName.setPadding(25,25,25,25);


                if (counter == 0) {
                    personName.setHint("Head of House");
                } else {
                    personName.setHint("Household Member " + counter);
                }
                personName.setText(thisHouse.getPersons()[ii].getP01());
                personName.setId(counter);
                personName.setSingleLine();


                Display display = getWindowManager().getDefaultDisplay();
                int width = display.getWidth();
                if (width > 700) {
                    //personName.setWidth(700);
                    personName.setWidth(width);
                } else {
                    personName.setWidth(width);
                }

                //store edittext personName in hhArray
                hhArray[counter] = personName;
                //remove person button
                final Button btnPremove = new Button(MainActivity.this);
                btnPremove.setId(counter);
                btnPremove.setText("Remove " + thisHouse.getPersons()[ii].getP01());
                final int PersonNumber = ii;
                btnPremove.setTextColor(Color.RED);
                btnPremove.setCompoundDrawablesWithIntrinsicBounds(deleteIcon, null, null, null);
                btnPremove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**
                         * Throws null pointer exception at some point ----- fix
                         */
                        AlertDialog.Builder adBuilder = new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Remove Individual")
                                .setMessage("Are you sure you want to remove "+thisHouse.getPersons()[PersonNumber].getP01()+" from this household? All data associated with " + thisHouse.getPersons()[PersonNumber].getP01() + " will be lost.")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        ((ViewManager)hhArray[btnPremove.getId()].getParent()).removeView(hhArray[btnPremove.getId()]);
                                        ((ViewManager)removeArray[btnPremove.getId()].getParent()).removeView(removeArray[btnPremove.getId()]);


                                        //set hhArray and removeArray index to null
                                        hhArray[counter]=null;
                                        removeArray[counter]=null;

                                        if(counter==1){
                                            counter=0;
                                        }
                                        else{

                                            //make sure to clear the already entered data about the individual and reorder everything
                                            //Re shuffle the arrays and fill the missing index
                                            int t=0;
                                            for (int o = 0; o<hhArray.length;o++)
                                            {
                                                if(hhArray[o]==null)
                                                {
                                                    continue;
                                                }else {
                                                    hhArrayTemp[o]=hhArray[o];
                                                    t+=1;
                                                }
                                            }

                                            int t1=0;
                                            for (int o = 0; o<removeArray.length;o++)
                                            {
                                                if(removeArray[o]==null)
                                                {
                                                    continue;
                                                }
                                                else {
                                                    removeArrayTemp[t1]=removeArray[o];
                                                    t1+=1;
                                                }
                                            }

                                            //copy the temp to hhArray

                                            hhArray=hhArrayTemp.clone();
                                            removeArray = removeArrayTemp.clone();
                                            counter-=1;


                                            //*******************Remove the prson from database as well


                                            setTitle("P01 Total Persons " + counter);
                                            //add items to the lenearlayout afresh;
                                            //linearLayout.removeAllViewsInLayout();



                                        }
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                        //call show() to build and show the AlertDialog.
                        AlertDialog ad = adBuilder.show();



                    }
                });

                btnPremove.setEnabled(false);
                /**
                 * Set onKeyListener to text box to update remove person button tex
                 */
                personName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(s.toString().length() == 0)
                        {
                            btnPremove.setText("Remove");
                        }
                        else{
                            btnPremove.setText("Remove "+s);
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                linearLayout.addView(hhArray[counter]);
                hhArray[counter].requestFocus();

                personName.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(5, 10, 5, 10);
                personName.setLayoutParams(params);

                removeArray[counter] = btnPremove;

                linearLayout.addView(removeArray[counter]);
                btnPremove.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;


                counter++;
                setTitle("P01 Total Persons " + counter);

            }

        }
        }

        final int[] totalPersons=new int[1];
        /**
         * Button to add household member
         *
         */
        final FloatingActionButton fb = (FloatingActionButton)findViewById(R.id.floatAdd);
        fb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            if((counter>0 && hhArray[counter-1].getText().length()==0) || (counter>0 && hhArray[counter-1].getText().length()==0))
            {
                //do nothing wait for the enumerator to enter HH Head
            }
            else
            {
                //Set the name of Person to be removed
                if (counter == 0)
                {

                }
                else {
                    removeArray[counter - 1].setText("Remove " + hhArray[counter - 1].getText());
                }


                Display display = getWindowManager().getDefaultDisplay();
                int width = display.getWidth();

                //Textbox to enter hhmember names
                EditText personName = new EditText(MainActivity.this);

                // Create a border programmatically
                GradientDrawable shape = new GradientDrawable();
                int myColor = getResources().getColor(R.color.colorPrimaryDark);

                //shape.setColor(myColor);
                shape.setStroke(2, myColor);
                shape.setCornerRadius(5);


                //Assign the created border to EditText widget
                personName.setBackground(shape);
                personName.setPadding(25,25,25,25);


                if (counter == 0) {
                    personName.setHint("Head of House");
                } else {
                    personName.setHint("Household Member " + (counter + 1));
                }

                personName.setId(counter);
                personName.setSingleLine();

                if (width > 700) {
                    //personName.setWidth(700);
                    personName.setWidth(width);
                }else{
                    personName.setWidth(width);
                }

                //store edittext personName in hhArray
                hhArray[counter] = personName;

                //remove person button

                final Button btnPremove = new Button(MainActivity.this);
                btnPremove.setId(counter);
                btnPremove.setText("Remove");
                btnPremove.setTextColor(Color.RED);
                btnPremove.setCompoundDrawablesWithIntrinsicBounds(deleteIcon, null, null, null);
                btnPremove.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        /**
                         * Throws null pointer exception at some point ----- fix
                         */
                        //del[0]=btnPremove.getId();

                        try{

                            if(btnPremove.getId()==totalPersons[0]-1) {
                                Log.d("********************", btnPremove.getId() + " === ");
                                ((ViewManager) hhArray[btnPremove.getId()].getParent()).removeView(hhArray[btnPremove.getId()]);
                                ((ViewManager) removeArray[btnPremove.getId()].getParent()).removeView(removeArray[btnPremove.getId()]);

                                if (counter == 1) {
                                    Log.d("Counter ===== ", counter + "");
                                    counter = 0;
                                } else {

                                    //make sure to clear the already entered data about the individual and reorder everything
                                    //Re shuffle the arrays and fill the missing index
                                    String curposition = "50";
                                    int t = 0;
                                    int y = 100;
                                    for (int i = 0; i < hhArray.length; i++) {
                                        if (i == btnPremove.getId()) {
                                            if (i == btnPremove.getId()) {
                                                hhArrayTemp[i] = null;
                                                continue;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            EditText tempTxt = hhArray[i];
                                            //tempTxt.setId(t);
                                            hhArrayTemp[t] = tempTxt;
                                            t += 1;
                                        }
                                    }


                                    int t1 = 0;
                                    for (int i = 0; i < removeArray.length; i++) {
                                        if (i == btnPremove.getId()) {

                                            if (i == btnPremove.getId()) {
                                                removeArrayTemp[i] = null;
                                                continue;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            Button tmpBtn = removeArray[i];
                                            //tmpBtn.setId(t1);
                                            removeArrayTemp[t1] = tmpBtn;
                                            t1 += 1;
                                        }
                                    }

                                    //copy the temp to hhArray
                                    hhArray = hhArrayTemp;
                                    removeArray = removeArrayTemp;
                                    counter -= 1;
                                    setTitle("P01 Total Persons " + counter);
                                    Log.d("Last counter value", counter + "");
                                    totalPersons[0]=counter;

                                }
                            }else{
                                //**************
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("P01 : Person Listing");
                                builder.setIcon(R.drawable.ic_warning_orange_24dp);

                                builder.setMessage("You can only remove from bottom upwards. Please make sure to ask P01 question properly to get accurate data.");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });

                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);

                                AlertDialog alertDialog = builder.show();
                                final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                                LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                                positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                                positiveButton.setTextColor(Color.WHITE);
                                positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                                positiveButton.setLayoutParams(positiveButtonLL);


                            }
                        }catch (Exception ff){
                                ff.printStackTrace();
                        }


                    }
                });

                /**
                 * Set onKeyListener to text box to update remove person button tex
                 */
                personName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(s.toString().length() == 0)
                        {
                            btnPremove.setText("Remove");
                        }
                        else{
                            btnPremove.setText("Remove "+s);
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                linearLayout.addView(hhArray[counter]);
                hhArray[counter].requestFocus();

                personName.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(5, 10, 5, 10);
                personName.setLayoutParams(params);

                removeArray[counter] = btnPremove;

                linearLayout.addView(removeArray[counter]);
                btnPremove.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;


                counter++;
                setTitle("P01 Total Persons " + counter);
                totalPersons[0]=counter;



            }

            }
        });


        /**
         * Button to complete listing Individuals
         */


        final FloatingActionButton fbDone = (FloatingActionButton)findViewById(R.id.floatNext);
        try {
            fbDone.setOnClickListener(new View.OnClickListener() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                @Override
                public void onClick(View v) {
                    int Head = 0;

                    if (hhArray[0] == null) {
                        AlertDialog.Builder builderErr = new AlertDialog.Builder(MainActivity.this);
                        builderErr.setTitle("P01 Error");
                        builderErr.setIcon(R.drawable.ic_warning_orange_24dp);
                        builderErr.setMessage("Persons roster requires atleast 1 person");
                        builderErr.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Do nothing only when the Head of House is selected we proceed.

                            }
                        });

                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);


                        AlertDialog alertDialog = builderErr.show();
                        final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                        positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        positiveButton.setTextColor(Color.WHITE);
                        positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                        positiveButton.setLayoutParams(positiveButtonLL);

                    } else {
                        //Complete the list entry\
                        int c = 0;
                        for (EditText ed : hhArray) {
                            if (ed != null) {
                                if (!ed.getText().toString().isEmpty()) {
                                    c++;
                                }
                            }
                        }

                        counter =0;
                        final CharSequence[] list = new String[c];
                        for (EditText ed : hhArray) {
                            if (ed != null) {

                                list[counter] = (counter + 1) + " - " + ed.getText().toString();
                                if (memberExist[0] == 1) {

                                    if (counter == thisHouse.getPersons().length) {
                                        if (thisHouse.getPersons() != null) {
                                            if (counter < thisHouse.getPersons().length){
                                                    if (thisHouse.getPersons()[counter].getP02() != null) {
                                                        {
                                                            if (thisHouse.getPersons()[counter].getP02().trim() != "") {
                                                                if (thisHouse.getPersons()[counter].getP02().equals("00")) {
                                                                    Head = counter;
                                                                }
                                                            }
                                                        }

                                                }
                                            }
                                        }
                                    } else {

                                        if (thisHouse.getPersons() != null) {
                                            if (counter == thisHouse.getPersons().length && thisHouse.getPersons().length != 0) {
                                                break;
                                            } else {
                                                if (counter < thisHouse.getPersons().length){

                                                        if (thisHouse.getPersons()[counter].getP02() != null) {
                                                            if (thisHouse.getPersons()[counter].getP02().trim() != "") {
                                                                if (thisHouse.getPersons()[counter].getP02().equals("00")) {
                                                                    Head = counter;
                                                                }
                                                            }
                                                        }


                                                }
                                            }

                                        }

                                    }


                                }
                                counter++;
                            }

                        }


                        builder.setTitle("Select/Confirm head of Household");
                        int selected = Head; // or whatever you want

                        builder.setIcon(R.drawable.ic_person_black_24dp);

                        for(CharSequence fff:list){
                        }
                        builder.setSingleChoiceItems(list, selected, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {

                                //Start New House Class Activity
                                String[] plist = new String[counter];

                                for (int i = 0; i < counter; i++) {
                                    if(hhArray[i]!=null){
                                        plist[i] = hhArray[i].getText().toString();
                                    }
                                }

                                //Set the Head of house to index of selected array
                                //Log.d("Size ",plist.length+"");
                                HeadofHouse = which;

                                thisHouse.setHead(HeadofHouse);

                                /**
                                 * DATA FROM THE LISTING
                                 */
                                PersonRoster[] p = new PersonRoster[plist.length];
                                Log.d("Array : ",plist.length+"");
                                for (int i = 0; i < plist.length; i++){
                                    p[i] = new PersonRoster();
                                    p[i].setLineNumber(i);
                                    p[i].setP01(plist[i]);
                                    if (i == which){
                                        p[i].setP02("00");
                                    }
                                    //Log.d("Person ", p[i].getLineNumber() + " " + p[i].getP01());
                                }


                                thisHouse.next = String.valueOf(0);
                                thisHouse.previous = null;


                                //Save or Update Persons Roster
                                List<PersonRoster> ll = myDB.getPersonP01(thisHouse.getAssignment_ID(), thisHouse.getBatchNumber());

                                if (ll.size() > 0){


                                    List<PersonRoster> list = myDB.getPersonP01(thisHouse.getAssignment_ID(), thisHouse.getBatchNumber());
                                    thisHouse.setHouseHoldeMembers(list.toArray(thisHouse.getHouseHoldeMembers()));

                                    //Log.d("Memmbers", thisHouse.getPersons().length+"");
                                    List<PersonRoster> kTemp = new ArrayList<>();

                                    for (int ii = 0; ii < plist.length; ii++) {
                                        if (ii > thisHouse.getPersons().length - 1) {
                                            //new members
                                            kTemp.add(p[ii]);
                                        } else {

                                            if(!thisHouse.getPersons()[ii].getP01().matches(p[ii].getP01())){
                                                try {


                                                    String enumLog = "";

                                                    Date currentTime = Calendar.getInstance().getTime();
                                                    enumLog = thisHouse.getAssignment_ID() + "," + thisHouse.getBatchNumber() + "," + thisHouse.getDWELLING_NO() + ", " + thisHouse.getHH_NO() + ", " + currentTime.toString() + "," + " Updated: Existing member from: " +thisHouse.getPersons()[ii].getP01()+ " to " + p[ii].getP01()+" : " + p[ii].getSRNO() + " ### ";

                                                    writeFileOnInternalStorage(MainActivity.this, "ActivityLog.txt", enumLog);
                                                } catch (Exception ss) {

                                                }
                                            }
                                            thisHouse.getPersons()[ii].setP01(p[ii].getP01());
                                        }

                                        //Log.d("Check Persons", ii+"======================");
                                    }

                                    /**
                                     * Copy the already existing members to arraylist
                                     */
                                    List<PersonRoster> finalList = new ArrayList<>();
                                    for (PersonRoster pp : thisHouse.getPersons()) {
                                        finalList.add(pp);

                                    }
                                    /**
                                     * Add each new Member in kTemp to finalList then Insert that person
                                     */
                                    for (PersonRoster prr : kTemp) {
                                        finalList.add(prr);
                                        //Insert this member to DB
                                        myDB.insertPerson(prr, thisHouse.getAssignment_ID(), thisHouse.getBatchNumber());
                                        try {


                                            String enumLog = "";

                                            Date currentTime = Calendar.getInstance().getTime();

                                            enumLog = thisHouse.getAssignment_ID() + "," + thisHouse.getBatchNumber() + "," + thisHouse.getDWELLING_NO() + ", " + thisHouse.getHH_NO() + ", " + currentTime.toString() + "," + " Insert: Added A new member: " + " " + prr.getSRNO() + ", " + prr.getP01() +" ### ";

                                            writeFileOnInternalStorage(MainActivity.this, "ActivityLog.txt", enumLog);
                                        } catch (Exception ss) {

                                        }

                                    }


                                    PersonRoster[] pp = new PersonRoster[finalList.size()];
                                    pp = finalList.toArray(pp);
                                    thisHouse.setHouseHoldeMembers(pp);

                                    //myDB.updateHouseholdAllColumns(myDB.getWritableDatabase(), thisHouse);

                                    MainActivity.this.finish();
                                    Intent intent = new Intent(MainActivity.this, P02.class);
                                    intent.putExtra("Household", thisHouse);
                                    startActivity(intent);


                                } else {
                                    thisHouse.setHouseHoldeMembers(p);

                                    //myDB.insertHhroster(thisHouse);


                                    for (PersonRoster pr : p) {
                                        myDB.insertPerson(pr, thisHouse.getAssignment_ID(), thisHouse.getBatchNumber());

                                        try {


                                            String enumLog = "";

                                            Date currentTime = Calendar.getInstance().getTime();
                                            enumLog = thisHouse.getAssignment_ID() + "," + thisHouse.getBatchNumber() + "," + thisHouse.getDWELLING_NO() + ", " + thisHouse.getHH_NO() + ", " + currentTime.toString() + "," + " Insert: Added A new member: " + " " + pr.getSRNO() + ", " + pr.getP01() +" ### ";

                                            writeFileOnInternalStorage(MainActivity.this, "ActivityLog.txt", enumLog);
                                        } catch (Exception ss) {

                                        }
                                    }

                                    MainActivity.this.finish();
                                    Intent intent = new Intent(MainActivity.this, P02.class);
                                    intent.putExtra("Household", thisHouse);

                                    startActivity(intent);


                                }


                            }





                        });

                        try {

                                AlertDialog   ad = builder.show();

                                //SET DIVIDER
                                ListView listView = ad.getListView();
                                listView.setDivider(new ColorDrawable(Color.parseColor("#FFB4B4B4")));
                                listView.setDividerHeight(3);


                                //OK Button layout
                                final Button positiveButton = ad.getButton(AlertDialog.BUTTON_POSITIVE);
                                LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                                positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                                positiveButton.setTextColor(Color.WHITE);
                                positiveButton.setBackgroundColor(Color.parseColor("#3FC0FF"));
                                positiveButton.setLayoutParams(positiveButtonLL);



                        } catch (Exception g) {

                        }finally {

                        }


                    }


                }
            });
        }catch (Exception ggg){
            ggg.printStackTrace();
        }



    }


    /**
     * WRITE LIST OF MEMBERS
     */
    public void writeFileOnInternalStorage(Context mcoContext,String sFileName, String sBody){
        //String root = Environment.getExternalStorageDirectory().toString();
        File file = new File(mcoContext.getFilesDir(),"BaisDataLogs");
        if(!file.exists()){
            file.mkdir();
        }

        try{
            File gpxfile = new File(file, sFileName);
            FileWriter writer = new FileWriter(gpxfile,true);
            writer.append(sBody);
            writer.append("\n\r");
            writer.flush();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }




    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

}
