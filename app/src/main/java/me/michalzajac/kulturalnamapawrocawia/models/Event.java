package me.michalzajac.kulturalnamapawrocawia.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Event implements Parcelable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public Date description;
    @SerializedName("starts")
    @Expose
    public Date starts;
    @SerializedName("ends")
    @Expose
    public String ends;
    @SerializedName("price")
    @Expose
    public Integer price;
    @SerializedName("public")
    @Expose
    public Boolean _public;
    @SerializedName("poi_id")
    @Expose
    public Integer poiId;
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

    }
}