package bw.org.statsbots.bias;


import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bw.org.statsbots.bias.google.zxing.integration.android.IntentIntegrator;
import bw.org.statsbots.bias.google.zxing.integration.android.IntentResult;

public class Barcode extends AppCompatActivity implements  Serializable {

    private Button scanBtn, btnNext, btnPrev;
    private TextView formatTxt, contentTxt;
    private PersonRoster p1 = null;
    PersonRoster cont;
    LibraryClass lib;
    protected DatabaseHelper myDB;
    protected HouseHold thisHouse;
    protected Individual individual;

    boolean hasbeenUsed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        scanBtn = (Button) findViewById(R.id.scan_button);
        formatTxt = (TextView) findViewById(R.id.scan_format);
        contentTxt = (TextView) findViewById(R.id.scan_content);
        lib = new LibraryClass();
        //setTitle("Barcode scan for Individual");

        btnPrev = (Button) findViewById(R.id.barcd_btnPrev);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        Intent h = getIntent();
        thisHouse = (HouseHold) h.getSerializableExtra("Household");

        Intent r = getIntent();
        p1 = (PersonRoster) r.getSerializableExtra("Personroster");


        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());

        final Individual ind = myDB.getdataIndivisual(p1.getAssignmentID(), p1.getBatch(), p1.getSRNO());
        individual = ind;

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), individual.getAssignmentID());
        sample.getSTATUS();

        thisHouse = myDB.getHouseForUpdate(p1.getAssignmentID(), p1.getBatch()).get(0);
        if (thisHouse == null) {
            thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(), individual.getBatch()).get(0);
        }

        if (p1.getBarcode() != null) {

            if (p1.getBarcode().toString().trim().length() == 0)
            {
                scanBtn.setEnabled(true);
            }else {
               scanBtn.setEnabled(false);
            }


        }

        if (Integer.valueOf(p1.getP04YY()) > 15) {
            setTitle("Barcode  Children < 15 years");
        }


        if (Integer.valueOf(p1.getP04YY()) > 15 && Integer.valueOf(p1.getP04YY()) < 64) {
            setTitle("Barcode scan for Adults 15 -64");
        }

        if (Integer.valueOf(p1.getP04YY()) > 64) {
            setTitle("Barcode scan for Adults 64 plus");
        }
        if (Integer.valueOf(p1.getP04YY()) >= 10 && Integer.valueOf(p1.getP04YY()) <= 14) {
            setTitle("Barcode scan for 10 - 14 years");
        }

//        if (sample.getStatusCode().equals("1") && (p1.getP06() != null && p1.getP06().equals("2") ) )
//
//        {
//            lib.showError(Barcode.this, "BarCode Error", "This Person does not qualify for Any Processes of HIV");
//
//            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//            vibs.vibrate(100);
//        }


        contentTxt.setText(individual.getIndBarcode());

        contentTxt.setText(p1.getBarcode());

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (individual.getIndBarcode() != null || p1.getBarcode() != null) {

                    List<HouseHold> thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(), individual.getBatch());

                    Intent intent1 = new Intent(Barcode.this, HIVParentalConsent6wks_9y.class);
                    intent1.putExtra("Household", thisHouse.get(0));
                    startActivity(intent1);

                }

                IntentIntegrator scanIntegrator = new IntentIntegrator(Barcode.this);
                scanIntegrator.initiateScan();

            }
        });

        btnNext = (Button) findViewById(R.id.bar_btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contentTxt.getText() == null || contentTxt.getText().length() == 0) {
                    lib.showError(Barcode.this, "BarCode Error", "Please scan the barcode for this individual to continue");

                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }else {
                    if (hasbeenUsed) {
                        lib.showError(Barcode.this, "BarCode Error", "Barcode has been used, try another one.");

                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {


//                IntentIntegrator scanIntegrator = new IntentIntegrator(Barcode.this);
//                scanIntegrator.initiateScan();

                        try {
                            individual.setIndBarcode(contentTxt.getText().toString());
                            p1.setBarcode(contentTxt.getText().toString());
                            myDB.updateInd("IndBarcode", individual.getAssignmentID(), individual.getBatch(), individual.getIndBarcode(), String.valueOf(individual.getSRNO()));
                            myDB.updateConsents("Barcode", p1.getAssignmentID(), p1.getBatch(), p1.getBarcode(), String.valueOf(p1.getSRNO()));
                            myDB.close();

                            Intent intent = new Intent(Barcode.this, HIVParentalConsent6wks_9y.class);
                            intent.putExtra("Individual", individual);
                            intent.putExtra("Personroster", p1);
                            startActivity(intent);

                        } catch (Exception dd) {

                        }

                        //retrieve scan result
                    }

                }
            }

        });
    }


            public void onActivityResult(int requestCode, int resultCode, Intent intent) {
                //retrieve scan result


                IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
                if (scanningResult != null) {
                    //we have a result
                    String scanContent = scanningResult.getContents();
                    String scanFormat = scanningResult.getFormatName();

                    if (scanFormat.equals("CODE_39") && scanContent.length() == 8) {
                        //formatTxt.setText("FORMAT: " + scanFormat);
                        contentTxt.setText(scanContent);

                        //Check if the Barcode has been used


                        final List<PersonRoster> r = myDB.getdataHhPBar();

                        if (r.size() > 0) {
                            /**
                             * Check if the barcode has been used in all the house holds given to the enumerator
                             */
                            for (PersonRoster p : r) {
                                if (p.getBarcode() != null) {

                                    if (p.getBarcode().toString().trim().length() == 0)
                                    {

                                    }else {
                                        if (p.getBarcode().equals(scanContent)) {
                                            hasbeenUsed = true;
                                            break;
                                        }
                                    }


                                }
                            }


                            if (hasbeenUsed) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(Barcode.this);
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

                                AlertDialog alertDialog = builder.show();
                                final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                                LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                                positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                                positiveButton.setTextColor(Color.WHITE);
                                positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                                positiveButton.setLayoutParams(positiveButtonLL);


                            } else {
                                //save barcode and proceed to consent
                                try {
                                    individual.setIndBarcode(contentTxt.getText().toString());
                                    p1.setBarcode(contentTxt.getText().toString());
                                    myDB.updateInd("IndBarcode", individual.getAssignmentID(), individual.getBatch(), individual.getIndBarcode(), String.valueOf(individual.getSRNO()));
                                    myDB.updateConsents("Barcode", p1.getAssignmentID(), p1.getBatch(), p1.getBarcode(), String.valueOf(p1.getSRNO()));


                                    myDB.close();


                                } catch (Exception dd) {

                                }


                            }
                        }


                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Barcode.this);
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

                        AlertDialog alertDialog = builder.show();
                        final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                        positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        positiveButton.setTextColor(Color.WHITE);
                        positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                        positiveButton.setLayoutParams(positiveButtonLL);


                    }


                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Barcode.this);
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

                    AlertDialog alertDialog = builder.show();
                    final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                    positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    positiveButton.setTextColor(Color.WHITE);
                    positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
                    positiveButton.setLayoutParams(positiveButtonLL);
                }


        }



            @Override
            public void onBackPressed() {
                Intent intent = new Intent(Barcode.this, started_household.class);
                intent.putExtra("Household", thisHouse);
                startActivity(intent);
            }

        }
