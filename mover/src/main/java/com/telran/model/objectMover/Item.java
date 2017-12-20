package com.telran.model.objectMover;

import com.telran.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Entity
@Access(AccessType.FIELD)
@Table(name = "items", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "items_unique_name_idx")})
public class Item extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private Set<TypeProperties> properties;

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

    public Item(@NotBlank String name, Set<TypeProperties> properties, Address from, Address to, Room room) {
        this.name = name;
        this.properties = properties;
        this.from = from;
        this.to = to;
        this.room = room;
    }

    public Item(Integer id, @NotBlank String name, Set<TypeProperties> properties, Address from, Address to, Room room) {
        super(id);
        this.name = name;
        this.properties = properties;
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

    public Set<TypeProperties> getProperties() {
        return properties;
    }

    public void setProperties(Set<TypeProperties> properties) {
        this.properties = properties;
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
