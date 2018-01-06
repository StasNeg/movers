package com.tomove.controller;


import com.tomove.common.DataTo;
import com.tomove.model.objectMover.Request;
import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Mover;
import com.tomove.repository.AccountRepository;
import com.tomove.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.tomove.common.PathConstant.*;

@RestController
@CrossOrigin
public class RequestController {

    private AccountRepository accountRepository;
    private RequestRepository requestRepository;

    @Autowired
    public RequestController(AccountRepository accountRepository, RequestRepository requestRepository) {
        this.accountRepository = accountRepository;
        this.requestRepository = requestRepository;
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
}
