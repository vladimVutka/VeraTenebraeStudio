package com.example.veratenebraestudio.Projects;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veratenebraestudio.databinding.CardLayoutBinding;
import com.squareup.picasso.Picasso;

import DataFiles.ProjectEntity;

public class ProjectAdapter extends ListAdapter<ProjectEntity, ProjectAdapter.ViewHolder> {



    private final OpenProject listener;

    public ProjectAdapter(OpenProject listener){
    super(new ProjectDiff());
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

        public void bind(ProjectEntity item) {
            binding.ProjectName.setText(item._projectName());
            binding.discription.setText(item._projectId());
            binding.discription.setOnClickListener(v -> {
                listener.goToProject(item._projectId());
            });
            Picasso.get().load("https://clck.ru/3MDBC5").into(binding.icon);
        }
    }
            public static class ProjectDiff extends DiffUtil.ItemCallback<ProjectEntity> {
                @Override
                public boolean areItemsTheSame(@NonNull ProjectEntity oldItem, @NonNull ProjectEntity newItem) {
                    return oldItem._projectName().equals(newItem._projectName());
                }

                @Override
                public boolean areContentsTheSame(@NonNull ProjectEntity oldItem, @NonNull ProjectEntity newItem) {
                    return true;
                }
            }
}
