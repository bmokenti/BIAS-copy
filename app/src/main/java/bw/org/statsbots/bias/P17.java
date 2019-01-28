package bw.org.statsbots.bias;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class P17 extends AppCompatActivity implements Serializable {

    HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    ListView Allpersonslist;
    protected DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p17);
        setTitle("P17 INDIVIDUAL QUESTIONNAIRE (HIV)");
        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();



        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        thisHouse.getPersons();
        final Sample sample = myDB.getSample(myDB.getReadableDatabase(),thisHouse.getAssignment_ID());
        Button btnNext = (Button) findViewById(R.id.p17_btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sample.EACode.equals("1") ) {
                    Intent intent = new Intent(P17.this, P20.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);
                }


            }

        });

        List<String> p17 = new ArrayList<>();

                for (int r = 0; r < thisHouse.getTotalPersons(); r++) {
                    p1 = thisHouse.getPersons()[r];
                    Sample s = myDB.getSample(myDB.getReadableDatabase(),thisHouse.getAssignment_ID());


                    if (Integer.valueOf(p1.getP04YY()) >= 15 && Integer.valueOf(p1.getP04YY()) <= 64 && s.EACode.equals("1") ) {

                        //add to listview
                        p17.add(p1.getP01());
                        //Set P02 fir the current individual
                        thisHouse.getPersons()[p1.getLineNumber()].setP17("1");

                    } else {
                        //Set P02 fir the current individual
                        thisHouse.getPersons()[p1.getLineNumber()].setP17("2");
                        continue;

                    }


                }
                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, p17);

                Allpersonslist = (ListView) findViewById(R.id.personslist);
                Allpersonslist.setAdapter(itemsAdapter);

        Allpersonslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                Intent intent = new Intent(P17.this, Barcode.class);
                intent.putExtra("Household", thisHouse);
                startActivity(intent);





            }
        });


            }
        }



