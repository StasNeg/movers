package com.tomove.model.mapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomove.controller.to.AddressDto;
import com.tomove.model.subjectMover.Customer;
import com.tomove.model.subjectMover.Mover;
import com.tomove.model.subjectMover.Truck;
import com.tomove.repository.RequestAddressRepository;
import com.tomove.repository.RequestRepository;
import com.tomove.repository.TruckRepository;

@Component
public class RequestORM implements RequestMapping{
	
	@Autowired
	private RequestRepository requestRepo;
	@Autowired
	private RequestAddressRepository reqAddrRepo;
	@Autowired
	private TruckRepository truckRepo;

	@Override
	public List<RequestDetails> getRequestDetailsByCustomer(Customer customer) {
		List<RequestDetails> res = new ArrayList<RequestDetails>();
		res = (List<RequestDetails>) requestRepo.reqDetails(customer);
		addAddressesToRequest(res);
		return res;
	}

	@Override
	public List<RequestDetails> getRequestDetailsByCustomerAndDay(Customer customer, LocalDate date) {
		List<RequestDetails> res = new ArrayList<RequestDetails>();
		LocalDateTime datefrom = date.atStartOfDay();
		LocalDateTime dateto = date.atTime(23,59);
		res = (List<RequestDetails>) requestRepo.reqDetailsDate(customer, datefrom, dateto);
		addAddressesToRequest(res);
		return res;
	}
	
	@Override
	public List<RequestDetails> getRequestDetailsByCustomerFromDay(Customer customer, LocalDate date) {
		List<RequestDetails> res = new ArrayList<RequestDetails>();
		LocalDateTime datefrom = date.atStartOfDay();
		res = (List<RequestDetails>) requestRepo.reqDetailsFromDate(customer, datefrom);
		addAddressesToRequest(res);
		return res;
	}
	
	@Override
	public List<RequestDetails> getRequestDetailsByMoverAndDay(Mover mover, LocalDate date) {
		List<RequestDetails> res = new ArrayList<RequestDetails>();
		LocalDateTime datefrom = date.atStartOfDay();
		LocalDateTime dateto = date.atTime(23,59);
		res = (List<RequestDetails>) requestRepo.reqDetailsMoverDate(mover, datefrom, dateto);
		addAddressesToRequest(res);
		return res;
	}
	
	@Override
	public List<RequestDetails> getRequestDetailsByMoverFromDay(Mover mover, LocalDate date) {
		List<RequestDetails> res = new ArrayList<RequestDetails>();
		LocalDateTime datefrom = date.atStartOfDay();
		res = (List<RequestDetails>) requestRepo.reqDetailsMoverFromDate(mover, datefrom);
		addAddressesToRequest(res);
		return res;
	}
	
	
	
	@Override
	public List<RequestDetailsAdvanced> getBasiclyFilteredRequestsByMover(Mover mover) {
		List<RequestDetailsAdvanced> allPossible = new ArrayList<>();
		LocalDateTime datefrom = LocalDateTime.now();
		allPossible = (List<RequestDetailsAdvanced>) requestRepo.reqFilteredByMover(mover,datefrom);		
		return allPossible;
	}	
	

	@Override
	public List<RequestDetails> getDeeplyFilteredRequestsByMover(Mover mover, List<RequestDetailsAdvanced> requests) {
		List<RequestDetails> res = new ArrayList<>();
		requests.forEach(request->{
			List<Truck> availableTrucks = (List<Truck>) truckRepo.getAvailableTrucksForRequest(Truck.getGlobalTruckMovesPerDayLimit(), mover, 
					request.getArea(), request.getRoomCount(), getDateBorders(request.getDetails().getMovedatetime())[0],
					getDateBorders(request.getDetails().getMovedatetime())[1]);
			if(availableTrucks.size()>0) res.add(request.getDetails());
		});
		return res;
	}
	
	@Override
	public List<RequestDetails> getPossibleRequestsForMover(Mover mover) {
		List<RequestDetailsAdvanced> basicList = getBasiclyFilteredRequestsByMover(mover);
		List<RequestDetails> finalList = getDeeplyFilteredRequestsByMover(mover,basicList);
		addAddressesToRequest(finalList);
		return finalList;
	}


	@Override
	public int getRequestCountForMoverByDay(Mover mover, LocalDateTime reqDateTime) {		
		int res = requestRepo.reqCountForMoverByDay(mover, getDateBorders(reqDateTime)[0], getDateBorders(reqDateTime)[1]);
		return res;
	}
	
	private void addAddressesToRequest(List<RequestDetails> res){
		if(res.size()>0){
			res.forEach(resReq -> {
				List<AddressDto> addresses = (List<AddressDto>) reqAddrRepo.getAddressByRequest(resReq.getId());
				resReq.setAddressesList(addresses);
				});
		}
	}
	
	public LocalDateTime[] getDateBorders(LocalDateTime reqDateTime){
		LocalDateTime[] res = new LocalDateTime[2];
		LocalDate date = LocalDate.from(reqDateTime);
		res[0] = date.atStartOfDay();
		res[1] = date.atTime(23,59);		
		return res;
	}
}
