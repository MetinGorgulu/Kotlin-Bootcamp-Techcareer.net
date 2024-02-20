package com.example.odev4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.example.odev4.databinding.FragmentAnasayfaBinding


class AnasayfaFragment : Fragment() {
    private lateinit var binding : FragmentAnasayfaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAnasayfaBinding.inflate(inflater , container , false)

        binding.gitAButton.setOnClickListener(){

            val aGecis = AnasayfaFragmentDirections.anasayfaToSayfaA()

            Navigation.findNavController(it).navigate(aGecis)
        }

        binding.gitXButton.setOnClickListener(){

            val xGecis = AnasayfaFragmentDirections.anasayfaToSayfaX()

            Navigation.findNavController(it).navigate(xGecis)
        }

        val geriTusu = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.e("Detay Sayfa : ", "Geri Tuşu Tıklandı")

                requireActivity().finish()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, geriTusu)



        return binding.root
    }

}