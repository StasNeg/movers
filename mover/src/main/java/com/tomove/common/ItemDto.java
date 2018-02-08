package com.tomove.common;

import java.util.Map;

public class ItemDto {
    public String name;
    public Map<String, String> properties;

    public ItemDto() {
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}