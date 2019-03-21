package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

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
        RejectedHH=new ArrayList<>();
        StartedHH=new ArrayList<>();

            myDB = new DatabaseHelper(container.getContext());
            myDB.onOpen(myDB.getWritableDatabase());

            try
            {
                myDB.getdataHouseInfo();
                RejectedHH = (ArrayList<HouseHold>) myDB.getReject();
                StartedHH = (ArrayList<HouseHold>) myDB.getStarted();
            }catch (SQLiteException e){
                if (e.getMessage().contains("no such table")){
                    Log.e(TAG, "No Household info available" );
                    // create table
                    // re-run query, etc.
                }
            }



            if(RejectedHH.size() != 0 )
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
                         "<br/><b>Assignment No:</b>" + RejectedHH.get(h).getAssignment_ID() +
                         "<br/><b>Batch No:</b>" + RejectedHH.get(h).getBatchNumber() +
                         "<br/><i style='text-color:#ff0000;'>Supervisor Comment:</i>" + RejectedHH.get(h).getSuperComment()
                    );
                    j.add(RejectedHH.get(h));
                }

                t.setText(Html.fromHtml("*There are "+ RejectedHH.size()+"  Rejected and " +StartedHH.size() + " Started Interviews <br/> *Press and hold a Household to mark it for final synchronization"));
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

                t.setText(Html.fromHtml("*There are "+ RejectedHH.size()+"  Rejected and " +StartedHH.size() + " Started Interviews <br/> *Press and hold a Household to mark it for final synchronization") );
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

                    HouseHold thisHouse = j.get(i);


                            Intent intent = new Intent(getActivity(), started_household.class);
                            String selectedFromList = (listView.getItemAtPosition(i).toString());

                            HouseHold h = j.get(i);


                            if (h == null) {

                            } else {
                                Log.d("Inside", "house hold not null");
                                intent.putExtra("Household", h);
                                startActivity(intent);
                            }


                }

            });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                try {
                    String hhd = hhDetails.get(i);
                    String[] sss = hhd.split("Assignment No:</b>");
                    String assid=sss[1].substring(0,8);

                    String[] ss = hhd.split("Batch No:</b>");

                    Log.d("Assignment ID:",assid);//Assignment ID
                    String checkBacth[] = ss[1].split("<br/>");
                    String batch=checkBacth[0]; //Batch number

                    List<HouseHold> hh = myDB.getHouseForUpdate(assid,batch);
                    thisHouse=hh.get(0);

                    if(thisHouse.getVISIT1_RESULT()==null && thisHouse.getVISIT2_RESULT()==null && thisHouse.getVISIT3_RESULT()!=null){
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setMessage("You cannot send a house without at least 1 Visit. Please record Visits before you can send the household");
                                alertDialogBuilder.setPositiveButton("Ok",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1) {
                                                Intent intent = new Intent(getContext(),started_household.class);
                                                intent.putExtra("Household",  thisHouse);
                                                startActivity(intent);
                                            }
                                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }else{
                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

                        alertDialogBuilder.setMessage("This action will remove this household from your tablet to you Supervisor on your next synchronization. Do you want to proceed?");
                        alertDialogBuilder.setPositiveButton("Send",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                        //Change the status of the Interviewer visit
                                        SQLiteDatabase db = myDB.getWritableDatabase();
                                        ContentValues hhValues = new ContentValues();
                                        hhValues.put("Interview_Status","10");
                                        hhValues.put("Clear","1");


                                        int i = db.update
                                                (   "House_Hold_Assignments", // table
                                                        hhValues, // column/value
                                                        "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                                                        new String[]{ String.valueOf(thisHouse.getAssignment_ID()),String.valueOf(thisHouse.getBatchNumber()) }
                                                );

                                        db.close();




                                        Intent intent = new Intent(getContext(),Dashboard.class);
                                        intent.putExtra("tbNumber", "1");
                                        startActivity(intent);
                                    }
                                });

                        alertDialogBuilder.setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                    }
                                });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
                        alertDialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_info_outline_blue_24dp);
                        //alertDialog.setContentView(R.layout.custom_dialog);
                        alertDialog.setTitle("Mark House for Final Sync");

                        alertDialog.show();
                    }




                }catch (Exception g){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setIcon(R.drawable.ic_error_red_24dp);
                    builder.setMessage("Error encounter while retrieving Assignment Id and Batch Number")
                            .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            });
                    // Create the AlertDialog object and return it
                    builder.create();
                }



                return true;
            }
        });

        return v;

    }

}
