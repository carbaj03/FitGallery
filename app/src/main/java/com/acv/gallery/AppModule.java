package com.acv.gallery;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.acv.gallery.model.AlbumDataSource;
import com.acv.gallery.model.DataSource;
import com.acv.gallery.util.DateUtil;
import com.acv.gallery.util.FileUtil;
import com.acv.gallery.util.ImageLoader;
import com.acv.gallery.util.ImageLoaderImpl;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private static final String PREFERENCES_NAME = "GALLERY_PREFERENCES";

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides @Singleton
    Picasso providePicasso() {
        return new Picasso.Builder(application).loggingEnabled(true).build();
    }

    @Provides @Singleton
    ImageLoader provideImageLoader(Picasso picasso) {
        return new ImageLoaderImpl(picasso);
    }

    @Provides @Singleton
    SharedPreferences providePreferences(){
        return application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Provides @Singleton
    FileUtil provideFileUtil(){
        return new FileUtil();
    }

    @Provides @Singleton
    DateUtil provideDateUtil(){
        return new DateUtil();
    }

    @Provides @Singleton
    DataSource provideDataSource(FileUtil fileUtil){
        return new AlbumDataSource(fileUtil);
    }

}