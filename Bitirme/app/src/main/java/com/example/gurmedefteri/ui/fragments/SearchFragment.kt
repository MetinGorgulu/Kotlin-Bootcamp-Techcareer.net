package com.example.gurmedefteri.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gurmedefteri.R
import com.example.gurmedefteri.data.entity.Yemekler
import com.example.gurmedefteri.data.entity.YemeklerFavori
import com.example.gurmedefteri.databinding.FragmentSearchBinding
import com.example.gurmedefteri.ui.adapter.SearchAdapter
import com.example.gurmedefteri.ui.adapter.YemeklerAdapter
import com.example.gurmedefteri.ui.viewmodel.HomePageViewModel
import com.example.gurmedefteri.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.ObjectStreamException
import java.util.ArrayList
import java.util.Locale

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var tumYemekler: MutableList<Yemekler>
    private var searchesFoods : MutableLiveData<List<Yemekler>> = MutableLiveData<List<Yemekler>>()
    private var favorilerList: MutableList<YemeklerFavori> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        if (!::tumYemekler.isInitialized) {
            tumYemekler = mutableListOf()
        }
       viewModel.yemeklerListesi.observe(viewLifecycleOwner){
           try {
               tumYemekler = it as MutableList<Yemekler>
               viewModel.arananFavoriler.observe(viewLifecycleOwner){
                   favorilerList = it as MutableList<YemeklerFavori>
               }

           }catch (e:Exception){

           }

       }
        searchesFoods.observe(viewLifecycleOwner){
            val list = it
            getRV(it)
        }

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            tumYemekler = it as MutableList<Yemekler>
        }



        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val seachText = newText!!.toLowerCase(Locale.getDefault())
                val filteredList = mutableListOf<Yemekler>()
                if(seachText.isNotEmpty()){
                    for (x in tumYemekler){
                        if (x.yemek_adi.toLowerCase(Locale.getDefault()).contains(seachText)){
                            filteredList.add(x)
                        }
                    }
                }else{
                    filteredList.addAll(tumYemekler)
                }
                searchesFoods.value = filteredList
                return true
            }

        })
        return binding.root
    }
    fun getRV(list:List<Yemekler>){
        val searchAdapter = SearchAdapter(requireContext(),list,viewModel,favorilerList,requireActivity())
        binding.recyclerView.adapter = searchAdapter
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false) // İki sütunlu bir GridLayoutManager kullanılıyor
            adapter = searchAdapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SearchViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        binding.search.setQuery("",false)
        (binding.recyclerView.adapter as SearchAdapter).clear()
    }

}