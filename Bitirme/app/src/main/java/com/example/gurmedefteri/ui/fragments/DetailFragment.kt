package com.example.gurmedefteri.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.gurmedefteri.R
import com.example.gurmedefteri.data.entity.SepetYemekler
import com.example.gurmedefteri.data.entity.Yemekler
import com.example.gurmedefteri.data.entity.YemeklerFavori
import com.example.gurmedefteri.databinding.FragmentDetailBinding
import com.example.gurmedefteri.ui.viewmodel.DetailViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment() : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel : DetailViewModel
    lateinit var favorilerListesi: MutableList<YemeklerFavori>
    private lateinit var auth:FirebaseAuth
    private var list : MutableList<SepetYemekler> = mutableListOf()
    private var list1 : MutableLiveData<MutableList<SepetYemekler>> = MutableLiveData()
    private var sepetteVarMi = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {viewModel.favorileriYukle()

        val bundle:DetailFragmentArgs by navArgs()
        val gelenYemek = bundle.yemekler
        viewModel.coroutine()
        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner){
            try {
                list1.value = it as MutableList<SepetYemekler>
            }catch (e:Exception){

            }

        }
        list1.observe(viewLifecycleOwner){
            list = it
            sepetControl(gelenYemek)

        }

        viewModel.arananFavoriler.observe(viewLifecycleOwner){
            favorilerListesi = it as MutableList<YemeklerFavori>
            favoriControl(gelenYemek)

        }

        binding = FragmentDetailBinding.inflate(inflater,container,false)
        auth = FirebaseAuth.getInstance()




        binding.textViewYemekAdi.setText(gelenYemek.yemek_adi)
        binding.textViewYemekFiyat.setText(gelenYemek.yemek_fiyat.toString())
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Glide.with(requireContext()).load(url).override(400,300).into(binding.imageViewDetailResim)


        binding.imageViewDetailFavoriImage.setOnClickListener {

            if(viewModel.checkUserSession()){
                var favMi = false
                lateinit var favoriYemek:YemeklerFavori
                var endeks = 0
                for ((indeks, eleman) in favorilerListesi.withIndex()) {
                    if(eleman.yemek_id == gelenYemek.yemek_id){
                        favoriYemek = YemeklerFavori(eleman.user_id,eleman.yemek_id,eleman.yemek_adi,eleman.yemek_resim_adi,eleman.yemek_fiyat,eleman.email)
                        viewModel.favoriSil(favoriYemek)
                        endeks = indeks
                        binding.imageViewDetailFavoriImage.setImageResource(R.drawable.ic_unfavorite)
                        favMi = true
                    }
                }
                if(!favMi){
                    favoriYemek = YemeklerFavori(0,gelenYemek.yemek_id,gelenYemek.yemek_adi,gelenYemek.yemek_resim_adi,gelenYemek.yemek_fiyat,auth.currentUser?.email.toString())

                    viewModel.favoriKaydet(favoriYemek)
                    favorilerListesi.add(favoriYemek)
                    binding.imageViewDetailFavoriImage.setImageResource(R.drawable.ic_favorite)
                }else{
                    favorilerListesi.removeAt(endeks)
                }
            }else{
                viewModel.firstlLogin(requireActivity())
            }


        }
        binding.imageViewDetailPlus.setOnClickListener {
            var count = binding.textViewDetailCount.text.toString().toInt()
            if(count < 10){
                count++
                binding.textViewDetailCount.text = count.toString()
                binding.textViewYemekFiyat.text = (gelenYemek.yemek_fiyat * count).toString()
                sepetControl(gelenYemek)
            }else{
                viewModel.snackBar(requireView(), "Maksimum 10 adet sipariş verebilirsiniz")
            }
        }
        binding.imageViewDetailMinus.setOnClickListener {
            var count = binding.textViewDetailCount.text.toString().toInt()
            if (count > 1){
                count--
                binding.textViewDetailCount.text = count.toString()
                binding.textViewYemekFiyat.text = (gelenYemek.yemek_fiyat * count).toString()
                sepetControl(gelenYemek)
            }
        }

        binding.buttonSepeteEkle.setOnClickListener {
            userControl()
            binding.buttonSepeteEkle.isEnabled =false
            val quantity = binding.textViewDetailCount.text.toString().toInt()
            if(viewModel.checkUserSession()){
                if (binding.buttonSepeteEkle.text.toString() =="Güncelle"){
                    var itemsToRemove = mutableListOf<SepetYemekler>()
                    Log.e("mesaj","sildim1")
                    for (x in list) {
                        if (gelenYemek.yemek_adi == x.yemek_adi) {
                            Log.e("mesaj","sildim2")
                            itemsToRemove.add(x)
                            viewModel.sepettenYemekSil(x)
                        }
                    }

                    for (item in itemsToRemove) {
                        Log.e("mesaj","sildim3")
                        list.remove(item)
                    }
                }

                viewModel.sepeteEkle(gelenYemek , quantity,auth.currentUser?.email.toString())
                val sepetYemek = SepetYemekler(gelenYemek.yemek_id,gelenYemek.yemek_adi,gelenYemek.yemek_resim_adi,gelenYemek.yemek_fiyat,quantity,auth.currentUser?.email.toString())
                list.add(sepetYemek)
                viewModel.sepettekiYemekleriGetir()
                binding.buttonSepeteEkle.isEnabled =false
                binding.buttonSepeteEkle.text = "Güncelle"
                startAnimation()
                sepetControl(gelenYemek)


            }
        }
        return binding.root
    }
    fun sepetControl(gelenYemek:Yemekler){
        sepetteVarMi = false
        if (!list.isEmpty()){
            for (x in list){
                if(x.yemek_adi == gelenYemek.yemek_adi){
                    sepetteVarMi = true
                    binding.buttonSepeteEkle.setText("Güncelle")
                }
                if(sepetteVarMi == true){
                    if(x.yemek_siparis_adet == binding.textViewDetailCount.text.toString().toInt()){
                        binding.buttonSepeteEkle.isEnabled = false
                    }else{
                        binding.buttonSepeteEkle.isEnabled = true
                    }
                    return
                }else{
                    binding.buttonSepeteEkle.setText("Sepete Ekle")
                    binding.buttonSepeteEkle.isEnabled = true
                }
            }
        }
    }
    fun userControl() {
        if (viewModel.checkUserSession()) {
            return
        } else {
            viewModel.firstlLogin(requireActivity())

        }
    }
    fun favoriControl(gelenYemek:Yemekler){
        var favMi = false
        for (eleman in favorilerListesi) {
            if (eleman.yemek_id == gelenYemek.yemek_id) {

                favMi = true
                break
            }
        }
        if(favMi){
            binding.imageViewDetailFavoriImage.setImageResource(R.drawable.ic_favorite)
        }else{
            binding.imageViewDetailFavoriImage.setImageResource(R.drawable.ic_unfavorite)
        }
    }
    private fun startAnimation() {
        binding.buttonSepeteEkle.isEnabled =false
        binding.imageViewDetailPlus.isEnabled =false
        binding.imageViewDetailMinus.isEnabled =false
        binding.animationView.visibility = View.VISIBLE
        binding.animationView.bringToFront()
        binding.animationView.playAnimation()

        binding.animationView.postDelayed({
            binding.animationView.cancelAnimation()
            binding.imageViewDetailPlus.isEnabled =true
            binding.imageViewDetailMinus.isEnabled =true
            binding.animationView.visibility = View.GONE

        }, 3000)

    }

    fun coroutine(){
        viewLifecycleOwner.lifecycleScope.launch {
            while (true) {
                viewModel.sepettekiYemekleriGetir()

                delay(1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel
        auth = Firebase.auth
        viewModel.favorileriYukle()
        viewModel.sepettekiYemekleriGetir()
    }

    override fun onResume() {
        super.onResume()
        viewModel.favorileriYukle()
        viewModel.sepettekiYemekleriGetir()
        viewModel.coroutine()

    }

}