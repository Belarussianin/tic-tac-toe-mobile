package com.belarusianin.game.core.interfaces

import com.belarusianin.game.core.Player

sealed interface GameCell {
    object EmptyCell : GameCell {
        override fun toString(): String = " "
    }
    data class OccupiedCell(val player: Player) : GameCell {
        override fun toString(): String = player.toString()
    }
}