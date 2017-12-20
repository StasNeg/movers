package com.telran.model.objectMover;

import com.telran.model.AbstractBaseEntity;
import com.telran.model.enums.Area;
import com.telran.model.enums.Lift;

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

    @Column(name = "appartment", nullable = false)
    @NotBlank
    private String appartment;

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
}
