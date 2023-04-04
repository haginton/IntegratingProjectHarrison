package com.ada.integratingprojectharrison.data

import com.ada.integratingprojectharrison.storage.LocalStorage
import okhttp3.Interceptor
import okhttp3.Response

//antes de enviar la información al servidor va a pasar por esta clase para añadir el JWT en la petición en los headers
class AuthInterceptor(private val localStorage: LocalStorage): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = localStorage.getToken()
        if (token.isNotEmpty()) {
            request.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(request.build())
    }
}