package com.example.gurmedefteri.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.example.gurmedefteri.MainActivity
import com.example.gurmedefteri.R
import com.example.gurmedefteri.databinding.FragmentProfileBinding
import com.example.gurmedefteri.ui.activities.LoginActivity
import com.example.gurmedefteri.ui.viewmodel.ProfileViewModel
import com.example.gurmedefteri.ui.viewmodel.RegisterScreenViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var auth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentProfileBinding.inflate(inflater,container,false)
        if(viewModel.checkUserSession()){
            binding.profileView.visibility = View.VISIBLE
        }
        auth = FirebaseAuth.getInstance()
        binding.buttonCikis.setOnClickListener {
            auth.signOut()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        binding.textView5.text = auth.currentUser?.email.toString()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProfileViewModel by viewModels()
        viewModel = tempViewModel
        auth = Firebase.auth



        if(viewModel.checkUserSession()){

        }else{
            viewModel.firstlLogin(requireActivity())
        }

    }

}