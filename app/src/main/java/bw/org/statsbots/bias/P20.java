package bw.org.statsbots.bias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class P20 extends AppCompatActivity implements Serializable {
    HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    ListView Allpersonslist;
    protected DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p20);
        setTitle("P20 BLOOD SPECIMEN");
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




        List<String> p20 = new ArrayList<>();

        for (int r = 0; r < thisHouse.getTotalPersons(); r++) {
            p1 = thisHouse.getPersons()[r];
            if (((Integer.valueOf(p1.getP04MM()) >= 1 && Integer.valueOf(p1.getP04WKS()) >= 2) || Integer.valueOf(p1.getP04YY())>=1 || Integer.valueOf(p1.getP04MM())>=2) &&
                     Integer.valueOf(p1.getP06())==1 || Integer.valueOf(p1.getP06())==3) {

                //add to listview
                p20.add(p1.getP01());
                //Set P02 fir the current individual
                thisHouse.getPersons()[p1.getLineNumber()].setP20("1");
                p1.setP20("1");

                myDB = new DatabaseHelper(P20.this);
                myDB.onOpen(myDB.getWritableDatabase());

                myDB.updateRoster(thisHouse,"P20",p1.getP20(), String.valueOf(p1.getSRNO()));
                myDB.close();



            } else {
                //Set P02 fir the current individual
                thisHouse.getPersons()[p1.getLineNumber()].setP20("2");
                p1.setP20("2");

                myDB = new DatabaseHelper(P20.this);
                myDB.onOpen(myDB.getWritableDatabase());

                myDB.updateRoster(thisHouse,"P20",p1.getP20(), String.valueOf(p1.getSRNO()));
                myDB.close();
                continue;
            }


        }
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, p20);

        Allpersonslist = (ListView) findViewById(R.id.personslist);
        Allpersonslist.setAdapter(itemsAdapter);
        Button btnNext = (Button)findViewById(R.id.p20_btnNext);
        Button btnPrev = (Button)findViewById(R.id.p03_btnPrev);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sample.getStatusCode().equals("3")){
                    Intent intent = new Intent(P20.this, P21.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);
                }
                else if(sample.getStatusCode().equals("2") && thisHouse.getHIVTB40().equals("1"))
                {
                    Intent intent = new Intent(P20.this, P21.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(P20.this, H01.class);
                    intent.putExtra("Household", thisHouse);
                    startActivity(intent);
                }
            }
        });


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
