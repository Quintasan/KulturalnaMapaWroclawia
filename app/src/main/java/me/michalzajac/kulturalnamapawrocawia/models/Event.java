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
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("starts")
    @Expose
    public Date starts;
    @SerializedName("ends")
    @Expose
    public Date ends;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("event_image")
    @Expose
    public String eventImage;
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    @SerializedName("updated_at")
    @Expose
    public Date updatedAt;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeLong(starts != null ? starts.getTime() : -1);
        dest.writeLong(ends != null ? ends.getTime() : -1);
        dest.writeString(this.price);
        dest.writeString(this.eventImage);
        dest.writeLong(createdAt != null ? createdAt.getTime() : -1);
        dest.writeLong(updatedAt != null ? updatedAt.getTime() : -1);
    }

    public Event() {
    }

    protected Event(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.description = in.readString();
        long tmpStarts = in.readLong();
        this.starts = tmpStarts == -1 ? null : new Date(tmpStarts);
        long tmpEnds = in.readLong();
        this.ends = tmpEnds == -1 ? null : new Date(tmpEnds);
        this.price = in.readString();
        this.eventImage = in.readString();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        long tmpUpdatedAt = in.readLong();
        this.updatedAt = tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt);
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