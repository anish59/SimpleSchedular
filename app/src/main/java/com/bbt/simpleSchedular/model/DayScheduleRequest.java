package com.bbt.simpleSchedular.model;

import android.support.annotation.Nullable;

/**
 * Created by anish on 24-10-2017.
 */

public class DayScheduleRequest extends DaySchedule {

    private long daySchedule_id;
    private Long weekDay_id;
    private String weekDay;
    private String story;
    private Long createdDateInt;
    private Long updatedDateInt;

    @Override
    public long daySchedule_id() {
        return daySchedule_id;
    }

    @Nullable
    @Override
    public Long weekDay_id() {
        return weekDay_id;
    }

    @Nullable
    @Override
    public String weekDay() {
        return weekDay;
    }

    @Nullable
    @Override
    public String story() {
        return story;
    }

    @Nullable
    @Override
    public Long createdDateInt() {
        return createdDateInt;
    }

    @Nullable
    @Override
    public Long updatedDateInt() {
        return updatedDateInt;
    }

    @Override
    public String toString() {
        return "DaySchedule{"
                + "daySchedule_id=" + daySchedule_id + ", "
                + "weekDay_id=" + weekDay_id + ", "
                + "weekDay=" + weekDay + ", "
                + "story=" + story + ", "
                + "createdDateInt=" + createdDateInt + ", "
                + "updatedDateInt=" + updatedDateInt
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof DaySchedule) {
            DaySchedule that = (DaySchedule) o;
            return (this.daySchedule_id == that.daySchedule_id())
                    && ((this.weekDay_id == null) ? (that.weekDay_id() == null) : this.weekDay_id.equals(that.weekDay_id()))
                    && ((this.weekDay == null) ? (that.weekDay() == null) : this.weekDay.equals(that.weekDay()))
                    && ((this.story == null) ? (that.story() == null) : this.story.equals(that.story()))
                    && ((this.createdDateInt == null) ? (that.createdDateInt() == null) : this.createdDateInt.equals(that.createdDateInt()))
                    && ((this.updatedDateInt == null) ? (that.updatedDateInt() == null) : this.updatedDateInt.equals(that.updatedDateInt()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= (this.daySchedule_id >>> 32) ^ this.daySchedule_id;
        h *= 1000003;
        h ^= (weekDay_id == null) ? 0 : this.weekDay_id.hashCode();
        h *= 1000003;
        h ^= (weekDay == null) ? 0 : this.weekDay.hashCode();
        h *= 1000003;
        h ^= (story == null) ? 0 : this.story.hashCode();
        h *= 1000003;
        h ^= (createdDateInt == null) ? 0 : this.createdDateInt.hashCode();
        h *= 1000003;
        h ^= (updatedDateInt == null) ? 0 : this.updatedDateInt.hashCode();
        return h;
    }

    public long getDaySchedule_id() {
        return daySchedule_id;
    }

    public void setDaySchedule_id(long daySchedule_id) {
        this.daySchedule_id = daySchedule_id;
    }

    public Long getWeekDay_id() {
        return weekDay_id;
    }

    public void setWeekDay_id(Long weekDay_id) {
        this.weekDay_id = weekDay_id;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Long getCreatedDateInt() {
        return createdDateInt;
    }

    public void setCreatedDateInt(Long createdDateInt) {
        this.createdDateInt = createdDateInt;
    }

    public Long getUpdatedDateInt() {
        return updatedDateInt;
    }

    public void setUpdatedDateInt(Long updatedDateInt) {
        this.updatedDateInt = updatedDateInt;
    }
}
