package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import bw.org.statsbots.bias.google.zxing.integration.android.IntentIntegrator;
import bw.org.statsbots.bias.google.zxing.integration.android.IntentResult;

public class Barcode extends AppCompatActivity implements View.OnClickListener,Serializable {
    private Button scanBtn, btnNext;
    private TextView formatTxt, contentTxt;
    private PersonRoster p1 = null;
    LibraryClass lib;
    protected HouseHold thisHouse;
    protected Individual individual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

       // TextView textView = (TextView) findViewById(R.id.P06);
       // String s = getResources().getString(R.string.P06);
       // int t = s.indexOf("#");
       // s = s.replace("#", "<b>" + p1.getP01() +"</b> ");

       // textView.setText(Html.fromHtml(s));



        scanBtn = (Button) findViewById(R.id.scan_button);
        formatTxt = (TextView) findViewById(R.id.scan_format);
        contentTxt = (TextView) findViewById(R.id.scan_content);
        btnNext = findViewById(R.id.bar_btnNext);

        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");



        scanBtn.setOnClickListener(this);
         btnNext.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if ( contentTxt.getText()==null ||  contentTxt.getText().length() == 0) {
                     lib.showError(Barcode.this,"BarCode Error","Please scan the barcode for this individual to continue");
                     /**
                      * VIBRATE DEVICE
                      */
                     Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                     vibs.vibrate(100);
                 }
                 else {

                       // individual.setIndBarcode(contentTxt.getText().toString());
                         Intent intent = new Intent(Barcode.this, q101.class);
                     intent.putExtra("Household", thisHouse);
                         startActivity(intent);

                 }

             }
         });

    }

    //@Override
    public void onClick(View v) {
        if (v.getId() == R.id.scan_button) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);

        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
