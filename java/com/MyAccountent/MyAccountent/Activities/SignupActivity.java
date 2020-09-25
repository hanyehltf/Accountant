package com.MyAccountent.MyAccountent.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.MyAccountent.MyAccountent.DB.UserApiService;
import com.MyAccountent.MyAccountent.DB.UserSharedPerManager;
import com.MyAccountent.MyAccountent.Data.User;
import com.MyAccountent.MyAccountent.R;


public class SignupActivity extends AppCompatActivity {
    EditText Email;
    EditText Password;
    EditText BusinessName;
    Spinner BusinessType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setUpViews();
    }

    private void setUpViews() {

        Email = (EditText) findViewById(R.id.SinUp_username);
        Password = (EditText) findViewById(R.id.signUp_password);
        BusinessName = (EditText) findViewById(R.id.Business_Name);
        BusinessType = (Spinner) findViewById(R.id.Business_Type_sppiner);


        ArrayAdapter spinnerAdaptor = ArrayAdapter.createFromResource(this, R.array.spinner_items, R.layout.support_simple_spinner_dropdown_item);
        spinnerAdaptor.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        BusinessType.setAdapter(spinnerAdaptor);

        Button SignUp = (Button) findViewById(R.id.SignUp_btn);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String businessName = BusinessName.getText().toString();
                String businessType = BusinessType.getSelectedItem().toString();
                PostDataInDataBase(email, password, businessName, businessType);
            }
        });

    }

    private void PostDataInDataBase(final String email, String password, final String businessName, final String businessType) {


        if (!(email.isEmpty()) && !(password.isEmpty()) && !(businessName.isEmpty())) {
            if (password.length() >= 8) {
                if (isEmailValid(email)) {
                    final UserSharedPerManager userSharedPerManager = new UserSharedPerManager(SignupActivity.this);
                    UserApiService PostDataInApiServer = new UserApiService(SignupActivity.this);
                    PostDataInApiServer.SignUpUser(email, password, businessName, businessType, new UserApiService.OnSignupComplete() {
                        @Override
                        public void onSignUp(int responseStatus) {
                            switch (responseStatus) {
                                case UserApiService.STATUS_EMAIL_EXIST:
                                    Toast.makeText(SignupActivity.this, R.string.STATUS_EMAIL_EXIST, Toast.LENGTH_SHORT).show();

                                    break;
                                case UserApiService.STATUS_FAILED:

                                    Toast.makeText(SignupActivity.this, R.string.STATUS_EMAIL_FIELD, Toast.LENGTH_SHORT).show();
                                    break;
                                case UserApiService.STATUS_SUCCESS:
                                    Toast.makeText(SignupActivity.this, R.string.STATUS_EMAIL_SUCCESS, Toast.LENGTH_SHORT).show();

                                    userSharedPerManager.saveUserLoginInfo(email, businessName, businessType);
                                    LoadHomePage loadHomePage = new LoadHomePage(SignupActivity.this);

                                    break;

                            }
                        }
                    });


                } else {

                    Toast.makeText(SignupActivity.this, R.string.valid_email, Toast.LENGTH_LONG).show();

                }


            } else {


                Toast.makeText(SignupActivity.this, R.string.password_long, Toast.LENGTH_LONG).show();
            }


        } else {

            Toast.makeText(SignupActivity.this, R.string.empty_field, Toast.LENGTH_LONG).show();


        }


    }

    public boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
