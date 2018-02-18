package com.tomove.model.objectMover;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tomove.model.AbstractBaseEntity;
import com.tomove.model.enums.RoomType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
@Table(name = "item_type")
public class ItemType extends AbstractBaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemType")
    private Set<TypeProperties> properties;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typePrice")
    private Set<Item> items;

    public ItemType() {
    }

    public ItemType(RoomType roomType, @NotBlank String name, Set<TypeProperties> properties, Set<Item> items) {
        this.roomType = roomType;
        this.name = name;
        this.properties = properties;
        this.items = items;
    }

    public ItemType(RoomType roomType, @NotBlank String name) {
        this.roomType = roomType;
        this.name = name;
    }

    public ItemType(Integer id, RoomType roomType, @NotBlank String name, Set<TypeProperties> properties, Set<Item> items) {
        super(id);
        this.roomType = roomType;
        this.name = name;
        this.properties = properties;
        this.items = items;
    }


    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
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

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
