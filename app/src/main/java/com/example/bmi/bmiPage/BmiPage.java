package com.example.bmi.bmiPage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bmi.bmiResult.BmiResult;
import com.example.bmi.R;

import java.util.Objects;

import io.ghyeok.stickyswitch.widget.StickySwitch;

public class BmiPage extends AppCompatActivity {

    private Button ageIncrement;
    private Button ageDecrement;
    private Button weightIncrement;
    private Button weightDecrement;
    private TextView ageText;
    private TextView weightText;
    private SeekBar cmView;
    private TextView heightText;
    private Button calculateButton;
    private StickySwitch heightView;
    private LinearLayout feetView;
    private Spinner spinnerfeet;
    private Spinner spinnerinches;
    String feetVal;
    String inchesVal;
    String ageVal="1";
    String weightVal="45";
    int ageCount = 0;
    int weightCount = 45;
    String heightVal="cm";
    String heightIncm="130";

String heightDisplay = "130"+ "cm";

    public BmiPage() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_page);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7dad3f")));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        findViews();
        increaseText();
        decreseText();
        heightParameterCheck("cm");
    }

    private void findViews() {
        ageIncrement = findViewById(R.id.button_age_increment);
        ageDecrement = findViewById(R.id.button_age_decrement);
        ageText = findViewById(R.id.text_view_age);
        weightIncrement = findViewById(R.id.button_weight_increment);
        weightDecrement = findViewById(R.id.button_weight_decrement);
        weightText = findViewById(R.id.text_view_weight);
        cmView = findViewById(R.id.seekBar_cm);
        feetView = findViewById(R.id.linearlayout_feet_inches);
        heightText = findViewById(R.id.height_text);
        calculateButton = findViewById(R.id.button_calculate);
        heightView = findViewById(R.id.sticky_switch_height);
        spinnerfeet = findViewById(R.id.spinner_feet);
        spinnerinches = findViewById(R.id.spinnerinches);
    }

    private void increaseText() {
        ageIncrement.setOnClickListener(v -> {
            ageCount++;
            String age = String.valueOf(ageCount);
            ageText.setText(age);
            ageVal = age;
        });
        weightIncrement.setOnClickListener(v -> {
            weightCount++;
            String weight = String.valueOf(weightCount);
            weightText.setText(weight);
            weightVal = weight;
        });
    }

    private void heightParameterCheck(String val) {
        if (val.equals("cm")) {
            cmView.setVisibility(View.VISIBLE);
            feetView.setVisibility(View.INVISIBLE);
            heightText.setVisibility(View.VISIBLE);
            heightText.setText(heightDisplay);
        } else {
            cmView.setVisibility(View.INVISIBLE);
            feetView.setVisibility(View.VISIBLE);
            heightText.setVisibility(View.INVISIBLE);
        }
    }
    private void decreseText() {
        ageDecrement.setOnClickListener(v -> {
            if (ageCount <= 0) ageCount = 0;
            else ageCount--;
            String age = String.valueOf(ageCount);
            ageText.setText(age);
            ageVal = age;
        });
        weightDecrement.setOnClickListener(v -> {
            if (weightCount <= 0) weightCount = 0;
            else weightCount--;
            String weight = String.valueOf(weightCount);
            weightText.setText(weight);
            weightVal = weight;
        });

        cmView.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                heightIncm = String.valueOf(progress * 5);
                heightDisplay = heightIncm + " cm";
                heightText.setText(heightIncm+" cm");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Integer[] feetData = {1, 2, 3, 4, 5, 6, 7};
        Integer[] inchesData = {0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11};

        ArrayAdapter<Integer> feetList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feetData);
        spinnerfeet.setAdapter(feetList);
        ArrayAdapter<Integer> inchesList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, inchesData);
        spinnerinches.setAdapter(inchesList);


        spinnerfeet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                feetVal = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerinches.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                inchesVal = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        calculateButton.setOnClickListener(v -> {
                Intent intent = new Intent(BmiPage.this, BmiResult.class);
                intent.putExtra("age", ageVal);
                intent.putExtra("weight", weightVal);
                intent.putExtra("feet", feetVal);
                intent.putExtra("inches", inchesVal);
                intent.putExtra("heightData", heightVal);
                intent.putExtra("heightInCm", heightIncm);
                startActivity(intent);
        });


        heightView.setOnSelectedChangeListener((direction, text) -> {
            heightParameterCheck(text);
            heightVal = text;
        });
    }
}