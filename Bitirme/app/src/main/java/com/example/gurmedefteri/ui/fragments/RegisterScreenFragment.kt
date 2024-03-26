package com.example.gurmedefteri.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gurmedefteri.MainActivity
import com.example.gurmedefteri.databinding.FragmentRegisterScreenBinding
import com.example.gurmedefteri.ui.activities.LoginActivity
import com.example.gurmedefteri.ui.viewmodel.RegisterScreenViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterScreenFragment : Fragment() {
    private lateinit var binding: FragmentRegisterScreenBinding
    private lateinit var viewModel: RegisterScreenViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterScreenBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()



        binding.buttonKayit.setOnClickListener {
            val email = binding.editTextRegisterEmail.text.toString()
            val pass = binding.editTextRegisterPassword.text.toString()

            if (TextUtils.isEmpty(email)) {
                viewModel.snackBar(it,"email boş bırakılamaz")
                return@setOnClickListener
            }


            if (TextUtils.isEmpty(pass)) {
                viewModel.snackBar(it,"Password Boş Bırakılamaz")
                return@setOnClickListener
            }
            if (pass.length < 6) {
                viewModel.snackBar(it,"Password 6 haneden düşük olamaz")
                return@setOnClickListener
            }
            if(isValidEmail(email)){
                viewModel.snackBar(it,"Lütfen Gerçek Bir Mail Adresi Giriniz")
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                if(it.isSuccessful){
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                } else{
                    viewModel.snackBar(requireView(),"Bu Kullanıcı Zaten Var")
                }
            }

        }





        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:RegisterScreenViewModel by viewModels()
        viewModel = tempViewModel
        auth = Firebase.auth
    }
    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9_+.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+")
        return !emailRegex.matches(email)
    }
}