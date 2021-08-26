package com.cybertek.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@JsonIgnoreProperties(value = "id", allowSetters = true)
@Getter //from lombok dependency
@Setter
@ToString
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Spartan {
/*
{
    "id": 15,
    "name": "Meta",
    "gender": "Female",
    "phone": 1938695106
}
 */
    private int id;
    private String name;
    private String gender;
    private long phone;
//
//    public Spartan(){
//
//    }

//    public Spartan(int id, String name, String gender, long phone) {
//        this.id = id;
//        this.name = name;
//        this.gender = gender;
//        this.phone = phone;
//    }


}
