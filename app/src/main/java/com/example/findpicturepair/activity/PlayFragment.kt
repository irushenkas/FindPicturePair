package com.example.findpicturepair.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.findpicturepair.R
import com.example.findpicturepair.databinding.FragmentPlayBinding
import com.example.findpicturepair.databinding.FragmentStartBinding

class PlayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPlayBinding.inflate(
            inflater,
            container,
            false
        )


//        binding.finish.setOnClickListener{
//            findNavController()
//                .navigate(
//                    R.id.action_playFragment_to_finishFragment
//                )
//        }
        return binding.root
    }
}