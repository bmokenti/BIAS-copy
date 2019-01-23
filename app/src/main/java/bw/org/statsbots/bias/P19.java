package bw.org.statsbots.bias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class P19 extends AppCompatActivity {
    HouseHold thisHouse;
    protected PersonRoster p1 = null;
    protected String currentHH = null;
    protected LibraryClass lib;
    ListView Allpersonslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p19);
        setTitle("P19 INDIVIDUAL QUESTIONNAIRE (HIVTB)");
        Intent i = getIntent();
        thisHouse = (HouseHold) i.getSerializableExtra("Household");
        thisHouse.getPersons();
        Button btnNext = (Button)findViewById(R.id.p19_btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(P19.this,P20.class);
                intent.putExtra("Household",  thisHouse);
                startActivity(intent);
            }
        });





        List<String> p19 = new ArrayList<>();

        for (int r = 0; r < thisHouse.getTotalPersons(); r++) {
            p1 = thisHouse.getPersons()[r];
            if ((Integer.valueOf(p1.getP04YY())>=15 && Integer.valueOf(p1.getP04YY())<=64 && (Integer.valueOf(p1.getP06())==1 )) ||
            ((Integer.valueOf(p1.getP04YY())>=15 && Integer.valueOf(p1.getP04YY())<=64) && (Integer.valueOf(p1.getP06())==3 && Integer.valueOf(p1.getP07())>=14)))
            {

                //add to listview
                p19.add(p1.getP01());
                //Set P02 fir the current individual
                thisHouse.getPersons()[p1.getLineNumber()].setP19("1");

            } else {
                //Set P02 fir the current individual
                thisHouse.getPersons()[p1.getLineNumber()].setP19("2");
                continue;
            }


        }
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, p19);

        Allpersonslist = (ListView) findViewById(R.id.personslist);
        Allpersonslist.setAdapter(itemsAdapter);
        Allpersonslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(P19.this, q101.class);
                intent.putExtra("Household", thisHouse);
                startActivity(intent);
            }
        });

    }
}
