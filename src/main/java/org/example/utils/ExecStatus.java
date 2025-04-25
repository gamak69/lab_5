package org.example.utils;

import lombok.Getter;

import java.io.Serializable;
@Getter
public class ExecStatus implements Serializable {
    private final boolean isOk;
    private final String message;

    public ExecStatus(boolean isOk, String message) {
        this.isOk = isOk;
        this.message = message;
    }

    public ExecStatus(String message) {
        this(true, message);
    }

    @Override
    public String toString() {
        return message;
    }
}