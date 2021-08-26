package com.cybertek.day8;
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
import com.cybertek.utilities.SpartanAuthTestBase;

public class SpartanWithAuthTest extends SpartanAuthTestBase {

        @DisplayName("Get /api/spartans as a public user expect 401")
        @Test
    public void test1() {
            get("/api/spartans")
                    .then().statusCode(401)
                    .log().all();
        }

    @DisplayName("GET /api/spartans as admin user expect 200")
    @Test
    public void testAdmin(){
        //how to pass admin admin as a username and password ?
        given()
                .auth().basic("admin","admin")
                .and().accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .log().all();

    }
    @DisplayName("Delete /api/spartans{id} as editor user expect 403")
        @Test
    public void deleteSpartan(){

            given().auth().basic("editor","editor")
                    .and().accept(ContentType.JSON)
                    .and().pathParam("id", 30)
                    .when()
                    .delete("/api/spartans/{id}")
                    .then()
                    .statusCode(403)
                    .log().body();




    }






}
