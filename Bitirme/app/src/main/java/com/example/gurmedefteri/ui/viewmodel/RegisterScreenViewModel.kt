package com.example.gurmedefteri.ui.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.gurmedefteri.data.repo.YemekRuyasiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(var yemekRuyasiRepo: YemekRuyasiRepository) : ViewModel() {


    fun snackBar(view: View, message:String){
        yemekRuyasiRepo.snackBar(view,message)
    }
}