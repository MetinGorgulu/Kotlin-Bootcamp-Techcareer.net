package com.example.gurmedefteri.di

import com.example.gurmedefteri.retrofit.YemekRuyasiDao
import com.example.gurmedefteri.retrofit.RetrofitClient

class ApiUtils {
    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getYemekRuyasiDao() : YemekRuyasiDao {
            return RetrofitClient.getClient(BASE_URL).create(YemekRuyasiDao::class.java)
        }
    }
}