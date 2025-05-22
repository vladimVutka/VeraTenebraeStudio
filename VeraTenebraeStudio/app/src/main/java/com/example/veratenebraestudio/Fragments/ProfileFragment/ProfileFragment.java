package com.example.veratenebraestudio.Fragments.ProfileFragment;

import com.example.veratenebraestudio.Network.ThisUser;
import com.example.veratenebraestudio.Projects.OpenProject;
import com.example.veratenebraestudio.Projects.ProjectAdapter;

import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.veratenebraestudio.R;
import com.example.veratenebraestudio.databinding.MainViewLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;

import DataFiles.ProjectEntity;
import DataFiles.UserEntity;

public class ProfileFragment extends Fragment implements OpenProject {

    private MainViewLayoutBinding binding;
    private ProjectAdapter adapter;
    private List<ProjectEntity> projectList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = MainViewLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle saveInstances)
    {
        //fixUI();
        addListToRecycle();
        super.onViewCreated(view, saveInstances);
        ThisUser thisUser = ThisUser.getInstance();

        UserEntity user = thisUser.getCurrentUser();
        binding.userName.setText(user.getUsername());
        Picasso.get().load(user.getImageurl()).into(binding.avatar);
        Log.d("pic", user.getImageurl());

        /*.getApiService().getUsers().enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(Call<List<UserEntity>> call, Response<List<UserEntity>> response) {
                if (response.isSuccessful()) {
                    List<UserEntity> users = response.body();

                }
            }

            @Override
            public void onFailure(Call<List<UserEntity>> call, Throwable t) {
                //Log.e("API", "Failed: " + t.getMessage());
            }
    });*/
    }

    private void addListToRecycle()
    {
        adapter = new ProjectAdapter(this);
        binding.rc.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rc.setAdapter(adapter);

        projectList.add(new ProjectEntity("Project 1", "Description 1", "Details 1"));
        adapter.submitList(projectList);
    }

        private void fixUI()
        {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        if (binding.scrollview != null) {
            //работаем с по идеи костылём в виде темно красного квадрата
            ViewGroup.LayoutParams params = binding.sw.getLayoutParams();
            params.height = metrics.heightPixels;
            params.width = metrics.widthPixels;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(binding.mainView);
            //инвалидка фона
            constraintSet.connect(binding.sw.getId(), ConstraintSet.TOP  , binding.fm.getId(), constraintSet.TOP, -15);
            //constraintSet.connect(binding.rc.getId(), ConstraintSet.TOP  , binding.fm.getId(), constraintSet.TOP, 35);

            //работа с карточкой профиля
            ViewGroup.LayoutParams userBackParams = binding.userBack.getLayoutParams();
            userBackParams.width = 150;
            binding.userBack.setLayoutParams(userBackParams);
            constraintSet.connect(binding.userBack.getId(), ConstraintSet.TOP, binding.backGround.getId(), ConstraintSet.TOP, 15);
            constraintSet.connect(binding.userBack.getId(), ConstraintSet.START, binding.backGround.getId(), ConstraintSet.START, 35);
            constraintSet.connect(binding.userBack.getId(), ConstraintSet.END, binding.backGround.getId(), ConstraintSet.END, 35);

            constraintSet.connect(binding.avatar.getId(), ConstraintSet.TOP, binding.userBack.getId(), ConstraintSet.TOP, 5);
            constraintSet.connect(binding.avatar.getId(), ConstraintSet.START, binding.userBack.getId(), ConstraintSet.START, 25);
            constraintSet.connect(binding.avatar.getId(), ConstraintSet.BOTTOM, binding.userBack.getId(), ConstraintSet.BOTTOM, 8);


            constraintSet.connect(binding.userName.getId(), ConstraintSet.TOP, binding.avatar.getId(), ConstraintSet.TOP, 5);
            constraintSet.connect(binding.userName.getId(), ConstraintSet.START, binding.avatar.getId(), ConstraintSet.END, 25);
            // constraintSet.connect(binding.userName.getId(), ConstraintSet.BOTTOM, binding.avatar.getId(), ConstraintSet.BOTTOM, 8);


            //ну времено засунем текст
            constraintSet.connect(binding.textView.getId(),  ConstraintSet.TOP, binding.fm.getId(), ConstraintSet.TOP, 0);
            //проверка работы
            //constraintSet.applyTo(binding.mainView);
            binding.mainView.requestLayout();
            binding.mainView.invalidate();
            Debug.logStack("f", String.format("%d", binding.scrollview.getLayoutParams().height), 5);

        }
        binding.textView.setText("Ghbdtn");
    }


    @Override
    public void goToProject(String username) {

    }
}
