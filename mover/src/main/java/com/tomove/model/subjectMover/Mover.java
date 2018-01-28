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
//    @NotBlank
    @Column(name = "name")
    private String name;

//    @NotBlank
    @Column(name = "logo")
    private byte[] logo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mover")
    private List<Request> request;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mover")
    private List<Truck> trucks;



    public Mover() {
    }

    public Mover(@NotBlank String name, @NotBlank byte[] logo) {
        this.name = name;
        this.logo = logo;
    }

    public Mover(@NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull boolean enabled, @NotBlank String name, @NotBlank byte[] logo) {
        super(phone, email, password, enabled);
        this.name = name;
        this.logo = logo;
    }

    public Mover(Integer id, @NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull boolean enabled, @NotBlank String name, @NotBlank byte[] logo) {
        super(id, phone, email, password, enabled);
        this.name = name;
        this.logo = logo;
    }

    public Mover(@NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull boolean enabled, String name, byte[] logo, List<Request> request, List<Truck> trucks) {
        super(phone, email, password, enabled);
        this.name = name;
        this.logo = logo;
        this.request = request;
        this.trucks = trucks;
    }

    public Mover(Integer id, @NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull boolean enabled, String name, byte[] logo, List<Request> request, List<Truck> trucks) {
        super(id, phone, email, password, enabled);
        this.name = name;
        this.logo = logo;
        this.request = request;
        this.trucks = trucks;
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

    public List<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }
}
