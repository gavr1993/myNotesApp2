package com.example.mynotesapp;

public class Note {
    private String name;
    private String body;
    private String createDateTime;
    private String modifiedDateTime;
    private String id;

    public Note(String name, String body, String createDateTime, String modifiedDateTime, String id) {
        this.name = name;
        this.body = body;
        this.createDateTime = createDateTime;
        this.modifiedDateTime = modifiedDateTime;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(String modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

