package com.tomove.common;

import java.util.List;

public class RoomDto {
    public String room;
    public List<ItemDto> items;

    public RoomDto() {
    }

    public String getRoom() {
        return room;
    }

    public List<ItemDto> getItems() {
        return items;
    }
}