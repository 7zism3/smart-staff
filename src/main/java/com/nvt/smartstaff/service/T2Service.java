package com.nvt.smartstaff.service;

import com.google.gson.Gson;
import com.nvt.smartstaff.dto.t2.Root;
import com.nvt.smartstaff.utils.httpClient.HttpClientCustomExecutor;
import com.nvt.smartstaff.utils.httpClient.HttpClientAsynchronous;
import com.nvt.smartstaff.utils.httpClient.HttpClientSynchronous;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;



@AllArgsConstructor
@Service
public class T2Service {
    private static String url2 = "https://24pullrequests.com/users.json?page=1";

    private static String baseUrl = "https://24pullrequests.com/users.json";

    public Root[] getRequest() {
        String kq = "";
        // String url = newURIBuilder(baseUrl, "").toString();
        String url = url2;

        try {
            kq = HttpClientAsynchronous.getHttp(url);
        } catch (Exception e) {
            System.out.println("Lỗi khi call getHttp = " + e);
        }
        try {
            Root[] roots = new Gson().fromJson(kq, (Type) Root[].class);
            return roots;
        } catch (Exception e) {
            System.out.println("Lỗi khi chuyển json qua Obj = " + e);
        }

        return null;
    }

    public Root[] getRequest2() {
        String kq = "";

        String[] urls = new String[10];
        for (int i = 1; i < 11; i++) {
            String url3 = baseUrl + "?page=" + i;
            urls[i - 1] = url3;
        }
        try {
            kq = HttpClientCustomExecutor.getHttp(urls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            Root[] roots = new Gson().fromJson(kq, (Type) Root[].class);
            return roots;
        } catch (Exception e) {
            System.out.println("Lỗi khi chuyển json qua Obj = " + e);
        }

        return null;
    }

}