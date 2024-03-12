package com.example.todoapplication.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapplication.data.entity.Yapilacak
import com.example.todoapplication.data.repo.YapilacaklarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var krepo : YapilacaklarRepository): ViewModel() {
    var yapilacaklarListesi = MutableLiveData<List<Yapilacak>>()

    init {
        yapilacaklariYukle()
    }

    fun sil (kisi_id :Int){
        CoroutineScope(Dispatchers.Main).launch {
            krepo.sil(kisi_id)
            yapilacaklariYukle()
        }
    }

    fun yapilacaklariYukle() {
        CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = krepo.yapilacaklariYukle()
        }

    }
    fun ara(aramaKelimesi:String){
        CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = krepo.ara(aramaKelimesi)
        }
    }
}