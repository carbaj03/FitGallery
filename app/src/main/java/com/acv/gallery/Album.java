package com.acv.gallery;


import android.os.Parcel;
import android.os.Parcelable;

import com.acv.gallery.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Album implements Parcelable{

    private String url;
    private Date date;

    private List<Image> images;

    public Album(String url, Date date, List<Image> images) {
        this.url = url;
        this.date = date;
        this.images = images;
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

    public List<Image> getImages() {
        return images != null ? images : new ArrayList<>();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeLong(date.getTime());
        dest.writeList(images);
    }

    protected Album(Parcel in) {
        url = in.readString();
        date.setTime(in.readLong());
        in.readList(images, Image.class.getClassLoader());
    }

    public static final Parcelable.Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
}
