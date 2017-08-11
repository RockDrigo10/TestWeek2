package com.example.admin.testweek2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 8/11/2017.
 */

public class Cars implements Parcelable {
    int id;
    String model;
    String types;
    int year;

    public Cars(int id, String model, String types, int year) {
        this.id = id;
        this.model = model;
        this.types = types;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getTypes() {
        return types;
    }

    public int getYear() {
        return year;
    }

    protected Cars(Parcel in) {
        id = in.readInt();
        model = in.readString();
        types = in.readString();
        year = in.readInt();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static final Creator<Cars> CREATOR = new Creator<Cars>() {
        @Override
        public Cars createFromParcel(Parcel in) {
            return new Cars(in);
        }

        @Override
        public Cars[] newArray(int size) {
            return new Cars[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(model);
        dest.writeString(types);
        dest.writeInt(year);
    }
}
