package com.ada.integratingprojectharrison.network

import com.ada.integratingprojectharrison.data.product.ProductDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsService {
    @GET("v1/products/{id}")
    suspend fun getProduct(@Path("id") id: String): Response<ProductDto>
}