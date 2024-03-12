package com.example.todoapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.todoapplication.R
import com.example.todoapplication.databinding.FragmentYapilacakKayitBinding
import com.example.todoapplication.ui.viewmodel.YapilacakKayitViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YapilacakKayitFragment : Fragment() {
    private lateinit var binding: FragmentYapilacakKayitBinding
    private lateinit var viewModel: YapilacakKayitViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYapilacakKayitBinding.inflate(inflater, container, false)

        binding.buttonKaydet.setOnClickListener {
            val yapilacak_yapilacak = binding.editTextYapilacak.text.toString()
            viewModel.kaydet(yapilacak_yapilacak)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:YapilacakKayitViewModel by viewModels()
        viewModel = tempViewModel
    }
}