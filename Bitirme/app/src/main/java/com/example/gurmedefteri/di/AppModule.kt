package com.example.gurmedefteri.di

import android.content.Context
import androidx.room.Room
import com.example.gurmedefteri.data.datasource.YemekRuyasiDataSource
import com.example.gurmedefteri.data.entity.YemeklerFavori
import com.example.gurmedefteri.data.repo.YemekRuyasiRepository
import com.example.gurmedefteri.retrofit.YemekRuyasiDao
import com.example.gurmedefteri.room.Veritabani
import com.example.gurmedefteri.room.YemeklerFavoriDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideYemekRuyasiRepository(yemekRuyasiDataSource: YemekRuyasiDataSource): YemekRuyasiRepository {
        return YemekRuyasiRepository(yemekRuyasiDataSource)
    }


    @Provides
    @Singleton
    fun provideYemekRuyasiDataSource(yemekRuyasiDAO:YemekRuyasiDao,yemeklerFavoriDao:YemeklerFavoriDao) : YemekRuyasiDataSource {
        return YemekRuyasiDataSource(yemekRuyasiDAO, yemeklerFavoriDao )
    }
    @Provides
    @Singleton
    fun provideYemekRuyasiDao() : YemekRuyasiDao{
        return ApiUtils.getYemekRuyasiDao()
    }
    @Provides
    @Singleton
    fun provideYemeklerFavoriDao(@ApplicationContext context: Context) : YemeklerFavoriDao {
        val vt = Room.databaseBuilder(context, Veritabani::class.java,"gurmeDefteri.sqlite")
            .createFromAsset("gurmeDefteri.sqlite").build()
        return vt.getYemeklerFavoriDao()
    }

}