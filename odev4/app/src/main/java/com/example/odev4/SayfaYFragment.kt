package com.example.odev4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.example.odev4.databinding.FragmentSayfaYBinding


class SayfaYFragment : Fragment() {

    private lateinit var binding : FragmentSayfaYBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSayfaYBinding.inflate(inflater,container,false)

        val geriTusu = object : OnBackPressedCallback(true){//Geri dönüş aktif değil
        override fun handleOnBackPressed() {
            Log.e("Detay Sayfa : ", "Geri Tuşu Tıklandı")
            val anasayfaDonus = SayfaYFragmentDirections.sayfaYToAnasayfa()

            Navigation.findNavController(requireView()).navigate(anasayfaDonus)



            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,geriTusu)


        return binding.root
    }

}