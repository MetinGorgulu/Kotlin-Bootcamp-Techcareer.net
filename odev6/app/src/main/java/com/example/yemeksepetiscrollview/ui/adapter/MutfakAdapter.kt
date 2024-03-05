package com.example.yemeksepetiscrollview.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeksepetiscrollview.data.entity.Mutfaklar
import com.example.yemeksepetiscrollview.databinding.MutfakCardTasarimBinding

class MutfakAdapter(var mContext: Context, var mutfakListesi:List<Mutfaklar>)
    : RecyclerView.Adapter<MutfakAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(var tasarim: MutfakCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = MutfakCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {//0,1,2
        val mutfak = mutfakListesi.get(position)
        val t = holder.tasarim


        t.imageView.setImageResource(
            mContext.resources.getIdentifier(mutfak.resim,"drawable",mContext.packageName))
        t.textViewAd.text = mutfak.ad


    }

    override fun getItemCount(): Int {
        return mutfakListesi.size
    }
}