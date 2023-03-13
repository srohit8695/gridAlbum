package com.example.kreditbee.dataSource



import com.example.kreditbee.endPoints.ApiService
import com.example.kreditbee.networks.resopnse.AlbumIdResponse1
import com.example.kreditbee.resopnse.AlbumResponse
import retrofit2.Response
import javax.inject.Inject

class PackageInfoDataSource @Inject constructor() {

    @Inject lateinit var apiService: ApiService

    suspend fun getAlbums(): Response<AlbumResponse>{
        return apiService.getAllAlbums()
    }

    suspend fun getSpecificAlbums(albumId : String): Response<AlbumIdResponse1>{
        return apiService.getSpecificAlbums(albumId)
    }

}