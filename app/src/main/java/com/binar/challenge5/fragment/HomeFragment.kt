package com.binar.challenge5.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.challenge5.R
import com.binar.challenge5.adapter.MovieAdapter
import com.binar.challenge5.databinding.FragmentHomeBinding
import com.binar.challenge5.viewmodel.ViewModelMovie


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("REGISTERUSER", Context.MODE_PRIVATE)

        var getUsername = sharedPreferences.getString("USER", "")
        binding.txtWelcomeUsername.text = "Wellcome, $getUsername"

        binding.imgProfile.setOnClickListener {
            var addUser = sharedPreferences.edit()
            addUser.putString("USER", getUsername)
            addUser.apply()
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_profileFragment)
        }

    }

    override fun onStart() {
        super.onStart()
        val movieViewModel = ViewModelProvider(this).get(ViewModelMovie::class.java)
        movieViewModel.getMovie()
        movieViewModel.liveDataMovie.observe(viewLifecycleOwner, Observer {
            if(it != null){
                binding.movieRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.movieRecyclerView.adapter = MovieAdapter(it)
            }
        })

    }


}