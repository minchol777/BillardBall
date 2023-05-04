package com.example.billardball;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Billiard billiard = new Billiard(this);
        setContentView(billiard);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

}