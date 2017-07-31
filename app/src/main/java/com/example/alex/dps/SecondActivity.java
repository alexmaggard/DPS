package com.example.alex.dps;

import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


@RequiresApi(api = Build.VERSION_CODES.N)
public class SecondActivity extends AppCompatActivity {

    private  TextView sixMonthSaveTV;
    private  TextView oneYearSaveTV;
    private  TextView twoYearSaveTV;

    //widget variables for display
    private TextView yearZeroTV;
    private TextView yearOneTV;
    private TextView yearTwoTV;
    private TextView monthsSixTV;
    private TextView monthsTwelveTV;
    private TextView months24TV;

    float saveAmount;
    float displayAmount;


      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //retrieve bundle to bring passed information from first intent
        Bundle data = getIntent().getExtras();
        if(data == null){
            return;
        }
        //apply info to the current text view on page 2 of the app
        String msg = data.getString("msg");
        TextView goalLabelTV = (TextView) findViewById(R.id.goalLabelTV);
        goalLabelTV.setText(msg);


          //get reference to widget
          sixMonthSaveTV = (TextView) findViewById(R.id.sixMonthSaveTV);
          oneYearSaveTV = (TextView) findViewById(R.id.oneYearSaveTV);
          twoYearSaveTV = (TextView) findViewById(R.id.yearTwoSaveTV);

          //year column widgets
          yearZeroTV = (TextView) findViewById(R.id.yearZeroTV);
          yearOneTV = (TextView) findViewById(R.id.yearOneTV);
          yearTwoTV = (TextView) findViewById(R.id.yearTwoTV);

          //months column widgets
          monthsSixTV = (TextView) findViewById(R.id.monthsSixTV);
          monthsTwelveTV = (TextView) findViewById(R.id.monthsTwelveTV);
          months24TV = (TextView) findViewById(R.id.months24TV);

          NumberFormat currency = NumberFormat.getCurrencyInstance();

          saveAmount = Float.parseFloat(data.getString(msg));
          //start with short term
          displayAmount = saveAmount/6;
          sixMonthSaveTV.setText(currency.format(displayAmount));
          displayAmount = saveAmount/12;
          oneYearSaveTV.setText(currency.format(displayAmount));
          displayAmount = saveAmount/24;
          twoYearSaveTV.setText(currency.format(displayAmount));

          yearZeroTV.setText("<1");
    }


    /*
    * Onlick methods to change savings plan
    *
    * */

    public void shortTermSaving(View view) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        displayAmount = saveAmount/6;
        sixMonthSaveTV.setText(currency.format(displayAmount));
        displayAmount = saveAmount/12;
        oneYearSaveTV.setText(currency.format(displayAmount));
        displayAmount = saveAmount/24;
        twoYearSaveTV.setText(currency.format(displayAmount));

        yearZeroTV.setText("<1");
    }

    public void longTermSaving(View view) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        displayAmount = saveAmount/6;
        sixMonthSaveTV.setText(currency.format(displayAmount));
        displayAmount = saveAmount/12;
        oneYearSaveTV.setText(currency.format(displayAmount));
        displayAmount = saveAmount/24;
        twoYearSaveTV.setText(currency.format(displayAmount));

        yearZeroTV.setText("<1");
    }

    public void mediumTermSaving(View view) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        displayAmount = saveAmount/6;
        sixMonthSaveTV.setText(currency.format(displayAmount));
        displayAmount = saveAmount/12;
        oneYearSaveTV.setText(currency.format(displayAmount));
        displayAmount = saveAmount/24;
        twoYearSaveTV.setText(currency.format(displayAmount));

        yearZeroTV.setText("<1");
    }


    /*          FOR REFERENCE


    private TextView yearZeroTV;
    private TextView yearOneTV;
    private TextView yearTwoTV;
    private TextView monthsSixTV;
    private TextView monthsTwelveTV;
    private TextView months24TV;



     */

}
