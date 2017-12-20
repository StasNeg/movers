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


}
