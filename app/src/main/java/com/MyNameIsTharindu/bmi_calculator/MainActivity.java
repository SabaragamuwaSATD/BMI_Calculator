package com.MyNameIsTharindu.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView myString;
    private RadioButton yourGenderMale;
    private RadioButton yourGenderFemale;
    private EditText yourAge;
    private EditText yourFeet;
    private EditText yourInches;
    private EditText yourWeight;
    private Button calculateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        buttonClickListener();

    }
    private void findViews(){
        myString = findViewById(R.id.text_view_result);
        yourGenderMale = findViewById(R.id.radio_button_male);
        yourGenderFemale = findViewById(R.id.radio_button_female);
        yourAge = findViewById(R.id.input_age);
        yourFeet = findViewById(R.id.input_feet);
        yourInches = findViewById(R.id.input_inches);
        yourWeight = findViewById(R.id.input_weight);
        calculateButton = findViewById(R.id.calculate_button);
    }
    private void buttonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmi = calculateBmi();
                String ageText = yourAge.getText().toString();
                int age = Integer.parseInt(ageText);
                
                if(age > 18){
                    displayResult(bmi);   
                }else{
                    displayResultWithGuidance(bmi);
                }
            }
        });
    }

    private double calculateBmi() {
        String feetText = yourFeet.getText().toString();
        String inchesText = yourInches.getText().toString();
        String weightText = yourWeight.getText().toString();
        
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        double heightInMeters = totalInches * 0.0254;

        return weight / (heightInMeters * heightInMeters);

    }

    private void displayResult(double bmi){
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullTextResult;

        if(bmi < 18.5){
            fullTextResult = bmiTextResult + " You are in Under Weight";
        }else if(bmi > 25){
            fullTextResult = bmiTextResult + " You are in Over Weight";
        }else{
            fullTextResult = bmiTextResult + " You are in Healthy Weight";
        }

        myString.setText(fullTextResult);
    }

    private void displayResultWithGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullTextResult;

        if(yourGenderMale.isChecked()){
            fullTextResult = bmiTextResult + " As you are Under 18 please consult with your doctor and follow the male guideline ";
        }else if(yourGenderFemale.isChecked()){
            fullTextResult = bmiTextResult + " As you are Under 18 please consult with your doctor and follow the Female guideline ";
        }else{
            fullTextResult = bmiTextResult + " As you are Under 18 please consult with your doctor and follow the General guideline ";
        }

        myString.setText(fullTextResult);
    }
}