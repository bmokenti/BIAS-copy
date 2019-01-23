package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bw.org.statsbots.bias.google.zxing.integration.android.*;

public class ScanBarcode extends AppCompatActivity implements Serializable {

    private Button scanBtn;
    private TextView formatTxt, contentTxt;


    PersonRoster cont ;

    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);

        scanBtn = (Button)findViewById(R.id.scan_button);
        contentTxt = (TextView)findViewById(R.id.scan_content);

        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());

        Intent i = getIntent();
        cont =  (PersonRoster) i.getSerializableExtra("toscan");

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(ScanBarcode.this);
                scanIntegrator.initiateScan();

            }
        });


        ScrollView l = findViewById(R.id.scrollView3);
        LinearLayout linear = findViewById(R.id.RosterButton);


        if(cont.getU15Rapid_Results().isEmpty() ||
                cont.getU15Rapid_Results()==null ||
                cont.getU15Rapid_Results().equals("null"))
        {
            l.setVisibility(View.GONE);
            linear.setVisibility(View.VISIBLE);
            Log.d("rESULTS NULL", cont.getP04YY());


        }
        else {
            Log.d("Herre", cont.getP04YY());
            l.setVisibility(View.GONE);
            int yy = Integer.parseInt(cont.getP04YY());
            int mm = Integer.parseInt(cont.getP04MM());
            int wks = Integer.parseInt(cont.getP04WKS());
            if(yy==0 && mm<=1 && wks <2)
            {
                //less
            }
            else if(yy>9 && yy < 14) {

            }
            else if(yy>14 && yy < 18) {

            }
            else if(yy>17 && yy < 64) {

            }else if(yy > 63){

            }else{
                //6 weeks to 9 Years
                l.setVisibility(View.VISIBLE);
                linear.setVisibility(View.GONE);
                EditText EdtBarcode = findViewById(R.id.EdtBarcode);
                RadioGroup grp = findViewById(R.id.B3_Rapid_ConsentGroup);
                EditText lsGuard = findViewById(R.id.ListGuardians);
                EditText txtDate = findViewById(R.id.DateTxt);
                RadioGroup status = findViewById(R.id.bloodCollectStatusRGB);
                RadioGroup results = findViewById(R.id.ParentalradioGroup1);
                EditText comment = findViewById(R.id.RapidComment);

                EdtBarcode.setText(cont.getBarcode());
                EdtBarcode.setEnabled(false);
                //Set parental consent
                lsGuard.setText(cont.getHIVAssentParticipantsID());
                txtDate.setText(cont.getHIVAssentDate());
                //setn blood collected status
                results.check(Integer.valueOf(cont.getU15Rapid_Results()));
                comment.setText(cont.getRapid_Comment());



            }






        }




    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            if(scanFormat.equals("CODE_128") && scanContent.length()==8)
            {
                //formatTxt.setText("FORMAT: " + scanFormat);
                contentTxt.setText("INDIVIDUAL BARCODE : " + scanContent);

                //Check if the Barcode has been used
                List<PersonRoster> r = myDB.getPersonsBarCheck(cont.getAssignmentID());
                boolean hasbeenUsed=false;
                if(r.size()>0)
                {
                    /**
                     * Check if the barcode has been used in all the house holds given to the enumerator
                     */
                    for (PersonRoster p:r) {
                        if(p.getBarcode().equals(scanContent)){
                            hasbeenUsed=true;
                            break;
                        }
                    }

                    if(hasbeenUsed){

                        AlertDialog.Builder builder = new AlertDialog.Builder(ScanBarcode.this);
                        builder.setTitle("Barcode Exists Error!");
                        builder.setIcon(R.drawable.ic_warning_orange_24dp);

                        builder.setMessage("This barcode has been used before, \n Please discard or return any leftover used barcode");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Do nothing only when the Head of House is selected we proceed.

                            }
                        });

                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                        AlertDialog alertDialog =  builder.show();
                        final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                        positiveButtonLL.width=ViewGroup.LayoutParams.MATCH_PARENT;
                        positiveButton.setTextColor(Color.WHITE);
                        positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                        positiveButton.setLayoutParams(positiveButtonLL);


                    }
                    else
                        {
                        //save barcode and proceed to consent
                            List<PersonRoster> rr = myDB.getdataHhP(cont.getAssignmentID(),cont.getBatch());

                            PersonRoster person=null;
                            for (PersonRoster p:rr )
                            {
                                if(p.getSRNO() == Integer.valueOf(cont.getSRNO()))
                                {
                                    int yy = Integer.parseInt(p.getP04YY());
                                    int mm = Integer.parseInt(p.getP04MM());
                                    int wks = Integer.parseInt(p.getP04WKS());
                                    if(yy==0 && mm<=1 && wks <2)
                                    {
                                       //less
                                    }
                                    else if(yy>9 && yy < 14) {

                                    }
                                    else if(yy>14 && yy < 18) {

                                    }
                                    else if(yy>17 && yy < 64) {

                                    }else if(yy > 63){

                                    }else{
                                        //6 weeks to 9 Years

                                        Intent i = new Intent(ScanBarcode.this,HIV_Under_15_tests.class);
                                       // String[] persontoscan={thisHouse.getAssignment_ID(),thisHouse.getBatchNumber(),srno};
                                        //intent.putExtra("persontoscan",  persontoscan);
                                        startActivity(intent);

                                    }

                                    person = p;
                                    break;
                                }
                            }

                            if(person==null)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ScanBarcode.this);
                                builder.setTitle("Invdividual Does not Exists Error!");
                                builder.setIcon(R.drawable.ic_warning_orange_24dp);

                                builder.setMessage("Person does not exisit");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //Do nothing only when the Head of House is selected we proceed.

                                    }
                                });

                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);

                                AlertDialog alertDialog =  builder.show();
                                final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                                LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                                positiveButtonLL.width=ViewGroup.LayoutParams.MATCH_PARENT;
                                positiveButton.setTextColor(Color.WHITE);
                                positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                                positiveButton.setLayoutParams(positiveButtonLL);
                            }



                    }



                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScanBarcode.this);
                    builder.setTitle("Individual Error!");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("This household does not have any members");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Do nothing only when the Head of House is selected we proceed.

                        }
                    });

                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);

                    AlertDialog alertDialog =  builder.show();
                    final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                    positiveButtonLL.width=ViewGroup.LayoutParams.MATCH_PARENT;
                    positiveButton.setTextColor(Color.WHITE);
                    positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                    positiveButton.setLayoutParams(positiveButtonLL);



                }





            }
            else
                {

                AlertDialog.Builder builder = new AlertDialog.Builder(ScanBarcode.this);
                builder.setTitle("Individual Barcode Error!");
                builder.setIcon(R.drawable.ic_warning_orange_24dp);

                builder.setMessage("The scanned Barcode does not meet BAIS V individual barcode standard, retry");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Do nothing only when the Head of House is selected we proceed.

                    }
                });

                /**
                 * VIBRATE DEVICE
                 */
                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibs.vibrate(100);

                AlertDialog alertDialog =  builder.show();
                final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                positiveButtonLL.width=ViewGroup.LayoutParams.MATCH_PARENT;
                positiveButton.setTextColor(Color.WHITE);
                positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                positiveButton.setLayoutParams(positiveButtonLL);
            }

        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(ScanBarcode.this);
            builder.setTitle("Individual Barcode Error!");
            builder.setIcon(R.drawable.ic_warning_orange_24dp);

            builder.setMessage("No scan data has been received, retry");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //Do nothing only when the Head of House is selected we proceed.

                }
            });
            /**
             * VIBRATE DEVICE
             */
            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibs.vibrate(100);

            AlertDialog alertDialog =  builder.show();
            final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
            positiveButtonLL.width=ViewGroup.LayoutParams.MATCH_PARENT;
            positiveButton.setTextColor(Color.WHITE);
            positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
            positiveButton.setLayoutParams(positiveButtonLL);
        }
    }



}
