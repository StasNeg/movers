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
import com.tomove.repository.RequestAddressRepository;
import com.tomove.repository.RequestRepository;

@Component
public class RequestORM implements RequestMapping{
	
	@Autowired
	private RequestRepository requestRepo;
	@Autowired
	private RequestAddressRepository reqAddrRepo;

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
		System.out.println(datefrom.toString()+" from");
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
	
	private void addAddressesToRequest(List<RequestDetails> res){
		if(res.size()>0){
			res.forEach(resReq -> {
				List<AddressDto> addresses = (List<AddressDto>) reqAddrRepo.getAddressByRequest(resReq.getId());
				resReq.setAddressesList(addresses);
				});
		}
	}

	
	
}
