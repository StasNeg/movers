package com.tomove.common;

import com.tomove.model.enums.Lift;

public class AddressDto {
    public int id;
    public int seqnumber;
    public float latitude;
    public float longitude;
    public String city;
    public String street;
    public String building;
    public String apartment;
    public int floor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSeqnumber(int seqnumber) {
        this.seqnumber = seqnumber;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setLift(String lift) {
        this.lift = lift;
    }

    public String lift;

    public int getSeqnumber() {
        return seqnumber;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getBuilding() {
        return building;
    }

    public String getApartment() {
        return apartment;
    }

    public int getFloor() {
        return floor;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getLift() {
        return lift;
    }

    public AddressDto(int id, int seqnumber, String city, String street, String building, String apartment, int floor, Lift lift) {
        this.id = id;
        this.seqnumber = seqnumber;
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.floor = floor;
        this.lift = lift.name();
    }

    public AddressDto(int seqnumber, String city, String street, String building, String apartment, int floor) {
        super();
        this.seqnumber = seqnumber;
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.floor = floor;
    }

    public AddressDto(int seqnumber, String city, String street, String building, String apartment, int floor, String lift) {
        super();
        this.seqnumber = seqnumber;
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.floor = floor;
        this.lift = lift;
    }

    public AddressDto(int seqnumber, float latitude, float longitude, String city, String street, String building, String apartment, int floor, String lift) {
        this.seqnumber = seqnumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.floor = floor;
        this.lift = lift;
    }

    public AddressDto() {
    }

    @Override
    public String toString() {
        return "AddressDto [seqnumber=" + seqnumber + ", city=" + city + ", street=" + street + ", building=" + building
                + ", apartment=" + apartment + ", floor=" + floor + ", lift=" + lift  + "]";
    }

    public String getAddressString() {
        return city + ", " + street + ", " + building;
    }

}
