package com.tomove.model.objectMover;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_type_id")
    private ItemType itemType;


    public TypeProperties() {
    }

    public TypeProperties(@NotBlank String name, @NotBlank String value, ItemType itemType) {
        this.name = name;
        this.value = value;
        this.itemType = itemType;
    }

    public TypeProperties(Integer id, @NotBlank String name, @NotBlank String value, ItemType itemType) {
        super(id);
        this.name = name;
        this.value = value;
        this.itemType = itemType;
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

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
