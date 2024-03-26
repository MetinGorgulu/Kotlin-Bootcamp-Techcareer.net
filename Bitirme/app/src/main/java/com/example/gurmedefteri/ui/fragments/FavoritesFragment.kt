package com.example.gurmedefteri.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gurmedefteri.R
import com.example.gurmedefteri.data.entity.YemeklerFavori
import com.example.gurmedefteri.databinding.FragmentFavoritesBinding
import com.example.gurmedefteri.databinding.FragmentHomePageBinding
import com.example.gurmedefteri.ui.adapter.FavoritesAdapter
import com.example.gurmedefteri.ui.adapter.YemeklerAdapter
import com.example.gurmedefteri.ui.viewmodel.FavoritesViewModel
import com.example.gurmedefteri.ui.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var favorilerList: MutableList<YemeklerFavori>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater,container,false)

        viewModel.arananFavoriler.observe(viewLifecycleOwner){
            favorilerList = it as MutableList<YemeklerFavori>
            val favoritesAdapter = FavoritesAdapter(requireContext(),viewModel,favorilerList,requireActivity())
            binding.favorilerRV.adapter = favoritesAdapter
            binding.favorilerRV.apply {
                layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false) // İki sütunlu bir GridLayoutManager kullanılıyor
                adapter = favoritesAdapter
            }
        }
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FavoritesViewModel by viewModels()
        viewModel = tempViewModel
        viewModel.favorileriYukle()
    }
    override fun onResume() {
        super.onResume()

        viewModel.favorileriYukle()
    }

}