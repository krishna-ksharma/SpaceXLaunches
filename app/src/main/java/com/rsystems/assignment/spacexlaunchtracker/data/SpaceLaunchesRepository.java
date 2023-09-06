package com.rsystems.assignment.spacexlaunchtracker.data;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.rsystems.assignment.spacexlaunchtracker.data.Result;
import com.rsystems.assignment.spacexlaunchtracker.data.local.SpaceDao;
import com.rsystems.assignment.spacexlaunchtracker.data.remote.DispatchGroup;
import com.rsystems.assignment.spacexlaunchtracker.data.remote.SpaceLaunchesApi;
import com.rsystems.assignment.spacexlaunchtracker.model.Launches;
import com.rsystems.assignment.spacexlaunchtracker.model.Rocket;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpaceLaunchesRepository {
    private SpaceLaunchesApi launchesApi;
    private SpaceDao spaceDao;

    @Inject
    public SpaceLaunchesRepository(SpaceLaunchesApi launchesApi, SpaceDao dao) {
        this.launchesApi = launchesApi;
        this.spaceDao = dao;
    }

    public void listSpaceLaunches(MutableLiveData<Result<List<Launches>>> liveData, boolean hardRefresh) {
        if (hardRefresh) {
            fetchItems(liveData);
        } else {
            DispatchGroup dbDispatcher = new DispatchGroup(() -> {
            });
            dbDispatcher.enter();
            AsyncTask.execute(() -> {
                List<Launches> items = spaceDao.listLaunches();
                if (items != null && !items.isEmpty()) {
                    liveData.postValue(Result.success(items));
                } else {
                    fetchItems(liveData);
                }
                dbDispatcher.leave();
            });
        }
    }

    private void fetchItems(MutableLiveData<Result<List<Launches>>> liveData) {
        launchesApi.listLaunches().enqueue(new Callback<List<Launches>>() {
            @Override
            public void onResponse(Call<List<Launches>> call, Response<List<Launches>> response) {
                if (response.isSuccessful()) {
                    List<Launches> items = response.body();
                    DispatchGroup dispatchGroup = new DispatchGroup(() -> {
                        DispatchGroup dbDispatcher = new DispatchGroup(() -> liveData.postValue(Result.success(spaceDao.listLaunches())));
                        dbDispatcher.enter(items.size());
                        items.forEach(item -> AsyncTask.execute(() -> {
                            spaceDao.insert(item);
                            dbDispatcher.leave();
                        }));
                    });
                    dispatchGroup.enter(items.size());
                    items.forEach(item -> launchesApi.getRocket(item.getRocket()).enqueue(new Callback<Rocket>() {
                        @Override
                        public void onResponse(Call<Rocket> call, Response<Rocket> response) {
                            item.setRocketModel(response.body());
                            dispatchGroup.leave();
                        }

                        @Override
                        public void onFailure(Call<Rocket> call, Throwable t) {
                            dispatchGroup.leave();
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<List<Launches>> call, Throwable t) {
                liveData.postValue(Result.error(t.getLocalizedMessage()));
            }
        });
    }
}

