package com.MyAccountent.MyAccountent.DB;

import android.content.Context;
import android.util.Log;

import com.MyAccountent.MyAccountent.Data.User;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserApiService {
    private static final String TAG = "ApiService";
    private Context context;

    public UserApiService(Context context) {
        this.context = context;


    }

    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAILED = 0;
    public static final int STATUS_EMAIL_EXIST = 2;

    public void SignUpUser(String Email, String Password, String BusinessName, String BusinessType, final OnSignupComplete onSignupComplete) {
        final JSONObject requestJsonObject = new JSONObject();
        try {
            requestJsonObject.put("Email", Email);
            requestJsonObject.put("password", Password);
            requestJsonObject.put("BusinessName", BusinessName);
            requestJsonObject.put("BusinessType", BusinessType);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.1.7/Accountent/SaveUser.php", requestJsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        int ResponseStatus = response.getInt("response");
                        onSignupComplete.onSignUp(ResponseStatus);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    onSignupComplete.onSignUp(STATUS_FAILED);
                }
            });
            request.setRetryPolicy(new DefaultRetryPolicy(18000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(context).add(request);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void LoginUser(String Email, String Password, final OnLoginResponse onLoginResponse) {
        final JSONObject requestJsonObject = new JSONObject();
        try {

            requestJsonObject.put("email", Email);
            requestJsonObject.put("password", Password);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.1.7/Accountent/LoginUser.php", requestJsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
User user=new User();
                    JSONObject jsonObject=response.getJSONObject("response");
                    user.setId(jsonObject.getString("id"));
                    user.setEmail(jsonObject.getString("Email"));
                    user.setPassword(jsonObject.getString("password"));
                    user.setBusinessName(jsonObject.getString("BusinessName"));
                    user.setBusinessType(jsonObject.getString("BusinessType"));
                    onLoginResponse.onResponse(user);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(18000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);


    }


    public interface OnSignupComplete {
        void onSignUp(int responseStatus);
    }

    public interface OnLoginResponse {
        void onResponse(User user);
    }
}
