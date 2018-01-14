package com.tomove.controller.to;

import java.io.Serializable;

public class DataTo implements Serializable {
    private boolean success;
    private Object data;

    public DataTo() {
    }

    public DataTo(boolean succes, Object data) {
        this.success = succes;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSucces(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
