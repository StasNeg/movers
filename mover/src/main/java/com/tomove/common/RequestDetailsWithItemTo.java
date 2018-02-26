package com.tomove.common;


import java.util.ArrayList;
import java.util.List;

public class RequestDetailsWithItemTo {
    private AddressDto addressDto;
    private List<ItemDto> itemsFrom;
    private List<ItemDto> itemsTo;

    public RequestDetailsWithItemTo() {
    }

    public RequestDetailsWithItemTo(AddressDto addressDto) {
        this.addressDto = addressDto;
        this.itemsFrom = new ArrayList<>();
        this.itemsTo = new ArrayList<>();
    }

    public RequestDetailsWithItemTo(AddressDto addressDto, List<ItemDto> itemsFrom, List<ItemDto> itemsTo) {
        this.addressDto = addressDto;
        this.itemsFrom = itemsFrom;
        this.itemsTo = itemsTo;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public List<ItemDto> getItemsFrom() {
        return itemsFrom;
    }

    public void setItemsFrom(List<ItemDto> itemsFrom) {
        this.itemsFrom = itemsFrom;
    }

    public List<ItemDto> getItemsTo() {
        return itemsTo;
    }

    public void setItemsTo(List<ItemDto> itemsTo) {
        this.itemsTo = itemsTo;
    }
}
