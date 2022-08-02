package com.ivanduri.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumResources {

    USERS("/users");

    private String resource;
}
