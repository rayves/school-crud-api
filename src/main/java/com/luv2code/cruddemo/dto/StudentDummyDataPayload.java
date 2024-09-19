package com.luv2code.cruddemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StudentDummyDataPayload {
    @JsonProperty("name")
    private String fullName;
    private String email;

    public StudentDummyDataPayload() {
    }

    public StudentDummyDataPayload(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }
}
