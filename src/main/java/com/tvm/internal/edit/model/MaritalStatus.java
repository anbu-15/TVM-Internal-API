package com.tvm.internal.edit.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MaritalStatus {
    SINGLE, MARRIED, DIVORCED;

    @JsonValue
    public String toLowerCase() {
        return this.name().toLowerCase();
    }
    @JsonCreator
    public static MaritalStatus fromValue(String value) {
        return MaritalStatus.valueOf(value.toUpperCase());
    }
}
