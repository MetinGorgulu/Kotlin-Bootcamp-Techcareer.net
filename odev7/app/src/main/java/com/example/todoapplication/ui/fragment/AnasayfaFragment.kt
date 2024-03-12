package com.example.todoapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplication.R
import com.example.todoapplication.databinding.FragmentAnasayfaBinding
import com.example.todoapplication.ui.adapter.YapilacaklarAdapter
import com.example.todoapplication.ui.viewmodel.AnasayfaViewModel
import com.example.todoapplication.utils.gecis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private lateinit var binding : FragmentAnasayfaBinding
    private lateinit var  viewModel : AnasayfaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)

        viewModel.yapilacaklarListesi.observe(viewLifecycleOwner){
            val YapilacaklarAdapter = YapilacaklarAdapter(requireContext(), it, viewModel)
            binding.yapilacaklarRV.adapter = YapilacaklarAdapter
        }



        binding.yapilacaklarRV.layoutManager = LinearLayoutManager(requireContext())

        binding.fab.setOnClickListener{
            //Navigation.findNavController(it).navigate(R.id.anasayfaToKisiKayit)
            Navigation.gecis(it,R.id.anasayfa_to_kayit)

        }


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {//harf girdikçe ve sildikçe çalışır
                viewModel.ara(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {// klavyedeki arama butonu ile çalışır
                viewModel.ara(query)
                return true
            }
        })

        return binding.root



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnasayfaViewModel by viewModels()
        viewModel = tempViewModel

    }

}