package com.example.findpicturepair.game

class PictureGrid {
    var items: List<PictureCell> = ArrayList<PictureCell>()

    fun fillItems() {

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