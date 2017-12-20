package com.telran.model.objectMover;

import com.telran.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Access(AccessType.FIELD)
@Table(name = "request_address")
public class RequestAdress extends AbstractBaseEntity{

    @Column(name = "seqnumber_in_Request", nullable = false)
    @NotNull
    private int seqnumberRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "req_id")
    private Request request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    public RequestAdress() {
    }
}
