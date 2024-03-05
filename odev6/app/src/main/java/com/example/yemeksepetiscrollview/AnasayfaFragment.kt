package com.example.yemeksepetiscrollview

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.yemeksepetiscrollview.data.entity.Mutfaklar
import com.example.yemeksepetiscrollview.data.entity.Restaurants
import com.example.yemeksepetiscrollview.databinding.FragmentAnasayfaBinding
import com.example.yemeksepetiscrollview.ui.adapter.MutfakAdapter
import com.example.yemeksepetiscrollview.ui.adapter.RestaurantAdapter


class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)

        val restaurantListesi = ArrayList<Restaurants>()
        val mutfakListesi = ArrayList<Mutfaklar>()
        val f1 = Restaurants(1,"Komagene Etsiz Çiğ Köf...","restoran1","50 dakika","3.9",
            "₺ - Minimum 40 TL - Çiğ Köfte","(100+)")
        val f2 = Restaurants(2,"Muammer Usta Pizza Ve...","restoran2","35 dakika","4.2",
            "₺ - Minimum 80 TL - Lahmacun","(500+)")
        val f3 = Restaurants(3,"Katık Döner","restoran3","30 dakika", "4.1",
            "₺ - Minimum 80 TL - Tavuk","(800+)")
        //val f4 = Restaurants(4,"The Hateful Eight","thehatefuleight",28)
        //val f5 = Restaurants(5,"The Pianist","thepianist",18)
        //val f6 = Restaurants(6,"Anadoluda","anadoluda",10)
        restaurantListesi.add(f1)
        restaurantListesi.add(f2)
        restaurantListesi.add(f3)
        //resaurantListesi.add(f4)
        //resaurantListesi.add(f5)
        //resaurantListesi.add(f6)
        val m1 = Mutfaklar(1,"Pizza" , "mutfak1")
        val m2 = Mutfaklar(2,"Tatlı" , "mutfak2")
        val m3 = Mutfaklar(3,"Burger" , "mutfak3")
        val m4 = Mutfaklar(4,"Tavuk" , "mutfak4")
        val m5 = Mutfaklar(5,"Kebab & Türk Mutfağı" , "mutfak5")
        val m6 = Mutfaklar(6,"Tost & Sandviç" , "mutfak6")
        val m7 = Mutfaklar(7,"Dünya Mutfağı" , "mutfak7")
        val m8 = Mutfaklar(8,"Çiğ Köfte" , "mutfak8")
        mutfakListesi.add(m1)
        mutfakListesi.add(m2)
        mutfakListesi.add(m3)
        mutfakListesi.add(m4)
        mutfakListesi.add(m5)
        mutfakListesi.add(m6)
        mutfakListesi.add(m7)
        mutfakListesi.add(m8)

        val restaurantAdapter = RestaurantAdapter(requireContext(), restaurantListesi )
        val mutfakAdapter = MutfakAdapter(requireContext(), mutfakListesi)
        binding.oncekiSiparisRV.adapter = restaurantAdapter
        binding.mutfakRV.adapter = mutfakAdapter

        binding.oncekiSiparisRV.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        binding.mutfakRV.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        binding.oncekiSiparisRV.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.left = spacingInPixels
                outRect.right = spacingInPixels
            }
        })
        val boslukMutfak = resources.getDimensionPixelSize(R.dimen.bosluk)
        binding.mutfakRV.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.left = boslukMutfak
                outRect.right = boslukMutfak
                outRect.top = boslukMutfak
            }
        })


        return binding.root
    }

}