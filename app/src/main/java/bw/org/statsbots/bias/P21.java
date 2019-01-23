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

import java.util.ArrayList;
import java.util.List;

public class P21 extends AppCompatActivity {
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

      myDB.dropTables(myDB.getWritableDatabase());
        myDB.createTables(myDB.getReadableDatabase());




        List<String> p21 = new ArrayList<>();

        for (int r = 0; r < thisHouse.getTotalPersons(); r++) {
            p1 = thisHouse.getPersons()[r];
            if (((Integer.valueOf(p1.getP04YY())>= 15) && (Integer.valueOf(p1.getP06())== 1 || Integer.valueOf(p1.getP06())== 2)) ||
                    (Integer.valueOf(p1.getP04YY())>=15 && Integer.valueOf(p1.getP06())== 3 && Integer.valueOf(p1.getP07())>=14)) {

                //add to listview
                p21.add(p1.getP01());
                //Set P02 fir the current individual
                thisHouse.getPersons()[p1.getLineNumber()].setP21("1");

            } else {
                //Set P02 fir the current individual
                thisHouse.getPersons()[p1.getLineNumber()].setP21("2");
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
            public void onClick(View view) {

                boolean isInserted = myDB.insertHhroster(thisHouse);
                Log.d("DB Name: ",myDB.getDatabaseName().toString() );

                if(isInserted == true)
                    Toast.makeText(P21.this,"Data Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(P21.this,"Data not Inserted",Toast.LENGTH_LONG).show();
/*




                insertEAAssignment(
                        String EA_Assignment_ID,String STRATUM,String DISTRICT,String VILLAGE,String
                        LOCALITY,String EA,
                        String BLOCK_NO ,String EA_STATUS)*/
                Intent intent = new Intent(P21.this, Dashboard.class);
                intent.putExtra("Household", thisHouse);
                startActivity(intent);
            }
        });


        /*
        Button btnPrev = (Button)findViewById(R.id.p21_btnPrev);
       btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = myDB.getHhroster("HHP_ROSTER");
                if(res.getCount() == 0) {
                    // show message
                    //myDB.showMessage("Error","Nothing found");
                    Log.d("DB Number of Rows: ", String.valueOf(res.getCount()));
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("AssgnID:"+ res.getString(0)+"\n");
                    buffer.append("BatchNO:"+ res.getString(1)+"\n");
                    buffer.append("SRNO:"+ res.getString(2)+"\n");
                    buffer.append("P01:"+ res.getString(3)+"\n");
                    buffer.append("P02:"+ res.getString(4)+"\n");
                    buffer.append("P03:"+ res.getString(5)+"\n");
                    buffer.append("P04YY:"+ res.getString(6)+"\n");
                    buffer.append("P04MM:"+ res.getString(7)+"\n");
                    buffer.append("P04WKS:"+ res.getString(8)+"\n");
                    buffer.append("P05:"+ res.getString(9)+"\n");
                    buffer.append("P06:"+ res.getString(10)+"\n");
                    buffer.append("P07:"+ res.getString(11)+"\n");
                    buffer.append("P17:"+ res.getString(12)+"\n");
                    buffer.append("P18:"+ res.getString(13)+"\n");
                    buffer.append("P19:"+ res.getString(14)+"\n");
                    buffer.append("P20:"+ res.getString(15)+"\n");
                    buffer.append("P21:"+ res.getString(16)+"\n");



                }
                Log.d("DB Output: " , buffer.toString());
                // Show all data
                /// myDB.showMessage("Data",buffer.toString());
            }
        });*/
    }
}
