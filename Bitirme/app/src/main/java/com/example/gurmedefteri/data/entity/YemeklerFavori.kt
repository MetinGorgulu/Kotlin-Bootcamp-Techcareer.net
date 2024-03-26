package com.example.gurmedefteri.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.auth.FirebaseUser
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "yemeklerFavori")
class YemeklerFavori(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_id") @NotNull var user_id:Int,
                     @ColumnInfo(name = "yemek_id") @NotNull var yemek_id:Int,
                     @ColumnInfo(name = "yemek_adi") @NotNull var yemek_adi:String,
                     @ColumnInfo(name = "yemek_resim_adi") @NotNull var yemek_resim_adi:String,
                     @ColumnInfo(name = "yemek_fiyat") @NotNull var yemek_fiyat: Int,
                     @ColumnInfo(name = "email") @NotNull var email: String) : Serializable {
}