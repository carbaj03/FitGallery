package com.acv.gallery;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.acv.gallery.repository.LocalGalleryRepositoryImpl;
import com.acv.gallery.repository.GalleryRepository;
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
    public Context provideContext() {
        return application;
    }

    @Provides @Singleton
    Picasso providePicasso(Context context) {
        return new Picasso.Builder(context).loggingEnabled(true).build();
    }

    @Provides @Singleton
    ImageLoader provideImageLoader(Picasso picasso) {
        return new ImageLoaderImpl(picasso);
    }

    @Provides @Singleton
    SharedPreferences providePreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
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
    GalleryRepository provideDataSource(FileUtil fileUtil){
        return new LocalGalleryRepositoryImpl(fileUtil);
    }

}