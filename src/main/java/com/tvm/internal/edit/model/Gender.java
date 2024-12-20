package com.tvm.internal.edit.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE, FEMALE;

    @JsonValue
    public String toLowerCase() {
        return this.name().toLowerCase();
    }

    @JsonCreator
    public static Gender fromValue(String value) {
        return Gender.valueOf(value.toUpperCase());
    }
}
