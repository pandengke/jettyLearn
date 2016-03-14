package com.studio.service.templates;

import com.google.gson.GsonBuilder;
import com.studio.service.doc.ResponseCode;

/**
 * Created by Administrator on 2016/3/10.
 */
public class BaseTemplate<M, D> {
    public boolean status;
    public M msg;
    public D data;
    public String code;

    public BaseTemplate(boolean status, M msg, D data, String code) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public BaseTemplate(D data, String code) {
        this(true, null, data, code);
    }

    public BaseTemplate(D data) {
        this(true, null, data, ResponseCode.OK.getCode());
    }


    @Override
    public String toString() {
        return new GsonBuilder().
                serializeNulls().create().
                toJson(this);
    }
}
