package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Layout;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class TabFragment2 extends Fragment implements Serializable {
    protected HouseHold thisHouse;
    protected LibraryClass lib;
    ArrayList<HouseHold> RejectedHH;
    ArrayList<HouseHold> StartedHH;
    private ArrayAdapter<String> m_arrayAdapter;
    private ArrayList<String> hhDetails;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    protected DatabaseHelper myDB;
    private RecyclerView.LayoutManager mLayoutManager;
    TextView t;
    ArrayList<HouseHold>j = new ArrayList<>();

    private boolean checkDataBase(Context c) {
        SQLiteDatabase checkDB = null;
        try {
            myDB = new DatabaseHelper(c);

        } catch (SQLiteException e) {
            // database doesn't exist yet.
        }
        return checkDB != null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_2, container, false);

        t = (TextView)v.findViewById(R.id.started_heading);
        hhDetails=new ArrayList<>();

            myDB = new DatabaseHelper(container.getContext());
            myDB.onOpen(myDB.getWritableDatabase());
            myDB.getdataHouseInfo();
            RejectedHH = (ArrayList<HouseHold>) myDB.getReject();
            StartedHH = (ArrayList<HouseHold>) myDB.getStarted();


            if(RejectedHH.size() !=0)
            {
                /***REJECTED COLOR ORANGE**/
                for(int h = 0; h< RejectedHH.size(); h++)
                {
                    Sample s =  myDB.getSample(myDB.getReadableDatabase(), RejectedHH.get(h).getAssignment_ID());
                    String[] l = s.getDistrictEAVillageLocality().split(":");

                    hhDetails.add
                    (
                            "<b><i>Rejected by Supervisor</i></b><br/>" +
                         "<b>Locality:</b>" + l[1] + " " +
                         "<br/><b>EA No:</b>" + s.EACode +
                         "<br/><b>Dwelling No:</b>" + RejectedHH.get(h).getDWELLING_NO()+
                         "<br/><b>House Hold No:</b>" + RejectedHH.get(h).getHH_NO()+
                         "<br/><i style='color:red;'>Supervisor Comment:</i>" + RejectedHH.get(h).getSuperComment()
                    );
                    j.add(RejectedHH.get(h));
                }

                t.setText("There are "+ RejectedHH.size()+"  rejected and " +StartedHH.size() + " started Interviews" );
            }
            else {

                t.setText("There are currently no started assignments");
            }

            if(StartedHH.size() !=0)
            {
                /***Started COLOR blues**/
                for(int h = 0; h< StartedHH.size(); h++)
                {
                    Sample s =  myDB.getSample(myDB.getReadableDatabase(), StartedHH.get(h).getAssignment_ID());
                    String[] l = s.getDistrictEAVillageLocality().split(":");

                    hhDetails.add
                            (
                                    "<b><i>Started</i></b><br/>" +
                                            "<b>Locality:</b>" + l[1] + " " +
                                            "<br/><b>EA No:</b>" + s.EACode +
                                            "<br/><b>Assignment No:</b>" + StartedHH.get(h).getAssignment_ID() +
                                            "<br/><b>Batch No:</b>" + StartedHH.get(h).getBatchNumber() +
                                            "<br/><b>Dwelling No:</b>" + StartedHH.get(h).getDWELLING_NO()+
                                            "<br/><b>House Hold No:</b>" + StartedHH.get(h).getHH_NO()
                            );
                    j.add(StartedHH.get(h));
                }


            }


            ArrayAdapter adapter = new ArrayAdapter<Spanned>(container.getContext(),R.layout.simplie_list_layout);
            final ListView listView = (ListView) v.findViewById(R.id.listview);

            for(String s:hhDetails)
            {
                adapter.add(Html.fromHtml(s));

            }
            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    //Show started Household to edit
                    Intent intent = new Intent(getActivity(),started_household.class);
                    String selectedFromList = (listView.getItemAtPosition(i).toString());
                    Log.d("Text: ",selectedFromList);

                    HouseHold h=j.get(i);


                    if(h==null){
                        Log.d("From started ","house hold null");
                    }
                    else{
                        intent.putExtra("Household",  h);
                        startActivity(intent);
                    }

                }
            });

        listView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                return false;
            }
        });

        return v;

    }

}
