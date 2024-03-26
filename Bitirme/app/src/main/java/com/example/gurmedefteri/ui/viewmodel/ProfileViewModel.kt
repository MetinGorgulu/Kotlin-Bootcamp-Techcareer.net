package com.example.gurmedefteri.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.gurmedefteri.data.repo.YemekRuyasiRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(var yemekRuyasiRepo: YemekRuyasiRepository) : ViewModel(){
    private var auth:FirebaseAuth
    init {
        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth
    }
    fun checkUserSession() : Boolean {

        return yemekRuyasiRepo.checkUserSession(auth)

    }
    fun firstlLogin(activity: Activity){
        yemekRuyasiRepo.firstlLogin(activity)
    }
}