package com.example.gurmedefteri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.gurmedefteri.databinding.ActivityMainBinding
import com.example.gurmedefteri.ui.activities.LoginActivity
import com.example.gurmedefteri.ui.fragments.HomePageFragment
import com.example.gurmedefteri.ui.fragments.HomePageFragmentDirections
import com.example.gurmedefteri.ui.fragments.ProfileFragment
import com.example.gurmedefteri.ui.viewmodel.ActivityViewModel
import com.example.gurmedefteri.ui.viewmodel.SepetViewModel
import com.example.gurmedefteri.utils.gecis
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ActivityViewModel by viewModels()
        viewModel = tempViewModel
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.bottomNavigationView.background = null
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostFragment.navController)

        binding.fab.setOnClickListener {
            binding.bottomNavigationView.selectedItemId = R.id.sepetFragment
        }

        setContentView(binding.root)
    }
}



