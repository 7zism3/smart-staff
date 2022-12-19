package com.nvt.smartstaff.service;

import com.google.gson.Gson;
import com.nvt.smartstaff.dto.t2.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

import static com.nvt.smartstaff.utils.httpClient.HttpClientAsynchronous.getHttp;


@AllArgsConstructor
@Service
public class T2Service {
    private static String baseUrl = "https://24pullrequests.com/users.json?page=1";
    private static String baseUrl2 = "https://postman-echo.com/get";

    public Root  []  getRequest() {
        String kq = "";
        // String url = newURIBuilder(baseUrl, "").toString();
        String url = baseUrl;

        try {
            kq = getHttp(url);
        } catch (Exception e) {
            System.out.println("Lỗi khi call getHttp = " + e);;
        }
        try {
            Root[] roots = new Gson().fromJson(kq, (Type)  Root[].class);
            return roots;
        } catch (Exception e) {
            System.out.println("Lỗi khi chuyển json qua Obj = " + e);;
        }
        return null;
    }

}