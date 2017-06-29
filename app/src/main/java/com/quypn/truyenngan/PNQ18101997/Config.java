package com.quypn.truyenngan.PNQ18101997;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.AdapterStoryExpand.Profile;
import com.AdapterStoryExpand.Profile_Type;

/**
 * Created by QuyPN on 3/2/2017.
 */

public class Config {

    public static String UserAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML," +
            " like Gecko) Chrome/41.0.2228.0 Safari/537.36";
    public static String key;
    public static Boolean page_error = false;
    public static String content;
    public static int PageNumber = 1;
    public static String linkGGPlay = "https://play.google.com/store/apps/details?id=com.quypn.truyenngan.PNQ18101997";
    public static Boolean modeReader = false;
    public static Profile profile;
    public static Profile_Type profile_type;


     public static boolean isNetworkConected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

    }
}
