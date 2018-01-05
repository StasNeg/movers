<<<<<<< HEAD
package com.tomove.controller;


import com.tomove.repository.RequestRepository;
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
import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Customer;
import com.tomove.model.subjectMover.Mover;
import com.tomove.repository.AccountRepository;

import javassist.expr.Instanceof;

import static com.tomove.controller.PathConstant.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
	//FIXME	DISCUSS USE OF THIS REPOS
	private RequestRepository requestRepository;
	private AccountRepository accountRepository;

	@Autowired public RequestController(
			RequestORM requestManager,
			AccountRepository accRepo,
			RequestRepository requestRepository,
			AccountRepository accountRepository) {
		this.requestManager = requestManager;
		this.accRepo = accRepo;
		this.requestRepository = requestRepository;
		this.accountRepository = accountRepository;
	}
	
	@RequestMapping(value=GET_RECENT_CUSTOMER_REQUESTS, method=RequestMethod.GET)
	public DataTo getRecent(@RequestParam(name="token") String tokenVal){
		//test period only - should be changed to get user from token
		int userid = Integer.parseInt(tokenVal);
		
		Account account = accRepo.findById(userid).orElse(new Account() {}); 
		if (!account.isCustomer()) return new DataTo(false, "Not a valid customer id");
		Customer customer1 = (Customer) account; 
		
		List<RequestDetailsDTO> resDb = requestManager.getRequestDetailsByCustomer(customer1).stream()
				.map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
		return resDb.size() == 0 ? new DataTo(false, "No data for the customer") : new DataTo(true, resDb);
	}
	
	@RequestMapping(value=GET_CALENDAR_CUSTOMER_REQUESTS, method=RequestMethod.GET)
	public DataTo getRecentFromDate(@RequestParam(name="token") String tokenVal, @RequestParam(name=REQUEST_DATE) String userDate){
		//test period only - should be changed to get user from token
		int userid = Integer.parseInt(tokenVal);
		
		Account account = accRepo.findById(userid).orElse(new Account() {}); 
		if (!account.isCustomer()) return new DataTo(false, "Not a valid customer id");
		Customer customer1 = (Customer) account; 
		
		LocalDate requestDate = getRequestDate(userDate);
		List<RequestDetailsDTO> resDb = requestManager.getRequestDetailsByCustomerFromDay(customer1,requestDate).stream()
				.map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
		return resDb.size() == 0 ? new DataTo(false, "No data for the date") : new DataTo(true, resDb);
	}
	
	@RequestMapping(value=GET_DATE_CUSTOMER_REQUESTS, method=RequestMethod.GET)
	public DataTo getRecentForDate(@RequestParam(name="token") String tokenVal, @RequestParam(name=REQUEST_DATE) String userDate){
		//test period only - should be changed to get user from token
		int userid = Integer.parseInt(tokenVal);
		
		Account account = accRepo.findById(userid).orElse(new Account() {}); 
		if (!account.isCustomer()) return new DataTo(false, "Not a valid customer id");
		Customer customer1 = (Customer) account; 
		
		LocalDate requestDate = getRequestDate(userDate);
		List<RequestDetailsDTO> resDb = requestManager.getRequestDetailsByCustomerAndDay(customer1,requestDate).stream()
				.map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
		return resDb.size() == 0 ? new DataTo(false, "No data for the date") : new DataTo(true, resDb);
	}
	
	@RequestMapping(value=GET_DATE_MOVER_REQUESTS, method=RequestMethod.GET)
	public DataTo getRecentForMoverDate(@RequestParam(name="token") String tokenVal, @RequestParam(name=REQUEST_DATE) String userDate){
		//test period only - should be changed to get user from token
		int userid = Integer.parseInt(tokenVal); 
		
		Account account = accRepo.findById(userid).orElse(new Account() {}); 
		if (!account.isMover()) return new DataTo(false, "Not a valid mover id");
		Mover mover1 = (Mover) account; 
		LocalDate requestDate = getRequestDate(userDate);
		List<RequestDetailsDTO> resDb = requestManager.getRequestDetailsByMoverAndDay(mover1, requestDate).stream()
				.map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
		return resDb.size() == 0 ? new DataTo(false, "No data for the date") : new DataTo(true, resDb);
		
	}
	
	private LocalDate getRequestDate(String userDate){
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate requestDate = LocalDate.of(2018, 01, 01); //default value could be changed to some value from app.props
		try {
			requestDate = LocalDate.parse(userDate,dateformatter);
		} catch (Exception e) {
		}
		return requestDate;		
	}
	
	private Map<String,List<RequestDetailsDTO>> getMapByDates(List<RequestDetailsDTO> iniList){
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

	@GetMapping(value = REQUEST_GET_INFO)
	public DataTo getRequestInfo(@RequestParam Integer id) {
		Request request = requestRepository.findById(id).orElse(null);
		return request == null ? new DataTo(false, "No request with id " + id) : new DataTo(true, request);
	}

	@GetMapping(value = REQUEST_GET_INFO)
	public DataTo getRequestInfo(@RequestParam Integer id) {
		Request request = requestRepository.findById(id).orElse(null);
		return request == null ? new DataTo(false, "No request with id " + id) : new DataTo(true, request);
	}

	@PostMapping(value = REQUEST_ASSIGN_TO_MOVER)
	public DataTo assignRequestToMover(@RequestBody Map<String, Integer> params) {
		Integer request_id = params.get("request_id");
		Integer mover_id = params.get("mover_id");
		Request request = requestRepository.findById(request_id).orElse(null);
		Account mover = accountRepository.findById(mover_id).orElse(null);
		if (mover == null) {
			return new DataTo(false, "No mover with id = " + mover_id);
		}
		if (request == null) {
			return new DataTo(false, "No request with id = " + request_id);
		}
		// TODO: 05/01/2018 MAKE RETURN CONSTANT CODE i.e. REQUEST_NOT_AVAILABLE
		if (request.getMover() != null) {
			return new DataTo(false, "Request " + request_id + " is already assigned");
		}
		// FIXME: 05/01/2018 UGLY CASTING. ALSO, SHOULD WE CHECK FOR MOVER.GETTYPE?
		request.setMover((Mover) mover);
		requestRepository.save(request);

		((Mover) mover).getRequest().add(request);
		accountRepository.save(mover);
		return new DataTo(true, String.format("Request %d was assigned to mover %d", request_id, mover_id));
	}
}
