package com.rsystems.assignment.spacexlaunchtracker.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.rsystems.assignment.spacexlaunchtracker.model.Launches;

@Database(version = 1, entities = {Launches.class}, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class SpaceRoomDatabase extends RoomDatabase {
    public abstract SpaceDao spaceDao();
}
