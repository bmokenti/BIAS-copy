package bw.org.statsbots.bias;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.commons.io.comparator.LastModifiedFileComparator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class appSettings extends AppCompatActivity {
    EditText txt;
    SharedPreferences preferences;
    public static final String MY_PREFS_NAME = "bw.org.statsbots.bias.enumerator";
    SharedPreferences.Editor editor;
    Button btnSendTech,btnRestore;

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
            try
            {
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
        btnRestore = findViewById(R.id.button5);

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

        btnRestore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                final List<File> l  = getDir();

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(appSettings.this);
                //builderSingle.setIcon(R.drawable.ic_launcher);
                builderSingle.setTitle("Select One Name:-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(appSettings.this, android.R.layout.select_dialog_singlechoice);
                for(int i=0; i<5;i++){
                    if(i<l.size()){
                        String s = "BAISBak_"+l.get(i).getName();
                        arrayAdapter.add(s);
                    }


                }


                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(appSettings.this);
                        builderInner.setMessage(strName);
                        builderInner.setTitle("Are you sure you want to backup to this restore point?");
                        builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,int which) {
                               // Remove the main db and copy the backup to this point


                            }
                        });
                        builderInner.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,int which) {
                                dialog.dismiss();
                            }
                        });
                        builderInner.show();
                    }
                });
                builderSingle.show();


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

    /**
     * tHIS METHOD GET list of all the  available back up created since the EA started
     * @return
     */
    public List<File> getDir(){
        ContextWrapper c = new ContextWrapper(this);
        //String path = c.getFilesDir()+"/";
        String path= c.getFilesDir()+"/";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] f = directory.listFiles();

        Arrays.sort(f, LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);
        System.out.println("\nLast Modified Ascending Order (LASTMODIFIED_COMPARATOR)");

        //Arrays.sort(f, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
        //System.out.println("\nLast Modified Descending Order (LASTMODIFIED_REVERSE)");




        List<File> files = Arrays.asList(f) ;

        Log.d("Files", "Size: "+ files.size());
        for (int i = 0; i < files.size(); i++)
        {
            Log.d("Files", "FileName:" + files.get(i).getName());
        }
        return files;
    }

    public void BackUpd(){
        try{
            ContextWrapper c = new ContextWrapper(this);
            final String inFileName = c.getDatabasePath("BIAS.db").toString();
            Log.d("Location",inFileName);
            File dbFile = new File(inFileName);
            FileInputStream fis = new FileInputStream(dbFile);

            Calendar calendar = Calendar.getInstance();

            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            int currentMinute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            int date = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            String DBName= "BaisCopy"+date+month+year+second+".db";

            String outFileName = c.getFilesDir()+"/"+DBName;
            Log.d("Backup db", outFileName);
            // Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);

            // Transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer))>0){
                output.write(buffer, 0, length);
            }

            // Close the streams
            output.flush();
            output.close();
            fis.close();
            //Log.d("Backup Done","Failed to backup");
        }catch(Exception f){
            Log.d("Backup failed","Failed to backup"+f.toString());
        }


    }


}
