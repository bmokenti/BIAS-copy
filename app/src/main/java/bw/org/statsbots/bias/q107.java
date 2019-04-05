package bw.org.statsbots.bias;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class q107 extends AppCompatActivity implements Serializable{
    protected  HouseHold thisHouse;
    protected RadioGroup rg, rg1, rg2,rg3;
    protected RadioButton rbtn1, rbtn2, rbtnb1, rbtnb2 ,rbtnb3, rbtnb4, rbtnb5, rbtnbOther, rbtnc1, rbtnc2, rbtnc3,rbtnc4, rbtnc5,rbtncOther,selectedRbtn, selectedRbtn1, selectedRbtn2, selectedRbtn3;
    protected EditText txtyy, txtmnth, edtbOther, edtcOther;
    protected CheckBox chk99;
    protected LibraryClass lib;
    protected Individual individual;
    protected DatabaseHelper myDB;
    PersonRoster p1 = null;
    protected TextView txta, txtb, txtc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q107);

        setTitle("Q107: WORKED IN MINE");
        lib = new LibraryClass();

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");

        Intent ii = getIntent();
      p1 = (PersonRoster) ii.getSerializableExtra("Personroster");



        rg = findViewById(R.id.q107radioGroup);
        rg1 = findViewById(R.id.q107bradioGroup);
        rg2 = findViewById(R.id.q107cradioGroup);



        rbtn1 = findViewById(R.id.q107_1);
        rbtn2 = findViewById(R.id.q107_2);

        chk99 = findViewById(R.id.q107_99);

        rbtnb1 = findViewById(R.id.q107b_1);
        rbtnb2 = findViewById(R.id.q107b_2);
        rbtnb3 = findViewById(R.id.q107b_3);
        rbtnb4 = findViewById(R.id.q107b_4);
        rbtnb5 = findViewById(R.id.q107b_5);
        rbtnbOther = (RadioButton) findViewById(R.id.q107b_other);
        edtbOther = (EditText) findViewById(R.id.q107b_other1);


        rbtnc1 = findViewById(R.id.q107c_1);
        rbtnc2 = findViewById(R.id.q107c_2);
        rbtnc3 = findViewById(R.id.q107c_3);
        rbtnc4 = findViewById(R.id.q107c_4);
        rbtnc5 = findViewById(R.id.q107c_5);
        rbtncOther = findViewById(R.id.q107c_other);
        edtcOther = (EditText) findViewById(R.id.q107c_other1);

        txtyy = findViewById(R.id.q107a_years);
        txtmnth = findViewById(R.id.q107_months);

        txta = (TextView) findViewById(R.id.q107atxt) ;
        txtb = (TextView) findViewById(R.id.q107btxt) ;
         txtc = (TextView) findViewById(R.id.q107ctxt) ;

        myDB = new DatabaseHelper(q107.this);
        myDB.onOpen(myDB.getReadableDatabase());
        myDB.getWritableDatabase();

