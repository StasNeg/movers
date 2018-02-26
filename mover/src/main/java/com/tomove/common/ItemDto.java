package com.tomove.common;


import com.tomove.model.objectMover.Item;

public class ItemDto {
    public String name;
    public String propertiesWithType;

    public ItemDto() {
    }

    public ItemDto(Item item) {
        this.name = item.getName();

    }

    public String getName() {
        return name;
    }

    public String getProperties() {
        return propertiesWithType;
    }
}