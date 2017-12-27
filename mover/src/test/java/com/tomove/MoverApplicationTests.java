package com.tomove;

import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Customer;
import com.tomove.model.subjectMover.Mover;
import com.tomove.repository.AccountRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoverApplicationTests {
	private Customer customer;
	private Mover mover;
	private List<Account> accounts;

	@Autowired
	private AccountRepository repository;

	@Before
	public void before() {
		repository.deleteAll();
		customer = new Customer("275", "stasn@ua.fm",
				"password", true, "FirstName",
				"LastName", "plumber", null);
		mover = new Mover("274", "stasn2@ua.fm", "password", true, "name", null);
		accounts = Arrays.asList(customer, mover);
		repository.saveAll(accounts);
	}
	@Test
	public void contextLoads() {
	}

	@Test
	public void findByEmail() {
		Assert.assertEquals(customer, repository.findByEmailAndPassword("stasn@ua.fm", "password"));
	}

	@Test
	public void findByWrongEmail() {
		Assert.assertEquals(null, repository.findByEmailAndPassword("stas@ua.fm", "password"));
	}

	@Test
	public void findByWrongPassword() {
		Assert.assertEquals(null, repository.findByEmailAndPassword("stasn@ua.fm", "pasord"));
	}

	@After
	public void after() {
		repository.deleteAll();
	}

}
