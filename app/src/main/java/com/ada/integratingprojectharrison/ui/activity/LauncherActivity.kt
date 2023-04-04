package com.ada.integratingprojectharrison.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ada.integratingprojectharrison.databinding.ActivityMainBinding
import com.ada.integratingprojectharrison.storage.LocalStorage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LauncherActivity: AppCompatActivity() {
    @Inject
    lateinit var localStorage: LocalStorage;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (localStorage.isAuthenticated()){
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }else {
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

    }
}