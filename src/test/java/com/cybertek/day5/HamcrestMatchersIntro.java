package com.cybertek.day5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @Test
    public void simpleTest1(){

        assertThat(5+5, is(10));
        assertThat(5+5, equalTo(10));
        assertThat(5+5, is (equalTo(10)));

        assertThat(5+5, not (9));
        assertThat(5+5, is (not (9)));
        assertThat(5+5, is (not(equalTo(9))));

        //number comparison

        assertThat(5+5, is (greaterThan(9)));
        assertThat(5+5,  (greaterThanOrEqualTo(10)));
        assertThat(5+5, is (lessThan(15)));

        String text = "Learning Hamcrest";

        assertThat(text, is (equalTo("Learning Hamcrest")));
        assertThat(text, is ("Learning Hamcrest"));
        assertThat(text, equalTo ("Learning Hamcrest"));

        assertThat(text, startsWith("Lea"));
        assertThat(text,startsWithIgnoringCase("learning"));
        assertThat(text, endsWith("rest"));

        //check if text contains String learning

        assertThat(text, containsString("Learning"));
        assertThat(text,containsStringIgnoringCase("LEARning"));

        String str = " ";

        //check if string is blank
        assertThat(str,blankString() );
        assertThat(str.trim(),emptyString());

    }

    @DisplayName("Hamcrest for Collection")
    @Test
    public void Test2(){

        List<Integer> listOfNums = Arrays.asList(1,4,5,6,32,54,66,77,45,23);

        //check size of the list
        assertThat(listOfNums, hasSize(10));
        //check if has item
        assertThat(listOfNums, hasItem(32));

        assertThat(listOfNums, hasItems(1,4,77));

        //check if all nums greater than 0
        assertThat(listOfNums, everyItem(greaterThan(0)));






    }





}
