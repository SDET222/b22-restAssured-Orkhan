package com.cybertek.homework;
import com.cybertek.pojo.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
public class GetRequestToStudent {


    /*
    Re do following homework with pojo. Use Jackson annotations if needed.
   send a get request to student id 23401 as a path parameter and accept header application/json
   verify status code=200 /content type=c /Content-Encoding = gzip
   verify Date header exists
   assert that
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606


     */

@Test
    public void test1() {

    Student student = given().accept(ContentType.JSON)
            .pathParam("id", 23401)
            .when()
            .get("http://api.cybertektraining.com/student/{id}")
            .then().statusCode(200)
            .and().contentType("application/json;charset=UTF-8")
            .and().header("Content-Encoding", equalTo("gzip"))
            .and().header("Date", notNullValue()).extract().jsonPath().getObject("students[0]", Student.class);

    System.out.println("student.getFirstName() = " + student.getFirstName());
    System.out.println("student.getBatch() = " + student.getBatch());
    System.out.println("student.getSection() = " + student.getSection());
    System.out.println("student.getContact().getEmailAddress() = " + student.getContact().getEmailAddress());
    System.out.println("student.getAddress().getState() = " + student.getCompany().getAddress().getState());
    System.out.println("student.getAddress().getZipCode() = " + student.getCompany().getAddress().getZipCode());

    System.out.println("student.getCompany().getCompanyName() = " + student.getCompany().getCompanyName());

    String expFirstName = "Vera";
    int expBatch = 14;
    int expSection = 12;
    String expEmail = "aaa@gmail.com";
    String expCompanyName = "Cybertek";
    String expState = "IL";
    int expZipCode = 60606;

    assertThat(expFirstName, equalTo(student.getFirstName()));
    assertThat(expBatch, equalTo(student.getBatch()));
    assertThat(expSection, equalTo( student.getSection()));
    assertThat(expEmail, equalTo(student.getContact().getEmailAddress()));
    assertThat(expCompanyName, equalTo(student.getCompany().getCompanyName()));
    assertThat(expState, equalTo(student.getCompany().getAddress().getState()));
    assertThat(expZipCode, equalTo(student.getCompany().getAddress().getZipCode()));






}

}
