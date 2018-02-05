package com.tomove.model.mapping;

import java.time.LocalDateTime;

import com.tomove.model.enums.Area;
import com.tomove.model.enums.Status;


public class RequestDetailsAdvanced{
	
	private RequestDetails details;
	private int roomCount;
	private Area area;	

	public RequestDetailsAdvanced(int id, Status status, LocalDateTime movedatetime, int cost, boolean ispersonal, Area area, long roomCount) {
		this.details = new RequestDetails(id, status, movedatetime, cost, ispersonal);
		this.area = area;
		this.roomCount = (int) roomCount;
	}

	@Override
	public String toString() {
		return "RequestDetailsAdvanced [details=" + details + ", roomCount=" + roomCount + ", area=" + area + "]";
	}

	public RequestDetails getDetails() {
		return details;
	}

	public int getRoomCount() {
		return roomCount;
	}

	public Area getArea() {
		return area;
	}		

	
	
}
