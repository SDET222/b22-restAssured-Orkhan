package com.cybertek.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter //from lombok dependency
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)

public class StudentsAddress {

private String state;
private int zipCode;




    }

