package com.tomove.common;

import com.tomove.controller.to.AddressDto;

import java.util.List;

public class RequestData {
    public String customerId;
    public String move_date;
    public String move_time;
    public boolean personal;
    public String place_type;
    public List<AddressDto> addresses;
    public Integer cost;
    public List<Move> moves;

    public RequestData() {
    }

    public Integer getCost() {
        return cost;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getMove_date() {
        return move_date;
    }

    public String getMove_time() {
        return move_time;
    }

    public boolean isPersonal() {
        return personal;
    }

    public String getPlace_type() {
        return place_type;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }
}
