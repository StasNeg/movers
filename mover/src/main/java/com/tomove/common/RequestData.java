package com.tomove.common;

import java.util.List;

public class RequestData {
    public String date;
    public List<Move> moves;

    public RequestData() {
    }

    public String getDate() {
        return date;
    }

    public List<Move> getMoves() {
        return moves;
    }
}
