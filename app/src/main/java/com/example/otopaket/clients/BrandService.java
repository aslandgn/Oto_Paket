package com.example.otopaket.clients;

import com.example.otopaket.dtos.BrandDto;
import com.example.otopaket.dtos.ServiceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BrandService {
    @GET("/api/brand")
    Call<ServiceResponse<BrandDto>> getBrand(@Query("id")String id);
    @GET("/api/brand/GetBrands")
    Call<ServiceResponse<List<BrandDto>>> getBrands();
}
