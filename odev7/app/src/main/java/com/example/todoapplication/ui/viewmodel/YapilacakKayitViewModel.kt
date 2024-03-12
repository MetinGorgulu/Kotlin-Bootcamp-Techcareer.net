package com.example.todoapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todoapplication.data.repo.YapilacaklarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YapilacakKayitViewModel @Inject constructor(var krepo:YapilacaklarRepository): ViewModel() {

    fun kaydet(yapilacak_yapilacak:String){
        CoroutineScope(Dispatchers.Main).launch {
            krepo.kaydet(yapilacak_yapilacak)
        }
    }
}