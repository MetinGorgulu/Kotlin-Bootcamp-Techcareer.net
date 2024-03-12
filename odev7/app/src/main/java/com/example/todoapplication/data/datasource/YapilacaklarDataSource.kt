package com.example.todoapplication.data.datasource

import com.example.todoapplication.data.entity.Yapilacak
import com.example.todoapplication.room.YapilacaklarDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YapilacaklarDataSource(var kdao:YapilacaklarDAO) {

    suspend fun kaydet( yapilacak_yapilacak:String){
        val yeniKayit = Yapilacak(0,yapilacak_yapilacak)
        kdao.kaydet(yeniKayit)
    }
    suspend fun guncelle(yapilacak_id:Int,yapilacak_yapilacak:String){
        val guncellenen = Yapilacak(yapilacak_id,yapilacak_yapilacak)
        kdao.guncelle(guncellenen)
    }
    suspend fun sil(yapilacak_id:Int){
        val silinen = Yapilacak(yapilacak_id,"")
        kdao.sil(silinen)
    }
    suspend fun yapilacaklariYukle() : List<Yapilacak> =
        withContext(Dispatchers.IO){
            return@withContext kdao.yapilacaklariYukle()
        }
    suspend fun ara(aramaKelimesi:String) : List<Yapilacak> =
        withContext(Dispatchers.IO){
            return@withContext kdao.ara(aramaKelimesi)
        }
}