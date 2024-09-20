package com.rvo.schoolcrudapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDummyDataPayload {

    @JsonProperty("name")
    private String fullName;
    private String email;
    private List<String> subjects;
}
