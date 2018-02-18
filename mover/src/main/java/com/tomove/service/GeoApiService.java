package com.tomove.service;

import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.tomove.common.AddressDto;
import org.springframework.stereotype.Service;

@Service
public class GeoApiService {
    private final static String API_KEY = "AIzaSyAz9zX-L__RluZ8C3hgBWVE3YSoiprfuYs";

    private final static GeoApiContext CONTEXT = new GeoApiContext.Builder()
            .apiKey(API_KEY)
            .build();

    public static Integer getDistance(AddressDto addressIn, AddressDto addressOut) {
        DirectionsResult results = new DirectionsResult();
        try {
            results = new DirectionsApiRequest(CONTEXT)
                    .origin(new LatLng(addressIn.latitude, addressIn.longitude))
                    .destination(new LatLng(addressOut.latitude, addressOut.longitude))
                    .mode(TravelMode.DRIVING)
                    .await();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (results.routes == null) {
            System.out.println("Null response from google API");
            return 0;
        } else {
            return ((int) results.routes[0].legs[0].distance.inMeters) / 1000;
        }
    }

}
