package com.guru.composecookbook.datingapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.model.Album

class DatingHomeViewModel : ViewModel() {
    private val _albumLiveData = MutableLiveData<MutableList<Album>>()
    val albumLiveData: LiveData<MutableList<Album>> = _albumLiveData

    init {
        getAlbums()
    }

    private fun getAlbums() {
        _albumLiveData.value = AlbumsDataProvider.albums.take(15).toMutableList()
    }
}