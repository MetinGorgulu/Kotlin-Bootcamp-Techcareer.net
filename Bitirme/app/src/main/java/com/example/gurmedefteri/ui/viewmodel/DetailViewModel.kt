package com.example.gurmedefteri.ui.viewmodel

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gurmedefteri.data.entity.SepetYemekler
import com.example.gurmedefteri.data.entity.Yemekler
import com.example.gurmedefteri.data.entity.YemeklerFavori
import com.example.gurmedefteri.data.repo.YemekRuyasiRepository
import com.example.gurmedefteri.databinding.FragmentDetailBinding
import com.example.gurmedefteri.ui.activities.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var yemekRuyasiRepo: YemekRuyasiRepository) :ViewModel() {
    private var auth:FirebaseAuth
    var arananFavoriler = MutableLiveData<List<YemeklerFavori>>()
    var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>()
    init {
        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth
        favorileriYukle()
        sepettekiYemekleriGetir()
    }

    fun sepeteEkle(yemek:Yemekler, yemek_siparis_adet:Int, kullanici_adi:String){

        CoroutineScope(Dispatchers.Main).launch {
            yemekRuyasiRepo.sepeteYemekEkle(yemek, yemek_siparis_adet, kullanici_adi)
        }
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

    fun checkUserSession() : Boolean {

            return yemekRuyasiRepo.checkUserSession(auth)

    }
    fun snackBar(view: View, message:String){
        yemekRuyasiRepo.snackBar(view,message)
    }
    fun firstlLogin(activity: Activity){
        yemekRuyasiRepo.firstlLogin(activity)
    }
    fun favorileriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                arananFavoriler.value = yemekRuyasiRepo.favorileriYukle(auth.currentUser?.email.toString())
            }catch (e:Exception){
                arananFavoriler.value = emptyList()
            }
        }
    }
    fun favoriKaydet(favori: YemeklerFavori){
        CoroutineScope(Dispatchers.Main).launch {
            yemekRuyasiRepo.favoriKaydet(favori)
        }

    }

    fun favoriSil(favori: YemeklerFavori){
        CoroutineScope(Dispatchers.Main).launch {
            yemekRuyasiRepo.favoriSil(favori)
        }

    }
    fun coroutine() {
        viewModelScope.launch {
            while (true) {
                sepettekiYemekleriGetir()

                delay(1000)
            }
        }
    }

}