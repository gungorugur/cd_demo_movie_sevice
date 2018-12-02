package com.ugungor.movie.interfaces;

import com.google.gson.GsonBuilder;

public class ApiError {

    private final String error;

    private ApiError(String error) {
        this.error = error;
    }

    public static ApiError create(String error) {
        return new ApiError(error);
    }

    public String toJson() {
        return new GsonBuilder().create().toJson(this);
    }
}
