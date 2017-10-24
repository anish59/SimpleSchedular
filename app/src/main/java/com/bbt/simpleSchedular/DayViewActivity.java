package com.bbt.simpleSchedular;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.bbt.simpleSchedular.helper.AppConstants;
import com.bbt.simpleSchedular.helper.FunctionHelper;
import com.bbt.simpleSchedular.model.DaySchedule;
import com.bbt.simpleSchedular.model.DayScheduleRequest;
import com.bbt.simpleSchedular.widgets.LinedEditText;

import junit.framework.Test;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        init();
        initListeners();
    }

    private void initListeners() {
        edtStory.setOnTouchListener(new View.OnTouchListener() {

            private GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    setTextEditing();
                    return super.onDoubleTap(e);

                }
            });

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    private void init() {
        setContentView(R.layout.activity_day_view);
        this.edtStory = (LinedEditText) findViewById(R.id.edtStory);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);

        getIntentData();
        setTextEditing();
    }

    private void setTextEditing() {

        if (isEditingEnabled) {
            Toast.makeText(context, "Edit Mode On", Toast.LENGTH_SHORT).show();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            edtStory.setEnabled(true);
            isEditingEnabled = false;
        } else {
            Toast.makeText(context, "Edit Mode Off", Toast.LENGTH_SHORT).show();
            edtStory.setEnabled(false);
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            isEditingEnabled = true;
        }
    }

    private void getIntentData() {
        if (getIntent().getStringExtra(AppConstants.INTENT_DAY) != null) {
            day = getIntent().getStringExtra(AppConstants.INTENT_DAY);
            weekDayId = getIntent().getIntExtra(AppConstants.INTENT_WEEK_DAY_ID, 0);
            FunctionHelper.initToolbar(DayViewActivity.this, toolbar, day, "");

            List<DaySchedule> dayScheduleList = DaySchedule.getWeekdayStory(weekDayId);
            if (dayScheduleList != null && dayScheduleList.size() > 0) {
                isUpdate = true;
                menuEditStory.setVisible(true);
                menuDeleteStory.setVisible(true);
            } else {
                menuSaveStory.setVisible(true);
                isUpdate = false;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_day_view, menu);

        menuSaveStory = menu.findItem(R.id.menuSaveStory);
        menuEditStory = menu.findItem(R.id.menuEditStory);
        menuDeleteStory = menu.findItem(R.id.menuDeleteStory);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSaveStory:
                if (!isUpdate) {
                    saveStory();
                }
                return true;
            case R.id.menuEditStory:
                if (isUpdate) {
                    updateStory();
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
            Toast.makeText(context, String.format("your %s story is empty", day), Toast.LENGTH_SHORT).show();
            return;
        }
        String story = edtStory.getText().toString();


        if (day != null && weekDayId != 0) {
            DayScheduleRequest dayScheduleRequest = new DayScheduleRequest();
            dayScheduleRequest.setWeekDay_id((long) weekDayId);
            dayScheduleRequest.setWeekDay(day);
            dayScheduleRequest.setStory(story);
            /*dayScheduleRequest.setCreatedDateInt(createdDateInt);
            dayScheduleRequest.setUpdatedDateInt(createdDateInt);*/
            try {
                DaySchedule.insertInDaySchedule(dayScheduleRequest);
            } catch (Exception e) {
                Toast.makeText(context, "err" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(context, "Inserted", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteStory() {

    }

    private void updateStory() {

    }

}
