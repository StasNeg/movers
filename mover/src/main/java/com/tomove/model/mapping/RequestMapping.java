package com.tomove.model.mapping;

import java.time.LocalDate;
import java.util.List;

import com.tomove.model.subjectMover.Customer;

public interface RequestMapping {
	public List<RequestDetails> getRequestDetailsByCustomer(Customer customer);
	public List<RequestDetails> getRequestDetailsByCustomerAndDay(Customer customer, LocalDate requestDate);
	public List<RequestDetails> getRequestDetailsByCustomerFromDay(Customer customer, LocalDate requestDate);
}
