package com.example.gurmedefteri.data.datasource


import com.example.gurmedefteri.data.entity.SepetYemekler
import com.example.gurmedefteri.data.entity.Yemekler
import com.example.gurmedefteri.data.entity.YemeklerFavori
import com.example.gurmedefteri.retrofit.YemekRuyasiDao
import com.example.gurmedefteri.room.YemeklerFavoriDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemekRuyasiDataSource (var yemekRuyasiDao:YemekRuyasiDao,var yemeklerFavoriDao:YemeklerFavoriDao) {
    suspend fun tumYemekleriGetir() :List<Yemekler> =
        withContext(Dispatchers.IO){
            return@withContext yemekRuyasiDao.tumYemekleriGetir().yemekler
        }

    suspend fun sepettekiYemekleriGetir(kullanici_adi: String) : List<SepetYemekler> =
        withContext(Dispatchers.IO){
            return@withContext yemekRuyasiDao.sepettekiYemekleriGetir(kullanici_adi).sepet_yemekler
        }

    suspend fun sepeteYemekEkle(yemek:Yemekler,
                                yemek_siparis_adet:Int,
                                kullanici_adi:String){
        yemekRuyasiDao.sepeteYemekEkle(yemek.yemek_adi, yemek.yemek_resim_adi, yemek.yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }

    suspend fun sepettenYemekSil(sepet_yemek_id:Int,
                                 kullanici_adi: String) = yemekRuyasiDao.sepettenYemekSil(sepet_yemek_id, kullanici_adi)

    suspend fun favoriKaydet(favori:YemeklerFavori){
        yemeklerFavoriDao.kaydet(favori)
    }
    suspend fun favoriSil(favori:YemeklerFavori){
        yemeklerFavoriDao.sil(favori)
    }
    suspend fun favorileriYukle(email:String) : List<YemeklerFavori> =
        withContext(Dispatchers.IO){
            return@withContext yemeklerFavoriDao.favorileriYukle(email)
        }

}