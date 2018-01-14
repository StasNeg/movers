package com.tomove.init;

import com.tomove.model.enums.Area;
import com.tomove.model.enums.Lift;
import com.tomove.model.enums.Place;
import com.tomove.model.enums.RoomType;
import com.tomove.model.enums.Status;
import com.tomove.model.objectMover.Address;
import com.tomove.model.objectMover.Item;
import com.tomove.model.objectMover.Request;
import com.tomove.model.objectMover.RequestAdress;
import com.tomove.model.objectMover.Room;
import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Customer;
import com.tomove.model.subjectMover.Mover;
import com.tomove.model.subjectMover.Truck;
import com.tomove.repository.AccountRepository;
import com.tomove.repository.AddressRepository;
import com.tomove.repository.ItemRepository;
import com.tomove.repository.RequestAddressRepository;
import com.tomove.repository.RequestRepository;
import com.tomove.repository.RoomRepository;
import com.tomove.repository.TruckRepository;

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
public class RequestsInit implements ApplicationRunner {
	
	private AccountRepository accountRepo;
	private RequestRepository requestRepo;
	private TruckRepository truckRepo;
	private AddressRepository addressRepo;
	private RoomRepository roomRepo;
	private RequestAddressRepository reqAddrRepo;
	private ItemRepository itemRepo;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String isInit;

    @Autowired
    public RequestsInit(AccountRepository accountRepo, RequestRepository requestRepo, TruckRepository truckRepo, AddressRepository addressRepo,
    		RoomRepository roomRepo, RequestAddressRepository reqAddrRepo, ItemRepository itemRepo)  {
        this.accountRepo = accountRepo;
        this.requestRepo = requestRepo;
        this.truckRepo = truckRepo;
        this.addressRepo = addressRepo;
        this.roomRepo = roomRepo;
        this.reqAddrRepo = reqAddrRepo;
        this.itemRepo = itemRepo;
    }    

    public void run(ApplicationArguments args) {
        if (!isInit.equals("none")) {
        	Customer customer1 = new Customer("050887766", "iamcustomer@co.il", "qwerty", true, "Izya", "Katsman", "whatuhskills", null);
        	Mover mover1 = new Mover("0503121421", "moveyou@co.il", "bumbumbigalow", true, "Wizzy", null);
        	List<Account> accounts = Arrays.asList(customer1, mover1);
        	accountRepo.saveAll(accounts);
        	
        	Address address1 = new Address("Ashkelon", "Bar Kochba", "209", "1203", 31.1231f,34.1231f,2, 
    				Lift.LIFT_TRUCK, Area.CENTER, null, null);
        	Address address2 = new Address("Kfar Saba","Kineret","10","55",32.41f,35.10f,4,
    				Lift.LIFT,Area.CENTER,null,null);
        	Address address3 = new Address("Rehovot", "Palmah Boulevard", "22", "18A", 31.13f, 34.10f, 10, 
    				Lift.NO_LIFT, Area.CENTER, null, null);
        	List<Address> addresses = Arrays.asList(address1,address2,address3);
        	addressRepo.saveAll(addresses);
        	
        	Truck truck1 = new Truck("Kamaz 20T", "123sdq13", 5, 3, Area.CENTER, mover1);	
        	List<Truck> trucks = Arrays.asList(truck1);
        	truckRepo.saveAll(trucks);
        	
        	LocalDate date1 = LocalDate.now();
        	LocalDateTime date2 = LocalDateTime.of(2018, 5, 25, 17, 30);
        	LocalDateTime date3 = LocalDateTime.of(2018, 6, 21, 12, 30);
        	LocalDateTime date4 = LocalDateTime.of(2018, 6, 21, 15, 38);
        	List<Room> roomsList = new ArrayList<Room>();
        	Request request1 = new Request(date2, date1, Status.INITIAL, false, 1200, 0, Place.APARTMENT, mover1, customer1, truck1, roomsList);
        	Request request2 = new Request(date3, date1, Status.INITIAL, false, 1200, 0, Place.APARTMENT, mover1, customer1, truck1, roomsList);
        	Request request3 = new Request(date4, date1, Status.INITIAL, false, 1200, 0, Place.APARTMENT, mover1, customer1, truck1, roomsList);
        	List<Request> requests = Arrays.asList(request1,request2,request3);
        	requestRepo.saveAll(requests);		
        	
        	RequestAdress reqAddress0 = new RequestAdress(0, request1, address1);
       		RequestAdress reqAddress1 = new RequestAdress(1, request1, address3);
       		RequestAdress reqAddress2 = new RequestAdress(2, request1, address2);
       		RequestAdress reqAddress3 = new RequestAdress(0, request2, address2);
       		RequestAdress reqAddress4 = new RequestAdress(1, request2, address3);
       		RequestAdress reqAddress5 = new RequestAdress(0, request3, address1);
       		RequestAdress reqAddress6 = new RequestAdress(1, request3, address3);
    		List<RequestAdress> requestAdresses = Arrays.asList(reqAddress0,reqAddress1,reqAddress2,reqAddress3,reqAddress4,reqAddress5,reqAddress6);    		
    		reqAddrRepo.saveAll(requestAdresses);
    		
    		List<Item> items1 = new ArrayList<Item>();
    		List<Item> items2 = new ArrayList<Item>();
    		List<Item> items3 = new ArrayList<Item>();
    		Room room1 = new Room(RoomType.SALON, null, request1, items1);
    		Room room2 = new Room(RoomType.BEDROOM, null, request1, items2);
    		Room room3 = new Room(RoomType.BEDROOM, null, request1, items3);
    		List<Room> rooms = Arrays.asList(room1,room2,room3);
    		roomRepo.saveAll(rooms);
    		
        	
    				
    		byte[] image = "someImamge".getBytes();  		
     		Item item1 = new Item("Sofa", null, address1, address2, room1);
    		items1 = new ArrayList<Item>();
      		Item item2 = new Item("TV",null,address1,address2,room2);
    		Item item3 = new Item("Brah",null,address1,address3,room3);	    		
    		List<Item> items = Arrays.asList(item1,item2,item3);
    		itemRepo.saveAll(items);
    		
    		/*    		TypeProperties itemProps = new TypeProperties("Length", "110", item1);
    		Set<TypeProperties> setProps = new HashSet<TypeProperties>();
    		setProps.add(itemProps); */   
        }
    }
}

