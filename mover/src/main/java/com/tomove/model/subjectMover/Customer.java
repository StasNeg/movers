package com.tomove.model.subjectMover;


import com.tomove.model.objectMover.Request;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue(value="customer")
public class Customer extends Account {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "additional_skills")
    private String additionalSkills;


    @Column(name = "avatar")
    private byte[] avatar;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Request> request;


    public Customer() {
    }

    public Customer(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String additionalSkills, @NotBlank byte[] avatar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.additionalSkills = additionalSkills;
        this.avatar = avatar;
    }

    public Customer(@NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull boolean enabled, @NotBlank String firstName, @NotBlank String lastName, @NotBlank String additionalSkills, @NotBlank byte[] avatar) {
        super(phone, email, password, enabled);
        this.firstName = firstName;
        this.lastName = lastName;
        this.additionalSkills = additionalSkills;
        this.avatar = avatar;
    }

    public Customer(Integer id, @NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull boolean enabled, @NotBlank String firstName, @NotBlank String lastName, @NotBlank String additionalSkills, @NotBlank byte[] avatar) {
        super(id, phone, email, password, enabled);
        this.firstName = firstName;
        this.lastName = lastName;
        this.additionalSkills = additionalSkills;
        this.avatar = avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdditionalSkills() {
        return additionalSkills;
    }

    public void setAdditionalSkills(String additionalSkills) {
        this.additionalSkills = additionalSkills;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }
}
