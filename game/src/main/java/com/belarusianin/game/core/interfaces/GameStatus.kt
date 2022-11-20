package com.belarusianin.game.core.interfaces

import com.belarusianin.game.core.Player

sealed interface GameStatus {
    object Started : GameStatus {
        override fun toString(): String = "Started"
    }

    object Draw : GameStatus {
        override fun toString(): String = "Draw"
    }

    data class Win(val player: Player) : GameStatus
}