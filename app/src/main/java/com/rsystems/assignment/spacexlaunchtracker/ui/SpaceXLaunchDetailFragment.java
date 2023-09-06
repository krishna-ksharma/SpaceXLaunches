package com.rsystems.assignment.spacexlaunchtracker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rsystems.assignment.spacexlaunchtracker.utils.ImageLoader;
import com.rsystems.assignment.spacexlaunchtracker.R;
import com.rsystems.assignment.spacexlaunchtracker.databinding.FragmentSpaceLaunchDetailBinding;
import com.rsystems.assignment.spacexlaunchtracker.utils.DateUtils;
import com.rsystems.assignment.spacexlaunchtracker.viewmodels.SpaceLaunchesViewModel;

public class SpaceXLaunchDetailFragment extends Fragment {
    private FragmentSpaceLaunchDetailBinding binding;
    private SpaceLaunchesViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SpaceLaunchesViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSpaceLaunchDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getSelectedSpaceLaunch().observe(getViewLifecycleOwner(), spaceLaunch -> {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(spaceLaunch.getName());
            }
            binding.missionName.setText(getString(R.string.mission_name, spaceLaunch.getName()));
            binding.launchDate.setText(getString(R.string.launch_date, DateUtils.getDate(spaceLaunch.getDate_utc())));
            ImageLoader.loadThumbImage(spaceLaunch.getLinks().getPatch().getLarge(), binding.missionPatchImage);
            binding.launchStatus.setText(spaceLaunch.isSuccess() ? R.string.launch_details_status_success : R.string.launch_details_status_failed);
            binding.rocketName.setText(getString(R.string.rocket_details_name, spaceLaunch.getRocketModel().getName()));
            binding.rocketType.setText(getString(R.string.rocket_details_type, spaceLaunch.getRocketModel().getType()));
            binding.rocketDescription.setText(getString(R.string.rocket_details_description, spaceLaunch.getRocketModel().getDescription()));
            binding.missionDescription.setText(getString(R.string.mission_description, spaceLaunch.getDetails()));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
