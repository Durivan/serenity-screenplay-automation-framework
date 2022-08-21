package com.ivanduri.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumConstants {

    ACCESS_TOKEN("access.token"),
    API_BASE_URL("api.base.url"),
    APPLICATION_JSON("application/json"),
    AUTHORIZATION("Authorization"),
    BASE_URL("base.url"),
    CONTENT_TYPE("Content-Type"),
    USERID("userId");

    private String constantValue;
}
