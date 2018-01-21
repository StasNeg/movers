package com.tomove.init;

import com.tomove.model.enums.Place;
import com.tomove.model.enums.Status;
import com.tomove.model.objectMover.Request;
import com.tomove.model.objectMover.RequestAdress;
import com.tomove.model.objectMover.Room;
import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Customer;
import com.tomove.model.subjectMover.Mover;
import com.tomove.repository.AccountRepository;
import com.tomove.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInit implements ApplicationRunner {

    private AccountRepository accountRepository;
    private RequestRepository requestRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String isInit;

    @Autowired
    public DatabaseInit(AccountRepository accountRepository, RequestRepository requestRepository) {
        this.accountRepository = accountRepository;
        this.requestRepository = requestRepository;

    }

    public void run(ApplicationArguments args) {
        if (!isInit.equals("none")) {

            /* INIT ACCOUNTS */
            Account customer1 = new Customer("111111", "stasn1@ua.fm",
                    "password1", true, "FirstName1",
                    "LastName1", "plumber", null);
            Account customer2 = new Customer("222222", "stasn2@ua.fm",
                    "password2", true, "FirstName2",
                    "LastName2", "designer", null);
            Account mover1 = new Mover("111", "mcfoton+1@gmail.com", "password1", true, "name1", null);
            Account mover2 = new Mover("222", "mcfoton+2@gmail.com", "password2", true, "name2", null);
            List<Account> accounts = Arrays.asList(customer1, customer2, mover1, mover2);

            /* INIT REQUESTS */
            List<Request> requests = Arrays.asList(
                    new Request(
                            LocalDateTime.now(),
                            LocalDate.now(),
                            Status.INITIAL,
                            false,
                            200,
                            20,
                            Place.APARTMENT,
                            new ArrayList<RequestAdress>(),
                            new ArrayList<Room>()),
                    new Request(
                            LocalDateTime.now(),
                            LocalDate.now(),
                            Status.INITIAL,
                            false,
                            200,
                            10,
                            Place.APARTMENT,
                            new ArrayList<RequestAdress>(),
                            new ArrayList<Room>())
            );


            accountRepository.saveAll(accounts);
            requestRepository.saveAll(requests);
        }
    }
}