//        final Individual ind = myDB.getdataIndivisual(p1.getAssignmentID(),p1.getBatch(),p1.getSRNO());
//        individual = ind;

        if (p1 != null) {
            final Individual ind = myDB.getdataIndivisual(p1.getAssignmentID(), p1.getBatch(), p1.getSRNO());
            individual = ind;
        } else  {
            individual = myDB.getdataIndivisual(individual.getAssignmentID(), individual.getBatch(), individual.getSRNO());
        }

        final Individual ind = individual;


        final Sample sample = myDB.getSample(myDB.getReadableDatabase(), ind.getAssignmentID());
        sample.getSTATUS();

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        thisHouse = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch()).get(0);


     final List <PersonRoster>  roster = myDB.getdataHhP(ind.getAssignmentID(), ind.getBatch());
        for (PersonRoster p: roster
             ) {
            if (p.getSRNO() == ind.getSRNO()){
                p1 = p;
                break;
            }

        }
       // (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 )


      //  || ((sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1"))
        //        || ((p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 ) || p1.getP06().equals("2")))
        if ((sample.getStatusCode().equals("1")) || (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") &&
                (p1.getP07()  != null &&  Integer.parseInt(p1.getP07() ) < 14 )) ||
                (sample.getStatusCode().equals("2") && thisHous.get(0).getHIVTB40().equals("1") && p1.getP06().equals("2")))
        {
            individual.setQ107(null);
            individual.setQ107b(null);
            individual.setQ107c(null);
            individual.setQ107cOther(null);
            individual.setQ107aYY(null);
            individual.setQ107aMnth(null);
            individual.setQ107bOther(null);

            myDB.onOpen(myDB.getReadableDatabase());
            myDB.getWritableDatabase();
            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
            myDB.close();

            Intent q1o3 = new Intent(q107.this, Q201.class);
            q1o3.putExtra("Individual", individual);
            q1o3.putExtra("Personroster", p1);
            startActivity(q1o3);
        }

        chk99.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chk99.isChecked()) {
                    txtmnth.setEnabled(false);
                    txtyy.setEnabled(false);
                    txtmnth.setText("99");
                    txtyy.setText("99");



                }
                else
                {
                    txtmnth.setEnabled(true);
                    txtyy.setEnabled(true);
                    txtmnth.setText("");
                    txtyy.setText("");
                }
            }
        });


        rbtnbOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnbOther.isChecked()) {

                    edtbOther.setVisibility(View.VISIBLE);
                    // edtbOther.setText("");


                }
            }
        });

        rbtnb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnb1.isChecked()) {

                    edtbOther.setVisibility(View.INVISIBLE);
                  edtbOther.setText("");


                }
            }
        });
        rbtnb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnb2.isChecked()) {

                    edtbOther.setVisibility(View.INVISIBLE);
                   edtbOther.setText("");


                }
            }
        });
        rbtnb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnb3.isChecked()) {

                    edtbOther.setVisibility(View.INVISIBLE);
                    edtbOther.setText("");


                }
            }
        });
        rbtnb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnb4.isChecked()) {

                    edtbOther.setVisibility(View.INVISIBLE);
                    // edtbOther.setText("");


                }
            }
        });
        rbtnb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnb5.isChecked()) {

                    edtbOther.setVisibility(View.INVISIBLE);
                    edtbOther.setText("");


                }
            }
        });



        rbtncOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtncOther.isChecked()) {

                    edtcOther.setVisibility(View.VISIBLE);
                    //edtcOther.setText("");

                }
            }
        });

        rbtnc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnc1.isChecked()) {

                    edtcOther.setVisibility(View.INVISIBLE);
                    edtcOther.setText("");

                }
            }
        });

        rbtnc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnc2.isChecked()) {

                    edtcOther.setVisibility(View.INVISIBLE);
                    edtcOther.setText("");

                }
            }
        });

        rbtnc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnc3.isChecked()) {

                    edtcOther.setVisibility(View.INVISIBLE);
                    edtcOther.setText("");

                }
            }
        });
        rbtnc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnc4.isChecked()) {

                    edtcOther.setVisibility(View.INVISIBLE);
                    edtcOther.setText("");

                }
            }
        });
        rbtnc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnc5.isChecked()) {

                    edtcOther.setVisibility(View.INVISIBLE);
                    edtcOther.setText("");

                }
            }
        });


        RadioButton[] bt = new RadioButton[2];
        RadioGroup rg22 = findViewById(R.id.q107radioGroup);

        for(int f=0;f<rg22.getChildCount();f++)
        {
            View o = rg22.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ107()!= null &&  !ind.getQ107().equals(""))
                {
                    if(Integer.parseInt(ind.getQ107())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);

                        boolean checked = radioButton.isChecked();

                        switch(radioButton.getId()){
                            case R.id.q107_1:

                                if(checked)

                                    chk99.setEnabled(true);
                                rbtnb1.setEnabled(true);
                                rbtnb2.setEnabled(true);
                                rbtnb3.setEnabled(true);
                                rbtnb4.setEnabled(true);
                                rbtnb5.setEnabled(true);
                                rbtnbOther.setEnabled(true);
                                rbtnc1.setEnabled(true);
                                rbtnc2.setEnabled(true);
                                rbtnc3.setEnabled(true);
                                rbtnc4.setEnabled(true);
                                rbtnc5.setEnabled(true);
                                rbtncOther.setEnabled(true);
                                txtyy.setEnabled(true);
                                txtmnth.setEnabled(true);
                                txta.setTextColor(Color.BLACK);
                                txtb.setTextColor(Color.BLACK);
                                txtc.setTextColor(Color.BLACK);
                                //rbtn99.setChecked(true);





                                //  rGroup2.setVisibility(View.VISIBLE);

                                break;

                            case R.id.q107_2:
                                if(checked)

                                    chk99.setChecked(false);
                                chk99.setEnabled(false);
                                rbtnb1.setEnabled(false);
                                rbtnb2.setEnabled(false);
                                rbtnb3.setEnabled(false);
                                rbtnb4.setEnabled(false);
                                rbtnb5.setEnabled(false);
                                rbtnbOther.setEnabled(false);
                                rbtnc1.setEnabled(false);
                                rbtnc2.setEnabled(false);
                                rbtnc3.setEnabled(false);
                                rbtnc4.setEnabled(false);
                                rbtnc5.setEnabled(false);
                                rbtncOther.setEnabled(false);
                                txtyy.setEnabled(false);
                                txtmnth.setEnabled(false);

                                chk99.setChecked(false);
                                rbtnb1.setChecked(false);
                                rbtnb2.setChecked(false);
                                rbtnb3.setChecked(false);
                                rbtnb4.setChecked(false);
                                rbtnb5.setChecked(false);
                                rbtnbOther.setChecked(false);
                                rbtnc1.setChecked(false);
                                rbtnc2.setChecked(false);
                                rbtnc3.setChecked(false);
                                rbtnc4.setChecked(false);
                                rbtnc5.setChecked(false);
                                rbtncOther.setChecked(false);
                                txtyy.setText("");
                                txtmnth.setText("");

                                txta.setTextColor(Color.LTGRAY);
                                txtb.setTextColor(Color.LTGRAY);
                                txtc.setTextColor(Color.LTGRAY);



                                break;

                            case R.id.q107b_other:
                                if(checked)




                                    txtyy.setEnabled(false);
                                txtmnth.setEnabled(false);


                                break;

                            case R.id.q107c_other:
                                if(checked)



                                    txtyy.setEnabled(false);
                                txtmnth.setEnabled(false);




                                break;
                            case R.id.q107_99:
                                if(checked)



                                    txtyy.setEnabled(false);
                                txtmnth.setEnabled(false);
                                txtyy.setText("");
                                txtmnth.setText("");




                                break;
                        }
                        break;
                    }
                }
            }
            else
            {
                Log.d("h13 Lost Here","**********");
            }
        }

        if(ind.getQ107aYY()!=null){
            if(ind.getQ107aYY().equals("99")){
                chk99.setChecked(true);
            }
            txtyy.setText(ind.getQ107aYY());
        }
        if(ind.getQ107aMnth()!=null){
            if(ind.getQ107aMnth().equals("99")){
                chk99.setChecked(true);
            }
            txtmnth.setText(ind.getQ107aMnth());
        }

        if(individual.getQ107cOther()!=null ){
            if(individual.getQ107cOther().isEmpty()){

            }else{
                rbtncOther.setChecked(true);
                edtcOther.setText(individual.getQ107cOther());
                edtcOther.setVisibility(View.VISIBLE);
                edtcOther.setEnabled(true);
            }
        }

        if(individual.getQ107bOther()!=null ){
            if(individual.getQ107bOther().isEmpty()){

            }else{
                rbtnbOther.setChecked(true);
                edtbOther.setText(individual.getQ107bOther());
                edtbOther.setVisibility(View.VISIBLE);
                edtbOther.setEnabled(true);
            }
        }

        if(  ind.getQ107bOther() != null )
        {
            if ( ind.getQ107b() != null &&  ind.getQ107b().equals("O") )
            {
                rbtnbOther.setChecked(true);
                edtbOther.setText(ind.getQ107bOther());
            }
        }
        else
        {
        RadioGroup rgg = findViewById(R.id.q107bradioGroup);
        RadioButton[] bt5 = new RadioButton[6];
        for(int f=0;f<rgg.getChildCount();f++)
        {
            View o = rgg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt5[f]=((RadioButton)o);
                if(ind.getQ107b()!= null ) {
                    try {
                        if (Integer.parseInt(ind.getQ107b()) == f + 1) {
                            RadioButton radioButton = bt5[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    } catch (Exception ff) {


                    }
                }

                }
            }
        }


        if(  ind.getQ107cOther() != null )
        {
            if ( ind.getQ107c() != null &&  ind.getQ107c().equals("O") )
            {
                rbtncOther.setChecked(true);
                edtcOther.setText(ind.getQ107cOther());
            }
        }
        else
        {
        RadioGroup rg3 = findViewById(R.id.q107cradioGroup);
        RadioButton[] bt6 = new RadioButton[6];

        for(int f=0;f<rg3.getChildCount();f++)
        {
            View o = rg3.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt6[f]=((RadioButton)o);
                if(ind.getQ107c()!= null ) {
                    try {
                        if (Integer.parseInt(ind.getQ107c()) == f + 1) {
                            RadioButton radioButton = bt6[f];
                            radioButton.setChecked(true);
                            break;
                        }
                    } catch (Exception r) {

                    }
                }
                }
            }
        }
