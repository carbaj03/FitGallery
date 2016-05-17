package com.acv.gallery;


import com.acv.gallery.util.DateUtil;

import java.util.Date;


public class Image {

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

}
