package com.tomove.model.mapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.tomove.model.subjectMover.Customer;
import com.tomove.model.subjectMover.Mover;

public interface RequestMapping {
	public List<RequestDetails> getRequestDetailsByCustomer(Customer customer);
	public List<RequestDetails> getRequestDetailsByCustomerAndDay(Customer customer, LocalDate requestDate);
	public List<RequestDetails> getRequestDetailsByCustomerFromDay(Customer customer, LocalDate requestDate);
	public List<RequestDetails> getRequestDetailsByMoverAndDay(Mover mover, LocalDate requestDate);
	public List<RequestDetails> getRequestDetailsByMoverFromDay(Mover mover, LocalDate requestDate);
	public List<RequestDetailsAdvanced> getBasiclyFilteredRequestsByMover(Mover mover);
	public List<RequestDetails> getDeeplyFilteredRequestsByMover(Mover mover, List<RequestDetailsAdvanced> requests);
	public List<RequestDetails> getPossibleRequestsForMover(Mover mover);
	public int getRequestCountForMoverByDay(Mover mover, LocalDateTime requestDateTime);
}
