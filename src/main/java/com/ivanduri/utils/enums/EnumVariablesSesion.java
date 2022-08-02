package com.ivanduri.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumVariablesSesion {

    CREATE_USER_REQUEST_BODY("Payload of create user endpoint"),
    CREATE_USER_RESPONSE("Response of create user endpoint"),
    CONSULT_USER_RESPONSE("Response of consult user endpoint");

    private final String variableSesion;
}
