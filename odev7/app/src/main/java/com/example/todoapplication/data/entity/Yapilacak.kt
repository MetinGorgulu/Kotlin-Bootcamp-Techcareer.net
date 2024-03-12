package com.example.todoapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable


@Entity(tableName = "yapilacaklar")
data class Yapilacak (@PrimaryKey(autoGenerate = true)
                      @ColumnInfo(name = "yapilacak_id") @NotNull var yapilacak_id:Int,
                      @ColumnInfo(name = "yapilacak_yapilacak") @NotNull var yapilacak_yapilacak:String) : Serializable{
}