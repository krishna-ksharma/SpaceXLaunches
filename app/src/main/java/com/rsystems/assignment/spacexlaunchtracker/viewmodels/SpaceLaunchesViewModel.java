package com.rsystems.assignment.spacexlaunchtracker.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rsystems.assignment.spacexlaunchtracker.data.Result;
import com.rsystems.assignment.spacexlaunchtracker.data.SpaceLaunchesRepository;
import com.rsystems.assignment.spacexlaunchtracker.model.Launches;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SpaceLaunchesViewModel extends ViewModel {
    private MutableLiveData<Result<List<Launches>>> spaceLaunchesLiveData;
    private MutableLiveData selectedSpaceLaunch = new MutableLiveData<Launches>();
    private SpaceLaunchesRepository repository;

    @Inject
    public SpaceLaunchesViewModel(SpaceLaunchesRepository repository) {
        this.repository = repository;
        spaceLaunchesLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Launches> getSelectedSpaceLaunch() {
        return selectedSpaceLaunch;
    }

    public LiveData<Result<List<Launches>>> listLaunches(boolean hardRefresh) {
        spaceLaunchesLiveData.postValue(Result.loading());
        repository.listSpaceLaunches(spaceLaunchesLiveData, hardRefresh);
        return spaceLaunchesLiveData;
    }

    public void setSpaceLaunch(Launches launches)  {
        selectedSpaceLaunch.postValue(launches);
    }

    public void setFabStatus(Launches launches)  {
        repository.updateFavStatus(launches.getId(), launches.isFavorite());
    }
}
