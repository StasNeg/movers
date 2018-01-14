package com.tomove.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tomove.controller.to.DataTo;
import com.tomove.controller.to.RequestDetailsDTO;
import com.tomove.model.mapping.RequestDetails;
import com.tomove.model.mapping.RequestORM;
import com.tomove.model.subjectMover.Customer;
import com.tomove.repository.AccountRepository;


import static com.tomove.controller.PathConstant.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class RequestController {
	private AccountRepository accRepo;
	private RequestORM requestManager;
	
	@Autowired public RequestController(RequestORM requestManager, AccountRepository accRepo) {
		this.requestManager = requestManager;
		this.accRepo = accRepo;
	}
	
	@RequestMapping(value=GET_RECENT_CUSTOMER_REQUESTS, method=RequestMethod.GET)
	public DataTo getRecent(@RequestParam(name="token") String tokenVal){
		//test period only - should be changed to get user from token
		int userid = Integer.parseInt(tokenVal);
		Customer customer1 = (Customer) accRepo.findById(userid).orElse(null); 
		
		List<RequestDetailsDTO> resDb = requestManager.getRequestDetailsByCustomer(customer1).stream()
				.map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
		return resDb.size() == 0 ? new DataTo(false, "No data for the customer") : new DataTo(true, resDb);
	}
	
	@RequestMapping(value=GET_CALENDAR_CUSTOMER_REQUESTS, method=RequestMethod.GET)
	public DataTo getRecentFromDate(@RequestParam(name="token") String tokenVal, @RequestParam(name=REQUEST_DATE) String userDate){
		//test period only - should be changed to get user from token
		Customer customer1 = (Customer) accRepo.findById(100000).orElse(null); 
		
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate requestDate = LocalDate.of(2100, 01, 01);
		try {
			requestDate = LocalDate.parse(userDate,dateformatter);
		} catch (Exception e) {
		}
		List<RequestDetailsDTO> resDb = requestManager.getRequestDetailsByCustomerFromDay(customer1,requestDate).stream()
				.map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
		return resDb.size() == 0 ? new DataTo(false, "No data for the date") : new DataTo(true, resDb);
	}
	
	@RequestMapping(value=GET_DATE_CUSTOMER_REQUESTS, method=RequestMethod.GET)
	public DataTo getRecentForDate(@RequestParam(name="token") String tokenVal, @RequestParam(name=REQUEST_DATE) String userDate){
		//test period only - should be changed to get user from token
		Customer customer1 = (Customer) accRepo.findById(100000).orElse(null); 
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate requestDate = LocalDate.of(2018, 01, 01);
		try {
			requestDate = LocalDate.parse(userDate,dateformatter);
		} catch (Exception e) {
		}
		List<RequestDetailsDTO> resDb = requestManager.getRequestDetailsByCustomerAndDay(customer1,requestDate).stream()
				.map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
		return resDb.size() == 0 ? new DataTo(false, "No data for the date") : new DataTo(true, resDb);
	}
	
	public Map<String,List<RequestDetailsDTO>> getMapByDates(List<RequestDetailsDTO> iniList){
		Map<String,List<RequestDetailsDTO>> res = new HashMap<>();
		iniList.forEach(request -> {	
			String dateval = request.getMovedatetime().toLocalDate().toString();			
			List<RequestDetailsDTO> dateList = iniList.stream()
					.filter(x-> x.getMovedatetime().toLocalDate().equals(request.getMovedatetime().toLocalDate()))
					.collect(Collectors.toList());
			res.put(dateval, dateList);
		});		
		return res;		
	}
	
}
