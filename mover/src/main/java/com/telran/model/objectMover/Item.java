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

}
