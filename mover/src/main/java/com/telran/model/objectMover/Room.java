package com.telran.model.objectMover;

import com.telran.model.AbstractBaseEntity;
import com.telran.model.enums.RoomType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "rooms")
public class Room extends AbstractBaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @NotBlank
    @Column(name = "image", nullable = false)
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Request request;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<Item> items;

    public Room() {
    }

    public Room(RoomType roomType, @NotBlank byte[] image, Request request, List<Item> items) {
        this.roomType = roomType;
        this.image = image;
        this.request = request;
        this.items = items;
    }

    public Room(Integer id, RoomType roomType, @NotBlank byte[] image, Request request, List<Item> items) {
        super(id);
        this.roomType = roomType;
        this.image = image;
        this.request = request;
        this.items = items;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
