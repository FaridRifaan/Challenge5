package com.binar.challenge5.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.binar.challenge5.R
import com.binar.challenge5.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {
    lateinit var sharedPref : SharedPreferences
    lateinit var binding: FragmentProfileBinding
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences("REGISTERUSER", Context.MODE_PRIVATE)

        binding.btnUpdate.setOnClickListener {
           toUpdate()
        }

        binding.btnLogout.setOnClickListener {
          toLogout()
        }
    }

    private fun toUpdate(){
        val updateUsername = binding.etUsername.text.toString()
        val namaLengkap = binding.etNamaLengkap.text.toString()
        val ttl = binding.etTanggalLahir.text.toString()
        val alamat = binding.etAlamat.text.toString()
        var adduser = sharedPref.edit()
        adduser.putString("USER", updateUsername)
        adduser.apply()
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        Toast.makeText(context, "Username Berhasil di update", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(binding.root).navigate(R.id.action_profileFragment_to_homeFragment)

    }


    private fun toLogout(){
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        Toast.makeText(context, "Anda telah logout :)", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(binding.root).navigate(R.id.action_profileFragment_to_loginFragment)


    }

}