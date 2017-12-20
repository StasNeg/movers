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

}
