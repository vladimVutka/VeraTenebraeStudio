package com.example.veratenebraestudio.Fragments.ProfileFragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class ProfileViewModel extends ViewModel {

    private final MutableLiveData<State> _state = new MutableLiveData<>();



    public abstract static class State{
        public static final class Loading extends State{
            @Override
            public String toString(){
                return "Loading";
            }
        }

        public static final class Show extends State{

        }

        public static final class Error extends State{

        }
    }




}
