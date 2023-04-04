package com.ada.integratingprojectharrison.di

import com.ada.integratingprojectharrison.data.AuthInterceptor
import com.ada.integratingprojectharrison.data.RetrofitGenerator
import com.ada.integratingprojectharrison.network.ActorsService
import com.ada.integratingprojectharrison.network.AuthService
import com.ada.integratingprojectharrison.network.MoviesService
import com.ada.integratingprojectharrison.network.ProductsService
import com.ada.integratingprojectharrison.storage.LocalStorage
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ActivityComponent::class)
object MovieServiceModule {

    @Provides
    fun provideMoviesService() : MoviesService {
        val retrofit = RetrofitGenerator.getInstance()
        return retrofit.create(MoviesService::class.java)
    }

    //Ejemplo de Provide ficticio solo para ver que puedo agregar mas servicios
    @Provides
    fun provideActorService(retrofit: Retrofit) : ActorsService {
        return retrofit.create(ActorsService::class.java)
    }

    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService{
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    fun provideProductsService(retrofit: Retrofit): ProductsService{
        return retrofit.create(ProductsService::class.java)
    }

    @Provides
    fun providesRetrofit(localStorage: LocalStorage) : Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .writeTimeout(0, TimeUnit.MILLISECONDS)
            .readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(AuthInterceptor(localStorage))
            .build()

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
            .create()

        return Retrofit.Builder()
            //.baseUrl("https://www.omdbapi.com/")
            .baseUrl("https://api-rest-java-production-bd09.up.railway.app/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}