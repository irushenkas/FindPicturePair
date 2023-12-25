package com.example.findpicturepair.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.example.findpicturepair.game.Money
import com.example.findpicturepair.game.PictureGrid

class PlayFragment : Fragment() {

    private var seconds = 0

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

        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                activity?.runOnUiThread {
                    binding.timer.text = secondsToTime(seconds)
                    seconds++
                }
                mainHandler.postDelayed(this, 1000)
            }
        })

        return binding.root
    }

    fun secondsToTime(secondsAll: Int): String {
        val minutes = secondsAll / 60
        val seconds = secondsAll % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cellCount = 20
        val pictureGrid = PictureGrid(cellCount)
        pictureGrid.fillItems()

        val gridLayout = view?.findViewById<GridLayout>(R.id.playTable)
        var previousView: ImageView? = null

        for (i in 0 until cellCount) {
            val imageView = createImageView()

            imageView.setOnClickListener {v ->
                val isFound = pictureGrid.isCellFound(i)
                if(isFound == null || isFound) {
                    return@setOnClickListener
                }

                val isPictureVisible = pictureGrid.isCellPictureVisible(i)
                if(isPictureVisible == null || isPictureVisible) {
                    return@setOnClickListener
                }

                val pictureNumber = pictureGrid.getItemPictureNumber(i)
                if(pictureNumber == null) {
                    return@setOnClickListener
                }
                setImageViewResources(imageView, pictureNumber)

                if(pictureGrid.getPreviousNumber() == null) {
                    pictureGrid.setCellPictureVisible(i, true)
                    pictureGrid.setPreviousNumber(i)
                    previousView = imageView
                    return@setOnClickListener
                }

                if(pictureGrid.isPairWithPrevious(i)) {
                    pictureGrid.setCellPictureVisible(i, true)
                    pictureGrid.setCellFound(i)

                    val previousNumber = pictureGrid.getPreviousNumber()
                    if(previousNumber == null) {
                        return@setOnClickListener
                    }

                    val previousPictureNumber = pictureGrid.getItemPictureNumber(previousNumber)
                    if(previousPictureNumber == null) {
                        return@setOnClickListener
                    }
                    if(previousView == null) {
                        return@setOnClickListener
                    }
                    setImageViewResources(previousView, previousPictureNumber)

                    pictureGrid.setCellPictureVisible(previousNumber, true)
                    pictureGrid.setCellFound(previousNumber)

                    pictureGrid.setPreviousNumber(null)
                    previousView = null
                } else {
                    Handler().postDelayed(Runnable {
                        imageView.setImageResource(R.drawable.corner_cell)
                        pictureGrid.setCellPictureVisible(i, false)

                        val previousNumber = pictureGrid.getPreviousNumber()
                        if(previousNumber != null) {
                            previousView?.setImageResource(R.drawable.corner_cell)
                            pictureGrid.setCellPictureVisible(previousNumber, false)
                        }
                        pictureGrid.setPreviousNumber(null)
                        previousView = null
                    }, 500)
                }

                if(pictureGrid.isFinished()) {
                    val money = Money(seconds)
                    val bundle = Bundle()
                    bundle.putInt("win", money.countMoney())

                    findNavController()
                        .navigate(
                            R.id.action_playFragment_to_finishFragment,
                            bundle
                        )
                }
            }

            gridLayout?.addView(imageView)
        }
    }

    private fun createImageView(): ImageView {
        val imageView = ImageView(this.context)
        imageView.setImageResource(R.drawable.corner_cell)
        imageView.adjustViewBounds = true

        val layoutParams = LinearLayout.LayoutParams(170, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(10, 10, 10, 10);
        imageView.layoutParams = layoutParams

        return imageView
    }

    private fun setImageViewResources(imageView: ImageView?, pictureNumber: Int) {
        val previousDrawableId = resources.getIdentifier(
            "image$pictureNumber",
            "drawable",
            this.requireContext().packageName
        )
        imageView?.setImageResource(previousDrawableId)
    }
}