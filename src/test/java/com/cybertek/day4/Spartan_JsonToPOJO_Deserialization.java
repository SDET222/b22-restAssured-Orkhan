package com.cybertek.day4;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Spartan_JsonToPOJO_Deserialization extends SpartanTestBase {
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/api/spartans/{id}");

        response.prettyPrint();

Spartan spartan1 = response.body().as(Spartan.class);
        System.out.println(spartan1.toString());
        assertEquals(spartan1.getName(),"Meta");
        assertEquals(spartan1.getId(),15);
        assertEquals(spartan1.getGender(),"Female");
        assertEquals(spartan1.getPhone(),1938695106);

    }
    public class Spartan {
        private int id;
        private String name;
        private String gender;
        private long phone;

        public Spartan(){

        }

        public Spartan(int id, String name, String gender, long phone) {
            this.id = id;
            this.name = name;
            this.gender = gender;
            this.phone = phone;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public long getPhone() {
            return phone;
        }

        public void setPhone(long phone) {
            this.phone = phone;
        }
    }

}
