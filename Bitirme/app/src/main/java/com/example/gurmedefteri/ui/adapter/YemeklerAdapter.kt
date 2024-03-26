package com.example.gurmedefteri.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gurmedefteri.R
import com.example.gurmedefteri.data.entity.Yemekler
import com.example.gurmedefteri.data.entity.YemeklerFavori
import com.example.gurmedefteri.databinding.YemeklerCardTasarimBinding
import com.example.gurmedefteri.ui.fragments.HomePageFragmentDirections
import com.example.gurmedefteri.ui.viewmodel.HomePageViewModel
import com.example.gurmedefteri.utils.gecis
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class YemeklerAdapter(
    var mContext: Context,
    var yemeklerListesi:List<Yemekler>,
    var viewModel:HomePageViewModel,
    var lifecycleOwner:LifecycleOwner,
    var favorilerListesi:MutableList<YemeklerFavori>,
    var activity: Activity)
    : RecyclerView.Adapter<YemeklerAdapter.YemeklerCardTasarimTutucu>() {
    inner class YemeklerCardTasarimTutucu(var tasarim: YemeklerCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemeklerCardTasarimTutucu {
        val binding = YemeklerCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        viewModel.sepettekiYemekleriGetir()
        return YemeklerCardTasarimTutucu(binding)
    }



    override fun onBindViewHolder(holder: YemeklerCardTasarimTutucu, position: Int) {
        var auth: FirebaseAuth = Firebase.auth
        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim
        t.textViewYemekAd.text = yemek.yemek_adi
        t.textView3.text = "₺${yemek.yemek_fiyat}"
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"

        val layoutParams = holder.itemView.layoutParams as RecyclerView.LayoutParams
        if (position % 2 == 0) {
            layoutParams.setMargins(32, 16, 16, 16)
        }else if(position == itemCount - 2){
            layoutParams.setMargins(32, 16, 16, 160)
        }
        else if(position == itemCount - 1){
            layoutParams.setMargins(16, 16, 32, 160)
        }else {
            layoutParams.setMargins(16, 16, 32, 16)
        }


        holder.itemView.layoutParams = layoutParams
        Glide.with(mContext).load(url).override(400,300).into(t.imageViewResim)




        t.imageButtonFavorite.setOnClickListener {
            if(viewModel.checkUserSession()){
                var favMi = false
                lateinit var favoriYemek:YemeklerFavori
                var endeks = 0
                for ((indeks, eleman) in favorilerListesi.withIndex()) {
                    if(eleman.yemek_id == yemek.yemek_id){
                        favoriYemek = YemeklerFavori(eleman.user_id,eleman.yemek_id,eleman.yemek_adi,eleman.yemek_resim_adi,eleman.yemek_fiyat,eleman.email)
                        viewModel.favoriSil(favoriYemek)
                        endeks = indeks
                        t.imageButtonFavorite.setImageResource(R.drawable.ic_unfavorite)
                        favMi = true
                    }
                }
                if(!favMi){
                    favoriYemek = YemeklerFavori(0,yemek.yemek_id,yemek.yemek_adi,yemek.yemek_resim_adi,yemek.yemek_fiyat,auth.currentUser?.email.toString())

                    viewModel.favoriKaydet(favoriYemek)
                    favorilerListesi.add(favoriYemek)
                    t.imageButtonFavorite.setImageResource(R.drawable.ic_favorite)
                }else{
                    favorilerListesi.removeAt(endeks)
                }
            }else{
                viewModel.firstlLogin(activity)
            }


            notifyDataSetChanged()
        }




        t.cardViewYemek.setOnClickListener {
            val gecis = HomePageFragmentDirections.homeToDetail(yemekler = yemek)
            Navigation.gecis(it,gecis)
        }

        for (eleman in favorilerListesi){
            if(eleman.yemek_id == yemek.yemek_id){
                t.imageButtonFavorite.setImageResource(R.drawable.ic_favorite)
                return
            }else{
                t.imageButtonFavorite.setImageResource(R.drawable.ic_unfavorite)
            }
        }

    }


    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }


}