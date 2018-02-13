package com.tomove.controller;


import com.tomove.common.DataTo;
import com.tomove.common.TruckTo;
import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Mover;
import com.tomove.model.subjectMover.Truck;
import com.tomove.repository.MoverRepository;
import com.tomove.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.tomove.common.PathConstant.*;

@RestController
@CrossOrigin
public class TruckController {


    private MoverRepository repository;
    private TruckRepository truckRepository;


    @Autowired
    public TruckController(MoverRepository repository, TruckRepository truckRepository) {
        this.repository = repository;
        this.truckRepository = truckRepository;
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

    @RequestMapping(value = SAVE_EDIT_TRUCKS, method = {RequestMethod.PUT, RequestMethod.POST} )
    public DataTo saveOrEdit(@PathVariable(value  = "userId") Integer userId, @RequestBody TruckTo truckTo) {
        Mover account = repository.findById(userId).orElse(null);
        if (account == null) {
            return new DataTo(false, "No account with id = " + userId);
        }
        if (!account.getType().equals("mover")) {
            return new DataTo(false, "Account with id = " + userId + " isn't a mover");
        }
        if(truckTo.getId()==null){
            Truck newTruck = truckTo.save(new Truck());
            newTruck.setMover(account);
            return new DataTo(true, new TruckTo(truckRepository.save(newTruck)));
        }
        Truck truck = truckRepository.getByIdAndMover(truckTo.getId(),account).orElse(null);
        if (truck == null){
            return new DataTo(false, "No truck with accountId = " + userId + " and truckId = " + truckTo.getId());
        }
        return new DataTo(true, new TruckTo(truckRepository.save(truckTo.save(truck))));
    }
}
