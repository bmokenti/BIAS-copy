package bw.org.statsbots.bias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class P18 extends AppCompatActivity implements Serializable {

    HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    ListView Allpersonslist;
    protected DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p18);
        setTitle("P18 INDIVIDUAL QUESTIONNAIRE (TB)");
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();
        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        thisHouse.getPersons();
        final Sample sample = myDB.getSample(myDB.getReadableDatabase(),thisHouse.getAssignment_ID());
        Button btnNext = (Button) findViewById(R.id.p18_btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sample.getStatusCode().equals("3")){
                    Intent intent = new Intent(P18.this, P21.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);
                }
                else if(sample.getStatusCode().equals("2") && thisHouse.getIsHIVTB40().equals("2"))
                {
                    Intent intent = new Intent(P18.this, P21.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);
                }
                else{
                    Log.d("Status",sample.getStatusCode());
                }
            }
        });

        List<String> p18 = new ArrayList<>();

        for (int r = 0; r < thisHouse.getTotalPersons(); r++) {
            p1 = thisHouse.getPersons()[r];
            if ((((Integer.valueOf(p1.getP04YY()) >= 15) && ((Integer.valueOf(p1.getP06()) == 1) || (Integer.valueOf(p1.getP06()) == 2)) )
                    || ((Integer.valueOf(p1.getP04YY())) >= 15 && (Integer.valueOf(p1.getP06()) == 3 && Integer.valueOf(p1.getP07()) >= 14))))
            {

                //add to listview
                p18.add(p1.getP01());
                //Set P02 fir the current individual
                thisHouse.getPersons()[p1.getLineNumber()].setP18("1");

            } else {
                //Set P02 fir the current individual
                thisHouse.getPersons()[p1.getLineNumber()].setP18("2");
                continue;
            }
        }
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, p18);

        Allpersonslist = (ListView) findViewById(R.id.personslist);
        Allpersonslist.setAdapter(itemsAdapter);

        Allpersonslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                Intent intent = new Intent(P18.this, q101.class);
                intent.putExtra("Household", thisHouse);
                startActivity(intent);
            }
        });


    }
}
