package com.cybertek.tests.pojo.zipcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Place {
    @JsonProperty("place name")
    private String placeName;

    private String longitude;

    private String state;

    @JsonProperty("state abbreviation")
    private String stateAbbreviation;

    private String latitude;
}
