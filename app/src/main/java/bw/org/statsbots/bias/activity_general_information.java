package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class activity_general_information extends AppCompatActivity implements Serializable {
    protected EditText dwellingNum;
    protected EditText HHNumber;
    protected HouseHold thisHouse;
    protected LibraryClass lib;
    DatabaseHelper myDb;
    public TextView txtEAStatus, txtstratum_code, txtdistrict_name, txtdistrict_code, txtvillage_name, txtvillage_code, txtlocality_name, txtlocality_code,
            txtea_code, txtblock_number, txt_status_code, txtenumerators_code, txtsupervisors_code;
    public String  AssignmentID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("GENERAL INFORMATION");
        setContentView(R.layout.activity_general_information);
        lib = new LibraryClass();
        TextView txtTitle = (TextView)findViewById(R.id.generalInfo);
        txtTitle.setText(R.string.identification);

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");

        myDb = new DatabaseHelper(this);
        Sample s = myDb.getSample(myDb.getReadableDatabase(),thisHouse.getAssignment_ID());
        thisHouse = myDb.getHouseForUpdate(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber()).get(0);

        Log.d("Batch Number",thisHouse.getBatchNumber());

        txtstratum_code = (TextView)findViewById(R.id.stratum_code);
        txtdistrict_name = (TextView)findViewById(R.id.district_name);
        txtdistrict_code = (TextView)findViewById(R.id.district_code);
        txtvillage_name  = (TextView)findViewById(R.id.village_name);
        txtvillage_code  = (TextView)findViewById(R.id.village_code);
        txtlocality_name = (TextView)findViewById(R.id.locality_name);
        txtlocality_code = (TextView)findViewById(R.id.locality_code);
        txtea_code = (TextView)findViewById(R.id.ea_code1);
        txtblock_number = (TextView)findViewById(R.id.block_number);
        //txt_status_code = (TextView)findViewById(R.id.ea_status_code);
        txtEAStatus = (TextView) findViewById(R.id.ea_status_code);
        txtenumerators_code = (TextView)findViewById(R.id.enumerator_code);
        txtsupervisors_code = (TextView)findViewById(R.id.supervisor_code);

        dwellingNum = (EditText)findViewById(R.id.dwelling_number);
        HHNumber=(EditText)findViewById(R.id.household_number);

        txtstratum_code.setText(s.getStratumNo());
        txtdistrict_name.setText(s.getDistrictName());
        txtdistrict_code.setText(s.getDistrictCode());
        try
        {
            Log.d("------- ",s.getDistrictEAVillageLocality());
            String s1[] = s.getDistrictEAVillageLocality().split(":");

            txtvillage_name.setText(s1[1]);
            txtlocality_name.setText(s1[1]);
        }catch (Exception f){
            f.printStackTrace();
        }

        txtvillage_code.setText(s.getVillageCode());
        txtlocality_code.setText(s.getLocalityCode());

        txtea_code.setText(s.getEACode());
        txtblock_number.setText(s.getBlockNo());

         String st = thisHouse.getHIVTB40();
        String status = s.getStatusCode();
        if(st.equals("1")){
            status= status+ " (Part of HIV 40)";
        }
        //txt_status_code.setText(status);
        txtEAStatus.setText(status);



        //If update
        if(thisHouse.getDWELLING_NO()!=null && !thisHouse.getDWELLING_NO().isEmpty()){
            dwellingNum.setText(thisHouse.getDWELLING_NO());
        }

        if(thisHouse.getHH_NO()!=null && !thisHouse.getHH_NO().isEmpty()){
            HHNumber.setText(thisHouse.getHH_NO());
        }


        //setting values for cover page

        /**
         * INITIALIZE ALL THE HOUSE HOLD VARIABLES KNOWN
         */
        Button btnNext = (Button)findViewById(R.id.button);

        String btnLabel="";


        /**
         * NEXT BUTTON
         */
        btnNext.setText("Start Interview");
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                /**
                 * VALIDATION FOR PLOT/DWELLING NUMBER
                 */
                if(dwellingNum.getText().toString().length()<5)
                {
                    lib.showError(activity_general_information.this,"Dwelling Number Error","Please enter Dwelling Number 5 or more characters");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                    dwellingNum.requestFocus();
                }
                else
                    {
                        /**
                         * VALIDATION FOR HOUSEHOLD NUMBER
                         */
                    try{
                        int HHNO=Integer.parseInt(HHNumber.getText().toString());
                        if((HHNumber.getText().toString().length()>0 && HHNumber.getText().toString().length()<4) && HHNO!=0)
                        {
                            //set values of Dwelling Number and HouseHold Number
                            thisHouse.setDWELLING_NO(dwellingNum.getText().toString());
                            thisHouse.setHH_NO(HHNumber.getText().toString());

                            //Launch the Interviewer Visits Activity
                            Intent intent = new Intent(activity_general_information.this, InterviewersVisits.class);
                            intent.putExtra("Household", thisHouse);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            lib.showError(activity_general_information.this,"HouseHold Number Error","Please enter HouseHold Number 1 - 3 characters");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                            HHNumber.requestFocus();
                        }

                    }catch (Exception e){
                        lib.showError(activity_general_information.this,"HouseHold Number Error","Please enter HouseHold Number 1 - 3 characters");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                        HHNumber.requestFocus();
                    }

                }
            }

        });


        Button btnPrev = (Button)findViewById(R.id.button3);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

    }
}
