package com.ivanduri.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("first_name")
    private String first_name;

    @JsonProperty("last_name")
    private String last_name;

    @JsonProperty("avatar")
    private String avatar;

}
