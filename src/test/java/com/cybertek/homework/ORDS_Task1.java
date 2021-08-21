package com.cybertek.homework;

import com.cybertek.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ORDS_Task1 extends HrTestBase {
    /*
    ORDS API:
Q1:
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is
     */

    @Test
    public  void test1() {

        given().accept(ContentType.JSON)
            .pathParam("value","US")
            .when().get("/countries/{value}")
            .then().statusCode(200)
            .and().assertThat().contentType("application/json")
            .and().assertThat().body("country_id", equalTo("US"), "country_name", equalTo("United States of America"),
                    "region_id",equalTo(2));


    }
@Test
    public void test2(){
        /*
        Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
         */

    Response response = given().accept(ContentType.JSON)
            .queryParam("q","{\"department_id\":80}")
            .when().get("/employees/");

    assertEquals(200,response.statusCode());
    assertEquals("application/json",response.contentType());

    JsonPath jsonPath = response.jsonPath();
    List<String> allJobIds = jsonPath.getList("items.job_id");
    System.out.println("allJobIds = " + allJobIds);
    for (String jobId : allJobIds) {
        assertTrue(jobId.startsWith("SA"));
    }


    List<Integer> allDepIds = jsonPath.getList("items.department_id");
    for (Integer depId : allDepIds) {
        assertEquals(80, depId );
    }

    int count = jsonPath.getInt("count");
    assertEquals(25, count );

}
@Test
public void test3(){
        /*
         Given accept type is Json
-Query param value q= region_id 3
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
         */

    Response response = given().accept(ContentType.JSON)
            .queryParam("q","{ \"region_id\": 3}")
            .when().get("/countries");

    assertEquals(200,response.statusCode());

    JsonPath jsonPath = response.jsonPath();
   List<Integer> allRegionIds = jsonPath.getList("items.region_id");
    for (Integer regionId : allRegionIds) {
        assertEquals(3,regionId);
    }

    int count = jsonPath.getInt("count");
    assertEquals(6,count);

    boolean hasMore = jsonPath.getBoolean("hasMore");
    assertTrue(hasMore==false);

    List<String> allCountrieNames = jsonPath.getList("items.country_name");
    System.out.println("allCountrieNames = " + allCountrieNames);

    List<String> expectedList = new ArrayList<>();
    expectedList.add("Australia");
    expectedList.add("China");
    expectedList.add("India");
    expectedList.add("Japan");
    expectedList.add("Malaysia");
    expectedList.add("Singapore");

    assertEquals(expectedList,allCountrieNames);

}


}
