package com.example.trialone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserWelcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome);
    }

    public void onUserManagement(View view){
        Intent intent = new Intent(this, UserManagement.class);
        startActivity(intent);
    }
}
