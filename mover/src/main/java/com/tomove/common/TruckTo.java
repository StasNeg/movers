package com.tomove.common;

import com.tomove.model.enums.Area;
import com.tomove.model.subjectMover.Truck;

import java.io.Serializable;


public class TruckTo implements Serializable {

    private Integer  id;

    private String truckType;

    private String driver_id;

    private int roomsCount;

    private int movesDay;

    private Area area;

    public TruckTo() {
    }

    public TruckTo(Truck truck) {
        this.id = truck.getId();
        this.truckType = truck.getTruckType();
        this.driver_id = truck.getDriver_id();
        this.roomsCount = truck.getRoomsCount();
        this.movesDay = truck.getMovesDay();
        this.area = truck.getArea();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public int getMovesDay() {
        return movesDay;
    }

    public void setMovesDay(int movesDay) {
        this.movesDay = movesDay;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "TruckTo{" +
                "truckType='" + truckType + '\'' +
                ", driver_id='" + driver_id + '\'' +
                ", roomsCount=" + roomsCount +
                ", movesDay=" + movesDay +
                ", area=" + area +
                '}';
    }
}
