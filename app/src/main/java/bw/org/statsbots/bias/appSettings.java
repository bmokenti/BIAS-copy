package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class appSettings extends AppCompatActivity {
    EditText txt;
    SharedPreferences preferences;
    public static final String MY_PREFS_NAME = "bw.org.statsbots.bias.enumerator";
    SharedPreferences.Editor editor;
    Button btnSendTech;

    protected LibraryClass lib = new LibraryClass();
    public String readData(Context mcoContext,String sFileName){
        //String root = Environment.getExternalStorageDirectory().toString();
        File file = new File(mcoContext.getFilesDir(),"BaisDataLogs");

        StringBuilder text = new StringBuilder();
        File gpxfile = new File(file, sFileName);
        if(gpxfile.exists())
        {
            try{
                BufferedReader br = new BufferedReader(new FileReader(gpxfile));
                String line;
                while ((line = br.readLine()) != null){
                    text.append(line);
                    text.append('\n');
                }
                br.close();
            }
            catch(Exception e){
                //You'll need to add proper error handling here
                e.printStackTrace();
            }
        }

        return text.toString();
    }


    public String readJson(Context mcoContext,String sFileName){
        //String root = Environment.getExternalStorageDirectory().toString();
        File file = new File("json.txt");

        StringBuilder text = new StringBuilder();
        File gpxfile = new File(file, sFileName);
        if(gpxfile.exists())
        {
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null){
                    text.append(line);
                }
                br.close();
            }
            catch(Exception e){
                //You'll need to add proper error handling here
                e.printStackTrace();
            }
        }

        return text.toString();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.setTitle("BIAS V - Settings");

        txt = findViewById(R.id.settingtxt);
        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        txt.setText(preferences.getString("server_ip",null));
        Button btn = findViewById(R.id.settingbtn);
        Button btn1 = findViewById(R.id.button3);

        if(txt.getText().toString().length()>0){
           txt.setEnabled(false);
            btn.setEnabled(false);
        }

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setEnabled(true);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setEnabled(true);
            }
        });
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String text = txt.getText().toString();
                Log.d("on click", text);

                if(text.length()>0){
                    Log.d("sds", "greater than 0");
                    if(Patterns.WEB_URL.matcher(text).matches()){

                        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("server_ip", text);
                        editor.apply();

                        Context context = getApplicationContext();
                        CharSequence text1 = "Settings has been saved!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text1, duration);
                        toast.show();

                        Intent intent = new Intent(appSettings.this,login.class);
                        startActivity(intent);
                        finish();

                    }else{
                        lib.showError(appSettings.this,"Server URL Error","Wrong URL format");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                }
                else
                {
                    lib.showError(appSettings.this,"Server URL Error","URL length must be greater than 0 characters");
                    /**
                     * VIBRATE DEVICE
                     **/
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                }

            }
        });

        btnSendTech = findViewById(R.id.button5);

        btnSendTech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    String s = readData(appSettings.this,"ActivityLog.txt");
                    String json  = readJson(appSettings.this,"json.txt");

                    if(s.length()>0){


                        //send error log
                        Gson gson = new Gson();
                        AppErrors e = new AppErrors();
                        e.user=preferences.getString("Username",null)+ " "+preferences.getString("Name",null)+ " "+ preferences.getString("Surname_Name",null);
                        e.contents=s;
                        String Errorjson = gson.toJson(e);
                        writeToFile(Errorjson,appSettings.this,"TechErr.txt");

                        try
                        {
                            URL url = new URL(preferences.getString("server_ip",null)+"dataTech");
                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setDoOutput(true);
                            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                            conn.setRequestProperty("Accept", "application/json");

                            //json
                            OutputStream os = conn.getOutputStream();
                            os.write(Errorjson.getBytes());
                            os.flush();

                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    (conn.getInputStream())));
                            String output;



                            conn.disconnect();
                        } catch (Exception ee) {
                            ee.printStackTrace();
                            Log.d("Error in Send TechLog", ee.toString());

                        }


                        //send data json
                        try
                        {
                            URL url = new URL(preferences.getString("server_ip",null)+"techError");
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setDoOutput(true);
                            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                            conn.setRequestProperty("Accept", "application/json");

                            //json

                            OutputStream os = conn.getOutputStream();
                            os.write(json.getBytes());
                            os.flush();

                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    (conn.getInputStream())));
                            String output;



                            conn.disconnect();
                        } catch (Exception er) {
                            er.printStackTrace();
                            Log.d("Error in Send", e.toString());

                        }




                    }
                }catch (Exception d){
d.printStackTrace();
                }
            }
        });



    }


    private void writeToFile(String data,Context context,String fileName){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
