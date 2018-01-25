package com.tomove.common;

import java.util.Map;

public class ItemDto {
    String name;
    String room;
    Map<String, String> properties;

    public ItemDto() {
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}