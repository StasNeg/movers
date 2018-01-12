package com.tomove.common;


public class PathConstant {
    public static final String URL = "http://localhost:8080";
    public static final String URL_FRONT = "http://localhost:4202";
    public static final String GET_ALL_ACCOUNTS = "/";
    public static final String POST_LOGIN = "/login";
    public static final String GET_RECENT_REQUESTS = "/dashboard/requests/getrecent";
    public static final String GET_CALENDAR_REQUESTS = "/dashboard/requests/getforcalendar";
    public static final String GET_DATE_REQUESTS = "/dashboard/requests/getfordate";
    public static final String FORGOT_PASSWORD = "/forgot"; // { "email" : "myemail@gmail.com" }
    public static final String RESET_PASSWORD = "/reset"; // { "token" : "mytoken", "password" : "mynewpassword"}
    public static final String CHECK_TOKEN = "/token/check"; // { "token" : "mytoken" }

    public static final String REQUEST_GET_INFO = "/request/get";
    public static final String REQUEST_ASSIGN_TO_MOVER = "/request/assign";
    public static final String GET_TOTAL_COST_ESTIMATE = "/request/estimate/get";
}
