package com.belarusianin.tic_tac_toe_mobile.presentation.tic_tac_toe

import androidx.annotation.DrawableRes
import com.belarusianin.game.core.Player
import com.belarusianin.game.core.interfaces.GameCell
import com.belarusianin.tic_tac_toe_mobile.R

data class Cell(
    private val gameCell: GameCell
) {
    @DrawableRes
    val idRes: Int? = when (gameCell) {
        is GameCell.EmptyCell -> null
        is GameCell.OccupiedCell -> when (gameCell.player) {
            Player.X -> R.drawable.ic_x_48
            Player.O -> R.drawable.ic_o_48
        }
    }

    fun isEmpty(): Boolean = gameCell is GameCell.EmptyCell
    fun isNotEmpty(): Boolean = !isEmpty()

    override fun toString(): String = gameCell.toString()
}
