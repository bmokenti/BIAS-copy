package bw.org.statsbots.bias;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;


import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.*;

public class login extends AppCompatActivity {
    private ProgressBar progressBar;
    SharedPreferences preferences;
    public static final String MY_PREFS_NAME = "bw.org.statsbots.bias.enumerator";
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final boolean userlogin_Status = preferences.getBoolean("is_logged", false);
        final String ipaddress = preferences.getString("server_ip", null);
        setContentView(R.layout.activity_login);



        //check if the ip address of server is null and prompt for it to be entered.
        if (ipaddress == null) {
            //initialize the ip address of the server
            editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("server_ip", "https://bais.statsbots.org.bw/webservice/");
            editor.apply();
            finish();
            Intent intent = new Intent(login.this,login.class);
            startActivity(intent);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            /*Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibs.vibrate(100);
            AlertDialog.Builder adBuilder = new AlertDialog.Builder(login.this)
                    .setTitle("Application Setting Error !")
                    .setMessage("No Synchronization End Point detected, Please configure end point first")
                    .setPositiveButton("appSettings", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(login.this,appSettings.class);
                            startActivity(intent);
                            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        }

                    });

            //call show() to build and show the AlertDialog.
            AlertDialog ad = adBuilder.show();*/
        }

                //Will be executed if there is no active session

                progressBar = (ProgressBar) findViewById(R.id.progressBar);


                /**
                 * Add LoginValidator Button
                 */
                final EditText txtusername = (EditText) findViewById(R.id.email);
                final EditText txtpassword = (EditText) findViewById(R.id.password);


                Button btnLogin = findViewById(R.id.btnLogin);
                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        if (ipaddress == null) {
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                            AlertDialog.Builder adBuilder = new AlertDialog.Builder(login.this)
                                    .setTitle("Application Setting Error !")
                                    .setMessage("Synchronization End Point not detected, Please configure end point first")
                                    .setPositiveButton("App Settings", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {

                                            Intent intent = new Intent(login.this,appSettings.class);
                                            startActivity(intent);
                                            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        }

                                    });

