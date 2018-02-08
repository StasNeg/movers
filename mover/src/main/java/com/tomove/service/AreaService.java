package com.tomove.service;

/**
 * NORTH top left (33.299999, 34.55) top right (33.299999, 35.54999999)
 * bottom left (32.399999, 35.299999) bottom right (32.3999999, 35.54999999)
 * <p>
 * CENTER top left (32.4, 34.55999999) top right (32.4, 34.9999999)
 * bottom left (31.599999, 34.55999999) bottom right (31.5999999, 34.99999999)
 * <p>
 * SOUTH top left (31.6, 34.40000) top right (31.6, 35.20)
 * bottom left (29.55, 34.400000) bottom right (29.55, 35.20)
 * <p>
 * EAST  top left (32.4, 35.00000000) top right (32.4, 35.55)
 * bottom left (31.599999999, 35.0000000000) bottom right (31.5999999, 35.55)
 **/

import com.tomove.model.enums.Area;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaService {

    private static final Map<Area, List<Coordinate>> areas = new HashMap<>();

    static {
        areas.put(Area.NORTH, Arrays.asList(new Coordinate(33.299999f, 34.55f), new Coordinate(32.3999999f, 35.54999999f)));
        areas.put(Area.CENTER, Arrays.asList(new Coordinate(32.4f, 34.55999999f), new Coordinate(31.5999999f, 34.99999999f)));
        areas.put(Area.SOUTH, Arrays.asList(new Coordinate(31.6f, 34.40000f), new Coordinate(29.55f, 35.20f)));
        areas.put(Area.EAST, Arrays.asList(new Coordinate(32.4f, 35.00000000f), new Coordinate(31.5999999f, 35.55f)));
    }

    public static Area getArea(float lat, float lng) {
        Area area = Area.ALL;
        Coordinate current = new Coordinate(lat, lng);
        for (Area key : areas.keySet()) {
            if (checkBottom(areas.get(key).get(0), areas.get(key).get(1), current)) {
                area = key;
                break;
            }
        }
        return area;
    }

    private static boolean checkBottom(Coordinate topLeft, Coordinate bottomRight, Coordinate check) {
        return ((topLeft.getLat() >= check.getLat() && topLeft.getLng() <= check.getLng())
                && (bottomRight.getLat() <= check.getLat() && bottomRight.getLng() >= check.getLng()));
    }


    private static class Coordinate {

        private float lat;
        private float lng;

        public Coordinate(float lat, float lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public float getLat() {
            return lat;
        }

        public void setLat(float lat) {
            this.lat = lat;
        }

        public float getLng() {
            return lng;
        }

        public void setLng(float lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    '}';
        }
    }
}
