package com.bbt.simpleSchedular.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bbt.simpleSchedular.DayScheduleModel;
import com.bbt.simpleSchedular.dataBaseHelper.DatabaseManager;
import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;
import com.squareup.sqldelight.SqlDelightStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anish on 24-10-2017.
 */
@AutoValue
public abstract class DaySchedule implements DayScheduleModel {
    public static final Factory<DaySchedule> FACTORY = new Factory<>(AutoValue_DaySchedule::new);
    public static final RowMapper<DaySchedule> DAY_SCHEDULE_ROW_MAPPER = FACTORY.select_all_from_DayScheduleMapper();

    public static void insertInDaySchedule(DayScheduleRequest dayScheduleRequest) {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        DaySchedule.Insert_into_DaySchedule insert_into_daySchedule =
                new Insert_into_DaySchedule(sqLiteDatabase);
        insert_into_daySchedule.bind(
                dayScheduleRequest.weekDay_id(),
                dayScheduleRequest.weekDay(),
                dayScheduleRequest.story(),
                dayScheduleRequest.createdDateInt(),
                dayScheduleRequest.updatedDateInt());
        insert_into_daySchedule.program.execute();
        DatabaseManager.getInstance().closeDatabase();
    }

    public static void updateInDaySchedule(long dayScheduleId, String story, long updatedDateInt) {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        DaySchedule.Update_into_DaySchedule update_into_daySchedule = new Update_into_DaySchedule(sqLiteDatabase);
        update_into_daySchedule.bind(story, updatedDateInt, dayScheduleId);
        update_into_daySchedule.program.execute();
        DatabaseManager.getInstance().closeDatabase();
    }

    public static void deleteDaySchedule(long dayScheduleId) {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        DaySchedule.Delete_DaySchedule delete_daySchedule = new Delete_DaySchedule(sqLiteDatabase);
        delete_daySchedule.bind(dayScheduleId);
        delete_daySchedule.program.execute();
        DatabaseManager.getInstance().closeDatabase();
    }


    public static List<DaySchedule> getWeekdayStory(long weekDayId) {
        List<DaySchedule> daySchedules = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        SqlDelightStatement query = DaySchedule.FACTORY.select_all_from_DaySchedule(weekDayId);
        Cursor cursor = sqLiteDatabase.rawQuery(query.statement, query.args);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            daySchedules.add(DaySchedule.FACTORY.select_all_from_DayScheduleMapper().map(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return daySchedules;
    }
}
