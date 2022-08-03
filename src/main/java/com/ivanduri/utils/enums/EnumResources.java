package com.ivanduri.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumResources {

    CREATE_USER("/users"),
    GET_USER("/users/{userId}"),
    UPDATE_USER("/users/{userId}");

    private String resource;
}
