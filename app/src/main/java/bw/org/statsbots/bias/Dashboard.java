package bw.org.statsbots.bias;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.Toast;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


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
        menu.getItem(0).setTitle("Log out (" + preferences.getString("Name",null)+ " "+ preferences.getString("Surname_Name",null)+ ")");
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

                return true;
            case R.id.action_sync:
                // Execute Syncronize Activity

                new SyncAssignments().execute();
                new SyncSample().execute();
                new SyncEAssgn().execute();




                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //Synchronization HouseHold(House,Roster & Individual)
    private class SyncAssignments extends AsyncTask<Void,Void,String> {

        @Override
        protected  void  onPreExecute(){
           //showProgress(true);
        }

        @Override
        protected String doInBackground(Void... voids)
        {
            String Response=null;
            try {

                String url="http://10.30.3.169:8080/WebService/sync?Username=" + preferences.getString("Username",null);
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

                    }else
                        {
                        for(int i=0;i<jsnArray.length();i++)
                        {
                            String jsonItem = jsnArray.get(i).toString();


                            JSONObject jObject = new JSONObject(jsonItem);

                            //CREATE HOUSEHOLD

                            HouseHold hh = new HouseHold();

                            hh.setBatchNumber(jObject.get("BatchNumber").toString());
                            hh.setDWELLING_NO(jObject.get("DWELLING_NO").toString());
                            hh.setHH_NO(jObject.get("HH_NO").toString());
                            hh.setENUMERATOR(jObject.get("ENUMERATOR").toString());
                            hh.setSUPERVISOR(jObject.get("SUPERVISOR").toString());
                            hh.setQUALITY_CONTROLLER(jObject.get("QUALITY_CONTROLLER").toString());
                            hh.setINTERVIEWER_VISITS1(jObject.get("INTERVIEWER_VISITS1").toString());
                            hh.setDATE1(jObject.get("DATE1").toString());
                            hh.setVISIT1_RESULT(jObject.get("VISIT1_RESULT").toString());
                            hh.setCOMMENT1(jObject.get("COMMENT1").toString());
                            hh.setNEXT_VISIT_2_DATE(jObject.get("NEXT_VISIT_2_DATE").toString());
                            hh.setNEXT_VISIT_2(jObject.get("NEXT_VISIT_2").toString());
                            hh.setINTERVIEWER_VISITS2(jObject.get("INTERVIEWER_VISITS2").toString());
                            hh.setDATE2(jObject.get("DATE2").toString());
                            hh.setVISIT2_RESULT(jObject.get("VISIT2_RESULT").toString());
                            hh.setCOMMENT2(jObject.get("COMMENT2").toString());
                            hh.setNEXT_VISIT_3_DATE(jObject.get("NEXT_VISIT_3_DATE").toString());
                            hh.setNEXT_VISIT_3(jObject.get("NEXT_VISIT_3").toString());
                            hh.setINTERVIEWER_VISITS3(jObject.get("INTERVIEWER_VISITS3").toString());
                            hh.setDATE3(jObject.get("DATE3").toString());
                            hh.setVISIT3_RESULT(jObject.get("VISIT3_RESULT").toString());
                            hh.setCOMMENT_3(jObject.get("COMMENT_3").toString());
                            hh.setTOTAL_VISITS(jObject.get("TOTAL_VISITS").toString());
                            hh.setSample_FK(jObject.get("Sample_FK").toString());
                            hh.setAssignment_ID(jObject.get("Assignment_ID").toString());
                            hh.setCONSENT(jObject.get("CONSENT").toString());
                            hh.setCHECKED_BY(jObject.get("BatchNumber").toString());
                            hh.setCODED(jObject.get("CODED").toString());
                            hh.setFINAL_RESULT(jObject.get("FINAL_RESULT").toString());
                            hh.setFINAL_OTHER(jObject.get("FINAL_OTHER").toString());
                            hh.setSuperComment(jObject.get("SuperComment").toString());
                            hh.setInterview_Status(jObject.get("Interview_Status").toString());
                            //hh.setInterview_Status(jObject.get("Interview_Status").toString());

                            //hh.setSuperComment(jObject.get("SuperComment").toString());

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
                                pp.setP06( roster.get("P06").toString());
                                pp.setP07( roster.get("P07").toString());
                                pp.setP17( roster.get("P17").toString());
                                pp.setP18( roster.get("P18").toString());
                                pp.setP19( roster.get("P19").toString());
                                pp.setP20( roster.get("P20").toString());
                                pp.setP21( roster.get("P21").toString());

                                pp.setB3_RapidConsent_Yes_No( roster.get("B3_RapidConsent_Yes_No").toString());
                                pp.setB3_Guardian( roster.get("B3_Guardian").toString());
                                pp.setB3_Date( roster.get("B3_Date").toString());
                                pp.setU15Rapid_Results( roster.get("U15Rapid_Result").toString());
                                pp.setRapid_Comment( roster.get("Rapid_Comment").toString());
                                pp.setBarcode( roster.get("BarCode").toString());




                                //INSERT ROSTER FOR THIS HOUSE  HOLD
                                myDB.insertSyncRoster(pp,hh.getAssignment_ID(),hh.getBatchNumber());

                            }

                            //Individual
                            JSONArray l= (JSONArray)jObject.get("ind");

                            for(int o=0;o<l.length();o++)
                            {
                                JSONObject roster = (JSONObject)l.get(o);

                                //PersonRoster
                                Individual ind = new Individual();
                                ind.setSRNO(Integer.parseInt(roster.get("SRNO").toString()));
                                ind.setIndBarcode(roster.get("BarCode").toString());
                                ind.setIndBarcode(roster.get("BarCode").toString());
                                ind.setQ101(roster.get("Q101").toString());
                                ind.setQ102(roster.get("Q102").toString());
                                ind.setQ103(roster.get("Q103").toString());
                                ind.setQ104(roster.get("Q104").toString());
                                ind.setQ104c(roster.get("Q104c").toString());
                                ind.setQ104cBISCED(roster.get("Q104cBISCED").toString());
                                ind.setQ401(roster.get("Q401").toString());ind.setQ101(roster.get("Q101").toString());
                                ind.setQ402(roster.get("Q402").toString());
                                ind.setQ402a(roster.get("Q402a").toString());
                                ind.setQ402b(roster.get("Q402b").toString());
                                ind.setQ403(roster.get("Q403").toString());
                                ind.setQ501(roster.get("Q501").toString());
                                ind.setQ502(roster.get("Q502").toString());
                                ind.setQ503(roster.get("Q503").toString());
                                //ind.setQ504_1(roster.get("Q504_1").toString());
                               // ind.setQ504_2(roster.get("Q504_2").toString());
                               // ind.setQ504_3(roster.get("Q504_3").toString());
                               // ind.setQ504_4(roster.get("Q504_4").toString());
                                //ind.setQ504_5(roster.get("Q504_5").toString());
                               // ind.setQ504_6(roster.get("Q504_6").toString());
                               // ind.setQ504_7(roster.get("Q504_7").toString());
                               // ind.setQ504_8(roster.get("Q504_8").toString());
                               // ind.setQ504_10(roster.get("Q504_10").toString());
                               // ind.setQ504_Other(roster.get("Q504_Other").toString());
                                //ind.setQ504_OtherSpecify(roster.get("Q504_OtherSpecify").toString());
                                //ind.setQ504(roster.get("Q501").toString());
                                //ind.setQ504Other(roster.get("Q504Other").toString());
                                ind.setQ904(roster.get("Q904").toString());
                                ind.setQ1101(roster.get("Q1101").toString());
                                ind.setQ1101a(roster.get("Q1101a").toString());
                                ind.setQ1101aOther(roster.get("Q1101aOther").toString());
                                ind.setQ1102(roster.get("Q1102").toString());
                                ind.setQ1102a(roster.get("Q1102a").toString());
                                ind.setQ1103(roster.get("Q1103").toString());
                                //ind.setQ1103a(roster.get("Q1103a").toString());
                                ind.setQ1104(roster.get("Q1104").toString());
                                ind.setQ1105(roster.get("Q1105").toString());
                                ind.setQ1106(roster.get("Q1106").toString());
                                ind.setQ1106a(roster.get("Q1106a").toString());
                                ind.setQ1106b(roster.get("Q1106b").toString());
                                ind.setQ1106bOther(roster.get("Q1106bOther").toString());
                                ind.setQ1107(roster.get("Q1107").toString());
                                //ind.setQ1107a(roster.get("Q1107a").toString());
                                ind.setQ1108(roster.get("Q1108").toString());
                                //ind.setQ1108a(roster.get("Q1108a").toString());
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
                                ind.setB8_Date(roster.get("B8_Date").toString());
                                ind.setB8_O15_Rapid(roster.get("B8_O15Rapid").toString());
                                ind.setQ801f(roster.get("Q801f").toString());
                                ind.setIndRapid_Comment(roster.get("RapidComment").toString());





                                //INSERT INDIVIDUALS FROM THIS HOUSE
                                myDB.insertSyncIndividual(ind,hh.getAssignment_ID() ,hh.getBatchNumber(),ind.getSRNO());



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

        @Override
        protected  void  onPreExecute(){
            //showProgress(true);
        }

        @Override
        protected String doInBackground(Void... voids)
        {
            String Response=null;
            try {

                String url="http://10.30.3.169:8080/WebService/syncSample?Username=" + preferences.getString("Username",null);
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

    //Synchronization EA Assignments
    private class SyncEAssgn extends AsyncTask<Void,Void,String> {

        @Override
        protected  void  onPreExecute(){
            //showProgress(true);
        }

        @Override
        protected String doInBackground(Void... voids)
        {
            String Response=null;
            try {

                String url="http://10.30.3.169:8080/WebService/syncAssign?Username=" + preferences.getString("Username",null);
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
            Intent intent=new Intent(Dashboard.this,Dashboard.class);
            startActivity(intent);
            finish();

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

    //Synchronization Sample
    private class syncDataToServer extends AsyncTask<Void,Void,String> {

        @Override
        protected  void  onPreExecute(){
            //showProgress(true);
        }

        @Override
        protected String doInBackground(Void... voids)
        {
            String Response=null;
            try {

                String url="http://10.30.3.169:8080/WebService/syncSample?Username=" + preferences.getString("Username",null);
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
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                        AlertDialog.Builder adBuilder = new AlertDialog.Builder(Dashboard.this)
                                .setTitle("Synchronization")
                                .setMessage("No sample received from Server")
                                .setNegativeButton("Done", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                        //call show() to build and show the AlertDialog.
                        AlertDialog ad = adBuilder.show();

                    }else
                    {
                        for(int i=0;i<jsnArray.length();i++)
                        {
                            String jsonItem = jsnArray.get(i).toString();


                            JSONObject jObject = new JSONObject(jsonItem);
                            //CREAT HOUSEHOLD

                            HouseHold hh = new HouseHold();
                            hh.setAssignment_ID(jObject.get("Assignment_ID").toString());
                            hh.setBatchNumber(jObject.get("BatchNumber").toString());

                            Gson gson = new Gson();
                            String json = gson.toJson(hh);

                            try{
                                URL url = new URL("http://10.30.3.169:8080/WebService/dataFromField");
                                //Open the connection here, and remember to close it when job its done.
                                URLConnection conn = url.openConnection();
                                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                                conn.setDoOutput(true);

                                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                                //theJSONYouWantToSend should be the JSONObject as String
                                wr.write(json);  //<--- sending data.

                                wr.flush();

                                //  Here you read any answer from server.
                                BufferedReader serverAnswer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                String line;
                                while ((line = serverAnswer.readLine()) != null) {

                                    System.out.println("LINE: " + line); //<--If any response from server
                                    //use it as you need, if server send something back you will get it here.
                                }

                                wr.close();
                                serverAnswer.close();

                            } catch (Exception e) {
                                Log.e("Cuack", "Something its wrong");
                            }

                            break;
                            /*
                            //Person Roster
                            JSONArray k = (JSONArray)jObject.get("roster");

                            for(int o=0;o<k.length();o++)
                            {
                                JSONObject roster = (JSONObject)k.get(o);
                                Log.d("SRNO", roster.get("SRNO").toString());
                                //PersonRoster
                                PersonRoster pp = new PersonRoster();
                                pp.setLineNumber( Integer.parseInt(roster.get("SRNO").toString()));

                            }

                            //Individual
                            JSONArray l= (JSONArray)jObject.get("ind");

                            for(int o=0;o<l.length();o++)
                            {
                                JSONObject roster = (JSONObject)l.get(o);
                                Log.d("SRNO", roster.get("SRNO").toString());
                                //PersonRoster
                                PersonRoster pp = new PersonRoster();
                                pp.setLineNumber( Integer.parseInt(roster.get("SRNO").toString()));

                            }

                            //Save to DB

                            */


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


}
