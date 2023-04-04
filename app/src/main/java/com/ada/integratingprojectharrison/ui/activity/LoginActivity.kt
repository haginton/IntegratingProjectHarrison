package com.ada.integratingprojectharrison.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ada.integratingprojectharrison.R
import com.ada.integratingprojectharrison.data.LoginDto
import com.ada.integratingprojectharrison.data.TokenDto
import com.ada.integratingprojectharrison.databinding.ActivityMainBinding
import com.ada.integratingprojectharrison.network.AuthService
import com.ada.integratingprojectharrison.storage.LocalStorage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {

    @Inject
    lateinit var authService: AuthService

    @Inject
    lateinit var localStorage: LocalStorage;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestAuthService()

    }

    private fun requestAuthService() {
        GlobalScope.launch {
            val loginDto = LoginDto(
                "ada6@mail.com",
                "1523asd*"
            )
            val response: Response<TokenDto> = authService.login(loginDto)

            if (response.isSuccessful){
                val token = response.body()
                Log.d("AndroidKotlinAda", "token: ${token!!.token}")
                localStorage.saveToken(token.token)
                Log.d("AndroidKotlinAda", "token from storage: ${localStorage.getToken()}")
            }
        }
    }

}