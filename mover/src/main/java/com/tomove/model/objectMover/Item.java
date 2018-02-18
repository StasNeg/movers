package com.tomove.model.objectMover;

import com.tomove.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Access(AccessType.FIELD)
@Table(name = "items")
public class Item extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_price")
    private TypePrice typePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_from")
    private Address from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_to")
    private Address to;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public Item() {
    }

    public Item(@NotBlank String name, TypePrice typePrice, Address from, Address to, Room room) {
        this.name = name;
        this.typePrice = typePrice;
        this.from = from;
        this.to = to;
        this.room = room;
    }

    public Item(Integer id, @NotBlank String name, TypePrice typePrice, Address from, Address to, Room room) {
        super(id);
        this.name = name;
        this.typePrice = typePrice;
        this.from = from;
        this.to = to;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypePrice getTypePrice() {
        return typePrice;
    }

    public void setTypePrice(TypePrice itemType) {
        this.typePrice = itemType;
    }

    public Address getFrom() {
        return from;
    }

    public void setFrom(Address from) {
        this.from = from;
    }

    public Address getTo() {
        return to;
    }

    public void setTo(Address to) {
        this.to = to;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
