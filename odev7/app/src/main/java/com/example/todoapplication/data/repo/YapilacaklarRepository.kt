package com.example.todoapplication.data.repo

import com.example.todoapplication.data.datasource.YapilacaklarDataSource
import com.example.todoapplication.data.entity.Yapilacak

class YapilacaklarRepository(var kds:YapilacaklarDataSource) {

    suspend fun kaydet(yapilacak_yapilacak:String) = kds.kaydet(yapilacak_yapilacak)

    suspend fun guncelle(yapilacak_id:Int,yapilacak_yapilacak:String) = kds.guncelle(yapilacak_id,yapilacak_yapilacak)

    suspend fun sil(yapilacak_id: Int) = kds.sil(yapilacak_id)

    suspend fun yapilacaklariYukle() : List<Yapilacak> = kds.yapilacaklariYukle()

    suspend fun ara(aramaKelimesi:String) : List<Yapilacak> = kds.ara(aramaKelimesi)
}