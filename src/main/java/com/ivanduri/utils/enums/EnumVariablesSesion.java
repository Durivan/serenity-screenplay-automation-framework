package com.ivanduri.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumVariablesSesion {

    CREATE_USER_REQUEST_BODY("Payload of create user endpoint"),
    CREATE_USER_RESPONSE("Response of create user endpoint"),
    CREATE_USER_RESPONSE_NOT_DESERIALIZED("Response of create user endpoint not deserialized"),
    CONSULT_USER_RESPONSE("Response of consult user endpoint"),
    USER_ID("User id created"),
    UPDATE_USER_REQUEST_BODY("Payload of update user endpoint"),
    UPDATE_USER_RESPONSE("Response of update user endpoint");

    private final String variableSesion;
}
