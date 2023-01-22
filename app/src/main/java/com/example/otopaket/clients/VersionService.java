package com.example.otopaket.clients;

import com.example.otopaket.dtos.ServiceResponse;
import com.example.otopaket.dtos.VersionDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VersionService {
    @GET("/api/Version/GetVersionsOfModel")
    Call<ServiceResponse<List<VersionDto>>> GetVersionsOfModel(@Query("modelId")int modelId);
}
