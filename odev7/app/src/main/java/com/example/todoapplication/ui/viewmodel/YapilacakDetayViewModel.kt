package com.example.todoapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todoapplication.data.repo.YapilacaklarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YapilacakDetayViewModel @Inject constructor(var krepo:YapilacaklarRepository) : ViewModel() {

    fun guncelle(yapilacak_id:Int,yapilacak_yapilacak:String){
        CoroutineScope(Dispatchers.Main).launch {
            krepo.guncelle(yapilacak_id, yapilacak_yapilacak)
        }
    }
}