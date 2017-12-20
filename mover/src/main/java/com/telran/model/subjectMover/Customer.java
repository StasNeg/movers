package com.telran.model.subjectMover;


import com.telran.model.subjectMover.Account;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Access(AccessType.FIELD)
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "customers_unique_email_idx")})
public class Customer extends Account {

    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;


    @Column(name = "additional_skills", nullable = false)
    @NotBlank
    private String additionalSkills;


    @Column(name = "avatar", nullable = false)
    @NotBlank
    private byte[] avatar;

    public Customer() {
    }

    public Customer(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String additionalSkills, @NotBlank byte[] avatar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.additionalSkills = additionalSkills;
        this.avatar = avatar;
    }

    public Customer(@NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull String enabled, @NotBlank String firstName, @NotBlank String lastName, @NotBlank String additionalSkills, @NotBlank byte[] avatar) {
        super(phone, email, password, enabled);
        this.firstName = firstName;
        this.lastName = lastName;
        this.additionalSkills = additionalSkills;
        this.avatar = avatar;
    }

    public Customer(Integer id, @NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull String enabled, @NotBlank String firstName, @NotBlank String lastName, @NotBlank String additionalSkills, @NotBlank byte[] avatar) {
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

}
