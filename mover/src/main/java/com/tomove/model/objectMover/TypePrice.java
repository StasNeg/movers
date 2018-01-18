package com.tomove.model.objectMover;

import com.tomove.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.FIELD)
@Table(name = "type_prices")
public class TypePrice extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;
    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    public TypePrice() {
    }

    public TypePrice(@NotBlank String name, @NotNull Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
