package com.rsystems.assignment.spacexlaunchtracker.data.remote;

import com.rsystems.assignment.spacexlaunchtracker.model.Launches;
import com.rsystems.assignment.spacexlaunchtracker.model.Rocket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SpaceLaunchesApi {
    @GET(SpaceApiConstants.LAUNCHES_API_ENDPOINT)
    Call<List<Launches>> listLaunches();

    @GET(SpaceApiConstants.ROCKET_DETAIL_API_ENDPOINT)
    Call<Rocket> getRocket(@Path("id") String id);
}
