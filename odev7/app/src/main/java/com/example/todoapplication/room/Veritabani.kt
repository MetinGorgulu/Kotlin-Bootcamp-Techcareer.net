package com.example.todoapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapplication.data.entity.Yapilacak

@Database(entities = [Yapilacak::class], version = 1)
abstract class Veritabani : RoomDatabase()  {
    abstract fun getYapilacaklarDao() : YapilacaklarDAO

}