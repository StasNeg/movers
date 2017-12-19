package com.telran.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Access(AccessType.FIELD)
@Table(name = "movers", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "movers_unique_email_idx")})
public class Mover extends Account {
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "logo", nullable = false)
    private byte[] logo;

    public Mover() {
    }

    public Mover(@NotBlank String name, @NotBlank byte[] logo) {
        this.name = name;
        this.logo = logo;
    }

    public Mover(@NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull String enabled, @NotBlank String name, @NotBlank byte[] logo) {
        super(phone, email, password, enabled);
        this.name = name;
        this.logo = logo;
    }

    public Mover(Integer id, @NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull String enabled, @NotBlank String name, @NotBlank byte[] logo) {
        super(id, phone, email, password, enabled);
        this.name = name;
        this.logo = logo;
    }
}
