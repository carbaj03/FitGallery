package com.acv.gallery.repository;


import com.acv.gallery.model.Album;
import com.acv.gallery.model.Image;
import com.acv.gallery.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

public class LocalGalleryRepository implements GalleryRepository {

    private FileUtil fileUtil;

    public LocalGalleryRepository(FileUtil fileUtil) {
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
                .map(files -> {
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
                });
    }

    @Override
    public Observable<List<Album>> getAlbumsPerDay() {
        return getFiles()
                .map(files -> {
                    Map<String, Album> albums = new HashMap<>();
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                    Date lastModified;
                    String format;
                    Collections.sort(files, new FileComparator());
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
                })
                .map(images -> {
                    List<Album> albums = new ArrayList<>();
                    for (Map.Entry<String, Album> album : images.entrySet()) {
                        albums.add(album.getValue());
                    }
                    return albums;
                });
    }

    public static class FileComparator implements Comparator<File> {

        public int compare(File f0, File f1) {
            long date1 = f0.lastModified();
            long date2 = f1.lastModified();

            if (date1 > date2)
                return -1;
            else if (date2 > date1)
                return 1;

            return 0;
        }
    }


    @Override
    public Observable<List<Album>> getAlbumsPerWeek() {
        return getFiles()
                .map(files -> {
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
                })
                .map(images -> {
                    List<Album> albums = new ArrayList<>();
                    for (Map.Entry<String, Album> album : images.entrySet()) {
                        albums.add(album.getValue());
                    }
                    return albums;
                });
    }

    @Override
    public Observable<List<Album>> getAlbumsPerMonth() {
        return getFiles()
                .map(files -> {
                    Map<String, Album> albums = new HashMap<>();
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
                    Date lastModified;
                    String format;
                    Collections.sort(files, new FileComparator());
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
                })
                .map(images -> {
                    List<Album> albums = new ArrayList<>();
                    for (Map.Entry<String, Album> album : images.entrySet()) {
                        albums.add(album.getValue());
                    }
                    return albums;
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

    @Override
    public void delete(List<Image> images) throws IOException {
        if(images == null || images.size() <= 0)
            throw new IOException("No ha seleccionado ninguna imagen");

        fileUtil.eliminarFicheros(imagesToFiles(images));
    }

    private List<File> imagesToFiles(List<Image> images){
        List<File> files = new ArrayList<>();
        for (Image image: images) {
            files.add(image.toFile());
        }
        return files;
    }
}
