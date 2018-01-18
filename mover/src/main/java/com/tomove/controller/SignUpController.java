package com.tomove.controller;


import com.tomove.controller.to.DataTo;
import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Customer;
import com.tomove.model.subjectMover.Mover;
import com.tomove.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class SignUpController {

	private AccountRepository repository;

	@Autowired
	public SignUpController(AccountRepository repository) {
		this.repository = repository;
	}


	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public DataTo getSignUpAccount(@RequestBody Map<String, Object> params) {
		Account account = repository.findByEmail((String) params.get("email"));
		if (account != null) {
			return new DataTo(false, "Wrong email");
		}
		if (((String) params.get("type")).equals("customer"))
			account = new Customer();

		if (((String) params.get("type")).equals("mover"))
			account = new Mover();

		account.setEmail((String) params.get("email"));
		account.setPassword((String) params.get("password"));
		repository.save(account);

		return new DataTo(true, account);
	}


}
