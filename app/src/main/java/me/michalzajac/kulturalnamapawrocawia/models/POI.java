package me.michalzajac.kulturalnamapawrocawia.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class POI implements Parcelable, Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("additional_information")
    @Expose
    public String additionalInformation;
    @SerializedName("latitude")
    @Expose
    public Float latitude;
    @SerializedName("longitude")
    @Expose
    public Float longitude;
    @SerializedName("opening_hours")
    @Expose
    public String openingHours;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("poi_image")
    @Expose
    public String poiImage;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.additionalInformation);
        dest.writeFloat(this.latitude);
        dest.writeFloat(this.longitude);
        dest.writeString(this.openingHours);
        dest.writeString(this.url);
        dest.writeString(this.phone);

        dest.writeString(this.poiImage);
    }

    public static final Creator<POI> CREATOR = new Creator<POI>() {
        @Override
        public POI createFromParcel(Parcel source) {
            return new POI(source);
        }

        @Override
        public POI[] newArray(int size) {
            return new POI[size];
        }
    };

    private POI(Parcel source) {
        this.id = source.readInt();
        this.name = source.readString();
        this.description = source.readString();
        this.additionalInformation = source.readString();
        this.latitude = source.readFloat();
        this.longitude = source.readFloat();
        this.openingHours = source.readString();
        this.url = source.readString();
        this.phone = source.readString();

        this.poiImage = source.readString();
    }
}
