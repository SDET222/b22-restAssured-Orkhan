package com.cybertek.day7;

import com.cybertek.utilities.SpartanTestBase;
import com.cybertek.pojo.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanPostRequest_Demo extends SpartanTestBase {

       /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */
        @Test

        public void postMethod1(){

            String requestJsonBody = "{\"gender\":\"Male\",\n" +
                    "\"name\":\"Severus\",\n" +
                    "\"phone\":8877445596}";

            Response response = given().accept(ContentType.JSON)
                    .and().contentType(ContentType.JSON)
                    .body(requestJsonBody)
                    .when()
                    .post("/api/spartans");

            assertThat(response.statusCode(), is(201));
            assertThat(response.contentType(), is("application/json"));

            String expectedResponseMsg = "A Spartan is Born!";
            assertThat(response.path("success"), is(expectedResponseMsg));
            assertThat(response.path("data.name"), is("Severus"));


        }

    @Test

    public void postWithJson(){

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("name", "Severus");
        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("phone", 1112223334);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMsg = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMsg));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(1112223334));



    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod3(){
        //create one object from your pojo, send it as a JSON
        Spartan spartan = new Spartan();
        spartan.setName("SeverusSpartan");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);

        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("SeverusSpartan"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));

        response.prettyPrint();
    }
    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod4(){
        //create one object from your pojo, send it as a JSON
        Spartan spartan = new Spartan();
        spartan.setName("SeverusSpartan");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);

        int idFromPost = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(is(201))
                .contentType(is("application/json"))
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath().getInt("data.id");

        System.out.println(idFromPost);


    }


    //Create one SpartanUtil class
    //create a static method that returns Map<String,Object>
    //use faker library(add as a depedency) to assign each time different information
    //for name,gender,phone number
    //then use your method for creating spartan as a map,dynamically.





}
