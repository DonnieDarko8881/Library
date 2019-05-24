package com.crud.library.domain;

public enum Status {
    LOST("LOST"), DESTROYED("DESTROYED"), GOOD("GOOD");

    private String status;

    Status(String status) {
        this.status = status;
    }
}