///

//        if(  thisHouse.getH03Other()!= null )
//        {
//            if (thisHouse.getH03() != null && thisHouse.getH03().equals("Ot") )
//            {
//                rbtn9.setChecked(true);
//                edt.setText(thisHouse.getH03Other());
//            }
//        }
//        else
//        {
//            RadioButton[] bt = new RadioButton[9];
//            for(int f=0;f<rg.getChildCount();f++)
//            {
//                View o = rg.getChildAt(f);
//                if (o instanceof RadioButton)
//                {
//                    bt[f]=((RadioButton)o);
//
//
//                    if( (thisHouse.getH03()!= null ) ) {
//                        if(thisHouse.getH03().equals("") ) {
//                            if (Integer.parseInt(thisHouse.getH03()) == f + 1) {
//                                RadioButton radioButton = bt[f];
//                                radioButton.setChecked(true);
//                                break;
//                            }
//                        }
//                    }
//
//                }
//            }
////            else
////            {
////                Log.d("Lost Here","**********");
////            }
//        }



        Button btnnext = findViewById(R.id.button);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {

                    lib.showError(q107.this, "Q107: Error", "Have you ever worked in a mine?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);


                } else {
                    // individual.setQ1106(selectedRbtn1.getText().toString().substring(0,1));
                    //int selectedId1 = rg1.getCheckedRadioButtonId();
                    //selectedRbtn1 = (RadioButton) findViewById(selectedId1);

                    if (rbtn1.isChecked() && !chk99.isChecked() && txtyy.length() == 0) {


                        lib.showError(q107.this, "Q107: Error: Year", "For how long have you worked in a mine?");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);


                    }
                    else {
                        // individual.setQ1106(selectedRbtn1.getText().toString().substring(0,1));
                        //int selectedId1 = rg1.getCheckedRadioButtonId();
                        //selectedRbtn1 = (RadioButton) findViewById(selectedId1);

                        if (rbtn1.isChecked() && !chk99.isChecked() && txtmnth.length() == 0) {


                            lib.showError(q107.this, "Q107: Error: Months", "Please provide months ");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);


                        } else {
                            // individual.setQ1106(selectedRbtn1.getText().toString().substring(0,1));
                            //int selectedId1 = rg1.getCheckedRadioButtonId();
                            //selectedRbtn1 = (RadioButton) findViewById(selectedId1);

                            if (!chk99.isChecked() && txtmnth.length() > 0 && Integer.valueOf(txtmnth.getText().toString()) > 11) {


                                lib.showError(q107.this, "Q107: Error: Months", "Months should be 12 or less ");
                                /**
                                 * VIBRATE DEVICE
                                 */
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);


                            } else {
                                //individual.setQ1106a(selectedRbtn2.getText().toString().substring(0,1));
                                int selectedId1 = rg1.getCheckedRadioButtonId();
                                selectedRbtn1 = (RadioButton) findViewById(selectedId1);

                                if (selectedRbtn1 == null && rbtn1.isChecked()) {

                                    lib.showError(q107.this, "Q107: Error", "b) What was your occupation?");
                                    /**
                                     * VIBRATE DEVICE
                                     */
                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    vibs.vibrate(100);


                                } else {
                                    //individual.setQ1106a(selectedRbtn2.getText().toString().substring(0,1));


                                    if (rbtnbOther.isChecked() && edtbOther.length() == 0) {

                                        lib.showError(q107.this, "Q107b: Error", "Please specify");
                                        /**
                                         * VIBRATE DEVICE
                                         */
                                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                        vibs.vibrate(100);


                                    } else {
                                        //individual.setQ1106b(selectedRbtn3.getText().toString().substring(0,1));
                                        int selectedId2 = rg2.getCheckedRadioButtonId();
                                        selectedRbtn2 = (RadioButton) findViewById(selectedId2);
                                        if (selectedRbtn2 == null && rbtn1.isChecked()) {
                                            lib.showError(q107.this, "Q107 Error", "What is /was the type of the commodity mined?");
                                            /**
                                             * VIBRATE DEVICE
                                             */
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);
                                        } else {
                                            //individual.setQ1106a(selectedRbtn2.getText().toString().substring(0,1));


                                            if (rbtncOther.isChecked() && edtcOther.length() == 0) {

                                                lib.showError(q107.this, "Q107c: Error", "Please specify");
                                                /**
                                                 * VIBRATE DEVICE
                                                 */
                                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                vibs.vibrate(100);


                                            } else {
                                                if (rbtn2.isChecked())
                                                {
                                                    individual.setQ107(selectedRbtn.getText().toString().substring(0, 1));
                                                    individual.setQ107b(null);
                                                    individual.setQ107c(null);
                                                    individual.setQ107cOther(null);
                                                    individual.setQ107aYY(null);
                                                    individual.setQ107aMnth(null);
                                                    individual.setQ107bOther(null);

                                                    myDB.onOpen(myDB.getReadableDatabase());
                                                    myDB.getWritableDatabase();
                                                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                                    myDB.close();

                                                    Intent q1o3 = new Intent(q107.this, Q201.class);
                                                    q1o3.putExtra("Individual", individual);
                                                    q1o3.putExtra("Personroster", p1);
                                                    startActivity(q1o3);


                                                } else {
                                                    individual.setQ107(selectedRbtn.getText().toString().substring(0, 1));

                                                    if(txtyy.getText().toString().length()==0){
                                                        individual.setQ107aYY("00");
                                                    }
                                                    else if(txtyy.getText().toString().length()==1){
                                                        individual.setQ107aYY("0"+txtyy.getText().toString());
                                                    }else{
                                                        individual.setQ107aYY(txtyy.getText().toString());
                                                    }

                                                    if(txtmnth.getText().toString().length()==0){
                                                        individual.setQ107aMnth("00");
                                                    }
                                                    else if(txtmnth.getText().toString().length()==1){
                                                        individual.setQ107aMnth("0"+txtmnth.getText().toString());
                                                    }else{
                                                        individual.setQ107aMnth(txtmnth.getText().toString());
                                                    }

                                                    individual.setQ107b(selectedRbtn1.getText().toString().substring(0, 1));
                                                    individual.setQ107bOther(edtbOther.getText().toString());
                                                    individual.setQ107c(selectedRbtn2.getText().toString().substring(0, 1));
                                                    individual.setQ107cOther(edtcOther.getText().toString());

                                                    myDB.onOpen(myDB.getReadableDatabase());
                                                    myDB.getWritableDatabase();
                                                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                                                    myDB.close();

                                                    Intent q1o3 = new Intent(q107.this, Q201.class);
                                                    q1o3.putExtra("Individual", individual);
                                                    q1o3.putExtra("Personroster", p1);

                                                    startActivity(q1o3);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (individual.getQ105() != null && (individual.getQ105().equals("1") || individual.getQ105().equals("2")
                        || individual.getQ105().equals("3") || (individual.getQ105().equals("4")))) {
                     finish();
                    Intent intent = new Intent(q107.this, q105.class);
                    intent.putExtra("Individual", individual);
                    intent.putExtra("Personroster", p1);
                    startActivity(intent);


                }
                else
                {
                    if (individual.getQ105() != null && (individual.getQ105().equals("8") ))
                    {
                        finish();
                        Intent intent = new Intent(q107.this, q105.class);
                        intent.putExtra("Individual", individual);
                        intent.putExtra("Personroster", p1);
                        startActivity(intent);
                    }
                    else {

                        finish();
                        Intent q1o2 = new Intent(q107.this, q106.class);
                        q1o2.putExtra("Personroster", p1);
                        startActivity(q1o2);
                    }
                }


            }
        });
    }



    public void onRadioButtonClicked(View v){



        // Is the current Radio Button checked?
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()){
            case R.id.q107_1:

                if(checked)

                chk99.setEnabled(true);
                rbtnb1.setEnabled(true);
                rbtnb2.setEnabled(true);
                rbtnb3.setEnabled(true);
                rbtnb4.setEnabled(true);
                rbtnb5.setEnabled(true);
                rbtnbOther.setEnabled(true);
                rbtnc1.setEnabled(true);
                rbtnc2.setEnabled(true);
                rbtnc3.setEnabled(true);
                rbtnc4.setEnabled(true);
                rbtnc5.setEnabled(true);
                rbtncOther.setEnabled(true);
                txtyy.setEnabled(true);
                txtmnth.setEnabled(true);
                txta.setTextColor(Color.BLACK);
                txtb.setTextColor(Color.BLACK);
                txtc.setTextColor(Color.BLACK);
                //rbtn99.setChecked(true);





                //  rGroup2.setVisibility(View.VISIBLE);

                break;

            case R.id.q107_2:
                if(checked)

                    chk99.setChecked(false);
                chk99.setEnabled(false);
                rbtnb1.setEnabled(false);
                rbtnb2.setEnabled(false);
                rbtnb3.setEnabled(false);
                rbtnb4.setEnabled(false);
                rbtnb5.setEnabled(false);
                rbtnbOther.setEnabled(false);
                rbtnc1.setEnabled(false);
                rbtnc2.setEnabled(false);
                rbtnc3.setEnabled(false);
                rbtnc4.setEnabled(false);
                rbtnc5.setEnabled(false);
                rbtncOther.setEnabled(false);
                txtyy.setEnabled(false);
                txtmnth.setEnabled(false);

                chk99.setChecked(false);
                rbtnb1.setChecked(false);
                rbtnb2.setChecked(false);
                rbtnb3.setChecked(false);
                rbtnb4.setChecked(false);
                rbtnb5.setChecked(false);
                rbtnbOther.setChecked(false);
                rbtnc1.setChecked(false);
                rbtnc2.setChecked(false);
                rbtnc3.setChecked(false);
                rbtnc4.setChecked(false);
                rbtnc5.setChecked(false);
                rbtncOther.setChecked(false);
                txtyy.setText("");
                txtmnth.setText("");

                txta.setTextColor(Color.LTGRAY);
                txtb.setTextColor(Color.LTGRAY);
                txtc.setTextColor(Color.LTGRAY);



                break;

            case R.id.q107b_other:
                if(checked)




                txtyy.setEnabled(false);
                txtmnth.setEnabled(false);


                break;

            case R.id.q107c_other:
                if(checked)



                    txtyy.setEnabled(false);
                txtmnth.setEnabled(false);




                break;
            case R.id.q107_99:
                if(checked)



                 txtyy.setEnabled(false);
                txtmnth.setEnabled(false);
                txtyy.setText("");
                txtmnth.setText("");




                break;
        }
    }

    public void onCheckboxClicked(View v) {


        // Is the current Radio Button checked?
        boolean checked = ((CheckBox) v).isChecked();

        switch (v.getId()) {
            case R.id.q107_99:

                if (checked)
                {

                }
                else {


                }


                    break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intervie_control, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {

            case R.id.pause:
                // Show the settings activity
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("[Demo!] Are you sure you want to pause the interview");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(getApplicationContext(), started_household.class);
                                intent.putExtra("Household", thisHouse);
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(q107.this).toBundle());

                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
