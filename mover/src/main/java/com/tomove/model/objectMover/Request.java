package com.tomove.model.objectMover;

import com.tomove.model.AbstractBaseEntity;
import com.tomove.model.enums.Place;
import com.tomove.model.enums.Status;
import com.tomove.model.subjectMover.Customer;
import com.tomove.model.subjectMover.Mover;
import com.tomove.model.subjectMover.Truck;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "requests")
public class Request extends AbstractBaseEntity{

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "creation_date", nullable = false)
    @NotNull
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "is_personal", nullable = false)
    @NotNull
    private boolean isPersonal;

    @Column(name = "cost", nullable = false)
    @NotNull
    private int cost;

    @Column(name = "rate", nullable = false)
    @NotNull
    private int rate;

    @Enumerated(EnumType.STRING)
    @Column(name = "place", nullable = false)
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mover_id")
    private Mover mover;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "request")
    private List<RequestAdress> requestAdresses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "request")
    private List<Room> rooms;

    public Request() {
    }

    public Request(@NotNull LocalDateTime dateTime, @NotNull LocalDate creationDate, Status status, @NotNull boolean isPersonal, @NotNull int cost, @NotNull int rate, Place place, List<RequestAdress> requestAdresses, List<Room> rooms) {
        this.dateTime = dateTime;
        this.creationDate = creationDate;
        this.status = status;
        this.isPersonal = isPersonal;
        this.cost = cost;
        this.rate = rate;
        this.place = place;
        this.requestAdresses = requestAdresses;
        this.rooms = rooms;
    }

    public Request(@NotNull LocalDateTime dateTime, @NotNull LocalDate creationDate, Status status, @NotNull boolean isPersonal, @NotNull int cost, @NotNull int rate, Place place, Mover mover, Customer customer, Truck truck, List<RequestAdress> requestAdresses, List<Room> rooms) {
        this.dateTime = dateTime;
        this.creationDate = creationDate;
        this.status = status;
        this.isPersonal = isPersonal;
        this.cost = cost;
        this.rate = rate;
        this.place = place;
        this.mover = mover;
        this.customer = customer;
        this.truck = truck;
        this.requestAdresses = requestAdresses;
        this.rooms = rooms;
    }

    public Request(Integer id, @NotNull LocalDateTime dateTime, @NotNull LocalDate creationDate, Status status, @NotNull boolean isPersonal, @NotNull int cost, @NotNull int rate, Place place, Mover mover, Customer customer, Truck truck, List<RequestAdress> requestAdresses, List<Room> rooms) {
        super(id);
        this.dateTime = dateTime;
        this.creationDate = creationDate;
        this.status = status;
        this.isPersonal = isPersonal;
        this.cost = cost;
        this.rate = rate;
        this.place = place;
        this.mover = mover;
        this.customer = customer;
        this.truck = truck;
        this.requestAdresses = requestAdresses;
        this.rooms = rooms;
    }

    public Request(Integer id, @NotNull LocalDateTime dateTime, @NotNull LocalDate creationDate, Status status, @NotNull boolean isPersonal, @NotNull int cost, @NotNull int rate, Place place, List<RequestAdress> requestAdresses, List<Room> rooms) {
        super(id);
        this.dateTime = dateTime;
        this.creationDate = creationDate;
        this.status = status;
        this.isPersonal = isPersonal;
        this.cost = cost;
        this.rate = rate;
        this.place = place;
        this.requestAdresses = requestAdresses;
        this.rooms = rooms;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isPersonal() {
        return isPersonal;
    }

    public void setPersonal(boolean personal) {
        isPersonal = personal;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<RequestAdress> getRequestAdresses() {
        return requestAdresses;
    }

    public void setRequestAdresses(List<RequestAdress> requestAdresses) {
        this.requestAdresses = requestAdresses;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Mover getMover() {
        return mover;
    }

    public void setMover(Mover mover) {
        this.mover = mover;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

}
