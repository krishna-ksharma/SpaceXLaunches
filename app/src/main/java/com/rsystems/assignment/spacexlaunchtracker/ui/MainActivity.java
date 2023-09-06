package com.rsystems.assignment.spacexlaunchtracker.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import com.rsystems.assignment.spacexlaunchtracker.R;
import com.rsystems.assignment.spacexlaunchtracker.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private NavHostFragment navHostFragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupNavHostFragment();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            navHostFragment.getNavController().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavHostFragment() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        navHostFragment.getNavController().addOnDestinationChangedListener((controller, destination, arguments) -> {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(destination.getLabel());
                actionBar.setDisplayHomeAsUpEnabled(destination.getId() == R.id.spaceLauncheDetail);
            }
        });
    }
}
