package com.bbt.simpleSchedular;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bbt.simpleSchedular.helper.AppConstants;
import com.bbt.simpleSchedular.helper.FunctionHelper;
import com.bbt.simpleSchedular.widgets.LinedEditText;

public class DayViewActivity extends AppCompatActivity {
    private Context context;
    private android.support.v7.widget.Toolbar toolbar;
    private com.bbt.simpleSchedular.widgets.LinedEditText edtStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        init();
    }

    private void init() {
        setContentView(R.layout.activity_day_view);
        this.edtStory = (LinedEditText) findViewById(R.id.edtStory);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);

        getIntentData();

    }

    private void getIntentData() {
        if (getIntent().getStringExtra(AppConstants.INTENT_DAY) != null) {
            String day = getIntent().getStringExtra(AppConstants.INTENT_DAY);
            FunctionHelper.initToolbar(DayViewActivity.this, toolbar, day, "");
        }
    }
}
