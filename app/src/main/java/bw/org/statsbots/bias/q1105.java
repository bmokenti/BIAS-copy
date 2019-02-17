
package bw.org.statsbots.bias;

        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Vibrator;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Html;
        import android.util.Log;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.Serializable;

public class q1105 extends AppCompatActivity implements  Serializable {
    protected HouseHold thisHouse;
    protected RadioButton  Q1105_1, Q1105_2, selectedRbtn;
    PersonRoster p1 = null;
    Individual pp1 = null;
    protected  Individual individual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1105);
        setTitle("Q1105 ");


        Q1105_1 = findViewById(R.id.q1105_1);

        Q1105_2 = findViewById(R.id.q1105_2);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.q1105radioGroup);

        Intent i = getIntent();
        individual = (Individual) i.getSerializableExtra("Individual");
        int p = 0;

        Button btnNext = (Button) findViewById(R.id.btnNext);
        //btnNext.setText(btnLabel);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rg.getCheckedRadioButtonId();
                selectedRbtn = (RadioButton) findViewById(selectedId);

                if (selectedRbtn == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(q1105.this);
                    builder.setTitle("Select answer");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);

                    builder.setMessage("Does that sputum have blood in it?");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

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
                    //Set q1105 for the current individual

               individual.setQ1105(selectedRbtn.getText().toString().substring(0,1));


                        //Next question P04
                        Intent intent = new Intent(q1105.this, q1106.class);
                        intent.putExtra("Individual", individual);
                        startActivity(intent);



                }
            }
        });


        Button btprev = findViewById(R.id.p03_btnPrev);

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q1105.super.onBackPressed();
            }


        });
    }

}



