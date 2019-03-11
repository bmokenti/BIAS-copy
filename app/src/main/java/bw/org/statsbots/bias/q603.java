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

import java.io.Serializable;
import java.util.List;

public class q603 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected Individual individual;
    protected LibraryClass lib;
    protected CheckBox ck1txt, ck2txt, ck3txt, ck4txt, ck5txt, ck6txt, ck7txt, ck8txt, ck9txt,chkOther, selected = null;
    protected Button btn;
    protected EditText q603edt;
    protected DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q603);

        setTitle("Q603: KNOWLEDGE ABOUT HIV/AIDS AND TB");
        lib = new LibraryClass();


        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        final Individual ind = myDB.getdataIndivisual(individual.getAssignmentID(),individual.getBatch(),individual.getSRNO());
        individual = ind;
        final List<HouseHold> thisHous = myDB.getHouseForUpdate(individual.getAssignmentID(),individual.getBatch());
        thisHous.get(0).getHIVTB40();

//btn = findViewById(R.id.btn);

        ck1txt = findViewById(R.id.q603_1);
        ck2txt = findViewById(R.id.q603_2);
        ck3txt = findViewById(R.id.q603_3);
        ck4txt = findViewById(R.id.q603_4);
        ck5txt = findViewById(R.id.q603_5);
        ck6txt = findViewById(R.id.q603_6);
        ck7txt = findViewById(R.id.q603_7);
        ck8txt = findViewById(R.id.q603_8);
        ck9txt = findViewById(R.id.q603_9);
        chkOther = findViewById(R.id.q603_other);
        q603edt = findViewById(R.id.q603edt_Other);

