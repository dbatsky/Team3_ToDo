package com.example.team3_todo;

public class todos {

    private String title, date, description, key;

    public todos() {
    }

    public todos(String title, String date, String description, String key) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.key = key;
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
