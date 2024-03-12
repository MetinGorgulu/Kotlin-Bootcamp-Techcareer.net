package com.example.todoapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.data.entity.Yapilacak
import com.example.todoapplication.databinding.CardTasarimBinding
import com.example.todoapplication.ui.fragment.AnasayfaFragmentDirections
import com.example.todoapplication.ui.viewmodel.AnasayfaViewModel
import com.example.todoapplication.utils.gecis
import com.google.android.material.snackbar.Snackbar

class YapilacaklarAdapter  (var mContext: Context,
                            var yapilacaklarListesi:List<Yapilacak>,
                            var viewModel: AnasayfaViewModel
)
    :RecyclerView.Adapter<YapilacaklarAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(var tasarim: CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {

        val binding = CardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)

        return CardTasarimTutucu(binding)
    }



    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {

        val yapilacak = yapilacaklarListesi.get(position)
        val t = holder.tasarim
        t.textView.text = yapilacak.yapilacak_yapilacak

        t.cardViewSatir.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.anasayfaToDetay( yapilacak = yapilacak)
            //Navigation.findNavController(it).navigate(gecis)
            Navigation.gecis(it,gecis)
        }

        t.imageViewSil.setOnClickListener {
            Snackbar.make(it," Silmek İstediğinize Emin Misiniz?", Snackbar.LENGTH_SHORT)
                .setAction("EVET"){
                    viewModel.sil(yapilacak.yapilacak_id)
                }.show()
        }


    }

    override fun getItemCount(): Int {
        return yapilacaklarListesi.size
    }

}