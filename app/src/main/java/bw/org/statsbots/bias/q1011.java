package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class q1011 extends AppCompatActivity implements Serializable {

    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    protected  Individual individual;
    protected CheckBox ck1txt, ck2txt;
    protected Button btn;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtn8, rbtnOther ;
    protected RadioGroup rg ;
    protected TextView t1;protected  DatabaseHelper myDB;
    protected EditText edtOther;
    protected RadioButton selectedRbtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1011);



        setTitle("Q1011: CHILD BEARING");
        lib = new LibraryClass();

//btn = findViewById(R.id.btn);
        rg = (RadioGroup)findViewById(R.id.q1011radioGroup) ;
        rbtn1 = (RadioButton) findViewById(R.id.q1011_1);
        rbtn2 = (RadioButton) findViewById(R.id.q1011_2);
        rbtn3 = (RadioButton) findViewById(R.id.q1011_3);

        rbtn4 = (RadioButton) findViewById(R.id.q1011_4);
        rbtn5 = (RadioButton) findViewById(R.id.q1011_5);
        rbtn6 = (RadioButton) findViewById(R.id.q1011_6);
        rbtn7 = (RadioButton) findViewById(R.id.q1011_7);
        rbtn8 = (RadioButton) findViewById(R.id.q1011_8);

        rbtnOther = (RadioButton) findViewById(R.id.q1011_other);
        edtOther = (EditText) findViewById(R.id.q1011_other1);

        //rga = (RadioGroup)findViewById(R.id.q1010aGroup1) ;



        //rg = (RadioGroup) findViewById(R.id.q901radioGroup);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;

        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

        RadioButton[] bt = new RadioButton[9];
        for(int f=0;f<rg.getChildCount();f++)
        {
            View o = rg.getChildAt(f);
            if (o instanceof RadioButton)
            {
                bt[f]=((RadioButton)o);
                if(ind.getQ1011()!= null &&  !ind.getQ1011().equals(""))
                {
                    if(Integer.parseInt(ind.getQ1011())==f+1)
                    {
                        RadioButton radioButton = bt[f];
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }

        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    lib.showError(q1011.this, "Q1011: ERROR", "How did you feed your baby the first 6 months?");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                } else {

                    if (rbtnOther.isChecked()&& edtOther.length()==0) {
                        lib.showError(q1011.this, "Q1011: ERROR", "Other specify");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    } else {
                        if (rbtn8.isChecked())
                        {
                            individual.setQ1011(selectedRbtn.getText().toString().substring(0,1));

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();


                            Intent intent1 = new Intent(q1011.this, q1017.class);
                            intent1.putExtra("Individual", individual);
                            startActivity(intent1);
                        }
                        else {
                            individual.setQ1011(selectedRbtn.getText().toString().substring(0,1));
                            individual.setQ1011_Other(edtOther.getText().toString());

                            myDB.onOpen(myDB.getReadableDatabase());
                            myDB.getWritableDatabase();
                            myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                            myDB.close();


                            Intent intent = new Intent(q1011.this, q1012.class);
                            intent.putExtra("Individual", individual);
                            startActivity(intent);


                        }
                    }

                }
            }
        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(q1011.this, q1010.class);
                intent.putExtra("Individual", individual);
                startActivity(intent);
            }


        });
    }




    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
//
        switch (view.getId()) {

            case R.id.q1011_1:
                if(checked)




                    edtOther.setVisibility(View.INVISIBLE);
                edtOther.setText("");



                    break;

            case R.id.q1011_2:
                if(checked)

                    edtOther.setVisibility(View.INVISIBLE);
                edtOther.setText("");




                    break;

            case R.id.q1011_3:
                if(checked)

                    edtOther.setVisibility(View.INVISIBLE);
                edtOther.setText("");

                    break;
            case R.id.q1011_4:
                if(checked)
                    edtOther.setVisibility(View.INVISIBLE);
                edtOther.setText("");


                    break;

            case R.id.q1011_5:
                if(checked)

                    edtOther.setVisibility(View.INVISIBLE);
                edtOther.setText("");

                    break;

            case R.id.q1011_6:
                if(checked)

                    edtOther.setVisibility(View.INVISIBLE);
                edtOther.setText("");

                    break;

            case R.id.q1011_7:
                if(checked)

                    edtOther.setVisibility(View.INVISIBLE);
                edtOther.setText("");

                    break;

            case R.id.q1011_8:
                if(checked)

                    edtOther.setVisibility(View.INVISIBLE);
                edtOther.setText("");

                    break;


            case R.id.q1011_other:
                if(checked)

                    edtOther.setVisibility(View.VISIBLE);

                else


                break;




            default:

                break;

        }

    }


}

