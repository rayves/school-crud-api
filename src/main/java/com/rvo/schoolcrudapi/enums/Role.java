package com.rvo.schoolcrudapi.enums;

public enum Role {
    TEACHER, STUDENT, ADMIN;

    public String getRole() {
        return "ROLE_" + this.name();
    }
}
