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



    protected boolean enabled;

    protected String type;



    protected Language language;

    public AccountTo(int id, String phone, String email,  boolean enabled, String type,  Language language) {
        this.id = id;
        this.phone = phone;
        this.email = email;

        this.enabled = enabled;
        this.type = type;

        this.language = language;
    }

    public AccountTo() {
    }

    public AccountTo(Account account) {
        this.id = account.getId();
        this.phone = account.getPhone();
        this.email = account.getEmail();

        this.enabled = account.getEnabled();
        this.type = account.getType();

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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
