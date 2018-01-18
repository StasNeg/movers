package com.tomove;

import com.tomove.controller.to.AddressDto;
import com.tomove.controller.to.RequestDetailsDTO;
import com.tomove.model.enums.Area;
import com.tomove.model.enums.Lift;
import com.tomove.model.enums.Place;
import com.tomove.model.enums.RoomType;
import com.tomove.model.enums.Status;
import com.tomove.model.mapping.RequestDetails;
import com.tomove.model.mapping.RequestMapping;
import com.tomove.model.mapping.RequestORM;
import com.tomove.model.objectMover.Address;
import com.tomove.model.objectMover.Item;
import com.tomove.model.objectMover.ItemType;
import com.tomove.model.objectMover.Request;
import com.tomove.model.objectMover.RequestAdress;
import com.tomove.model.objectMover.Room;
import com.tomove.model.objectMover.TypeProperties;
import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Customer;
import com.tomove.model.subjectMover.Mover;
import com.tomove.model.subjectMover.Truck;
import com.tomove.repository.*;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoverApplicationTests2 {
	private Customer customer1,customer2;
	private Mover mover1;
	private Truck truck1;
	private Item item1,item2,item3,item4,item5;

	private Room room1, room2, room3;
	private Address address1, address2, address3;
	private Request request1;
	private RequestAdress reqAddress0, reqAddress1, reqAddress2;
	private LocalDate date1,date3;
	private LocalDateTime date2;
	private byte[] image;
	
	private List<Item> items1, items2, items3;
	private List<RequestAdress> requestAdresses;
	private List<Room> roomsList;
	private TypeProperties itemProps;
	private Set<TypeProperties> setProps;
	
	private List<Account> accounts;
	private List<Truck> trucks;
	private List<Room> rooms;
	private List<Request> requests;
	private List<Address> addresses;
	private List<Item> items;
	private List<ItemType> itemTypes;
	

	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private RequestRepository requestRepo;
	@Autowired
	private TruckRepository truckRepo;
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private RoomRepository roomRepo;
	@Autowired
	private RequestAddressRepository reqAddrRepo;
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private RequestORM requestsManager;



	@Before
	public void before() {
		accountRepo.deleteAll();
		truckRepo.deleteAll();
		addressRepo.deleteAll();
		reqAddrRepo.deleteAll();
		roomRepo.deleteAll();
		requestRepo.deleteAll();
		itemRepo.deleteAll();
		
		address1 = new Address("Ashkelon", "Bar Kochba", "209", "1203", 31.1231f,34.1231f,2, 
				Lift.LIFT_TRUCK, Area.CENTER, items1, items2);
		address2 = new Address("Kfar Saba","Kineret","10","55",32.41f,35.10f,4,
				Lift.LIFT,Area.CENTER,items3,items2);
		address3 = new Address("Rehovot", "Palmah Boulevard", "22", "18A", 31.13f, 34.10f, 10, 
				Lift.NO_LIFT, Area.CENTER, items1, items2);
		itemProps = new TypeProperties();
		setProps = new HashSet<TypeProperties>();
		setProps.add(itemProps);
		date1 = LocalDate.now();
		date2 = LocalDateTime.of(2018, 5, 25, 17, 30);
		date3 = LocalDate.of(2018, 5, 25);
		image = "someImamge".getBytes();
		customer1 = new Customer("050887766", "iamcustomer@co.il", "qwerty", true, "Izya", "Katsman", "whatuhskills", null);
		customer2 = new Customer("050887766", "iamcustomer2@co.il", "qwerty", true, "Vasya", "Katsman", "whatuhskills", null);
		mover1 = new Mover("0503121421", "moveyou@co.il", "bumbumbigalow", true, "Wizzy", null);
		truck1 = new Truck("Kamaz 20T", "123sdq13", 5, 3, Area.CENTER, mover1);			
		
		request1 = new Request(date2, date1, Status.INITIAL, false, 1200, 0, Place.APARTMENT, mover1, customer1, truck1, roomsList);
		
		room1 = new Room(RoomType.SALON, null, request1, items1);
		room2 = new Room(RoomType.BEDROOM, null, request1, items2);
		room3 = new Room(RoomType.BEDROOM, null, request1, items3);		
		
		item1 = new Item("Sofa", null, address1, address2, room1);
		items1 = new ArrayList<Item>();
		items1.add(item1);
		item2 = new Item("TV",null,address1,address2,room2);
		item3 = new Item("Brah",null,address1,address3,room3);				
		
		accounts = Arrays.asList(customer1,customer2, mover1);
		trucks = Arrays.asList(truck1);
		rooms = Arrays.asList(room1,room2,room3);
		requests = Arrays.asList(request1);
		addresses = Arrays.asList(address1,address2,address3);
		items = Arrays.asList(item1,item2,item3);
		
		reqAddress0 = new RequestAdress(0, request1, address1);
		reqAddress1 = new RequestAdress(1, request1, address3);
		reqAddress2 = new RequestAdress(2, request1, address2);
		requestAdresses = Arrays.asList(reqAddress0, reqAddress1, reqAddress2);
		
		accountRepo.saveAll(accounts);		
		truckRepo.saveAll(trucks);
		addressRepo.saveAll(addresses);
		requestRepo.saveAll(requests);		
		reqAddrRepo.saveAll(requestAdresses);
		roomRepo.saveAll(rooms);
		itemRepo.saveAll(items);		
	}

	/*@Test
	public void contextLoads() {
	}	*/	
	
	@Test
	public void otherTests(){
		System.out.println("START");
		List<RequestDetails> res = requestsManager.getRequestDetailsByMoverAndDay(mover1,date3);
		if(res.size()>0){
			res.forEach(System.out::println);
		}
		System.out.println("FINISH");
	}
	
/*	@Test
	public void findByUserId(){
		System.out.println("YO2");
		List<RequestDetails> res = requestsManager.getRequestDetailsByCustomer(customer1);
		Assert.assertEquals(1,res.size());
		List<RequestDetails> res2 = requestsManager.getRequestDetailsByCustomer(customer2);
		Assert.assertNotEquals(1,res2.size());
	}*/
	/*
	@Test
	public void findByUserIdFromDate(){
		List<RequestDetails> res = requestsManager.getRequestDetailsByCustomerFromDay(customer1,date1);
		Assert.assertEquals(1,res.size());
		List<RequestDetails> res2 = requestsManager.getRequestDetailsByCustomerFromDay(customer2,date1);
		Assert.assertNotEquals(1,res2.size());
	}
	@Test
	public void findByUserIdByDate(){
		List<RequestDetails> res = requestsManager.getRequestDetailsByCustomerAndDay(customer1,date3);
		Assert.assertEquals(1,res.size());
		List<RequestDetails> res3 = requestsManager.getRequestDetailsByCustomerAndDay(customer1,date1);
		Assert.assertNotEquals(1,res3.size());
		List<RequestDetails> res2 = requestsManager.getRequestDetailsByCustomerAndDay(customer2,date1);
		Assert.assertNotEquals(1,res2.size());
	}	*/

	@After
	public void after() {
		//accountRepo.deleteAll();
	}

}
