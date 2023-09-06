package com.rsystems.assignment.spacexlaunchtracker.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsystems.assignment.spacexlaunchtracker.R;
import com.rsystems.assignment.spacexlaunchtracker.databinding.ItemSpaceLaunchBinding;
import com.rsystems.assignment.spacexlaunchtracker.model.Launches;
import com.rsystems.assignment.spacexlaunchtracker.utils.DateUtils;
import com.rsystems.assignment.spacexlaunchtracker.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class SpaceLaunchesAdapter extends RecyclerView.Adapter<SpaceLaunchesAdapter.ViewHolder> {
    private List<Launches> spaceLaunches;
    private OnItemClickListener onClick;

    public SpaceLaunchesAdapter(OnItemClickListener onClick) {
        this.onClick = onClick;
        this.spaceLaunches = new ArrayList<>();
    }

    public void setData(List<Launches> launches) {
        spaceLaunches = launches;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Launches launch);

        void onFabItemClick(Launches launch);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemSpaceLaunchBinding binding;

        public ViewHolder(ItemSpaceLaunchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Launches item) {
            binding.missionName.setText(item.getName());
            binding.launchStatus.setText(item.isSuccess() ? R.string.launch_success : R.string.launch_failure);
            binding.rocketName.setText(item.getRocketModel().getName());
            binding.launchDate.setText(DateUtils.getDate(item.getDate_utc()));
            ImageLoader.loadThumbImage(item.getLinks().getPatch().getSmall(), binding.missionPatchImage);
            binding.favButton.setImageResource(item.isFavorite() ? R.drawable.ic_bookmark_filled : R.drawable.ic_bookmark);
            binding.favButton.setOnClickListener(v -> {
                if (onClick != null) {
                    item.setFavorite(!item.isFavorite());
                    notifyItemChanged(getAdapterPosition());
                    onClick.onFabItemClick(item);
                }
            });
            binding.getRoot().setOnClickListener(v -> {
                if (onClick != null) {
                    onClick.onItemClick(item);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSpaceLaunchBinding binding = ItemSpaceLaunchBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Launches rocket = spaceLaunches.get(position);
        holder.bind(rocket);
    }

    @Override
    public int getItemCount() {
        return spaceLaunches.size();
    }
}
