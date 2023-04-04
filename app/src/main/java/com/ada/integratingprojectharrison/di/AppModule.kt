package com.ada.integratingprojectharrison.di

import android.content.Context
import com.ada.integratingprojectharrison.storage.LocalStorage
import com.ada.integratingprojectharrison.storage.SharedPreferencesLocalStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

const val SHARED_PREFERENCES_NAME = "shared_preferences_ada"

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    //se necedita el contexto o activity, por parametro de la función
    @Provides
    fun provideLocalStorage(@ApplicationContext appContext: Context): LocalStorage{
        //el sharedPreference es como una clase especial del sistema operativo por eso se requiere el contexto
        //se hace en modo privado osea que estas preferencias son solo para la aplicación
        val sharedPreferences = appContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return SharedPreferencesLocalStorage(sharedPreferences)
    }
}