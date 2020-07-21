package com.example.teamworks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.room.Room;

import com.example.teamworks.model.UserList;
import com.example.teamworks.utils.AppDatabase;
import com.example.teamworks.utils.DAO;
import com.google.android.material.snackbar.Snackbar;

public class SignupActivity extends AppCompatActivity {
    TextView newUser;
    EditText phoneNumber,username, emailId, passwordone, confirmPassword;
    Button signupBtn;
    private DAO userDao;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        getSupportActionBar().hide();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.signup);
        newUser = (TextView) findViewById(R.id.createAccount);
        username = (EditText) findViewById(R.id.userName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        emailId = (EditText) findViewById(R.id.login_emailid);
        passwordone = (EditText) findViewById(R.id.signup_password);
        confirmPassword = (EditText) findViewById(R.id.confirm_password);
        signupBtn = (Button) findViewById(R.id.signupBtn);

        userDao = Room.databaseBuilder(this, AppDatabase.class, "UserList.db").allowMainThreadQueries()
                .build().userDao();

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser();
            }
        });
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void saveUser() {
        String userName = username.getText().toString().trim();
        String email = emailId.getText().toString().trim();
        String password = passwordone.getText().toString().trim();
        String passwordConf = confirmPassword.getText().toString().trim();
        String phone = phoneNumber.getText().toString().trim();
        if (isValidEmail(email)) {
            if (password.equals(passwordConf)) {
                UserList user = new UserList(userName, password, email, phone);
                userDao.insert(user);
                Intent moveToLogin = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(moveToLogin);

            } else {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Password is not matching", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }else{
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Invalid email type", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
