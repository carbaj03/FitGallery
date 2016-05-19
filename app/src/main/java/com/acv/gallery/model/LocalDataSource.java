//package com.acv.gallery.model;
//
//import com.acv.gallery.model.Image;
//import com.acv.gallery.util.FileUtil;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import rx.Observable;
//import rx.functions.Func1;
//
//public class LocalDataSource implements DataSource {
//
//    private FileUtil fileUtil;
//    private List<File> files;
//
//    public LocalDataSource(FileUtil fileUtil) {
//        this.fileUtil = fileUtil;
//    }
//
//    public Observable<List<File>> getFiles()  {
//        return Observable.create(subscriber -> {
//            try {
//                this.files = fileUtil.loadImages();
//                subscriber.onNext(files);
//                subscriber.onCompleted();
//            }catch (Exception ex){
//                subscriber.onError(ex);
//            }
//        });
//    }
//
//    public Observable<List<Image>> loadImages(){
//        return getFiles()
//                .map(new Func1<List<File>, List<Image>>() {
//                    @Override
//                    public List<Image> call(List<File> files) {
//                        List<Image> images = new ArrayList<>();
//                        for (File file : files) {
//                            images.add(new Image(file.toURI().toString(), new Date(file.lastModified())));
//                        }
//                        return images;
//                    }
//                });
//    }
//
//    public Observable<List<Image>> getImagesPerDay(){
//        return Observable.from(files).toList()
//                .map(new Func1<List<File>, List<Image>>() {
//                    @Override
//                    public List<Image> call(List<File> files) {
//                        List<Image> images = new ArrayList<>();
//                        Date lastModified;
//                        Date lastModifiedOld = new Date();
//                        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
//                        for (File file : files) {
//                            lastModified = new Date(file.lastModified());
//
//                            if (!fmt.format(lastModified).equals(fmt.format(lastModifiedOld)))
//                                images.add(new Image(file.toURI().toString(), lastModified));
//
//                            lastModifiedOld = lastModified;
//                        }
//                        return images;
//                    }
//                });
//    }
//
//    public Observable<List<Image>> getImagesPerWeek(){
//        return getFiles()
//                .map(new Func1<List<File>, List<Image>>() {
//                    @Override
//                    public List<Image> call(List<File> files) {
//                        List<Image> images = new ArrayList<>();
//                        Date lastModified;
//                        Date lastModifiedOld = new Date();
//                        SimpleDateFormat fmt = new SimpleDateFormat("yyyyw");
//                        for (File file : files) {
//                            lastModified = new Date(file.lastModified());
//
//                            if (!fmt.format(lastModified).equals(fmt.format(lastModifiedOld)))
//                                images.add(new Image(file.toURI().toString(), lastModified));
//
//                            lastModifiedOld = lastModified;
//                        }
//                        return images;
//                    }
//                });
//    }
//
//    public Observable<List<Image>> getImagesPerMonth(){
//        return getFiles()
//                .map(new Func1<List<File>, List<Image>>() {
//                    @Override
//                    public List<Image> call(List<File> files) {
//                        List<Image> images = new ArrayList<>();
//                        Date lastModified;
//                        Date lastModifiedOld = new Date();
//                        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
//                        for (File file : files) {
//                            lastModified = new Date(file.lastModified());
//
//                            if (!fmt.format(lastModified).equals(fmt.format(lastModifiedOld)))
//                                images.add(new Image(file.toURI().toString(), lastModified));
//
//                            lastModifiedOld = lastModified;
//                        }
//                        return images;
//                    }
//                });
//    }
//}
