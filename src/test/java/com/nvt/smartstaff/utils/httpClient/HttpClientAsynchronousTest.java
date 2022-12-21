package com.nvt.smartstaff.utils.httpClient;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HttpClientAsynchronousTest {

    @Test
    void getHttp() throws Exception {
        String url = "https://24pullrequests.com/users.json?page=1";
        long inTime = System.currentTimeMillis();
        assertNotEquals(4, HttpClientAsynchronous.getHttp(url));
        System.out.println("Time: " + (System.currentTimeMillis() - inTime));
    }
}