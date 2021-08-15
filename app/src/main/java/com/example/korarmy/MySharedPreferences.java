package com.example.korarmy;

import android.content.Context;
import android.content.SharedPreferences;


public class MySharedPreferences{

    private static final String PREF_USER_ID = "MY_LOGIN";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return ctx.getSharedPreferences(PREF_USER_ID, Context.MODE_PRIVATE);
    }

    public static void setUserEmail(Context ctx,String userEamil){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID, userEamil);
        editor.commit();
    }

    public static String getUserEamil(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_ID, "");
    }

    public static void userEmailClear(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }
}
