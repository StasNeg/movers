package com.tomove.controller;


import com.tomove.controller.to.DataTo;
import com.tomove.model.subjectMover.Account;
import com.tomove.repository.AccountRepository;
import com.tomove.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;
import java.util.UUID;

import static com.tomove.controller.PathConstant.*;

@RestController
@CrossOrigin
public class LoginController {

    private AccountRepository repository;

    @Autowired
    private EmailService emailService;

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

    @RequestMapping(value = FORGOT_PASSWORD, method = RequestMethod.POST)
    public DataTo sendForgotPasswordEmail(@RequestBody Map<String, String> params) {
        String email = params.get("email");
        Account account = repository.findByEmail(email);
        if (account == null) {
            return new DataTo(false, String.format("No user with email: %s", email));
        } else {
            account.setVerificationCode(UUID.randomUUID().toString());
            account.setFlPasswordUpdate(true);
            repository.save(account);
            sendResetPasswordEmail(email, account.getVerificationCode());
            return new DataTo(true, String.format("Email was sent to %s", email));
        }
    }

    private void sendResetPasswordEmail(String email, String token) {
        // Email message
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setFrom("support@demo.com");
        passwordResetEmail.setTo(email);
        passwordResetEmail.setSubject("Password Reset Request");
        passwordResetEmail.setText("To reset your password, click the link below:\n" + URL
                + "/reset?token=" + token);

        emailService.sendEmail(passwordResetEmail);
    }

    @RequestMapping(value = CHECK_TOKEN, method = RequestMethod.GET)
    public DataTo checkToken(String token) {
        Account account = repository.findByVerificationCode(token);
        if (account == null) {
            return new DataTo(false, "Password reset token invalid");
        } else {
            return new DataTo(true, "Password reset token valid");
        }
    }

    @RequestMapping(value = RESET_PASSWORD, method = RequestMethod.POST)
    public DataTo storeNewPassword(@RequestBody Map<String, String> params) {
        String token = params.get("token");
        Account account = repository.findByVerificationCode(token);
        if (account == null || !account.isFlPasswordUpdate()) {
            return new DataTo(false, "No such token"); // shouldn't happen, but we check anyway
        } else {
            String password = params.get("password");
            /// TODO: 27/12/2017 ADD PROPER CHECK FOR LENGTH AM ERROR MESSAGE
            if (password == null || password.length() == 0) {
                return new DataTo(false, "Password can not be that");
            } else {
                account.setPassword(password);
                account.setFlPasswordUpdate(false);
            }
            repository.save(account);
            return new DataTo(true, "Password saved");
        }
    }
}
