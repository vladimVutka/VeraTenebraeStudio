package com.example.veratenebraestudio.Fragments.ProfileFragment;

import java.util.List;

import DataFiles.UserEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetProjectsData
{
    @GET("users")
    Call<List<UserEntity>> gerUsers();
    @POST("users")
    Call<UserEntity> createUser(@Body UserEntity user);
}
