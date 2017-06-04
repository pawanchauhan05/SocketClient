package com.android.socketclient.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Created by pawansingh on 03/06/17.
 */

public class Utils {
    /**
     * this function is used for store String type value in shared preference.
     *
     * @param context - to prevent unconditionally errors use application context.
     * @param key - shared preference key (not null)
     * @param value - value to be stored in shared preference
     */
    public static void savePreferenceData(Context context, String key, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * this function is used for store boolean type value in shared preference.
     *
     * @param context - to prevent unconditionally errors use application context.
     * @param key - shared preference key (not null)
     * @param value - value to be stored in shared preference
     */
    public static void savePreferenceData(Context context, String key, Boolean value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * this function is used for store int type value in shared preference.
     *
     * @param context - to prevent unconditionally errors use application context.
     * @param key - shared preference key (not null)
     * @param value - value to be stored in shared preference
     */
    public static void savePreferenceData(Context context, String key, int value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * this function is used for store Custom Object in shared preference.
     *
     * @param context - to prevent unconditionally errors use application context.
     * @param key - shared preference key (not null)
     * @param value - custom object to be stored in shared preference
     */
    public static void savePreferenceData(Context context, String key, Object value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, new Gson().toJson(value));
        editor.commit();
    }

    /**
     * this function is used for clear all key value pair from shared preference.
     * Recommended - used this function while you want to clear all shared preference data like logout etc.
     *
     * @param context - to prevent unconditionally errors use application context.
     */
    public static void clearPreferences(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * this function is used for clear particular key value pair from shared preference.
     *
     * @param context - to prevent unconditionally errors use application context.
     * @param key - shared preference key (not null)
     */
    public static void removePreferenceData(Context context, String key) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().remove(key).commit();
    }

    /**
     * this function is used to get String type value which stored in shared preference.
     *
     * @param context - to prevent unconditionally errors use application context.
     * @param key - shared preference key (not null)
     * @param defaultValue - provide default value to prevent unconditionally result.
     * @return - returns String value
     */
    public static String readPreferenceData(Context context, String key, String defaultValue) {
        if (context != null) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            return sp.getString(key, defaultValue);
        }
        return null;
    }

    /**
     * this function is used to get Boolean type value which stored in shared preference.
     *
     * @param context - to prevent unconditionally errors use application context.
     * @param key - shared preference key (not null)
     * @param defaultValue - provide default value to prevent unconditionally result.
     * @return - returns Boolean value
     */
    public static Boolean readPreferenceData(Context context, String key, boolean defaultValue) {
        if (context != null) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            return sp.getBoolean(key, defaultValue);
        }
        return false;
    }

    /**
     * this function is used to get int type value which stored in shared preference.
     *
     * @param context - to prevent unconditionally errors use application context.
     * @param key - shared preference key (not null)
     * @param defaultValue - provide default value to prevent unconditionally result.
     * @return - returns Int value
     */
    public static int readPreferenceData(Context context, String key, int defaultValue) {
        if (context != null) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            return sp.getInt(key, defaultValue);
        }
        return -1;
    }

    /**
     * this function is used to get Custom Object value which stored in shared preference.
     *
     * @param context - to prevent unconditionally errors use application context.
     * @param key - shared preference key (not null)
     * @param defaultValue - provide default value to prevent unconditionally result.
     * @param clazz - provide .class which object want to get
     * @return - returns stored Object
     */
    public static Object readPreferenceData(Context context, String key, String defaultValue, Class clazz) {
        String result = readPreferenceData(context, key, defaultValue);
        if (result != null) {
            return new Gson().fromJson(result, clazz);
        }
        return result;
    }
}
