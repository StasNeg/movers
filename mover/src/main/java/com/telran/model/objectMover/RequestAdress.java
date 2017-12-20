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

    public RequestAdress(@NotNull int seqnumberRequest, Request request, Address address) {
        this.seqnumberRequest = seqnumberRequest;
        this.request = request;
        this.address = address;
    }

    public RequestAdress(Integer id, @NotNull int seqnumberRequest, Request request, Address address) {
        super(id);
        this.seqnumberRequest = seqnumberRequest;
        this.request = request;
        this.address = address;
    }

    public int getSeqnumberRequest() {
        return seqnumberRequest;
    }

    public void setSeqnumberRequest(int seqnumberRequest) {
        this.seqnumberRequest = seqnumberRequest;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
