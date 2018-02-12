package com.tomove.controller;


import com.tomove.common.DataTo;
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
	public DataTo getSignUpAccount(@RequestBody Map<String, Object> params) throws Exception {
		Account account = repository.findByEmail((String) params.get("email"));
		if (account != null) {
			return new DataTo(false, "Wrong email");
		}
		if (params.get("type").equals("customer"))
			account = new Customer();


		if (params.get("type").equals("mover"))
			account = new Mover();

		account.setEmail((String) params.get("email"));
		account.setPassword((String) params.get("password"));

//		int checkNumber = 1000+(int)(Math.random()*9000);
//		String code=""+checkNumber;
//		com.tomove.controller.SmsUtils.sendSMS(account.getPhone(), code, "toMove");
//		account.setVerificationCode(code);
		repository.save(account);


		return new DataTo(true, account);
	}


}
