package me.michalzajac.kulturalnamapawrocawia.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Event implements Parcelable, Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("starts")
    @Expose
    private Date starts;
    @SerializedName("ends")
    @Expose
    private Date ends;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("event_image")
    @Expose
    private String eventImage;
    @SerializedName("latitude")
    @Expose
    private Float latitude;
    @SerializedName("longitude")
    @Expose
    private Float longitude;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("created_at")
    @Expose
    private Date createdAt;
    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;

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
        dest.writeLong(getStarts() != null ? getStarts().getTime() : -1);
        dest.writeLong(getEnds() != null ? getEnds().getTime() : -1);
        dest.writeString(this.getPrice());
        dest.writeString(this.getEventImage());
        dest.writeValue(this.getLatitude());
        dest.writeValue(this.getLongitude());
        dest.writeString(this.getUrl());
        dest.writeLong(getCreatedAt() != null ? getCreatedAt().getTime() : -1);
        dest.writeLong(getUpdatedAt() != null ? getUpdatedAt().getTime() : -1);
    }

    public Event() {
    }

    protected Event(Parcel in) {
        this.setId((Integer) in.readValue(Integer.class.getClassLoader()));
        this.setName(in.readString());
        this.setDescription(in.readString());
        long tmpStarts = in.readLong();
        this.setStarts(tmpStarts == -1 ? null : new Date(tmpStarts));
        long tmpEnds = in.readLong();
        this.setEnds(tmpEnds == -1 ? null : new Date(tmpEnds));
        this.setPrice(in.readString());
        this.setEventImage(in.readString());
        this.setLatitude((Float) in.readValue(Float.class.getClassLoader()));
        this.setLongitude((Float) in.readValue(Float.class.getClassLoader()));
        this.setUrl(in.readString());
        long tmpCreatedAt = in.readLong();
        this.setCreatedAt(tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt));
        long tmpUpdatedAt = in.readLong();
        this.setUpdatedAt(tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt));
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        public Event[] newArray(int size) {
            return new Event[size];
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

    public Date getStarts() {
        return starts;
    }

    public void setStarts(Date starts) {
        this.starts = starts;
    }

    public Date getEnds() {
        return ends;
    }

    public void setEnds(Date ends) {
        this.ends = ends;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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