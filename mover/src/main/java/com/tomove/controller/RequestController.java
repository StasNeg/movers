package com.tomove.controller;

import com.tomove.common.DataTo;
import com.tomove.common.RequestData;
import com.tomove.model.enums.Place;
import com.tomove.model.enums.Status;
import com.tomove.model.objectMover.Request;
import com.tomove.model.objectMover.RequestAdress;
import com.tomove.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tomove.controller.to.RequestDetailsDTO;
import com.tomove.model.mapping.RequestDetails;
import com.tomove.model.mapping.RequestORM;
import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Customer;
import com.tomove.model.subjectMover.Mover;
import com.tomove.repository.AccountRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.*;
import java.util.stream.Collectors;

import static com.tomove.common.PathConstant.*;

@RestController
@CrossOrigin
public class RequestController {
	private AccountRepository accountRepository;
	private RequestORM requestManager;
	//FIXME	DISCUSS USE OF THIS REPO
	private RequestRepository requestRepository;

	@Autowired public RequestController(
			RequestORM requestManager,
			RequestRepository requestRepository,
			AccountRepository accountRepository) {
		this.requestManager = requestManager;
		this.requestRepository = requestRepository;
		this.accountRepository = accountRepository;
	}
	
	@RequestMapping(value=GET_RECENT_CUSTOMER_REQUESTS, method=RequestMethod.GET)
	public DataTo getRecent(@RequestParam(name="token") String tokenVal){
		//test period only - should be changed to get user from token
		int userid = Integer.parseInt(tokenVal);
		
		Account account = accountRepository.findById(userid).orElse(new Account() {});
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
		
		Account account = accountRepository.findById(userid).orElse(new Account() {});
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
		
		Account account = accountRepository.findById(userid).orElse(new Account() {});
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
		
		Account account = accountRepository.findById(userid).orElse(new Account() {});
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

	@PostMapping(value = REQUEST_ASSIGN_TO_MOVER)
	// TODO: 06/01/2018 ASK STAS HOW TO TAKE PARAMS DIRECTLY
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
<<<<<<< HEAD
	
	@RequestMapping(value = GET_CALENDAR_MOVER_REQUESTS, method=RequestMethod.GET)
	public DataTo getRecentForMoverFromDate(@RequestParam(name="token") String tokenVal, @RequestParam(name=REQUEST_DATE) String userDate){
		//test period only - should be changed to get user from token
		int userid = Integer.parseInt(tokenVal); 
				
		Account account = accountRepository.findById(userid).orElse(new Account() {});
		if (!account.isMover()) return new DataTo(false, "Not a valid mover id");
		Mover mover1 = (Mover) account; 
		LocalDate requestDate = getRequestDate(userDate);
		List<RequestDetailsDTO> resDb = requestManager.getRequestDetailsByMoverFromDay(mover1, requestDate).stream()
				.map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
		return resDb.size() == 0 ? new DataTo(false, "No data for the date") : new DataTo(true, resDb);			
	}

	@RequestMapping(value = GET_RECENT_MOVER_REQUESTS, method=RequestMethod.GET)
	public DataTo getPossibleForMover(@RequestParam(name="token") String tokenVal){
		//test period only - should be changed to get user from token
		int userid = Integer.parseInt(tokenVal); 
		Account account = accountRepository.findById(userid).orElse(new Account() {});
		if (!account.isMover()) return new DataTo(false, "Not a valid mover id");
		Mover mover1 = (Mover) account; 		
		List<RequestDetailsDTO> resDb = requestManager.getPossibleRequestsForMover(mover1).stream()
				.map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
		return resDb.size() == 0 ? new DataTo(false, "No possible requests for this parameters") : new DataTo(true, resDb);			
	}

//	@PostMapping(value = SAVE_REQUEST)
//	public DataTo saveRequestToDatabase(@RequestBody RequestData requestData) {
//		Request request = new Request(
////				LocalDateTime.of(requestData.move_date, requestData.move_time),
//				LocalDateTime.now(),
//				LocalDate.now(),
//				Status.INITIAL,
//				false,
//				requestData.cost,
//				0,
//				Place.valueOf(requestData.place_type),
//				new List<RequestAdress>(Arrays.asList(requestData.addresses)),
//
//
//
//				);
//	}
}