package com.example.todoapplication.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapplication.data.entity.Yapilacak

@Dao
interface YapilacaklarDAO {
    @Query("SELECT * FROM yapilacaklar")
    suspend fun yapilacaklariYukle() : List<Yapilacak>

    @Insert
    suspend fun kaydet(yapilacak: Yapilacak)

    @Update
    suspend fun guncelle(yapilacak: Yapilacak)

    @Delete
    suspend fun sil(yapilacak: Yapilacak)

    @Query("SELECT * FROM yapilacaklar WHERE yapilacak_yapilacak like '%' || :aramaKelimesi || '%'")
    suspend fun ara(aramaKelimesi:String) : List<Yapilacak>
}