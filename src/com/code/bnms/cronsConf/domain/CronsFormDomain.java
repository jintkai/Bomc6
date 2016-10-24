package com.code.bnms.cronsConf.domain;

import com.code.common.JacksonUtil;

/**
 * Created by jon on 2016/10/20.
 */
public class CronsFormDomain {
    private String cronName;
    private String cronDesc;
    private String seconds;
    private String minute;
    private String hour;
    private String day;
    private String month;
    private String week;
    private String year;

    public String getCronName() {
        return cronName;
    }

    public void setCronName(String cronName) {
        this.cronName = cronName;
    }

    public String getCronDesc() {
        return cronDesc;
    }

    public void setCronDesc(String cronDesc) {
        this.cronDesc = cronDesc;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String toString(){

        return JacksonUtil.toJSon(this);
    }
}
