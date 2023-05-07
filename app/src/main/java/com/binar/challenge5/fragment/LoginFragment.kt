package com.binar.challenge5.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.binar.challenge5.R
import com.binar.challenge5.databinding.FragmentLoginBinding
import com.binar.challenge5.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    lateinit var sharedPref : SharedPreferences
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var auth : FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences("REGISTERUSER", Context.MODE_PRIVATE)
        auth = FirebaseAuth.getInstance()
        toRegister()
        toLogin()

    }


    private fun toRegister() {
        binding.textBpa.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun toLogin(){
        binding.btnLogin.setOnClickListener {
            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()


            if(email.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }else{
                        Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            }else{
                Toast.makeText(context, "Kata sandi harus di isi", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}