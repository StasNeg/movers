package com.tomove.controller;


import com.tomove.common.DataTo;
import com.tomove.common.TruckTo;
import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Truck;
import com.tomove.repository.AccountRepository;
import com.tomove.repository.TruckRepository;
import com.tomove.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.tomove.common.PathConstant.DELETE_TRUCKS;
import static com.tomove.common.PathConstant.GET_ALL_TRUCKS;

@RestController
@CrossOrigin
public class TruckController {


    private AccountRepository repository;
    private TruckRepository truckRepository;
    private EmailService emailService;

    @Autowired
    public TruckController(AccountRepository repository, TruckRepository truckRepository, EmailService emailService) {
        this.repository = repository;
        this.truckRepository = truckRepository;
        this.emailService = emailService;
    }

    @RequestMapping(value = GET_ALL_TRUCKS, method = RequestMethod.GET)
    public DataTo getTrucks(@RequestParam(value = "userId") Integer userId) {
        Account account = repository.findById(userId).orElse(null);
        if (account == null){
            return new DataTo(false, "No account with id = " + userId);
        }
        if (!account.getType().equals("mover")){
            return new DataTo(false, "Account with id = " + userId + " isn't a mover");
        }
        List<TruckTo> trucks = new ArrayList<>();
        truckRepository.getAllByMoverId(userId).forEach(truck -> trucks.add(new TruckTo(truck)));
        return new DataTo(true, trucks);
    }

    @RequestMapping(value = DELETE_TRUCKS, method = RequestMethod.DELETE)
    public DataTo deleteTruck(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "truckId") Integer truckId) {
        Account account = repository.findById(userId).orElse(null);
        if (account == null) {
            return new DataTo(false, "No account with id = " + userId);
        }
        if (!account.getType().equals("mover")) {
            return new DataTo(false, "Account with id = " + userId + " isn't a mover");
        }
        return truckRepository.deleteTruckByIdAndMover(truckId, account) > 0 ? new DataTo(true, "AllRight") :
         new DataTo(false, "No truck with accountId = " + userId + " and truckId = " + truckId);
    }

}
