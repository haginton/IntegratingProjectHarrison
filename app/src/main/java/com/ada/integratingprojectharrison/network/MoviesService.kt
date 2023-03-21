package com.ada.integratingprojectharrison.network

import com.ada.integratingprojectharrison.data.SearchResultDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("?apikey=a9a1b302")

    suspend fun searchMovies(@Query("s") query: String): Response<SearchResultDto>

}