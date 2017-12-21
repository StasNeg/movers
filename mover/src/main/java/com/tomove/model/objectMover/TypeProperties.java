package com.tomove.model.objectMover;

import com.tomove.model.AbstractBaseEntity;

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


    public TypeProperties() {
    }

    public TypeProperties(@NotBlank String name, @NotBlank String value, Item item) {
        this.name = name;
        this.value = value;
        this.item = item;
    }

    public TypeProperties(Integer id, @NotBlank String name, @NotBlank String value, Item item) {
        super(id);
        this.name = name;
        this.value = value;
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
