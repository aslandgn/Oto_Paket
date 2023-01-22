package com.example.otopaket.clients;

import com.example.otopaket.dtos.ModelDto;
import com.example.otopaket.dtos.ServiceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ModelService {

    @GET("/api/model/GetModelsOfBrand")
    Call<ServiceResponse<List<ModelDto>>> getModelsOfBrand(@Query("brandid")String id);
}
