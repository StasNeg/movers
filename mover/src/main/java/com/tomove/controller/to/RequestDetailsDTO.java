package com.tomove.controller.to;

import java.time.LocalDateTime;
import java.util.List;

import com.tomove.model.enums.Status;


public class RequestDetailsDTO {
	private int id;
	private LocalDateTime movedatetime;
	private Status status;
	private boolean ispersonal;
	private int cost;
	private AddressDto from;
	private AddressDto to;
	private List<AddressDto> addresses;
	public int getId() {
		return id;
	}
	public LocalDateTime getMovedatetime() {
		return movedatetime;
	}
	public Status getStatus() {
		return status;
	}
	public boolean isIspersonal() {
		return ispersonal;
	}
	public int getCost() {
		return cost;
	}
	public List<AddressDto> getAddresses() {
		return addresses;
	}	
	public AddressDto getFrom() {
		return from;
	}
	public AddressDto getTo() {
		return to;
	}
	public RequestDetailsDTO() {	}
	public RequestDetailsDTO(int id, LocalDateTime movedatetime, Status status, boolean ispersonal, int cost, List<AddressDto> addresses,
			AddressDto from, AddressDto to) {
		super();
		this.id = id;
		this.movedatetime = movedatetime;
		this.status = status;
		this.ispersonal = ispersonal;
		this.cost = cost;
		this.addresses = addresses;
		this.from = from;
		this.to = to;
	}	
	
}
