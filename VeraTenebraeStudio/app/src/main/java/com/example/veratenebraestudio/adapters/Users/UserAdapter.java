package com.example.veratenebraestudio.adapters.Users;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veratenebraestudio.Projects.OpenProject;
import com.example.veratenebraestudio.databinding.CardLayoutBinding;

import DataFiles.UserEntity;

public class UserAdapter extends ListAdapter<UserEntity, UserAdapter.ViewHolder> {
    private final OpenProject listener;

    public UserAdapter(OpenProject listener) {
        super(new UserDiff());
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardLayoutBinding binding = CardLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardLayoutBinding binding;
        private final OpenProject listener;

        public ViewHolder(CardLayoutBinding binding, OpenProject listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        public void bind(UserEntity item) {
           // binding.title.setText(item.getUsername());
            binding.discription.setOnClickListener(v -> {
                listener.goToProject(item.getUsername());
            });
        }
    }

    public static class UserDiff extends DiffUtil.ItemCallback<UserEntity> {
        @Override
        public boolean areItemsTheSame(@NonNull UserEntity oldItem, @NonNull UserEntity newItem) {
            return oldItem.getUsername().equals(newItem.getUsername());
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserEntity oldItem, @NonNull UserEntity newItem) {
            return true;
        }
    }
}