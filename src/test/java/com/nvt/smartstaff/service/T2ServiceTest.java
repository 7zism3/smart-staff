package com.nvt.smartstaff.service;

import com.nvt.smartstaff.utils.httpClient.HttpClientAsynchronous;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class T2ServiceTest {
    @Test
    void getRequest2() {

        long inTime = System.currentTimeMillis();
        assertNotEquals(4, T2Service.getRequest2());
        System.out.println("Time: " + (System.currentTimeMillis() - inTime));
    }
    @Test
    void getRequest3() {
        long inTime = System.currentTimeMillis();
        assertNotEquals(4, T2Service.getRequest3());
        System.out.println("Time: " + (System.currentTimeMillis() - inTime));
    }

    @Test
    void getRequest4() {
        long inTime = System.currentTimeMillis();
        assertNotEquals(4, T2Service.getRequest4());
        System.out.println("Time: " + (System.currentTimeMillis() - inTime));
    }


}