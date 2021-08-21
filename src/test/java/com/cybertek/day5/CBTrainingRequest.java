package com.cybertek.day5;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CBTrainingRequest {
//api.cybertektraining.com/

    @Test
    public void test1() {

        given()
                    .accept(ContentType.JSON)
                    .and()
                    .pathParam("id", 10423)
                    .when()
                    .get("http://api.cybertektraining.com/teacher/{id}")
                .then()
                    .statusCode(200)
                    .and()
                    .contentType("application/json;charset=UTF-8")
                    .and()
                    .header("Content-Length", is("236"))
                    .and()
                    .header("Date", notNullValue())
                    .and().assertThat()
                    .body("teachers[0].firstName", is("Alexander"))
                    .body("teachers[0].lastName", is("Syrup"))
                    .body("teachers[0].gender", is("male"));


    }

      @DisplayName("Get request to teacher/all and chaining")
    @Test

    public void test2(){
          given()
                  .accept(ContentType.JSON)
                  .and()
                  .when()
                  .get("http://api.cybertektraining.com/teacher/all")
                  .then()
                  .statusCode(200)
                  .and()
                  .body("teachers.firstName",hasItems("Alexander", "Darleen", "Sean"));







      }






}
