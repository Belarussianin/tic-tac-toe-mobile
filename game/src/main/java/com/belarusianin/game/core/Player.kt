package com.belarusianin.game.core

sealed interface Player {
    data object All : Player
    data object None : Player
    data object X : Player
    data object O : Player

    operator fun not() = when (this) {
        All -> None
        None -> All
        X -> O
        O -> X
    }
}