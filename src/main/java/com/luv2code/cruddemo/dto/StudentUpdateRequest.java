package com.luv2code.cruddemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
}
