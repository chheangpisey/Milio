package com.example.milio.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UtilLibSessionMgr {

    private SharedPreferences pref;
    private Editor editor;

    private static final String REG_LIB_PREF_NAME = "FM_RegLib_Pref";

    public UtilLibSessionMgr() {}

    public UtilLibSessionMgr(Context context) {
        try {
            int PRIVATE_MODE = 0;
            pref = context.getSharedPreferences(REG_LIB_PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String userToken = "userToken";
    public String getUserToken () {
        return pref.getString(userToken, "");
    }
    public void setUserToken(String userTokens) {
        editor.putString(userToken, userTokens);
        editor.commit();
    }
    private String userId = "userId";
    public String getUserId () { return pref.getString(userId, ""); }
    public void setUserId(String userIds) {
        editor.putString(userId , userIds);
        editor.commit();
    }
    private String password = "password";
    public String getPassword () {
        return pref.getString(password, "");
    }
    public void setPassword(String passwords) {
        editor.putString(password , passwords);
        editor.commit();
    }


}