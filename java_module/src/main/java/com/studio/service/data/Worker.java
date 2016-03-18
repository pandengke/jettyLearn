package com.studio.service.data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 1234 on 2016/3/9.
 */
@Table(name = "d_worker")
@Entity
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

    @GenericGenerator(name = "generator", strategy = "increment")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column
    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    @Column
    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    @Column
    public String getWskill() {
        return wskill;
    }

    public void setWskill(String wskill) {
        this.wskill = wskill;
    }
}
