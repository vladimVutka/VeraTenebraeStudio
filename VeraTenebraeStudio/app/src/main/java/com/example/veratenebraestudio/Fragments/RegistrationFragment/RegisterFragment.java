package com.example.veratenebraestudio.Fragments.RegistrationFragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.example.veratenebraestudio.Fragments.LoginFragment.LoginFragment;
import com.example.veratenebraestudio.Network.ThisUser;
import com.example.veratenebraestudio.R;
import com.example.veratenebraestudio.Services.ApiService;
import com.example.veratenebraestudio.Services.RetrofitClient;
import com.example.veratenebraestudio.databinding.RegistrationLayoutBinding;

import DTOs.UserDTO;
import DataFiles.UserEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    private RegistrationLayoutBinding binding;

    private ApiService apiService = RetrofitClient.getApiService();

    private int canOpenNextPage = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup conteiner, Bundle savedInstanceState){
        binding = RegistrationLayoutBinding.inflate(inflater, conteiner, false);
        return binding.getRoot();
    }

    public void onViewCreated(View view, Bundle saveInstance){
        fixUI();


        View.OnClickListener onClickReg = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.inputLogin.getText() != null){
                    apiService.getUserByUsername(binding.inputLogin.getText().toString()).enqueue(new Callback<UserEntity>() {
                        @Override
                        public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                            if(response.isSuccessful()){
                                Debug.logStack("login","we have", 1);
                                Toast.makeText(getContext(), "Пользователь с таким логином уже существует", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                if(binding.inputEmail.getText() != null && binding.inputPassword.getText() != null){
                                    apiService.getUserByEmail(binding.inputEmail.getText().toString()).enqueue(new Callback<UserEntity>() {
                                        @Override
                                        public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                                            if(response.isSuccessful()){
                                                Toast.makeText(getContext(), "Пользователь с такой почтой уже существует", Toast.LENGTH_SHORT).show();
                                                Debug.logStack("email","we have", 1);
                                            }
                                            else{
                                                UserDTO userDTO = new UserDTO(binding.inputLogin.getText().toString(), binding.inputEmail.getText().toString(), binding.inputPassword.getText().toString());
                                                ThisUser user = ThisUser.getInstance();
                                                user.setCurrentUserDTO(userDTO);
                                                getParentFragmentManager()
                                                        .beginTransaction()
                                                        .replace(R.id.register_main_layout, new RegistrationSecondFragment())
                                                        .addToBackStack(null)
                                                        .commit();
                                            }
                                        }


                                        @Override
                                        public void onFailure(Call<UserEntity> call, Throwable t) {
                                            Debug.logStack("ddeer", "deded0", 1);
                                        }
                                    });
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<UserEntity> call, Throwable t) {
                            Debug.logStack("ddeer", "deded0", 1);

                        }
                    });
                }
                else{

                }


            }
        };
        binding.regText.setOnClickListener(onClickReg);

        View.OnClickListener onClickLogin = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.register_main_layout, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }
        };
        binding.vhod.setOnClickListener(onClickLogin);


    }

    private void fixUI(){
//        ConstraintSet constraintSet = new ConstraintSet();
//        constraintSet.clone(binding.registerMainLayout);
//        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        //
//        constraintSet.connect(binding.veraTenebrae.getId(), ConstraintSet.TOP, binding.registerMainLayout.getId(), constraintSet.TOP, 125);
//        constraintSet.connect(binding.veraTenebrae.getId(), ConstraintSet.START, binding.registerMainLayout.getId(), constraintSet.START, displayMetrics.widthPixels/8);
//        constraintSet.connect(binding.veraTenebrae.getId(), ConstraintSet.END, binding.registerMainLayout.getId(), constraintSet.END, displayMetrics.widthPixels/8);
//        //
//        constraintSet.connect(binding.codeImage.getId(), ConstraintSet.TOP, binding.registerMainLayout.getId(), ConstraintSet.TOP, 320);
//        constraintSet.connect(binding.codeImage.getId(), ConstraintSet.START, binding.registerMainLayout.getId(), ConstraintSet.START, 0);
//        constraintSet.connect(binding.codeImage.getId(), ConstraintSet.BOTTOM, binding.registerMainLayout.getId(), ConstraintSet.BOTTOM, 0);
//        //
//        constraintSet.connect(binding.regBackField.getId(), ConstraintSet.TOP,binding.codeImage.getId(), ConstraintSet.TOP, 120);
//        constraintSet.connect(binding.regBackField.getId(), ConstraintSet.START,binding.codeImage.getId(), ConstraintSet.START, displayMetrics.widthPixels/24);
//        constraintSet.connect(binding.regBackField.getId(), ConstraintSet.END,binding.codeImage.getId(), ConstraintSet.END, displayMetrics.widthPixels/24);
//        //
//        constraintSet.connect(binding.vhod.getId(), ConstraintSet.START, binding.regBackField.getId(), ConstraintSet.START, 52);
//        constraintSet.connect(binding.vhod.getId(), ConstraintSet.END, binding.regBackField.getId(), ConstraintSet.END, 486);
//        constraintSet.connect(binding.vhod.getId(), ConstraintSet.TOP, binding.regBackField.getId(), ConstraintSet.TOP, 54);
//        constraintSet.connect(binding.vhod.getId(), ConstraintSet.BOTTOM, binding.regBackField.getId(), ConstraintSet.BOTTOM, 602);
//        //
//        constraintSet.connect(binding.register.getId(), ConstraintSet.START, binding.regBackField.getId(), ConstraintSet.START, 286);
//        constraintSet.connect(binding.register.getId(), ConstraintSet.END, binding.regBackField.getId(), ConstraintSet.END, 72);
//        constraintSet.connect(binding.register.getId(), ConstraintSet.TOP, binding.regBackField.getId(), ConstraintSet.TOP, 54);
//        constraintSet.connect(binding.register.getId(), ConstraintSet.BOTTOM, binding.regBackField.getId(), ConstraintSet.BOTTOM, 602);
//        //
//        constraintSet.connect(binding.inputLogin.getId(), ConstraintSet.TOP, binding.regBackField.getId(), constraintSet.TOP, 185);
//        constraintSet.connect(binding.inputLogin.getId(), ConstraintSet.START, binding.regBackField.getId(), constraintSet.START, 25);
//        constraintSet.connect(binding.inputLogin.getId(), ConstraintSet.END, binding.regBackField.getId(), constraintSet.END, 25);
//
//        constraintSet.connect(binding.inputEmail.getId(), ConstraintSet.TOP, binding.regBackField.getId(), constraintSet.TOP, 315);
//        constraintSet.connect(binding.inputEmail.getId(), ConstraintSet.START, binding.regBackField.getId(), constraintSet.START, 25);
//        constraintSet.connect(binding.inputEmail.getId(), ConstraintSet.END, binding.regBackField.getId(), constraintSet.END, 25);
//
//        constraintSet.connect(binding.inputPassword.getId(), ConstraintSet.TOP, binding.regBackField.getId(), constraintSet.TOP, 445);
//        constraintSet.connect(binding.inputPassword.getId(), ConstraintSet.START, binding.regBackField.getId(), constraintSet.START, 25);
//        constraintSet.connect(binding.inputPassword.getId(), ConstraintSet.END, binding.regBackField.getId(), constraintSet.END, 25);
//        //
//        constraintSet.connect(binding.regButton.getId(), ConstraintSet.TOP, binding.regBackField.getId(), constraintSet.TOP, 575);
//        constraintSet.connect(binding.regButton.getId(), ConstraintSet.START, binding.regBackField.getId(), constraintSet.START, 25);
//        constraintSet.connect(binding.regButton.getId(), ConstraintSet.END, binding.regBackField.getId(), constraintSet.END, 25);
//        //
//        constraintSet.connect(binding.regText.getId(), ConstraintSet.TOP, binding.regButton.getId(), constraintSet.TOP, 15);
//        constraintSet.connect(binding.regText.getId(), ConstraintSet.START, binding.regButton.getId(), constraintSet.START, 75);
//        constraintSet.connect(binding.regText.getId(), ConstraintSet.END, binding.regButton.getId(), constraintSet.END, 75);
//        constraintSet.connect(binding.regText.getId(), ConstraintSet.BOTTOM, binding.regButton.getId(), constraintSet.BOTTOM, 15);
//
//        constraintSet.applyTo(binding.registerMainLayout);
    }

}
