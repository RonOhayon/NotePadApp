package com.ronoh.moveohometask;

public class Note {
    private long ID;
    private String title;
    private String content;
    private String date;
    private String location;

    public Note() { }

    public Note(String title, String content, String date, String location) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.location = location;
    }

    public Note(long ID, String title, String content, String date, String location) {
        this.ID = ID;
        this.title = title;
        this.content = content;
        this.date = date;
        this.location = location;
    }

    public long getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
