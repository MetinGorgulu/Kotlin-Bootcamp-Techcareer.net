package com.example.gurmedefteri.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
class FavoritesViewModel @Inject constructor(var yemekRuyasiRepo: YemekRuyasiRepository) :
    ViewModel(){
    var auth: FirebaseAuth
    init {
        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth
    }
    var arananFavoriler = MutableLiveData<List<YemeklerFavori>>()
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

    }