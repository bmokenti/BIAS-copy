package bw.org.statsbots.bias;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class P21 extends AppCompatActivity implements Serializable {
    HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
       DatabaseHelper myDB;
    ListView Allpersonslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p21);
        setTitle("P21 CHEST X-RAY");
        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        thisHouse.getPersons();


        myDB = new DatabaseHelper(this);
        Log.d("DB Name: ",myDB.getDatabaseName().toString() );
        myDB.onOpen(myDB.getWritableDatabase());

      /*myDB.dropTables(myDB.getWritableDatabase());
        myDB.createTables(myDB.getReadableDatabase());*/

        final Sample sample = myDB.getSample(myDB.getReadableDatabase(),thisHouse.getAssignment_ID());



        List<String> p21 = new ArrayList<>();

        for (int r = 0; r < thisHouse.getTotalPersons(); r++) {
            p1 = thisHouse.getPersons()[r];
            if (((Integer.valueOf(p1.getP04YY())>= 15) && (Integer.valueOf(p1.getP06())== 1 || Integer.valueOf(p1.getP06())== 2)) ||
                    (Integer.valueOf(p1.getP04YY())>=15 && Integer.valueOf(p1.getP06())== 3 && Integer.valueOf(p1.getP07())>=14)) {

                //add to listview
                p21.add(p1.getP01());
                //Set P02 fir the current individual
                thisHouse.getPersons()[p1.getLineNumber()].setP21("1");
                p1.setP21("1");

                myDB = new DatabaseHelper(P21.this);
                myDB.onOpen(myDB.getWritableDatabase());

                myDB.updateRoster(thisHouse,"P21",p1.getP21(), String.valueOf(p1.getSRNO()));
                myDB.close();

            } else {
                //Set P02 fir the current individual
                thisHouse.getPersons()[p1.getLineNumber()].setP21("2");

                p1.setP21("2");

                myDB = new DatabaseHelper(P21.this);
                myDB.onOpen(myDB.getWritableDatabase());

                myDB.updateRoster(thisHouse,"P21",p1.getP21(), String.valueOf(p1.getSRNO()));
                myDB.close();

                continue;
            }


        }
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, p21);

        Allpersonslist = (ListView) findViewById(R.id.personslist);
        Allpersonslist.setAdapter(itemsAdapter);
        Button btnNext = (Button)findViewById(R.id.p121_btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                //myDB = new DatabaseHelper(P21.this);
                //myDB.onOpen(myDB.getReadableDatabase());

                //UPDATE HOUSEHOLD
                //myDB.updateHouseholdAllColumns(myDB.getWritableDatabase(),thisHouse);

                Intent intent = new Intent(P21.this,H01.class);
                intent.putExtra("Household",  thisHouse);
                startActivity(intent);
            }
        });

        Button btnPrev = (Button)findViewById(R.id.p21_btnPrev);
       btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //thisHouse.previous = String.valueOf(p1.getSRNO());

                finish();

                //Intent intent = new Intent(P18.this, P17.class);
                //intent.putExtra("Household", thisHouse);
                //startActivity(intent);

            }
        });
    }
}
