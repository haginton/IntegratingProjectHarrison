package com.ada.integratingprojectharrison.storage

interface LocalStorage {

    fun saveToken(token: String)

    fun getToken(): String?

    fun isAuthenticated(): Boolean

    fun clearToken()
}