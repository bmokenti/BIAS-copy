package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class H06 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    protected Individual individual;
    protected LibraryClass lib;
    protected EditText edt,norooms;
    protected RadioGroup rbtngroup;
    protected RadioButton selectedRbtn;
    PersonRoster p1 = null;
    Individual pp1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h06);
        setTitle("H06: NUMBER OF ROOMS");
        lib = new LibraryClass();

        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        int p = 0;

        Button btnext = findViewById(R.id.button);
        btnext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                edt = (EditText) findViewById(R.id.H02_ROOMS);
                int numRooms =0;

                if (edt.length() == 0 || edt.getText() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(H06.this);
                    builder.setTitle("Number of rooms");
                    builder.setIcon(R.drawable.ic_warning_orange_24dp);
                    builder.setMessage("Please enter number of rooms");
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
                    numRooms = Integer.parseInt(edt.getText().toString());
                    if(numRooms >=0 && numRooms <= 15 && numRooms <= Integer.parseInt(thisHouse.getH02())){
                        //True SAve
                        thisHouse.setH02(String.valueOf(numRooms));

                        Intent q1o2 = new Intent(H06.this, H07.class);
                        q1o2.putExtra("Household",  thisHouse);
                        startActivity(q1o2);

                    }else{
                        lib.showError(H06.this,"H06 Error","Number of rooms expected 0 to 15 only");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                    }





                }

            }
        });






    }
}
