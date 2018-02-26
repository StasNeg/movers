package com.tomove.model.objectMover;

import com.tomove.model.AbstractBaseEntity;
import com.tomove.model.enums.Area;
import com.tomove.model.enums.Lift;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Access(AccessType.FIELD)
@Table(name = "address")
public class Address extends AbstractBaseEntity{

    @Column(name = "city", nullable = false)
    @NotBlank
    private String city;

    @Column(name = "street", nullable = false)
    @NotBlank
    private String street;

    @Column(name = "building", nullable = false)
    @NotBlank
    private String building;

    @Column(name = "apartment")
//    @NotBlank
    private String apartment;

    @Column(name = "longitude", nullable = false)
    @NotNull
    private float longitude;

    @Column(name = "lattitude", nullable = false)
    @NotNull
    private float lattitude;

    @Column(name = "floor", nullable = false)
    @NotNull
    private int floor;

    @Enumerated(EnumType.STRING)
    @Column(name = "lift", nullable = false)
    private Lift lift;

    @Enumerated(EnumType.STRING)
    @Column(name = "area", nullable = false)
    private Area area;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "from")
    private List<Item> itemsFrom;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "to")
    private List<Item> itemsTo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    private List<RequestAdress> requestAdresses;

    public Address() {
    }

    public Address(Integer id, @NotBlank String city, @NotBlank String street, @NotBlank String building, @NotBlank String apartment, @NotNull float longitude, @NotNull float lattitude, @NotNull int floor, Lift lift, Area area) {
        super(id);
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.floor = floor;
        this.lift = lift;
        this.area = area;
    }

    public Address(@NotBlank String city, @NotBlank String street, @NotBlank String building, @NotBlank String apartment, @NotNull float longitude, @NotNull float lattitude, @NotNull int floor, Lift lift, Area area) {
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.floor = floor;
        this.lift = lift;
        this.area = area;
    }

    public Address(@NotBlank String city, @NotBlank String street, @NotBlank String building, @NotBlank String apartment, @NotNull float longitude, @NotNull float lattitude, @NotNull int floor, Lift lift, Area area, List<Item> itemsFrom, List<Item> itemsTo, List<RequestAdress> requestAdresses) {
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.floor = floor;
        this.lift = lift;
        this.area = area;
        this.itemsFrom = itemsFrom;
        this.itemsTo = itemsTo;
        this.requestAdresses = requestAdresses;
    }

    public Address(@NotBlank String city, @NotBlank String street, @NotBlank String building,
			@NotBlank String apartment, @NotNull float longitude, @NotNull float lattitude, @NotNull int floor,
			Lift lift, Area area, List<Item> itemsFrom, List<Item> itemsTo) {
		super();
		this.city = city;
		this.street = street;
		this.building = building;
		this.apartment = apartment;
		this.longitude = longitude;
		this.lattitude = lattitude;
		this.floor = floor;
		this.lift = lift;
		this.area = area;
		this.itemsFrom = itemsFrom;
		this.itemsTo = itemsTo;
	}

	public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Lift getLift() {
        return lift;
    }

    public void setLift(Lift lift) {
        this.lift = lift;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Item> getItemsFrom() {
        return itemsFrom;
    }

    public void setItemsFrom(List<Item> itemsFrom) {
        this.itemsFrom = itemsFrom;
    }

    public List<Item> getItemsTo() {
        return itemsTo;
    }

    public void setItemsTo(List<Item> itemsTo) {
        this.itemsTo = itemsTo;
    }

    public List<RequestAdress> getRequestAdresses() {
        return requestAdresses;
    }

    public void setRequestAdresses(List<RequestAdress> requestAdresses) {
        this.requestAdresses = requestAdresses;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", id=" + id +
                ", building='" + building + '\'' +
                ", apartment='" + apartment + '\'' +
                ", longitude=" + longitude +
                ", lattitude=" + lattitude +
                ", floor=" + floor +
                ", lift=" + lift +
                ", area=" + area +
//                ", itemsFrom=" + itemsFrom +
//                ", itemsTo=" + itemsTo +
//                ", requestAdresses=" + requestAdresses +
                "} " + super.toString();
    }
}
