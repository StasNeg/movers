package com.tomove.repository;

import com.tomove.model.subjectMover.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {


    Account findByEmailAndPassword(String email, String password);

    Account findByEmail(String email);

    Account findByVerificationCode(String token);
}
