package com.example.gurmedefteri.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gurmedefteri.data.repo.YemekRuyasiRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(var yemekRuyasiRepo: YemekRuyasiRepository) : ViewModel() {
}