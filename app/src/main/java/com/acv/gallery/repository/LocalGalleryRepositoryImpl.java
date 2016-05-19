package com.acv.gallery.repository;


import com.acv.gallery.model.Album;
import com.acv.gallery.model.Image;
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

public class LocalGalleryRepositoryImpl implements GalleryRepository {

    private FileUtil fileUtil;

    public LocalGalleryRepositoryImpl(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    public Observable<List<File>> getFiles() {
        return Observable.create(subscriber -> {
            try {
                List<File> files = fileUtil.getImages();
                subscriber.onNext(files);
                subscriber.onCompleted();
            } catch (Exception ex) {
                subscriber.onError(ex);
            }
        });
    }

    @Override
    public Observable<List<Album>> getAlbums() {
        return getFiles()
                .map(new Func1<List<File>, List<Album>>() {
                    @Override
                    public List<Album> call(List<File> files) {
                        List<Album> albums = new ArrayList<>();
                        List<Image> images = new ArrayList<>();
                        for (File file : files) {
                            images.add(new Image(file.toURI().toString(), new Date(file.lastModified())));
                        }
                        albums.add(new Album(
                                images.get(0).getUrl(),
                                images.get(0).getDate(),
                                images));
                        return albums;
                    }
                });
    }

    @Override
    public Observable<List<Album>> getAlbumsPerDay() {
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
                            if (albums.containsKey(format))
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
                        for (Map.Entry<String, Album> album : images.entrySet()) {
                            albums.add(album.getValue());
                        }
                        return albums;
                    }
                });
    }

    @Override
    public Observable<List<Album>> getAlbumsPerWeek() {
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
                            if (albums.containsKey(format))
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
                        for (Map.Entry<String, Album> album : images.entrySet()) {
                            albums.add(album.getValue());
                        }
                        return albums;
                    }
                });
    }

    @Override
    public Observable<List<Album>> getAlbumsPerMonth() {
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
                            if (albums.containsKey(format))
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
                        for (Map.Entry<String, Album> album : images.entrySet()) {
                            albums.add(album.getValue());
                        }
                        return albums;
                    }
                });
    }

    @Override
    public Observable<List<Image>> getImages(Album album) {
        return Observable.from(album.getImages()).toList();
    }

    @Override
    public Observable<Image> getImage(Image image) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(image);
                subscriber.onCompleted();
            }catch (Exception ex){
                subscriber.onError(ex);
            }
        });
    }
}
