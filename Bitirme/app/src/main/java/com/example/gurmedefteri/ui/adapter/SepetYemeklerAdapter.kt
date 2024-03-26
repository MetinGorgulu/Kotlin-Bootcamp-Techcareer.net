package com.example.gurmedefteri.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gurmedefteri.data.entity.SepetYemekler
import com.example.gurmedefteri.data.entity.Yemekler
import com.example.gurmedefteri.databinding.SepetCardTasarimBinding
import com.example.gurmedefteri.ui.viewmodel.SepetViewModel

class SepetYemeklerAdapter(var mContext: Context,
                           var SepetYemeklerListesi: MutableList<SepetYemekler>,
                           var viewModel: SepetViewModel)
    : RecyclerView.Adapter<SepetYemeklerAdapter.SepetCardTasarimTutucu>()  {
    inner class SepetCardTasarimTutucu(var tasarim: SepetCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetCardTasarimTutucu {
        val binding = SepetCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return SepetCardTasarimTutucu(binding)
    }



    override fun onBindViewHolder(holder: SepetCardTasarimTutucu, position: Int) {
        val sepetYemek = SepetYemeklerListesi.get(position)
        val t = holder.tasarim

        val layoutParams = holder.itemView.layoutParams as RecyclerView.LayoutParams
        layoutParams.setMargins(0, 8, 0, 8)
        if(position == itemCount - 1){
            layoutParams.setMargins(0, 8, 0, 160)
        }
        t.textViewSepetYemekadi.text = sepetYemek.yemek_adi
        t.textViewSepetYemekFiyat.text = "₺${sepetYemek.yemek_fiyat}"
        t.textViewYemekSiparisAdet.text = sepetYemek.yemek_siparis_adet.toString()
        t.textViewBasketSiparisToplamFiyat.text = "₺${(sepetYemek.yemek_fiyat * sepetYemek.yemek_siparis_adet)}"
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(400,300).into(t.imageViewSepetResim)


        t.imageViewBasketButtonDelete.setOnClickListener {
            var genelToplam =viewModel.genelToplam.value ?: 0
            genelToplam -= (sepetYemek.yemek_fiyat * sepetYemek.yemek_siparis_adet)
            viewModel.genelToplam.value = genelToplam
            SepetYemeklerListesi.removeAt(position)
            viewModel.sepettenYemekSil(sepetYemek)
            notifyDataSetChanged()

        }
    }

    override fun getItemCount(): Int {
        return SepetYemeklerListesi.size
    }
}