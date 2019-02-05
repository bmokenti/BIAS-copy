package bw.org.statsbots.bias;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class individual_assgn extends AppCompatActivity implements Serializable {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    protected LibraryClass lib;
    DatabaseHelper myDb;
    protected String selectedEA;
    protected String Assgn;
    protected String Stratum;
    protected String District;
    protected String Village;
    protected String Locality;
    protected String Block;
    protected String EAStatus;


    //This comes from the database
    private ArrayList<HouseHold> myAssignments;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invdividual_assgn);
        lib = new LibraryClass();

        //get intent and selected EA from previous page
        Intent i = getIntent();
        String l = (String)i.getSerializableExtra("EA");
        String ll[]=l.split("#");
        selectedEA=ll[0];
        Assgn=ll[1];
        Stratum=ll[2];
        District=ll[3];
        Village=ll[4];
        Locality=ll[5];
        Block=ll[6];
        EAStatus=ll[7];


        //Populate the Enumerator s assignments
        populateAssignments();

        //Set title with number of assignments
        if(myAssignments.size()==0){
            Toast.makeText(this,"You have no assignments",Toast.LENGTH_LONG);
            this.setTitle("BIAS V - You have no Households");
        }
        else
        {
            setTitle(selectedEA + " Assignments ("+ myAssignments.size()+")");
            recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view) ;

            // use this setting to
            // improve performance if you know that changes
            // in content do not change the layout size
            // of the RecyclerView
            recyclerView.setHasFixedSize(true);
            // use a linear layout manager
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);


            // define an adapter
            mAdapter = new MyAdapter(myAssignments);
            recyclerView.setAdapter(mAdapter);
        }




    }

    //Read from tbl_ssignmnts
    public void populateAssignments()
    {
        myDb= new DatabaseHelper(this);
        myAssignments=myDb.getHouseHold(myDb.getReadableDatabase(),selectedEA,Assgn);

    }


    /**
     * MyAdapter Class
     */
    public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder>
    {
        private ArrayList<HouseHold> values;
        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder
        {
            // each data item is just a string in this case
            public TextView tvAssigNo;
            public TextView tvDistrict;
            public TextView tvVillage;
            public TextView tvLocality;
            public TextView tvEA;
            public Button btnOpen;

            public View layout;

            public ViewHolder(View v)
            {
                super(v);
                layout = v;
                tvAssigNo = v.findViewById(R.id.tvAssignValue);
                tvDistrict = v.findViewById(R.id.tvDistrictValue);
                tvVillage = v.findViewById(R.id.tvVillageValue);
                tvLocality = v.findViewById(R.id.tvLocalityValue);
                tvEA = v.findViewById(R.id.tvEAValue);
                btnOpen = v.findViewById(R.id.OpenAssignmnt);
            }
        }

        public void add(int position, HouseHold item) {
            values.add(position, item);
            notifyItemInserted(position);
        }

        public void remove(int position) {
            values.remove(position);
            notifyItemRemoved(position);
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ArrayList<HouseHold>myDataset)
        {
            values = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            // create a new view
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.row_layout, parent, false);

            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position)
        {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element

            final HouseHold houseHold = values.get(position);
            Log.d("Position ***** ", "onBindViewHolder: "+ position);
            holder.tvAssigNo.setText(houseHold.getDWELLING_NO());
            Sample s = myDb.getSample(myDb.getReadableDatabase(),houseHold.getAssignment_ID());
            try
            {
                holder.tvDistrict.setText(s.getDistrictName());
                holder.tvVillage.setText(houseHold.getHH_NO());
                Log.d("########",s.getDistrictEAVillageLocality());
                if(s.getDistrictEAVillageLocality()!=null){
                    String[] loc = s.getDistrictEAVillageLocality().split(":");
                    holder.tvLocality.setText(loc[1]);
                }
                Log.d("########",s.getDistrictEAVillageLocality());
                holder.tvEA.setText(s.getEACode());
            }
            catch(Exception g)
            {
                g.printStackTrace();
            }


            //holder.txtHeader.setText(name);
        /*holder.txtHeader.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Open the assignment in a new activity


                remove(position);
            }
        });*/

            //holder.txtFooter.setText("Footer: " + name);

            holder.btnOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    HouseHold currentHH= myAssignments.get(position);

                    if(currentHH==null){
                        lib.showError(individual_assgn.this,"House Hold Error","Error encoutered when loading selected house hold");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);

                    }else{
                        //Launch Visits
                        Intent intent = new Intent(individual_assgn.this,activity_general_information.class);
                        intent.putExtra("Household",  currentHH);
                        startActivity(intent);
                    }


                }
            });


        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return values.size();
        }
    }



}