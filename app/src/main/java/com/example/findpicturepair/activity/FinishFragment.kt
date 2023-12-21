package com.example.findpicturepair.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.findpicturepair.R
import com.example.findpicturepair.databinding.FragmentFinishBinding
import com.example.findpicturepair.databinding.FragmentStartBinding

class FinishFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFinishBinding.inflate(
            inflater,
            container,
            false
        )


        binding.home.setOnClickListener{
            findNavController()
                .navigate(
                    R.id.action_finishFragment_to_startFragment
                )
        }

        return binding.root
    }
}