package com.example.findpicturepair.game

class Money(private val seconds: Int) {
    private val maxWin = 100
    private val minWin = 10
    private val maxWinSeconds = 20
    private val decreaseStepSeconds = 5

    fun countMoney(): Int {
        if(seconds <= maxWinSeconds) {
            return maxWin
        }
        val diff = seconds - maxWinSeconds
        val win = maxWin - (diff * decreaseStepSeconds)
        return if(win > minWin) {
            win
        } else {
            minWin
        }
    }
}