package com.example.otopaket.dtos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BaseDto implements Parcelable {
    @SerializedName("id")
    public int Id;
    @SerializedName("name")
    public String Name;

    protected BaseDto(Parcel in) {
        Id = in.readInt();
        Name = in.readString();
    }

    public static final Creator<BaseDto> CREATOR = new Creator<BaseDto>() {
        @Override
        public BaseDto createFromParcel(Parcel in) {
            return new BaseDto(in);
        }

        @Override
        public BaseDto[] newArray(int size) {
            return new BaseDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(Name);
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
