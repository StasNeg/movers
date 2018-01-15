package com.tomove.common;

import java.util.List;

public class RequestData {
    public String customerId;
    public String move_date;
    public String move_time;
    public boolean personal;
    public String place_type;
    public List<Address> addresses;

    public List<Move> moves;

    public RequestData() {
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

    public List<Address> getAddresses() {
        return addresses;
    }
}
