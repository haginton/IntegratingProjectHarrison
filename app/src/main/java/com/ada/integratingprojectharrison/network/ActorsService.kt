package com.ada.integratingprojectharrison.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ActorsService {
    @GET("/actors/{id}")
    fun getActors(@Path("id") id: Int): Response<String>
}