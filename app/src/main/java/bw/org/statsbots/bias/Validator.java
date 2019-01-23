package bw.org.statsbots.bias;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.File;

/**Validator class use for various kinds of validation validating*/
public class Validator {

    /** This method returns true if the String Value is empty*/
    public static boolean isNull(String value) {
        boolean t = false;
        if (value.trim().matches("") ||  value.trim().length() <=0)
        {
            t = true;
        }
        return t;
    }

    /**
     * Checks if SQLLite database file exists
     *
     * @return
     */
    public static boolean checkDataBase(String DB_FULL_PATH) {
        File file = new File(DB_FULL_PATH);
        boolean exists = false;

        if (file.exists()) //here's how to check
        {
            exists = true;
        }
        return exists;
    }

    /**
     * Checks if Network is available and is connected
     * @return
     */
    public static  boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}
