package com.bbt.simpleSchedular.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

/**
 * Created by anish on 23-10-2017.
 */

public class FunctionHelper {


    public static void initToolbar(final AppCompatActivity activity, Toolbar toolbar, String title, String subtitle) {
        toolbar.setTitle(title);
        toolbar.setSubtitle(subtitle);
        toolbar.setTitleTextColor(Color.WHITE);
//        activity.setSupportActionBar(toolbar);
    }

    public static float dpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static float pixelToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public static void showAlertDialogWithOneOpt(Context mContext, String message, final DialogOptionsSelectedListener dialogOptionsSelectedListener, String yesOption, String noOption) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(yesOption, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (dialogOptionsSelectedListener != null)
                            dialogOptionsSelectedListener.onSelect(true);
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton(noOption, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (dialogOptionsSelectedListener != null)
                            dialogOptionsSelectedListener.onSelect(false);
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog alert = builder.create();

        alert.show();
    }

    public interface DialogOptionsSelectedListener {
        void onSelect(boolean isYes);
    }

}
