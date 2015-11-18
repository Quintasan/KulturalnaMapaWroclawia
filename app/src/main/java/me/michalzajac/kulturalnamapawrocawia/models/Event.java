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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeLong(description != null ? description.getTime() : -1);
        dest.writeLong(starts != null ? starts.getTime() : -1);
        dest.writeString(this.ends);
        dest.writeValue(this.price);
        dest.writeValue(this._public);
        dest.writeValue(this.poiId);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public Event() {
    }

    protected Event(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        long tmpDescription = in.readLong();
        this.description = tmpDescription == -1 ? null : new Date(tmpDescription);
        long tmpStarts = in.readLong();
        this.starts = tmpStarts == -1 ? null : new Date(tmpStarts);
        this.ends = in.readString();
        this.price = (Integer) in.readValue(Integer.class.getClassLoader());
        this._public = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.poiId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}