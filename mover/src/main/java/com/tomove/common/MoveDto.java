package com.tomove.common;

import java.util.List;

public class MoveDto {
    public AddressDto addressIn;
    public AddressDto addressOut;
    public List<RoomDto> rooms;

    public MoveDto() {
    }

    public AddressDto getAddressIn() {
        return addressIn;
    }

    public AddressDto getAddressOut() {
        return addressOut;
    }

    public List<RoomDto> getRooms() {
        return rooms;
    }
}
