package me.michalzajac.kulturalnamapawrocawia.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class POI implements Parcelable, Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("additional_information")
    @Expose
    private String additionalInformation;
    @SerializedName("latitude")
    @Expose
    private Float latitude;
    @SerializedName("longitude")
    @Expose
    private Float longitude;
    @SerializedName("opening_hours")
    @Expose
    private String openingHours;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("poi_image")
    @Expose
    private String poiImage;
    @SerializedName("created_at")
    @Expose
    private Date createdAt;
    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;

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
        dest.writeString(this.getAdditionalInformation());
        dest.writeValue(this.getLatitude());
        dest.writeValue(this.getLongitude());
        dest.writeString(this.getOpeningHours());
        dest.writeString(this.getUrl());
        dest.writeString(this.getPhone());
        dest.writeString(this.getPoiImage());
        dest.writeLong(getCreatedAt() != null ? getCreatedAt().getTime() : -1);
        dest.writeLong(getUpdatedAt() != null ? getUpdatedAt().getTime() : -1);
    }

    public POI() {
    }

    protected POI(Parcel in) {
        this.setId((Integer) in.readValue(Integer.class.getClassLoader()));
        this.setName(in.readString());
        this.setDescription(in.readString());
        this.setAdditionalInformation(in.readString());
        this.setLatitude((Float) in.readValue(Float.class.getClassLoader()));
        this.setLongitude((Float) in.readValue(Float.class.getClassLoader()));
        this.setOpeningHours(in.readString());
        this.setUrl(in.readString());
        this.setPhone(in.readString());
        this.setPoiImage(in.readString());
        long tmpCreatedAt = in.readLong();
        this.setCreatedAt(tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt));
        long tmpUpdatedAt = in.readLong();
        this.setUpdatedAt(tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt));
    }

    public static final Creator<POI> CREATOR = new Creator<POI>() {
        public POI createFromParcel(Parcel source) {
            return new POI(source);
        }

        public POI[] newArray(int size) {
            return new POI[size];
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

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
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

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPoiImage() {
        return poiImage;
    }

    public void setPoiImage(String poiImage) {
        this.poiImage = poiImage;
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
