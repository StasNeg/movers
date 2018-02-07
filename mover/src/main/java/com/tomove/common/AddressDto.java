package com.tomove.common;

public class AddressDto {
    public int seqnumber;
    public float latitude;
    public float longitude;
    public String city;
    public String street;
    public String building;
    public String apartment;
    public int floor;
    public boolean lift;

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

    public boolean isLift() {
        return lift;
    }

    public AddressDto(int seqnumber, String city, String street, String building, String apartment, int floor, boolean lift) {
        super();
        this.seqnumber = seqnumber;
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.floor = floor;
        this.lift = lift;
    }

    public AddressDto(int seqnumber, float latitude, float longitude, String city, String street, String building, String apartment, int floor, boolean lift) {
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
