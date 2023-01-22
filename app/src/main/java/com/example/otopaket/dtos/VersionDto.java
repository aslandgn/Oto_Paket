package com.example.otopaket.dtos;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

public class VersionDto extends BaseDto {

    @SerializedName("transmissionType")
    public String TransmissionType;

    protected VersionDto(Parcel in) {
        super(in);
        TransmissionType = in.readString();
    }

    public String getTransmissionType() {
        return TransmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        TransmissionType = transmissionType;
    }
}
