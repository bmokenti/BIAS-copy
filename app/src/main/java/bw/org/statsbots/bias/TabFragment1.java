package bw.org.statsbots.bias;


import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Application;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class TabFragment1 extends Fragment implements Serializable {
    List<String> mobileArray = new ArrayList<String>();
    private ArrayList<String> _listEntrys = new ArrayList<String>();    // String-ArrayList for storing the itmes that should be shown in the list
    private ArrayAdapter<String> m_arrayAdapter;
    ListView mainListView;
    Context context;
    TextView tvTotal,User;
    public static final String MY_PREFS_NAME = "bw.org.statsbots.bias.enumerator";
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    DatabaseHelper myDb;

    private boolean checkDataBase(Context c) {
        SQLiteDatabase checkDB = null;
        try {
            myDb = new DatabaseHelper(c);

        } catch (SQLiteException e) {
            // database doesn't exist yet.
        }
        return checkDB != null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {
        View v =  inflater.inflate(R.layout.tab_fragment_1, container, false);
        //Read EA using Assignment ID join [EA_Assignments]
        myDb= new DatabaseHelper(container.getContext());


            try {
                mobileArray=myDb.getEA(myDb.getReadableDatabase());
            }catch(Exception e)
            {

            }

            final ArrayList<String> tmp = new ArrayList<>();

            for(int i=0;i<mobileArray.size();i++){
                String S[] = mobileArray.get(i).split("#");
                tmp.add(S[0]);
            }
            preferences = container.getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);


            tvTotal=(TextView)v.findViewById(R.id.EaCount);
            tvTotal.setText(Html.fromHtml("<b>Welcome "+ preferences.getString("Name",null)+ " "+preferences.getString("Surname_Name",null) + "</b> <br/> <i>You have <b>"+mobileArray.size()+" EA's</b> to be attended</i>"));

            ListView listView = (ListView)v.findViewById(R.id.ea_list);

            m_arrayAdapter = new ArrayAdapter<String>(container.getContext(), R.layout.ea_grouping_layout , tmp);
            listView.setAdapter(m_arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                {
                    //Pass the selected EA to the next activity to show list of assignments for that EA
                    String selectedEA = (String) adapterView.getItemAtPosition(i);
                    Intent intent = new Intent(container.getContext(),individual_assgn.class);
                    int s=0;
                    for(int ii =0;ii<tmp.size();ii++){

                        s = tmp.indexOf(selectedEA);

                    }
                    intent.putExtra("EA",mobileArray.get(s));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    {
                        // Apply activity transition
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());

                    } else {
                        // Swap without transition
                        startActivity(intent);
                    }

                }
            });


        return  v;
    }


    @Override
    public  void onResume(){
        super.onResume();

    }






}
