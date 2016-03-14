package com.mindstorm.param;

/**
 * Created by Administrator on 2016/3/14.
 */
public class BaseParams {

    private String id;
    private int version;
    private String language;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
