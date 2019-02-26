package bw.org.statsbots.bias;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import bw.org.statsbots.bias.google.zxing.integration.android.IntentIntegrator;
import bw.org.statsbots.bias.google.zxing.integration.android.IntentResult;

public class Barcode extends AppCompatActivity implements OnClickListener, Serializable {

    private Button scanBtn, btnNext, btnPrev;
    private TextView formatTxt, contentTxt;
    private PersonRoster p1 = null;
    LibraryClass lib;
    protected  DatabaseHelper myDB;
    protected HouseHold thisHouse;
    protected Individual individual;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_barcode);

            scanBtn = (Button)findViewById(R.id.scan_button);
            formatTxt = (TextView)findViewById(R.id.scan_format);
            contentTxt = (TextView)findViewById(R.id.scan_content);
          lib= new LibraryClass();
            //setTitle("Barcode scan for Individual");
            scanBtn.setOnClickListener(this);
            btnPrev = (Button)findViewById(R.id.barcd_btnPrev);

            Intent i = getIntent();
            individual = (Individual) i.getSerializableExtra("Individual");
            int p = 0;

            Intent h = getIntent();
            thisHouse = (HouseHold) h.getSerializableExtra("Household");

            Intent r = getIntent();
            p1 = (PersonRoster) r.getSerializableExtra("Personroster");


            myDB = new DatabaseHelper(this);
            myDB.onOpen(myDB.getReadableDatabase());

            final Individual ind = myDB.getdataIndivisual(p1.getAssignmentID(),p1.getBatch(),p1.getSRNO());
            individual = ind;

            if(Integer.valueOf(p1.getP04YY()) > 15)
            {
                setTitle("Barcode  Children < 15 years");
            }


            if(Integer.valueOf(p1.getP04YY()) > 15  && Integer.valueOf(p1.getP04YY()) < 64)
            {
                setTitle("Barcode scan for Adults 15 -64");
            }

            if(Integer.valueOf(p1.getP04YY()) > 64 )
            {
                setTitle("Barcode scan for Adults 64 plus");
            }
            if(Integer.valueOf(p1.getP04YY()) >= 10 && Integer.valueOf(p1.getP04YY()) <= 14 )
            {
                setTitle("Barcode scan for 10 - 14 years");
            }


            contentTxt.setText(individual.getIndBarcode());



            btnNext = (Button)findViewById(R.id.bar_btnNext);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ( contentTxt.getText()==null ||  contentTxt.getText().length() == 0) {
                        lib.showError(Barcode.this,"BarCode Error","Please scan the barcode for this individual to continue");

                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }
                    else {

                        try{
                            individual.setIndBarcode(contentTxt.getText().toString());
                            p1.setBarcode(contentTxt.getText().toString());
                            myDB.updateInd("IndBarcode",individual.getAssignmentID(),individual.getBatch(), individual.getIndBarcode(),String.valueOf(individual.getSRNO()));
                            myDB.updateConsents("Barcode", p1.getAssignmentID(), p1.getBatch(), p1.getBarcode(), String.valueOf(p1.getSRNO()));


                            myDB.close();


                        }catch(Exception dd){

                        }


/*
                        myDB = new DatabaseHelper(Barcode.this);
                        myDB.onOpen(myDB.getReadableDatabase());
                        //  myDB.updateRoster(thisHouse,"tRapidDate",p1.getRapidDate(), String.valueOf(p1.getSRNO()));
                        myDB.updateInd("IndBarcode", p1.getAssignmentID(), p1.getBatch(), p1.getBarcode(), String.valueOf(p1.getSRNO()));
                        myDB.close();*/
                        //finish();
                        Toast.makeText(getApplicationContext(),p1.getBarcode(),Toast.LENGTH_LONG);
                      Intent intent = new Intent(Barcode.this, HIVParentalConsent6wks_9y.class);
                        intent.putExtra("Individual", individual);
                        intent.putExtra("Personroster", p1);
                        startActivity(intent);

                    }

                }
            });
            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Barcode.super.onBackPressed();
                }


            });

        }

        public void onClick(View v){
            if(v.getId()==R.id.scan_button){

                if(individual.getIndBarcode()!=null){
                    Toast.makeText(getApplicationContext(),p1.getBarcode(),Toast.LENGTH_LONG);
                    Intent intent = new Intent(Barcode.this, HIVParentalConsent6wks_9y.class);
                    intent.putExtra("Individual", individual);
                    intent.putExtra("Personroster", p1);
                    startActivity(intent);
                }else{
                    IntentIntegrator scanIntegrator = new IntentIntegrator(this);
                    scanIntegrator.initiateScan();
                }

            }
        }




        public void onActivityResult(int requestCode, int resultCode, Intent intent) {
            IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            if (scanningResult != null) {
                String scanContent = scanningResult.getContents();
                String scanFormat = scanningResult.getFormatName();
                formatTxt.setText("FORMAT: " + scanFormat);
                contentTxt.setText(scanContent);
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "No scan data received!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }