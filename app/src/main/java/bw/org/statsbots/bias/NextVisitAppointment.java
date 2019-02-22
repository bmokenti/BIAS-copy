package bw.org.statsbots.bias;

import android.app.Application;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class NextVisitAppointment extends AppCompatActivity implements View.OnClickListener, Serializable {
    DatePicker nextDate;
    Button btnGet;
    TextView nxtVisit;
    EditText txtTime;
    Context context;
    int mYear;
    int mMonth;
    int mDay;
    private TimePicker timePicker1;
    int hour;
    int min;
    String AMPM;
    TextView textView;
    Button finish;
    protected HouseHold thisHouse;
    protected LibraryClass lib;
    int month;
    int year;
    int day;
    LocalDateTime now;
    private DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        context=getApplicationContext();
        setContentView(R.layout.activity_next_visit_appointment);

        myDB = new DatabaseHelper(this);
        myDB.getWritableDatabase();

        Intent i = getIntent();
        thisHouse = (HouseHold)i.getSerializableExtra("Household");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            now = LocalDateTime.now();
        }


        nextDate = (DatePicker) findViewById(R.id.calendarNextDate);
        nxtVisit = (TextView) findViewById(R.id.visit2Ap);
        btnGet = (Button) findViewById(R.id.btnDateConfirm);
        btnGet.setVisibility(View.VISIBLE);
        btnGet.setOnClickListener(this);
        finish=findViewById(R.id.btnDone);
        finish.setVisibility(View.INVISIBLE);

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        hour = timePicker1.getCurrentHour();
        min = timePicker1.getCurrentMinute();
        AMPM = updateTime(hour,min);
        month = nextDate.getMonth();
        year = nextDate.getYear();
        day = nextDate.getDayOfMonth();

        textView = findViewById(R.id.NextVisitAppointment);
        textView.setText("NEXT VISIT DATE TIME");

        timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(timePicker.getHour() != hour){
                        hour = timePicker.getHour();
                    }
                    if(timePicker.getMinute() != min){
                        min=timePicker.getMinute();
                    }
                }
                btnGet.setVisibility(View.VISIBLE);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            nextDate.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                    if(datePicker.getDayOfMonth() != day){
                        day =datePicker.getDayOfMonth();
                    }
                    if(datePicker.getMonth() != month){
                        month=datePicker.getMonth();
                    }
                    if(datePicker.getYear() != year){
                        year = datePicker.getYear();
                    }

                    btnGet.setVisibility(View.VISIBLE);
                }

            });
        }


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Redirect enumerator to started assignments
                //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                    int day = nextDate.getDayOfMonth();
                    int month = nextDate.getMonth();
                    int year =  nextDate.getYear();
                    int Hour = timePicker1.getCurrentHour();
                    int Minute = timePicker1.getCurrentMinute();

                    /**
                     * SELECTED DATE
                     */
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, day,Hour,Minute);
                    calendar.getTime();


                    /**
                     * GET THE CURRENT DATE
                     */
                    Calendar curDate = Calendar.getInstance();


                    /**
                     * TIME SPENT IN AN EA
                     */
                    Calendar future=Calendar.getInstance();
                    future.add(Calendar.DAY_OF_YEAR,6); //Add 6 days which the team spends in an EA

                    /**
                     * IF THE SET DATE IS BETWEEN NOW AND 6 DAYS FROM NO
                     * PROCEED, ELSE REQUEST DATE TIME CHECK
                     */


                    //IF Selected Date is Before today
                    if(isBeforeDay(calendar,curDate))
                    {
                        //QUERY the selected date
                        new LibraryClass().showError(NextVisitAppointment.this,"Next visit Date", "Make sure the next visit falls between today and 6 days from now");
                        /**
                         * VIBRATE DEVICE
                         */
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        if(vibs != null) {
                            vibs.vibrate(100);
                        }
                        btnGet.setVisibility(View.VISIBLE);

                    }
                    else {
                        Log.d("Current date is ",calendar.getTime().toString());

                        if((!isBeforeDay(calendar,curDate) && isBeforeDay(calendar,future)))
                        {


                            Date currentDate = new Date(System.currentTimeMillis());
                            String[] s = currentDate.toString().split(" ");
                           Log.d("Date Time", s[3]);

                           String st[] = s[3].split(":");

                                int curHour = Integer.parseInt(st[0]);
                                int minutes = Integer.parseInt(st[1]);

                                if(((calendar.getTime().getDay()) == (curDate.getTime().getDay())) &&  (hour<=curHour))
                                {
                                    //QUERY the selected date
                                    new LibraryClass().showError(NextVisitAppointment.this,"Next visit Time", "Next Visit Time should be 1 hour or more from current time ");

                                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    if(vibs != null) {
                                        vibs.vibrate(100);
                                    }
                                    btnGet.setVisibility(View.VISIBLE);


                                }else{


                                    /**
                                     * SAVE OR UPDATE THE HOUSE HOLD BASED
                                     */


                                    myDB.onOpen(myDB.getReadableDatabase());

                                    //Save this house

                                    thisHouse.setInterview_Status("9");

                                    myDB.updateHouseholdAllColumns(myDB.getWritableDatabase(),thisHouse);

                                    myDB.updateHHStatus(thisHouse);

                                    myDB.close();


                                   /* *
                                     * LAUNCH DASH BOARD WITHOUT PASSING ANY PAYLOADS
                                     */
                                    Intent intent = new Intent(NextVisitAppointment.this,Dashboard.class);
                                    intent.putExtra("Household",  thisHouse);
                                    startActivity(intent);



                                }


                        }else{

                            //QUERY the selected date
                            new LibraryClass().showError(NextVisitAppointment.this,"Next visit Date", "Make sure the next visit falls between today and 6 days from now");
                            /**
                             * VIBRATE DEVICE
                             */
                            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            if(vibs != null) {
                                vibs.vibrate(100);
                            }
                            btnGet.setVisibility(View.VISIBLE);


                        }






                    }


                //}






            }
        });


    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean isAfterDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA)) return false;
        if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA)) return true;
        if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) return false;
        if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) return true;
        return cal1.get(Calendar.DAY_OF_YEAR) > cal2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Checks if Selected Date is Before today
     * @param cal1
     * @param cal2
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean isBeforeDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }


            if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA)) return true;
            if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA)) return false;
            if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) return true;
            if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) return false;


        return cal1.get(Calendar.DAY_OF_YEAR) < cal2.get(Calendar.DAY_OF_YEAR);
    }

    private String updateTime(int hours, int mins) {

        String timeSet = "";

        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";


        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        return aTime;
    }


  @Override
    public void onClick(View view) {


        if(String.valueOf(hour).length()==0 || String.valueOf(min).length()==0)
        {
            new LibraryClass().showError(NextVisitAppointment.this,"Next Appointment Time Error","Please enter time ");
            /**
             * VIBRATE DEVICE
             */
            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibs.vibrate(100);
        }
        else
            {
                hour = timePicker1.getCurrentHour();
                min = timePicker1.getCurrentMinute();
                AMPM = updateTime(hour,min);
                nxtVisit.setText("Next Visit: "+ nextDate.getDayOfMonth()+"/"+ (nextDate.getMonth() + 1)+"/"+nextDate.getYear() +" "+AMPM);

                //SAVE NEXT VISIT DATE AND TIME
                if(thisHouse.getVISIT2_RESULT() == null){
                    thisHouse.setNEXT_VISIT_2_DATE(nextDate.getDayOfMonth()+"/"+ (nextDate.getMonth() + 1)+"/"+nextDate.getYear());
                    thisHouse.setNEXT_VISIT_2_TIME(AMPM);
                }
                else
                {
                    thisHouse.setNEXT_VISIT_3_DATE(nextDate.getDayOfMonth()+"/"+ (nextDate.getMonth() + 1)+"/"+nextDate.getYear());
                    thisHouse.setNEXT_VISIT_3_TIME(AMPM);
                }


                finish.setVisibility(View.VISIBLE);
                btnGet.setVisibility(View.INVISIBLE);
        }



    }
    }


