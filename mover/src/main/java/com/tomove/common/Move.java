package com.tomove.common;

import java.util.List;

public class Move {
    String addressIn;
    String addressOut;
    List<Item> items;

    public Move() {
    }

    public String getAddressIn() {
        return addressIn;
    }

    public String getAddressOut() {
        return addressOut;
    }

    public List<Item> getItems() {
        return items;
    }
}
