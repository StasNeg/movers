package com.tomove.controller.to;

public class AddressDto {
    public int seqnumber;
    public String city;
    public String street;
    public String building;
    public String apartment;
    public int floor;
    public boolean elevator;

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

    public boolean isElevator() {
        return elevator;
    }

    public AddressDto(int seqnumber, String city, String street, String building, String apartment, int floor, boolean elevator) {
        super();
        this.seqnumber = seqnumber;
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.floor = floor;
        this.elevator = elevator;
    }

    @Override
    public String toString() {
        return "AddressDto [seqnumber=" + seqnumber + ", city=" + city + ", street=" + street + ", building=" + building
                + ", apartment=" + apartment + ", floor=" + floor + ", elevator=" + elevator  + "]";
    }

    public String getAddressString() {
        return city + ", " + street + ", " + building;
    }

}
