package com.tomove.controller.to;

public class AddressDto {
	private int seqnumber;
	private String city;
	private String street;
	private String building;
	private String apartment;
	private int floor;
	
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
	public AddressDto(int seqnumber, String city, String street, String building, String apartment, int floor) {
		super();
		this.seqnumber = seqnumber;
		this.city = city;
		this.street = street;
		this.building = building;
		this.apartment = apartment;
		this.floor = floor;
	}
	@Override
	public String toString() {
		return "AddressDto [seqnumber=" + seqnumber + ", city=" + city + ", street=" + street + ", building=" + building
				+ ", apartment=" + apartment + ", floor=" + floor + "]";
	}
	
}
