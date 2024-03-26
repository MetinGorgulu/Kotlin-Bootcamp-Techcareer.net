package com.example.gurmedefteri.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gurmedefteri.R
import com.example.gurmedefteri.data.entity.SepetYemekler
import com.example.gurmedefteri.databinding.FragmentSepetBinding
import com.example.gurmedefteri.ui.adapter.SepetYemeklerAdapter
import com.example.gurmedefteri.ui.viewmodel.SepetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private lateinit var binding: FragmentSepetBinding
    private lateinit var viewModel : SepetViewModel
    private lateinit var list: MutableList<SepetYemekler>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner){
            try {
                list = viewModel.sepetYemeklerListesi.value as MutableList<SepetYemekler>
                genelToplamBul()
                val sepetYemeklerAdapter = SepetYemeklerAdapter(requireContext(), list, viewModel)
                binding.sepetYemeklerRV.adapter = sepetYemeklerAdapter
                binding.sepetYemeklerRV.layoutManager = LinearLayoutManager(requireContext())
            }catch (e:Exception){

            }

        }

        viewModel.genelToplam.observe(viewLifecycleOwner){
            binding.textViewSepetToplam.text ="₺$it"
        }
        binding = FragmentSepetBinding.inflate(inflater,container,false)




        return binding.root
    }
    fun genelToplamBul(){
        var toplam = 0
        for (x in list){
            toplam += (x.yemek_fiyat * x.yemek_siparis_adet)
        }
        viewModel.genelToplam.value=toplam
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SepetViewModel by viewModels()
        viewModel = tempViewModel

    }
    override fun onResume() {
        super.onResume()
        viewModel.sepettekiYemekleriGetir()
    }
}