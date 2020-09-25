package com.MyAccountent.MyAccountent.DB;

import android.content.Context;
import android.content.SharedPreferences;

import com.MyAccountent.MyAccountent.Data.User;

import java.util.HashMap;
import java.util.List;

public class UserSharedPerManager {
    private static final String USER_SHARED_PREF_NAME = "user_shared_pref";

    private Context context;
    public static final String EMAIL_KEY = "EMAIL";
    public static final String PASSWORD_KEY = "PASSWORD";
    public static final String BUSINESS_NAME_KEY = "BUSINESS_NAME";
    public static final String BUSINESS_TYPE_KEY = "BUSINESS_TYPE";
    public static final String BUSINESS_ID_KEY = "BUSINESS_ID";
    private SharedPreferences sharedPreferences;

    public UserSharedPerManager(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_SHARED_PREF_NAME, Context.MODE_PRIVATE);


    }

    public void SaveUser(User user) {
        if (user != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(BUSINESS_ID_KEY,user.getId());
            editor.putString(EMAIL_KEY, user.getEmail());
            editor.putString(PASSWORD_KEY, user.getPassword());
            editor.putString(BUSINESS_NAME_KEY, user.getBusinessName());
            editor.putString(BUSINESS_TYPE_KEY, user.getBusinessType());
            editor.commit();

        }


    }


    public User GetUser() {
        User user = new User();
        user.setId(sharedPreferences.getString(BUSINESS_ID_KEY,null));
        user.setEmail(sharedPreferences.getString(EMAIL_KEY, null));
        user.setPassword(sharedPreferences.getString(PASSWORD_KEY, null));
        user.setBusinessName(sharedPreferences.getString(BUSINESS_NAME_KEY, null));
        user.setBusinessType(sharedPreferences.getString(BUSINESS_TYPE_KEY, null));
        return user;


    }

    public void saveUserLoginInfo(String email, String businessName, String businessType) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL_KEY, email);
        editor.putString(BUSINESS_NAME_KEY, businessName);
        editor.putString(BUSINESS_TYPE_KEY, businessType);

        editor.commit();

    }

    public final HashMap getUserLoginInfo() {

        HashMap UserInfo = new HashMap();
        UserInfo.put("email", sharedPreferences.getString(EMAIL_KEY, null));
        UserInfo.put("businessName", sharedPreferences.getString(BUSINESS_NAME_KEY, null));
        UserInfo.put("businessType", sharedPreferences.getString(BUSINESS_TYPE_KEY, null));


return UserInfo;
    }

}
