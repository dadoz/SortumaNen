package com.aplication.material.sortumanen.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Event {
    public String timestamp;
    public String type;
    public String url;
    public String title;
    public String content;

    Event() {
    }

    Event(String title, String content, String timestamp, String type, String url) {
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
        this.type = type;
        this.url = url;
    }
}
