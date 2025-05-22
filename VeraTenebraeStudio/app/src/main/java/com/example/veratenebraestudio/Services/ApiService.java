package com.example.veratenebraestudio.Services;

import com.example.veratenebraestudio.Network.ImageUploadResponse;

import DTOs.LoginDTO;
import DTOs.UserDTO;
import DataFiles.UserEntity;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApiService {
    @GET("users")
    Call<List<UserEntity>> getUsers();

    @POST("auth/login")
    Call<UserDTO> login(@Body LoginDTO loginDTO);

    @POST("users")
    Call<UserDTO> createUser(@Body UserDTO userDTO);

    @GET("users/{id}")
    Call<UserEntity> getUserById(@Path("id") Long id);

    @GET("users/username/{username}")
    Call<UserEntity> getUserByUsername(@Path("username") String username);

    @GET("users/email/{email}")
    Call<UserEntity> getUserByEmail(@Path("email") String email);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") Long id);

    @Multipart
    @POST("upload-image")
    Call<ImageUploadResponse> uploadImage(@Part MultipartBody.Part file);

    @POST("register")
    Call<UserDTO> registerUser(@Body UserDTO userDTO);
}