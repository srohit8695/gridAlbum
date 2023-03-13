package com.example.kreditbee.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kreditbee.networks.resopnse.AlbumIdResponse1
import com.example.kreditbee.repository.AlbumRepository
import com.example.kreditbee.resopnse.AlbumResponse
import com.example.kreditbee.resopnse.genericResopnse.BaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private var packageInfoRepository: AlbumRepository): ViewModel(){

    fun getAllPackageInfo(): LiveData<BaseResponse<AlbumResponse>> {
        return packageInfoRepository.getAlbumInfoList()
    }

    fun getDataForAlbum(albumId : String): LiveData<BaseResponse<AlbumIdResponse1>> {
        return packageInfoRepository.getAlbumById(albumId)
    }

}