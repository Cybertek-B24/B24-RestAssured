package com.cybertek.tests.day08_hamcrest_post;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @Test
    public void hamcrestTest() {
        //numbers
        assertThat(4 + 3, equalTo(7));
        assertThat(11 + 23 , is(34));
        assertThat(5 + 2 , is(equalTo(7)));

        assertThat(5 + 5 , not(8));
        assertThat(10 + 3, is(greaterThan(11)));

        //String assertions
        String str = "B24 - wooden spoon";
        assertThat(str , equalTo("B24 - wooden spoon"));
        assertThat(str, startsWith("B24"));
        assertThat(str, endsWith("spoon"));
        assertThat(str, containsStringIgnoringCase("WOODEN"));

        str= "";
        assertThat(str, emptyString());

        //list examples
        List<Integer> nums = Arrays.asList(3, 1, 6, 9, 10, 55);

        assertThat(nums , hasSize(6)); //check size
        assertThat(nums, hasItem(10)); //check if contains 10

        assertThat(nums, hasItems(1, 3, 55));
        assertThat(nums, everyItem(lessThan(100)));

    }

}
