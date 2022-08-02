package com.ivanduri.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserByIdResponse {

    @JsonProperty("data")
    private Data data;

    @JsonProperty("support")
    private Support support;
}
