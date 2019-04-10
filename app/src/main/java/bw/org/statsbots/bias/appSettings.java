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

public class appSettings extends AppCompatActivity {
    EditText txt;
    SharedPreferences preferences;
    public static final String MY_PREFS_NAME = "bw.org.statsbots.bias.enumerator";
    SharedPreferences.Editor editor;

    protected LibraryClass lib = new LibraryClass();

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



    }

}
