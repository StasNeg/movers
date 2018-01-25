package com.tomove.common;

import com.tomove.controller.to.AddressDto;

import java.util.List;

public class Move {
    public AddressDto addressIn;
    public AddressDto addressOut;
    public List<ItemDto> itemDtos;

    public Move() {
    }

    public AddressDto getAddressIn() {
        return addressIn;
    }

    public AddressDto getAddressOut() {
        return addressOut;
    }

    public List<ItemDto> getItemDtos() {
        return itemDtos;
    }
}
