package com.bbt.simpleSchedular;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.bbt.simpleSchedular.helper.AppConstants;
import com.bbt.simpleSchedular.helper.DateHelper;
import com.bbt.simpleSchedular.helper.FunctionHelper;
import com.bbt.simpleSchedular.model.DaySchedule;
import com.bbt.simpleSchedular.model.DayScheduleRequest;
import com.bbt.simpleSchedular.widgets.LinedEditText;

import junit.framework.Test;

import java.util.Date;
import java.util.List;

public class DayViewActivity extends AppCompatActivity {
    private MenuItem menuSaveStory, menuDeleteStory, menuEditStory;
    private Context context;
    private android.support.v7.widget.Toolbar toolbar;
    private com.bbt.simpleSchedular.widgets.LinedEditText edtStory;
    private String day;
    private int weekDayId;
    private boolean isEditingEnabled = false;
    private boolean isUpdate = false;
    DaySchedule daySchedule;
    private Long createdDateInt;
    private long dayScheduleId;
    private boolean isStayStill = true;

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

        setToobar();

        getIntentData();
        if (isUpdate) {
            edtStory.setInputType(InputType.TYPE_NULL);
        }
    }

    private void setToobar() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });
    }


    private void getIntentData() {
        if (getIntent().getStringExtra(AppConstants.INTENT_DAY) != null) {
            day = getIntent().getStringExtra(AppConstants.INTENT_DAY);
            weekDayId = getIntent().getIntExtra(AppConstants.INTENT_WEEK_DAY_ID, 0);
            FunctionHelper.initToolbar(DayViewActivity.this, toolbar, day, "");

            List<DaySchedule> dayScheduleList = DaySchedule.getWeekdayStory(weekDayId);
            if (dayScheduleList != null && dayScheduleList.size() > 0) {
                edtStory.setText(dayScheduleList.get(0).story());
                createdDateInt = dayScheduleList.get(0).createdDateInt();
                dayScheduleId = dayScheduleList.get(0).daySchedule_id();
                isUpdate = true;
            } else {
                isUpdate = false;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_day_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSaveStory:
                if (!isUpdate) {
                    saveStory();
                } else {
                    updateStory();
                }
                return true;
            case R.id.menuEditStory:
                if (isUpdate) {
                    edtStory.setInputType(InputType.TYPE_CLASS_TEXT);
                    Toast.makeText(context, "Edit Mode on", Toast.LENGTH_SHORT).show();
                    menuEditStory.setVisible(false);
                }
                return true;

            case R.id.menuDeleteStory:
                if (isUpdate) {
                    deleteStory();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveStory() {
        if (TextUtils.isEmpty(edtStory.getText().toString())) {
            FunctionHelper.showAlertDialogWithOneOpt(context, String.format("your %s story is empty", day).toLowerCase(), new FunctionHelper.DialogOptionsSelectedListener() {
                @Override
                public void onSelect(boolean isYes) {
                    if (isYes) {
                        isStayStill = false;
                        deleteStory();
                        finish();
                    } else {
                        isStayStill = true;
                    }
                }
            }, "Fine by me", "Cancel");
        } else {
            String story = edtStory.getText().toString();
            long currentdateTime = Long.parseLong(DateHelper.getDateInIntFormat(new Date()));

            if (day != null && weekDayId != 0) {
                DayScheduleRequest dayScheduleRequest = new DayScheduleRequest();
                dayScheduleRequest.setWeekDay_id((long) weekDayId);
                dayScheduleRequest.setWeekDay(day);
                dayScheduleRequest.setStory(story);
                dayScheduleRequest.setCreatedDateInt(currentdateTime);//old created date time
                dayScheduleRequest.setUpdatedDateInt(currentdateTime);
                try {
                    DaySchedule.insertInDaySchedule(dayScheduleRequest);
                    Toast.makeText(context, "Story saved", Toast.LENGTH_SHORT).show();
                    isStayStill = false;
                    finish();
                } catch (Exception e) {
                    System.out.println("err" + e.getMessage());
                }
            }
        }

    }

    private void deleteStory() {
        try {
            DaySchedule.deleteDaySchedule(dayScheduleId);
            if (isUpdate) {
                Toast.makeText(context, "Story deleted!", Toast.LENGTH_SHORT).show();
            }
            isStayStill = false;
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStory() {
        if (TextUtils.isEmpty(edtStory.getText().toString())) {
            FunctionHelper.showAlertDialogWithOneOpt(context, String.format("your %s story is empty", day).toLowerCase(), new FunctionHelper.DialogOptionsSelectedListener() {
                @Override
                public void onSelect(boolean isYes) {
                    if (isYes) {
                        deleteStory();
                    } else {
                        isStayStill = true;
                    }
                }
            }, "Fine by me", "Cancel");
        } else {
            String story = edtStory.getText().toString();
            long currentdateTime = Long.parseLong(DateHelper.getDateInIntFormat(new Date()));
            try {
                DaySchedule.updateInDaySchedule(dayScheduleId, story, currentdateTime);
                Toast.makeText(context, "Story Updated", Toast.LENGTH_SHORT).show();
                isStayStill = false;
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menuSaveStory = menu.findItem(R.id.menuSaveStory);
        menuEditStory = menu.findItem(R.id.menuEditStory);
        menuDeleteStory = menu.findItem(R.id.menuDeleteStory);

        if (isUpdate) {
            menuEditStory.setVisible(true);
            menuDeleteStory.setVisible(true);
            menuSaveStory.setVisible(true);
        } else {
            menuSaveStory.setVisible(true);
            menuEditStory.setVisible(false);
            menuDeleteStory.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isUpdate) {
            updateStory();
        } else {
            saveStory();
        }
    }

    @Override
    public void finish() {
        if (!isStayStill) {
            super.finish();
        }
    }
}
