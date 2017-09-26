package com.expertzlab.yieldmanagement.models;

/**
 * Created by gireeshbabu on 26/09/17.
 */
public class YMDate {
    int id;
    String date;
    int month;
    int year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = Integer.parseInt( month);
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setYear(String year) {
        this.year = Integer.parseInt( year);
    }
}
