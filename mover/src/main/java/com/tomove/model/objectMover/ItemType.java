package com.tomove.model.objectMover;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemType")
    private Set<TypeProperties> properties;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemType")
    private Set<Item> items;
}
