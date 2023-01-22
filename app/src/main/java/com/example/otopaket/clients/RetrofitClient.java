package com.example.otopaket.clients;

import com.example.otopaket.constants.BaseUrls;
import com.example.otopaket.dtos.BrandDto;
import com.example.otopaket.dtos.ServiceResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private Retrofit retrofit = null;
    private BrandService brandService;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder().baseUrl(BaseUrls.CarsApiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        brandService = retrofit.create(BrandService.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public BrandService getBrandService() {
        return brandService;
    }

    public  ModelService getModelService() {
        return  retrofit.create(ModelService.class);
    }

    public  VersionService getVersionService() {
        return  retrofit.create(VersionService.class);
    }
}

