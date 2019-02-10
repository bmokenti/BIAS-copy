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

import java.io.Serializable;

public class q617 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    private EditText edtOther;
    protected CheckBox chkOther;
    protected RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5, rbtn6, rbtn7, rbtn8, rbtn9, rbtn10, rbtn11, rbtn12, rbtn13, rbtn14, rbtn15, rbtn16, rbtn17, rbtn18, rbtn19, rbtn20, rbtn21, rbtn22, rbtn23, rbtn24, rbtnOther;
    protected RadioGroup  rg1, rg2, rg3, rg4, rg5, rg6, rg7, rg8;
    protected RadioButton selected1, selected2, selected3, selected4, selected5, selected6, selected7, selected8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q617);

        setTitle("Q617: KNOWLEDGE ABOUT HIV/AIDS AND TB ");
        lib = new LibraryClass();

        rg1 = (RadioGroup) findViewById(R.id.rg1) ;
        rg2 = (RadioGroup) findViewById(R.id.rg2) ;
        rg3 = (RadioGroup) findViewById(R.id.rg3) ;
        rg4 = (RadioGroup) findViewById(R.id.rg4) ;
        rg5 = (RadioGroup) findViewById(R.id.rg5) ;
        rg6 = (RadioGroup) findViewById(R.id.rg6) ;
        rg7 = (RadioGroup) findViewById(R.id.rg7) ;
        rg8 = (RadioGroup) findViewById(R.id.rg8) ;


        rbtn1 = (RadioButton) findViewById(R.id.rg1_01) ;
        rbtn2 = (RadioButton) findViewById(R.id.rg1_02) ;
        rbtn3 = (RadioButton) findViewById(R.id.rg1_03) ;

        rbtn4 = (RadioButton) findViewById(R.id.rg2_1) ;
        rbtn5 = (RadioButton) findViewById(R.id.rg2_2) ;
        rbtn6 = (RadioButton) findViewById(R.id.rg2_3) ;

        rbtn7 = (RadioButton) findViewById(R.id.rg3_1) ;
        rbtn8 = (RadioButton) findViewById(R.id.rg3_2) ;
        rbtn9 = (RadioButton) findViewById(R.id.rg3_3) ;

        rbtn10 = (RadioButton) findViewById(R.id.rg4_1) ;
        rbtn11 = (RadioButton) findViewById(R.id.rg4_2) ;
        rbtn12 = (RadioButton) findViewById(R.id.rg4_3) ;

        rbtn13 = (RadioButton) findViewById(R.id.rg5_1) ;
        rbtn14 = (RadioButton) findViewById(R.id.rg5_2) ;
        rbtn15 = (RadioButton) findViewById(R.id.rg5_3) ;

        rbtn16 = (RadioButton) findViewById(R.id.rg6_1) ;
        rbtn17 = (RadioButton) findViewById(R.id.rg6_2) ;
        rbtn18 = (RadioButton) findViewById(R.id.rg6_3) ;

        rbtn19 = (RadioButton) findViewById(R.id.rg7_1) ;
        rbtn20 = (RadioButton) findViewById(R.id.rg7_2) ;
        rbtn21 = (RadioButton) findViewById(R.id.rg7_3) ;

        rbtn22 = (RadioButton) findViewById(R.id.rg8_1) ;
        rbtn23 = (RadioButton) findViewById(R.id.rg8_2) ;
        rbtn24 = (RadioButton) findViewById(R.id.rg8_3) ;
        chkOther = (CheckBox) findViewById(R.id.checkOther) ;

        edtOther = (EditText) findViewById(R.id.Othertxt) ;


        if (chkOther.isChecked())
        {
            edtOther.setVisibility(View.VISIBLE);
        }
        else
        {
            edtOther.setVisibility(View.INVISIBLE);
            edtOther.setText("");
        }

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        Button btnnext = findViewById(R.id.button);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(q617.this, q618.class);
                intent.putExtra("Individual", individual);
                startActivity(intent);


            }
        });
        Button btprev = findViewById(R.id.button3);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q617.super.onBackPressed();
            }


        });
    }
}


//***********************************************************************************************