                            //call show() to build and show the AlertDialog.
                            AlertDialog ad = adBuilder.show();
                        }





                        //preLogin()
                        String username = txtusername.getText().toString();
                        String password = txtpassword.getText().toString();

                        LoginValidator loginValidator = new LoginValidator(username, password);
                        TextView txterror = (TextView) findViewById(R.id.login_error);

                        if (Validator.isNull(loginValidator.preLogin())) {
                            txterror.setText(loginValidator.preLogin()); //empty text from preLogin() method

                            //Check preferences
                            if (userlogin_Status == true) {
                                //proceed to Dashboard
                                Intent intentHome = new Intent(login.this, Dashboard.class);
                                // Check if we're running on Android 5.0 or higher
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    // Apply activity transition
                                    startActivity(intentHome, ActivityOptions.makeSceneTransitionAnimation(login.this).toBundle());

                                } else {
                                    // Swap without transition
                                    startActivity(intentHome);
                                    editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                    editor.putString("last_access", DateHelper.getDateTime().toString());
                                    editor.putBoolean("is_logged", true);
                                    editor.apply();
                                }
                            } else {

                                if(preferences.getString("Username",null)==null && preferences.getString("Password",null)==null){
                                    if (Validator.isNetworkAvailable(login.this)) {
                                        //Proceed connect to web service
                                        new UserLoginTask(username, password).execute();

                                    } else {
                                        //Request the user to enable network settings. Build the AlertDialog first.
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);
                                        AlertDialog.Builder adBuilder = new AlertDialog.Builder(login.this)
                                                .setTitle("Internet Connection !")
                                                .setMessage("No active internet connections detected. Please enable mobile data or connect to WIFI")
                                                .setPositiveButton("appSettings", new DialogInterface.OnClickListener() {

                                                    public void onClick(DialogInterface dialog, int which) {

                                                        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                                        login.this.startActivities(new Intent[]{intent});
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
                                }else{

                                    //Check from Shared Preferences
                                    if((preferences.getString("Username",null).equals(username) && preferences.getString("Password",null).equals(password)))
                                    {

                                        //proceed to Dashboard
                                        Intent intentHome = new Intent(login.this,Dashboard.class);
                                        // Check if we're running on Android 5.0 or higher
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                                        {
                                            // Apply activity transition
                                            editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                            editor.putString("last_access", DateHelper.getDateTime().toString());
                                            editor.putBoolean("is_logged", true);
                                            editor.apply();
                                            startActivity(intentHome, ActivityOptions.makeSceneTransitionAnimation(login.this).toBundle());

                                        }
                                        else
                                        {
                                            // Swap without transition
                                            startActivity(intentHome);
                                        }

                                    }else{

                                        //Request the user to enable network settings. Build the AlertDialog first.
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);
                                        AlertDialog.Builder adBuilder = new AlertDialog.Builder(login.this)
                                                .setTitle("Login Error !")
                                                .setMessage("Invalid login credentials, Please retry with correct details.")
                                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.cancel();
                                                    }
                                                });

                                        //call show() to build and show the AlertDialog.
                                        AlertDialog ad = adBuilder.show();
                                    }

                                }
                            }


                            //}

                        } else {
                            txterror.setText(loginValidator.preLogin());
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                        }
                    }
                });


    }



    /**This class queries the webservice{Process LoginValidator;}*/
    private class UserLoginTask extends AsyncTask<Void,Void,String> {
        private  final String UserName;
        private  final String PasswordHash;
        private HttpURLConnection conn;
        final String ipaddress = preferences.getString("server_ip", null);
        protected LibraryClass lib = new LibraryClass();

        UserLoginTask(String UserName, String PasswordHash){
            this.UserName=UserName;
            this.PasswordHash=PasswordHash;
        }

        @Override
        protected  void  onPreExecute(){
            showProgress(true);
        }
        @Override
        protected String doInBackground(Void... voids) {
            String Response=null;
            try
            {

                String url = ipaddress+"login?Username=" + UserName + "&password=" + PasswordHash;
                Log.d("Login",url);
                //String url="http://10.30.3.169:8080/Webservice/login?Username=" + UserName + "&password=" + PasswordHash;
                HttpHandler sh = new HttpHandler();
                String jsonStr = sh.makeServiceCall(url);

                if (jsonStr != null)
                {
                    Response=jsonStr;
                }

            }catch (Exception e){

            }

            return Response;
        }

        @Override
        protected void onPostExecute(String result){
            //mAuthTask = null
            showProgress(false);
            loginVerification(result);


        }


        /**
         * Method to verify that the loggin was successful by checking the HTTP response text
         * @param svrmsg
         */
        private void loginVerification(String svrmsg)
        {
            String savemsg="";
            svrmsg=svrmsg.substring(1,svrmsg.length()-1);
            svrmsg=svrmsg.replaceAll("\\\\","");
            svrmsg=svrmsg.trim();
             if (svrmsg != null)
            {

                try {
                    //JSONObject jsnbObject = new JSONObject(svrmsg);
                    JSONArray jsnArray = new JSONArray(svrmsg);

                    String jsonItem = jsnArray.get(0).toString();
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, Object> map = mapper.readValue(jsonItem, new TypeReference<Map<String,Object>>(){});

                    User user = new User();
                    user.setCode((String) map.get("Code"));
                    user.setName((String) map.get("Name"));
                    user.setPassword((String)map.get("password"));
                    user.setSName((String) map.get("SName"));
                    user.setContact((String) map.get("Contact"));
                    user.setQC_Code((String) map.get("QC_Code"));
                    user.setSuper_Code((String) map.get("Super_Code"));
                    user.setIs_Active((String) map.get("Is_Active"));

                    Log.d("Exception 1 :", svrmsg);
                    if(user.getCode().equals("0000")){
                        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("last_try", DateHelper.getDateTime().toString());
                        editor.putBoolean("is_logged",false);
                        editor.apply();

                        lib.showError(login.this,"Login Error","Login failed, please use correct credentials");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);


                    }else{
                        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("last_access", DateHelper.getDateTime().toString());
                        editor.putBoolean("is_logged",true);
                        editor.putString("Username",user.getCode());
                        editor.putString("Password",user.getPassword());
                        editor.putString("Name",user.getName());
                        editor.putString("Surname_Name",user.getSName());
                        editor.putString("Role",user.getRole());
                        editor.apply();
                        Intent intentHome = new Intent(login.this,Dashboard.class);
                        // Check if we're running on Android 5.0 or higherLog.d("Exception 1 :", "from JSONArray: ");
                        Log.d("Exception 1 :", "from JSONArray: ");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        {
                            // Apply activity transition
                            startActivity(intentHome, ActivityOptions.makeSceneTransitionAnimation(login.this).toBundle());

                        }
                        else
                        {Log.d("Exception 1 :", "from JSONArray: ");
                            // Swap without transition
                            startActivity(intentHome);
                        }
                    }




                } catch (Exception g) {
                    Log.d("Exception 1 :", "from JSONArray: "+ g.getStackTrace() + " ==== "+ g.getMessage());
                }


            }

        }

        /**
         * method to retrieve post  data
         * @param params
         * @return
         * @throws Exception
         */
        private String getPostDataString(JSONObject params) throws Exception
        {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            Iterator<String> itr = params.keys();

            while(itr.hasNext()){
                String key = itr.next();
                Object value = params.get(key);
                if(first)
                    first=false;
                else
                    result.append(",");

                result.append(URLEncoder.encode(key,"UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(),"UTF-8"));
            }//and getPostDataString

            Log.d("Url : " , result.toString());
            return result.toString();


        }
    }//end UserLoginTask


    /**
     * Shows or hides the progressbar
     * @param b
     */
    private void showProgress(boolean b) {
        if(b){
            progressBar.setVisibility(View.VISIBLE);

        }else{
            progressBar.setVisibility(View.INVISIBLE);
        }


    }

}
