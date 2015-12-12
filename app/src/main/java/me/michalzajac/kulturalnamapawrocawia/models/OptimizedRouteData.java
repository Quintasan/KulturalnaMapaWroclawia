package me.michalzajac.kulturalnamapawrocawia.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OptimizedRouteData {

    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("url")
    @Expose
    private String url;

    public Integer getDistance() {
        return distance;
    }

    public Double getDistanceKilometers() { return distance / 1000.0; }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }
    public Double getDurationHours() { return duration/3600.0; }

    public void setDuration(Integer time) {
        this.duration = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}