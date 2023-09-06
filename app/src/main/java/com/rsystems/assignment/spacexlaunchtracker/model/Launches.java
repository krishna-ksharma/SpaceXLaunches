package com.rsystems.assignment.spacexlaunchtracker.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "launches")
public class Launches {
    private boolean isFavorite;
    private boolean auto_update;
    private String date_local;
    private String date_precision;
    private int date_unix;
    private String date_utc;
    private String details;
    private int flight_number;
    @PrimaryKey
    @NonNull
    private String id;
    private String launchpad;
    private Links links;
    private String name;
    private boolean net;
    private String rocket;
    private String static_fire_date_utc;
    private boolean success;
    private boolean tbd;
    private boolean upcoming;
    private int window;

    private Rocket rocketModel;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Rocket getRocketModel() {
        return rocketModel;
    }

    public void setRocketModel(Rocket rocketModel) {
        this.rocketModel = rocketModel;
    }

    public boolean isAuto_update() {
        return auto_update;
    }

    public void setAuto_update(boolean auto_update) {
        this.auto_update = auto_update;
    }

    public String getDate_local() {
        return date_local;
    }

    public void setDate_local(String date_local) {
        this.date_local = date_local;
    }

    public String getDate_precision() {
        return date_precision;
    }

    public void setDate_precision(String date_precision) {
        this.date_precision = date_precision;
    }

    public int getDate_unix() {
        return date_unix;
    }

    public void setDate_unix(int date_unix) {
        this.date_unix = date_unix;
    }

    public String getDate_utc() {
        return date_utc;
    }

    public void setDate_utc(String date_utc) {
        this.date_utc = date_utc;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(int flight_number) {
        this.flight_number = flight_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLaunchpad() {
        return launchpad;
    }

    public void setLaunchpad(String launchpad) {
        this.launchpad = launchpad;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNet() {
        return net;
    }

    public void setNet(boolean net) {
        this.net = net;
    }

    public String getRocket() {
        return rocket;
    }

    public void setRocket(String rocket) {
        this.rocket = rocket;
    }

    public String getStatic_fire_date_utc() {
        return static_fire_date_utc;
    }

    public void setStatic_fire_date_utc(String static_fire_date_utc) {
        this.static_fire_date_utc = static_fire_date_utc;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isTbd() {
        return tbd;
    }

    public void setTbd(boolean tbd) {
        this.tbd = tbd;
    }

    public boolean isUpcoming() {
        return upcoming;
    }

    public void setUpcoming(boolean upcoming) {
        this.upcoming = upcoming;
    }

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }
}
