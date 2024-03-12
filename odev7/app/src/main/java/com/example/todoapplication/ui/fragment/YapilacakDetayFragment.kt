package com.example.todoapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.todoapplication.R
import com.example.todoapplication.databinding.FragmentYapilacakDetayBinding
import com.example.todoapplication.ui.viewmodel.YapilacakDetayViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YapilacakDetayFragment : Fragment() {
    private lateinit var binding: FragmentYapilacakDetayBinding
    private lateinit var viewModel: YapilacakDetayViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYapilacakDetayBinding.inflate(inflater, container, false)

        val bundle:YapilacakDetayFragmentArgs by navArgs()
        val gelenYapilacak = bundle.yapilacak

        binding.editTextYapilacak.setText(gelenYapilacak.yapilacak_yapilacak)

        binding.buttonGuncelle.setOnClickListener {
            val yapilacak_yapilacak = binding.editTextYapilacak.text.toString()
            viewModel.guncelle(gelenYapilacak.yapilacak_id,yapilacak_yapilacak)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: YapilacakDetayViewModel by viewModels()
        viewModel = tempViewModel
    }
}