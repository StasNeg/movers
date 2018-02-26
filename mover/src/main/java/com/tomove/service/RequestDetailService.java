package com.tomove.service;

import com.tomove.common.AddressDto;
import com.tomove.common.ItemDto;
import com.tomove.common.RequestDetailsWithItemTo;
import com.tomove.model.objectMover.Item;
import com.tomove.repository.ItemRepository;
import com.tomove.repository.RequestAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RequestDetailService {

    private RequestAddressRepository requestRepository;
    private ItemRepository itemRepository;

    @Autowired
    public RequestDetailService(RequestAddressRepository requestRepository, ItemRepository itemRepository) {
        this.requestRepository = requestRepository;
        this.itemRepository = itemRepository;
    }

    public Iterable getRequestMoverDetail(Integer requestId) {
        Iterable<AddressDto> addresses = requestRepository.getAddressByRequest(requestId);
        List<Item> items = itemRepository.getAllByRequestId(requestId);
        Map<Integer, RequestDetailsWithItemTo> map = new HashMap<>();
        addresses.forEach(x -> map.put(x.getId(), new RequestDetailsWithItemTo(x)));
        items.forEach(item -> {
            map.get(item.getFrom().getId()).getItemsFrom().add(new ItemDto(item));
            map.get(item.getTo().getId()).getItemsTo().add(new ItemDto(item));
        });
        return map.values().stream().sorted((o1, o2) -> (o1.getAddressDto().seqnumber - o2.getAddressDto().seqnumber)).collect(Collectors.toList());
    }
}
