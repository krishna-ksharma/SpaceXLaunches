package com.rsystems.assignment.spacexlaunchtracker.di;

import com.rsystems.assignment.spacexlaunchtracker.data.local.SpaceDao;
import com.rsystems.assignment.spacexlaunchtracker.data.remote.SpaceApiConstants;
import com.rsystems.assignment.spacexlaunchtracker.data.remote.SpaceLaunchesApi;
import com.rsystems.assignment.spacexlaunchtracker.data.SpaceLaunchesRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class SpaceApiModule {

    @Provides
    public SpaceLaunchesApi provideSpaceLaunchesApi(Retrofit.Builder retrofitBuilder, OkHttpClient okHttpClient) {
        return retrofitBuilder.client(okHttpClient).build().create(SpaceLaunchesApi.class);
    }

    @Provides
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       // interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient().newBuilder()/*.addInterceptor(interceptor)*/.build();
    }

    @Provides
    public Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(SpaceApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
    }

    @Provides
    public SpaceLaunchesRepository provideRepository(SpaceLaunchesApi launchesApi, SpaceDao dao) {
        return new SpaceLaunchesRepository(launchesApi, dao);
    }
}
