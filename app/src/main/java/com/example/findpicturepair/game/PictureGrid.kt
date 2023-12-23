package com.example.findpicturepair.game

class PictureGrid(private val cellCount: Int) {
    private val pictureCount: Int = cellCount / 2
    private var items: MutableList<PictureCell> = ArrayList()
    private var previousCellNumber: Int = -1

    fun fillItems() {
        val pictures: MutableList<Int> = ArrayList(cellCount)
        for(i in 0 until pictureCount) {
            pictures.add(i)
            pictures.add(i)
        }
        val picturesShuffled = pictures.shuffled()

        for(i in 0 until cellCount) {
            val cell = PictureCell()
            cell.pictureNumber = picturesShuffled.get(i)
            items.add(i, cell)
        }
    }

    fun getItemPictureNumber(cellNumber: Int): Int? {
        if(cellNumber >= cellCount) {
            return null
        }
        return items[cellNumber].pictureNumber
    }

    fun isCellFound(cellNumber: Int): Boolean? {
        if(cellNumber >= cellCount) {
            return null
        }
        return items[cellNumber].isFound
    }

    fun isCellPictureVisible(cellNumber: Int): Boolean? {
        if(cellNumber >= cellCount) {
            return null
        }
        return items[cellNumber].isVisible
    }

    fun setCellPictureVisible(cellNumber: Int) {
        if(cellNumber >= cellCount) {
            return
        }

        if(previousCellNumber == -1) {
            previousCellNumber = cellNumber
            items[cellNumber].isVisible = true
            return
        }

        if(items[cellNumber].pictureNumber == items[previousCellNumber].pictureNumber) {

        } else {
            items[cellNumber].isVisible = true
        }
        previousCellNumber = cellNumber
    }

    fun setCellPictureVisible(cellNumber: Int, visible: Boolean) {
        if(cellNumber >= cellCount) {
            return
        }
        items[cellNumber].isVisible = visible
    }

    fun isFinished(): Boolean {
        for (item: PictureCell in items) {
            if(!item.isFound) {
                return false
            }
        }
        return true
    }
}