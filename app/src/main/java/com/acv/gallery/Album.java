package com.acv.gallery;


import com.acv.gallery.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Album {

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
}
