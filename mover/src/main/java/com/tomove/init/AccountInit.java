package com.tomove.init;

import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Customer;
import com.tomove.model.subjectMover.Mover;
import com.tomove.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AccountInit implements ApplicationRunner {

    private AccountRepository repository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String isInit;

    @Autowired
    public AccountInit(AccountRepository repository) {
        this.repository = repository;
    }

    public void run(ApplicationArguments args) {
        if (!isInit.equals("none")) {
            Account customer = new Customer("275", "stasn1@ua.fm",
                    "password", true, "FirstName",
                    "LastName", "plumber", null);
            Account mover = new Mover("274", "stasn22@ua.fm", "password", true, "name", null);
            List<Account> accounts = Arrays.asList(customer, mover);
            repository.saveAll(accounts);
        }
    }
}

