package com.tomove.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.tomove.model.enums.Language;
import com.tomove.model.subjectMover.Account;

import java.io.Serializable;

//@JsonAutoDetect
public class AccountTo implements Serializable {

    protected int  id;

    protected String phone;

    protected String email;

    protected String password;

    protected boolean enabled;

    protected String type;

    protected String verificationCode;

    protected Language language;

    public AccountTo(int id, String phone, String email, String password, boolean enabled, String type, String verificationCode, Language language) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.type = type;
        this.verificationCode = verificationCode;
        this.language = language;
    }

    public AccountTo() {
    }

    public AccountTo(Account account) {
        this.id = account.getId();
        this.phone = account.getPhone();
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.enabled = account.getEnabled();
        this.type = account.getType();
        this.verificationCode = account.getVerificationCode();
        this.language = account.getLanguage();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
