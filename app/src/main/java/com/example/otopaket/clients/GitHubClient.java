package com.example.otopaket.clients;

import com.example.otopaket.dtos.BrandDto;
import com.example.otopaket.dtos.GithubRepo;
import com.example.otopaket.dtos.ServiceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubClient {
    @GET("/users/{user}/repos")
    Call<List<GithubRepo>> reposForUser(
            @Path("user") String user
    );
    @GET("/api/brand?")
    Call<ServiceResponse> getBrand(@Query("id")int data);
}