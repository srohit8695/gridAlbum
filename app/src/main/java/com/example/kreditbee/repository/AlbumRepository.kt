package com.example.kreditbee.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kreditbee.dataSource.PackageInfoDataSource
import com.example.kreditbee.networks.resopnse.AlbumIdResponse1
import com.example.kreditbee.resopnse.AlbumResponse
import com.example.kreditbee.resopnse.genericResopnse.BaseResponse

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlbumRepository @Inject constructor(private val packageInfoDataSource: PackageInfoDataSource) {

    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + parentJob)

    fun getAlbumInfoList(): LiveData<BaseResponse<AlbumResponse>> {
        val getMainInfoResponse = MutableLiveData<BaseResponse<AlbumResponse>>()
        getMainInfoResponse.postValue(BaseResponse.loading());
        coroutineScope.launch {
            try {
                val request = packageInfoDataSource.getAlbums()
                if (request.isSuccessful) {
                    getMainInfoResponse.postValue(BaseResponse.success(request.body()!!))
                } else {
                    getMainInfoResponse.postValue(
                        BaseResponse.error(
                            "Network Issue"
                        )
                    )
                }
            } catch (e: Exception) {
                getMainInfoResponse.postValue(BaseResponse.error(e.localizedMessage))
            }
        }
        return getMainInfoResponse
    }

    fun getAlbumById(albumId : String): LiveData<BaseResponse<AlbumIdResponse1>> {
        val getMainInfoResponse = MutableLiveData<BaseResponse<AlbumIdResponse1>>()
        getMainInfoResponse.postValue(BaseResponse.loading());
        coroutineScope.launch {
            try {
                val request = packageInfoDataSource.getSpecificAlbums(albumId)
                if (request.isSuccessful) {
                    getMainInfoResponse.postValue(BaseResponse.success(request.body()!!))
                } else {
                    getMainInfoResponse.postValue(
                        BaseResponse.error(
                            "Network Issue"
                        )
                    )
                }
            } catch (e: Exception) {
                getMainInfoResponse.postValue(BaseResponse.error(e.localizedMessage))
            }
        }
        return getMainInfoResponse
    }

}