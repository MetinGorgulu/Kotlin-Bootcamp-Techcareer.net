package com.example.odev5.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.odev5.R
import java.util.*
import com.example.odev5.databinding.FragmentAnasayfaBinding
import java.util.Arrays

class AnasayfaFragment : Fragment() {
    private lateinit var binding : FragmentAnasayfaBinding
    private val toplanacaklar = ArrayList<String>()
    var text:String = "0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)


        binding.button0.setOnClickListener {
            addToText("0")

        }
        binding.button1.setOnClickListener {
            addToText("1")

        }
        binding.button2.setOnClickListener {
            addToText("2")

        }
        binding.button3.setOnClickListener {
            addToText("3")

        }
        binding.button4.setOnClickListener {
            addToText("4")

        }
        binding.button5.setOnClickListener {
            addToText("5")

        }
        binding.button6.setOnClickListener {

            addToText("6")
        }
        binding.button7.setOnClickListener {
            addToText("7")

        }
        binding.button8.setOnClickListener {
            addToText("8")

        }
        binding.button9.setOnClickListener {
            addToText("9")

        }
        binding.buttonC.setOnClickListener {

            text = if (text.isNotEmpty()&& text.length != 1) text.substring(0, text.length - 1) else "0"
            binding.textView.text = text
        }
        binding.buttonPlus.setOnClickListener {
            if(text != "0"){
                toplanacaklar.add(text)
                toplanacaklarListele()
                textClean()
            }


        }
        binding.buttonEqual.setOnClickListener {
            toplaButunSayilar()

        }
        binding.buttonReset.setOnClickListener {
            resetle()
        }

        return binding.root
    }
    fun addToText (word:String){
        if(text =="0") {
            text = word
        }else{
            text += word
        }

        binding.textView.text = text
    }
    fun toplanacaklarListele(){
        for ((index , element) in toplanacaklar.withIndex()){
            var reText = binding.textView2.text.toString()
            if (index == 0){
                reText = element
                binding.textView2.text = reText
            }else{
                reText += "+"
                reText += element
                binding.textView2.text = reText
            }
        }
    }
    fun textClean(){
        text = "0"
        binding.textView.text = text

    }
    fun toplaButunSayilar(){
        var toplam = 0
        for (element in toplanacaklar){
            toplam += element.toInt()
        }
        toplanacaklar.clear()
        binding.textView2.text = toplam.toString()
        toplanacaklar.add(toplam.toString())
    }
    fun resetle(){
        text = "0"
        toplanacaklar.clear()
        binding.textView.text = text
        binding.textView2.text = "0"
    }






}