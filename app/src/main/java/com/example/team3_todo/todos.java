package com.example.team3_todo;

import java.io.Serializable;

public class todos implements Serializable {

    private String title, date, description;

    public todos() {
    }



    public todos(String title, String date, String description) {
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return date;
    }

    public void setData(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
