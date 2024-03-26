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
import com.example.gurmedefteri.ui.viewmodel.FavoritesViewModel
import com.example.gurmedefteri.ui.viewmodel.HomePageViewModel
import com.example.gurmedefteri.utils.gecis

class FavoritesAdapter (
    var mContext: Context,
    var viewModel: FavoritesViewModel,
    var favorilerListesi:MutableList<YemeklerFavori>,
    var activity: Activity
)
    : RecyclerView.Adapter<FavoritesAdapter.YemeklerCardTasarimTutucu>() {
    inner class YemeklerCardTasarimTutucu(var tasarim: YemeklerCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemeklerCardTasarimTutucu {
        val binding = YemeklerCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return YemeklerCardTasarimTutucu(binding)
    }



    override fun onBindViewHolder(holder: YemeklerCardTasarimTutucu, position: Int) {
        val yemek = favorilerListesi.get(position)
        val t = holder.tasarim
        t.textViewYemekAd.text = yemek.yemek_adi
        t.textView3.text = "₺${yemek.yemek_fiyat}"
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"

        val layoutParams = holder.itemView.layoutParams as RecyclerView.LayoutParams
        if (position % 2 == 0) {
            layoutParams.setMargins(32, 16, 16, 16)
        }else {
            layoutParams.setMargins(16, 16, 32, 16)
        }


        holder.itemView.layoutParams = layoutParams
        Glide.with(mContext).load(url).override(400,300).into(t.imageViewResim)




        t.imageButtonFavorite.setOnClickListener {
            viewModel.favoriSil(yemek)
            favorilerListesi.removeAt(position)
            notifyDataSetChanged()
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
        return favorilerListesi.size
    }


}