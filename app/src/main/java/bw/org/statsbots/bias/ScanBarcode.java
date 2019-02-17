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

    private Button scanBtn; protected LibraryClass lib;
    private TextView formatTxt, contentTxt;
    protected Individual individual;


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

        lib = new LibraryClass();
        Intent i = getIntent();
        cont =  (PersonRoster) i.getSerializableExtra("Roster");

       // Log.d("Check ", cont.getP01());
        Intent intent = getIntent();
        individual = (Individual) intent.getSerializableExtra("Individual");
        int p = 0;

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(ScanBarcode.this);
                scanIntegrator.initiateScan();

            }
        });


        ScrollView l = findViewById(R.id.scrollView3);
        LinearLayout linear = findViewById(R.id.RosterButton);
        //contentTxt.setText("25534493");


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
                contentTxt.setText(scanContent);

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

                                    Individual individual = new Individual();
                                    individual.setAssignmentID(p.getAssignmentID());
                                    individual.setBatch(p.getBatch());
                                    individual.setSRNO(p.getSRNO());
                                    HouseHold intentHouse = null;
                                    List<HouseHold> hh = myDB.getStarted();
                                    for(HouseHold started:hh){
                                        if(started.getAssignment_ID().equals(p.getAssignmentID()) && started.getBatchNumber().equals(p.getBatch())){
                                            intentHouse = started;
                                            break;
                                        }
                                    }
                                    individual.setIndBarcode(contentTxt.getText().toString());

                                    Intent intent1 = new Intent(ScanBarcode.this,q101.class);
                                    intent.putExtra("Household",  intentHouse);
                                    startActivity(intent);


                                    /*int yy = Integer.parseInt(p.getP04YY());
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
*/                                    break;
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
