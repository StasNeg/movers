package com.tomove.controller;

import com.tomove.common.*;
import com.tomove.common.AddressDto;
import com.tomove.model.enums.*;
import com.tomove.model.objectMover.*;
import com.tomove.repository.*;
import com.tomove.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tomove.common.RequestDetailsDTO;
import com.tomove.model.mapping.RequestDetails;
import com.tomove.model.mapping.RequestORM;
import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Customer;
import com.tomove.model.subjectMover.Mover;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.*;
import java.util.stream.Collectors;

import static com.tomove.common.PathConstant.*;

@RestController
@CrossOrigin
public class RequestController {
    private AccountRepository accountRepository;
    private RequestORM requestManager;
    private RequestRepository requestRepository;
    private AddressRepository addressRepository;
    private RequestAddressRepository requestAddressRepository;
    private ItemRepository itemRepository;
    private ItemTypeRepository itemTypeRepository;
    private RoomRepository roomRepository;

    @Autowired
    public RequestController(
            RequestORM requestManager,
            RequestRepository requestRepository,
            AccountRepository accountRepository,
            AddressRepository addressRepository,
            RequestAddressRepository requestAddressRepository,
            ItemRepository itemRepository,
            ItemTypeRepository itemTypeRepository,
            RoomRepository roomRepository) {
        this.requestManager = requestManager;
        this.requestRepository = requestRepository;
        this.accountRepository = accountRepository;
        this.addressRepository = addressRepository;
        this.requestAddressRepository = requestAddressRepository;
        this.itemRepository = itemRepository;
        this.itemTypeRepository = itemTypeRepository;
        this.roomRepository = roomRepository;
    }

    @RequestMapping(value = GET_RECENT_CUSTOMER_REQUESTS, method = RequestMethod.GET)
    public DataTo getRecent(@RequestParam(name = "token") String tokenVal) {
        //test period only - should be changed to get user from token
        int userid = Integer.parseInt(tokenVal);

        Account account = accountRepository.findById(userid).orElse(new Account() {
        });
        if (!account.isCustomer()) return new DataTo(false, "Not a valid customer id");
        Customer customer1 = (Customer) account;

        List<RequestDetailsDTO> resDb = requestManager.getRequestDetailsByCustomer(customer1).stream()
                .map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
        return resDb.size() == 0 ? new DataTo(false, "No data for the customer") : new DataTo(true, resDb);
    }

    @RequestMapping(value = GET_CALENDAR_CUSTOMER_REQUESTS, method = RequestMethod.GET)
    public DataTo getRecentFromDate(@RequestParam(name = "token") String tokenVal, @RequestParam(name = REQUEST_DATE) String userDate) {
        //test period only - should be changed to get user from token
        int userid = Integer.parseInt(tokenVal);

        Account account = accountRepository.findById(userid).orElse(new Account() {
        });
        if (!account.isCustomer()) return new DataTo(false, "Not a valid customer id");
        Customer customer1 = (Customer) account;

        LocalDate requestDate = getRequestDate(userDate);
        List<RequestDetailsDTO> resDb = requestManager.getRequestDetailsByCustomerFromDay(customer1, requestDate).stream()
                .map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
        return resDb.size() == 0 ? new DataTo(false, "No data for the date") : new DataTo(true, resDb);
    }

    @RequestMapping(value = GET_DATE_CUSTOMER_REQUESTS, method = RequestMethod.GET)
    public DataTo getRecentForDate(@RequestParam(name = "token") String tokenVal, @RequestParam(name = REQUEST_DATE) String userDate) {
        //test period only - should be changed to get user from token
        int userid = Integer.parseInt(tokenVal);

        Account account = accountRepository.findById(userid).orElse(new Account() {
        });
        if (!account.isCustomer()) return new DataTo(false, "Not a valid customer id");
        Customer customer1 = (Customer) account;

        LocalDate requestDate = getRequestDate(userDate);
        List<RequestDetailsDTO> resDb = requestManager.getRequestDetailsByCustomerAndDay(customer1, requestDate).stream()
                .map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
        return resDb.size() == 0 ? new DataTo(false, "No data for the date") : new DataTo(true, resDb);
    }

    @RequestMapping(value = GET_DATE_MOVER_REQUESTS, method = RequestMethod.GET)
    public DataTo getRecentForMoverDate(@RequestParam(name = "token") String tokenVal, @RequestParam(name = REQUEST_DATE) String userDate) {
        //test period only - should be changed to get user from token
        int userid = Integer.parseInt(tokenVal);

        Account account = accountRepository.findById(userid).orElse(new Account() {
        });
        if (!account.isMover()) return new DataTo(false, "Not a valid mover id");
        Mover mover1 = (Mover) account;
        LocalDate requestDate = getRequestDate(userDate);
        List<RequestDetailsDTO> resDb = requestManager.getRequestDetailsByMoverAndDay(mover1, requestDate).stream()
                .map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
        return resDb.size() == 0 ? new DataTo(false, "No data for the date") : new DataTo(true, resDb);

    }

    private LocalDate getRequestDate(String userDate) {
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate requestDate = LocalDate.of(2018, 01, 01); //default value could be changed to some value from app.props
        try {
            requestDate = LocalDate.parse(userDate, dateformatter);
        } catch (Exception e) {
        }
        return requestDate;
    }

