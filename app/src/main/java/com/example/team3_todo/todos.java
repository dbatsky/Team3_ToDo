package com.example.team3_todo;

import java.util.Date;

public class todos {

    private String title, description, date, key;
    private Date label;

    public todos() {
    }

    public Date getLabel() {
        return label;
    }

    public void setLabel(Date label) {
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
