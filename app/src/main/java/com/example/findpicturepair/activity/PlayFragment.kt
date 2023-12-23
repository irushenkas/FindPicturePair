package com.example.findpicturepair.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.findpicturepair.R
import com.example.findpicturepair.databinding.FragmentPlayBinding


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

//        for (i in 0..5) {
//
//        }


        binding.finish.setOnClickListener{
            findNavController()
                .navigate(
                    R.id.action_playFragment_to_finishFragment
                )
        }
        return binding.root
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //val imageView = view?.findViewById<ImageView>(R.id.playTable)
        for (i in 1..5) {
            val  imageViewResourceId = resources.getIdentifier(
                "imageView$i",
                "id",
                this.requireContext().packageName
            )
            val imageView: ImageView? = view?.findViewById(imageViewResourceId)
            if (imageView != null) {
                imageView.id = i
            }

            val drawableId = resources.getIdentifier("image$i", "drawable", this.requireContext().packageName)
            imageView?.setImageResource(drawableId)


//            val imageView = ImageView(this.context)
//            imageView.setImageResource(R.drawable.image1)
//            val layoutParams = LinearLayout.LayoutParams(160, LinearLayout.LayoutParams.WRAP_CONTENT)
//            layoutParams.setMargins(10, 10, 10, 10);
//            imageView.layoutParams = layoutParams
//            gridLayout?.addView(imageView)
        }


    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val gridLayout = view?.findViewById<GridLayout>(R.id.playTable)
//        for (i in 0..5) {
//            val imageView = ImageView(this.context)
//            imageView.setImageResource(R.drawable.image1)
//            val layoutParams = LinearLayout.LayoutParams(160, LinearLayout.LayoutParams.WRAP_CONTENT)
//            layoutParams.setMargins(10, 10, 10, 10);
//            imageView.layoutParams = layoutParams
//            gridLayout?.addView(imageView)
//        }
//
//
//    }
}