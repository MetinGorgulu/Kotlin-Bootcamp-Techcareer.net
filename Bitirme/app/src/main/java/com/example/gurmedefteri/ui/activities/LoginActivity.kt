package com.example.gurmedefteri.ui.activities

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.gurmedefteri.R
import com.example.gurmedefteri.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra("message")

        // Snackbar mesajı varsa oluştur ve göster
        if (!message.isNullOrEmpty()) {
            Snackbar.make(binding.fragmentContainerView, message, Snackbar.LENGTH_SHORT).show()
        }

    }


}