package com.tomove.common;

import java.util.Map;

public class Item {
    String name;
    String room;
    Map<String, String> properties;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}