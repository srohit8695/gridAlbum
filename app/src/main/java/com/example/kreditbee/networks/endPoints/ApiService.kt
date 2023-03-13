package com.example.kreditbee.endPoints



import com.example.kreditbee.networks.resopnse.AlbumIdResponse1
import com.example.kreditbee.resopnse.AlbumResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("albums")
    suspend fun getAllAlbums(): Response<AlbumResponse>

    @GET("photos?albumId=1")
    suspend fun getSpecificAlbums(@Path("id") id : String ): Response<AlbumIdResponse1>



}