    private Map<String, List<RequestDetailsDTO>> getMapByDates(List<RequestDetailsDTO> iniList) {
        Map<String, List<RequestDetailsDTO>> res = new HashMap<>();
        iniList.forEach(request -> {
            String dateval = request.getMovedatetime().toLocalDate().toString();
            List<RequestDetailsDTO> dateList = iniList.stream()
                    .filter(x -> x.getMovedatetime().toLocalDate().equals(request.getMovedatetime().toLocalDate()))
                    .collect(Collectors.toList());
            res.put(dateval, dateList);
        });
        return res;
    }

    @RequestMapping(value = GET_CALENDAR_MOVER_REQUESTS, method = RequestMethod.GET)
    public DataTo getRecentForMoverFromDate(@RequestParam(name = "token") String tokenVal, @RequestParam(name = REQUEST_DATE) String userDate) {
        //test period only - should be changed to get user from token
        int userid = Integer.parseInt(tokenVal);

        Account account = accountRepository.findById(userid).orElse(new Account() {
        });
        if (!account.isMover()) return new DataTo(false, "Not a valid mover id");
        Mover mover1 = (Mover) account;
        LocalDate requestDate = getRequestDate(userDate);
        List<RequestDetailsDTO> resDb = requestManager.getRequestDetailsByMoverFromDay(mover1, requestDate).stream()
                .map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
        return resDb.size() == 0 ? new DataTo(false, "No data for the date") : new DataTo(true, resDb);
    }

    @RequestMapping(value = GET_RECENT_MOVER_REQUESTS, method = RequestMethod.GET)
    public DataTo getPossibleForMover(@RequestParam(name = "token") String tokenVal) {
        //test period only - should be changed to get user from token
        int userid = Integer.parseInt(tokenVal);
        Account account = accountRepository.findById(userid).orElse(new Account() {
        });
        if (!account.isMover()) return new DataTo(false, "Not a valid mover id");
        Mover mover1 = (Mover) account;
        List<RequestDetailsDTO> resDb = requestManager.getPossibleRequestsForMover(mover1).stream()
                .map(RequestDetails::makeReqDetailsDTO).collect(Collectors.toList());
        return resDb.size() == 0 ? new DataTo(false, "No possible requests for this parameters") : new DataTo(true, resDb);
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
        System.out.println(mover.getType());
        if (mover == null || !mover.getType().equals("mover")) {
            return new DataTo(false, "No mover with id = " + mover_id);
        }
        if (request == null) {
            return new DataTo(false, "No request with id = " + request_id);
        }
        if (request.getMover() != null) {
            return new DataTo(false, "Request " + request_id + " is already assigned");
        }
        request.setMover((Mover) mover);
        requestRepository.save(request);

        ((Mover) mover).getRequest().add(request);
        accountRepository.save(mover);
        return new DataTo(true, String.format("Request %d was assigned to mover %d", request_id, mover_id));
    }

    @PostMapping(value = SAVE_REQUEST)
    public DataTo saveRequestToDatabase(@RequestBody RequestData requestData) {

        List<RequestAdress> requestAdresses = new ArrayList<>();
        for (AddressDto addressDto : requestData.getAddresses()) {
            Address address = new Address(
                    addressDto.city,
                    addressDto.street,
                    addressDto.building,
                    addressDto.apartment,
                    addressDto.longitude,
                    addressDto.latitude,
                    addressDto.floor,
                    Lift.valueOf(addressDto.lift),
                    AreaService.getArea(addressDto.latitude, addressDto.longitude));
            addressRepository.save(address);
            RequestAdress requestAdress = new RequestAdress(addressDto.seqnumber, address);
            requestAdresses.add(requestAdress);
        }

        List<Room> rooms = new ArrayList<>();

        Request request = new Request(
				LocalDateTime.of(LocalDate.parse(requestData.move_date), LocalTime.parse(requestData.move_time)),
                LocalDate.now(),
                Status.INITIAL,
                false,
                requestData.cost,
                0,
                Place.valueOf(requestData.place_type),
                requestAdresses,
                rooms
        );
        Customer customer = (Customer) accountRepository.findById(requestData.customerId).get();
        request.setCustomer(customer);
        requestRepository.save(request);

        for (RequestAdress requestAdress : requestAdresses) {
            requestAdress.setRequest(request);
            requestAddressRepository.save(requestAdress);
        }

        for (MoveDto move : requestData.getMoves()) {
            // FIXME: 31/01/2018 GET REQUEST ADDRESS BY SEQNUMBER AND NOT BY INDEX (NOW IS NOT GUARANTEED)
            Address addressFrom = requestAdresses.get(move.addressIn.seqnumber).getAddress();
            Address addressTo = requestAdresses.get(move.addressOut.seqnumber).getAddress();
            for (RoomDto room : move.getRooms()) {
                for (ItemDto itemDto : room.getItems()) {
                    // TODO: 31/01/2018 SAVE ROOM IMAGE FROM REQUEST TO DB
                        Room itemRoom = new Room(RoomType.valueOf(room.room), null, request);
                    StringBuilder itemName = new StringBuilder();
                    itemName.append(itemDto.name);
                    for (Map.Entry<String, String> property : itemDto.properties.entrySet()) {
                        itemName.append("_" + property.getKey() + "=" + property.getValue());
                    }

                    Item item = new Item(itemName.toString(),
                            itemTypeRepository.findByName(itemDto.name).orElse(null),
                            addressFrom,
                            addressTo,
                            itemRoom);

                    itemRoom.setItems(new ArrayList<>());
                    itemRoom.getItems().add(item);
                    roomRepository.save(itemRoom);
                    itemRepository.save(item);
                }
            }
        }

        return new DataTo(true, "Saved to db");
    }

}