package com.cybertek.day2;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HR_GetRequests {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://3.86.27.247:1000/ords/hr";
    }

    @Test
    public void tes1() {

    }


}
