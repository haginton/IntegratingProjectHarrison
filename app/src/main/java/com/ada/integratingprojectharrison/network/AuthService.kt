package com.ada.integratingprojectharrison.network

import com.ada.integratingprojectharrison.data.LoginDto
import com.ada.integratingprojectharrison.data.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("v1/auth")
    suspend fun login(@Body loginDto: LoginDto): Response<TokenDto>
}