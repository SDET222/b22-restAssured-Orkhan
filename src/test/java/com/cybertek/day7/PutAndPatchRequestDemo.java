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
public class PutAndPatchRequestDemo extends SpartanTestBase {

    @DisplayName("Put request to one spartan for update with Map")
    @Test
    public void putRequest() {
        //just like post request we have different options to send body, we will go with map
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name","BruceWayne");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",8811111111L);

        given().contentType(ContentType.JSON)
                .body(putRequestMap)
                .and().pathParam("id" , 80)
                .and().put("api/spartans/{id}")
                .then().statusCode(204);

        // send a get request after update, make sure updated field changed, or new info matching
        //with requestBody that we send


    }
    @Test
    public void patchRequest() {
        //just like post request we have different options to send body, we will go with map
        Map<String,Object> putRequestMap = new LinkedHashMap<>();

        putRequestMap.put("phone",9911111111L);

        given().contentType(ContentType.JSON)
                .body(putRequestMap).log().all()
                .and().pathParam("id" , 80)
                .and().patch("api/spartans/{id}")
                .then().statusCode(204);


        // send a get request after update, make sure updated field changed, or new info matching
        //with requestBody that we send

    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod4(){
        //this example we implement serialization with creatin spartan object sending as a request body
        //also implemented deserialization getting the id,sending get request and saving that body as a response

        //create one object from your pojo, send it as a JSON
        Spartan spartan = new Spartan();
        spartan.setName("BruceWayne");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);
        String expectedResponseMessage = "A Spartan is Born!";

        int idFromPost = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");

        System.out.println("idFromPost = " + idFromPost);
        //send a get request to id
        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).log().all().extract().as(Spartan.class);

        assertThat(spartanPosted.getName(),is(spartan.getName()));
        assertThat(spartanPosted.getGender(),is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(),is(spartan.getPhone()));
        assertThat(spartanPosted.getId(),is(idFromPost));

    }


}
