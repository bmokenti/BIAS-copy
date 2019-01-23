package bw.org.statsbots.bias;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class DateHelper {

    /**This class retrieves the current Date Time from device*/
    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
