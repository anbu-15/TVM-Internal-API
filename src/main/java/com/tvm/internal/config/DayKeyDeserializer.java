package com.tvm.internal.config;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.tvm.internal.edit.model.Day;

import java.io.IOException;

public class DayKeyDeserializer extends KeyDeserializer {

    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        // Convert the input string to the corresponding enum value
        return Day.valueOf(key.toUpperCase()); // Convert "Monday" to "MONDAY"
    }
}