package com.tomove.model.subjectMover;

import com.tomove.model.AbstractBaseEntity;
import com.tomove.model.enums.Language;

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

    @Column(name = "phone")
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
    protected boolean enabled;

    @Column(name = "type", nullable =  false, insertable = false, updatable = false)
    protected String type;

    @Column(name = "verification_code")
    protected String verificationCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    protected Language language;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Account() {
    }

    public Account(@NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull boolean enabled) {
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public Account(@NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull boolean enabled, String type, String verificationCode) {
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.type = type;
        this.verificationCode = verificationCode;
    }


    public Account(Integer id, @NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull boolean enabled) {
        super(id);
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public Account(Integer id, @NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull boolean enabled, String type, String verificationCode) {
        super(id);
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.type = type;
        this.verificationCode = verificationCode;
    }

    public Account(@NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull boolean enabled, String type, String verificationCode, Language language) {
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.type = type;
        this.verificationCode = verificationCode;
        this.language = language;
    }

    public Account(Integer id, @NotBlank String phone, @Email @NotBlank String email, @NotBlank String password, @NotNull boolean enabled, String type, String verificationCode, Language language) {
        super(id);
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.type = type;
        this.verificationCode = verificationCode;
        this.language = language;
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

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }
    
    public boolean isCustomer(){
    	if (this instanceof Customer) return true;
    	return false;
    }
    
    public boolean isMover(){
    	if (this instanceof Mover) return true;
    	return false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
