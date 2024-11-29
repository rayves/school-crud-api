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

    @JsonProperty("users")
    private List<TempTeacher> teachers;

    @Data
    public static class TempTeacher {
        private String firstName;
        private String lastName;
        private String email;
        private Company company;
    }

    @Data
    public static class Company {
        private String department;
    }

}
