package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.Serializable;
import java.util.List;

public class P04 extends AppCompatActivity implements Serializable {
    protected HouseHold thisHouse;
    PersonRoster p1=null;
    protected DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p04);
        setTitle("P04 AGE");

        final TextView tvYY = (TextView)findViewById(R.id.tvyy) ;
        final EditText txtYY = (EditText)findViewById(R.id.txtyy);
        final TextView tvmm = (TextView)findViewById(R.id.tvmm);
        final EditText txtmm = (EditText)findViewById(R.id.txtmm);
        final TextView tvwks = (TextView)findViewById(R.id.tvwks);
        final EditText txtwks = (EditText)findViewById(R.id.txtwks);
        final LinearLayout P04Layout = (LinearLayout)findViewById(R.id.P04values);
        final LinearLayout commentLayout = (LinearLayout)findViewById(R.id.P04_LinearComment);
        final EditText txtComment = (EditText)findViewById(R.id.txtP04_Comment);

        // get your ToggleButton
        final RadioButton less = (RadioButton) findViewById(R.id.p04less);
        final RadioButton old = (RadioButton) findViewById(R.id.p04Greater);
        //b.setChecked(true);

        myDB = new DatabaseHelper(this);
        myDB.onOpen(myDB.getReadableDatabase());

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");
        p1 = thisHouse.getPersons()[0];

        //***************************Read Roster from Database and load it into Object thisHouse
        List<PersonRoster> list = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
        thisHouse.setHouseHoldeMembers(list.toArray(thisHouse.getHouseHoldeMembers()));


        if(thisHouse.next!=null)
        {
            //Next Members
            p1=thisHouse.getPersons()[Integer.parseInt(thisHouse.next)];
            thisHouse.setCurrentPerson(p1.getSRNO());
            //Log.d("#######",p1.getP04YY());




            if(p1.getP02().equals("00") || p1.getP02().equals("01") || p1.getP02().equals("05") || p1.getP02().equals("06")){
                less.setVisibility(View.INVISIBLE);
                txtYY.setVisibility(View.INVISIBLE);
            }else{
                less.setVisibility(View.VISIBLE);
                txtYY.setVisibility(View.VISIBLE);
            }



            if(p1.checkP04()==3)
            {
                txtYY.setHint("00");
                txtmm.setHint("00");
                txtwks.setHint("00");
            }

            if(p1.getP04YY()!=null){
                txtYY.setText(p1.getP04YY());
                if(Integer.parseInt(p1.getP04YY())>=2){
                    old.setChecked(true);
                    less.setChecked(false);
                    tvYY.setVisibility(View.VISIBLE);
                    txtYY.setVisibility(View.VISIBLE);

                    tvYY.setGravity(Gravity.CENTER);
                    txtYY.setGravity(Gravity.CENTER);
                    tvmm.setVisibility(View.INVISIBLE);
                    txtmm.setVisibility(View.INVISIBLE);
                    tvwks.setVisibility(View.INVISIBLE);
                    txtwks.setVisibility(View.INVISIBLE);

                }else if(Integer.parseInt(p1.getP04YY())<2){
                    less.setChecked(true);
                    old.setChecked(false);

                    tvYY.setVisibility(View.VISIBLE);
                    txtYY.setVisibility(View.VISIBLE);
                    tvmm.setVisibility(View.VISIBLE);
                    txtmm.setVisibility(View.VISIBLE);
                    tvwks.setVisibility(View.VISIBLE);
                    txtwks.setVisibility(View.VISIBLE);



                }


            }
            if(p1.getP04MM()!=null){
                txtmm.setText(p1.getP04MM());
            }
            if(p1.getP04WKS()!=null){
                txtwks.setText(p1.getP04WKS());
            }

        }

        /**
         ** IF PREVIOUS PERSON IS SELECTED
         * **/
        else if(thisHouse.previous!=null)
        {
            //Previous Members
            p1=thisHouse.getPersons()[Integer.parseInt(thisHouse.previous)];
            thisHouse.setCurrentPerson(p1.getSRNO());




            if(p1.getP02().equals("00") || p1.getP02().equals("01") || p1.getP02().equals("05") || p1.getP02().equals("06")){
                less.setVisibility(View.INVISIBLE);
                txtYY.setVisibility(View.INVISIBLE);
            }else{
                less.setVisibility(View.VISIBLE);
                txtYY.setVisibility(View.VISIBLE);
            }



            if(p1.checkP04()==3)
            {
                txtYY.setHint("00");
                txtmm.setHint("00");
                txtwks.setHint("00");

            }

            if(p1.getP04YY()!=null)
            {
                if(Integer.parseInt(p1.getP04YY())>=2){
                    old.setChecked(true);
                    less.setChecked(false);
                    tvYY.setVisibility(View.VISIBLE);
                    txtYY.setVisibility(View.VISIBLE);
                    tvYY.setGravity(Gravity.CENTER);
                    txtYY.setGravity(Gravity.CENTER);
                    tvmm.setVisibility(View.INVISIBLE);
                    txtmm.setVisibility(View.INVISIBLE);
                    tvwks.setVisibility(View.INVISIBLE);
                    txtwks.setVisibility(View.INVISIBLE);

                }else if(Integer.parseInt(p1.getP04YY())<2){
                    less.setChecked(true);
                    old.setChecked(false);

                    tvYY.setVisibility(View.VISIBLE);
                    txtYY.setVisibility(View.VISIBLE);
                    tvmm.setVisibility(View.VISIBLE);
                    txtmm.setVisibility(View.VISIBLE);
                    tvwks.setVisibility(View.VISIBLE);
                    txtwks.setVisibility(View.VISIBLE);



                }
                txtYY.setText(p1.getP04YY());
            }
            if(p1.getP04MM()!=null){
                txtmm.setText(p1.getP04MM());
            }
            if(p1.getP04WKS()!=null){
                txtwks.setText(p1.getP04WKS());
            }
        }


      /*  tvYY.setVisibility(View.INVISIBLE);
        txtYY.setVisibility(View.INVISIBLE);
        tvmm.setVisibility(View.INVISIBLE);
        txtmm.setVisibility(View.INVISIBLE);
        tvwks.setVisibility(View.INVISIBLE);
        txtwks.setVisibility(View.INVISIBLE);*/



        TextView textView = (TextView) findViewById(R.id.P04);
        String s = getResources().getString(R.string.P04);
        int t = s.indexOf("#");
        s = s.replace("#", "<b>" + p1.getP01() + "</b> ");
        textView.setText(Html.fromHtml(s));





        /**
         * Add press and hold to add comment
         */
        P04Layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(commentLayout.getVisibility()==View.VISIBLE){
                    commentLayout.setVisibility(View.INVISIBLE);
                }else{
                    commentLayout.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });


        /**
         * Handle the textbox focus to clear text or padd 00
         */
        txtYY.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    clearText(txtYY);
                }else{
                    if(txtYY.getText().length()==0){
                        txtYY.setText("00");
                    }
                }
            }
        });
        /**
         * Handle the textbox focus to clear text or padd 00
         */
        txtmm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    clearText(txtmm);
                }else{
                    if(txtmm.getText().length()==0){
                        txtmm.setText("00");
                    }
                }
            }
        });
        /**
         * Handle the textbox focus to clear text or padd 00
         */
       txtwks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View view, boolean b) {
               if(b){
                   clearText(txtwks);
               }else{
                   if(txtwks.getText().length()==0){
                       txtwks.setText("00");
                   }
               }
           }
       });


        /** attach an OnClickListener
         * for Ages Less than 2
         */
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(less.isChecked()){
                    old.setChecked(false);

                    tvYY.setVisibility(View.VISIBLE);
                    txtYY.setVisibility(View.VISIBLE);
                    tvmm.setVisibility(View.VISIBLE);
                    txtmm.setVisibility(View.VISIBLE);
                    tvwks.setVisibility(View.VISIBLE);
                    txtwks.setVisibility(View.VISIBLE);
                    if(txtYY.hasFocus()){
                        clearText(txtYY);
                    }
                    if(txtmm.hasFocus()){
                        clearText(txtmm);
                    }
                    if(txtwks.hasFocus()){
                        clearText(txtwks);
                    }
                }
            }
        });

        /**
         * Set onclick listener for 2 years and above
         */
        old.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(old.isChecked()){
                    less.setChecked(false);
                    tvYY.setVisibility(View.VISIBLE);
                    txtYY.setVisibility(View.VISIBLE);
                    tvYY.setGravity(Gravity.CENTER);
                    txtYY.setGravity(Gravity.CENTER);
                    tvmm.setVisibility(View.INVISIBLE);
                    txtmm.setVisibility(View.INVISIBLE);
                    tvwks.setVisibility(View.INVISIBLE);
                    txtwks.setVisibility(View.INVISIBLE);

                    if(txtYY.hasFocus()){
                        clearText(txtYY);
                    }
                    if(txtmm.hasFocus()){
                        clearText(txtmm);
                    }
                    if(txtwks.hasFocus()){
                        clearText(txtwks);
                    }

                }
            }
        });
        old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(old.isChecked()){
                    less.setChecked(false);
                    tvYY.setVisibility(View.VISIBLE);
                    txtYY.setVisibility(View.VISIBLE);
                    tvYY.setGravity(Gravity.CENTER);
                    txtYY.setGravity(Gravity.CENTER);
                    tvmm.setVisibility(View.INVISIBLE);
                    txtmm.setVisibility(View.INVISIBLE);
                    tvwks.setVisibility(View.INVISIBLE);
                    txtwks.setVisibility(View.INVISIBLE);

                    if(txtYY.hasFocus()){
                        clearText(txtYY);
                    }
                    if(txtmm.hasFocus()){
                        clearText(txtmm);
                    }
                    if(txtwks.hasFocus()){
                        clearText(txtwks);
                    }

                }
            }
        });




            /**
             * NEXT Person BUTTON
             */
            Button btnNext = (Button)findViewById(R.id.p03_btnNext);
            Button btnPrev = (Button)findViewById(R.id.p03_btnPrev);
            String btnLabel="";

            if(p1.getLineNumber()+1==thisHouse.getTotalPersons()){
                btnLabel="Next";
            }else{
                btnLabel="Next > "+ thisHouse.getPersons()[p1.getLineNumber()+1].getP01();
            }
            btnNext.setText(btnLabel);

            /**
             * set onclick listener for Next Button
             */
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    final LibraryClass lib = new LibraryClass();
                    boolean checkComplete=false;

                    RadioButton selectedRbtn=null;

                    //Crosschek the text for each textbox
                    if(txtYY.getText().toString().length()==0){
                        txtYY.setText("00");
                    }if(txtmm.getText().toString().length()==0){
                        txtmm.setText("00");
                    }if(txtwks.getText().toString().length()==0){
                        txtwks.setText("00");
                    }

                    /**
                     * Individuals 2 and above
                     */
                    if(old.isChecked())
                    {
                        /**
                         * AGE FOR LESS THAN 2 YEARS && OVER 2 YEARS VALIDATION
                         */
                        if((txtYY.getText().length()>0) && (Integer.valueOf(txtYY.getText().toString())>=2 && Integer.valueOf(txtYY.getText().toString())<=99))
                        {
                            //---------------proceed

                            if(p1.getP02().equals("00") && (Integer.valueOf(txtYY.getText().toString())<12) )
                            {
                                String errmsg =  p1.getP01() +", Head of House's age must be 12 Years or more";
                                String title = "Age of Head of House";
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                                lib.showError(P04.this,title,errmsg);

                            }
                            else
                                {
                                PersonRoster p;
                                for(int r=0; r<thisHouse.getTotalPersons();r++)
                                {
                                    p= thisHouse.getPersons()[r];

                                    if(p.getP02().equals("00") && p.getP04YY()!=null)
                                    {
                                        String relationship = p.getP02(); //relationship Head
                                        String AgeYY = p.getP04YY();
                                        int age = Integer.parseInt(AgeYY);//Age of Head

                                        int ageCurInd = Integer.valueOf(txtYY.getText().toString());//Age of current Individual
                                        String relationCurInd = p1.getP02(); //Current indivudual s Relationship to Head

                                        if(relationCurInd.equals("02") &&
                                                ((age-ageCurInd) <12) &&
                                                (txtComment.getText().toString().length()==0) &&
                                                (ageCurInd!=99))
                                        {
                                            /**
                                             * Son/Daughter Relationship to Head
                                             */
                                            String errmsg =  p1.getP01() +", Son/Daughter of "+ p.getP01() +"'s age difference must be 12 Years or more. \n\n Please provide comment if this is not the case ";
                                            String title = "Age relation to Head of House";

                                            AlertDialog.Builder builder = new AlertDialog.Builder(P04.this);
                                            builder.setTitle(title);
                                            builder.setIcon(R.drawable.ic_warning_orange_24dp);
                                            builder.setMessage(errmsg);
                                            builder.setPositiveButton("Comment", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    //Allow Comment
                                                    commentLayout.setVisibility(View.VISIBLE);
                                                    txtComment.requestFocus();


                                                }
                                            });
                                            builder.setNegativeButton("Ammend", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    //Do nothing
                                                    commentLayout.setVisibility(View.INVISIBLE);

                                                }
                                            });

                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);

                                            AlertDialog alertDialog =  builder.show();
                                            final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                                            final Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                                            LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                                            LinearLayout.LayoutParams negativeButtonLL = (LinearLayout.LayoutParams) negativeButton.getLayoutParams();

                                            positiveButton.setTextColor(Color.WHITE);
                                            negativeButton.setTextColor(Color.WHITE);

                                            positiveButton.setBackgroundColor(Color.parseColor("#3180e9"));
                                            negativeButton.setBackgroundColor(Color.parseColor("#3180e9"));

                                            positiveButtonLL.leftMargin=10;

                                            negativeButtonLL.weight = 10;
                                            positiveButtonLL.weight = 10;

                                            positiveButton.setLayoutParams(positiveButtonLL);
                                            negativeButton.setLayoutParams(negativeButtonLL);
                                            break;
                                        }
                                        else if(relationCurInd.equals("04") &&
                                                    ((age-ageCurInd) <24) &&
                                                    (txtComment.getText().toString().length()==0) &&
                                                    (ageCurInd!=99))
                                            {
                                                /**
                                                 * Grandchild Relationship to Head
                                                 */
                                                String errmsg =  p1.getP01() +", Grandchild of "+ p.getP01() +"'s age difference must be 24 Years or more. \n\n Please provide comment if this is not the case ";
                                                String title = "Age relation to Head of House";

                                                AlertDialog.Builder builder = new AlertDialog.Builder(P04.this);
                                                builder.setTitle(title);
                                                builder.setIcon(R.drawable.ic_warning_orange_24dp);
                                                builder.setMessage(errmsg);
                                                builder.setPositiveButton("Comment", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        //Allow Comment
                                                        commentLayout.setVisibility(View.VISIBLE);
                                                        txtComment.requestFocus();


                                                    }
                                                });
                                                builder.setNegativeButton("Ammend", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        //Do nothing
                                                        commentLayout.setVisibility(View.INVISIBLE);

                                                    }
                                                });

                                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                vibs.vibrate(100);

                                                AlertDialog alertDialog =  builder.show();
                                                final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                                                final Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                                                LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                                                LinearLayout.LayoutParams negativeButtonLL = (LinearLayout.LayoutParams) negativeButton.getLayoutParams();

                                                positiveButton.setTextColor(Color.WHITE);
                                                negativeButton.setTextColor(Color.WHITE);

                                                positiveButton.setBackgroundColor(Color.parseColor("#3180e9"));
                                                negativeButton.setBackgroundColor(Color.parseColor("#3180e9"));

                                                positiveButtonLL.leftMargin=10;

                                                negativeButtonLL.weight = 10;
                                                positiveButtonLL.weight = 10;

                                                positiveButton.setLayoutParams(positiveButtonLL);
                                                negativeButton.setLayoutParams(negativeButtonLL);
                                                break;

                                            }
                                        else if(relationCurInd.equals("06") &&
                                                ((ageCurInd-age) < 24) &&
                                                (txtComment.getText().toString().length()==0) &&
                                                (ageCurInd!=99))
                                        {
                                            /**
                                             * Grandparent Relationship to Head
                                             */
                                            String errmsg =  p1.getP01() +", Grandparent of "+ p.getP01() +"'s age difference must be 24 Years or more. \n\n Please provide comment if this is not the case ";
                                            String title = "Age relation to Head of House";

                                            AlertDialog.Builder builder = new AlertDialog.Builder(P04.this);
                                            builder.setTitle(title);
                                            builder.setIcon(R.drawable.ic_warning_orange_24dp);
                                            builder.setMessage(errmsg);
                                            builder.setPositiveButton("Comment", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    //Allow Comment
                                                    commentLayout.setVisibility(View.VISIBLE);
                                                    txtComment.requestFocus();


                                                }
                                            });
                                            builder.setNegativeButton("Ammend", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    //Do nothing
                                                    commentLayout.setVisibility(View.INVISIBLE);

                                                }
                                            });

                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);

                                            AlertDialog alertDialog =  builder.show();
                                            final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                                            final Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                                            LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                                            LinearLayout.LayoutParams negativeButtonLL = (LinearLayout.LayoutParams) negativeButton.getLayoutParams();

                                            positiveButton.setTextColor(Color.WHITE);
                                            negativeButton.setTextColor(Color.WHITE);

                                            positiveButton.setBackgroundColor(Color.parseColor("#3180e9"));
                                            negativeButton.setBackgroundColor(Color.parseColor("#3180e9"));

                                            positiveButtonLL.leftMargin=10;

                                            negativeButtonLL.weight = 10;
                                            positiveButtonLL.weight = 10;

                                            positiveButton.setLayoutParams(positiveButtonLL);
                                            negativeButton.setLayoutParams(negativeButtonLL);
                                            break;

                                        }
                                        else if(relationCurInd.equals("05") &&
                                                ((ageCurInd-age) < 12) &&
                                                (txtComment.getText().toString().length()==0) &&
                                                (ageCurInd!=99))
                                        {
                                            /**
                                             * Grandparent Relationship to Head
                                             */
                                            String errmsg =  p1.getP01() +", Parent of "+ p.getP01() +"'s age difference must be 12 Years or more. \n\n Please provide comment if this is not the case ";
                                            String title = "Age relation to Head of House";

                                            AlertDialog.Builder builder = new AlertDialog.Builder(P04.this);
                                            builder.setTitle(title);
                                            builder.setIcon(R.drawable.ic_warning_orange_24dp);
                                            builder.setMessage(errmsg);
                                            builder.setPositiveButton("Comment", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    //Allow Comment
                                                    commentLayout.setVisibility(View.VISIBLE);
                                                    txtComment.requestFocus();


                                                }
                                            });
                                            builder.setNegativeButton("Ammend", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    //Do nothing
                                                    commentLayout.setVisibility(View.INVISIBLE);

                                                }
                                            });

                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);

                                            AlertDialog alertDialog =  builder.show();
                                            final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                                            final Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                                            LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                                            LinearLayout.LayoutParams negativeButtonLL = (LinearLayout.LayoutParams) negativeButton.getLayoutParams();

                                            positiveButton.setTextColor(Color.WHITE);
                                            negativeButton.setTextColor(Color.WHITE);

                                            positiveButton.setBackgroundColor(Color.parseColor("#3180e9"));
                                            negativeButton.setBackgroundColor(Color.parseColor("#3180e9"));

                                            positiveButtonLL.leftMargin=10;

                                            negativeButtonLL.weight = 10;
                                            positiveButtonLL.weight = 10;

                                            positiveButton.setLayoutParams(positiveButtonLL);
                                            negativeButton.setLayoutParams(negativeButtonLL);
                                            break;

                                        }
                                        else{
                                            /**
                                             * Save age
                                             */
                                            p1.setP04YY(txtYY.getText().toString());
                                            p1.setP04MM(txtmm.getText().toString());
                                            p1.setP04WKS(txtmm.getText().toString());

                                            thisHouse.getPersons()[p1.getLineNumber()].setP04YY(txtYY.getText().toString());
                                            thisHouse.getPersons()[p1.getLineNumber()].setP04MM(txtmm.getText().toString());
                                            thisHouse.getPersons()[p1.getLineNumber()].setP04WKS(txtwks.getText().toString());

                                            //Restart the current activity for next individual
                                            if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){

                                                thisHouse.next =String.valueOf(0);
                                                thisHouse.previous = String.valueOf(thisHouse.getTotalPersons()-1);

                                                myDB = new DatabaseHelper(P04.this);
                                                myDB.onOpen(myDB.getWritableDatabase());

                                                //UPDATE HOUSEHOLD
                                                List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                                if(ll.size()>0){
                                                    myDB.updateRoster(thisHouse,"P04_YY",p1.getP04YY(), String.valueOf(p1.getSRNO()));
                                                    //myDB.updateRoster(thisHouse,"P04MM",p1.getP04MM(), String.valueOf(p1.getSRNO()));
                                                    //myDB.updateRoster(thisHouse,"P04WKS",p1.getP04WKS(), String.valueOf(p1.getSRNO()));
                                                    myDB.close();
                                                }



                                                Intent intent = new Intent(P04.this,P05.class);
                                                intent.putExtra("Household",  thisHouse);
                                                startActivity(intent);
                                                finish();
                                            }else{
                                                if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length)
                                                {
                                                    myDB = new DatabaseHelper(P04.this);
                                                    myDB.onOpen(myDB.getWritableDatabase());

                                                    //UPDATE HOUSEHOLD
                                                    List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                                    if(ll.size()>0){
                                                        myDB.updateRoster(thisHouse,"P04_YY",p1.getP04YY(), String.valueOf(p1.getSRNO()));
                                                        //myDB.updateRoster(thisHouse,"P04MM",p1.getP04MM(), String.valueOf(p1.getSRNO()));
                                                        //myDB.updateRoster(thisHouse,"P04WKS",p1.getP04WKS(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();
                                                    }
                                                    thisHouse.next=String.valueOf(p1.getSRNO()+1);

                                                    //Restart the current activity for next individual
                                                    finish();
                                                    Intent intent = new Intent(P04.this,P04.class);
                                                    intent.putExtra("Household",  thisHouse);
                                                    startActivity(intent);

                                                }

                                            }
                                        }

                                    }
                                    else
                                    {
                                        /**
                                         * Save household head
                                         */
                                        p1.setP04YY(txtYY.getText().toString());
                                        //p1.setP04MM(txtmm.getText().toString());
                                        //p1.setP04WKS(txtmm.getText().toString());

                                        thisHouse.getPersons()[p1.getLineNumber()].setP04YY(txtYY.getText().toString());
                                        //thisHouse.getPersons()[p1.getLineNumber()].setP04MM(txtmm.getText().toString());
                                        //thisHouse.getPersons()[p1.getLineNumber()].setP04WKS(txtwks.getText().toString());

                                        //Restart the current activity for next individual
                                        if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){

                                            thisHouse.next =String.valueOf(0);
                                            thisHouse.previous = String.valueOf(thisHouse.getTotalPersons()-1);
                                            myDB = new DatabaseHelper(P04.this);
                                            myDB.onOpen(myDB.getWritableDatabase());

                                            //UPDATE HOUSEHOLD
                                            List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                            if(ll.size()>0){
                                                myDB.updateRoster(thisHouse,"P04_YY",p1.getP04YY(), String.valueOf(p1.getSRNO()));
                                                //myDB.updateRoster(thisHouse,"P04MM",p1.getP04MM(), String.valueOf(p1.getSRNO()));
                                               //myDB.updateRoster(thisHouse,"P04WKS",p1.getP04WKS(), String.valueOf(p1.getSRNO()));
                                                myDB.close();
                                            }



                                            Intent intent = new Intent(P04.this,P05.class);
                                            intent.putExtra("Household",  thisHouse);
                                            startActivity(intent);
                                            finish();

                                        }else{
                                            if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length)
                                            {
                                                myDB = new DatabaseHelper(P04.this);
                                                myDB.onOpen(myDB.getWritableDatabase());

                                                //UPDATE HOUSEHOLD
                                                List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                                if(ll.size()>0){
                                                    myDB.updateRoster(thisHouse,"P04_YY",p1.getP04YY(), String.valueOf(p1.getSRNO()));
                                                    //myDB.updateRoster(thisHouse,"P04MM",p1.getP04MM(), String.valueOf(p1.getSRNO()));
                                                    //myDB.updateRoster(thisHouse,"P04WKS",p1.getP04WKS(), String.valueOf(p1.getSRNO()));
                                                    myDB.close();
                                                }
                                                thisHouse.next=String.valueOf(p1.getSRNO()+1);

                                                //Restart the current activity for next individual
                                                finish();
                                                Intent intent = new Intent(P04.this,P04.class);
                                                intent.putExtra("Household",  thisHouse);
                                                startActivity(intent);

                                            }

                                        }
                                    }
                                }



                            }



                        }
                        else
                        {
                            String errmsg = "Please enter "+ p1.getP01() + "'s age within range \n2 Years - 98 Years \n 99 For Dont know";
                            String title = "Age range";
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);
                            lib.showError(P04.this,title,errmsg);
                        }

                    }
                    /**
                     * Else for Individuals less than 2
                     */
                    else if(less.isChecked())
                    {

                        /**
                         * AGE FOR LESS THAN 2 YEARS && OVER 2 YEARS VALIDATION
                         */

                        if(((txtYY.getText().length()==0) && (txtmm.getText().length()==0) && (txtYY.getText().length()==0))
                                || ((Integer.valueOf(txtYY.getText().toString())==0) && (Integer.valueOf(txtmm.getText().toString())==0) && (Integer.valueOf(txtwks.getText().toString())==0)))
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(P04.this);
                            builder.setTitle("Confirm Age");
                            builder.setIcon(R.drawable.ic_warning_orange_24dp);
                            builder.setMessage("Are you sure " + p1.getP01() + "'s age is less than 1 Week?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //Set ages to 00
                                    /**
                                     * Save SON, GRANDCHILD,PARENT,GRAND PARENT
                                     */
                                    p1.setP04YY(txtYY.getText().toString());
                                    p1.setP04MM(txtmm.getText().toString());
                                    p1.setP04WKS(txtmm.getText().toString());

                                    //Restart the current activity for next individual
                                    if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){

                                        //Next question P05
                                        Intent intent = new Intent(P04.this,P04.class);
                                        intent.putExtra("Household",  thisHouse);
                                        startActivity(intent);

                                    }else{
                                        //Restart the current activity for next individual
                                        finish();
                                        startActivity(getIntent());
                                    }


                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //Do nothing

                                }
                            });

                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibs.vibrate(100);

                            AlertDialog alertDialog =  builder.show();
                            final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                            final Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                            LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                            LinearLayout.LayoutParams negativeButtonLL = (LinearLayout.LayoutParams) negativeButton.getLayoutParams();

                            positiveButton.setTextColor(Color.WHITE);
                            negativeButton.setTextColor(Color.WHITE);

                            positiveButton.setBackgroundColor(Color.parseColor("#3180e9"));
                            negativeButton.setBackgroundColor(Color.parseColor("#3180e9"));

                            positiveButtonLL.leftMargin=10;

                            negativeButtonLL.weight = 10;
                            positiveButtonLL.weight = 10;

                            positiveButton.setLayoutParams(positiveButtonLL);
                            negativeButton.setLayoutParams(negativeButtonLL);



                        }
                        else
                            {
                            if((txtYY.getText().length()==0) ||
                                    Integer.valueOf(txtYY.getText().toString())==1 ||
                                    Integer.valueOf(txtYY.getText().toString())==99 ||
                                    Integer.valueOf(txtYY.getText().toString())==0 )
                            {
                                //Check months
                                if((txtmm.getText().length()==0) && (txtwks.getText().length()==0)){
                                    //-------------------Whole 1 Year proceed
                                    /**
                                     * Save age
                                     */
                                    p1.setP04YY(txtYY.getText().toString());
                                    p1.setP04MM(txtmm.getText().toString());
                                    p1.setP04WKS(txtmm.getText().toString());

                                    thisHouse.getPersons()[p1.getLineNumber()].setP04YY(txtYY.getText().toString());
                                    thisHouse.getPersons()[p1.getLineNumber()].setP04MM(txtmm.getText().toString());
                                    thisHouse.getPersons()[p1.getLineNumber()].setP04WKS(txtwks.getText().toString());

                                    //Restart the current activity for next individual
                                    if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){
                                        thisHouse.next =String.valueOf(0);
                                        thisHouse.previous = String.valueOf(thisHouse.getTotalPersons()-1);
                                        myDB = new DatabaseHelper(P04.this);
                                        myDB.onOpen(myDB.getWritableDatabase());

                                        //UPDATE HOUSEHOLD
                                        List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                        if(ll.size()>0){
                                            myDB.updateRoster(thisHouse,"P04_YY",p1.getP04YY(), String.valueOf(p1.getSRNO()));
                                            myDB.updateRoster(thisHouse,"P04_MM",p1.getP04MM(), String.valueOf(p1.getSRNO()));
                                            myDB.updateRoster(thisHouse,"P04_WKS",p1.getP04WKS(), String.valueOf(p1.getSRNO()));
                                            myDB.close();
                                        }



                                        Intent intent = new Intent(P04.this,P05.class);
                                        intent.putExtra("Household",  thisHouse);
                                        startActivity(intent);
                                        finish();

                                    }else{
                                        if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length)
                                        {
                                            myDB = new DatabaseHelper(P04.this);
                                            myDB.onOpen(myDB.getWritableDatabase());

                                            //UPDATE HOUSEHOLD
                                            List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                            if(ll.size()>0){
                                                myDB.updateRoster(thisHouse,"P04_YY",p1.getP04YY(), String.valueOf(p1.getSRNO()));
                                                myDB.updateRoster(thisHouse,"P04_MM",p1.getP04MM(), String.valueOf(p1.getSRNO()));
                                                myDB.updateRoster(thisHouse,"P04_WKS",p1.getP04WKS(), String.valueOf(p1.getSRNO()));
                                                myDB.close();
                                            }
                                            thisHouse.next=String.valueOf(p1.getSRNO()+1);

                                            //Restart the current activity for next individual
                                            finish();
                                            Intent intent = new Intent(P04.this,P04.class);
                                            intent.putExtra("Household",  thisHouse);
                                            startActivity(intent);

                                        }

                                    }





                                }else{
                                    //months greater than 0
                                    thisHouse.getPersons()[p1.getLineNumber()].setP04YY(txtYY.getText().toString());
                                    thisHouse.getPersons()[p1.getLineNumber()].setP04MM(txtmm.getText().toString());
                                    thisHouse.getPersons()[p1.getLineNumber()].setP04WKS(txtwks.getText().toString());

                                    if((txtmm.getText().length()>0))
                                    {
                                        //months between 0 and 11
                                        if((Integer.parseInt(txtmm.getText().toString())>=0
                                                && Integer.parseInt(txtmm.getText().toString())<=11)
                                                || (Integer.parseInt(txtmm.getText().toString())==99))
                                        {
                                            //weeks
                                            if(txtwks.getText().length()>0)
                                            {
                                                //weeks between 0 and 3 weeks
                                                if((Integer.parseInt(txtwks.getText().toString())>=0
                                                        && Integer.parseInt(txtwks.getText().toString())<=3)
                                                        || (Integer.parseInt(txtwks.getText().toString())==99))
                                                {
                                                    // -----------------proceed
                                                    /**
                                                     * Save age
                                                     */
                                                    if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){
                                                        thisHouse.next =String.valueOf(0);
                                                        thisHouse.previous = String.valueOf(thisHouse.getTotalPersons()-1);
                                                        myDB = new DatabaseHelper(P04.this);
                                                        myDB.onOpen(myDB.getWritableDatabase());

                                                        //UPDATE HOUSEHOLD
                                                        List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                                        if(ll.size()>0){
                                                            myDB.updateRoster(thisHouse,"P04_YY",p1.getP04YY(), String.valueOf(p1.getSRNO()));
                                                            myDB.updateRoster(thisHouse,"P04_MM",p1.getP04MM(), String.valueOf(p1.getSRNO()));
                                                            myDB.updateRoster(thisHouse,"P04_WKS",p1.getP04WKS(), String.valueOf(p1.getSRNO()));
                                                            myDB.close();
                                                        }



                                                        Intent intent = new Intent(P04.this,P05.class);
                                                        intent.putExtra("Household",  thisHouse);
                                                        startActivity(intent);
                                                        finish();

                                                    }else{
                                                        if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length)
                                                        {
                                                            myDB = new DatabaseHelper(P04.this);
                                                            myDB.onOpen(myDB.getWritableDatabase());

                                                            //UPDATE HOUSEHOLD
                                                            List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                                            if(ll.size()>0){
                                                                myDB.updateRoster(thisHouse,"P04_YY",p1.getP04YY(), String.valueOf(p1.getSRNO()));
                                                                myDB.updateRoster(thisHouse,"P04_MM",p1.getP04MM(), String.valueOf(p1.getSRNO()));
                                                                myDB.updateRoster(thisHouse,"P04_WKS",p1.getP04WKS(), String.valueOf(p1.getSRNO()));
                                                                myDB.close();
                                                            }
                                                            thisHouse.next=String.valueOf(p1.getSRNO()+1);

                                                            //Restart the current activity for next individual
                                                            finish();
                                                            Intent intent = new Intent(P04.this,P04.class);
                                                            intent.putExtra("Household",  thisHouse);
                                                            startActivity(intent);

                                                        }

                                                    }

                                                }
                                                else
                                                    {
                                                    String errmsg = "Please enter "+ p1.getP01() + "'s age Weeks within range \n0 Weeks - 3 Weeks, \n 99 For Dont know";
                                                    String title = "Age Months range";
                                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                    vibs.vibrate(100);
                                                    lib.showError(P04.this,title,errmsg);
                                                }
                                            }



                                        }
                                        else {
                                            String errmsg = "Please enter "+ p1.getP01() + "'s age Months within range \n0 Month - 11 Months, \n 99 For Dont know";
                                            String title = "Age Months range";
                                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            vibs.vibrate(100);
                                            lib.showError(P04.this,title,errmsg);
                                        }


                                    }else{
                                        //weeks greater than zero
                                        if(txtwks.getText().length()>0){

                                            //weeks between 0 and 3 weeks
                                            if((Integer.parseInt(txtwks.getText().toString())>=0
                                                    && Integer.parseInt(txtwks.getText().toString())<=3)
                                                    || (Integer.parseInt(txtwks.getText().toString())==99))
                                            {
                                                // -------------------proceed
                                                /**
                                                 * Save
                                                 */
                                                if(p1.getLineNumber() == thisHouse.getTotalPersons()-1){
                                                    thisHouse.next =String.valueOf(0);
                                                    thisHouse.previous = String.valueOf(thisHouse.getTotalPersons()-1);
                                                    myDB = new DatabaseHelper(P04.this);
                                                    myDB.onOpen(myDB.getWritableDatabase());

                                                    //UPDATE HOUSEHOLD
                                                    List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                                    if(ll.size()>0){
                                                        myDB.updateRoster(thisHouse,"P04_YY",p1.getP04YY(), String.valueOf(p1.getSRNO()));
                                                        myDB.updateRoster(thisHouse,"P04_MM",p1.getP04MM(), String.valueOf(p1.getSRNO()));
                                                        myDB.updateRoster(thisHouse,"P04_WKS",p1.getP04WKS(), String.valueOf(p1.getSRNO()));
                                                        myDB.close();
                                                    }



                                                    Intent intent = new Intent(P04.this,P05.class);
                                                    intent.putExtra("Household",  thisHouse);
                                                    startActivity(intent);
                                                    finish();

                                                }else{
                                                    if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length)
                                                    {
                                                        myDB = new DatabaseHelper(P04.this);
                                                        myDB.onOpen(myDB.getWritableDatabase());

                                                        //UPDATE HOUSEHOLD
                                                        List<PersonRoster> ll = myDB.getdataHhP(thisHouse.getAssignment_ID(),thisHouse.getBatchNumber());
                                                        if(ll.size()>0){
                                                            myDB.updateRoster(thisHouse,"P04_YY",p1.getP04YY(), String.valueOf(p1.getSRNO()));
                                                            myDB.updateRoster(thisHouse,"P04_MM",p1.getP04MM(), String.valueOf(p1.getSRNO()));
                                                            myDB.updateRoster(thisHouse,"P04_WKS",p1.getP04WKS(), String.valueOf(p1.getSRNO()));
                                                            myDB.close();
                                                        }
                                                        thisHouse.next=String.valueOf(p1.getSRNO()+1);

                                                        //Restart the current activity for next individual
                                                        finish();
                                                        Intent intent = new Intent(P04.this,P04.class);
                                                        intent.putExtra("Household",  thisHouse);
                                                        startActivity(intent);

                                                    }

                                                }

                                            }
                                            else
                                            {
                                                String errmsg = "Please enter "+ p1.getP01() + "'s age Weeks within range \n0 Weeks - 3 Weeks, \n 99 For Dont know";
                                                String title = "Age Months range";
                                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                vibs.vibrate(100);
                                                lib.showError(P04.this,title,errmsg);
                                            }


                                        }

                                    }
                                }


                            }else{
                                String errmsg = "Please enter "+ p1.getP01() + "'s age Year within range \n0 Years - 1 Year ,\n 99 For Dont know";
                                String title = "Age range";
                                Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibs.vibrate(100);
                                lib.showError(P04.this,title,errmsg);
                            }

                        }

                    }else{
                        //Error message to enter or select ages
                        String errmsg = "Please select either 2 Years & above or Less than 2 Years to enter age";
                        String title = "Age range";
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(100);
                        lib.showError(P04.this,title,errmsg);

                    }




                }
            });
            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(p1.getSRNO() == 0){
                        //Next question P03
                        //thisHouse.previous=String.valueOf(p1.getLineNumber());//set previous to last person covered
                        thisHouse.previous=String.valueOf(thisHouse.getTotalPersons()-1);
                        thisHouse.next=null;
                        finish();

                        Intent intent = new Intent(P04.this,P03.class);
                        intent.putExtra("Household",  thisHouse);
                        startActivity(intent);


                    }else if(p1.getSRNO()>=0 && p1.getSRNO()<thisHouse.getPersons().length){
                        //Restart the current activity for previous individual
                        thisHouse.previous = String.valueOf(p1.getSRNO()-1);
                        thisHouse.next=null;

                        finish();

                        Intent intent = new Intent(P04.this, P04.class);
                        intent.putExtra("Household", thisHouse);
                        startActivity(intent);


                    }
                }
            });





        }

        /**
         * Method to clear Textbox onfocus if default text is 00
         */
        public void clearText(EditText editText){
            if(editText.getText().toString().equals("00")){
                editText.setText("");
            }else{

            }
        }

}

