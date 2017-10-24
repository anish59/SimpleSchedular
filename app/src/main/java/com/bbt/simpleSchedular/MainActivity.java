package com.bbt.simpleSchedular;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bbt.simpleSchedular.helper.AppConstants;
import com.bbt.simpleSchedular.helper.FunctionHelper;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private android.support.v7.widget.Toolbar toolbar;
    private android.widget.ImageView imgMonday;
    private android.widget.TextView txtMonday;
    private android.widget.ImageView imgTuesday;
    private android.widget.TextView txtTuesday;
    private android.widget.ImageView imgWednesday;
    private android.widget.TextView txtWednesday;
    private android.widget.LinearLayout ll1;
    private android.widget.ImageView imgThursday;
    private android.widget.TextView txtThursday;
    private android.widget.ImageView imgFriday;
    private android.widget.TextView txtFriday;
    private android.widget.ImageView imgSaturday;
    private android.widget.TextView txtSaturday;
    private android.widget.LinearLayout ll2;
    private android.widget.TextView txtSunday;
    private android.widget.LinearLayout ll3;
    private ImageView imgBookMark1;
    private ImageView imgBookMark2;
    private ImageView imgBookMark3;
    private ImageView imgBookMark4;
    private ImageView imgBookMark5;
    private ImageView imgBookMark6;
    private ImageView imgBookMark7;
    private android.support.v7.widget.CardView cardMonday;
    private android.support.v7.widget.CardView cardTuesday;
    private android.support.v7.widget.CardView cardWednesday;
    private android.support.v7.widget.CardView cardThursday;
    private android.support.v7.widget.CardView cardFriday;
    private android.support.v7.widget.CardView cardSaturday;
    private android.support.v7.widget.CardView cardSunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        init();
        initListeners();
    }

    private void initListeners() {
        cardMonday.setOnClickListener(this);
        cardTuesday.setOnClickListener(this);
        cardWednesday.setOnClickListener(this);
        cardWednesday.setOnClickListener(this);
        cardThursday.setOnClickListener(this);
        cardFriday.setOnClickListener(this);
        cardSaturday.setOnClickListener(this);
        cardSunday.setOnClickListener(this);
    }

    private void init() {
        setContentView(R.layout.activity_main);
        this.cardSunday = (CardView) findViewById(R.id.cardSunday);
        this.cardSaturday = (CardView) findViewById(R.id.cardSaturday);
        this.cardFriday = (CardView) findViewById(R.id.cardFriday);
        this.cardThursday = (CardView) findViewById(R.id.cardThursday);
        this.cardWednesday = (CardView) findViewById(R.id.cardWednesday);
        this.cardTuesday = (CardView) findViewById(R.id.cardTuesday);
        this.cardMonday = (CardView) findViewById(R.id.cardMonday);
        this.imgBookMark7 = (ImageView) findViewById(R.id.imgBookMark7);
        this.imgBookMark6 = (ImageView) findViewById(R.id.imgBookMark6);
        this.imgBookMark5 = (ImageView) findViewById(R.id.imgBookMark5);
        this.imgBookMark4 = (ImageView) findViewById(R.id.imgBookMark4);
        this.imgBookMark3 = (ImageView) findViewById(R.id.imgBookMark3);
        this.imgBookMark2 = (ImageView) findViewById(R.id.imgBookMark2);
        this.imgBookMark1 = (ImageView) findViewById(R.id.imgBookMark1); //starts from monday=1
        this.ll3 = (LinearLayout) findViewById(R.id.ll3);
        this.txtSunday = (TextView) findViewById(R.id.txtSunday);
        this.ll2 = (LinearLayout) findViewById(R.id.ll2);
        this.txtSaturday = (TextView) findViewById(R.id.txtSaturday);
        this.imgSaturday = (ImageView) findViewById(R.id.imgSaturday);
        this.txtFriday = (TextView) findViewById(R.id.txtFriday);
        this.imgFriday = (ImageView) findViewById(R.id.imgFriday);
        this.txtThursday = (TextView) findViewById(R.id.txtThursday);
        this.imgThursday = (ImageView) findViewById(R.id.imgThursday);
        this.ll1 = (LinearLayout) findViewById(R.id.ll1);
        this.txtWednesday = (TextView) findViewById(R.id.txtWednesday);
        this.imgWednesday = (ImageView) findViewById(R.id.imgWednesday);
        this.txtTuesday = (TextView) findViewById(R.id.txtTuesday);
        this.imgTuesday = (ImageView) findViewById(R.id.imgTuesday);
        this.txtMonday = (TextView) findViewById(R.id.txtMonday);
        this.imgMonday = (ImageView) findViewById(R.id.imgMonday);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);

