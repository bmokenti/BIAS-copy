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

        List<PersonRoster> list = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
        thisHouse.setHouseHoldeMembers(list.toArray(thisHouse.getHouseHoldeMembers()));

        if(thisHouse.next!=null){
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];

        }else if(thisHouse.previous!=null){
            p1 = thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];

        }



        Button btnNext = (Button) findViewById(R.id.p17_btnNext);
        Button btnPrev = (Button) findViewById(R.id.p03_btnPrev);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                              if ((sample.getStatusCode().equals("2") && thisHouse.getHIVTB40().equals("1")))
                {
                    Intent intent = new Intent(P17.this, P18.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);
                }
                else
                    {
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


                    if (((Integer.valueOf(p1.getP04YY()) >= 15 && Integer.valueOf(p1.getP04YY()) <= 64) && sample.getStatusCode().equals("1")
                            && (p1.getP06().equals("1") || p1.getP06().equals("3")))||
                            ((sample.getStatusCode().equals("2") && thisHouse.getHIVTB40().equals("1")) && (
                                    (p1.getP06().equals("3") && Integer.valueOf(p1.getP07()) < 14))&&
                                    Integer.valueOf(p1.getP04YY()) >= 15 && Integer.valueOf(p1.getP04YY()) <= 64) )
                            {

                        //add to listview
                        p17.add(p1.getP01());
                        //Set P02 fir the current individual
                        p1.setP17("1");
                        thisHouse.getPersons()[p1.getLineNumber()].setP17("1");

                        myDB = new DatabaseHelper(P17.this);
                        myDB.onOpen(myDB.getWritableDatabase());

                        myDB.updateRoster(thisHouse,"P17",p1.getP17(), String.valueOf(p1.getSRNO()));
                        myDB.close();



                    } else {
                        //Set P02 fir the current individual
                        p1.setP17("2");
                        thisHouse.getPersons()[p1.getLineNumber()].setP17("2");
                        myDB = new DatabaseHelper(P17.this);
                        myDB.onOpen(myDB.getWritableDatabase());

                        myDB.updateRoster(thisHouse,"P17",p1.getP17(), String.valueOf(p1.getSRNO()));
                        myDB.close();
                        continue;

                    }


                }
                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, p17);

                Allpersonslist = (ListView) findViewById(R.id.personslist);
                Allpersonslist.setAdapter(itemsAdapter);

        Allpersonslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

//                Intent intent = new Intent(P17.this, Barcode.class);
//                intent.putExtra("Household", thisHouse);
//                startActivity(intent);
//




            }
        });


        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //thisHouse.previous = String.valueOf(p1.getSRNO());


                finish();

                Intent intent = new Intent(P17.this, P16.class);
                intent.putExtra("Household", thisHouse);
                startActivity(intent);
            }
        });





            }
        }



