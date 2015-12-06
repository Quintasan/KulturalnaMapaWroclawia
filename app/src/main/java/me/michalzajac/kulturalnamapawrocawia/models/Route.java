package me.michalzajac.kulturalnamapawrocawia.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Route implements Parcelable, Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("route_image")
    @Expose
    private String routeImage;
    @SerializedName("pois")
    @Expose
    private ArrayList<POI> pois;
    @SerializedName("created_at")
    @Expose
    private Date createdAt;
    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;

    public String googleMapsUrl() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://www.google.com/maps/dir/");
        for (POI poi : getPois()) {
            stringBuilder.append(poi.getLatitude() + "," + poi.getLongitude() + "/");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.getId());
        dest.writeString(this.getName());
        dest.writeString(this.getDescription());
        dest.writeString(this.getRouteImage());
        dest.writeTypedList(getPois());
        dest.writeLong(getCreatedAt() != null ? getCreatedAt().getTime() : -1);
        dest.writeLong(getUpdatedAt() != null ? getUpdatedAt().getTime() : -1);
    }

    public Route() {
    }

    protected Route(Parcel in) {
        this.setId((Integer) in.readValue(Integer.class.getClassLoader()));
        this.setName(in.readString());
        this.setDescription(in.readString());
        this.setRouteImage(in.readString());
        this.setPois(in.createTypedArrayList(POI.CREATOR));
        long tmpCreatedAt = in.readLong();
        this.setCreatedAt(tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt));
        long tmpUpdatedAt = in.readLong();
        this.setUpdatedAt(tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt));
    }

    public static final Creator<Route> CREATOR = new Creator<Route>() {
        public Route createFromParcel(Parcel source) {
            return new Route(source);
        }

        public Route[] newArray(int size) {
            return new Route[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRouteImage() {
        return routeImage;
    }

    public void setRouteImage(String routeImage) {
        this.routeImage = routeImage;
    }

    public ArrayList<POI> getPois() {
        return pois;
    }

    public void setPois(ArrayList<POI> pois) {
        this.pois = pois;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}