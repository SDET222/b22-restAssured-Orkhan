package com.cybertek.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = ConfigurationReader.getProperty("spartansUrl");
        String dbUrl = "jdbc:oracle:thin:@3.86.27.247:1521:xe";
        String dbUsername = "SP";
        String dbPassword = "SP";
        DBUtils.createConnection(dbUrl,dbUsername,dbPassword);



    }


}
