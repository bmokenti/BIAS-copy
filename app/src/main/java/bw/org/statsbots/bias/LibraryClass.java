package bw.org.statsbots.bias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import static android.content.Context.VIBRATOR_SERVICE;

public class LibraryClass extends MainActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/

    public void showError(Context context, String title,String errmsg){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setIcon(R.drawable.ic_warning_orange_24dp);
        builder.setMessage(errmsg);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Do nothing

            }
        });


        AlertDialog alertDialog =  builder.show();
        final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
        positiveButtonLL.width=ViewGroup.LayoutParams.MATCH_PARENT;
        positiveButton.setTextColor(Color.WHITE);
        positiveButton.setBackgroundColor(Color.parseColor("#FF9007"));
        positiveButton.setLayoutParams(positiveButtonLL);
    }
}
