package com.example.podcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void pindah(View view) {
        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void pindahHost(View view) {
        Intent intent = new Intent(HomeActivity.this,HostActivity.class);
        startActivity(intent);
    }
}