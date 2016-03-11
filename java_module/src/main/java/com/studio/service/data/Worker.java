package com.studio.service.data;

/**
 * Created by 1234 on 2016/3/9.
 */
public class Worker {
    private long id;
    private String wid;
    private String wname;
    private String wskill;

    public Worker(long id, String wid, String wname, String wskill) {
        this.id = id;
        this.wid = wid;
        this.wname = wname;
        this.wskill = wskill;
    }

    public Worker() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getWskill() {
        return wskill;
    }

    public void setWskill(String wskill) {
        this.wskill = wskill;
    }
}
