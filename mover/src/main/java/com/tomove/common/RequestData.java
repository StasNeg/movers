package com.tomove.common;

import java.util.List;

public class RequestData {
    String date;
    List<Item> items;

    public RequestData() {
    }

    public String getDate() {
        return date;
    }

    public List<Item> getItems() {
        return items;
    }
}
