package com.example.gurmedefteri.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gurmedefteri.data.entity.SepetYemekler
import com.example.gurmedefteri.data.entity.Yemekler
import com.example.gurmedefteri.data.repo.YemekRuyasiRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.*
@HiltViewModel
class SepetViewModel @Inject constructor(var yemekRuyasiRepo: YemekRuyasiRepository) :ViewModel(){
    private  var auth: FirebaseAuth
    var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>()
    var genelToplam = MutableLiveData(0)

    init {
        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth
        sepettekiYemekleriGetir()
    }

    fun sepettenYemekSil(sepetYemek:SepetYemekler){
        CoroutineScope(Dispatchers.Main).launch {
            yemekRuyasiRepo.sepettenYemekSil(sepetYemek.sepet_yemek_id,auth.currentUser?.email.toString())
        }
    }


    fun sepettekiYemekleriGetir(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                sepetYemeklerListesi.value = yemekRuyasiRepo.sepettekiYemekleriGetir(auth.currentUser?.email.toString())
            } catch (e: Exception) {
                sepetYemeklerListesi.value = emptyList()

            }
        }


    }
}