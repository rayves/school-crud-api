package com.luv2code.cruddemo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StudentDTO {
    @JsonProperty("name")
    private String fullName;
    private String email;

    public StudentDTO() {
    }

    public StudentDTO(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }
}
