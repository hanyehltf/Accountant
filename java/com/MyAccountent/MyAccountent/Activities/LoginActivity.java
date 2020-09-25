package com.MyAccountent.MyAccountent.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.MyAccountent.MyAccountent.DB.UserApiService;
import com.MyAccountent.MyAccountent.DB.UserSharedPerManager;
import com.MyAccountent.MyAccountent.Data.User;
import com.MyAccountent.MyAccountent.R;


public class LoginActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;

    private UserSharedPerManager userSharedPerManager;

    private TextView dontSugnUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        setUpViews();
        loginStatus();

    }

    private void loginStatus() {
        final Button login_button = (Button) findViewById(R.id.Loginbutton);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = Email.getText().toString();
                final String password = Password.getText().toString();
                Authentication(email, password);
            }
        });
    }

    public void setUpViews() {


        Email = (EditText) findViewById(R.id.login_username);
        Password = (EditText) findViewById(R.id.login_password);
        dontSugnUp = (TextView) findViewById(R.id.dont_signup_text_view);


        dontSugnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


    }


    private boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void Authentication(String Email, String Password) {
        if (!Email.isEmpty() && !Password.isEmpty()) {
            if (isEmailValid(Email)) {
                userSharedPerManager = new UserSharedPerManager(LoginActivity.this);
                UserApiService Authentication = new UserApiService(LoginActivity.this);
                Authentication.LoginUser(Email, Password, new UserApiService.OnLoginResponse() {
                    @Override
                    public void onResponse(User user) {
                        if (user!=null) {


                            LoadHomePage loadHomePage = new LoadHomePage(LoginActivity.this);
                            userSharedPerManager.saveUserLoginInfo(user.getEmail(),user.getBusinessName(),user.getBusinessType());
userSharedPerManager.SaveUser(user);


                        } else {
                            Toast.makeText(LoginActivity.this, R.string.login_error, Toast.LENGTH_LONG).show();

                        }
                    }
                });
            } else {

                Toast.makeText(LoginActivity.this, R.string.valid_email, Toast.LENGTH_LONG).show();
            }
        } else {


            Toast.makeText(LoginActivity.this, R.string.empty_field, Toast.LENGTH_LONG).show();
        }
    }
}
