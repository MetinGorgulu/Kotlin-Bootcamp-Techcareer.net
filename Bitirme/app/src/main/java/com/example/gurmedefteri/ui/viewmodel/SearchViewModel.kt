package com.example.gurmedefteri.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gurmedefteri.data.entity.SepetYemekler
import com.example.gurmedefteri.data.entity.Yemekler
import com.example.gurmedefteri.data.entity.YemeklerFavori
import com.example.gurmedefteri.data.repo.YemekRuyasiRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(var yemekRuyasiRepo: YemekRuyasiRepository) : ViewModel(){
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>()
    var auth: FirebaseAuth
    var arananFavoriler = MutableLiveData<List<YemeklerFavori>>()


    init {
        tumYemekleriGetir()
        favorileriYukle()
        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth
    }
    fun tumYemekleriGetir(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                yemeklerListesi.value = yemekRuyasiRepo.tumYemekleriGetir()
            }catch (e:Exception){
                yemeklerListesi.value = emptyList()
            }

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
    fun favorileriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                arananFavoriler.value = yemekRuyasiRepo.favorileriYukle(auth.currentUser?.email.toString())
            }catch (e:Exception){
                arananFavoriler.value = emptyList()
            }
        }
    }
    fun checkUserSession() : Boolean {

        return yemekRuyasiRepo.checkUserSession(auth)

    }
    fun firstlLogin(activity: Activity){
        yemekRuyasiRepo.firstlLogin(activity)
    }
}