package com.studio.service.doc;

/**
 * Created by Administrator on 2016/3/10.
 */
public enum ResponseCode {

    /**
     * ResponseCode is designed a string that length() is 6;
     * each bit of the string map to a kind of info;
     * 1 -> server error;
     * 2 -> permission error;
     * 3 -> auth error;
     * 4 -> ???;
     * 5 -> ???;
     * 6 -> ???;
     */

    OK("000000"),
    UNKNOWN("900000");

    public final String code;

    ResponseCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
