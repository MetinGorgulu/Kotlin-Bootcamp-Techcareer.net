package com.example.gurmedefteri.data.repo

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.View
import com.example.gurmedefteri.data.datasource.YemekRuyasiDataSource
import com.example.gurmedefteri.data.entity.SepetYemekler
import com.example.gurmedefteri.data.entity.Yemekler
import com.example.gurmedefteri.data.entity.YemeklerFavori
import com.example.gurmedefteri.ui.activities.LoginActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemekRuyasiRepository(var yemekRuyasiDataSource:YemekRuyasiDataSource) {


    suspend fun tumYemekleriGetir() : List<Yemekler> = yemekRuyasiDataSource.tumYemekleriGetir()
    suspend fun sepettekiYemekleriGetir(kullanici_adi: String) : List<SepetYemekler> = yemekRuyasiDataSource.sepettekiYemekleriGetir(kullanici_adi)
    suspend fun sepeteYemekEkle(yemek:Yemekler,
                                yemek_siparis_adet:Int,
                                kullanici_adi:String) =
        yemekRuyasiDataSource.sepeteYemekEkle(yemek, yemek_siparis_adet, kullanici_adi)
    suspend fun sepettenYemekSil(sepet_yemek_id:Int, kullanici_adi: String) = yemekRuyasiDataSource.sepettenYemekSil(sepet_yemek_id,kullanici_adi)

    suspend fun favoriKaydet(favori: YemeklerFavori){
        yemekRuyasiDataSource.favoriKaydet(favori)
    }

    suspend fun favoriSil(favori: YemeklerFavori){
        yemekRuyasiDataSource.favoriSil(favori)
    }
    suspend fun favorileriYukle(email:String) : List<YemeklerFavori> = yemekRuyasiDataSource.favorileriYukle(email)
    fun checkUserSession(auth: FirebaseAuth) : Boolean {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            return true

        } else {
            return false
        }
    }

    fun snackBar(view: View, message:String){
        Snackbar.make(view,message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(Color.WHITE)
            .setTextColor(Color.BLACK)
            .setActionTextColor(Color.RED)
            .show()
    }
    fun firstlLogin(activity: Activity){
        val intent = Intent(activity, LoginActivity::class.java)
        intent.putExtra("message", "Lütfen Giriş Yapınız")
        activity.startActivity(intent)
    }


}