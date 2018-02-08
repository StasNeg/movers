package com.tomove.model.mapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.tomove.common.AddressDto;
import com.tomove.common.RequestDetailsDTO;
import com.tomove.model.enums.Status;

public class RequestDetails {
	private int id;
	private Status status;
	private LocalDateTime movedatetime;
	private int cost;
	private boolean ispersonal;
	private List<AddressDto> addressesList;
	
	public int getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	public LocalDateTime getMovedatetime() {
		return movedatetime;
	}

	public int getCost() {
		return cost;
	}

	public boolean isIspersonal() {
		return ispersonal;
	}
	
	public List<AddressDto> getAddressesList() {
		return addressesList;
	}	
	
	public void setId(int id) {
		this.id = id;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setMovedatetime(LocalDateTime movedatetime) {
		this.movedatetime = movedatetime;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setIspersonal(boolean ispersonal) {
		this.ispersonal = ispersonal;
	}

	public void setAddressesList(List<AddressDto> addressesList) {
		this.addressesList = addressesList;
	}	

	public RequestDetails(int id) {	}

	public RequestDetails(int id, Status status, LocalDateTime movedatetime, int cost, boolean ispersonal) {
		this.id = id;
		this.status = status;
		this.movedatetime = movedatetime;
		this.cost = cost;
		this.ispersonal = ispersonal;	
	}

	@Override
	public String toString() {
		return "RequestDetails [id=" + id + ", status=" + status + ", movedatetime=" + movedatetime + ", cost=" + cost
				+ ", ispersonal=" + ispersonal + ", addressesList=" + addressesList + "]";
	}
	
	public static RequestDetailsDTO makeReqDetailsDTO(RequestDetails reqDet){
		List<AddressDto> addresses = reqDet.getAddressesList();
		AddressDto from = (addresses.size()>0) ? addresses.get(0) : null;
		AddressDto to = (addresses.size()>0) ? addresses.get(addresses.size()-1) : null;
		addresses = addresses.stream().filter((x)->x.getSeqnumber()>from.getSeqnumber())
				.filter((x)->x.getSeqnumber()<to.getSeqnumber()).collect(Collectors.toList());
		return new RequestDetailsDTO(reqDet.getId(), reqDet.getMovedatetime(), reqDet.getStatus(), 
				reqDet.isIspersonal(), reqDet.getCost(), addresses,
				from, to);	
	}		
}
