package com.example.alex.dps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class FirstActivity extends AppCompatActivity implements OnEditorActionListener{

    //set widget variables
    private EditText itemETV;
    private EditText downPaymentETV;
    private EditText interestETV;
    private TextView savingsLabelTV;

    //set instance variables
    String itemString;
    String dpString;
    String interestString;

    float savings;
    float item;
    float downpayment;
    float interest;

    //define shared preferences
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //get reference to widgets
        itemETV = (EditText) findViewById(R.id.itemETV);
        downPaymentETV = (EditText) findViewById(R.id.downPaymentETV);
        interestETV = (EditText) findViewById(R.id.interestETV);
        savingsLabelTV = (TextView) findViewById(R.id.savingsLabelTV);

        //set listeners
        interestETV.setOnEditorActionListener(this);
        downPaymentETV.setOnEditorActionListener(this);
        itemETV.setOnEditorActionListener(this);

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

    }
    //clear all fields
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClickClear(View view) {
        itemETV.setText("");
        downPaymentETV.setText("");
        interestETV.setText("");

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        savings = 0.00f;
        savingsLabelTV.setText(currency.format(savings));
        itemETV.requestFocus();
    }

    public void onClickNext(View view) {
        //save information and pass to the next page when the next button is clicked
        final TextView savingsLabelTV = (TextView) findViewById(R.id.savingsLabelTV);
        Intent i = new Intent (this, SecondActivity.class);

        String msg = savingsLabelTV.getText().toString();
        i.putExtra("msg",msg);

        startActivity(i);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            calculateAndDisplay();
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calculateAndDisplay() {
        itemString = itemETV.getText().toString();
        dpString = downPaymentETV.getText().toString();
        interestString = interestETV.getText().toString();

        item = Float.parseFloat(itemString);
        downpayment = Float.parseFloat(dpString)*.01f;
        interest = Float.parseFloat(interestString)*.01f;

        if(item != 0 && downpayment != 0 && interest != 0){

            item = (item*interest)+item;
            savings = item*downpayment;

            NumberFormat currency = NumberFormat.getCurrencyInstance();
            savingsLabelTV.setText(currency.format(savings));
        }

    }

    /**************************OnPause and OnResume Methods**************************************/
    protected void onPause(){
        Editor editor = savedValues.edit();
        editor.putFloat("item",item);
        editor.putFloat("downpayment",downpayment);
        editor.putFloat("interest",interest);
        editor.putFloat("savings",savings);
        editor.apply();
        super.onPause();
    }

    protected void onResume(){
        super.onResume();
        item = savedValues.getFloat("item",item);
        downpayment = savedValues.getFloat("downpayment",downpayment);
        interest = savedValues.getFloat("interest",interest);
        savings = savedValues.getFloat("savings",savings);

    }
}
