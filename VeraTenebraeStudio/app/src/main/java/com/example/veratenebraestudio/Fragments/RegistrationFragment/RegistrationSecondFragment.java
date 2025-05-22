package com.example.veratenebraestudio.Fragments.RegistrationFragment;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.example.veratenebraestudio.Fragments.LoginFragment.LoginFragment;
import com.example.veratenebraestudio.Network.ThisUser;
import com.example.veratenebraestudio.R;
import com.example.veratenebraestudio.Services.ApiService;
import com.example.veratenebraestudio.Services.RetrofitClient;
import com.example.veratenebraestudio.databinding.RegistrationLayout2Binding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import DTOs.UserDTO;
import DataFiles.UserEntity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationSecondFragment extends Fragment implements OpenLogin {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "ImgBBUpload";
    private static final String API_KEY = "8d9d6dcd4108c3750075243dec4f09c1";
    private static final String BASE_URL = "https://api.imgbb.com/1/";

    private UserDTO user;
    private ThisUser thisUser;
    private Uri imageUri;
    private RegistrationLayout2Binding binding;
    private ImgbbApi imgbbApi;

    private ApiService apiService = RetrofitClient.getApiService();

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = RegistrationLayout2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override

    public void onViewCreated(View view, Bundle savedInstance) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        imgbbApi = retrofit.create(ImgbbApi.class);

        binding.imageChooseButton.setOnClickListener(v -> openImageChooser());
        //setUIElements();
        binding.registerButton.setOnClickListener(v -> register());
        binding.registerText.setOnClickListener(v -> register());
    }

    private void register(){
        if(binding.inputFirstname.getText() != null && binding.inputLastname.getText() != null && user != null){
            user.setFirstname(binding.inputFirstname.getText().toString());
            user.setLastname(binding.inputLastname.getText().toString());
            user.setMiddlename(binding.inputMiddlename.getText().toString());

            apiService.createUser(user).enqueue(new Callback<UserDTO>() {
                @Override
                public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                    Log.d("API1", "User created: " + response.body());
                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(R.id.register_main_layout, new LoginFragment())
                            .addToBackStack(null)
                            .commit();
                }

                @Override
                public void onFailure(Call<UserDTO> call, Throwable t) {
                    Log.e("API1", "Failed: " + t.getMessage());
                }
            });
        }
        else{
            Toast.makeText(getContext(), "Необходимо заполнить все поля!", Toast.LENGTH_SHORT).show();
        }

    }
    @Override

    public void openLogin(){
        if(binding.inputFirstname.getText() != null && binding.inputLastname.getText() != null && user != null){
            register();
        }
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }


    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);
        if (requestCode != PICK_IMAGE_REQUEST) return;
        if (resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                imageUri = data.getData();
                try {
                    Bitmap squareBitmap = getSquareBitmap(imageUri);
                    binding.imageChooseButton.setImageBitmap(squareBitmap);
                    uploadImageToImgBB(squareBitmap);
                } catch (Exception e) {
                    Log.e(TAG, "Ошибка обработки изображения", e);
                    Toast.makeText(getContext(), "Ошибка обработки изображения", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Изображение не выбрано", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == RESULT_CANCELED) {
            Log.d(TAG, "Пользователь отменил выбор изображения");
        } else {
            Toast.makeText(getContext(), "Ошибка выбора", Toast.LENGTH_SHORT).show();
        }
    }


    private Bitmap getSquareBitmap(Uri imageUri) throws IOException {

        InputStream inputStream = getContext().getContentResolver().openInputStream(imageUri);
        Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream);
        inputStream.close();

        int size = Math.min(originalBitmap.getWidth(), originalBitmap.getHeight());
        int x = (originalBitmap.getWidth() - size) / 2;
        int y = (originalBitmap.getHeight() - size) / 2;

        Bitmap squareBitmap = Bitmap.createBitmap(originalBitmap, x, y, size, size);
        originalBitmap.recycle();
        return squareBitmap;

    }


    private void uploadImageToImgBB(Bitmap bitmap) {

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            String encodedImage = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);

            Call<ImgBBResponse> call = imgbbApi.uploadImage(API_KEY, encodedImage);
            call.enqueue(new Callback<ImgBBResponse>() {
                @Override

                public void onResponse(Call<ImgBBResponse> call, Response<ImgBBResponse> response) {

                    if (response.isSuccessful() && response.body() != null) {

                        String imageUrl = response.body().getData().getUrl();

                        Log.d(TAG, "Изображение успешно загружено: " + imageUrl);

                        Toast.makeText(getContext(), "Изображение успешно загружено", Toast.LENGTH_SHORT).show();

                        saveImageUrl(imageUrl);

                    } else {

                        Log.e(TAG, "Ошибка загрузки: " + response.message());

                        Toast.makeText(getContext(), "Ошибка загрузки: " + response.message(), Toast.LENGTH_SHORT).show();

                    }

                }


                @Override

                public void onFailure(Call<ImgBBResponse> call, Throwable t) {

                    Log.e(TAG, "Ошибка загрузки", t);

                    Toast.makeText(getContext(), "Ошибка загрузки: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                }

            });

        } catch (Exception e) {

            Log.e(TAG, "Ошибка загрузки", e);

            Toast.makeText(getContext(), "Ошибка загрузки", Toast.LENGTH_SHORT).show();

        }

    }


    private void saveImageUrl(String url) {
        thisUser = ThisUser.getInstance();
        user = thisUser.getCurrentUserDTO();
        user.setImageUrl(url);
    }

}

interface ImgbbApi {
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST("upload")
    Call<ImgBBResponse> uploadImage(
            @retrofit2.http.Field("key") String key,
            @retrofit2.http.Field("image") String imageBase64
    );
}

class ImgBBResponse {
    private ImgBBData data;

    public ImgBBData getData() {

        return data;

    }


    static class ImgBBData {

        private String url;


        public String getUrl() {

            return url;

        }

    }

}