package com.example.bmi.mainActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bmi.R;
import com.example.bmi.bmiPage.BmiPage;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7dad3f")));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        Button bmi = findViewById(R.id.button_bmi);
        bmi.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BmiPage.class);
        startActivity(intent);
        });
    }
}