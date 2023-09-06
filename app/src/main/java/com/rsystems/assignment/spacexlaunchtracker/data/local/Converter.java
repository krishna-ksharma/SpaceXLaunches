package com.rsystems.assignment.spacexlaunchtracker.data.local;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rsystems.assignment.spacexlaunchtracker.model.Links;
import com.rsystems.assignment.spacexlaunchtracker.model.Rocket;

public class Converter {
    private Gson gson = new Gson();

    @TypeConverter
    public String fromLinks(Links links) {
        return gson.toJson(links);
    }

    @TypeConverter
    public Links toLinks(String value) {
        return gson.fromJson(value, new TypeToken<Links>() {
        }.getType());
    }

    @TypeConverter
    public String fromRocket(Rocket rocket) {
        return gson.toJson(rocket);
    }

    @TypeConverter
    public Rocket toRocket(String value) {
        return gson.fromJson(value, new TypeToken<Rocket>() {
        }.getType());
    }
}
