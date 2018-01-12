package com.tomove.common;

import java.util.Map;

public class Item {
    String name;
    Map<String, String> properties;
    String addressIn;
    String addressOut;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public String getAddressIn() {
        return addressIn;
    }

    public String getAddressOut() {
        return addressOut;
    }
}