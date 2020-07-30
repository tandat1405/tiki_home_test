package com.datnt.tikihometest.api;

public class ApiUtils {
    public static final String BASE_URL = "https://api.tiki.vn/";

    public static HomeClient homeClient() {
        return RetrofitClient.getClient(BASE_URL).create(HomeClient.class);
    }
}
