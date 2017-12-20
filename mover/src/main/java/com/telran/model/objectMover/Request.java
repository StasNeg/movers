package com.telran.model.objectMover;

import com.telran.model.AbstractBaseEntity;
import com.telran.model.enums.Place;
import com.telran.model.enums.Status;

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
}
