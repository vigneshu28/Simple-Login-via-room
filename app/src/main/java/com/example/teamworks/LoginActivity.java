package com.example.teamworks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {
    TextView newUser;
    EditText email, password;
    Button loginBtn;
    DAO db;
    AppDatabase dataBase;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getSupportActionBar().hide();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.login);

        newUser = (TextView) findViewById(R.id.createAccount);
        email = (EditText) findViewById(R.id.login_emailid);
        password = (EditText) findViewById(R.id.login_password);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        dataBase = Room.databaseBuilder(this, AppDatabase.class, "UserList.db")
                .allowMainThreadQueries()
                .build();

        db = dataBase.userDao();

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("data", db.getAll().toString());
                UserList user = db.getUser(email.getText().toString().trim(), password.getText().toString().trim());
                if (isValidEmail(email.getText().toString().trim())) {
                    if (user != null) {
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("User", user);
                   /* SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Name",user.getUsername());
                    editor.putString("Email",user.getEmail());
                    editor.putString("Phone",user.getPhone());
                    editor.apply();*/
                        startActivity(i);
                        finish();
                    } else {
                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "Unregistered user, or incorrect", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                } else {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Invalid email type", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
