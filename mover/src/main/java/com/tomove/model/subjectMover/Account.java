package com.tomove.model.subjectMover;

import com.tomove.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "accounts", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "accounts_unique_email_idx")})
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value="account")
public abstract class Account extends AbstractBaseEntity {

    @NotBlank
    @Column(name = "phone", nullable = false)
    protected String phone;


    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    protected String email;


    @Column(name = "password", nullable = false)
    @NotBlank
    protected String password;

    @Column(name = "enabled", nullable =  false)
    @NotNull
    protected String enabled;



    public Account() {
    }

    public Account(@NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull String enabled) {
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public Account(Integer id, @NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull String enabled) {
        super(id);
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }


}
