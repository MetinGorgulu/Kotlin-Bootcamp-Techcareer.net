package com.example.gurmedefteri.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gurmedefteri.data.entity.YemeklerFavori

@Database(entities = [YemeklerFavori::class], version = 1)
abstract class Veritabani : RoomDatabase(){
    abstract fun getYemeklerFavoriDao() : YemeklerFavoriDao
}