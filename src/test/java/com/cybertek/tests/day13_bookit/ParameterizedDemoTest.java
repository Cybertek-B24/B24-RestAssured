package com.cybertek.tests.day13_bookit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ParameterizedDemoTest {

    public static List<String> getFriendNames() {
        List<String> friendNames = Arrays.asList("Kalys", "Tach", "Dzhamilya","Naserahmad", "Fahima", "Jama","Meeer");
        return friendNames;
    }

    @ParameterizedTest
    @MethodSource("getFriendNames")
    public void friendNamesTest(String name) {
        System.out.println("name = " + name);
    }

    public static List<Map<String, String>> getAllUserCredentials() {

    }



}
