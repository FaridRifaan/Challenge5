package com.binar.challenge5.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.binar.challenge5.R
import com.binar.challenge5.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {
    lateinit var sharedPrefRegis : SharedPreferences
    lateinit var binding: FragmentRegisterBinding
    lateinit var auth : FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        sharedPrefRegis = requireContext().getSharedPreferences("REGISTERUSER", Context.MODE_PRIVATE)

        binding.btnRegister.setOnClickListener {
            register()
        }

    }

    private fun register(){
        var createUsername = binding.etUsernameRegister.text.toString()
        var createEmail = binding.etEmailRegister.text.toString()
        var createPass = binding.etPasswordRegister.text.toString()
        var createRepeatPass = binding.etKonfirmPasswordRegister.text.toString()

        var addUser = sharedPrefRegis.edit()
        addUser.putString("USER", createUsername)

        if (createUsername.isNotEmpty() && createEmail.isNotEmpty() && createPass.isNotEmpty() && createRepeatPass.isNotEmpty()) {
            if (createPass == createRepeatPass) {
                // Register user with email and password
                addUser.apply()
                auth.createUserWithEmailAndPassword(createEmail, createPass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // User registered successfully
                            // Navigate to the login screen
                            Toast.makeText(context, "Berhasil Register", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                        } else {
                            // Show error message if registration fails
                            Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                // Show error message if passwords do not match
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Show error message if any field is empty
            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }

    }
}