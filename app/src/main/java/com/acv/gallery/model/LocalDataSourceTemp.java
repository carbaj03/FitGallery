package com.acv.gallery.model;

import com.acv.gallery.Album;
import com.acv.gallery.Image;
import com.acv.gallery.util.FileUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

public class LocalDataSourceTemp implements DataSource {

    private FileUtil fileUtil;

    public LocalDataSourceTemp(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    public Observable<List<File>> getFiles()  {
        return Observable.create(subscriber -> {
            try {
                List<File> files = fileUtil.getImages();
                subscriber.onNext(files);
                subscriber.onCompleted();
            }catch (Exception ex){
                subscriber.onError(ex);
            }
        });
    }

    public Observable<List<Image>> getImages(){
        return getFiles()
                .map(new Func1<List<File>, List<Image>>() {
                    @Override
                    public List<Image> call(List<File> files) {
                        List<Image> images = new ArrayList<>();
                        for (File file : files) {
                            images.add(new Image(file.toURI().toString(), new Date(file.lastModified())));
                        }
                        return images;
                    }
                });
    }

    public Observable<List<Album>> getImagesPerDay(){
        return getFiles()
                .map(new Func1<List<File>, Map<String, Album>>() {
                    @Override
                    public Map<String, Album> call(List<File> files) {
                        Map<String, Album> albums = new HashMap<>();
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                        Date lastModified;
                        String format;

                        for (File file : files) {
                            lastModified = new Date(file.lastModified());
                            format = fmt.format(lastModified);
                            if(albums.containsKey(format))
                                albums.get(format).getImages().add(new Image(file.toURI().toString(), lastModified));
                            else {
                                List<Image> images = new ArrayList<>();
                                images.add(new Image(file.toURI().toString(), lastModified));
                                Album album = new Album(file.toURI().toString(), lastModified, images);
                                albums.put(format, album);
                            }
                        }
                        return albums;
                    }
                })
                .map(new Func1<Map<String, Album>, List<Album>>() {
                    @Override
                    public List<Album> call(Map<String, Album> images) {
                        List<Album> albums = new ArrayList<>();
                        for (Map.Entry<String, Album> album : images.entrySet()){
                            albums.add(album.getValue());
                        }
                        return albums;
                    }
                });
    }

    public Observable<List<Album>> getImagesPerWeek(){
        return getFiles()
                .map(new Func1<List<File>, Map<String, Album>>() {
                    @Override
                    public Map<String, Album> call(List<File> files) {
                        Map<String, Album> albums = new HashMap<>();
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyyw");
                        Date lastModified;
                        String format;

                        for (File file : files) {
                            lastModified = new Date(file.lastModified());
                            format = fmt.format(lastModified);
                            if(albums.containsKey(format))
                                albums.get(format).getImages().add(new Image(file.toURI().toString(), lastModified));
                            else {
                                List<Image> images = new ArrayList<>();
                                images.add(new Image(file.toURI().toString(), lastModified));
                                Album album = new Album(file.toURI().toString(), lastModified, images);
                                albums.put(format, album);
                            }
                        }
                        return albums;
                    }
                })
                .map(new Func1<Map<String, Album>, List<Album>>() {
                    @Override
                    public List<Album> call(Map<String, Album> images) {
                        List<Album> albums = new ArrayList<>();
                        for (Map.Entry<String, Album> album : images.entrySet()){
                            albums.add(album.getValue());
                        }
                        return albums;
                    }
                });
    }

    public Observable<List<Album>> getImagesPerMonth(){
        return getFiles()
                .map(new Func1<List<File>, Map<String, Album>>() {
                    @Override
                    public Map<String, Album> call(List<File> files) {
                        Map<String, Album> albums = new HashMap<>();
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
                        Date lastModified;
                        String format;

                        for (File file : files) {
                            lastModified = new Date(file.lastModified());
                            format = fmt.format(lastModified);
                            if(albums.containsKey(format))
                                albums.get(format).getImages().add(new Image(file.toURI().toString(), lastModified));
                            else {
                                List<Image> images = new ArrayList<>();
                                images.add(new Image(file.toURI().toString(), lastModified));
                                Album album = new Album(file.toURI().toString(), lastModified, images);
                                albums.put(format, album);
                            }
                        }
                        return albums;
                    }
                })
                .map(new Func1<Map<String, Album>, List<Album>>() {
                    @Override
                    public List<Album> call(Map<String, Album> images) {
                        List<Album> albums = new ArrayList<>();
                        for (Map.Entry<String, Album> album : images.entrySet()){
                            albums.add(album.getValue());
                        }
                        return albums;
                    }
                });
    }
}
