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
import androidx.navigation.Navigation
import com.example.gurmedefteri.MainActivity
import com.example.gurmedefteri.databinding.FragmentLoginScreenBinding
import com.example.gurmedefteri.ui.viewmodel.LoginScreenViewModel
import com.example.gurmedefteri.utils.gecis
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginScreenFragment : Fragment() {
    private lateinit var binding: FragmentLoginScreenBinding
    private lateinit var viewModel: LoginScreenViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginScreenBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()



        binding.buttonGiris.setOnClickListener {
            val email = binding.editTextUserName.text.toString()
            val pass = binding.editTextPassword.text.toString()

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
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                } else {
                    viewModel.snackBar(it,"E posta veya şifre Hatalı")

                }
            }







        }

        binding.textViewHesapOlustur.setOnClickListener {
            val gecis = LoginScreenFragmentDirections.loginToRegister()
            Navigation.gecis(it,gecis)
        }



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:LoginScreenViewModel by viewModels()
        viewModel = tempViewModel
        auth = Firebase.auth
    }
    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9_+.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+")
        return !emailRegex.matches(email)
    }
}