if(ck9txt.isChecked())
{
    ck1txt.setEnabled(false);
    ck2txt.setEnabled(false);
    ck3txt.setEnabled(false);
    ck4txt.setEnabled(false);
    ck5txt.setEnabled(false);
    ck6txt.setEnabled(false);
    ck7txt.setEnabled(false);
    ck8txt.setEnabled(false);
    chkOther.setEnabled(false);
    q603edt.setText("");

    ck2txt.setChecked(false);
    ck3txt.setChecked(false);
    ck4txt.setChecked(false);
    ck5txt.setChecked(false);
    ck6txt.setChecked(false);
    ck7txt.setChecked(false);
    ck8txt.setChecked(false);
    chkOther.setChecked(false);


}
else
{  ck1txt.setEnabled(true);
    ck2txt.setEnabled(true);
    ck3txt.setEnabled(true);
    ck4txt.setEnabled(true);
    ck5txt.setEnabled(true);
    ck6txt.setEnabled(true);
    ck7txt.setEnabled(true);
    ck8txt.setEnabled(true);
    chkOther.setEnabled(true);
}
        if(individual.getQ603_1()!= null &&  !individual.getQ603_1().equals(""))
        {
            if(Integer.parseInt(individual.getQ603_1())== 1)
            {
                ck1txt.setChecked(true);

            }else
            {
                ck1txt.setChecked(false);
            }
        }

        if(ind.getQ603_2()!= null &&  !ind.getQ603_2().equals(""))
        {
            if(Integer.parseInt(ind.getQ603_2())== 1)
            {
                ck2txt.setChecked(true);

            }else
            {
                ck2txt.setChecked(false);
            }
        }

        if(ind.getQ603_3()!= null &&  !ind.getQ603_3().equals(""))
        {
            if(Integer.parseInt(ind.getQ603_3())== 1)
            {
                ck3txt.setChecked(true);

            }else
            {
                ck3txt.setChecked(false);
            }
        }

        if(ind.getQ603_4()!= null &&  !ind.getQ603_4().equals(""))
        {
            if(Integer.parseInt(ind.getQ603_4())== 1)
            {
                ck4txt.setChecked(true);

            }else
            {
                ck4txt.setChecked(false);
            }
        }
        ////
        if(ind.getQ603_5()!= null &&  !ind.getQ603_5().equals(""))
        {
            if(Integer.parseInt(ind.getQ603_5())== 1)
            {
                ck5txt.setChecked(true);

            }else
            {
                ck5txt.setChecked(false);
            }
        }


        if(ind.getQ603_6()!= null &&  !ind.getQ603_6().equals(""))
        {
            if(Integer.parseInt(ind.getQ603_6())== 1)
            {
                ck6txt.setChecked(true);

            }else
            {
                ck6txt.setChecked(false);
            }
        }

        if(ind.getQ603_7()!= null &&  !ind.getQ603_7().equals(""))
        {
            if(Integer.parseInt(ind.getQ603_7())== 1)
            {
                ck7txt.setChecked(true);

            }else
            {
                ck7txt.setChecked(false);
            }
        }

        if(ind.getQ603_8()!= null &&  !ind.getQ603_8().equals(""))
        {
            if(Integer.parseInt(ind.getQ603_8())== 1)
            {
                ck8txt.setChecked(true);

            }else
            {
                ck8txt.setChecked(false);
            }
        }

        if(ind.getQ603_9()!= null &&  !ind.getQ603_9().equals(""))
        {
            if(Integer.parseInt(ind.getQ603_9())== 1)
            {
                ck9txt.setChecked(true);

            }else
            {
                ck9txt.setChecked(false);
            }
        }

        if(ind.getQ603_Other()!= null &&  !ind.getQ603_Other().equals(""))
        {
            if(Integer.parseInt(ind.getQ603_Other())== 1)
            {
                chkOther.setChecked(true);

            }else
            {
                chkOther.setChecked(false);
            }
        }

        if((ind.getQ603_Otherspecify()!= null &&  !ind.getQ603_Otherspecify().equals("")) &&  ind.getQ603_Otherspecify() != null )

        {
            q603edt.setText(ind.getQ504_OtherSpecify());
        }

        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((((!ck1txt.isChecked() && !ck2txt.isChecked() && !ck3txt.isChecked() && !ck4txt.isChecked() && !ck5txt.isChecked() && !ck6txt.isChecked() && !ck7txt.isChecked() && !ck8txt.isChecked() && !ck9txt.isChecked() && !chkOther.isChecked())))) {
                    lib.showError(q603.this, "Q603:", "How can people prevent becoming infected with HIV?"
                            +"Please select atleast one checkbox");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }  else
                if ((((chkOther.isChecked() && q603edt.length() ==0 )))) {
                    lib.showError(q603.this, "Q603:", "Please specify other or uncheck Other specify");
                    /**
                     * VIBRATE DEVICE
                     */
                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(100);
                }  else{
                    individual.setQ603_1(ck1txt.getText().toString().substring(0,1));
                     individual.setQ603_2(ck2txt.getText().toString().substring(0,1));
                   individual.setQ603_3(ck3txt.getText().toString().substring(0,1));
                      individual.setQ603_4(ck4txt.getText().toString().substring(0,1));
                     individual.setQ603_5(ck5txt.getText().toString().substring(0,1));
                      individual.setQ603_6(ck6txt.getText().toString().substring(0,1));
                     individual.setQ603_7(ck7txt.getText().toString().substring(0,1));
                    individual.setQ603_8(ck8txt.getText().toString().substring(0,1));
                     individual.setQ603_9(ck9txt.getText().toString().substring(0,1));
                     individual.setQ603_Other(chkOther.getText().toString().substring(0,1));
                     individual.setQ603_Otherspecify(q603edt.getText().toString());

                    if (ck1txt.isChecked()) {
                        individual.setQ603_1("1");
                    } else {
                        individual.setQ603_1("2");
                    }
                    if (ck2txt.isChecked()) {
                        individual.setQ603_2("1");
                    } else {
                        individual.setQ603_2("2");
                    }
                    if (ck3txt.isChecked()) {
                        individual.setQ603_3("1");
                    } else {
                        individual.setQ603_3("2");
                    }if (ck4txt.isChecked()) {
                        individual.setQ603_4("1");
                    } else {
                        individual.setQ603_4("2");
                    }if (ck5txt.isChecked()) {
                        individual.setQ603_5("1");
                    } else {
                        individual.setQ603_5("2");
                    }if (ck6txt.isChecked()) {
                        individual.setQ603_6("1");
                    } else {
                        individual.setQ603_6("2");
                    }if (ck7txt.isChecked()) {
                        individual.setQ603_7("1");
                    } else {
                        individual.setQ603_7("2");
                    }if (ck8txt.isChecked()) {
                        individual.setQ603_8("1");
                    } else {
                        individual.setQ603_8("2");
                    }if (ck9txt.isChecked()) {
                        individual.setQ603_9("1");
                    } else {
                        individual.setQ603_9("2");
                    }if (chkOther.isChecked()) {
                        individual.setQ603_Other("1");
                        individual.setQ603_Otherspecify(q603edt.getText().toString());

                    } else {
                        individual.setQ603_Other("2");
                    }

                    myDB.onOpen(myDB.getReadableDatabase());
                    myDB.getWritableDatabase();
                    myDB.updateIndividual(myDB.getWritableDatabase(),individual);
                    myDB.close();



                    Intent intent = new Intent(q603.this, q604.class);
                    intent.putExtra("Individual", individual);
                    startActivity(intent);
                }

            }

        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q603.super.onBackPressed();
            }


        });

    }



    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.q603_1:
                if (checked)
                    // Remove the meat
                    break;

            case R.id.q603_2:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q603_3:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q603_4:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q603_5:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q603_6:
                if (checked)
                    break;
            case R.id.q603_7:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q603_8:
                if (checked)
                    // Remove the meat
                    break;
            case R.id.q603_9:
                if (checked)

                    {
                        ck1txt.setEnabled(false);
                        ck2txt.setEnabled(false);
                        ck3txt.setEnabled(false);
                        ck4txt.setEnabled(false);
                        ck5txt.setEnabled(false);
                        ck6txt.setEnabled(false);
                        ck7txt.setEnabled(false);
                        ck8txt.setEnabled(false);
                        chkOther.setEnabled(false);
                        q603edt.setText("");

                        ck2txt.setChecked(false);
                        ck3txt.setChecked(false);
                        ck4txt.setChecked(false);
                        ck5txt.setChecked(false);
                        ck6txt.setChecked(false);
                        ck7txt.setChecked(false);
                        ck8txt.setChecked(false);
                        chkOther.setChecked(false);


                    }
                    else
                    {  ck1txt.setEnabled(true);
                        ck2txt.setEnabled(true);
                        ck3txt.setEnabled(true);
                        ck4txt.setEnabled(true);
                        ck5txt.setEnabled(true);
                        ck6txt.setEnabled(true);
                        ck7txt.setEnabled(true);
                        ck8txt.setEnabled(true);
                        chkOther.setEnabled(true);
                    }

                break;
            case R.id.q603_other:
                if (checked)
                     {
                        q603edt.setVisibility(View.VISIBLE);
                    }

                    // Put some meat on the sandwich
                    else
                    // Remove the meat
                    q603edt.setVisibility(View.INVISIBLE);
                    q603edt.setText("");

                    break;


                }
        }
    }
