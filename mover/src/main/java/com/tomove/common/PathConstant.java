package com.tomove.common;


public class PathConstant {
    public static final String URL = "http://localhost:8080";
    public static final String URL_FRONT = "http://localhost:4200";
    public static final String GET_ALL_ACCOUNTS = "/";
    public static final String POST_LOGIN = "/login";
    public static final String GET_RECENT_CUSTOMER_REQUESTS = "/dashboard/requests/customer/getrecent";
    public static final String GET_CALENDAR_CUSTOMER_REQUESTS = "/dashboard/requests/customer/getforcalendar";
    public static final String GET_DATE_CUSTOMER_REQUESTS = "/dashboard/requests/customer/getfordate";
    public static final String GET_RECENT_MOVER_REQUESTS = "/dashboard/requests/mover/getrecent";
    public static final String GET_CALENDAR_MOVER_REQUESTS = "/dashboard/requests/mover/getforcalendar";
    public static final String GET_DATE_MOVER_REQUESTS = "/dashboard/requests/mover/getfordate";
    
    public static final String REQUEST_DATE = "request_date";

    public static final String FORGOT_PASSWORD = "/forgot"; // { "email" : "myemail@gmail.com" }
    public static final String RESET_PASSWORD = "/reset"; // { "token" : "mytoken", "password" : "mynewpassword"}
    public static final String CHECK_TOKEN = "/token/check"; // { "token" : "mytoken" }

    public static final String REQUEST_GET_INFO = "/request/get";
    public static final String REQUEST_ASSIGN_TO_MOVER = "/request/assign";
    public static final String GET_TOTAL_COST_ESTIMATE = "/request/estimate/get";
    public static final String SAVE_REQUEST = "/request/save";

    public static final String GET_ALL_ROOMS_TYPE = "/roomstype/all";
    public static final String GET_ALL_ITEMS_FROM_ROOMTYPE = "/itemtype/roomtype";
    public static final String PROPERTIES_OF_ITEM = "/item_properties/value";

    public static final String GET_ALL_TRUCKS = "/trucks";

}
