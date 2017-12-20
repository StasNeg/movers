package com.telran.model.objectMover;

import com.telran.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Access(AccessType.FIELD)
@Table(name = "item_properties")
public class TypeProperties extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;
    @Column(name = "value", nullable = false)
    @NotBlank
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

}