//        FunctionHelper.initToolbar(MainActivity.this, toolbar, );
    }

    @Override
    protected void onResume() {
        super.onResume();
        calculateDay();
    }

    private void calculateDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        imgBookMark1.setVisibility(View.GONE);
        imgBookMark2.setVisibility(View.GONE);
        imgBookMark3.setVisibility(View.GONE);
        imgBookMark4.setVisibility(View.GONE);
        imgBookMark5.setVisibility(View.GONE);
        imgBookMark6.setVisibility(View.GONE);
        imgBookMark7.setVisibility(View.GONE);

        switch (day) {
            case Calendar.MONDAY:
                FunctionHelper.initToolbar(MainActivity.this, toolbar, getString(R.string.monday), "");
                imgBookMark1.setVisibility(View.VISIBLE);
                break;

            case Calendar.TUESDAY:
                FunctionHelper.initToolbar(MainActivity.this, toolbar, getString(R.string.tuesday), "");
                imgBookMark2.setVisibility(View.VISIBLE);
                break;

            case Calendar.WEDNESDAY:
                FunctionHelper.initToolbar(MainActivity.this, toolbar, getString(R.string.wednesday), "");
                imgBookMark3.setVisibility(View.VISIBLE);
                break;

            case Calendar.THURSDAY:
                FunctionHelper.initToolbar(MainActivity.this, toolbar, getString(R.string.thursday), "");
                imgBookMark4.setVisibility(View.VISIBLE);
                break;

            case Calendar.FRIDAY:
                FunctionHelper.initToolbar(MainActivity.this, toolbar, getString(R.string.friday), "");
                imgBookMark5.setVisibility(View.VISIBLE);
                break;
            case Calendar.SATURDAY:
                FunctionHelper.initToolbar(MainActivity.this, toolbar, getString(R.string.saturday), "");
                imgBookMark6.setVisibility(View.VISIBLE);
                break;

            case Calendar.SUNDAY:
                FunctionHelper.initToolbar(MainActivity.this, toolbar, getString(R.string.sunday), "");
                imgBookMark7.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String day;
        switch (id) {
            case R.id.cardMonday:
                day = getString(R.string.monday);
                launchIntent(day, AppConstants.Monday);
                break;
            case R.id.cardTuesday:
                day = getString(R.string.tuesday);
                launchIntent(day, AppConstants.Tuesday);
                break;
            case R.id.cardWednesday:
                day = getString(R.string.wednesday);
                launchIntent(day, AppConstants.Wednesday);
                break;
            case R.id.cardThursday:
                day = getString(R.string.thursday);
                launchIntent(day, AppConstants.Thursday);
                break;
            case R.id.cardFriday:
                day = getString(R.string.friday);
                launchIntent(day, AppConstants.Friday);
                break;
            case R.id.cardSaturday:
                day = getString(R.string.saturday);
                launchIntent(day, AppConstants.Saturday);
                break;

            case R.id.cardSunday:
                day = getString(R.string.sunday);
                launchIntent(day, AppConstants.Sunday);
                break;

        }
    }

    private void launchIntent(String day, int weekDayId) {
        Intent intent = new Intent(context, DayViewActivity.class);
        intent.putExtra(AppConstants.INTENT_DAY, day);
        intent.putExtra(AppConstants.INTENT_WEEK_DAY_ID, weekDayId);
        startActivity(intent);
    }
}
