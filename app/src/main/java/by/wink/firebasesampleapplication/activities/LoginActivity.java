package by.wink.firebasesampleapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import by.wink.firebasesampleapplication.R;
import by.wink.firebasesampleapplication.controllers.AuthController;
import by.wink.firebasesampleapplication.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().beginTransaction().
                add(R.id.main_content,LoginFragment.getInstance()).commit();
    }
}
