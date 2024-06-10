package com.example.myapplication;

public class ModelClass {

    private String title;
    private String desc;
    private String dateNtime;
    private String type;
    private String time;

    ModelClass(String title,String desc, String dateNtime,String type,String time){
        this.dateNtime=dateNtime;
        this.desc=desc;
        this.title=title;
        this.type=type;
        this.time=time;
    }
    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDateNtime() {
        return dateNtime;
    }

    public String getType() {
        return type;
    }
    public String getTime() {
        return time;
    }
}
