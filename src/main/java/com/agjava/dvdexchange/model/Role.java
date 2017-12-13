package com.agjava.dvdexchange.model;

public enum Role {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private String value;

    Role(String role) {
        this.value = role;
    }

    public String getValue() {
        return value;
    }
}
