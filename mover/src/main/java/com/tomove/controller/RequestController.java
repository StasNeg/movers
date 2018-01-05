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
//
//
//    @RequestMapping(value = POST_LOGIN, method = RequestMethod.POST)
//    public DataTo getLoginAccount(@RequestBody Map<String, Object> params) {
//        Account account = repository.findByEmailAndPassword((String) params.get("email"), (String) params.get("password"));
//        return account == null ? new DataTo(false, "Wrong login") : new DataTo(true, account);
//    }
//
//    @RequestMapping(value = GET_ALL_ACCOUNTS, method = RequestMethod.GET)
//    public DataTo getAll() {
//        Iterable<Account> account = repository.findAll();
//        return account == null ? new DataTo(false, "Wrong login") : new DataTo(true, account);
//    }
//
//    @RequestMapping(value = FORGOT_PASSWORD, method = RequestMethod.POST)
//    public DataTo sendForgotPasswordEmail(@RequestBody Map<String, String> params) {
//        String email = params.get("email");
//        Account account = repository.findByEmail(email);
//        if (account != null) {
//            account.setVerificationCode(UUID.randomUUID().toString());
//            repository.save(account);
//            sendResetPasswordEmail(email, account.getVerificationCode());
//        }
//        return new DataTo(true, String.format("Email was sent to %s", email));
//    }
}
