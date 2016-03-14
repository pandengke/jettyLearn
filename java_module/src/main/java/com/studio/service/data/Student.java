package com.studio.service.data;

import com.google.gson.GsonBuilder;

/**
 * Created by Administrator on 2016/3/14.
 */
public class Student {
    private String name;
    private String age;
    private String height;

    @Override
    public String toString() {
        return
                new GsonBuilder().
                        serializeNulls().create().
                        toJson(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
