package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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

public class TabFragment3 extends Fragment implements Serializable {
    protected HouseHold thisHouse;
    protected LibraryClass lib;
    ArrayList<HouseHold> RejectedHH;
    ArrayList<HouseHold> completedHH;
    private ArrayAdapter<String> m_arrayAdapter;
    private ArrayList<String> hhDetails;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    protected DatabaseHelper myDB;
    private RecyclerView.LayoutManager mLayoutManager;
    TextView t;
    ArrayList<HouseHold>j = new ArrayList<>();

    private boolean checkDataBase(Context c)
    {
        SQLiteDatabase checkDB = null;
        try
        {
            myDB = new DatabaseHelper(c);
        }
        catch (SQLiteException e)
        {
            // database doesn't exist yet.
        }
        return checkDB != null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_3, container, false);


        t = (TextView)v.findViewById(R.id.started_heading);
        hhDetails=new ArrayList<>();

        myDB = new DatabaseHelper(container.getContext());
        myDB.onOpen(myDB.getWritableDatabase());
        myDB.getdataHouseInfo();
        completedHH = (ArrayList<HouseHold>) myDB.getCompleted();



        if(completedHH.size() !=0)
        {
            /***Started COLOR blues**/
            for(int h = 0; h< completedHH.size(); h++)
            {
                Sample s =  myDB.getSample(myDB.getReadableDatabase(), completedHH.get(h).getAssignment_ID());
                String[] l = s.getDistrictEAVillageLocality().split(":");

                hhDetails.add
                        (
                                "<b><i>Completed</i></b><br/>" +
                                        "<b>Locality:</b>" + l[1] + " " +
                                        "<br/><b>EA No:</b>" + s.EACode +
                                        "<br/><b>Assignment No:</b>" + completedHH.get(h).getAssignment_ID() +
                                        "<br/><b>Batch No:</b>" + completedHH.get(h).getBatchNumber() +
                                        "<br/><b>Dwelling No:</b>" + completedHH.get(h).getDWELLING_NO()+
                                        "<br/><b>House Hold No:</b>" + completedHH.get(h).getHH_NO()
                        );
                j.add(completedHH.get(h));
            }

            t.setText("There are  " +completedHH.size() + " completed Interviews, please synchronize to send them to your Supervisor" );
        }else {

            t.setText("There are currently no completed assignments");
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