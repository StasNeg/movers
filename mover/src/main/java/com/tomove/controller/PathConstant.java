package com.tomove.controller;


public class PathConstant {
    public static final String URL = "http://localhost:8080";
    public static final String GET_ALL_ACCOUNTS = "/";
    public static final String POST_LOGIN = "/login";
    public static final String GET_RECENT_REQUESTS = "/dashboard/requests/getrecent";
    public static final String GET_CALENDAR_REQUESTS = "/dashboard/requests/getforcalendar";
    public static final String GET_DATE_REQUESTS = "/dashboard/requests/getfordate";
    public static final String FORGOT_PASSWORD = "/forgot"; // { "email" : "myemail@gmail.com" }
    public static final String RESET_PASSWORD = "/reset"; // { "token" : "mytoken", "password" : "mynewpassword"}
    public static final String CHECK_TOKEN = "/token/check"; // { "token" : "mytoken" }
}
