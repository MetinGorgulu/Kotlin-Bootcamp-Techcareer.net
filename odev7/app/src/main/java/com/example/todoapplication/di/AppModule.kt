package com.example.todoapplication.di

import android.content.Context
import androidx.room.Room
import com.example.todoapplication.data.datasource.YapilacaklarDataSource
import com.example.todoapplication.data.repo.YapilacaklarRepository
import com.example.todoapplication.room.Veritabani
import com.example.todoapplication.room.YapilacaklarDAO
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
    fun provideYapilacaklarRepository(kds:YapilacaklarDataSource) : YapilacaklarRepository {
        return YapilacaklarRepository(kds)
    }

    @Provides
    @Singleton
    fun provideYapilacaklarDataSource(kdao:YapilacaklarDAO) : YapilacaklarDataSource {
        return YapilacaklarDataSource(kdao)
    }

    @Provides
    @Singleton
    fun provideYapilacaklarDao(@ApplicationContext context: Context) : YapilacaklarDAO {
        val vt = Room.databaseBuilder(context, Veritabani::class.java,"toDoList.sqlite")
            .createFromAsset("toDoList.sqlite").build()
        return vt.getYapilacaklarDao()
    }
}