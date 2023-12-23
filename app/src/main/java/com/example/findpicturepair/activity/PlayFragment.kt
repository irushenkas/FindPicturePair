package com.example.findpicturepair.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.findpicturepair.R
import com.example.findpicturepair.databinding.FragmentPlayBinding
import com.example.findpicturepair.game.PictureGrid


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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cellCount = 20
        val pictureGrid = PictureGrid(cellCount)
        pictureGrid.fillItems()

        val gridLayout = view?.findViewById<GridLayout>(R.id.playTable)
        for (i in 0 until cellCount) {
            val imageView = ImageView(this.context)
            imageView.setImageResource(R.drawable.corner_cell)
            imageView.adjustViewBounds = true

            val layoutParams = LinearLayout.LayoutParams(170, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(10, 10, 10, 10);
            imageView.layoutParams = layoutParams

            imageView.setOnClickListener {view ->
                val isFound = pictureGrid.isCellFound(i)
                if(isFound == null || isFound) {
                    return@setOnClickListener
                }

                val isPictureVisible = pictureGrid.isCellPictureVisible(i)
                if(isPictureVisible == null) {
                    return@setOnClickListener
                }
                if(isPictureVisible) {
                    imageView.setImageResource(R.drawable.corner_cell)
                    pictureGrid.setCellPictureVisible(i, false)
                } else {
                    val pictureNumber = pictureGrid.getItemPictureNumber(i)
                    if(pictureNumber != null) {
                        val drawableId = resources.getIdentifier(
                            "image$pictureNumber",
                            "drawable",
                            this.requireContext().packageName
                        )
                        imageView.setImageResource(drawableId)
                        pictureGrid.setCellPictureVisible(i, true)
                    }
                }

                if(pictureGrid.isFinished()) {
                    findNavController()
                        .navigate(
                            R.id.action_playFragment_to_finishFragment
                        )
                }
            }

            gridLayout?.addView(imageView)
        }
    }
}