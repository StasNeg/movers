package com.tomove.controller;


import com.tomove.controller.to.DataTo;
import com.tomove.model.subjectMover.Account;
import com.tomove.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.tomove.controller.PathConstant.GET_ALL_ACCOUNTS;
import static com.tomove.controller.PathConstant.POST_LOGIN;

@RestController
@CrossOrigin
public class LoginController {

    private AccountRepository repository;

    @Autowired
    public LoginController(AccountRepository repository) {
        this.repository = repository;
    }


    @RequestMapping(value = POST_LOGIN, method = RequestMethod.POST)
    public DataTo getLoginAccount(@RequestBody Map<String, Object> params) {
        Account account = repository.findByEmailAndPassword((String) params.get("email"), (String) params.get("password"));
        return account == null ? new DataTo(false, "Wrong login") : new DataTo(true, account);
    }

    @RequestMapping(value = GET_ALL_ACCOUNTS, method = RequestMethod.GET)
    public DataTo getAll() {
        Iterable<Account> account = repository.findAll();
        return account == null ? new DataTo(false, "Wrong login") : new DataTo(true, account);
    }
}
