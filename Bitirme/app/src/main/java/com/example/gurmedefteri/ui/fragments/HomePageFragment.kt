package com.example.gurmedefteri.ui.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gurmedefteri.data.entity.SepetYemekler
import com.example.gurmedefteri.data.entity.YemeklerFavori
import com.example.gurmedefteri.databinding.FragmentHomePageBinding
import com.example.gurmedefteri.ui.adapter.SepetYemeklerAdapter
import com.example.gurmedefteri.ui.adapter.YemeklerAdapter
import com.example.gurmedefteri.ui.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel: HomePageViewModel
    private lateinit var favorilerList: MutableList<YemeklerFavori>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater,container,false)

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            try {
                val lis =it
                viewModel.arananFavoriler.observe(viewLifecycleOwner){
                    favorilerList = viewModel.arananFavoriler.value as MutableList<YemeklerFavori>
                    val yemeklerAdapter = YemeklerAdapter(requireContext(),lis,viewModel,this,favorilerList,requireActivity())
                    binding.yemeklerRV.adapter = yemeklerAdapter
                    binding.yemeklerRV.apply {
                        layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false) // İki sütunlu bir GridLayoutManager kullanılıyor
                        adapter = yemeklerAdapter
                    }
                }

            }catch (e:Exception){

            }

        }
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomePageViewModel by viewModels()
        viewModel = tempViewModel
        viewModel.tumYemekleriGetir()
        viewModel.favorileriYukle()
    }
    override fun onResume() {
        super.onResume()

        viewModel.favorileriYukle()
    }

}