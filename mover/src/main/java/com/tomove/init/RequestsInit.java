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
import java.util.concurrent.ThreadLocalRandom;

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
    
    public static int randomInt(int min, int max){
		return ThreadLocalRandom.current().nextInt(min, max + 1);		
	}

    public void run(ApplicationArguments args) {
        if (!isInit.equals("none")) {
        	Customer customer1 = new Customer("050887766", "iamcustomer@co.il", "qwerty", true, "Izya", "Katsman", "whatuhskills", null);
        	Customer customer2 = new Customer("050887766", "iamcustomer2@co.il", "qwerty", true, "Vasya", "Katsman", "whatuhskills", null);
        	Mover mover1 = new Mover("0503121421", "moveyou@co.il", "bumbumbigalow", true, "Wizzy", null);
        	Mover mover2 = new Mover("05031214212", "2@co.il", "bumbumbigalow", true, "Wizzy", null);
        	List<Account> accounts = Arrays.asList(customer1, mover1,customer2,mover2);
        	accountRepo.saveAll(accounts);
        	
        	Address address1 = new Address("Ashkelon", "Bar Kochba", "209", "1203", 31.1231f,34.1231f,2, 
    				Lift.LIFT_TRUCK, Area.CENTER, null, null);
        	Address address2 = new Address("Kfar Saba","Kineret","10","55",32.41f,35.10f,4,
    				Lift.LIFT,Area.CENTER,null,null);
        	Address address3 = new Address("Rehovot", "Palmah Boulevard", "22", "18A", 31.13f, 34.10f, 10, 
    				Lift.NO_LIFT, Area.CENTER, null, null);
        	Address address4 = new Address("Haifa", "Str", "1", "1", 33f, 44f, 5, Lift.LIFT_TRUCK, Area.NORTH, null, null);
        	List<Address> addresses = Arrays.asList(address1,address2,address3,address4);
        	addressRepo.saveAll(addresses);
        	
        	Truck truck1 = new Truck("Kamaz 20T", "123sdq13", 5, 3, Area.CENTER, mover1);	
        	Truck truck2 = new Truck("Kamaz 20T", "123sdq13", 5, 3, Area.NORTH, mover2);
        	List<Truck> trucks = Arrays.asList(truck1,truck2);
        	truckRepo.saveAll(trucks);
        	
        	LocalDate date1 = LocalDate.now();
        	LocalDateTime date2 = LocalDateTime.of(2018, 5, 25, 17, 30);
        	LocalDateTime date3 = LocalDateTime.of(2018, 6, 21, 12, 30);
        	LocalDateTime date4 = LocalDateTime.of(2018, 6, 21, 15, 38);
        	List<Room> roomsList = new ArrayList<Room>();
        	Request request1 = new Request(date2, date1, Status.INITIAL, false, 1200, 0, Place.APARTMENT, mover1, customer1, truck1, roomsList);
        	Request request2 = new Request(date3, date1, Status.INITIAL, false, 1200, 0, Place.APARTMENT, mover1, customer1, truck1, roomsList);
        	Request request3 = new Request(date4, date1, Status.INITIAL, false, 1200, 0, Place.APARTMENT, mover1, customer1, truck1, roomsList);
        	Request request4 = new Request(date2, date1, Status.INITIAL, false, 1500, 0, Place.APARTMENT, null, customer1, null, roomsList);
        	Request request5 = new Request(date3, date1, Status.INITIAL, false, 1100, 0, Place.APARTMENT, null, customer1, null, roomsList);
        	Request request6 = new Request(date4, date1, Status.INITIAL, false, 1400, 0, Place.APARTMENT, null, customer1, null, roomsList);
        	Request request9 = new Request(date2, date1, Status.INITIAL, false, 1300, 0, Place.APARTMENT, null, customer2, null, roomsList);
        	Request request8 = new Request(date3, date1, Status.CANCELLED_BY_CUSTOMERS, false, 1800, 0, Place.APARTMENT, null, customer1, null, roomsList);
        	Request request7 = new Request(date4, date1, Status.CANCELLED_BY_MOVER, false, 1900, 0, Place.APARTMENT, null, customer1, null, roomsList);
        	List<Request> requests = Arrays.asList(request1,request2,request3,request4,request5,request6,request7,request8,request9);    	
        	requestRepo.saveAll(requests);	
        	
        	Room room1 = new Room();
        	List<Item> items1 = new ArrayList<Item>();       	
        	List<Room> rooms = new ArrayList<>();
    		for(int i=0;i<10;i++){
    			Request request = requests.get(randomInt(0,requests.size()-1));
    			room1 = new Room(RoomType.SALON, null, request, items1);
    			Room room2 = new Room(RoomType.BEDROOM, null, request, items1);
    			Room room3 = new Room(RoomType.BATHROOM, null, request, items1);
    			rooms.add(room1); 
    			rooms.add(room2);
    			rooms.add(room3);
    		}
    		roomRepo.saveAll(rooms);
    		
    		List<RequestAdress> requestAdresses = new ArrayList<>();
    		requests.forEach(x->{
    			for(int i=0;i<2;i++){	
    				int adressNum = randomInt(0,addresses.size()-1);			
    				Address address = new Address();
    				address =  addresses.get(adressNum);
    				RequestAdress reqAddress = new RequestAdress(i, x, address);
    				requestAdresses.add(reqAddress);
    			}
    		});
    		
    		reqAddrRepo.saveAll(requestAdresses);        	
        	    		    		
        	    				
    		/*byte[] image = "someImamge".getBytes();  		
     		Item item1 = new Item("Sofa", null, address1, address2, room1);
    		items1 = new ArrayList<Item>();
      		Item item2 = new Item("TV",null,address1,address2,room2);
    		Item item3 = new Item("Brah",null,address1,address3,room3);	    		
    		List<Item> items = Arrays.asList(item1,item2,item3);
    		itemRepo.saveAll(items);*/    		

        }
        
        
    }
}

