package com.example.gurmedefteri.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gurmedefteri.data.entity.YemeklerFavori


@Dao
interface YemeklerFavoriDao {
    @Query ("SELECT * FROM yemeklerFavori WHERE email like '%' || :email || '%'")
    suspend fun favorileriYukle(email:String) : List<YemeklerFavori>

    @Insert
    suspend fun kaydet(favori:YemeklerFavori)

    @Delete
    suspend fun sil(favori:YemeklerFavori)
}