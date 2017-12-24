package com.tomove.model.subjectMover;


import com.tomove.model.objectMover.Request;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@DiscriminatorValue(value="mover")
@Access(AccessType.FIELD)
public class Mover extends Account {
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "logo", nullable = false)
    private byte[] logo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mover")
    private List<Request> request;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }
}
