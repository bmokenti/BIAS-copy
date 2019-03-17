package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import com.google.gson.Gson;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Dashboard extends AppCompatActivity implements Serializable, NavigationView.OnNavigationItemSelectedListener {
    public static final String MY_PREFS_NAME = "bw.org.statsbots.bias.enumerator";
    SharedPreferences.Editor editor;
    SharedPreferences preferences;

    public TabLayout tabLayout;
    PagerAdapter adapter;
    ViewPager viewPager;
    private DatabaseHelper myDB;
    private HouseHold thisHouse;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        //Intent intent = getIntent();
        //thisHouse = (HouseHold)intent.getSerializableExtra("Household");

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();



        preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final boolean userlogin_Status = preferences.getBoolean("is_logged", false);

        if(userlogin_Status==false)
        {
            editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("last_try", DateHelper.getDateTime().toString());
            editor.apply();

            Intent intentHome = new Intent(this,login.class);
            // Check if we're running on Android 5.0 or higher

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                // Apply activity transition
                startActivity(intentHome, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

            }
            else
                {
                // Swap without transition
                startActivity(intentHome);
            }
        }


        setContentView(R.layout.activity_dashboard);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Assignments"));
        tabLayout.addTab(tabLayout.newTab().setText("Started"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed"));

        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#ffffff"));

        for (int i = 0; i < tabLayout.getTabCount(); i++)
        {
            if(i==0)
            {
                //dashboard tab icon
                tabLayout.getTabAt(i).setIcon(R.drawable.ic_view_workload_24dp);


            }else if(i == 1)
            {
                //ongoing  tab icon
                tabLayout.getTabAt(i).setIcon(R.drawable.inprogress16);
            }else if(i==2)
            {
                //completed  tab icon
                tabLayout.getTabAt(i).setIcon(R.drawable.ic_done_all_black_24dp);
            }

        }



        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    public HouseHold completedHH(){
        HouseHold tmp=null;
        if(thisHouse!=null){
            tmp= thisHouse;
        }
        return tmp;
    }

    public void switchTabs(int pageIndex)
    {
        viewPager.setCurrentItem(pageIndex);
        Intent intent=new Intent(Dashboard.this,Dashboard.class);
        startActivity(intent);
        finish();
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_dash, menu);
        String con = "Log out (" + preferences.getString("Name",null)+ " "+ preferences.getString("Surname_Name",null)+ ")";
        Log.d("****" , con);
        menu.getItem(0).setTitle(con);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        return false;
    }

    /***
     * ACTIONS FROM LOGOUT AND SETTINGS OPTION MENU
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.action_logout:
                // Delete shared preferences and log user out
                editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("last_access", DateHelper.getDateTime().toString());
                editor.putBoolean("is_logged",false);
                editor.apply();

                Intent intentHome = new Intent(Dashboard.this,login.class);
                // Check if we're running on Android 5.0 or higher
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    // Apply activity transition
                    startActivity(intentHome, ActivityOptions.makeSceneTransitionAnimation(Dashboard.this).toBundle());

                }
                else
                {
                    // Swap without transition
                    startActivity(intentHome);
                }

                return true;
            case R.id.action_settings:
                // Show the settings activity
                Intent intent = new Intent(Dashboard.this, appSettings.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Dashboard.this).toBundle());



                return true;
            case R.id.action_sync:
                // Execute Syncronize Activity
                if (Validator.isNetworkAvailable(Dashboard.this)) {
                    //Proceed connect to web service
                    new SyncAssignments().execute();
                    new SyncSample().execute();
                    new SyncEAssgn().execute();
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                } else {
                    //Request the user to enable network settings. Build the AlertDialog first.
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                    AlertDialog.Builder adBuilder = new AlertDialog.Builder(Dashboard.this)
                            .setTitle("Internet Connection !")
                            .setMessage("No active internet connections detected. Please enable mobile data or connect to WIFI")
                            .setPositiveButton("appSettings", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                    Dashboard.this.startActivities(new Intent[]{intent});
                                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                }

                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    //call show() to build and show the AlertDialog.
                    AlertDialog ad = adBuilder.show();

                }





                return true;

            case R.id.action_send:
                List<HouseHold> CompleteddHH = myDB.getCompleted();
                LibraryClass lib = new LibraryClass();
                if(CompleteddHH.size()==0){
                    lib.showError(Dashboard.this,"Synchronization","You have no completed assignments to synchronize");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs1.vibrate(100);

                }
                else{


                    if (Validator.isNetworkAvailable(Dashboard.this)) {
                        //Proceed connect to web service
                        new syncDataToServer().execute();

                    } else {
                        //Request the user to enable network settings. Build the AlertDialog first.
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                        AlertDialog.Builder adBuilder = new AlertDialog.Builder(Dashboard.this)
                                .setTitle("Internet Connection !")
                                .setMessage("No active internet connections detected. Please enable mobile data or connect to WIFI")
                                .setPositiveButton("appSettings", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                        Dashboard.this.startActivities(new Intent[]{intent});
                                        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    }

                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                        //call show() to build and show the AlertDialog.
                        AlertDialog ad = adBuilder.show();

                    }

                }


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //Synchronization HouseHold(House,Roster & Individual)
    private class SyncAssignments extends AsyncTask<Void,Void,String> {
        ProgressDialog d;

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        @Override
        protected  void  onPreExecute(){
            d = new ProgressDialog(Dashboard.this);
            d.setMessage("Reaching for Households....");
            d.setIndeterminate(true);
            d.show();
        }

        @Override
        protected String doInBackground(Void... voids)
        {
            String Response=null;

            try
            {



                String url=preferences.getString("server_ip",null)+"sync?Username=" + preferences.getString("Username",null);
                Log.d("SERVER: ",url);
                HttpHandler sh = new HttpHandler();
                String jsonStr = sh.makeServiceCall(url);

                if (jsonStr != null)
                {
                    Response=jsonStr;
                }

            }
            catch (Exception e){

            }

            return Response;
        }

        private void showProgress(boolean b) {
            if(b){
                progressBar.setVisibility(View.VISIBLE);

            }else{
                progressBar.setVisibility(View.INVISIBLE);
            }


        }

        @Override
        protected void onPostExecute(String result){
            //mAuthTask = null

            readFromServer(result);
            d.dismiss();


        }

        /**
         * Method to verify that the loggin was successful by checking the HTTP response text
         * @param svrmsg
         */
        private void readFromServer(String svrmsg)
        {

            //Log.d("Data from Webservice", svrmsg);
            if (svrmsg != null)
            {

                try {
                    svrmsg = svrmsg.replaceAll("null","\"\"");
                    JSONArray jsnArray = new JSONArray(svrmsg);

                    if(jsnArray.length()==0){
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                        AlertDialog.Builder adBuilder = new AlertDialog.Builder(Dashboard.this)
                                .setTitle("Synchronization")
                                .setMessage("You currently have no workload from you supervisor")
                                .setNegativeButton("Done", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                        //call show() to build and show the AlertDialog.
                        AlertDialog ad = adBuilder.show();

                    }
                    else
                        {
                        for(int i=0;i<jsnArray.length();i++)
                        {
                            String jsonItem = jsnArray.get(i).toString();


                            JSONObject jObject = new JSONObject(jsonItem);

                            HouseHold j = myDB.searchHouse(myDB.getReadableDatabase(),jObject.get("Assignment_ID").toString(),jObject.get("BatchNumber").toString());

                            if(j==null){
                            //CREATE HOUSEHOLD
                            HouseHold hh = new HouseHold();
                            hh.setAssignment_ID(jObject.get("Assignment_ID").toString());
                            hh.setBatchNumber(jObject.get("BatchNumber").toString());
                            hh.setDWELLING_NO(jObject.get("DWELLING_NO").toString());
                            hh.setHH_NO(jObject.get("HH_NO").toString());
                            hh.setENUMERATOR(jObject.get("ENUMERATOR").toString());
                            hh.setSUPERVISOR(jObject.get("SUPERVISOR").toString());
                            hh.setQUALITY_CONTROLLER(jObject.get("QUALITY_CONTROLLER").toString());
                            hh.setINTERVIEWER_VISITS1(jObject.get("INTERVIEWER_VISITS1").toString());


                            String dateInString = jObject.get("DATE1").toString();


                            DateTime result = null;
                            if (dateInString != null) {
                                Pattern datePatt = Pattern.compile("^/Date\\((\\d+)([+-]\\d+)?\\)/$");
                                Matcher m = datePatt.matcher(dateInString);
                                if (m.matches()) {
                                    Long l = Long.parseLong(m.group(1));
                                    result = new DateTime(l);
                                    hh.setDATE1(result.toString());
                                    // Time zone is not needed to calculate date
                                } else {
                                    //throw new IllegalArgumentException("Wrong date format");
                                }
                            }





                            hh.setVISIT1_RESULT(jObject.get("VISIT1_RESULT").toString());
                            hh.setCOMMENT1(jObject.get("COMMENT1").toString());


                            dateInString = jObject.get("NEXT_VISIT_2_DATE").toString();
                            DateTime DateNext2 = null;
                            if (dateInString != null) {
                                Pattern datePatt = Pattern.compile("^/Date\\((\\d+)([+-]\\d+)?\\)/$");
                                Matcher m = datePatt.matcher(dateInString);
                                if (m.matches()) {
                                    Long l = Long.parseLong(m.group(1));
                                    DateNext2 = new DateTime(l);
                                    hh.setNEXT_VISIT_2_DATE(DateNext2.toString());
                                    // Time zone is not needed to calculate date
                                } else {
                                    //throw new IllegalArgumentException("Wrong date format");
                                }
                            }

                            hh.setNEXT_VISIT_2(jObject.get("NEXT_VISIT_2").toString());
                            hh.setINTERVIEWER_VISITS2(jObject.get("INTERVIEWER_VISITS2").toString());

                            dateInString = jObject.get("DATE2").toString();
                            DateTime DateNext3 = null;
                            if (dateInString != null) {
                                Pattern datePatt = Pattern.compile("^/Date\\((\\d+)([+-]\\d+)?\\)/$");
                                Matcher m = datePatt.matcher(dateInString);
                                if (m.matches()) {
                                    Long l = Long.parseLong(m.group(1));
                                    DateNext3 = new DateTime(l);
                                    hh.setDATE2(DateNext3.toString());
                                    // Time zone is not needed to calculate date
                                } else {
                                    //throw new IllegalArgumentException("Wrong date format");
                                }
                            }


                            hh.setVISIT2_RESULT(jObject.get("VISIT2_RESULT").toString());
                            hh.setCOMMENT2(jObject.get("COMMENT2").toString());

                            dateInString = jObject.get("NEXT_VISIT_3_DATE").toString();
                            DateTime DateNext4 = null;
                            if (dateInString != null) {
                                Pattern datePatt = Pattern.compile("^/Date\\((\\d+)([+-]\\d+)?\\)/$");
                                Matcher m = datePatt.matcher(dateInString);
                                if (m.matches()) {
                                    Long l = Long.parseLong(m.group(1));
                                    DateNext4 = new DateTime(l);
                                    hh.setNEXT_VISIT_3_DATE(DateNext4.toString());
                                    // Time zone is not needed to calculate date
                                } else {
                                    //throw new IllegalArgumentException("Wrong date format");
                                }
                            }

                            hh.setNEXT_VISIT_3(jObject.get("NEXT_VISIT_3").toString());
                            hh.setINTERVIEWER_VISITS3(jObject.get("INTERVIEWER_VISITS3").toString());


                            dateInString = jObject.get("DATE3").toString();
                            DateTime DateNext5 = null;
                            if (dateInString != null) {
                                Pattern datePatt = Pattern.compile("^/Date\\((\\d+)([+-]\\d+)?\\)/$");
                                Matcher m = datePatt.matcher(dateInString);
                                if (m.matches()) {
                                    Long l = Long.parseLong(m.group(1));
                                    DateNext5 = new DateTime(l);
                                    hh.setDATE3(DateNext5.toString());
                                    // Time zone is not needed to calculate date
                                } else {
                                    //throw new IllegalArgumentException("Wrong date format");
                                }
                            }


                            hh.setVISIT3_RESULT(jObject.get("VISIT3_RESULT").toString());
                            hh.setCOMMENT_3(jObject.get("COMMENT_3").toString());
                            hh.setTOTAL_VISITS(jObject.get("TOTAL_VISITS").toString());
                            hh.setSample_FK(jObject.get("Sample_FK").toString());

                            hh.setCONSENT(jObject.get("CONSENT").toString());
                            hh.setCHECKED_BY(jObject.get("CHECKED_BY").toString());
                            hh.setCODED(jObject.get("CODED").toString());
                            hh.setFINAL_RESULT(jObject.get("FINAL_RESULT").toString());
                            hh.setFINAL_OTHER(jObject.get("FINAL_OTHER").toString());
                            hh.setSuperComment(jObject.get("SuperComment").toString());
                            hh.setInterview_Status(jObject.get("Interview_Status").toString());
                            hh.setH01(jObject.get("H01").toString());

                            hh.setH02(jObject.get("H02").toString());
                            hh.setH03(jObject.get("H03").toString());
                            hh.setH03Other(jObject.get("H03Other").toString());
                            hh.setH04(jObject.get("H04").toString());
                            hh.setH04Other(jObject.get("H04Other").toString());
                            hh.setH05(jObject.get("H05").toString());
                            hh.setH05Other(jObject.get("H05Other").toString());
                            hh.setH06(jObject.get("H06").toString());
                            hh.setH07(jObject.get("H07").toString());
                            hh.setH08(jObject.get("H08").toString());
                            hh.setH08Other(jObject.get("H08Other").toString());
                            hh.setH09(jObject.get("H09").toString());
                            hh.setH09Other(jObject.get("H09Other").toString());
                            hh.setH10(jObject.get("H10").toString());
                            hh.setH11(jObject.get("H11").toString());
                            hh.setH11Other(jObject.get("H11Other").toString());


                            hh.setH12(jObject.get("H12Radio").toString());
                            hh.setH12TV(jObject.get("H12TV").toString());
                            hh.setH12Telephone(jObject.get("H12Telephone").toString());
                            hh.setH12CellPhone(jObject.get("H12CellPhone").toString());
                            hh.setH12PrintMedia(jObject.get("H12PrintMedia").toString());
                            hh.setH12ElecMedia(jObject.get("H12ElecMedia").toString());
                            hh.setH12PerfomArts(jObject.get("H12PerfomArts").toString());


                            hh.setH13(jObject.get("H13Vehicle").toString());
                            hh.setH13Tractor(jObject.get("H13Tractor").toString());
                            hh.setH13Motorcycle(jObject.get("H13Motorcycle").toString());
                            hh.setH13Bicycle(jObject.get("H13Bicycle").toString());
                            hh.setH13DonkeyCart(jObject.get("H13DonkeyCart").toString());
                            hh.setH13DonkeyHorse(jObject.get("H13DonkeyHorse").toString());
                            hh.setH13Camels(jObject.get("H13Camels").toString());

                                String tb40=jObject.get("HIVTB40").toString();

                                if(tb40.equals("true")){
                                    hh.setHIVTB40("1");
                                }else{
                                    hh.setHIVTB40("0");
                                }







                            //Check if the entry exisits in the database firs

                            //INSERT INTO HOUSEHOLD

                                    myDB.inserthousehold(hh);

                                //Person Roster
                                JSONArray k = (JSONArray)jObject.get("roster");

                                for(int o=0;o<k.length();o++)
                                {
                                    JSONObject roster = (JSONObject)k.get(o);
                                    Log.d("SRNO", roster.get("SRNO").toString());
                                    //PersonRoster
                                    PersonRoster pp = new PersonRoster();
                                    pp.setLineNumber( Integer.parseInt(roster.get("SRNO").toString()));
                                    pp.setP01( roster.get("P01").toString());
                                    pp.setP02( roster.get("P02").toString());
                                    pp.setP03( roster.get("P03").toString());
                                    pp.setP04YY( roster.get("P04_YY").toString());
                                    pp.setP04MM( roster.get("P04_MM").toString());
                                    pp.setP04WKS( roster.get("P04_WKS").toString());
                                    pp.setP05(roster.get("P05").toString());
                                    pp.setP06( roster.get("P06").toString());
                                    pp.setP07( roster.get("P07").toString());
                                    pp.setP17( roster.get("P17").toString());
                                    pp.setP18( roster.get("P18").toString());
                                    pp.setP19( roster.get("P19").toString());
                                    pp.setP20( roster.get("P20").toString());
                                    pp.setP21( roster.get("P21").toString());


                                    pp.setB3_RapidConsent_Yes_No( roster.get("B3_RapidConsent_Yes_No").toString());
                                    pp.setB3_Guardian( roster.get("B3_Guardian").toString());
                                    //pp.setB3_Date( roster.get("B3_Date").toString());

                                    String dateInString1 = roster.get("B3_Date").toString();
                                    DateTime resulty = null;
                                    if (dateInString1 != null) {
                                        Pattern datePatt = Pattern.compile("^/Date\\((\\d+)([+-]\\d+)?\\)/$");
                                        Matcher m = datePatt.matcher(dateInString1);
                                        if (m.matches()) {
                                            Long l = Long.parseLong(m.group(1));
                                            resulty = new DateTime(l);
                                            pp.setB3_Date(resulty.toString());
                                            // Time zone is not needed to calculate date
                                        } else {
                                            //throw new IllegalArgumentException("Wrong date format");
                                        }
                                    }

                                    pp.setU15Rapid_Results( roster.get("U15Rapid_Result").toString());
                                //    pp.setRapid_Comment( roster.get("Rapid_Comment").toString());
                                    pp.setBarcode( roster.get("BarCode").toString());

                                    pp.setP08( roster.get("P08").toString());
                                    pp.setP10( roster.get("P10").toString());
                                    pp.setP11( roster.get("P11").toString());
                                    pp.setP12( roster.get("P12").toString());
                                    pp.setP13( roster.get("P13").toString());
                                    pp.setP13Other( roster.get("P13Other").toString());
                                    pp.setP14( roster.get("P14").toString());
                                    pp.setP15( roster.get("P15").toString());
                                    pp.setP16 ( roster.get("P16").toString());

                                    pp.setBloodDraw(roster.get("BloodDraw").toString());
                                    pp.setBloodVolume_1(roster.get("BloodVolume_1").toString());
                                    pp.setBloodVolume_4(roster.get("BloodVolume_4").toString());
                                    pp.setBloodVolume_6(roster.get("BloodVolume_6").toString());
                                    pp.setBloodVolume_10(roster.get("BloodVolume_10").toString());
                                    pp.setBloodVolumeComment(roster.get("BloodVolumeComment").toString());
                                    pp.setRapid(roster.get("Rapid").toString());
                                    pp.setRapidResults(roster.get("RapidResults").toString());
                                    pp.setBloodLabTest(roster.get("BloodLabTest").toString());
                                    pp.setBloodStore(roster.get("BloodStore").toString());

                                    pp.setRapidDate(roster.get("RapidDate").toString());

                                    pp.setBloodSampleCollected(roster.get("BloodSampleCollected").toString());
                                    pp.setChPrntlConsentBloodDraw(roster.get("ChPrntlConsentBloodDraw").toString());
                                    pp.setChPrntlConsentRHT(roster.get("ChPrntlConsentRHT").toString());
                                    pp.setChPrntlConsentLabTest(roster.get("ChPrntlConsentLabTest").toString());
                                    pp.setChPrntlConsentBloodStore(roster.get("ChPrntlConsentBloodStore").toString());
                                    pp.setChPrntlConsentDate(roster.get("ChPrntlConsentDate").toString());
                                    pp.setPrntlConsentX_Ray(roster.get("PrntlConsentX_Ray").toString());
                                    pp.setPrntlConsentX_RayReview(roster.get("PrntlConsentX_RayReview").toString());
                                    pp.setPrntlConsentX_RayStore(roster.get("PrntlConsentX_RayStore").toString());
                                    pp.setPrntlConsentSP_Collect(roster.get("PrntlConsentSP_Collect").toString());
                                    pp.setPrntlParentSP_AddTests(roster.get("PrntlParentSP_AddTests").toString());
                                    pp.setPrntlConsentSP_LabTest(roster.get("PrntlConsentSP_LabTest").toString());
                                    pp.setPrntlConsentTBDate(roster.get("PrntlConsentTBDate").toString());
                                    pp.setIndTB_X_Ray(roster.get("IndTB_X_Ray").toString());
                                    pp.setIndTB_X_RayReview(roster.get("IndTB_X_RayReview").toString());
                                    pp.setIndTB_X_RayStore(roster.get("IndTB_X_RayStore").toString());
                                    pp.setIndSP_Collect(roster.get("IndSP_Collect").toString());
                                    pp.setIndSP_AddTests(roster.get("IndSP_AddTests").toString());
                                    pp.setIndSP_LabTests(roster.get("IndSP_LabTests").toString());
                                    pp.setIndTB_ConsentDate(roster.get("IndTB_ConsentDate").toString());

                                    //INSERT ROSTER FOR THIS HOUSE  HOLD
                                    myDB.insertSyncRoster(pp,hh.getAssignment_ID(),hh.getBatchNumber());

                                }

                                //Individual
                                JSONArray l= (JSONArray)jObject.get("ind");

                                for(int o=0;o<l.length();o++) {
                                    JSONObject roster = (JSONObject) l.get(o);

                                    //PersonRoster
                                    Individual ind = new Individual();
                                    ind.setSRNO(Integer.parseInt(roster.get("SRNO").toString()));

                                    ind.setIndBarcode(roster.get("BarCode").toString());
                                    ind.setQ101(roster.get("Q101").toString());
                                    ind.setQ102(roster.get("Q102").toString());
                                    ind.setQ103(roster.get("Q103").toString());
                                    ind.setQ104(roster.get("Q104").toString());
                                    ind.setQ104a(roster.get("Q104a").toString());
                                    ind.setQ104b(roster.get("Q104b").toString());
                                    ind.setQ104c(roster.get("Q104c").toString());
                                    ind.setQ104cBISCED(roster.get("Q104cBISCED").toString());

                                    ind.setQ105(roster.get("Q105").toString());
                                    ind.setQ105Other(roster.get("Q105Other").toString());
                                    ind.setQ105a(roster.get("Q105a").toString());

                                    ind.setQ105b(roster.get("Q105b").toString());

                                    ind.setQ106(roster.get("Q106").toString());
                                    ind.setQ106a(roster.get("Q106a").toString());
                                    ind.setQ106aOther(roster.get("Q106aOther").toString());

                                    ind.setQ106b(roster.get("Q106b").toString());
                                    ind.setQ106c(roster.get("Q106c").toString());

                                    ind.setQ106d(roster.get("Q106d").toString());

                                    ind.setQ107(roster.get("Q107").toString());
                                    if (roster.get("Q107a").toString().equals(null) || roster.get("Q107a").toString().equals("")) {

                                    }
                                    else {
                                        ind.setQ107aMnth(roster.get("Q107a").toString().substring(2, 4));
                                        ind.setQ107aYY(roster.get("Q107a").toString().substring(0, 2));
                                    }

                                    ind.setQ107b(roster.get("Q107b").toString());
                                    ind.setQ107bOther(roster.get("Q107bOther").toString());
                                    ind.setQ107c(roster.get("Q107c").toString());
                                    ind.setQ107cOther(roster.get("Q107cOther").toString());

                                    ind.setQ201(roster.get("Q201").toString());
                                    ind.setQ202(roster.get("Q202").toString());
                                    ind.setQ203(roster.get("Q203").toString());
                                    ind.setQ204(roster.get("Q204").toString());
                                    ind.setQ205(roster.get("Q205").toString());
                                    ind.setQ205a(roster.get("Q205a").toString());


                                    ind.setQ301(roster.get("Q301").toString());
                                    ind.setQ301a(roster.get("Q301a").toString());

                                    ind.setQ302(roster.get("Q302").toString());

                                    ind.setQ303(roster.get("Q303").toString());
                                    ind.setQ303a(roster.get("Q303a").toString());

                                    ind.setQ304(roster.get("Q304").toString());
                                    ind.setQ304a(roster.get("Q304a").toString());

                                    ind.setQ305_1(roster.get("Q305Smoking").toString());
                                    ind.setQ305_2(roster.get("Q305Sniffing").toString());
                                    ind.setQ305_3(roster.get("Q305Chewing").toString());
                                    ind.setQ305_4(roster.get("Q305None").toString());

                                    ind.setQ306(roster.get("Q306").toString());
                                    ind.setQ307(roster.get("Q307").toString());

                                    ind.setQ401(roster.get("Q401").toString());
                                    ind.setQ101(roster.get("Q101").toString());
                                    ind.setQ402(roster.get("Q402").toString());
                                    ind.setQ402a(roster.get("Q402a").toString());
                                    ind.setQ402b(roster.get("Q402b").toString());
                                    ind.setQ403(roster.get("Q403").toString());


                                    ind.setQ404_1(roster.get("Q404Vaginal").toString());
                                    ind.setQ404_2(roster.get("Q404Anal").toString());
                                    ind.setQ404_3(roster.get("Q404Oral").toString());

                                    ind.setQ404a(roster.get("Q404a").toString());

                                    ind.setQ405(roster.get("Q405").toString());
                                    ind.setQ406(roster.get("Q406").toString());
                                    ind.setQ407(roster.get("Q407").toString());

                                    ind.setQ408(roster.get("Q408").toString());
                                    ind.setQ408a(roster.get("Q408a").toString());

                                    ind.setQ410Slapped(roster.get("Q410Slapped").toString());
                                    ind.setQ410Pushed(roster.get("Q410Pushed").toString());
                                    ind.setQ410Choked(roster.get("Q410Choked").toString());
                                    ind.setQ410Threatened(roster.get("Q410Threatened").toString());
                                    ind.setQ410Physical(roster.get("Q410Physical").toString());
                                    ind.setQ410Forced(roster.get("Q410Forced").toString());
                                    ind.setQ410MadeAfraid(roster.get("Q410MadeAfraid").toString());

                                    ind.setQ501(roster.get("Q501").toString());
                                    ind.setQ502(roster.get("Q502").toString());
                                    ind.setQ503(roster.get("Q503").toString());

                                    ind.setQ504_1(roster.get("Q504Pain").toString());
                                    ind.setQ504_2(roster.get("Q504Reduced").toString());
                                    ind.setQ504_3(roster.get("Q504Fear").toString());
                                    ind.setQ504_4(roster.get("Q504Culture").toString());
                                    ind.setQ504_5(roster.get("Q504Religion").toString());
                                    ind.setQ504_6(roster.get("Q504Spouse").toString());
                                    ind.setQ504_7(roster.get("Q504Parental").toString());
                                    ind.setQ504_8(roster.get("Q504Long").toString());
                                    ind.setQ504_10(roster.get("Q504FearHIV").toString());

                                    ind.setQ504_Other(roster.get("Q504Other").toString());

                                    ind.setQ601(roster.get("Q601").toString());
                                    ind.setQ601a(roster.get("Q601a").toString());

                                    ind.setQ602_1(roster.get("Q602Youth").toString());
                                    ind.setQ602_2(roster.get("Q602TV").toString());
                                    ind.setQ602_3(roster.get("Q602Radio").toString());
                                    ind.setQ602_4(roster.get("Q602NewsPaper").toString());
                                    ind.setQ602_5(roster.get("Q602Hospital").toString());
                                    ind.setQ602_6(roster.get("Q602Posters").toString());
                                    ind.setQ602_7(roster.get("Q602Traditional").toString());
                                    ind.setQ602_8(roster.get("Q602Workshop").toString());
                                    ind.setQ602_10(roster.get("Q602Individual").toString());
                                    ind.setQ602_11(roster.get("Q602Church").toString());
                                    ind.setQ602_12(roster.get("Q602Kgotla").toString());
                                    ind.setQ602_13(roster.get("Q602WorkPlace").toString());
                                    ind.setQ602_14(roster.get("Q602Peer").toString());
                                    ind.setQ602_15(roster.get("Q602School").toString());
                                    ind.setQ602_Other(roster.get("Q602Other").toString());

                                    ind.setQ603_1(roster.get("Q603Condom").toString());
                                    ind.setQ603_2(roster.get("Q603FewerP").toString());
                                    ind.setQ603_3(roster.get("Q603Both").toString());
                                    ind.setQ603_4(roster.get("Q603NoCasual").toString());
                                    ind.setQ603_5(roster.get("Q603Abstain").toString());
                                    ind.setQ603_6(roster.get("Q603NoCommercial").toString());
                                    ind.setQ603_7(roster.get("Q603Injection").toString());
                                    ind.setQ603_8(roster.get("Q603Blood").toString());
                                    ind.setQ603_9(roster.get("Q603DontKnow").toString());
                                    ind.setQ603_Other(roster.get("Q603Other").toString());


                                    ind.setQ604(roster.get("Q604").toString());
                                    ind.setQ604a(roster.get("Q604a").toString());
                                    ind.setQ604b_1(roster.get("Q604bYouth").toString());
                                    ind.setQ604b_2(roster.get("Q604bTV").toString());
                                    ind.setQ604b_3(roster.get("Q604bRadio").toString());
                                    ind.setQ604b_4(roster.get("Q604bNewsPaper").toString());
                                    ind.setQ604b_5(roster.get("Q604bHospital").toString());
                                    ind.setQ604b_6(roster.get("Q604bPoster").toString());
                                    ind.setQ604b_7(roster.get("Q604bTraditional").toString());
                                    ind.setQ604b_8(roster.get("Q604bWorkshop").toString());
                                    ind.setQ604b_10(roster.get("Q604bIndividual").toString());
                                    ind.setQ604b_11(roster.get("Q604bChurch").toString());
                                    ind.setQ604b_12(roster.get("Q604bKgotal").toString());
                                    ind.setQ604b_13(roster.get("Q604bWorkPlace").toString());
                                    ind.setQ604b_14(roster.get("Q604bPeer").toString());
                                    ind.setQ604b_15(roster.get("Q604bSchool").toString());
                                    ind.setQ604b_Other(roster.get("Q604bOther").toString());


                                    ind.setQ605_1(roster.get("Q605Windows").toString());
                                    ind.setQ605_2(roster.get("Q605Mouth").toString());
                                    ind.setQ605_3(roster.get("Q605Hands").toString());
                                    ind.setQ605_4(roster.get("Q605Nutrition").toString());
                                    ind.setQ605_5(roster.get("Q605Praying").toString());
                                    ind.setQ605_9(roster.get("Q605DontKnow").toString());
                                    ind.setQ605_Other(roster.get("Q605Other").toString());


                                    ind.setQ606(roster.get("Q606").toString());
                                    ind.setQ607(roster.get("Q607").toString());
                                    ind.setQ608(roster.get("Q608").toString());
                                    ind.setQ609(roster.get("Q609").toString());
                                    ind.setQ610(roster.get("Q610").toString());

                                    ind.setQ611a(roster.get("Q611a").toString());
                                    ind.setQ611b(roster.get("Q611b").toString());
                                    ind.setQ611c(roster.get("Q611c").toString());

                                    ind.setQ612(roster.get("Q612").toString());
                                    ind.setQ612a(roster.get("Q612a").toString());
                                    ind.setQ612aOther(roster.get("Q612Other").toString());

                                    ind.setQ613(roster.get("Q613").toString());
                                    ind.setQ613a(roster.get("Q613a").toString());
                                    ind.setQ613aOther(roster.get("Q613aOther").toString());
                                    ind.setQ614(roster.get("Q614").toString());
                                    ind.setQ614Other(roster.get("Q614Other").toString());
                                    ind.setQ615(roster.get("Q615").toString());

                                    ind.setQ616_1(roster.get("Q616Anybody").toString());
                                    ind.setQ616_2(roster.get("Q616Poor").toString());
                                    ind.setQ616_3(roster.get("Q616Homelesee").toString());
                                    ind.setQ616_4(roster.get("Q616Alcoholics").toString());
                                    ind.setQ616_5(roster.get("Q616Drugs").toString());
                                    ind.setQ616_6(roster.get("Q616PeopHIV").toString());
                                    ind.setQ616_7(roster.get("Q616PeopPrison").toString());
                                    ind.setQ616_8(roster.get("Q616Smokers").toString());
                                    ind.setQ616_9(roster.get("Q616DntKnw").toString());
                                    ind.setQ616_10(roster.get("Q616Other").toString());


                                    ind.setQ617a(roster.get("Q617Meal").toString());
                                    ind.setQ617b(roster.get("Q617Clothes").toString());
                                    ind.setQ617c(roster.get("Q617Miscarried").toString());
                                    ind.setQ617d(roster.get("Q617Widow").toString());
                                    ind.setQ617e(roster.get("Q617FamilyHIV").toString());
                                    ind.setQ617f(roster.get("Q617Sejeso").toString());
                                    ind.setQ617g(roster.get("Q617Touching").toString());
                                    ind.setQ617h(roster.get("Q617Someone").toString());
                                    ind.setQ617_0ther(roster.get("Q617Other").toString());
                                    ind.setQ618(roster.get("Q618").toString());

                                    ind.setQ619_1(roster.get("Q619Rash").toString());
                                    ind.setQ619_2(roster.get("Q619Cough").toString());
                                    ind.setQ619_3(roster.get("Q619LongCough").toString());
                                    ind.setQ619_4(roster.get("Q619Blood").toString());
                                    ind.setQ619_5(roster.get("Q619Headache").toString());
                                    ind.setQ619_6(roster.get("Q619Nausea").toString());
                                    ind.setQ619_7(roster.get("Q619Weight").toString());
                                    ind.setQ619_8(roster.get("Q619Fever").toString());
                                    ind.setQ619_10(roster.get("Q619Fever7Days").toString());
                                    ind.setQ619_11(roster.get("Q619ChestPain").toString());
                                    ind.setQ619_12(roster.get("Q619Breath").toString());
                                    ind.setQ619_13(roster.get("Q619Fatigue").toString());
                                    ind.setQ619_14(roster.get("Q619Sweats").toString());
                                    ind.setQ619_9(roster.get("Q619DontKnw").toString());
                                    ind.setQ619_Other(roster.get("Q619Other").toString());


                                    ind.setQ620(roster.get("Q620").toString());
                                    ind.setQ620_Other(roster.get("Q620Other").toString());

                                    ind.setQ621(roster.get("Q621").toString());
                                    ind.setQ621a_1(roster.get("Q621aSpouse").toString());
                                    ind.setQ621a_2(roster.get("Q621aPartner").toString());
                                    ind.setQ621a_3(roster.get("Q621aFriend").toString());
                                    ind.setQ621a_4(roster.get("Q621aFamily").toString());
                                    ind.setQ621a_5(roster.get("Q621aRelative").toString());
                                    ind.setQ621a_6(roster.get("Q621aHCWorker").toString());
                                    ind.setQ621a_7(roster.get("Q621aCoWorker").toString());
                                    ind.setQ621a_Other(roster.get("Q621aOther").toString());
                                    ind.setQ621b(roster.get("Q621b").toString());

                                    ind.setQ621bOther(roster.get("Q621bOther").toString());

                                    ind.setQ622(roster.get("Q622").toString());

                                    ind.setQ622a(roster.get("Q622a").toString());
                                    ind.setQ622aOther(roster.get("Q622aOther").toString());

                                    ind.setQ622b(roster.get("Q622b").toString());
                                    ind.setQ622bOther(roster.get("Q622bOther").toString());


                                    ind.setQ623(roster.get("Q623").toString());
                                    ind.setQ624(roster.get("Q624").toString());
                                    ind.setQ625(roster.get("Q625").toString());
                                    ind.setQ701(roster.get("Q701").toString());
                                    ind.setQ702(roster.get("Q702").toString());
                                    ind.setQ703(roster.get("Q703").toString());
                                    ind.setQ704(roster.get("Q704").toString());
                                    ind.setQ705(roster.get("Q705").toString());
                                    ind.setQ801(roster.get("Q801").toString());


                                    ind.setQ801a(roster.get("Q801a").toString());
                                    ind.setQ801b(roster.get("Q801b").toString());

                                    if (roster.get("Q801c").toString().equals(null) || roster.get("Q801c").toString().equals("")) {

                                    } else {
                                        ind.setQ801cMonth(roster.get("Q801c").toString().substring(0, 2));
                                        ind.setQ801cYear(roster.get("Q801c").toString().substring(2, 6));
                                    }


                                    ind.setQ801d(roster.get("Q801d").toString());
                                    ind.setQ801dOther(roster.get("Q801dOther").toString());

                                    ind.setQ801e(roster.get("Q801e").toString());
                                    ind.setQ801eOther(roster.get("Q801eOther").toString());


                                    ind.setQ801f(roster.get("Q801f").toString());

                                    ind.setQ802(roster.get("Q802").toString());
                                    ind.setQ802a(roster.get("Q802a").toString());
                                    ind.setQ802aOther(roster.get("Q802aOther").toString());


                                    ind.setQ803(roster.get("Q803").toString());
                                    ind.setQ803Other(roster.get("Q803Other").toString());

                                    ind.setQ804(roster.get("Q804").toString());
                                    ind.setQ804Other(roster.get("Q804Other").toString());

                                    ind.setQ901(roster.get("Q901").toString());
                                    ind.setQ901a(roster.get("Q901a").toString());
                                    ind.setQ901aOther(roster.get("Q901aOther").toString());

                                    if (roster.get("Q902").toString().equals(null) || roster.get("Q902").toString().equals("")) {

                                    } else {
                                        ind.setQ902Month(roster.get("Q902").toString().substring(0, 2));
                                        ind.setQ902Year(roster.get("Q902").toString().substring(2, 6));
                                    }


                                    ind.setQ903a(roster.get("Q903DenyCare").toString());
                                    ind.setQ903b(roster.get("Q903Gossip").toString());
                                    ind.setQ903c(roster.get("Q903NoSex").toString());
                                    ind.setQ903d(roster.get("Q903VerbalAbuse").toString());
                                    ind.setQ903e(roster.get("Q903PhysicalAbuse").toString());
                                    ind.setQ903f(roster.get("Q903NoContact").toString());
                                    ind.setQ903g(roster.get("Q903SharingStatus").toString());


                                    ind.setQ904(roster.get("Q904").toString());
                                    ind.setQ904a(roster.get("Q904a").toString());
                                    ind.setQ904aOther(roster.get("Q904aOther").toString());


                                    if (roster.get("Q904b").toString().equals(null) || roster.get("Q904b").toString().equals("")) {

                                    } else {
                                        ind.setQ904bMM(roster.get("Q904b").toString().substring(0, 2));
                                        ind.setQ904bYYYY(roster.get("Q904b").toString().substring(2, 6));
                                    }


                                    ind.setQ904c(roster.get("Q904c").toString());
                                    ind.setQ904cOther(roster.get("Q904cOther").toString());


                                    ind.setQ905(roster.get("Q905").toString());
                                    ind.setQ905a(roster.get("Q905a").toString());
                                    ind.setQ905aOther(roster.get("Q905aOther").toString());

                                    ind.setQ1001(roster.get("Q1001").toString());
                                    ind.setQ1002(roster.get("Q1002").toString());

                                    ind.setQ1002a_1(roster.get("Q1002aMCondom").toString());
                                    ind.setQ1002a_2(roster.get("Q1002aFCondom").toString());
                                    ind.setQ1002a_3(roster.get("Q1002aInjectContra").toString());
                                    ind.setQ1002a_4(roster.get("Q1002aOralContra").toString());
                                    ind.setQ1002a_5(roster.get("Q1002aUID").toString());
                                    ind.setQ1002a_6(roster.get("Q1002aBTL").toString());
                                    ind.setQ1002a_7(roster.get("Q1002aFSterilization").toString());
                                    ind.setQ1002a_8(roster.get("Q1002aMSterilization").toString());
                                    ind.setQ1002a_10(roster.get("Q1002aImplants").toString());
                                    ind.setQ1002a_11(roster.get("Q1002aEContra").toString());
                                    ind.setQ1002a_12(roster.get("Q1002aSafePeriod").toString());
                                    ind.setQ1002a_13(roster.get("Q1002aLAM").toString());
                                    ind.setQ1002a_14(roster.get("Q1002aDiagraphm").toString());
                                    ind.setQ1002a_15(roster.get("Q1002aSpermicides").toString());
                                    ind.setQ1002a_16(roster.get("Q1002aNatural").toString());
                                    ind.setQ1002a_17(roster.get("Q1002aTraditional").toString());
                                    ind.setQ1002a_18(roster.get("Q1002aSpritual").toString());
                                    ind.setQ1002a_Other(roster.get("Q1002aOther").toString());

                                    ind.setQ1002b(roster.get("Q1002b").toString());
                                    ind.setQ1002bOther(roster.get("Q1002bOther").toString());

                                    ind.setQ1003(roster.get("Q1003").toString());

                                    if (roster.get("Q1004").toString().equals(null) || roster.get("Q1004").toString().equals("")) {

                                    } else {
                                        ind.setQ1004_Day(roster.get("Q1004").toString().substring(0, 2));
                                        ind.setQ1004_Month(roster.get("Q1004").toString().substring(2, 4));
                                        ind.setQ1004_Year(roster.get("Q1004").toString().substring(4, 8));
                                    }


                                    ind.setQ1004a(roster.get("Q1004a").toString());
                                    ind.setQ1004b(roster.get("Q1004b").toString());
                                    ind.setQ1004bOther(roster.get("Q1004bOther").toString());


                                    ind.setQ1005(roster.get("Q1005").toString());
                                    ind.setQ1005a(roster.get("Q1005a").toString());
                                    ind.setQ1006(roster.get("Q1006").toString());

                                    ind.setQ1007(roster.get("Q1007").toString());
                                    ind.setQ1007a(roster.get("Q1007a").toString());

                                    ind.setQ1008(roster.get("Q1008").toString());
                                    ind.setQ1008a(roster.get("Q1008a").toString());
                                    ind.setQ1008aOther(roster.get("Q1008aOther").toString());


                                    ind.setQ1009(roster.get("Q1009").toString());
                                    ind.setQ1009a(roster.get("Q1009a").toString());

                                    ind.setQ1010(roster.get("Q1010").toString());
                                    ind.setQ1010Other(roster.get("Q1010Other").toString());

                                    ind.setQ1011(roster.get("Q1011").toString());
                                    ind.setQ1011_Other(roster.get("Q1011Other").toString());

                                    if (roster.get("Q1012").toString().equals(null) || roster.get("Q1012").toString().equals("")) {

                                    } else {
                                        if (roster.get("Q1012").toString().length() == 8) {
                                            ind.setQ1012_Week(roster.get("Q1012").toString().substring(0, 2));
                                            ind.setQ1012_Month(roster.get("Q1012").toString().substring(2, 4));
                                            ind.setQ1012_Year(roster.get("Q1012").toString().substring(4, 8));
                                            Log.d("WW", roster.get("Q1012").toString().substring(0, 2));
                                            Log.d("MM", roster.get("Q1012").toString().substring(2, 4));
                                            Log.d("YYYY", roster.get("Q1012").toString().substring(4, 8));
                                        } else {
                                            ind.setQ1012_Week(roster.get("Q1012").toString());

                                        }


                                    }


                                    ind.setQ1013(roster.get("Q1013").toString());
                                    ind.setQ1014(roster.get("Q1014").toString());
                                    ind.setQ1014a(roster.get("Q1014a").toString());
                                    ind.setQ1014b(roster.get("Q1014b").toString());

                                    ind.setQ1015(roster.get("Q1015").toString());
                                    ind.setQ1015a(roster.get("Q1015a").toString());
                                    ind.setQ1015b(roster.get("Q1015b").toString());
                                    ind.setQ1016(roster.get("Q1016").toString());
                                    ind.setQ1017(roster.get("Q1017").toString());


                                    ind.setQ1101(roster.get("Q1101").toString());
                                    ind.setQ1101a(roster.get("Q1101a").toString());
                                    ind.setQ1101aOther(roster.get("Q1101aOther").toString());
                                    ind.setQ1102(roster.get("Q1102").toString());
                                    ind.setQ1102a(roster.get("Q1102a").toString());
                                    ind.setQ1103(roster.get("Q1103").toString());

                                    if (roster.get("Q1103a").toString().equals(null) || roster.get("Q1103a").toString().equals("")) {

                                    } else {
                                        ind.setQ1103aDD(roster.get("Q1103a").toString().substring(0, 2));
                                        ind.setQ1103aWks(roster.get("Q1103a").toString().substring(2, 4));

                                    }

                                    ind.setQ1103aDontKnow(roster.get("Q1103a").toString());

                                    ind.setQ1104(roster.get("Q1104").toString());
                                    ind.setQ1105(roster.get("Q1105").toString());
                                    ind.setQ1106(roster.get("Q1106").toString());
                                    ind.setQ1106a(roster.get("Q1106a").toString());
                                    ind.setQ1106b(roster.get("Q1106b").toString());
                                    ind.setQ1106bOther(roster.get("Q1106bOther").toString());
                                    ind.setQ1107(roster.get("Q1107").toString());


                                    if (roster.get("Q1107a").toString().equals(null) || roster.get("Q1107a").toString().equals("")) {

                                    } else {
                                        ind.setQ1107aDD(roster.get("Q1107a").toString().substring(0, 2));
                                        ind.setQ1107aWks(roster.get("Q1107a").toString().substring(2, 4));

                                    }


                                    ind.setQ1107aDontKnow(roster.get("Q1107a").toString());

                                    ind.setQ1108(roster.get("Q1108").toString());

                                    if (roster.get("Q1108a").toString().equals(null) || roster.get("Q1108a").toString().equals("")) {

                                    } else {
                                        ind.setQ1108aDD(roster.get("Q1108a").toString().substring(0, 2));
                                        ind.setQ1108aWks(roster.get("Q1108a").toString().substring(2, 4));
                                    }

                                    ind.setQ1108aDontKnow(roster.get("Q1108a").toString());


                                    ind.setQ1109(roster.get("Q1109").toString());
                                    ind.setQ1110(roster.get("Q1110").toString());
                                    ind.setQ1111(roster.get("Q1111").toString());
                                    ind.setQ1111Other(roster.get("Q1111Other").toString());

                                    ind.setQ1112(roster.get("Q1112").toString());
                                    ind.setQ1112_Other(roster.get("Q1112Other").toString());

                                    ind.setQ1113(roster.get("Q1113").toString());
                                    ind.setQ1113Other(roster.get("Q1113Other").toString());

                                    ind.setQ1114(roster.get("Q1114").toString());

                                    ind.setB8_Yes_No(roster.get("B8_Yes_No").toString());
                                    //ind.setB8_Date(roster.get("B8_Date").toString());


                                    String dateInString1 = roster.get("B8_Date").toString();
                                    DateTime resulty = null;
                                    if (dateInString1 != null) {
                                        Pattern datePatt = Pattern.compile("^/Date\\((\\d+)([+-]\\d+)?\\)/$");
                                        Matcher m = datePatt.matcher(dateInString1);
                                        if (m.matches()) {
                                            Long ll = Long.parseLong(m.group(1));
                                            resulty = new DateTime(ll);
                                            ind.setB8_Date(resulty.toString());
                                            // Time zone is not needed to calculate date
                                        } else {
                                            //throw new IllegalArgumentException("Wrong date format");
                                        }
                                    }

                                    ind.setB8_O15_Rapid(roster.get("B8_O15Rapid").toString());
                                    ind.setQ801f(roster.get("Q801f").toString());
                                    ind.setIndRapid_Comment(roster.get("RapidComment").toString());

                                    //ind.setBlooConsent(roster.get("B8_O15Rapid").toString());


                                    //ind.setResult(roster.get("B8_O15Rapid").toString());
                                    //ind.setResultOther(roster.get("B8_O15Rapid").toString());
                                    //ind.setResultComment(roster.get("B8_O15Rapid").toString());
                                    //ind.setBlooConsent(roster.get("B8_O15Rapid").toString());
                                    //ind.setLayCounselor(roster.get("B8_O15Rapid").toString());


                                    ind.setIndvQuestionnairePConsent15_17(roster.get("IndvQuestionnairePConsent15_17").toString());
                                    ind.setIndvQuestionnaireConsent(roster.get("IndvQuestionnaireConsent").toString());
                                    ind.setIndvBloodDraw(roster.get("IndvBloodDraw").toString());
                                    ind.setBloodVol_1(roster.get("BloodVol_1").toString());
                                    ind.setBloodVol_4(roster.get("BloodVol_4").toString());
                                    ind.setBloodVol_6(roster.get("BloodVol_6").toString());
                                    ind.setBloodVol_10(roster.get("BloodVol_10").toString());
                                    ind.setBloodVolComment(roster.get("BloodVolComment").toString());
                                    ind.setIndBloodLabTest(roster.get("IndBloodLabTest").toString());
                                    ind.setIndBloodStore(roster.get("IndBloodStore").toString());
                                    ind.setPrntlConsentBloodDraw(roster.get("PrntlConsentBloodDraw").toString());
                                    ind.setPrntlConsentRHT(roster.get("PrntlConsentRHT").toString());
                                    ind.setPrntlConsentLabTest(roster.get("PrntlConsentLabTest").toString());
                                    ind.setPrntlConsentBloodStore(roster.get("PrntlConsentBloodStore").toString());
                                    ind.setPrntlParentID(roster.get("PrntlParentID").toString());
                                    ind.setPrntlConsentDate(roster.get("PrntlConsentDate").toString());

                                    ind.setVISIT1(roster.get("VISIT1").toString());
                                    ind.setVISIT2(roster.get("VISIT2").toString());
                                    ind.setVISIT3(roster.get("VISIT3").toString());
                                    ind.setDATE1(roster.get("DATE").toString());
                                    ind.setDATE2(roster.get("DATE2").toString());
                                    ind.setDATE3(roster.get("DATE3").toString());
                                    ind.setSync(roster.get("Sync").toString());



                                    //check if the entry exists in the database first


                                    //INSERT INDIVIDUALS FROM THIS HOUSE
                                    myDB.insertSyncIndividual(ind, hh.getAssignment_ID(), hh.getBatchNumber(), ind.getSRNO());

                                }



                            }
                            //Save to DB




                        }
                    }


                    //Send HTTP OK



                } catch (Exception g) {
                    g.printStackTrace();
                    Log.d("Exception 1 :", "from JSONArray: ");
                }


            }

        }



    }

    //Synchronization Sample
    private class SyncSample extends AsyncTask<Void,Void,String> {
        ProgressDialog d;
        @Override
        protected  void  onPreExecute(){
            //showProgress(true);
            d = new ProgressDialog(Dashboard.this);
            d.setMessage("Getting Sample....");
            d.setIndeterminate(true);
            d.show();
        }

        @Override
        protected String doInBackground(Void... voids)
        {
            String Response=null;
            try {

                String url=preferences.getString("server_ip",null)+"syncSample?Username=" + preferences.getString("Username",null);
                HttpHandler sh = new HttpHandler();
                String jsonStr = sh.makeServiceCall(url);

                if (jsonStr != null)
                {
                    Response=jsonStr;
                }

            }
            catch (Exception e){

            }

            return Response;
        }

        private void showProgress(boolean b) {
            if(b){
                progressBar.setVisibility(View.VISIBLE);

            }else{
                progressBar.setVisibility(View.INVISIBLE);
            }


        }

        @Override
        protected void onPostExecute(String result){
            //mAuthTask = null
            //showProgress(false);

            readFromServer(result);
            d.dismiss();


        }

        /**
         * Method to verify that the loggin was successful by checking the HTTP response text
         * @param svrmsg
         */
        private void readFromServer(String svrmsg)
        {

            if (svrmsg != null)
            {

                try {
                    svrmsg = svrmsg.replaceAll("null","\"null\"");
                    JSONArray jsnArray = new JSONArray(svrmsg);

                    if(jsnArray.length()==0){


                    }else
                        {


                        for(int i=0;i<jsnArray.length();i++)
                        {
                            String jsonItem = jsnArray.get(i).toString();


                            JSONObject jObject = new JSONObject(jsonItem);
                            //CREAT SAMPLE

                            Sample hh = new Sample();
                            hh.setPK(jObject.get("PK").toString());
                            hh.setRegion(jObject.get("Region").toString());
                            hh.setStratumNo(jObject.get("StratumNo").toString());
                            hh.setDistrictCode(jObject.get("DistrictCode").toString());
                            hh.setVillageCode(jObject.get("VillageCode").toString());
                            hh.setLocalityCode(jObject.get("LocalityCode").toString());
                            hh.setEACode(jObject.get("EACode").toString());
                            hh.setBlockNo(jObject.get("BlockNo").toString());
                            hh.setStatusCode(jObject.get("StatusCode").toString());
                            hh.setDistrictName(jObject.get("DistrictName").toString());
                            hh.setDistrictEAVillageLocality(jObject.get("DistrictEAVillageLocality").toString());
                            hh.setLocalityType(jObject.get("LocalityType").toString());
                            hh.setNoofHholds(jObject.get("NoofHholds").toString());
                            hh.setSTATUS(jObject.get("STATUS").toString());
                            hh.setTBculture(jObject.get("TBculture").toString());
                            hh.setAssignmentGenerated(jObject.get("AssignmentGenerated").toString());

                            //INSERT INDIVIDUALS FROM THIS HOUSE
                            myDB.insertSyncSample(hh);



                        }
                    }


                    //Send HTTP OK



                } catch (Exception g) {
                    g.printStackTrace();
                    Log.d("Exception 1 :", "from JSONArray: ");
                }


            }

        }



    }

    //###################################Synchronization EA Assignments
    private class SyncEAssgn extends AsyncTask<Void,Void,String> {
        ProgressDialog d;
        @Override
        protected  void  onPreExecute(){
            //showProgress(true);
            d = new ProgressDialog(Dashboard.this);
            d.setMessage("Checking EA Assignments....");
            d.setIndeterminate(true);
            d.show();
        }

        @Override
        protected String doInBackground(Void... voids)
        {
            String Response=null;
            try {

                String url=preferences.getString("server_ip",null)+"syncAssign?Username=" + preferences.getString("Username",null);
                HttpHandler sh = new HttpHandler();
                String jsonStr = sh.makeServiceCall(url);

                if (jsonStr != null)
                {
                    Response=jsonStr;
                }


            }
            catch (Exception e){

            }

            return Response;
        }

        private void showProgress(boolean b) {
            if(b){
                progressBar.setVisibility(View.VISIBLE);

            }else{
                progressBar.setVisibility(View.INVISIBLE);
            }


        }

        @Override
        protected void onPostExecute(String result){
            //mAuthTask = null
            //showProgress(false);
            d.dismiss();
            readFromServer(result);
            Intent intent=new Intent(Dashboard.this,Dashboard.class);
            startActivity(intent);


        }

        /**
         * Method to verify that the loggin was successful by checking the HTTP response text
         * @param svrmsg
         */
        private void readFromServer(String svrmsg)
        {

            if (svrmsg != null)
            {

                try {
                    svrmsg = svrmsg.replaceAll("null","\"null\"");
                    JSONArray jsnArray = new JSONArray(svrmsg);
                    writeToFile(svrmsg,Dashboard.this);
                    if(jsnArray.length()==0){


                    }else{
                        for(int i=0;i<jsnArray.length();i++)
                        {
                            String jsonItem = jsnArray.get(i).toString();


                            JSONObject jObject = new JSONObject(jsonItem);
                            //CREAT HOUSEHOLD


                                Assignments hh = new Assignments();
                                hh.setAssignment_ID(jObject.get("EA_Assignment_ID").toString());
                                hh.setStratum_code(jObject.get("STRATUM").toString());
                                hh.setDISTRCIT_NO(jObject.get("DISTRICT").toString());
                                hh.setVillage_code(jObject.get("VILLAGE").toString());
                                hh.setLocality_code(jObject.get("LOCALITY").toString());
                                hh.setEA_code(jObject.get("EA").toString());
                                hh.setBlock_number(jObject.get("BLOCK_NO").toString());
                                hh.setEA_Status(jObject.get("EA_STATUS").toString());
                                hh.setSample_FK(jObject.get("SampleFK").toString());
                                hh.setROUND_NUMBER(jObject.get("ROUND_NUMBER").toString());
                                hh.setSupervisor(jObject.get("Supervisor").toString());



                            //Save to DB

                            myDB.insertEAAssignment(hh);


                        }
                    }


                    //Send HTTP OK



                } catch (Exception g) {
                    g.printStackTrace();
                    Log.d("Exception 1 :", "from JSONArray: ");
                }


            }

        }



    }

    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("json.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    //####################################Synchronization Data to database
    private class syncDataToServer extends AsyncTask<Void,Void,String> {
        String status = null;
        int done = 0;
        ProgressDialog d;


        String s="Sending data. Please wait....";

        @Override
        protected  void  onPreExecute(){
            super.onPreExecute();
            d = new ProgressDialog(Dashboard.this);
            d.setMessage(s);
            d.setIndeterminate(true);
            d.setCancelable(false);
            d.show();
        }

        @Override
        protected String doInBackground(Void... voids)
        {
            List<HouseHold> CompleteddHH = myDB.getCompleted();

            /**
             * SET THE STATUS OF INTERVIEW TO 0 FOR THE SUPERVISOR TO SEE
             * INTERVIEWS FROM FIELD
             */
            for(HouseHold h : CompleteddHH){
                h.setInterview_Status("0");
            }


            Gson gson = new Gson();/*Builder()
                    .setDateFormat(DateFormat.FULL, DateFormat.FULL).create();*/
            String json = gson.toJson(CompleteddHH);
            //Log.d("This",json);
            writeToFile(json,Dashboard.this);
            if(CompleteddHH.size()==0)
            {
                d.dismiss();

            }
            else
            {
                /***
                 *SYNCHRONIZE*
                 */
                try
                {
                    URL url = new URL(preferences.getString("server_ip",null)+"dataFromField");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");


                    OutputStream os = conn.getOutputStream();
                    os.write(json.getBytes());
                    os.flush();

                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));
                    String output;
                    while ((output = br.readLine()) != null)
                    {
                        status = output;
                    }

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Error in Send", e.toString());
                    status="error" + e.toString();
                }
            }


        return  status;
        }


        private void showProgress(boolean b) {
            if(b){
                progressBar.setVisibility(View.VISIBLE);

            }else{
                progressBar.setVisibility(View.INVISIBLE);
            }


        }

        @Override
        protected void onPostExecute(String result){
            Log.d("From Server", "The...." + result);
            int r = 3;
            try{
            r=Integer.parseInt(result);}catch (Exception g){}
            if(result!=null){
                if(r==1){
                    d.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
                    builder.setTitle("Success");
                    builder.setIcon(R.drawable.ic_done_all_black_24dp);

                    builder.setMessage("Your work has been synchronized successfully");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Do nothing only when the Head of House is selected we proceed.

                        }
                    });

                    AlertDialog alertDialog =  builder.show();
                    final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                    positiveButtonLL.width= ViewGroup.LayoutParams.MATCH_PARENT;
                    positiveButton.setTextColor(Color.WHITE);
                    positiveButton.setBackgroundColor(Color.parseColor("#3FC0FF"));
                    positiveButton.setLayoutParams(positiveButtonLL);


                }else{
                    if(r==0){
                        d.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
                        builder.setTitle("Error");
                        builder.setIcon(R.drawable.ic_error_red_24dp);

                        builder.setMessage("An error has been encountered while synchronizing");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Do nothing only when the Head of House is selected we proceed.

                            }
                        });


                        AlertDialog alertDialog =  builder.show();
                        final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                        positiveButtonLL.width= ViewGroup.LayoutParams.MATCH_PARENT;
                        positiveButton.setTextColor(Color.WHITE);
                        positiveButton.setBackgroundColor(Color.parseColor("#3FC0FF"));
                        positiveButton.setLayoutParams(positiveButtonLL);
                    }else{
                        //exception during sync
                        d.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
                        builder.setTitle("Fatal Error");
                        builder.setIcon(R.drawable.ic_error_red_24dp);

                        builder.setMessage("An error has been encountered while synchronizing");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Do nothing only when the Head of House is selected we proceed.

                            }
                        });


                        AlertDialog alertDialog =  builder.show();
                        final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                        positiveButtonLL.width= ViewGroup.LayoutParams.MATCH_PARENT;
                        positiveButton.setTextColor(Color.WHITE);
                        positiveButton.setBackgroundColor(Color.parseColor("#FF0000"));
                        positiveButton.setLayoutParams(positiveButtonLL);

                    }


                }



            }else{
//exception during sync
                d.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
                builder.setTitle("Synchronization Error");
                builder.setIcon(R.drawable.ic_error_red_24dp);

                builder.setMessage("An error has been encountered while synchronizing, no response returned");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Do nothing only when the Head of House is selected we proceed.

                    }
                });


                AlertDialog alertDialog =  builder.show();
                final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                positiveButtonLL.width= ViewGroup.LayoutParams.MATCH_PARENT;
                positiveButton.setTextColor(Color.WHITE);
                positiveButton.setBackgroundColor(Color.parseColor("#3FC0FF"));
                positiveButton.setLayoutParams(positiveButtonLL);

            }



        }



    }


}
