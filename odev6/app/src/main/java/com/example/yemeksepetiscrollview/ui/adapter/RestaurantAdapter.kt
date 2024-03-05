package com.example.yemeksepetiscrollview.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeksepetiscrollview.data.entity.Restaurants
import com.example.yemeksepetiscrollview.databinding.CardTasarimBinding

class RestaurantAdapter(var mContext: Context, var restaurantListesi:List<Restaurants>)
    : RecyclerView.Adapter<RestaurantAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(var tasarim: CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = CardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {//0,1,2
        val restaurant = restaurantListesi.get(position)
        val t = holder.tasarim


        t.imageViewResim1.setImageResource(
            mContext.resources.getIdentifier(restaurant.resim,"drawable",mContext.packageName))
        t.textViewRestoranAd.text = restaurant.ad
        t.textViewSure.text = restaurant.sure
        t.textViewYildiz.text = restaurant.yildiz
        t.textViewMinimumSepet.text = restaurant.minsepet
        t.textViewSiparisSayi.text = restaurant.siparisSayi


    }

    override fun getItemCount(): Int {
        return restaurantListesi.size
    }
}