package com.example.veratenebraestudio.Fragments.LoginFragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.example.veratenebraestudio.Fragments.ProfileFragment.ProfileFragment;
import com.example.veratenebraestudio.Fragments.RegistrationFragment.RegisterFragment;
import com.example.veratenebraestudio.Network.ThisUser;
import com.example.veratenebraestudio.R;
import com.example.veratenebraestudio.Services.ApiService;
import com.example.veratenebraestudio.Services.RetrofitClient;
import com.example.veratenebraestudio.adapters.Users.OpenProfile;
import com.example.veratenebraestudio.databinding.LoginLayoutBinding;

import DTOs.LoginDTO;
import DTOs.UserDTO;
import DataFiles.UserEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements OpenProfile
{
    private LoginLayoutBinding binding;

    private ApiService apiService = RetrofitClient.getApiService();

    private String usernael;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = LoginLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(View view, Bundle saveInstance){
        //setUIElements();
        View.OnClickListener onClickRegister = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.login_layout, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        };
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = binding.inputPassword.getText().toString();
                String login = binding.inputLogin.getText().toString();
                goToProfile(login, password);
            }
        };
        binding.loginButton.setOnClickListener(onClickListener);
        binding.loginText.setOnClickListener(onClickListener);
        binding.register.setOnClickListener(onClickRegister);

        super.onViewCreated(view, saveInstance);
    }


    private void setUIElements(){
        ConstraintSet constraintSet = new ConstraintSet();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        constraintSet.clone(binding.loginLayout);
        //Фон с кодом
        constraintSet.connect(binding.codeImage.getId(), ConstraintSet.TOP, binding.loginLayout.getId(), ConstraintSet.TOP, 320);
        constraintSet.connect(binding.codeImage.getId(), ConstraintSet.START, binding.loginLayout.getId(), ConstraintSet.START, 0);
        constraintSet.connect(binding.codeImage.getId(), ConstraintSet.BOTTOM, binding.loginLayout.getId(), ConstraintSet.BOTTOM, 0);
        // Область с фоном ввода
        constraintSet.connect(binding.loginFieldView.getId(), ConstraintSet.TOP,binding.codeImage.getId(), ConstraintSet.TOP, 120);
        constraintSet.connect(binding.loginFieldView.getId(), ConstraintSet.START,binding.codeImage.getId(), ConstraintSet.START, displayMetrics.widthPixels/24);
        constraintSet.connect(binding.loginFieldView.getId(), ConstraintSet.END,binding.codeImage.getId(), ConstraintSet.END, displayMetrics.widthPixels/24);
        //Заголовок
        constraintSet.connect(binding.veraTenebrae.getId(), ConstraintSet.TOP, binding.loginLayout.getId(), constraintSet.TOP, 125);
        constraintSet.connect(binding.veraTenebrae.getId(), ConstraintSet.START, binding.loginLayout.getId(), constraintSet.START, displayMetrics.widthPixels/8);
        constraintSet.connect(binding.veraTenebrae.getId(), ConstraintSet.END, binding.loginLayout.getId(), constraintSet.END, displayMetrics.widthPixels/8);
        // Ввод данных
        constraintSet.connect(binding.inputLogin.getId(), ConstraintSet.TOP, binding.loginFieldView.getId(), constraintSet.TOP, 185);
        constraintSet.connect(binding.inputLogin.getId(), ConstraintSet.START, binding.loginFieldView.getId(), constraintSet.START, 25);
        constraintSet.connect(binding.inputLogin.getId(), ConstraintSet.END, binding.loginFieldView.getId(), constraintSet.END, 25);

        constraintSet.connect(binding.inputPassword.getId(), ConstraintSet.TOP, binding.loginFieldView.getId(), constraintSet.TOP, 315);
        constraintSet.connect(binding.inputPassword.getId(), ConstraintSet.START, binding.loginFieldView.getId(), constraintSet.START, 25);
        constraintSet.connect(binding.inputPassword.getId(), ConstraintSet.END, binding.loginFieldView.getId(), constraintSet.END, 25);
        //Кнопка входа
        constraintSet.connect(binding.loginButton.getId(), ConstraintSet.TOP, binding.loginFieldView.getId(), constraintSet.TOP, 445);
        constraintSet.connect(binding.loginButton.getId(), ConstraintSet.START, binding.loginFieldView.getId(), constraintSet.START, 25);
        constraintSet.connect(binding.loginButton.getId(), ConstraintSet.END, binding.loginFieldView.getId(), constraintSet.END, 25);
        //
        constraintSet.connect(binding.loginText.getId(), ConstraintSet.TOP, binding.loginButton.getId(), constraintSet.TOP, 15);
        constraintSet.connect(binding.loginText.getId(), ConstraintSet.START, binding.loginButton.getId(), constraintSet.START, 75);
        constraintSet.connect(binding.loginText.getId(), ConstraintSet.END, binding.loginButton.getId(), constraintSet.END, 75);
        constraintSet.connect(binding.loginText.getId(), ConstraintSet.BOTTOM, binding.loginButton.getId(), constraintSet.BOTTOM, 15);
        //
        constraintSet.connect(binding.vhod.getId(), ConstraintSet.START, binding.loginFieldView.getId(), ConstraintSet.START, 52);
        constraintSet.connect(binding.vhod.getId(), ConstraintSet.END, binding.loginFieldView.getId(), ConstraintSet.END, 486);
        constraintSet.connect(binding.vhod.getId(), ConstraintSet.TOP, binding.loginFieldView.getId(), ConstraintSet.TOP, 54);
        constraintSet.connect(binding.vhod.getId(), ConstraintSet.BOTTOM, binding.loginFieldView.getId(), ConstraintSet.BOTTOM, 495);
        //
        constraintSet.connect(binding.register.getId(), ConstraintSet.START, binding.loginFieldView.getId(), ConstraintSet.START, 286);
        constraintSet.connect(binding.register.getId(), ConstraintSet.END, binding.loginFieldView.getId(), ConstraintSet.END, 72);
        constraintSet.connect(binding.register.getId(), ConstraintSet.TOP, binding.loginFieldView.getId(), ConstraintSet.TOP, 54);
        constraintSet.connect(binding.register.getId(), ConstraintSet.BOTTOM, binding.loginFieldView.getId(), ConstraintSet.BOTTOM, 495);

        Debug.logStack("disp","" + displayMetrics.widthPixels/12, 1);//constraintSet.applyTo(binding.loginLayout);
    }


    public void goToProfile(String login, String password){

        Debug.logStack("gyu", "3", 1);
        LoginDTO loginDTO = new LoginDTO(login, password);
        Call<UserDTO> call = apiService.login(loginDTO);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if(response.isSuccessful()){
                    UserDTO user = response.body();
                    Debug.logStack("gyu", "yes", 1);
                    ThisUser thisUser = ThisUser.getInstance();
                    UserEntity userEntity = user.toUserEntity();
                    thisUser.setCurrentUser(userEntity);
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.login_layout, new ProfileFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Debug.logStack("gyu", "no", 1);
            }
        });
    }
}
