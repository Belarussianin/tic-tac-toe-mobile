package com.belarusianin.game.core.interfaces

import com.belarusianin.game.core.Player

sealed interface GameStatus {
    data object Started : GameStatus

    data object Draw : GameStatus

    data class Win(val player: Player) : GameStatus
}