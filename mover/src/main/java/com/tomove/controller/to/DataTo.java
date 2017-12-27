package com.tomove.controller.to;

import java.io.Serializable;

public class DataTo implements Serializable {
    private boolean succes;
    private Object data;

    public DataTo() {
    }

    public DataTo(boolean succes, Object data) {
        this.succes = succes;
        this.data = data;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
