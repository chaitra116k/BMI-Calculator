package com.example.bmi.bmiResult;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bmi.R;
import com.example.bmi.bmiPage.BmiPage;

import java.text.DecimalFormat;
import java.util.Objects;

public class BmiResult extends AppCompatActivity {
    private TextView bmiIndex;
    private TextView bmiStatus;
    private TextView tips;
    private Button reCalculate;
    String ageText;
    String weightText;
    String feetText;
    String inchesText;
    String heightIncm;
    String heightData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7dad3f")));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        ageText = getIntent().getStringExtra("age");
        weightText = getIntent().getStringExtra("weight");
        feetText = getIntent().getStringExtra("feet");
        inchesText = getIntent().getStringExtra("inches");
        heightData = getIntent().getStringExtra("heightData");
        heightIncm = getIntent().getStringExtra("heightInCm");
        findViews();
        setOnClickListeners();
        calulateBmi();
    }

    private void findViews() {
        bmiIndex = findViewById(R.id.text_view_bmi_index);
        bmiStatus = findViewById(R.id.text_view_health_status);
        tips = findViewById(R.id.text_view_tips);
        reCalculate = findViewById(R.id.button_recalculate);
    }

    private void setOnClickListeners() {
        reCalculate.setOnClickListener(v -> {
            Intent intent = new Intent(BmiResult.this, BmiPage.class);
            startActivity(intent);
        });
    }

    private void calulateBmi() {
        if (heightData.equals("ft")) {
            int feet = Integer.parseInt(feetText);
            int inches = Integer.parseInt(inchesText);
            int weight = Integer.parseInt(weightText);
            int totalInches = (feet * 12) + inches;
            double heightInMeters = totalInches * 0.0254;
            double bmiVal = weight / (heightInMeters * heightInMeters);
            displayResult(bmiVal);
        } else {
            int heignCm = Integer.parseInt(heightIncm);
            int weight = Integer.parseInt(weightText);

            double heightInMeters = heignCm * 0.01;
            double bmiVal = weight / (heightInMeters * heightInMeters);
            displayResult(bmiVal);
        }
    }

    private void displayResult(double bmiVal) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmiVal);
        bmiIndex.setText(bmiTextResult);
        int age = Integer.parseInt(ageText);
        String status;
        if (bmiVal < 18.5) {
            status = "under weight";
            bmiStatus.setTextColor(Color.RED);
        } else if (bmiVal > 25) {
            status = "over weight";
            bmiStatus.setTextColor(Color.RED);
        } else {
            status = "Healthy";
            bmiStatus.setTextColor(Color.GREEN);
        }
        bmiStatus.setText(status);
        if (age >= 18) {
            generaltips(bmiVal);
        } else {
            displayForChildrens();
        }
    }

    private void generaltips(double bmiVal) {
        String data;
        if (bmiVal < 18.5) {
            data = "You are lower than normal body weight you should eat more";
        } else if (bmiVal > 25) {
            data = "You have higher than normal body weight try to exercise more!";
        } else {
            data = "You have a normal body weight good job";
        }
        tips.setText(data);
    }

    private void displayForChildrens() {
        String data;
        data = "As You are under 18 please consult your doctor for healthy range";
        tips.setText(data);
    }
}