package com.example.otopaket.dtos;

import com.google.gson.annotations.SerializedName;

public class ServiceResponse<T> {
    @SerializedName("statusCode")
    public int StatusCode;
    @SerializedName("exceptionMessage")
    public String ExceptionMessage;
    @SerializedName("data")
    public T Data;
}
