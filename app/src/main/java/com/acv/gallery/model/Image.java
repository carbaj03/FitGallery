package com.acv.gallery.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.acv.gallery.util.DateUtil;

import java.io.File;
import java.util.Date;


public class Image implements Parcelable {

    private String url;
    private Date date;

    public Image(String url, Date date) {
        this.url = url;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getDateFormat(){
        return DateUtil.getInstance().getFechaDia(date);
    }

    public String getUrl() {
        return url;
    }

    public File toFile(){
        return new File(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeLong(date.getTime());
    }

    protected Image(Parcel in) {
        url = in.readString();
        date = new Date(in.readLong());
    }

    public static final Parcelable.Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

}
