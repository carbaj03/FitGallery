package com.acv.gallery;


import com.acv.gallery.model.Album;
import com.acv.gallery.model.Image;
import com.acv.gallery.presenter.GalleryPresenter;
import com.acv.gallery.presenter.GalleryPresenterImpl;
import com.acv.gallery.repository.GalleryRepository;
import com.acv.gallery.view.GalleryView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresenterTest {

    GalleryRepository mockGalleryRepository;
    GalleryView mockGalleryView;
    GalleryPresenter presenter;

    @Before
    public void setUp(){
        mockGalleryRepository = mock(GalleryRepository.class);

        when(mockGalleryRepository.getAlbums()).thenReturn(getAlbums());

        mockGalleryView = mock(GalleryView.class);

        presenter = new GalleryPresenterImpl(mockGalleryView, mockGalleryRepository);
    }

    public Observable<List<Album>> getAlbums(){
        List<Album> albums = new ArrayList<>();
        albums.add(new Album("url", new Date(), new ArrayList<>()));
        return Observable.from(albums).toList();
    }

    @Test
    public void noIteractionsWithViewShouldShowAlbums(){
        presenter.loadAlbums();

        verifyZeroInteractions(mockGalleryView);
    }

    @Test
    public void shouldBeAbleToLoadTheAlbumsFromTheRepository(){
        presenter.loadAlbums();

        verify(mockGalleryRepository, times(1)).getAlbums();

        verify(mockGalleryView, times(1)).displayLoading(true);
    }
}
