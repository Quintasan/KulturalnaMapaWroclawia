package me.michalzajac.kulturalnamapawrocawia.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POI implements Parcelable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("latitude")
    @Expose
    public Float latitude;
    @SerializedName("longitude")
    @Expose
    public Float longitude;
    @SerializedName("open_from")
    @Expose
    public String openFrom;
    @SerializedName("open_to")
    @Expose
    public String openTo;
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
        dest.writeFloat(this.latitude);
        dest.writeFloat(this.longitude);
        dest.writeString(this.openFrom);
        dest.writeString(this.openTo);
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
        this.latitude = source.readFloat();
        this.longitude = source.readFloat();
        this.openFrom = source.readString();
        this.openTo = source.readString();
    }
}