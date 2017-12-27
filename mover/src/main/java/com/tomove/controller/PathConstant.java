package com.tomove.controller;


public class PathConstant {

    public static final String URL = "http://localhost:8080";
    public static final String GET_ALL_ACCOUNTS = "/";
    public static final String POST_LOGIN = "/login";
    public static final String GET_RECENT_CUSTOMER_REQUESTS = "/dashboard/requests/customer/getrecent";
    public static final String GET_CALENDAR_CUSTOMER_REQUESTS = "/dashboard/requests/customer/getforcalendar";
    public static final String GET_DATE_CUSTOMER_REQUESTS = "/dashboard/requests/customer/getfordate";
    public static final String GET_RECENT_MOVER_REQUESTS = "/dashboard/requests/mover/getrecent";
    public static final String GET_CALENDAR_MOVER_REQUESTS = "/dashboard/requests/mover/getforcalendar";
    public static final String GET_DATE_MOVER_REQUESTS = "/dashboard/requests/mover/getfordate";
    
    public static final String REQUEST_DATE = "request_date";

    public static final String FORGOT_PASSWORD = "/forgot";
    public static final String RESET_PASSWORD = "/reset";
    public static final String CHECK_TOKEN = "/token/check";

}
