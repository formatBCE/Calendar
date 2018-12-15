package com.andrukhiv.calendar.models;

import com.rilixtech.agendacalendarview.models.BaseCalendarEvent;
import com.rilixtech.agendacalendarview.models.CalendarEvent;
import com.rilixtech.agendacalendarview.models.IDayItem;
import com.rilixtech.agendacalendarview.models.IWeekItem;

import java.util.Calendar;

public class CalendarModel implements CalendarEvent {

    private long mId;
    private int mColor;
    private boolean mAllDay;
    private String mDuration;
    private String mTitle;
    private String mDescription;
    private String mLocation;
    private Calendar mStartTime;
    private Calendar mEndTime;


    public CalendarModel(BaseCalendarEvent calendarEvent) {
        this.mId = calendarEvent.getId();
        this.mColor = calendarEvent.getColor();
        this.mAllDay = calendarEvent.isAllDay();
        this.mDuration = calendarEvent.getDuration();
        this.mTitle = calendarEvent.getTitle();
        this.mDescription = calendarEvent.getDescription();
        this.mLocation = calendarEvent.getLocation();
        this.mStartTime = calendarEvent.getStartTime();
        this.mEndTime = calendarEvent.getEndTime();
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }


    public boolean isAllDay() {
        return this.mAllDay;
    }

    public String getmDuration() {
        return mDuration;
    }

    public void setmDuration(String mDuration) {
        this.mDuration = mDuration;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public Calendar getmStartTime() {
        return mStartTime;
    }

    public void setmStartTime(Calendar mStartTime) {
        this.mStartTime = mStartTime;
    }

    public Calendar getmEndTime() {
        return mEndTime;
    }

    public void setmEndTime(Calendar mEndTime) {
        this.mEndTime = mEndTime;
    }

    @Override
    public void setPlaceholder(boolean b) {

    }

    @Override
    public boolean isPlaceholder() {
        return false;
    }

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public void setLocation(String s) {

    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void setId(long l) {

    }

    @Override
    public Calendar getStartTime() {
        return null;
    }

    @Override
    public void setStartTime(Calendar calendar) {

    }

    @Override
    public Calendar getEndTime() {
        return null;
    }

    @Override
    public void setEndTime(Calendar calendar) {

    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String s) {

    }

    @Override
    public Calendar getInstanceDay() {
        return null;
    }

    @Override
    public void setInstanceDay(Calendar calendar) {

    }

    @Override
    public IDayItem getDayReference() {
        return null;
    }

    @Override
    public void setDayReference(IDayItem iDayItem) {

    }

    @Override
    public IWeekItem getWeekReference() {
        return null;
    }

    @Override
    public void setWeekReference(IWeekItem iWeekItem) {

    }

    @Override
    public CalendarEvent copy() {
        return null;
    }
}