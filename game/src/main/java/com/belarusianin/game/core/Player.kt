package com.belarusianin.game.core

enum class Player {
    X, O;

    operator fun not() = when (this) {
        X -> O
        O -> X
    }

    override fun toString(): String = when (this) {
        O -> "O"
        X -> "X"
    }
}