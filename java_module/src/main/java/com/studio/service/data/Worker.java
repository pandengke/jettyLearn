package com.studio.service.data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    public Worker(String wname, String wskill) {
        this.wname = wname;
        this.wskill = wskill;
    }

    public Worker() {

    }

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.IncrementGenerator")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
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
