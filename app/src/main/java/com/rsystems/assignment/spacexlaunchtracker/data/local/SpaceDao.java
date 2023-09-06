package com.rsystems.assignment.spacexlaunchtracker.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.rsystems.assignment.spacexlaunchtracker.model.Launches;

import java.util.List;

@Dao
public interface SpaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Launches item);

    @Query("SELECT * FROM launches")
    List<Launches> listLaunches();

    @Query("UPDATE launches SET isFavorite = :status WHERE id =:launchId")
    void updateFavStatus(String launchId, boolean status);
}
