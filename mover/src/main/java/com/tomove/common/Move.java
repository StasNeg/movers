package com.tomove.common;

import java.util.List;

public class Move {
    public Address addressIn;
    public Address addressOut;
    public List<Item> items;

    public Move() {
    }

    public Address getAddressIn() {
        return addressIn;
    }

    public Address getAddressOut() {
        return addressOut;
    }

    public List<Item> getItems() {
        return items;
    }
}
