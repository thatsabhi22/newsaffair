package com.theleafapps.pro.newsaffair.utils;

import android.app.ProgressDialog;

/**
 * Created by aviator on 30/12/16.
 */

public class Commons {

    public static final String BASE_URL = "https://newsapi.org/";

    public static void showpDialog(ProgressDialog pDialog) {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public static void hidepDialog(ProgressDialog pDialog) {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
